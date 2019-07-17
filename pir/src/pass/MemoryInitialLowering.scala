package pir
package pass

import pir.node._
import prism.graph._
import spade.param._
import scala.collection.mutable

class MemoryInitialLowering(implicit compiler:PIR) extends PIRTransformer {

  override def runPass = {
    pirTop.collectDown[Context]().foreach(lowerMem)
  }

  //TODO: move to rewriter
  def lowerMem(ctx:Context) = dbgblk(s"lowerMem($ctx)"){
    ctx.collectChildren[Access].groupBy { _.mem.T }.map { case (mem, accesses) =>
      val sorted = accesses.sortBy { _.order.get }
      dbg(s"mem:$mem, accesses:$sorted")
      (mem, sorted)
    }.foreach { 
      case (mem, accesses) if accesses.size == 1 & mem.accesses.size != 1 =>
      case (mem:Reg, List(reader:MemRead)) if mem.accesses.size==1 =>
        mem.inits.v.fold { 
          err(s"Reading undefined memory ${quoteSrcCtx(mem)}")
        }{ inits =>
          val const = within(reader.parent.get, reader.getCtrl) {
            stage(Const(inits))
          }
          swapOutput(reader.out, const.out)
        }
      case (mem:Reg, List(writer:MemWrite, reader:MemRead)) if writer.en.matchWith(reader.en) =>
        dbgblk(s"Remove $writer -> $mem -> $reader") {
          swapOutput(reader.out, writer.data.singleConnected.get)
        }
      case (mem:Memory, List(writer:BankedWrite, reader:BankedRead)) 
        if writer.en.matchWith(reader.en)
          && writer.bank.matchWith(reader.bank)
          && writer.offset.matchWith(reader.offset) =>
        dbgblk(s"Remove $writer -> $mem -> $reader") {
          swapOutput(reader.out, writer.data.singleConnected.get)
        }
      case (mem, List(writer:InAccess, reader:OutAccess)) =>
        err(s"Multiple accesses to the same memory in the same basic block will deadlock on plasticine.", false)
        err(s"Please partition them in multiple basic block", false)
        err(s"${quoteSrcCtx(mem)} ${quoteSrcCtx(writer)} ${quoteSrcCtx(reader)}:", true)
      case (mem, accesses) => 
    }
  }

}
