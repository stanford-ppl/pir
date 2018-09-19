package pir
package codegen

import pir.node._
import pir.mapper._
import spade.node._
import prism.collection.mutable._
import scala.collection.mutable

class PlastisimPlacementCodegen(implicit compiler: PIR) extends PlastisimCodegen with RoutingUtil {
  import pirmeta._
  import PIRConfig._

  type Hop = (MKMap.K,MKMap.K)
  type Route = List[Hop]
  def isInitPlacement = isDynamic(designS) && routingAlgo != "proute"

  val fileName = if (isInitPlacement) s"init.place" else s"final.place"

  val hops = mutable.Map[String, mutable.Map[MKMap.V, mutable.Map[Hop, Long]]]()

  override def initPass = {
    super.initPass
    hops.clear
    hops += "Dyn" -> mutable.Map.empty
    hops += "Stat" -> mutable.Map.empty
  }

  def countHop(net:String, gout:MKMap.V, hop:(MKMap.K,MKMap.K)):Unit = {
    if (!isDynamic(designS) && !isStatic(designS)) return
    val count = hops(net).getOrElseUpdate(gout, mutable.Map[Hop,Long]())
    count.getOrElseUpdate(hop, {
      val (from,to) = hop
      assert(routableOf(from).get != routableOf(to).get)
      val tp = pinTypeOf(gout) match {
        case ct if isBit(ct) => "Ctrl"
        case ct if isWord(ct) => "Scal"
        case ct if isVector(ct) => "Vec"
      }
      // Only counting global links
      if (List(routableOf(from).get, routableOf(to).get).exists { r => r.isInstanceOf[Router] || r.isInstanceOf[SwitchBox]}) {
        constCountsOf(gout)
      } else {
        0
      }
    })
  }

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
    if (routers.size >= 2) { // If only 1 router, no need to emit between router direction
      routers.sliding(2, 1).foreach { case List(from, to) =>
        emitln(s"H ${gout.id} ${addrOf(from).get} ${getDirection(from, to)} 0") // assign all vc to 0
      }
    }
    emitln(s"H ${gout.id} ${addrOf(routers.last).get} X 0") // assign all vc to 0
    routes.foreach { hop => countHop("Dyn", gout, hop) }
  }

  def emitStaticRoute(gout:GlobalOutput, gin:GlobalInput, routes:Route) = {
    val src = globalOf(gout).get.id
    val dst = globalOf(gin).get.id
    val hop = routes.size
    emitln(s"S ${gout.id} $src $dst $hop")
    routes.foreach { hop => countHop("Stat", gout, hop) }
  }

  val summaryPath = psimOut.map { psimOut => s"$psimOut/summary.csv" }
  val log = s"$dirName/proute.log"

  override def finPass = {
    super.finPass
    if (isDynamic(designS)) {
      getCommand.foreach { command =>
        val statKeys = List("DynHopsVec", "DynHopsScal", "StatHopsVec", "StatHopsScal")
        if (option[Boolean]("rerun-proute")) {
          dbg(s"Run command:")
          dbg(command)
          deleteFile(summaryPath.get)
          deleteFile(log)
          val exitCode = shellProcess("proute", command, log) { line =>
            if (line.contains("Could not find node")) {
              warn(line)
              //err(line, exception=false)
              //fail(s"Plastiroute failed. details in $log")
            }
            if (line.contains("Used") && line.contains("VCs.") && verbose) {
              info(Console.GREEN, s"proute", line)
            }
          }
          if (exitCode != 0) {
            fail(s"Plastiroute failed. details in $log")
          } else {
            getCSVRows(summaryPath.get).foreach { row =>
              statKeys.foreach { key => totalHopCountOf(key) = row(key).toLong }
            }
            printHops("proute")
          }
        } else {
          info(s"Using old placement")
          getCSVRows(summaryPath.get).foreach { row =>
            statKeys.foreach { key => totalHopCountOf(key) = row(key).toLong }
          }
          printHops("proute")
        }
      }
    } else {
      hops.foreach { case (net, map) =>
        map.map { case (gout, map) =>
          (gout, map.values.sum)
        }.groupBy { case (gout,map) =>
          pinTypeOf(gout) match {
            case ct if isBit(ct) => "Ctrl"
            case ct if isWord(ct) => "Scal"
            case ct if isVector(ct) => "Vec"
          }
        }.foreach { case (tp, groups) =>
          totalHopCountOf(s"${net}Hops$tp") = groups.values.sum
        }
      }
      printHops("pir")
      hops.foreach { case (net, map) =>
        map.foreach { case (gout, map) =>
          val count = map.values.sum
          dbg(s"$gout ${pinTypeOf(gout)} hopCount=$count")
        }
      }
    }
  }

  def printHops(header:String) = {
    val msg = totalHopCountOf.map.toSeq.map { case (k,v) => s"$k: $v" }.mkString(",")
    if (verbose) info(Console.GREEN, header, "\n" + msg)
    dbg(msg)
  }

  def getCommand = {
    zipOption(PLASTIROUTE_HOME, psimOut).fold[Option[String]] {
      warn(s"set PLASTIROUTE_HOME and PLASTISIM_HOME to launch plastiroute automatically!")
      None
    } { case (prouteHome:String, psimOut:String) =>
      var command = s"$prouteHome/plastiroute -n $psimOut/node.csv -l $psimOut/link.csv -o $psimOut/final.place"
      if (isInitPlacement) command += s" -b $psimOut/init.place"
      command += s" -r $numRows -c $numCols"
      command += s" -g $psimOut/proute.dot"
      command += s" -v ${summaryPath.get}"
      command += s" -x${SpadeConfig.option[Int]("vlink")}"
      command += s" -e ${SpadeConfig.option[Int]("slink")}"
      command += s" -a ${option[String]("proute-algo")} "
      command += s" -q${option[String]("proute-q")} "
      command += s" -s${option[String]("proute-seed")} "
      command += s" ${option[String]("proute-opts")}"
      Some(command)
    }
  }

}
