package pir
package pass

import pir.node._

class BankedAccessMerging(implicit compiler:PIR) extends PIRTransformer with PIRNodeUtil with prism.traversal.GraphUtil {
  import pirmeta._

  override def runPass =  {
    val mems = compiler.top.collectDown[Memory]()
    mems.foreach { mem =>
      dbgblk(s"analyze($mem)") {
        groupAccesses(writersOf(mem)).foreach( a => mergeWrites(mem, a))
        groupAccesses(readersOf(mem)).foreach(a => mergeReads(mem, a))
      }
    }
  }

  def groupAccesses[A<:LocalAccess](accesses:List[A]):List[List[A]] = {
    val accessGroups = groupByForkJoin(accesses)
    accessGroups.filter { _.size > 1}.map(_.toList)
  }

  def mergeWrites(mem:Memory, writes:List[LocalStore]) = dbgblk(s"mergeWrites($mem)"){
    // Assumption writers under ForkJoin will run the same number of iterations!!!
    val ctrl = ctrlOf(writes.head)
    withParentCtrl(globalOf(mem).get, ctrl) {
      val addrs = writes.map { case StoreMem(mem, addr, data) => addr }
      val datas = writes.map { case StoreMem(mem, addr, data) => data }
      val addr = addrs.reduce[Def]{ case (a1, a2) => SelectByValid(a1, a2) }
      val data = datas.reduce[Def]{ case (d1, d2) => SelectByValid(d1, d2) }
      val newStore = StoreMem(mem, addr, data)
      dbg(s"merge $writes to $newStore")
      pirmeta.migrate(writes.head, newStore)
      writes.foreach(removeNode)
    }
  }

  def mergeReads(mem:Memory, reads:List[LocalLoad]) = dbgblk(s"mergeReads($mem)"){
    // Assumption reads under ForkJoin will run the same number of iterations!!!
    val lca = reads.map { read => ctrlOf(read) }
    val ctrl = ctrlOf(reads.head)
    withParentCtrl(globalOf(mem).get, ctrl) {
      val addrs = reads.map { case LoadMem(mem, addr) => addr }
      //reads.foreach { case read@LoadMem(mem, addr) =>
        //val exps = addr.accumInTillMem()
        //dbg(s"$addr exps=$exps")
        //exps.foreach { exp =>
          //if (ctrlOf(exp) != k)
          //if (ctrlOf(exp) == ctrlOf(read)) {
            //ctrlOf.removeKey(exp)
            //ctrlOf(exp) = ctrl
            //dbg(s"reset ctrlOf($exp) = $ctrl")
          //}
        //}
      //}
      val addr = addrs.reduce[Def]{ case (addr1, addr2) => SelectByValid(addr1,addr2) }
      val newLoad = LoadMem(mem, addr)
      dbg(s"merge $reads to $newLoad")
      pirmeta.migrate(reads.head, newLoad)
      reads.foreach { read =>
        swapUsage(read, newLoad) // TODO: Add saperate valid outputs for each reader. Valid decided by valids of addrs
      }
      reads.foreach(removeNode)
    }
  }

}

