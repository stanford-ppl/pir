package prism
package graph

import scala.collection.mutable

trait IR extends Serializable with Metadata { 

  val positiveHashCode = super.hashCode & 0x7FFFFFFF
  val id:Int = positiveHashCode

  override def equals(that: Any) = that match {
    case n: IR => id == n.id
    case _ => super.equals(that)
  }

  def className = this.getClass.getSimpleName

  override def toString = s"${className}$id"

  def newInstance[T](args:Seq[Any]):T = {
    val arguments = args.toList
    val constructor = this.getClass.getConstructors()(0) 
    try {
      constructor.newInstance(arguments.map(_.asInstanceOf[Object]):_*).asInstanceOf[T]
    } catch {
      case e:java.lang.IllegalArgumentException =>
        err(s"Error during newInstance of node $this", exception=false)
        err(s"Expected type: ${constructor.getParameterTypes().mkString(",")}", exception=false)
        err(s"Got type: ${arguments.map(_.getClass).mkString(",")}", exception=false)
        throw e
      case e:java.lang.reflect.InvocationTargetException =>
        err(s"InvocationTargetException during newInstance of node $this", exception=false)
        err(s"arguments=$arguments", exception=false)
        err(s"Cause:", exception=false)
        err(s"${e.getCause}", exception=false)
        throw e
      case e:Throwable => throw e
    }
  }
}
