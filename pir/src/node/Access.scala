package pir
package node

trait LocalAccess extends Def
trait LocalLoad extends LocalAccess
object LocalLoad {
  def unapply(n:Any):Option[(List[Memory], Option[List[Def]])] = n match {
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
    case x:Iterable[_] if x.exists(_.isInstanceOf[Memory]) => false
    case _ => true
  }
}
object LocalStore {
  def unapply(n:Any):Option[(List[Memory], Option[List[Def]], Def)] = n match {
    case WriteMem(mem, data) => Some((List(mem), None, data))
    case StoreBanks(banks, addrs, data) => Some((banks, Some(addrs), data))
    case StoreMem(mem, addrs, data) => Some((List(mem), Some(addrs), data))
    case EnabledStoreMem(mem, addrs, data, enqEn) => Some((List(mem), addrs, data))
    case _ => None
  }
}

object WithWriters {
  def unapply(n:Any)(implicit pass:PIRPass):Option[List[LocalStore]] = n match {
    case n:Memory => Some(writersOf(n).toList)
    case _ => None
  }
}
object WithWriter {
  def unapply(n:Any)(implicit pass:PIRPass):Option[LocalStore] = n match {
    case n:Memory if writersOf(n).size == 1 => Some(writersOf(n).head)
    case _ => None
  }
}
object WithReaders {
  def unapply(n:Any)(implicit pass:PIRPass):Option[List[LocalLoad]] = n match {
    case n:Memory => Some(readersOf(n).toList)
    case _ => None
  }
}
object WithReader {
  def unapply(n:Any)(implicit pass:PIRPass):Option[LocalLoad] = n match {
    case n:Memory if readersOf(n).size == 1 => Some(readersOf(n).head)
    case _ => None
  }
}

// Write without address
case class ReadMem(mem:Memory)(implicit design:PIRDesign) extends LocalLoad
case class WriteMem(mem:Memory, data:Def)(implicit design:PIRDesign) extends LocalStore

case class LoadBanks(banks:List[Memory], addrs:List[Def])(implicit design:PIRDesign) extends LocalLoad
case class StoreBanks(banks:List[Memory], addrs:List[Def], data:Def)(implicit design:PIRDesign) extends LocalStore
// Lowered
case class LoadMem(mem:Memory, addrs:List[Def])(implicit design:PIRDesign) extends LocalLoad
case class StoreMem(mem:Memory, addrs:List[Def], data:Def)(implicit design:PIRDesign) extends LocalStore
case class SelectBanks(bankLoads:List[LocalLoad])(implicit design:PIRDesign) extends Def

// Lowered with control
case class EnabledLoadMem(mem:Memory, addrs:Option[List[Def]], readNext:Def)(implicit design:PIRDesign) extends LocalLoad
case class EnabledStoreMem(mem:Memory, addrs:Option[List[Def]], data:Def, writeNext:Def)(implicit design:PIRDesign) extends LocalStore

case class FIFOEmpty(mem:Memory)(implicit design:PIRDesign) extends Def
case class FIFOPeak(mem:Memory)(implicit design:PIRDesign) extends Def
case class FIFONumel(mem:Memory)(implicit design:PIRDesign) extends Def

// Memory Control Signals
case class NotEmpty(mem:Memory)(implicit design:PIRDesign) extends ControlNode
case class NotFull(mem:Memory)(implicit design:PIRDesign) extends ControlNode

trait PIRAccess {
  def memsOf(n:LocalAccess) = {
    n match {
      case n:LocalStore => n.collect[Memory](visitFunc=n.visitGlobalOut, depth=2)
      case n:LocalLoad => n.collect[Memory](visitFunc=n.visitGlobalIn, depth=2)
    }
  }

  def dataOf(n:LocalStore) = {
    val Def(writer, LocalStore(mems, addrs, data)) = n
    data
  }

  def accessNextOf(n:PIRNode) = {
    n match {
      case Def(n,EnabledLoadMem(mem, addrs, readNext)) => readNext
      case Def(n,EnabledStoreMem(mem, addrs, data, writeNext)) => writeNext
    }
  }

  def writersOf(mem:Memory):List[LocalStore] = {
    mem.collect[LocalStore](visitFunc=mem.visitGlobalIn)
  }

  def readersOf(mem:Memory):List[LocalLoad] = {
    def visitFunc(n:PIRNode):List[PIRNode] = n match {
      case n:NotEmpty => Nil
      case n:NotFull => Nil
      case n => n.visitGlobalOut(n)
    }
    mem.collect[LocalLoad](visitFunc={ 
      case n:NotEmpty => Nil
      case n:NotFull => Nil
      case n => mem.visitGlobalOut(n) 
    })
  }

  def accessesOf(mem:Memory):List[LocalAccess] = writersOf(mem) ++ readersOf(mem)

  def globalOf(n:PIRNode) = {
    n.collectUp[GlobalContainer]().headOption
  }

  def contextOf(n:PIRNode) = {
    n.collectUp[ComputeContext]().headOption
  }

  def ctrlsOf(container:Container) = {
    implicit val design = container.design.asInstanceOf[PIRDesign]
    import design.pirmeta._
    container.collectDown[ComputeNode]().flatMap { comp => ctrlOf.get(comp) }.toSet[Controller]
  }

}
