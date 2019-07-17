package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable
import spade.param._

trait TungstenCtxGen extends TungstenCodegen with TungstenTopGen {

  def numStagesOf(ctx:Context) = ctx.global.get match {
    case g:ArgFringe => 1
    case g:DRAMFringe => 100
    case g:GlobalContainer if spadeParam.isAsic =>
      Math.max(ctx.collectDown[OpNode]().size, 1)
    case g:GlobalContainer if spadeParam.isInf =>
      val cuParam = topMap.right.get.cumap(g).head.params.get.as[CUParam]
      if (cuParam.traceOut[TopParam].scheduled) 1 else cuParam.numStage
    case g:GlobalContainer =>
      val cuParam = topMap.right.get.cumap.usedMap(g).params.get.as[CUParam]
      if (cuParam.traceOut[TopParam].scheduled) 1 else cuParam.numStage
  }

  override def emitNode(n:N) = n match {
    case n:Context =>
      dbgblk(s"emitNode($n)") {
        val (tp, name) = varOf(n)
        val numStages = numStagesOf(n)
        ctxExtVars.clear
        getBuffer("fields").foreach { _.reset }
        getBuffer("inits").foreach { _.reset }
        getBuffer("computes").foreach { _.reset }
        getBuffer("computes-mid").foreach { _.reset }
        getBuffer("computes-end").foreach { _.reset }
        enterFile(dirName, s"$n.h", false) {
          genCtxCompute {
            visitNode(n)
          }

          emitln("""
using   namespace std;
""")    
          emitBlock(s"""class $n: public Context<$numStages>""") {
            emitln(s"public:")
            getBuffer("fields").foreach { _.flushTo(sw) }
            ctxExtVars.foreach { case (tp, field) => 
              emitln(s"$tp* $field;")
            }
            emitln(s"public:")
            val constructorArgs = ctxExtVars.map { case (tp, field) => s"$tp* _$field" }.mkString(",")
            val constructor = s"""explicit $n($constructorArgs):Context<$numStages>("$n")"""
            emitBlock(constructor) {
              ctxExtVars.foreach { case (tp, field) =>
                emitln(s"$field = _$field;")
              }
              getBuffer("inits").foreach { _.flushTo(sw) }
            }
            emitBlock(s"void Eval()") {
              getBuffer("computes-begin").foreach { _.flushTo(sw) }
              val checkIO = n.collectChildren[LocalAccess].filterNot{_.nonBlocking}.nonEmpty
              if (checkIO) {
                emitIf(s"InputsValid() && OutputsReady()") {
                  getBuffer("computes").foreach { _.flushTo(sw) }
                  getBuffer("computes-mid").foreach { _.flushTo(sw) }
                }
              } else {
                getBuffer("computes").foreach { _.flushTo(sw) }
                getBuffer("computes-mid").foreach { _.flushTo(sw) }
              }
              emitln(s"EvalControllers();")
              if (checkIO) {
                emitIf(s"InputsValid() && OutputsReady()") {
                  getBuffer("computes-end").foreach { _.flushTo(sw) }
                }
              } else {
                getBuffer("computes-end").foreach { _.flushTo(sw) }
              }
              getBuffer("computes-end").foreach { _.flushTo(sw) }
              emitln(s"EvalPipe();")
            }
          }
          emit(s""";""")
        }
        genTop {
          emitln(s"""#include "$n.h"""")
        }
        genTopMember(n, ctxExtVars.map { _._2 }.map { _.& }, end=true)
      }

    case n => super.emitNode(n)
  }

  val ctxExtVars = mutable.ListBuffer[(String, String)]()

  final protected def genCtxFields(block: => Unit) = enterBuffer("fields"){ incLevel(1); block; decLevel(1) }

  final protected def genCtxInits(block: => Unit) = enterBuffer("inits") { incLevel(2); block; decLevel(2) }

  final protected def genCtxComputeBegin(block: => Unit) = enterBuffer("computes-begin") { incLevel(2); block; decLevel(2) }

  final protected def genCtxCompute(block: => Unit) = enterBuffer("computes") { incLevel(2); block; decLevel(2) }

  final protected def genCtxComputeMid(block: => Unit) = enterBuffer("computes-mid") { incLevel(2); block; decLevel(2) }

  final protected def genCtxComputeEnd(block: => Unit) = enterBuffer("computes-end") { incLevel(2); block; decLevel(2) }

  final protected def addEscapeVar(n:PIRNode):Unit = {
    addEscapeVar(varOf(n))
  }
  final protected def addEscapeVar(v:(String,String)):Unit = {
    if (!ctxExtVars.contains(v)) ctxExtVars += v
  }

  def emitNewMember(tp:String, name:Any) = {
    genCtxFields {
      emitln(s"""$tp* $name = new $tp("$name");""")
    }
    genCtxInits {
      emitln(s"AddChild($name);");
    }
  }

  def declare(tp:String, name:String, rhs: => Any) = {
    genCtxFields {
      emitln(s"${tp} $name;")
    }
    emitln(s"${name} = $rhs;")
  }

  def declare(sig:String) = {
    genCtxFields {
      emitln(s"$sig;")
    }
  }

  /*
   * Emit n as a vector even when n.getVec is 1
   * */
  def emitToVec(n:IR)(rhs: Option[String] => Any) = {
    val vec = n.getVec
    if (vec > 1) {
      declare(s"${n.qtp} ${n.qref}[${vec}]")
        emitBlock(s"for (int i = 0; i < ${vec}; i++)") {
          emitln(s"${n.qref}[i] = ${rhs(Some("i"))};")
        }
    } else {
      emitln(s"${n.qtp} ${n.qref}[] = {${rhs(None)}};")
    }
  }

  /*
   * Right hand side is a vector. Emit lhs as vector if vec > 1, otherwise as scalar
   * */
  def emitUnVec(lhs:IR)(rhs:Any) = {
    val vec = lhs.getVec
    if (vec > 1) {
      declare(s"${lhs.qtp} ${lhs.qref}[${lhs.getVec}]")
      emitBlock(s"for (int i = 0; i < ${vec}; i++)") {
        emitln(s"${lhs.qref}[i] = $rhs[i];")
      }
    } else {
      declare(lhs.qtp, lhs.qref, s"$rhs[0]")
    }
  }

  def emitVec(n:IR)(rhs: Option[String] => Any) = {
    val vec = n.getVec
    if (vec > 1) {
      declare(s"${n.qtp} ${n.qref}[${vec}];")
      emitBlock(s"for (int i = 0; i < ${vec}; i++)") {
        emitln(s"${n.qref}[i] = ${rhs(Some("i"))};")
      }
    } else {
      declare(n.qtp, n.qref, rhs(None))
    }
  }

  def emitVec(n:IR, rhs:List[Any]) = {
    assert(n.getVec == rhs.size)
    if (n.getVec==1) {
      declare(n.qtp, n.qref, rhs.head)
    } else {
      declare(s"${n.qtp} ${n.qref}[${n.getVec}]")
      rhs.view.zipWithIndex.foreach { case (e,i) => 
        emitln(s"${n.qref}[$i] = ${e};")
      }
    }
  }

  def emitEn(en:Input[PIRNode]):Unit = {
    emitVec(en) { i => quoteEn(en, i) }
  }

  def quoteEn(en:Input[PIRNode], i:Option[String]):String = {
    val name = en.as[Field[PIRNode]].name
    val default = name match {
      case "en" => true
      case "done" => false
    }
    var ens = en.connected.map { _.qidx(i) }
    ens.distinct.reduceOption[String]{ _ + " & " + _ }.getOrElse(default.toString)
  }

}
