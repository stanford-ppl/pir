package prism.node

import prism._
import prism.util._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

trait ProductSubGraph[N<:Node[N]] extends ProductNode[N] with SubGraph[N] { self:N with ProductSubGraph[N] =>
  override def connectFields(x:Any, i:Int)(implicit design:Design):Any = {
    implicit val ev = nct
    x match {
      case x:N => this.addChild(x); x
      case x => super.connectFields(x, i)
    }
  }
}

