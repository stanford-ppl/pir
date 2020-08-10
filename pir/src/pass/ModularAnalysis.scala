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
  private def accumName(l:LockRMWBlock,io:Edge[PIRNode,_,_]) = {
    val accumIdx = l.accums.indexOf(l.accumMap(io))
    s"${accumIdx}"
  }
  private def agID(b:SparseDRAMBlock, io:Edge[PIRNode,_,_]) = {
    val (aid, lane) = b.portMap(io.as)
    s"${aid}_$lane"
  }
  private def agID(b:SparseParSRAMBlock, io:Edge[PIRNode,_,_]) = {
    val (aid, lane) = b.portMap(io.as)
    s"${aid}_$lane"
  }

  override def getAlias(io:Edge[PIRNode,_,_]) = io match {
    case InputField(l:LockRMWBlock, "lockAddr") => s"$l/splitter_in_c${l.laneMap(io)}"
    case OutputField(l:LockRMWBlock, "lockAck") => s"$l/rmw_acks_c${l.laneMap(io)}"
    case InputField(l:LockRMWBlock, "lockInputIn") => s"$l/f${l.inputMap(io)}_in_tree_p${l.laneMap(io)}/in${l.treeInMap(io)}"
    case OutputField(l:LockRMWBlock, "lockInputOut") => s"$l/f${l.inputMap(io)}_in_tree_p${l.laneMap(io)}/out"
    case InputField(l:LockRMWBlock, "lockDataIn") => s"$l/pmu_dat_in_a${accumName(l,io)}_p${l.laneMap(io)}"
    case OutputField(l:LockRMWBlock, "lockDataOut") => s"$l/pmu_dat_out_a${accumName(l,io)}_p${l.laneMap(io)}"
    case InputField(l:LockRMWBlock, "unlockReadAddr") => s"$l/pmu_addr_flush_a${accumName(l,io)}_p${l.laneMap(io)}"
    case OutputField(l:LockRMWBlock, "unlockReadData") => s"$l/pmu_data_flush_a${accumName(l,io)}_p${l.laneMap(io)}"
    case InputField(l:LockRMWBlock, "unlockWriteAddr") => s"$l/pmu_addr_init_a${accumName(l,io)}_p${l.laneMap(io)}"
    case InputField(l:LockRMWBlock, "unlockWriteData") => s"$l/pmu_data_init_a${accumName(l,io)}_p${l.laneMap(io)}"
    case OutputField(l:LockRMWBlock, "unlockWriteAck") => s"$l/pmu_ack_init_a${accumName(l,io)}_p${l.laneMap(io)}"

    case InputField(b:SparseParSRAMBlock, "readAddr") => s"$b/read${agID(b,io)}_addr/out0"
    case OutputField(b:SparseParSRAMBlock, "readData") => s"$b/read${agID(b,io)}_data_out/in"
    case InputField(b:SparseParSRAMBlock, "writeAddr") => s"$b/write${agID(b,io)}_addr/out0"
    case InputField(b:SparseParSRAMBlock, "writeData") => s"$b/write${agID(b,io)}_data_in/out0"
    case OutputField(b:SparseParSRAMBlock, "writeAck") => s"$b/write${agID(b,io)}_data_out/in"
    case InputField(b:SparseParSRAMBlock, "rmwAddr") => s"$b/rmw${agID(b,io)}_addr/out0"
    case InputField(b:SparseParSRAMBlock, "rmwDataIn") => s"$b/rmw${agID(b,io)}_data_in/out0"
    case OutputField(b:SparseParSRAMBlock, "rmwDataOut") => s"$b/rmw${agID(b,io)}_data_out/in"

    case InputField(b:SparseDRAMBlock, "readAddr") => s"$b/read${agID(b,io)}_addr/out0"
    case OutputField(b:SparseDRAMBlock, "readData") => s"$b/read${agID(b,io)}_data_out/in"
    case InputField(b:SparseDRAMBlock, "writeAddr") => s"$b/write${agID(b,io)}_addr/out0"
    case InputField(b:SparseDRAMBlock, "writeData") => s"$b/write${agID(b,io)}_data_in/out0"
    case OutputField(b:SparseDRAMBlock, "writeAck") => s"$b/write${agID(b,io)}_data_out/in"
    case InputField(b:SparseDRAMBlock, "rmwAddr") => s"$b/rmw${agID(b,io)}_addr/out0"
    case InputField(b:SparseDRAMBlock, "rmwDataIn") => s"$b/rmw${agID(b,io)}_data_in/out0"
    case OutputField(b:SparseDRAMBlock, "rmwDataOut") => s"$b/rmw${agID(b,io)}_data_out/in"
    case _ => super.getAlias(io)
  }
}
