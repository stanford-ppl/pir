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

  def lowerMem(ctx:Context) = dbgblk(s"lowerMem($ctx)"){
    ctx.collectChildren[Access].groupBy { _.mem.T }.foreach { 
      case (mem, accesses) if accesses.size == 1 & mem.accesses.size != 1 =>
      case (mem:Reg, List(reader:MemRead)) if mem.accesses.size==1 =>
        mem.inits.v.fold { 
          err(s"Reading undefined memory ${quoteSrcCtx(mem)}")
        }{ inits =>
          val const = within(reader.parent.get, reader.getCtrl) {
            stage(Const(inits))
          }
          swapOutput(reader.out, const.out)
          removeNodes(List(mem, reader))
        }
      case (mem:Reg, List(writer:MemWrite, reader:MemRead)) if !writer.en.isConnected && !reader.en.isConnected =>
        swapOutput(reader.out, writer.data.singleConnected.get)
        removeNodes(List(mem, writer, reader))
      case (mem, accesses) => 
        err(s"Multiple accesses to the same memory in the same basic block will deadlock on plasticine.", false)
        err(s"Please partition them in multiple basic block", false)
        err(s"memory: ${quoteSrcCtx(mem)} accesses:", false)
        accesses.foreach { a => err(quoteSrcCtx(a), false) }
        err("")
    }
  }

}
