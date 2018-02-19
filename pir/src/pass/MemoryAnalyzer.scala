package pir.pass

import pir._
import pir.node._

import pirc._
import pirc.util._
import prism.traversal._

import scala.collection.mutable
import scala.reflect._

class MemoryAnalyzer(implicit design:PIR) extends PIRTransformer {
  import pirmeta._

  def shouldRun = true

  val traversal = new ControllerTraversal {}
  def setParentControl(mem:Memory) = dbgblk(s"setParentControl($mem)") {
    dbg(s"accesses: ${mem.accesses}")
    var accessCtrls = mem.accesses.map { access => 
      dbg(s"access:$access ctrl=${ctrlOf(access)}")
      ctrlOf(access)
    }
    mem match {
      case mem:ArgOut => accessCtrls += design.top.argController
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
      val topCtrl = ancestors(topCtrlIdx)
      val newAccess = access match {
        case Def(n, LocalStore(mems, addrs, data)) if topCtrlOf.get(access).fold(false){ _ != topCtrl} =>
          // store access write to multiple mems with different topControl. Duplicate the access
          val accessCU = globalOf(n).get 
          dbg(s"disconnecting $n from $mem")
          n.outs.foreach { out =>
            dbg(s"out ${out} ${out.connected.map(_.src)}")
          }
          val maccess = mirror(
            node=n, 
            container=Some(accessCU), 
            init=Map(mems -> List(mem), addrs -> addrs, data -> data),
            mirrorRule=NodeMatchRule(n, (n:Any,m:Any) => pirmeta.mirrorExcept(n, m, topCtrlOf))
          )
          disconnect(n, mem)
          dbg(s"duplicating ${qtype(n)} -> ${qtype(maccess)} for different topCtrl")
          maccess
        case n => n
      }
      topCtrlOf(newAccess) = topCtrl
      topCtrlOf.info(newAccess).foreach(dbg)
    }

    val isLocalMem = mem.accesses.map(a => ctrlOf(a)).toSet.size==1
    isInnerAccum(mem) = isLocalMem && isAccum(mem)
    isInnerAccum.info(mem).foreach(dbg)
  }

  override def runPass =  {
    lazy val mems = collectDown[Memory](design.top)
    mems.foreach { mem =>
      setParentControl(mem)
    }
  }

}

