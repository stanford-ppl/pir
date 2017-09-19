package pir.codegen

import pir.node._
import pir._
import pir.util.misc._
import pir.util.enums._
import pir.pass._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File

class PIRStat(implicit design:PIR) extends Printer {
  override lazy val stream:OutputStream = newStream(s"PIRStat.log", append=true) 
  def cycle(cycle:Long)(implicit design: PIR) = {
    val latency = cycle / Math.pow(10,9)
    emit(s"[${design}] ${new java.sql.Timestamp(System.currentTimeMillis())} cycle:$cycle, latency:${latency}s, ")
    flush
  }
  def numCUs(cu:Int, mc:Int) = {
    emit(s"cu:$cu, mc:$mc, ")
    flush
  }
  def numRegs(reg:Int, totReg:Int) = {
    emit(s"reg=${reg.toDouble / totReg.toDouble * 100}%, ")
    flush
  }
  def numStages(alu:Int, totAlus:Int) = {
    emitln(s"alu=${alu.toDouble / totAlus.toDouble}, ")
    flush
  }
  def numSrams(srams:Int, totSrams:Int) = {
    emitln(s"srams=${srams.toDouble / totSrams.toDouble}, ")
    flush
  }
}

class PIRStatLog(fn:String)(implicit design: PIR) extends Traversal with Printer {
  override def shouldRun = true
  import pirmeta._

  def this()(implicit design: PIR) = {
    this(s"${design}.stat")
  }
  override lazy val stream:OutputStream = newStream(fn) 

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
    fields ++= PIRPrinter.genFields(node)
    node match {
      case n:Controller =>
        n match {
          case n:MemoryController => fields += s"cycles=${cycleOf(n)}"
          case n => fields += s"cycles=${cycleOf(n)}"
        }
        n match {
          case n:MemoryController =>
            fields += s"contention=${contentionOf(n)}"
            n.mctpe match {
              case (TileLoad | TileStore) => fields += s"len=${constOf(n.len)}"
              case _ =>
            }
          case _ =>
        }
        n match {
          case n:InnerController =>
            //val active = design.resourceAnalysis.activeCycle(n)
            //fields += s"active=${active}"
          case _ =>
        }
        n match {
          case _ => fields += s"iter=${iterOf(n)}"
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

  override def traverse = {
    if (design.latencyAnalyzer.hasRun) {
      super.run
    }
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
