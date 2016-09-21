package pir.graph.traversal

import pir._
import pir.codegen._
import pir.misc._
import pir.typealias._
import pir.graph.mapper.PIRMap
import pir.plasticine.main._
import pir.plasticine.graph.{SwitchBox}

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File
import scala.reflect.runtime.universe._

object CUDotPrinter extends Metadata {
  def quote(pne:PNE)(implicit spade:Spade) = {
    coordOf.get(pne).fold(s"$pne") { case (x,y) => s"$pne[$x,$y]"}
  }
}
class CUDotPrinter(fileName:String)(implicit design:Design) extends DotCodegen with Metadata { 
  implicit lazy val spade:Spade = design.arch

  def this()(implicit design:Design) = this(Config.spadeNetwork)

  override val stream = newStream(fileName) 
  
  import CUDotPrinter._
  val scale = 4

  def emitSwitchBoxes(sbs:List[PSB]) = {
    sbs.foreach { sb =>
      val (x,y) = coordOf(sb)
      val attr = DotAttr().shape(box)
      coordOf.get(sb).foreach { case (x,y) => attr.pos((x*scale-scale/2, y*scale-scale/2)) }
      emitNode(sb, quote(sb), attr)
      sb.vins.foreach { vin =>
        vin.fanIns.foreach { vout =>
          vout.src match {
            case from:PSB =>
              emitEdge(s"$from", sb)
            case from:PCU =>
              emitEdge(s"$from:$vout", sb)
          }
        }
      }
    }
  }

  def emitPCUs(pcus:List[PCU]) = {
    //emitln(s"splines=ortho;")
    pcus.foreach { cu =>
      val recs = ListBuffer[String]()
      recs += s"{${cu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
      recs += s"${cu}${coordOf.get(cu).fold(""){ case (x,y) => s"[$x,$y]"}}"
      recs += s"<${cu.vout}> ${cu.vout}"
      val label = s"{${recs.mkString("|")}}"
      var attr = DotAttr().shape(Mrecord)
      coordOf.get(cu).foreach { case (x,y) => attr.pos((x*scale, y*scale)) }
      emitNode(cu, label, attr)
      cu.vins.foreach { vin =>
        vin.fanIns.foreach { vout =>
          vout.src match {
            case from:SwitchBox =>
              emitEdge(from, s"$cu:$vin")
            case from:PCU =>
              emitEdge(from, vout, cu, vin)
            case from:PTop =>
          }
        }
      }
    }
  }

  def emitMapping(pcus:List[PCU], mapping:PIRMap) = {
    pcus.foreach { pcu =>
      val recs = ListBuffer[String]()
      recs += s"{${pcu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
      recs += mapping.clmap.pmap.get(pcu).fold(s"${pcu}") { cu => s"{${pcu}|${cu}}"}
      recs += s"<${pcu.vout}> ${pcu.vout}"
      val label = s"{${recs.mkString("|")}}"
      emitNode(pcu, label, DotAttr().shape(Mrecord))
      pcu.vins.foreach { pvin =>
        pvin.fanIns.foreach { pvout =>
          val attr = mapping.vimap.pmap.get(pvin).fold(DotAttr()) { set =>
            DotAttr().label(set.map(_.variable).mkString(",")).color(red)
          }
          emitEdge(pvout.src, pvout, pcu, pvin, attr)
        }
      }
    }
  }

  def emitNodes(cus:List[CU]) = {
    cus.foreach { _ match {
        case cu:ICL =>
          emitNode(cu, cu, DotAttr().shape(box).style(rounded))
          cu.sinMap.foreach { case (s, sin) => emitEdge(s.writer.ctrler, cu, s"$s")}
          cu.vinMap.foreach { case (v, vin) => emitEdge(v.writer.ctrler, cu, s"$v")}
        case cu:OCL =>
          cu.sinMap.foreach { case (s, sin) => 
            val writer = s.writer.ctrler match {
              case w:ICL => w
              case w:OCL => w.inner
            }
            emitEdge(writer, cu.inner, s"$s")
          }
      } 
    }
  }

  def print(pcus:List[PCU]) = {
    emitBlock("digraph G") { emitPCUs(pcus) }
    close
  }

  def print(res:(List[PCU], List[SwitchBox])) = {
    val (pcus, sbs) = res
    emitBlock("digraph G") { emitPCUs(pcus); emitSwitchBoxes(sbs) }
    close
  }

  def print(pcus:List[PCU], cus:List[CU]) = {
    emitBlock("digraph G") {
      emitSubGraph("PCUs", "PCUs") { emitPCUs(pcus) }
      emitSubGraph("Nodes", "Nodes") { emitNodes(cus) }
    }
    close
  }

  def print(pcus:List[PCU], cus:List[CU], mapping:PIRMap) = {
    emitBlock("digraph G") {
      emitSubGraph("Mapping", "Mapping") { emitMapping(pcus, mapping) }
      emitSubGraph("Nodes", "Nodes") { emitNodes(cus) }
    }
    close
  }
}

class ArgDotPrinter(fileName:String) extends DotCodegen { 
  override val stream = newStream(fileName)

  def this() = this(Config.spadeArgInOut)

  def print(pcus:List[PCU], ptop:PTop) = {
    emitBlock("digraph G") {
      pcus.foreach { pcu =>
        val recs = ListBuffer[String]()
        recs += s"{${pcu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
        recs += s"${pcu}"
        recs += s"<${pcu.vout}> ${pcu.vout}"
        val label = recs.mkString("|")
        emitNode(pcu, label, DotAttr().shape(Mrecord))
        pcu.vins.foreach { vin =>
          vin.fanIns.foreach { vout =>
            if (vout.src.isInstanceOf[PTop])
              emitEdge(s"""argin_${vout}""", s"""${pcu}:${vin}:n""")
          }
        }
      }

      ptop.vins.foreach { vin =>
        vin.fanIns.foreach { vout =>
          emitEdge(s"""${vout.src}:${vout}:s""", s"""argout_${vin}""")
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
