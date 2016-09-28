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
            ins.reduce(_ && _)
          }
          EnLUT(cu, ins, tf, en)
          cu match {
            case tt:TileTransfer if tt.mctpe==TileLoad => 
              val streamcc = tt.streamCChain // TODO: this won't behave correctly
              EnLUT(cu, ins, tf, streamcc.inner.en)
            case _ =>
          }
        case cu:OuterController =>
          val en = cu.localCChain.inner.en
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
              ins(map(init)) || tos.map(to => ins(map(to))).reduce(_ && _)
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
    design.top.compUnits.foreach { implicit cu =>
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
        val done = deped.asInstanceOf[ComputeUnit].ctrlBox.outerCtrDone
        val tf = TransferFunction(s"${done}") { case (map, ins) => ins(map(done)) }
        cd.inc.connect(TokenOutLUT(deped, done::Nil, tf)) 
      }
    }
    design.top.status.connect(design.top.children.head.ctrlBox.outerCtrDone)
  }
  def wireCChainCopy = {
    design.top.innerCUs.foreach { inner =>
      inner.cchains.foreach { cc =>
        if (cc.isCopy) {
          assert(!cc.inner.en.isConnected, s"${cc} ${cc.inner.en.from}")
          cc.original.ctrler match {
            case cu:InnerController =>
              cc.inner.en.connect(cc.original.inner.en.from)
            case cu:OuterController =>
              var child:ComputeUnit = inner
              while (child.parent!=cu) {
                val parent = child.parent.asInstanceOf[ComputeUnit]
                val plocal = inner.getCopy(parent.localCChain)
                val clocal = inner.getCopy(child.localCChain)
                plocal.inner.en.connect(clocal.outer.done)
                child = child.parent.asInstanceOf[ComputeUnit]
                if (child.parent.isInstanceOf[Top]) {
                  throw PIRException(s"${inner} made cchain copy ${cc} of non-ancestor outer controller ${cu}")
                }
              }
              val clocal = inner.getCopy(child.localCChain)
              cc.inner.en.connect(clocal.outer.done)
          }
        }
      }
    }
  }
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
  override def finPass = {
    info("Finishing control logic allocation")
  }
}
