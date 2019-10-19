package pir
package codegen

import pir.node._
import pir.mapper._
import spade.param._
import prism.codegen._
import prism.graph._

class ProgramReport(implicit design:PIR) extends Report with PIRTraversal with JsonCodegen with ChildFirstTraversal with UnitTraversal {

  val fileName = "program.json"
  override val denseFormat = false

  override def runPass = {
    val globals = pirTop.collectChildren[GlobalContainer]
    emitb {
      reportAlloc(globals)
      reportOps(globals)
    }
  }

  def reportAlloc(globals:Iterable[GlobalContainer]) = {
    sinfo(s"Allocation: ")
    val (dags, rest1) = globals.partition { _.isDAG.get }
    sinfo(s"DAGs: ${dags.size}")
    emitkv(s"DAG:", dags.size)
    rest1.groupBy { _.getClass }.foreach {
      case (cl, globals) if cl == classOf[ComputeContainer]=>
        sinfo(s"PCUs: ${globals.size}")
        emitkv(s"PCU:", globals.size)
      case (cl, globals) if cl == classOf[MemoryContainer]=>
        sinfo(s"PMUs: ${globals.size}")
        emitkv(s"PMU:", globals.size)
      case (cl, globals) if cl == classOf[ArgFringe] =>
        sinfo(s"ArgFringe: ${globals.size}")
        emitkv(s"AFG:", globals.size)
      case (cl, globals) if cl == classOf[DRAMFringe] =>
        sinfo(s"MCs: ${globals.size}")
        emitkv(s"MC:", globals.size)
      case (cl, globals) if cl == classOf[BlackBoxContainer] =>
        sinfo(s"BBs: ${globals.size}")
        emitkv(s"BB:", globals.size)
    }
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
                case n@RegAccumOp(_,_) => n.in.T.getVec
                case n@RegAccumFMA(_) => math.max(n.in1.T.getVec, n.in2.T.getVec)
                case _ => n.getVec
              }
              s"${vec}|$op|${n.srcCtx.v.getOrElse("")}"
            }
            emitkv(ctx, ops)
          }
        }
      }
    }
  }

  override def quote(n:Any):String = n match {
    case n:PIRNode => super.quote(s"$n")
    case n:Opcode => super.quote(s"$n")
    case _ => super.quote(n)
  }

}


