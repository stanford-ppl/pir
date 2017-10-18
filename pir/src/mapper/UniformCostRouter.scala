package pir.mapper

import pir._
import pir.util.typealias.{Seq => _, _}

import spade._
import spade.traversal.PlasticineGraphTraversal

import pirc._

import scala.language.existentials
import scala.reflect.runtime.universe._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

abstract class UniformCostRouter(implicit design:PIR) extends Router with PlasticineGraphTraversal {
  import spademeta._

  override type M = PIRMap

  /* ---- API ------- */
  def filterPCL(cl:CL, prts:List[PCL], mp:PIRMap):List[PCL] = log[List[PCL]]((s"filterPCL($cl)", true)){
    var reses = prts 
    if (reses.isEmpty) throw MappingException(mp, s"No prts to filter for $cl")

    var edges = List[(IO,IO)]()
    edges ++= mappedInputs(cl, mp) // out -> in
    edges ++= mappedOutputs(cl, mp) // in -> out
    edges.foreach { case (tail, head) =>
      reses = reses intersect span(mp, tail, head).toList
    }
    reses
  }

  def route(cl:CL, m:M):M = log[M]((s"route($cl)", true)) {
    var mp = m
    var edges = List[(O,I)]()
    edges ++= mappedInputs(cl, mp)
    edges ++= mappedOutputs(cl, mp).map { case (in, out) => (out, in) }
    mp = route(mp, edges)
    mp
  }
  /* ---- API (END) ------- */

  def route(m:M, edges:List[(O,I)]):M = {
    if (!edges.isEmpty) {
      val (out, in)::rest = edges
      search(m, out, in) { m => route(m, rest) }
    } else m
  }

  def span(mp:M, tail:IO, head:IO):Seq[S] = {
    val start = mp.pmmap(ctrler(tail))
    def adv(n:S, prevEdge:Option[(PIO, PIO)], c:C) = {
      advance[IO, IO, PIO, PIO](mp, tail, head)(n, prevEdge, c)
    }
    val nodes = uniformCostSpan[(PIO, PIO)](
      start=start,
      advance=adv _,
      logger=Some(logger)
    )
    nodes.flatMap { case (n, c) =>
      if (c >= minHop) Some(n) else None
    }
  }

  def search(mp:M, out:O, in:I)(finPass:M => M):M = {
    val start = mp.pmmap(ctrler(out))
    val end = mp.pmmap(ctrler(in))
    def fp(m:M, route:List[(S, FE)], cost:C) = {
      var mp = m
      val pin = route.last._2._2.asGlobal
      val pout = route.head._2._1.asGlobal
      mp = mp.setVO(out, pout)
      mp = mp.setVI(in, pin)
      mp = mp.setOP(in.asGlobal.out, pin.ic)
      route.foreach { case (_, (o, i)) => 
        mp = mp.setMK(i, out).setMK(o, out)
        //dprintln(s"marker:${quote(o)} $out")
        //dprintln(s"marker:${quote(i)} $out")
        mp
      }
      mp = mp.setRT(in, cost)
      mp = mp.setRT(out, cost)
      finPass(mp)
    }
    val info = s"route $out(${quote(start)}) -> $in(${quote(end)}) maxHop=$maxHop"
    //def failPass(e:Throwable) = {
      //logger.closeAndWriteAllBuffers
      //breakPoint(mp, s"$e", true)
      //e match {
        //case e:E => // mapping exception
        //case e => throw e
      //}
    //}
    log[M](info/*, failPass=failPass*/) {
      uniformCostSearch[FE](
        start   = start,
        end     = end,
        advance = advance(mp, out, in) _,
        map     = mp,
        finPass = fp _,
        logger  = Some(logger)
      ) match {
        case Left(e) => throw MappingException(mp, info + " (failed)")
        case Right(m) => m
      }
    }
  }

  /*
   *   +----------+              +-----------+
   *   |Producer  | (T) ---> (H) | Current   |
   *   +----------+              +-----------+
   * */
  def mappedInputs(cl:CL, mp:M):List[(O,I)] = log[List[(O,I)]](s"mappedInputs($cl)"){
    dprintln(s"ins=${quote(ins(cl))}")
    val res = ins(cl).flatMap { in => 
      val out = from(in)
      if (mp.pmmap.contains(ctrler(out))) Some((out, in)) else None
    }
    dprintln(s"mappedInputs=${quote(res)}")
    res
  }

  /*
   *   +----------+               +-----------+
   *   | Current  | (H) ---> (T)  | Consumer  |
   *   +----------+               +-----------+
   * */
  def mappedOutputs(cl:CL, mp:M):List[(I,O)] = log[List[(I,O)]](s"mappedOutputs($cl)") {
    dprintln(s"outs=${quote(outs(cl))}")
    val res = outs(cl).flatMap { out => 
      to(out).flatMap { in => 
        if (mp.pmmap.contains(ctrler(in))) Some((in, out)) else None
      }
    }
    dprintln(s"mappedOutputs=${quote(res)}")
    res
  }

  def advance[T<:IO,H<:IO,PT<:PIO,PH<:PIO](
    mp:M, 
    tail:T, 
    head:H
  )(n:S, prevEdge:Option[(PT, PH)], c:C):Seq[(S, (PT,PH), C)] = {

    val start = mp.pmmap(ctrler(tail))

    val marker = (tail, head) match {
      case (tail:O, head:I) => tail
      case (tail:I, head:O) => head
    }

    def filterUsed[T<:PIO](pios:Seq[T]):Seq[T] = {
      var res = pios
      if (c > maxHop) res = Nil
      // Filter out unmatched marker
      res = res.filterNot { pio => mp.mkmap.contains(pio) && mp.mkmap(pio) != marker }
      // Encourage reuse
      val (reuse, unused) = res.partition { pio => mp.mkmap.contains(pio) }
      res = reuse ++ unused
      res
    }

    def tails(n:S, prevHead:Option[PH]) = {
      var ptails = ((tail, head) match {
        case (tail:O, head:I) => pouts(n)
        case (tail:I, head:O) => pins(n)
      }).asInstanceOf[Seq[PT]]
      if(n.isInstanceOf[PSB]) {
        prevHead.foreach { 
          case prevHead:PGI[_] =>
            /*
             *    prevHead  +-------------------------+  pt
             *  ----------->|prevhead.ic(o)   ptics (is)| ------> 
             *              +-------------------------+
             * */
            mp.fimap.get(prevHead.ic).foreach { ptics =>
              ptails = ptails.filter { pt => ptics.map(_.src).contains(pt.asGlobal) }
            }
          case prevHead:PGO[_] =>
            /*
             *    prevHead  +-------------------------+  pt
             *  <-----------|prevhead.ic(is)   ptic(o)| <------
             *              +-------------------------+
             * */
            mp.fimap.get(prevHead.ic).foreach { ptic =>
              ptails == ptails.filter { _ == ptic.src }
            }
        }
      }
      filterUsed(ptails)
    }

    def heads(tail:PT) = {
      val pheads = (tail match {
        case tail:PI => tail.fanIns.map(_.asGlobal)
        case tail:PO => tail.fanOuts.map(_.asGlobal)
      }).asInstanceOf[Seq[PH]]
      filterUsed(pheads)
    }

    super.advance[PH, PT]( // Head, Tail
      tails=tails _,
      heads=heads _,
      src=(head:PH) => head.src.asInstanceOf[PRT],
      start=start
    )(n, prevEdge, c).map { case (n, edge) => (n, edge, getCost(edge)) }

  }

  def getCost(edge:(PIO, PIO)) = {
    1
  }

}

class VectorUCRouter()(implicit val design:PIR) extends UniformCostRouter with VectorRouter

class ScalarUCRouter()(implicit val design:PIR) extends UniformCostRouter with ScalarRouter

class ControlUCRouter()(implicit val design:PIR) extends UniformCostRouter with ControlRouter
