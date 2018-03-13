package pir.codegen

import pir._
import prism.Config

class PlastimaCodegen(implicit design: PIR) extends PIRPass {
  def shouldRun = false && Config.codegen

  //val codegen = new spade.codegen.ConfigCodegen()(design.arch)

  override def runPass =  {
    //codegen.runPass
  }

}
