package pir

import prism.graph._

package object node {
  implicit class PIREdgeOp(e:Edge[PIRNode,_,_]) extends EdgeCollector[PIRNode](e)

  implicit class PIRInputOp(e:Input[PIRNode]) {
    def from:Option[Output[PIRNode]] = e.singleConnected
  }

  var _logger:Option[Logging] = None
  implicit class MetadataIRUtil[T<:MetadataIR](n:T) {
    def presetVec(v:Int) = n.getMeta[Int]("presetVec")(v)
    def tp(v:BitType) = n.getMeta[BitType]("tp")(v)
    def inferTp:Option[BitType] = n.getMeta[BitType]("tp").orElseUpdate { 
      dbgblk(_logger,s"compType(${_logger.get.dquote(n)})") {
        n match {
          case n:Output[_] if n.src.localOuts.size == 1 && n.src.as[PIRNode].tp.nonEmpty => n.src.inferTp
          case n:Edge[_,_,_] => n.src.as[PIRNode].compType(n)
          case n:PIRNode => n.compType(n)
          case _ => None
        } 
      }
    }
    def inferVec:Option[Int] = n.getMeta[Int]("vec").orElseUpdate { 
      dbgblk(_logger,s"compVec(${_logger.get.dquote(n)})") {
        n match {
          case n:Edge[_,_,_] if n.getMeta[Int]("presetVec").nonEmpty => n.getMeta[Int]("presetVec").v
          case n:Edge[_,_,_] => n.src.as[PIRNode].compVec(n)
          case n:PIRNode => n.presetVec.v.orElse(n.compVec(n))
          case n:ControlTree => if (n.children.isEmpty) Some(n.par.get) else Some(1)
          case _ => None
        }
      }
    }
  }

  def withLogger[T](logger:Logging)(block: => T) = {
    val saved = _logger
    _logger = Some(logger)
    val res = block
    _logger = saved
    res
  }

}
