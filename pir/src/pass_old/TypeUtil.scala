package pir
package pass

import pir.node._

trait TypeUtil extends ConstantPropogator with RuntimeUtil with PIRNodeUtil { self:Logging =>

  val pirmeta:PIRMetadata
  import pirmeta._

  def pinTypeOf(n:PIRNode):ClassTag[_<:PinType] = dbgblk(s"pinTypeOf($n)") {
    implicit val design = n.design.asInstanceOf[PIRDesign]
    n match {
      case n:ControlNode => classTag[Bit]
      case n:ResetMem => classTag[Bit]

      case n:Memory if isControlMem(n) => classTag[Bit]
      case n:Memory if isReg(n) => classTag[Word]
      case n:Memory => 
        val maxAccess = accessesOf(n).maxBy(access => getParOf(access))
        pinTypeOf(maxAccess)
      case n:LocalAccess if isControlMem(memsOf(n).head) => classTag[Bit]
      case n:LocalAccess if isReg(memsOf(n).head) => classTag[Word]
      case n:LocalAccess if getParOf(n) == 1 => classTag[Word]
      case n:LocalAccess if getParOf(n) > 1 => classTag[Vector]

      case n:GlobalInput => pinTypeOf(goutOf(n).get)
      case Def(n, GlobalOutput(data, valid)) => pinTypeOf(data)

      case n:ProcessDramDenseStore => classTag[Bit]
      case n:ProcessDramSparseStore => classTag[Bit]
      case n:Primitive if getParOf(n) == 1 => classTag[Word]
      case n:Primitive if getParOf(n) > 1 => classTag[Vector]

      case n => throw PIRException(s"Don't know pinTypeOf($n)")
    }
  }

  implicit def pnodeToBct(x:PIRNode):ClassTag[_<:PinType] = pinTypeOf(x)

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

  def isSFG(n:GlobalContainer):Boolean = {
    cuType(n) == Some("sfg")
  }

  def isFringe(n:GlobalContainer):Boolean = {
    isAFG(n) || isDAG(n) || isSFG(n)
  }

  private def writersToCommandStreams(n:GlobalContainer) = {
    val targets = n.depeds
    targets.nonEmpty && targets.forall { in => 
      globalOf(in).get.isInstanceOf[DramFringe] && {
        val streamOuts = in.collectOutTillMem[StreamOut]()
        streamOuts.nonEmpty && streamOuts.forall { so => so.field == "size" || so.field == "offset" }
      }
    }
  }

  private def containsRemoteMem(n:GlobalContainer) = {
    n.collectDown[Memory]().filter(isRemoteMem).nonEmpty
  }

  private def containsStageOp(n:GlobalContainer) = {
    n.collectDown[StageDef]().size > 0
  }

  def nonVectorized(n:PIRNode) = getParOf(n) == 1

  def cuType(n:PIRNode):Option[String] = {
    n match {
      case n:ArgFringe => Some("afg")
      case n:DramFringe => Some("dfg")
      case n:StreamFringe => Some("sfg")
      case n:GlobalContainer if containsRemoteMem(n) => Some("pmu")
      case n:GlobalContainer if writersToCommandStreams(n) => Some("dag")
      case n:GlobalContainer if !containsStageOp(n) => Some("ocu")
      case n:GlobalContainer if nonVectorized(n) => Some("scu")
      case n:GlobalContainer => Some("pcu")
      case n => None
    }
  }
}
