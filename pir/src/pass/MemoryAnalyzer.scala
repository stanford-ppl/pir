package pir.pass

import pir._
import pir.node._

import pirc._
import pirc.util._
import prism.traversal._

import scala.collection.mutable
import scala.reflect._

class MemoryAnalyzer(implicit design:PIR) extends PIRPass {
  import pirmeta._

  type N = Node with Product
  type T = Unit

  def shouldRun = true

  val traversal = new ControllerTraversal {}
  def setParentControl(mem:Memory) = dbgblk(s"setParentControl($mem)") {
    dbg(s"accesses: ${mem.accesses}")
    var accessCtrls = mem.accesses.map { access => 
      dbg(s"access:$access ctrl=${ctrlOf(access)}")
      ctrlOf(access)
    }
    mem match {
      case mem:ArgOut => accessCtrls += design.newTop.argController
      case _ =>
    }
    val lcaCtrl = accessCtrls.reduce[Controller]{ case (a1, a2) =>
      val lca = traversal.leastCommonAncesstor(a1, a2)
      dbg(s"a1=$a1, a2=$a2, lca=$lca")
      if (lca.isEmpty) err(s"$a1 and $a2 do not share common ancestor")
      lca.get
    }
    ctrlOf(mem) = lcaCtrl
    ctrlOf.info(mem).foreach(dbg)

    mem.accesses.foreach { access =>
      val ancestors = ctrlOf(access) :: ctrlOf(access).ancestors
      val idx = ancestors.indexOf(lcaCtrl)
      val topCtrlIdx = if (idx==0) idx else idx - 1
      topCtrlOf(access) = ancestors(topCtrlIdx)
      topCtrlOf.info(access).foreach(dbg)
    }

    isLocalMem(mem) = mem.accesses.forall(a => ctrlOf(a) == ctrlOf(mem))
    isLocalMem.info(mem).foreach(dbg)
  }

  override def runPass =  {
    lazy val mems = collectDown[Memory](design.newTop)
    mems.foreach { mem =>
      setParentControl(mem)
    }
  }

}

