package pir.pass

import pir._
import pir.node._

import pirc._
import pirc.util._

import prism.traversal._

import scala.collection.mutable
import scala.reflect._

class AccessLowering(implicit design:PIR) extends PIRTransformer {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    val accesses = collectDown[LocalAccess](design.top)
    accesses.foreach(lowerAccess)
  }

  def lowerAccess(n:N):Unit = {
    n match {
      case Def(n:LocalLoad, LocalLoad(banks, Some(addrs))) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val accessCU = globalOf(n).get 
          val bankCUs = banks.map { bank => bank -> globalOf(bank).get }.toMap
          val addrCUs = if (banks.size>1 || false /*TODO: if stages are only counters*/) {
            val addrCU = CUContainer().setParent(design.top).ctrl(ctrlOf(n))
            banks.map { _ -> addrCU }.toMap
          } else {
            bankCUs
          }
          val bankAccesses = banks.map { bank =>
            // Remote read address calculation
            val maddrs = addrs.map { addr => 
              mirror(addr, Some(addrCUs(bank)))
            }
            val bankCU = bankCUs(bank)
            val access = LoadMem(bank, maddrs).setParent(bankCU)
            dbg(s"add ${qtype(access)} in ${qtype(bankCU)}")
            pirmeta.mirror(n, access)
            retime(access, accessCU).asInstanceOf[LocalLoad]
          }
          val access = if (bankAccesses.size > 1) {
            val sb = SelectBanks(bankAccesses).setParent(accessCU)
            dbg(s"add ${qtype(sb)} in ${qtype(accessCU)}")
            sb
          } else bankAccesses.head
          swapNode(n,access)
        }
      case Def(n:LocalStore, LocalStore(banks, Some(addrs), data)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          banks.foreach { bank =>
            // Local write address calculation
            val bankCU = globalOf(bank).get 
            val (raddrs, rdata) = {
              val dataLoad = retime(data, bankCU)
              val addrLoad = addrs.map { addr => retime(addr, bankCU) }
              (addrLoad, dataLoad)
            }
            dbg(s"disconnect ${qtype(n)} from ${qtype(bank)}")
            val access = StoreMem(bank, raddrs, rdata).setParent(bankCU)
            dbg(s"add ${qtype(access)} in ${qtype(bankCU)}")
            swapNode(n,access)
          }
        }
      case Def(n:LocalLoad, LocalLoad(mem::Nil, None)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val memCU = globalOf(mem).get
          val accessCU = globalOf(n).get 
          swapParent(n, memCU)
          val depeds = n.depeds.toList
          val raccess = retime(n, accessCU)
          swapNode(n,raccess,at=Some(depeds))
        }
      case Def(n:LocalStore, LocalStore(mem::Nil, None, data)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val memCU = globalOf(mem).get
          swapParent(n, memCU)
        }
      case n =>
    }
  }

}

