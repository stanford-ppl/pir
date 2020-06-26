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
    dbgblk(s"Total CUs") {
      spadeTop.cus.groupBy { term =>
        term.param
      }.foreach { case (param, cus) =>
        dbg(s"${param}: ${cus.size}")
      }
    }
    dbgblk(s"available CUs") {
      snodes.groupBy { term =>
        term.param
      }.foreach { case (param, cus) =>
        dbg(s"${param}: ${cus.size}")
      }
    }
  }

  def reserveResourceForBlackBox(bbs:List[GlobalContainer]) = {
    val spDramPar = bbs.map { bb => 
      bb.descendentTree.collectFirst { case block:SparseDRAMBlock =>
        block.dramPar.nextPow2
      }.getOrElse(0)
    }.sum
    dbg(s"SpDRAM par total = ${spDramPar}")
    config.updateOption[Int]("reserve-dag") { _.getOrElse(0) + spDramPar }
    config.updateOption[Int]("reserve-mc") { _.getOrElse(0) + spDramPar }
    dbg(s"reserve-dag: ${config.option[Int]("reserve-dag")}")
    dbg(s"reserve-mc: ${config.option[Int]("reserve-mc")}")
  }

}
