package pir.mapper
import pir.graph._
import pir.{Design, Config}
import pir.util.typealias._
import pir.codegen.Printer
import pir.exceptions._
import pir.codegen.{CUCtrlDotPrinter, CUVectorDotPrinter}
import pir.pass.{PIRMapping}
import pir.plasticine.main._
import pir.plasticine.util._
import pir.util.misc._
import pir.util.topoSort
import pir.util.enums._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

class CUMapper(implicit val design:Design) extends Mapper {
  import pirmeta._
  type N = CL
  type R = PCL

  val typeStr = "CUMapper"

  override implicit val mapper:CUMapper = this

  override val exceptLimit = 200

  val routers = ListBuffer[Router]()
  routers += new VectorRouter()
  routers += new ScalarRouter()
  routers += new ControlRouter()

  def finPass(m:M):M = m
  override def debug = Config.debugCUMapper

  def resMap = design.pirMapping.prescreen.resMap

  def place(cl:N, prt:R, m:M):M = {
    val mp = log((s"Try $cl -> ${quote(prt)}", true)) {
      Try {
        routers.foldLeft(m.setCL(cl, prt)) { case (pm, router) =>
          router.route(cl, pm)
        }
      } match {
        case Success(mp) => mp
        case Failure(PassThroughException(e@ReplaceController(blacklist, _), _)) if (blacklist.contains((cl, prt))) =>
          throw e
        case Failure(e) => throw e
      }
    }
    //breakPoint(mp, s"debugging placer")
    mp
  }

  //TODO: change to resFuncWithExcept
  def resFunc(cl:N, m:M, triedRes:List[R]):List[R] = {
    implicit val spade:Spade = design.arch
    log((s"$cl resFunc:", true)) {
      dprintln(s"--triedRes:[${triedRes.mkString(",")}]")
      var prts = resMap(cl).filterNot( prt => triedRes.contains(prt) || m.clmap.pmap.contains(prt) )
      dprintln(s"--not mapped and not tried:[${prts.mkString(",")}]")
      cl match {
        case cl:MC if cl.mctpe==Scatter =>
        case cl:MC => 
          val scu = scuOf(cl)
          if (m.clmap.contains(scu)) {
            prts = prts.filter{ prt => prt.coord == m.clmap(scu).coord }
          }
        case cu:CU if scuOf.pmap.contains(cu) =>
          val mc = scuOf.pmap(cu)
          if (m.clmap.contains(mc)) {
            prts = prts.filter{ prt => prt.coord == m.clmap(mc).coord }
          }
        case _ =>
      }
      if (prts.size>1) routers.foreach { router => prts = router.filterPCL(cl, prts, m) }
      else if (prts.size==0) throw MappingException(m, s"No remaining Controller to map $cl")
      prts
    }
  }

  def map(m:M):M = {
    dprintln(s"Datapath placement & routing ")
    val nodes = topoSort(design.top)
    val reses = design.arch.prts
    bind(
      allNodes = nodes,
      initMap = m,
      constrain = place _,
      resFunc = resFunc _,
      finPass = finPass _ 
    )
  }

  override def checkRemain(mapping:PIRMap) = {
    import mapping._
    val unmapped = design.top.ctrlers.filter { cl => !clmap.contains(cl) }
    warn(s"Unmapped Controllers(${unmapped.size}): [${unmapped.mkString(",")}]")
  }

}

case class ReplaceController[M](blacklist:List[(CL, PCL)], mp:M)(implicit design:Design, mapper:Mapper) 
  extends MappingException(mp) {
  implicit def spade:Spade = design.arch

    override val msg = s"Avoid mapping of ${ blacklist.map{ case (cl, pcl) => s"$cl -> ${quote(pcl)}"}.mkString(",") }"
}
