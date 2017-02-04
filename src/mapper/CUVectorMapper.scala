package pir.graph.mapper
import pir.graph._
import pir.{Config, Design}
import pir.typealias._
import pir.codegen.{DotCodegen, Printer}
import pir.graph.traversal.{CUCtrlDotPrinter, CUVectorDotPrinter, MapPrinter, PIRMapping}
import pir.plasticine.graph.{Node => PNode}
import pir.plasticine.main._

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.{Map => MMap}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue
import scala.util.{Failure, Success, Try}

class VectorRouter()(implicit val design:Design) extends Mapper with FatPlaceAndRoute {
  override val typeStr = "VecRouter"

  val minHop = 1
  val maxHop = 7

  def filterPNE(cl:CL, pnes:List[PNE], m:PIRMap):List[PNE] = {
    pnes
  }

  def advance(start:PNE, validCons:(PCL, FatPath) => Option[FatPath], 
      advanceCons:(PSB, FatPath) => Boolean)(implicit design:Design):FatPaths = {
    advance((pne:PNE) => pne.vectorIO.outs)(start, validCons, advanceCons)
  }

  type N = VI
  type R = (PCL, Path)

  def resFilter(in:N, m:M, triedRes:PathMap):PathMap = {
    val cl = in.ctrler
    val pcl = m.clmap(cl)
    val out = in.writer
    def validCons(reached:PCL, path:FatPath):Option[FatPath] = {
      var valid = true
      valid &&= (reached == pcl)
      valid &&= (path.size >= minHop)
      valid &&= (path.size < maxHop)
      if (valid) {
        // Make sure picked pco is not already used
        var fp = path
        var fatedge::rest = fp 
        fatedge = fatedge.filter { case (pout, pin) => 
          m.vomap.pmap.get(pout).fold(true) { _ == out }
        }
        // If co is already placed, check if current fatpath can start from that pco, if not pick
        // any unused pco
        val optfatedge = fatedge.filter { case (pout, pin) =>
          m.vomap.get(out).fold(!m.vomap.pmap.contains(pout)) { _ == pout }
        }
        if (optfatedge.size!=0) fatedge = optfatedge
        fp = fatedge::rest

        if (isFatPathValid(fp)) Some(fp) else None
      } else {
        None
      }
    }
    def advanceCons(psb:PSB, fatpath:FatPath) = {
      var valid = true
      valid &&= (fatpath.size < maxHop)
      valid
    }
    val routes = advance(pcl, validCons _, advanceCons _)
    routes.diff(triedRes)
    filterUsedRoutes(routes, m)
  }

  def mapIns(ins:List[VI], m:M):M = {

    val sameSrcMap:Map[CL, Map[VO, List[VI]]] = ins.groupBy{ _.ctrler }.map { case (ctrler, ins) =>
      ctrler -> ins.groupBy { in => in.writer }
    }

    def cons(n:N, r:R, m:M):M = {
      val (reachedCU, path) = r
      val pin = path.last._2
      val pout = path.head._1
      var mp = sameSrcMap(n.ctrler)(n.writer).foldLeft(m) { case (pm, n) =>
        pm.setVI(n, pin).setVO(n.writer, pout).setRT(n, path.size)
      }
      path.zipWithIndex.foreach { case ((vout, vin), i) => 
        mp = mp.setFB(vin, vout)
        if (vout.src.isInstanceOf[PSB]) { // Config SwitchBox
          val to = vout.voport
          val from = path(i-1)._2.viport
          mp = mp.setFP(to, from)
        }
      }
      mp
    }

    val uniqueIns = sameSrcMap.flatMap { case (ctlrer, srcMap) => 
      srcMap.map { case (vout, vins) => vins.head }
    }.toList

    def failPass(e:Throwable) = {
    }

    log("TODO", ((m:M) => ()), failPass) {
      bind[R,N,M](
        allNodes=uniqueIns, 
        initMap=m, 
        constrain=cons _, 
        resFunc=resFilter _, 
        finPass= (m:M) => m
      )
    }
  }

  def route(cl:CL, m:M):M = {
    val pcl = m.clmap(cl)
    val outins = cl.vouts.flatMap { vout =>
      vout.readers.filter { vin => 
        !m.vimap.contains(vin) && m.clmap.contains(vin.ctrler)
      }
    }
    var mp = mapIns(outins, m)
    val ins = cl.vins.filter { vin =>
      !m.vimap.contains(vin) && m.clmap.contains(vin.writer.ctrler)
    }
    mp = mapIns(ins, mp)
    mp
  }

}

class CUMapper1()(implicit val design:Design) extends CUMapper {
  var debugRouting = false 
  val typeStr = "CUMapper"

  override implicit val mapper:CUMapper1 = this

  override val exceptLimit = 200

  val minHop = 1
  val maxHop = 7

  val routers:List[VectorRouter] = List(
    new VectorRouter() //,
    //new ScalarRouter(),
    //new CtrlRouter()
  )

  // DEBUG
  val failPass:Throwable=>Unit = if (debugRouting) {
    {
      case e@FailToMapNode(_,n,es,mp) =>
        val node = n match {
          case (vi, cu) => (vi.asInstanceOf[VI].vector, cu)
          case n => n
        }
        println(s"Fail to map ${node} $es")
        new CUVectorDotPrinter(true)(design).print(mp.asInstanceOf[M])
      case e:Throwable =>
        println(e)
    }
  } else {
    (e:Throwable) => ()
  }
  // DEBUG --

  val resMap:MMap[CL, List[PNE]] = MMap.empty

  def place(cl:CL, pcl:PCL, m:M):M = {
    routers.foldLeft(m.setCL(cl, pcl)) { case (pm, router) =>
      router.route(cl, pm)
    }
  }

  def resFunc(cl:CL, m:M, triedRes:List[PNE]):List[PNE] = {
    val pnes = resMap(cl).filterNot( pcl => triedRes.contains(pcl) )
    routers.foldLeft(pnes) { case (pnes, router) =>
      router.filterPNE(cl, pnes, m)
    }
  }

  def map(m:M):M = {
    dprintln(s"Datapath placement & routing ")
    val nodes = topoSort(design.top).filterNot { _.isInstanceOf[MC] }
    val reses = design.arch.ctrlers
    qualifyCheck(reses, nodes, resMap)
    //bind(
      //allNodes = nodes,
      //initMap = m,
      //constrain = place _,
      //resFunc = resFunc _,
      //finPass = finPass _ 
    //)
    m
  }

  def topoSort(top:Top) = {
    val list = ListBuffer[CL]()
    def isDepFree(cl:CL) = {
      cl.isHead || cl.consumed.forall { csm => list.contains(csm.producer) }
    }
    def addCtrler(cl:CL):Unit = {
      list += cl
      var children = cl.children
      while (!children.isEmpty) {
        children = children.filter { child =>
          if (isDepFree(child)) {
            addCtrler(child)
            false
          } else { true }
        }
      }
    }
    addCtrler(top)
    list.toList.reverse
  }

}
