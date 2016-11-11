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
  val inc = CtrlInPort(this, s"${this}.inc")
  val dec = CtrlInPort(this, s"${this}.dec")
  val init = CtrlInPort(this, s"${this}.init")
  val out = CtrlOutPort(this, s"${this}.o")
}
case class TokenBuffer(dep:CtrlController, initVal:Int)
  (implicit ctrler:Controller, design: Design) extends UDCounter{
  override val name = None
  override val typeStr = "TokBuf"
}
case class CreditBuffer(deped:CtrlController)(implicit ctrler:Controller, design: Design) 
  extends UDCounter{
  override val initVal = 2
  override val name = None
  override val typeStr = "CredBuf"
}

case class TransferFunction(tf:(Map[CtrlOutPort, Int], List[Boolean]) => Boolean, info:String)
object TransferFunction {
  def apply(info:String)(transFunc:(Map[CtrlOutPort, Int], List[Boolean]) => Boolean):TransferFunction = {
    TransferFunction(transFunc, info)
  }
}
abstract class LUT(implicit override val ctrler:CtrlController, design: Design) extends Primitive {
  override val name = None
  val transFunc:TransferFunction
  val numIns:Int
  val ins = List.fill(numIns) { CtrlInPort(this,s"${this}.i") } 
  val out = CtrlOutPort(this, s"${this}.o")
}
case class TokenDownLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrler:CtrlController, design: Design) extends LUT {
  override val typeStr = "TokDownLUT"
}
object TokenDownLUT {
  def apply(cu:CtrlController, outs:List[CtrlOutPort], transFunc:TransferFunction)
  (implicit design: Design):CtrlOutPort = {
    val lut = TokenDownLUT(outs.size, transFunc)(cu, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    cu.ctrlBox.tokDownLUTs += lut
    lut.out
  }
}
case class TokenOutLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrler:CtrlController, design: Design) extends LUT {
  override val typeStr = "TokOutLUT"
}
object TokenOutLUT {
  def apply(cu:CtrlController, outs:List[CtrlOutPort], transFunc:TransferFunction)
  (implicit design: Design):CtrlOutPort = {
    val lut = TokenOutLUT(outs.size, transFunc)(cu, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    cu.ctrlBox.tokOutLUTs += lut
    lut.out
  }
}
case class EnLUT(numIns:Int, transFunc:TransferFunction)
              (implicit ctrler:CtrlController, design: Design) extends LUT {
  override val typeStr = "EnLUT"
}
object EnLUT {
  def apply(cu:CtrlController, outs:List[CtrlOutPort], transFunc:TransferFunction, en:EnInPort)
  (implicit design: Design):EnLUT = {
    val lut = EnLUT(outs.size, transFunc)(cu, design)
    lut.ins.zipWithIndex.foreach { case (in, i) => in.connect(outs(i)) }
    en.connect(lut.out)
    cu.ctrlBox.enLUTs += (en -> lut)
    lut
  }
}
class AndTree(implicit override val ctrler:CtrlController, design:Design) extends Primitive {
  override val name = None
  override val typeStr = "AndTree"
  val ins = ListBuffer[CtrlInPort]() 
  val out = CtrlOutPort(this, s"$this.out")
  def addInputs(inputs:List[CtrlOutPort]) = {
    ins ++= inputs.map{op => val ip = CtrlInPort(this, s"$this.in"); ip.connect(op); ip }
  }
}
object AndTree {
  def apply(inputs:CtrlOutPort*)(implicit ctrler:CtrlController, design:Design) = {
    val at = new AndTree()
    at.addInputs(inputs.toList)
    at
  }
}
case class FIFOAndTree()(implicit ctrler:CtrlController, design:Design) extends AndTree {
  override val typeStr = "FIFOAndTree"
}
case class TokenInAndTree()(implicit ctrler:CtrlController, design:Design) extends AndTree {
  override val typeStr = "TokInAndTree"
}

case class CtrlBox()(implicit cu:CtrlController, design: Design) extends Primitive {
  override val ctrler:CtrlController = cu

  override val name = None
  override val typeStr = "CtrlBox"
  val tokenBuffers = Map[Controller, TokenBuffer]()
  val creditBuffers = Map[CtrlController, CreditBuffer]()
  def udcounters:Map[Controller, UDCounter] = tokenBuffers ++ creditBuffers
  val enLUTs = Map[EnInPort, EnLUT]()
  val tokOutLUTs = ListBuffer[TokenOutLUT]()
  val tokDownLUTs = ListBuffer[TokenDownLUT]()
  def luts = enLUTs.map(_._2).toList ++ tokOutLUTs.toList ++ tokDownLUTs.toList 
  val fifoAndTree = FIFOAndTree()
  val tokInAndTree = TokenInAndTree()
  val andTree = AndTree(fifoAndTree.out, tokInAndTree.out)
  def innerCtrEn:EnInPort = cu match {
    case cu:Pipeline => cu.localCChain.inner.en //Local CChain Enable
    //Make a copy of parent's CounterChain and becomes local CounterChain
    case cu:StreamPipeline => cu.getCopy(cu.parent.localCChain).inner.en
    //OuterController's inner most counter's enable
    case cu:OuterController => cu.inner.getCopy(cu.localCChain).inner.en
  }
  def outerCtrDone:DoneOutPort = cu match {
    case cu:Pipeline => cu.localCChain.outer.done 
    case cu:StreamPipeline => cu.getCopy(cu.parent.localCChain).outer.done
    case cu:OuterController => cu.inner.getCopy(cu.localCChain).outer.done
  }
  var tokenOut:Option[CtrlOutPort] = None 
  // only outer controller have token down, which is the init signal first child stage
  var tokenDown:Option[CtrlOutPort] = None

  lazy val ctrlIns:List[CtrlInPort] = {
    val cins = ListBuffer[CtrlInPort]()
    udcounters.foreach { case (ctrler, udc) =>
      if (udc.inc.isConnected)
        cins += udc.inc
      if (udc.init.isConnected)
        cins += udc.init
    }
    if (cu.isInstanceOf[InnerController]) {
      cu.cchains.foreach { cc =>
        if (cc.inner.en.isConnected) {
          val from = cc.inner.en.from.src.asInstanceOf[Primitive].ctrler
          assert(from.isInstanceOf[InnerController])
          if (from!=cu) cins += cc.inner.en
        }
      }
    }
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

  override def toUpdate = super.toUpdate || tokenOut == null || tokenDown == null
}

