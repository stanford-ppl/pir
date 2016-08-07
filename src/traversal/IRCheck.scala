package pir.graph.traversal
import pir.graph._
import pir._
import pir.PIRMisc._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.HashMap

class IRCheck(implicit val design: Design) extends Traversal{
  override def traverse:Unit = {
    design.allNodes.foreach{ n => 
      if (n.toUpdate) {
        val printer = new PIRPrinter()
        printer.run
        printer.close
        throw PIRException(s"Node ${n} contains unupdated field/fields! Refer to ${printer.getPath} for more information")
      }
    }
  } 
  override def finPass = {
    info("Finishing checking mutable fields")
  }

}
