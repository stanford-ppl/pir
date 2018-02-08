package pir.pass

import pir._
import pir.node._

import pirc._

import prism.traversal._

import scala.collection.mutable
import scala.reflect._



class RouteThroughElimination(implicit design:PIR) extends PIRTransformer with BottomUpTopologicalTraversal with BFSTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    traverseScope(design.newTop, ())
  }

  override def visitNode(n:N, prev:T):T = {
    n match {
      case Def(load:MemLoad, MemLoad(WithWriters(Def(rstore:MemStore, MemStore(rmem, None, Def(rload:MemLoad, MemLoad(mem, None))))::Nil), None)) =>
        dbgblk(s"Found Route Through ${qdef(load)}") {
          dbg(s"rload:${qdef(rload)}")
          dbg(s"rstore:${qdef(rstore)}")
          dbg(s"rmem:${qdef(rmem)}")
          dbg(s"load:${qdef(load)}")
          swapConnection(load, rmem.out, mem.out)
        }
      case _ =>
    }
    super.visitNode(n, prev)
  }

}

