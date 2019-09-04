package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._

trait MemoryAnalyzer { self:PIRTransformer =>

  def insertToken(fctx:Context, tctx:Context):TokenRead = {
    val isFIFO = false
    val fctrl = fctx.ctrl.get
    val tctrl = tctx.ctrl.get
    dbgblk(s"InsertToken(fctx=$fctx($fctrl), tctx=$tctx($tctrl))") {
      val (enq, deq) = compEnqDeq(isFIFO=isFIFO, fctx, tctx, None, Nil)
      val write = within(fctx, enq.src.getCtrl) {
        allocate[TokenWrite](_.done.isConnectedTo(enq)) {
          stage(TokenWrite().done(enq))
        }
      }
      within(tctx, deq.src.getCtrl) {
        allocate[TokenRead](read => read.in.isConnectedTo(write.out) && read.done.isConnectedTo(deq)) {
          stage(TokenRead().in(write).done(deq))
        }
      }
    }
  }

  def compEnqDeq(isFIFO:Boolean, octx:Context, ictx:Context, out:Option[Output[PIRNode]], ins:Seq[Input[PIRNode]]):(Output[PIRNode], Output[PIRNode]) = {
    val from = out.map { _.src }
    val o = octx.ctrl.get
    val i = ictx.ctrl.get
    dbgblk(s"compEnqDeq(isFIFO=$isFIFO, o=${dquote(o)}, i=${dquote(i)})") {
      dbg(s"out=$from.$out")
      dbg(s"ins=${ins.map { in => s"${in.src}.$in"}.mkString(",")}")
      (out, ins) match {
        case (out,ins) if isFIFO => (valid(o, octx), valid(i, ictx))
        case (out,Seq(InputField(n:LoopController, "stopWhen"))) if o == i => (childDone(o, octx), childDone(i, ictx))
        case (out,ins) if o == i => (done(o, octx), done(i, ictx)) // This should be childDone other than hack for block reduce token
        case (out,ins) =>
          val lca = leastCommonAncesstor(o,i).get
          val oAncesstors = o.ancestorTree
          val iAncesstors = i.ancestorTree
          val oidx = oAncesstors.indexOf(lca)
          val iidx = iAncesstors.indexOf(lca)
          // Use def to prevent evaluation outside if statement to prevent idx out of bound
          // in case of one ctrl is ancesstor of another
          def octrl = oAncesstors(oidx-1)
          def ictrl = iAncesstors(iidx-1)
          val enq = if (lca == o) childDone(o, octx) else done(octrl, octx)
          val deq = if (lca == i) childDone(i, ictx) else done(ictrl, ictx)
          dbg(s"enqCtrl=${enq.src.getCtrl} deqCtrl=${deq.src.getCtrl}")
          (enq,deq)
      }
    }
  }

  def valid(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    if (ctx.streaming.get) {
      within(ctx, ctrl) { allocConst(true).out }
    } else if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get.valid
    } else {
       //Distributed controller
      //assertOneOrLess(ctx.ctrlers.filter { _.getCtrl == ctrl }, 
        //s"$ctrl.valid in $ctx").map { _.valid }.getOrElse {
          ////assert(this.isInstanceOf[CUPruner], s"$ctx has no Controller for $ctrl")
          //val c = within(ctx, ctrl) { allocConst(true) }
          //dbg(s"$ctx has no Controller for $ctrl. Use $c instead")
          //c.out
        //}
      ctx.getCtrler(ctrl).valid
    }
  }

  def done(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get.done
    } else {
      //Distributed controller
      ctx.getCtrler(ctrl).done
    }
  }

  def childDone(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get.childDone
    } else {
      //Distributed controller
      ctx.getCtrler(ctrl).childDone
    }
  }

  def allocate[T<:PIRNode:ClassTag:TypeTag](
    filter:T => Boolean = (n:T) => true
  )(newNode: => T):T = {
    val ct = implicitly[ClassTag[T]]
    val container = stackTop[PIRParent].getOrElse(throw PIRException(s"allocate[$ct] outside PIRParent env")).as[PIRNode]
    (container, classTag[T]) match {
      case (container:Top, ct) if ct == classTag[Const] => newNode // allocation is too expensive performance-wise, just get a new one
      case (container, ct) if ct == classTag[Const] => 
        container.children.find { case c:T => filter(c); case _ => false }.getOrElse { newNode }.as[T]
      case _ =>
        val nodes = container.collectDown[T]().filter(filter)
        assertOneOrLess(nodes, s"$ct under $container").getOrElse {
          val node = within(container) { newNode }
          dbg(s"allocate[$ct](container=$container) = ${dquote(node)}")
          node
        }
    }
  }

  private def equalValue(a:Any, b:Any):Boolean = {
    (a,b) match {
      case (a:Iterable[_], b:Iterable[_]) => a.size == b.size && (a,b).zipped.forall { (a,b) => equalValue(a,b) }
      case (a,b) => a == b && (a.getClass == b.getClass)
    }
  }

  def allocConst(value:Any) = allocate[Const] { c => 
    equalValue(c.value,value) &&
    stackTop[Ctrl].fold(true) { ctrl => c.getCtrl == ctrl }
  } { 
    Const(value)
  }

}
