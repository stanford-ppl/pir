package prism
package graph

import scala.collection.mutable

trait MetadataIR extends Serializable { self =>

  val metadata = mutable.ListBuffer[Metadata[_]]()
  
  def mirrorMetas(from:MetadataIR):self.type = {
    metadata.zip(from.metadata).foreach { case (tometa, frommeta) =>
      frommeta.mirror(from, self).foreach { mv =>
        tometa.update(mv)
      }
    }
    self
  }

  case class Metadata[T](name:String, default:Option[T] = None) extends Serializable {
    var value:Option[T] = default
    metadata += this

    override def toString = s"$self.$name"
    def check(v:T) = if (value.nonEmpty && Some(v) != value && value != default) throw PIRException(s"$this already has value $value, but reupdate to $v")
    def :=(v:T) = { check(v); value = Some(v) }
    def update(v:Any) = :=(v.asInstanceOf[T])
    def apply(value:T):self.type = { :=(value); self }
    def v:Option[T] = value
    def get:T = value.getOrElse(throw PIRException(s"$self.$name is empty"))
    def nonEmpty = v.nonEmpty
    def isEmpty = v.isEmpty
    def map[A](func:T => A):Option[A] = value.map(func)
    def getOrElseUpdate(v: => T):T = {
      if (value.isEmpty) :=(v)
      get
    }
    def reset = value = default
    def mirror(from:MetadataIR, to:MetadataIR):Option[T] = value
  }
  object Metadata {
    def apply[T](name:String, default:T):Metadata[T] = Metadata[T](name, Some(default))
  }
}

