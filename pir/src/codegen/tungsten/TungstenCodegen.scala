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
  with TungstenOpGen
  with TungstenMemGen

trait TungstenCodegen extends PIRTraversal with DFSTopDownTopologicalTraversal with CppCodegen {
  override def dirName = buildPath(super.dirName, s"../../tungsten/src") 
  val forward = true
  val fileName = "Top.h"

  def tungstenHome = config.option[String]("tungsten-home")

  override def initPass = {
    clearDir(dirName, { fileName => fileName.contains("Context") })
    copyFiles(buildPath(tungstenHome, "plasticine", "resources"), buildPath(dirName, ".."))
    withOpen(buildPath(dirName, ".."),"Makefile",false) {
      emitln(s"TUNGSTEN_HOME=${tungstenHome}")
      getLines(buildPath(tungstenHome, "plasticine", "resources", "Makefile")).foreach { emitln }
    }
    super.initPass
  }

  override def quote(n:Any) = n match {
    case n:Iterable[_] => 
      s"{${n.map(quote).mkString(",")}}"
    case n => super.quote(n)
  }

  override def visitIn(n:N) = n match {
    case n:BufferRead => super.visitIn(n).filterNot{_.isInstanceOf[BufferWrite]}
    case n => super.visitIn(n)
  }

  override def visitOut(n:N) = n match {
    case n:BufferWrite => super.visitOut(n).filterNot{_.isInstanceOf[BufferRead]}
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

  def quoteEn(en:Input[PIRNode] with Field[_]):String = {
    val src = en.src
    val ens = en.neighbors
    s"${ens.map { _.toString}.foldLeft("true"){ case (prev,b) => s"$prev & $b" }}"
  }

  def emitVec(n:PIRNode)(rhs:Any) = {
    val vec = n.getVec
    if (vec > 1) {
      emitln(s"int $n[${vec}] = {};")
      emitBlock(s"for (int i = 0; i < ${vec}; i++)") {
        emitln(s"$n[i] = ${rhs}")
      }
    } else {
      emitln(s"auto ${n} = ${rhs}")
    }
  }

  def emitToken(name:Any, vec:Int, value:List[Any]) = {
    emitln(s"Token $name;")
    if (vec == 1) {
      emitln(s"$name.type = TT_INT;")
      emitln(s"$name.int_ = ${assertOne(value, s"$name.value")};") 
    } else {
      emitln(s"$name.type = TT_INTVEC;")
      val vs = value ++ List.fill(spadeParam.vecWidth - vec)(0)
      emitln(s"$name.intVec_ = {${vs.mkString(",")}};") 
    }
  }

  def quoteRef(n:PIRNode):String = {
    if (n.getVec > 1) s"${n}[i]" else s"${n}"
  }

  implicit class PIRNodeGenOp(n:PIRNode) {
    def qref(i:Int):String = {
      if (n.getVec > 1) s"${n}[$i]" else s"${n}"
    }
    def qref:String = {
      if (n.getVec > 1) s"${n}[i]" else s"${n}"
    }
    def qref(i:String):String = {
      if (n.getVec > 1) s"${n}[$i]" else s"${n}"
    }
  }

}
