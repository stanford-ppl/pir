package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenOpGen extends TungstenCodegen with TungstenCtxGen {

  override def emitNode(n:N) = n match {
    case n:Controller =>
      val tp = n match {
        case n:LoopController => "LoopController"
        case n => "UnitController"
      }
      genCtxFields {
        emitln(s"""$tp* $n = new $tp("$n"); // ${n.getCtrl}""")
      }
      genCtxInits {
        assertOneOrLess(n.childCtrlers, s"$n.childCtrlers").foreach { child =>
          emitln(s"""$n->SetChild($child);""");
        }
        emitln(s"AddChild($n);");
        emitln(s"controllers.push_back($n);");
        n.collectDown[Counter]().foreach { ctr =>
          emitln(s"$n->AddCounter(${ctr});")
        }
      }
      emitln(s"$n->SetEn(${quoteEn(n.en)} & ${quoteEn(n.parentEn)});")
      super.visitNode(n)
      if (n.isLeaf) {
        n.ctx.get.collectDown[Controller]().foreach { ctrler =>
          emitln(s"$ctrler->Eval();")
          emitln(s"bool ${ctrler.done.T} = ${ctrler}->Done();")
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

    case n:ControllerDone =>
      //val ctrler = n.collectUp[Controller]().head
      //emitln(s"bool $n = ${ctrler}.Done();")
      super.visitNode(n)

    case n:ControllerValid =>
      emitln(s"bool $n = ${n.ctrler}->Valid();")

    case n:Counter if !n.isForever =>
      genCtxFields {
        emitln(s"""Counter<${n.par}>* $n = new Counter<${n.par}>("$n");""")
      }
      genCtxInits {
        emitln(s"AddChild($n);");
      }
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

    case n@Const(v) =>
      emitln(s"auto $n =  $v;")

    case n@RegAccumOp(op) =>
      genCtxFields {
        emitln(s"int $n = 0;")
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
      val ctrler = assertOne(n.collectPeer[LoopController]().filter { _.getCtrl == n.getCtrl }, s"$n.ctrler")
      emitIf(s"${quoteEn(n.en)} & ${ctrler.valid.T}") {
        emitBlock(s"for (int i = 0; i < ${firstVec}; i++)") {
          emitIf(s"laneValids[i]") {
            emitln(s"$n = (${n.first.T.qref}) ? ${in.qref} : $accumOp($n, ${in.qref});")
          }
        }
      }

    case n:OpDef =>
      val inputs = n.input.T
      emitVec(n) {
        s"${n.op}(${inputs.map(in => s"${quoteRef(in)}").mkString(",")});" 
      }

    case n:TokenRead =>
      emitln(s"// TokenRead $n")

    case n:HostRead =>
      n.input.T.as[BufferRead].sname.v.foreach { sname =>
        emitln(s"${sname} = ${n.input.T};")
      }

    case n:HostWrite =>
      emitln(s"int $n;")
      emitln(s"""std::ifstream stream_$n("${n.sid}.txt");""")
      emitln(s"""stream_$n << $n;""")
      emitln(s"""stream_$n.close();""")
      emitln(s"""cout << "Set ArgIn ${n.sid} " << ${n} << endl;""")

    case n:DRAMAddr =>
      emitln(s"long $n;")
      emitln(s"""std::ifstream stream_$n("${n.dram.sid}_addr.txt");""")
      emitln(s"""stream_$n << $n;""")
      emitln(s"""stream_$n.close();""")
      emitln(s"""cout << "Set DRAMAddr ${n.dram.sid} " << ${n} << endl;""")

    case n => super.emitNode(n)
  }

}
