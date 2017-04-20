package pir.pass
import pir.graph._
import pir._
import pir.util._
import pir.exceptions._
import pir.codegen.Logger

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.Map

class MemoryAnalyzer(implicit design: Design) extends Pass with Logger {
  def shouldRun = true 
  import pirmeta._

  override lazy val stream = newStream(s"MemoryAnalyzer.log")

  def analyzeStageOperands = {
    design.top.memCUs.foreach { cu =>
      (cu.wtAddrStages ++ cu.rdAddrStages).foreach { st =>
        st.fu.foreach { fu =>
          fu.operands.foreach { oprd =>
            (oprd.from.src, st) match {
              case (p:Counter, st:WAStage) => forWrite(p) = true
              case (p:Counter, st:RAStage) => forRead(p) = true
              case (p:ScalarFIFO, st:WAStage) => forWrite(p) = true
              case (p:ScalarFIFO, st:RAStage) => forRead(p) = true
              case (p:Const[_], _) =>
            }
          }
        }
      }
      cu.mem.writePort.from.src match {
        case fifo:VectorFIFO => forWrite(fifo) = true
      }
      cu.mem.readAddr.from.src match {
        case ctr:Counter => forRead(ctr) = true
        case fu:FuncUnit =>
      }
      cu.mem.writeAddr.from.src match {
        case ctr:Counter => forWrite(ctr) = true
        case fu:FuncUnit =>
      }
    }
  }

  def analyzeCChain = {
    design.top.memCUs.foreach { cu =>
      cu.cchains.foreach { cc =>
        if (cc.counters.exists(cc => forWrite(cc))) {
          forWrite(cc) = true
          cc.counters.foreach(ctr => forWrite(ctr) = true)
        } else if (cc.counters.exists(cc => forRead(cc))) {
          forRead(cc) = true
          cc.counters.foreach(ctr => forRead(ctr) = true)
        }
      }
    }
  }

  def copyAddrCChains = {
    design.top.memCUs.foreach { cu =>
      //TODO
    }
  }

  def analyzeAddrCalc = {
    design.top.memCUs.foreach { cu =>
      val readCCs = cu.cchains.filter { cc => forRead(cc) }
      readCChainsOf(cu) = sortCChains(readCCs)
      val writeCCs = cu.cchains.filter { cc => forWrite(cc) }
      writeCChainsOf(cu) = sortCChains(writeCCs)
    }
  }

  def sortCChains(cchains:List[CounterChain]) = {
    cchains.sortBy { cc => cc.ctrler.ancestors.size }.reverse
  }

  override def traverse = {
    analyzeStageOperands
    analyzeCChain
    copyAddrCChains
    analyzeAddrCalc
    design.top.memCUs.foreach { cu =>
      emitBlock(s"$cu") {
        cu.fifos.foreach { fifo => 
          dprintln(s"$fifo forRead=${forRead(fifo)} forWrite=${forWrite(fifo)}")
        }
        cu.cchains.foreach { cchain =>
          dprintln(s"$cchain forRead=${forRead(cchain)} forWrite=${forWrite(cchain)}")
        }
        dprintln(s"readCChains:[${readCChainsOf(cu).mkString(",")}]")
        dprintln(s"writeCChains:[${writeCChainsOf(cu).mkString(",")}]")
      }
    }
  } 

}
