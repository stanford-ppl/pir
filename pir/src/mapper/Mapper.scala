package pir
package mapper

import pir.node._
import pir.pass._
import prism.mapper._

trait Mapper extends PIRPass with BackTrackingMatch with MappingLogger {

  def bind[T](x:T):EOption[T] = x match {
    case x:TopMap => x.flatMapAll(field => bind[Any](field)).asInstanceOf[EOption[T]]
    case x => Right(x)
  }

  override def runPass = {
    topMap = topMap.flatMap { tmap => bind[TopMap](tmap) }
    topMap.left.foreach { fail }
    topMap.right.foreach { logging }
  }

}
