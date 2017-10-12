package pir.node

import pir._
import pirc._
import scala.collection.mutable.ListBuffer

case class Top()(implicit design: PIR) extends Controller { self =>
  implicit val top:Controller = self
  import pirmeta._

  nameOf(this) = "top"
  override val typeStr = "Top"

  /* Fields */
  private val _innerCUs = ListBuffer[InnerController]()
  def innerCUs = _innerCUs.toList

  private val _outerCUs = ListBuffer[OuterController]()
  def outerCUs = _outerCUs.toList

  private var _memCUs = ListBuffer[MemoryPipeline]()
  def memCUs = _memCUs.toList

  def compUnits:List[ComputeUnit] = innerCUs ++ outerCUs
  def spadeCtrlers:List[Controller] = this :: innerCUs
  def ctrlers = this :: compUnits

  def addCtrler(ctrler:Controller) = {
    ctrler match {
      case ctrler:MemoryPipeline =>
        _memCUs += ctrler
        _innerCUs += ctrler
      case ctrler:InnerController => 
        _innerCUs += ctrler
      case ctrler:OuterController => 
        _outerCUs += ctrler
    }
  }
  
  def removeCtrler(ctrler:Controller) = {
    ctrler match {
      case ctrler:MemoryPipeline =>
        _memCUs -= ctrler
        _innerCUs -= ctrler
      case ctrler:InnerController => 
        _innerCUs -= ctrler
      case ctrler:OuterController => 
        _outerCUs -= ctrler
    }
  }

  private var _scalars:List[Scalar] = Nil
  def scalars:List[Scalar] = _scalars
  def scalars(scalars:List[Scalar]) = _scalars = scalars
  def argIns = scalars.collect { case s:ArgIn => s }

  private var _vectors:List[Vector] = Nil
  def vectors:List[Vector] = _vectors
  def vectors(vectors:List[Vector]) = _vectors = vectors

  override lazy val ctrlBox:TopCtrlBox = TopCtrlBox()(this, design)

  override def toUpdate = super.toUpdate || innerCUs == null || outerCUs == null

  override def updateBlock(block:this.type => Any)(implicit design: PIR):this.type = {
    val (inners, outers, memcus, scalars, vectors) = 
      design.addBlock[InnerController, OuterController, MemoryPipeline, Scalar, Vector](block(this), 
                      (n:Node) => n.isInstanceOf[InnerController],
                      (n:Node) => n.isInstanceOf[OuterController],
                      (n:Node) => n.isInstanceOf[MemoryPipeline],
                      (n:Node) => n.isInstanceOf[Scalar], 
                      (n:Node) => n.isInstanceOf[Vector] 
                      )
    this._innerCUs ++= inners
    this._outerCUs ++= outers
    this._memCUs ++= memcus
    this.scalars(scalars)
    scalars.foreach { s => s match {
        case a:ArgIn => 
          super.newOut(a)
        case a:ArgOut => 
          /*val sin = */super.newIn(a)
          //val sbuf = ScalarBuffer()(this, design)
          //sbuf.wtPort(sin)
          //Input(this, s"$this.in").connect(sbuf.readPort)
          //super.mems(List(sbuf))
        case _ => 
      }
    }
    this.vectors(vectors)
    this
  }
}

