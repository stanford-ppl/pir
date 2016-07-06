package dhdl.graph

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.math.max
import dhdl.Design
import dhdl.graph._

/** Counter node. Represents a chain of counters where each counter counts upto a certain max value. When
 *  the topmost counter reaches its maximum value, the entire counter chain ''saturates'' and does not
 *  wrap around.
 *  @param maxNStride: An iterable [[scala.Seq]] tuple of zero or more values.
 *  Each tuple represents the (max, stride) for one level in the loop.
 *  Maximum values and strides are specified in the order of topmost to bottommost counter.
 */
class CounterChain(nameStr:Option[String])(implicit design: Design) extends Node(nameStr, "CC") {
  var bounds: List[(Wire, Wire, Wire)] = Nil
  var dep:Option[CounterChain] = None
  def ctrs = bounds.map {i => Wire()}
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
  def apply(bds: (Wire, Wire, Wire)*)(implicit design: Design):CounterChain =
    new CounterChain(None) { bounds = bds.toList } 
  def apply(name:String, bds: (Wire, Wire, Wire)*)(implicit design: Design):CounterChain =
    new CounterChain(Some(name)) { bounds = bds.toList }
  def copy(name:String) (implicit design: Design) = {
    val cc = new CounterChain(Some(s"${name}_copy"))
    def updateFunc(cp:Node) = {
      val cpCC = cp.asInstanceOf[CounterChain]
      cc.bounds = cpCC.bounds ++ Nil
      cc.dep = Some(cpCC)
    }
    design.addUpdate(name, updateFunc)
    cc
  }
}

/** SRAM 
 *  @param nameStr: user defined name of SRAM 
 *  @param Size: size of SRAM in all dimensions 
 */
class SRAM(nameStr: Option[String], val size: List[Int])(implicit design: Design) extends Node(nameStr, "sram"){
  var readAddr: Wire = _
  var writeAddr: Wire = _
}

object SRAM {
  def apply(size: List[Int])(implicit design: Design): SRAM
    = new SRAM(None, size)
  def apply(name:String, size:List[Int])(implicit design: Design): SRAM
    = new SRAM(Some(name), size)
  def apply(name:String, size:List[Int], ra:Wire, wa:Wire)(implicit design: Design): SRAM
    = new SRAM(Some(name), size) { readAddr = ra; writeAddr = wa }
}

/** SRAM 
 *  @param nameStr: user defined name of SRAM 
 *  @param Size: size of SRAM in all dimensions 
 */
class Pipeline(nameStr: Option[String], val stages:List[Stage])(implicit design: Design) extends Node(nameStr, "pipeline"){
  override def toString = {
    s"stages: {\n  ${if (stages.size>0) stages.map(_.toString).reduce(_+",\n"+_) else ""}\n  }"
  }
}
object Pipeline {
  def apply(stages:List[Stage])(implicit design: Design): Pipeline
    = new Pipeline(None, stages)
  def apply(name:String, stages:List[Stage])(implicit design: Design): Pipeline
    = new Pipeline(Some(name), stages)
  def apply(block: => Any) (implicit design: Design):Pipeline = {
    val s = design.addBlock(block, (n:Node) => n.isInstanceOf[Stage])
    new Pipeline(None, s.asInstanceOf[List[Stage]])
  }
  def apply(name:String) (block: => Any) (implicit design: Design):Pipeline = {
    val s = design.addBlock(block, (n:Node) => n.isInstanceOf[Stage])
    new Pipeline(Some(name), s.asInstanceOf[List[Stage]])
  }
}

class Stage(nameStr: Option[String]) (implicit design: Design) extends Node(nameStr, "stage") {
}
object Stage {
}

