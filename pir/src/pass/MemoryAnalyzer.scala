package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._

trait MemoryAnalyzer extends PIRPass with Transformer {

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
    val to = ins.map { _.src }.distinct
    val o = octx.ctrl.get
    val i = ictx.ctrl.get
    dbgblk(s"compEnqDeq(isFIFO=$isFIFO, o=$o, i=$i, out=$from.$out, ins=${ins.map { in => s"${in.src}.$in"}.mkString(",")})") {
      val deq = out match {
        case Some(OutputField(from:BankedRead, _)) => Some(from.valid)
        case Some(OutputField(from:DRAMLoadCommand, "data")) => Some(from.dataValid)
        case Some(OutputField(from:DRAMStoreCommand, "ack")) => Some(from.ackValid)
        case Some(OutputField(from:FringeStreamWrite, "stream")) => Some(from.dataValid)
        case _ => None
      }
      val enq = ins match {
        case List(InputField(to:DRAMDenseCommand, "offset")) => Some(to.deqCmd)
        case List(InputField(to:DRAMDenseCommand, "size")) => Some(to.deqCmd)
        case List(InputField(to:DRAMSparseCommand, "addr")) => Some(to.deqCmd)
        case List(InputField(to:DRAMStoreCommand, "data")) => Some(to.deqData)
        case List(InputField(to:FringeDenseStore, "valid")) => Some(to.deqData)
        case List(InputField(to:FringeStreamRead, "stream")) => Some(to.deqData)
        case List(InputField(to:Access, _)) => Some(within(i, ictx) { allocConst(true).out })
        case List(InputField(to:TokenWrite, _)) => Some(within(i, ictx) { allocConst(true).out })
        case List(InputField(to:TokenRead, _)) => Some(within(i, ictx) { allocConst(true).out })
        case ins => None
      }
      (o, i) match {
        case (o,i) if isFIFO => (deq.getOrElse(ctrlValid(o, octx)), enq.getOrElse(ctrlValid(i, ictx)))
        case (o,i) if o == i => (deq.getOrElse(ctrlDone(o, octx)), enq.getOrElse(ctrlDone(i, ictx)))
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
          if (lca == o)      (deq.getOrElse(ctrlValid(o, octx)), enq.getOrElse(ctrlDone(ictrl, ictx)))
          else if (lca == i) (deq.getOrElse(ctrlDone(octrl, octx)), enq.getOrElse(ctrlValid(i, ictx)))
          else               (deq.getOrElse(ctrlDone(octrl, octx)), enq.getOrElse(ctrlDone(ictrl, ictx)))
      }
    }
  }

  def ctrlValid(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get.valid
    } else {
       //Distributed controller
      assertOneOrLess(ctx.ctrlers.filter { _.getCtrl == ctrl }, 
        s"ctrlValid with ctrl=$ctrl in $ctx").map { _.valid }.getOrElse {
          assert(this.isInstanceOf[CUPruner], s"$ctx has no Controller for $ctrl")
          within(ctx, ctrl) { allocConst(true).out }
        }
    }
  }

  def ctrlDone(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get.done
    } else {
      //Distributed controller
      ctx.ctrler(ctrl).done
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

  def allocConst(value:Any) = allocate[Const] { c => c.value == value } { Const(value) }
}
