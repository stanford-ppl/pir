package pir.codegen

import pir.node._
import pir.pass._
import prism.codegen._

class TerminalCSVCodegen(implicit compiler: PIR) extends PlastisimCodegen with CSVCodegen {
  import pirmeta._

  val fileName = s"node.csv"

  def cuTypeToInt(tp:String) = tp match {
    case "afg" => 0 // arg fringe
    case "dfg" => 1 // dram fringe
    case "pmu" => 2 // pmu
    case "dag" => 3 // dram address generator
    case "scu" => 4 // scalar cu
    case "pcu" => 5 // vector cu
    case "ocu" => 6 // cu with only control logic
  }

  override def emitNode(n:N) = n match {
    case n:GlobalContainer =>
      val row = newRow
      row("node") = n
      row("tp") = cuTypeToInt(cuType(n).get)
    case n => super.visitNode(n)
  }

}

