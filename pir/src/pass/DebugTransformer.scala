package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

import scala.collection.mutable

class DebugTransformer(implicit compiler:PIR) extends PIRTransformer with BufferAnalyzer {

  override def runPass = {
    saveToFile((runner.id-1, compiler.getDesign), buildPath(config.outDir,"debug.ckpt"))
    //val ctx = pirTop.collectDown[Context]().filter { _.id == 254 }.head
    //breakPoint("Debug Transformer")
    
    def prefix(x:PIRNode) =  x match {
        case n:LocalOutAccess => 
          n.out.connected.exists { case InputField(_:LoopController, "stopWhen") => true; case _ => false };
        case _ => false
    }
    val buffers = pirTop.filter(
      prefix=prefix,
      visitFunc=visitDown _,
    )
    buffers.foreach { x =>
      val a = x.as[LocalOutAccess]
      a.done.disconnect
      a.depth.reset
      a.depth := 1
    }

    val nodes = pirTop.filter(
      prefix={ n => n.barrier.nonEmpty | n.waitFors.nonEmpty },
      visitFunc=visitDown _,
    )

    dbg(s"Sync nodes=$nodes")

    val barrier = nodes.view.filterNot { _.barrier.isEmpty }.groupBy { n => n.barrier.get }

    nodes.foreach { dst =>
      dst.waitFors.v.foreach { ids =>
        ids.foreach { id =>
          val srcs = barrier.get(id).getOrElse {
            throw PIRException(s"$dst (${dst.srcCtx.v.getOrElse("No source context")}) watis on barrier $id not exists!")
          }
          srcs.foreach { src =>
            src match {
              case src:DRAMCommand =>
              case _ => insertBarrier(src, dst)
            }
          }
        }
      }
    }
  }

  def insertBarrier(from:PIRNode, to:PIRNode) = dbgblk(s"insertBarrier($from, $to)") {
    val fctx = from.ctx.get
    val tctx = to.ctx.get
    if (fctx != tctx) {
      val write = insertToken(fctx, tctx).in.T.as[TokenWrite] // Use buffer read write with reg instead of token
      val ens = from match {
        case from:BufferWrite => from.en.connected
        case from:MemWrite => from.en.connected
        case _ => Nil
      }
      write.en(ens)
      insertGlobalOutput(fctx.global.get)
      insertGlobalInput(tctx.global.get)
    } else {
      (from, to) match {
        case (from:BufferWrite, to:BufferWrite) =>
          to.en(from.en.connected)
        case _ => //TODO
      }
    }
  }

}
