package pir

import prism.graph._
import pir.node._
import pir.util._
import pir.pass._
import pir.codegen._
//import pir.mapper._
import scala.collection.mutable

abstract class PIRPass(implicit override val compiler:PIR) extends Pass 
  with PIREnv 
  with PIRDebugger 
  with GraphUtilImplicits 
  with CollectorImplicit
  with TypeUtil
  with RuntimeUtil
  {

  override def states = compiler.pirenv.states
  override def config:PIRConfig = compiler.config

  override def handle(e:Throwable) = {
    if (config.enableDot) {
      tryDot(new PIRGlobalDotGen(s"global.dot"))
      tryDot(new PIRCtxDotGen(s"ctx.dot"))
    }
    if (config.enableVerboseDot)
      tryDot(new PIRIRDotGen(s"top.dot"))
    super.handle(e)
  }

  def tryDot(dotGen:PIRIRDotGen) = {
    try {
      dotGen.run
    } catch {
      case e:Exception =>
        bug[Unit](s"Fail to generate ${dotGen.fileName}", false)
    }
  }


  override def dquote(x:Any) = x match {
    case x:ControlTree if x.sname.nonEmpty => s"$x[${x.sname.get}]"
    case Const(v) => s"${super.dquote(x)}($v)"
    case OpDef(op) => s"${super.dquote(x)}($op)"
    case x:PIRNode if x.sname.nonEmpty => s"$x[${x.sname.get}]"
    case x:Edge[n,_,_] => s"${dquote(x.src)}.$x"
    case x => super.dquote(x)
  }
}
trait PIRTraversal extends PIRPass {
  type N = PIRNode
  def top = compiler.pirenv.pirTop
}
trait ControlTreeTraversal extends PIRPass {
  type N = ControlTree
  def top = compiler.pirenv.pirTop.topCtrl
}
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
    val tp = n.inferTp
    n.localIns.foreach { in => 
      in.inferVec
      in.inferTp
    }
    val vec = n.inferVec
    dbgn(n)
    n
  }

  def stage(out:Output[PIRNode]):Output[PIRNode] = {
    stage(out.src)
    withGC(false) {
      rewriteRules.foldLeft(out) { (out, rule) => rule.apply(out).as[Output[PIRNode]] }
    }
  }

}
