package pir
package mapper

import pir.node._
import spade.node._
import prism.collection.immutable._

import scala.collection.mutable

trait CUPruner extends PIRPass with prism.util.Memorization {

  val constrains = ListBuffer[CUConstrain]()
  def costConstrains = constrains.collect { case c:CUCostConstrain => c }
  if (!spadeParam.isAsic) {
    constrains += new CUCostConstrain
  }

  def initCUMap:EOption[TopMap] = dbgblk(s"initCUMap") {
    pirMap.map { _.set[CUMap] { 
      var cumap = CUMap.empty
      val topP = compiler.top
      val topS = compiler.arch.top
      val pnodes = pirTop.collectDown[CUMap.K]()
      val snodes = spadeTop.collectDown[CUMap.V]().filterNot { _.isInstanceOf[Connector] }
      cumap ++= pnodes.toSet -> snodes.toSet
      cumap
    } }
  }

  def prune(cumap:CUMap):EOption[CUMap] = dbgblk(s"prune") {
    flatFold(constrains, cumap) { case (cumap, constrain) => constrain.prune(cumap) }
  }

  def prune(cumap:CUMap, key:CUMap.K) = {
    flatFold(costConstrains, cumap) { case (cumap, constrain) => constrain.prune(cumap, key) }
  }

  override def initPass = {
    super.initPass
    constrains.collect { case c:prism.util.Memorization => c }.foreach { _.resetAllCaches }
  }

}

