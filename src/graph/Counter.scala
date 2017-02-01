package pir.graph

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map
import scala.math.max
import scala.reflect.runtime.universe._
import pir.{Design, Config}
import pir.graph._
import pir.graph.enums._
import pir.graph.mapper.PIRException
import pir.graph.traversal.ForwardRef

case class CounterChain(name:Option[String])(implicit ctrler:ComputeUnit, design: Design) extends Primitive {
  override val typeStr = "CC"
  /* Fields */
  val _counters = ListBuffer[Counter]()
  def counters:List[Counter] = _counters.toList

  /* Pointers */
  // Pointer to the original copy
  private var _copy:Option[Either[String, CounterChain]] = None
  def copy:Option[Either[String, CounterChain]] = _copy
  def setCopy(cc:String) = { _copy = Some(Left(cc)) }
  def setCopy(cc:CounterChain) = { _copy = Some(Right(cc)); cc.addCopied(this) }

  // List of copies to this original Counterchain 
  private val _copied = ListBuffer[CounterChain]()
  def addCopied(cc:CounterChain) = _copied += cc
  def copied = _copied.toList

  /*
   * Whether CounterChain is a copy of other CounterChain
   * */
  def isCopy = copy.isDefined
  def isDummy = counters.forall{_.isInstanceOf[DummyCounter]}
  def isLocal = !isCopy
    
  /*
   * Whether CounterChain is not a copy or is a copy and has been updated
   * */
  def isDefined = copy.fold(true) { e => e.isRight }
  /*
   * The original copy of this CounterChain
   * */
  lazy val original = copy.fold(this) { e => e.right.get}

  override def toUpdate = super.toUpdate

  def outer:Counter = counters.head
  def inner:Counter = counters.last

  def addCounter(ctr:Counter):Unit = {
    _counters.lastOption.foreach { pre =>
      pre.setDep(ctr)
    }
    _counters += ctr
  }

  def addCounters(ctrs:List[Counter]):Unit = {ctrs.foreach { ctr => addCounter(ctr) } }
  def addOuterCounter(ctr:Counter):Unit = {
    _counters.headOption.foreach { next =>
      ctr.setDep(next)
    }
    _counters.insert(0, ctr)
  }

  def this(name:Option[String], bds: (OutPort, OutPort, OutPort)*)(implicit ctrler:ComputeUnit, design: Design) = {
    this(name)
    bds.zipWithIndex.foreach {case ((mi, ma, s),i) => addCounter(Counter(this, mi, ma, s)(ctrler, design)) }
  }

  def apply(num: Int)(implicit ctrler:ComputeUnit, design: Design):Counter = {
    if (isCopy) {
      // Speculatively create extra counters base on need and check bound during update
      addCounters(List.fill(num+1-counters.size)(Counter(this)))
    }
    counters(num)
  }

  def copy(cp:CounterChain):Unit = {
    // Check whether speculative wire allocation was correct
    assert(counters.size <= cp.counters.size, 
      s"Accessed counter ${counters.size-1} of ${this} is out of bound")
    assert(!cp.isCopy, s"Can only copy original CounterChain. Target ${cp} is a copy of ${cp.original}")
    val addiCtrs = List.fill(cp.counters.size-counters.size)(Counter(this))
    addCounters(addiCtrs)
    counters.zipWithIndex.foreach { case(c,i) => c.copy(cp.counters(i)) }
    this.setCopy(cp)
    ctrler.addCChain(this)
  }

}
object CounterChain {
  def apply(bds: (OutPort, OutPort, OutPort)*)(implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    new CounterChain(None, bds:_*)
  }
  def apply(name:String, bds: (OutPort, OutPort, OutPort)*)(implicit ctrler:ComputeUnit, design: Design):CounterChain =
    new CounterChain(Some(name), bds:_*)
  /*
   * @param from: User defined name for Controller of the copying CounterChain 
   * @param name: User defined name for Primitive 
   * */
  def copy(from:String, name:String) (implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    copy(ForwardRef.getPrimName(from, name))
  }
  /*
   * @param from: Controller of the copying CounterChain 
   * @param name: User defined name for Primitive 
   * */
  def copy(from:ComputeUnit, name:String) (implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    copy(ForwardRef.getPrimName(from, name))
  }
  /*
   * @param from: full name of Primitive 
   * */
  def copy(from:String) (implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    val cc = CounterChain(Some(s"${from}_copy"))
    cc.setCopy(from)
    def updateFunc(cp:Node) = cc.copy(cp.asInstanceOf[CounterChain])
    design.updateLater(from, updateFunc _ )
    cc
  }
  def copy(from:CounterChain)(implicit ctrler:ComputeUnit, design: Design):CounterChain = {
    val cc = CounterChain(Some(s"${from}_copy"))
    cc.copy(from)
    cc
  }
  def dummy(implicit ctrler:ComputeUnit, design: Design) = {
    val cc = CounterChain(Some(s"dummy"))
    cc.addCounter(DummyCounter(cc))
    cc
  }
}

class Counter(val name:Option[String])(implicit override val ctrler:ComputeUnit, design: Design) extends Primitive {
  override val typeStr = "Ctr"
  /* Fields */
  val min:InPort = InPort(this, s"${this}.min")
  val max:InPort = InPort(this, s"${this}.max")
  val step:InPort = InPort(this, s"${this}.step")
  val out:OutPort = OutPort(this, {s"${this}.out"}) 
  val en:EnInPort = EnInPort(this, s"${this}.en")
  val done:DoneOutPort = DoneOutPort(this, s"${this}.done")
  var _cchain:CounterChain = _
  def cchain:CounterChain = _cchain
  def cchain(cc:CounterChain):Counter = {
    en.disconnect
    done.disconnect
    _cchain = cc
    this
  }
  override def toUpdate = super.toUpdate || cchain==null

  def update(mi:OutPort, ma:OutPort, s:OutPort):Unit = {
    min.connect(mi)
    max.connect(ma)
    step.connect(s)
  }

  def isInner = { 
    ctrler match {
      case mc:MemoryController =>
        en.isConnected && en.from.src.isInstanceOf[EnLUT] || en.isConnectedTo(mc.done)
      case _ => en.isConnected && en.from.src.isInstanceOf[EnLUT]
    }
  }
  def isOuter = { !done.isConnected || done.to.forall{!_.src.isInstanceOf[Counter]} } 
  def next:Counter = {
    val ns = done.to.map(_.src).collect{ case c:Counter => c}
    assert(ns.size==1, s"$this has not exactly 1 next counter ${done.to} ${ns}")
    ns.head
  }
  def prev:Counter = en.from.src.asInstanceOf[Counter]

  def setDep(c:Counter) = { en.connect(c.done) }

  def copy(c:Counter) = {
    assert(min.from==null, 
      s"Overriding existing counter ${this} with min ${c.min}")
    assert(max.from==null, 
      s"Overriding existing counter ${this} with min ${c.max}")
    assert(step.from==null, 
      s"Overriding existing counter ${this} with min ${c.step}")
    def copyOutPort(p:OutPort):OutPort = {
      p.src match {
        case s:Const => s.out
        case s:PipeReg => 
          assert(s.stage.isInstanceOf[EmptyStage])
          assert(s.reg.isInstanceOf[ScalarInPR])
          val ScalarIn(n, scalar) = s.reg.asInstanceOf[ScalarInPR].scalarIn
          val cu = ctrler.asInstanceOf[ComputeUnit]
          val pr = cu.scalarIn(cu.emptyStage, scalar)
          pr.out
        case _ => throw new Exception(s"Don't know how to copy port")
      }
    }
    update(copyOutPort(c.min.from), copyOutPort(c.max.from), copyOutPort(c.step.from))
  } 
}
object Counter {
  def apply(name:Option[String], cc:CounterChain)(implicit ctrler:ComputeUnit, design: Design):Counter = {
    new Counter(name).cchain(cc)
  }
  def apply(cchain:CounterChain, min:OutPort, max:OutPort, step:OutPort)(implicit ctrler:ComputeUnit, design: Design):Counter =
    { val c = Counter(None, cchain); c.update(min, max, step); c }
  def apply(name:String, cchain:CounterChain, min:OutPort, max:OutPort, step:OutPort)(implicit ctrler:ComputeUnit, design: Design):Counter =
    { val c = Counter(Some(name), cchain); c.update(min, max, step); c }
  def apply(cchain:CounterChain)(implicit ctrler:ComputeUnit, design: Design):Counter = 
    Counter(None, cchain)
}

case class DummyCounter(cc:CounterChain)(implicit override val ctrler:ComputeUnit, design: Design)
  extends Counter(Some(s"dummyCtr")) {
  this.cchain(cc)
  this.min.connect(Const(s"-1i").out)
  this.max.connect(Const(s"-1i").out)
  this.step.connect(Const(s"-1i").out)
  //val dummyCtrl = CtrlOutPort(this, s"${this}.dummyEn")
  //this.en.connect(dummyCtrl)
  override def toUpdate = false
}


