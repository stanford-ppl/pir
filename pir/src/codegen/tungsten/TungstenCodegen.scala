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

trait TungstenCodegen extends PIRTraversal with DFSTopDownTopologicalTraversal with CppCodegen {

  override def dirName = buildPath(config.tstOutDir, s"src")
  val forward = true
  val fileName = "Top.h"

  override def initPass = {
    clearDir(dirName, { fileName => fileName.contains("Context") })
    clearDir(buildPath(config.tstOutDir, "build"))
    clearDir(buildPath(config.tstOutDir, "logs"))
    copyFiles(buildPath(config.tstHome, "plasticine", "resources"), config.tstOutDir)
    withOpen(config.tstOutDir,"Makefile",false) {
      emitln(s"TUNGSTEN_HOME=${config.tstHome}")
      getLines(buildPath(config.tstHome, "plasticine", "resources", "Makefile")).foreach { emitln }
    }
    withOpen(buildPath(dirName, ".."),"script",true) {
      if (!noPlaceAndRoute) {
        emitln(s"source place")
      }
      //emitln(s"logon")
      //emitln(s"log2files")
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

  def emitEn(en:Input[PIRNode] with Field[_]):Unit = {
    val src = en.src
    val ens = en.neighbors
    val enName = s"${src}_${en.name}"
    emitln(s"bool $enName = ${ens.map { _.toString}.foldLeft("true"){ case (prev,b) => s"$prev & $b" }};")
    en.src match {
      case n:BufferWrite if n.getCtrl.schedule != "Streaming" =>
        emitln(s"${enName} &= ${n.ctx.get.ctrler(n.getCtrl).valid.T};")
      case n:InAccess if n.getCtrl.schedule != "Streaming" =>
        emitln(s"${enName} &= ${n.ctx.get.ctrler(n.getCtrl).valid.T};")
      case n:RegAccumOp =>
        emitln(s"${enName} &= ${n.ctx.get.ctrler(n.getCtrl).valid.T};")
      case _ =>
    }
  }

  /*
   * Emit rhs as a vector
   * */
  def emitToVec(lhs:String)(rhs:PIRNode) = {
    if (rhs.getVec > 1) {
      emitln(s"auto& $lhs = $rhs;")
    } else {
      emitln(s"${rhs.qtp} $lhs[] = {$rhs};")
    }
  }

  /*
   * Right hand side is a vector. Emit lhs as vector if vec > 1, otherwise as scalar
   * */
  def emitUnVec(lhs:PIRNode)(rhs:String) = {
    if (lhs.getVec > 1) {
      emitln(s"auto& $lhs = $rhs;")
    } else {
      emitln(s"${lhs.qtp} $lhs = $rhs[0];")
    }
  }

  def emitVec(n:PIRNode)(rhs: Option[String] => Any) = {
    val vec = n.getVec
    if (vec > 1) {
      emitln(s"${n.qtp} $n[${vec}] = {};")
      emitBlock(s"for (int i = 0; i < ${vec}; i++)") {
        emitln(s"$n[i] = ${rhs(Some("i"))};")
      }
    } else {
      emitln(s"${n.qtp} ${n} = ${rhs(None)};")
    }
  }

  def emitVec(n:PIRNode, rhs:List[Any]) = {
    assert(n.getVec == rhs.size)
    if (n.getVec==1) {
      emitln(s"${n.qtp} ${n} = ${rhs.head};")
    } else {
      emitln(s"${n.qtp} ${n}[] = {${rhs.mkString(",")}};")
    }
  }

  def quoteRef(n:Any):String = n match {
    case n@InputField(_, name) if name == "en" | name == "parentEn" =>
      val ens = n.as[Input[PIRNode]].connected
      s"${ens.foldLeft("true"){ case (prev,b) => s"$prev & ${quoteRef(b)}" }}"
    case n:Input[_] => quoteRef(assertOne(n.connected, s"${n.src}.$n.connected"))
    case n:Output[_] => quoteRef(n.src)
    //case n:PIRNode => if (n.getVec > 1) s"${n}[i]" else s"${n}"
    case n => s"$n"
  }

  implicit class AnyGenOp(n:Any) {
    def qref:String = quoteRef(n)
  }

  implicit class PIRNodeGenOp(n:PIRNode) {
    def qidx(i:String):String = {
      qidx(Some(i))
    }
    def qidx(i:Option[String]):String = {
      val q = quoteRef(n)
      i.fold(q) { i => if (n.getVec > 1) s"$q[$i]" else s"$q" }
    }
    def qtp:String = n.getTp match {
      case Fix(true, 32, 0) => "int"
      case Fix(true, 64, 0) => "long"
      case Fix(false, 32, 0) => "uint32_t"
      case Fix(false, 64, 0) => "uint64_t"
      case Fix(true, i, f) if f > 0 && i + f <= 32 => "float"
      case Fix(true, i, f) if f > 0 && i + f <= 64 => "double"
      case Flt(m,f) => "float"
      case Bool => "bool"
      case Text => "string"
    }
    def tokenTp = qtp match {
      case "int" if n.getVec == 1 => "TT_INT"
      case "int" if n.getVec > 1 => "TT_INTVEC"
      case "uint64_t" if n.getVec == 1 => "TT_UINT64"
      case "float" if n.getVec == 1 => "TT_FLOAT"
      case "float" if n.getVec > 1 => "TT_FLOATVEC"
      case "bool" if n.getVec == 1 => "TT_BOOL"
    }
  }

}
