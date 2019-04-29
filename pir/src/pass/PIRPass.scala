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
  with RuntimeAnalyzer
  //with prism.traversal.GraphUtil  
  //with spade.SpadeAlias 
  //with RoutingUtil 
  //with TypeUtil 
  //with MappingUtil
  //with MappingLogger
  {

  override def states = compiler.pirenv.states
  override def config:PIRConfig = compiler.config

  override def handle(e:Throwable) = {
    super.handle(e)
    if (config.enableDot && config.debug) {
      new PIRIRDotGen(s"top_err.dot").run
      new PIRCtxDotGen(s"ctx_err.dot").run
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
trait PIRTransformer extends PIRPass with Transformer {
  override def mirrorField[N<:Node[N]](
    nodes:Iterable[FieldNode[N]], 
    mapping:mutable.Map[ND,ND],
    mirrorN:(ND, Seq[Any]) => ND = (n:ND, margs:Seq[Any]) => this.mirrorN(n, margs)
  ) = {
    val orig = mapping.values.toList
    super.mirrorField(nodes, mapping, mirrorN)
    val newnodes = mapping.values.toList diff orig
    newnodes.foreach { n => stage(n.as[PIRNode]) }
  }
}
