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

abstract class PlasticineDotGen(fn:String)(implicit design:PIR) 
  extends spade.codegen.PlasticineDotGen(fn)(design.arch) {
  import spademeta._
  design.plasticineDotPrinters += this

  override lazy val stream = if (design.mapping.isDefined) newStream(fn)(design) else newStream(fn)(design.arch)

  private var _mapping:Option[PIRMap] = None
  override def mapping:Option[PIRMap] = _mapping

  override def print:this.type = { 
    if (mapping.isEmpty) this._mapping = design.mapping
    super.print
  }

  def print(mapping:Option[PIRMap]):this.type = {
    this._mapping = mapping
    super.print
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
          mp.pmmap.get[CL](prt).fold(quote(prt)) { cl => 
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
            case vi:GI => label += s"${vi.variable}"
            case ip:I => label += s"${ip}"
          }
        }
        m.vomap.get(pout).foreach { out =>
          out match {
            case vo:GO => label += s"${vo.variable}"
            case op:O => label += s"${op}"
          }
        }
        attr.label(label.mkString("\n"))
      }
    }
  }

}

class PlasticineCtrlDotPrinter(file:String)(implicit design:PIR)
  extends PlasticineDotGen(file) { 
  def this()(implicit design:PIR) = this(SpadeConfig.spadeCtrlNetwork)

  val scale = 20

  def io(prt:Routable) = prt.ctrlIO
}

class PlasticineScalarDotPrinter(file:String)(implicit design:PIR) 
  extends PlasticineDotGen(file) { 
  def this()(implicit design:PIR) = this(SpadeConfig.spadeScalarNetwork)
  
  val scale = 20

  def io(prt:Routable) = prt.scalarIO

}

class PlasticineVectorDotPrinter(file:String)(implicit design:PIR) 
  extends PlasticineDotGen(file) { 
  def this()(implicit design:PIR) = this(SpadeConfig.spadeVectorNetwork)
  
  val scale = 15

  def io(prt:Routable) = prt.vectorIO
}
