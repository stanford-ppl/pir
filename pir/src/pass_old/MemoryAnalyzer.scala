package pir
package pass

import pir.node._

import scala.collection.mutable

class MemoryAnalyzer(implicit compiler:PIR) extends SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  override def visitNode(n:N) = { 
    n match {
      case n:Memory => setCtrl(n)
      case n => super.visitNode(n)
    }
  }

  def setCtrl(mem:Memory) = dbgblk(s"setCtrl($mem)") {
    val accesses = accessesOf(mem)
    dbg(s"accesses: ${accesses}")
    val accessCtrls:List[Controller] = accesses.map { access => 
      dbg(s"access:$access ctrl=${ctrlOf(access)}")
      ctrlOf(access)
    }
    val lcaCtrl = leastCommonAncesstor(accessCtrls).getOrElse {
      throw PIRException(s"${accessCtrls} do not share common ancestor")
    }
    ctrlOf.removeKey(mem)
    ctrlOf(mem) = lcaCtrl
    ctrlOf.info(mem).foreach(dbg)
  }

}

