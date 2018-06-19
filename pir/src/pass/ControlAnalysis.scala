package pir
package pass

import pir.node._
import scala.collection.mutable

abstract class ControlAnalysis(implicit compiler:PIR) extends PIRTransformer { self:PIRTraversal =>
  import pirmeta._

  override def allocate[T<:PIRNode:ClassTag:TypeTag](
    container:Container, 
    filter:T => Boolean = (n:T) => true
  )(newNode: => T):T = {
    val node = super.allocate[T](container, filter)(newNode)
    (node, container) match {
      case (node:Primitive, context:ComputeContext) if !ctrlOf.contains(node) =>
        ctrlOf(node) = innerCtrlOf(context)
      case node => 
    }
    node
  }

  def allocateWithFields[T<:PIRNode:ClassTag:TypeTag](fields:Any*)(container:Container):T = {
    val args = fields :+ designP
    def newNode = {
      val constructor = implicitly[ClassTag[T]].runtimeClass.getConstructors()(0)
      constructor.newInstance(args.map(_.asInstanceOf[Object]):_*).asInstanceOf[T]
    }
    allocate(container, (n:T) => n.values == fields)(newNode)
  }

  def insertGlobalIO(
    from:Def, 
    toCtx:ComputeContext
  )(validFunc: => ControlNode)(readyFunc: => ControlNode):Def = dbgblk(s"insertGlobalIO($from, $toCtx)") {
    val fromCtx = contextOf(from).get
    val fromCU = globalOf(fromCtx).get
    val toCU = globalOf(toCtx).get
    if (fromCU == toCU) from else {
      val gout = allocateWithFields[GlobalOutput](from,validFunc)(fromCtx)
      if (compiler.arch.designParam.topParam.busWithReady) {
        allocateWithFields[ReadyValidGlobalInput](gout, readyFunc)(toCtx)
      } else {
        allocateWithFields[ValidGlobalInput](gout)(toCtx)
      }
    }
  }


}
