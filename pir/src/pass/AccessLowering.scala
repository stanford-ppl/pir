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
            val addrCU = withParent(compiler.top) { CUContainer() }
            banks.map { _ -> addrCU }.toMap
          } else {
            bankCUs
          }
          val bankAccesses = banks.map { bank =>
            // Remote read address calculation
            val maddrs = addrs.map { addr => 
              withParent(addrCUs(bank)) { mirror(addr) }
            }
            val bankCU = bankCUs(bank)
            val dims = staticDimsOf(banks.head)
            val access = withParentCtrl(bankCU, ctrlOf(n)) {
              val faddr = flattenNDAddr(maddrs, dims)
              val bs = BankSelect(maddrs) // compute which bank to load
              LoadMem(bank, BankMask(bs, faddr)) // enable of faddr is bs == bank id
            }
            pirmeta.migrate(n, access)
            retime(access, accessCU).asInstanceOf[LocalLoad]
          }
          swapUsage(n, {
            if (bankAccesses.size > 1) { 
              withParentCtrl(accessCU, ctrlOf(n)) { 
                bankAccesses.reduce[Def] { case (a1, a2) => SelectByValid(a1, a2) }
              }
            } else bankAccesses.head
          })
          removeNode(n)
        }
      case Def(n:StoreBanks, StoreBanks(_, addrs, data)) => //TODO: using Def here cause List of List not working properly
        val insts = n.mems
        dbgblk(s"Lowering ${qdef(n)}") {
          val storeCU = globalOf(n).get
          dbg(s"insts=$insts")
          val singleBank = insts.forall { _.size == 1 }
          val dims = staticDimsOf(insts.head.head)
          if (singleBank) { // Do address flattening inside the PMU
            val hackInsts = insts.map { banks => // TODO: hack to get the banks still connected
              banks.filter { bank => n.depeds.contains(bank) }
            }.filterNot { _.isEmpty }
            hackInsts.foreach { banks =>
              val bank = assertOne(banks, "bank")
              dbg(s"bank=$bank")
              // Local write address calculation
              val bankCU = globalOf(bank).get 
              val dataLoad = retime(data, bankCU)
              val inds = addrs.map { ind => retime(ind, bankCU) }
              withParentCtrl(bankCU, ctrlOf(n)) {
                val faddr = flattenNDAddr(inds, dims)
                val access = StoreMem(bank, faddr, dataLoad)
                pirmeta.migrate(n, access)
                access
              }
            }
          } else { // Do address flattening inside the source CU
            dbg(s"${qdef(n)} stores multiple banks")
            val (faddr, bs) = withParentCtrl(storeCU, ctrlOf(n)) {
              val faddr = flattenNDAddr(addrs, dims)
              val bs = BankSelect(addrs) // compute which bank to store
              (faddr, bs)
            }
            insts.foreach { banks =>
              banks.map { bank =>
                // Local write address calculation
                val (maddr, mdata) = withParentCtrl(storeCU, ctrlOf(n)) {
                  val maddr = BankMask(bs, faddr) // enable of faddr is bs == bank id
                  val mdata = BankMask(bs, data) // enable of data is bs == bank id
                  (maddr, mdata)
                }
                val bankCU = globalOf(bank).get 
                val dataLoad = retime(mdata, bankCU)
                val addrLoad = retime(maddr, bankCU)
                withParentCtrl(bankCU, ctrlOf(n)) {
                  val access = StoreMem(bank, addrLoad, dataLoad)
                  dbg(s"add ${qtype(access)} in ${qtype(bankCU)}")
                  pirmeta.migrate(n, access)
                }
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
  
  def flattenNDAddr(inds:List[Def], dims:List[Int]):Def = { 
    import prism.enums._
    val i::irest = inds
    val d::drest = dims
    if (inds.size==1 && dims.size==1) {
      i
    } else { 
      //i * drest.product + flattenND(irest, drest)
      val flatten = flattenNDAddr(irest, drest)
      val dim = Const(drest.product)
      val prod = OpDef(FixMul, inputs=List(i, dim))
      OpDef(FixAdd, inputs=List(prod, flatten))
    }
  }

}

