package pir.graph.traversal

import pir.{Design, Config}
import pir.codegen._
import pir.misc._
import pir.typealias._
import pir.graph.mapper.{PIRMap, PIRException}
import pir.plasticine.main._
import pir.plasticine.graph._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File
import scala.reflect.runtime.universe._
import sys.process._
import scala.language.postfixOps

abstract class CUDotPrinter(file:String, open:Boolean)(implicit design:Design) extends DotCodegen with Metadata {
  implicit lazy val spade:Spade = design.arch

  val scale:Int

  def sbs(sn:SwitchNetwork):List[List[SwitchBox]]
  def grid(cu:Controller):GridIO[Controller]

  override val stream = newStream(file) 

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

  def print:Unit = { print(design.mapping) }

  def print(mapping:PIRMap):Unit = {
    emitBlock("digraph G") {
      design.arch match {
        case pn:PointToPointNetwork =>
        case sn:SwitchNetwork if (mapping!=null) =>
          emitSwitchBoxes(sbs(sn).flatten, Some(mapping))
          emitPCLs(sn.cus :+ sn.top, Some(mapping))
        case sn:SwitchNetwork if (mapping==null) =>
          emitSwitchBoxes(sbs(sn).flatten, None)
          emitPCLs(sn.cus :+ sn.top, None)
      }
    }
    close
    if (open) { 
        println(s"Waiting for input ...")
        val command = scala.io.StdIn.readLine()
        if (command=="n") {
          s"out/bin/run -c out/${file}".replace(".dot", "") !
        } else {
          println(s"Stop debugging control routing ...")
          System.exit(-1)
        }
    }
  }

  def emitPCLs(pcls:List[PCL], mapping:Option[PIRMap]) = {
    //emitln(s"splines=ortho;")
    //val bandWidth = spade match {
      //case sb:SwitchNetwork => sb.switchNetworkCtrlBandwidth
      //case pn:PointToPointNetwork => 1
    //}
    pcls.foreach { pcl =>
      val recs = ListBuffer[String]()
      pcl match {
        case pcu:PCU => 
          val qpcu = quote(pcu)
          val culabel = mapping.fold(qpcu) { mp => mp.clmap.pmap.get(pcu).fold(qpcu) { cu => 
            val icl = cu.asInstanceOf[ICL]
            s"{$qpcu|${(icl +: icl.outers).mkString(s"|")}}"} 
          }
          def ports(dir:String) = {
            var ins = grid(pcu).vinAt(dir).map{io => s"<$io> $io(${indexOf(io)})"}
            var outs = grid(pcu).voutAt(dir).map{io => s"<$io> $io(${indexOf(io)})"}
            val maxLength = Math.max(ins.size, outs.size)
            ins = ins ++ List.fill(maxLength-ins.size){""}
            outs = outs ++ List.fill(maxLength-outs.size){""}
            val ios = ins.zip(outs).flatMap{case (i,o) => 
              if (dir=="S" || dir=="E") List(o,i)
              else List(i,o)
            }
            ios.mkString("|")
          }
          recs += s"{${ports("NW")}  | ${ports("N")} | ${ports("NE")}}"
          recs += s"{{${ports("W")}} | ${culabel}    | {${ports("E")}}}"
          recs += s"{${ports("SW")}  | ${ports("S")} | ${ports("SE")}}"
        case ptop:PTop => recs += s"$ptop" 
      }
      val label = s"{${recs.mkString("|")}}"
      var attr = DotAttr().shape(Mrecord)
      pcl match {
        case pmc:PMC => coordOf.get(pmc).foreach { case (x,y) => attr.pos((x*scale, y*scale-scale/2)) }
        case pcu:PCU => coordOf.get(pcu).foreach { case (x,y) => attr.pos((x*scale, y*scale)) }
        case _ =>
      }
      mapping.foreach { mp => if (mp.clmap.pmap.contains(pcl)) attr.style(filled).fillcolor(indianred) }
      val nr = design.arch.asInstanceOf[SwitchNetwork].numRows
      val nc = design.arch.asInstanceOf[SwitchNetwork].numCols
      pcl match {
        case pcu:PCU =>
          emitNode(pcl, label, attr)
        case ptop:PTop => s"$ptop" 
          emitNode(quote(ptop, false), label, DotAttr.copy(attr).pos( (nr/2-1)*scale+scale/2, nc*scale))
          emitNode(quote(ptop, true), label, DotAttr.copy(attr).pos( (nr/2-1)*scale+scale/2, -scale))
      }
      grid(pcl).vins.foreach { in =>
        emitInput(pcl, in, mapping)
      }
    }
  }
  def emitSwitchBoxes(sbs:List[PSB], mapping:Option[PIRMap])(implicit design:Design) = {
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

  def emitInput(pcl:PCL, pvin:PIB, mapping:Option[PIRMap])(implicit design:Design) = {
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
                  case op:OP => ""
                  case vis:ISet[_] => s"${vis.mkString(",")}"
                }
              case top:PTop =>
                val dvo = m.vomap.pmap(pvout).asInstanceOf[DVO] 
                s"${dvo.vector}[\n${dvo.vector.scalars.mkString(",\n")}]"
              case s => ""
            }
            val cl = m.clmap.pmap(pcl)
            vin match {
              case op:OP =>
                val to = op.to.filter{_.asInstanceOf[CIP].ctrler==cl}
                label += s"to:[${to.mkString(",\n")}]\nfrom:${op}" 
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

class CUCtrlDotPrinter(file:String, open:Boolean)(implicit design:Design) extends CUDotPrinter(file, open) { 

  def this(file:String)(implicit design:Design) = this(file, false)
  def this(open:Boolean)(implicit design:Design) = this(Config.spadeCtrlNetwork, open)
  def this()(implicit design:Design) = this(false)

  val scale = 12

  def sbs(sn:SwitchNetwork):List[List[SwitchBox]] = sn.csbs
  def grid(cu:Controller):GridIO[Controller] = cu.ctrlBox
}

class CUVectorDotPrinter(file:String, open:Boolean)(implicit design:Design) extends CUDotPrinter(file, open) { 

  def this(file:String)(implicit design:Design) = this(file, false)
  def this(open:Boolean)(implicit design:Design) = this(Config.spadeNetwork, open)
  def this()(implicit design:Design) = this(false)
  
  val scale = 12

  def sbs(sn:SwitchNetwork):List[List[SwitchBox]] = sn.sbs
  def grid(cu:Controller):GridIO[Controller] = cu
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

class SpadeDotGen(cuPrinter:CUVectorDotPrinter, cuCtrlPrinter:CUCtrlDotPrinter, argInOutPrinter:ArgDotPrinter,
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
