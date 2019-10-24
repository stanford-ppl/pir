package pir

import prism.graph._
import pir.node._
import pir.util._
import pir.pass._
//import pir.mapper._

abstract class PIRPass(implicit override val compiler:PIR) extends Pass 
  with PIREnv 
  with PIRDebugger 
  with GraphUtilImplicits 
  with CollectorImplicit
  with TypeUtil
  with RuntimeUtil
  {

  override def states = compiler.pirenv.states
  override def config:PIRConfig = compiler.config

  override def dquote(x:Any) = x match {
    case x:ControlTree if x.sname.nonEmpty => s"$x[${x.sname.get}]"
    case Const(v) => s"${super.dquote(x)}($v)"
    case OpDef(op) => s"${super.dquote(x)}($op)"
    case x:PIRNode if x.sname.nonEmpty => s"$x[${x.sname.get}]"
    case x:Edge[n,_,_] => s"${dquote(x.src)}.$x"
    case x:DRAM => s"$x[${x.sid}]"
    case x => super.dquote(x)
  }
}
trait PIRTraversal extends PIRPass {
  type N = PIRNode
  def top = compiler.pirenv.pirTop
}
trait ControlTreeTraversal extends PIRPass {
  type N = ControlTree
  def top = compiler.pirenv.pirTop.topCtrl
}
