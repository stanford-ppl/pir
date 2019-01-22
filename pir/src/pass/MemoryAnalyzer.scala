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
    dbgblk(s"compEnqDeq(isFIFO=$isFIFO, out=$from.$out, ins=${ins.map { in => s"${in.src}.$in"}.mkString(",")})") {
      (out, ins) match {
        case (out, List(InputField(to:DRAMDenseCommand, "offset"))) => 
          (ctrlValid(o, octx), to.deqCmd)
        case (out, List(InputField(to:DRAMDenseCommand, "size"))) => 
          (ctrlValid(o, octx), to.deqCmd)
        case (out, List(InputField(to:DRAMSparseCommand, "addr"))) => 
          (ctrlValid(o, octx), to.deqCmd)
        case (out, List(InputField(to:DRAMStoreCommand, "data"))) => 
          (ctrlValid(o, octx), to.deqData)
        case (out, List(InputField(to:FringeDenseStore, "valid"))) => 
          (ctrlValid(o, octx), to.deqData)
        case (out, List(InputField(to:FringeStreamRead, "stream"))) => 
          (ctrlValid(o, octx), to.deqData)
        case (Some(OutputField(from:DRAMLoadCommand, "data")), to) => 
          (from.dataValid, ctrlValid(i, ictx))
        case (Some(OutputField(from:DRAMStoreCommand, "ack")), to) => 
          (from.ackValid, ctrlValid(i, ictx))
        case (Some(OutputField(from:FringeStreamWrite, "stream")), to) => 
          (from.dataValid, ctrlValid(i, ictx))
        case (_,_) if isFIFO =>
          (ctrlValid(o, octx), ctrlValid(i, ictx))
        case (_,_) if o == i =>
          (ctrlDone(o, octx), ctrlDone(i, ictx))
        case _ =>
          val lca = leastCommonAncesstor(o,i).get
          val oAncesstors = o.ancestorTree
          val iAncesstors = i.ancestorTree
          val oidx = oAncesstors.indexOf(lca)
          val iidx = iAncesstors.indexOf(lca)
          // Use def to prevent evaluation outside if statement to prevent idx out of bound
          // in case of one ctrl is ancesstor of another
          def octrl = oAncesstors(oidx-1)
          def ictrl = iAncesstors(iidx-1)
          if (lca == o)      (ctrlValid(o, octx), ctrlDone(ictrl, ictx))
          else if (lca == i) (ctrlDone(octrl, octx), ctrlValid(i, ictx))
          else               (ctrlDone(octrl, octx), ctrlDone(ictrl, ictx))
      }
    }
  }

  def ctrlValid(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get.valid.out
    } else {
       //Distributed controller
      assertOneOrLess(ctx.collectDown[ControllerValid]().filter { _.ctrl.get == ctrl }, 
        s"ctrlValid with ctrl=$ctrl in $ctx").getOrElse {
          assert(this.isInstanceOf[ComputePartitioner], s"$ctx has no ControllerValid for $ctrl")
          within(ctx, ctrl) { allocConst(true) }
        }.out
    }
  }

  def ctrlDone(ctrl:ControlTree, ctx:Context):Output[PIRNode] = {
    if (!compiler.hasRun[DependencyDuplication]) {
      // Centralized controller
      ctrl.ctrler.get.done.out
    } else {
       //Distributed controller
      assertOne(ctx.collectDown[ControllerDone]().filter { _.ctrl.get == ctrl }, 
        s"ctrlDone with ctrl=$ctrl in $ctx").out
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
          dbg(s"allocate[$ct](container=$container) = ${quote(node)}")
          node
        }
    }
  }

  def allocConst(value:Any) = allocate[Const] { c => c.value == value } { Const(value) }
}
