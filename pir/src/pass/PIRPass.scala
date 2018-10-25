package pir

import pir.node._
import pir.util._
//import pir.mapper._

abstract class PIRPass(implicit override val compiler:PIR) extends Pass with PIREnv with PIRDebugger
  //with prism.traversal.GraphUtil  
  //with spade.SpadeAlias 
  //with PIRNodeUtil 
  //with RoutingUtil 
  //with RuntimeUtil 
  //with TypeUtil 
  //with MappingUtil
  //with MappingLogger
  {

  override val states = compiler.states
  def pirTop = compiler.pirTop
  override def config:PIRConfig = compiler.config

  //def qdef(n:Any) = n match {
    //case n:PIRNode => n.qdef
    //case n => s"$n"
  //}

  //def qtype(n:Any) = n match {
    //case n:IR => n.qtype
    //case n => s"$n"
  //}
  
}
trait PIRTraversal extends PIRPass {
  def top = compiler.pirTop
}
trait ControlTreeTraversal extends PIRPass {
  def top = compiler.pirTop.topCtrl
}
