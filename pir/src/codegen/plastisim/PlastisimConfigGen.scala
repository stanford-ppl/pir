package pir
package codegen

import pir.node._
import prism.graph._
import spade.param._

class PlastisimConfigGen(implicit compiler: PIR) extends PlastisimCodegen with PIRTraversal {

  val fileName = configName
  val forward = true

  override def emitNode(n:N) = {
    n match {
      case n:Context =>
        val nodeType = n.global.get match {
          case n:DRAMFringe if config.enableTrace => s"dramnode"
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
    n.collectDown[DRAMCommand]().headOption.flatMap { command =>
      if (config.enableTrace) Some(command) else None
    }.fold{
      emitln(s"lat = ${getLatency(n)}")
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
    n.getCount.fold {
      emitln(s"# count not exists")
    } { c =>
      emitln(s"count = $c")
    }
  }

  def paramOf(n:Context):Parameter = {
    val global = n.global.get
    topMap.right.get.cumap.usedMap(global).params.get
  }

  def getLatency(n:Context) = {
    n.global.get match {
      case g:ArgFringe => 1
      case g:DRAMFringe => 100
      case g:GlobalContainer if spadeParam.isAsic =>
        Math.max(n.collectDown[OpNode]().size, 1)
      case g:GlobalContainer =>
        paramOf(n).as[CUParam].numStage
    }
  }

  def emitStartToken(n:Context) = {
    val isHostIn = n.collectDown[HostInController]().nonEmpty
    if (isHostIn) {
      emitln(s"start_at_tokens = 1")
    } else {
      val reads = n.reads.filter { read => read.initToken.get }
      if (reads.nonEmpty) {
        val token = assertUnify(reads, s"$n.start_at_token"){ _.constScale }.get
        emitln(s"start_at_tokens = $token")
      }
    }
  }

  def emitStopToken(n:Context) = {
    val isHostOut = n.collectDown[HostOutController]().nonEmpty
    if (isHostOut) {
      emitln(s"stop_after_tokens = 1")
    }
  }

  def getBufferSize(n:Context, read:LocalOutAccess) = {
    if (spadeParam.isAsic || spadeParam.isP2P) 100
    else {
      val tp = if (read.getVec > 1) "vec" else "word"
      paramOf(n) match {
        case param:CUParam => param.fifoParamOf(tp).get.depth
        case param:ArgFringeParam => 100
        case param:MCParam => 100
      }
    }
  }

  def emitInLinks(n:Context) = dbgblk(s"emitInLinks($n)") {
    n.reads.zipWithIndex.foreach { case (read, idx) =>
      emitln(s"link_in[$idx] = ${read.inAccess}")
      emitln(s"scale_in[$idx] = ${read.constScale}")
      emitln(s"buffer[$idx] = ${getBufferSize(n, read)}")
    }
  }

  def emitOutLinks(n:Context) = dbgblk(s"emitOutLinks($n)") {
    n.writes.zipWithIndex.foreach { case (write, idx) =>
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
      dsts.zipWithIndex.foreach { case (dst,idx) =>
        emitln(s"dst[$idx] = ${quote(dst)}")
      }
      n.count.v.fold {
        emitln(s"# count doen't exist")
      } { c =>
        emitln(s"count = $c")
      }
      if (isLocalLink) {
        dsts.zipWithIndex.foreach { case (dst, dstIdx) =>
          emitln(s"lat[0, $dstIdx] = 1")
        }
      } else {
        emitln(s"net = vecnet")
        val vc_id = n.gout.get.id
        emitln(s"vc_id = $vc_id")
        val sid = src.global.get.id
        emitln(s"src_id[0] = $sid")
        dsts.zipWithIndex.foreach { case (dst, idx) =>
          val did = dst.global.get.id
          emitln(s"dst_id[$idx] = $did")
        }
      }
    }
  }

}
