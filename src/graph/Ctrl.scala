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

class CtrlBox()(implicit cu:Controller, design: Design) extends Primitive {
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
    val lut = EnLUT(outs.size, transFunc)(cu, design)
    cu.ctrlBox.enLUTs += lut
    lut
  }

  def innerCtrEn:EnInPort = cu match {
    case cu:Pipeline => cu.localCChain.inner.en //Local CChain Enable
    case cu:OuterController => cu.localCChain.inner.en //Local CChain Enable
    //Make a copy of parent's CounterChain and becomes local CounterChain
    case cu:StreamPipeline => cu.getCopy(cu.parent.localCChain).inner.en
  }

  def outerCtrDone:CtrlOutPort = cu match {
    case cu:Pipeline => cu.localCChain.outer.done 
    case cu:OuterController => cu.localCChain.outer.done 
    case cu:StreamPipeline => cu.getCopy(cu.parent.localCChain).outer.done
  }

  def enable:EnInPort = cu match {
    case cu:Pipeline => cu.localCChain.inner.en //Local CChain Enable
    case cu:OuterController => cu.localCChain.inner.en //Local CChain Enable
    //Make a copy of parent's CounterChain and becomes local CounterChain
    case cu:StreamPipeline => cu.getCopy(cu.parent.localCChain).inner.en
  }

  def done:CtrlOutPort = cu match {
    case cu:Pipeline => cu.localCChain.outer.done 
    case cu:OuterController => cu.localCChain.outer.done 
    case cu:StreamPipeline => cu.getCopy(cu.parent.localCChain).outer.done
  }

  def ctrlIns:List[CtrlInPort] = {
    val cins = ListBuffer[CtrlInPort]()
    udcounters.foreach { case (ctrler, udc) =>
      if (udc.inc.isConnected)
        cins += udc.inc
      //TODO
      if (udc.init.isConnected)
        cins += udc.init
    }
    //cins ++= cu.cchains.map(_.inner.en).filter{ _.isCtrlIn }
    cu.ctrlBox.tokDownLUTs.foreach { tdl => 
      cins ++= tdl.ins.filter { _.isCtrlIn }
    }
    cu match {
      case cu:InnerController =>
        cu.mems.foreach { 
          case mem:FIFOOnWrite => cins += mem.enqueueEnable
          case _ =>
        }
        cins ++= tokInAndTree.ins
      case _ =>
    }
    cins.toList
  }

  lazy val ctrlOuts:List[CtrlOutPort] = { 
    cu.ctrlBox.luts.map(_.out).filter(_.isCtrlOut) ++
    (cu match {
      case cu:InnerController =>
        cu.mems.collect{ case mem:FIFOOnWrite => mem }.filter{_.readPort.isConnected}.map(_.notFull)
      case _ => Nil
    })
  }

  override def toUpdate = super.toUpdate || tokenOut == null
}

trait StageCtrlBox extends CtrlBox {
  def siblingAndTree:SiblingAndTree
}

class InnerCtrlBox()(implicit cu:InnerController, design: Design) extends CtrlBox() with StageCtrlBox {
  override val ctrler:InnerController = cu
  val siblingAndTree = SiblingAndTree()
}
object InnerCtrlBox {
  def apply()(implicit cu:InnerController, design: Design) = new InnerCtrlBox()
} 

class OuterCtrlBox()(implicit cu:Controller, design: Design) extends CtrlBox() with StageCtrlBox {
  override val ctrler:Controller = cu
  val tokenDown:CtrlOutPort = CtrlOutPort(this, s"$cu.tokenDown")
  val childrenAndTree = ChildrenAndTree()
  val siblingAndTree = SiblingAndTree()

  override def toUpdate = super.toUpdate || tokenDown == null
}
object OuterCtrlBox {
  def apply()(implicit cu:Controller, design: Design) = {
    new OuterCtrlBox()
  }
}

case class MemCtrlBox()(implicit cu:MemoryPipeline, design: Design) extends InnerCtrlBox() {
  override val ctrler:MemoryPipeline = cu
  
  //TODO
  val readEn:CtrlInPort = CtrlInPort(this, s"$cu.readEn")
  val writeEn:CtrlInPort = CtrlInPort(this, s"$cu.writeEn")

  val swapRead:CtrlInPort = CtrlInPort(this, s"$cu.swapRead")
  val credit:CtrlOutPort = CtrlOutPort(this, s"${cu.mem.consumer}.credit")

  val swapWrite:CtrlInPort = CtrlInPort(this, s"$cu.swapWrite")
  val token:CtrlOutPort = CtrlOutPort(this, s"${cu.mem.producer}.token")

  override def ctrlIns:List[CtrlInPort] = {
    super.ctrlIns ++ (readEn :: writeEn :: swapRead :: swapWrite :: Nil).filter{_.isCtrlIn}
  }
}


