package pir
package pass

import pir.node._

trait BuildEnvironment {

  private lazy val logger = this match {
    case logger:Logging => Some(logger)
    case logger => None
  }

  def designP:PIRDesign

  def currentParent = designP.parentStack.headOption
  def currentCtrl = designP.ctrlStack.headOption

  def setCurrentParent(n:PIRNode) = {
    currentParent.foreach { p => 
      n.setParent(p)
      //dbg(logger, s"$n.setCurrentParent($p)")
    }
  }

  def resetCurrentParent(n:PIRNode) = {
    currentParent.foreach { p => 
      n.unsetParent
      n.setParent(p)
      //dbg(logger, s"$n.resetCurrentParent($p)")
    }
  }

  def setCurrentCtrl(n:PIRNode) = {
    currentCtrl.foreach { c => 
      designP.pirmeta.ctrlOf(n) = c
      //dbg(logger, s"$n.setCurrentCtrl($c)")
    }
  }

  def withParent[T](p:Container)(scope: => T) = dbgblk(logger, s"withParent($p)"){
    designP.parentStack.push(p)
    val res = scope
    designP.parentStack.pop
    res
  }

  def withCtrl[T](ctrl:Controller)(scope: => T) = dbgblk(logger, s"withCtrl($ctrl)"){
    designP.ctrlStack.push(ctrl)
    val res = scope
    designP.ctrlStack.pop
    res
  }

  def withParentCtrl[T](parent:Container, ctrl:Controller)(scope: => T) = {
    withParent(parent) {
      withCtrl(ctrl) {
        scope
      }
    }
  }

}
