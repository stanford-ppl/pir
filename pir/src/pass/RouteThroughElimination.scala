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
    traverseNode(design.newTop, ())
  }

  override def visitNode(n:N, prev:T):T = {
    n match {
      case Def(rwrite:WriteMems, WriteMems(mems,Def(rread, ReadMem(WithWriter(Def(write, WriteMems(rmem::Nil, data))))))) =>
        dbgblk(s"Found Route Through ${qdef(write)}") {
          dbgs(s"pattern: data -> rwirte -> rmem -> rread -> write -> mem => data -> write -> mems")
          dbg(s"data:${qdef(data)}")
          dbg(s"rwrite:${qdef(rwrite)}")
          dbg(s"rmem:${qdef(rmem)}")
          dbg(s"rread:${qdef(rread)}")
          dbg(s"write:${qdef(write)}")
          dbg(s"mems:${qdef(mems)}")
          mems.foreach { mem =>
            swapConnection(mem, rwrite.out, write.out)
          }
        }
      //case Def(load:ReadMem, ReadMem(WithWriter(Def(rwrite:WriteMems, WriteMems(rmem::Nil, Def(rread:ReadMem, ReadMem(mem))))))) =>
        //dbgblk(s"Found Route Through ${qdef(load)}") {
          //dbgs(s"pattern: ")
          //dbg(s"rread:${qdef(rread)}")
          //dbg(s"rwrite:${qdef(rwrite)}")
          //dbg(s"rmem:${qdef(rmem)}")
          //dbg(s"load:${qdef(load)}")
          //swapConnection(load, rmem.out, mem.out)
        //}
      case _ => dbg(s"unmatched ${qdef(n)}")
    }
    super.visitNode(n, prev)
  }

}

