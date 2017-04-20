package pir.pass
import pir.graph._
import pir._
import pir.util._
import pir.exceptions._
import pir.codegen.Logger

import scala.collection.mutable._

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

  def analyzeAddrCalc = {
    design.top.compUnits.foreach { cu =>
      val readCCs = cu.cchains.filter { cc => forRead(cc) }
      dprintln(s"readCCs:$readCCs")
      readCChainsOf(cu) = fillChain(cu, sortCChains(readCCs))
      val writeCCs = cu.cchains.filter { cc => forWrite(cc) }
      dprintln(s"writeCCs:$writeCCs")
      writeCChainsOf(cu) = fillChain(cu, sortCChains(writeCCs))
      val compCCs = cu.cchains.filter { cc => !forRead(cc) && !forWrite(cc) }
      dprintln(s"compCCs:$compCCs")
      fillChain(cu, sortCChains(compCCs))
    }
  }

  override def traverse = {
    analyzeStageOperands
    analyzeCChain
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
