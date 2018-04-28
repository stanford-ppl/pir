
package pir.pass

import pir.node._

trait RuntimeUtil extends ConstantPropogator { self:PIRPass =>
  import pirmeta._
  import spademeta._

  def assertUnify[A,B](list:Iterable[A],info:String)(lambda:A => B):B = {
    val res = list.map(lambda)
    assert(res.toSet.size==1, s"$list doesnt have the same $info = $res")
    res.head
  }

  def getParOf(x:Any):Int = parOf.getOrElseUpdate(x) {
    dbgblk(s"getParOf($x)") {
      x match {
        case x:UnitController => 1
        case x:TopController => 1
        case x:LoopController => getParOf(x.cchain)
        case x:ArgInController => 1
        case x:ArgOutController => 1
        case DramController(size, par) => par

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

  def getItersOf(n:Any):Int = itersOf.getOrElseUpdate(n) {
    dbgblk(s"getItersOf(${quote(n)})") {
      n match {
        case x:UnitController => 1
        case x:TopController => 1
        case x:LoopController => getItersOf(x.cchain)
        case x:ArgInController => 1
        case x:ArgOutController => 1
        case DramController(size, par) if size.isEmpty => 
          throw PIRException(s"cannot compute iter of $DramController due to size not constant")
        case DramController(size, par) => 
          val wordSize = size.get / 4
          wordSize / par
        case cchain:CounterChain => getItersOf(cchain.outer)
        case Def(ctr:Counter, Counter(min, max, step, par)) =>
          val cmin = getConstOf[Int](min, logger=Some(this))
          val cmax = getConstOf[Int](max, logger=Some(this))
          val cstep = getConstOf[Int](step, logger=Some(this))
          dbg(s"ctr=${quote(ctr)} cmin=$cmin, cmax=$cmax, cstep=$cstep par=$par")
          if ((cmax - cmin) % (cstep * par) != 0)
            warn(s"(max=$cmax - min=$cmin) % (step=$cstep * par=$par) != 0 for ${quote(ctr)}")
          val iters = (cmax - cmin) / (cstep * par)
          iters * ctr.getEnable.fold (1) { en => getItersOf(en) }
        case Def(n, CounterDone(ctr)) => getItersOf(ctr)
        case n:DramControllerDone => getItersOf(ctrlOf(n))
        case n:ContextEnable => 1
        case n:LocalAccess => getItersOf(accessNextOf(n))
        case n:GlobalOutput => getItersOf(validOf(n))

        case n:Primitive => 
          val deps = n.deps.filterNot(isBackPressure)
          assertUnify(deps, s"itersOf") { dep => getItersOf(dep) }
      }
    }
  }

  def verifyCounts(n:Any)(computeCount: => Int) = {
    val count = computeCount
    n match {
      case n:ContextEnable =>
        val ctrlCount = getCountsOf(ctrlOf(n))
        assert(ctrlCount == count, s"$n's count=$count != ${ctrlOf(n)}.count=$ctrlCount")
      case n:Memory if isFIFO(n) =>
        val ctrlCount = assertUnify(writersOf(n), "counts") { writer => getCountsOf(writer) }
        assert(ctrlCount == count, s"$n's count=$count != ${writersOf(n)}.count=$ctrlCount")
      case n:Memory =>
        val ctrlCount = getCountsOf(ctrlOf(n))
        assert(ctrlCount == count, s"$n's count=$count != ${ctrlOf(n)}.count=$ctrlCount")
      case _ =>
    }
    count
  }

  def getCountsOf(n:Any):Int = countsOf.getOrElseUpdate(n) {
    dbgblk(s"getCountsOf(${quote(n)})") { verifyCounts(n) {
      n match {
        case n:Controller =>
          val parentCount = n.parent.fold(1) { parent => getCountsOf(parent) }
          parentCount * itersOf(n)
        case n:LocalLoad => 
          val count = assertUnify(memsOf(n), "count") { mem => getCountsOf(mem) }
          count * getItersOf(n) // Scale up
        case n:LocalStore => getCountsOf(dataOf(n)) / getItersOf(n) // Scale down
        case n:Memory => 
          assertUnify(writersOf(n), "count") { writer => getCountsOf(writer) }
        case n:Primitive =>
          val deps = n.deps.filterNot(isBackPressure) 
          if (deps.isEmpty) {
            n match {
              case n:ContextEnable =>
                assert(ctrlOf(n).isInstanceOf[ArgInController]); 1
              case n =>
                val node = n.collectPeer[ContextEnable]().head
                getCountsOf(node)
            }
          } else assertUnify(deps, "count") {
            case dep:Memory =>
              // Get readers in the same context
              val readers = readersOf(dep).filter { reader => contextOf(reader) == contextOf(n) }
              assertUnify(readers, "count") { reader => getCountsOf(reader) }
            case dep => getCountsOf(dep)
          }
      }
    } }
  }


  def isBackPressure(n:Primitive) = n match {
    case n:NotFull => true
    case n:DataReady => true
    case _ => false
  }

}
