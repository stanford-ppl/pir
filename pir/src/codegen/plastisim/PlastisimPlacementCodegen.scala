package pir
package codegen

import pir.node._
import pir.mapper._
import spade.node._
import prism.collection.mutable._

class PlastisimPlacementCodegen(implicit compiler: PIR) extends PlastisimCodegen with RoutingUtil {
  import pirmeta._
  import PIRConfig._

  def isInitPlacement = isDynamic(designS) && routingAlgo != "proute"

  val fileName = if (isInitPlacement) s"init.place" else s"final.place"

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
      val route = mappedTo[Route]((gin, gout)).get
      if (isStaticLink(route)) { // Static 
        emitStaticRoute(gout, gin, route)
      } else { // Dynamic
        emitDynamicRoute(gout, gin, route)
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

  override def finPass = {
    super.finPass
    if (isDynamic(designS)) {
      getCommand.foreach { command =>
        val log = s"$dirName/proute.log"
        val exitCode = shellProcess("proute", command, log) { line =>
          if (line.contains("Could not find node")) {
            err(line, exception=false)
            fail(s"Plastiroute failed. details in $log")
          }
        }
        if (exitCode != 0) {
          fail(s"Plastiroute failed. details in $log")
        }
      }
    }
  }

  def getCommand = {
    zipOption(PLASTIROUTE_HOME, psimOut).fold[Option[String]] {
      warn(s"set PLASTIROUTE_HOME and PLASTISIM_HOME to launch plastiroute automatically!")
      None
    } { case (prouteHome:String, psimOut:String) =>
      val log = s"$dirName/proute.log"
      var command = s"$prouteHome/plastiroute -n $psimOut/node.csv -l $psimOut/link.csv -o $psimOut/final.place -r $numRows -c $numCols -s1"
      command += s" -x${option[Int]("proute-slink")}"
      if (isInitPlacement) {
        command += s" -b $psimOut/init.place -i=0"
      }
      Some(command)
    }
  }

}
