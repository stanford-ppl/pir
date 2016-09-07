package pir.graph.traversal

import pir._
import pir.codegen._
import pir.PIRMisc._
import pir.plasticine.graph.{Counter => PCtr, ComputeUnit => PCU, Top => PTop}

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class PCUPrinter(fileName:String) extends DotCodegen { 

  def this() = this(Config.spadeNetwork)

  override val stream = newStream(fileName) 
  
  def print(pcus:List[PCU]) = {
    emitBlock("digraph G") {
    //CUPrinter.emitln(s"splines=ortho;")
      pcus.foreach { cu =>
        val recs = ListBuffer[String]()
        recs += s"{${cu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
        recs += s"${cu}"
        recs += s"<${cu.vout}> ${cu.vout}"
        val label = recs.mkString("|")
        emitNode(cu, label, DotAttr().shape(Mrecord))
        cu.vins.foreach { vin =>
          vin.fanIns.foreach { vout =>
            if (vout.src.isDefined) { //TODO
              emitEdge(vout.src.get, vout, cu, vin)
            }
          }
        }
      }
    }
    close
  }
}

class PArgPrinter(fileName:String) extends DotCodegen { 
  override val stream = newStream(fileName)

  def this() = this(Config.spadeArgInOut)

  def print(pcus:List[PCU], ptop:PTop) = {
    emitBlock("digraph G") {
      pcus.foreach { cu =>
        val recs = ListBuffer[String]()
        recs += s"{${cu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
        recs += s"${cu}"
        recs += s"<${cu.vout}> ${cu.vout}"
        val label = recs.mkString("|")
        emitNode(cu, label, DotAttr().shape(Mrecord))
        cu.vins.foreach { vin =>
          vin.fanIns.foreach { vout =>
            if (!vout.src.isDefined) { //TODO
              emitEdge(s"""argin_${vout}""", s"""${cu}:${vin}:n""")
            }
          }
        }
      }

      ptop.argOutBuses.foreach { vin =>
        vin.fanIns.foreach { vout =>
          emitEdge(s"""${vout.src.get}:${vout}:s""", s"""argout_${vin}""")
        }
      }
    }
    close
  }
}

class PCtrPrinter(fileName:String) extends DotCodegen { 

  def this() = this(Config.spadeCtr)

  override val stream = newStream(fileName) 

  def print(pctrs:List[PCtr]) {
    emitBlock(s"digraph G") {
      pctrs.foreach { ctr =>
        val recs = ListBuffer[String]()
        recs += s"<en> en"
        recs += s"${ctr}"
        recs += s"<done> done"
        val label = recs.mkString(s"|")
        emitNode(ctr, label, DotAttr().shape(Mrecord))
        ctr.en.fanIns.foreach { from => 
          emitEdge(s"${s"${from}".replace(".", ":")}", s"${s"${ctr.en}".replace(".",":")}")
        }
      }
    }
    close
  }
}

class SpadeDotGen(implicit design: Design) extends Traversal {

  val ctrPrinter = new PCtrPrinter()
  val cuPrinter = new PCUPrinter()
  val argInOutPrinter = new PArgPrinter()

  override def traverse = {
    cuPrinter.print(design.arch.cus)
    ctrPrinter.print(design.arch.rcus.head.ctrs)
    argInOutPrinter.print(design.arch.cus, design.arch.top)
  }

  override def finPass = {
    info(s"Finishing Spade Dot Printing in ${cuPrinter.getPath} ${argInOutPrinter.getPath} ${ctrPrinter.getPath}")
  }

}
