package pir.newnode

import pir._

import pirc._
import pirc.enums._
import pirc.util._
import prism.traversal._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._
import prism.codegen.Logging

abstract class Pass(implicit val design:PIR) extends prism.pass.Pass with PIRCollector {

  def qdef(n:Any) = n match {
    case n:IR => s"${n.name.getOrElse(n.toString)} = ${n.productName}"
    case n => n.toString
  }

  def qtype(n:Any) = n match {
    case n:IR => n.name.map { name => s"${n.className}${n.id}[$name]" }.getOrElse(s"$n")
    case n => n.toString
  }

  lazy val pirmeta = design.newTop.metadata
}

trait PIRCollector extends GraphCollector {
  def collectUp[M<:Node:ClassTag](n:Node, depth:Int=10, log:Option[Logging]=None):List[M] =
    super.collectUp[Node, M](n, depth, log)

  def collectDown[M<:Node:ClassTag](n:Node, depth:Int=10, log:Option[Logging]=None):List[M] = 
    super.collectDown[Node, M](n, depth, log)

  def collectIn[M<:Node:ClassTag](n:Node, depth:Int=10, log:Option[Logging]=None):List[M] = 
    super.collectIn[Node, M](n, depth, log)

  def collectOut[M<:Node:ClassTag](n:Node, depth:Int=10, log:Option[Logging]=None):List[M] = 
    super.collectOut[Node, M](n, depth, log)
}

abstract class PIRTraversal(implicit design:PIR) extends Pass with prism.traversal.Traversal  {
  implicit val nct = classTag[N]
  type N = Node with Product
  type P = Container
  type A = Module
  type D = PIR
}

trait TopologicalTraversal extends PIRTraversal with prism.traversal.TopologicalTraversal {
  override def selectFrontier = {
    var frontier = super.selectFrontier
    frontier = frontier.collect { case store:LocalStore => store }
    if (frontier.isEmpty) frontier = super.selectFrontier
    frontier
  }
}

trait DFSTopDownTopologicalTraversal extends TopologicalTraversal with prism.traversal.DFSTopDownTopologicalTraversal
trait BFSTopDownTopDownTopologicalTraversal extends TopologicalTraversal with prism.traversal.BFSTopDownTopDownTopologicalTraversal
trait BottomUpTopologicalTraversal extends TopologicalTraversal with prism.traversal.BottomUpTopologicalTraversal

abstract class PIRTransformer(implicit design:PIR) extends PIRTraversal with GraphTransformer {

  def quote(n:Any) = qtype(n)

  override def mirrorX(n:Any, mapping:Map[Any,Any])(implicit design:D):Map[Any,Any] = {
    if (mapping.contains(n)) return mapping
    var mp  = mapping
    // Nodes do not mirror
    mp = n match {
      case n@(_:SRAM | _:StreamIn | _:StreamOut) => mp + (n -> n)
      case n => mp
    }
    dbgblk(s"mirrorX(${quote(n)})") {
      mp = super.mirrorX(n, mp)
      val m = mp(n)
      pirmeta.mirror(n,m)
      dbgs(s"${quote(n)} -> ${quote(m)}")
      (n, m) match {
        case (n:Memory, m:Memory) => 
          val writers = n.writers.map { 
            case Def(w,LocalStore(mems, addrs, data)) => 
              // prevent mirroring of writer
              mp += w -> w
              w
          }
          dbg(s"writers of $n = ${writers}")
          mp = writers.foldLeft(mp) { case (mp, writer) => mirrorX(writer, mp) }
        case (n:Counter, m:Counter) =>
          dbg(s"$m.parent = ${m.parent}")
          mp = collectUp[CounterChain](n).foldLeft(mp) { case (mp, cc) => mirrorX(cc, mp) }
        case (n:CounterChain, m:CounterChain) =>
          dbg(s"$m.counters=${m.counters.map { c => s"counter=$c"}}")
          mp = n.counters.foldLeft(mp) { case (mp, ctr) => mirrorX(ctr, mp) }
        case _ =>
      }
      mp(n)
    }
    mp
  }

  def mirrorX(n:Any)(implicit design:D):Map[Any,Any] = mirrorX(n, Map[Any,Any]())

  def mirrorX(n:Any, container:Container, init:Map[Any,Any])(implicit design:D):Map[Any,Any] = {
    val mapping = mirrorX(n, init)
    val newNodes = (mapping.values.toSet diff mapping.keys.toSet).collect { case n:N => n}.filter(_.parent.isEmpty)
    newNodes.foreach { m => 
      m.setParent(container)
      dbg(s"${qtype(container)} add ${qtype(m)}")
    }
    mapping
  }

  def mirrorX(n:Any, container:Container)(implicit design:D):Map[Any,Any] =
    mirrorX(n, container, Map[Any,Any]())

  def mirror[T<:N](n:T, container:Container)(implicit design:D):T = {
    val mapping = mirrorX(n, container)
    mapping(n).asInstanceOf[T]
  }
}

trait ControllerTraversal extends GraphTraversal with SiblingFirstTraversal with GraphUtil {
  type N = Controller
}

class CUInsertion(implicit design:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal {

  import pirmeta._

  override def shouldRun = true

  override def initPass = {
    super.initPass
    controllerTraversal.resetTraversal
  }
  
  val cuMap = mutable.Map[Controller, GlobalContainer]()

  val controllerTraversal = new ControllerTraversal with UnitTraversal {
    override def visitNode(n:N, prev:T):T = {
      val cu = n match {
        case n:TopController => design.newTop
        case n:ArgController => design.newTop.argFringe
        case n => CUContainer().setParent(design.newTop).name(s"${qtype(n)}").ctrl(n)
      }
      dbg(s"${qtype(n)} -> ${qtype(cu)}")
      cuMap += n -> cu
      super.visitNode(n, prev)
    }
  }

  override def runPass =  {
    createCUForController
    traverseNode(design.newTop)
  }

  def createCUForController = {
    controllerTraversal.traverseNode(design.newTop.topController, ())
  }

  override def visitNode(n:N):Unit = n match {
    case n:SRAM => swapParent(n, CUContainer().setParent(design.newTop).name(s"${qtype(n)}")) 
    case n:ComputeNode if !cuMap(ctrlOf(n)).isParentOf(n) => swapParent(n, cuMap(ctrlOf(n)))
    case _ => super.visitNode(n)
  }

}

class AccessPulling(implicit design:PIR) extends PIRTransformer with BottomUpTopologicalTraversal with DFSTraversal with UnitTraversal {

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    traverseScope(design.newTop, ())
  }

  override def check = {
    // Checking
    (collectDown[Def](design.newTop) ++ collectDown[Memory](design.newTop)).foreach { n =>
      assert(withParent[GlobalContainer](n), s"$n is not contained by a CU")
    }
  }

  def withParent[T<:Node:ClassTag](n:Node) = {
    n.parent.fold(false) { case p:T => true; case _ => false }
  }

  def pullNode(dep:A, deped:A, container:GlobalContainer) = dbgblk(s"pullNode(${qtype(dep)}, ${qtype(deped)}, ${qtype(container)})") {
    dbg(s"dep.depeds=${dep.depeds}")
    val depedContainers = dep.depeds.flatMap { deped => collectUp[GlobalContainer](deped).headOption }

    val portable = dep match {
      case dep:Counter => false
      case dep:Def => true
      case dep:Memory if isRemoteMem(dep) => false
      case dep:Memory => true
    } 

    // Single consumer, simply move node into destination container
    if (depedContainers.size==1 && portable) {
      dbg(s"swapParent ${qtype(dep)} from ${dep.parent.map(qtype)} to ${qtype(container)}")
      swapParent(dep, container)
    } else { //Multiple consumer, mirror new node into destination consumer containers and reconnect 
      val m = mirror(dep, container)
      swapOutputs(deped, from=dep, to=m)
      dbg(s"swapOutputs(deped=${qtype(deped)}, from=${qtype(dep)}, to=${qtype(m)})")
    }
  }

  def isRemoteMem(n:Memory) = n match {
    case (_:SRAM | _:StreamIn | _:StreamOut)  => true
    case n:FIFO if n.writers.size>=1 => true
    case n:RegFile => true
    case _ => false
  }

  override def visitNode(n:N):Unit = {
    dbgs(s"visitNode ${qdef(n)}")
    n match {
      case n:Def =>
        val localDeps = n.deps.flatMap {
          case n:Memory if isRemoteMem(n) => None
          case n:StoreDef => None
          case n:ArgInDef => None
          case n => Some(n)
        }
        val cu = collectUp[GlobalContainer](n).head
        localDeps.foreach { dep =>
          if (!cu.isAncestorOf(dep)) pullNode(dep, n, cu)
        }
      case _ =>
    }
    super.visitNode(n)
  }

}

class DeadCodeElimination(implicit design:PIR) extends PIRTransformer with BottomUpTopologicalTraversal with BFSTraversal {
  import pirmeta._

  type T = Map[N, Boolean]

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    // Mark dead code
    val deathMap = traverseScope(design.newTop, Map.empty)
    // Remove dead code
    deathMap.foreach { 
      case (n, true) =>
        dbg(s"eliminate ${qdef(n)} from parent=${n.parent}")
        val neighbors = n.neighbors
        removeNode(n)
        neighbors.foreach { nb =>
          dbg(s"neighbor=$nb, neighbor.neighbors=${nb.neighbors}")
          assert(!nb.neighbors.asInstanceOf[Set[N]].contains(n))
        }
        pirmeta.removeAll(n)
      case (n, false) => removeUnusedIOs(n)
    }
  }

  def markDeath(deathMap:T, n:N) = {
    val isDead = n match {
      case n:ArgOut => false
      case n:StreamOut => false
      case n:Counter => false
        //if (!design.controlPropogator.hasRunAll) false
        //else if (ctrlOf(n).isOuterControl) false //TODO: after ControlAllocation this can be eliminated if n.depeds is empty
        //else n.depeds.forall(d => deathMap.getOrElse(d, false))
      case n => depFunc(n).forall(d => deathMap.getOrElse(d, false))
    }
    if (isDead) dbgs(s"Mark $n as dead code")
    deathMap + (n -> isDead)
  }

  override def visitNode(n:N, prev:T):T = {
    super.visitNode(n, markDeath(prev, n))
  }

}

class ControlPropogation(implicit design:PIR) extends PIRTraversal with BottomUpTopologicalTraversal with BFSTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  override def initPass = {
    super.initPass
    controllerTraversal.resetTraversal
  }

  val forward = false

  override def runPass =  {
    controllerTraversal.traverseNode(design.newTop.topController, ())
    traverseScope(design.newTop, ())
  }

  override def check = {
    val computes = collectDown[ComputeNode](design.newTop)
    computes.foreach { computes =>
      assert(ctrlOf.contains(computes), s"$computes's controller is not set")
    }
  }

  def resetController(n:Node, ctrl:Controller):Unit = n match {
    case n:CounterChain =>
      dbg(s"setting ${qtype(n)}.ctrl=$ctrl")
      ctrlOf.removeKey(n)
      n.ctrl(ctrl)
      n.children.foreach(c => resetController(c, ctrl))
    case n:ComputeNode => 
      dbg(s"setting ${qtype(n)}.ctrl=$ctrl")
      ctrlOf.removeKey(n)
      n.ctrl(ctrl)
      n.deps.foreach(d => resetController(d, ctrl))
    case n =>
  }

  val controllerTraversal = new ControllerTraversal with UnitTraversal{
    override def visitNode(n:N, prev:T):T = {
      n match {
        case n:LoopController => resetController(n.cchain, n)
        case _ =>
      }
      super.visitNode(n, prev)
    }
  }

  override def visitNode(n:N, prev:T):T = {
    dbg(s"visitNode(${qtype(n)}, n.ctrl=${ctrlOf.get(n)})")
    n match {
      case n:ComputeNode =>
        if (!ctrlOf.isDefinedAt(n)) {
          assert(depFunc(n).forall(ctrlOf.isDefinedAt), s"$ctrlOf is not defined at ${depFunc(n).filterNot(ctrlOf.isDefinedAt)}")
          val ctrls = depFunc(n).map(ctrlOf.apply).toSet
          assert(ctrls.size==1, s"deps have different controls ${depFunc(n).map(d => (d, ctrlOf(d)))}")
          ctrlOf(n) = ctrls.head
        }
        dbg(ctrlOf.info(n).get)
      case n => 
    }
    super.visitNode(n, prev) 
  }

}

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

class CUStatistics(implicit design:PIR) extends Pass {

  type N = Node with Product
  type T = Unit

  def shouldRun = true

  override def dbg(s:Any):Unit = {
    info(s"$s")
    super.dbg(s)
  }

  override def runPass =  {
    val cus = collectDown[GlobalContainer](design.newTop)
    val cuMap = cus.groupBy {
      case cu if collectDown[SRAM](cu).nonEmpty => "pmus"
      case cu:ArgFringe => "argFringe"
      case cu:FringeContainer => "fringes"
      case cu if collectDown[StageDef](cu).nonEmpty => "pcus"
      case cu => "ocus"
    }
    dbg(s"number of cus=${cus.size}")
    cuMap.foreach { case (key, cus) =>
      dbg(s"")
      dbg(s"number of $key = ${cus.size}")
      //dbg(s"$key = ${cus.map(qtype)}")
      val fanIns = cus.map { cu => cu.ins.size }
      dbg(s"max fanIn of $key = ${fanIns.max}")
      dbg(s"average fanIn of $key = ${fanIns.sum.toFloat / fanIns.size}")
      val fanOuts = cus.map { cu => cu.outs.size }
      dbg(s"max fanOut of $key = ${fanOuts.max}")
      dbg(s"average fanOut of $key = ${fanOuts.sum.toFloat / fanOuts.size}")
    }
  }

}

class IRCheck(implicit design:PIR) extends Pass {
  import pirmeta._

  type N = Node with Product
  def shouldRun = true

  def warn(s:Any) = {
    dbg(s"$s")
    pirc.util.warn(s)
  }

  def err(s:Any) = {
    dbg(s"$s")
    pirc.util.err(s)
  }

  def assert(predicate:Boolean, info:Any) = {
    dbg(s"$info")
    pirc.util.assert(predicate, info)
  }

  def checkCUIO(cu:GlobalContainer) = {
    cu.ins.foreach { in =>
      in.src match {
        case node:LocalLoad =>
        case node:Memory =>
        case node =>
          dbg(s"$cu's global input $in.src = $node")
          in.connected.foreach { out =>
            dbg(s"out=$out out.src=${out.src}")
          }
          throw PIRException(s"$cu's global output $in.src = $node")
      }
    }
    cu.outs.foreach { out =>
      out.src match {
        case node:LocalStore =>
        case node:Memory =>
        case node =>
          dbg(s"$cu's global output $out.src = $node")
          out.connected.foreach { in =>
            dbg(s"in=$in in.src=${in.src}")
          }
          throw PIRException(s"$cu's global output $out.src = $node")
      }
    }
  }

  def checkMemoryAccess(cu:GlobalContainer) = {
    val mems = collectDown[Memory](cu)
    mems.foreach { mem =>
      mem match {
        case mem:ArgIn =>
        case mem:StreamIn =>
        case mem if mem.writers.isEmpty =>
          warn(s"$mem in $cu does not have writer")
        case _ =>
      }
      mem match {
        case mem:ArgOut =>
        case mem:StreamOut =>
        case mem:StreamIn if mem.field == "ack" =>
        case mem if mem.readers.isEmpty =>
          warn(s"$mem in $cu does not have reader")
        case _ =>
      }
    }
  }

  def checkDefControl(cu:GlobalContainer) = {
    val stageDef = collectDown[StageDef](cu)
    stageDef.foreach { stageDef =>
      assert(ctrlOf.contains(stageDef), s"${qtype(stageDef)} in $cu doesn't have ctrl defined")
      val ctrl = ctrlOf(stageDef)
      assert(ctrl.isInnerControl, s"${qtype(stageDef)}.ctrl.level = ${ctrl.level}. stageDef.ctrl=${ctrl}")
    }
  }

  override def runPass =  {
    val cus = collectDown[GlobalContainer](design.newTop)
    cus.foreach { cu =>
      checkCUIO(cu)
      checkMemoryAccess(cu)
      checkDefControl(cu)
    }
  }

}

class MemoryAnalyzer(implicit design:PIR) extends Pass {
  import pirmeta._

  type N = Node with Product
  type T = Unit

  def shouldRun = true

  val traversal = new ControllerTraversal {}
  def setParentControl(mem:Memory) = dbgblk(s"setParentControl($mem)") {
    dbg(s"accesses: ${mem.accesses}")
    var accessCtrls = mem.accesses.map { access => 
      dbg(s"access:$access ctrl=${ctrlOf(access)}")
      ctrlOf(access)
    }
    mem match {
      case mem:ArgOut => accessCtrls += design.newTop.argController
      case _ =>
    }
    val lcaCtrl = accessCtrls.reduce[Controller]{ case (a1, a2) =>
      val lca = traversal.leastCommonAncesstor(a1, a2)
      dbg(s"a1=$a1, a2=$a2, lca=$lca")
      if (lca.isEmpty) err(s"$a1 and $a2 do not share common ancestor")
      lca.get
    }
    ctrlOf(mem) = lcaCtrl
    ctrlOf.info(mem).foreach(dbg)

    mem.accesses.foreach { access =>
      val ancestors = ctrlOf(access) :: ctrlOf(access).ancestors
      val idx = ancestors.indexOf(lcaCtrl)
      val topCtrlIdx = if (idx==0) idx else idx - 1
      topCtrlOf(access) = ancestors(topCtrlIdx)
      topCtrlOf.info(access).foreach(dbg)
    }

    isLocalMem(mem) = mem.accesses.forall(a => ctrlOf(a) == ctrlOf(mem))
    isLocalMem.info(mem).foreach(dbg)
  }

  override def runPass =  {
    lazy val mems = collectDown[Memory](design.newTop)
    mems.foreach { mem =>
      setParentControl(mem)
    }
  }

}

class RouteThroughElimination(implicit design:PIR) extends PIRTransformer with BottomUpTopologicalTraversal with BFSTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    traverseScope(design.newTop, ())
  }

  override def visitNode(n:N, prev:T):T = {
    n match {
      case Def(load:MemLoad, MemLoad(WithWriters(Def(rstore:MemStore, MemStore(rmem, None, Def(rload:MemLoad, MemLoad(mem, None))))::Nil), None)) =>
        dbgblk(s"Found Route Through ${qdef(load)}") {
          dbg(s"rload:${qdef(rload)}")
          dbg(s"rstore:${qdef(rstore)}")
          dbg(s"rmem:${qdef(rmem)}")
          dbg(s"load:${qdef(load)}")
          swapConnection(load, rmem.out, mem.out)
        }
      case _ =>
    }
    super.visitNode(n, prev)
  }

}

class CounterChainFilling(implicit design:PIR) extends PIRTransformer with ChildFirstTraversal with UnitTraversal {

  import pirmeta._

  override def shouldRun = true

  override def runPass =  {
    traverseNode(design.newTop)
  }

  //override def visitNode(n:N):Unit = n match {
    //case n:GlobalContainer => emitBlock(s"Visiting ${qdef(n)}") {
      //val chains = collectDown[CounterChain](n)
      //dbgs(s"chains: ${chains.mkString(qtype)}")
      //chains.map
    //}
    //case _ => super.visitNode(n)
  //}

}

class TestTraversal(implicit design:PIR) extends PIRTraversal with BottomUpTopologicalTraversal with BFSTraversal with UnitTraversal {
//class TestTraversal(implicit design:PIR) extends Pass with DFSTopDownTopologicalTraversal with BFSTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    tic
    traverseScope(design.newTop, ())
    //traverseNode(design.newTop, ())
    toc("TestPass", "ms")
    check
  }

  override def check = {
    val top = design.newTop
    val allNodes = top::top.descendents
    val unvisited = allNodes.filterNot(isVisited)
    assert(unvisited.isEmpty, s"not all nodes are visited! unvisited=${unvisited}")
  }

  override def visitNode(n:N) = {
    dbg(s"Visiting ${qdef(n)}")
    super.visitNode(n)
  }

}

//trait CUPartition {

  //type N
  //type R

  //trait CostMetric {
    //def evaluate(n:N, r:R):Either[Success, Failure]
  //}

  //object TypeCost extends CostMetric {
    //def evaluate(n:N, r:R):Either[Success, Failure] = {
    //}
  //}
  //object FanInCost extends CostMetric {
  //}

  //val metrics = mutable.ListBuffer[CostMetric]()

//}
