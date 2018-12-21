package pir
package pass

import pir.node._
import prism.graph._
import scala.collection.mutable

trait RuntimeAnalyzer { self:PIRPass =>

  implicit class CtxUtil(ctx:Context) {
    def reads:Seq[LocalOutAccess] = ctx.collectDown[LocalOutAccess]().filterNot { _.isLocal }
    def writes:Seq[LocalInAccess] = ctx.collectDown[LocalInAccess]().filterNot { _.isLocal }
    def ctrs:Seq[Counter] = ctx.collectDown[Counter]()
    def ctrler(ctrl:ControlTree) = {
      assertOne(
        ctx.collectDown[Controller]().filter { _.ctrl.get == ctrl }, 
        s"$ctx.ctrler with ($ctrl)"
      )
    }
    def dramCommands:Option[DRAMCommand] = assertOneOrLess(ctx.children.collect{ case fringe:DRAMCommand => fringe }, s"fringe in $ctx")
    def activeRate(n:Float) = ctx.getMeta[Float]("activeRate").update(n)
    def activeRate = ctx.getMeta[Float]("activeRate").v
    def stallRate(n:Float) = ctx.getMeta[Float]("stallRate").update(n)
    def stallRate = ctx.getMeta[Float]("stallRate").v
    def starveRate(n:Float) = ctx.getMeta[Float]("starveRate").update(n)
    def starveRate = ctx.getMeta[Float]("starveRate").v
    def scheduleFactor = ctx.getMeta[Int]("scheduleFactor").v
    def getScheduleFactor = ctx.getMeta[Int]("scheduleFactor").getOrElseUpdate(compScheduleFactor(ctx))
  }

  implicit class PIRNodeRuntimeOp(n:PIRNode) {
    def getCtrl:ControlTree = n.ctrl.get
    def getBound:Option[Int] = n.getMeta[Option[Int]]("bound").getOrElseUpdate(constProp(n).as[Option[Int]])
    def getScale:Option[Long] = n.scale.getOrElseUpdate(compScale(n))
    def getIter:Option[Long] = n.iter.getOrElseUpdate(compIter(n))
    def getCount:Option[Long] = n.count.getOrElseUpdate(compCount(n))
  }
  implicit class NodeRuntimeOp(n:N) {
    def getVec:Int = n.getMeta[Int]("vec").getOrElseUpdate(compVec(n))
  }

  //TODO
  def constProp(n:PIRNode):Option[Any] = dbgblk(s"constProp($n)"){
    n match {
      case Const(v) => Some(v)
      case n:BufferRead => n.in.T.getBound
      case n:BufferWrite => n.data.T.getBound
      case n:GlobalInput => n.in.T.getBound
      case n:GlobalOutput => n.in.T.getBound
      case n => None
    }
  }

  def compIter(n:PIRNode):Option[Long] = dbgblk(s"compIter($n)"){
    n match {
      case n:Counter if !n.isForever => 
        val min = n.min.T.get.getBound
        val max = n.max.T.get.getBound
        val step = n.step.T.get.getBound
        zipMap(min, max, step) { case (min, max, step) =>
          val par = n.par
          (max - min) /! (step * par)
        }
      case n:LoopController =>
        flatReduce(n.cchain.T.map { _.getIter }) { _ * _ }
      case n:Controller => Some(1l)
      case n:FringeDenseLoad =>
        val size = n.size.T.getBound
        val dataPar = n.data.T.getVec //TODO:
        size.map { size => size /! (spadeParam.bytePerWord * dataPar) }
      case n:FringeDenseStore =>
        val size = n.size.T.getBound
        val dataPar = n.data.T.getVec //TODO
        size.map { size => size /! (spadeParam.bytePerWord * dataPar) }
      case n:FringeSparseLoad => Some(1l)
      case n:FringeSparseStore => Some(1l)
      case n => None
    }
  }

  def compScale(n:PIRNode):Option[Long] = dbgblk(s"compScale($n)"){
    n match {
      case n:LocalOutAccess =>
        n.out.T match {
          case List(dram:FringeDenseLoad) => dram.getIter.map { _ * n.ctx.get.getScheduleFactor }
          case List(dram:FringeDenseStore) if n.out.isConnectedTo(dram.data) | n.out.isConnectedTo(dram.valid) => Some(n.ctx.get.getScheduleFactor)
          case List(dram:FringeDenseStore) if n.out.isConnectedTo(dram.size) | n.out.isConnectedTo(dram.offset) =>
            dram.getIter.map { _ * n.ctx.get.getScheduleFactor }
          case List(dram:FringeSparseLoad) => Some(n.ctx.get.getScheduleFactor)
          case List(dram:FringeSparseStore) => Some(n.ctx.get.getScheduleFactor)
          case _ => n.done.T.getScale
        }
      case n:BufferWrite =>
        n.data.T match {
          case data:FringeDenseLoad => Some(n.ctx.get.getScheduleFactor)
          case data:FringeDenseStore => data.getIter.map { _ * n.ctx.get.getScheduleFactor } // ack
          case data:FringeSparseLoad => Some(n.ctx.get.getScheduleFactor)
          case data:FringeSparseStore => Some(n.ctx.get.getScheduleFactor)
          case data =>  n.done.T.getScale
        }
      case n:TokenWrite => n.done.T.getScale
      case n:ControllerDone =>
        val ctrler = n.ctrler
        zipMap(ctrler.getIter, ctrler.valid.T.getScale) { _*_ }
      case n:ControllerValid =>
        val ctrler = n.ctrler
        val children = ctrler.valid.T.out.connected.filter { _.asInstanceOf[Field[_]].name == "parentEn" }.map { _.src.as[Controller] }
        assertUnify(children, s"$n.valid.scale") { child =>
          zipMap(child.valid.T.getScale, child.getIter) { _ * _ }
        }.getOrElse(Some(n.ctx.get.getScheduleFactor))
      case _:High => Some(n.ctx.get.getScheduleFactor)
      case n => throw PIRException(s"Don't know how to compute scale of $n")
    }
  }

  def compScheduleFactor(n:Context):Int = dbgblk(s"compScheduleFactor($n)"){
    if (spadeParam.scheduled) {
      Math.max(n.collectDown[OpNode]().size,1)
    } else {
      1
    }
  }

  def accountForCycle = true

  def compCount(n:PIRNode):Option[Long] = dbgblk(s"compCount($n)"){
    n match {
      case n:Context if n.collectDown[HostInController]().nonEmpty => Some(1l)
      case n:Context =>
        var (readsWithInits, reads) = n.reads.partition { _.initToken.get }
        if (accountForCycle) {
          reads = reads ++ readsWithInits
          if (reads.isEmpty) err(s"$n has no read inputs!")
        }
        assertOptionUnify(reads, s"$n.reads=$reads, read.count * read.scale") { read => 
          zipMap(read.getCount, read.getScale) { _ * _ }
        }
      case n:LocalOutAccess =>
        n.in.T.getCount
      case n:LocalInAccess =>
        zipMap(n.ctx.get.getCount, n.getScale) { _ /! _ }
      case n:GlobalInput =>
        n.in.T.getCount
      case n:GlobalOutput =>
        n.in.T.getCount
      case n => throw PIRException(s"Don't know how to compute count of $n")
    }
  }

  def compVec(n:N):Int = dbgblk(s"compVec($n)"){
    n match {
      case n:Const => 1
      case n:CounterIter if n.i.nonEmpty => 1
      case n:CounterValid if n.i.nonEmpty => 1
      case n:RegAccumOp => 1
      case n:ControllerValid => 1
      case n:ControllerDone => 1
      case n:GlobalOutput => n.in.T.getVec
      case n:GlobalInput => assertUnify(n.out.T, s"$n.out.T") { _.getVec }.get
      case n:BufferWrite if n.getCtrl.schedule=="Streaming" =>
        assertUnify(n.outAccesses, s"$n.outAccesses.bank") { _.banks.get.head }.get
      case n:MemWrite if n.getCtrl.schedule=="Streaming" =>
        n.mem.banks.get.head
      case n:BufferRead if n.getCtrl.schedule=="Streaming" =>
        n.banks.get.head
      case n:MemRead if n.getCtrl.schedule=="Streaming" =>
        n.mem.banks.get.head
      case n:PIRNode => n.getCtrl.getVec
      case n:ControlTree =>
        if (n.children.isEmpty) n.par.get else 1
    }
  }

}

