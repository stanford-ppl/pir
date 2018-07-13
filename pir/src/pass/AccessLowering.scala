package pir
package pass

import pir.node._

class AccessLowering(implicit compiler:PIR) extends PIRTransformer {
  import pirmeta._

  override def runPass =  {
    val accesses = compiler.top.collectDown[LocalAccess]()
    accesses.foreach(lowerAccess)
  }

  def lowerAccess(n:N):Unit = {
    n match {
      case Def(n:LocalLoad, LocalLoad(banks, Some(addrs))) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val accessCU = globalOf(n).get 
          val bankCUs = banks.map { bank => bank -> globalOf(bank).get }.toMap
          val addrCUs = if (banks.size>1 || false /*TODO: if stages are only counters*/) {
            val addrCU = CUContainer().setParent(compiler.top).ctrl(ctrlOf(n))
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
            val dims = staticDimsOf(banks.head)
            val faddr = flattenNDAddr(bankCU, ctrlOf(n))(maddrs, dims)
            val access = LoadMem(bank, faddr).setParent(bankCU)
            dbg(s"add ${qtype(access)} in ${qtype(bankCU)}")
            pirmeta.migrate(n, access)
            retime(access, accessCU).asInstanceOf[LocalLoad]
          }
          swapUsage(n, {
            if (bankAccesses.size > 1) { 
              val bm = BankMerge(bankAccesses).setParent(accessCU).ctrl(ctrlOf(n))
              dbg(s"add ${qtype(bm)} in ${qtype(accessCU)}")
              bm
            } else bankAccesses.head
          })
          removeNode(n)
        }
      case Def(n:StoreBanks, StoreBanks(_, addrs, data)) => // using Def here cause List of List not working properly
        val insts = n.mems
        dbgblk(s"Lowering ${qdef(n)}") {
          val storeCU = globalOf(n).get
          dbg(s"insts=$insts")
          val singleBank = insts.forall { _.size == 1 }
          val dims = staticDimsOf(insts.head.head)
          if (singleBank) { // Do address flattening inside the PMU
            insts.foreach { banks =>
              val bank = assertOne(banks, "bank")
              // Local write address calculation
              val bankCU = globalOf(bank).get 
              val dataLoad = retime(data, bankCU)
              val inds = addrs.map { ind => retime(ind, bankCU) }
              val faddr = flattenNDAddr(bankCU, ctrlOf(n))(inds, dims)
              val access = StoreMem(bank, faddr, dataLoad).setParent(bankCU)
              dbg(s"add ${qtype(access)} in ${qtype(bankCU)}")
              pirmeta.migrate(n, access)
              access
            }
          } else { // Do address flattening inside the source CU
            dbg(s"${qdef(n)} stores multiple banks")
            val faddr = flattenNDAddr(storeCU, ctrlOf(n))(addrs, dims)
            val bs = BankSelect(addrs).setParent(storeCU).ctrl(ctrlOf(n))
            val maddr = BankMask(bs, faddr).setParent(storeCU).ctrl(ctrlOf(n))
            val mdata = BankMask(bs, data).setParent(storeCU).ctrl(ctrlOf(n))
            insts.foreach { banks =>
              banks.map { bank =>
                // Local write address calculation
                val bankCU = globalOf(bank).get 
                val dataLoad = retime(mdata, bankCU)
                val addrLoad = retime(maddr, bankCU)
                val access = StoreMem(bank, addrLoad, dataLoad).setParent(bankCU)
                dbg(s"add ${qtype(access)} in ${qtype(bankCU)}")
                pirmeta.migrate(n, access)
              }
            }
          }
          removeNode(n)
        }
      case Def(n:LocalLoad, LocalLoad(mem::Nil, None)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val memCU = globalOf(mem).get
          val accessCU = globalOf(n).get 
          swapParent(n, memCU)
          lowerNode(n)(retime(n, accessCU))
        }
      case Def(n:LocalStore, LocalStore(mem::Nil, None, data)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val memCU = globalOf(mem).get
          swapParent(n, memCU)
        }
      case Def(n:LocalReset, LocalReset(mem::Nil, reset)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val memCU = globalOf(mem).get
          swapParent(n, memCU)
        }
      case n =>
    }
  }
  
  def flattenNDAddr(cu:GlobalContainer, ctrl:Controller)(inds:List[Def], dims:List[Int]):Def = { 
    import prism.enums._
    val i::irest = inds
    val d::drest = dims
    if (inds.size==1 && dims.size==1) {
      i
    } else { 
      //i * drest.product + flattenND(irest, drest)
      val flatten = flattenNDAddr(cu, ctrl)(irest, drest)
      val dim = Const(drest.product).setParent(cu).ctrl(ctrl)
      val prod = OpDef(FixMul, inputs=List(i, dim)).setParent(cu).ctrl(ctrl)
      OpDef(FixAdd, inputs=List(prod, flatten)).setParent(cu).ctrl(ctrl)
    }
  }

}

