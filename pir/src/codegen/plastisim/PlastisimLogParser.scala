package pir
package codegen

import pir.node._
import prism.graph._
import spade.param._

class PlastisimLogParser(implicit compiler: PIR) extends PIRPass with PlastisimUtil {

  var simulationSucceeded:Option[Boolean] = None

  override def runPass = {
    config.loadPsim.foreach { psimLog =>
      val nameMap = pirTop.collectDown[Context]().map { ctx => 
        (s"$ctx".replace("Context","C"), ctx)
      }.toMap
      getLines(psimLog).foreach { processOutput(nameMap) _ }
      if (!simulationSucceeded.getOrElse(false)) fail(s"Plastisim failed. details in $psimLog")
    }
  }

  def processOutput(nameMap:Map[String, Context])(line:String) = {
    if (line.contains("Total DRAM")) {
      info(Console.GREEN, s"psim", line)
    }
    if (line.contains("Simulation complete at cycle:")) {
      if (simulationSucceeded.isEmpty) {
        info(Console.GREEN, s"psim", line)
        simulationSucceeded = Some(true)
      } else {
        info(Console.RED, s"psim", line)
      }
      states.simulationCycle = Some(line.split("Simulation complete at cycle:")(1).trim.toLong)
    }
    if (line.contains("DEADLOCK") || line.contains("TIMEOUT")) {
      info(Console.RED, s"psim", line)
      simulationSucceeded = Some(false)
    }
    if (line.contains("Total Active:")) {
      val ctx = line.split(":")(0).trim
      nameMap.get(ctx).foreach { ctx =>
        ctx.active := line.split("Total Active:")(1).trim.split(" ")(0).trim.toLong
        ctx.activeRate(line.split("Active:")(1).trim.split(" ")(0).trim.toFloat)
        //stallRateOf(node) = line.split("Stalled:")(1).trim.split(" ")(0).trim.toFloat
        //starveRateOf(node) = line.split("Starved:")(1).trim.split(" ")(0).trim.toFloat
        if (line.contains("Final State:")) ctx.state := line.split("Final State:")(1).trim.split(" ")(0).trim
        if (line.contains("Expected Active:")) ctx.count := Some(line.split("Expected Active:")(1).trim.split(" ")(0).trim.toLong)
      }
    }
  }

}
