package pir.node

import pir._
import pir.util._
import pir.pass.ForwardRef

import pirc._

import scala.collection.mutable.ListBuffer

case class CounterChain(cc:Option[Either[String, CounterChain]])(implicit override val ctrler:ComputeUnit, design: PIR) extends Primitive {
  import pirmeta._

  override val typeStr = "CC"
  /* Fields */
  val _counters = ListBuffer[Counter]()
  def counters:List[Counter] = _counters.toList

  /* Pointers */
  // Pointer to the original copy
  private var _copy:Option[Either[String, CounterChain]] = None
  def copy:Option[Either[String, CounterChain]] = _copy
  def setCopy(cc:String):Unit = { _copy = Some(Left(cc)) }
  def setCopy(cc:CounterChain):Unit = { _copy = Some(Right(cc)); cc.addCopied(this) }
  cc.foreach{ case Left(cc) => setCopy(cc); case Right(cc) => setCopy(cc) }

  // List of copies to this original Counterchain 
  private val _copied = ListBuffer[CounterChain]()
  def addCopied(cc:CounterChain) = _copied += cc
  def copied = _copied.toList

  /*
   * Whether CounterChain is a copy of other CounterChain
   * */
  def isCopy = copy.isDefined
  def isDummy = counters.forall{_.isInstanceOf[DummyCounter]}
  def isLocal = !isCopy && (!inner.en.isConnected || !inner.en.from.src.isInstanceOf[Counter])
    
  /*
   * Whether CounterChain is not a copy or is a copy and has been updated
   * */
  def isDefined = copy.fold(true) { e => e.isRight }
  /*
   * The original copy of this CounterChain
   * */
  def original:CounterChain = copy.fold(this) { e => e.right.get}

  override def toUpdate = super.toUpdate

  def outer:Counter = counters.head
  def inner:Counter = counters.last

  def addCounter(ctr:Counter):Unit = {
    ctr.cchain(this)
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

  def this(ctrs:Counter*)(implicit ctrler:ComputeUnit, design: PIR) = {
    this(None)
    ctrs.foreach { ctr => addCounter(ctr) }
  }

  def apply(num: Int)(implicit ctrler:ComputeUnit, design: PIR):Counter = {
    if (isCopy) {
      // Speculatively create extra counters base on need and check bound during update
      addCounters(List.fill(num+1-counters.size)(Counter(this)))
    }
    counters(num)
  }

  def iterIdx(ctrIdx:Int, iterIdx:Int):this.type = {
    apply(ctrIdx).iterIdx(iterIdx)
    this
  }

  def copy(cp:CounterChain):Unit = {
    assert(!cp.isCopy, s"Can only copy original CounterChain. Target ${cp} is a copy of ${cp.original}")
    this.setCopy(cp)
    ctrler.addCChain(this)
    clone(cp)
  }

  def clone(cc:CounterChain):Unit = {
    if (cc.isCopy) {
      clone(cc.original)
    } else {
      // Check whether speculative wire allocation was correct
      assert(counters.size <= cc.counters.size, 
        s"Accessed counter ${counters.size-1} of ${this} is out of bound")
      val addiCtrs = List.fill(cc.counters.size-counters.size)(Counter(this))
      addCounters(addiCtrs)
      counters.zipWithIndex.foreach { case(c,i) => c.clone(cc.counters(i)) }
      //iterOf(this) = iterOf(cc) 
    }
  }

  def iter(it:Long) = {
    iterOf(this) = it
    this
  }

  ctrler.addCChain(this)
}
object CounterChain {
  def apply(name:String, ctrs: Counter*)(implicit ctrler:ComputeUnit, design: PIR):CounterChain =
    new CounterChain(ctrs:_*).name(name)
  /*
   * @param from: User defined name for Controller of the copying CounterChain 
   * @param name: User defined name for Primitive 
   * */
  def copy(from:String, name:String) (implicit ctrler:ComputeUnit, design: PIR):CounterChain = {
    import design.pirmeta._
    copy(nameOf.getPrimName(from, name))
  }
  /*
   * @param from: Controller of the copying CounterChain 
   * @param name: User defined name for Primitive 
   * */
  def copy(from:ComputeUnit, name:String) (implicit ctrler:ComputeUnit, design: PIR):CounterChain = {
    import design.pirmeta._
    copy(nameOf.getPrimName(from, name))
  }
  /*
   * @param from: full name of Primitive 
   * */
  def copy(from:String) (implicit ctrler:ComputeUnit, design: PIR):CounterChain = {
    import design.pirmeta._
    val cc = new CounterChain(Some(Left(from))).name(s"${from}_copy")
    design.updateLater{ cc.copy(nameOf.find[CounterChain](from)) }
    cc
  }
  def copy(from:CounterChain)(implicit ctrler:ComputeUnit, design: PIR):CounterChain = {
    val cc = new CounterChain(Some(Right(from))).name(s"${from}_copy")
    cc.copy(from)
    cc
  }
  def clone(from:CounterChain)(implicit ctrler:ComputeUnit, design: PIR):CounterChain = {
    val cc = CounterChain(None)
    from.name.foreach { name => cc.name(name) }
    cc.clone(from)
    cc
  }
  def dummy(implicit ctrler:ComputeUnit, design: PIR) = {
    import design.pirmeta._
    val cc = CounterChain(None).name("dummy")
    cc.addCounter(DummyCounter(cc))
    iterOf(cc) = 1
    cc
  }
}

class Counter(implicit override val ctrler:ComputeUnit, design: PIR) extends Primitive {
  override val typeStr = "Ctr"
  /* Fields */
  val min:InPort = InPort(this, s"${this}.min")
  val max:InPort = InPort(this, s"${this}.max")
  val step:InPort = InPort(this, s"${this}.step")
  val out:OutPort = OutPort(this, {s"${this}.out"}) 
  val en:InPort = InPort(this, s"${this}.en")
  val done:OutPort = OutPort(this, s"${this}.done")
  var par:Int = 1
  var _iterIdx:Option[Int] = None
  def iterIdx:Option[Int] = _iterIdx
  def iterIdx(ii:Int) = _iterIdx = Some(ii)


  override def toUpdate = super.toUpdate || cchain==null

  var _cchain:CounterChain = _
  def cchain:CounterChain = _cchain
  def cchain(cc:CounterChain):Counter = {
    en.disconnect
    done.disconnect
    _cchain = cc
    this
  }

  def isInner = { 
    if (PIRConfig.ctrl) {
      assert(en.isConnected, s"${this}.en is not connected")
      !en.from.src.isInstanceOf[Counter]
    } else {
      (ctrler.cchains.head == cchain) && (cchain.inner == this)
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

  def clone(c:Counter) = {
    assert(min.from==null, 
      s"Overriding existing counter ${this} with min ${c.min}")
    assert(max.from==null, 
      s"Overriding existing counter ${this} with min ${c.max}")
    assert(step.from==null, 
      s"Overriding existing counter ${this} with min ${c.step}")
    def copyOutPort(p:OutPort):OutPort = {
      p.src match {
        case Const(c) => Const(c).out
        case s:ScalarBuffer =>
          val writers = collectIn[ScalarIn](s.writePort).map{_.scalar}
          val sb = ctrler.smems.filter { smem =>
            collectIn[ScalarIn](smem.writePort).map{_.scalar} == writers
          }.headOption.getOrElse {
            val sb = ScalarBuffer()(ctrler, design)
            writers.foreach { scalar =>
              sb.wtPort(scalar)
            }
            ctrler.mems(List(sb))
            sb
          }
          //val smem = design.scalMemInsertion.insertScalarMem(sin)
          sb.load
        case _ => throw new Exception(s"Don't know how to copy port") //TODO
      }
    }
    min.connect(copyOutPort(c.min.from))
    max.connect(copyOutPort(c.max.from))
    step.connect(copyOutPort(c.step.from))
    par = c.par
  } 
}
object Counter {
  def apply(min:OutPort, max:OutPort, step:OutPort, par:Int)(implicit ctrler:ComputeUnit, design: PIR):Counter = { 
    val c = new Counter()
    c.min.connect(min)
    c.max.connect(max)
    c.step.connect(step)
    c.par = par
    c 
  }
  def apply(cchain:CounterChain)(implicit ctrler:ComputeUnit, design: PIR):Counter = 
    new Counter().cchain(cchain)
  def apply(name:Option[String], cchain:CounterChain)(implicit ctrler:ComputeUnit, design: PIR):Counter =  {
    val ctr = Counter(cchain)
    name.foreach { name => ctr.name(name) }
    ctr
  }
}

case class DummyCounter(cc:CounterChain)(implicit override val ctrler:ComputeUnit, design: PIR)
  extends Counter() {
  this.name(s"dummyCtr")
  this.cchain(cc)
  this.min.connect(Const(-1).out)
  this.max.connect(Const(-1).out)
  this.step.connect(Const(-1).out)
  //val dummyCtrl = OutPort(this, s"${this}.dummyEn")
  //this.en.connect(dummyCtrl)
  override def toUpdate = false
}


