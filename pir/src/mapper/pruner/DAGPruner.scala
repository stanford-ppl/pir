package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class DAGPruner(implicit compiler:PIR) extends CUPruner {

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic =>
      flatFold(x.freeKeys.filter { _.isDAG.get }, x) { case (x, k) =>
        val vs = x.freeValuesOf(k)
        val (isDAGs, nonDAGs) = vs.partition { _.params.get.isInstanceOf[DramAGParam] }
        val fits = isDAGs.nonEmpty
        dbg(s"$k fits in DramAGParam=$fits")
        if (fits) {
          x.filterNotAtKey(k) { v => nonDAGs.contains(v) }
        } else Right(x)
      }.flatMap { x =>
        if (config.deadicatedDAG) {
          flatFold(x.freeKeys.filterNot { _.isDAG.get }, x) { case (x, k) =>
            val vs = x.freeValuesOf(k)
            val (isDAGs, nonDAGs) = vs.partition { _.params.get.isInstanceOf[DramAGParam] }
            x.filterNotAtKey(k) { v => isDAGs.contains(v) }
          }
        } else Right(x)
      }.asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }

  override def initPass = {
    super.initPass
    val dags = scala.collection.mutable.Set[GlobalContainer]()
    topMap.foreach { tmap =>
      val globals = tmap.cumap.freeKeys
      val fringes = globals.collect { case global:DRAMFringe => global }
      fringes.foreach { fringe =>
        fringe.collectDown[DRAMCommand]().foreach { 
          case command:DRAMDenseCommand =>
            dags ++= trace(command.offset) ++ trace(command.size)
          case command:DRAMSparseCommand =>
            dags ++= trace(command.addr)
        }
      }
      dbg(s"Find DAGs: $dags")
      dags.foreach { _.isDAG := true }
    }
  }

  def trace(input:Input[PIRNode]):Iterable[GlobalContainer] = {
    input.neighbors.flatMap { _.collect[GlobalOutput](visitGlobalIn _).map{ _.global.get } }
  }

}
