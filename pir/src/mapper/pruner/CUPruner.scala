package pir
package mapper

import pir.node._
import prism.collection.immutable._

import scala.collection.mutable

trait CUPruner extends PIRPass {
  import pirmeta._

  val constrains = ListBuffer[CUConstrain]()
  if (isStatic(designS) || isDynamic(designS)) {
    constrains += new CUCostConstrain
  }

  def initCUMap:EOption[PIRMap] = dbgblk(s"initCUMap") {
    pirMap.map { _.set[CUMap] { 
      var cumap = CUMap.empty
      val topP = compiler.top
      val topS = compiler.arch.top
      val pnodes = topP.collectDown[CUMap.K]()
      val snodes = topS.collectDown[CUMap.V]().filterNot { rt =>
        rt.isInstanceOf[spade.node.SwitchBox] || rt.isInstanceOf[spade.node.Router]
      }
      cumap ++= pnodes.toSet -> snodes.toSet
      cumap
    } }
  }

  def prune(cumap:CUMap):EOption[CUMap] = dbgblk(s"prune") {
    flatFold(constrains, cumap) { case (cumap, constrain) => constrain.prune(cumap) }
  }

  def prune(cumap:CUMap, key:CUMap.K) = {
    val costConstrains = constrains.collect { case c:CUCostConstrain => c }
    flatFold(costConstrains, cumap) { case (cumap, constrain) => constrain.prune(cumap, key) }
  }

}

