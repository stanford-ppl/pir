package pir
package pass

import prism.util._
import pir.node._

trait RuntimeUtil extends ConstantPropogator with PIRNodeUtil with ScalaUtil { self:Logging =>
  val pirmeta:PIRMetadata
  val spademeta:SpadeMetadata
  import pirmeta._
  import spademeta._

  def minByWithBound[A,B:Ordering](list:Iterable[A], bound:B)(lambda:A => B):B = {
    list.foldLeft[Option[B]](None) { 
      case (Some(`bound`), x) => Some(bound)
      case (Some(currMin), x) => Some(List(currMin, lambda(x)).min)
      case (None, x) => Some(lambda(x))
    }.getOrElse(bound)
  }

  def getParOf(x:Controller):Int = parOf.getOrElseUpdate(x) {
    dbgblk(s"$x.par") {
      x match {
        case n:ForeverController => 1
        case x:UnitController => 1
        case x:TopController => 1
        case x:LoopController => 
          val cc = ctrlOf.bmap(x).collect { case cc:CounterChain => cc }.head
          getParOf(cc)
        case x:ArgInController => 1
        case x:ArgOutController => 1
        case DramController(size, par) => par
      }
    }
  }

  /*
   * For controller, itersOf is the number of iteration the current controller runs before saturate
   * */
  def getItersOf(n:Controller):Option[Long] = itersOf.getOrElseUpdate(n) {
    dbgblk(s"$n.iters") {
      n match {
        case x:ForeverController => throw PIRException(s"ForeverController doesn't have iter")
        case x:UnitController => Some(1)
        case x:TopController => Some(1)
        case x:LoopController => 
          val cc = ctrlOf.bmap(x).collect { case cc:CounterChain => cc }.head
          getItersOf(cc)
        case x:ArgInController => Some(1)
        case x:ArgOutController => Some(1)
        case DramController(size, par) => 
          size.map { size =>
            val wordSize = size / 4
            wordSize / par
          }
      }
    }
  }

  def getCountsOf(n:Controller):Option[Long] = countsOf.getOrElseUpdate(n) {
    dbgblk(s"$n.counts") { 
      n match {
        case ctrl:ArgInController => Some(1l)
        case ctrl:ForeverController => 
          val inMems = inMemsOf(ctrl).filter { m => isFIFO(m) }
          dbg(s"inMems=$inMems")
          val memCounts = inMems.flatMap { m => 
            assertOptionUnify(inAccessesOf(m), s"$m inAccesses.ctrl.count") { a => getCountsOf(ctrlOf(a)) }
          }
          assertIdentical(memCounts, s"counts for $ctrl") 
        case n:Controller if n.ancestors.exists(isForever) && n.children.isEmpty =>
          val forever = n.ancestors.filter(isForever).head
          dbg(s"Under forever. Is leaf")
          getCountsOf(forever)
        case n:Controller if n.ancestors.exists(isForever) =>
          assertOptionUnify(n.children, s"counts for $n") { c =>
            zipMap(getCountsOf(c), getItersOf(c)) { case (c,i) => c / i }
          }
        case n:Controller =>
          val ancestors = (n::n.ancestors)
          val ancestorIters = ancestors.map { n => getItersOf(n) }
          dbg(s"ancestors=$ancestors")
          dbg(s"ancestorIters=$ancestorIters")
          ancestorIters.reduce[Option[Long]]{
            case (Some(iter1), Some(iter2)) => Some(iter1 * iter2)
            case _ => None
          }
      }
    }
  }


  def getParOf(x:PIRNode):Int = parOf.getOrElseUpdate(x) {
    dbgblk(s"$x.par") {
      x match {
        case x:ControlNode => 1
        case x:Counter => x.par
        case x:CounterChain => x.counters.map(getParOf).product
        case Def(n, ReduceOp(op, input)) => getParOf(input) / 2 
        case n:AccumOp => 1
        case n:ReduceAccumOp => 1
        case n:Container => n.children.map { d => getParOf(d) }.max
        case x:LocalLoad => getParOf(ctrlOf(x))
        case x:CounterIter if ctrlOf(x).isInnerControl => getParOf(ctrlOf(x))
        case x:CounterIter => 1
        case x:ProcessDramDenseLoad => getParOf(ctrlOf(x))
        case x:ProcessDramDenseStore => 1 // output ack
        case x:ProcessDramSparseLoad => getParOf(ctrlOf(x))
        case x:ProcessDramSparseStore => 1 // output ack
        case x:Primitive => 
          if (x.deps.isEmpty) {
            getParOf(ctrlOf(x))
          } else {
            x.deps.map { dep => getParOf(dep) }.max
          }
      }
    }
  }

  /*
   * For PIR nodes, itersOf is iteration interval between activation of the nodes with respect to
   * local contextEnable
   * */
  def getItersOf(n:PIRNode):Option[Long] = itersOf.getOrElseUpdate(n) {
    dbgblk(s"$n.iters") {
      n match {
        case Def(n, CounterChain(counters)) => flatReduce(counters.map(getItersOf)) { _ * _ }
        case Def(ctr:Counter, Counter(min, max, step, par)) =>
          val cmin = getBoundAs[Int](min, logger=Some(this))
          val cmax = getBoundAs[Int](max, logger=Some(this))
          val cstep = getBoundAs[Int](step, logger=Some(this))
          dbg(s"ctr=${quote(ctr)} cmin=$cmin, cmax=$cmax, cstep=$cstep par=$par")
          zipMap(cmin, cmax, cstep) { case (cmin, cmax, cstep) =>
            if ((cmax - cmin) % (cstep * par) != 0)
              warn(s"(max=$cmax - min=$cmin) % (step=$cstep * par=$par) != 0 for ${quote(ctr)}")
            (cmax - cmin) / (cstep * par)
          }
        case n:ProcessDramCommand => getItersOf(ctrlOf(n))
        case n:DramControllerDone => getItersOf(ctrlOf(n))
        case n:Primitive => Some(1l)
      }
    }
  }


  def getScaleOf(n:PIRNode):Option[Long] = scaleOf.getOrElseUpdate(n) {
    dbgblk(s"$n.scale") {
      n match {
        case n:ContextEnable => Some(1l)
        case n:ForeverControllerDone => getCountsOf(ctrlOf(n))
        case n =>
          val en = enableOf(n)
          val enScale = en.map { en => getScaleOf(en) }.getOrElse(Some(1l))
          zipMap(enScale, getItersOf(n), s"$en.scale * $n.iters") { _ * _ }
      }
    }
  }

  def enableOf(n:PIRNode):Option[Primitive] = {
    n match {
      case n:LocalAccess => Some(accessNextOf(n))
      case n:Counter => n.getEnable
      case Def(n, DataValid(gin)) => enableOf(gin)
      case n:ControlNode => 
        assert(n.deps.size==1, s"$n has more than 1 dep")
        Some(n.deps.head)
      case n:GlobalInput => goutOf(n).flatMap(enableOf) 
      case n:GlobalOutput => Some(validOf(n))
    }
  }

  def getCountsOf(n:PIRNode):Option[Long] = countsOf.getOrElseUpdate(n) {
    dbgblk(s"$n.counts") { 
      n match {
        case n:ContextEnable => getCountsOf(ctrlOf(n))
        case n:Memory => assertUnify(inAccessesOf(n), s"${inAccessesOf(n)}.counts") { a => getCountsOf(a) }.get
        case n:Primitive => zipMap(getCountsOf(ctxEnOf(n).get), getScaleOf(n), s"${ctxEnOf(n).get}.counts / $n.scale") { _ / _ }
      }
    }
  }

  def zipMap[A,B,T](a:Option[A], b:Option[B], info:String)(lambda:(A,B) => T):Option[T] = {
    val res = super.zipMap(a,b)(lambda)
    dbg(s"$info: $a zip $b => $res")
    res
  }
  
}
