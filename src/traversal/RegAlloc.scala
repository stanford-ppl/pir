package pir.graph.traversal
import pir.graph.{Controller => CL, ComputeUnit => CU, TileTransfer => TT, Stage}
import pir._
import pir.PIRMisc._
import pir.graph.mapper._

import scala.collection.mutable.Map
import scala.collection.mutable.{Set => MSet}

class RegAlloc(mapper:PIRMapping)(implicit val design: Design) extends Traversal{

  def mapping = mapper.mapping

  val liveMap = Map[CU, Map[Stage, Set[Int]]]()
  val infGraph = Map[CU, Map[Int, MSet[Int]]]()

  override def reset = {
    liveMap.clear
    infGraph.clear
  }

  private def liveAnalysis = {
    design.top.compUnits.foreach { cu =>
      liveMap += cu -> Map.empty
      infGraph += cu -> Map.empty
      val lm = liveMap(cu)
      val stages = cu.stages
      for (i <- stages.size-1 to 0 by -1){
        val s = stages(i)
        val liveOut = if (i==stages.size-1) cu.liveOuts.toSet 
        else lm(stages(i+1))
        lm += s -> (liveOut -- cu.stageDefs(s) ++ cu.stageUses(s))
      }

      val ig = infGraph(cu)
      stages.foreach {s =>
        lm(s).foreach { r =>
          ig += (r -> MSet.empty)
          ig(r) ++= (lm(s) - r)
        }
      }
    }
  }

  override def traverse:Unit = {
    if (mapping==null)
      return
    liveAnalysis
  } 

  override def finPass = {
    info("Finishing Register Allocation")
    if (Config.debug) {
      import MapPrinter._
      emitln(s"/* ------------------- Liveness -----------------*/")
      design.top.compUnits.foreach { cu =>
        emitBlock(s"${cu}") {
          cu.stages.foreach {s =>
            val defs = cu.stageDefs(s).mkString(",") 
            val uses = cu.stageUses(s).mkString(",")
            val louts = liveMap(cu)(s).mkString(",")
            emitln(s"${s} def=[${defs}] use=[${uses}] liveIn=[${louts}]]")
          }
          emitln(s"liveOut=[${cu.liveOuts.mkString(",")}]")
        }
      }
      info(s"Mapping printed in ${MapPrinter.getPath}")
    }
    MapPrinter.close
  }
}
