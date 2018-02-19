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
  
  val ctMap = mutable.Map[Controller, Container]()

  val controllerTraversal = new ControllerTraversal with UnitTraversal {
    override def visitNode(n:N, prev:T):T = {
      val cu = n match {
        case n:TopController => design.top
        case n:ArgInController => design.top.argFringe
        case n => CUContainer().setParent(design.top).name(s"${qtype(n)}").ctrl(n)
      }
      dbg(s"${qtype(n)} -> ${qtype(cu)}")
      ctMap += n -> cu
      super.visitNode(n, prev)
    }
  }

  override def runPass =  {
    createCUForController
    traverseNode(design.top)
  }

  def createCUForController = {
    controllerTraversal.traverseNode(design.top.topController, ())
  }

  override def swapParent(node:N, newParent:N) = {
    dbg(s"swapParent ${qtype(node)} newParent=${qtype(newParent)}")
    super.swapParent(node, newParent)
    dbg(s"node=${node.parent}")
  }

  override def visitNode(n:N):Unit = {
    dbg(s"visitNode ${qdef(n)}")
    n match {
      case n:Memory if isRemoteMem(n) & !withinGlobal(n) => swapParent(n, CUContainer().setParent(design.top).name(s"${qtype(n)}")) 
      case n:ComputeNode if !ctMap(ctrlOf(n)).isAncestorOf(n) => swapParent(n, ctMap(ctrlOf(n)))
      case _ => super.visitNode(n)
    }
  }

}

