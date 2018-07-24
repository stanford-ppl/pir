package pir
package pass

import pir.node._
import pir.mapper._

abstract class PIRPass(implicit override val compiler:PIR) extends Pass 
  with prism.traversal.GraphUtil  
  with spade.SpadeAlias 
  with PIRNodeUtil 
  with RoutingUtil 
  with RuntimeUtil 
  with TypeUtil 
  with MappingUtil
  with MappingLogger {

  implicit val designP:PIRDesign = compiler.design
  lazy val pirmeta = compiler.pirmeta
  lazy val spademeta = compiler.spademeta
  lazy val designS:SpadeDesign = compiler.arch.design

  def qdef(n:Any) = n match {
    case n:PIRNode => n.qdef
    case n => s"$n"
  }

  def qtype(n:Any) = n match {
    case n:IR => n.qtype
    case n => s"$n"
  }

}
