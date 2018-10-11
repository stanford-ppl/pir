package prism
package node

import scala.collection.mutable

@SerialVersionUID(123L)
abstract class IR(implicit val design:Design) extends Serializable { 
  val id = design.nextId

  override def equals(that: Any) = that match {
    case n: IR => id == n.id
    case _ => super.equals(that)
  }

  def className = this.getClass.getSimpleName

  override def toString = s"${className}$id"
}

