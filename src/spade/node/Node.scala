package spade.node

import spade._
import spade.util._

import scala.collection.mutable.ListBuffer

/* Spade Node */
class Node(implicit val spade:Spade) { 
  val spademeta: SpadeMetadata = spade
  import spademeta._
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

  def isConst = this.isInstanceOf[Const[_]]
}
