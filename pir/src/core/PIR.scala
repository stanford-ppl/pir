package pir

import prism.graph._
import pir.node._

trait PIR extends Compiler with PIREnv {

  override val logExtensions = super.logExtensions ++ List(".py", ".cluster")
  override lazy val config = new PIRConfig(this)

  def handle(e:Throwable):Try[Boolean] = Failure(e)

  override def getDesign = {
    states
  }
  
  override def loadDesign(loaded:Any) = {
    _states = Some(loaded.asInstanceOf[States])
  }

}
