package pir
package pass

import pir.node._

trait RuntimeUtil extends ConstantPropogator { self:PIRPass =>
  import pirmeta._
  import spademeta._

  def minByWithBound[A,B:Ordering](list:Iterable[A], bound:B)(lambda:A => B):B = {
    list.foldLeft[Option[B]](None) { 
      case (Some(`bound`), x) => Some(bound)
      case (Some(currMin), x) => Some(List(currMin, lambda(x)).min)
      case (None, x) => Some(lambda(x))
    }.getOrElse(bound)
  }

  def fifoLoadAccessOf(n:PIRNode) = loadAccessesOf(n).filter { read => memsOf(read).forall(isFIFO) }
  def fifoLoadAccessOf(n:Controller) = loadAccessesOf(n).filter { read => memsOf(read).forall(isFIFO) }

  def getParOf(x:Controller):Int = parOf.getOrElseUpdate(x) {
    dbgblk(s"getParOf($x)") {
      x match {
        case x:UnitController => 1
        case x:TopController => 1
        case x:LoopController => getParOf(x.cchain)
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
    dbgblk(s"getItersOf(${quote(n)})") {
      n match {
        case x if x.isStream => None
        case x:UnitController => Some(1)
        case x:TopController => Some(1)
        case x:LoopController => getItersOf(x.cchain)
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
    dbgblk(s"getCountsOf(${quote(n)})") { 
      n match {
        case n if n.isStream => 
          val reads = fifoLoadAccessOf(n)
          if (reads.isEmpty) None // set in fringe elaboration if annotated
          else {
            getCountsOf(ctxEnOf(reads.head).get)
          }
        case n =>
          val ancestors = n.ancestors
          ancestors.find { _.isStream }.fold {
            (n::ancestors).map { n => getItersOf(n) }.reduce[Option[Long]]{
              case (Some(iter1), Some(iter2)) => Some(iter1 * iter2)
              case _ => None
            }
          } { stream => 
            if (n.isInnerControl) getCountsOf(stream)
            else assertUnify(n.children, s"counts for $n") { child => 
              zipMap(getCountsOf(child), getItersOf(child)) { (count, iter) => count / iter }
            }
          }
      }
    }
  }

  def getParOf(x:PIRNode):Int = parOf.getOrElseUpdate(x) {
    dbgblk(s"getParOf($x)") {
      x match {
        case x:ControlNode => 1
        case x:Counter => x.par
        case x:CounterChain => getParOf(x.counters.last)
        case Def(n, ReduceOp(op, input)) => getParOf(input) / 2 
        case Def(n, AccumOp(op, input)) => getParOf(input)
        case n:ReduceAccumOp => 1
        case n:Container => n.collectDown[Primitive]().map { d => getParOf(d) }.max
        case x:LocalLoad => getParOf(ctrlOf(x))
        case x:ProcessDramCommand => getParOf(ctrlOf(x))
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
    dbgblk(s"getItersOf(${quote(n)})") {
      n match {
        case cchain:CounterChain => getItersOf(cchain.outer)
        case Def(ctr:Counter, Counter(min, max, step, par)) =>
          val cmin = getBoundAs[Int](min, logger=Some(this))
          val cmax = getBoundAs[Int](max, logger=Some(this))
          val cstep = getBoundAs[Int](step, logger=Some(this))
          dbg(s"ctr=${quote(ctr)} cmin=$cmin, cmax=$cmax, cstep=$cstep par=$par")
          val iters = zipMap(cmin, cmax, cstep) { case (cmin, cmax, cstep) =>
            if ((cmax - cmin) % (cstep * par) != 0)
              warn(s"(max=$cmax - min=$cmin) % (step=$cstep * par=$par) != 0 for ${quote(ctr)}")
            (cmax - cmin) / (cstep * par)
          }
          val enIters = ctr.getEnable.map { en => getItersOf(en) }.getOrElse(Some(1l))
          dbg(s"iters=$iters, enIters=$enIters")
          zipMap(iters, enIters) { case (iters, enIters) => iters * enIters }
        case Def(n, CounterDone(ctr)) => getItersOf(ctr)
        case n:DramControllerDone => getItersOf(ctrlOf(n))
        case n:StreamControllerDone => 
          val ctxCtrl = ctrlOf(ctxEnOf(n).get)
          getCountsOf(ctxCtrl)
        case n:ContextEnable => Some(1l)
        case n:EnabledAccess => getItersOf(accessNextOf(n))
        case n:GlobalOutput => getItersOf(validOf(n))
        case n:ProcessDramCommand => Some(1l)
        case n:Primitive => 
          val deps = n.deps.filterNot { dep => isBackPressure(dep) || dep.isInstanceOf[Memory] }
          minByWithBound[Primitive, Option[Long]](deps, Some(1l)) { dep => getItersOf(dep) }
      }
    }
  }

  // CtxEn.counts = R.count * R.iters / CtxEn.iters
  // R.counts = M.counts
  // M.counts = W.counts
  // W.counts = Input.counts * Input.iters / W.iters
  // Input.counts = Output.counts * Output.iters / Input.iters
  // Output.counts = Def.counts * Def.iters / Output.iters
  // Def.counts = CtxEn.counts
  // Def.iters = 1
  //
  // CtxEn.counts = R.count * R.iters / CtxEn.iters
  // CtxEn.counts = M.count * R.iters / 1 
  // CtxEn.counts = W.count * R.iters
  // CtxEn.counts = Output.counts * Output.iters / W.iters * R.iters
  // CtxEn.counts = Def.counts * Def.iters / W.iters * R.iters
  // CtxEn.counts = CtxEn.counts / W.iters * R.iters
  
  def getCountsOf(n:PIRNode):Option[Long] = countsOf.getOrElseUpdate(n) {
    dbgblk(s"getCountsOf(${quote(n)})") { 
      n match {
        case n:ContextEnable => 
          ctrlOf(n) match {
            case ctrl if ctrl.isStream & fifoLoadAccessOf(n).isEmpty => None
            case ctrl if ctrl.isStream => computeCount(n, fifoLoadAccessOf(n))
            case ctrl:ArgInController => Some(1l)
            case ctrl => computeCount(n, loadAccessesOf(n))
          }
        case n:LocalLoad => assertUnify(memsOf(n), "memCounts") { mem => getCountsOf(mem) }
        case n:Memory if writersOf(n).isEmpty => Some(1)
        case n:Memory => assertUnify(writersOf(n), "writeCounts") { writer => getCountsOf(writer) }
        case n:LocalStore => computeCount(n, List(dataOf(n)))
        case Def(n:LocalReset, LocalReset(mems, reset)) => computeCount(n, List(reset))
        case n:GlobalInput => computeCount(n, List(goutOf(n).get))
        case n:GlobalOutput => computeCount(n, List(gdataOf(n)))
        case n:StreamInDef => countsOf.get(n).getOrElse(None)
        case n:Primitive => getCountsOf(ctxEnOf(n).get)
      }
    }
  }

  def computeCount(curr:Def, deps:List[Def]) = {
    val currIter = getItersOf(curr)
    assertUnify(deps, s"counts for ${quote(curr)}") { dep => 
      zipMap(getCountsOf(dep), getItersOf(dep), currIter) { case (depCount, depIter, currIter) =>
        depCount * depIter / currIter
      }
    }
  }


}
