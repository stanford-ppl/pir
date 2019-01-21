package pir
package mapper

import prism.collection.immutable._
import prism.util._

trait ConstrainPruner extends PIRTraversal with MappingLogger with Memorization {
  def prune[T](x:T):EOption[T] = x match {
    case x:TopMap => x.flatMapAll(field => prune[Any](field)).asInstanceOf[EOption[T]]
    case x => Right(x)
  }

  override def runPass = {
    resetAllCaches
    topMap = topMap.flatMap { tmap => prune[TopMap](tmap) }
    topMap.left.foreach { fail }
    topMap.right.foreach { logging }
  }
}

