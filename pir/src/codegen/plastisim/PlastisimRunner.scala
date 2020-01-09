package pir
package codegen

import pir.node._
import prism.graph._
import spade.param._

class PlastisimRunner(implicit compiler: PIR) extends PlastisimUtil with Printer {

  override def runPass = {
    val conf = config
    val psimHome = config.psimHome.getOrElse("psim-home is not set")
    var command = s"${psimHome}/plastisim -f ${config.psimConfigName}"
    if (!noPlaceAndRoute) {
      command += s" -p ${config.proutePlaceName}"
    }
    command += config.getOption[Long]("psim-timeout").fold("") { t => s" -c $t" }
    val networkParam = spadeParam.networkParams.filter { _.granularity == "vec" }.head
    command += (networkParam.linkProp match {
      case "db" => s" -l B"
      case "cd" => s" -l C"
    })
    command += s" -w ${networkParam.flitWidth}" 
    command += s" -q${config.option[Int]("psim-q")}" 
    withOpen(config.cwd, s"psim.sh", false) {
      emitln(command)
    }
    shell(s"psim", s"bash psim.sh", config.psimLog)
    config.getArgOption[String]("load-psim").foreach { _.updateValue(config.psimLog) }
  }

}
