package pir
package mapper

import pir.node._
import pir.pass._
import prism.graph._
import prism.collection.immutable._

trait PartitionCost extends CUCostUtil {
  def isPartitionDep(out:Output[PIRNode]):Boolean = true

  override protected def compCost(x:Any, ct:ClassTag[_]):Cost[_] = dbgblk(s"getCost($x,${ct.toString.split("\\.").last})") {
    switch[InputCost](x,ct) { 
      case x: Partition => 
        val scope = x.scope
        val scopeCovered = scope.flatMap { _.descendentTree }.toSet
        scope.map { n =>
          n.descendentTree.map { n =>
            val outs = n.localEdges.collect { case in:Input[PIRNode] => in }.flatMap { in =>
              in.as[Input[PIRNode]].connected.find { out => isPartitionDep(out) && !scopeCovered.contains(out.src) }
            }.toSet
            val (vins, sins) = outs.partition { isVec(_) }
            InputCost(sins.size, vins.size).scheduledBy(scope.size)
          }.reduceOption { _ + _ }.getOrElse(InputCost())
        }.reduceOption { _ + _ }.getOrElse(InputCost())
        //dbg(s"deps=$deps")
    } orElse switch[OutputCost](x,ct) { 
      case x: Partition => 
        val scope = x.scope
        val scopeCovered = scope.flatMap { _.descendentTree }.toSet
        scope.map { n => 
          n.descendentTree.map { n =>
            val outs = n.localEdges.collect { case o:Output[PIRNode] => o }.filter { out =>
              out.connected.exists { in => !scopeCovered.contains(in.src) }
            }.toSet
            val (vouts, souts) = outs.partition { isVec(_) }
            OutputCost(souts.size, vouts.size).scheduledBy(scope.size)
          }.reduceOption { _ + _ }.getOrElse(OutputCost())
        }.reduceOption { _ + _ }.getOrElse(OutputCost())
    } orElse switch[PRCost](x,ct) { 
      case x: Partition => 
        val scope = x.scope.filter {
          case _:OpNode => true
          case _ => false
        }
        val cost = getPRCost(x, scope)
        //breakPoint(s"$scope $cost")
        cost
    } orElse x.to[Partition].map { x =>
      type C = C forSome{ type C <:Cost[C] }
      x.scope.map { _.getCost[C](ct.as) }.reduce[Cost[_]]{ (a,b) => a.add(b) }
    } getOrElse super.compCost(x, ct)
  }
}

class Partition(val scope:List[PIRNode]) {
  var delay:Option[Int] = None
  override def toString = s"${super.toString.split("\\.").last}(${scope.size},delay=$delay)"
}
object Partition {
  def unapply(x:Partition):Option[List[PIRNode]] = Some(x.scope)
}

