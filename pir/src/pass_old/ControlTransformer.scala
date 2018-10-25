package pir
package pass

import pir.node._
import scala.collection.mutable

abstract class ControlTransformer(implicit compiler:PIR) extends PIRTransformer {
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
      val gout = from match {
        case from:GlobalInput => goutOf(from).get
        case from:GlobalOutput => from
        case from => withParent(fromCtx) { 
          allocateWithFields[GlobalOutput](from,validFunc)
        }
      }
      withParent(toCtx) {
        if (compiler.arch.designParam.topParam.busWithReady) {
          toCtx.collectDown[ReadyValidGlobalInput]().filter { gin => goutOf(gin).get == gout }.headOption.fold {
            allocateWithFields[ReadyValidGlobalInput](gout, readyFunc)
          } { case Def(gin, ReadyValidGlobalInput(gout, origReady)) =>
            val ready = readyFunc
            withCtrl(ctrlOf(ready)) {
              val newReady = And(ready, origReady)
              dbg(s"Anding $ready with original ready $origReady")
              swapConnection(gin, origReady.out, newReady.out)
              gin
            }
          }
        } else {
          allocateWithFields[ValidGlobalInput](gout)
        }
      }
    }
  }

}
