package pir
package pass

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._

class ContextMerging(implicit compiler:PIR) extends PIRPass with Transformer {

  override def runPass {
    val globals = pirTop.collectDown[GlobalContainer]().collect { case global:MemoryContainer => global }
    globals.foreach(mergeCtx)
  }

  def mergeCtx(global:GlobalContainer) = dbgblk(s"mergeCtx($global)"){
    val mem = assertOne(global.children.collect { case mem:Memory => mem }, s"$global.mem")
    dbg(s"mem=$mem")
    mem.outAccesses.groupBy { read =>
      val readCtrl = read.getCtrl
      (readCtrl::readCtrl.ancestors).as[List[ControlTree]].filter { _.schedule == "ForkJoin" }
    }.filterNot { _._1.isEmpty }.foreach { case (forkJoins, reads) =>
      if (reads.size > 1) {
        dbg(s"forkJoins=$forkJoins")
        val ctxs = reads.map { read =>
          val ctx = read.ctx.get 
          dbg(s"read=$read, ctrl=${read.getCtrl}, ctx=$ctx")
          ctx
        }
        assertUnify(ctxs, s"count") { _.count.v }
        val to::rest = ctxs
        dbg(s"Merge $rest into $to")
        rest.foreach { ctx =>
          ctx.children.foreach { c => swapParent(c, to) }
        }
        rest.foreach(removeNode)
        to.collectDown[LocalOutAccess]().groupBy { _.in.T }.foreach { case (in, reads) => 
          if (reads.size > 1) {
            val read::rest = reads
            dbg(s"Remove redundant $rest")
            rest.foreach { r =>
              r.out.neighbors.foreach { deped =>
                swapInput(deped, r.out, read.out)
              }
            }
            rest.foreach { r => removeNode(r) }
          }
        }
      }
    }
  }

  //def mergeCtx(global:GlobalContainer) = dbgblk(s"mergeCtx($global)"){
    //val mem = assertOne(global.children.collect { case mem:Memory => mem }, s"$global.mem")
    //dbg(s"mem=$mem")
    //mem.outAccesses.groupBy { read =>
      //val readCtrl = read.getCtrl
      //(readCtrl::readCtrl.ancestors).as[List[ControlTree]].filter { _.schedule == "ForkJoin" }
    //}.filterNot { _._1.isEmpty }.foreach { case (forkJoins, reads) =>
      //if (reads.size > 1) {
        //dbg(s"forkJoins=$forkJoins")
        //val ctxs = reads.map { read =>
          //val ctx = read.ctx.get 
          //dbg(s"read=$read, ctrl=${read.getCtrl}, ctx=$ctx")
          //ctx
        //}
        //val newCtx = within(global) {
          //val ctx = Context()
          //within(ctx) {
          //}
        //}
      //}
    //}
  //}

}
