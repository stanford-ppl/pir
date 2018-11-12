package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._

class PlastisimConfigGen(implicit compiler: PIR) extends PIRTraversal with Codegen with ChildFirstTraversal {

  override def dirName = buildPath(super.dirName, s"../plastisim") 
  val fileName = s"psim.conf"
  val forward = true

  def emitNodeBlock(n:Any)(block: => Unit) = dbgblk(s"emitNodeBlock($n)") {
    emitBlock(s"$n", b=NoneBraces)(block)
  }

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
      case _ => visitNode(n)
    }
  }

  def emitNodeSpecs(n:Context) = {
    //n.global.get match {
      //case cuP:DRAMFringe if isDenseFringe(cuP) & enableTrace =>
        //val offset = cuP.collectDown[StreamOut]().filter { _.field == "offset" }.head
        //val size = cuP.collectDown[StreamOut]().filter { _.field == "size" }.head
        //emitln(s"offset_trace = traces/${readersOf(offset).head}.trace")
        //emitln(s"size_trace = traces/${readersOf(size).head}.trace")
        //val par = ctrlOf(ctxEnOf(cuP).get).asInstanceOf[DramController].par
        //cuP match {
          //case cuP:FringeDenseLoad => 
            //emitln(s"dram_cmd_tp=dense_load")
            //emitln(s"out_token_size = ${par * designParam.bytePerWord}")
          //case cuP:FringeDenseStore => 
            //emitln(s"dram_cmd_tp=dense_store")
            //emitln(s"in_token_size = ${par * designParam.bytePerWord}")
        //}
        //emitln(s"controller=DRAM")
      //case cuP:DRAMFringe if isSparseFringe(cuP) & enableTrace =>
        //val addr = cuP.collectDown[StreamOut]().filter { _.field == "addr" }.head
        //val par = getParOf(writersOf(addr).head)
        //emitln(s"offset_trace = traces/${readersOf(addr).head}.trace")
        //emitln(s"size_trace = ${par * designParam.bytePerWord}") // burst size (byte)
        //emitln(s"burst_size = ${designParam.bytePerWord}")
        //cuP match {
          //case cuP:FringeSparseLoad => 
            //emitln(s"dram_cmd_tp=dense_load")
            //emitln(s"out_token_size = ${par * designParam.bytePerWord}")
          //case cuP:FringeSparseStore => 
            //emitln(s"dram_cmd_tp=dense_store")
            //emitln(s"in_token_size = ${par * designParam.bytePerWord}")
        //}
        //emitln(s"controller=DRAM")
        ////TODO: for scatther this is not called addr
      //case cuP =>
        //emitln(s"lat = ${latencyOf(n).get}")
        emitln(s"lat = 6")
    //}
    //TODO
    //startAtToken(n).foreach { token => emitln(s"start_at_tokens = $token") }
    //stopAfterToken(n).foreach { token => emitln(s"stop_after_tokens = $token") }
    n.count.v.fold {
      emitln(s"# count not exists")
    } { c =>
      emitln(s"count = $c")
    }
  }

  def emitInLinks(n:Context) = dbgblk(s"emitInLinks($n)") {
    n.reads.filterNot(_.isLocal).zipWithIndex.foreach { case (read, idx) =>
      emitln(s"link_in[$idx] = ${read.in.T}")
      emitln(s"scale_in[$idx] = ${read.scale.get.getOrElse(throw PIRException(s"${read}.scale not statically known"))}")
      emitln(s"buffer[$idx] = ${read.depth.get}")
    }
  }

  def emitOutLinks(n:Context) = dbgblk(s"emitOutLinks($n)") {
    n.writes.filterNot(_.isLocal).zipWithIndex.foreach { case (write, idx) =>
      emitln(s"link_out[$idx] = $write")
      emitln(s"scale_out[$idx] = ${write.scale.get.getOrElse(throw PIRException(s"${write}.scale not statically known"))}")
      emitLink(write)
    }
  }

  def emitLink(n:LocalInAccess) = {
    val src = n.ctx.get
    val dsts = n.out.T.map { _.ctx.get }
    val isGlobal = n.isGlobal
    val linkstr = if (isGlobal) "net" else ""

    emitNodeBlock(s"${linkstr}link ${quote(n)}") {
      val tp = if (n.getVec > 1) "vec" else "scal"
      emitln(s"type = ${quote(tp)}")
      emitln(s"src[0] = ${quote(src)}")
      dsts.zipWithIndex.foreach { case (dst,idx) =>
        emitln(s"dst[$idx] = ${quote(dst)}")
      }
      n.getCount.fold {
        emitln(s"# count doen't exist")
      } { c =>
        emitln(s"count = $c")
      }
      if (!isGlobal || spadeParam.isAsic || spadeParam.isP2P) {
        dsts.zipWithIndex.foreach { case (dst, dstIdx) =>
          emitln(s"lat[0, $dstIdx] = 1")
        }
      } else {
        emitln(s"net = vecnet")
        //val vc_id = goutOf(n).get.id
        //emitln(s"vc_id = $vc_id")
        //val sids = srcs.map(src => globalOf(src).get.id)
        //val dids = dsts.map(dst => globalOf(dst).get.id)
        //sids.zipWithIndex.foreach { case (sid, idx) =>
          //emitln(s"src_id[$idx] = $sid")
        //}
        //dids.zipWithIndex.foreach { case (did, idx) =>
          //emitln(s"dst_id[$idx] = $did")
        //}
      }
    }
  }

}
