package pir.node

trait PIRCollector extends prism.traversal.GraphCollector[PIRNode] { self:PIRNode =>

  def collectInTillMem[M<:PIRNode:ClassTag](depth:Int = -1, logger:Option[Logging]=None):List[M] = {
    def visitFunc(n:PIRNode):List[PIRNode] = n match {
      case n:Memory if n != this => Nil
      case n => visitGlobalIn(n)
    }
    collect[M](visitFunc _, depth, logger)
  }

  def collectOutTillMem[M<:PIRNode:ClassTag](depth:Int = -1, logger:Option[Logging]=None):List[M] = {
    def visitFunc(n:PIRNode):List[PIRNode] = n match {
      case n:Memory if n != this => Nil
      case n => visitGlobalOut(n)
    }
    collect[M](visitFunc _, depth, logger)
  }
}
