package pir.mapper

import pir._
import pir.util.topoSort
import pir.util.typealias._

import spade._
import spade.util._

import pirc.util._
import pirc.enums._
import pirc.exceptions._

import scala.collection.mutable.ListBuffer
import scala.util.{Try, Success, Failure}

class CUMapper(implicit val design:PIR) extends Mapper {
  import pirmeta._
  type N = CL
  type R = PCL

  val typeStr = "CUMapper"

  override implicit val mapper:CUMapper = this

  override val exceptLimit = 200

  val routers = ListBuffer[Router]()
  //routers += new VectorBFRouter()
  //routers += new ScalarBFRouter()
  //routers += new ControlBFRouter()
  //routers += new VectorUCRouter()
  //routers += new ScalarUCRouter()
  //routers += new ControlUCRouter()
  routers += new VectorAStarRouter()
  routers += new ScalarAStarRouter()
  routers += new ControlAStarRouter()

  override def debug = PIRConfig.debugCUMapper

  def finPass(m:M):M = m

  def resMap = design.prescreen.resMap

  def place(cl:N, prt:R, m:M):M = {
    val mp = log[M](s"Try $cl -> ${quote(prt)}", buffer=true) {
      Try {
        routers.foldLeft(m.setPM(cl, prt)) { case (pm, router) =>
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

  def resFilter(info:String, before:List[R], mp:M)(func: List[R] => List[R]) = log[List[R]](s"resFilter($info)"){
    val after = func(before)
    dprintln(s"before: ${quote(before)}")
    dprintln(s"after: ${quote(after)}")
    if (after.isEmpty)
      throw MappingException(mp, s"No resource after $info")
    after
  }

  //TODO: change to resFuncWithExcept
  def resFunc(cl:N, m:M, triedRes:List[R]):List[R] = {
    log[List[R]](s"$cl resFunc:", buffer=true) {

      var prts = resFilter(s"resMap", resMap(cl), m) { reses => reses }

      prts = resFilter(s"used", prts, m) { _.filterNot( prt => m.pmmap.contains(prt) ) }

      prts = resFilter(s"triedRes", prts, m) { _.filterNot( prt => triedRes.contains(prt) ) }

      prts = resFilter(s"Fringe Filter", prts, m) { reses =>
        var prts = reses
        cl match {
          case cl:MC if cl.mctpe==Scatter =>
          case cl:MC => 
            dagOf.get(cl).foreach { dag =>
              if (m.pmmap.contains(dag)) {
                prts = prts.filter{ prt => prt.coord == m.pmmap(dag).coord }
              }
            }
            sagOf.get(cl).foreach { sag =>
              if (m.pmmap.contains(sag)) {
                prts = prts.filter{ prt => prt.coord == m.pmmap(sag).coord }
              }
            }
          case cu:CU if dagOf.icontains(cu) =>
            val mc = dagOf.imap(cu)
            if (m.pmmap.contains(mc)) {
              prts = prts.filter{ prt => prt.coord == m.pmmap(mc).coord }
            }
          case cu:CU if sagOf.icontains(cu) =>
            val mc = sagOf.imap(cu)
            if (m.pmmap.contains(mc)) {
              prts = prts.filter{ prt => prt.coord == m.pmmap(mc).coord }
            }
          case cu:CU => // regular cu. prioritize using cuArray
            val (array, fringe) = prts.partition { prt => prt.coord._1 >= 0 && prt.coord._1 < arch.top.param.numCols }
            prts = array ++ fringe
          case _ =>
        }
        prts
      }

      routers.foreach { router => 
        prts = resFilter(s"$router filter", prts, m) { prts => router.filterPCL(cl, prts, m) }
      }

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
    val unmapped = design.top.ctrlers.filter { cl => !pmmap.contains(cl) }
    warn(s"Unmapped Controllers(${unmapped.size}): [${unmapped.mkString(",")}]")
  }

}

case class ReplaceController[M](blacklist:List[(CL, PCL)], mp:M)(implicit design:PIR, mapper:Mapper) 
  extends MappingException(mp) {
  implicit def spade:Spade = design.arch

    override val msg = s"Avoid mapping of ${ blacklist.map{ case (cl, pcl) => s"$cl -> ${quote(pcl)}"}.mkString(",") }"
}
