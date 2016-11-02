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
  def logicGen = {
    genEnDec
    genTokenOut
    genTokenDown
    connectInputs
    wireCChainCopy
  }
  override def traverse:Unit = {
    bufferAlloc
    logicGen
  } 
  // Allocate token buffer and credit buffer in all compute units 
  private def bufferAlloc = {
    design.top.compUnits.foreach { cu =>
      val cb = cu.ctrlBox
      if (cu.isHead) {
        // no dep, buffer for parent
        cb.tokenBuffers += design.top -> TokenBuffer(null, 1)(cu, design)
      } else {
        val tokenInit = cu.parent match {
          case p:MetaPipeline => p.children.size
          case p:Sequential => 1
        }
        cu.dependencies.foreach { dep =>
          cb.tokenBuffers += dep -> TokenBuffer(dep, tokenInit)(cu, design)
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
  def genEnDec:Unit = {
    design.top.compUnits.foreach { implicit cu =>
      val cb = cu.ctrlBox
      cu match {
        case _:InnerController =>
          val en = cb.innerCtrEn
          val tks = cb.tokenBuffers.map(_._2.out).toList
          val cds = cb.creditBuffers.map(_._2.out).toList
          val ins = tks ++ cds
          val tf = TransferFunction(s"${ins.mkString(s" && ")}") { case (map, ins) =>
            (tks ++ cds).map(in =>ins(map(in))).reduce(_ && _)
          }
          EnLUT(cu, ins, tf, en)
          cu match {
            case tt:TileTransfer if tt.mctpe==TileLoad => 
              val streamcc = tt.streamCChain // TODO: this won't behave correctly
              EnLUT(cu, ins, tf, streamcc.inner.en)
            case _ =>
          }
        case cu:OuterController =>
          val en = cu.ctrlBox.innerCtrEn
          val lasts = cu.children.filter(_.isTail)
          if (lasts.size!=1) throw PIRException("Currently only support a single last stage")
          // Assume OuterController is in the same CU as last stage children 
          en.connect(lasts.head.ctrlBox.outerCtrDone)
      }
      cb.tokenBuffers.foreach { case (dep, t) => t.dec.connect(cb.outerCtrDone) }
      cb.creditBuffers.foreach { case (dep, c) => c.dec.connect(cb.outerCtrDone) }
    }
  }
  def genTokenOut:Unit = {
    design.top.compUnits.foreach { implicit cu =>
      val cb = cu.ctrlBox
      if (cu.isUnitStage) {
        cb.tokenOut = None 
      } else {
        cu.parent match {
          case parent:ComputeUnit =>
            if (!cu.isTail) {
              val done = cb.outerCtrDone
              val tf = TransferFunction(s"${done}") { case (map, ins) => ins(map(done)) }
              cb.tokenOut = Some(TokenOutLUT(cu, done::Nil, tf))
            } else {
              //TODO: don't need this tokenout if cu.parent is unit controller
              val c = cb.outerCtrDone
              val p = parent.ctrlBox.outerCtrDone
              val ins = c::p::Nil
              val tf = TransferFunction(s"!${p} && ${c}") { case (map, ins) =>
                !ins(map(p)) && ins(map(c))
              }
              cb.tokenOut = Some(TokenOutLUT(cu, ins, tf))
            }
          case t:Top => 
            //val done = cb.outerCtrDone
            //val tf = TransferFunction(s"${done}") { case (map, ins) => ins(map(done)) }
            //cb.tokenOut = TokenOutLUT(cu, done::Nil, tf)
          case _ =>
        }
      }
    }
  }
  def genTokenDown:Unit = {
    val queue = Queue[ComputeUnit]() 
    queue ++= design.top.children
    while (!queue.isEmpty) {
      implicit val cu = queue.dequeue 
      val cb = cu.ctrlBox
      cu match {
        case _:OuterController =>
          if (cu.isHead) {
            val init:OutPort = cu.parent match {
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
  def connectInputs = {
    design.top.compUnits.foreach { cu =>
      val cb = cu.ctrlBox
      if (cu.isHead) {
        val tk = cb.tokenBuffers.head._2
        val lasts = cu.parent.children.filter(_.isTail)
        assert(lasts.size==1) // TODO: Assume a single last stage, which means a single tokenbuffer for first stage
        val last = lasts.head
        if (cu!=last) { // Avoid single stage case
          tk.inc.connect(last.ctrlBox.tokenOut.get)
        }
        val tokenDown = cu.parent match {
          case t:Top => t.command
          case c:ComputeUnit => c.ctrlBox.tokenDown.get
          case _ => throw PIRException(s"unknown parent type")
        }
        tk.init.connect(tokenDown)
      } else {
        cb.tokenBuffers.foreach { case (dep, tk) =>
          tk.inc.connect(dep.asInstanceOf[ComputeUnit].ctrlBox.tokenOut.get)
        }
      }
      cb.creditBuffers.foreach { case (deped, cd) =>
        implicit val depedCU = deped.asInstanceOf[ComputeUnit]
        val done = depedCU.ctrlBox.outerCtrDone
        val tf = TransferFunction(s"${done}") { case (map, ins) => ins(map(done)) }
        cd.inc.connect(TokenOutLUT(deped, done::Nil, tf)) 
      }
    }
    design.top.status.connect(design.top.children.head.ctrlBox.outerCtrDone)
  }

  private def chainCounterChains(current:InnerController, inner:InnerController, outer:OuterController) = {
    val ancestors = inner.ancestors
    var iter = 0
    while (iter!=ancestors.size && ancestors(iter)!=outer) {
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
      iter += 1
    }
  }

  def wireCChainCopy = {
    design.top.innerCUs.foreach { inner =>
      inner.cchains.foreach { cc =>
        if (cc.isCopy) {
          cc.original.ctrler match {
            case cu:InnerController => // Copy of inner controller for write addr calculation
              cc.inner.en.connect(cc.original.inner.en.from)
            case cu:OuterController if inner.ancestors.contains(cu) =>
              chainCounterChains(inner, inner, cu)
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
