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

  def getParOf(x:Controller):Int = parOf.getOrElseUpdate(x) {
    dbgblk(s"getParOf($x)") {
      x match {
        case n:ForeverController => 1
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
        case x:ForeverController => getCountsOf(x)
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
        case ctrl:ArgInController => Some(1l)
        case ctrl:ForeverController => 
          assertUnify(n.children.filter(c => foreverLoadsOf(c).nonEmpty), s"counts for $n") { c => 
            zipMap(getCountsOf(c), itersOf(c)) { case (c, i) => c / i }
          }
        case n:Controller if n.ancestors.exists(isForever) =>
          val reads = foreverLoadsOf(n).toList
          if (reads.nonEmpty) {
            val ctxEn = ctxEnOf(reads.head).get
            computeCount(ctxEn, reads)
          } else if (n.children.nonEmpty) {
            assertUnify(n.children, s"counts for $n") { c => 
              zipMap(getCountsOf(c), itersOf(c)) { case (c, i) => c / i }
            }
          } else {
            throw PIRException(s"don't know how to compute getCountsOf($n)")
          }
        case n:Controller =>
          (n::n.ancestors).map { n => getItersOf(n) }.reduce[Option[Long]]{
            case (Some(iter1), Some(iter2)) => Some(iter1 * iter2)
            case _ => None
          }
      }
    }
  }

  def foreverLoadsOf(n:Controller) = fifoLoadsOf(n) ++ loadsUnderForever(n)

  def fifoLoadsOf(n:Controller) = dbgblk(s"remoteStoreAccessesOf($n)"){
    val descendents = (n :: n.descendents)
    val reads = ctrlOf.bmap(n).collect { case n:LocalLoad => n }.toList
    dbg(s"reads=${reads.map { read => (read, memsOf(read))}}")
    dbg(s"descendents=$descendents")
    reads.filter { read => 
      memsOf(read).forall { mem => 
        isFIFO(mem) && writersOf(mem).forall { writer => 
          val writerCtrl = ctrlOf(writer)
          dbg(s"mem=$mem writerCtrl=$writerCtrl")
          !descendents.contains(writerCtrl)
        }
      } 
    }
  }

  def loadsUnderForever(n:Controller) = dbgblk(s"loadAccessesUnderForever($n)"){
    val forever = n.ancestors.filter(isForever).head
    val fdescendents = forever::forever.descendents
    val descendents = n :: n.descendents
    val reads = ctrlOf.bmap(n).collect { case n:LocalLoad => n }.toList
    dbg(s"reads=${reads.map { read => (read, memsOf(read))}}")
    reads.filter { read => memsOf(read).forall { mem => 
      writersOf(mem).forall { writer =>
        val writerCtrl = ctrlOf(writer)
        !descendents.contains(writerCtrl)
      } && fdescendents.contains(ctrlOf(mem)) 
    } }
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
        case n:ForeverControllerDone => getItersOf(ctrlOf(n))
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
    dbgblk(s"getCountsOf(${quote(n)}, ctrl=${ctrlOf(n)})") { 
      n match {
        case n:ContextEnable => getCountsOf(ctrlOf(n))
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

  def getItersOf(n:Any):Option[Long] = n match {
    case n:PIRNode => getItersOf(n)
    case n:Controller => getItersOf(n)
  }

  def getCountsOf(n:Any):Option[Long] = n match {
    case n:PIRNode => getCountsOf(n)
    case n:Controller => getCountsOf(n)
  }

  def computeCount(curr:Any, deps:List[Any]) = dbgblk(s"computeCount(${quote(curr)}, ${deps.map(quote)})"){
    val currIter = getItersOf(curr)
    assertUnify(deps, s"counts for ${quote(curr)}") { dep => 
      zipMap(getCountsOf(dep), getItersOf(dep), currIter) { case (depCount, depIter, currIter) =>
        dbg(s"depCount=$depCount * depIter=$depIter / currIter=$currIter")
        depCount * depIter / currIter
      }
    }
  }


}
