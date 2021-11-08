package pir
package codegen

import pir.mapper._
import pir.node._
import spade.param._
import prism.graph._
import prism.codegen._

class PlastirouteNodeGen(implicit compiler: PIR) extends PlastisimCodegen with CSVCodegen {
  override def fileName = config.prouteNodeName

  override def runPass = {
    topMap.foreach { tmap =>
      tmap.cumap.usedMap.foreach { case (cuP, cuS) =>
        emitCU(cuP, cuS)
      }
    }
  }

  def emitCU(cuP:CUMap.K, cuS:CUMap.V):Unit = {
    assert(!cuP.isExtern.get)
    val row = newRow
    row("node") = quote(cuP)
    row("tp") = (cuS.params.get match {
      case param:ArgFringeParam => 0
      case param:MCParam => 1
      // case param:PMUParam => if (param.isSparse != 0) 5 else 2
      case param:PMUParam => 2
      case param:SpMUParam => 5
      // case param:PMUParam if param.isSparse == 0 => 2
      case param:DramAGParam => 3
      case param:PCUParam => 4
    })
  }

  override def quote(n:Any) = n match {
    case n:GlobalContainer =>
      s"${config.modulePrefix.getOrElse(s"/$topName")}/$n"
    case _ => super.quote(n)
  }

}

