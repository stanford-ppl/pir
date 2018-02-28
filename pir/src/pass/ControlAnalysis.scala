package pir.pass

import pir._
import pir.node._

import pirc._

import scala.collection.mutable
import scala.reflect._
import pirc.util._

trait ControlAnalysis extends PIRTransformer {

  def allocateCounterDone(counter:Primitive)(implicit compiler:PIR) = {
    import compiler.pirmeta._
    val context = contextOf(counter).get
    allocate[CounterDone](context, _.counter == counter){
      val done = CounterDone(counter)
      ctrlOf(done) = ctrlOf(counter)
      done
    }
  }

  def allocate[T<:PIRNode:ClassTag](
    container:Container, 
    filter:T => Boolean = (n:T) => true
  )(newNode: => T)(implicit compiler:PIR):T = dbgblk(s"allocate(container=$container, T=${implicitly[ClassTag[T]]})"){
    import compiler.pirmeta._
    val nodes = collectDown[T](container).filter(filter)
    assert(nodes.size <= 1, s"more than 1 node in container: $nodes")
    nodes.headOption.getOrElse { 
      val node = newNode 
      (node, container) match {
        case (node:Primitive, context:ComputeContext) if !ctrlOf.contains(node) =>
          ctrlOf(node) = innerCtrlOf(context)
        case node => 
      }
      node.setParent(container)
    }
  }

  def insertGlobalIO(from:Def, toCtx:ComputeContext)(validFunc: => ControlNode):Def = {
    val fromCtx = contextOf(from).get
    val fromCU = globalOf(fromCtx).get
    val toCU = globalOf(toCtx).get
    if (fromCU == toCU) return from 
    val valid = validFunc
    val gout = allocate(fromCtx, (n:GlobalOutput) => n.data==from && n.valid==valid)(GlobalOutput(from, valid))
    allocate[GlobalInput](toCtx, _.globalOutput==gout)(GlobalInput(gout))
  }

}
