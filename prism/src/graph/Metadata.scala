package prism
package graph

import scala.collection.mutable

trait Metadata extends Serializable { self =>

  val metadata = mutable.Map[ClassTag[_],Any]()
  
  def mirrorMetas(to:Metadata) = {
    metadata.foreach { case (key, value) =>  
      to.metadata.update(key, key.runtimeClass.newInstance.asInstanceOf[Data[_,_]].mirror(value))
    }
  }

  class Data[D:ClassTag,T:ClassTag] extends Serializable {
    val name = getClass.getSimpleName
    val key = classTag[D]
    def check(value:T) = {
      if (metadata.contains(key)) throw new Exception(s"Reupdate $key to $value for $self")
    }
    def :=(value:T) = { check(value); metadata.update(key, value) }
    def apply(value:T):self.type = { :=(value); self }
    def value:Option[T] = metadata.get(key).asInstanceOf[Option[T]]
    def get:T = value.get
    def reset = metadata.remove(key)
    def mirror(value:Any) = value.asInstanceOf[T]
  }
}

