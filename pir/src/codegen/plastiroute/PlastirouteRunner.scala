package pir
package codegen

import pir.node._
import prism.graph._
import spade.param._

class PlastirouteRunner(implicit compiler: PIR) extends PlastisimUtil with Printer {

  override def runPass = {
    val conf = config
    import conf._
    if (!noPlaceAndRoute && runproute) {
      var command = s"${config.prouteHome}/plastiroute -n $prouteNodeName -l $prouteLinkName -o $proutePlaceName"
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
      command += s" -v $prouteSummaryName"
      command += s" -a ${config.option[String]("proute-algo")} "
      command += s" -q${config.option[String]("proute-q")} "
      command += s" -s${config.option[String]("proute-seed")} "
      command += s" ${config.option[String]("proute-opts")}"
      // Generate proute.sh script containing proute commands to run
      withOpen(config.cwd, s"proute.sh", false) {
        emitln(s"cd $psimOut")
        emitln(command)
      }
      deleteFile(prouteSummaryPath)
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

