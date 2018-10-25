package pir
package node

import prism.enums._

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


