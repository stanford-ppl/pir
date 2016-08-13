package pir.graph.traversal
import pir.graph._
import pir._
import pir.PIRMisc._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Queue

class CtrlAlloc(implicit val design: Design) extends Traversal{
  // Collect outer controllers that are in the same CU
  private def collectOuters = {
    design.top.innerCUs.foreach { inner =>
      val outers = ListBuffer[Controller]()
      var parent = inner.parent
      var child = inner
      while (!parent.isInstanceOf[Top]) {
        if (child.isTail)
          outers += parent
        parent = parent.asInstanceOf[ComputeUnit].parent
      }
      inner.outers = outers.toList
    }
  }
  // Allocate token buffer and credit buffer in all compute units 
  private def bufferAlloc = {
    design.top.compUnits.foreach { cu =>
      if (cu.isHead) {
        // no dep, buffer for parent
        cu.ctrlBox.tokenBuffers += design.top -> TokenBuffer(null, 1)(cu, design)
      } else {
        val parent = cu.parent.asInstanceOf[ComputeUnit] // Cannot be the top-level
        val tokenInit = parent.tpe match {
          case MetaPipeline => parent.children.size
          case Sequential => 1
          case _ => throw PIRException(s"unknown parent type ${parent.tpe}")
        }
        cu.dependencies.foreach { dep =>
          cu.ctrlBox.tokenBuffers += dep -> TokenBuffer(dep, tokenInit)(cu, design)
        }
      }
      if (cu.parent.isInstanceOf[ComputeUnit]) {
        val parent = cu.parent.asInstanceOf[ComputeUnit] // Cannot be the top-level
        if (parent.tpe==MetaPipeline) {
          cu.dependeds.foreach { deped =>
            cu.ctrlBox.creditBuffers += deped -> CreditBuffer(deped)(cu, design)
          }
        }
      }
    }
  }
  def qi(ins:List[InPort]) = ins.map{ in =>
    in match {
      case p:EnInPort => p.inner 
      case _ => in.src
    }
  }
  def qo(outs:List[OutPort]) = outs.map { out => 
    out match {
      case p:DoneOutPort => p.outer 
      case _ => out.src
    }
  }
  def q(in:InPort) = in match {
    case p:EnInPort => p.inner 
    case _ => in.src
  }
  def q(out:OutPort) = out match {
    case p:DoneOutPort => p.outer 
    case _ => out.src
  }
  // Generate logic on control I/Os
  def genEnDec:Unit = {
    design.top.compUnits.foreach { implicit cu =>
      val en = cu.ctrlBox.innerCtrEn
      cu.tpe match {
        case Pipe =>
          val parent = cu.parent.asInstanceOf[ComputeUnit]
          val tks = cu.ctrlBox.tokenBuffers
          val cds = cu.ctrlBox.creditBuffers
          val ins = tks.map(_._2.out).toList ++ cds.map(_._2.out).toList
          def tf(ins:List[Boolean]) = ins.reduce(_ && _) 
          en.connect(EnLUT(cu, ins, tf _, s"${qo(ins).mkString(s" && ")}"))
        case _ =>
          val lasts = cu.children.filter(_.isTail)
          if (lasts.size!=1) throw PIRException("Currently only support a single last stage")
          en.connect(lasts.head.ctrlBox.outerCtrDone)
      }
      cu.ctrlBox.tokenBuffers.foreach { case (dep, t) =>
        t.dec.connect(cu.ctrlBox.outerCtrDone)
      }
      cu.ctrlBox.creditBuffers.foreach { case (dep, c) =>
        c.dec.connect(cu.ctrlBox.outerCtrDone)
      }
    }
  }
  def genTokenOut:Unit = {
    design.top.compUnits.foreach { implicit cu =>
      cu.parent match {
        case parent:ComputeUnit =>
          if (!cu.isTail) {
            val ins = cu.ctrlBox.outerCtrDone::Nil
            def tf(ins:List[Boolean]) = ins.head
            cu.ctrlBox.tokenOut = TokenOutLUT(cu, ins, tf _, s"${q(ins.head)}")
          } else {
            val c = cu.ctrlBox.outerCtrDone
            val p = parent.ctrlBox.outerCtrDone
            val ins = c::p::Nil
            def tf(ins:List[Boolean]) = { val c::p::_ = ins; !p && c }
            cu.ctrlBox.tokenOut = TokenOutLUT(cu, ins, tf _, s"!${q(p)} && ${q(c)}")
          }
          case t:Top => 
            val ins = cu.ctrlBox.outerCtrDone::Nil
            def tf(ins:List[Boolean]) = { val c::_ = ins; c }
            cu.ctrlBox.tokenOut = TokenOutLUT(cu, ins, tf _, s"${q(ins.head)}")
            case _ =>
      }
    }
  }
  def genTokenDown:Unit = {
    val queue = Queue[ComputeUnit]() 
    queue ++= design.top.children
    while (!queue.isEmpty) {
      implicit val cu = queue.dequeue 
      if (cu.tpe!=Pipe) {
        if (cu.isHead) {
          val td:OutPort = cu.parent match {
            case t:Top => t.command 
            case c:ComputeUnit => c.ctrlBox.tokenDown 
            case _ => throw PIRException(s"unknown parent type")
          } 
          val ins = td :: cu.ctrlBox.tokenBuffers.map { case (dep, tk) => tk.out }.toList
          def tf(ins:List[Boolean]) = { val init::tos = ins; init || tos.reduce(_ && _) }
          val init::tos = ins
          cu.ctrlBox.tokenDown = 
            TokenDownLUT(cu, ins, tf _, s"${q(init)} || ${qo(tos).mkString( "&&")}")
        } else {
          val ins = cu.ctrlBox.tokenBuffers.map { case (dep, tk) => tk.out }.toList
          def tf(ins:List[Boolean]) = {ins.reduce(_ && _) }
          cu.ctrlBox.tokenDown = TokenDownLUT(cu, ins, tf _, qo(ins).mkString( "&&"))
        }
      }
      queue ++= cu.children
    }
  }
  def connectInputs = {
    design.top.compUnits.foreach { implicit cu =>
      if (cu.isHead) {
        // Assume a single last stage, which means a single tokenbuffer for first stage
        val tk = cu.ctrlBox.tokenBuffers.head._2
        val lasts = cu.parent.children.filter(_.isTail)
        assert(lasts.size==1)
        val last = lasts.head
        if (cu!=last) {
          tk.inc.connect(last.ctrlBox.tokenOut)
          cu.ctrlBox.creditBuffers.foreach { case (deped, cd) =>
            cd.inc.connect(deped.asInstanceOf[ComputeUnit].ctrlBox.outerCtrDone) 
          }
        }
        val tokenDown = cu.parent match {
          case t:Top => t.command
          case c:ComputeUnit => c.ctrlBox.tokenDown
          case _ => throw PIRException(s"unknown parent type")
        }
        tk.init.connect(tokenDown)
      } else {
        cu.ctrlBox.tokenBuffers.foreach { case (dep, tk) =>
          val dto = dep.asInstanceOf[ComputeUnit].ctrlBox.tokenOut
          tk.inc.connect(dep.asInstanceOf[ComputeUnit].ctrlBox.tokenOut)
        }
      }
      cu.ctrlBox.creditBuffers.foreach { case (deped, cd) =>
        cd.inc.connect(deped.asInstanceOf[ComputeUnit].ctrlBox.outerCtrDone)
      }
    }
    design.top.status.connect(design.top.children.head.ctrlBox.outerCtrDone)
  }
  def logicGen = {
    genEnDec
    genTokenOut
    genTokenDown
    connectInputs
  }
  override def traverse:Unit = {
    collectOuters
    bufferAlloc
    logicGen
  } 
  override def finPass = {
    info("Finishing control logic allocation")
  }
}
object CtrlCodegen {
  def lookUp(numBits:Int, transFunc: List[Boolean] => Boolean):List[Boolean] = {
    val size:Int = Math.pow(2, numBits).toInt
    val table = ListBuffer[Boolean]()
    for (i <- 0 until size) {
      var inputs = i.toBinaryString.toList.map(_ == '1') // Boolean inputs
      inputs = List.fill(numBits-inputs.size)(false) ++ inputs
      table += transFunc(inputs)
    }
    table.toList
  }
  def printTable(table:List[Boolean]) = {
    val size = table.size
    val numBits = Math.ceil(Math.log(size)/Math.log(2)).toInt
    println(s"----- Start ------")
    for (i <- 0 until size) {
      println(f"${int2Bin(i, numBits+1)} ${bool2Bin(table(i))}")
    }
    println(s"----- End ------")
  }

  def int2Bin(i:Int, width:Int):String = {
    val fmt = s"%${width}s"
    String.format(fmt, Integer.toBinaryString(i)).replace(' ', '0')
  }

  def bool2Bin(i:Boolean):String = if (i) "1" else "0"
}
