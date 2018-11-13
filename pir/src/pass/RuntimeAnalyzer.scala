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
  }

  implicit class PIRNodeRuntimeOp(n:PIRNode) {
    def getCtrl:ControlTree = n.ctrl.get
    def getVec:Int = n.getMeta[Int]("vec").getOrElseUpdate(compVec(n))
    def getBound:Option[Int] = n.getMeta[Option[Int]]("bound").getOrElseUpdate(constProp(n).as[Option[Int]])
    def getScale:Option[Long] = n.scale.getOrElseUpdate(compScale(n))
    def getIter:Option[Long] = n.iter.getOrElseUpdate(compIter(n))
    def getCount:Option[Long] = n.count.getOrElseUpdate(compCount(n))
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

  val bytePerWord = 4

  def compIter(n:PIRNode):Option[Long] = dbgblk(s"compIter($n)"){
    n match {
      case n:Counter => 
        val min = n.min.T.getBound
        val max = n.max.T.getBound
        val step = n.step.T.getBound
        zipMap(min, max, step) { case (min, max, step) =>
          val par = n.par
          (max - min) /! (step * par)
        }
      case n:LoopController =>
        flatReduce(n.cchain.T.map { _.getIter }) { _ * _ }
      case n:Controller => Some(1l)
      case n:FringeDenseLoad =>
        val size = n.size.T.getBound
        val dataPar = n.data.T.getVec
        size.map { size => size /! (bytePerWord * dataPar) }
      case n:FringeDenseStore =>
        val size = n.size.T.getBound
        val dataPar = n.data.T.getVec
        size.map { size => size /! (bytePerWord * dataPar) }
      case n:FringeSparseLoad => Some(1l)
      case n:FringeSparseStore => Some(1l)
      case n => None
    }
  }

  def compScale(n:PIRNode):Option[Long] = dbgblk(s"compScale($n)"){
    n match {
      case n:LocalOutAccess =>
        n.out.T match {
          case List(dram:FringeDenseLoad) => dram.getIter
          case List(dram:FringeDenseStore) if n.out.isConnectedTo(dram.data) | n.out.isConnectedTo(dram.valid) => Some(1l)
          case List(dram:FringeDenseStore) if n.out.isConnectedTo(dram.size) | n.out.isConnectedTo(dram.offset) =>
            dram.getIter
          case List(dram:FringeSparseLoad) => Some(1l)
          case List(dram:FringeSparseStore) => Some(1l)
          case _ => n.done.T.getScale
        }
      case n:BufferWrite =>
        n.data.T match {
          case data:FringeDenseLoad => Some(1l)
          case data:FringeDenseStore => data.getIter // ack
          case data:FringeSparseLoad => Some(1l)
          case data:FringeSparseStore => Some(1l)
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
        }.getOrElse(Some(1l))
      case n => throw PIRException(s"Don't know how to compute scale of $n")
    }
  }

  def accountForCycle = true

  def compCount(n:PIRNode):Option[Long] = dbgblk(s"compCount($n)"){
    n match {
      case n:Context if n.collectDown[HostInController]().nonEmpty => Some(1l)
      case n:Context =>
        var (readsWithInits, reads) = n.reads.partition { _.initToken.get }
        if (accountForCycle) reads = reads ++ readsWithInits
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

  def compVec(n:PIRNode):Int = dbgblk(s"compVec($n)"){
    n match {
      case n:Const => 1
      case n:CounterIter if n.i.nonEmpty => 1
      case n:CounterValid if n.i.nonEmpty => 1
      case n:RegAccumOp => 1
      case n:LoopController if n.ctrl.get.children.isEmpty => n.cchain.T.last.getVec
      case n:Controller => 1
      case n:ControllerValid => 1
      case n:ControllerDone => 1
      case n:Counter => n.par
      case n:LocalOutAccess => assertOne(n.banks.get, s"$n.banks")
      case n:LocalInAccess => assertUnify(n.out.T, s"$n.out.T") { _.getVec }.get
      case n:GlobalOutput => assertUnify(n.out.T, s"$n.out.T") { _.getVec }.get
      case n:GlobalInput => assertUnify(n.out.T, s"$n.out.T") { _.getVec }.get
      case n:BanckedAccess =>
        val bank = n.bank.T
        val offset = n.offset.T
        (bank :+ offset).map { _.getVec }.max
      case n:Access =>
        val mem = n.mem.T
        assertOne(mem.banks.get, s"$n's mem $mem bank dimension")
      case n:PIRNode => 
        val ctrler = assertOne(
          n.ctx.get.collectDown[Controller]().filter { _.ctrl.get == n.ctrl.get }, 
          s"$n.ctrler"
        )
        ctrler.getVec
    }
  }

}

