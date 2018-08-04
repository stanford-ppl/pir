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
    emitMemoryController
  }

  override def finPass = {
    super.finPass
    zipOption(PLASTISIM_HOME, psimOut).foreach { case (psimHome, psimOut) =>
      shell(s"ln -f $psimHome/configs/mesh_generic.cfg $psimOut/")
    }
    runPsim
  }

  lazy val nameMap = reverseOneToOneMap(designP.top.collectDown[NetworkNode]().map {
    node => node -> s"${quote(node)}"
  }.toMap)

  var simulationSucceeded:Option[Boolean] = None
  def processOutput(line:String) = {
    if (line.contains("Total DRAM")) {
      info(Console.GREEN, s"psim", line)
    }
    if (line.contains("Simulation complete at cycle")) {
      if (simulationSucceeded.isEmpty) {
        info(Console.GREEN, s"psim", line)
        simulationSucceeded = Some(true)
      } else {
        info(Console.RED, s"psim", line)
      }
    }
    if (line.contains("DEADLOCK") || line.contains("TIMEOUT")) {
      info(Console.RED, s"psim", line)
      simulationSucceeded = Some(false)
    }
    if (line.contains("Total Active")) {
      val node = line.split(":")(0).trim
      nameMap.get(node).foreach { node =>
        activeOf(node) = line.split("Total Active:")(1).split("Total Output")(0).trim.toLong
        stalledOf(node) = line.split("Stalled:")(1).split("Starved")(0).trim.toFloat
        starvedOf(node) = line.split("Starved:")(1).split("Total Active")(0).trim.toFloat
        zipOption(countOf.getOrElse(node,None), activeOf.get(node)).foreach { case (count, active) =>
          val expectedCount = globalOf(node).get match {
            case cuP:FringeDenseLoad =>
              val par = ctrlOf(ctxEnOf(cuP).get).asInstanceOf[DramController].par
              count / (burstSize / par)
            case cuP => count
          }
          if (active < expectedCount) { 
            err(s"${quote(node)} count=$expectedCount active=$active", false)
            simulationSucceeded = Some(false)
          }
        }
      }
    }
  }

  def runPsim = {
    zipOption(PLASTISIM_HOME, psimOut).fold {
      warn(s"set PLASTISIM_HOME to launch plastiroute automatically!")
    } { case (psimHome, psimOut) =>
      val log = s"$dirName/psim.log"
      var command = s"$psimHome/plastisim -f $psimOut/config.psim -p $psimOut/final.place"
      command += getOption[Long]("psim-timeout").fold("") { t => s" -c $t" }
      command += (SpadeConfig.option[String]("link-prop") match {
        case "db" => s" -l B"
        case "cb" => s" -l C"
      })
      if (runPlastisim) {
        shellProcess(s"psim", command, log)(processOutput)
        if (!simulationSucceeded.getOrElse(false)) fail(s"Plastisim failed. details in $log")
      } else {
        info(s"To run simulation manually, use following command, or use --run-psim to launch simulation automatically")
        info(cstr(Console.YELLOW, command))
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
      case n:DramFringe if enableTrace => s"dramnode"
      case n => s"node"
    }
    emitNodeBlock(s"$nodeType ${quote(n)} # ${quote(cuP)}") {
      emitNodeSpecs(n)
      emitInLinks(n)
      emitOutLinks(n)
    }
  }

  lazy val bytePerWord = topParam.wordWidth / 8

  def emitNodeSpecs(n:NetworkNode) = {
    val cuP = globalOf(n).get
    cuP match {
      case cuP:DramFringe if isDenseFringe(cuP) & enableTrace =>
        val offset = cuP.collectDown[StreamOut]().filter { _.field == "offset" }.head
        val size = cuP.collectDown[StreamOut]().filter { _.field == "size" }.head
        emitln(s"offset_trace = traces/${readersOf(offset).head}.trace")
        emitln(s"size_trace = traces/${readersOf(size).head}.trace")
        val par = ctrlOf(ctxEnOf(cuP).get).asInstanceOf[DramController].par
        cuP match {
          case cuP:FringeDenseLoad => 
            emitln(s"dram_cmd_tp=dense_load")
            emitln(s"out_token_size = ${par * bytePerWord}")
          case cuP:FringeDenseStore => 
            emitln(s"dram_cmd_tp=dense_store")
            emitln(s"in_token_size = ${par * bytePerWord}")
        }
        emitln(s"controller=DRAM")
      case cuP:DramFringe if isSparseFringe(cuP) & enableTrace =>
        val addr = cuP.collectDown[StreamOut]().filter { _.field == "addr" }.head
        val par = getParOf(writersOf(addr).head)
        emitln(s"offset_trace = traces/${readersOf(addr).head}.trace")
        emitln(s"size_trace = ${par * bytePerWord}") // burst size (byte)
        emitln(s"burst_size = $bytePerWord")
        cuP match {
          case cuP:FringeSparseLoad => 
            emitln(s"dram_cmd_tp=dense_load")
            emitln(s"out_token_size = ${par * bytePerWord}")
          case cuP:FringeSparseStore => 
            emitln(s"dram_cmd_tp=dense_store")
            emitln(s"in_token_size = ${par * bytePerWord}")
        }
        emitln(s"controller=DRAM")
        //TODO: for scatther this is not called addr
      case cuP =>
        emitln(s"lat = ${latencyOf(n).get}")
    }
    startAtToken(n).foreach { token => emitln(s"start_at_tokens = $token") }
    stopAfterToken(n).foreach { token => emitln(s"stop_after_tokens = $token") }
    countOf(n).fold {
      emitln(s"# count not exists")
    } { c =>
      emitln(s"count = $c")
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
        val numVC = networkParam match {
          case networkParam:DynamicGridNetworkParam[_] => networkParam.numVirtualClasses
          case networkParam:DynamicCMeshNetworkParam[_] => networkParam.numVirtualClasses
        }
        val topo = networkParam match {
          case networkParam:DynamicGridNetworkParam[_] if !networkParam.isTorus => "mesh"
          case networkParam:DynamicGridNetworkParam[_] => "torus"
          case networkParam:DynamicCMeshNetworkParam[_] => "cmesh"
        }
        if (quote(tp) == "vec") {
          emitNodeBlock(s"net ${quote(tp)}net") {
            emitln(s"cfg = ${topo}_generic.cfg")
            emitln(s"dim[0] = $maxDim")
            emitln(s"dim[1] = $maxDim")
            emitln(s"num_classes = ${numVC}")
          }
        }
      }
    }
  }

  def emitMemoryController = {
    if (enableTrace) {
      val psimHome = PLASTISIM_HOME.getOrElse(throw PIRException(s"PLASTISIM_HOME need to set to find ini files"))
      emitNodeBlock(s"mc DRAM") {
        emitln(s"memfile = $psimHome/configs/DDR3_micron_64M_8B_x4_sg15.ini")
        emitln(s"sysfile = $psimHome/configs/system.ini")
      }
    }
  }
  def emitLink(n:Link) = dbgblk(s"emitLink(${quote(n)})") {
    val srcMap = srcsOf(n)
    val dstMap = dstsOf(n)
    val srcs:List[NetworkNode] = srcMap.values.flatMap { _.keys }.toSet.toList
    val dsts:List[NetworkNode] = dstMap.values.flatMap { _.keys }.toSet.toList

    val isStatic = topS match {
      case topS if isDynamic(topS) => isLocalLink(n)
      //case topS => isStaticLink(n) // TODO: static place file break the simulator
      case topS => isLocalLink(n)
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
      assertOptionUnify(n, s"count") { m => countOf.getOrElse(m, None) }.fold {
        emitln(s"# count doen't exist")
      } { c =>
        emitln(s"count = $c")
      }
      if (isStatic) {
        n.foreach { mem =>
          srcMap(mem).foreach { case (src, ias) =>
            val srcIdx = srcs.indexOf(src)
            val lat = staticLatencyOf(src, mem).get
            dstMap(mem).foreach { case (dst, oas) =>
              val dstIdx = dsts.indexOf(dst)
              emitln(s"lat[$srcIdx, $dstIdx] = $lat") // Local latency is always 1
            }
          }
        }
      } else {
        //emitln(s"net = ${quote(tp)}net")
        //HACK: everything onto vecnet
        if (isDynamic(designS)) {
          emitln(s"net = vecnet")
        }
        val vc_id = goutOf(n).get.id
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

  /*
   * Load
   * for (numCommands) {
   *   Pipe {
   *     // enqueue offset
   *     // enqueue size
   *   }
   *   Pipe {
   *     // read offset
   *     // read size
   *     for (size by burstSize) { // consume token here
   *       // handle burst command
   *       for (burstSize par readPar) { // modeled here
   *         // send data
   *       }
   *     }
   *   }
   * }
   * */

  /*
   * Store
   * for (numCommands) {
   *   Pipe {
   *     // enqueue offset
   *     // enqueue size
   *     for (size par writePar) {
   *       // enqueue data
   *     }
   *   }
   *   Pipe {
   *     // read offset
   *     // read size
   *     for (size by burstSize) {
   *       for (burstSize par writePar) {
   *         // read data // modeled here, consume token here
   *       }
   *       // handle burst command
   *     }
   *   }
   * }
   * */
  def emitInLinks(n:NetworkNode) = dbgblk(s"emitInLinks($n)") {
    inlinksOf(n).zipWithIndex.foreach { case ((link, reads), idx) =>
      emitln(s"link_in[$idx] = ${quote(link)}")
      globalOf(n).get match {
        case cuP:FringeDenseLoad if enableTrace =>
          // HACK: original scale in = size / read par
          // we want size / burstSize
          val par = ctrlOf(ctxEnOf(cuP).get).asInstanceOf[DramController].par
          emitln(s"scale_in[$idx] = ${constScaleOf(reads) * par / burstSize}")
        case cuP:FringeDenseStore if enableTrace =>
          // scale_in for data is 1
          // scale_in for offset and size are size / burstSize * burstSize / writePar = size /
          // writePar
          emitln(s"scale_in[$idx] = ${constScaleOf(reads)}")
        case cuP =>
          emitln(s"scale_in[$idx] = ${constScaleOf(reads)}")
      }
      emitln(s"buffer[$idx]=${assertOptionUnify(link, "bufferSize")(bufferSizeOf).get}")
    }
  }

  def emitOutLinks(n:NetworkNode) = dbgblk(s"emitOutLinks($n)") {
    outlinksOf(n).zipWithIndex.foreach { case ((link, writes), idx) =>
      emitln(s"link_out[$idx] = ${quote(link)}")
      //globalOf(n).get match {
        //case cuP:FringeDenseLoad if enableTrace =>
          //emitln(s"scale_out[$idx] = 1")
        //case cuP =>
          emitln(s"scale_out[$idx] = ${constScaleOf(writes)}")
      //}
    }
  }

}
