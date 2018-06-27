package pir
package codegen

import pir.node._
import pir.pass._
import prism.util._

import scala.collection.mutable

class PlastisimTraceCodegen(implicit compiler:PIR) extends PlastisimCodegen with ScalaCodegen {
  import pirmeta._
  import spademeta._

  val fileName = s"gen_trace.scala"

  val offsetMap = mutable.Map[DRAM, Int]()

  val ctrlbmap = revertMap(ctrlOf.map.toMap)

  def setDramOffset = dbgblk(s"setDramOffset"){
    val drams = top.collectDown[DramFringe]().flatMap { _.dram }.toSet
    var offset = 0
    drams.foreach { dram =>
      offsetMap += dram -> offset
      emitComment(s"$dram -> $offset")
      val size = dram.dims.map { d => 
        getBoundAs[Int](d).getOrElse(throw PIRException(s"dram=$dram dimension unknown"))
      }.product[Int]
      offset += size
    }
  }

  override def runPass = {
    controllerTraversal.traverseTop
  }

  val schedular = new PIRTraversal with prism.traversal.BFSTopologicalTraversal with prism.traversal.GraphSchedular {
    override lazy val logger = PlastisimTraceCodegen.this.logger
    val forward = false
    override def visitIn(n:N):List[N] = n match {
      case n:CounterIter => Nil
      case n:Memory if boundOf.contains(n) => Nil
      case n => visitGlobalIn(n).filterNot { _.isInstanceOf[ControlNode] }
    }
    override def visitOut(n:N):List[N] = visitGlobalOut(n)
    override def isDepFree(n:N) = depFunc(n).exists(isVisited)
  }

  val controllerTraversal = new ControllerChildFirstTraversal with UnitTraversal {
    override lazy val logger = PlastisimTraceCodegen.this.logger
    override def visitNode(n:N, prev:T):T = {
      n match {
        case n:LoopController => 
          val cchain = ctrlbmap(n).collect { case cc:CounterChain => cc }.head
          val counters = cchain.counters
          def emitLoop(idx:Int):Unit = {
            if (idx >= counters.size) {
              super.visitNode(n, prev) 
            } else {
              val counter = counters(idx)
              val exps = schedular.scheduleNode(counter)
              exps.reverseIterator.foreach { exp =>
                emitNode(exp)
              }
              emitLambda(s"$counter.foreach", s"${n}_iter$idx") {
                emitLoop(idx+1)
              }
            }
          }
          emitLoop(0)
        case n =>
          emitComment(s"$n")
          emitBlock(s"") {
            super.visitNode(n, prev)
          }
      }
    }
  }

  override def emitNode(n:N) = n match {
    case Def(n, Counter(min, max, step, par)) =>
      emitln(s"val $n = Range($min, $max, $step * $par)")
    case GlobalInput(gout:GlobalOutput) =>
      emitln(s"val $n = $gout")
    case GlobalOutput(data, valid) =>
      emitln(s"val $n = $data")
    case n:Memory if boundOf.contains(n) => 
      emitln(s"val $n = ${boundOf(n)}")
    case WithWriter(writer) =>
      val data = dataOf(writer)
      emitln(s"val $n = $data")
    case DramAddress(dram) => 
      emitln(s"val $n = ${offsetMap(dram)}") 
    case n:LocalLoad if memsOf(n).size==1 =>
      val mem = memsOf(n).head
      emitln(s"val $n = $mem")
    case Def(n, CounterIter(counter, offset)) =>
      emitln(s"val $n = ${counter}_iter + ${offset.getOrElse(0)}")
    case Const(c) =>
      emitln(s"val $n = $c")
    case Def(n, OpDef(op, inputs)) =>
      emitln(s"val $n = eval($op, $inputs)")
    case n => super.emitNode(n)
  }

}
