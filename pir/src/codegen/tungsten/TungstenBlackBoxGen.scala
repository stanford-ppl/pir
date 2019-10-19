package pir
package codegen

import pir.node._
import prism.graph._
import scala.collection.mutable

trait TungstenBlackBoxGen extends TungstenCodegen with TungstenCtxGen with TungstenTopGen {

  private var enterBB = false
  private var globalBB = false

  def withinBB(block: => Unit) = {
    val saved = enterBB
    enterBB = true
    block
    enterBB = saved
  }

  override def emitNode(n:N) = n match {
    case n:LocalOutAccess if enterBB =>
      if (!globalBB) genTopMember(n, Seq(n.qstr))

    case n:GlobalOutput if n.isUnder[BlackBoxContainer] =>

    case n:GlobalInput if n.isUnder[BlackBoxContainer] =>

    case n:Context if n.isUnder[BlackBoxContainer] => 
      globalBB = true
      withinBB {
        visitNode(n)
      }
      globalBB = false

    case n if enterBB => visitNode(n)

    case n => super.emitNode(n)
  }

}
