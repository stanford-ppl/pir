package pir.graph.traversal

import pir.graph._
import pir._
import pir.PIRMisc._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class SpadeNetworkDot(implicit design: Design) extends Traversal with Printer {

  override val stream = newStream(Config.spadeNetwork) 
  
  override def initPass = {
    emitln("digraph{")
  }
  override def traverse = {
    design.arch.computeUnits.foreach { cu =>
    }
  }

  override def finPass = {
    emitln(s"}")
    close
    info(s"Finishing Spade Config Printing in ${getPath}")
  }

}
