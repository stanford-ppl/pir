package pir
package codegen

import pir.node._
import prism.graph._
import scala.collection.mutable

trait TungstenBlackBoxGen extends TungstenCodegen with TungstenCtxGen {

  private var enterBB = false

  def withinBB(block: => Unit) = {
    val saved = enterBB
    enterBB = true
    block
    enterBB = saved
  }

  override def emitNode(n:N) = n match {
    case n:LocalOutAccess if enterBB =>
      genTopMember(n, Seq(n.qstr))

    case n if enterBB => visitNode(n)

    case n => super.emitNode(n)
  }

}

