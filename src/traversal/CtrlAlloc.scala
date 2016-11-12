package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.enums._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue

class CtrlAlloc(implicit val design: Design) extends Traversal{
  override def traverse:Unit = {
    bufferAlloc
    logicGen
  } 
  def logicGen = {
    genEnDec
    genTokenOut
    genTokenDown
    connectInputs
    wireCChainCopy
  }
  // Allocate token buffer and credit buffer in all compute units 
  private def bufferAlloc = {
    design.top.compUnits.foreach { cu =>
      val cb = cu.ctrlBox
      if (cu.isHead) {
        // no dep, buffer for parent (all types of controller)
        cb.tokenBuffers += design.top -> TokenBuffer(null, 1)(cu, design)
      } else {
        cu match {
          case cu:StreamPipeline =>
          case cu =>
            /* Allocate tokenBuffer for pipelined inputs */
            val tokenInit = cu.parent match {
              case p:MetaPipeline => p.children.size
              case p:Sequential => 1
            }
            cu.dependencies.foreach { dep =>
              cb.tokenBuffers += dep -> TokenBuffer(dep, tokenInit)(cu, design)
            }
        }
      }
      cu.parent match {
        case p:MetaPipeline =>
          cu.dependeds.foreach { deped =>
            cb.creditBuffers += deped -> CreditBuffer(deped)(cu, design)
          }
        case _ =>
      }
    }
  }
  // Generate logic on control I/Os
  private def genEnDec:Unit = {
    design.top.compUnits.foreach { implicit cu =>
      val cb = cu.ctrlBox
      cu match {
        case cu:Pipeline =>
          val en = cb.innerCtrEn
          val tks = cb.tokenBuffers.map(_._2.out).toList
          val cds = cb.creditBuffers.map(_._2.out).toList
          val ins = tks ++ cds
          val tf = TransferFunction(s"${ins.mkString(s" && ")}") { case (map, inputs) =>
            ins.map(in =>inputs(map(in))).reduce(_ && _)
          }
          EnLUT(cu, ins, tf, en)
          //TODO: remove folling after apps are rewritten
          cu match {
            case tt:TileTransfer if tt.mctpe==TileLoad => 
              val streamcc = tt.streamCChain // TODO: this won't behave correctly
              EnLUT(cu, ins, tf, streamcc.inner.en)
            case _ =>
          }
        case cu:StreamPipeline =>
          val en = cb.innerCtrEn 
          cb.tokInAndTree.addInputs(cu.writtenMem.map(_.notFull))
          var ins = cb.andTree.out::Nil
          if (cu.isHead) {
            val tks = cb.tokenBuffers.map(_._2.out).toList
            assert(tks.size==1)
            ins ++= tks
          } else {
            cb.fifoAndTree.addInputs(cu.mems.map(_.notEmpty))
          }
          val tf = TransferFunction(s"${ins.mkString(s" && ")}") { case (map, inputs) =>
            ins.map(in =>inputs(map(in))).reduce(_ && _)
          }
          val enlut = EnLUT(cu, ins, tf, en)
          cu.writtenMem.foreach{ mem => mem.enqueueEnable.connect(enlut.out) }
          cu.mems.foreach { mem => mem.dequeueEnable.connect(enlut.out) }
        case cu:StreamController =>
          val en = cb.innerCtrEn
          val lasts = cu.children.filter(_.isLast)
          if (lasts.size!=1) throw PIRException("Currently only support a single last stage")
          lasts.head match {
            case child:StreamPipeline => // StreamPipe doesn't have counterchain 
            case child:StreamController => en.connect(child.ctrlBox.outerCtrDone)
          }
        case cu:OuterController =>
          val en = cb.innerCtrEn
          val lasts = cu.children.filter(_.isLast)
          if (lasts.size!=1) throw PIRException("Currently only support a single last stage")
          // Assume OuterController is in the same CU as last stage children 
          val child = lasts.head
          child match {
            case mc:MemoryController => en.connect(mc.dataValid)
            case _ => en.connect(child.ctrlBox.outerCtrDone)
          }
        case mc:MemoryController =>
          //TODO not correct
          mc.writtenMem.foreach{ mem => mem.enqueueEnable.connect(mc.dataValid) }
      }
      cb.tokenBuffers.foreach { case (dep, t) => 
        val done = cu match {
          case mc:MemoryController => mc.ready
          case _ => cb.outerCtrDone
        }
        t.dec.connect(done)
      }
      cb.creditBuffers.foreach { case (dep, c) => c.dec.connect(cb.outerCtrDone) }
    }
  }
  private def genTokenOut:Unit = {
    design.top.compUnits.foreach { implicit cu =>
      val cb = cu.ctrlBox
      if (cu.isUnitStage) {
        cb.tokenOut = None 
      } else if (cu.isInstanceOf[StreamPipeline]) {
        cb.tokenOut = None
      } else {
        cu.parent match {
          case parent @ (_:Sequential | _:MetaPipeline) =>
            if (!cu.isLast) {
              val done = cb.outerCtrDone
              cb.tokenOut = Some(TokenOutLUT.passThrough(cu, done))
            } else {
              //TODO: don't need this tokenout if cu.parent is unit controller
              val c = cu match {
                case mc:MemoryController => mc.ready
                case _ => cb.outerCtrDone
              } 
              val p = parent.asInstanceOf[OuterController].ctrlBox.outerCtrDone
              val ins = c::p::Nil
              val tf = TransferFunction(s"!${p} && ${c}") { case (map, ins) =>
                !ins(map(p)) && ins(map(c))
              }
              cb.tokenOut = Some(TokenOutLUT(cu, ins, tf))
            }
          case t:Top => 
          case _ =>
        }
      }
      // Generate status event when cu is UnitStage 
      cu.parent match {
        case t:Top =>
          val done = cb.outerCtrDone
          t.status.connect(TokenOutLUT.passThrough(cu, done))
        case _ =>
      }
    }
  }
  private def genTokenDown:Unit = {
    val queue = Queue[ComputeUnit]() 
    queue ++= design.top.children
    while (!queue.isEmpty) {
      implicit val cu = queue.dequeue 
      val cb = cu.ctrlBox
      cu match {
        case _:OuterController =>
          if (cu.isHead) {
            val init:CtrlOutPort = cu.parent match {
              case t:Top => t.command 
              case c:OuterController => c.ctrlBox.tokenDown.get
            } 
            val tos = cb.tokenBuffers.map { case (dep, tk) => tk.out }.toList
            val tf = TransferFunction(s"${init} || ${tos.mkString( "&&")}") { case (map, ins) =>
              ins(map(init)) || tos.map{to => ins(map(to))}.reduce(_ && _)
            }
            cb.tokenDown = Some(TokenDownLUT(cu, init::tos, tf))
          } else {
            val tos = cb.tokenBuffers.map { case (dep, tk) => tk.out }.toList
            val tf = TransferFunction(s"${tos.mkString( "&&")}") { case (map, ins) =>
              tos.map(to => ins(map(to))).reduce(_ && _)
            }
            cb.tokenDown = Some(TokenDownLUT(cu, tos, tf))
          }
        case _:InnerController => cb.tokenDown = None 
      }
      queue ++= cu.children
    }
  }
  /* Connect inputs of UpdownCounters */
  private def connectInputs = {
    design.top.compUnits.foreach { cu =>
      val cb = cu.ctrlBox
      if (cu.isHead) {
        val tk = cb.tokenBuffers.head._2
        val lasts = cu.parent.children.filter(_.isLast)
        assert(lasts.size==1) // TODO: Assume a single last stage, which means a single tokenbuffer for first stage
        val last = lasts.head
        cu match {
          case cu:StreamPipeline => // StreamPipeline has no inc on TokenBuffer
          case cu =>
            if (cu!=last) { // Avoid single stage case
              tk.inc.connect(last.ctrlBox.tokenOut.get)
            }
        }
        val tokenDown = cu.parent match {
          case t:Top => t.command
          case c:ComputeUnit => c.ctrlBox.tokenDown.get
          case _ => throw PIRException(s"unknown parent type")
        }
        tk.init.connect(tokenDown)
      } else {
        cu match {
          case cu:StreamPipeline =>
          case cu =>
            cb.tokenBuffers.foreach { case (dep, tk) =>
              tk.inc.connect(dep.asInstanceOf[ComputeUnit].ctrlBox.tokenOut.get)
            }
            cu match {
              case mc:MemoryController =>
                val unitPipe = mc.saddr.writer.ctrler.asInstanceOf[ComputeUnit]
                mc.issue.connect(unitPipe.ctrlBox.tokenOut.get)
              case _ =>
            }
        }
      }
      cu match {
        case cu:StreamPipeline => // StreamPipe doesn't have creditBuffer
        case cu =>
          cb.creditBuffers.foreach { case (deped, cd) =>
            implicit val depedCU = deped.asInstanceOf[ComputeUnit]
            val done = depedCU match {
              case mc:MemoryController => mc.ready
              case _ => depedCU.ctrlBox.outerCtrDone
            }
            cd.inc.connect(TokenOutLUT.passThrough(deped, done)) 
          }
      }
    }
  }

  /* Copy counterchain from inner to outer (exclusive) in current controller and chain them together */
  private def chainCounterChains(current:InnerController, inner:InnerController, outer:OuterController) = {
    val ancestors = inner.ancestors
    var iter = 0
    if (!ancestors.contains(outer)) throw PIRException(s"Ancesstory of $current doesn't contains $outer")
    while (ancestors(iter)!=outer) {
      ancestors(iter) match {
        case cu:StreamPipeline => // No local CounterChain
        case cu:MemoryController => // No local CounterChain
        case cu =>
          val ccc = ancestors(iter) match {
            //TODO: should this be changed with stream counter or normal counter?
            case tt:TileTransfer if (!current.isInstanceOf[TileTransfer] && tt.mctpe==TileLoad) =>
              val streamCopy = current.getCopy(tt.streamCChain)
              streamCopy.inner.en.connect(tt.streamCChain.inner.en.from)
              streamCopy
            case cu => current.getCopy(cu.localCChain)
          }
          val pcc = current.getCopy(ancestors(iter+1).localCChain)
          pcc.inner.en.connect(ccc.outer.done)
          assert(!pcc.inner.en.isConnected || pcc.inner.en.isConnectedTo(ccc.outer.done))
      }
      iter += 1
    }
  }

  private def wireCChainCopy = {
    design.top.innerCUs.foreach { inner =>
      inner.cchains.foreach { cc =>
        if (cc.isCopy) {
          cc.original.ctrler match {
            // Copy of inner controller for write addr calculation
            case cu:InnerController => 
              cc.inner.en.connect(cc.original.inner.en.from)
            // Copy ancesstor outer controller because used in datapath 
            case cu:OuterController if inner.ancestors.contains(cu) => 
              chainCounterChains(inner, inner, cu)
            // Copy outer controller of the writer for write addr calculation
            case cu:OuterController =>
              inner match {
                case inner:InnerComputeUnit =>
                  val srams = inner.srams.filter { sram =>
                    sram.writer.ancestors.contains(cu)
                  }
                  if (srams.size==0)
                    throw PIRException(s"Copyiing non ancestor OuterController CounterChain that's not used for write address calculation ${cc}")
                  val usrams = srams.groupBy {_.writer} 
                  if (usrams.size!=1) {
                    throw PIRException(s"Currently don't support if more than one sram use a single copy")
                  }
                  val (writer, sram::_) = usrams.head
                  chainCounterChains(inner, writer, cu)
                case mc:MemoryController =>
                  throw PIRException(s"MemoryController shouldn't copy counter for write addr calculation ${mc}:${cc}")
              }
          }
        }
      }
    }
  }

  override def finPass = {
    design.top.compUnits.foreach { cu =>
      cu match {
        case cu:OuterController =>
          cu.cchains.foreach{ cc =>
            if (cc.inner.en.isConnected || cc.outer.done.isConnected)
              throw PIRException(s"Outer controller shouldn't connect inner most counter enable or outer most counter done signals ${cu}")
          }
        case cu:InnerController =>
          cu.cchains.flatMap{ _.counters }.foreach { ctr =>
            if (!ctr.en.isConnected) 
              throw PIRException(s"${ctr}'s en in ${ctr.cchain} in ${ctr.ctrler} is not connected")
          }
      }
    }
    info("Finishing control logic allocation")
  }
}
