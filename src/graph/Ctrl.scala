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
import pir.graph.enums._
import pir.graph.mapper.PIRException
import pir.graph.traversal.ForwardRef


abstract class UDCounter(implicit ctrler:Controller, design: Design) extends Primitive {
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
  (implicit ctrler:Controller, design: Design) extends UDCounter{
  override val name = None
  override val typeStr = "TokBuf"
  def initOnStart = false
}
case class CreditBuffer(deped:Any, initVal:Int)(implicit ctrler:Controller, design: Design) 
  extends UDCounter{
  override val name = None
  override val typeStr = "CredBuf"
  def initOnStart = true 
}

case class TransferFunction(tf:(Map[CtrlOutPort, Int], List[Boolean]) => Boolean, info:String)
object TransferFunction {
  def apply(info:String)(transFunc:(Map[CtrlOutPort, Int], List[Boolean]) => Boolean):TransferFunction = {
    TransferFunction(transFunc, info)
  }
}
abstract class LUT(implicit override val ctrler:Controller, design: Design) extends Primitive {
  override val name = None
  val transFunc:TransferFunction
  val numIns:Int
  val ins = List.fill(numIns) { CtrlInPort(this,s"${this}.i") } 
  val out = CtrlOutPort(this, s"${this}.o")
}
case class TokenDownLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrler:Controller, design: Design) extends LUT {
  override val typeStr = "TokDownLUT"
}
object TokenDownLUT {
  def apply(cu:Controller, outs:List[CtrlOutPort], transFunc:TransferFunction)
  (implicit design: Design):CtrlOutPort = {
    val lut = TokenDownLUT(outs.size, transFunc)(cu, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    cu.ctrlBox.tokDownLUTs += lut
    lut.out
  }
}
case class TokenOutLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrler:Controller, design: Design) extends LUT {
  override val typeStr = "TokOutLUT"
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
    val lut = TokenOutLUT(outs.size, transFunc)(cu, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    cu.ctrlBox.tokOutLUTs += lut
    lut.out
  }
}
case class EnLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrler:Controller, design: Design) extends LUT {
  override val typeStr = "EnLUT"
}
object EnLUT {
  def apply(outs:List[CtrlOutPort], transFunc:TransferFunction)
  (implicit ctrler:Controller, design: Design):EnLUT = {
    val lut = EnLUT(outs.size, transFunc)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    lut
  }
}
class AndTree(implicit override val ctrler:Controller, design:Design) extends Primitive {
  override val name = None
  override val typeStr = "AndTree"
  val ins = ListBuffer[CtrlInPort]() 
  val out = CtrlOutPort(this, s"$this.out")
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
  def apply(inputs:CtrlOutPort*)(implicit ctrler:Controller, design:Design) = {
    val at = new AndTree()
    at.addInputs(inputs.toList)
    at
  }
}
case class FIFOAndTree()(implicit ctrler:Controller, design:Design) extends AndTree {
  override val typeStr = "FIFOAndTree"
}
case class TokenInAndTree()(implicit ctrler:Controller, design:Design) extends AndTree {
  override val typeStr = "TokInAndTree"
}
case class ChildrenAndTree()(implicit ctrler:Controller, design:Design) extends AndTree {
  override val typeStr = "ChildrenAndTree"
}
case class SiblingAndTree()(implicit ctrler:Controller, design:Design) extends AndTree {
  override val typeStr = "SiblingAndTree"
}

class CtrlBox()(implicit ctrler:Controller, design: Design) extends Primitive {
  override val name = None
  override val typeStr = "CtrlBox"

  val tokenBuffers = Map[Any, TokenBuffer]()
  val creditBuffers = Map[Any, CreditBuffer]()
  def udcounters:Map[Any, UDCounter] = tokenBuffers ++ creditBuffers
  val enLUTs = ListBuffer[EnLUT]()
  val tokOutLUTs = ListBuffer[TokenOutLUT]()
  val tokDownLUTs = ListBuffer[TokenDownLUT]()
  def luts = enLUTs.toList ++ tokOutLUTs.toList ++ tokDownLUTs.toList 
  val fifoAndTree = FIFOAndTree()
  val tokInAndTree = TokenInAndTree()
  val andTree = AndTree(fifoAndTree.out, tokInAndTree.out)
  var tokenOut:Option[CtrlOutPort] = None 
  val swapReads = Map[MultiBuffering, (CtrlInPort, CtrlOutPort)]()
  val swapWrites = Map[MultiBuffering, (CtrlInPort, CtrlOutPort)]()
  def swapRead(mem:MultiBuffering, sr:CtrlOutPort) = {
    val ci = CtrlInPort(this, s"$mem.swapRead")
    val co = CtrlOutPort(this, s"$mem.credit")
    ci.connect(sr)
    swapReads += mem -> (ci,co)
    ci
  }
  def swapWrite(mem:MultiBuffering, sw:CtrlOutPort) = {
    val ci = CtrlInPort(this, s"$mem.swapWrite")
    val co = CtrlOutPort(this, s"$mem.token")
    ci.connect(sw)
    swapWrites += mem -> (ci,co)
    ci
  }
  def swapRead(mem:MultiBuffering):CtrlOutPort = {
    swapReads(mem)._2
  }
  def swapWrite(mem:MultiBuffering):CtrlOutPort = {
    swapWrites(mem)._2
  }
  // only outer controller have token down, which is the init signal first child stage

  def tokenBuffer(dep:Any):TokenBuffer = {
    //TODO
    val tk = TokenBuffer(dep, 1) 
    tokenBuffers += dep -> tk
    tk
  }
  def creditBuffer(deped:Any, initVal:Int):CreditBuffer = {
    val cd = CreditBuffer(deped, initVal) 
    creditBuffers += deped -> cd
    cd
  }
  def enLut(outs:List[CtrlOutPort], transFunc:TransferFunction) = {
    val lut = EnLUT(outs.size, transFunc)(ctrler, design)
    ctrler.ctrlBox.enLUTs += lut
    lut
  }

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

  def enable:EnInPort = ctrler match {
    case mc:MemoryController => throw PIRException(s"MemoryController doesn't have enable")
    case ctrler:ComputeUnit => ctrler.localCChain.inner.en
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
    ctrler match {
      case top:Top =>
      case cu:ComputeUnit => cins ++= cu.cchains.map(_.inner.en).filter{ _.isCtrlIn }
    }
    ctrler match {
      case ctrler:InnerController =>
        ctrler.mems.foreach { 
          case mem:ScalarFIFO => cins += mem.enqueueEnable
          case _ =>
        }
        cins ++= tokInAndTree.ins
      case _ =>
    }
    cins ++= swapReads.values.map(_._1).filter{ _.isCtrlIn }
    cins ++= swapWrites.values.map(_._1).filter{ _.isCtrlIn }
    cins.toList
  }

  def ctrlOuts:List[CtrlOutPort] = { 
    val couts = ListBuffer[CtrlOutPort]()
    //couts ++= ctrler.ctrlBox.luts.map(_.out).filter(_.isCtrlOut)
    couts ++= (ctrler match {
      case ctrler:InnerController =>
        ctrler.mems.collect{ case mem:FIFOOnWrite => mem }.map(_.notFull).filter{_.isCtrlOut}
      case _ => Nil
    })
    //TODO
    couts ++= swapReads.values.map(_._2).filter{ _.isCtrlOut }
    couts ++= swapWrites.values.map(_._2).filter{ _.isCtrlOut }
    couts.toList
  }

  override def toUpdate = super.toUpdate || tokenOut == null
}

trait StageCtrlBox extends CtrlBox {
  def siblingAndTree:SiblingAndTree = SiblingAndTree()
  override def ctrlOuts:List[CtrlOutPort] = {
    if (siblingAndTree.out.isCtrlOut) super.ctrlOuts :+ siblingAndTree.out
    else super.ctrlOuts
  }
}

class InnerCtrlBox()(implicit override val ctrler:InnerController, design: Design) 
  extends CtrlBox() with StageCtrlBox {
  val enableOut = CtrlOutPort(this, s"$this.enable")
}
object InnerCtrlBox {
  def apply()(implicit ctrler:InnerController, design: Design) = new InnerCtrlBox()
} 

class OuterCtrlBox()(implicit override val ctrler:Controller, design: Design) extends CtrlBox() with StageCtrlBox {
  // Connect to pulser SM if pipelining, connect to sibling and tree if streamming
  val tokenDown:CtrlOutPort = CtrlOutPort(this, s"$ctrler.tokenDown")
  val childrenAndTree = ChildrenAndTree()

  override def toUpdate = super.toUpdate || tokenDown == null
}
object OuterCtrlBox {
  def apply()(implicit ctrler:Controller, design: Design) = {
    new OuterCtrlBox()
  }
}

case class MemCtrlBox()(implicit override val ctrler:MemoryPipeline, design: Design) extends InnerCtrlBox() {
  
  def readEn:CtrlInPort = ctrler.getCopy(ctrler.mem.reader.localCChain).inner.en
  //def writeEn:CtrlInPort = ctrler.getCopy(ctrler.mem.writer.localCChain).inner.en //goes along
  //with data path
}


