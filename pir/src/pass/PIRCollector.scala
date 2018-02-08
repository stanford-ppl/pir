package pir.pass

import pir._
import pir.node._
import prism.traversal._

import scala.reflect._
import prism.codegen.Logging

trait PIRCollector extends GraphCollector {
  def collectUp[M<:Node:ClassTag](n:Node, depth:Int=10, logger:Option[Logging]=None):List[M] =
    super.collectUp[Node, M](n, depth, logger)

  def collectDown[M<:Node:ClassTag](n:Node, depth:Int=10, logger:Option[Logging]=None):List[M] = 
    super.collectDown[Node, M](n, depth, logger)

  def collectIn[M<:Node:ClassTag](n:Node, depth:Int=10, logger:Option[Logging]=None):List[M] = 
    super.collectIn[Node, M](n, depth, logger)

  def collectOut[M<:Node:ClassTag](n:Node, depth:Int=10, logger:Option[Logging]=None):List[M] = 
    super.collectOut[Node, M](n, depth, logger)
}

