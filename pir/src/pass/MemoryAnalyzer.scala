package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._

trait MemoryAnalyzer extends PIRTransformer { self:BufferAnalyzer =>

  def insertToken(fctx:Context, tctx:Context):TokenRead = {
    val fctrl = fctx.ctrl.get
    val tctrl = tctx.ctrl.get
    dbgblk(s"InsertToken(fctx=$fctx($fctrl), tctx=$tctx($tctrl))") {
      val (enq, deq) = compEnqDeq(isFIFO=false, fctx, tctx, None, Nil)
      val write = within(fctx, fctrl) {
        allocate[TokenWrite](_.done.isConnectedTo(enq)) {
          TokenWrite().done(enq)
        }
      }
      dbg(s"add $write")
      within(tctx, tctrl) {
        allocate[TokenRead](read => read.in.isConnectedTo(write.out) && read.done.isConnectedTo(deq)) {
          TokenRead().in(write).done(deq)
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
      (o, i) match {
        case (o,i) if isFIFO => (valid(o, octx), valid(i, ictx))
        case (o,i) if o == i => (done(o, octx), done(i, ictx))
        case (o,i) =>
          val lca = leastCommonAncesstor(o,i).get
          val oAncesstors = o.ancestorTree
          val iAncesstors = i.ancestorTree
          val oidx = oAncesstors.indexOf(lca)
          val iidx = iAncesstors.indexOf(lca)
          // Use def to prevent evaluation outside if statement to prevent idx out of bound
          // in case of one ctrl is ancesstor of another
          def octrl = oAncesstors(oidx-1)
          def ictrl = iAncesstors(iidx-1)
          if (lca == o)      (childDone(o, octx), done(ictrl, ictx))
          else if (lca == i) (done(octrl, octx), childDone(i, ictx))
          else               (done(octrl, octx), done(ictrl, ictx))
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

  def allocConst(value:Any) = allocate[Const] { c => 
    c.value == value &&
    stackTop[Ctrl].fold(true) { ctrl => c.getCtrl == ctrl }
  } { 
    Const(value)
  }

}
