package pir
package codegen

import pir.node._
import pir.mapper._
import spade.param._
import prism.codegen._
import prism.graph._

class ProgramReport(val fileName:String)(implicit design:PIR) extends Report with PIRTraversal with JsonCodegen with ChildFirstTraversal with UnitTraversal {

  override def runPass = {
    val globals = pirTop.collectChildren[GlobalContainer]
    emitb {
      reportAlloc(globals)
      //reportOps(globals)
      emitkb("IR") {
        traverseTop
      }
    }
  }

  def reportAlloc(globals:Iterable[GlobalContainer]) = {
    sinfo(s"Allocation: ")
    val (dags, rest1) = globals.partition { _.isDAG.get }
    sinfo(s"DAGs: ${dags.size}")
    emitkv(s"DAG", dags.size)
    rest1.groupBy { _.getClass }.foreach {
      case (cl, globals) if cl == classOf[ComputeContainer]=>
        sinfo(s"PCUs: ${globals.size}")
        emitkv(s"PCU", globals.size)
      case (cl, globals) if cl == classOf[MemoryContainer]=>
        sinfo(s"PMUs: ${globals.size}")
        emitkv(s"PMU", globals.size)
      case (cl, globals) if cl == classOf[ArgFringe] =>
        sinfo(s"ArgFringe: ${globals.size}")
        emitkv(s"AFG", globals.size)
      case (cl, globals) if cl == classOf[DRAMFringe] =>
        sinfo(s"MCs: ${globals.size}")
        emitkv(s"MC", globals.size)
      case (cl, globals) if cl == classOf[BlackBoxContainer] =>
        sinfo(s"BBs: ${globals.size}")
        emitkv(s"BB", globals.size)
    }
  }

  private def emitMetadata(n:PIRNode) = {
    n.metadata.values.foreach { metadata =>
      metadata.v.foreach { v =>
        v match {
          case v:List[_] =>
            emitkv(metadata.name, v)
          case _ =>
            emitkv(metadata.name, v)
        }
      }
    }
  }

  override def emitNode(n:N):Unit = n match {
    case n:GlobalContainer =>
      emitkb(s"$n"){
        emitMetadata(n)
        emitkv("children", n.children)
      }
      visitNode(n)
    case n:Context =>
      emitkb(s"$n"){
        emitMetadata(n)
        val ctrlers = n.collectDown[Controller]()
        emitkv("parent", n.parent.get)
        emitkv("ctrlers", ctrlers)
      }
      visitNode(n)
    case n:Controller =>
      emitkb(s"$n"){
        emitMetadata(n)
        emitkv("parent", n.ctx.get)
      }
      visitNode(n)
    case n:GlobalInput =>
      emitkb(s"$n"){
        emitMetadata(n)
        emitkv("parent", n.parent.get)
        emitkv(s"inputs", List(n.in.T))
        emitkv(s"outputs", n.out.T)
      }
      visitNode(n)
    case n:GlobalOutput =>
      emitkb(s"$n"){
        emitMetadata(n)
        emitkv("parent", n.parent.get)
        emitkv(s"outputs", n.out.T)
      }
      visitNode(n)
    case n:LocalInAccess =>
      emitkb(s"$n"){
        emitMetadata(n)
        emitkv("parent", n.ctx.get)
      }
      visitNode(n)
    case n:LocalOutAccess =>
      emitkb(s"$n"){
        emitMetadata(n)
        emitkv("parent", n.ctx.get)
        emitkv("inputs", List(n.in.T))
      }
      visitNode(n)
    case n:Memory =>
      emitkb(s"$n"){
        emitMetadata(n)
        emitkv("parent", n.parent.get)
        emitkv("contexts", n.collectPeer[Context]())
      }
      visitNode(n)
    case n =>
      visitNode(n)
  }

  def getOp(n:OpNode) = n match {
    case n:OpDef => Some(n.op)
    case n:RegAccumOp => Some(n.op)
    case _ => None
  }

  def reportOps(globals:Iterable[GlobalContainer]) = {
    emitkb("IR") {
      globals.foreach { global =>
        emitkb(global) {
          val ctxs = global.collectChildren[Context]
          ctxs.foreach { ctx =>
            val ops = ctx.collectDown[OpNode]().view.map { 
              case n@OpDef(op) => (n,op)
              case n@RegAccumOp(List(op),_) => (n,s"RegAccum-${op.as[OpDef].op}")
              case n@RegAccumFMA(_) => (n,s"RegAccumFMA")
              case n => (n,n)
            }.map { case (n,op) => 
              val vec = n match {
                case n@RegAccumOp(_,_) => n.in.getVec
                case n@RegAccumFMA(_) => math.max(n.in1.T.getVec, n.in2.T.getVec)
                case _ => n.getVec
              }
              s"${vec}|$op|${n.srcCtx.get.headOption.getOrElse("No source context")}"
            }
            emitkv(ctx, ops)
          }
        }
      }
    }
  }

}


