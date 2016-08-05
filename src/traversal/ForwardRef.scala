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
  override def traverse = {
    design.allNodes.foreach(n => addName(n))
    design.toUpdate.foreach { case (k,f) =>
      val n:Node = getByName(k)
      f(n)
    }
    design.toUpdate.clear()
    design.allNodes.foreach{ n => 
      if (n.toUpdate) {
        var info = ""
        n match {
          case s:Scalar => 
            def str(p:Primitive) = { if (p==null) "null" else p.ctrler.toString }
            info += s"writer:${str(s.writer)} readers=[${s.readers.map(r => str(r)).mkString(",")}]" 
          case t:Top => 
            info += s"sins:${t.sins} souts:${t.souts} vins:${t.vins} vouts:${t.vouts}" 
            info += s"compUnits:${t.compUnits} memCtrls:${t.memCtrls}"
          case _ =>
        }
        throw PIRException(s"Node ${n} contains unupdated field/fields! ${info}")
      }
    }
  } 

  override def finPass = {
    info("Finishing updating forward referenced nodes")
  }

  def addName(n:Node):Unit = if (n.name.isDefined) {
    n match {
      case c:Controller => 
        val s = n.name.get  
        assert(!nameMap.contains(s), s"Already create controller with name ${s}: ${n}")
        nameMap += (s -> c)
      case p:Primitive =>
        assert(p.ctrler!=null, s"Primitive ${p} doesn't have ctriler!")
        val s = s"${p.ctrler.name.getOrElse("")}_${n.name.get}"
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
