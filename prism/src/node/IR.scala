package prism.node

import prism._
import prism.util._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

@SerialVersionUID(123L)
trait IR extends Serializable { 
  val id:Int

  override def equals(that: Any) = that match {
    case n: IR => id == n.id
    case _ => super.equals(that)
  }

  def className = this.getClass.getSimpleName

  override def toString = s"${className}$id"
}

