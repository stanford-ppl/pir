package pir
package pass

import pir.node._

import scala.collection.mutable

class CUInsertion(implicit compiler:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal {

  import pirmeta._

  override def initPass = {
    super.initPass
    controllerTraversal.resetTraversal
  }
  
  val ctMap = mutable.Map[Controller, Container]()

  val controllerTraversal = new ControllerTraversal with prism.traversal.UnitTraversal {
    override def visitNode(n:N, prev:T):T = {
      val cu = n match {
        case n:TopController => 
        case n@(_:ArgInController | _:ArgOutController) => 
        case n:DramController => 
        case n => 
          val cu = CUContainer().setParent(compiler.top).ctrl(n)
          n.name.foreach { name => cu.name(name) }
          dbg(s"${qtype(n)} -> ${qtype(cu)}")
          ctMap += n -> cu
      }
      super.visitNode(n, prev)
    }
  }

  override def runPass =  {
    createCUForController
    traverseNode(compiler.top)
  }

  def createCUForController = {
    controllerTraversal.traverseNode(compiler.top.topController, ())
  }

  override def visitNode(n:N):Unit = {
    dbg(s"visitNode ${qdef(n)}")
    n match {
      case n if within[GlobalContainer](n) =>
      case n:Memory if isRemoteMem(n) => swapParent(n, CUContainer().setParent(compiler.top).name(s"${qtype(n)}")) 
      case n:ComputeNode if !ctMap(ctrlOf(n)).isAncestorOf(n) => swapParent(n, ctMap(ctrlOf(n)))
      case _ => super.visitNode(n)
    }
  }

}

