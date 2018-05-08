
package pir.pass

import pir.node._
import spade.node.{Bit, Word, Vector}

trait TypeUtil extends ConstantPropogator { self:PIRPass =>
  import pirmeta._

  def pinTypeOf(n:PIRNode, logger:Option[Logging]=None):ClassTag[_<:PinType] = prism.util.dbgblk(logger, s"pinTypeOf($n)") {
    implicit val design = n.design.asInstanceOf[PIRDesign]
    n match {
      case n:Memory if isControlMem(n) => classTag[Bit]
      case n:Memory => 
        val maxWriter = writersOf(n).maxBy(writer => getParOf(writer))
        pinTypeOf(maxWriter)

      case Def(n,LocalLoad(mems,_)) if isControlMem(mems.head) => classTag[Bit]
      case Def(n,LocalStore(_,_,data)) => pinTypeOf(data, logger)

      case n:GlobalInput => pinTypeOf(goutOf(n).get, logger)
      case Def(n, GlobalOutput(data, valid)) => pinTypeOf(data, logger)

      case n:Primitive if getParOf(n) > 1 => classTag[Vector]
      case n:ControlNode => classTag[Bit]
      case n:Primitive if getParOf(n) == 1 => classTag[Word]

      case n => throw PIRException(s"Don't know pinTypeOf($n)")
    }
  }

  implicit def pnodeToBct(x:PIRNode):ClassTag[_<:PinType] = pinTypeOf(x, None)

  def isPMU(n:GlobalContainer):Boolean = {
    cuType(n) == Some("pmu")
  }

  def isSCU(n:GlobalContainer):Boolean = {
    cuType(n) == Some("scu")
  }

  def isOCU(n:GlobalContainer):Boolean = {
    cuType(n) == Some("ocu")
  }

  def isPCU(n:GlobalContainer):Boolean = {
    cuType(n) == Some("pcu")
  }

  def isDAG(n:GlobalContainer):Boolean = {
    cuType(n) == Some("dag")
  }

  def isAFG(n:GlobalContainer):Boolean = {
    cuType(n) == Some("afg")
  }

  def isDFG(n:GlobalContainer):Boolean = {
    cuType(n) == Some("dfg")
  }

  def isFringe(n:GlobalContainer):Boolean = {
    isAFG(n) || isDAG(n)
  }

  private def writesOnlyToDFG(n:GlobalContainer) = {
    val targets = n.visitLocalOut[PIRNode](n)
    targets.nonEmpty && targets.forall(_.isInstanceOf[DramFringe])
  }

  private def containsRemoteMem(n:GlobalContainer) = {
    n.collectDown[Memory]().filter(isRemoteMem).nonEmpty
  }

  private def containsStageOp(n:GlobalContainer) = {
    n.collectDown[StageDef]().size > 0
  }

  def nonVectorized(n:Any) = getParOf(n) == 1

  def cuType(n:PIRNode):Option[String] = {
    n match {
      case n:ArgFringe => Some("afg")
      case n:DramFringe => Some("dfg")
      case n:GlobalContainer if containsRemoteMem(n) => Some("pmu")
      case n:GlobalContainer if writesOnlyToDFG(n) => Some("dag")
      case n:GlobalContainer if !containsStageOp(n) => Some("ocu")
      case n:GlobalContainer if nonVectorized(n) => Some("scu")
      case n:GlobalContainer => Some("pcu")
      case n => None
    }
  }
}
