package prism
package graph

import scala.collection.mutable

trait MetadataIR extends Serializable { self =>

  val metadata = mutable.ListBuffer[Metadata[_]]()
  
  def mirrorMetas(to:MetadataIR) = {
    metadata.zip(to.metadata).foreach { case (frommeta, tometa) =>
      frommeta.mirror(self, to).foreach { mv =>
        tometa.update(mv)
      }
    }
  }

  case class Metadata[T](name:String, default:Option[T] = None) extends Serializable {
    var value:Option[T] = default
    metadata += this

    override def toString = s"Metadata($name)"
    def check(v:T) = if (value.nonEmpty && value != default) throw PIRException(s"$this already has value $value, but reupdate to $v")
    def :=(v:T) = { check(v); value = Some(v) }
    def update(v:Any) = :=(v.asInstanceOf[T])
    def apply(value:T):self.type = { :=(value); self }
    def v:Option[T] = value
    def get:T = value.get
    def reset = value = default
    def mirror(self:MetadataIR, to:MetadataIR):Option[T] = value
  }
  object Metadata {
    def apply[T](name:String, default:T):Metadata[T] = Metadata[T](name, Some(default))
  }
}

