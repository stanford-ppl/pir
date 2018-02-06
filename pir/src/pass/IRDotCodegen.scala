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

abstract class CodegenWrapper(implicit design:PIR) extends PIRTraversal with prism.codegen.Codegen with pir.newnode.DFSTopDownTopologicalTraversal {

  val forward = true
  val dirName = design.outDir

  def quote(n:Any):String = qdef(n)

  override def runPass = {
    traverseNode(design.newTop)
  }
  
}

class IRPrinter(val fileName:String)(implicit design:PIR) extends CodegenWrapper {

  def shouldRun = Config.debug

  override def quote(n:Any) = qtype(n)

  override def emitNode(n:N) = {
    n match {
      case n:SubGraph[_] =>
        emitBlock(qdef(n)) {
          emitln(s"parent=${quote(n.parent)}")
          traverse(n)
        }
      case n:Atom[_] =>
        emitBlock(qdef(n)) {
          emitln(s"parent=${quote(n.parent)}")
          emitln(s"deps=${n.deps.map(quote)}")
          emitln(s"depeds=${n.depeds.map(quote)}")
          n.ios.foreach { io =>
            emitln(s"$io.connected=[${io.connected.mkString(",")}]")
          }
        }
        traverse(n)
    }
  }

}

class ControllerPrinter(implicit design:PIR) extends pir.newnode.Pass with prism.codegen.Codegen with ChildFirstTraversal {
  val fileName = "CtrlPrinter.txt"

  type N = Controller
  def shouldRun = Config.debug

  val dirName = design.outDir

  def quote(n:Any) = qdef(n)

  override def emitNode(n:N) = {
    emitBlock(qdef(n)) { traverse(n) }
  }

  override def runPass = {
    traverseNode(design.newTop.topController)
  }
}

trait IRDotCodegen extends prism.codegen.Codegen with prism.codegen.DotCodegen {

  type N <: prism.node.Node[N]

  val horizontal:Boolean = false
  def shouldRun = Config.debug
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

  def label(attr:DotAttr, n:Any) = attr.label(quote(n))

  def emitSubGraph(n:N)(block: => Unit):Unit = {
    var attr = DotAttr()
    attr = shape(attr, n)
    attr = color(attr, n)
    attr = label(attr, n)
    emitSubGraph(n, attr) { block }
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
      case _:Atom[_] => emitSingleNode(n); traverse(n) 
      case g:SubGraph[_] if g.children.isEmpty => emitSingleNode(n); traverse(n) 
      case g:SubGraph[_] => emitSubGraph(n) { traverse(n) }
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

  import pirmeta._

  override def quote(n:Any):String = qtype(n)

  override def label(attr:DotAttr, n:Any) = {
    var label = quote(n) 
    n match {
      case n:Counter =>
        val fields = n.fieldNames.zip(n.productIterator.toList).flatMap { 
          case (field, Const(v)) => Some(s"$field=$v")
          case _ => None
        }
        label += s"\n(${fields.mkString(",")})"
      case n:OpDef => label += s"\n(${n.op})"
      case n:StreamIn => label += s"\n(${n.field})"
      case n:StreamOut => label +=s"\n(${n.field})"
      case n =>
    }
    n match {
      case n:pir.newnode.Node => ctrlOf.get(n).foreach { ctrl => label += s"\n(${quote(ctrl)})" }
      case _ =>
    }
    attr.label(label)
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
      case (from, to:ArgIn) if from.parent != to.parent =>
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
      case g:Top => emitSubGraph(n)(traverse(n))
      case g:GlobalContainer => emitSingleNode(n); traverse(n)
      case _ => traverse(n)
    }
  }
}

class LocalIRDotCodegen(fn:String)(implicit design:PIR) extends GlobalIRDotCodegen(fn) {
  override def emitNode(n:N) = {
    n match {
      case n:CUContainer if List("x5074", "x4725_d0_b0", "x5055").contains(n.name.get) => emitSubGraph(n)(traverse(n))
      case n:CUContainer => traverse(n)
      case n => super.emitNode(n)
    }
  }
}

class ControllerDotCodegen(val fileName:String)(implicit design:PIR) extends pir.newnode.Pass with IRDotCodegen with ChildFirstTraversal {

  import pirmeta._

  type N = Controller

  val dirName = design.outDir

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

  override def emitSubGraph(n:N)(block: => Unit):Unit = {
    var attr = DotAttr()
    attr = label(attr, n)
    emitSubGraph(n, attr) { 
      emitSingleNode(n)
      ctrlOf.bmap(n).foreach {
        case mem:Memory if !isLocalMem(mem) => emitSingleNode(mem)
        case _ =>
      }
      block
    }
  }

  val collector = new GraphCollector {
    type N = pir.newnode.Node
  }
  
  override def runPass = {
    traverseNode(design.newTop.topController)
  }

  override def emitEdges = {
    val mems = collectDown[Memory](design.newTop)
    mems.foreach { 
      case mem:ArgIn =>
        mem.readers.foreach { reader => emitEdge(mem, ctrlOf(reader)) }
      case mem:ArgOut =>
        mem.writers.foreach { writer => emitEdge(ctrlOf(writer), mem) }
      case mem if !isLocalMem(mem) =>
        mem.readers.foreach { reader => emitEdge(mem, ctrlOf(reader)) }
        mem.writers.foreach { writer => emitEdge(ctrlOf(writer), mem) }
      case mem =>
    }
  }

  def quote(n:Any):String = qtype(n)

}

