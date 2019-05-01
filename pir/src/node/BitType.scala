package pir
package node

sealed trait BitType {
  def isFraction = this match {
    case Fix(s, i,f) if f != 0 => true
    case Flt(m,e) => true
    case _ => false
  }
  def fixTpMax = this match {
    case Fix(true,i,f) => (Math.pow(2, (i+f-1)) - 1) / Math.pow(2,f)
    case Fix(false,i,f) => (Math.pow(2, (i+f)) - 1) / Math.pow(2,f)
    case tp => throw PIRException(s"Unexpected tp for fixTpMax $tp")
  }
  def fixTpMin = this match {
    case Fix(false,i,f) => 0
    case Fix(true,i,f) => - Math.pow(2, (i+f-1))
    case tp => throw PIRException(s"Unexpected tp for fixTpMin $tp")
  }
  def toUnsigned = this match {
    case Fix(s,i,f) => Fix(!s,i,f)
    case _ => throw PIRException(s"Don't know how to convert to unsigned type")
  }
  def nbits:Option[Int]
  def bytePerWord = nbits.map { _ /! 8 }
}
case class Fix(s:Boolean, i:Int, f:Int) extends BitType {
  val nbits = Some(i + f)
}
case class Flt(m:Int, e:Int) extends BitType {
  val nbits = Some(m + e)
}
case object Bool extends BitType {
  val nbits = Some(1)
}
case object Text extends BitType {
  val nbits = None
}
case object Void extends BitType {
  val nbits = None
}
