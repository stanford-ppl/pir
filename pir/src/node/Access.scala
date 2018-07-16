package pir
package node

trait LocalAccess extends Def
object LocalAccess {
  def unapply(n:Any):Option[(List[Memory], Option[List[Def]])] = n match {
    case LocalLoad(mems, addrs) => Some(mems, addrs)
    case LocalStore(mems, addrs, data) => Some(mems, addrs)
    case LocalReset(mems, reset) => Some(mems, None)
    case _ => None
  }
}
trait LocalLoad extends LocalAccess
object LocalLoad {
  def unapply(n:Any):Option[(List[Memory], Option[List[Def]])] = n match {
    case ReadMem(mem) => Some((List(mem), None))
    case LoadBanks(banks, addrs) => Some((banks, Some(addrs)))
    case LoadMem(mem, faddr) => Some(List(mem), Some(List(faddr)))
    case EnabledLoadMem(mem, faddr, deqEn) => Some((List(mem), faddr.map { a => List(a)}))
    case _ => None
  }
}

trait LocalInAccess extends LocalAccess {
  override def isInputField(field:Any, fieldIdx:Int) = field match {
    case x:Memory => false
    case x:Iterable[_] if x.exists(_.isInstanceOf[Memory]) => false
    case _ => true
  }
  // Add new written memory
  def writes(mem:Memory) = mem.newIn.connect(this.out)
}
trait LocalStore extends LocalInAccess
object LocalStore {
  def unapply(n:Any):Option[(List[Memory], Option[List[Def]], Def)] = n match {
    case WriteMem(mem, data) => Some((List(mem), None, data))
    case StoreBanks(mems, addrs, data) => Some((mems.flatten, Some(addrs), data))
    case StoreMem(mem, faddr, data) => Some((List(mem), Some(List(faddr)), data))
    case EnabledStoreMem(mem, faddr, data, enqEn) => Some((List(mem), faddr.map { a => List(a)}, data))
    case _ => None
  }
}
trait LocalReset extends LocalInAccess
object LocalReset {
  def unapply(n:Any):Option[(List[Memory], Def)] = n match {
    case ResetMem(mem, reset) => Some((List(mem), reset))
    case EnabledResetMem(mem, reset, enqEn) => Some((List(mem), reset))
    case _ => None
  }
}

trait EnabledAccess extends LocalAccess
object EnabledAccess {
  def unapply(n:Any):Option[(List[Memory], Def)] = n match {
    case EnabledLoadMem(mem, faddr, readNext) => Some((List(mem), readNext))
    case EnabledStoreMem(mem, faddr, data, writeNext) => Some((List(mem), writeNext))
    case EnabledResetMem(mem, reset, writeNext) => Some((List(mem), writeNext))
    case _ => None
  }
}

// Write without address
case class ReadMem(mem:Memory)(implicit design:PIRDesign) extends LocalLoad
case class WriteMem(mem:Memory, data:Def)(implicit design:PIRDesign) extends LocalStore
case class ResetMem(mem:Memory, reset:Def)(implicit design:PIRDesign) extends LocalReset

case class LoadBanks(banks:List[Memory], addrs:List[Def])(implicit design:PIRDesign) extends LocalLoad
case class StoreBanks(mems:List[List[Memory]], addrs:List[Def], data:Def)(implicit design:PIRDesign) extends LocalStore
// Lowered
case class LoadMem(mem:Memory, addr:Def)(implicit design:PIRDesign) extends LocalLoad
case class StoreMem(mem:Memory, addr:Def, data:Def)(implicit design:PIRDesign) extends LocalStore
case class BankMerge(bankLoads:List[LocalLoad])(implicit design:PIRDesign) extends Def
case class BankSelect(addrs:List[Def])(implicit design:PIRDesign) extends Def
case class BankMask(mask:Def, exp:Def)(implicit design:PIRDesign) extends Def

// Lowered with control
case class EnabledLoadMem(mem:Memory, faddr:Option[Def], readNext:Def)(implicit design:PIRDesign) extends LocalLoad with EnabledAccess
case class EnabledStoreMem(mem:Memory, faddr:Option[Def], data:Def, writeNext:Def)(implicit design:PIRDesign) extends LocalStore with EnabledAccess
case class EnabledResetMem(mem:Memory, reset:Def, writeNext:Def)(implicit design:PIRDesign) extends LocalReset with EnabledAccess

case class FIFOEmpty(mem:Memory)(implicit design:PIRDesign) extends Def
case class FIFOPeak(mem:Memory)(implicit design:PIRDesign) extends Def
case class FIFONumel(mem:Memory)(implicit design:PIRDesign) extends Def

// Memory Control Signals
case class NotEmpty(mem:Memory)(implicit design:PIRDesign) extends ControlNode
case class NotFull(mem:Memory)(implicit design:PIRDesign) extends ControlNode

trait AccessUtil {
  def memsOf(n:LocalAccess) = {
    n match {
      case n:LocalStore => n.collect[Memory](visitFunc=n.visitGlobalOut, depth=2)
      case n:LocalLoad => n.collect[Memory](visitFunc=n.visitGlobalIn, depth=2)
      case n:LocalReset => n.collect[Memory](visitFunc=n.visitGlobalOut, depth=2)
    }
  }

  def dataOf(n:LocalStore) = {
    val Def(writer, LocalStore(mems, addrs, data)) = n
    data
  }

  def accessNextOf(n:PIRNode) = {
    n match {
      case Def(n,EnabledLoadMem(mem, faddr, readNext)) => readNext
      case Def(n,EnabledStoreMem(mem, faddr, data, writeNext)) => writeNext
      case Def(n,EnabledResetMem(mem, reset, writeNext)) => writeNext
    }
  }

  def resetersOf(mem:Memory):List[LocalReset] = {
    mem.collect[LocalReset](visitFunc=mem.visitGlobalIn, depth=2)
  }

  def writersOf(mem:Memory):List[LocalStore] = {
    mem.collect[LocalStore](visitFunc=mem.visitGlobalIn, depth=2)
  }

  def readersOf(mem:Memory):List[LocalLoad] = {
    mem.collect[LocalLoad](visitFunc=mem.visitGlobalOut, depth=2)
  }

  def inAccessesOf(mem:Memory):List[LocalAccess] = resetersOf(mem) ++ writersOf(mem)
  def outAccessesOf(mem:Memory):List[LocalAccess] = readersOf(mem)

  def accessesOf(mem:Memory):List[LocalAccess] = writersOf(mem) ++ readersOf(mem) ++ resetersOf(mem)

  def isInAccess(n:PIRNode) = n match {
    case n:LocalLoad => false
    case n:LocalStore => true
    case n:LocalReset => true
    case n => false
  }

  def isOutAccess(n:PIRNode) = n match {
    case n:LocalLoad => true
    case n:LocalStore => false
    case n:LocalReset => false
    case n => false
  }

  object WithWriters {
    def unapply(n:Any):Option[List[LocalStore]] = n match {
      case n:Memory => Some(writersOf(n).toList)
      case _ => None
    }
  }
  object WithWriter {
    def unapply(n:Any):Option[LocalStore] = n match {
      case n:Memory if writersOf(n).size == 1 => Some(writersOf(n).head)
      case _ => None
    }
  }
  object WithReaders {
    def unapply(n:Any):Option[List[LocalLoad]] = n match {
      case n:Memory => Some(readersOf(n).toList)
      case _ => None
    }
  }
  object WithReader {
    def unapply(n:Any):Option[LocalLoad] = n match {
      case n:Memory if readersOf(n).size == 1 => Some(readersOf(n).head)
      case _ => None
    }
  }

}
