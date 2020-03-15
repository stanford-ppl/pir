package pir
package mapper

import pir.node._

class MappingInitializer(implicit compiler:PIR) extends PIRPass with MappingLogger {

  def getOpt[T](name:String):Option[T] = config.getOption[T](name)

  override def runPass = {

    val pnodes = pirTop.collectDown[CUMap.K]().filterNot { _.isInstanceOf[BlackBoxContainer] }
    val snodes = spadeTop.cus
    val tmap = TopMap(CUMap() ++ (pnodes.toSet -> snodes.toSet))
    compiler.states.topMap = Right(tmap)
  }

}
