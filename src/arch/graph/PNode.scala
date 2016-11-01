package pir.plasticine.graph

import pir.graph._
import pir.plasticine.main._

import scala.language.reflectiveCalls

/* Spade Node */
class Node(implicit spade:Spade) extends Metadata { 
  val id : Int = spade.nextId
  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }
  val typeStr = this.getClass().getSimpleName()
  override def toString = s"${typeStr}${id}" 
  def index(i:Int)(implicit spade:Spade):this.type = { indexOf(this) = i; this }
  def index(implicit spade:Spade):Int = { indexOf(this) }
  def coord(c:(Int, Int))(implicit spade:Spade):this.type = { coordOf(this) = c; this} // Coordinate
  def coord(implicit spade:Spade):(Int, Int) = { coordOf(this) }
}
