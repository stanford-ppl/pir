package pir.graph.traversal
import pir.graph._
import pir._
import pir.PIRMisc._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.HashMap

class ForwardRef(implicit val design: Design) extends Traversal{

  private val nameMap = HashMap[String, Node]()

  override def reset = nameMap.clear()
  override def traverse:Unit = {
    design.allNodes.foreach(n => addName(n))
    design.toUpdate.foreach { case (k,f) =>
      val n:Node = getByName(k)
      f(n)
    }
    design.toUpdate.clear()
  } 

  override def finPass = {
    info("Finishing updating forward referenced nodes")
  }

  def addName(n:Node):Unit = n.name.foreach { name => 
    n match {
      case c:Controller => 
        assert(!nameMap.contains(name), s"Already create controller with name ${name}: ${n}")
        nameMap += (name -> c)
      case p:Primitive =>
        val s = ForwardRef.getPrimName(p.ctrler, name)
        assert(!nameMap.contains(s),
          s"Already create primitive with name ${s} for controller ${p.ctrler}")
        nameMap += (s -> p)
      case w:Scalar =>
      case w:Vector =>
      case w:Port =>
        //assert(false, "No support for adding name for wire yet!")
    }
  }

  def getByName(s:String):Node = {
    assert(nameMap.contains(s), s"No node defined with name:${s}. \nnameMap:${nameMap}")
    nameMap(s)
  }

}
object ForwardRef {
  def getPrimName(ctrler:Controller, name:String) = s"${ctrler.name.fold("")(cn => s"${cn}_")}${name}"
  def getPrimName(ctrler:String, name:String) = s"${ctrler}_${name}"
}
