package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import spade.param._
import scala.collection.mutable

trait TungstenOpGen extends TungstenCodegen with TungstenCtxGen {

  override def emitNode(n:N) = n match {

    case n:HostRead =>
      n.sname.v.foreach { sname =>
        emitln(s"$sname = ${n.input.T};")
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
        case "AccumMax" => s"fmax($a,$b)"
        case "AccumMin" => s"fmin($a,$b)"
        //case "AccumFMA" => s"FixFMA"
        //case "AccumUnk" => s"" //TODO
        case List(op:OpDef) => 
          quoteOp(op.op, n.getTp, List(a.toString,b.toString), List(n.getTp, n.getTp), quoteSrcCtx(n))
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

    case n:OpDef => emitVec(n) { i => 
      val ins = n.input.connected.map { _.qidx(i) }
      quoteOp(n.op, n.getTp, ins, n.input.connected.map { _.getTp}, quoteSrcCtx(n))
    }

    case n:PrintIf =>
      var ens = n.en.qref :: Nil
      n.ctx.get.ctrler(n.ctrl.get).foreach { ctrler => ens +:= ctrler.valid.qref }
      emitIf(s"${ens.distinct.reduce { _ + " && " + _ }}") {
        emitBlock(s"for (int i = 0; i < ${n.msg.T.getVec}; i++)") {
          emitln(s"cout << ${n.msg.T.qidx("i")};")
        }
      }
      emitVec(n) { i => "true" }

    case n => super.emitNode(n)
  }

  def quoteOp(op:Opcode, ntp:BitType, ins:List[String], intps:List[BitType], ctx:String) = {
    def a = ins(0)
    def b = ins(1)
    def c = ins(2)
    def ta = intps(0)
    def tb = intps(1)
    def tc = intps(2)
    op match {
      //case FixInv                 => s"1/$a"  // TODO: same as Recip?
      case FixNeg                   => s"- $a"
      case FixAdd                   => s"$a + $b"
      case FixSub                   => s"$a - $b"
      case FixMul                   => s"$a * $b"
      case FixDiv                   => s"""SafeDiv($a, $b, "$ctx")"""
      case FixRecip                 => s"1 / $a"
      case FixMod if ntp.isFraction =>
        val Fix(s,i,f) = ntp
        s"(float) (((int) ($a * pow(2,$f))) % ((int) ($b * pow(2,$f)))) / pow(2,$f)"
      case FixMod                   => s"$a % $b"
      case FixAnd                   => s"$a & $b"
      case FixOr if ntp.isFraction => s"asT<float,int>(asT<int,float>($a) | asT<int,float>($b))"
      case FixOr                    => s"$a | $b"
      case FixLst                   => s"$a < $b"
      case FixLeq                   => s"$a <= $b"
      case FixXor                   => s"$a ^ $b"
      case FixSLA if ntp.isFraction => s"$a * pow(2,$b)"
      case FixSRA if ntp.isFraction => s"$a / pow(2,$b)"
      case FixSLA                   => s"$a << $b"
      case FixSRA                   => s"$a >> $b"
      case FixSRU                   => s"(${ta.qtp}) ((${ta.toUnsigned.qtp}) $a >> $b)"
      case SatAdd                   => s"$a + $b"
      case SatSub                   => s"$a - $b"
      case SatMul                   => s"$a * $b"
      case SatDiv                   => s"$a / $b"
      case UnbMul                   => s"$a * $b"
      case UnbDiv                   => s"$a / $b"
      case UnbSatMul                => s"$a * $b"
      case UnbSatDiv                => s"$a / $b"
      case FixNeq                   => s"$a != $b"
      case FixEql                   => s"$a == $b"
      case FixMax                   => s"fmax($a,$b)"
      case FixMin                   => s"fmin($a,$b)"
      case FixToFix                 => s"(${ntp.qtp}) $a"
      case FixToFixSat              => 
        val Fix(_,_,af) = ta
        val Fix(_,_,nf) = ntp
        var res = s"(${ntp.qtp}) fmax(fmin($a, ${ntp.fixTpMax}), ${ntp.fixTpMin})"
        if (nf < af)
          s"(${ntp.qtp}) ((int) ($res * pow(2,$af)) >> (${af - nf})) / pow(2,$nf)"
        res
      case FixToFlt                 => s"(${ntp.qtp}) $a"
      case FixToText                => s"to_string($a)"
      case FixRandom                => 
        val Fix(s,i,f) = ntp
        var max = Integer.parseInt("1" * (i+f-1),2).toString
        ins.headOption.foreach { m => max = s"min($max, (int) ($m * pow(2,$f)))"}
        var res = s"(rand() % $max)"
        if (f > 0) res = s"$res * 1.0 / pow(2,${f})"
        res
      case FixAbs                   => s"abs($a)"
      case FixFloor                 => s"floor($a)"
      case FixCeil                  => s"ceil($a)"
      case FixLn                    => s"log($a)"
      case FixExp                   => s"exp($a)"
      case FixSqrt                  => s"sqrt($a)"
      case FixSin                   => s"sin($a)"
      case FixCos                   => s"cos($a)"
      case FixTan                   => s"tan($a)"
      case FixSinh                  => s"sinh($a)"
      case FixCosh                  => s"sinh($a)"
      case FixTanh                  => s"sinh($a)"
      case FixAsin                  => s"asin($a)"
      case FixAcos                  => s"acos($a)"
      case FixAtan                  => s"atan($a)"
      case FixPow                   => s"pow($a, $b)"
      case FixFMA                   => s"$a * $b + $c"
      //case FixRecipSqrt           => s"-$a"
      case FltSigmoid               => s"1.0 / (exp(-$a) + 1)"
      case SelectNonNeg             => s"($a<0) ? $b : $a"
      //case FltIsPosInf            =>
      //case FltIsNegInf            =>
      //case FltIsNaN               =>
      case FltNeg                   => s"-$a"
      case FltAdd                   => s"$a + $b"
      case FltSub                   => s"$a - $b"
      case FltMul                   => s"$a * $b"
      case FltDiv                   => s"SafeDiv($a, $b, $ctx)"
      //case FltMod                 =>
      case FltRecip                 => s"1/$a"
      case FltLst                   => s"$a < $b"
      case FltLeq                   => s"$a <= $b"
      case FltNeq                   => s"$a != $b"
      case FltEql                   => s"$a == $b"
      case FltMax                   => s"fmax($a,$b)"
      case FltMin                   => s"fmin($a,$b)"
      case FltToFlt                 => s"(${ntp.qtp}) $a"
      case FltToFix                 => s"(${ntp.qtp}) $a"
      //case TextToFlt              =>
      case FltToText                => s"to_string($a)"
      //case FltRandom              =>
      case FltAbs                   => s"abs($a)"
      case FltFloor                 => s"floor($a)"
      case FltCeil                  => s"ceil($a)"
      case FltLn                    => s"log($a)"
      case FltExp                   => s"exp($a)"
      case FltSqrt                  => s"sqrt($a)"
      case FltSin                   => s"sin($a)"
      case FltCos                   => s"cos($a)"
      case FltTan                   => s"tan($a)"
      case FltSinh                  => s"sinh($a)"
      case FltCosh                  => s"cosh($a)"
      case FltTanh                  => s"tanh($a)"
      case FltAsin                  => s"asin($a)"
      case FltAcos                  => s"acos($a)"
      case FltAtan                  => s"atan($a)"
      case FltPow                   => s"pow($a, $b)"
      case FltFMA                   => s"$a * $b + $c"
      //case FltRecipSqrt           =>
      //case FltSigmoid             =>

      case Not                      => s"!$a"
      case And                      => s"$a & $b"
      case Or                       => s"$a | $b"
      case Xor                      => s"$a ^ $b"
      case Xnor                     => s"$a == $b"
      //case BitRandom              =>
      case BitToText                => s"to_string($a)"
      case BitsAsData               => a.asTp(ntp.qtp)

      case Mux                      => s"$a ? $b : $c"
      case TextConcat               => ins.reduce[String] { case (a,b) => s"$a + $b" }
      case TextEql                  => s"$a == $b"
      case TextNeq                  => s"$a != $b"
      case TextLength               => s"$a.size() / ${ta.bytePerWord.get}"
      case TextApply                => s"$a[$b]"
      //case CharArrayToText        =>
      //case OneHotMux              =>
      case op                       => throw PIRException(s"TODO: unsupported op $op")
    }
  }

  override def quoteRef(n:Any):String = n match {
    case InputField(n:Shuffle, field) => s"${n}_$field"
    case n@InputField(_:OpNode, "en") => quoteEn(n.as[Input[PIRNode]], None)
    case n => super.quoteRef(n)
  }

  implicit class StringOp(x:String) {
    def asTp(tp:String) = s"(*($tp*) &$x)"
  }

}
