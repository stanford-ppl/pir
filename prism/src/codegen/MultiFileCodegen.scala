package prism
package codegen

trait MultiFileCodegen extends Printer {
  var lineNumber:Int = 0
  var fileNumber:Int = 0

  def traitName:String 
  def dir:String

  def isSplitting = false //TODO

  //override def write(s:String):Unit = {
    //if (lineNumber > 100) {
      //closeFile
      //openFile
    //}
    //if (isSplitting) {
      //printer.pprint(s); lineNumber += 1false //TODO
    //} else {
      //super.pprint(s)
    //}
  //}
  //override def writeln(s:String):Unit = write(s"$s\n")

  def splitPreHeader:Unit = {
  }

  def splitPostHeader:Unit = {
  }

  def splitPreFooter:Unit = {
  }

  def splitPostFooter:Unit = {
  }

  def openFile = {
    fileNumber += 1
    //printer = new Printer {
      //override lazy val stream = newStream(dir, s"$traitName$fileNumber.scala") 
    //}
    lineNumber = 0
    splitPreHeader
    val prevFile = if (fileNumber==1) "" else s" extends ${traitName}${fileNumber-1} "
    emitBSln(s"trait $traitName$fileNumber$prevFile")
    splitPostHeader
  }

  def closeFile = {
    lineNumber = 0
    splitPreFooter
    emitBEln
    //printer.close
  }

  def emitSplit(block: =>Unit):Unit = {
    openFile
    block
    closeFile
  }

  def emitMixed(block: => Unit):Unit = {
    emitBlock(s"trait $traitName extends ${(0 until fileNumber).map(i => s"${traitName}${i+1}").mkString(" with ")}") {
//    emitBlock(s"trait $traitName extends ${traitName}${fileNumber}") {
      block
    }
  }

}
