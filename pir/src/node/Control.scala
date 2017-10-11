package pir.node

import pir._

import pirc.enums._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

abstract class CtrlPrimitive(implicit ctrlBox:CtrlBox, design:PIR) extends Primitive {
  def ctrler = ctrlBox.ctrler 
}

abstract class UDCounter(implicit ctrlBox:CtrlBox, design:PIR) extends CtrlPrimitive {
  val initVal:Int
  def initOnStart:Boolean
  val inc = Input(this, s"${this}.inc")
  val dec = Input(this, s"${this}.dec")
  val init = Input(this, s"${this}.init")
  val out = Output(this, s"${this}.out")
}
/* TokenBuffer represents the forward data dependency.
 * @param dep depended compute unit where data is from. None if if allocated in first stage, in
 * which case used for handling token from parent and collect token from last stage. */
case class TokenBuffer(dep:Any)
  (implicit ctrlBox:CtrlBox, design:PIR) extends UDCounter{
  override val typeStr = "TokBuf"
  val initVal = 0
  def initOnStart = false
  ctrler.ctrlBox.tokenBuffers += dep -> this
}
case class CreditBuffer(deped:Any, initVal:Int)(implicit ctrlBox:CtrlBox, design:PIR) 
  extends UDCounter{
  override val typeStr = "CredBuf"
  def initOnStart = true 
  ctrler.ctrlBox.creditBuffers += deped -> this
}

case class TransferFunction(tf:(Map[Output, Int], List[Boolean]) => Boolean, info:String)
object TransferFunction {
  def apply(info:String)(transFunc:(Map[Output, Int], List[Boolean]) => Boolean):TransferFunction = {
    TransferFunction(transFunc, info)
  }
}
abstract class LUT(implicit ctrlBox:CtrlBox, design:PIR) extends CtrlPrimitive {
  val transFunc:TransferFunction
  val numIns:Int
  (0 until numIns).foreach { i => Input(this,s"${this}.in$i") }
  val out = Output(this, s"${this}.out")
}
case class TokenDownLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrlBox:CtrlBox, design:PIR) extends LUT {
  override val typeStr = "TokDownLUT"
  //ctrler.ctrlBox.tokDownLUTs += this
}
object TokenDownLUT {
  def apply(cu:Controller, outs:List[Output], transFunc:TransferFunction)
  (implicit design:PIR):Output = {
    val lut = TokenDownLUT(outs.size, transFunc)(cu.ctrlBox, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    lut.out
  }
}
case class TokenOutLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrlBox:CtrlBox, design:PIR) extends LUT {
  override val typeStr = "TokOutLUT"
  //ctrler.ctrlBox.tokOutLUTs += this
}
object TokenOutLUT {
  def apply(cu:Controller, outs:List[Output], transFunc:TransferFunction)
  (implicit design: PIR):Output = {
    val lut = TokenOutLUT(outs.size, transFunc)(cu.ctrlBox, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    lut.out
  }
}
case class EnLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrlBox:CtrlBox, design:PIR) extends LUT {
  override val typeStr = "EnLUT"
  //ctrler.ctrlBox.enLUTs += this
}
object EnLUT {
  def apply(outs:List[Output], transFunc:TransferFunction)
  (implicit ctrlBox:CtrlBox, design:PIR):EnLUT = {
    val lut = EnLUT(outs.size, transFunc)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    lut
  }
}
class AndTree(implicit ctrlBox:CtrlBox, design:PIR) extends CtrlPrimitive {
  override val typeStr = "AndTree"
  val out = Output(this, s"$this.out")
  ctrlBox.andTrees += this
  def addInput(input:Output):Input = {
    val idx = ins.size
    val ip = Input(this, s"$this.in${idx}")
    ip.connect(input)
    ip
  }
  def addInputs(inputs:List[Output]) = {
    inputs.map(input => addInput(input))
 }
}
object AndTree {
  def apply(inputs:Output*)(implicit ctrlBox:CtrlBox, design:PIR):AndTree = {
    val at = new AndTree()
    at.addInputs(inputs.toList)
    at
  }
  def apply(name:String)(implicit ctrlBox:CtrlBox, design:PIR):AndTree = new AndTree().name(name)
  def apply()(implicit ctrlBox:CtrlBox, design:PIR):AndTree = new AndTree()
}

case class PredicateUnit(op:FixOp, const:Int) (implicit ctrlBox:CtrlBox, design:PIR) extends CtrlPrimitive {
  ctrlBox.predicateUnits += this
  override val typeStr = "PredicateUnit"
  val in = Input(this, s"$this.in")
  val out = Output(this, s"$this.out")
}

abstract class CtrlBox()(implicit ctrler:Controller, design:PIR) extends Primitive {
  implicit def ctrlBox:CtrlBox = this

  val tokenBuffers = Map[Any, TokenBuffer]() // Mem or Parent
  val creditBuffers = Map[Any, CreditBuffer]()
  def udcounters:Map[Any, UDCounter] = tokenBuffers ++ creditBuffers
  val andTrees = ListBuffer[AndTree]()
  val delays = ListBuffer[Delay]()
  val predicateUnits = ListBuffer[PredicateUnit]()
  var tokenOut:Option[Output] = None 
  // only outer controller have token down, which is the init signal first child stage

  def tokenBuffer(dep:Any):TokenBuffer = { TokenBuffer(dep) }
  def creditBuffer(deped:Any, initVal:Int):CreditBuffer = { CreditBuffer(deped, initVal) }
  def enLut(outs:List[Output], transFunc:TransferFunction) = { EnLUT(outs.size, transFunc) }

  override def toUpdate = super.toUpdate || tokenOut == null
}

class Delay()(implicit ctrlBox:CtrlBox, design:PIR) extends CtrlPrimitive {
  override val typeStr = "Delay"
  val in = Input(this, s"$this.in") 
  val out = Output(this, s"$this.out")
  ctrlBox.delays += this
}
object Delay {
  def apply(n:String)(implicit ctrlBox:CtrlBox, design:PIR):Delay = new Delay().name(n)
  def apply()(implicit ctrlBox:CtrlBox, design:PIR):Delay = new Delay()
}

trait StageCtrlBox extends CtrlBox {
  val siblingAndTree = AndTree("SiblingAndTree")

  val en = Delay(s"$this.en")
  val done = Delay(s"$this.done")

  def enOut:Output
  def doneOut:Output
}

class InnerCtrlBox()(implicit override val ctrler:InnerController, design: PIR) 
  extends CtrlBox() with StageCtrlBox {
  override val typeStr = "InnerCtrlBox"
  val enDelay = Delay(s"$this.enDelay")
  val doneDelay = Delay(s"$this.doneDelay")
  def enOut = enDelay.out
  def doneOut = doneDelay.out

  val fifoAndTree = AndTree("FIFOAndTree")
  val tokenInAndTree = AndTree("TokenInAndTree")

  var accumPredUnit:Option[PredicateUnit] = None
  var fifoPredUnit:Option[PredicateUnit] = None
  def setAccumPredicate(ctr:Counter, op:FixOp, const:Int):PredicateUnit = {
    accumPredUnit = Some(PredicateUnit(op, const).name("accumPredUnit"))
    accumPredUnit.foreach{ _.in.connect(ctr.out) }
    accumPredUnit.get
  }
  def setFifoPredicate(ctr:Counter, op:FixOp, const:Int):PredicateUnit = {
    assert(fifoPredUnit.isEmpty, s"Assume a single FifoPredicateUnit in each controller $ctrler")
    fifoPredUnit = Some(PredicateUnit(op, const).name("fifoPredUnit"))
    fifoPredUnit.foreach{ _.in.connect(ctr.out) }
    fifoPredUnit.get
  }
}
object InnerCtrlBox {
  def apply()(implicit ctrler:InnerController, design: PIR) = new InnerCtrlBox()
} 

class OuterCtrlBox()(implicit override val ctrler:Controller, design: PIR) extends CtrlBox() with StageCtrlBox {
  override val typeStr = "OuterCtrlBox"
  def enOut = en.out
  val doneOut = Output(this, s"$this.doneOut")
  val childrenAndTree = AndTree("ChildrenAndTree")
}
object OuterCtrlBox {
  def apply()(implicit ctrler:Controller, design:PIR) = { new OuterCtrlBox() }
}

case class TopCtrlBox()(implicit override val ctrler:Controller, design: PIR) extends CtrlBox {
  override val typeStr = "TopCtrlBox"
  val status = Input(this, s"$this.status")
  val command = Output(this, s"$this.command")
}

case class MemCtrlBox()(implicit override val ctrler:MemoryPipeline, design: PIR) extends CtrlBox() {
  override val typeStr = "MemCtrlBox"
  val tokenInAndTree = AndTree("TokenInAndTree")
  val readFifoAndTree = AndTree("ReadFIFOAndTree")
  val writeFifoAndTree = AndTree("WriteFIFOAndTree")

  val readEn = Delay(s"$this.readEn")
  val readEnDelay = Delay(s"$this.readEnDelay")
  val readDone = Delay(s"$this.readDone")
  val readDoneDelay = Delay(s"$this.readDoneDelay")

  val writeEn = Delay(s"$this.writeEn")
  val writeEnDelay = Delay(s"$this.writeEnDelay")
  val writeDone = Delay(s"$this.writeDone")
  val writeDoneDelay = Delay(s"$this.writeDoneDelay")
}

case class MCCtrlBox()(implicit override val ctrler:MemoryController, design: PIR) extends StageCtrlBox() {
  override val typeStr = "MCCtrlBox"
  def enOut = en.out
  def doneOut = done.out
  val running = Output(this, s"$this.running")
  val fifoAndTree = AndTree("FIFOAndTree")
}


