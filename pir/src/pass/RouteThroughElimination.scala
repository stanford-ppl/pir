package pir.pass

import pir._
import pir.node._

import pirc._

import prism.traversal._

import scala.collection.mutable
import scala.reflect._



class RouteThroughElimination(implicit design:PIR) extends PIRTransformer with BFSBottomUpTopologicalTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    traverseNode(design.top, ())
  }

  override def visitNode(n:N, prev:T):T = {
    n match {
      // Pattern if write is inside mem CU
      case Def(write:LocalStore, LocalStore(mems,None,Def(rread, LocalLoad(WithWriter(Def(rwrite, LocalStore(rmem::Nil, None, data)))::Nil,None)))) =>
        dbgblk(s"Found Route Through ${qdef(write)}") {
          dbgs(s"pattern: data -> rwirte -> rmem -> rread -> write -> mem => data -> write -> mems")
          dbg(s"data:${qdef(data)}")
          dbg(s"rwrite:${qdef(rwrite)}")
          dbg(s"rmem:${qdef(rmem)}")
          dbg(s"rread:${qdef(rread)}")
          dbg(s"write:${qdef(write)}")
          dbg(s"mems:${qdef(mems)}")
          swapConnection(write, rread.out, data.out)
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

