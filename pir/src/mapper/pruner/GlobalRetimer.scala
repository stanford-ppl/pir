package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import spade.param._

trait GlobalRetimer extends PIRTransformer with CUCostUtil {
  private val traversal = new PIRTraversal with BFSTopologicalTraversal with UnitTraversal { 
    val forward = false // Reverse to schedule nodes as late as possible. Assume small fanins from external
    override def visitIn(n:N) = visitLocalIn(n)
    override def visitOut(n:N) = visitLocalOut(n)
    override def visitNode(n:N, prev:Unit) = {
      val depeds = visitOut(n).filter(withinScope)
      val depedDelays = depeds.map { deped => deped.delay.get }
      val delay = depedDelays.minOptionBy { i => i }.map { min => min - numStage }.getOrElse(0)
      n.delay := delay
    }
    var numStage:Int = 0
  }

  def retimeGlobal (scope:List[GlobalContainer], numStage:Int):List[GlobalContainer] = 
    if (!config.enableGlobalRetiming) Nil else dbgblk(s"retimeGlobal") {
      val scopeSet = scope.toSet
      if (scopeSet.exists { _.delay.v.isEmpty }) {
        traversal.resetTraversal
        traversal.numStage = numStage
        traversal.traverseScope(scope, ())
      }
      val externInDelay = scope.toStream.map { _.delay.get }.min - numStage
      val externOutDelay = scope.toStream.map { _.delay.get }.max + numStage
      val exDelays = (externInDelay, externOutDelay)
      val fifoDepth = assertUnify(spadeParam.traceIn[FIFOParam], "fifoParam") { _.depth }.get
      val sramParam = assertIdentical(spadeParam.traceIn[PMUParam], "PMUParam").get.sramParam
      within(pirTop) {
        // A map between outputs (internal and external) to internal inputs
        val outIns = scope.map { n => 
          val from = n.depsFrom.filter { case (out, ins) => 
            needRetime(out, n, exDelays, fifoDepth, numStage)
          }
          if (config.enableRetimeExout) {
            val externTo = n.depedsTo.map { case (out, ins) =>
              out -> ins.filter { in => 
                !scopeSet.contains(in.src.global.get) && 
                needRetime(out, in.src.global.get, exDelays, fifoDepth, numStage)
              }
            }
            sumMap(from, externTo) { _ ++ _ }
          } else from
        }.reduce { sumMap(_,_) { _ ++ _ } }
        // Inserting retiming ops for each output
        val delayOps = outIns.map { case (out, ins) =>
          out -> retimeOutput(out, ins, exDelays, fifoDepth, numStage, sramParam)
        }.toMap
        // Put retiming ops into retiming containers. Share container for outputs from the same context
        delayOps.groupBy { case (out, delayOps) => out.src.global.get
        }.flatMap { case (dep, group) =>
          val delayOps = group.flatMap { case (out, delayOps) => delayOps }
          val layers = delayOps.groupBy { _.delay.get }.toSeq.sortBy { _._1 }
          val retimeGlobs = layers.flatMap { case (delay, ops) =>
            val retimeCtxs = ListBuffer[Context]()
            ops.foreach { op =>
              val retimeCtx = allocateCtx(retimeCtxs, op, sramParam)
              swapParent(op, retimeCtx)
              op.in.singleConnected.get.src match {
                case write:BufferWrite =>
                  withLive(write) {
                    swapConnection(op.in, write.out, write.data.singleConnected.get)
                    bufferInput(op.in).foreach { buffer =>
                      val bufferWrite = buffer.inAccess.as[BufferWrite]
                      bufferWrite.data.presetVecMeta.mirror(write.data.presetVecMeta)
                      bufferWrite.data.vecMeta.reset
                      bufferWrite.out.presetVecMeta.mirror(write.out.presetVecMeta)
                      bufferWrite.out.vecMeta.reset
                      transferLocalAccess(write, bufferWrite)
                      bufferWrite.en(write.en.connected)
                    }
                  }
                  free(write)
                case delay:DelayOp => bufferInput(op.in)
              }
            }
            //breakPoint(s"add $retimeGlob")
            retimeCtxs.map { _.global.get }
          }.toList
          delayOps.foreach { op =>
            op.out.connected.foreach {
              case in@InputField(read:BufferRead, _) =>
                val ins = read.out.connected
                withLive(read) {
                  swapOutput(read.out, op.out)
                  ins.foreach { in =>
                    bufferInput(in).foreach { bufferRead =>
                      bufferRead.out.presetVecMeta.mirror(read.out.presetVecMeta)
                      bufferRead.out.vecMeta.reset
                      transferLocalAccess(read, bufferRead)
                      bufferRead.en(read.en.connected)
                      in.vecMeta.reset
                      in.inferVec
                    }
                  }
                }
                free(read)
              case _ =>
            }
            op.delay.reset
          }
          retimeGlobs.map { glob =>
            val kcost = glob.getCost[SRAMCost]
            val vcost = sramParam.getCost[SRAMCost]
            if (!fit(kcost,vcost)) {
              bug(s"Retime cu $glob cost = $kcost vcost=$vcost")
            }
            glob
          }
        }.toList
      }
    }

  private def allocateCtx(pool:ListBuffer[Context], op:DelayOp, sramParam:SRAMParam):Context = {
    pool.find { ctx => 
      fit(op.getCost[SRAMCost] + ctx.getCost[SRAMCost], sramParam.getCost[SRAMCost])
    }.getOrElse {
      val retimeGlob = op match {
        case op:Delay => stage(ComputeContainer())
        case op:ScratchpadDelay => stage(MemoryContainer())
      }
      val ctx = within(retimeGlob,op.getCtrl) { stage(Context().streaming(true)) }
      pool += ctx
      ctx
    }
  }

  private def getDepDelay(out:Output[PIRNode], exDelays:(Int,Int)) = {
    val (externInDelay, externOutDelay) = exDelays
    out.src match {
      case src:DelayOp => src.delay.get
      case src => src.global.get.delay.v.getOrElse(externInDelay)
    }
  }

  private def getDepedDelay(deped:GlobalContainer, exDelays:(Int,Int)) = {
    val (externInDelay, externOutDelay) = exDelays
    deped.delay.v.getOrElse(externOutDelay)
  }

  // Do a filter on ins to filter out inputs that requires retiming
  private def needRetime(out:Output[PIRNode], deped:GlobalContainer, exDelays:(Int,Int), fifoDepth:Int, numStage:Int):Boolean = {
    out.src match {
      case write:BufferWrite if !write.isFIFO => return false
      case write:TokenWrite => return false
      case _ =>
    }
    val depedDelay = getDepedDelay(deped,exDelays)
    val depDelay = getDepDelay(out, exDelays)
    val diff = depedDelay - depDelay - numStage
    diff > fifoDepth
  }

  private def retimeOutput(out:Output[PIRNode], ins:Vector[Input[PIRNode]], exDelays:(Int,Int), fifoDepth:Int, numStage:Int, sramParam:SRAMParam):List[DelayOp] = {
    val depDelay = getDepDelay(out, exDelays)
    val toretime = ins.filter { in => 
      val deped = in.src.global.get
      needRetime(out, deped, exDelays, fifoDepth, numStage)
    }
    if (toretime.isEmpty) return Nil
    val depedDelay = toretime.toStream.map { in => getDepedDelay(in.src.global.get, exDelays) }.min
    val delayDiff = depedDelay - depDelay - numStage
    dbg(s"delayDiff=$delayDiff")
    val sramDepth = sramParam.sizeInWord / out.getVec
    val retimeDepth = if (delayDiff > fifoDepth && !config.retimeBufferOnly) sramDepth else fifoDepth
    // The earliest time to schedule this retime node is after its previous node and before any used
    // node
    val opdelay = math.min(depDelay + retimeDepth, depedDelay - 1)
    val d = within(out.src.getCtrl) { 
      if (retimeDepth <= fifoDepth) {
        stage(Delay(retimeDepth).in(out).delay(opdelay))
      } else {
        stage(ScratchpadDelay(retimeDepth).in(out).delay(opdelay))
      }
    }
    toretime.foreach { in =>
      swapConnection(in, out, d.out)
    }
    d :: retimeOutput(d.out, toretime, exDelays, fifoDepth, numStage, sramParam)
  }

}
