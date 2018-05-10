package pir
package pass

import pir.node._
import scala.collection.mutable

class RouteThroughElimination(implicit compiler:PIR) extends PIRTransformer with BFSTopologicalTraversal with UnitTraversal {
  import pirmeta._

  val forward = false

  override def runPass =  {
    traverseNode(compiler.top, ())
  }

  override def visitNode(n:N, prev:T):T = {
    n match {
      // Pattern if write is inside mem CU
      case Def(rwrite:LocalStore, LocalStore(mem::Nil,None,Def(rread, LocalLoad(WithWriter(Def(write, LocalStore(rmem::Nil, None, data)))::Nil,None)))) =>
        //TODO: check mem and rmem are off the same time. i.e. both reg or both fifo
        dbgblk(s"Found Route Through ${qdef(write)}") {
          dbgs(s"pattern: data -> wirte -> rmem -> rread -> rwrite -> mem => data -> write -> mem")
          dbg(s"data:${qdef(data)}")
          dbg(s"write:${qdef(write)}")
          dbg(s"rmem:${qdef(rmem)}")
          dbg(s"rread:${qdef(rread)}")
          dbg(s"rwrite:${qdef(rwrite)}")
          dbg(s"mem:${qdef(mem)}")
          val memCU = globalOf(mem).get
          disconnect(write, rmem)
          swapConnection(mem, rwrite.out, write.out)
          swapParent(write, memCU)
        }
      // Pattern if write is inside writer CU
      //case Def(rwrite:LocalStore, LocalStore(mems,None,Def(rread, LocalLoad(WithWriter(Def(write, LocalStore(rmem::Nil, None, data)))::Nil,None)))) =>
        //dbgblk(s"Found Route Through ${qdef(write)}") {
          //dbgs(s"pattern: data -> rwirte -> rmem -> rread -> write -> mem => data -> write -> mems")
          //dbg(s"data:${qdef(data)}")
          //dbg(s"rwrite:${qdef(rwrite)}")
          //dbg(s"rmem:${qdef(rmem)}")
          //dbg(s"rread:${qdef(rread)}")
          //dbg(s"write:${qdef(write)}")
          //dbg(s"mems:${qdef(mems)}")
          //mems.foreach { mem =>
            //swapConnection(mem, rwrite.out, write.out)
          //}
        //}
      case _ => dbg(s"unmatched ${qdef(n)}")
    }
    super.visitNode(n, prev)
  }

}

