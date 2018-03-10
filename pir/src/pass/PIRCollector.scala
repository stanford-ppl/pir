package pir.pass

import pir._
import pir.node._
import prism._
import prism.traversal._

trait PIRCollector extends GraphCollector {

  def collectUp[M<:PIRNode:ClassTag](n:PIRNode, depth:Int= -1, visitFunc:PIRNode => List[PIRNode] = visitUp _, logger:Option[Logging]=None):List[M] =
    super.collect[PIRNode, M](n, depth, visitFunc, logger)

  def collectDown[M<:PIRNode:ClassTag](n:PIRNode, depth:Int= -1, visitFunc:PIRNode => List[PIRNode] = visitDown _, logger:Option[Logging]=None):List[M] = 
    super.collect[PIRNode, M](n, depth, visitFunc, logger)

  def collectIn[M<:PIRNode:ClassTag](n:PIRNode, depth:Int= -1, visitFunc:PIRNode => List[PIRNode] = visitLocalIn _, logger:Option[Logging]=None):List[M] = 
    super.collect[PIRNode, M](n, depth, visitFunc, logger)

  def collectOut[M<:PIRNode:ClassTag](n:PIRNode, depth:Int= -1, visitFunc:PIRNode => List[PIRNode] = visitLocalOut _, logger:Option[Logging]=None):List[M] = 
    super.collect[PIRNode, M](n, depth, visitFunc, logger)

  def collectPeer[M<:PIRNode:ClassTag](n:PIRNode, depth:Int= -1, visitFunc:PIRNode => List[PIRNode] = visitPeer _, logger:Option[Logging]=None):List[M] =  {
    super.collect[PIRNode, M](n, depth, visitFunc, logger)
  }

  def globalOf(n:PIRNode) = {
    collectUp[GlobalContainer](n).headOption
  }

  def contextOf(n:PIRNode) = {
    collectUp[ComputeContext](n).headOption
  }

  def ctrlsOf(container:Container)(implicit compiler:PIR) = {
    import compiler.pirmeta._
    collectDown[ComputeNode](container).flatMap { comp => ctrlOf.get(comp) }.toSet[Controller]
  }

  def innerCtrlOf(container:Container)(implicit compiler:PIR) = {
    import compiler.pirmeta._
    ctrlsOf(container).maxBy { _.ancestors.size }
  }

}

