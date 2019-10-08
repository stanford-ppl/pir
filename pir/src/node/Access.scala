package pir
package node

import prism.graph._

trait Access extends PIRNode {
  val order = Metadata[Float]("order")
  val port = Metadata[Option[Int]]("port", default=Some(0))
  val muxPort = Metadata[Int]("muxPort")
  val broadcast = Metadata[Seq[Int]]("broadcast")
  val castgroup = Metadata[Seq[Int]]("castgroup")

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
    this
  }
}
trait BankedAccess extends Access {
  val bank = new InputField[List[PIRNode]]("bank")
  val offset = new InputField[PIRNode]("offset")
}
trait FlatBankedAccess extends Access { // lowered access
  val offset = new InputField[PIRNode]("offset")
}
trait LockAccess extends Access {
  val addr = new InputField[PIRNode]("addr")
  val lock = new InputField[Option[LockOnKeys]]("lock")
}
trait InAccess extends Access { // Memory as output
  val mem = new OutputField[Memory]("mem")
}
trait OutAccess extends Access { // Memory as input
  val out = new OutputField[List[PIRNode]]("out")
  override def asOutput = Some(out)
  val mem = new InputField[Memory]("mem")
}
trait WriteAccess extends InAccess {
  val data = new InputField[PIRNode]("data")
}
trait ReadAccess extends OutAccess
case class BankedRead()(implicit env:Env) extends ReadAccess with BankedAccess
case class BankedWrite()(implicit env:Env) extends WriteAccess with BankedAccess
case class LockRead()(implicit env:Env) extends ReadAccess with LockAccess 
case class LockWrite()(implicit env:Env) extends WriteAccess with LockAccess {
  val ack = new OutputField[List[PIRNode]]("ack")
}
case class FlatBankedRead()(implicit env:Env) extends ReadAccess with FlatBankedAccess
case class FlatBankedWrite()(implicit env:Env) extends WriteAccess with FlatBankedAccess
case class MemRead()(implicit env:Env) extends ReadAccess
case class MemWrite()(implicit env:Env) extends WriteAccess

trait LocalAccess extends PIRNode {
  // en is branch-dependent
  // done is branch independent
  // Check valid when en is true
  // Pop when if done and all en are true
  val en = new InputField[Set[PIRNode]]("en").tp(Bool) // if not connected, default true
  val done = new InputField[Option[PIRNode]]("done").tp(Bool).presetVec(1) // if not connected, default false

  val isSplit = Metadata[Boolean]("isSplit", default=false)
}
trait LocalInAccess extends LocalAccess with Def
trait LocalOutAccess extends LocalAccess with Def with MemoryNode {
  val in = new InputField[PIRNode]("in")
  val initToken = Metadata[Boolean]("initToken", default=false)
}
case class BufferWrite(isFIFO:Boolean)(implicit env:Env) extends LocalInAccess {
  val data = new InputField[PIRNode]("data")
}
case class BufferRead(isFIFO:Boolean)(implicit env:Env) extends LocalOutAccess
case class BufferRegRead()(implicit env:Env) extends LocalOutAccess {
  val writeEn = new InputField[Option[PIRNode]]("writeEn")
  val writeDone = new InputField[Option[PIRNode]]("writeDone")
}
trait TokenAccess extends LocalAccess
case class TokenWrite()(implicit env:Env) extends TokenAccess with LocalInAccess {
  val dummy = new InputField[PIRNode]("dummy")
}
case class TokenRead()(implicit env:Env) extends TokenAccess with LocalOutAccess

trait AccessUtil {
  implicit class AccessOp[T<:PIRNode](x:T) {
    def isInAccess:Boolean = x match {
      case x:InAccess => true
      case x => false
    }
    def isOutAccess:Boolean = x match {
      case x:OutAccess => true
      case x => false
    }
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
