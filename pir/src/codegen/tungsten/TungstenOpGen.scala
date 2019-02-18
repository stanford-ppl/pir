package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import spade.param._
import scala.collection.mutable

trait TungstenOpGen extends TungstenCodegen with TungstenCtxGen {

  override def quoteRef(n:Any):String = n match {
    case OutputField(ctrler:Controller, "done") => s"$ctrler->Done()"
    case OutputField(ctrler:Controller, "valid") => s"$ctrler->Valid()"
    case n => super.quoteRef(n)
  }

  override def emitNode(n:N) = n match {
    case n:Controller =>
      val tp = n match {
        case n:LoopController => "LoopController"
        case n => "UnitController"
      }
      emitNewMember(tp, n)
      genCtxInits {
        assertOneOrLess(n.childCtrlers, s"$n.childCtrlers").foreach { child =>
          emitln(s"""$n->SetChild($child);""");
        }
        emitln(s"controllers.push_back($n);");
        n.collectDown[Counter]().foreach { ctr =>
          emitln(s"$n->AddCounter(${ctr});")
        }
      }
      emitln(s"$n->SetEn(${n.en.qref} & ${n.parentEn.qref});")
      super.visitNode(n)
      if (n.isLeaf) {
        n.ctx.get.collectDown[Controller]().foreach { ctrler =>
          emitln(s"$ctrler->Eval();")
        }
        n.to[LoopController].foreach { ctrler =>
          val laneValids = ctrler.cchain.T.foldLeft(List[String]()) { 
            case (Nil, ctr) => List.tabulate(ctr.par) { i => s"$ctr->Valids()[$i]" }
            case (prev, ctr) => 
              prev.flatMap { valid => 
                List.tabulate(ctr.par) { i => s"$valid & $ctr->Valids()[$i]" }
              }
          }
          emitln(s"bool laneValids[] = {${laneValids.mkString(",")}};")
        }
      }

    case n:Counter if !n.isForever =>
      emitNewMember(s"Counter<${n.par}>", n)
      emitln(s"$n->setMin(${n.min.T.get});")
      emitln(s"$n->setStep(${n.step.T.get});")
      emitln(s"$n->setMax(${n.max.T.get});")
      emitln(s"$n->Eval();")

    case n@CounterIter(None) =>
      val ctr = n.counter.T
      emitln(s"auto $n = $ctr->Iters()${if (n.getVec==1) "[0]" else ""};")

    case n@CounterIter(Some(i)) =>
      val ctr = n.counter.T
      emitln(s"int $n = $ctr->Iters()[$i];")

    case n@CounterValid(None) =>
      val ctr = n.counter.T
      emitln(s"auto $n = $ctr->Valids()${if (n.getVec==1) "[0]" else ""};")

    case n@CounterValid(Some(i)) =>
      val ctr = n.counter.T
      emitln(s"bool $n = $ctr->Valids()[$i];")

    case n@Const(v:List[_]) => emitVec(n, v)

    case n@Const(v) => emitVec(n, List.fill(n.getVec)(v))

    case n@RegAccumOp(op) =>
      genCtxFields {
        emitln(s"${n.tp} $n = 0;")
      }
      val accumOp = op match {
        case "AccumAdd" => s"FixAdd"
        case "AccumMul" => s"FixMul"
        case "AccumMax" => s"FixMax"
        case "AccumMin" => s"FixMin"
        case "AccumFMA" => s"FixFMA"
        //case "AccumUnk" => s"" //TODO
      }
      val in = n.in.T
      val firstVec = n.first.T.getVec
      val ctrler = n.ctx.get.ctrler(n.getCtrl)
      emitIf(s"${n.en.qref}") {
        emitBlock(s"for (int i = 0; i < ${firstVec}; i++)") {
          emitIf(s"laneValids[i]") {
            emitln(s"$n = (${n.first.T.qref("i")}) ? ${in.qref("i")} : $accumOp($n, ${in.qref("i")});")
          }
        }
      }

    case n:Shuffle =>
      val from = n.from.T
      val base = n.base.T
      val to = n.to.T
      emitln(s"${from.tp} ${n}_vfrom[] = ${if (from.getVec==1) s"{$from}" else from};")
      emitln(s"${to.tp} ${n}_vto[] = ${if (to.getVec==1) s"{$to}" else to};")
      emitln(s"${base.tp} ${n}_vbase[] = ${if (base.getVec==1) s"{$base}" else base};")
      (0 until n.getVec).foreach { i =>
        emitln(s"int ${n}_idx_$i = find<${from.tp}, ${from.getVec}>(${n}_vfrom, ${n}_vto[$i]);")
        emitln(s"auto ${n}_$i = (${n}_idx_$i < 0) ? INVALID : ${n}_vbase[${n}_idx_$i];")
      }
      emitVec(n, List.tabulate(n.getVec) { i => s"${n}_$i"} )

    case n@OpDef(FixToFix) =>
      val inputs = n.input.T
      emitVec(n) {
        s"(${n.tp}) ${inputs.head.qref("i")}" 
      }

    case n:OpDef =>
      val inputs = n.input.T
      emitVec(n) {
        s"${n.op}(${inputs.map(in => s"${in.qref("i")}").mkString(",")})" 
      }

    case n:TokenRead =>
      emitln(s"// TokenRead $n")

    case n:HostRead =>
      n.input.T.as[BufferRead].sname.v.foreach { sname =>
        emitln(s"${sname} = ${n.input.T};")
      }

    case n:HostWrite =>
      emitln(s"${n.tp} $n;")
      emitln(s"""std::ifstream stream_$n("${n.sid}.txt");""")
      emitln(s"""stream_$n << $n;""")
      emitln(s"""stream_$n.close();""")
      emitln(s"""cout << "Set ArgIn ${n.sid} " << ${n} << endl;""")

    case n:DRAMAddr =>
      emitln(s"auto $n = (uint64_t) ${n.dram.sname.get};")

    case n => super.emitNode(n)
  }

}
