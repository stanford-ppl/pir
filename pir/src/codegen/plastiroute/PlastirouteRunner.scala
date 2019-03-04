package pir
package codegen

import pir.node._
import prism.graph._
import spade.param._

class PlastirouteRunner(implicit compiler: PIR) extends PlastisimUtil with Printer {

  lazy val summaryName = "summary.csv"
  lazy val summaryPath = buildPath(psimOut, summaryName)
  lazy val prouteLog = buildPath(config.cwd, "proute.log")

  override def runPass = {
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
      // Generate proute.sh script containing proute commands to run
      withOpen(config.cwd, s"proute.sh", false) {
        emitln(s"cd $psimOut")
        emitln(command)
      }
      deleteFile(summaryPath)
      deleteFile(prouteLog)
      val exitCode = shellProcess("proute", s"bash proute.sh", prouteLog) { line =>
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

