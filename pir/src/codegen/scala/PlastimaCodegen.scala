package pir.codegen

class PlastimaCodegen(implicit design: PIR) extends PIRPass {
  def shouldRun = false && Config.codegen

  //val codegen = new spade.codegen.ConfigCodegen()(design.arch)

  override def runPass =  {
    //codegen.runPass
  }

}
