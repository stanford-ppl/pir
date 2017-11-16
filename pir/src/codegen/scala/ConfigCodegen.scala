package pir.codegen

import pir._
import pirc.Config

class ConfigCodegen(implicit design: PIR) extends Pass {
  def shouldRun = design.pirMapping.succeeded && Config.codegen

  val codegen = new spade.codegen.ConfigCodegen()(design.arch)

  addPass(canRun=true, runCount=1) {
    codegen.init(design.mapping.get)
    codegen.run
  }

}
