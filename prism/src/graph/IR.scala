package prism
package graph

import scala.collection.mutable

trait IR extends Serializable with MetadataIR { 

  /*  ------- State -------- */
  val positiveHashCode = super.hashCode & 0x7FFFFFFF
  val id:Int = positiveHashCode

  /*  ------- Metadata -------- */
  val name = new Metadata[String]("name")

  override def equals(that: Any) = that match {
    case n: IR => id == n.id
    case _ => super.equals(that)
  }

  def className = this.getClass.getSimpleName

  override def toString = s"${className}$id"

  def newInstance[T](args:Seq[Any]):T = this.getClass.newInstanceAs[T](args)

  def as[T] = asInstanceOf[T]

}

