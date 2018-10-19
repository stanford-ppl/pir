package spade

//import spade.node._
import spade.param2._
import spade.node._
//import spade.codegen._

import java.io._

trait Spade extends Compiler with BaseFactory {

  override lazy val config:SpadeConfig = new SpadeConfig(this)

  def handle(e:Exception):Unit = throw e

  //lazy val spadeTop = Factory.create[Top](topParam)

  /* Analysis */
  //TODO: Area model

  //lazy val areaModel = new AreaModel()

  /* Codegen */
  //lazy val spadeNetworkCodegen = new SpadeNetworkCodegen()
  //lazy val statCodegen = new StatCodegen()
  //lazy val paramScalaCodegen = new ParamScalaCodegen(s"GeneratedParameters.scala")

  /* Debug */
  //lazy val spadePrinter = new SpadePrinter()

  //override def initSession = {
    //val sess = session
    //import sess._

    //// Pass
    ////addPass(areaModel)

    //// Debug
    ////addPass(new SpadeIRPrinter(s"spade.txt"))
    //addPass(new ParamIRPrinter(s"param.txt"))
    //addPass(new NetworkDotCodegen[Bit](s"control.dot"))
    //addPass(new NetworkDotCodegen[Word](s"scalar.dot"))
    //addPass(new NetworkDotCodegen[Vector](s"vector.dot"))
    ////addPass(new SpadeIRDotCodegen[PCU](s"pcu.dot"))
    ////addPass(new SpadeIRDotCodegen[PMU](s"pmu.dot"))
    ////addPass(new SpadeIRDotCodegen[SCU](s"scu.dot"))
    ////addPass(spadePrinter)

    //// Codegen
    //addPass(statCodegen)
    //addPass(paramScalaCodegen)
    ////addPass(spadeNetworkCodegen)
    ////addPass(spadeParamCodegen)
  //}

}
