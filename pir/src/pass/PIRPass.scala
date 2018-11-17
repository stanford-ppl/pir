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
  with PIRNodeUtil 
  with RuntimeAnalyzer
  //with prism.traversal.GraphUtil  
  //with spade.SpadeAlias 
  //with RoutingUtil 
  //with TypeUtil 
  //with MappingUtil
  //with MappingLogger
  {

  override def states = compiler.states
  override def config:PIRConfig = compiler.config

}
trait PIRTraversal extends PIRPass {
  def top = compiler.pirTop
}
trait ControlTreeTraversal extends PIRPass {
  def top = compiler.pirTop.topCtrl
}
