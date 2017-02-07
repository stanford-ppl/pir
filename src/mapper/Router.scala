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

trait Router extends Mapper with FatPlaceAndRoute{
  val minHop = 1
  val maxHop = 7
  override val exceptLimit = 200

  type I<:Node
  type O<:Node
  type R = (PCL, Path)

  def ctrler(io:Node):CL
  def from(in:I):O
  def to(out:O):List[I]
  def ins(cl:CL):List[I]
  def outs(cl:CL):List[O]

  def io(pne:PNE):PGIO[PNE]

  def filterPNE(cl:CL, pnes:List[PNE], m:PIRMap):List[PNE] = {
    pnes
  }

  def advance(start:PNE, validCons:(PCL, FatPath) => Option[FatPath], 
      advanceCons:(PSB, FatPath) => Boolean)(implicit design:Design):FatPaths = {
    advance((pne:PNE) => io(pne).outs)(start, validCons, advanceCons)
  }

  def resFilter(in:I, m:M, triedRes:PathMap):PathMap = {
    val pcl = m.clmap(ctrler(in))
    val out = from(in) 
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
    val fcl = ctrler(from(in))
    val fpcl = m.clmap(fcl)
    val routes = advance(fpcl, validCons _, advanceCons _)
    routes.diff(triedRes)
    val froutes = filterUsedRoutes(routes, m)
    if (froutes.isEmpty)
      dprintln(s"resFunc: $in -> routes:${routes.size} froutes:${froutes.size}")
    froutes
  }

  def mapIns(ins:List[I], m:M):M = {

    val sameSrcMap:Map[CL, Map[O, List[I]]] = ins.groupBy{ in => ctrler(in) }.map { case (ctrler, ins) =>
      ctrler -> ins.groupBy { in => from(in) }
    }

    def cons(n:I, r:R, m:M):M = {
      log(s"Try $n -> $r") {
        val (reachedCU, path) = r
        val pin = path.last._2
        val pout = path.head._1
        var mp = sameSrcMap(ctrler(n))(from(n)).foldLeft(m) { case (pm, n) =>
          pm.setVI(n, pin).setVO(from(n), pout).setRT(n, path.size)
        }
        path.zipWithIndex.foreach { case ((vout, in), i) => 
          mp = mp.setFB(in, vout)
          if (vout.src.isInstanceOf[PSB]) { // Config SwitchBox
            val to = vout.voport
            val from = path(i-1)._2.viport
            mp = mp.setFP(to, from)
          }
        }
        mp
      }
    }

    val uniqueIns = sameSrcMap.flatMap { case (ctlrer, srcMap) => 
      srcMap.map { case (vout, ins) => ins.head }
    }.toList


    def failPass(e:Throwable) = {
    }

    //log("TODO", ((m:M) => ()), failPass) {
      bind[R,I,M](
        allNodes=uniqueIns, 
        initMap=m, 
        constrain=cons _, 
        resFunc=resFilter _, 
        finPass= (m:M) => m
      )
    //}
  }

  def route(cl:CL, m:M):M = {
    val pcl = m.clmap(cl)
    val outins = outs(cl).flatMap { out =>
      to(out).filter { in => 
        !m.vimap.contains(in) && m.clmap.contains(ctrler(in))
      }
    }
    var mp = mapIns(outins, m)
    val inputs = ins(cl).filter { in =>
      !m.vimap.contains(in) && m.clmap.contains(ctrler(from(in)))
    }
    mp = mapIns(inputs, mp)
    mp
  }

  override def mappingCheck(mapping:M) = {
    design.top.ctrlers.foreach { ctrler =>
      ins(ctrler).foreach { in =>
        if(!mapping.vimap.contains(in))
          throw PIRException(s"${in} in ${ctrler} wasn't mapped")
      }
      outs(ctrler).foreach { out =>
        if(!mapping.vomap.contains(out))
          throw PIRException(s"${out} in ${ctrler} wasn't mapped")
      }
    }
  }
}

class VectorRouter()(implicit val design:Design) extends Router {
  override val typeStr = "VecRouter"

  type I = VI
  type O = VO

  def io(pne:PNE):PGIO[PNE] = pne.vectorIO

  def ctrler(io:Node):CL = {
    io match {
      case in:I => in.ctrler
      case out:O => out.ctrler
    }
  }
  def from(in:I):O = in.writer
  def to(out:O) = out.readers
  def ins(cl:CL):List[I] = cl.vins
  def outs(cl:CL):List[O] = cl.vouts

}

class ScalarRouter()(implicit val design:Design) extends Router {
  override val typeStr = "ScalRouter"

  type I = SI
  type O = SO

  def io(pne:PNE):PGIO[PNE] = pne.scalarIO

  def ctrler(io:Node):CL = {
    io match {
      case in:I => in.ctrler
      case out:O => out.ctrler
    }
  }
  def from(in:I):O = in.writer
  def to(out:O) = out.readers
  def ins(cl:CL):List[I] = cl.sins
  def outs(cl:CL):List[O] = cl.souts
}

class ControlRouter()(implicit val design:Design) extends Router {
  override val typeStr = "CtrlRouter"

  type I = IP
  type O = OP

  def io(pne:PNE):PGIO[PNE] = pne.ctrlIO

  def ctrler(io:Node):CL = {
    io match {
      case in:I => in.src.asInstanceOf[PRIM].ctrler
      case out:O => out.src.asInstanceOf[PRIM].ctrler
    }
  }
  def from(in:I):O = in.from
  def to(out:O) = out.to.toList
  def ins(cl:CL):List[I] = cl.ctrlIns
  def outs(cl:CL):List[O] = cl.ctrlOuts
}
