package dhdl.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import dhdl.Design
import dhdl.graph._


class Primitive(nameStr:Option[String], typeStr:String)(implicit design: Design) extends Node(nameStr, typeStr){
  var ctrler:Controller = _
} 
/** Counter node. Represents a chain of counters where each counter counts upto a certain max value. When
 *  the topmost counter reaches its maximum value, the entire counter chain ''saturates'' and does not
 *  wrap around.
 *  @param maxNStride: An iterable [[scala.Seq]] tuple of zero or more values.
 *  Each tuple represents the (max, stride) for one level in the loop.
 *  Maximum values and strides are specified in the order of topmost to bottommost counter.
 */
trait CounterChain extends Primitive {
  var bounds: List[(Wire, Wire, Wire)] = Nil
  var dep:Option[CounterChain] = None
  var copy:Option[CounterChain] = None
  def ctrs:List[Wire]
  def apply(x: Int):Wire = ctrs(x)
  override def toString = {
    super.toString + { 
    if (bounds.size > 0) {
      val ds = if (dep.isDefined) s", dep=${dep.get.toString}" else ""
      bounds.map{ case (min, max, step) => s"(${min}, ${max}, ${step}${ds})" }.reduce(_+_)
    } else
      "()"
    }
  }
}
object CounterChain {
  val typeStr = "CC"
  def apply(name:Option[String], bds: Option[Seq[(Wire, Wire, Wire)]])(implicit design: Design):CounterChain =
    new Primitive(name, typeStr) with CounterChain {
      bds.foreach {i => bounds = i.toList}
      override def ctrs = bounds.map {i => Wire()}
    }
  def apply(bds: (Wire, Wire, Wire)*)(implicit design: Design):CounterChain =
    CounterChain(None, Some(bds))
  def apply(name:String, bds: (Wire, Wire, Wire)*)(implicit design: Design):CounterChain =
    CounterChain(Some(name), Some(bds))
  def copy(ctrler:Controller, name:String) (implicit design: Design) = {
    val cc = CounterChain(Some(s"${name}_copy"), None)
    def updateFunc(cp:Node) = {
      val cpCC = cp.asInstanceOf[CounterChain]
      cc.bounds = cpCC.bounds ++ Nil
      cc.dep = Some(cpCC)
    }
    design.updateLater(s"${ctrler.title}_${name}", updateFunc)
    cc
  }
}

/** SRAM 
 *  @param nameStr: user defined name of SRAM 
 *  @param Size: size of SRAM in all dimensions 
 */
trait SRAM extends Primitive {
  val size:List[Int]
  var readAddr: Wire = _
  var writeAddr: Wire = _
}
object SRAM {
  def apply(nameStr: Option[String], s: List[Int], ra:Option[Wire], wa:Option[Wire])(implicit design: Design) =
    new {override val size = s} with Primitive(nameStr, "SRAM")
                                with SRAM {ra.foreach(readAddr = _); wa.foreach(writeAddr = _)}
  def apply(size: List[Int])(implicit design: Design): SRAM
    = SRAM(None, size, None, None)
  def apply(name:String, size:List[Int])(implicit design: Design): SRAM
    = SRAM(Some(name), size, None, None)
  def apply(name:String, size:List[Int], ra:Wire, wa:Wire)(implicit design: Design): SRAM
    = SRAM(Some(name), size, Some(ra), Some(wa)) 
}

/** SRAM 
 *  @param nameStr: user defined name of SRAM 
 *  @param Size: size of SRAM in all dimensions 
 */
trait Pipeline extends Primitive {
  val stages:List[Stage]
  override def toString = {
    s"stages: {\n  ${if (stages.size>0) stages.map(_.toString).reduce(_+",\n"+_) else ""}\n  }"
  }
}
object Pipeline {
  val typeStr = "Pipeline"
  def apply(name:Option[String])(block: => Any) (implicit design: Design):Pipeline = new {
    override val stages = 
      design.addBlock(block, (n:Node) => n.isInstanceOf[Stage]).asInstanceOf[List[Stage]]
  } with Primitive(name, typeStr) with Pipeline
  def apply(block: => Any) (implicit design: Design):Pipeline =
    Pipeline(None)(block)
  def apply(name:String) (block: => Any) (implicit design: Design):Pipeline =
    Pipeline(Some(name))(block)
}

trait Stage extends Primitive {
  val operands:List[Op]
} 

