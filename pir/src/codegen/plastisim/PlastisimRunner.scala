package pir
package codegen

import pir.node._
import prism.graph._
import spade.param._

class PlastisimRunner(implicit compiler: PIR) extends PlastisimUtil with Printer {

  override def runPass = {
    runProute
    runPsim
  }

  def runProute = {
    if (!spadeParam.isAsic) {
      var command = s"$PLASTIROUTE_HOME/plastiroute -n $prouteNodeName -l $prouteLinkName -o $prouteName"
      spadeParam.pattern match {
        case p:Checkerboard => 
          command += s" -r ${p.row} -c ${p.col}"
          command += s" -T checkerboard"
          val vnet = p.networkParams.filter { _.granularity == "vec" }.head
          val snet = p.networkParams.filter { _.granularity == "word" }.head
          command += s" -x ${vnet.switchLink}"
          command += s" -e ${snet.switchLink}"
        //case _:MCMColumnStrip => "mcmcstrip"
      }
      //if (isInitPlacement) command += s" -b $psimOut/init.place"
      command += s" -g proute.dot"
      command += s" -v $summaryName"
      command += s" -a ${config.option[String]("proute-algo")} "
      command += s" -q${config.option[String]("proute-q")} "
      command += s" -s${config.option[String]("proute-seed")} "
      command += s" ${config.option[String]("proute-opts")}"
      withOpen(psimOut, s"proute.sh", false) {
        emitln(s"cd $psimOut")
        emitln(command)
      }
      if (config.runPsim) {
        deleteFile(summaryPath)
        deleteFile(prouteLog)
        val exitCode = shellProcess("proute", s"bash ${buildPath(psimOut, s"proute.sh")}", prouteLog) { line =>
          if (line.contains("Used") && line.contains("VCs.")) {
            info(Console.GREEN, s"proute", line)
          }
        }
        if (exitCode != 0) {
          fail(s"Plastiroute failed. details in $prouteLog")
        }
      }
    } 
  }

  def runPsim = {
    var command = s"$PLASTISIM_HOME/plastisim -f $configName"
    if (!noPlaceAndRoute) {
      command += s" -p $prouteName"
    }
    command += config.getOption[Long]("psim-timeout").fold("") { t => s" -c $t" }
    val networkParam = spadeParam.networkParams.filter { _.granularity == "vec" }.head
    command += (networkParam.linkProp match {
      case "db" => s" -l B"
      case "cd" => s" -l C"
    })
    command += s" -w ${networkParam.flitWidth}" 
    command += s" -q${config.option[Int]("psim-q")}" 
    withOpen(psimOut, s"psim.sh", false) {
      emitln(s"cd $psimOut")
      emitln(command)
    }
    if (config.runPsim) {
      shellProcess(s"psim", s"bash ${buildPath(psimOut,"psim.sh")}", psimLog)(processOutput)
      if (!simulationSucceeded.getOrElse(false)) fail(s"Plastisim failed. details in $psimLog")
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
