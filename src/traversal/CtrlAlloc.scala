package pir.graph.traversal
import pir.graph._
import pir._
import pir.PIRMisc._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class CtrlAlloc(implicit val design: Design) extends Traversal{
  override def traverse:Unit = {
    design.top.innerCUs.foreach { inner =>
      val outers = ListBuffer[Controller]()
      var parent = inner.parent
      var child = inner
      while (!parent.isInstanceOf[Top]) {
        if (child.isTail)
          outers += parent
        parent = parent.asInstanceOf[ComputeUnit].parent
      }
      inner.outers = outers.toList
    }
    design.top.outerCUs.foreach { outer =>
      outer.children.foreach { child =>
        if (outer.tpe==Sequential) {
          child.tokenBuffers += TokenBuffer(1)(child, design)
        } else if (outer.tpe==MetaPipeline) {
          child.tokenBuffers += TokenBuffer(outer.children.size)(child, design)
          if (!child.isTail)
            child.creditBuffers += CreditBuffer(2)(child, design)
        } else throw PIRException(s"Unknown outer controller type")
      }
    }
  } 
  override def finPass = {
    info("Finishing control logic allocation")
  }

}
object CtrlCodegen {
  def lookUp(numBits:Int, transFunc: List[Boolean] => Boolean):List[Boolean] = {
    val size:Int = Math.pow(2, numBits).toInt
    val table = ListBuffer[Boolean]()
    for (i <- 0 until size) {
      var inputs = i.toBinaryString.toList.map(_ == '1') // Boolean inputs
      inputs = List.fill(numBits-inputs.size)(false) ++ inputs
      table += transFunc(inputs)
    }
    table.toList
  }
  def printTable(table:List[Boolean]) = {
    val size = table.size
    val numBits = Math.ceil(Math.log(size)/Math.log(2)).toInt
    println(s"----- Start ------")
    for (i <- 0 until size) {
      println(f"${int2Bin(i, numBits+1)} ${bool2Bin(table(i))}")
    }
    println(s"----- End ------")
  }

  def int2Bin(i:Int, width:Int):String = {
    val fmt = s"%${width}s"
    String.format(fmt, Integer.toBinaryString(i)).replace(' ', '0')
  }

  def bool2Bin(i:Boolean):String = if (i) "1" else "0"
}
