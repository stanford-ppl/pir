package spade

import spade.node._
import spade.param._
import spade.codegen._

import java.io._

trait Spade extends Compiler with SpadeWorld {

  val configs = List(Config, SpadeConfig)

  lazy val spademeta:SpadeMetadata = design.spademeta
  def top = design.top

  override def reset = {
    super[Compiler].reset
  }

  def handle(e:Exception):Unit = {
    //logger.close
    throw e
  }

  def load = SpadeConfig.loadDesign
  def save = SpadeConfig.saveDesign

  val designPath = s"${outDir}${separator}${name}.spade"

  lazy val designParam:DesignParam = DesignParam() // Default. Override by different designs

  def newDesign = design = {
    Factory.logger.withOpen(compiler.outDir, s"top.log", append=false) {
      Factory.create(designParam)
    }
  }

  /* Analysis */
  //TODO: Area model

  /* Passes */
  //lazy val areaModel = new AreaModel()

  /* Codegen */
  //lazy val spadeNetworkCodegen = new SpadeNetworkCodegen()
  lazy val statCodegen = new StatCodegen()
  lazy val paramScalaCodegen = new ParamScalaCodegen(s"GeneratedParameters.scala")

  /* Debug */
  //lazy val spadePrinter = new SpadePrinter()

  override def initSession = {
    val sess = session
    import sess._

    // Pass
    //addPass(areaModel)

    // Debug
    //addPass(new SpadeIRPrinter(s"spade.txt"))
    addPass(new ParamIRPrinter(s"param.txt"))
    addPass(new NetworkDotCodegen[Bit](s"control.dot"))
    addPass(new NetworkDotCodegen[Word](s"scalar.dot"))
    addPass(new NetworkDotCodegen[Vector](s"vector.dot"))
    //addPass(new SpadeIRDotCodegen[PCU](s"pcu.dot"))
    //addPass(new SpadeIRDotCodegen[PMU](s"pmu.dot"))
    //addPass(new SpadeIRDotCodegen[SCU](s"scu.dot"))
    //addPass(spadePrinter)

    // Codegen
    addPass(statCodegen)
    addPass(paramScalaCodegen)
    //addPass(spadeNetworkCodegen)
    //addPass(spadeParamCodegen)
  }

}
