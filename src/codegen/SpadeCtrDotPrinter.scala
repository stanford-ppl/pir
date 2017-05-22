package pir.codegen

import pir.{Design, Config}
import pir.codegen._
import pir.util._
import pir.util.typealias._
import pir.mapper.{PIRMap}
import pir.exceptions._
import pir.util.misc._
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.util._

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

class CtrDotPrinter(fn:String)(implicit design:Design) extends DotCodegen { 

  def this()(implicit design:Design) = this(Config.spadeCtr)

  override lazy val stream = newStream(fn) 

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

