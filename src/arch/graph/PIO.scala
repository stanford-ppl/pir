package pir.plasticine.graph

import pir.graph._
import pir.util.enums._
import pir.codegen._
import pir.plasticine.main._
import pir.plasticine.util._
import pir.plasticine.simulation._

import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import scala.language.existentials

trait PortType extends Value
/* Three types of pin */
case class Bit() extends PortType with BitValue
case class Word(wordWidth:Int) extends PortType with WordValue
object Word {
  def apply()(implicit spade:Spade):Word = Word(spade.wordWidth)
}
case class Bus(busWidth:Int, elemTp:PortType) extends PortType with BusValue
object Bus {
  def apply(elemTp:PortType)(implicit spade:Spade):Bus = Bus(spade.wordWidth, elemTp)
}

/* 
 * An input port of a module that can be recofigured to other's output ports
 * fanIns stores the list of ports the input port can configured to  
 * */
abstract class IO[P<:PortType, +S<:Module](val tp:P, val src:S)(implicit spade:Spade) extends Node {
  import spademeta._
  src.addIO(this)
  override val typeStr = {
    var s = this match {
      case _:Input[_,_] => s"i"
      case _:Output[_,_] => s"o"
    }
    s += (tp match {
      case Bit() => "b"
      case Word(w) => "w"
      case Bus(w, tp) => "u"
    })
    s
  } 
  override def toString =s"${super.toString}${spademeta.indexOf.get(this).fold(""){idx=>s"[$idx]"}}"
  def isConnected: Boolean
  def disconnect:Unit
  def canConnect(n:Any):Boolean
  def indexOf(n:Any):Int

  def isIn:Boolean
  def isOut:Boolean
  def asIn = this.asInstanceOf[Input[P,S]]
  def asOut = this.asInstanceOf[Output[P,S]]
  def isBus = tp.isInstanceOf[Bus]
  def isWord = tp.isInstanceOf[Word]
  def isBit = tp.isInstanceOf[Bit]
  def asBus:IO[Bus, S]
  def asWord:IO[Word, S]
  def asBit:IO[Bit, S]
  def asGlobal:GlobalIO[P, S]

  val v:Val[P] = Val(this)
  def ev(implicit sim:Simulator) = { v.eval }
}

/* Input pin. Can only connects to output of the same level */
class Input[P<:PortType, +S<:Module](tp:P, src:S, sf: Option[()=>String])(implicit spade:Spade) extends IO[P, S](tp, src) { 
  import spademeta._
  type O = Output[P, Module]
  // List of connections that can map to
  val _fanIns = ListBuffer[O]()
  def fanIns = _fanIns.toList
  def connect(n:O):Unit = { _fanIns += n; n.connectedTo(this) }
  private[plasticine] def <==(n:O) = { connect(n) }
  private[plasticine] def <==(ns:List[O]) = ns.foreach(n => connect(n))
  private[plasticine] def <==(r:PipeReg):Unit = { this.asBus.connect(r.out) }
  private[plasticine] def <==(n:Output[Bus, Module], i:Int) = n.slice(i, this)
  private[plasticine] def <==(ns:List[Output[Bus, Module]], i:Int) = ns.foreach(_.slice(i, this))
  private[plasticine] def <-- (n:Output[_, Module]) = n.broadcast(this.asBus)
  def ms = s"${this}=mp[${_fanIns.mkString(",")}]"
  def canConnect(n:Any):Boolean = {
    _fanIns.contains(n) || _fanIns.map(_.src).collect{case s:Slice[_] => s.in; case b:BroadCast[_] => b.in }.exists(_.canConnect(n))
  }
  def indexOf(o:Any):Int = {
    _fanIns.map { out =>
      out.src match {
        case s:Slice[_] if s.in.canConnect(o) => o
        case b:BroadCast[_] if b.in.canConnect(o) => o
        case _ => out
      }
    }.indexOf(o)
  }
  def isConnected = _fanIns.size!=0
  def disconnect = _fanIns.clear
  def isIn:Boolean = true
  def isOut:Boolean = false
  override def asBus:Input[Bus, S] = this.asInstanceOf[Input[Bus, S]]
  override def asWord:Input[Word, S] = this.asInstanceOf[Input[Word, S]]
  override def asBit:Input[Bit, S] = this.asInstanceOf[Input[Bit, S]]
  override def asGlobal:GlobalInput[P, S] = this.asInstanceOf[GlobalInput[P, S]]
  override def toString():String = sf.fold(super.toString) { sf => sf() }
}
object Input {
  def apply[P<:PortType, S<:Module](t:P, s:S)(implicit spade:Spade):Input[P, S] = new Input[P, S](t, s, None)
  def apply[P<:PortType, S<:Module](t:P, s:S, sf: =>String)(implicit spade:Spade):Input[P, S] = 
    new Input[P, S](t,s, Some(sf _))
} 

/* Output pin. Can only connects to input of the same level */
class Output[P<:PortType, +S<:Module](tp:P, src:S, sf: Option[()=>String])(implicit spade:Spade) extends IO[P, S](tp, src) { 
  import spademeta._
  type I = Input[P, Module]
  val _fanOuts = ListBuffer[I]()
  def fanOuts = _fanOuts.toList
  def connectedTo(n:I):Unit = _fanOuts += n
  private[plasticine] def ==>(n:I):Unit = { n.connect(this.asInstanceOf[n.O]) }
  private[plasticine] def ==>(ns:List[I]):Unit = ns.foreach { n => ==>(n) }
  def mt = s"${this}=mt[${_fanOuts.mkString(",")}]" 
  def canConnect(n:Any):Boolean = {
    _fanOuts.contains(n) || _fanOuts.map(_.src).collect{case s:Slice[_] => s.out; case b:BroadCast[_] => b.out}.exists(_.canConnect(n))
  }
  def indexOf(i:Any):Int = {
    _fanOuts.map { in =>
      in.src match {
        case s:Slice[_] if s.out.canConnect(i) => i
        case b:BroadCast[_] if b.out.canConnect(i) => i 
        case _ => in
      }
    }.indexOf(i)
  }
  def isConnected = _fanOuts.size!=0
  def disconnect = _fanOuts.clear
  def isIn:Boolean = false
  def isOut:Boolean = true
  override def asBus:Output[Bus, S] = this.asInstanceOf[Output[Bus, S]]
  override def asWord:Output[Word, S] = this.asInstanceOf[Output[Word, S]]
  override def asBit:Output[Bit, S] = this.asInstanceOf[Output[Bit, S]]
  override def asGlobal:GlobalOutput[P, S] = this.asInstanceOf[GlobalOutput[P, S]]
  override def toString():String = sf.fold(super.toString) { sf => sf() }

  def slice(i:Int, in:Input[_<:PortType,Module]) = Slice(in, this.asBus, i)
  def sliceHead(in:Input[_<:PortType,Module]) = slice(0, in)
  def broadcast(in:Input[Bus, Module]) = BroadCast(this, in)
} 
object Output {
  def apply[P<:PortType, S<:Module](t:P, s:S)(implicit spade:Spade):Output[P, S] = new Output[P,S](t, s, None)
  def apply[P<:PortType, S<:Module](t:P, s:S, sf: =>String)(implicit spade:Spade):Output[P, S] = 
    new Output[P, S](t,s, Some(sf _))
}

trait GlobalIO[P<:PortType, +S<:Module] extends IO[P, S] with Simulatable {
  val ic:IO[P, this.type]
}

/* Input pin of network element. Has an innernal output */
class GlobalInput[P<:PortType, +S<:Module](tp:P, src:S, sf: Option[()=>String])(implicit spade:Spade)
  extends Input(tp, src, sf) with GlobalIO[P,S] { 
  import spademeta._
  override val ic:Output[P, this.type] = new Output(tp, this, sf)
  override def register(implicit sim:Simulator):Unit = {
    super.register
    ic.v <= this
  }
}
object GlobalInput {
  def apply[P<:PortType, S<:Module](t:P, s:S)(implicit spade:Spade):GlobalInput[P, S] = new GlobalInput[P, S](t, s, None)
  def apply[P<:PortType, S<:Module](t:P, s:S, sf: =>String)(implicit spade:Spade):GlobalInput[P, S] = 
    new GlobalInput[P, S](t,s, Some(sf _))
} 

/* Output pin. Can only connects to input of the same level */
class GlobalOutput[P<:PortType, +S<:Module](tp:P, src:S, sf: Option[()=>String])(implicit spade:Spade) 
  extends Output(tp, src, sf) with GlobalIO[P, S] { 
  import spademeta._
  override val ic:Input[P, this.type] = new Input(tp, this, sf)
  override def register(implicit sim:Simulator):Unit = {
    super.register
    this.v <= ic
  }
} 
object GlobalOutput {
  def apply[P<:PortType, S<:Module](t:P, s:S)(implicit spade:Spade):GlobalOutput[P, S] = new GlobalOutput[P,S](t, s, None)
  def apply[P<:PortType, S<:Module](t:P, s:S, sf: =>String)(implicit spade:Spade):GlobalOutput[P, S] = 
    new GlobalOutput[P, S](t,s, Some(sf _))
}

case class Slice[P<:PortType](din:Input[P,Module], dout:Output[Bus,Module], i:Int)(implicit spade:Spade) extends Simulatable {
  override val typeStr = "slice"
  val in = Input(dout.tp, this, s"${this}.in").asBus
  val out = Output(din.tp, this, s"${this}.out")
  in <== dout
  din <== out
  override def register(implicit sim:Simulator):Unit = {
    super.register
    out.v.set { v => v.value.copy(in.ev.value.value(i)) }
  }
}

case class BroadCast[P<:PortType](dout:Output[P,Module], din:Input[Bus, Module])(implicit spade:Spade) extends Simulatable {
  override val typeStr = "broadcast"
  val in = Input(dout.tp, this, s"${this}.in")
  val out = Output(din.tp, this, s"${this}.out")
  in <== dout
  din <== out
  override def register(implicit sim:Simulator):Unit = {
    super.register
    out.v.set { v => v.value.value.foreach{ _.copy(in.ev.value) } }
  }
}

trait GridIO[P <:PortType, +NE<:NetworkElement] extends Node {
  import spademeta._
  private val inMap = Map[String, ListBuffer[Input[P, _]]]()
  private val outMap = Map[String, ListBuffer[Output[P, _]]]()

  def src:NE
  def tp:P
  def inputs(num:Int)(implicit spade:Spade, nt:GridNetwork):List[GlobalInput[P, NE]] = 
    List.tabulate(num) { i => val in = GlobalInput(tp, src); networkOf(in) = nt; in }
  def outputs(num:Int)(implicit spade:Spade, nt:GridNetwork):List[GlobalOutput[P, NE]] = 
    List.tabulate(num) { i => val out = GlobalOutput(tp, src); networkOf(out) = nt; out }
  def addInAt(dir:String, num:Int)(implicit spade:Spade, nt:GridNetwork):List[GlobalInput[P, NE]] = { 
    val ibs = inputs(num)
    inMap.getOrElseUpdate(dir, ListBuffer.empty) ++= ibs
    ibs
  }
  def addOutAt(dir:String, num:Int)(implicit spade:Spade, nt:GridNetwork):List[GlobalOutput[P, NE]] = {
    val obs = outputs(num)
    outMap.getOrElseUpdate(dir, ListBuffer.empty) ++= obs
    obs
  }
  def addIOAt(dir:String, num:Int)(implicit spade:Spade, nt:GridNetwork):NE = {
    addInAt(dir,num)
    addOutAt(dir,num)
    src
  }
  def addIns(num:Int)(implicit spade:Spade, nt:GridNetwork):NE = { 
    addInAt("N", num)
    src
  }
  def addOuts(num:Int)(implicit spade:Spade, nt:GridNetwork):NE = {
    addOutAt("N", num)
    src
  }
  def inAt(dir:String):List[GlobalInput[P, NE]] = { 
    inMap.getOrElse(dir, ListBuffer.empty).toList.asInstanceOf[List[GlobalInput[P, NE]]]
  }
  def outAt(dir:String):List[GlobalOutput[P, NE]] = { 
    outMap.getOrElse(dir, ListBuffer.empty).toList.asInstanceOf[List[GlobalOutput[P, NE]]]
  }
  def ins:List[GlobalInput[P, NE]] = GridIO.eightDirections.flatMap { dir => inAt(dir) } 
  def outs:List[GlobalOutput[P, NE]] = GridIO.eightDirections.flatMap { dir => outAt(dir) }  
  def ios:List[IO[P, NE]] = ins ++ outs
  def numIns:Int = inMap.values.map(_.size).sum
  def numOuts:Int = outMap.values.map(_.size).sum
  def io(in:Input[P, NetworkElement]):String = {
    val dirs = inMap.filter{ case (dir, l) => l.contains(in) }
    assert(dirs.size==1)
    val (dir, list) = dirs.head
    s"${dir.toLowerCase}_${list.indexOf(in)}"
  }
  def clearIO:Unit = {
    inMap.clear
    outMap.clear
  }
}
object GridIO {
  def fourDirections = { "W" :: "N" :: "E" :: "S" ::Nil }
  def eightDirections = { "W" :: "NW" :: "N" :: "NE" :: "E" ::  "SE" :: "S" :: "SW" ::Nil }
  def diagDirections = {"NW":: "NE":: "SE":: "SW" :: Nil}
}

case class ScalarIO[+N<:NetworkElement](src:N)(implicit spade:Spade) extends GridIO[ScalarIO.P, N] {
  override def toString = s"${src}.scalarIO"
  override val tp = Word()
}
object ScalarIO {
  type P = Word 
}

case class VectorIO[+N<:NetworkElement](src:N)(implicit spade:Spade) extends GridIO[VectorIO.P, N] {
  override def toString = s"${src}.vectorIO"
  override val tp = Bus(spade.numLanes, Word())
}
object VectorIO {
  type P = Bus
}

case class ControlIO[+N<:NetworkElement](src:N)(implicit spade:Spade) extends GridIO[ControlIO.P, N] {
  override def toString = s"${src}.ctrlIO"
  override val tp = Bit()
}
object ControlIO {
  type P = Bit 
}
