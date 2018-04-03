package pir.pass

import pir.node._
import prism.traversal._

import scala.collection.mutable

class CUInsertion(implicit compiler:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal {

  import pirmeta._

  override def shouldRun = true

  override def initPass(runner:RunPass[_]) = {
    super.initPass(runner)
    controllerTraversal.resetTraversal
  }
  
  val ctMap = mutable.Map[Controller, Container]()

  val controllerTraversal = new ControllerTraversal with UnitTraversal {
    override def visitNode(n:N, prev:T):T = {
      val cu = n match {
        case n:TopController => compiler.top
        case n:ArgInController => compiler.top.argFringe
        case n => CUContainer().setParent(compiler.top).name(s"${qtype(n)}").ctrl(n)
      }
      dbg(s"${qtype(n)} -> ${qtype(cu)}")
      ctMap += n -> cu
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

  override def swapParent(node:N, newParent:N) = {
    dbg(s"swapParent ${qtype(node)} newParent=${qtype(newParent)}")
    super.swapParent(node, newParent)
    dbg(s"node=${node.parent}")
  }

  override def visitNode(n:N):Unit = {
    dbg(s"visitNode ${qdef(n)}")
    n match {
      case n:Memory if isRemoteMem(n) & !withinGlobal(n) => swapParent(n, CUContainer().setParent(compiler.top).name(s"${qtype(n)}")) 
      case n:ComputeNode if !ctMap(ctrlOf(n)).isAncestorOf(n) => swapParent(n, ctMap(ctrlOf(n)))
      case _ => super.visitNode(n)
    }
  }

}

