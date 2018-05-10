package pir
package codegen

class PlasmaCodegen(implicit compiler: PIR) extends PIRCodegen {

  lazy val fileName = s"$compiler.plasma"

  //val codegen = new spade.codegen.ConfigCodegen()(design.arch)

  override def runPass =  {
    //codegen.runPass
  }

}
