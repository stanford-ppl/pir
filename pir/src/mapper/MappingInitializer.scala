package pir
package mapper

import pir.node._

class MappingInitializer(implicit compiler:PIR) extends PIRPass with MappingLogger {

  def getOpt[T](name:String):Option[T] = config.getOption[T](name)

  override def runPass = {

    val (bbs,pnodes) = pirTop.collectDown[CUMap.K]().partition { _.isInstanceOf[BlackBoxContainer] }
    reserveResourceForBlackBox(bbs)
    val snodes = getAvailableCUs
    val tmap = TopMap(CUMap() ++ (pnodes.toSet -> snodes.toSet))
    compiler.states.topMap = Right(tmap)
  }

  def reserveResourceForBlackBox(bbs:List[GlobalContainer]) = {
    val numSpDRAM = bbs.count { bb => bb.descendentTree.exists { _.isInstanceOf[SparseDRAMBlock] } }
    config.updateOption[Int]("reserve-dag") { _.getOrElse(0) + numSpDRAM }
    config.updateOption[Int]("reserve-mc") { _.getOrElse(0) + numSpDRAM }
  }

}
