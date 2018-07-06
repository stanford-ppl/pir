package pir
package codegen

import pir.node._
import spade.param._
import prism.collection.mutable._

class PlastisimConfigCodegen(implicit compiler: PIR) extends PlastisimCodegen {
  import pirmeta._
  import PIRConfig._

  val fileName = s"config.psim"

  // Execution of codegen
  override def runPass = {
    super.runPass
    linkGroupOf.values.toSet.foreach { link => emitLink(link) }
    emitNetwork
  }

  override def finPass = {
    super.finPass
    zipOption(PLASTISIM_HOME, psimOut).foreach { case (psimHome, psimOut) =>
      shell(s"ln -f $psimHome/configs/mesh_generic.cfg $psimOut/")
    }
    runPsim
  }

  def runPsim = {
    zipOption(PLASTISIM_HOME, psimOut).fold {
      warn(s"set PLASTISIM_HOME to launch plastiroute automatically!")
      } { case (psimHome, psimOut) =>
        val log = s"$dirName/psim.log"
        var succeed = false
        def processOutput(line:String) = {
          if (line.contains("Simulation complete at cycle")) {
            info(Console.GREEN, s"psim", line)
            succeed = true
          }
        }
        if (runPlastisim) {
          if (enablePlastiroute) {
            shellProcess(s"psim", s"$psimHome/plastisim -f $psimOut/config.psim -p $psimOut/proute.place", log)(processOutput)
          } else {
            //TODO: static place file breaks the simulator
            if (isDynamic(topS)) {
              shellProcess(s"psim", s"$psimHome/plastisim -f $psimOut/config.psim -p $psimOut/pir.place", log)(processOutput)
            } else {
              shellProcess(s"psim", s"$psimHome/plastisim -f $psimOut/config.psim", log)(processOutput)
            }
          }
          if (!succeed) fail(s"Plastisim failed. details in $log")
        } else {
          info(s"To run simulation manually, use following command, or use --run-psim to launch simulation automatically")
          if (enablePlastiroute) {
            info(cstr(Console.YELLOW, s"$psimHome/plastisim -f $psimOut/config.psim -p $psimOut/proute.place"))
          } else {
            //TODO: static place file breaks the simulator
            if (isDynamic(topS)) {
              info(cstr(Console.YELLOW,s"$psimHome/plastisim -f $psimOut/config.psim -p $psimOut/pir.place"))
            } else {
              info(cstr(Console.YELLOW,s"$psimHome/plastisim -f $psimOut/config.psim"))
            }
          }
        }
      }
  }

  override def emitNode(n:N) = n match {
    case n:NetworkNode => emitNetworkNode(n)
    case n => super.visitNode(n)
  }

  def emitNetworkNode(n:NetworkNode) = {
    val cuP = globalOf(n).get
    val nodeType = cuP match {
      case n:DramFringe if PIRConfig.enableTrace => s"dramnode"
      case n => s"node"
    }
    emitNodeBlock(s"$nodeType ${quote(n)} # ${quote(cuP)}") {
      emitNodeSpecs(n)
      emitInLinks(n)
      emitOutLinks(n)
    }
  }

  lazy val bytePerWord = topParam.wordWidth / 8

  def emitNodeSpecs(n:ContextEnable) = {
    val cuP = globalOf(n).get
    cuP match {
      case cuP:DramFringe if isDenseFringe(cuP) & PIRConfig.enableTrace =>
        val size = cuP.collectDown[StreamOut]().filter { _.field == "size" }.head
        val offset = cuP.collectDown[StreamOut]().filter { _.field == "offset" }.head
        cuP match {
          case cuP:FringeDenseLoad => emitln(s"dram_cmd_tp=dense_load")
          case cuP:FringeDenseStore => emitln(s"dram_cmd_tp=dense_store")
        }
        emitln(s"offset_trace = traces/${dataOf(writersOf(offset).head)}.trace")
        emitln(s"size_trace = traces/${dataOf(writersOf(size).head)}.trace")
        val par = ctrlOf(ctxEnOf(cuP).get).asInstanceOf[DramController].par
        emitln(s"out_token_size = ${par * bytePerWord}")
      case FringeStreamIn(streamIn, streamInDef) =>
        val counts:Long = countsOf.get(streamIn).flatten.getOrElse(-1)
        emitln(s"start_at_tokens = ${counts} # number of stream inputs")
      case FringeStreamOut(streamOut, processStreamOut) =>
        val counts:Long = countsOf.get(streamOut).flatten.getOrElse(-1)
        emitln(s"stop_after_tokens = ${counts} # number of stream outputs")
      case cuP:pir.node.ArgFringe =>
        ctrlOf(n) match {
          case _:ArgInController =>
            emitln(s"start_at_tokens = 1")
          case _:ArgOutController =>
            emitln(s"stop_after_tokens = 1")
        }
      case cuP =>
        emitln(s"lat = ${latencyOf(n).get}")
        val accums = loadAccessesOf(n).flatMap { read =>
          memsOf(read).head match {
            case mem if isAccum(mem) => Some((mem, constItersOf(read)))
            case mem => None
          }
        }
        assertOneOrLess(accums, s"accum").foreach { case (accum, scaleIn) =>
          //HACK: set token to 1 to allow accumulator start the first run
          if (inAccessesOf(accum).size <= 1) {
            emitln(s"start_at_tokens = $scaleIn # accum=$accum")
          }
        }
    }
  }

  def emitNodeBlock(n:Any)(block: => Unit) = dbgblk(s"emitNodeBlock($n)") {
    emitBlock(s"$n", b=NoneBraces)(block)
  }

  override def emitComment(msg:String) = emitln(s"# $msg")

  def emitNetwork = {
    if (isDynamic(topS)) {
      val networkParams = topParam match {
        case topParam:DynamicGridTopParam => topParam.networkParams
        case topParam:DynamicCMeshTopParam => topParam.networkParams
      }
      networkParams.foreach { networkParam =>
        val tp = networkParam.bct
        //val nr = numTotalRows
        //val nc = numTotalCols
        //val sq = math.max(nr, nc)+1
        val numVC = networkParam match {
          case networkParam:DynamicGridNetworkParam[_] => networkParam.numVirtualClasses
          case networkParam:DynamicCMeshNetworkParam[_] => networkParam.numVirtualClasses
        }
        val topo = networkParam match {
          case networkParam:DynamicGridNetworkParam[_] if !networkParam.isTorus => "mesh"
          case networkParam:DynamicGridNetworkParam[_] => "torus"
          case networkParam:DynamicCMeshNetworkParam[_] => "cmesh"
        }
        val sq = math.max(numRows, numCols)
        emitNodeBlock(s"net ${quote(tp)}net") {
          emitln(s"cfg = ${topo}_generic.cfg")
          emitln(s"dim[0] = $sq")
          emitln(s"dim[1] = $sq")
          //emitln(s"num_classes = ${networkParam.numVirtualClasses}")
          emitln(s"num_classes = 64")
          //emitln(s"num_classes = ${numVC}")
        }
      }
    }
  }

  def emitLink(n:Link) = dbgblk(s"emitLink(${quote(n)})") {
    val srcs = srcsOf(n)
    val dsts = dstsOf(n)

    val isStatic = topS match {
      case topS if isDynamic(topS) => isLocalLink(n)
      case topS => isStaticLink(n) // TODO: static place file break the simulator
    }

    val linkstr = if (isStatic) "" else "net"

    emitNodeBlock(s"${linkstr}link ${quote(n)}") {
      val tp = pinTypeOf(n)
      emitln(s"type = ${quote(tp)}")
      srcs.zipWithIndex.foreach { case (src,idx) =>
        emitln(s"src[$idx] = ${quote(src)}")
      }
      dsts.zipWithIndex.foreach { case (dst,idx) =>
        emitln(s"dst[$idx] = ${quote(dst)}")
      }
      if (isStatic) {
        n.foreach { mem =>
          val memSrcs = srcsOf(mem)
          val memDsts = dstsOf(mem)
          memSrcs.foreach { src =>
            val srcIdx = srcs.indexOf(src)
            val lat = staticLatencyOf(src, mem).get
            memDsts.foreach { dst =>
              val dstIdx = dsts.indexOf(dst)
              emitln(s"lat[$srcIdx, $dstIdx] = $lat") // Local latency is always 1
            }
          }
        }
      } else {
        //emitln(s"net = ${quote(tp)}net")
        //HACK: everything onto vecnet
        emitln(s"net = vecnet")
        // HACK: get global output of link
        val data = assertUnify(n.flatMap { mem =>
          inAccessesOf(mem).map {
            case Def(n, LocalStore(mems, addr, data:GlobalInput)) => goutOf(data).get
            case Def(n, LocalReset(mems, reset:GlobalInput)) => goutOf(reset).get
          }
        }, s"write data of $n") { d => d }
        val vc_id = data.id
        emitln(s"vc_id = $vc_id")
        val sids = srcs.map(src => globalOf(src).get.id)
        val dids = dsts.map(dst => globalOf(dst).get.id)
        sids.zipWithIndex.foreach { case (sid, idx) =>
          emitln(s"src_id[$idx] = $sid")
        }
        dids.zipWithIndex.foreach { case (did, idx) =>
          emitln(s"dst_id[$idx] = $did")
        }
      }
    }
  }

  def emitInLinks(n:NetworkNode) = dbgblk(s"emitInLinks($n)") {
    inlinksOf(n).zipWithIndex.foreach { case ((link, reads), idx) =>
      emitln(s"link_in[$idx] = ${quote(link)}")
      globalOf(n).get match {
        case cuP:DramFringe if PIRConfig.enableTrace =>
          emitln(s"scale_in[$idx] = 1")
        case cuP =>
          emitln(s"scale_in[$idx] = ${constItersOf(reads)}")
      }
      emitln(s"buffer[$idx]=${bufferSizeOf(reads).get}")
    }
  }

  def emitOutLinks(n:NetworkNode) = dbgblk(s"emitOutLinks($n)") {
    outlinksOf(n).zipWithIndex.foreach { case ((link, writes), idx) =>
      emitln(s"link_out[$idx] = ${quote(link)}")
      globalOf(n).get match {
        case cuP:DramFringe if PIRConfig.enableTrace =>
          emitln(s"scale_out[$idx] = 1")
        case cuP =>
          emitln(s"scale_out[$idx] = ${constItersOf(writes)}")
      }
    }
  }

}
