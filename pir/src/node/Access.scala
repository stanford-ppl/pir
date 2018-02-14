package pir.node

import pir._

import pirc._
import pirc.enums._
import pirc.util._

trait LocalLoad extends Def
object LocalLoad {
  def unapply(n:Any)(implicit design:PIR):Option[(List[Memory], Option[List[Def]])] = n match {
    case ReadMem(mem) => Some((List(mem), None))
    case LoadBanks(banks, addrs) => Some((banks, Some(addrs)))
    case LoadBank(bank, addrs) => Some(List(bank), Some(addrs))
    case _ => None
  }
}
trait LocalStore extends Def {
  override def connectFields(x:Any, i:Int)(implicit design:Design):Any = {
    implicit val pir = design.asInstanceOf[PIR]
    x match {
      case x:Memory => this.out.connect(x.newIn); this.out
      case x:Iterable[_] if x.forall(_.isInstanceOf[Memory]) => 
        x.foreach { x => this.out.connect(x.asInstanceOf[Memory].newIn) }
        this.out
      case x => super.connectFields(x, i)
    }
  }
  override def evaluateFields(f:Any, x:Any):Any = (f,x) match {
    case (f:Iterable[_], x:Output) => x.connected.map{_.src}
    case (f,x) => super.evaluateFields(f,x)
  }
}
object LocalStore {
  def unapply(n:Any)(implicit design:PIR):Option[(List[Memory], Option[List[Def]], Def)] = n match {
    case WriteMems(mems, data) => Some((mems, None, data))
    case StoreBanks(banks, addrs, data) => Some((banks, Some(addrs), data))
    case StoreBank(bank, addrs, data) => Some((List(bank), Some(addrs), data))
    case _ => None
  }
}

object WithWriters {
  def unapply(n:Any):Option[List[LocalStore]] = n match {
    case n:Memory => Some(n.writers.toList)
    case _ => None
  }
}

// Write without address
case class ReadMem(mem:Memory)(implicit design:PIR) extends LocalLoad
case class WriteMems(mems:List[Memory], data:Def)(implicit design:PIR) extends LocalStore

case class LoadBanks(banks:List[Memory], addrs:List[Def])(implicit design:PIR) extends LocalLoad
case class StoreBanks(banks:List[Memory], addrs:List[Def], data:Def)(implicit design:PIR) extends LocalStore
// Lowered
case class LoadBank(bank:Memory, addrs:List[Def])(implicit design:PIR) extends LocalLoad
case class StoreBank(bank:Memory, addrs:List[Def], data:Def)(implicit design:PIR) extends LocalStore
case class SelectBanks(bankLoads:List[LocalLoad])(implicit design:PIR) extends Def

case class FIFOEmpty(mem:Memory)(implicit design:PIR) extends Def
case class FIFOPeak(mem:Memory)(implicit design:PIR) extends Def
case class FIFONumel(mem:Memory)(implicit design:PIR) extends Def
//case class NotEmpty(mem:Memory) extends Def
//case class NotFull(mem:Memory) extends Def
