package pir

import prism.graph._
import pir.node._
import pir.codegen._

trait PIR extends Compiler {

  val pirenv = new PIREnv {}
  def states = pirenv.states

  override val logExtensions = super.logExtensions ++ List(".py", ".cluster")
  override lazy val config = new PIRConfig(this)

  def handle(e:Throwable):Try[Boolean] = {
    handle
    Failure(e)
  }

  override def handle:Unit = {
    if (config.enableDot) {
      tryDot(new PIRGlobalDotGen(s"global.dot"))
      tryDot(new PIRCtxDotGen(s"ctx.dot"))
    }
    if (config.enableVerboseDot)
      tryDot(new PIRIRDotGen(s"top.dot"))
  }

  def tryDot(dotGen:PIRIRDotGen) = {
    try {
      dotGen.run
    } catch {
      case e:Exception =>
        bug[Unit](s"Fail to generate ${dotGen.fileName} $e", false)
    }
  }

  override def getDesign = {
    states
  }
  
  override def loadDesign(loaded:Any) = {
    pirenv._states = Some(loaded.asInstanceOf[States])
  }

}
