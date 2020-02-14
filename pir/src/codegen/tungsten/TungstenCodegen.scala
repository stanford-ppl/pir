package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

class TungstenPIRGen(implicit design:PIR) extends TungstenCodegen 
  with TungstenTopGen 
  with TungstenIOGen
  with TungstenCtxGen 
  with TungstenControllerGen
  with TungstenOpGen
  with TungstenMemGen
  with TungstenStreamGen
  with TungstenBlackBoxGen 
  with TungstenDRAMGen 
  with TungstenLockGen 
  with TungstenSparseGen 
  with TungstenMergeBufferGen 
  with TungstenDelayGen

trait TungstenCodegen extends PIRTraversal with StaticTopDownTopologicalTraversal with CppCodegen {

  override def dirName = buildPath(config.tstOut, s"src")
  val forward = true
  val fileName = "DUT.h"

  override def initPass = {
    clearDir(dirName, { fileName => fileName.contains("Context") })
    clearDir(buildPath(config.tstOut, "build"))
    clearDir(buildPath(config.tstOut, "logs"))
    val tstHome = config.getTstHome
    //lnFiles(buildPath(tstHome, "plasticine", "resources"), config.tstOut)
    copyFiles(buildPath(tstHome, "plasticine", "resources"), config.tstOut)
    withOpen(config.tstOut,"TUNGSTEN_HOME",false) {
      emitln(tstHome)
    }
    withOpen(config.tstOut,"PSPEC",false) {
      val tokenWidth = spadeParam match {
        case param if param.isAsic =>
          val tw = pirTop.children.map{ glob => 
            glob.children.collect { case io:GlobalIO => io.getVec }.reduce { math.max(_,_) }
          }.reduce{ math.max(_,_) }
          info(s"TOKEN_WIDTH=${tw}")
          tw
        case param => param.vecWidth
      }
      emitln(s"-DTOKEN_WIDTH=$tokenWidth")
    }
    super.initPass
  }

  override def quote(n:Any) = n match {
    case n:PIRNode => n.externAlias.v.getOrElse(s"$n")
    case n:Iterable[_] => 
      s"{${n.map(quote).mkString(",")}}"
    case n => super.quote(n)
  }

  def filterEdge(out:Output[PIRNode], in:Input[PIRNode]) = (out,in) match {
    case (_, InputField(BufferRead(true), "en")) => true 
    case (_, InputField(_:LocalOutAccess, "en" | "done")) => false // signals used to pop the input buffer in the next cycle.
    case (OutputField(_:LocalInAccess, _), InputField(_:LocalOutAccess, _)) => false
    case (_,InputField(_:LoopController, "stopWhen")) => false
    case _ => true
  }

  override def visitIn(n:N) = n.siblingDeps(filter=Some(filterEdge)).toList

  override def visitOut(n:N) = n.siblingDepeds(filter=Some(filterEdge)).toList

  override def visitFunc(n:N):List[N] = n match {
    case n:Top => n.children.flatMap { _.children }
    case n => super.visitFunc(n)
  }

  //override def visitNode(n:N) = dbgblk(s"visitNode($n)"){
    //super.visitNode(n)
  //}

  override def selectFrontier(unvisited:List[N]) = {
    bug(s"Loop in tungsten codegen. Unvisited ${unvisited.map { n => quoteSrcCtx(n) }}")
  }

  def varOf(n:PIRNode):(String, String) = bug(s"Don't know varOf($n)")
  def nameOf(n:PIRNode) = varOf(n)._2
  def tpOf(n:PIRNode) = varOf(n)._1


  def quoteRef(n:Any):String = n match {
    case n:Input[_] => quoteRef(n.singleConnected.getOrElse(bug(s"Don't know how to quoteRef for ${dquote(n)}")))
    case n:Output[_] => quoteRef(n.src)
    //case n:PIRNode => if (n.getVec > 1) s"${n}[i]" else s"${n}"
    case n => s"$n"
  }

  def quoteIdx(n:Any, i:Option[String]):String = {
    val q = quoteRef(n)
    i.fold(q) { i => 
      val vec = n.as[IR].getVec
      if (vec > 1) s"$q[$i]" else s"$q"
    }
  }

  implicit class IRGenOp(n:IR) {
    def qref:String = quoteRef(n)
    def qidx(i:String):String = qidx(Some(i))
    def qidx(i:Option[String]):String = quoteIdx(n, i)
    def qany:String = {
      if (n.getVec > 1) s"Any<${n.getVec}>(${qref})" else qref
    }
    def qall:String = {
      if (n.getVec > 1) s"All<${n.getVec}>(${qref})" else qref
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
      case _ => bug(s"Unsupported token type $n")
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
