package pir
package node

import prism.graph._

trait Access extends PIRNode {
  val order = Metadata[Int]("order")
  val port = Metadata[Option[Int]]("port", default=Some(0))
  val muxPort = Metadata[Int]("muxPort")
  val broadcast = Metadata[Seq[Int]]("broadcast")
  val castgroup = Metadata[Seq[Int]]("castgroup")

  val en = new InputField[List[PIRNode]]("en")
  val done = new InputField[Option[PIRNode]]("done")
  def mem:FieldEdge[Memory,_,_]
  def isBroadcast = port.get.isEmpty
}
trait BankedAccess extends Access {
  val bank = new InputField[List[PIRNode]]("bank")
  val offset = new InputField[PIRNode]("offset")
}
trait FlatBankedAccess extends Access { // lowered access
  val offset = new InputField[PIRNode]("offset")
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
case class FlatBankedRead()(implicit env:Env) extends ReadAccess with FlatBankedAccess
case class FlatBankedWrite()(implicit env:Env) extends WriteAccess with FlatBankedAccess
case class MemRead()(implicit env:Env) extends ReadAccess
case class MemWrite()(implicit env:Env) extends WriteAccess

trait LocalAccess extends PIRNode {
  val done = new InputField[PIRNode]("done")
}
trait LocalInAccess extends LocalAccess with Def
trait LocalOutAccess extends LocalAccess with Def with MemoryNode {
  val in = new InputField[PIRNode]("in")
  val initToken = Metadata[Boolean]("initToken", default=false)
}
case class BufferWrite()(implicit env:Env) extends LocalInAccess {
  val data = new InputField[PIRNode]("data")
  // En is anded with done. But done is branch independent
  val en = new InputField[List[PIRNode]]("en")
}
case class BufferRead()(implicit env:Env) extends LocalOutAccess
trait TokenAccess extends LocalAccess
case class TokenWrite()(implicit env:Env) extends TokenAccess with LocalInAccess
case class TokenRead()(implicit env:Env) extends TokenAccess with LocalOutAccess

trait AccessUtil {
  implicit class AccessOp[T](x:T) {
    def isInAccess:Boolean = x match {
      case x:InAccess => true
      case x => false
    }
    def isOutAccess:Boolean = x match {
      case x:OutAccess => true
      case x => false
    }
    def setMem(m:Memory):T = {
      x.to[Access].fold(x) { xx => 
        xx.order := m.accesses.size
        xx.mem(m)
        x 
      }
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
  def unapply(x:Any) = x match {
    case x:Access => Some((x, x.mem.T))
    case x => None
  }
}
object WithData {
  def unapply(x:Any) = x match {
    case x:WriteAccess => Some((x, x.data.T))
    case x:BufferWrite => Some((x, x.data.T))
    case x => None
  }
}
object WithInAccess {
  def unapply(x:Any) = x match {
    case x:Memory if x.inAccesses.size == 1 => Some((x, x.inAccesses.head))
    case x => None
  }
}
