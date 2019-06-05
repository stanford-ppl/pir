package pir
package codegen

import pir.node._
import spade.param._
import prism.graph._
import prism.codegen._

class PlastirouteNodeGen(implicit compiler: PIR) extends PlastisimCodegen with CSVCodegen {
  override def fileName = config.prouteNodeName

  override def runPass = {
    if (!noPlaceAndRoute) {
      topMap.foreach { tmap =>
        tmap.cumap.usedMap.foreach { case (cuP, cuS) =>
          val row = newRow
          row("node") = cuP.id
          row("tp") = (cuS.params.get match {
            case param:ArgFringeParam => 0
            case param:MCParam => 1
            case param:PMUParam => 2
            case param:DramAGParam => 3
            case param:PCUParam => 4
          })
        }
      }
    }
  }

}

