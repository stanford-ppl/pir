package pir
package pass

import pir.node._
import scala.collection.mutable

class LocalMemDuplication(implicit compiler:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal {

  import pirmeta._

  override def visitNode(n:N) = { 
    n match {
      case n:Memory if isLocalMem(n) => duplicateMem(n)
      case n => super.visitNode(n)
    }
  }

  def duplicateMem(mem:Memory) = dbgblk(s"duplicateMem($mem)") {
    val accesses = accessesOf(mem)
    dbg(s"accesses: ${accesses}")
    val ctxMap = accesses.groupBy { access => contextOf(access).get }
    val (inCtxs, outCtxs) = ctxMap.partition { case (ctx, accesses) =>
      if (accesses.forall(isInAccess)) { // writes, resets
        true 
      } else if (accesses.forall(isOutAccess)) { //reads
        false
      } else {
        throw PIRException(s"Not all accesses to local mem $mem in $ctx are the same type $accesses")
      }
    }
    dbg(s"inCtxs=$inCtxs")
    dbg(s"outCtxs=$outCtxs")
    assert(inCtxs.size == 1, s"Local mem $mem has more than 1 inAccess ctx, $inCtxs, $accesses")
    if (outCtxs.size > 1) {
      val memCU = globalOf(mem).get
      val inAccess::Nil = inAccessesOf(mem)
      val mmems = withParent(memCU) {
        List.fill(outCtxs.size-1) { 
          val mmem = dbgblk(s"mirrorX($mem)") {
            mirrorX(mem, mem.values).asInstanceOf[Memory]
          }
          inAccess.writes(mmem)
          mmem
        }
      }
      mmems.zip(outCtxs.tail).foreach { case (mmem, (outCtx,accesses)) =>
        val depeds = mem.depeds.filter { _.isDescendentOf(outCtx) }.toList
        swapUsage[Memory](mem, mmem, at=Some(depeds))
      }
    }

  }

}
