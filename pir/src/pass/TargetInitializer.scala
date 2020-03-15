package pir
package pass

import spade.param._
import spade.pass._

class TargetInitializer(implicit compiler:PIR) extends PIRPass with DefaultParamLoader with BaseFactory {

  def getOpt[T](name:String):Option[T] = config.getOption[T](name)

  override def runPass = {
    compiler.states.spadeParam = loadParam
    compiler.states.spadeTop = create[spade.node.Top](spadeParam)
  }

}
