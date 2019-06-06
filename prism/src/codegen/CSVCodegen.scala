package prism
package codegen

import scala.collection.mutable

trait CSVCodegen extends Codegen {

  val headers = ListBuffer[String]()
  val rows = ListBuffer[Row]()

  class Row {
    val cell:mutable.Map[String, String] = mutable.Map.empty
  
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

  def setHeaders(header:String*) = { headers ++= header }

  override def finPass = {
    emitln(headers.mkString(","))
    rows.foreach(emit)
    super.finPass
  }
}
