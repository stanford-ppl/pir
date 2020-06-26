package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait SparseLowering extends GenericMemoryLowering {

  override val invalidAddress = -2

  protected val accessReqResp = mutable.Map[Access, (Input[PIRNode], Output[PIRNode])]()
  protected val barrierWrite = mutable.Map[Barrier, mutable.ListBuffer[Access]]()
  protected val barrierRead = mutable.Map[Barrier, mutable.ListBuffer[Access]]()

  override def finPass = {
    barrierInsertion
    accessReqResp.clear
    barrierWrite.clear
    barrierRead.clear
  }
  
  private def barrierInsertion = {
    val barriers = barrierWrite.keys.toSet ++ barrierRead.keys
    barriers.foreach { barrier =>
      val writes = barrierWrite.getOrElseUpdate(barrier, mutable.ListBuffer.empty)
      val reads = barrierRead.getOrElseUpdate(barrier,mutable.ListBuffer.empty)
      if (writes.isEmpty)
        warn(s"${quoteSrcCtx(barrier)} doesn't have any writer!")
      else if (reads.isEmpty)
        warn(s"${quoteSrcCtx(barrier)} doesn't have any reader!")
      else
        processBarrier(barrier)
    }
  }

  private def processBarrier(barrier:Barrier) = {
    within(barrier.srcCtx.get) {
      val barrierCtx = within(pirTop, barrier.ctrl) {
        stage(Context().name.mirror(barrier.name).streaming(true))
      }
      val writes = barrierWrite(barrier)
      val intokens:Iterable[Output[PIRNode]] = writes.map { writer =>
        val resp = accessReqResp(writer)._2
        insertToken(fctx=resp.src.ctx.get, tctx=barrierCtx, dep=Some(resp)).out
      }
      val merged = within(barrierCtx, barrier.ctrl) {
        intokens.reduce[Output[PIRNode]]{ case (out1, out2) =>
          stage(Forward().in(out1).dummy(out2)).out
        }
      }
      val reads = barrierRead(barrier)
      reads.foreach { access =>
        val req = accessReqResp(access)._1
        val reqctx = req.src.ctx.get
        val token = insertToken(fctx=barrierCtx, tctx=reqctx, dep=Some(merged))
        token.initToken := barrier.init
        token.inits := true
        val forward = within(reqctx, req.src.getCtrl) {
          stage(Forward().in(req.connected).dummy(token.out))
        }
        swapConnection(req, req.singleConnected.get, forward.out)
      }
    }
    //breakPoint(s"barrier=$barrier ${writes} ${reads}")
  }

}
