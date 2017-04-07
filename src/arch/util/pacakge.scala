package pir.plasticine

import pir.plasticine.main.Spade
import pir.plasticine.graph._
import pir.exceptions.PIRException
import pir.Design

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.language.implicitConversions

package object util {
  implicit def pr_to_ip(pr:PipeReg):Input[Bus, PipeReg] = pr.in
  implicit def pr_to_op(pr:PipeReg):Output[Bus, PipeReg] = pr.out

  def mappingOf(io:IO[_,_]):List[ArchReg] = io match {
    case in:Input[_,_] => 
      in.fanIns.map(_.src).collect{ case pr:PipeReg => pr.reg }
    case out:Output[_,_] =>
      out.fanOuts.map(_.src).collect { case pr:PipeReg => pr.reg }
  }
  def isMappedTo(io:IO[_,_], reg:ArchReg):Boolean = {
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

  def isMapped(node:Node)(implicit design: Design):Boolean = {
    val mp = design.mapping.getOrElse(return false)
    node match {
      case n:Controller => mp.clmap.isMapped(n)
      case n:SRAM => mp.smmap.isMapped(n)
      case n:Counter => mp.ctmap.isMapped(n)
      case n:ScalarBuffer => mp.smmap.isMapped(n)
      case n:Stage => mp.stmap.isMapped(n)
      case n:UDCounter => mp.ucmap.isMapped(n)
      case n:Input[_,_] => mp.xbmap.isMapped(n)
      case n:Output[_,_] => mp.fimap.isMapped(n)
      case n:SwitchBox => n.ios.exists(isMapped)
      case n:CtrlBox => isMapped(n.ctrler)
      case n => throw PIRException(s"Don't know how to check whether $n is mapped")
    }
  }
}
