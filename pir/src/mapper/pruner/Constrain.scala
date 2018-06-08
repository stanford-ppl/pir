package pir
package mapper

import prism.collection.immutable._
import prism.util.Memorization

abstract class Constrain(implicit val pass:PIRPass) extends Logging with Memorization with MappingLogger {
  type K
  type V
  type FG <: FactorGraphLike[K,V,FG]
  implicit def fgct:ClassTag[FG]
  override lazy val logger = pass.logger
  override def toString = this.getClass.getSimpleName.replace("$","")
  override def quote(n:Any) = pass.quote(n)
  memorizing = true
  def prune(fg:FG):EOption[FG]
  def prune(pmap:PIRMap):EOption[PIRMap] = {
    pmap.flatMap[FG](field => prune(field))
  }
}
