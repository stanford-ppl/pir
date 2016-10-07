package pir.graph.traversal

import pir.{Design, Config}
import pir.codegen._
import pir.misc._
import pir.typealias._
import pir.graph.mapper.{PIRMap, PIRException}
import pir.plasticine.main._
import pir.plasticine.graph.{SwitchBox, Node}

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File
import scala.reflect.runtime.universe._

class CUDotPrinter(fileName:String)(implicit design:Design) extends DotCodegen with Metadata { 
  implicit lazy val spade:Spade = design.arch

  def this()(implicit design:Design) = this(Config.spadeNetwork)

  override val stream = newStream(fileName) 
  
  import Node._
  val scale = 4

  def emitSwitchBoxes(sbs:List[PSB], mapping:Option[PIRMap]) = {
    sbs.foreach { sb =>
      val (x,y) = coordOf(sb)
      val attr = DotAttr().shape(Mrecord)
      coordOf.get(sb).foreach { case (x,y) => attr.pos((x*scale-scale/2, y*scale-scale/2)) }
      val label = mapping.flatMap { mp => 
        if (sb.vins.exists( vi => mp.fbmap.contains(vi))) { 
          attr.style(filled).fillcolor(indianred) 
          val xbar = sb.vouts.flatMap { vout => 
            mp.fpmap.get(vout.voport).map{ fp =>
              s"i-${indexOf(fp.src)} -\\> o-${indexOf(vout)}"
            }
          }.mkString(s"|") 
          Some(s"{${quote(sb)}|${xbar}}")
        } else {
          None
        }
      }.getOrElse(quote(sb))
      emitNode(sb, label, attr)
      sb.vins.foreach { pvin =>
        pvin.fanIns.foreach { pvout =>
          val attr = DotAttr()
          mapping.foreach { mp => 
            if (mp.fbmap.get(pvin).fold(false) { _ == pvout}) { 
              var label = s"(i-${indexOf(pvin)})"
              if (pvout.src.isInstanceOf[PSB]) label += s"\n(o-${indexOf(pvout)})"
              attr.color(indianred).style(bold).label(label)
            } 
          }
          pvout.src match {
            case from:PSB => emitEdge(s"$from", sb, attr)
            case from:PCU => emitEdge(s"$from:$pvout", sb, attr)
          }
        }
      }
    }
  }

  def emitPCUs(pcus:List[PCU], mapping:Option[PIRMap]) = {
    //emitln(s"splines=ortho;")
    pcus.foreach { pcu =>
      val recs = ListBuffer[String]()
      recs += s"{${pcu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
      val qpcu = s"${quote(pcu)}"
      recs += mapping.fold(qpcu) { mp => mp.clmap.pmap.get(pcu).fold(qpcu) { cu => 
        val icl = cu.asInstanceOf[ICL]
        s"{$qpcu|{${(icl +: icl.outers).mkString(s"|")}}}"} 
      }
      recs += s"<${pcu.vout}> ${pcu.vout}"
      val label = s"{${recs.mkString("|")}}"
      var attr = DotAttr().shape(Mrecord)
      coordOf.get(pcu).foreach { case (x,y) => attr.pos((x*scale, y*scale)) }
      mapping.foreach { mp => if (mp.clmap.pmap.contains(pcu)) attr.style(filled).fillcolor(indianred) }
      emitNode(pcu, label, attr)
      pcu.vins.foreach { pvin =>
        emitInput(pcu, pvin, mapping)
      }
    }
    mapping.foreach { mp =>
      design.top.vins.foreach { vin =>
        mp.vomap.get(vin.writer).foreach { pvout =>
          val dvo = mp.vomap.pmap(pvout).asInstanceOf[DVO] 
          val label = s"${dvo.vector}[\n${dvo.vector.scalars.mkString(",\n")}]"
          val attr = DotAttr().label(label).color(indianred).style(bold)
          emitEdge(s"${mp.clmap(dvo.ctrler)}:$pvout", design.top, attr)
        }
      }
    }
  }

  def emitInput(pcl:PCL, pvin:PIB, mapping:Option[PIRMap]) = {
    pvin.fanIns.foreach { pvout =>
      val attr = DotAttr()
      mapping.foreach { m => 
        m.vimap.pmap.get(pvin).foreach { vin =>
          if (m.fbmap(pvin)==pvout) {
            attr.color(indianred).style(bold)
            pvout.src match {
              case cu:PCU if m.clmap.pmap.contains(cu) =>
                val label = vin match {
                  case dvi:DVI => s"${dvi.vector}[\n${dvi.vector.scalars.mkString(",\n")}]"
                  case _ => s"${vin.vector}"
                }
                attr.label(label)
              case top:PTop =>
                val dvo = m.vomap.pmap(pvout).asInstanceOf[DVO] 
                val label = s"${dvo.vector}[\n${dvo.vector.scalars.mkString(",\n")}]"
                attr.label(label)
                emitEdge(s"${dvo.ctrler}", s"$pcl:$pvin", attr)
              case s => 
            }
          }
        }
      }
      pvout.src match {
        case from:PSB =>
          attr.label.foreach { l => attr.label(l + s"\n(o-${indexOf(pvout)})") }
          emitEdge(from, s"$pcl:$pvin", attr)
        case from:PCU =>
          emitEdge(from, pvout, pcl, pvin, attr)
        case from:PTop =>
      }
    }
  }

  def print(pcus:List[PCU]) = {
    emitBlock("digraph G") { emitPCUs(pcus, None) }
    close
  }

  def print(res:(List[PCU], List[SwitchBox])) = {
    val (pcus, sbs) = res
    emitBlock("digraph G") { emitPCUs(pcus, None); emitSwitchBoxes(sbs, None) }
    close
  }

  def print(pcus:List[PCU], mapping:PIRMap) = {
    emitBlock("digraph G") {
      emitPCUs(pcus, Some(mapping))
    }
    close
  }

  def print(res:(List[PCU], List[SwitchBox]), mapping:PIRMap) = {
    val (pcus, sbs) = res
    emitBlock("digraph G") {
      emitPCUs(pcus, Some(mapping))
      emitSwitchBoxes(sbs, Some(mapping))
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
  ctrPrinter:CtrDotPrinter, pirMapping:PIRMapping)(implicit design: Design) extends Traversal {

  override def traverse = {
    if (pirMapping.mapping!=null)
      cuPrinter.print((design.arch.cus, design.arch.sbs), pirMapping.mapping)
    else
      cuPrinter.print((design.arch.cus, design.arch.sbs))
    ctrPrinter.print(design.arch.rcus.head.ctrs)
    argInOutPrinter.print(design.arch.cus, design.arch.top)
  }

  override def finPass = {
    info(s"Finishing Spade Dot Printing in ${cuPrinter.getPath} ${argInOutPrinter.getPath} ${ctrPrinter.getPath}")
  }
}
