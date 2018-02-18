package pir.node

import pir._

import pirc._
import pirc.enums._
import pirc.util._

trait LocalAccess extends Def
trait LocalLoad extends LocalAccess
object LocalLoad {
  def unapply(n:Any)(implicit design:PIR):Option[(List[Memory], Option[List[Def]])] = n match {
    case ReadMem(mem) => Some((List(mem), None))
    case LoadBanks(banks, addrs) => Some((banks, Some(addrs)))
    case LoadMem(mem, addrs) => Some(List(mem), Some(addrs))
    case EnabledLoadMem(mem, addrs, deqEn) => Some((List(mem), addrs))
    case _ => None
  }
}
trait LocalStore extends LocalAccess {
  override def isInputField(field:Any, fieldIdx:Int) = field match {
    case x:Memory => false
    case _ => true
  }
}
object LocalStore {
  def unapply(n:Any)(implicit design:PIR):Option[(List[Memory], Option[List[Def]], Def)] = n match {
    case WriteMem(mem, data) => Some((List(mem), None, data))
    case StoreBanks(banks, addrs, data) => Some((banks, Some(addrs), data))
    case StoreMem(mem, addrs, data) => Some((List(mem), Some(addrs), data))
    case EnabledStoreMem(mem, addrs, data, enqEn) => Some((List(mem), addrs, data))
    case _ => None
  }
}

object WithWriters {
  def unapply(n:Any):Option[List[LocalStore]] = n match {
    case n:Memory => Some(n.writers.toList)
    case _ => None
  }
}
object WithWriter {
  def unapply(n:Any):Option[LocalStore] = n match {
    case n:Memory if n.writers.size == 1 => Some(n.writers.head)
    case _ => None
  }
}

// Write without address
case class ReadMem(mem:Memory)(implicit design:PIR) extends LocalLoad
case class WriteMem(mem:Memory, data:Def)(implicit design:PIR) extends LocalStore

case class LoadBanks(banks:List[Memory], addrs:List[Def])(implicit design:PIR) extends LocalLoad
case class StoreBanks(banks:List[Memory], addrs:List[Def], data:Def)(implicit design:PIR) extends LocalStore
// Lowered
case class LoadMem(mem:Memory, addrs:List[Def])(implicit design:PIR) extends LocalLoad
case class StoreMem(mem:Memory, addrs:List[Def], data:Def)(implicit design:PIR) extends LocalStore
case class SelectBanks(bankLoads:List[LocalLoad])(implicit design:PIR) extends Def

case class EnabledLoadMem(mem:Memory, addrs:Option[List[Def]], readNext:Def)(implicit design:PIR) extends LocalLoad
case class EnabledStoreMem(mem:Memory, addrs:Option[List[Def]], data:Def, writeNext:Def)(implicit design:PIR) extends LocalStore

case class FIFOEmpty(mem:Memory)(implicit design:PIR) extends Def
case class FIFOPeak(mem:Memory)(implicit design:PIR) extends Def
case class FIFONumel(mem:Memory)(implicit design:PIR) extends Def
//case class NotEmpty(mem:Memory) extends Def
//case class NotFull(mem:Memory) extends Def

// Memory Control Signals
case class NotEmpty(mem:Memory)(implicit design:PIR) extends ControlNode
case class NotFull(mem:Memory)(implicit design:PIR) extends ControlNode
