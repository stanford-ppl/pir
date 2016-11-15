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
        cb.tokenBuffers += design.top -> TokenBuffer(None, 1)(cu, design)
      } else {
        cu match {
          case cu:StreamPipeline =>
          case cu =>
            /* Allocate tokenBuffer for pipelined inputs */
            val tokenInit = cu.parent match {
              case p:MetaPipeline => p.height 
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
      val en = cu.ctrlBox.innerCtrEn
      val cb = cu.ctrlBox
      cu match {
        case cu:InnerController if (cu.parent.isInstanceOf[StreamController]) => 
          cu match {
            case mc:MemoryController =>
              en.connect(mc.done)
              mc.writtenMem.foreach{ mem => mem.enqueueEnable.connect(mc.dataValid) }
            case cu =>
              val ins = ListBuffer[CtrlOutPort]()
              val readMems = cu.mems.collect{ case f:FIFOOnRead => f }
              val writtenMems = cu.writtenMem.collect{ case f:FIFOOnWrite => f}
              if (readMems.size!=0) {
                ins += cb.andTree.out
                cb.fifoAndTree.addInputs(readMems.map(_.notEmpty))
              }
              if (cu.isHead) {
                val tks = cb.tokenBuffers.map(_._2.out).toList
                assert(tks.size==1)
                ins ++= tks
              }
              val tf = TransferFunction(s"${ins.mkString(s" && ")}") { case (map, inputs) =>
                ins.map(in => inputs(map(in))).reduce(_ && _)
              }
              val enlut = EnLUT(cu, ins.toList, tf, en)
              writtenMems.foreach{ mem => mem.enqueueEnable.connect(enlut.out) }
              readMems.foreach { mem => mem.dequeueEnable.connect(enlut.out)}
              cb.tokInAndTree.addInputs(writtenMems.map(_.notFull))
          }
        case cu:Pipeline =>
          val tks = cb.tokenBuffers.map(_._2.out).toList
          val cds = cb.creditBuffers.map(_._2.out).toList
          val ins = tks ++ cds
          val tf = TransferFunction(s"${ins.mkString(s" && ")}") { case (map, inputs) =>
            ins.map(in =>inputs(map(in))).reduce(_ && _)
          }
          val enlut = EnLUT(cu, ins, tf, en)
        case cu:StreamController => // enable should be connected during connection in last children
        case cu:OuterController =>
          val lasts = cu.children.filter(_.isLast)
          if (lasts.size!=1) throw PIRException("Currently only support a single last stage")
          // Assume OuterController is in the same CU as last stage children 
          val child = lasts.head
          child match {
            case mc:MemoryController => en.connect(mc.dataValid)
            case _ => en.connect(child.ctrlBox.outerCtrDone) 
          }
      }

      // MC and StreamPipeline other than the first stage shouldn't have tokenBuffer
      cb.tokenBuffers.foreach { case (dep, t) => t.dec.connect(cb.outerCtrDone) }
      // MC and StreamPipeline shouldn't have creditBuffer 
      cb.creditBuffers.foreach { case (dep, c) => c.dec.connect(cb.outerCtrDone) }
    }
    val lasts = design.top.children.filter(_.isLast)
    if (lasts.size!=1) throw PIRException("Currently only support a single last stage")
    // Assume OuterController is in the same CU as last stage children 
    val child = lasts.head
    design.top.status.connect(TokenOutLUT.passThrough(child, child.ctrlBox.outerCtrDone))
  }

  private def genTokenOut:Unit = {
    design.top.compUnits.foreach { implicit cu =>
      val cb = cu.ctrlBox
      if (cu.isUnitStage) {
        cb.tokenOut = None 
      } else {
        cu.parent match {
          case p:StreamController =>
            cb.tokenOut = None
          case parent:OuterController =>
            if (!cu.isLast) {
              val done = cb.outerCtrDone
              cb.tokenOut = Some(TokenOutLUT.passThrough(cu, done))
            } else {
              val c = cb.outerCtrDone
              val p = parent.ctrlBox.outerCtrDone
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
      // TokenBuffer
      if (cu.isHead) {
        val tk = cb.tokenBuffers.head._2
        val lasts = cu.parent.children.filter(_.isLast)
        assert(lasts.size==1) // TODO: Assume a single last stage, which means a single tokenbuffer for first stage
        val last = lasts.head
        cu match {
          case cu:StreamPipeline => // StreamPipeline has no inc on TokenBuffer
          case cu:Pipeline if (cu.parent.isInstanceOf[StreamController]) =>
          case cu =>
            if (cu!=last) { // Avoid single stage case
              tk.inc.connect(last.ctrlBox.tokenOut.get)
            }
        }
        val tokenDown = cu.parent match {
          case t:Top => t.command
          case c:ComputeUnit => c.ctrlBox.tokenDown.get
        }
        tk.init.connect(tokenDown)
      } else {
        cu match {
          case cu:StreamPipeline =>
          case cu =>
            cb.tokenBuffers.foreach { case (dep, tk) =>
              tk.inc.connect(dep.asInstanceOf[ComputeUnit].ctrlBox.tokenOut.get)
            }
        }
      }
      // CreditBuffer 
      cu match {
        case cu:StreamPipeline => // StreamPipe doesn't have creditBuffer
        case cu =>
          cb.creditBuffers.foreach { case (deped, cd) =>
            implicit val depedCU = deped.asInstanceOf[ComputeUnit]
            cd.inc.connect(TokenOutLUT.passThrough(deped, depedCU.ctrlBox.outerCtrDone)) 
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
        case cu =>
          val ccc = current.getCopy(cu.localCChain)
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
