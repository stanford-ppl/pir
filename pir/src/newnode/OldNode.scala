package pir.oldnode

import pir._

import pirc.enums._
import pirc.util._

import scala.collection.mutable.ListBuffer
import scala.math.max

/** Base class for all PIR nodes. 
  * @param name: optional user name for a node 
  */
@SerialVersionUID(123L)
abstract class Node(implicit design: PIR) extends Serializable { 

  val id : Int = design.nextId // Unique id for each node

  override def equals(that: Any) = that match {
    case n: Node => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }

  override def toString = s"${this.getClass.getSimpleName}${id}${name.fold("")(n => s"_${n}")}" 

  var name:Option[String] = None
  def name(n:String):Unit = this.name = Some(n)
}

abstract class Module(implicit design: PIR) extends Node { 
  implicit val self:Module = this

  var _parent:Option[Module] = None
  def parent:Option[Module] = _parent
  def parent(p:Module) =  _parent = Some(p)
  def unsetParent = _parent = None
  def isParentOf(m:Module) = m.parent == Some(this)

  val _children = ListBuffer[Module]()
  def children = _children.toList
  def addChild(c:Module) = _children += c
  def removeChild(c:Module) = _children -= c

  def isInner = children.isEmpty
  def isOuter = children.nonEmpty

  def _ios = ListBuffer[IO]()
  def addIO(io:IO) = _ios += io
  def removeIO(io:IO) = _ios -= io
  def ios = _ios.toList
  def ins = ios.collect{ case io:Input => io }.toList
  def outs = ios.collect{ case io:Output => io }.toList
}

object Module {

  def addChild(child:Module, parent:Module) = {
    child.parent(parent)
    parent.addChild(child)
  }

  def removeModule(module:Module) = {
    module.ios.foreach { io => io.disconnect }
    module.parent.foreach { parent =>
      parent.removeChild(module)
      module.unsetParent
      (parent.children.filterNot { _ == module } :+ parent).foreach(removeUnusedIOs)
    }
  }

  def moveChild(child:Module, newParent:Module)(implicit design:PIR) = {
    val oldParent = child.parent
    oldParent.foreach { oldParent =>
      oldParent.removeChild(child)
      child.unsetParent
    }
    child.parent(newParent)
    child.ios.foreach { childIO =>
      childIO.connected.foreach {
        case innerPin:InnerPin => 
          val parentIO = innerPin.src
          val newParentIO = copyIO(newParent, parentIO)
          innerPin.disconnectFrom(childIO)
          newParentIO.ic.connect(childIO.asInstanceOf[newParentIO.ic.P])
        case pin => 
      }
    }
    oldParent.foreach(removeUnusedIOs)
  }

  def copyIO(dest:Module, io:IO)(implicit design:PIR) = {
    io match {
      case io@Input(tp) => 
        dest.ins.filter { _.from == io.from }.headOption.fold {
          val newIn = Input(tp)(dest, design)
          newIn.connect(io.from)
          newIn
        } { io => io }
      case io@Output(tp) => 
        val newOut = Output(tp)(dest, design)
        io.to.foreach { to =>
          newOut.connect(to)
        }
        newOut
    }
  }

  def removeUnusedIOs(module:Module):Unit = {
    module.ios.foreach {
      case io:Input => 
        if (module.isOuter && !io.ic.isConnected) {
          val srcs = io.connected.map(_.src).map {
            case src:Module => src
            case io:IO => io.src
          }
          io.disconnect
          module.removeIO(io)
          srcs.foreach(removeUnusedIOs)
        }
      case io:Output =>
        if (io.isConnected) {
          if (module.isOuter && !io.ic.isConnected) {
            err(s"output $io is not defined but used by inputs ${io.connected}")
          }
        } else {
          val innerSrcs = io.ic.connected.map(_.src).map {
            case src:Module => src
            case io:IO => io.src
          }
          io.ic.disconnect
          module.removeIO(io)
          innerSrcs.foreach(removeUnusedIOs)
        }
    }
  }

  def apply[M<:Module](m:M)(implicit parent:Module):M = {
    addChild(m, parent)
    m
  }
}

trait Pin extends Node {
  val tp:IOType
  val src:Node

  type P<:Pin
  protected val _connected = ListBuffer[P]()
  def connected:List[P] = _connected.toList
  def isConnected:Boolean = connected.nonEmpty
  def isConnectedTo(p:Pin) = connected.contains(p)
  def connect(p:P):Unit = {
    if (isConnectedTo(p)) return
    err(this.isInstanceOf[InputPin] && this.isConnected, s"$this is already connected to ${connected.head}, reconnecting to $p")
    _connected += p 
    p.connect(this.asInstanceOf[p.P])
  }

  def disconnectFrom(pin:Pin):Unit = {
    _connected -= pin.asInstanceOf[P]
    if (pin.isConnectedTo(this)) pin.disconnectFrom(this)
  }
  def disconnect = connected.foreach(_.disconnectFrom(this))
}

trait InputPin extends Pin {
  type P = OutputPin
  def from:OutputPin = connected.head
}

trait OutputPin extends Pin {
  type P = InputPin
  def to = connected
}

trait InnerPin extends Pin {
  override val src:IO
  override def toString = s"$src.ic" 
  override def connect(p:P):Unit = {
    p.src match {
      case m:Module => err(!src.src.isParentOf(m), s"Connect connect innerPin $this to $p")
      case io:IO => err(src.src != io.src, s"Connect connect innerPin $this to innerPin $p")
    }
    super.connect(p)
  }
}
case class InnerInputPin(src:IO, tp:IOType)(implicit design:PIR) extends InnerPin with InputPin 
case class InnerOutputPin(src:IO, tp:IOType)(implicit design:PIR) extends InnerPin with OutputPin

abstract class IO(override val src:Module)(implicit design:PIR) extends Pin {
  src.addIO(this)
  val ic:Pin
}

case class Input(tp:IOType)(implicit override val src:Module, design:PIR) extends IO(src) with InputPin {
  override val ic = InnerOutputPin(this, tp)
}

case class Output(tp:IOType)(implicit override val src:Module, design:PIR) extends IO(src) with OutputPin {
  override val ic = InnerInputPin(this, tp)
}

class Container(implicit design:PIR) extends Module {
}

abstract class Memory(implicit design:PIR) extends Module {
}

class SRAM(implicit design:PIR) extends Module {
}
object SRAM {
  def apply(implicit design:PIR, parent:Module) = Module(new SRAM())
}

class Controller(implicit design:PIR) extends Module {
  def chains = children.collect { case c:CounterChain => c }
}

class CounterChain(val original:Controller)(implicit design:PIR) extends Module {
}

class Counter(implicit design:PIR) extends Module {
}

trait Def extends Module {
}
  sealed trait IOType extends Enum
  case object VectorIO extends IOType
  case object ScalarIO extends IOType
  case object ControlIO extends IOType

