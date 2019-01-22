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
      emitVisitNode(n.valid)
      emitln(s"""bool ${n}_done = 1;""")
      emitVisitNode(n.done)

    case n:HostOutController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      emitVisitNode(n.valid)
      emitln(s"""bool ${n}_done = 1;""")
      emitVisitNode(n.done)

    case n:UnitController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      emitVisitNode(n.valid)
      emitln(s"""bool ${n}_done = ${n.valid.T};""")
      emitVisitNode(n.done)

    case n:LoopController =>
      emitEn(n.en)
      emitEn(n.parentEn)
      emitVisitNode(n.valid)
      val cchain = n.cchain.T
      cchain.foreach(emitVisitNode)
      markVisited(cchain)
      emitln(s"if (${n}_en) ${cchain.last}->Inc();")
      cchain.sliding(2, 1).foreach {
        case List(outer, inner) =>
          emitln(s"if ($inner->Done()) $outer->Inc();")
        case _ =>
      }
      emitln(s"""bool ${n}_done = (bool) ${cchain.head}->Done();""")
      visitNode(n)

    case n:ControllerDone =>
      val ctrler = n.collectUp[Controller]().head
      emitln(s"bool $n = ${ctrler}_done;")

    case n:ControllerValid =>
      val ctrler = n.collectUp[Controller]().head
      emitln(s"bool $n = ${ctrler}_en & ${ctrler}_parentEn;")

    case n:Counter =>
      genFields {
        emitln(s"""Counter<${n.par}> *$n = new Counter<${n.par}>("$n");""")
      }
      emitln(s"$n->min=${n.min.T};")
      emitln(s"$n->step=${n.step.T};")
      emitln(s"$n->max=${n.max.T};")

    case n@CounterIter(None) =>
      val ctr = n.counter.T
      emitln(s"std::array<int, ${ctr.getVec}> $n = $ctr->iters();")

    case n@CounterIter(Some(i)) =>
      val ctr = n.counter.T
      emitln(s"int $n = $ctr->iters()[$i];")

    case n@CounterValid(None) =>
      val ctr = n.counter.T
      emitln(s"std::array<int, ${ctr.getVec}> $n = $ctr->valids();")

    case n@CounterValid(Some(i)) =>
      val ctr = n.counter.T
      emitln(s"bool $n = $ctr->valids()[$i];")

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
      val in = n.in.T
      emitBlock(s"for (int i = 0; i < ${in.getVec}; i++)") {
        emitln(s"$n = $accumOp($n, ${quoteRef(in).cast("float")})")
      }
      val firstVec = n.first.T.getVec
      emitIf(s"!${n.first.T}${if (firstVec > 1) "[0]" else ""} & ${n}_en") {
        emitln(s"$n = $accumOp($n, $accum)")
      }

    case n:OpDef =>
      val inputs = n.input.T
      emitVec(n) {
        s"${n.op}(${inputs.map(in => s"${quoteRef(in).cast("float")}").mkString(",")});" 
      }

    case n:TokenRead =>
      emitln(s"// TokenRead $n")

    case n:HostRead =>
      emitln(s"""std::ofstream stream_$n("${n.sid}.txt");""")
      emitln(s"""stream_$n << ${n.input.T};""")
      emitln(s"""stream_$n.close();""")

    case n:HostWrite =>
      emitln(s"float $n;")
      emitln(s"""std::ifstream stream_$n("${n.sid}.txt");""")
      emitln(s"""stream_$n >> $n;""")
      emitln(s"""stream_$n.close();""")

    case n:DRAMAddr =>
      emitln(s"long $n;")
      emitln(s"""std::ifstream stream_$n("${n.dram.sid}_addr.txt");""")
      emitln(s"""stream_$n >> $n;""")
      emitln(s"""stream_$n.close();""")

    case n => super.emitNode(n)
  }

}
