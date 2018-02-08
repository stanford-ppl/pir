package pir.pass

import pir._
import pir.node._

import pirc._

import prism.traversal._

import scala.collection.mutable
import scala.reflect._

class AccessLowering(implicit design:PIR) extends PIRTransformer with ChildFirstTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    traverseNode(design.newTop)
  }

  def retimeX(x:Def, cu:GlobalContainer, mapping:Map[Any,Any]):Map[Any,Any] = {
    x match {
      case x:Const[_] => mapping + (x -> x)
      case Def(x:IterDef, IterDef(counter, offset)) => mirrorX(x, cu, mapping)
      case x =>
        val xCU = collectUp[GlobalContainer](x).head
        val fifo = RetimingFIFO().setParent(cu)
        val store = MemStore(fifo, None, x).setParent(xCU)
        val load = MemLoad(fifo, None).setParent(cu)
        pirmeta.mirror(x, store)
        pirmeta.mirror(x, load)
        mapping + (x -> load)
    }
  }

  def retimeX(x:Def, cu:GlobalContainer):Map[Any,Any] = retimeX(x, cu, Map[Any, Any]())

  def retime(x:Def, cu:GlobalContainer):Def = retimeX(x, cu)(x).asInstanceOf[Def]

  override def visitNode(n:N):Unit = {
    n match {
      case Def(n:LoadDef, LoadDef(mems, addrs)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val accessCU = collectUp[GlobalContainer](n).head
          mems.foreach { mem =>

            // Remote read address calculation
            val memCU = collectUp[GlobalContainer](mem).head
            val maddrs = addrs.map { addrs => 
              val mp = addrs.foldLeft(Map[Any,Any]()) { case (mp, addr) => 
                mirrorX(addr, memCU, mp)
              }
              addrs.map { addr => mp(addr).asInstanceOf[Def] }
            }
            val access = MemLoad(mem, maddrs).setParent(memCU)
            pirmeta.mirror(n, access)
            val newOut = if (memCU == accessCU) {
              access.out
            } else { // Remote memory, add Retiming FIFO
              retime(access, accessCU).out
            }
            n.depeds.foreach { deped =>
              dbg(s"$n.deped=$deped")
              swapConnection(deped, n.out, newOut)
            }
          }
        }
      case Def(n:StoreDef, StoreDef(mems, None, data)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val accessCU = collectUp[GlobalContainer](n).head
          mems.foreach { mem =>
            disconnect(mem, n)
            val access = MemStore(mem, None, data).setParent(accessCU)
            pirmeta.mirror(n, access)
          }
        }
      case Def(n:StoreDef, StoreDef(mems, addrs, data)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val accessCU = collectUp[GlobalContainer](n).head
          mems.foreach { mem =>
            // Local write address calculation
            val memCU = collectUp[GlobalContainer](mem).head
            val (saddrs, sdata) = if (memCU!=accessCU && addrs.nonEmpty) {
              var mp = retimeX(data, memCU)
              val dataLoad = mp(data).asInstanceOf[Def]
              val addrLoad = addrs.map { addrs =>
                mp = addrs.foldLeft(mp) { case (mp, addr) => retimeX(addr, memCU, mp) }
                addrs.map { addr => mp(addr).asInstanceOf[Def] }
              }
              (addrLoad, dataLoad)
            } else {
              (addrs, data)
            }
            disconnect(mem, n)
            val access = MemStore(mem, saddrs, sdata).setParent(memCU)
            pirmeta.mirror(n, access)
          }
        }
      case n =>
    }
    super.visitNode(n)
  }
}

