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

abstract class Pass(implicit val design:PIR) extends prism.pass.Pass with GraphCollector {

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

abstract class Traversal(implicit design:PIR) extends Pass with prism.traversal.Traversal  {
  implicit val nct = classTag[N]
  type N = Node with Product
  type P = Container
  type A = Module
  type D = PIR
}

abstract class Transformer(implicit design:PIR) extends Traversal with GraphTransformer {

  def quote(n:Any) = qtype(n)

  override def mirrorX(n:Any, mapping:Map[Any,Any]=Map.empty)(implicit design:D):Map[Any,Any] = {
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
      (n, m) match {
        case (n:Memory, m:Memory) => 
          val writers = n.writers.map { 
            case Def(w,LocalStore(mems, addrs, data)) => 
              // prevent mirroring of addrs and data
              mp += addrs -> addrs
              mp += data -> data
              w
          }
          dbg(s"writers of $n = ${writers}")
          mp = writers.foldLeft(mp) { case (mp, writer) => mirrorX(writer, mp) }
        case (n:Counter, m:Counter) =>
          dbg(s"$m.parent = ${m.parent}")
          mp = collectUp[CounterChain](n).foldLeft(mp) { case (mp, cc) => mirrorX(cc, mp) }
        case (n:CounterChain, m:CounterChain) =>
          dbg(s"$m.counters=${m.counters.map { c => s"counter=$c"}}")
        case _ =>
      }
      mp(n)
    }
    mp
  }
  def mirror[T<:N](n:T, container:Container)(implicit design:D):T = {
    val (m, ms) = mirror(n)
    ms.filter{_.parent.isEmpty}.foreach(_.setParent(container))
    dbg(s"${qtype(container)} add ${ms.map(qtype)}")
    m
  }
}

trait ControllerTraversal extends GraphTraversal with SiblingFirstTraversal with GraphUtil {
  type N = Controller
}

class CUInsertion(implicit design:PIR) extends Transformer with SiblingFirstTraversal {

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

  override def transform(n:N):Unit = n match {
    case n:SRAM => swapParent(n, CUContainer().setParent(design.newTop).name(s"${qtype(n)}")) 
    case n:ComputeContext if !cuMap(ctrlOf(n)).isParentOf(n) => swapParent(n, cuMap(ctrlOf(n)))
    case _ => super.transform(n)
  }

}

class AccessPulling(implicit design:PIR) extends Transformer with BottomUpTopologicalTraversal with BFSTraversal {

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
    val depedContainers = dep.depeds.flatMap { 
      case deped:LocalStore => None
      case deped => collectUp[GlobalContainer](deped).headOption
    }

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
    case (_:SRAM | _:StreamIn)  => true
    case _ => false
  }

  override def transform(n:N):Unit = {
    dbgs(s"transform ${qdef(n)}")
    n match {
      case n:Def =>
        val localDeps = n.deps.flatMap {
          case n:Memory if isRemoteMem(n) => None
          case n => Some(n)
        }
        val cu = collectUp[GlobalContainer](n).head
        localDeps.foreach { dep =>
          if (!cu.isAncestorOf(dep)) pullNode(dep, n, cu)
        }
      case _ =>
    }
    super.transform(n)
  }

}

class DeadCodeElimination(implicit design:PIR) extends Transformer with BottomUpTopologicalTraversal with BFSTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    traverseScope(design.newTop, ())
  }

  override def check = {
    val containers = collectDown[CUContainer](design.newTop)
    val unvisited = containers.filterNot(isVisited)
    assert(unvisited.isEmpty, s"not all containers are visited! unvisited=${unvisited}")
  }

  def isUseFree(n:N) = n match {
    case n:ArgOut => false
    case n:StreamOut => false
    case n:Counter =>
      if (!design.controlPropogator.hasRunAll) false
      else if (ctrlOf(n).isOuterControl) false //TODO: after ControlAllocation this can be eliminated if n.depeds is empty
      else n.depeds.isEmpty
    case n:Container => n.children.isEmpty 
    case Def(n, LocalStore(mems, addrs, data)) => mems.isEmpty
    case n:MemStore => n.mem == null
    case n => n.depeds.isEmpty
  }

  override def transform(n:N):Unit = {
    removeUnusedIOs(n)
    if (isUseFree(n)) {
      dbgblk(s"transform ${qdef(n)}") {
        dbg(s"eliminate ${qdef(n)} from parent=${n.parent} ${isUseFree(n)}")
        val deps = n.deps
        n.ios.foreach(_.disconnect)
        n.parent.foreach { parent =>
          parent.removeChild(n)
          pirmeta.removeAll(n)
        }
        deps.foreach(traverseNode)
      }
    } else {
      dbg(s"transform ${qdef(n)}")
      super.transform(n)
    }
  }

}

class ControlPropogation(implicit design:PIR) extends Traversal with BottomUpTopologicalTraversal with BFSTraversal {
  import pirmeta._

  type T = Controller

  override def shouldRun = true

  override def initPass = {
    super.initPass
    controllerTraversal.resetTraversal
  }

  val forward = false

  override def runPass =  {
    controllerTraversal.traverseNode(design.newTop.topController, ())
    traverseScope(design.newTop, null)
  }

  override def check = {
    val contexts = collectDown[ComputeContext](design.newTop)
    contexts.foreach { context =>
      assert(ctrlOf.contains(context), s"$context's controller is not set")
    }
  }

  def resetController(n:Node, ctrl:Controller):Unit = n match {
    case n:CounterChain =>
      dbg(s"setting ${qtype(n)}.ctrl=$ctrl")
      ctrlOf.removeKey(n)
      n.ctrl(ctrl)
      n.children.foreach(c => resetController(c, ctrl))
    case n:ComputeContext => 
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

  override def visitNode(n:N, prev:Controller):T = {
    dbg(s"visitNode(${qtype(n)}, currentContext=$prev, n.ctrl=${ctrlOf.get(n)}), isDepFree=${isDepFree(n)}")
    n match {
      case n:ComputeContext =>
        val res = if (!ctrlOf.contains(n)) {
          assert(prev != null)
          ctrlOf(n) = prev
          super.visitNode(n, prev)
        } else {
          super.visitNode(n, ctrlOf(n))
        }
        dbg(ctrlOf.info(n).get)
        res
      case n => super.visitNode(n, null) 
    }
  }

}

class AccessLowering(implicit design:PIR) extends Transformer with ChildFirstTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    traverseNode(design.newTop)
  }

  def retime(x:Def, cu:GlobalContainer) = {
    x match {
      case x:Const[_] => x
      case Def(n:IterDef, IterDef(counter, offset)) =>
        mirror[IterDef](n, cu)
      case x =>
        val fifo = RetimingFIFO().setParent(cu)
        val store = MemStore(fifo, None, x).setParent(cu)
        val load = MemLoad(fifo, None).setParent(cu)
        pirmeta.mirror(x, store)
        pirmeta.mirror(x, load)
        load
    }
  }

  override def transform(n:N):Unit = {
    n match {
      case Def(n:LoadDef, LoadDef(mems, addrs)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val accessCU = collectUp[GlobalContainer](n).head
          mems.foreach { mem =>

            // Remote read address calculation
            val memCU = collectUp[GlobalContainer](mem).head
            val maddrs = addrs.map { addrs => addrs.map { addr => mirror[Def](addr, memCU) } }
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
      case Def(n:StoreDef, StoreDef(mems, addrs, data)) =>
        dbgblk(s"Lowering ${qdef(n)}") {
          val accessCU = collectUp[GlobalContainer](n).head
          mems.foreach { mem =>
            // Local write address calculation
            val memCU = collectUp[GlobalContainer](mem).head
            val (saddrs, sdata) = if (memCU!=accessCU && addrs.nonEmpty) {
              val dataLoad = retime(data, memCU)
              val addrLoad = addrs.map { addrs =>
                addrs.map { addr => retime(addr, memCU) }
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
    super.transform(n)
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
      dbg(s"$key = ${cus.map(qtype)}")
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
        case node:LocalStore =>
        case node =>
          dbg(s"$cu's global input $in.src = $node")
          throw PIRException(s"$cu's global input $in.src = $node is not LocalStore")
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

//class RouteThroughElimination(implicit design:PIR) extends Transformer with ChildFirstTopologicalTraversal {

  //override def shouldRun = true

  //val forward = false

  //override def runPass =  {
    //traverseNode(design.newTop)
  //}

  //override def check = {
    //val containers = collectDown[CUContainer](design.newTop)
    //val unvisited = containers.filterNot(isVisited)
    //assert(unvisited.isEmpty, s"not all containers are visited! unvisited=${unvisited}")
  //}

  //def isUseFree(n:N) = n match {
    //case n:ArgOut => false
    //case n:StreamOut => false
    ////case n:StreamIn => false
    //case n:Memory => n.depeds.filterNot { case n:LocalStore => true; case _ => false }.isEmpty
    //case n:Counter => false
    //case n:Top => false
    //case n:Container => n.children.isEmpty 
    //case Def(n, LocalStore(mems, addrs, data)) => mems.isEmpty
    //case n:MemStore => n.mem == null
    //case n => n.depeds.isEmpty
  //}

  //override def transform(n:N):Unit = dbgblk(s"transform(${qdef(n)})") {
    //removeUnusedIOs(n)
    //if (isUseFree(n)) {
      //dbg(s"eliminate ${qdef(n)} from parent=${n.parent} ${isUseFree(n)}")
      //val deps = n.deps
      //n.ios.foreach(_.disconnect)
      //n.parent.foreach { parent =>
        //parent.removeChild(n)
        //pirmeta.removeAll(n)
      //}
    //}
    //super.transform(n)
  //}

//}

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

class TestTraversal(implicit design:PIR) extends Pass with BottomUpTopologicalTraversal with BFSTraversal with UnitTraversal {
//class TestTraversal(implicit design:PIR) extends Pass with DFSTopDownTopologicalTraversal with BFSTraversal with UnitTraversal {
  import pirmeta._

  type N = Node with Product
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
