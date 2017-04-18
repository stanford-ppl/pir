package pir.pass
import pir.graph._
import pir._
import pir.util._
import pir.exceptions._
import pir.util.misc._

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.Map

class MemoryAnalysis(implicit design: Design) extends Pass {
  def shouldRun = true 
  import pirmeta._

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
    }
  }

  def analyzeCChain = {
    design.top.memCUs.foreach { cu =>
      cu.cchains.foreach { cc =>
        if (cc.counters.exists(cc => forWrite(cc))) {
          forWrite(cc) = true
        } else if (cc.counters.exists(cc => forRead(cc))) {
          forRead(cc) = true
        }
      }
    }
  }

  override def traverse = {
    analyzeStageOperands
    analyzeCChain
  } 

}
