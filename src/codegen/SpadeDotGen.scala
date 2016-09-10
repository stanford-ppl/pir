package pir.graph.traversal

import pir._
import pir.codegen._
import pir.PIRMisc._
import pir.plasticine.graph.{Counter => PCtr, ComputeUnit => PCU, Top => PTop}
import pir.graph.{Counter => Ctr}
import graph.mapper.PIRMap

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File

class CUDotPrinter(fileName:String) extends DotCodegen { 

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

class ArgDotPrinter(fileName:String) extends DotCodegen { 
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

class CtrDotPrinter(fileName:String) extends DotCodegen { 

  def this() = this(Config.spadeCtr)

  override val stream = newStream(fileName) 

  def print(pctrs:List[PCtr]) {
    emitBlock(s"digraph G") {
      pctrs.foreach { pctr =>
        val recs = ListBuffer[String]()
        recs += s"<en> en"
        recs += s"${pctr}"
        recs += s"<done> done"
        val label = recs.mkString(s"|")
        emitNode(pctr, label, DotAttr().shape(Mrecord))
        pctr.en.fanIns.foreach { from => 
          emitEdge(s"${s"${from}".replace(".", ":")}", s"${s"${pctr.en}".replace(".",":")}")
        }
      }
    }
  close
  }

  def emitMapping(pctrs:List[PCtr], mapping:PIRMap) = {
    val map = mapping.ctmap.map
    val pmap = mapping.ctmap.pmap
    pctrs.foreach { pctr =>
      val recs = ListBuffer[String]()
      val da = DotAttr().shape(Mrecord)
      recs += s"<en> en"
      recs += s"{${pctr}|${pmap.get(pctr).fold("no mapping"){c => da.color(red); c.toString }}}"
      recs += s"<done> done"
      val label = recs.mkString(s"|")
      emitNode(pctr, label, da)
      pctr.en.fanIns.foreach { from => 
        emitEdge(s"${s"${from}".replace(".", ":")}", s"${s"${pctr.en}".replace(".",":")}")
      }
    }
  }

  def print(pctrs:List[PCtr], mapping:PIRMap) = {
    emitBlock(s"digraph G") {
      emitMapping(pctrs, mapping)
    }
  }

  def print(pctrs:List[PCtr], ctrs:List[Ctr], mapping:PIRMap) {
    emitBlock(s"digraph G") {
      emitSubGraph("Mapping", "Mapping") {
        emitMapping(pctrs, mapping)
      }
      emitSubGraph("Nodes", "Nodes") {
        ctrs.foreach { ctr =>
          emitNode(ctr, ctr, DotAttr().shape(circle).color(indianred).style(filled))
          if (ctr.en.isConnected) 
            emitEdge(s"${ctr.en.from}".replace(".",":"), s"${ctr.en}".replace(".",":"))
        }
      }
    }
    close
  }
}

class SpadeDotGen(cuPrinter:CUDotPrinter, argInOutPrinter:ArgDotPrinter,
  ctrPrinter:CtrDotPrinter)(implicit design: Design) extends Traversal {

  override def traverse = {
    cuPrinter.print(design.arch.cus)
    ctrPrinter.print(design.arch.rcus.head.ctrs)
    argInOutPrinter.print(design.arch.cus, design.arch.top)
  }

  override def finPass = {
    info(s"Finishing Spade Dot Printing in ${cuPrinter.getPath} ${argInOutPrinter.getPath} ${ctrPrinter.getPath}")
  }
}
