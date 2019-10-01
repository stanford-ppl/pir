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
      declare(n.qtp, n.qref, n.sname.get)

    case n@Const(v:List[_]) => emitVec(n, v)
    case n@Const(v:String) => emitVec(n, List.fill(n.getVec)(s""""${v.replace("\n", "\\n")}""""))
    case n@Const(v) => emitVec(n, List.fill(n.getVec)(v))

    case n:Forward =>
      emitVec(n) { i => n.in.qidx(i) }

    case n:AccumAck =>
      emitVec(n) { i => "true" };

    case n@RegAccumOp(op, identity) =>
      genCtxFields {
        emitln(s"${n.qtp} $n = 0;")
      }
      def reduceOp(a:Any, b:Any) = op match {
        case List(op:OpDef) => 
          quoteOp(op.op, n.getTp, List(a.toString,b.toString), List(n.getTp, n.getTp), quoteSrcCtx(n))
        case op => bug(s"Unsupported reduce ops =$op")
      }
      val in = n.in.T
      val firstIter = n.first.singleConnected.map { _.qidx("0") }.getOrElse("true")
      emitIf(s"${firstIter}") {
        emitln(s"$n = $identity;")
      }
      emitIf(s"${n.en.qref}") {
        emitBlock(s"for (int i = 0; i < ${in.getVec}; i++)") {
          val laneValids = getCtrler(n).to[LoopController].fold("true") { _.laneValid.qidx(Some("i")) }
          emitIf(laneValids) {
            val initOrInput = n.init.singleConnected match {
              case Some(init) => init.qidx("i")
              case None => in.qidx("i")
            }
            val firstIter = n.first.singleConnected.map { _.qidx("i") }.getOrElse("true")
            emitln(s"$n = ($firstIter && i == 0) ? $initOrInput : ${reduceOp(n, in.qidx("i"))};")
          }
        }
      }

    case n@RegAccumFMA(identity) =>
      genCtxFields {
        emitln(s"${n.qtp} $n = 0;")
      }
      val firstIter = n.first.singleConnected.map { _.qidx("0") }.getOrElse("true")
      emitIf(s"${firstIter}") {
        emitln(s"$n = $identity;")
      }
      emitIf(s"${n.en.qref}") {
        val vec = math.max(n.in1.getVec, n.in2.getVec)
        emitBlock(s"for (int i = 0; i < ${vec}; i++)") {
          val laneValid = getCtrler(n).to[LoopController].fold("true") { _.laneValid.qidx(Some("i")) }
          emitIf(laneValid) {
            emitln(s"${n.qtp} mul = ${n.in1.T.qidx("i")} * ${n.in2.T.qidx("i")};")
            val initOrInput = n.init.singleConnected match {
              case Some(init) => init.qidx("i")
              case None => "mul"
            }
            val firstIter = n.first.singleConnected.map { _.qidx("i") }.getOrElse("true")
            emitln(s"$n = ($firstIter && i == 0) ? $initOrInput : $n + mul;")
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
      var ins = n.inputs.map { _.qidx(i) }
      var intps = n.inputs.map { _.getTp}
      val laneValid = getCtrler(n).to[LoopController].fold("true") { _.laneValid.qidx(i) }
      n.op match {
        case FixDiv | FltDiv =>
          ins = ins :+ laneValid
          intps = intps :+ Bool
        case _ =>
      }
      quoteOp(n.op, n.getTp, ins, intps, quoteSrcCtx(n))
    }

    case n:PrintIf =>
      emitIf(n.en.qref) {
        emitBlock(s"for (int i = 0; i < ${n.msg.T.getVec}; i++)") {
          emitln(s"cout << ${n.msg.T.qidx("i")};")
        }
      }
      emitVec(n) { i => "true" }

    case n:AssertIf =>
      emitIf(n.en.qref) {
        emitBlock(s"for (int i = 0; i < ${n.cond.getVec}; i++)") {
          emitln(s"""ASSERT(${n.cond.qidx(Some("i"))}, "${n.msg.T.fold {""} { _.qidx(Some("i")) }}");""")
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
      case FixDiv                   => s"""$c ? SafeDiv($a, $b, "$ctx") : $a"""
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
      case FixSigmoid               => s"1.0 / (1 + exp(-$a))"
      case SelectNonNeg             => s"($a<0) ? $b : $a"
      //case FltIsPosInf            =>
      //case FltIsNegInf            =>
      //case FltIsNaN               =>
      case FltNeg                   => s"-$a"
      case FltAdd                   => s"$a + $b"
      case FltSub                   => s"$a - $b"
      case FltMul                   => s"$a * $b"
      case FltDiv                   => s"""SafeDiv($a, $b, "$ctx")"""
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
      case FltSigmoid               => s"1.0 / (1 + exp(-$a))"

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
      case GenericToText            => s"to_string($a)"
      //case CharArrayToText        =>
      //case OneHotMux              =>
      case op                       => bug(s"TODO: unsupported op $op")
    }
  }

  override def quoteRef(n:Any):String = n match {
    case InputField(n:Shuffle, field) => s"${n}_$field"
    case n => super.quoteRef(n)
  }

  implicit class StringOp(x:String) {
    def asTp(tp:String) = s"(*($tp*) &$x)"
  }

}
