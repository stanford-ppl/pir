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
    removeStopWhenReg
    insertSynchronization
    insertControBlock
    //breakPoint("Debug Transformer")
  }

  def insertControBlock:Unit = {
    val ctxs = pirTop.collectDown[Context]()
    ctxs.foreach(insertControBlock)
  }

  def insertControBlock(ctx:Context):Unit = {
    // Remove existing control block
    ctx.collectChildren[ControlBlock].foreach { cb =>
      cb.children.foreach { c => swapParent(c, ctx) }
      removeNodes(List(cb))
    }
    val map = ctx.children.groupBy { _.getCtrl }
    var ctrls = map.keys.toList
    ctrls = ctrls.sortBy { _.ancestors.size }
    ctrls.tail.foldLeft[PIRNode](ctx) { case (prev, ctrl) =>
      val cb = within(prev, ctrl) { ControlBlock() }
      map(ctrl).foreach { n => swapParent(n, cb) }
      cb
    }
  }

  def removeStopWhenReg = {
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
      // if the nonblocking register is written locally, remove the register.
      a.in.T.to[BufferWrite].foreach { write =>
        var stops = (write.data.connected ++ write.en.connected)
        a.to[BufferRegRead].foreach { a =>
          a.writeEn.T.foreach { w =>
            w.to[BufferWrite].foreach { en =>
              stops ++= en.data.connected
            }
          }
        }
        val stop = within(a.getCtrl, a.parent.get) {
          stops.reduce[Output[PIRNode]]{ case (o1, o2) =>
            stage(OpDef(FixAnd).addInput(o1, o2)).out
          }
        }
        swapOutput(a.out, stop)
        free(a)
      }
    }
  }

  def insertSynchronization = {
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
              case src:DRAMStoreCommand =>
                val ack = src.ack.T.as[BufferWrite].outAccesses.head
                insertBarrier(ack, dst)
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
        case (from, to) =>
          throw PIRException(s"TODO: insert barrier between ${dquote(from)} ${dquote(to)}")
      }
    }
  }

}
