package pir.graph.traversal

import pir.graph._
import pir._
import pir.codegen.{Printer, DotCodegen}
import pir.misc._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File

class PIRStat(fileName:String)(implicit design: Design) extends DFSTraversal with Printer with Metadata {

  def this()(implicit design: Design) = {
    this(Config.pirStat)
  }
  override val stream:OutputStream = newStream(fileName) 

  override def initPass() = {
    super.initPass
    emitBlock("Scalars") {
      design.top.scalars.foreach { s =>
        emitln(s"${s}${genFields(s)}")
      }
    }
  }

  def genFields(node:Node):String = {
    val fields = ListBuffer[String]()
    fields += PIRPrinter.genFields(node)
    node match {
      case n:Controller =>
        n match {
          case n:StreamPipeline =>
          case n => fields += s"cycles=${cycleOf(n)}"
        }
        n match {
          case n:MemoryController =>
            if (design.contentionAnalysis.isTraversed) {
              fields += s"contention=${contentionOf(n)}"
            }
          case _ =>
        }
      case p:Counter =>
        fields += s"min=${constOf.get(p.min)}, max=${constOf.get(p.max)}, step=${constOf.get(p.step)}"
      case s:Scalar => fields += s"const=${constOf.get(s)}"
      case _ =>
    }
    s"[${if (fields.size>0) fields.reduce(_+", "+_) else ""}]"
  }

  def emitBlock(title:String, node:Node):Unit = {
    emitBlock(title) {
      node match {
        case _ => super.visitNode(node)
      }
    }
  }

  override def run = {
    if (design.latencyAnalysis.isTraversed) super.run
  }
  override def visitNode(node: Node) : Unit = {
    node match {
      case n:Top => emitBlock(s"${node}${genFields(node)}") {
        n.children.foreach { c => visitNode(c)}
      }
      case n:Controller => emitBlock(s"${node}${genFields(node)}") {
        super.visitNode(node)
        n.children.foreach { c => visitNode(c)}
      }
      case n:CounterChain => emitBlock(s"${node}${genFields(node)}", node)
      case n:Counter => emitln(s"${node}${genFields(node)}")
      case _ => super.visitNode(node)
    }
  }

  override def finPass() = {
    info(s"Finishing PIR statistics in ${getPath}")
    close
  }
}
