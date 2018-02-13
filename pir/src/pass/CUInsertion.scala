package pir.pass

import pir._
import pir.node._

import pirc._

import prism.traversal._

import scala.collection.mutable

class CUInsertion(implicit design:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal {

  import pirmeta._

  override def shouldRun = true

  override def initPass = {
    super.initPass
    controllerTraversal.resetTraversal
  }
  
  val cuMap = mutable.Map[Controller, GlobalContainer]()

  val controllerTraversal = new ControllerTraversal with UnitTraversal {
    override def visitNode(n:N, prev:T):T = {
      val cu = n match {
        case n:TopController => design.newTop
        case n:ArgController => design.newTop.argFringe
        case n => CUContainer().setParent(design.newTop).name(s"${qtype(n)}").ctrl(n)
      }
      dbg(s"${qtype(n)} -> ${qtype(cu)}")
      cuMap += n -> cu
      super.visitNode(n, prev)
    }
  }

  override def runPass =  {
    createCUForController
    traverseNode(design.newTop)
  }

  def createCUForController = {
    controllerTraversal.traverseNode(design.newTop.topController, ())
  }

  override def swapParent(node:N, newParent:N) = {
    dbg(s"swapParent ${qtype(node)} newParent=${qtype(newParent)}")
    super.swapParent(node, newParent)
  }

  override def visitNode(n:N):Unit = {
    dbg(s"visitNode ${qdef(n)}")
    n match {
      case n:Memory if isRemoteMem(n) => swapParent(n, CUContainer().setParent(design.newTop).name(s"${qtype(n)}")) 
      case n:ComputeNode if !cuMap(ctrlOf(n)).isParentOf(n) => swapParent(n, cuMap(ctrlOf(n)))
      case _ => super.visitNode(n)
    }
  }

}

