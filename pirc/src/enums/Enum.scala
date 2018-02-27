package pirc.enums

import scala.collection.mutable.ListBuffer

@SerialVersionUID(124L)
trait Enum extends Serializable

sealed trait MCType extends Enum {
  def isDense:Boolean = {
    this match {
      case TileLoad | TileStore => true
      case Gather | Scatter => false
    }
  }
  def isSparse:Boolean = {
    this match {
      case Gather | Scatter => true
      case TileLoad | TileStore => false
    }
  }
  def isLoad:Boolean = {
    this match {
      case TileLoad | Gather => true
      case TileStore | Scatter => false
    }
  }
  def isStore:Boolean = {
    this match {
      case TileStore | Scatter => true 
      case TileLoad | Gather => false
    }
  }
}
case object TileLoad extends MCType 
case object TileStore extends MCType 
case object Scatter extends MCType 
case object Gather extends MCType 

sealed trait ControlStyle extends Enum
case object InnerPipe extends ControlStyle
case object SeqPipe extends ControlStyle
case object MetaPipe extends ControlStyle
case object StreamPipe extends ControlStyle
case object ForkSwitch extends ControlStyle

sealed trait ControlLevel extends Enum
case object InnerControl extends ControlLevel
case object OuterControl extends ControlLevel


sealed trait MemMode extends Enum
case object SramMode extends MemMode
case object FifoMode extends MemMode

sealed trait Banking extends Enum
case class Strided(stride:Int, banks:Int) extends Banking
object Strided {
  def apply(stride:Int):Strided = {
    Strided(stride, 16) //TODO: rewrite app and fix this
  }
}
case class Diagonal(stride1:Int, stride2:Int) extends Banking
case class Duplicated() extends Banking
case class NoBanking() extends Banking

trait RegColor extends Enum
case object VecInReg extends RegColor
case object VecOutReg extends RegColor
case object ScalarInReg extends RegColor
case object ScalarOutReg extends RegColor
case object ReadAddrReg extends RegColor
case object WriteAddrReg extends RegColor
case object CounterReg extends RegColor
case object ReduceReg extends RegColor
case object AccumReg extends RegColor
