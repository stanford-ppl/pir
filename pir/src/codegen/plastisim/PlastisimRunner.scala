package pir
package codegen

import pir.node._
import prism.graph._
import spade.param2._

class PlastisimRunner(implicit compiler: PIR) extends PlastisimUtil {

  lazy val log = buildPath(psimOut, "psim.log")

  override def runPass = {
    var command = s"$PLASTISIM_HOME/plastisim -f $configPath"
    if (!noPlaceAndRoute) {
      command += s" -p $psimOut/final.place"
    }
    command += config.getOption[Long]("psim-timeout").fold("") { t => s" -c $t" }
    val networkParam = spadeParam.networkParams.filter { _.granularity == "vec" }.head
    command += (networkParam.linkProp match {
      case "db" => s" -l B"
      case "cd" => s" -l C"
    })
    command += s" -w ${networkParam.flitWidth}" 
    command += s" -q${config.option[Int]("psim-q")}" 
    if (config.runPsim) {
      shellProcess(s"psim", command, log)(processOutput)
      if (!simulationSucceeded.getOrElse(false)) fail(s"Plastisim failed. details in $log")
    } else {
      info(s"To run simulation manually, use following command, or use --run-psim to launch simulation automatically")
      info(cstr(Console.YELLOW, command))
    }
  }

  var simulationSucceeded:Option[Boolean] = None
  def processOutput(line:String) = {
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
    //if (line.contains("Total Active")) {
      //val node = line.split(":")(0).trim
      //nameMap.get(node).foreach { node =>
        //activeOf(node) = line.split("Total Active:")(1).trim.split(" ")(0).trim.toLong
        //activeRateOf(node) = line.split("Active:")(1).trim.split(" ")(0).trim.toFloat
        //stallRateOf(node) = line.split("Stalled:")(1).trim.split(" ")(0).trim.toFloat
        //starveRateOf(node) = line.split("Starved:")(1).trim.split(" ")(0).trim.toFloat
        //if (line.contains("Final State")) finalStateOf(node) = line.split("Final State:")(1).trim.split(" ")(0).trim
        //checkActive(node).foreach { case (active, expected) =>
          //if (active < expected) { 
            ////err(s"${quote(node)} count=$expected active=$active", false)
            //simulationSucceeded = Some(false)
          //}
        //}
      //}
    //}
  }

}
