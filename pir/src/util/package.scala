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
    def prevParent(cc:CounterChain) = {
      val prev = chained.last
      val prevOrig = prev.original
      prevOrig.ctrler.parent match {
        case Some(prevParent:ComputeUnit) => 
          prevParent
        case Some(top) =>
          var info = s"fillChain: ${cchains}\n"
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
          val newCC = cu.getCopy(localCChainOf(pp))
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
    chained.toList
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

  def toValue(s:String):AnyVal = {
    if (s.contains("true") || s.contains("false")) 
      s.toBoolean
    else if (s.contains("."))
      s.toFloat
    else s.toInt
  }

  def collectIn[X](x:Any, logger:Logger)(implicit ev:ClassTag[X]):Set[X] = logger.emitBlock(s"collectIn($x)") {
    collectIn(x)(ev)
  }

  def collectIn[X](x:Any)(implicit ev:ClassTag[X]):Set[X] = x match {
    case x:X => Set(x)
    case x:GlobalInput => Set() // Stop at CU boundary
    case LoadPR(mem) => collectIn[X](mem)
    case CtrPR(ctr) => collectIn[X](ctr)
    case PipeReg(_, reg) => collectIn[X](reg) 
    case x:CounterChain => x.counters.flatMap(collectIn[X]).toSet
    case x:Module => collectIn[X](x.ins)
    case x:InPort => if (!x.isConnected) Set() else collectIn[X](x.from.src)
    case x:OutPort => collectIn[X](x.src)
    case x:Iterable[_] => x.flatMap(collectIn[X]).toSet
    case _ => Set()
  }

  def filterIn[X](x:X, predicate: Any => Boolean):Iterable[X] = filterIn(Set(x), predicate)

  def filterIn[X](xs:Iterable[X], predicate: Any => Boolean):Iterable[X] = xs.filter { x =>
    (x match {
      case x if predicate(x) => Set(x)
      case x:GlobalInput => Set() // Stop at CU boundary
      case LoadPR(mem) => filterIn(mem, predicate)
      case CtrPR(ctr) => filterIn(ctr, predicate)
      case PipeReg(_, reg) => filterIn(reg, predicate) 
      case x:CounterChain => x.counters.flatMap(ctr => filterIn(ctr, predicate)).toSet
      case x:Module => filterIn(x.ins, predicate) 
      case x:InPort => if (!x.isConnected) Set() else filterIn(x.from.src, predicate)
      case x:OutPort => filterIn(x.src, predicate)
      case x:Iterable[_] => x.flatMap(x => filterIn(x, predicate)).toSet
      case _ => Set()
    }).nonEmpty
  }
    
  def collectOut[X](x:Any)(implicit ev:ClassTag[X]):Set[X] = x match {
    case x:X => Set(x)
    case x:GlobalOutput => Set() // Stop at CU boundary
    case x:Module => collectOut[X](x.outs)
    case x:OutPort => if (!x.isConnected) Set() else x.to.flatMap(in => collectOut[X](in.src)).toSet
    case x:InPort => collectOut[X](x.src)
    case x:Iterable[_] => x.flatMap(collectOut[X]).toSet
    case _ => Set()
  }

}

