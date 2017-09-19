package pir

import pir.node._
import pir.util.misc._
import mapper._
import pir.codegen.{Printer, Logger}
import scala.language.implicitConversions
import pirc.exceptions._
import java.lang.Thread
import pir.codegen.Logger
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag

package object util {
  implicit def pr_to_inport(pr:PipeReg):InPort = pr.in
  implicit def pr_to_outport(pr:PipeReg):OutPort = pr.out
  implicit def sram_to_outport(sram:SRAM):OutPort = sram.readPort
  implicit def ctr_to_port(ctr:Counter):OutPort = ctr.out
  implicit def const_to_port(const:Const[_<:AnyVal]):OutPort = const.out
  implicit def mExcep_to_string(e:MappingException[_]):String = e.toString
  implicit def range_to_bound(r:Range)(implicit design:PIR) = r by Const(1) 
  implicit def sRange_to_bound(r:scala.collection.immutable.Range)(implicit design:PIR): (OutPort, OutPort, OutPort) =
    (Const(r.min.toInt).out, Const(r.max.toInt+1).out, Const(r.step.toInt).out)

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
      (cl.souts ++ cl.vouts).forall( out => out.readers.forall(reader => list.contains(reader.ctrler)))
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
    logger.emitList(s"results") { res.foreach(cl => logger.dprintln(s"$cl") ) }
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
        case prevParent:ComputeUnit => 
          prevParent
        case top:Top =>
          var info = s"fillChain: ${cchains}\n"
          info += s"prev=$prev cchain.original=$prevOrig\n"
          info += s"original.ctrler=${prevOrig.ctrler}'s parent is Top! curr=${cc} in ${cc.ctrler}" 
          throw new Exception(info)
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
    case LoadPR(mem) => collectIn[X](mem)
    case CtrPR(ctr) => collectIn[X](ctr)
    case PipeReg(_, reg) => collectIn[X](reg) 
    case x:FuncUnit => collectIn[X](x.operands)
    case x:Counter => collectIn[X](x.min) ++ collectIn[X](x.max) ++ collectIn[X](x.step)
    case x:CounterChain => x.counters.flatMap(collectIn[X]).toSet
    case x:InPort => if (!x.isConnected) Set() else collectIn[X](x.from.src)
    case x:OutPort => collectIn[X](x.src)
    case x:Mux => x.ins.flatMap(collectIn[X]).toSet
    case x:SRAM => collectIn[X](x.readAddr) ++ collectIn[X](x.writeAddr) ++ collectIn[X](x.writePort)
    case x:LocalMem => collectIn[X](x.writePort)
    case x:Iterable[_] => x.flatMap(collectIn[X]).toSet
    case _ => Set()
  }

  def filterIn[X](x:X, target:Any):Iterable[X] = filterIn(Set(x), target)

  def filterIn[X](xs:Iterable[X], target:Any):Iterable[X] = xs.filter { x =>
    (x match {
      case `target` => Set(x)
      case LoadPR(mem) => filterIn(mem, target)
      case CtrPR(ctr) => filterIn(ctr, target)
      case PipeReg(_, reg) => filterIn(reg, target) 
      case x:FuncUnit => filterIn(x.operands, target)
      case x:Counter => filterIn(x.min, target) ++ filterIn(x.max, target) ++ filterIn(x.step, target)
      case x:CounterChain => x.counters.flatMap(ctr => filterIn(ctr, target)).toSet
      case x:InPort => if (!x.isConnected) Set() else filterIn(x.from.src, target)
      case x:OutPort => filterIn(x.src, target)
      case x:Mux => x.ins.flatMap(in => filterIn(in, target)).toSet
      case x:SRAM => filterIn(x.readAddr, target) ++ filterIn(x.writeAddr, target) ++ filterIn(x.writePort, target)
      case x:LocalMem => filterIn(x.writePort, target)
      case x:Iterable[_] => x.flatMap(x => filterIn(x, target)).toSet
      case _ => Set()
    }).nonEmpty
  }
    
  def collectOut[X](x:Any)(implicit ev:ClassTag[X]):Set[X] = x match {
    case x:X => Set(x)
    case x:Input => collectOut[X](x.out)
    case x:OnChipMem => collectOut[X](x.readPort)
    case x:Counter => collectOut[X](x.out)
    case x:PipeReg => collectOut[X](x.out)
    case x:Iterable[_] => x.flatMap(collectOut[X]).toSet
    case x:OutPort => if (!x.isConnected) Set() else x.to.flatMap(in => collectOut[X](in.src)).toSet
    case x:InPort => collectOut[X](x.src)
    case x:Mux => collectOut[X](x.out)
    case _ => Set()
  }

}

