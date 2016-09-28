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
}
object Node extends Metadata {
  def quote(pne:Any)(implicit spade:Spade) = {
    pne match {
      case pne:NetworkElement => coordOf.get(pne).fold(s"$pne") { case (x,y) => s"$pne[$x,$y]"}
      case _ => pne.toString
    }
  }
}
