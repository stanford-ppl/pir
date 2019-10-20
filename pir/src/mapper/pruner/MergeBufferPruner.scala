package pir
package mapper

import pir.node._
import pir.util._
import spade.param._
import prism.collection.immutable._
import prism.graph._
import scala.collection.mutable

class MergeBufferPruner(implicit compiler:PIR) extends CUPruner {

  override def getCosts(x:Any):List[Cost[_]] = x.getCost[MergeBufferCost] :: Nil

  override def recover(x:EOption[CUMap]):EOption[CUMap] = {
    x match {
      case Left(f@InvalidFactorGraph(fg:CUMap, k:CUMap.K)) =>
        val kcost = getCosts(k).head.as[MergeBufferCost]
        val vs = fg.freeValuesOf(k)
        val vcost = assertOne(vs.map { getCosts(_) }, s"MergeBufferCost").head.as[MergeBufferCost]
        if (kcost.quantity > vcost.quantity) Left(f.as) else {
          dbg(s"Recover $k")
          dbg(s"kcost=$kcost")
          dbg(s"vcost=$vcost")
          val mb = quoteSrcCtx(k.collectDown[MergeBuffer]().head)
          val ks = split(k, kcost, vcost)
          info(s"Split $k $mb into ${ks.size} CUs $kcost")
          //breakPoint(s"$k")
          newFG(fg, k, ks, vs)
        }
      case x => super.recover(x)
    }
  }

  def split(k:CUMap.K, kcost:MergeBufferCost, vcost:MergeBufferCost):Set[CUMap.K] = dbgblk(s"split($k)"){
    val mb = k.collectDown[MergeBuffer]().head
    val par = mb.par
    val ctx = mb.ctx.get
    var inputs = mb.inputs.map { _.collectFirst[BufferWrite]().data.singleConnected.get }
    var bounds = mb.bounds.map { _.collectFirst[BufferWrite]().data.singleConnected.get }
    var init = mb.init.collectFirst[BufferWrite]().data.singleConnected.get
    val out = mb.out.collectFirst[BufferRead]().out

    val globals = mutable.Set.empty[CUMap.K]

    var nmbs = List(mb)
    val vways = vcost.ways
    while (inputs.size > 1) {
      nmbs = within(pirTop) {
        inputs.grouped(vways).zip(bounds.grouped(vways)).map { case (inputs, bounds) =>
          val global = stage(ComputeContainer())
          globals += global
          within(global, ctx.getCtrl) {
            val mctx = stage(Context().streaming(true))
            mirrorMetas(mctx,ctx)
            within(mctx) {
              val mmb = MergeBuffer(vways, par)
              mirrorMetas(mmb,mb)
              (0 until vways).foreach { i =>
                mmb.inputs(i)(inputs(i))
                mmb.bounds(i)(bounds(i))
              }
              mmb.init(init)
              stage(mmb)
            }
          }
        }.toList

      }
      nmbs.foreach { nmb => 
        val mctx = nmb.ctx.get
        bufferInput(mctx)
      }
      inputs = nmbs.map { _.out }
      bounds = nmbs.map { _.outBound }
      init = nmbs.head.outInit
    }
    nmbs.foreach { nmb =>
      swapOutput(out, nmb.out)
      bufferOutput(nmb.out)
    }
    removeNodes(List(k))
    globals.toSet
  }

}
