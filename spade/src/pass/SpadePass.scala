package spade
package pass

import spade.node._

import prism._

import scala.collection.mutable

abstract class SpadePass(implicit override val compiler:Spade) extends prism.Pass {

  implicit val design:SpadeDesign = compiler.design
  lazy val spademeta: SpadeMetadata = compiler.design.spademeta
  import spademeta._

  override def quote(n:Any):String = n match {
    case n:SpadeNode => n.qindex
    case _ => n.toString
  }

}
