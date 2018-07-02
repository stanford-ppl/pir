package pir
package mapper

import pir.node._
import spade.node._
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

  override def initPass = {
    super.initPass
    constrains.foreach { _.resetAllCaches }
  }

  override def quote(n:Any) = n match {
    case n:GlobalContainer => s"${super.quote(n)}(${cuType(n).get})"
    case n:Set[_] if n.forall { _.isInstanceOf[Routable] } =>
      super.quote(n.map { case n:Routable => n.param })
    case n => super.quote(n)
  }

}

