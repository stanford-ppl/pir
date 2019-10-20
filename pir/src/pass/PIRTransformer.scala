package pir

import prism.graph._
import pir.node._
import pir.pass._
//import pir.mapper._
import scala.collection.mutable

trait PIRTransformer extends PIRPass with Transformer 
with GarbageCollector
with RewriteUtil
with BufferAnalyzer
{
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

  override def mirrorN(
    n:IR, 
    margs:Seq[Any]
  ):IR = {
    val m = super.mirrorN(n, margs)
    m.vecMeta.reset
    m.to[ND].foreach { m =>
      m.localEdges.foreach { e =>
        if (e.isStatic) e.vecMeta.reset
      }
    }
    m
  }

  def stage[T<:PIRNode](n:T):T = dbgblk(s"stage($n)"){
    n.localIns.foreach { in => 
      withLogger(this) {
        in.inferVec
        in.inferTp
      }
    }
    n.localOuts.foreach { out => 
      withLogger(this) {
        out.inferTp
        out.inferVec
      }
    }
    dbgn(n)
    n
  }

  def stage(out:Output[PIRNode]):Output[PIRNode] = {
    stage(out.src)
    withGC(false) {
      rewriteRules.foldLeft(out) { (out, rule) => rule.apply(out).as[Output[PIRNode]] }
    }
  }

  def transferLocalAccess(from:LocalAccess, to:LocalAccess) = {
    from.mirrorMergeMetas(to) {
      case ("name", v1, v2) => s"$v1/$v2"
      case ("sname",v1,v2) => s"$v1/$v2"
      case ("srcCtx", v1, v2) => s"$v1,$v2"
      case ("order", v1, v2) => v2
      case ("progorder",v1,v2) => v2
      case ("ctrl",v1,v2) => v2
      case ("dims",v1,v2) => v2
      case ("depth",v1,v2) => v2
      case ("castgroup",v1,v2) => v2
      case ("muxport",v1,v2) => v2
    }
    from.presetVec.v.foreach { v => to.presetVec(v) } // Most before swapConncetion
    to.en(from.en.connected)
  }

  def mirrorSyncMeta(from:PIRNode, to:PIRNode) = {
    to.waitFors.mirror(from.waitFors)
    to.barrier.mirror(from.barrier)
  }

}

