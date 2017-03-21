package pir.plasticine

import pir.plasticine.main.Spade
import pir.plasticine.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.language.implicitConversions

package object util {
  implicit def pr_to_ip(pr:PipeReg):Input[Word, PipeReg] = pr.in
  implicit def pr_to_op(pr:PipeReg):Output[Word, PipeReg] = pr.out

  def mappingOf(io:IO[_,_]):List[Reg] = io match {
    case in:Input[_,_] => 
      in.fanIns.map(_.src).collect{ case pr:PipeReg => pr.reg }
    case out:Output[_,_] =>
      out.fanOuts.map(_.src).collect { case pr:PipeReg => pr.reg }
  }
  def isMappedTo(io:IO[_,_], reg:Reg):Boolean = {
    mappingOf(io).contains(reg)
  }
  def stageOf(io:IO[_,_]):Option[Stage] = {
    io.src match {
      case pr:PipeReg => Some(pr.stage) 
      case fu:FuncUnit => Some(fu.stage)
      case _ => None
    }
  }

  def quote(n:Node)(implicit spade:Spade) = {
    val spademeta: SpadeMetadata = spade
    import spademeta._
    n match {
      case pne:NetworkElement => coordOf.get(pne).fold(s"$pne") { case (x,y) => s"$pne[$x,$y]"}
      case n => indexOf.get(n).fold(s"$n"){ i =>s"$n[$i]"}
    }
  }
}
