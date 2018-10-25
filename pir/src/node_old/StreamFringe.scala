package pir
package node

abstract class StreamFringe(implicit design:PIRDesign) extends GlobalContainer

case class FringeStreamIn(
  streamIn:StreamIn
)(implicit design:PIRDesign) extends StreamFringe

case class FringeStreamOut(
  streamOut:StreamOut
)(implicit design:PIRDesign) extends StreamFringe

case class StreamInDef()(implicit design:PIRDesign) extends Def
case class ProcessStreamOut(
  stream:Def
)(implicit design:PIRDesign) extends Def

trait StreamFringeUtil {
  def isStreamFringe(n:GlobalContainer) = n match {
    case n:StreamFringe => true
    case n => false
  }
}
