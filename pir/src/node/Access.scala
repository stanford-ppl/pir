package pir
package node

import spade.param._
import prism.graph._

trait Access extends PIRNode {

  /* Metadata set by spatial */
  val order = Metadata[Float]("order")
  val port = Metadata[Option[Int]]("port", default=Some(0))
  val muxPort = Metadata[Int]("muxPort")
  val broadcast = Metadata[Seq[Int]]("broadcast")
  val castgroup = Metadata[Seq[Int]]("castgroup")

  /* Metadata set by spatial */
  val isAccumAccess = Metadata[Boolean]("isAccumAccess", default=false)

  val en = new InputField[List[PIRNode]]("en").tp(Bool)
  val done = new InputField[Option[PIRNode]]("done").tp(Bool).presetVec(1)
  def mem:FieldEdge[Memory,_,_]
  def isBroadcast = port.get.isEmpty

  def setMem(m:Memory, order:Float=id):this.type = {
    // Id when the access is created is an indication 
    // of program order. This makes sure the program
    // oder is correct between accesses of different
    // memories that gets merged
    this.order := order
    mem(m)
    this.to[RMWAccess].foreach { rmw =>
      rmw.memOut(m)
    }
    this
  }
}
trait BankedAccess extends Access {
  val bank = InputField[List[PIRNode]]
  val offset = InputField[PIRNode]
}
trait InAccess extends Access { // Memory as output
  val mem = OutputField[Memory]
}
trait OutAccess extends Access { // Memory as input
  val out = OutputField[List[PIRNode]]
  override def asOutput = Some(out)
  val mem = InputField[Memory]
}
trait WriteAccess extends InAccess {
  val data = InputField[PIRNode]
}
trait ReadAccess extends OutAccess {
  override def compType(n:IR) = n match {
    case `out` => mem.T.inferTp
    case _ => super.compType(n)
  }
}
trait RMWAccess extends Access {
  val mem = InputField[Memory]
  val memOut = OutputField[Memory]
  val input = InputField[PIRNode]
}
// Nodes before lowering
case class BankedRead()(implicit env:Env) extends ReadAccess with BankedAccess {
  override def compVec(n:IR) = n match {
    case `out` => offset.inferVec
    case _ => super.compVec(n)
  }
}
case class BankedWrite()(implicit env:Env) extends WriteAccess with BankedAccess {
  override def compVec(n:IR) = n match {
    case n if n == this => zipMap(data.inferVec, offset.inferVec) { case (a,b) => Math.max(a,b) }
    case _ => super.compVec(n)
  }
}
// Nodes after lowering
trait FlatBankedAccess extends Access { // lowered access
  val offset = InputField[PIRNode]
  override def compVec(n:IR) = n match {
    case _:Edge[_,_,_] => Some(mem.T.nBanks)
    case _ => super.compVec(n)
  }
}
case class FlatBankedRead()(implicit env:Env) extends ReadAccess with FlatBankedAccess
case class FlatBankedWrite()(implicit env:Env) extends WriteAccess with FlatBankedAccess

trait LockAccess extends Access {
  val addr = InputField[PIRNode]
  val lock = InputField[Option[LockOnKeys]]
  override def compVec(n:IR) = n match {
    case out:Output[_] => Some(this.getCtrl.par.get)
    case _ => super.compVec(n)
  }
}
case class LockRead()(implicit env:Env) extends ReadAccess with LockAccess 
case class LockWrite()(implicit env:Env) extends WriteAccess with LockAccess {
  val ack = OutputField[List[PIRNode]].presetVec(1).tp(Bool)
}
case class LockRMW(op:Opcode)(implicit env:Env) extends LockAccess with RMWAccess {
  val ack = OutputField[List[PIRNode]].presetVec(1).tp(Bool)
}

class Barrier(val ctrl:ControlTree, val init:Int) extends prism.graph.IR { self =>
  val name = new Metadata[String]("name") {
    override def check(v:String) = {}
  }
  val srcCtx = new Metadata[List[String]]("srcCtx", default=Some(Nil)) {
    def apply(v:String):self.type = { 
      value = value.map { _ :+ v }
      self
    }
  }
}
object Barrier {
  def apply(ctrl:ControlTree, init:Int) = new Barrier(ctrl, init)
  def unapply(x:Barrier) = Some((x.ctrl,x.init))
}

trait SparseAccess extends Access {
  val addr = InputField[PIRNode]
  //val lock = InputField[Option[LockOnKeys]]
  
  // a list of (ControlTree, init, isWrite)
  val barriers = Metadata[List[(Barrier, Boolean)]]("barriers", default=Nil)

  override def compVec(n:IR) = n match {
    case out:Output[_] => Some(this.getCtrl.par.get)
    case _ => super.compVec(n)
  }
}
case class SparseRead()(implicit env:Env) extends ReadAccess with SparseAccess 
case class SparseWrite()(implicit env:Env) extends WriteAccess with SparseAccess {
  val ack = OutputField[List[PIRNode]].presetVec(1).tp(Bool)
}
case class SparseRMW(op:String, opOrder:String, remoteAddr:Boolean)(implicit env:Env) extends SparseAccess with RMWAccess {
  val dataOut = OutputField[List[PIRNode]]
  override def asOutput = Some(dataOut)
  override def compVec(n:IR) = n match {
    case `dataOut` => input.inferVec
    case _ => super.compVec(n)
  }
  override def compType(n:IR) = n match {
    case `dataOut` => input.inferTp
    case _ => super.compType(n)
  }
}

case class MemRead()(implicit env:Env) extends ReadAccess {
  override def compVec(n:IR) = (n, mem.T) match {
    case (`out`, mem:Reg) => Some(mem.banks.get.head)
    case (`out`, mem:FIFO) => 
      // Some(n.getCtrl.par.get) // doesn't work for stream in
      //out under stream controller
      out.connected match {
        case List(i@InputField(cmd:FringeDenseStore, "data" | "valid")) => i.inferVec
        case List(i@InputField(cmd:FringeSparseLoad, "addr")) => i.inferVec
        case List(i@InputField(cmd:FringeSparseStore, "addr" | "data")) => i.inferVec
        case List(i@InputField(cmd:FringeCoalStore, "addr" | "data" | "valid" | "len")) => i.inferVec
        case List(i@InputField(cmd:FringeDynStore, "addr" | "data" | "done")) => i.inferVec
        case List(i@InputField(cmd:Scanner, "input")) => i.inferVec
        case List(i@InputField(cmd:DataScanner, "input")) => i.inferVec
        case _ => broadcast.v.map { _.size }.orElse(Some(mem.banks.get.head))
      }
    case _ => super.compVec(n)
  }
}
case class MemWrite()(implicit env:Env) extends WriteAccess { self =>
  override def compVec(n:IR) = (n, data.singleConnected) match {
    case (`self`, Some(OutputField(data:MergeBuffer, _))) => Some(data.par)
    case (`self`, Some(OutputField(cmd:FringeDenseLoad, "data"))) => cmd.data.inferVec
    case (`self`, Some(OutputField(cmd:FringeSparseLoad, "data"))) => cmd.data.inferVec
    case (`self`, Some(OutputField(cmd:FringeSparseStore, "ack"))) => cmd.ack.inferVec
    case (`self`, Some(OutputField(cmd:FringeCoalStore, "ack"))) => cmd.ack.inferVec
    case (`self`,_) => 
      broadcast.v.map { _.size }.orElse { 
        if (mem.isConnected) {
          mem.T match {
            case mem:FIFO =>
              val ctrl = this.getCtrl
              ctrl.schedule match {
                case Streaming if !ctrl.isLoop.get => Some(mem.banks.get.head)
                case _ => Some(ctrl.par.get)
              }
            case mem:Reg => data.inferVec
          }
        } else {
          data.inferVec
        }
      }
    case _ => super.compVec(n)
  }
}

trait LocalAccess extends PIRNode with Def {
  // en is branch-dependent
  // done is branch independent
  // Check valid when en is true
  // Pop when if done and all en are true
  val en = InputField[Set[PIRNode]].tp(Bool) // if not connected, default true
  val done = InputField[Option[PIRNode]].tp(Bool).presetVec(1) // if not connected, default false

  val isSplit = Metadata[Boolean]("isSplit", default=false)
}
trait LocalInAccess extends LocalAccess 
trait LocalOutAccess extends LocalAccess with MemoryNode {
  val in = InputField[PIRNode]
  val initToken = Metadata[Int]("initToken", default=0)
  override def compType(n:IR) = n match {
    case `out` => in.inferTp
    case _ => super.compType(n)
  }
}
case class BufferWrite(isFIFO:Boolean)(implicit env:Env) extends LocalInAccess {
  val data = InputField[PIRNode]
  override def compType(n:IR) = n match {
    case `out` => data.inferTp
    case _ => super.compType(n)
  }
  override def compVec(n:IR) = n match {
    case `out` => en.inferVec
    case `en` => 
      val enVec = if (!en.isConnected) Some(1) else flatReduce(en.connected.map{ _.inferVec}) { Math.max(_,_) }
      zipMap(data.inferVec,enVec) { Math.max(_,_) }
    case _ => super.compVec(n)
  }
}
case class BufferRead(isFIFO:Boolean)(implicit env:Env) extends LocalOutAccess {
  val retiming = Metadata[Boolean]("retiming", Some(false)) 
  override def compVec(n:IR) = n match {
    case `out` => en.inferVec
    case `en` => 
      val enVec = if (!en.isConnected) Some(1) else flatReduce(en.connected.map{ _.inferVec}) { Math.max(_,_) }
      zipMap(in.inferVec,enVec) { Math.max(_,_) }
    case _ => super.compVec(n)
  }
}
case class BufferRegRead()(implicit env:Env) extends LocalOutAccess {
  val writeEn = InputField[Option[PIRNode]]
  val writeDone = InputField[Option[PIRNode]]
}
trait TokenAccess extends LocalAccess {
  out.tp(Bool).presetVec(1)
  en.tp(Bool).presetVec(1)
}
case class TokenWrite()(implicit env:Env) extends TokenAccess with LocalInAccess {
  val dummy = InputField[PIRNode]
}
case class TokenRead()(implicit env:Env) extends TokenAccess with LocalOutAccess {
  in.tp(Bool).presetVec(1)
}

trait AccessUtil {
  implicit class AccessOp[T<:PIRNode](x:T) {
    def isInAccess:Boolean = x.isInstanceOf[InAccess]
    def isOutAccess:Boolean = x.isInstanceOf[OutAccess]
    def isWrite:Boolean = x.isInstanceOf[WriteAccess]
    def isRead:Boolean = x.isInstanceOf[ReadAccess]
  } 
  implicit class LocalAccessOp(n:LocalAccess) {
    def isLocal = n match {
      case n:LocalOutAccess => n.in.T.parent == n.parent
      case n:LocalInAccess => 
        val parent = n.parent
        n.out.T.forall { _.parent == parent }
    }
    def isGlobal = n match {
      case n:LocalOutAccess => n.in.T.isInstanceOf[GlobalInput]
      case n:LocalInAccess => n.out.T.exists { _.isInstanceOf[GlobalOutput] }
    }
    def nonBlocking = n match {
      case n:BufferRead if !n.done.isConnected => 
        assert(n.depth.get==1)
        true
      case _ => false
    }
    def isFIFO = n match {
      case n:BufferRead => n.isFIFO
      case n:BufferWrite => n.isFIFO
      case n:TokenRead => false
      case n:TokenWrite => false
    }
  }
  implicit class LocalInAccessOp(n:LocalInAccess) {
    def outAccesses:List[LocalOutAccess] = n.out.collect[LocalOutAccess](visitGlobalOut _)
    def gout:Option[GlobalOutput] = assertOneOrLess(n.out.T.collect { case gout:GlobalOutput => gout }, s"$n.gout")
  }
  implicit class LocalOutAccessOp(n:LocalOutAccess) {
    def inAccess:LocalInAccess = assertOne(n.in.collect[LocalInAccess](visitGlobalIn _), s"$n.inAccess")
    def gin:Option[GlobalInput] = n.in.T.to[GlobalInput]
  }

}
object WithMem {
  def unapply(x:Access) = Some((x, x.mem.T))
}
object WithData {
  def unapply(x:PIRNode) = x match {
    case x:WriteAccess => Some((x, x.data.T))
    case x:BufferWrite => Some((x, x.data.T))
    case x => None
  }
}
object WithInAccess {
  def unapply(x:Memory) = {
    if (x.inAccesses.size == 1) Some((x, x.inAccesses.head)) else None
  }
}
object WrittenBy {
  def unapply(x:PIRNode) = x match {
    case x:WriteAccess => x.data.singleConnected
    case x:BufferWrite => x.data.singleConnected
    case _ => None
  }
}
