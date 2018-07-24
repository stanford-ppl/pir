package pir
package codegen

import pir.node._
import pir.mapper._
import spade.node._
import spade.param._
import prism.collection.mutable._

class PlastisimPlacementCodegen(implicit compiler: PIR) extends PlastisimCodegen with RoutingUtil {
  import pirmeta._
  import PIRConfig._

  val fileName = s"pir.place"

  type Route = List[(MKMap.K,MKMap.K)]

  override def emitNode(n:N) = 
    n match {
    case n:GlobalContainer if isDynamic(designS) =>
      emitln(s"N ${n.id} ${addrOf(n).get}")
      super.visitNode(n)
    case n:GlobalContainer =>
      emitln(s"N ${n.id} 0")
      super.visitNode(n)
    case n:GlobalInput if isDynamic(designS) | isStatic(designS) =>
      val gin = n
      val gout = goutOf(gin).get
      val routes = mappedTo[Route]((gin, gout)).get
      val routables = routes.map { case (out, in) => routableOf(out).get }
      val isStaticLink = !routables.exists { _.isInstanceOf[Router] }
      if (isStaticLink) { // Dynamic
        emitDynamicRoute(gout, gin, routes)
      } else { // Static
        emitStaticRoute(gout, gin, routes)
      }
    case n:GlobalInput =>
      val gin = n
      val gout = goutOf(gin).get
      emitStaticRoute(gout, gin, List((null,null)))
    case n => super.visitNode(n)
  }

  def emitDynamicRoute(gout:GlobalOutput, gin:GlobalInput, routes:Route) = {
    val routables = routes.map { case (out, in) => routableOf(out).get }
    val src::routers = routables
    assert(routers.forall{_.isInstanceOf[Router]}, s"route contains router but not just routers: $routables")
    routers.sliding(2, 1).foreach { case List(from, to) =>
      emitln(s"H ${gout.id} ${addrOf(from).get} ${getDirection(from, to)} 0") // assign all vc to 0
    }
    emitln(s"H ${gout.id} ${addrOf(routers.last).get} X 0") // assign all vc to 0
  }

  def emitStaticRoute(gout:GlobalOutput, gin:GlobalInput, routes:Route) = {
    val src = globalOf(gout).get.id
    val dst = globalOf(gin).get.id
    emitln(s"S ${gout.id} $src $dst ${routes.size}")
  }

}
