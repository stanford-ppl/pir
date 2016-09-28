package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
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
      n match {
        case c:ComputeUnit =>
          c.readers.foreach { r =>
            r match {
              case t:Top =>
              case c:ComputeUnit =>
                if (!c.isInstanceOf[InnerController]) 
                  throw PIRException(s"${n} have non inner pipe reader: ${r}")
              case _ =>
            }
          }
          c.writers.foreach { w =>
            w match {
              case t:Top =>
              case c:ComputeUnit =>
                if (!c.isInstanceOf[InnerController]) 
                  throw PIRException(s"${n} have non inner pipe writer: ${w}")
              case _ =>
            }
          }
          c match {
            case ocu:OuterController =>
              if (ocu.cchains.exists( _.isCopy)) 
                throw PIRException(s"Outer controller cannot have counter copy")
            case _ =>
          }
        case n:Counter => 
          if (!n.en.isConnected) throw PIRException(s"${n}'s en in ${n.ctrler} is not connected")
        case n =>
      }
    }
  } 
  override def finPass = {
    info("Finishing checking mutable fields")
  }

}
