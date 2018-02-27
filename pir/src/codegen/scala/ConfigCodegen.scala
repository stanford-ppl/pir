package pir.codegen

import pir._
import pirc.Config

class ConfigCodegen(implicit design: PIR) extends PIRPass {
  def shouldRun = false && Config.codegen

  //val codegen = new spade.codegen.ConfigCodegen()(design.arch)

  override def runPass =  {
    //codegen.runPass
  }

}
