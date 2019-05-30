package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

class TungstenPIRGen(implicit design:PIR) extends TungstenCodegen 
  with TungstenTopGen 
  with TungstenCtxGen 
  with TungstenDRAMGen 
  with TungstenControllerGen
  with TungstenOpGen
  with TungstenMemGen
  with TungstenStreamGen

trait TungstenCodegen extends PIRTraversal with DFSTopDownTopologicalTraversal with CppCodegen {

  override def dirName = buildPath(config.tstOutDir, s"src")
  val forward = true
  val fileName = "Top.h"

  override def initPass = {
    clearDir(dirName, { fileName => fileName.contains("Context") })
    clearDir(buildPath(config.tstOutDir, "build"))
    clearDir(buildPath(config.tstOutDir, "logs"))
    copyFiles(buildPath(config.tstHome, "plasticine", "resources"), config.tstOutDir)
    withOpen(config.tstOutDir,"TUNGSTEN_HOME",false) {
      emitln(config.tstHome)
    }
    withOpen(buildPath(dirName, ".."),"script",false) {
      if (!noPlaceAndRoute) {
        emitln(s"source place")
      }
      emitln(s"log2files")
      emitln(s"stepall")
      emitln(s"dumpstate logs/state.json")
      emitln(s"stat")
      //emitln(s"logon")
    }
    super.initPass
  }

  override def quote(n:Any) = n match {
    case n:Iterable[_] => 
      s"{${n.map(quote).mkString(",")}}"
    case n => super.quote(n)
  }

  override def visitIn(n:N) = n match {
    case n:LocalOutAccess => Nil
    case n => super.visitIn(n)
  }

  override def visitOut(n:N) = n match {
    case n:LocalInAccess => super.visitOut(n).filterNot{_.isInstanceOf[LocalOutAccess]}
    case n => super.visitOut(n)
  }

  def quoteEn(en:Input[PIRNode], i:Option[String]):String = {
    var ens = en.connected.map { _.qidx(i) }
    en match {
      case InputField(_:BufferWrite | _:InAccess | _:RegAccumOp, "en") =>
        val n = en.src
        n.ctx.get.ctrler(n.getCtrl).foreach { ctrler =>
          ens :+= ctrler.valid.qidx(i)
        }
      case _ =>
    }
    ens.reduceOption[String]{ _ + " & " + _ }.getOrElse("true")
  }

  def emitEn(en:Input[PIRNode]):Unit = {
    emitVec(en) { i => quoteEn(en, i) }
  }

  /*
   * Emit n as a vector even when n.getVec is 1
   * */
  def emitToVec(n:IR)(rhs: Option[String] => Any) = {
    val vec = n.getVec
    if (vec > 1) {
      emitln(s"${n.qtp} ${n.qref}[${vec}] = {};")
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
    if (lhs.getVec > 1) {
      emitln(s"auto& $lhs = $rhs;")
    } else {
      emitln(s"${lhs.qtp} $lhs = $rhs[0];")
    }
  }

  def emitVec(n:IR)(rhs: Option[String] => Any) = {
    val vec = n.getVec
    if (vec > 1) {
      emitln(s"${n.qtp} ${n.qref}[${vec}] = {};")
      emitBlock(s"for (int i = 0; i < ${vec}; i++)") {
        emitln(s"${n.qref}[i] = ${rhs(Some("i"))};")
      }
    } else {
      emitln(s"${n.qtp} ${n.qref} = ${rhs(None)};")
    }
  }

  def emitVec(n:IR, rhs:List[Any]) = {
    assert(n.getVec == rhs.size)
    if (n.getVec==1) {
      emitln(s"${n.qtp} ${n} = ${rhs.head};")
    } else {
      emitln(s"${n.qtp} ${n}[] = {${rhs.mkString(",")}};")
    }
  }

  def quoteRef(n:Any):String = n match {
    case n:Input[_] => quoteRef(n.singleConnected.getOrElse(throw PIRException(s"Don't know how to quoteRef for ${dquote(n)}")))
    case n:Output[_] => quoteRef(n.src)
    //case n:PIRNode => if (n.getVec > 1) s"${n}[i]" else s"${n}"
    case n => s"$n"
  }

  implicit class IRGenOp(n:IR) {
    def qref:String = quoteRef(n)
    def qidx(i:String):String = {
      qidx(Some(i))
    }
    def qidx(i:Option[String]):String = {
      val q = quoteRef(n)
      i.fold(q) { i => 
        val vec = n.as[IR].getVec
        if (vec > 1) s"$q[$i]" else s"$q"
      }
    }
    def qtp:String = n.getTp.qtp
    def tokenTp = qtp match {
      case "int" | "int8_t" | "int16_t" if n.getVec == 1 => "TT_INT"
      case "int" | "int8_t" | "int16_t" if n.getVec > 1 => "TT_INTVEC"
      case "long" if n.getVec == 1 => "TT_LONG"
      case "long" if n.getVec == 1 => "TT_LONGVEC"
      case "uint64_t" if n.getVec == 1 => "TT_UINT64"
      case "uint64_t" if n.getVec > 1 => "TT_UINT64VEC"
      case "float" if n.getVec == 1 => "TT_FLOAT"
      case "float" if n.getVec > 1 => "TT_FLOATVEC"
      case "bool" if n.getVec == 1 => "TT_BOOL"
      case _ => throw PIRException(s"Unsupported token type $n")
    }
  }

  implicit class TpOp(tp:BitType) {
    def qtp:String = tp match {
      case Fix(true, 8, 0) => "int8_t"
      case Fix(true, 16, 0) => "int16_t"
      case Fix(true, 32, 0) => "int"
      case Fix(true, 64, 0) => "long"
      case Fix(false, 8, 0) => "uint8_t"
      case Fix(false, 16, 0) => "uint16_t"
      case Fix(false, 32, 0) => "uint32_t"
      case Fix(false, 64, 0) => "uint64_t"
      case Fix(true, i, f) if f > 0 && i + f <= 32 => "float"
      //case Fix(true, i, f) if f > 0 && i + f <= 64 => "double"
      case Fix(true, i, f) if f > 0 && i + f <= 64 => "float" // Executing double as float for now
      case Flt(m,f) => "float"
      case Bool => "bool"
      case Text => "string"
    }
  }

}
