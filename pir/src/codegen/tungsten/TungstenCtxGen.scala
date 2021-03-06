package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable
import spade.param._

trait TungstenCtxGen extends TungstenTopGen {

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

  override def quote(n:Any) = n match {
    case n:Context if config.asModule => s"${topName}_$n"
    case n => super.quote(n)
  }

  def getCtrler(n:N):Controller = n match {
    case n:ControlBlock => assertOne(n.collectPeer[Controller](), s"$n.ctrler")
    case n:LocalOutAccess => assertOne(n.collectPeer[Controller](), s"$n.ctrler")
    case n => getCtrler(assertOne(n.collectUp[ControlBlock](), s"$n.cb"))
  }

  override def emitNode(n:N) = n match {
    case n:Context =>
      dbgblk(s"emitNode($n)") {
        val (tp, name) = varOf(n)
        ctxExtVars.clear
        declared.clear
        members.clear
        getBuffer("fields").foreach { _.reset }
        getBuffer("pre-inits").foreach { _.reset }
        getBuffer("inits").foreach { _.reset }
        getBuffer("computes").foreach { _.reset }
        getBuffer("computes-begin").foreach { _.reset }
        getBuffer("computes-mid").foreach { _.reset }
        getBuffer("computes-end").foreach { _.reset }
        genCtxCpp(n) {
          emitln(s"""#include "${quote(n)}.h"""")
        }
        genCtxHeader(n) {
          emitln("""
#include "ctx_import.h"
            """)

          genCtxCompute {
            visitNode(n)
          }

          val sortedMembers = members.toSeq.sortBy { _.toString } // Improve simulation speed
          emitln("""
using   namespace std;
""")    
          emitln(s"// ${quote(n)} in ${n.global.get}")
          emitBlock(s"""class ${quote(n)}: public Context""") {
            emitln(s"public:")
            getBuffer("fields").foreach { _.flushTo(sw) }
            ctxExtVars.foreach { case (tp, field) => 
              emitln(s"$tp& $field;")
            }
            emitln(s"public:")
            val constructorArgs = ctxExtVars.map { case (tp, field) => s"$tp& _$field" }.mkString(",")
            val constructor = s"""${quote(n)}($constructorArgs)"""
            emitln(s"explicit $constructor;")
            genCtxCpp(n) {
              // emitBlock(s"""${quote(n)}::$constructor :\nContext(${quote(n).qstr},${quote(n.global.get).qstr},${quoteSrcCtx(n,"\\n").qstr})""") {
              emitln(s"""${quote(n)}::$constructor :\nContext(${quote(n).qstr},${quote(n.global.get).qstr},${quoteSrcCtx(n,"\\n").qstr})""") 
              ctxExtVars.foreach { case (tp, field) =>
                emitln(s", $field(_$field)")
              }
              getBuffer("pre-inits").foreach { _.flushTo(sw) }
              emitBlock(s"") {
                // ctxExtVars.foreach { case (tp, field) =>
                  // emitln(s"$field = _$field;")
                // }
                getBuffer("inits").foreach { _.flushTo(sw) }
              }
            }
            emitln(s"void Clock();");
            genCtxCpp(n) {
              emitBlock(s"void ${quote(n)}::Clock()") {
                sortedMembers.foreach { m => 
                  emitln(s"$m.Clock();")
                }
              }
            }
            emitln(s"void Eval();")
            genCtxCpp(n) {
              emitBlock(s"void ${quote(n)}::Eval()") {
                getBuffer("computes-begin").foreach { _.flushTo(sw) }
                val checkIO = n.collectChildren[LocalAccess].filterNot{_.nonBlocking}.nonEmpty
                getBuffer("computes-reset").foreach { _.flushTo(sw) }
                getBuffer("computes").foreach { _.flushTo(sw) }
                getBuffer("computes-mid").foreach { _.flushTo(sw) }
                emitln(s"EvalControllers();")
                getBuffer("computes-end").foreach { _.flushTo(sw) }
                getBuffer("computes-end").foreach { _.flushTo(sw) }
                sortedMembers.foreach { m =>
                  if (!m.contains("Controller") && !m.contains("Counter")) {
                    emitln(s"$m.Eval();")
                  }
                }
              }
            }
          }
          emit(s""";""")
        }
        genTop {
          emitln(s"""#include "${quote(n)}.h"""")
        }
        // genTopMember(n, ctxExtVars.map { _._2 }.map { _.& }, end=true)
        genTopMember(n, ctxExtVars.map { _._2 }, end=true)
      }

    case n => super.emitNode(n)
  }

  override def varOf(n:PIRNode):(String, String) = n match {
    case n:Context => (s"${quote(n)}",s"ctx_${quote(n)}")
    case n => super.varOf(n)
  }

  val ctxExtVars = mutable.ListBuffer[(String, String)]()

  final protected def genCtxFields(block: => Unit) = enterBuffer("fields"){ incLevel(1); block; decLevel(1) }

  final protected def genCtxPreInits(block: => Unit) = enterBuffer("pre-inits") { incLevel(1); block; decLevel(1) }

  final protected def genCtxInits(block: => Unit) = enterBuffer("inits") { incLevel(1); block; decLevel(1) }

  final protected def genCtxComputeBegin(block: => Unit) = enterBuffer("computes-begin") { incLevel(1); block; decLevel(1) }

  final protected def genCtxCompute(block: => Unit) = enterBuffer("computes") { incLevel(1); block; decLevel(1) }

  final protected def genCtxComputeReset(block: => Unit) = enterBuffer("computes-reset") { incLevel(1); block; decLevel(1) }

  final protected def genCtxComputeMid(block: => Unit) = enterBuffer("computes-mid") { incLevel(1); block; decLevel(1) }

  final protected def genCtxComputeEnd(block: => Unit) = enterBuffer("computes-end") { incLevel(1); block; decLevel(1) }

  final protected def addEscapeVar(n:PIRNode):Unit = {
    addEscapeVar(varOf(n))
  }
  final protected def addEscapeVar(v:(String,String)):Unit = {
    if (!ctxExtVars.contains(v)) ctxExtVars += v
  }
  final protected def genCtxHeader(n:Context)(block: => Unit) = {
    enterFile(dirName, s"${quote(n)}.h", false) {
      block
    }
  }
  final protected def genCtxCpp(n:Context)(block: => Unit) = {
    enterFile(dirName, s"${quote(n)}.cc", false) {
      block
    }
  }

  private val members = mutable.ListBuffer[String]()

  def genCtxMember(n:PIRNode, args:Any*):Unit = {
    val (tp,name) = varOf(n)
    genCtxMember(tp, name, args, false)
  }

  def genCtxMember(tp:String, name:String, args:Seq[Any], end:Boolean=false):Unit = {
    genCtxFields {
      if (end) {
        emitln(s"$tp* $name;")
      } else {
        emitln(s"$tp $name;")
        //val fullargs = name.qstr +: args
        //emitln(s"""$tp* $name = new $tp(${fullargs.mkString(",")});""")
      }
    }
    genCtxPreInits {
      if (!end) {
        val fullargs = name.qstr +: args
        emitln(s", $name(${fullargs.mkString(",")})")
      }
    }
    genCtxInits {
      members += name
      if (end) {
        val fullargs = name.qstr +: args
        emitln(s"$name = new $tp(${fullargs.mkString(",")});")
      }
      emitln(s"AddChild(&$name, false);");
    }
  }

  val declared = mutable.Set[String]()

  def declare(tp:String, name:String, rhs: => Any):Unit = {
    val sig = s"$tp $name"
    declare(sig)
    emitln(s"${name} = $rhs;")
  }

  def declareInit(tp:String, name:String, init:Option[Any]):Unit = {
    val sig = s"$tp $name"
    declare(sig, init)
  }

  def declare(sig:String, init:Option[Any]=None):Unit = {
    assert(!declared.contains(sig), s"$sig has been declared")
    declared += sig
    val tp = sig.split(" ").head
    genCtxFields {
      init match {
        case Some(init) => emitln(s"$sig = $init;")
        case None => tp match {
          case "bool" => emitln(s"$sig{false};")
          case "int" => emitln(s"$sig{0};")
          case "float" => emitln(s"$sig{0.0f};")
          case "double" => emitln(s"$sig{0.0};")
          case default => emitln(s"$sig;")
        }
      }
    }
  }

  def getSig(n:IR) = {
    val vec = n.getVec
    if (vec > 1) {
      s"${n.qtp} ${n.qref}[${vec}]"
    } else {
      s"${n.qtp} ${n.qref}"
    }
  }

  def declare(n:IR):Unit = {
    declare(getSig(n))
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

  def emitAssign(n:IR)(rhs: Option[String] => Any) = {
    val vec = n.getVec
    if (vec > 1) {
      emitBlock(s"for (int i = 0; i < ${vec}; i++)") {
        emitln(s"${n.qref}[i] = ${rhs(Some("i"))};")
      }
    } else {
      emitln(s"${n.qref} = ${rhs(None)};")
    }
  }

  def emitVec(n:IR)(rhs: Option[String] => Any) = {
    declare(n)
    emitAssign(n)(rhs)
  }

  def emitVec(n:IR, rhs:List[Any]) = {
    assert(n.getVec == rhs.size, s"${dquote(n)}.vec ${n.getVec} != ${rhs.size}")
    n match {
      case n:Const if n.getVec == 1 =>
        declareInit(n.qtp, n.qref, Some(rhs.head))
      case n:Const =>
        declareInit(n.qtp, s"${n.qref}[${n.getVec}]", Some(s"{${rhs.mkString(",")}};"))
      case _ if n.getVec == 1 =>
        declare(n.qtp, n.qref, rhs.head)
      case _ =>
        declare(s"${n.qtp} ${n.qref}[${n.getVec}]")
        rhs.view.zipWithIndex.foreach { case (e,i) => 
          emitln(s"${n.qref}[$i] = ${e};")
        }
    }
  }

  override def quoteRef(n:Any):String = n match {
    case n@InputField(_, "en") => 
      var ens = n.as[Input[PIRNode]].connected.map { _.qref }
      ens.distinct.reduceOption[String]{ _ + " & " + _ }.getOrElse("true")
    case n => super.quoteRef(n)
  }

}
