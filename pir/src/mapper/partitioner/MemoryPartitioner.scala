package pir
package mapper

import pir.node._
import prism.graph._
import spade.param._
import prism.collection.immutable._
import scala.collection.mutable

trait MemoryPartitioner extends Partitioner with CUCostUtil {

  override def recover(x:EOption[CUMap]):EOption[CUMap] = {
    x match {
      case Left(f@InvalidFactorGraph(fg:CUMap, k:CUMap.K)) =>
        val kcost = k.getCost[SRAMCost]
        val vs = fg.freeValuesOf(k)
        val vcost = assertOne(vs.map { _.getCost[SRAMCost] }, s"sramCost")
        dbg(s"kcost: $kcost")
        dbg(s"vcost: $vcost")
        val mks = split(fg, k, kcost, vcost).toSet
        Right(fg.mapFreeMap { _ - k ++ (mks, vs) })
      case x => super.recover(x)
    }
  }

  def split(x:CUMap, k:CUMap.K, kcost:SRAMCost, vcost:SRAMCost):List[GlobalContainer] = dbgblk(s"split($k)"){
    val mem = k.collectDown[Memory]().head
    val bankMult = kcost.bank /! vcost.bank
    val newSize = mem.getDepth * mem.size * vcost.bank /! mem.totalBanks 
    val capMult = newSize /! vcost.size
    val totalBanks = kcost.bank * capMult
    val numCU = bankMult * capMult
    dbg(s"bankMult=$bankMult capMult=$capMult totalBanks=$totalBanks")
    info(s"Split $k into ${numCU} CUs")

    val nodes = k::k.descendents
    val mappings = List.fill(numCU) { within(pirTop) { mirrorAll(nodes) } }
    val mks = mappings.map { _(k).as[GlobalContainer] }

    // Update metadata of new partitions
    val bankids = (0 until totalBanks).toList.grouped(vcost.bank).toList
    (mappings, bankids).zipped.foreach { case (mapping, bankids) =>
      val mmem = mapping(mem).as[Memory]
      if (capMult != 1) {
        mmem.banks.reset
        mmem.banks := mem.banks.get :+ capMult
      }
      mmem.bankids.reset
      mmem.bankids := bankids
    }

    // Update Shuffles within new partitions
    updateShuffle(k, mem, mappings)

    // TODO: update bank address calculation if capMult is not one
    
    // Merge bankReads of multiple partitions
    val bankReads = k.collectDown[BankedRead]()
    bankReads.foreach { bankRead =>
      //breakPoint(s"$k, $bankRead")
      mergeReads(k, mem, bankRead, mappings)
    }
    mks.foreach { nk =>
      insertGlobalOutput(nk)
    }
    var toremove = nodes
    toremove ++= k.accum(visitFunc = visitGlobalOut _)
    removeNodes(toremove)
    //breakPoint(s"$k, $mks")
    mks
  }

  def removeUnusedConst(global:CUMap.K) = {
    val consts = global.collectDown[Const]().filter { !_.out.isConnected }
    removeNodes(consts)
  }

  def updateShuffle(k:CUMap.K, mem:Memory, mappings:List[mutable.Map[N, N]]) = {
    val shuffles = k.collectDown[Shuffle]()
    mappings.foreach { mapping =>
      val mk = mapping(k).as[CUMap.K]
      val mmem = mapping(mem).as[Memory]
      shuffles.foreach { shuffle =>
        val mshuffle = mapping(shuffle).as[Shuffle]
        within(mshuffle.parent.get.as[PIRNode], mshuffle.ctrl.get) {
          mshuffle.to.disconnect
          mshuffle.to(allocConst(mmem.bankids.get).out)
        }
      }
      removeUnusedConst(mk)
    }
  }

  def mergeReads(k:CUMap.K, mem:Memory, br:BankedRead, mappings:List[mutable.Map[N, N]]) = dbgblk(s"mergeReads($k, $br)") {
    val shuffles = br.collect[Shuffle](visitGlobalOut _)
    shuffles.foreach { shuffle =>
      val ctx = shuffle.ctx.get
      dbgblk(s"merge $shuffle in $ctx") {
        within(ctx, shuffle.ctrl.get) {
          val mshuffles = mappings.map { mapping =>
            val mmem = mapping(mem).as[Memory]
            val mbr = mapping(br).as[BankedRead]
            val mshuffle = mirrorAll(List(shuffle))(shuffle).as[Shuffle]
            // Swap shuffle.base connection from new cu
            mshuffle.base.disconnect
            mshuffle.base(mbr.out)
            bufferInput(mshuffle.base)
            // Swap connection shuffle.to, which is bank address. If bank address is passed from k, 
            // change to bank address calculated in corresponding new partitions
            mshuffle.to.T.collect[BufferWrite](visitFunc=visitGlobalIn _, logger=Some(this)).headOption.foreach { bout =>
              val bankAddr = bout.data.T
              mapping.get(bankAddr).foreach { mbankAddr =>
                dbg(s"bankAddr=$bankAddr, mbankAddr=$mbankAddr")
                mshuffle.to.disconnect
                mshuffle.to(mbankAddr)
                bufferInput(mshuffle)
              }
            }
            // Swap shuffle.from based on new statically assigned bankids.
            mshuffle.from.disconnect
            mshuffle.from(allocConst(mmem.bankids.get))
            mshuffle
          }
          var red:List[Output] = mshuffles.map { _.out }
          while(red.size > 1) {
            red = red.sliding(2,2).map{ 
              case List(o1, o2) =>
                val op = OpDef(FixOr).input(o1, o2)
                dbg(s"add val $op = FixOr.input(${o1.src}.$o1, ${o2.src}.$o2)")
                op.out
              case List(o1) => o1
            }.toList
          }
          val List(out) = red.toList
          shuffle.out.connected.distinct.foreach { in =>
            swapConnection(in.as[Input], shuffle.out, out)
          }
        }
      }
    }
    shuffles.map { _.global.get }.distinct.foreach { global =>
      insertGlobalInput(global)
      removeUnusedConst(global)
    }
  }

  // Split one pmu into multiple pmu that fits total number of banks
  //def mergeBanks(global:GlobalContainer, br:BankedRead, reads:List[BufferRead], mrs:List[(Output, Output)]):Seq[GlobalContainer] = dbgblk(s"mergeBanks($global)") {
    //global match {
      //case fringe:DRAMFringe =>
        //within(pirTop) {
          //val global = ComputeContainer()
          //val read = within(global) {
            //val ctx = Context()
            //dbg(s"Create $global and $ctx to merge banks for $fringe")
            //val ctrl = br.ctrl.get
            //val owrite = reads.head.inAccess 
            //val done = owrite.done.T
            //val deps = getDeps(owrite, { case `owrite` => List(owrite.done.T); case n => visitGlobalIn(n) })
            //val mdone = within(ctx) { mirrorAll(deps)(done) }
            //val read = within(ctx, ctrl) {
              //val read = BufferRead(reads.head.isFIFO).in(br.out).banks(reads.head.banks.get).done(mdone)
              //val write = BufferWrite().data(read.out).done(mdone)
              //reads.foreach { read =>
                //read.in.disconnect
                //read.in(write)
              //}
              //read
            //}
            //read
          //}
          //insertGlobalInput(fringe)
          //insertGlobalOutput(global)
          //global +: mergeBanks(global, br, List(read), mrs)
        //}
      //case global =>
        //reads.groupBy { _.ctx.get }.foreach { case (ctx, reads) =>
          //reads.foreach { read =>
            //within(ctx, read.ctrl.get) {
              //var red:List[(Output,Output)] = mrs
              //while(red.size > 1) {
                //red = red.sliding(2,2).map{ 
                  //case List((m1, o1),(m2, o2)) =>
                    //val op = OpDef(Mux).input(m1, o1, o2)
                    //dbg(s"add $op.input(${m1.src}.$m1, ${o1.src}.$o1, ${o2.src}.$o2)")
                    //(m2, op.out)
                  //case List((m1, o1)) => (m1, o1)
                //}.toList
              //}
              //val List((mask, out)) = red.toList
              //read.localDepeds.foreach { deped =>
                //swapInput(deped, read.out, out)
              //}
              //removeNode(read)
            //}
          //}
          //bufferInput(ctx)
        //}
        //insertGlobalInput(global)
        //Nil
    //}
  //}

}
