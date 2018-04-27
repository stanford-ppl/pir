package prism.codegen

import scala.collection.mutable._

trait CSVCodegen extends Codegen {

  val header = ListBuffer[String]()
  val rows = ListBuffer[Row]()

  class Row {
    val cell:Map[String, Any] = Map.empty
  
    def += (e:(String, Any)) = {
      val (k,v) = e
      if (!header.contains(k)) header += k
      cell += k -> v
    }
  }


  def newRow:Row = {
    val row = new Row
    rows += row
    row
  }

  def emit(row:Row) = {
    emitln(s"${header.map( h => row.cell.getOrElse(h, "") ).mkString(",")}")
  }

  def emitFile = {
    emitln(header.mkString(","))
    rows.foreach(emit)
  }
}
