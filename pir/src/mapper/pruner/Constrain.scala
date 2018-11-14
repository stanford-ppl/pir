package pir
package mapper

import prism.collection.immutable._
import prism.util.Memorization

abstract class Constrain(implicit val pass:PIRPass) extends Logging with MappingLogger {
  override lazy val logger = pass.logger
  override def toString = this.getClass.getSimpleName.replace("$","")
  override def quote(n:Any) = pass.quote(n)
  def prune(fg:Any):EOption[Any]
  def prune(tmap:TopMap):EOption[TopMap] = {
    tmap.flatMapAll(field => prune(field))
  }
}
