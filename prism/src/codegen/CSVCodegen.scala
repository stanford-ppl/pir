package prism
package codegen

import scala.collection.mutable

trait CSVRow {
  val cell:mutable.Map[String, String] = mutable.Map.empty

  def update(pair:(String, Any)):Unit
}

trait CSVPrinter extends Printer {

  type Row = CSVRow

  def dirName:String
  def fileName:String
  def append:Boolean
  def printHeader:Boolean = true

  val headers = ListBuffer[String]()
  val rows = ListBuffer[Row]()

  class CSVRowInst extends CSVRow {
    def update(pair:(String, Any)) = {
      val (header, value) = pair
      if (!headers.contains(header)) headers +=  header
      cell += header -> s"$value"
    }
  }

  def newRow:Row = {
    val row = new CSVRowInst
    rows += row
    row
  }

  def setHeaders(header:String*) = { headers ++= header }

  def emitCSV = {
    if (printHeader) emitln(headers.mkString(","))
    rows.foreach { row =>
      emitln(s"${headers.map( h => row.cell.getOrElse(h, "") ).mkString(",")}")
    }
    rows.clear
    headers.clear
  }

  def gencsv = {
    withOpen(dirName, fileName, append) {
      emitCSV
    }
  }
}

trait CSVCodegen extends Codegen with CSVPrinter {

  override def finPass = {
    emitCSV
    super.finPass
  }
}

