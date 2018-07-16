package pir
package node

trait GlobalIO extends Def
trait GlobalInput extends GlobalIO { val globalOutput:GlobalOutput }
object GlobalInput {
  def unapply(n:Any):Option[GlobalOutput] = n match {
    case ValidGlobalInput(gout) => Some(gout)
    case ReadyValidGlobalInput(gout, ready) => Some(gout)
    case _ => None
  }
}
case class ValidGlobalInput(globalOutput:GlobalOutput)(implicit design:PIRDesign) extends GlobalInput
case class ReadyValidGlobalInput(globalOutput:GlobalOutput, ready:ControlNode)(implicit design:PIRDesign) extends GlobalInput
case class DataValid(globalInput:GlobalInput)(implicit design:PIRDesign) extends ControlNode // If DataValid is enqEn of EnabledStoreMem, the valid goes along with data

case class GlobalOutput(data:Def, valid:ControlNode)(implicit design:PIRDesign) extends GlobalIO
case class DataReady(globalOutput:GlobalOutput)(implicit design:PIRDesign) extends ControlNode // If DataValid is enqEn of EnabledStoreMem, the valid goes along with data

trait GlobalIOUtil {
  def goutOf(gin:GlobalInput, logger:Option[Logging]=None) = {
    gin.collect[GlobalOutput](visitFunc=gin.visitGlobalIn, depth=2, logger=logger).headOption
  }

  def ginsOf(gout:GlobalOutput, logger:Option[Logging]=None) = {
    gout.collect[GlobalInput](visitFunc=gout.visitGlobalOut, depth=2, logger=logger).toList
  }

  def validOf(n:GlobalOutput) = n match {
    case Def(n, GlobalOutput(data, valid)) => valid
  }

  def dataOf(n:GlobalOutput) = n match {
    case Def(n, GlobalOutput(data, valid)) => data
  }

  def connectedOf(gio:GlobalIO, logger:Option[Logging]=None) = gio match {
    case gio:GlobalInput => goutOf(gio, logger).toList
    case gio:GlobalOutput => ginsOf(gio, logger)
  }

  def isGlobalInput(gin:GlobalIO) = gin match {
    case gin:GlobalInput => true
    case gout:GlobalOutput => false
  }

  def isGlobalOutput(gin:GlobalIO) = gin match {
    case gin:GlobalInput => false
    case gout:GlobalOutput => true
  }

  def degree(cuP:GlobalContainer) = cuP.collectDown[GlobalIO]().size

}

