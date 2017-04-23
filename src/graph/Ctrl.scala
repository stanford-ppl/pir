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
  val inc = CtrlInPort(this, s"${this}.inc")
  val dec = CtrlInPort(this, s"${this}.dec")
  val init = CtrlInPort(this, s"${this}.init")
  val out = CtrlOutPort(this, s"${this}.o")
}
/* TokenBuffer represents the forward data dependency.
 * @param dep depended compute unit where data is from. None if if allocated in first stage, in
 * which case used for handling token from parent and collect token from last stage. */
case class TokenBuffer(dep:Any, initVal:Int)
  (implicit ctrlBox:CtrlBox, design:Design) extends UDCounter{
  override val name = None
  override val typeStr = "TokBuf"
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

case class TransferFunction(tf:(Map[CtrlOutPort, Int], List[Boolean]) => Boolean, info:String)
object TransferFunction {
  def apply(info:String)(transFunc:(Map[CtrlOutPort, Int], List[Boolean]) => Boolean):TransferFunction = {
    TransferFunction(transFunc, info)
  }
}
abstract class LUT(implicit ctrlBox:CtrlBox, design:Design) extends CtrlPrimitive {
  override val name = None
  val transFunc:TransferFunction
  val numIns:Int
  val ins = List.fill(numIns) { CtrlInPort(this,s"${this}.i") } 
  val out = CtrlOutPort(this, s"${this}.o")
}
case class TokenDownLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrlBox:CtrlBox, design:Design) extends LUT {
  override val typeStr = "TokDownLUT"
  ctrler.ctrlBox.tokDownLUTs += this
}
object TokenDownLUT {
  def apply(cu:Controller, outs:List[CtrlOutPort], transFunc:TransferFunction)
  (implicit design:Design):CtrlOutPort = {
    val lut = TokenDownLUT(outs.size, transFunc)(cu.ctrlBox, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    lut.out
  }
}
case class TokenOutLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrlBox:CtrlBox, design:Design) extends LUT {
  override val typeStr = "TokOutLUT"
  ctrler.ctrlBox.tokOutLUTs += this
}
object TokenOutLUT {
  def passThrough(cu:Controller, done:CtrlOutPort)(implicit design:Design):CtrlOutPort = {
    cu.ctrlBox.tokOutLUTs.filter { to => to.ins.map(_.from) == List(done) }.headOption.fold {
      val tf = TransferFunction(s"${done}") { case (map, ins) => ins(map(done)) }
      TokenOutLUT(cu, done::Nil, tf)
    } { tolut => tolut.out }
  }
  def apply(cu:Controller, outs:List[CtrlOutPort], transFunc:TransferFunction)
  (implicit design: Design):CtrlOutPort = {
    val lut = TokenOutLUT(outs.size, transFunc)(cu.ctrlBox, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    lut.out
  }
}
case class EnLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrlBox:CtrlBox, design:Design) extends LUT {
  override val typeStr = "EnLUT"
  ctrler.ctrlBox.enLUTs += this
}
object EnLUT {
  def apply(outs:List[CtrlOutPort], transFunc:TransferFunction)
  (implicit ctrlBox:CtrlBox, design:Design):EnLUT = {
    val lut = EnLUT(outs.size, transFunc)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    lut
  }
}
class AndTree(val name:Option[String])(implicit ctrlBox:CtrlBox, design:Design) extends CtrlPrimitive {
  override val typeStr = "AndTree"
  val ins = ListBuffer[CtrlInPort]() 
  val out = CtrlOutPort(this, s"$this.out")
  ctrlBox.andTrees += this
  def addInput(input:CtrlOutPort):CtrlInPort = {
    val ip = CtrlInPort(this, s"$this.in")
    ins += ip
    ip.connect(input)
    ip
  }
  def addInputs(inputs:List[CtrlOutPort]) = {
    inputs.map(input => addInput(input))
 }
}
object AndTree {
  def apply(inputs:CtrlOutPort*)(implicit ctrlBox:CtrlBox, design:Design):AndTree = {
    val at = new AndTree(None)
    at.addInputs(inputs.toList)
    at
  }
  def apply(n:String)(implicit ctrlBox:CtrlBox, design:Design):AndTree = new AndTree(Some(n))
  def apply()(implicit ctrlBox:CtrlBox, design:Design):AndTree = new AndTree(None)
}

class CtrlBox()(implicit ctrler:Controller, design:Design) extends Primitive {
  implicit def ctrlBox:CtrlBox = this
  override val name = None
  override val typeStr = "CtrlBox"

  val tokenBuffers = Map[Any, TokenBuffer]() // Mem or Parent
  val creditBuffers = Map[Any, CreditBuffer]()
  def udcounters:Map[Any, UDCounter] = tokenBuffers ++ creditBuffers
  val enLUTs = ListBuffer[EnLUT]()
  val tokOutLUTs = ListBuffer[TokenOutLUT]()
  val tokDownLUTs = ListBuffer[TokenDownLUT]()
  def luts = enLUTs.toList ++ tokOutLUTs.toList ++ tokDownLUTs.toList 
  val andTrees = ListBuffer[AndTree]()
  val delays = ListBuffer[Delay]()
  var tokenOut:Option[CtrlOutPort] = None 
  // only outer controller have token down, which is the init signal first child stage

  def tokenBuffer(dep:Any):TokenBuffer = { TokenBuffer(dep, 1) } //TODO
  def creditBuffer(deped:Any, initVal:Int):CreditBuffer = { CreditBuffer(deped, initVal) }
  def enLut(outs:List[CtrlOutPort], transFunc:TransferFunction) = { EnLUT(outs.size, transFunc) }

  def outerCtrDone:CtrlOutPort = ctrler match {
    case ctrler:Pipeline => ctrler.localCChain.outer.done 
    case ctrler:OuterController => ctrler.localCChain.outer.done 
    case ctrler:StreamPipeline => ctrler.getCopy(ctrler.parent.localCChain).outer.done
  }

  def done:CtrlOutPort = ctrler match {
    case ctrler:Pipeline => ctrler.localCChain.outer.done 
    case ctrler:OuterController => ctrler.localCChain.outer.done 
    case ctrler:StreamPipeline if (ctrler.isLast) => ctrler.getCopy(ctrler.parent.localCChain).outer.done
    case ctrler:StreamPipeline => ctrler.localCChain.outer.done 
  }

  def ctrlIns:List[CtrlInPort] = {
    val cins = ListBuffer[CtrlInPort]()
    cins ++= udcounters.values.map(_.inc).filter{ _.isCtrlIn }
    cins ++= andTrees.flatMap(_.ins).filter { _.isCtrlIn }
    cins ++= delays.map(_.in).filter { _.isCtrlIn }
    ctrler match {
      case top:Top =>
      case cu:ComputeUnit => 
        cins ++= cu.cchains.map(_.inner.en).filter{ _.isCtrlIn }
        cins ++= cu.fifos.map { _.enqueueEnable }.filter{_.isCtrlIn}
        cins ++= cu.mbuffers.map{ _.swapRead }.filter{ _.isCtrlIn }
        cins ++= cu.mbuffers.map{ _.swapWrite }.filter{ _.isCtrlIn }
    }
    cins.toSet.toList
  }

  def ctrlOuts:List[CtrlOutPort] = { 
    val couts = ListBuffer[CtrlOutPort]()
    ctrler match {
      case cu:ComputeUnit => 
        couts ++= cu.fifos.map { _.notFull }.filter{_.isCtrlOut}
        couts ++= cu.cchains.map(_.outer.done).filter{ _.isCtrlOut }
      case _ =>
    }
    couts ++= andTrees.map{_.out}.filter{_.isCtrlOut}
    couts ++= delays.map{_.out}.filter{_.isCtrlOut}
    couts.toSet.toList
  }

  override def toUpdate = super.toUpdate || tokenOut == null
}

class Delay(val name:Option[String])(implicit ctrlBox:CtrlBox, design:Design) extends CtrlPrimitive {
  override val typeStr = "Delay"
  override def toString = name.getOrElse(super.toString)
  val in = CtrlInPort(this, s"$this.in") 
  val out = CtrlOutPort(this, s"$this.out")
  ctrlBox.delays += this
}
object Delay {
  def apply(n:String)(implicit ctrlBox:CtrlBox, design:Design):Delay = new Delay(Some(n))
  def apply()(implicit ctrlBox:CtrlBox, design:Design):Delay = new Delay(None)
}

trait StageCtrlBox extends CtrlBox {
  val siblingAndTree = AndTree("SiblingAndTree")
  val en = Delay(s"$this.en")
}

class InnerCtrlBox()(implicit override val ctrler:InnerController, design: Design) 
  extends CtrlBox() with StageCtrlBox {
  val fifoAndTree = AndTree("FIFOAndTree")
  val tokenInAndTree = AndTree("TokenInAndTree")
  val andTree = AndTree(fifoAndTree.out, tokenInAndTree.out)
}
object InnerCtrlBox {
  def apply()(implicit ctrler:InnerController, design: Design) = new InnerCtrlBox()
} 

class OuterCtrlBox()(implicit override val ctrler:Controller, design: Design) extends CtrlBox() with StageCtrlBox {
  val pulserSMOut = CtrlOutPort(this, s"$ctrler.pulserSM")
  // Connect to pulser SM if pipelining, connect to sibling and tree if streamming
  def tokenDown:CtrlOutPort = ctrler match {
    case ctrler:StreamController => siblingAndTree.out
    case ctrler => pulserSMOut
  }
  val childrenAndTree = AndTree("ChildrenAndTree")

  override def toUpdate = super.toUpdate || tokenDown == null
  override def ctrlOuts:List[CtrlOutPort] = { 
    super.ctrlOuts ++ List(pulserSMOut).filter { _.isCtrlOut }
  }
}
object OuterCtrlBox {
  def apply()(implicit ctrler:Controller, design:Design) = { new OuterCtrlBox() }
}

case class TopCtrlBox()(implicit override val ctrler:Controller, design: Design) extends OuterCtrlBox {
  // Connect to pulser SM if pipelining, connect to sibling and tree if streamming
  val status = CtrlInPort(this, s"$this.status")
  val command = CtrlOutPort(this, s"$this.command")
  override def tokenDown:CtrlOutPort = command

  override def ctrlIns:List[CtrlInPort]=  {
    super.ctrlIns ++ List(status).filter { _.isCtrlIn }
  }

  override def ctrlOuts:List[CtrlOutPort] = { 
    super.ctrlOuts ++ List(command).filter { _.isCtrlOut }
  }
}

case class MemCtrlBox()(implicit override val ctrler:MemoryPipeline, design: Design) extends CtrlBox() {
  //def readEnable:CtrlInPort = ctrler.getCopy(ctrler.mem.reader.asInstanceOf[ComputeUnit].localCChain).inner.en
  //def writeEnable:CtrlInPort = ctrler.getCopy(ctrler.mem.writer.asInstanceOf[ComputeUnit].localCChain).inner.en
  val readFifoAndTree = AndTree("ReadFIFOAndTree")
  val writeFifoAndTree = AndTree("WriteFIFOAndTree")
  val readEn = Delay(s"$this.readEn")
  val writeEn = Delay(s"$this.writeEn")
}


