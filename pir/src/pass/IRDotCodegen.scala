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

abstract class CodegenWrapper(implicit design:PIR) extends pir.newnode.Traversal with prism.codegen.Codegen with pir.newnode.ChildFirstTopologicalTraversal {

  val dirName = design.outDir

  def quote(n:Any):String = n match {
    case n:pir.newnode.IR => qdef(n)
    case n => n.toString
  }

  override def runPass = {
    traverseNode(design.newTop)
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
      super.emitNode(n)
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

trait IRDotCodegen extends prism.codegen.Codegen with prism.codegen.DotCodegen {

  type N <: prism.node.Node[N]

  val forward = true
  val horizontal:Boolean = false
  def shouldRun = true
  val fileName:String

  val nodes = mutable.ListBuffer[N]()

  override def initPass = {
    super.initPass
    emitBSln("digraph G")
    if (horizontal) emitln(s"rankdir=LR")
  }

  override def finPass = {
    emitEdges
    emitBEln
    super.finPass
  }

  def emitEdges = { nodes.foreach(emitEdge) }

  def open = {
    s"out/bin/run ${getPath} &".replace(".dot", "") !
  }

  def shape(attr:DotAttr, n:Any) = attr.shape(box)

  def color(attr:DotAttr, n:Any) = attr.fillcolor(white).style(filled)

  def label(attr:DotAttr, n:Any) = attr.label(n.toString)
  def label(attr:DotAttr, n:N) = attr.label(quote(n))

  def emitSubGraph(n:N):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitSubGraph(n, attr) { super.emitNode(n) }
  }

  def emitSingleNode(n:N):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitNode(n,attr)
    nodes += n
  }

  def emitSingleNode(n:Any):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitNode(n,attr)
  }

  override def emitNode(n:N) = {
    n match {
      case _:Atom[_] => emitSingleNode(n)
      case g:SubGraph[_] if g.children.isEmpty => emitSingleNode(n)
      case g:SubGraph[_] => emitSubGraph(n)
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

  def emitEdge(from:N, to:N):Unit = {
    matchLevel(from).foreach { from => super.emitEdge(from, to) }
  }

}

class GlobalIRDotCodegen(val fileName:String)(implicit design:PIR) extends CodegenWrapper with IRDotCodegen {

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
    case n => attr.label(qtype(n))
  }

  //def shape(attr:DotAttr, n:Any) = attr.shape(box)

  override def color(attr:DotAttr, n:Any) = n match {
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
      case n:Module => emitSingleNode(n)
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

class SimpleIRDotCodegen(override val fileName:String)(implicit design:PIR) extends GlobalIRDotCodegen(fileName) {
  override val horizontal:Boolean = false

  override def color(attr:DotAttr, n:Any) = n match {
    case n:CUContainer if collectDown[SRAM](n).nonEmpty => attr.fillcolor(orange).style(filled)
    //case n:CUContainer if n.controller.isOuterControl => attr.fillcolor(indianred).style(filled) ////TODO
    case n => super.color(attr,n)
  }

  override def emitNode(n:N) = {
    n match {
      case g:Top => emitSubGraph(n)
      case g:GlobalContainer => emitSingleNode(n)
      case _ => 
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

class ControllerDotCodegen(val fileName:String)(implicit design:PIR) extends IRDotCodegen with ChildFirstTraversal {

  lazy val metadata = design.newTop.metadata
  import metadata._

  type N = Controller

  val dirName = design.outDir

  //override def label(attr:DotAttr, n:N) = n match {
    //case n => attr.label(qtype(n))
  //}

  //def shape(attr:DotAttr, n:Any) = attr.shape(box)

  override def color(attr:DotAttr, n:Any) = n match {
    case n:SRAM => attr.fillcolor(orange).style(filled)
    case n:RetimingFIFO => attr.fillcolor(gold).style(filled)
    case n:FIFO => attr.fillcolor(gold).style(filled)
    case n:StreamIn => attr.fillcolor(gold).style(filled)
    case n:StreamOut => attr.fillcolor(gold).style(filled)
    case n:Reg => attr.fillcolor(limegreen).style(filled)
    case n:ArgIn => attr.fillcolor(limegreen).style(filled)
    case n:ArgOut => attr.fillcolor(limegreen).style(filled)
    case n:Controller if n.children.nonEmpty => attr.style(dashed)
    case n => super.color(attr, n)
  }

  override def emitSubGraph(n:N):Unit = {
    var attr = DotAttr()
    attr = label(attr, n)
    emitSubGraph(n, attr) { 
      emitSingleNode(n)
      ctrlOf.bmap(n).foreach {
        case mem:Memory if !isLocalMem(mem) => emitSingleNode(mem)
        case _ =>
      }
      traverse(n,())
    }
  }

  //override def emitEdge(from:N, to:N) = {
    //(from, to) match {
      //case (from:ArgInDef, to) if from.parent != to.parent =>
      //case (from:ArgIn, to) if from.parent != to.parent =>
      ////case (from:ArgOut, to) if from.parent != to.parent =>
      //case (from, to) => super.emitEdge(from, to)
    //}
  //}
  val collector = new GraphCollector {
    type N = pir.newnode.Node
  }
  
  override def runPass = {
    traverseNode(design.newTop.topController)
  }

  override def emitEdges = {
    val mems = collector.collectDown[Memory](design.newTop)
    mems.foreach { 
      case mem if !isLocalMem(mem) =>
        mem.readers.foreach { reader => emitEdge(mem, ctrlOf(reader)) }
        mem.writers.foreach { writer => emitEdge(ctrlOf(writer), mem) }
      case mem =>
    }
  }

  def quote(n:Any):String = n match {
    case n:pir.newnode.IR => n.name.map { name => s"${n.className}${n.id}[$name]" }.getOrElse(s"$n")
    case n => n.toString
  }

}

