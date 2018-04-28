package prism.codegen

import scala.collection.mutable._

trait CSVCodegen extends Codegen {

  val headers = ListBuffer[String]()
  val rows = ListBuffer[Row]()

  class Row {
    val cell:Map[String, String] = Map.empty
  
    def update(pair:(String, Any)) = {
      val (header, value) = pair
      if (!headers.contains(header)) headers +=  header
      cell += header -> s"$value"
    }
  }


  def newRow:Row = {
    val row = new Row
    rows += row
    row
  }

  def emit(row:Row) = {
    emitln(s"${headers.map( h => row.cell.getOrElse(h, "") ).mkString(",")}")
  }

  override def finPass = {
    emitln(headers.mkString(","))
    rows.foreach(emit)
    super.finPass
  }
}
