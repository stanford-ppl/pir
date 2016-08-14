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
  // Generate logic on control I/Os
  def genEnDec:Unit = {
    design.top.compUnits.foreach { implicit cu =>
      val en = cu.ctrlBox.innerCtrEn
      cu.tpe match {
        case Pipe =>
          val parent = cu.parent.asInstanceOf[ComputeUnit]
          val tks = cu.ctrlBox.tokenBuffers.map(_._2.out).toList
          val cds = cu.ctrlBox.creditBuffers.map(_._2.out).toList
          val ins = tks ++ cds
          val tf = TransferFunction(s"${ins.mkString(s" && ")}") { case (map, ins) =>
            //val tkIns = tks.map(tk => ins(map(tk)))
            //val cdIns = cds.map(cd => ins(map(cd)))
            ins.reduce(_ && _)
          }
          en.connect(EnLUT(cu, ins, tf))
        case _ =>
          val lasts = cu.children.filter(_.isTail)
          if (lasts.size!=1) throw PIRException("Currently only support a single last stage")
          en.connect(lasts.head.ctrlBox.outerCtrDone)
      }
      cu.ctrlBox.tokenBuffers.foreach { case (dep, t) => t.dec.connect(cu.ctrlBox.outerCtrDone) }
      cu.ctrlBox.creditBuffers.foreach { case (dep, c) => c.dec.connect(cu.ctrlBox.outerCtrDone) }
    }
  }
  def genTokenOut:Unit = {
    design.top.compUnits.foreach { implicit cu =>
      cu.parent match {
        case parent:ComputeUnit =>
          if (!cu.isTail) {
            val done = cu.ctrlBox.outerCtrDone
            val tf = TransferFunction(s"${done}") { case (map, ins) => ins(map(done)) }
            cu.ctrlBox.tokenOut = TokenOutLUT(cu, done::Nil, tf)
          } else {
            val c = cu.ctrlBox.outerCtrDone
            val p = parent.ctrlBox.outerCtrDone
            val ins = c::p::Nil
            val tf = TransferFunction(s"!${p} && ${c}") { case (map, ins) =>
              !ins(map(p)) && ins(map(c))
            }
            cu.ctrlBox.tokenOut = TokenOutLUT(cu, ins, tf)
          }
          case t:Top => 
            val done = cu.ctrlBox.outerCtrDone
            val tf = TransferFunction(s"${done}") { case (map, ins) =>
              ins(map(done))
            }
            cu.ctrlBox.tokenOut = TokenOutLUT(cu, done::Nil, tf)
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
          val init:OutPort = cu.parent match {
            case t:Top => t.command 
            case c:ComputeUnit => c.ctrlBox.tokenDown 
            case _ => throw PIRException(s"unknown parent type")
          } 
          val tos = cu.ctrlBox.tokenBuffers.map { case (dep, tk) => tk.out }.toList
          val tf = TransferFunction(s"${init} || ${tos.mkString( "&&")}") { case (map, ins) =>
            ins(map(init)) || tos.map(to => ins(map(to))).reduce(_ && _)
          }
          cu.ctrlBox.tokenDown = TokenDownLUT(cu, init::tos, tf)
        } else {
          val tos = cu.ctrlBox.tokenBuffers.map { case (dep, tk) => tk.out }.toList
          val tf = TransferFunction(s"${tos.mkString( "&&")}") { case (map, ins) =>
            tos.map(to => ins(map(to))).reduce(_ && _)
          }
          cu.ctrlBox.tokenDown = TokenDownLUT(cu, tos, tf)
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
  def wireCChainCopy = {
    design.top.innerCUs.foreach { inner =>
      inner.cchains.foreach { cc =>
        if (!cc.inner.en.isConnected) {
          cc.copy.foreach { original =>
            original.ctrler match {
              case cu:InnerComputeUnit =>
                cc.inner.en.connect(original.inner.en.from)
              case cu:OuterComputeUnit =>
                var child:ComputeUnit = inner
                while (child.parent!=cu) {
                  val parent = child.parent.asInstanceOf[ComputeUnit]
                  val plocal = inner.getCopy(parent.localCChain)
                  val clocal = inner.getCopy(child.localCChain)
                  plocal.inner.en.connect(clocal.outer.done)
                  child = child.parent.asInstanceOf[ComputeUnit]
                  if (child.parent.isInstanceOf[Top]) {
                    throw PIRException(s"${inner} made cchain copy ${cc} of non-ancestor outer controler ${cu}")
                  }
                }
                val clocal = inner.getCopy(child.localCChain)
                cc.inner.en.connect(clocal.outer.done)

            }
          }
        }
      }
    }
  }
  def logicGen = {
    genEnDec
    genTokenOut
    genTokenDown
    connectInputs
    wireCChainCopy
  }
  override def traverse:Unit = {
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
