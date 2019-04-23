package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import spade.param._
import scala.collection.mutable

trait TungstenOpGen extends TungstenCodegen with TungstenCtxGen {

  override def emitNode(n:N) = n match {

    case n:TokenRead =>
      emitln(s"// TokenRead $n")

    case n:HostRead =>
      n.input.T.as[BufferRead].sname.v.foreach { sname =>
        emitln(s"${sname} = ${n.input.T};")
      }

    case n:HostWrite =>
      emitln(s"auto& $n = ${n.sname.get};")

    case n@Const(v:List[_]) => emitVec(n, v)
    case n@Const(v:String) => emitVec(n, List.fill(n.getVec)(s""""${v.replace("\n", "\\n")}""""))
    case n@Const(v) => emitVec(n, List.fill(n.getVec)(v))

    case n@RegAccumOp(op) =>
      genCtxFields {
        emitln(s"${n.qtp} $n = 0;")
      }
      def accumOp(a:Any, b:Any) = op match {
        case "AccumAdd" => s"$a + $b"
        case "AccumMul" => s"$a * $b"
        case "AccumMax" => s"max($a,$b)"
        case "AccumMin" => s"min($a,$b)"
        //case "AccumFMA" => s"FixFMA"
        //case "AccumUnk" => s"" //TODO
      }
      val in = n.in.T
      val firstVec = n.first.T.getVec
      var ens = n.en.qref :: Nil
      n.ctx.get.ctrler(n.ctrl.get).foreach { ctrler => ens +:= ctrler.valid.qref }
      emitIf(s"${ens.distinct.reduce { _ + " && " + _ }}") {
        emitBlock(s"for (int i = 0; i < ${firstVec}; i++)") {
          emitIf(s"laneValids[i]") {
            emitln(s"$n = (${n.first.T.qidx("i")}) ? ${in.qidx("i")} : ${accumOp(n, in.qidx("i"))};")
          }
        }
      }

    case n@Shuffle(filled) =>
      emitToVec(n.from) { i => n.from.singleConnected.get.qidx(i) }
      emitToVec(n.to) { i => n.to.singleConnected.get.qidx(i) }
      emitToVec(n.base) { i => n.base.singleConnected.get.qidx(i) }
      emitln(s"${n.qtp} ${n}_shuffled[${n.getVec}];")
      filled match {
        case 0 => emitln(s"shuffleData<${n.qtp}, ${n.base.getVec}, ${n.to.getVec}>(${n.from.qref}, ${n.to.qref}, ${n.base.qref}, ${n}_shuffled);")
        case -1 => emitln(s"shuffleAddr<${n.base.getVec}, ${n.to.getVec}>(${n.from.qref}, ${n.to.qref}, ${n.base.qref}, ${n}_shuffled);")
        case false => emitln(s"shuffleData<${n.qtp}, ${n.base.getVec}, ${n.to.getVec}>(${n.from.qref}, ${n.to.qref}, ${n.base.qref}, ${n}_shuffled);")
      }
      emitUnVec(n)(s"${n}_shuffled")

    case n@OpDef(FixToFix) =>
      val inputs = n.input.T
      emitVec(n) { i =>
        s"(${n.qtp}) ${inputs.head.qidx(i)}" 
      }

    case n@OpDef(FixOr) if n.getTp.isFloat =>
      emitVec(n) { i =>
        val ins = n.input.T.map { _.qidx(i) }
        def a = ins(0)
        def b = ins(1)
        s"FloatOr($a,$b)"
      }

    case n:OpDef =>
      emitVec(n) { i =>
        val ins = n.input.connected.map { _.qidx(i) }
        def a = ins(0)
        def b = ins(1)
        def c = ins(2)
        n.op match {
          //case FixInv       => s"1/$a"  // TODO: same as Recip?
          case FixNeg       => s"- $a" 
          case FixAdd       => s"$a + $b" 
          case FixSub       => s"$a - $b" 
          case FixMul       => s"$a * $b" 
          case FixDiv       => s"$a / $b" 
          case FixRecip     => s"1 / $a"  
          case FixMod       => s"$a % $b" 
          case FixAnd       => s"$a & $b" 
          case FixOr        => s"$a | $b" 
          case FixLst       => s"$a < $b" 
          case FixLeq       => s"$a <= $b" 
          case FixXor       => s"$a ^ $b" 
          case FixSLA       => s"$a << $b" 
          case FixSRA       => s"$a >> $b" 
          //case FixSRU       => s"$a >> $b"  // TODO
          case SatAdd       => s"$a + $b" 
          case SatSub       => s"$a - $b" 
          case SatMul       => s"$a * $b" 
          case SatDiv       => s"$a / $b" 
          case UnbMul       => s"$a * $b" 
          case UnbDiv       => s"$a / $b" 
          case UnbSatMul    => s"$a * $b" 
          case UnbSatDiv    => s"$a / $b" 
          case FixNeq       => s"$a != $b" 
          case FixEql       => s"$a == $b" 
          case FixMax       => s"max($a,$b)" 
          case FixMin       => s"fmin($a,$b)" 
          case FixToFix     => s"(${n.qtp}) $a"
          case FixToFlt     => s"(${n.qtp}) $a"
          case FixToText     => s"to_string($a)"
          //case FixRandom    => s"-$a"
          case FixAbs       => s"abs($a)" 
          case FixFloor     => s"floor($a)" 
          case FixCeil      => s"ceil($a)" 
          case FixLn        => s"log($a)" 
          case FixExp       => s"exp($a)" 
          case FixSqrt      => s"sqrt($a)" 
          case FixSin       => s"sin($a)" 
          case FixCos       => s"cos($a)" 
          case FixTan       => s"tan($a)" 
          case FixSinh      => s"sinh($a)"
          case FixCosh      => s"sinh($a)"
          case FixTanh      => s"sinh($a)"
          case FixAsin      => s"asin($a)"
          case FixAcos      => s"acos($a)"
          case FixAtan      => s"atan($a)"
          case FixPow       => s"pow($a, $b)"
          case FixFMA       => s"$a * $b + $c" 
          //case FixRecipSqrt => s"-$a"
          case FltSigmoid => s"1.0 / (exp(-$a) + 1)"
          case SelectNonNeg        => s"($a<0) ? $b : $a" 
          //case FltIsPosInf  =>
          //case FltIsNegInf  =>
          //case FltIsNaN     =>
          case FltNeg       => s"-$a" 
          case FltAdd       => s"$a + $b" 
          case FltSub       => s"$a - $b" 
          case FltMul       => s"$a * $b" 
          case FltDiv       => s"$a / $b" 
          //case FltMod       => 
          case FltRecip     => s"1/$a" 
          case FltLst       => s"$a < $b" 
          case FltLeq       => s"$a <= $b" 
          case FltNeq       => s"$a != $b" 
          case FltEql       => s"$a == $b" 
          case FltMax       => s"fmax($a,$b)" 
          case FltMin       => s"fmin($a,$b)" 
          case FltToFlt     => s"(${n.qtp}) $a"
          case FltToFix     => s"(${n.qtp}) $a"
          //case TextToFlt    =>
          case FltToText    => s"to_string($a)"
          //case FltRandom    =>
          case FltAbs       => s"abs($a)" 
          case FltFloor     => s"$a.floor" 
          case FltCeil      => s"$a.ceil" 
          case FltLn        => s"log($a)" 
          case FltExp       => s"exp($a)" 
          case FltSqrt      => s"sqrt($a)" 
          case FltSin       => s"sin($a)" 
          case FltCos       => s"cos($a)" 
          case FltTan       => s"tan($a)" 
          case FltSinh      => s"sinh($a)"
          case FltCosh      => s"cosh($a)"
          case FltTanh      => s"tanh($a)"
          case FltAsin      => s"asin($a)"
          case FltAcos      => s"acos($a)"
          case FltAtan      => s"atan($a)"
          case FltPow       => s"pow($a, $b)"
          case FltFMA       => s"$a * $b + $c" 
          //case FltRecipSqrt =>
          //case FltSigmoid   =>

          case Not       => s"!$a" 
          case And       => s"$a & $b" 
          case Or        => s"$a | $b" 
          case Xor       => s"$a ^ $b" 
          case Xnor      => s"$a == $b" 
          //case BitRandom =>
          case BitToText => s"to_string($a)"

          case Mux => s"$a ? $b : $c"
          case TextConcat => ins.reduce[String] { case (a,b) => s"$a + $b" }
          case TextEql => s"$a == $b"
          case TextNeq => s"$a != $b"
          case TextLength => s"$a.size() / ${spadeParam.bytePerWord}"
          case TextApply => s"$a[$b]"
          //case CharArrayToText => 
          //case OneHotMux =>
          case op => throw PIRException(s"TODO: unsupported op $op")
        }
      }

    case n:PrintIf =>
      emitIf(s"${n.en.qref}") {
        emitBlock(s"for (int i = 0; i < ${n.msg.T.getVec}; i++)") {
          emitln(s"cout << ${n.msg.T.qidx("i")};")
        }
      }

    case n => super.emitNode(n)
  }

  implicit class TpOp(tp:BitType) {
    def isInt = tp match {
      case Fix(s, i,0) => true
      case _ => false
    }
    def isFloat = tp match {
      case Fix(s, i,f) if f != 0 => true
      case Flt(m,f) => true
      case _ => false
    }
  }

  override def quoteRef(n:Any):String = n match {
    case InputField(n:Shuffle, field) => s"${n}_$field"
    case n => super.quoteRef(n)
  }
}
