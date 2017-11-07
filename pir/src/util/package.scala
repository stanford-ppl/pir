package pir

import pir.node._
import pir.mapper._

import pirc._
import pirc.util._
import pirc.exceptions._

import scala.reflect.ClassTag
import scala.collection.mutable.ListBuffer

import java.lang.Thread

package object util {
  def quote(n:Node)(implicit design:PIR) = {
    val pirmeta: PIRMetadata = design
    import pirmeta._
    n match {
      case n => indexOf.get(n).fold(s"$n"){ i =>s"$n[$i]"}
    }
  }

  def topoSort(ctrler:Controller)(implicit logger:Logger):List[Controller] = logger.emitBlock(s"topoSort($ctrler)") {
    import ctrler.design.pirmeta._
    val list = ListBuffer[Controller]()
    def isDepFree(cl:Controller):Boolean = {
      descendentsOf(cl).filterNot(_==cl).forall(des => list.contains(des)) &&  //TODO: is children the same?
      (cl.souts ++ cl.vouts).forall( out => out.to.forall(reader => list.contains(reader.ctrler)))
    }
    list += ctrler
    var remain = descendentsOf(ctrler).filterNot(_== ctrler)
    while (remain.nonEmpty) {
      var (free, notfree) = remain.toList.partition(isDepFree)
      if (free.isEmpty) {
        val head::rest = notfree
        free = free :+ head
        notfree = rest
        logger.dprintln(s"Breaking the loop by picking $head")
      }
      logger.dprintln(s"free=$free")
      list ++= free
      remain = notfree
    }
    var res = list.toList
    val head::rest = res
    res = rest :+ head
    res
  }

  def fillChain(cu:ComputeUnit, cchains:List[CounterChain])(implicit design:PIR, logger:Logger) = logger.emitBlock(s"fillChain"){
    val pirmeta:PIRMetadata = design 
    import pirmeta._

    logger.dprintln(s"cu=$cu")
    logger.emitBlock(s"cchains") {
      cchains.foreach { cc => logger.dprintln(s"${cc} original=${cc.original} from ${cc.original.ctrler}") }
    }
    val chained = ListBuffer[CounterChain]()
    val newChains = ListBuffer[CounterChain]()
    def prevParent(cc:CounterChain) = {
      val prev = chained.last
      val prevOrig = prev.original
      prevOrig.ctrler.parent match {
        case Some(prevParent:ComputeUnit) => 
          prevParent
        case Some(top) =>
          var info = s"fillChain:\n"
          cchains.foreach { cc =>
            info += s"$cc (${cc.original.ctrler})\n"
          }
          info += s"prev=$prev cchain.original=$prevOrig\n"
          info += s"original.ctrler=${prevOrig.ctrler}'s parent is Top! curr=${cc} in ${cc.ctrler}" 
          throw new Exception(info)
        case None => throw new Exception("shouldn't happen")
      }
    }
    cchains.foreach { cc =>
      if (chained.nonEmpty) {
        while (prevParent(cc)!=cc.original.ctrler) {
          val pp = prevParent(cc)
          val newCC = cu.getCopy(localCChainOf(pp)).map { cc =>
            newChains += cc
            cc
          }.getOrElse(localCChainOf(pp))
          logger.dprintln(s"cc=$cc prevParent=$pp newCC=$newCC")
          forRead(newCC) = forRead(chained.last)
          forWrite(newCC) = forWrite(chained.last)
          newCC.counters.foreach { ctr => 
            forRead(ctr) = forRead(newCC)
            forWrite(ctr) = forWrite(newCC)
          }
          chained += newCC
        }
      }
      chained += cc
    }
    (chained.toList, newChains.toList)
  }

  def sortCChains(cchains:List[CounterChain])(implicit logger:Logger):List[CounterChain] = {
    import logger._
    val ancSize = cchains.map { _.original.ctrler.ancestors.size }
    if (ancSize.size != ancSize.toSet.size) {
      cchains.zip(ancSize).foreach { case (cc,size) =>
        dprintln(s"ctrler:${cc.ctrler} cchain:$cc original:${cc.original} ${cc.original.ctrler} $size")
      }
      throw new Exception(s"Don' how to sort $cchains!")
    }
    cchains.sortBy { cc => cc.original.ctrler.ancestors.size }.reverse
  }

  def pipelinedBy(ctrler:Controller)(implicit design:PIR) = {
    import design.pirmeta._
    ctrler match {
      case ctrler:StreamController => -1
      case ctrler:InnerController => -1
      case ctrler:MetaPipeline => 1 
      case ctrler:Sequential => lengthOf(ctrler) 
      case ctrler:Top => 1 
    }
  }

  def visitIn(x:Any):Iterable[Any] = x match {
    case x:GlobalInput => Set() // Stop at CU boundary
    case x:Input => if (!x.isConnected) Set() else Set(x.from)
    case x:Output => Set(x.src) 
    case x:CounterChain => x.counters.toSet
    case LoadPR(mem) => Set(mem) 
    case CtrPR(ctr) => Set(ctr) 
    case PipeReg(_, reg) => Set(reg) 
    case x:Module => x.ins
  }

  def visitOut(x:Any):Iterable[Any] = x match {
    case x:GlobalOutput => Set() // Stop at CU boundary
    case x:Input => Set(x.src) 
    case x:Output => if (!x.isConnected) Set() else x.to 
    case x:Module => x.outs 
  }

  def collectIn[X](x:Any, visitIn:Any => Iterable[Any] = visitIn, logger:Option[Logger] = None)(implicit ev:ClassTag[X]):Set[X] = {
    def collect(x:Any):Set[X] = collectIn[X](x, visitIn, logger)
    def f(x:Any) = x match {
      case x:X => Set(x)
      case x:Iterable[_] => x.flatMap(x => collect(x)).toSet
      case x => collect(visitIn(x))
    }
    logger.fold(f(x)) { _.emitBlock(s"collectIn($x)") {f(x)} }
  }

  def existsIn(x:Any, visitIn:Any => Iterable[Any] = visitIn)(predicate: Any => Boolean):Boolean = {
    x match {
      case x if predicate(x) => true
      case x:Iterable[_] => x.exists(x => existsIn(x)(predicate))
      case x => existsIn(visitIn(x))(predicate)
    }
  }
    
  def collectOut[X](x:Any, visitOut:Any => Iterable[Any] = visitOut, logger:Option[Logger]=None)(implicit ev:ClassTag[X]):Set[X] = {
    def collect(x:Any):Set[X] = collectOut[X](x, visitOut, logger)
    def f(x:Any) = x match {
      case x:X => Set(x)
      case x:Iterable[_] => x.flatMap(x => collect(x)).toSet
      case x => collect(visitOut(x))
    }
    logger.fold(f(x)) { _.emitBlock(s"collectOut($x)") {f(x)} }
  }

}

