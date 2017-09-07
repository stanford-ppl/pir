package pir.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.math.max
import pir.Design
import pir.graph._
import pir.util.enums._
import pir.exceptions._
import pir.util._
import scala.reflect.runtime.universe._
import pir.pass.ForwardRef
import pir.util._

case class Top()(implicit design: Design) extends Controller { self =>
  implicit val top:Controller = self
  import pirmeta._

  override val name = Some("Top")
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

  def updateBlock(block:Top => Any)(implicit design: Design):Top = {
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
          super.newSout(a)
        case a:ArgOut => 
          super.newSin(a)
        case _ => 
      }
    }
    this.vectors(vectors)
    this
  }
}

