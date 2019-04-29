package spade

import spade.node._
import spade.param._
import prism._

abstract class SpadePass(implicit override val compiler:Spade) extends Pass with Env {

  override def states = compiler.env.states
  override def config:SpadeConfig = compiler.config

  //override def dquote(n:Any):String = n match {
    //case n:SpadeNode => n.qindex
    //case _ => n.toString
  //}

}
trait SpadeTraversal {
  type N = SpadeNode
}

trait ParamTraversal {
  type N = Parameter
}
