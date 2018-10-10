package spade
package node

import param._
import scala.collection.mutable

abstract class Bundle[B<:PinType:ClassTag]()(implicit design:SpadeDesign) extends Module {
  val bct = implicitly[ClassTag[B]]
  def inputs:List[Input[B]]
  def outputs:List[Output[B]]
}
case class GridBundle[B<:PinType:ClassTag]()(implicit design:SpadeDesign) extends Bundle[B] {
  import GridBundle._

  val _inputs = ListBuffer[Input[B]]()
  val _outputs = ListBuffer[Output[B]]()
  def inputs = _inputs.toList
  def outputs = _outputs.toList

  def inputsByNeighbor = inputs.groupBy { in => assertUnify(in.connected, "fromBundle") { _.src } }
  def outputsByNeighbor = outputs.groupBy { out => assertUnify(out.connected, "toBundle") { _.src } }
  def neighborBundles = (inputs ++ outputs).map { io => 
    assertUnify(io.connected, "bundle") { _.src.asInstanceOf[GridBundle[B]] }
  }

  def addIns(num:Int):List[Input[B]] = { 
    val ins = List.fill(num) { Input[B]("in") }
    _inputs ++= ins
    ins
  }
  def addOuts(num:Int):List[Output[B]] = {
    val outs = List.fill(num) { Output[B]("out") }
    _outputs ++= outs
    outs
  }

}
