package pir.codegen

import pir._
import pir.codegen._
import pir.util._
import pir.util.typealias._
import pir.mapper.{PIRMap}
import pirc.exceptions._
import pirc.util._
import spade._
import spade.node._
import spade.util._
import spade.network._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.File
import scala.reflect.runtime.universe._
import sys.process._
import scala.language.postfixOps
import scala.language.existentials

abstract class SpadeDotGen(fn:String, open:Boolean)(implicit design:PIR) extends Codegen with DotCodegen {
  import spademeta._

  override implicit lazy val arch:SwitchNetwork = design.arch.asInstanceOf[SwitchNetwork]

  val scale:Int

  def io(prt:Routable):GridIO[_<:PortType, Routable]

  override lazy val stream = if (design.mapping.isDefined) newStream(fn)(arch) else newStream(fn, design.arch)

  trait Mode
  object OnlyOCU extends Mode
  object AllCU extends Mode
  object NoOCU extends Mode

  val mode:Mode = AllCU 
  //val mode:Mode = OnlyOCU 
  
  def linkColor = Color("indianred1") 

  def color(prt:PRT):Color = prt match {
    case pscu:PSCU => Color("palevioletred1")
    case pmcu:PMCU => Color("lightseagreen")
    //case pmu:PMU => Color("lightseagreen")
    case pocu:POCU => Color("orange")
    case pcu:PCU => Color("dodgerblue")
    case pmc:PMC => Color("forestgreen")
    case psb:PSB => linkColor 
    case ptop:PTop => Color("indianred1")
  }

  override def quote(n:Any):String = {
    n match {
      case (n,b) =>
        val bottom = b.asInstanceOf[Boolean]
        n match {
          case ptop:PTop if (bottom) => s"""${quote(ptop)}_bottom"""
          case ptop:PTop if (!bottom) => s"""${quote(ptop)}_top"""
        }
      case n => super[DotCodegen].quote(n)
    }
  }

  def print:Unit = { print(design.mapping) }

  def print(mapping:Option[PIRMap]):Unit = {
    emitBlock("digraph G") {
      design.arch match {
        case pn:PointToPointNetwork =>
        case sn:SwitchNetwork =>
          //emitln(s"splines=ortho;")
          val prts = mode match {
            case NoOCU =>
              sn.prts.filterNot{_.isInstanceOf[OuterComputeUnit]}
            case OnlyOCU =>
              sn.prts.filter{ prt => prt.isInstanceOf[ScalarComputeUnit] && prt.isInstanceOf[OuterComputeUnit]}
            case AllCU =>
              sn.prts
          }
          prts.foreach { prt =>
            emitPRTs(prt, mapping )
            val ins =  mode match {
              case NoOCU =>
                io(prt).ins.filterNot{ in => in.fanIns.head.src.isInstanceOf[OuterComputeUnit]}
              case OnlyOCU =>
                io(prt).ins.filter{ in => in.fanIns.head.src.isInstanceOf[ScalarComputeUnit] && in.fanIns.head.src.isInstanceOf[OuterComputeUnit]}
              case AllCU =>
                io(prt).ins
            }
            ins.foreach { in => emitInput(in, mapping) }
          }
      }
    }
    close
    if (open) { 
      s"out/bin/run -c ${getPath} &".replace(".dot", "") !
    }
  }

  /*
   * Position of Controller / SwitchBox as a function of coordinate
   * */
  def setPos(prt:PRT, attr:DotAttr, mapping:Option[PIRMap]) = {
    import arch.param._

    coordOf.get(prt).foreach { case (x,y) =>
      val coord:Option[(Double, Double)] = prt match {
        case pscu:PSCU if (x<0) | (x>=numCols) => Some(x, y-0.2)
        case ppcu:PPCU if (x<0) | (x>=numCols) => Some(x, y-0.8)
        case pmc:PMC => Some((x, y-0.5))
        case pocu:POCU => Some((x-0.3, y-0.3))
        case psb:PSB => Some((x-0.5, y-0.5))
        case ptop:PTop => None
        case pcu => Some((x, y))
      }
      coord.foreach { case (x,y) => attr.pos((x*scale, y*scale)) }
    }

  }

  def setColor(prt:PRT, attr:DotAttr, mapping:Option[PIRMap]) = {
    // Color node if any of the inputs is mapped
    mapping.foreach { mp => 
      prt match {
        case prt:PCL =>
          if (mp.pmmap.contains(prt) || io(prt).ins.exists( in => mp.fimap.contains(in)))
            attr.style(filled).fillcolor(color(prt))
        case prt =>
          if (io(prt).ins.exists(in => mp.fimap.contains(in)))
            attr.style(filled).fillcolor(color(prt))
      }
    }
  }

  def setLabel(prt:PRT, attr:DotAttr, mapping:Option[PIRMap]) = {
    def mappedLabel(prt:PCL):String = {
      mapping.fold(quote(prt)) { mp => mp.pmmap.get(prt).fold(quote(prt)) { cl => s"${quote(prt)}|$cl"} }
    }
    val recs = ListBuffer[String]()
    prt match {
      case ptop:PTop => recs += s"$ptop" 
      case pcl:PCL => 
        def ports(dir:String) = {
          var ins = io(pcl).inAt(dir).map{io => s"<$io> $io(${indexOf(io)})"}
          var outs = io(pcl).outAt(dir).map{io => s"<$io> $io(${indexOf(io)})"}
          val maxLength = Math.max(ins.size, outs.size)
          ins = ins ++ List.fill(maxLength-ins.size){""}
          outs = outs ++ List.fill(maxLength-outs.size){""}
          val ios = ins.zip(outs).flatMap{case (i,o) => 
            if (dir=="S" || dir=="E") List(o,i)
            else List(i,o)
          }
          ios.mkString("|")
        }
        recs += s"{${ports("NW")}  | ${ports("N")}          | ${ports("NE")}}"
        recs += s"{{${ports("W")}} | {${mappedLabel(pcl)}}  | {${ports("E")}}}"
        recs += s"{${ports("SW")}  | ${ports("S")}          | ${ports("SE")}}"
      case psb:PSB => 
        recs += mapping.flatMap { mp => 
          if (io(psb).ins.exists( in => mp.fimap.contains(in))) { 
            val xbar = io(psb).outs.flatMap { out => 
              mp.fimap.get(out.ic).map{ inic => 
                val in = inic.src.asInstanceOf[PI[PModule]]; s"i-${indexOf(in)} -\\> o-${indexOf(out)}"
              }
            }.mkString(s"|") 
            Some(s"${quote(psb)}|${xbar}")
          } else {
            None
          }
        }.getOrElse(quote(psb))
    }
    val label = s"{${recs.mkString("|")}}"
    attr.label(label)
  }

  def emitPRTs(prt:PRT, mapping:Option[PIRMap]) = {
    import arch.param._

    var attr = DotAttr().shape(Mrecord)
    setLabel(prt, attr, mapping)
    setPos(prt, attr, mapping)
    setColor(prt, attr, mapping)
    prt match {
      case ptop:PTop => s"$ptop" 
        emitNode(quote(ptop, false), DotAttr.copy(attr).pos( (numCols/2-1)*scale+scale/2, numRows*scale))
        emitNode(quote(ptop, true), DotAttr.copy(attr).pos( (numCols/2-1)*scale+scale/2, -scale))
      case _ =>
        emitNode(prt, attr)
    }
  }

  def emitInput(pin:PGI[PRT], mapping:Option[PIRMap])(implicit design:PIR) = {
    val prt:PRT = pin.src
    pin.fanIns.foreach { po =>
      val pout = po.asGlobal
      val attr = DotAttr()
      mapping.foreach { m => 
        if (m.fimap.get(pin).fold(false){ _ == pout }) {
          attr.color(linkColor).style(bold)
          val label = ListBuffer[String]()
          m.vimap.get(pin).foreach { ins =>
            ins.foreach {
              case vi:VI => label += s"${vi.vector}"
              case si:SI => label += s"${si.scalar}"
              case ip:IP => label += s"${ip}"
            }
            //pout.src match {
              //case cl@(_:PCU | _:PTop | _:PMC) if m.pmmap.contains(cl) =>
                //in match {
                  //case vi:VI => s"${vi.vector}"
                  //case si:SI => s"${si.vector}"
                  ////case op:OP => ""
                //}
              //case psb:PSB => s"\n(o-${indexOf(pout)})"
            //}
            //val cl = m.pmmap(prt)
            //in match {
              //case op:OP =>
                //val to = op.to.filter{_.asInstanceOf[CIP].ctrler==cl}
                //label += s"to:[${to.mkString(",\n")}]\nfrom:${op}" 
              //case _ =>
            //}
          }
          m.vomap.get(pout).foreach { out =>
            out match {
              case vo:VO => label += s"${vo.vector}"
              case so:SO => label += s"${so.scalar}"
              case op:OP => label += s"${op}"
            }
          }
          attr.label(label.mkString("\n"))
        }
      }
      val to = pin.src match {
        case psb:PSB => s"$psb"
        case ptop:PTop => quote(ptop, coordOf(pout.src)._2==0)
        case _ => s"$prt:$pin"
      }
      val from = pout.src match {
        case from:PSB =>
          attr.label.foreach { l => attr.label(l + s"\n(o-${indexOf(pout)})") }
          s"$from"
        case from:PTop =>
          val bottom = coordOf(pin.src)._2==0 
          quote(from, bottom)
        case from => s"$from:$pout"
      }
      emitEdge(from, to, attr)
    }
  }

  addPass {
    print
  }

  override def finPass = {
    close
    endInfo(s"Finishing $name in ${getPath}...")
  }

}

class SpadeCtrlDotPrinter(file:String, open:Boolean)(implicit design:PIR)
  extends SpadeDotGen(file, open) { 
  def shouldRun = Config.debug

  def this(file:String)(implicit design:PIR) = this(file, false)
  def this(open:Boolean)(implicit design:PIR) = this(SpadeConfig.spadeCtrlNetwork, open)
  def this()(implicit design:PIR) = this(false)

  val scale = 20

  def io(prt:Routable) = prt.ctrlIO
}

class SpadeScalarDotPrinter(file:String, open:Boolean)(implicit design:PIR) 
  extends SpadeDotGen(file, open) { 
  def shouldRun = Config.debug

  def this(file:String)(implicit design:PIR) = this(file, false)
  def this(open:Boolean)(implicit design:PIR) = this(SpadeConfig.spadeScalarNetwork, open)
  def this()(implicit design:PIR) = this(false)
  
  val scale = 15

  def io(prt:Routable) = prt.scalarIO

}

class SpadeVectorDotPrinter(file:String, open:Boolean)(implicit design:PIR) 
  extends SpadeDotGen(file, open) { 
  def shouldRun = Config.debug

  def this(file:String)(implicit design:PIR) = this(file, false)
  def this(open:Boolean)(implicit design:PIR) = this(SpadeConfig.spadeVectorNetwork, open)
  def this()(implicit design:PIR) = this(false)
  
  val scale = 15

  def io(prt:Routable) = prt.vectorIO
}
