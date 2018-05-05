package pir.pass

import pir.node._

import scala.collection.mutable

class MemoryAnalyzer(implicit compiler:PIR) extends PIRTransformer {
  import pirmeta._

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
            init=mutable.Map(mems -> List(mem), addrs -> addrs, data -> data),
            mirrorRule=PresetRule(n, topCtrlOf, topCtrl)
          )
          disconnect(n, mem)
          dbg(s"duplicating ${qtype(n)} -> ${qtype(maccess)} for different topCtrl")
          maccess
        case n => n
      }
      topCtrlOf(newAccess) = topCtrl
      topCtrlOf.info(newAccess).foreach(dbg)
    }
  }

  override def runPass =  {
    val mems = compiler.top.collectDown[Memory]()
    mems.foreach { mem =>
      setTopCtrl(mem)
    }
  }

}

