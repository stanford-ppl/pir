package pir
package codegen

import pir.node._
import prism.graph._
import scala.collection.mutable

trait TungstenBlackBoxGen extends TungstenCodegen with TungstenCtxGen {

  private var enterBB = false
  private var globalBB = false

  def withinBB(global:Boolean)(block: => Unit) = {
    val saved = enterBB
    val savedGlobal = global
    enterBB = true
    globalBB = global
    block
    enterBB = saved
    globalBB = savedGlobal
  }

  override def emitNode(n:N) = n match {
    case n:LocalOutAccess if enterBB =>
      if (!globalBB) genTopMember(n, Seq(n.qstr))

    case n if enterBB => visitNode(n)

    case n => super.emitNode(n)
  }

}
