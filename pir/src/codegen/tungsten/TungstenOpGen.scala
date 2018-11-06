package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenOpGen extends TungstenCodegen {

  override def emitNode(n:N) = n match {
    case n:HostInController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      emitNode(n.valid)
      emitln(s"""float ${n}_done = 1;""")
      emitNode(n.done)

    case n:HostOutController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      emitNode(n.valid)
      emitln(s"""float ${n}_done = 1;""")
      emitNode(n.done)

    case n:UnitController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      emitNode(n.valid)
      emitln(s"""float ${n}_done = ${n.valid.T};""")
      emitNode(n.done)

    case n:LoopController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      emitNode(n.valid)
      val cchain = n.cchain.T
      cchain.foreach(emitNode)
      emitln(s"if (${n}_en) ${cchain.last}->Inc();")
      cchain.sliding(2, 1).foreach {
        case List(outer, inner) =>
          emitln(s"if ($inner->Done()) $outer->Inc();")
        case _ =>
      }
      emitln(s"""float ${n}_done = (float) ${cchain.head}->Done();""")
      visitNode(n)

    case n:ControllerDone =>
      val ctrler = n.collectUp[Controller]().head
      emitln(s"float $n = ${ctrler}_done;")

    case n:ControllerValid =>
      val ctrler = n.collectUp[Controller]().head
      emitln(s"float $n = ${ctrler}_en & ${ctrler}_parentEn;")

    case n:Counter =>
      genFields {
        emitln(s"""Counter<${n.par}> *$n = new Counter<${n.par}>("$n");""")
      }
      emitln(s"$n->min=${n.min.T};")
      emitln(s"$n->step=${n.step.T};")
      emitln(s"$n->max=${n.max.T};")

    case n@CounterIter(None) =>
      val ctr = n.counter.T
      emitln(s"std::array<float> $n = (float) $ctr->iters;")

    case n@CounterIter(Some(i)) =>
      val ctr = n.counter.T
      emitln(s"float $n = (float) $ctr->iters($i);")

    case n@CounterValid(None) =>
      val ctr = n.counter.T
      emitln(s"std::array<float> $n = (float) $ctr->valids;")

    case n@CounterValid(Some(i)) =>
      val ctr = n.counter.T
      emitln(s"float $n = (float) $ctr->valids($i);")

    case n@Const(v) =>
      emitln(s"float $n = (float) $v;")

    case n@RegAccumOp(op) =>
      emitEn(n.en)
      val accum = s"accum_${n}"
      emitln(s"float $n;")
      genFields {
        emitln(s"float $accum = 0;")
      }
      val accumOp = op match {
        case "AccumAdd" => s"FixAdd"
        case "AccumMul" => s"FixMul"
      }
      val firstVec = n.first.T.getVec
      emitIfElse(s"${n.first.T}${if (firstVec > 1) "[0]" else ""}") {
        emitln(s"$n = ${n.in.T.cast("float")};")
      } {
        emitIf(s"${n}_en") {
          val in = n.in.T
          emitBlock(s"for (int i = 0; i < ${in.getVec}; i++)") {
            emitln(s"$accum = $accumOp($accum, ${quoteRef(in).cast("float")})")
          }
        }
        emitln(s"$n = $accum;")
      }

    case n:OpDef =>
      val inputs = n.input.T
      emitVec(n) {
        s"${n.op}(${inputs.map(in => s"${quoteRef(in).cast("float")}").mkString(",")});" 
      }

    case n:TokenRead =>

    case n => super.emitNode(n)
  }

}
