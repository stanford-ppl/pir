package pir
package pass

import pir.node._
import scala.collection.mutable

abstract class ControlTransformer(implicit compiler:PIR) extends PIRTransformer { self:PIRTraversal =>
  import pirmeta._

  def allocateWithFields[T<:PIRNode:ClassTag:TypeTag](fields:Any*):T = {
    val args = fields :+ designP
    def newNode = {
      val constructor = implicitly[ClassTag[T]].runtimeClass.getConstructors()(0)
      constructor.newInstance(args.map(_.asInstanceOf[Object]):_*).asInstanceOf[T]
    }
    allocate((n:T) => n.values == fields)(newNode)
  }

  def insertGlobalIO(
    from:Def, 
    toCtx:ComputeContext
  )(validFunc: => ControlNode)(readyFunc: => ControlNode):Def = dbgblk(s"insertGlobalIO($from, $toCtx)") {
    val fromCtx = contextOf(from).get
    val fromCU = globalOf(fromCtx).get
    val toCU = globalOf(toCtx).get
    if (fromCU == toCU) from else {
      val gout = withParent(fromCtx) { allocateWithFields[GlobalOutput](from,validFunc) }
      withParent(toCtx) {
        if (compiler.arch.designParam.topParam.busWithReady) {
          allocateWithFields[ReadyValidGlobalInput](gout, readyFunc)
        } else {
          allocateWithFields[ValidGlobalInput](gout)
        }
      }
    }
  }

}
