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

class CUCtrlDotPrinter(fileName:String)(implicit design:Design) extends DotCodegen with Metadata { 
  implicit lazy val spade:Spade = design.arch

  def this()(implicit design:Design) = this(Config.spadeCtrlNetwork)

  override val stream = newStream(fileName) 

  override def quote(n:Any)(implicit design:Design):String = {
    n match {
      case (n,b) =>
        val bottom = b.asInstanceOf[Boolean]
        n match {
          case ptop:PTop if (bottom) => s"""${quote(ptop)}_bottom"""
          case ptop:PTop if (!bottom) => s"""${quote(ptop)}_top"""
        }
      case n => super.quote(n)
    }
  }

  val scale = 13
  def emitPCLs(pcls:List[PCL], mapping:Option[PIRMap]) = {
    //emitln(s"splines=ortho;")
    val bandWidth = spade match {
      case sb:SwitchNetwork => sb.switchNetworkCtrlBandwidth
      case pn:PointToPointNetwork => 1
    }
    pcls.foreach { pcl =>
      val recs = ListBuffer[String]()
      pcl match {
        case pcu:PCU => 
          assert(pcu.cins.size==pcu.couts.size)
          val qpcu = quote(pcu)
          val culabel = mapping.fold(qpcu) { mp => mp.clmap.pmap.get(pcu).fold(qpcu) { cu => 
            val icl = cu.asInstanceOf[ICL]
            s"{$qpcu|${(icl +: icl.outers).mkString(s"|")}}"} 
          }
          def ports(dir:String) = {
            val ios = pcu.cinAt(dir).zip(pcu.coutAt(dir)).flatMap{case (i,o) => 
              if (dir=="S" || dir=="E") List(o,i)
              else List(i,o)
            }
            ios.map{io => s"<$io> $io"}.mkString("|")
          }
          recs += s"{${ports("NW")}  | ${ports("N")} | ${ports("NE")}}"
          recs += s"{{${ports("W")}} | ${culabel}    | {${ports("E")}}}"
          recs += s"{${ports("SW")}  | ${ports("S")} | ${ports("SE")}}"
        case ptop:PTop => recs += s"$ptop" 
      }
      val label = s"{${recs.mkString("|")}}"
      var attr = DotAttr().shape(Mrecord)
      coordOf.get(pcl).foreach { case (x,y) => attr.pos((x*scale, y*scale)) }
      mapping.foreach { mp => if (mp.clmap.pmap.contains(pcl)) attr.style(filled).fillcolor(indianred) }
      val nr = design.arch.asInstanceOf[SwitchNetwork].numRows
      val nc = design.arch.asInstanceOf[SwitchNetwork].numCols
      pcl match {
        case pcu:PCU =>
          emitNode(pcl, label, attr)
        case ptop:PTop => s"$ptop" 
          emitNode(quote(ptop, false), label, DotAttr.copy(attr).pos( (nr/2-1)*scale+scale/2, nc*scale))
          emitNode(quote(ptop, true), label, DotAttr.copy(attr).pos( (nr/2-1)*scale+scale/2, -scale/2))
      }
      pcl.cins.foreach { cin =>
        emitInput(pcl, cin, mapping)
      }
    }
  }

  def emitSwitchBoxes(sbs:List[PSB], mapping:Option[PIRMap]) = CUDotPrinter.emitSwitchBoxes(sbs, mapping, scale)(this)
  def emitInput(pcl:PCL, pvin:PIB, mapping:Option[PIRMap]) = CUDotPrinter.emitInput(pcl, pvin, mapping, scale)(this)

  def print:Unit = { print(design.mapping) }

  def print(mapping:PIRMap):Unit = {
    emitBlock("digraph G") {
      design.arch match {
        case pn:PointToPointNetwork =>
        case sn:SwitchNetwork if (mapping!=null) =>
          emitPCLs(sn.cus :+ sn.top, Some(mapping))
          emitSwitchBoxes(sn.csbs.flatten, Some(mapping))
        case sn:SwitchNetwork if (mapping==null) =>
          emitPCLs(sn.cus :+ sn.top, None)
          emitSwitchBoxes(sn.csbs.flatten, None)
      }
    }
    close
  }

}

object CUDotPrinter extends Metadata {
  def emitSwitchBoxes(sbs:List[PSB], mapping:Option[PIRMap], scale:Int)(printer:DotCodegen)(implicit design:Design) = {
    import printer._
    implicit val spade = design.arch
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
            case from:PTop => emitEdge(quote(from, coordOf(sb)._2==0), sb, attr)
          }
        }
      }
    }
  }
  def emitInput(pcl:PCL, pvin:PIB, mapping:Option[PIRMap], scale:Int)(printer:DotCodegen)(implicit design:Design) = {
    import printer._
    implicit val spade = design.arch
    pvin.fanIns.foreach { pvout =>
      val attr = DotAttr()
      mapping.foreach { m => 
        if (m.fbmap.get(pvin).fold(false){ pvo => pvo == pvout }) {
          attr.color(indianred).style(bold)
          m.vimap.pmap.get(pvin).foreach { vin =>
            var label = pvout.src match {
              case cu:PCU if m.clmap.pmap.contains(cu) =>
                vin match {
                  case dvi:DVI => s"${dvi.vector}[\n${dvi.vector.scalars.mkString(",\n")}]"
                  case vi:VI => s"${vi.vector}"
                  case ip:IP => ""
                }
              case top:PTop =>
                val dvo = m.vomap.pmap(pvout).asInstanceOf[DVO] 
                s"${dvo.vector}[\n${dvo.vector.scalars.mkString(",\n")}]"
              case s => ""
            }
            vin match {
              case ip:IP =>
                label += s"to: ${ip} \nfrom:${ip.from}" 
              case _ =>
            }
            attr.label(label)
          }
        }
      }
      pvout.src match {
        case from:PSB =>
          attr.label.foreach { l => attr.label(l + s"\n(o-${indexOf(pvout)})") }
          pcl match {
            case ptop:PTop => emitEdge(from, quote(ptop, coordOf(from)._2==0), attr)
            case _ => emitEdge(from, s"$pcl:$pvin", attr)
          }
        case from:PCU =>
          emitEdge(from, pvout, pcl, pvin, attr)
        case from:PTop =>
          spade match {
            case sn:SwitchNetwork =>
              val bottom = coordOf(pvin.src)._2==0 
              emitEdge(quote(from, bottom), s"$pcl:$pvin", attr)
            case pn:PointToPointNetwork =>
              emitEdge(quote(from), s"$pcl:$pvin", attr)
          }
      }
    }
  }
}

class CUDotPrinter(fileName:String)(implicit design:Design) extends DotCodegen with Metadata { 
  implicit lazy val spade:Spade = design.arch

  def this()(implicit design:Design) = this(Config.spadeNetwork)

  override val stream = newStream(fileName) 
  
  val scale = 4

  override def quote(n:Any)(implicit design:Design):String = {
    n match {
      case (n,b) =>
        val bottom = b.asInstanceOf[Boolean]
        n match {
          case ptop:PTop if (bottom) => s"""${quote(ptop)}_bottom"""
          case ptop:PTop if (!bottom) => s"""${quote(ptop)}_top"""
        }
      case n => super.quote(n)
    }
  }

  def emitSwitchBoxes(sbs:List[PSB], mapping:Option[PIRMap]) = CUDotPrinter.emitSwitchBoxes(sbs, mapping, scale)(this)
  def emitInput(pcl:PCL, pvin:PIB, mapping:Option[PIRMap]) = CUDotPrinter.emitInput(pcl, pvin, mapping, scale)(this)

  def emitPCLs(pcls:List[PCL], mapping:Option[PIRMap]) = {
    //emitln(s"splines=ortho;")
    pcls.foreach { pcl =>
      val recs = ListBuffer[String]()
      pcl match {
        case pcu:PCU => 
          recs += s"{${pcu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
          val qpcu = s"${quote(pcu)}"
          recs += mapping.fold(qpcu) { mp => mp.clmap.pmap.get(pcu).fold(qpcu) { cu => 
            val icl = cu.asInstanceOf[ICL]
            s"{$qpcu|{${(icl +: icl.outers).mkString(s"|")}}}"} 
          }
          recs += s"<${pcu.vout}> ${pcu.vout}"
        case ptop:PTop => recs += s"$ptop" 
      }
      val label = s"{${recs.mkString("|")}}"
      var attr = DotAttr().shape(Mrecord)
      coordOf.get(pcl).foreach { case (x,y) => attr.pos((x*scale, y*scale)) }
      mapping.foreach { mp => if (mp.clmap.pmap.contains(pcl)) attr.style(filled).fillcolor(indianred) }
      pcl match {
        case pcu:PCU =>
          emitNode(pcl, label, attr)
        case ptop:PTop => s"$ptop" 
          design.arch match {
            case sn:SwitchNetwork =>
              val nr = design.arch.asInstanceOf[SwitchNetwork].numRows
              val nc = design.arch.asInstanceOf[SwitchNetwork].numCols
              emitNode(quote(ptop, false), label, DotAttr.copy(attr).pos( (nr/2-1)*scale+scale/2, nc*scale))
              emitNode(quote(ptop, true), label, DotAttr.copy(attr).pos( (nr/2-1)*scale+scale/2, -scale))
            case pn:PointToPointNetwork =>
          }
      }
      pcl.vins.foreach { pvin =>
        emitInput(pcl, pvin, mapping)
      }
    }
  }

  def print:Unit = { print(design.mapping) }

  def print(mapping:PIRMap):Unit = {
    emitBlock("digraph G") {
      design.arch match {
        case pn:PointToPointNetwork if (mapping!=null) =>
          print(pn.cus :+ pn.top, mapping)
        case sn:SwitchNetwork if (mapping!=null) =>
          print((sn.cus :+ sn.top, sn.sbs.flatten), mapping)
        case pn:PointToPointNetwork if (mapping==null) =>
          print(pn.cus :+ pn.top)
        case sn:SwitchNetwork if (mapping==null) =>
          print((sn.cus :+ sn.top, sn.sbs.flatten))
      }
    }
    close
  }

  def print(pcls:List[PCL]):Unit = {
    emitPCLs(pcls, None)
  }

  def print(res:(List[PCL], List[SwitchBox])):Unit = {
    val (pcls, sbs) = res
    emitPCLs(pcls, None); emitSwitchBoxes(sbs, None)
  }

  def print(pcls:List[PCL], mapping:PIRMap):Unit = {
    emitPCLs(pcls, Some(mapping))
  }

  def print(res:(List[PCL], List[SwitchBox]), mapping:PIRMap):Unit = {
    val (pcls, sbs) = res
    emitPCLs(pcls, Some(mapping))
    emitSwitchBoxes(sbs, Some(mapping))
  }
}

object ArgDotPrinter extends Metadata{
  def print(ptop:PTop)(printer:DotCodegen)(implicit design:Design) = {
    implicit val spade = design.arch
    def quote(n:Any) = printer.quote(n)
    ptop.vins.foreach { vin =>
      vin.fanIns.foreach { vout =>
        spade match {
          case sn:SwitchNetwork =>
            printer.emitEdge(quote(vout), quote(ptop))
          case pn:PointToPointNetwork =>
            printer.emitEdge(quote(vout), quote(ptop))
        }
      }
    }
    ptop.vouts.foreach { vout =>
      vout.fanOuts.foreach { vin =>
        spade match {
          case sn:SwitchNetwork =>
            printer.emitEdge(quote(ptop), quote(vin))
          case pn:PointToPointNetwork =>
            printer.emitEdge(ptop, quote(vin))
        }
      }
    }
  }
}
class ArgDotPrinter(fileName:String)(implicit design:Design) extends DotCodegen { 
  override val stream = newStream(fileName)

  def this()(implicit design:Design) = this(Config.spadeArgInOut)

  def print(pcus:List[PCU], ptop:PTop) = {
    emitBlock("digraph G") {
      pcus.foreach { pcu =>
        val recs = ListBuffer[String]()
        recs += s"{${pcu.vins.map(vin => s"<${vin}> ${vin}").mkString(s"|")}}" 
        recs += s"${pcu}"
        recs += s"<${pcu.vout}> ${pcu.vout}"
        val label = recs.mkString("|")
        emitNode(pcu, label, DotAttr().shape(Mrecord))
      }
      
      ArgDotPrinter.print(ptop)(this)
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
    close
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
            emitEdge(s"${ctr.en.from.src}".replace(".",":"), s"${ctr}".replace(".",":"))
        }
      }
    }
    close
  }
}

class SpadeDotGen(cuPrinter:CUDotPrinter, cuCtrlPrinter:CUCtrlDotPrinter, argInOutPrinter:ArgDotPrinter,
  ctrPrinter:CtrDotPrinter, pirMapping:PIRMapping)(implicit design: Design) extends Traversal {

  override def traverse = {
    cuPrinter.print
    cuCtrlPrinter.print
    //ctrPrinter.print(design.arch.rcus.head.ctrs)
    argInOutPrinter.print(design.arch.cus, design.arch.top)
  }

  override def finPass = {
    info(s"Finishing Spade Dot Printing in ${cuPrinter.getPath} ${argInOutPrinter.getPath} ${ctrPrinter.getPath}")
  }
}
