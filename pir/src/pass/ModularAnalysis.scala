package pir
package pass

import pir.node._
import prism.graph._
import spade.param._
import scala.collection.mutable

class ModularAnalysis(implicit compiler:PIR) extends PIRPass 
  with ExternIOAliaser 
  with LockRMABlockAliaser {

  override def runPass = {
    if (config.asModule) {
      var externs:Seq[PIRNode] = pirTop.collectChildren[DRAMFringe]
      externs = externs.filter { cu =>
        cu.collectDown[StreamCommand]().headOption.nonEmpty
      }
      externs = externs.flatMap { _.descendentTree }
      externs.foreach { n =>
        n.isExtern := true
      }
      externs.collect { case cmd:StreamCommand => cmd }.foreach { cmd =>
        cmd.streams.view.zipWithIndex.foreach { case (stream, i) =>
          assertOneOrLess(stream.collect[GlobalOutput](), s"${dquote(stream)}.gout").foreach { gout =>
            if (cmd.isInstanceOf[FringeStreamWrite] || !gout.isExtern.get) {
              gout.externAlias := cmd.name.get + s"_${i+1}"
            }
          }
        }
      }
    }
    pirTop.collectChildren[BlackBoxContainer].foreach { global =>
      global.isExtern := true
      global.collectChildren[GlobalIO].foreach { gio =>
        val bbport = getBBPort(gio)
        gio.externAlias := getAlias(bbport)
        gio.isExtern := true
      }
    }
  }

}

trait ExternIOAliaser {
  def getAlias(io:Edge[PIRNode,_,_]) = s"$io"
  def getBBPort(n:GlobalIO) = n match {
    case n:GlobalInput => n.out.T.head.out.singleConnected.get
    case n:GlobalOutput => n.in.T.as[BufferWrite].data.singleConnected.get
  }
}

trait LockRMABlockAliaser extends ExternIOAliaser {
  private def accumName(l:LockRMABlock,io:Edge[PIRNode,_,_]) = {
    val accumIdx = l.accums.indexOf(l.accumMap(io))
    s"accum${accumIdx}"
  }
  override def getAlias(io:Edge[PIRNode,_,_]) = io match {
    case InputField(l:LockRMABlock, "lockAddr") => s"$l/splitter_in_c${l.laneMap(io)}"
    case OutputField(l:LockRMABlock, "lockAck") => s"$l/rmw_acks_c${l.laneMap(io)}"
    case InputField(l:LockRMABlock, "lockInputIn") => s"$l/f${l.inputMap(io)}_in_tree_p${l.laneMap(io)}/in${l.treeInMap(io)}"
    case OutputField(l:LockRMABlock, "lockInputOut") => s"$l/f${l.inputMap(io)}_in_tree_p${l.laneMap(io)}/out"
    case InputField(l:LockRMABlock, "lockDataIn") => s"$l/pmu_dat_in_a${accumName(l,io)}_p${l.laneMap(io)}"
    case OutputField(l:LockRMABlock, "lockDataOut") => s"$l/pmu_dat_out_a${accumName(l,io)}_p${l.laneMap(io)}"
    case InputField(l:LockRMABlock, "unlockReadAddr") => s"$l/pmu_addr_flush_a${accumName(l,io)}_p${l.laneMap(io)}"
    case OutputField(l:LockRMABlock, "unlockReadData") => s"$l/pmu_data_flush_a${accumName(l,io)}_p${l.laneMap(io)}"
    case InputField(l:LockRMABlock, "unlockWriteAddr") => s"$l/pmu_addr_init_a${accumName(l,io)}_p${l.laneMap(io)}"
    case InputField(l:LockRMABlock, "unlockWriteData") => s"$l/pmu_data_init_a${accumName(l,io)}_p${l.laneMap(io)}"
    case OutputField(l:LockRMABlock, "unlockWriteAck") => s"$l/pmu_ack_init_a${accumName(l,io)}_p${l.laneMap(io)}"
    case _ => super.getAlias(io)
  }
}
