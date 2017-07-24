package pir.graph

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.math.max
import scala.reflect.runtime.universe._
import pir.Design
import pir.graph._
import pir.util.enums._
import pir.exceptions._
import pir.pass.ForwardRef


abstract class CtrlPrimitive(implicit ctrlBox:CtrlBox, design:Design) extends Primitive()(ctrlBox.ctrler, design) {
}

abstract class UDCounter(implicit ctrlBox:CtrlBox, design:Design) extends CtrlPrimitive {
  val initVal:Int
  def initOnStart:Boolean
  val inc = InPort(this, s"${this}.inc")
  val dec = InPort(this, s"${this}.dec")
  val init = InPort(this, s"${this}.init")
  val out = OutPort(this, s"${this}.out")
}
/* TokenBuffer represents the forward data dependency.
 * @param dep depended compute unit where data is from. None if if allocated in first stage, in
 * which case used for handling token from parent and collect token from last stage. */
case class TokenBuffer(dep:Any)
  (implicit ctrlBox:CtrlBox, design:Design) extends UDCounter{
  override val name = None
  override val typeStr = "TokBuf"
  val initVal = 0
  def initOnStart = false
  ctrler.ctrlBox.tokenBuffers += dep -> this
}
case class CreditBuffer(deped:Any, initVal:Int)(implicit ctrlBox:CtrlBox, design:Design) 
  extends UDCounter{
  override val name = None
  override val typeStr = "CredBuf"
  def initOnStart = true 
  ctrler.ctrlBox.creditBuffers += deped -> this
}

case class TransferFunction(tf:(Map[OutPort, Int], List[Boolean]) => Boolean, info:String)
object TransferFunction {
  def apply(info:String)(transFunc:(Map[OutPort, Int], List[Boolean]) => Boolean):TransferFunction = {
    TransferFunction(transFunc, info)
  }
}
abstract class LUT(implicit ctrlBox:CtrlBox, design:Design) extends CtrlPrimitive {
  override val name = None
  val transFunc:TransferFunction
  val numIns:Int
  (0 until numIns).foreach { i => InPort(this,s"${this}.in$i") }
  val out = OutPort(this, s"${this}.out")
}
case class TokenDownLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrlBox:CtrlBox, design:Design) extends LUT {
  override val typeStr = "TokDownLUT"
  //ctrler.ctrlBox.tokDownLUTs += this
}
object TokenDownLUT {
  def apply(cu:Controller, outs:List[OutPort], transFunc:TransferFunction)
  (implicit design:Design):OutPort = {
    val lut = TokenDownLUT(outs.size, transFunc)(cu.ctrlBox, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    lut.out
  }
}
case class TokenOutLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrlBox:CtrlBox, design:Design) extends LUT {
  override val typeStr = "TokOutLUT"
  //ctrler.ctrlBox.tokOutLUTs += this
}
object TokenOutLUT {
  def apply(cu:Controller, outs:List[OutPort], transFunc:TransferFunction)
  (implicit design: Design):OutPort = {
    val lut = TokenOutLUT(outs.size, transFunc)(cu.ctrlBox, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    lut.out
  }
}
case class EnLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrlBox:CtrlBox, design:Design) extends LUT {
  override val typeStr = "EnLUT"
  //ctrler.ctrlBox.enLUTs += this
}
object EnLUT {
  def apply(outs:List[OutPort], transFunc:TransferFunction)
  (implicit ctrlBox:CtrlBox, design:Design):EnLUT = {
    val lut = EnLUT(outs.size, transFunc)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    lut
  }
}
class AndTree(val name:Option[String])(implicit ctrlBox:CtrlBox, design:Design) extends CtrlPrimitive {
  override val typeStr = "AndTree"
  val out = OutPort(this, s"$this.out")
  ctrlBox.andTrees += this
  def addInput(input:OutPort):InPort = {
    val idx = ins.size
    val ip = InPort(this, s"$this.in${idx}")
    ip.connect(input)
    ip
  }
  def addInputs(inputs:List[OutPort]) = {
    inputs.map(input => addInput(input))
 }
}
object AndTree {
  def apply(inputs:OutPort*)(implicit ctrlBox:CtrlBox, design:Design):AndTree = {
    val at = new AndTree(None)
    at.addInputs(inputs.toList)
    at
  }
  def apply(n:String)(implicit ctrlBox:CtrlBox, design:Design):AndTree = new AndTree(Some(n))
  def apply()(implicit ctrlBox:CtrlBox, design:Design):AndTree = new AndTree(None)
}

case class PredicateUnit(name:Option[String], op:FixOp, const:Int) (implicit ctrlBox:CtrlBox, design:Design) extends CtrlPrimitive {
  ctrlBox.predicateUnits += this
  override val typeStr = "PredicateUnit"
  val in = InPort(this, s"$this.in")
  val out = OutPort(this, s"$this.out")
}

abstract class CtrlBox()(implicit ctrler:Controller, design:Design) extends Primitive {
  implicit def ctrlBox:CtrlBox = this
  override val name = None
  override val typeStr = "CtrlBox"

  val tokenBuffers = Map[Any, TokenBuffer]() // Mem or Parent
  val creditBuffers = Map[Any, CreditBuffer]()
  def udcounters:Map[Any, UDCounter] = tokenBuffers ++ creditBuffers
  val andTrees = ListBuffer[AndTree]()
  val delays = ListBuffer[Delay]()
  val predicateUnits = ListBuffer[PredicateUnit]()
  var tokenOut:Option[OutPort] = None 
  // only outer controller have token down, which is the init signal first child stage

  def tokenBuffer(dep:Any):TokenBuffer = { TokenBuffer(dep) }
  def creditBuffer(deped:Any, initVal:Int):CreditBuffer = { CreditBuffer(deped, initVal) }
  def enLut(outs:List[OutPort], transFunc:TransferFunction) = { EnLUT(outs.size, transFunc) }

  def ctrlIns:List[InPort] = {
    val cins = ListBuffer[InPort]()
    cins ++= udcounters.values.map(_.inc)
    cins ++= andTrees.flatMap(_.ins)
    cins ++= delays.map(_.in)
    ctrler match {
      case top:Top =>
      case cu:ComputeUnit => 
        cins ++= cu.cchains.map(_.inner.en)
        cins ++= cu.fifos.map { _.enqueueEnable }
        cins ++= cu.mbuffers.map{ _.swapRead }
        cins ++= cu.mbuffers.map{ _.swapWrite }
    }
    cins ++= ins
    cins.toSet.filter{_.isGlobal}.toList
  }

  def ctrlOuts:List[OutPort] = { 
    val couts = ListBuffer[OutPort]()
    ctrler match {
      case cu:ComputeUnit => 
        couts ++= cu.fifos.map { _.notFull }
      case _ =>
    }
    couts ++= delays.map{_.out}
    couts ++= outs
    couts.toSet.filter{_.isGlobal}.toList
  }

  override def toUpdate = super.toUpdate || tokenOut == null
}

class Delay(val name:Option[String])(implicit ctrlBox:CtrlBox, design:Design) extends CtrlPrimitive {
  override val typeStr = "Delay"
  override def toString = name.getOrElse(super.toString)
  val in = InPort(this, s"$this.in") 
  val out = OutPort(this, s"$this.out")
  ctrlBox.delays += this
}
object Delay {
  def apply(n:String)(implicit ctrlBox:CtrlBox, design:Design):Delay = new Delay(Some(n))
  def apply()(implicit ctrlBox:CtrlBox, design:Design):Delay = new Delay(None)
}

trait StageCtrlBox extends CtrlBox {
  val siblingAndTree = AndTree("SiblingAndTree")

  val en = Delay(s"$this.en")
  val done = Delay(s"$this.done")

  def enOut:OutPort
  def doneOut:OutPort
}

class InnerCtrlBox()(implicit override val ctrler:InnerController, design: Design) 
  extends CtrlBox() with StageCtrlBox {
  val enDelay = Delay(s"$this.enDelay")
  val doneDelay = Delay(s"$this.doneDelay")
  def enOut = enDelay.out
  def doneOut = doneDelay.out

  val fifoAndTree = AndTree("FIFOAndTree")
  val tokenInAndTree = AndTree("TokenInAndTree")
  val pipeAndTree = AndTree("pipeAndTree")
  pipeAndTree.addInput(siblingAndTree.out)
  pipeAndTree.addInput(fifoAndTree.out)
  val streamAndTree = AndTree(s"streamAndTree")
  streamAndTree.addInput(tokenInAndTree.out)
  streamAndTree.addInput(siblingAndTree.out)
  streamAndTree.addInput(fifoAndTree.out)
  var accumPredUnit:Option[PredicateUnit] = None
  var fifoPredUnit:Option[PredicateUnit] = None
  def setAccumPredicate(ctr:Counter, op:FixOp, const:Int):PredicateUnit = {
    accumPredUnit = Some(PredicateUnit(Some("accumPredUnit"), op, const))
    accumPredUnit.foreach{ _.in.connect(ctr.out) }
    accumPredUnit.get
  }
  def setFifoPredicate(ctr:Counter, op:FixOp, const:Int):PredicateUnit = {
    assert(fifoPredUnit.isEmpty, s"Assume a single FifoPredicateUnit in each controller $ctrler")
    fifoPredUnit = Some(PredicateUnit(Some("fifoPredUnit"), op, const))
    fifoPredUnit.foreach{ _.in.connect(ctr.out) }
    fifoPredUnit.get
  }
}
object InnerCtrlBox {
  def apply()(implicit ctrler:InnerController, design: Design) = new InnerCtrlBox()
} 

class OuterCtrlBox()(implicit override val ctrler:Controller, design: Design) extends CtrlBox() with StageCtrlBox {
  def enOut = en.out
  val doneOut = OutPort(this, s"$this.doneOut")
  val childrenAndTree = AndTree("ChildrenAndTree")
}
object OuterCtrlBox {
  def apply()(implicit ctrler:Controller, design:Design) = { new OuterCtrlBox() }
}

case class TopCtrlBox()(implicit override val ctrler:Controller, design: Design) extends CtrlBox {
  val status = InPort(this, s"$this.status")
  val command = OutPort(this, s"$this.command")
}

case class MemCtrlBox()(implicit override val ctrler:MemoryPipeline, design: Design) extends CtrlBox() {
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

case class MCCtrlBox()(implicit override val ctrler:MemoryController, design: Design) extends StageCtrlBox() {
  def enOut = en.out
  def doneOut = done.out
  val fifoAndTree = AndTree("FIFOAndTree")
}


