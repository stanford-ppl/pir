package pir

import prism.graph._
import pir.node._
import pir.pass._
import pir.codegen._
import scala.collection.mutable

trait PIRTransformer extends PIRPass with Transformer 
with GarbageCollector
with RewriteUtil
with BufferAnalyzer
{ self =>
  protected def inProgramStaging = false

  override def finPass = {
    super.finPass
    if (config.debug) {
      val irprinter = new PIRIRPrinter(runner.logFile.replace(".log",".ir")) {
        override def dirName = config.logDir
        override lazy val logger = self.logger
      }
      irprinter.run
    }
  }

  override def mirrorField[N<:Node[N]](
    nodes:Iterable[FieldNode[N]], 
    mapping:mutable.Map[IR,IR]
  ) = {
    val orig = mapping.values.toList
    super.mirrorField(nodes, mapping)
    val newnodes = mapping.values.toList diff orig
    newnodes.foreach { case n:PIRNode => stage(n.as[PIRNode]); case _ => }
  }

  override def swapConnection[N<:Node[N]](input:Input[N], from:Output[N], to:Output[N]):Unit = {
    input.vecMeta.reset
    to.vecMeta.reset
    super.swapConnection(input, from, to)
    to.inferVec
    input.inferVec
    withLive(to.src) {
      free(from.src.as[PIRNode])
    }
  }

  override def swapOutput[N<:Node[N]](from:Output[N], to:Output[N]) = {
    super.swapOutput(from, to)
    withLive(to.src) {
      free(from.src.as[PIRNode])
    }
  }

  override def swapInput[N<:Node[N]](node:Node[N], from:Output[N], to:Output[N]):Unit = {
    super.swapInput(node, from, to)
    withLive(to.src) {
      free(from.src.as[PIRNode])
    }
  }

  override def swapInput[N<:Node[N]](node:Node[N], from:Node[N], to:Output[N]):Unit = {
    super.swapInput(node, from, to)
    withLive(to.src) {
      free(from.as[PIRNode])
    }
  }

  def stage[T<:PIRNode](n:T)(implicit file:sourcecode.File, line: sourcecode.Line):T = {
    n.localIns.foreach { in => 
      //withLogger(this) {
        in.inferVec
        in.inferTp
      //}
    }
    n.localOuts.foreach { out => 
      //withLogger(this) {
        out.inferTp
        out.inferVec
      //}
    }
    //dbgn(n)
    if (!inProgramStaging) {
      n.setSrcCtx
    }
    dbg(s"Stage ${dquote(n)}")
    n
  }

  def stage(out:Output[PIRNode])(implicit file:sourcecode.File, line: sourcecode.Line):Output[PIRNode] = {
    stage(out.src)
    withGC(false) {
      rewriteRules.foldLeft(out) { (out, rule) => rule.apply(out).as[Output[PIRNode]] }
    }
  }

  def transferLocalAccess(from:LocalAccess, to:LocalAccess) = {
    dbg(s"TransferLocalAccess($from, $to)")
    (from,to) match {
      case (from:BufferWrite,to:BufferWrite) => mirrorMetas(from.data, to.data)
      case (from:LocalOutAccess,to:LocalOutAccess) => mirrorMetas(from.in, to.in)
      case (from,to) =>
    }
    withMirrorRule {
      case (from,to,"name",Some(fvalue),Some(tvalue)) => Some(s"$fvalue/$tvalue")
      case (from,to,"sname",Some(fvalue),Some(tvalue)) => Some(s"$fvalue/$tvalue")
      case (from,to,"srcCtx",Some(fvalue),Some(tvalue)) => Some(fvalue.as[List[_]] ++ tvalue.as[List[_]])
      case (from,to,"order",Some(fvalue),Some(tvalue)) => Some(tvalue)
      case (from,to,"progorder",Some(fvalue),Some(tvalue)) => Some(tvalue)
      case (from,to,"ctrl",Some(fvalue),Some(tvalue)) => Some(tvalue)
      case (from,to,"dims",Some(fvalue),Some(tvalue)) => Some(tvalue)
      case (from,to,"depth",Some(fvalue),Some(tvalue)) => Some(tvalue)
      case (from,to,"castgroup",Some(fvalue),Some(tvalue)) => Some(tvalue)
      case (from,to,"controlShadowed",Some(fvalue),Some(tvalue)) => Some(tvalue)
      case (from,to,"muxport",Some(fvalue),Some(tvalue)) => Some(tvalue)
      case (from,to,"isExtern",Some(fvalue),Some(tvalue)) => Some(tvalue)
      case (from,to:BufferRead,"banks",Some(fvalue),Some(tvalue)) => Some(List(to.in.getVec))
      case (from,to,"retiming",Some(v1:Boolean),Some(v2:Boolean)) => Some(v1 || v2)
    } { mirrorMetas(from,to) }
    mirrorMetas(from.out, to.out)
  }

  def mirrorSyncMeta(from:PIRNode, to:PIRNode) = {
    to.waitFors.mirror(from.waitFors)
    to.barrier.mirror(from.barrier)
  }

}

