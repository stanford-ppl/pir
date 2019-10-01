package pir
package codegen

import pir.node._
import prism.graph._
import prism.util._
import spade.param.{NumOp => _, _}

class PlastisimConfigGen(implicit compiler: PIR) extends PlastisimCodegen with PIRTraversal {

  val fileName = config.psimConfigName
  val forward = true

  override def emitNode(n:N) = {
    n match {
      case n:Context =>
        val nodeType = n.global.get match {
          case n:DRAMFringe if config.enableTrace & n.collectDown[DRAMCommand]().nonEmpty => s"dramnode"
          case n => s"node"
        }
        emitNodeBlock(s"$nodeType ${quote(n)} # ${n.global.get}") {
          emitNodeSpecs(n)
          emitInLinks(n)
          emitOutLinks(n)
        }
        n.writes.foreach(emitLink)
      case _ => visitNode(n)
    }
  }

  override def runPass = {
    super.runPass
    emitNetwork
    emitMemoryController
  }

  def emitNetwork = {
    if (!noPlaceAndRoute) {
      spadeParam.pattern match {
        case pattern:Checkerboard =>
          val maxDim = Math.max(pattern.row, pattern.col + 2)
          val networkParam = pattern.networkParams.filter { _.granularity == "vec" }.head
          val numVC = networkParam.numVC
          val topo = networkParam.topology
          emitNodeBlock(s"net vecnet") {
            emitln(s"cfg = ${config.psimHome}/configs/${topo}_generic.cfg")
            emitln(s"dim[0] = $maxDim")
            emitln(s"dim[1] = $maxDim")
            emitln(s"num_classes = ${numVC}")
          }
      }
    }
  }

  def emitMemoryController = {
    if (config.enableTrace) {
      emitNodeBlock(s"mc DRAM") {
        emitln(s"memfile = ${config.psimHome}/configs/DDR3_micron_64M_8B_x4_sg15.ini")
        emitln(s"sysfile = ${config.psimHome}/configs/system.ini")
      }
    }
  }

  def emitNodeBlock(n:Any)(block: => Unit) = dbgblk(s"emitNodeBlock($n)") {
    emitBlock(s"$n", b=NoneBraces)(block)
  }

  def emitNodeSpecs(n:Context) = {
    val traceRelativePath = getRelativePath(config.tracePath, config.psimOut)
    n.collectDown[DRAMCommand]().headOption.flatMap { command =>
      if (config.enableTrace) Some(command) else None
    }.fold{
      emitln(s"lat = ${ getLatency(n)}")
    } {
      case command:FringeDenseLoad =>
        val par = command.data.T.getVec
        emitln(s"size_trace = ${buildPath(traceRelativePath, s"${command}_size.trace")}")
        emitln(s"offset_trace = ${buildPath(traceRelativePath, s"${command}_offset.trace")}")
        emitln(s"dram_cmd_tp=dense_load")
        emitln(s"out_token_size = ${par * spadeParam.bytePerWord}")
        emitln(s"controller=DRAM")
      case command:FringeDenseStore =>
        val par = command.data.T.getVec
        emitln(s"size_trace = ${buildPath(traceRelativePath, s"${command}_size.trace")}")
        emitln(s"offset_trace = ${buildPath(traceRelativePath, s"${command}_offset.trace")}")
        emitln(s"dram_cmd_tp=dense_store")
        emitln(s"in_token_size = ${par * spadeParam.bytePerWord}")
        emitln(s"controller=DRAM")
      case command:FringeSparseLoad =>
        val par = command.data.T.getVec
        emitln(s"size_trace = ${par * spadeParam.bytePerWord}")
        emitln(s"offset_trace = ${buildPath(traceRelativePath, s"${command}_addr.trace")}")
        emitln(s"dram_cmd_tp=dense_load")
        emitln(s"out_token_size = ${par * spadeParam.bytePerWord}")
        emitln(s"controller=DRAM")
        emitln(s"burst_size = ${spadeParam.bytePerWord}")
      case command:FringeSparseStore =>
        val par = command.data.T.getVec
        emitln(s"size_trace = ${par * spadeParam.bytePerWord}")
        emitln(s"offset_trace = ${buildPath(traceRelativePath, s"${command}_addr.trace")}")
        emitln(s"dram_cmd_tp=dense_store")
        emitln(s"in_token_size = ${par * spadeParam.bytePerWord}")
        emitln(s"controller=DRAM")
        emitln(s"burst_size = ${spadeParam.bytePerWord}")
    }
    emitStartToken(n)
    emitStopToken(n)
    n.count.get match {
      case Unknown => emitln(s"# count not exists")
      case Finite(c) => emitln(s"count = $c")
      case Infinite => emitln(s"count = $infCount # count is nfinite")
    }
  }

  def paramOf(n:Context):Parameter = {
    val global = n.global.get
    topMap.right.get.cumap.usedMap(global).params.get
  }

  def getLatency(n:Context):Int = {
    n.global.get match {
      case g:ArgFringe => 1
      case g:DRAMFringe if g.collectDown[DRAMCommand]().nonEmpty => 100
      case g:DRAMFringe if g.collectDown[StreamCommand]().nonEmpty => 1
      case g:GlobalContainer if spadeParam.isAsic =>
          Math.max(n.collectDown[OpNode]().map { getLatency }.sum, 1)
      case g:GlobalContainer =>
        val cuParam = paramOf(n).as[CUParam]
        if (cuParam.traceOut[TopParam].scheduled) 1 else {
          val ops = n.collectDown[OpNode]()
          val unusedStage = cuParam.numStage - ops.size
          ops.map { getLatency }.sum + unusedStage
        }
    }
  }

  def getLatency(n:OpNode):Int = n match {
    case n:RegAccumOp => new NumOp(n.getCtrl.getVec).log2 + 1
    case n => 1
  }

  def emitStartToken(n:Context) = {
    if (n.reads.isEmpty) {
      n.count.get match {
        case Unknown => err(s"count of $n with no inputs unknown")
        case Finite(c) => emitln(s"start_at_tokens = $c")
        case Infinite => emitln(s"start_at_tokens = $infCount")
      }
    } else {
      n.reads.view.zipWithIndex.foreach { case (read, idx) =>
        if (read.initToken.get) {
          emitln(s"unscaled_init_token[${idx}] = 1")
        }
      }
    }
  }

  def emitStopToken(n:Context) = {
    n match {
      case n if n.collectDown[HostOutController]().nonEmpty =>
        emitln(s"stop_after_tokens = 1")
      case n if n.collectDown[FringeStreamRead]().nonEmpty =>
        emitln(s"stop_after_tokens = ${n.constCount}")
      case n =>
    }
  }

  def getBufferSize(n:Context, read:LocalOutAccess) = {
    val fifoDepth = config.option[Int]("fifo-depth")
    if (spadeParam.isAsic || spadeParam.isP2P) fifoDepth
    else {
      val tp = if (read.getVec > 1) "vec" else "word"
      paramOf(n) match {
        case param:CUParam => param.fifoParamOf(tp).get.depth
        case param:ArgFringeParam => fifoDepth
        case param:MCParam => fifoDepth
      }
    }
  }

  def emitInLinks(n:Context) = dbgblk(s"emitInLinks($n)") {
    n.reads.view.zipWithIndex.foreach { case (read, idx) =>
      emitln(s"link_in[$idx] = ${read.inAccess}")
      emitln(s"scale_in[$idx] = ${read.constScale}")
      emitln(s"buffer[$idx] = ${getBufferSize(n, read)}")
    }
  }

  def emitOutLinks(n:Context) = dbgblk(s"emitOutLinks($n)") {
    n.writes.view.zipWithIndex.foreach { case (write, idx) =>
      emitln(s"link_out[$idx] = $write")
      emitln(s"scale_out[$idx] = ${write.constScale}")
    }
  }

  def emitLink(n:LocalInAccess) = dbgblk(s"emitLink($n)"){
    val src = n.ctx.get
    val dsts = n.outAccesses.map { _.ctx.get }
    dbg(s"src=$src dsts=$dsts")
    val isGlobal = n.isGlobal
    val isLocalLink = !isGlobal || noPlaceAndRoute
    val linkstr = if (isLocalLink) "" else "net"

    emitNodeBlock(s"${linkstr}link ${quote(n)}") {
      val tp = if (n.getVec > 1) "vec" else "scal"
      emitln(s"type = ${quote(tp)}")
      emitln(s"src[0] = ${quote(src)}")
      dsts.view.zipWithIndex.foreach { case (dst,idx) =>
        emitln(s"dst[$idx] = ${quote(dst)}")
      }
      n.count.get match {
        case Unknown => emitln(s"# count not exists")
        case Finite(c) => emitln(s"count = $c")
        case Infinite => emitln(s"count = $infCount # count is infinite")
      }
      if (isLocalLink) {
        dsts.view.zipWithIndex.foreach { case (dst, dstIdx) =>
          emitln(s"lat[0, $dstIdx] = 1")
        }
      } else {
        emitln(s"net = vecnet")
        val vc_id = n.gout.get.id
        emitln(s"vc_id = $vc_id")
        val sid = src.global.get.id
        emitln(s"src_id[0] = $sid")
        dsts.view.zipWithIndex.foreach { case (dst, idx) =>
          val did = dst.global.get.id
          emitln(s"dst_id[$idx] = $did")
        }
      }
    }
  }

}
