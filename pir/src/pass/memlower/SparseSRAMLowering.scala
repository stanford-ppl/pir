package pir 
package pass

import pir.node._
import pir.mapper._
import prism.graph._
import spade.param._
import scala.collection.mutable

trait SparseSRAMLowering extends SparseLowering {

  override def visitNode(n:N) = n match {
    case n@SparseMem(_, "SRAM",_) => lowerSparseSRAM(n)
    case _ => super.visitNode(n)
  }

  private def dependsOn(deped:Access, dep:Access):Option[Int] = {
    val lca = leastCommonAncesstor(deped.getCtrl, dep.getCtrl).get
    dbg(s"\t depedCtrl: ${dquote(deped.getCtrl)} depCtrl: ${dquote(dep.getCtrl)} lca: ${dquote(lca)}")
    lca.schedule match {
      case Fork => return None
      case ForkJoin => return None
      case _ => return Some(1)
    }
    Some(1) 
  }
  
  // private val addrCtxs = mutable.Map[Either[ControlTree, Access], Context]()
  // private val mergeCtxs = mutable.Map[Access, Context]()
  private def lowerSparseSRAM(n:SparseMem):Unit = dbgblk(s"lower($n)"){
    val memCU = within(pirTop,n.srcCtx.get) { stage(MemoryContainer()) }
    val init_ctrl = n.ctrlTree
    swapParent(n, memCU)
    dbg(s"accesses=${n.accesses}")
    n.accesses.foreach { access =>
      accessReqResp += lowerSparseAccess(access.as[SparseAccess], memCU)
    }
    if (n.autoBar.get) {
      dbg(s"Perform automatic barrier insertion for $n")
      consistencyBarrier(n.accesses)(dependsOn){ case (from,to,carried,depth) =>
        val stallTo = to.asInstanceOf[SparseAccess].barriers.get.map { bar =>
          bar match {
            case (barrier,true) => true // false
            case (barrier,false) => true
          }
        }.reduceOption({_|_}).getOrElse(false)
        if (stallTo) {
          dbg(s"Skip sparse barrier ${from}->${to}; user has already inserted a barrier holding back this memory")
        } else {
          dbg(s"Insert sparse barrier ${from}->${to}")
          val bar_init = if (carried) 1 else 0
          val ctrl = leastCommonAncesstor(to.getCtrl, from.getCtrl).get 
          val b = Barrier(ctrl, bar_init).name(s"__auto_bar_${from}_${to}")
          barrierWrite.getOrElseUpdate(b, mutable.ListBuffer()) += from
          barrierRead.getOrElseUpdate(b, mutable.ListBuffer()) += to
        }
      }
    } else {
      dbg(s"Skip automatic barrier insertion for $n")
    }
  }

  /* private def insertAck(ack:Output[PIRNode], ctrl:ControlTree):AccumAck = {
    // TODO: copy ctrl tree into a follower tree
    // val accumAck = within(ackCtx, ctrl) {
    val accumAck = within(ctrl) {
      // val followCtrler = stage(createCtrl(Sequenced) { FollowController().followToken(ack) })
      // val tree = stage(ControlTree(Sequenced))
      val tree = ControlTree(Sequenced)
      dbg(s"Tree: $tree tree parent: ${tree.parent.get}")
      // tree.parent(ctrl.v)
      // beginState(tree)
      within (tree) {
        val ackCtx = stage(Context().name("ackCtx")) // .followToken(ack)
        dbg(s"Stage ack context: $ackCtx")
        val ctrler = stage(FollowController()).followToken(ack)
        tree.ctrler(ctrler)
        tree.parent.foreach { parent =>
          parent.ctrler.v.foreach { pctrler =>
            ctrler.parentEn(pctrler.childDone)
          }
        }
        val a = stage(AccumAck().ack(ack))
        dbg(s"\tAccumAck: $a .ack: $a.ack}")
        bufferInput(a.ack).foreach { _.name := "ack" }
        ackCtx.streaming(false)
        ackCtx.collectDown[Controller]().sortBy { _.getCtrl.ancestors.size }.map { ctrler =>
          dbg(s"\tController: $ctrler")
        }
        a
      }
    }
    // accumAck.controlShadowed(true)
    // accumAck.bbCtrl(ctrl)
    accumAck
  }*/
  private def insertAck(ack:Output[PIRNode], ctrl:ControlTree):AccumAck = {
     // val tree = ControlTree(Sequenced)
     // tree.setParent(ctrl)
     // val accumAck = within (tree) {
     val accumAck = {
       val ackCtx = stage(Context().name("ackCtx").follow(true)) // .followToken(ack)
       dbg(s"Stage ack context: $ackCtx")
       ackCtx.collectDown[Controller]().sortBy { _.getCtrl.ancestors.size }.map { ctrler =>
         dbg(s"\tController: $ctrler")
       }
       // val followCtrler = stage(createCtrl(Sequenced) { FollowController().followToken(ack) })
       // dbg(s"Tree: $tree tree parent: ${tree.parent.get}")
       // beginState(tree)
       within (ackCtx) {
         // val ctrler = stage(UnitController()).par(ack.inferVec.getOrElse(1)).follow(true) //.followToken(ack)
         // tree.ctrler(ctrler)
         // tree.parent.foreach { parent =>
           // parent.ctrler.v.foreach { pctrler =>
             // ctrler.parentEn(pctrler.childDone)
           // }
         // }
         val a = stage(AccumAck().ack(ack))
         // ctrler.laneValid.disconnect
         a
       }
       // endState[Ctrl]
       // a
     }
     dbg(s"accumAck: $accumAck")
     bufferInput(accumAck.ack).foreach { _.name := "ack" }
     // accumAck.controlShadowed(true)
     // accumAck.bbCtrl(ctrl)
     // ackCtx.streaming(false)
     accumAck
   }

  private def lowerSparseAccess(access:SparseAccess, memCU:MemoryContainer) = dbgblk(s"lower($access)"){
    access.barriers.get.foreach { 
      case (barrier,true) => 
        dbg(s"Adding $barrier to write")
        barrierWrite.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
      case (barrier,false) => 
        dbg(s"Adding $barrier to read")
        barrierRead.getOrElseUpdate(barrier, mutable.ListBuffer()) += access
    }
    val ctrl = access.getCtrl
    access match {
      case access:SparseWrite =>
        within(memCU, ctrl,access.srcCtx.get) {
          dbg(s"ctrl: ${ctrl} data.T.getCtrl: ${access.data.T.getCtrl} addr.T.getCtrl: ${access.addr.T.getCtrl}")
          val addrCtx = stage(Context().name("addrCtx"))
          // flattenEnable(access) // in write ctx
          swapParent(access, addrCtx) // TESTING
          flattenEnable(access) // TESTING, in addrCtx
          // flattenEnable(access) // in write ctx
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          bufferInput(access.addr, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "addr" }
          bufferInput(access.data, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "data" }
          /* if (access.data.T.getCtrl != ctrl || access.addr.T.getCtrl != ctrl) {
            val addrCtx = stage(Context().name("addrCtx"))
            bufferInput(access.addr, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "addr" }
            bufferInput(access.data, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "data" }
          } else {
            bufferInput(access.addr).foreach { _.name := "addr" }
            bufferInput(access.data).foreach { _.name := "data" }
          }*/
          // bufferInput(accessCtx)
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          // TODO: refactor me
          /* val ackCtx = stage(Context().name("ackCtx"))
          val accumAck = within(ackCtx, ctrl) {
            stage(AccumAck().ack(access.ack))
          }
          bufferInput(accumAck.ack).foreach { _.name := "ack" } */
          val accumAck = insertAck(access.ack, ctrl) // TODO: put this in 
          // END refactor
          access -> (Some(req),Some(accumAck.out))
        }
      case access:SparseRead =>
        within(memCU, ctrl, access.srcCtx.get) {
          val addrCtx = stage(Context().name("addrCtx"))
          swapParent(access, addrCtx)
          flattenEnable(access) // in addrCtx
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          bufferInput(access.addr, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "addr" }
          val ins = access.out.connected
          ins.distinct.foreach { in =>
            bufferInput(in).foreach { read => read.inAccess.name := "readOut" }
          }
          val reads = ins.flatMap { in => in.neighbors.collect { case x:BufferRead => x } }
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          val resp = reads.headOption.getOrElse(err(s"${quoteSrcCtx(access)} is not used by anyone!")).out
          access -> (Some(req),Some(resp))
        }
      case access:SparseRMW =>
        within(memCU, ctrl, access.srcCtx.get) {
          flattenEnable(access) // in wirte ctx
          val accessCtx = stage(Context().streaming(true))
          swapParent(access, accessCtx)
          if (access.addr.T.getCtrl != ctrl || access.input.T.getCtrl != ctrl || access.remoteAddr) {
            val addrCtx = stage(Context().name("addrCtx"))
            bufferInput(access.addr, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "addr" }
            bufferInput(access.input, BufferParam(fromCtx=Some(addrCtx))).foreach { _.name := "input" }
          } else {
            bufferInput(access.addr).foreach { _.name := "addr" }
            bufferInput(access.input).foreach { _.name := "input" }
          }

          val ins = access.dataOut.connected
          ins.distinct.foreach { in =>
            bufferInput(in).foreach { read => read.inAccess.name := "readOut" }
          }
          val reads = ins.flatMap { in => in.neighbors.collect { case x:BufferRead => x } }
          val resp = reads.headOption.map { _.out }.getOrElse {
            val ackCtx = stage(Context().name("ackCtx"))
            // TODO: refactor me
            val accumAck = within(ackCtx, ctrl) {
              stage(AccumAck().ack(access.dataOut))
            }
            bufferInput(accumAck.ack)
            accumAck.out
          }
          val req = access.addr.singleConnected.get.src.as[BufferRead].inAccess.as[BufferWrite].data
          access -> (Some(req),Some(resp))
        }
    }
  }

}
