package pir.plasticine

import pir.plasticine.main.Spade
import pir.plasticine.graph._
import pir.exceptions.PIRException
import pir.Design

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.language.implicitConversions
import scala.reflect.{ClassTag, classTag}

package object util {
  implicit def pr_to_ip(pr:PipeReg):Input[Bus, PipeReg] = pr.in
  implicit def pr_to_op(pr:PipeReg):Output[Bus, PipeReg] = pr.out

  def regsOf(io:IO[_,_]):List[ArchReg] = mappingOf[PipeReg](io).map(_.reg)

  def mappingOf[T](io:IO[_,_])(implicit ev:ClassTag[T]):List[T] = io match {
    case in:Input[_,_] => 
      in.fanIns.map(_.src).flatMap {
        case n if ev.runtimeClass.isInstance(n) => List(n.asInstanceOf[T])
        case sl:Slice[_] => mappingOf[T](sl.in)
        case bc:BroadCast[_] => mappingOf[T](bc.in)
        case n => Nil
      }
    case out:Output[_,_] =>
      out.fanOuts.map(_.src).flatMap { 
        case n if ev.runtimeClass.isInstance(n) => List(n.asInstanceOf[T])
        case sl:Slice[_] => mappingOf[T](sl.out)
        case bc:BroadCast[_] => mappingOf[T](bc.out)
        case n => Nil
      }
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
      case n:IO[_,_] => s"$n"
      case n => indexOf.get(n).fold(s"$n"){ i =>s"$n[$i]"}
    }
  }

  def isMapped(node:Node)(implicit design: Design):Boolean = {
    val mp = design.mapping.getOrElse(return false)
    node match {
      case n:Controller => mp.clmap.isMapped(n)
      case n:SRAM => mp.smmap.isMapped(n)
      case n:Counter => mp.ctmap.isMapped(n)
      case n:ScalarMem => mp.smmap.isMapped(n)
      case n:Stage => mp.stmap.isMapped(n)
      case n:UDCounter => mp.ucmap.isMapped(n)
      case n:Input[_,_] => mp.ipmap.isMapped(n)
      case n:Output[_,_] => mp.fimap.isMapped(n)
      case n:SwitchBox => n.ios.exists(isMapped)
      case n:CtrlBox => isMapped(n.pne)
      case n:RegChain[_] => isMapped(n.din)
      case n => throw PIRException(s"Don't know how to check whether $n is mapped")
    }
  }
}
