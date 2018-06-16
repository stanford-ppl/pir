package pir
package pass

import pir.node._

import scala.collection.mutable

class MemoryAnalyzer(implicit compiler:PIR) extends SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  override def runPass =  {
    traverseNode(compiler.top)
  }

  override def visitNode(n:N) = { 
    n match {
      case n:Memory if !ctrlOf.contains(n) => setTopCtrl(n)
      case n => super.visitNode(n)
    }
  }

  def setTopCtrl(mem:Memory) = dbgblk(s"setTopCtrl($mem)") {
    val accesses = accessesOf(mem)
    dbg(s"accesses: ${accesses}")
    val accessCtrls:List[Controller] = accesses.map { access => 
      dbg(s"access:$access ctrl=${ctrlOf(access)}")
      ctrlOf(access)
    }
    val lcaCtrl = leastCommonAncesstor(accessCtrls).getOrElse {
      throw PIRException(s"${accessCtrls} do not share common ancestor")
    }
    ctrlOf(mem) = lcaCtrl
    ctrlOf.info(mem).foreach(dbg)

    val topCtrls = leastMatchedPeers(accessCtrls, Some(lcaCtrl)).get
    accesses.foreach { access =>
      val topCtrl = topCtrls(ctrlOf(access))
      val newAccess = access
      topCtrlOf(newAccess) = topCtrl
      topCtrlOf.info(newAccess).foreach(dbg)
    }
  }


}

