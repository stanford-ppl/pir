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

  val controllerTraversal = new ControllerSiblingFirstTraversal with prism.traversal.UnitTraversal with Logging {
    override lazy val logger = CUInsertion.this.logger
    implicit val designP = CUInsertion.this.designP
    override def visitNode(n:N, prev:T):T = {
      val cu = n match {
        case n:TopController => 
        case n@(_:ArgInController | _:ArgOutController) => 
        case n:DramController => 
        case n => 
          val cu = CUContainer().setParent(CUInsertion.this.compiler.top).ctrl(n)
          n.name.foreach { name => cu.name(name) }
          dbg(s"${qtype(n)} -> ${qtype(cu)}")
          ctMap += n -> cu
      }
      super.visitNode(n, prev)
    }
  }

  override def runPass =  {
    createCUForController
    super.runPass
  }

  def createCUForController = {
    controllerTraversal.traverseTop
  }

  override def visitNode(n:N):Unit = {
    dbg(s"visitNode ${qdef(n)} ${ctrlOf.get(n)}")
    n match {
      case n if within[GlobalContainer](n) =>
      case n:Memory if isRemoteMem(n) => swapParent(n, CUContainer().setParent(compiler.top).name(s"${qtype(n)}")) 
      case n:ComputeNode if !ctMap(ctrlOf(n)).isAncestorOf(n) => swapParent(n, ctMap(ctrlOf(n)))
      case _ => super.visitNode(n)
    }
  }

}

