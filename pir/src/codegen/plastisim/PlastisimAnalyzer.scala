package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.util._
import scala.collection.mutable

class PlastisimAnalyzer(implicit compiler:PIR) extends ContextTraversal with BFSTraversal with UnitTraversal {
  val forward = true

  var passTwo = false

  override def visitNode(n:N) = {
    n.to[Context].foreach { n =>
      compCount(n)
    }
  }

  override def finPass = {
    passTwo = true
    val ctxs = pirTop.collectDown[Context]()
    ctxs.foreach { _.count.reset }
    // Two passes to handle cycle in data flow graph
    ctxs.foreach { n =>
      val count = compCount(n).get
      if (n.collectDown[HostOutController]().nonEmpty & count.isKnown) {
        assert(count == Finite(1), s"Host out count != 1: $count")
      }
      n.collectDown[FringeStreamRead]().headOption.foreach { streamRead =>
        streamRead.count.v.foreach { v =>
          assert(count == v, s"StreamOut count $count != annotated count $v")
        }
      }
      countByReads(n).foreach { c =>
        c.zip(count).foreach { case (c, count) =>
          assert(c == count, s"${n.reads}.count($c) * scale != $n.count($count)")
        }
      }
    }
    super.finPass
    passTwo = false
  }

  /*
   * Compute count of the context using reads. Return None if reads is empty
   * and ctrlers nonEmpty
   * */
  def countByReads(n:Context):Option[Value[Long]] = dbgblk(s"countByReads($n)") {
    val counts = n.reads.flatMap { read => 
      if (!passTwo && read.initToken.get) None 
      else compCount(read).map { _ * read.getScale }
    }
    val (unknown, known) = counts.partition { _.unknown }
    val (finite, infinite) = known.partition { _.isFinite }
    val c = if (unknown.nonEmpty) Some(Unknown)
    else if (finite.nonEmpty) assertIdentical(finite, 
    s"$n.reads.count reads=${n.reads.map { r => (r, compCount(r)) }} countByController=${countByController(n)}")
    else if (infinite.nonEmpty) Some(Infinite)
    else if (n.collectFirstChild[FringeStreamWrite].nonEmpty) None
    else { // reads is empty
      val ctrlers = n.ctrlers
      val forevers = ctrlers.filter { _.isForever }
      val (infiniteForever, stopForever) = forevers.partition { _.as[LoopController].stopWhen.T.isEmpty }
      if (ctrlers.isEmpty) None
      else if (infiniteForever.nonEmpty) Some(Infinite)
      else if (stopForever.nonEmpty) Some(Unknown)
      else None
    }
    c
  }

  def countByController(n:Context) = dbgblk(s"countByContrller($n)"){
    n.ctrlers.map { _.getIter }.reduceOption { _ * _ }
  }

  def compCount(n:PIRNode):Option[Value[Long]] = {
    n.count.v orElse {
      val count:Option[Value[Long]] = dbgblk(s"compCount($n)"){
        n match {
          case StreamWriteContext(sw) => sw.count.v match {
            case Some(v) => Some(v)
            case None => Some(Unknown)
          }
          case n:Context =>
            val ctrlers = n.ctrlers
            var inferByInput = false
            inferByInput ||= n.streaming.get
            inferByInput ||= ctrlers.exists { ctrler => ctrler.isForever && !ctrler.hasBranch }
            if (inferByInput) countByReads(n)
            else countByController(n)
          case n:LocalOutAccess =>
            compCount(n.in.T)
          case n:LocalInAccess =>
            compCount(n.ctx.get).map { _ /! n.getScale }
          case n:GlobalInput =>
            compCount(n.in.T)
          case n:GlobalOutput =>
            compCount(n.in.T)
          case n => throw PIRException(s"Don't know how to compute count of $n")
        }
      }
      count.foreach { c => n.count := c }
      count
    }
  }
    
}
