package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

trait MemoryPartitioner extends Partitioner with CUCostUtil {
  // Split one pmu into multiple pmu that fits total number of banks
  def split(x:CUMap, k:CUMap.K, numCU:Int):List[GlobalContainer] = dbgblk(s"split($k, $numCU)"){
    val nodes = k::k.descendents
    val mappings = List.fill(numCU) { within(pirTop) { mirrorAll(nodes) } }
    val bankReads = k.collectDown[BankedRead]()
    val globals = bankReads.flatMap { br =>
      val mbrs = mappings.map { _(br).as[BankedRead] }
      val mrs = mbrs.map { mbr => (mbr.bankHit, mbr.out) }
      val reads = br.collect[BufferRead](visitGlobalOut _).groupBy { _.global.get }
      reads.flatMap { case (global, reads) => mergeBanks(global, br, reads, mrs) }
    }
    val mks = mappings.map { _(k).as[GlobalContainer] }
    mks.foreach { nk =>
      insertGlobalOutput(nk)
    }
    var toremove = nodes
    toremove ++= k.accum(visitFunc = visitGlobalOut _)
    toremove.foreach { removeNode }
    mks ++ globals
  }

  def mergeBanks(global:GlobalContainer, br:BankedRead, reads:List[BufferRead], mrs:List[(Output, Output)]):Seq[GlobalContainer] = dbgblk(s"mergeBanks($global)") {
    global match {
      case fringe:DRAMFringe =>
        within(pirTop) {
          val global = ComputeContainer()
          val read = within(global) {
            val ctx = Context()
            dbg(s"Create $global and $ctx to merge banks for $fringe")
            val ctrl = br.ctrl.get
            val owrite = reads.head.inAccess 
            val done = owrite.done.T
            val deps = getDeps(owrite, { case `owrite` => List(owrite.done.T); case n => visitGlobalIn(n) })
            val mdone = within(ctx) { mirrorAll(deps)(done) }
            val read = within(ctx, ctrl) {
              val read = BufferRead(reads.head.isFIFO).in(br.out).banks(reads.head.banks.get).done(mdone)
              val write = BufferWrite().data(read.out).done(mdone)
              reads.foreach { read =>
                read.in.disconnect
                read.in(write)
              }
              read
            }
            read
          }
          insertGlobalInput(fringe)
          insertGlobalOutput(global)
          global +: mergeBanks(global, br, List(read), mrs)
        }
      case global =>
        reads.groupBy { _.ctx.get }.foreach { case (ctx, reads) =>
          reads.foreach { read =>
            within(ctx, read.ctrl.get) {
              var red:List[(Output,Output)] = mrs
              while(red.size > 1) {
                red = red.sliding(2,2).map{ 
                  case List((m1, o1),(m2, o2)) =>
                    val op = OpDef(Mux).input(m1, o1, o2)
                    dbg(s"add $op.input(${m1.src}.$m1, ${o1.src}.$o1, ${o2.src}.$o2)")
                    (m2, op.out)
                  case List((m1, o1)) => (m1, o1)
                }.toList
              }
              val List((mask, out)) = red.toList
              read.localDepeds.foreach { deped =>
                swapInput(deped, read.out, out)
              }
              removeNode(read)
            }
          }
          bufferInput(ctx)
        }
        insertGlobalInput(global)
        Nil
    }
  }

}
