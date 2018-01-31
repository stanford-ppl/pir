package pir.pass
import pir._
import pir.newnode._
import pir.util._

import pirc._
import pirc.util._
import prism.node._
import prism.traversal._

import sys.process._
import scala.language.postfixOps
import scala.collection.mutable

abstract class CodegenWrapper(implicit design:PIR) extends pir.newnode.Traversal with prism.codegen.Codegen {

  val dirName = design.outDir

  def quote(n:N):String = qdef(n)

  override def traverseNode(n:N, prev:T):T = {
    dbg(s"traverseNode ${qdef(n)}")
    super.traverseNode(n, prev)
  }
  override def runPass = {
    traverseNode(design.newTop, ())
  }
  
}

class IRPrinter(implicit design:PIR) extends CodegenWrapper {

  val fileName = "IRPrinter.txt"

  def horizontal:Boolean = false
  def shouldRun = true
  val forward = true

  override def emitNode(n:N) = {
    emitBlock(qdef(n)) {
      emitln(s"parent=${n.parent}")
      emitln(s"children=${n.children}")
      emitln(s"localDeps=${n.localDeps}")
      emitln(s"localDepeds=${n.localDepeds}")
      traverseChildren(n, ())
      n match {
        case n:Module =>
          n.ios.foreach { io =>
            emitln(s"$io.connected=[${io.connected.mkString(",")}]")
          }
        case _ =>
      }
    }
  }

}

abstract class IRDotCodegen(val fileName:String)(implicit design:PIR) extends CodegenWrapper with prism.codegen.DotCodegen {

  val horizontal:Boolean = false
  def shouldRun = true

  val nodes = mutable.ListBuffer[N]()

  override def runPass = {
    emitBlock("digraph G") {
      if (horizontal) emitln(s"rankdir=LR")
      tic
      emitNode(design.newTop)
      dbg(s"emitNode took ${toc("ms")} ms")
      tic
      nodes.foreach(emitEdge)
      dbg(s"emitEdge took ${toc("ms")} ms")
    }
  }
  
  
  def open = {
    s"out/bin/run ${getPath} &".replace(".dot", "") !
  }

  def shape(attr:DotAttr, n:N) = attr.shape(box)

  def color(attr:DotAttr, n:N) = attr.fillcolor(white).style(filled)

  def label(attr:DotAttr, n:N) = attr.label(qtype(n))

  def emitSubGraph(n:N):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitSubGraph(n, attr) {
      traverseChildren(n, ())
    }
  }

  def emitSingleNode(n:N):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitNode(n,attr)
    nodes += n
  }

  override def emitNode(n:N) = {
    n match {
      case n:Atom[_] => emitSingleNode(n)
      case n:SubGraph[_] if n.children.isEmpty => emitSingleNode(n)
      case n:SubGraph[_] => emitSubGraph(n)
    }
  }

  def matchLevel(n:N):Option[N] = {
    (n::n.ancestors).foreach { n => if (nodes.contains(n)) return Some(n) }
    return None
  }

  def emitEdge(n:N):Unit = {
    n.ins.foreach { 
      case in if in.isConnected =>
        in.connected.foreach { out => emitEdge(out.src.asInstanceOf[N], n) }
      case in =>
    }
  }

  def emitEdge(from:N, to:N) = {
    matchLevel(from).foreach { from =>
      super.emitEdge(from, to)
    }
  }

}

class GlobalIRDotCodegen(fn:String)(implicit design:PIR) extends IRDotCodegen(fn) with prism.traversal.GraphCollector {

  val verbose = true
  override val horizontal:Boolean = if (verbose) false else true

  override def label(attr:DotAttr, n:N) = n match {
    case n:Counter =>
      val fields = n.fieldNames.zip(n.productIterator.toList).flatMap { 
        case (field, Const(v)) => Some(s"$field=$v")
        case _ => None
      }
      attr.label(s"${qtype(n)}\n(${fields.mkString(",")})")
    case n:OpDef => attr.label(s"${qtype(n)}\n(${n.op})")
    case n:StreamIn => attr.label(s"${qtype(n)}\n(${n.field})")
    case n:StreamOut => attr.label(s"${qtype(n)}\n(${n.field})")
    case n => super.label(attr, n)
  }

  //def shape(attr:DotAttr, n:N) = attr.shape(box)

  override def color(attr:DotAttr, n:N) = n match {
    case n:SRAM => attr.fillcolor(orange).style(filled)
    case n:RetimingFIFO => attr.fillcolor(gold).style(filled)
    case n:FIFO => attr.fillcolor(gold).style(filled)
    case n:StreamIn => attr.fillcolor(gold).style(filled)
    case n:StreamOut => attr.fillcolor(gold).style(filled)
    case n:Reg => attr.fillcolor(limegreen).style(filled)
    case n:ArgIn => attr.fillcolor(limegreen).style(filled)
    case n:ArgOut => attr.fillcolor(limegreen).style(filled)
    case n:Counter => attr.fillcolor(indianred).style(filled)
    case n:CUContainer => attr.fillcolor(deepskyblue).style(filled)
    case n:FringeContainer => attr.fillcolor(chartreuse).style(filled)
    case n => super.color(attr, n)
  }

  override def emitNode(n:N) = {
    n match {
      case n:Const[_] if collectOut[Counter](n).isEmpty =>
      case n:Module =>  
        if (verbose) {
          emitSingleNode(n)
        } else {

        }
      case n => super.emitNode(n) 
    }
  }

  override def emitEdge(from:N, to:N) = {
    (from, to) match {
      case (from:ArgInDef, to) if from.parent != to.parent =>
      case (from:ArgIn, to) if from.parent != to.parent =>
      //case (from:ArgOut, to) if from.parent != to.parent =>
      case (from, to) => super.emitEdge(from, to)
    }
  }

}

class LocalIRDotCodegen(fn:String)(implicit design:PIR) extends GlobalIRDotCodegen(fn) {
  override def emitNode(n:N) = {
    n match {
      case n:CUContainer if List("x5074", "x4725_d0_b0", "x5055").contains(n.name.get) => emitSubGraph(n)
      case n:CUContainer =>  
      case n => super.emitNode(n)
    }
  }
}
