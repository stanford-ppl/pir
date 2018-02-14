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
      case x:Const[_] => mirrorM(x, Some(cu), mapping)
      case Def(x:CounterIter, CounterIter(counter, offset)) => mirrorM(x, Some(cu), mapping)
      case x =>
        val xCU = collectUp[GlobalContainer](x).head
        val fifo = RetimingFIFO().setParent(cu)
        val stores = x.localDeps.collect { 
          case Def(a:WriteMems, WriteMems(mems, _)) if mems.forall(_.isInstanceOf[RetimingFIFO]) => a
        }
        val store = stores.headOption.getOrElse(WriteMems(List(fifo), x).setParent(xCU))
        val load = ReadMem(fifo).setParent(cu)
        pirmeta.mirror(x, store)
        pirmeta.mirror(x, load)
        mapping + (x -> load)
    }
  }

  def retimeX(x:Def, cu:GlobalContainer):Map[Any,Any] = retimeX(x, cu, Map[Any, Any]())

  def retime(x:Def, cu:GlobalContainer):Def = retimeX(x, cu)(x).asInstanceOf[Def]

  override def visitNode(n:N):Unit = {
    n match {
      case Def(n:LoadBank, _) =>
      case Def(n:LocalLoad, LocalLoad(banks, Some(addrs))) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val accessCU = collectUp[GlobalContainer](n).head
          val bankCUs = banks.map { bank => bank -> collectUp[GlobalContainer](bank).head }.toMap
          val addrCUs = if (banks.size>1 || false /*TODO: if stages are only counters*/) {
            val addrCU = CUContainer().setParent(design.newTop).ctrl(ctrlOf(n))
            banks.map { _ -> addrCU }.toMap
          } else {
            bankCUs
          }
          val mps = addrCUs.values.toSet[GlobalContainer].map { addrCU =>
            addrCU -> addrs.foldLeft(Map[Any,Any]()) { case (mp, addr) => 
              mirrorM(addr, Some(addrCU), mp)
            }
          }.toMap
          val bankAccesses = banks.map { bank =>
            // Remote read address calculation
            val maddrs = addrs.map { addr => 
              mps(addrCUs(bank))(addr).asInstanceOf[Def]
            }
            val bankCU = bankCUs(bank)
            val access = LoadBank(bank, maddrs).setParent(bankCU)
            dbg(s"add ${qtype(access)} in ${qtype(bankCU)}")
            pirmeta.mirror(n, access)
            if (bankCU == accessCU) {
              access
            } else { // Remote memory, add Retiming FIFO
              retime(access, accessCU).asInstanceOf[LocalLoad]
            }
          }
          val access = if (bankAccesses.size > 1) {
            //
            val sb = SelectBanks(bankAccesses).setParent(accessCU)
            dbg(s"add ${qtype(sb)} in ${qtype(accessCU)}")
            pirmeta.mirror(n, sb)
            sb
          } else bankAccesses.head
          n.depeds.foreach { deped =>
            swapConnection(deped, n.out, access.out)
          }
        }
      case Def(n:StoreBank, _) =>
      case Def(n:LocalStore, LocalStore(banks, Some(addrs), data)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val accessCU = collectUp[GlobalContainer](n).head
          banks.foreach { bank =>
            // Local write address calculation
            val bankCU = collectUp[GlobalContainer](bank).head
            val (saddrs, sdata) = if (bankCU!=accessCU) {
              var mp = retimeX(data, bankCU)
              val dataLoad = mp(data).asInstanceOf[Def]
              mp = addrs.foldLeft(mp) { case (mp, addr) => retimeX(addr, bankCU, mp) }
              val addrLoad = addrs.map { addr => mp(addr).asInstanceOf[Def] }
              (addrLoad, dataLoad)
            } else {
              (addrs, data)
            }
            dbg(s"disconnect ${qtype(n)} from ${qtype(bank)}")
            disconnect(n, bank)
            val access = StoreBank(bank, saddrs, sdata).setParent(bankCU)
            dbg(s"add ${qtype(access)} in ${qtype(bankCU)}")
            pirmeta.mirror(n, access)
          }
        }
      case n =>
    }
    super.visitNode(n)
  }

  override def check(runner:RunPass) = {
    val cus = collectDown[GlobalContainer](design.newTop)
    cus.foreach { cu =>
      checkCUIO(cu, runner)
    }
  }

  // All cu's inputs and outputs should go through a memory
  def checkCUIO(cu:GlobalContainer, runner:RunPass) = {
    import runner._
    cu.ins.foreach { in =>
      in.src match {
        case node:LocalLoad =>
        case node:Memory =>
        case node =>
          withLog(append=true) {
            dbg(s"$cu's global input $in.src = $node")
            in.connected.foreach { out =>
              dbg(s"out=$out out.src=${out.src}")
            }
          }
          throw PIRException(s"$cu's global output $in.src = $node")
      }
    }
    cu.outs.foreach { out =>
      out.src match {
        case node:LocalStore =>
        case node:Memory =>
        case node =>
          withLog(append=true) {
            dbg(s"$cu's global output $out.src = $node")
            out.connected.foreach { in =>
              dbg(s"in=$in in.src=${in.src}")
            }
          }
          throw PIRException(s"$cu's global output $out.src = $node")
      }
    }
  }

}

