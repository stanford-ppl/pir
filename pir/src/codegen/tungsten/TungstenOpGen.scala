package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenOpGen extends TungstenCodegen {

  def quoteRef(n:PIRNode) = {
    if (n.getVec > 1) s"${n}[i]" else s"${n}"
  }


  def emitEn(en:Input with Field[_]):Unit = {
    val src = en.src
    val ens = en.neighbors
    val enName = s"${src}_${en.name}"
    emitln(s"float $enName = ${ens.map { _.toString}.foldLeft("1"){ case (prev,b) => s"$prev & $b" }};")
    en.src match {
      case n:BufferWrite =>
        val ctrler = assertOne(
          n.ctx.get.collectDown[Controller]().filter { _.ctrl.get == n.ctrl.get }, 
          s"$n.ctrler"
        )
        emitln(s"${enName} &= ${ctrler.valid.T};")
      case _ =>
    }
  }

  def emitVec(n:PIRNode)(rhs:Any) = {
    val vec = n.getVec
    if (vec > 1) {
      emitln(s"float $n[${vec}] = {};")
      emitBlock(s"for (int i = 0; i < ${vec}; i++)") {
        emitln(s"$n[i] = ${rhs}")
      }
    } else {
      emitln(s"float ${n} = ${rhs}")
    }
  }

  override def visitIn(n:N) = n match {
    case n:BufferRead => super.visitIn(n).filterNot{_.isInstanceOf[BufferWrite]}
    case n => super.visitIn(n)
  }

  override def visitOut(n:N) = n match {
    case n:BufferWrite => super.visitOut(n).filterNot{_.isInstanceOf[BufferRead]}
    case n => super.visitOut(n)
  }

  override def emitNode(n:N) = n match {
    case n:HostInController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      visitNode(n.valid)
      emitln(s"""float ${n}_done = 1;""")
      visitNode(n.done)

    case n:HostOutController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      visitNode(n.valid)
      emitln(s"""float ${n}_done = 1;""")
      visitNode(n.done)

    case n:UnitController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      visitNode(n.valid)
      emitln(s"""float ${n}_done = ${n.valid.T};""")
      visitNode(n.done)

    case n:LoopController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      visitNode(n.valid)
      val cchain = n.cchain.T
      cchain.foreach(visitNode)
      emitln(s"if (${n}_en) ${cchain.last}->Inc();")
      cchain.sliding(2, 1).foreach {
        case List(outer, inner) =>
          emitln(s"if ($inner->Done()) $outer->Inc();")
        case _ =>
      }
      emitln(s"""float ${n}_done = (float) ${cchain.head}->Done();""")
      visitNode(n.done)

    case n:ControllerDone =>
      val ctrler = n.collectUp[Controller]().head
      emitln(s"float $n = ${ctrler}_done;")

    case n:ControllerValid =>
      val ctrler = n.collectUp[Controller]().head
      emitln(s"float $n = ${ctrler}_en & ${ctrler}_parentEn;'")

    case n:Counter =>
      emitln(s"$n->min=${n.min.T};")
      emitln(s"$n->step=${n.step.T};")
      emitln(s"$n->max=${n.max.T};")

    case n@CounterIter(None) =>
      val ctr = n.counter.T
      emitln(s"std::vector<float>* $n = (float) $ctr->iters;")

    case n@CounterIter(Some(i)) =>
      val ctr = n.counter.T
      emitln(s"float $n = (float) $ctr->iters($i);")

    case n@CounterValid(None) =>
      val ctr = n.counter.T
      emitln(s"std::vector<float>* $n = (float) $ctr->valids;")

    case n@CounterValid(Some(i)) =>
      val ctr = n.counter.T
      emitln(s"float $n = (float) $ctr->valids($i);")

    case n@Const(v) =>
      emitln(s"float $n = (float) $v;")

    case n:BufferRead =>
      emitVec(n)(s"fifo_${n}->Read()${if (n.getVec==0) "[0]" else ""};")
      emitEn(n.en)
      emitln(s"if (${n}_en) $n->Pop();")

    case n:BufferWrite =>
      val data = n.data.T
      emitEn(n.en)
      emitBlock(s"if (${n}_en)") {
        emitln(s"Token ${n} = Token();")
        emitBlock(s"for (int i = 0; i < ${n.getVec}; i++)") {
          emitln(s"$n.floatVec_[i] = ${quoteRef(data).cast("float")}")
        }
        val (accumReads, otherReads) = n.out.T.partition { _.isPipeReg.get }
        accumReads.foreach { read =>
          emitln(s"fifo_$read.Push($n)")
        }
        if (otherReads.nonEmpty) {
          emitln(s"pipe_${n}.Push($n)")
        }
        emitln(s"")
      }

    case n@OpDef("RegAccumFMA") =>
      val inputs = n.input.T
      var instr = ""
      instr = inputs(0).cast("float[]")
      instr = inputs.tail.map { _.cast("float") }.mkString(",")
      emitln(s"float $n = RegAccumFMA($instr);")

    case n:OpDef =>
      val inputs = n.input.T
      emitVec(n) {
        s"${n.op}(${inputs.map(in => s"${quoteRef(in).cast("float")}").mkString(",")});" 
      }

    case n => super.emitNode(n)
  }
}
