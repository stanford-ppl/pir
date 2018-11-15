package pir
package mapper

import prism.collection.immutable._
import prism.util._

abstract class Constrain(implicit val pass:PIRPass) extends Logging with MappingLogger {
  override lazy val logger = pass.logger
  override def quote(n:Any) = pass.quote(n)
  def prune[T](fg:T):EOption[T] //TODO: make this a cake pattern
  def prune(tmap:TopMap):EOption[TopMap] = {
    tmap.flatMapAll(field => prune(field))
  }
}
