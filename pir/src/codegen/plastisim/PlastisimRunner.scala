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
    if (!noPlaceAndRoute) {
      var command = s"${config.prouteHome}/plastiroute -n $prouteNodeName -l $prouteLinkName -o $prouteName"
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
        emitln(command)
      }
      if (config.runPsim) {
        deleteFile(summaryPath)
        deleteFile(prouteLog)
        val exitCode = shellProcess("proute", s"bash proute.sh", psimOut, prouteLog) { line =>
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
    var command = s"${config.psimHome}/plastisim -f $configName"
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
      emitln(command)
    }
    if (config.runPsim) {
      shell(s"psim", s"bash psim.sh", psimOut, psimLog)
      config.getArgOption[String]("load-psim").foreach { _.updateValue(psimLog) }
    }
  }

}
