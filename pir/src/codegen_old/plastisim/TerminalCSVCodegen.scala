package pir
package codegen

import pir.node._
import spade.param._
import pir.pass._
import prism.codegen._

class TerminalCSVCodegen(implicit compiler: PIR) extends PlastisimCodegen with CSVCodegen {
  import pirmeta._

  val fileName = s"node.csv"

  override def runPass = {
    pirMap.foreach { pmap =>
      pmap.cumap.usedMap.foreach { case (cuP, cuS) =>
        val row = newRow
        row("node") = cuP.id
        row("tp") = cuS.param match {
          case param:ArgFringeParam => 0
          case param:MCParam => 1
          case param:PMUParam => 2
          case param:DramAGParam => 3
          case param:PCUParam => 4
        }
      }
    }
  }

  //def cuTypeToInt(tp:String) = tp match {
    //case "afg" => 0 // arg fringe
    //case "dfg" => 1 // dram fringe
    //case "sfg" => 1 // strea fringe
    //case "pmu" => 2 // pmu
    //case "dag" => 3 // dram address generator
    //case "scu" => 4 // scalar cu
    //case "pcu" => 4 // vector cu
    //case "ocu" => 4 // cu with only control logic
  //}

  //override def emitNode(n:N) = n match {
    //case n:GlobalContainer =>
      //val row = newRow
      //row("node") = n.id
      //row("tp") = cuTypeToInt(cuType(n).get)
    //case n => super.visitNode(n)
  //}

}

