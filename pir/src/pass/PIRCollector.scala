package pir.pass

import pir._
import pir.node._
import prism.traversal._

import scala.reflect._
import prism.codegen.Logging

trait PIRCollector extends GraphCollector {

  def collectUp[M<:PIRNode:ClassTag](n:PIRNode, depth:Int= -1, visitFunc:PIRNode => List[PIRNode] = visitUp _, logger:Option[Logging]=None):List[M] =
    super.collect[PIRNode, M](n, depth, visitFunc, logger)

  def collectDown[M<:PIRNode:ClassTag](n:PIRNode, depth:Int= -1, visitFunc:PIRNode => List[PIRNode] = visitDown _, logger:Option[Logging]=None):List[M] = 
    super.collect[PIRNode, M](n, depth, visitFunc, logger)

  def collectIn[M<:PIRNode:ClassTag](n:PIRNode, depth:Int= -1, visitFunc:PIRNode => List[PIRNode] = visitLocalIn _, logger:Option[Logging]=None):List[M] = 
    super.collect[PIRNode, M](n, depth, visitFunc, logger)

  def collectOut[M<:PIRNode:ClassTag](n:PIRNode, depth:Int= -1, visitFunc:PIRNode => List[PIRNode] = visitLocalOut _, logger:Option[Logging]=None):List[M] = 
    super.collect[PIRNode, M](n, depth, visitFunc, logger)

  def globalOf(n:PIRNode) = {
    collectUp[GlobalContainer](n).headOption
  }

  def contextOf(n:PIRNode) = {
    collectUp[ComputeContext](n).headOption
  }

  def ctrlsOf(context:ComputeContext)(implicit design:PIR) = {
    import design.pirmeta._
    collectDown[ComputeNode](context).flatMap { comp => ctrlOf.get(comp) }.toSet[Controller]
  }

  def innerCtrlOf(context:ComputeContext)(implicit design:PIR) = {
    import design.pirmeta._
    ctrlsOf(context).maxBy { _.ancestors.size }
  }

}

