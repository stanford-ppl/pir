package pir
package codegen

import pir.node._
import prism.graph._
import spade.param._

class PlastirouteRunner(implicit compiler: PIR) extends PlastisimUtil with Printer {

  override def runPass = {
    val conf = config
    import conf._
    if (!noPlaceAndRoute) {
      val prouteHome = config.prouteHome.getOrElse(err(s"proute-home is not set"))
      var command = s"${prouteHome}/plastiroute -n $prouteNodeName -l $prouteLinkName -y $prouteOutLinkName -z $prouteInLinkName"
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
      command += s" -G $proutePlaceName"
      command += s" -X ${if (config.asModule) s"/" else s"/$topName"}"
      //command += s" -o $proutePlaceName"
      // Generate proute.sh script containing proute commands to run
      val dst = buildPath(config.appDir, "proute.sh")
      deleteFile(dst)
      config.proutesh match {
        case Some(proutesh) =>
          if (!exists(proutesh))
            err(s"${proutesh} doesn't exists!")
          lnFile(proutesh, dst)
        case None =>
          withOpen(config.appDir, s"proute.sh", false) {
            emitln(s"cd ${getRelativePath(config.psimOut, config.appDir)}")
            emitln(command)
          }
      }
      deleteFile(prouteSummaryPath)
      deleteFile(prouteLog)
      if (runproute) {
        val exitCode = shellProcess("proute", s"make proute", config.tstOut, prouteLog) { line =>
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

}

