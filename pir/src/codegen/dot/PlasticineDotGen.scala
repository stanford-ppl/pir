package pir.codegen

import pir._
import pir.util.typealias._

import spade._
import spade.node._

import pirc._
import pirc.util._

import scala.collection.mutable.ListBuffer
import sys.process._
import scala.language.postfixOps
import scala.language.existentials

abstract class PlasticineDotGen(fn:String, open:Boolean)(implicit design:PIR) 
  extends spade.codegen.PlasticineDotGen(fn, open)(design.arch) {
  import spademeta._

  override lazy val stream = if (design.mapping.isDefined) newStream(fn)(design) else newStream(fn)(design.arch)

  var mapping:Option[PIRMap] = None

  override def print:Unit = { 
    if (mapping.isEmpty) this.mapping = design.mapping
    super.print
  }

  def print(mapping:Option[PIRMap]):Unit = {
    this.mapping = mapping
    super.print
  }

  override def setColor(pin:PGI[PModule], pout:PGO[PModule], attr:DotAttr) = {
    mapping.foreach { m => 
      if (m.fimap.get(pin).fold(false){ _ == pout }) {
        attr.color(linkColor).style(bold)
      }
    }
  }

  override def setColor(prt:PRT, attr:DotAttr) = {
    // Color node if any of the inputs is mapped
    mapping.foreach { mp => 
      prt match {
        case prt:PCL =>
          if (mp.cfmap.contains(prt) || io(prt).ins.exists( in => mp.fimap.contains(in)))
            attr.style(filled).fillcolor(color(prt))
        case prt =>
          if (io(prt).ins.exists(in => mp.fimap.contains(in)))
            attr.style(filled).fillcolor(color(prt))
      }
    }
  }

  override def getLabel(prt:PRT):String = {
    mapping.fold(quote(prt)) { mp => 
      prt match {
        case psb:PSB =>
          if (io(psb).ins.exists( in => mp.fimap.contains(in))) { 
            val xbar = io(psb).outs.flatMap { out => 
              mp.fimap.get(out.ic).map{ inic => 
                val in = inic.src.asInstanceOf[PI[PModule]]; s"i-${indexOf(in)} -\\> o-${indexOf(out)}"
              }
            }.mkString(s"|") 
            s"${quote(psb)}|${xbar}"
          } else {
            quote(psb)
          }
        case pcl:PCL =>
          mp.pmmap.get(prt).fold(quote(prt)) { cl => 
            s"${quote(prt)}|${cl}"
          }
      }
    }
  }

  override def setLabel(pin:PGI[PModule], pout:PGO[PModule], attr:DotAttr) = {
    mapping.foreach { m => 
      if (m.fimap.get(pin).fold(false){ _ == pout }) {
        val label = ListBuffer[String]()
        m.vimap.get(pin).foreach { ins =>
          ins.foreach {
            case vi:VI => label += s"${vi.vector}"
            case si:SI => label += s"${si.scalar}"
            case ip:IP => label += s"${ip}"
          }
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
  }

}

class PlasticineCtrlDotPrinter(file:String, open:Boolean)(implicit design:PIR)
  extends PlasticineDotGen(file, open) { 
  def this(file:String)(implicit design:PIR) = this(file, false)
  def this(open:Boolean)(implicit design:PIR) = this(SpadeConfig.spadeCtrlNetwork, open)
  def this()(implicit design:PIR) = this(false)

  val scale = 20

  def io(prt:Routable) = prt.ctrlIO
}

class PlasticineScalarDotPrinter(file:String, open:Boolean)(implicit design:PIR) 
  extends PlasticineDotGen(file, open) { 
  def this(file:String)(implicit design:PIR) = this(file, false)
  def this(open:Boolean)(implicit design:PIR) = this(SpadeConfig.spadeScalarNetwork, open)
  def this()(implicit design:PIR) = this(false)
  
  val scale = 15

  def io(prt:Routable) = prt.scalarIO

}

class PlasticineVectorDotPrinter(file:String, open:Boolean)(implicit design:PIR) 
  extends PlasticineDotGen(file, open) { 
  def this(file:String)(implicit design:PIR) = this(file, false)
  def this(open:Boolean)(implicit design:PIR) = this(SpadeConfig.spadeVectorNetwork, open)
  def this()(implicit design:PIR) = this(false)
  
  val scale = 15

  def io(prt:Routable) = prt.vectorIO
}
