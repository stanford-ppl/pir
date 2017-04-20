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
class AndTree(implicit ctrlBox:CtrlBox, design:Design) extends CtrlPrimitive {
  override val name = None
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
  def apply(inputs:CtrlOutPort*)(implicit ctrlBox:CtrlBox, design:Design) = {
    val at = new AndTree()
    at.addInputs(inputs.toList)
    at
  }
}
case class FIFOAndTree()(implicit ctrlBox:CtrlBox, design:Design) extends AndTree {
  override val typeStr = "FIFOAndTree"
}
case class TokenInAndTree()(implicit ctrlBox:CtrlBox, design:Design) extends AndTree {
  override val typeStr = "TokInAndTree"
}
case class ChildrenAndTree()(implicit ctrlBox:CtrlBox, design:Design) extends AndTree {
  override val typeStr = "ChildrenAndTree"
}
case class SiblingAndTree()(implicit ctrlBox:CtrlBox, design:Design) extends AndTree {
  override val typeStr = "SiblingAndTree"
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
  val fifoAndTree = FIFOAndTree()
  val tokInAndTree = TokenInAndTree()
  val andTree = AndTree(fifoAndTree.out, tokInAndTree.out)
  var tokenOut:Option[CtrlOutPort] = None 
  //val swapReads = Map[MultiBuffering, (Option[CtrlInPort], CtrlOutPort)]()
  //val swapWrites = Map[MultiBuffering, (CtrlInPort, CtrlOutPort)]()
  //def swapRead(mem:MultiBuffering, sr:CtrlOutPort) = {
    //mem match {
      //case mem:LocalMem => 
        //val co = sr 
        //assert(sr.ctrler == ctrler)
        //swapReads += mem -> (None,co)
      //case mem:RemoteMem => 
        //val ci = CtrlInPort(this, s"$mem.swapRead")
        //val co = CtrlOutPort(this, s"$mem.credit")
        //ci.connect(sr)
        //swapReads += mem -> (Some(ci),co)
    //}
  //}
  //def swapWrite(mem:MultiBuffering, sw:CtrlOutPort) = {
    //val ci = CtrlInPort(this, s"$mem.swapWrite")
    //val co = CtrlOutPort(this, s"$mem.token")
    //ci.connect(sw)
    //swapWrites += mem -> (ci,co)
    //ci
  //}
  //def swapRead(mem:MultiBuffering):CtrlOutPort = {
    //swapReads(mem)._2
  //}
  //def swapWrite(mem:MultiBuffering):CtrlOutPort = {
    //swapWrites(mem)._2
  //}
  // only outer controller have token down, which is the init signal first child stage

  def tokenBuffer(dep:Any):TokenBuffer = { TokenBuffer(dep, 1) } //TODO
  def creditBuffer(deped:Any, initVal:Int):CreditBuffer = { CreditBuffer(deped, initVal) }
  def enLut(outs:List[CtrlOutPort], transFunc:TransferFunction) = { EnLUT(outs.size, transFunc) }

  def innerCtrEn:EnInPort = ctrler match {
    case ctrler:Pipeline => ctrler.localCChain.inner.en //Local CChain Enable
    case ctrler:OuterController => ctrler.localCChain.inner.en //Local CChain Enable
    //Make a copy of parent's CounterChain and becomes local CounterChain
    case ctrler:StreamPipeline => ctrler.getCopy(ctrler.parent.localCChain).inner.en
  }

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
    cins ++= tokInAndTree.ins.filter { _.isCtrlIn }
    ctrler match {
      case top:Top =>
      case cu:ComputeUnit => 
        cins ++= cu.cchains.map(_.inner.en).filter{ _.isCtrlIn }
        cins ++= cu.fifos.map { _.enqueueEnable }.filter{_.isCtrlIn}
        cins ++= cu.mbuffers.map{ _.swapRead }.filter{ _.isCtrlIn }
        cins ++= cu.mbuffers.map{ _.swapWrite }.filter{ _.isCtrlIn }
    }
    cins ++= tokInAndTree.ins
    //cins ++= swapReads.values.flatMap(_._1).filter{ _.isCtrlIn }
    //cins ++= swapWrites.values.map(_._1).filter{ _.isCtrlIn }
    cins.toSet.toList
  }

  def ctrlOuts:List[CtrlOutPort] = { 
    val couts = ListBuffer[CtrlOutPort]()
    //couts ++= swapReads.values.map(_._2).filter{ _.isCtrlOut }
    //couts ++= swapWrites.values.map(_._2).filter{ _.isCtrlOut }
    ctrler match {
      case cu:ComputeUnit => 
        couts ++= cu.fifos.map { _.notFull }.filter{_.isCtrlOut}
        couts ++= cu.cchains.map(_.outer.done).filter{ _.isCtrlOut }
      case _ =>
    }
    couts ++= andTrees.map{_.out}.filter{_.isCtrlOut}
    couts.toSet.toList
  }

  override def toUpdate = super.toUpdate || tokenOut == null
}

trait StageCtrlBox extends CtrlBox {
  val siblingAndTree:SiblingAndTree = SiblingAndTree()
  val enable = CtrlOutPort(this, s"$this.enable")
}

class InnerCtrlBox()(implicit override val ctrler:InnerController, design: Design) 
  extends CtrlBox() with StageCtrlBox {
  override def ctrlOuts:List[CtrlOutPort] = { 
    super.ctrlOuts ++ List(enable).filter { _.isCtrlOut }
  }
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
  val childrenAndTree = ChildrenAndTree()

  override def toUpdate = super.toUpdate || tokenDown == null
  override def ctrlOuts:List[CtrlOutPort] = { 
    super.ctrlOuts ++ List(pulserSMOut).filter { _.isCtrlOut }
  }
}
object OuterCtrlBox {
  def apply()(implicit ctrler:Controller, design:Design) = { new OuterCtrlBox() }
}

case class TopCtrlBox()(implicit override val ctrler:Controller, design: Design) extends OuterCtrlBox() with StageCtrlBox {
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

case class MemCtrlBox()(implicit override val ctrler:MemoryPipeline, design: Design) extends InnerCtrlBox() {
  //def readEnable:CtrlInPort = ctrler.getCopy(ctrler.mem.reader.asInstanceOf[ComputeUnit].localCChain).inner.en
  //def writeEnable:CtrlInPort = ctrler.getCopy(ctrler.mem.writer.asInstanceOf[ComputeUnit].localCChain).inner.en
  val readEnable = CtrlOutPort(this, s"$this.readEnable")
  val writeEnable = CtrlOutPort(this, s"$this.writeEnable")
}


