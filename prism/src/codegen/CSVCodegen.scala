package prism
package codegen

import scala.collection.mutable

trait CSVRow {
  val cell:mutable.Map[String, String] = mutable.Map.empty

  def update(pair:(String, Any)):Unit
}

trait CSVPrinter extends Printer {

  type Row = CSVRow

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

  def emitCSV(printHeader:Boolean) = {
    if (printHeader) emitln(headers.mkString(","))
    rows.foreach { row =>
      emitln(s"${headers.map( h => row.cell.getOrElse(h, "") ).mkString(",")}")
    }
    rows.clear
    headers.clear
  }

  def withCSV[T](dirName:String, fileName:String, append:Boolean=false, printHeader:Boolean=true)(block: => T) = {
    withOpen(dirName, fileName, append) {
      block
      emitCSV(printHeader)
    }
  }

  def writeToCSV(dirName:String, fileName:String, append:Boolean=false, printHeader:Boolean=true) = {
    withOpen(dirName, fileName, append) {
      emitCSV(printHeader)
    }
  }

}

trait CSVCodegen extends Codegen with CSVPrinter {

  def printHeader:Boolean = true

  override def finPass = {
    emitCSV(printHeader)
    super.finPass
  }
}

