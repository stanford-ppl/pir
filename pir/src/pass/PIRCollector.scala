package pir.pass

import pir._
import pir.node._
import prism.traversal._

import scala.reflect._
import prism.codegen.Logging

trait PIRCollector extends GraphCollector {
  def collectUp[M<:PIRNode:ClassTag](n:PIRNode, depth:Int=10, logger:Option[Logging]=None):List[M] =
    super.collectUp[PIRNode, M](n, depth, logger)

  def collectDown[M<:PIRNode:ClassTag](n:PIRNode, depth:Int=10, logger:Option[Logging]=None):List[M] = 
    super.collectDown[PIRNode, M](n, depth, logger)

  def collectIn[M<:PIRNode:ClassTag](n:PIRNode, depth:Int=10, logger:Option[Logging]=None):List[M] = 
    super.collectIn[PIRNode, M](n, depth, logger)

  def collectOut[M<:PIRNode:ClassTag](n:PIRNode, depth:Int=10, logger:Option[Logging]=None):List[M] = 
    super.collectOut[PIRNode, M](n, depth, logger)

  def accumIn(n:PIRNode, logger:Option[Logging]=None):List[PIRNode] = {
    super.accumIn(n, logger)
  }
  def accumOut(n:PIRNode, logger:Option[Logging]=None):List[PIRNode] = {
    super.accumOut(n, logger)
  }

  def canReachIn(n:PIRNode, target:PIRNode, logger:Option[Logging]):Boolean = {
    super.canReachIn(n, target, logger)
  }

  def canReachOut(n:PIRNode, target:PIRNode, logger:Option[Logging]):Boolean = {
    super.canReachOut(n, target, logger)
  }

  def areWeaklyConnected(n:PIRNode, target:PIRNode, logger:Option[Logging]=None) = {
    super.areWeaklyConnected(n, target, logger)
  }

  def areStronglyConnected(n:PIRNode, target:PIRNode, logger:Option[Logging]=None) = {
    super.areStronglyConnected(n, target, logger)
  }
}

