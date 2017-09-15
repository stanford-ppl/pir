package pir.spade

import pir.spade.main.Spade
import pir.spade.graph._
import pir.spade.simulation._
import pir.exceptions.PIRException
import pir.mapper.PIRMap
import pir.Design

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.language.implicitConversions
import scala.reflect.{ClassTag, classTag}

package object util {
  implicit def pr_to_ip(pr:PipeReg):Input[Bus, PipeReg] = pr.in
  implicit def pr_to_op(pr:PipeReg):Output[Bus, PipeReg] = pr.out

  def regsOf(io:IO[_,_]):List[ArchReg] = mappingOf[PipeReg](io).map(_.reg)

  def mappingOf[T](io:IO[_,_])(implicit ev:ClassTag[T]):List[T] = io match {
    case in:Input[_,_] => 
      fromInstanceOf[T](in).map{_.src.asInstanceOf[T]}
    case out:Output[_,_] =>
      fromInstanceOf[T](out).map{_.src.asInstanceOf[T]}
  }

  def fromInstanceOf[T](in:Input[_<:PortType,Module])(implicit ev:ClassTag[T]):List[Output[_<:PortType,Module]] = {
    //println(s"$in.fanIns=[${in.fanIns.map(n => s"($n, ${ev.runtimeClass.isInstance(n)})").mkString("\n")}]")
    in.fanIns.flatMap { out =>
      out.src match {
        case n:T => List(out)
        case sl:Slice[_,_] => fromInstanceOf[T](sl.in)
        case bc:BroadCast[_] => fromInstanceOf[T](bc.in)
        case n => Nil
      }
    }
  }

  def fromInstanceOf[T](out:Output[_<:PortType,Module])(implicit ev:ClassTag[T]):List[Input[_<:PortType,Module]] = {
    out.fanOuts.flatMap { in =>
      in.src match {
        case n:T => List(in);
        case sl:Slice[_,_] => fromInstanceOf[T](sl.out)
        case bc:BroadCast[_] => fromInstanceOf[T](bc.out)
        case n => Nil
      }
    }
  }

  def stageOf(io:IO[_,_]):Option[Stage] = {
    io.src match {
      case pr:PipeReg => Some(pr.stage) 
      case fu:FuncUnit => Some(fu.stage)
      case _ => None
    }
  }

  def collectIn[X](x:Any)(implicit ev:ClassTag[X]):Set[X] = x match {
    case x:X => Set(x)
    case x:Iterable[_] => x.flatMap(collectIn[X]).toSet
    case x:GlobalInput[_,_] => Set() // Do not cross CU boundary
    case x:GlobalOutput[_,_] => collectIn[X](x.src) ++ collectIn[X](x.ic)
    case x:Input[_,_] => collectIn[X](x.fanIns)
    case x:Output[_,_] => collectIn[X](x.src)
    case x:Slice[_, _] => collectIn[X](x.in)
    case x:BroadCast[_] => collectIn[X](x.in)
    case x:PipeReg => collectIn[X](x.in)
    case x:Counter => collectIn[X](x.min) ++ collectIn[X](x.max) ++ collectIn[X](x.step)
    case x:Mux[_] => x.ins.flatMap(collectIn[X]).toSet
    case x:SRAM => collectIn[X](x.readAddr) ++ collectIn[X](x.writeAddr) ++ collectIn[X](x.writePort)
    case x:LocalBuffer => collectIn[X](x.writePort)
    case _ => Set()
  }

  def collectOut[X](x:Any)(implicit ev:ClassTag[X]):Set[X] = x match {
    case x:X => Set(x)
    case x:Iterable[_] => x.flatMap(collectOut[X]).toSet
    case x:GlobalInput[_,_] => collectOut[X](x.src) ++ collectOut[X](x.ic)
    case x:GlobalOutput[_,_] => Set() // Do not cross CU boundary
    case x:Input[_,_] => collectOut[X](x.src)
    case x:Output[_,_] => collectOut[X](x.fanOuts)
    case x:Slice[_, _] => collectOut[X](x.out)
    case x:BroadCast[_] => collectOut[X](x.out)
    case x:PipeReg => collectOut[X](x.out)
    case x:Counter => collectOut[X](x.out)
    case x:Mux[_] => collectOut[X](x.out)
    case x:OnChipMem => collectOut[X](x.readPort)
    case _ => Set()
  }

  def quote(n:Node)(implicit spade:Spade):String = {
    val spademeta: SpadeMetadata = spade
    import spademeta._
    n match {
      case n:Routable => coordOf.get(n).fold(s"$n") { case (x,y) => s"$n[$x,$y]" }
      case n:GlobalIO[_,_] => s"${quote(n.src)}.$n[${n.index}]"
      case n => indexOf.get(n).fold(s"$n"){ i =>s"$n[$i]"}
    }
  }

  def isMapped(node:Node)(implicit mp: PIRMap):Boolean = {
    node match {
      case n:Primitive if !isMapped(n.prt) => return false
      case n =>
    }
    node match {
      case n:Controller => mp.pmmap.isMapped(n)
      case n:DRAM => true
      case n:FuncUnit => isMapped(n.stage)
      case n:PipeReg => isMapped(n.in)
      case n:UDCounter => 
        n.prt.ctrlBox match {
          case cb:MemoryCtrlBox => true
          case cb:OuterCtrlBox if cb.udsm.udc == n => true
          case cb:CtrlBox => mp.pmmap.isMapped(n)
        }
      case n:Input[_,_] => mp.fimap.contains(n) || n.fanIns.size==1
      case n:GlobalOutput[_,_] => mp.vomap.contains(n)
      case n:Output[_,_] => mp.opmap.contains(n)
      case n:SwitchBox => n.ios.exists(isMapped)
      case n:CtrlBox => isMapped(n.prt)
      case n:PulserSM => isMapped(n.prt) && !mp.pmmap(n.prt).isSC
      case n:UpDownSM => isMapped(n.prt)
      case n:Const[_] => mp.pmmap.isMapped(n)
      case n:BroadCast[_] => isMapped(n.in) 
      case n:Slice[_,_] => isMapped(n.in) 
      case n:Delay[_] => isMapped(n.prt)
      case n:AndTree => n.ins.exists(isMapped)
      case n:AndGate => n.ins.exists(isMapped)
      case n:PredicateUnit => isMapped(n.in) 
      case n:Primitive => mp.pmmap.isMapped(n)
      case n => throw PIRException(s"Don't know how to check whether $n is mapped")
    }
  }

  def fanInOf[P<:PortType](in:Input[P,Module])(implicit mp:PIRMap):Option[Output[P,Module]] = {
    mp.fimap.get(in).fold { 
      if (in.fanIns.size==1) Some(in.fanIns.head) else None
    } { out =>
      Some(out.asInstanceOf[Output[P, Module]])
    }
  }

  def fanOutOf[P<:PortType](out:Output[P,Module])(implicit mp:PIRMap):List[Input[P,Module]] = {
    mp.fimap.get(out).fold { 
      if (out.fanOuts.size==1) List(out.fanOuts.head) else Nil
    } { ins =>
      ins.map { _.asInstanceOf[Input[P, Module]] }.toList
    }
  }

  def OCU_MAX_CIN(implicit spade:Spade) = {
    val ocu = spade.ocus.head
    ocu.cins.size
  }

  def zip[T1, T2, T](x1:Option[T1], x2:Option[T2])(lambda:(T1,T2) => T):Option[T] = (x1, x2) match {
    case (Some(x1), Some(x2)) => Some(lambda(x1, x2))
    case _ => None
  }
  def zip[T1, T2, T3, T](x1:Option[T1], x2:Option[T2], x3:Option[T3])(lambda:(T1,T2,T3) => T):Option[T] = (x1, x2, x3) match {
    case (Some(x1), Some(x2), Some(x3)) => Some(lambda(x1, x2, x3))
    case _ => None
  }
  def zip[T1, T2, T3, T4, T](x1:Option[T1], x2:Option[T2], x3:Option[T3], x4:Option[T4])(lambda:(T1,T2,T3,T4) => T):Option[T] = (x1, x2, x3, x4) match {
    case (Some(x1), Some(x2), Some(x3), Some(x4)) => Some(lambda(x1, x2, x3, x4))
    case _ => None
  }
}
