package pir.newnode

import pir._

import pirc._
import pirc.enums._
import pirc.util._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._

abstract class Pass(implicit val design:PIR) extends prism.pass.Pass with Logger with prism.traversal.GraphCollector {
  type N = Node with Product
  type P = Container
  type A = Module
  type D = PIR
  implicit val nct = classTag[N]

  def qdef(n:N) = s"${n.name.getOrElse(n.toString)} = ${n.productName}"
  def qtype(n:N) = n.name.map { name => s"${n.className}${n.id}[$name]" }.getOrElse(s"$n")
}

abstract class Traversal(implicit design:PIR) extends Pass with prism.traversal.Traversal {

  override def reset = super[Pass].reset

  override def initPass = super[Traversal].reset
}

abstract class Transformer(implicit design:PIR) extends Traversal with prism.traversal.GraphTransformer { self:Logger =>

  def quote(n:Any) = n match {
    case n:N => qtype(n)
    case n => n.toString
  }
  override def mirrorX(n:Any, mapping:Map[Any,Any]=Map.empty)(implicit design:D):Map[Any,Any] = {
    if (mapping.contains(n)) return mapping
    var mp  = mapping
    emitBlock(s"mirrorX(${quote(n)})") {
      mp = n match {
        case n@(_:SRAM | _:StreamIn) => mapping + (n -> n)
        case n => super.mirrorX(n, mapping)
      }
      val m = mp(n)
      (n,m) match {
        case (n:N,m:N) => 
          if (n!=m) n.name.foreach { name => m.name(name) }
        case _ =>
      }
      (n, m) match {
        case (n:ComputeContext, m:ComputeContext) if n != m => m.setControl(n.ctrl)
        case _ =>
      }
      (n, m) match {
        case (n:Memory, m:Memory) => 
          val writers = n.depeds.collect { 
            case Def(w,LocalStore(mems, addrs, data)) => 
              // prevent mirroring of addrs and data
              mp += addrs -> addrs
              mp += data -> data
              w
          }
          dprintln(s"writers of $n = ${writers}")
          mp = writers.foldLeft(mp) { case (prev, writer) => mirrorX(writer, mp) }
        case (n:Counter, m:Counter) =>
          mp = collectUp[CounterChain](n).foldLeft(mp) { case (mp, cc) => 
            mirrorX(cc, mp)
          }
          dprintln(s"$m.parent = ${m.parent}")
        case (n:CounterChain, m:CounterChain) =>
          dprintln(s"$m.counters=${m.counters.map { c => s"counter=$c"}}")
        case _ =>
      }
      mp(n)
    }
    mp
  }
  def mirror[T<:N](n:T, container:Container)(implicit design:D):T = {
    val (m, ms) = mirror(n)
    ms.filter{_.parent.isEmpty}.foreach(_.setParent(container))
    dprintln(s"${qtype(container)} add ${ms.map(qtype)}")
    m
  }
}

trait ControllerTraversal extends prism.traversal.GraphTraversal with prism.traversal.BFSTraversal {
  type N = Controller
  def visitFunc(n:N):List[N] = n.children 
}

class CUInsertion(implicit design:PIR) extends Transformer with prism.traversal.ChildLastTraversal {

  override lazy val stream = newStream(s"CUInsertion.log")

  override def shouldRun = true

  override def reset = super[Transformer].reset

  override def initPass = {
    super[ChildLastTraversal].reset
    controllerTraversal.reset
  }
  
  val cuMap = mutable.Map[Controller, GlobalContainer]()

  val controllerTraversal = new ControllerTraversal {
    type T = Unit
    override def visitNode(n:N, prev:T):T = {
      val cu = n match {
        case n:TopController => design.newTop
        case n:ArgController => design.newTop.argFringe
        case n => CUContainer().setParent(design.newTop).name(n.name)
      }
      dprintln(s"creating $cu for $n")
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
    case n:SRAM => swapParent(n, CUContainer().setParent(design.newTop).name(n.name)) 
    case n:ComputeContext if !cuMap(n.ctrl).isParentOf(n) => swapParent(n, cuMap(n.ctrl))
    case _ => super.transform(n)
  }

}

class AccessPulling(implicit design:PIR) extends Transformer with prism.traversal.ChildLastTraversal {

  override lazy val stream = newStream(s"AccessPulling.log")

  override def shouldRun = true

  override def reset = super[Transformer].reset

  override def initPass = super[ChildLastTraversal].reset

  override def runPass =  {
    traverseNode(design.newTop)
  }

  override def check = {
    // Checking
    (collectDown[Def](design.newTop) ++ collectDown[Memory](design.newTop)).foreach { n =>
      assert(withParent[GlobalContainer](n), s"$n is not contained by a CU")
    }
  }

  def pullNode[C<:N with Container:ClassTag](n:Module with Product):Unit = emitBlock(s"pullNode(${qtype(n)})") {
    val output = n match {
      case n:Def => n.out
      case n:Memory => n.out
    }
    dprintln(s"n.depeds=${n.depeds}")
    val containerMaps = n.depeds.flatMap { 
      case deped:LocalStore => None
      case deped => collectUp[C](deped).headOption.map { c => (c, deped) }
    }.groupBy { _._1 }.map { case (c, depeds) => (c, depeds.map{_._2}) }
    dprintln(s"containerMaps=$containerMaps")

    // Single consumer, simply move node into destination container
    if (containerMaps.size==1) {
      val container = containerMaps.keys.head
      dprintln(s"swapParent ${qtype(n)} from ${n.parent.map(qtype)} to ${qtype(container)}")
      swapParent(n, container)
      n.deps.foreach(pull)
    }

     //Multiple consumer, mirror new node into destination consumer containers and reconnect 
    if (containerMaps.size > 1) {
      containerMaps.foreach { case (container, depeds) => 
        val m = mirror(n, container)
        depeds.foreach { deped =>
          swapOutputs(deped, from=n, to=m)
          dprintln(s"swapOutputs(deped=${qtype(deped)}, from=${qtype(n)}, to=${qtype(m)})")
        }
      }
    }

  }

  // Memory is local to the reader
  def isLocalMem(n:Memory) = n match {
    case mem:SRAM => false
    case mem:StreamIn => false
    case mem:StreamOut => false
    case mem => true
  }

  def withParent[T<:Node:ClassTag](n:Node) = {
    n.parent.fold(false) { case p:T => true; case _ => false }
  }

  def usesOf(n:Node) = {
    n match {
      case n:Def => n.depeds
      case n:Memory => n.depeds.filterNot { case n:LocalStore => true; case _ => false }
    }
  }

  def isLocalToUse(n:Node) = {
    val userCUs = usesOf(n).flatMap ( u => collectUp[GlobalContainer](u).headOption ).toSet
    if (userCUs.size > 1) false
    else if (userCUs.size==0) false
    else n.isChildOf(userCUs.head)
  }

  def pull(n:N) = n match {
    case _:IterDef | _:Const[_] if !isLocalToUse(n)=> pullNode[CUContainer](n.asInstanceOf[Def])
    case n:Def if !withParent[CUContainer](n) => pullNode[CUContainer](n)
    case n:Memory if isLocalMem(n) && !isLocalToUse(n) => pullNode[CUContainer](n)
    case _ => 
  }

  override def transform(n:N):Unit =  {
    pull(n)
    super.transform(n)
  }

}

class DeadCodeElimination(implicit design:PIR) extends Transformer with prism.traversal.HiearchicalTopologicalTraversal {

  override lazy val stream = newStream(s"DeadCodeElimination.log")

  override def shouldRun = true

  override def reset = super[Transformer].reset

  override def initPass = super[HiearchicalTopologicalTraversal].reset

  def depFunc(n:N):List[N] = visitOut(n)

  override def runPass =  {
    traverseNode(design.newTop)
  }

  override def check = {
    val containers = collectDown[CUContainer](design.newTop)
    val unvisited = containers.filterNot(isVisited)
    assert(unvisited.isEmpty, s"not all containers are visited! ${unvisited}")
  }

  override def isDepFree(n:N) = n match {
    case n:ArgFringe => true // heuristic breaking loop
    case _ => super.isDepFree(n)
  } 

  override def traverseChildren(n:N, prev:T):T = emitBlock(s"traverseChildren(${qdef(n)})") {
    dprintln(s"visitChild=${visitChild(n)}")
    super.traverseChildren(n, prev)
  }

  def isUseFree(n:N) = n match {
    case n:ArgOut => false
    case n:StreamIn => false
    case n:StreamOut => false
    case n:Memory => n.depeds.filterNot { case n:LocalStore => true; case _ => false }.isEmpty
    case n:Counter => false
    case n:Top => false
    case n:Container => n.children.isEmpty 
    case Def(n, LocalStore(mems, addrs, data)) => mems.isEmpty
    case n:MemStore => n.mem == null
    case n => n.depeds.isEmpty
  }

  override def transform(n:N):Unit = {
    removeUnusedIOs(n)
    if (isUseFree(n)) {
      dprintln(s"eliminate ${qdef(n)} from parent=${n.parent} ${isUseFree(n)}")
      val deps = n.deps
      n.ios.foreach(_.disconnect)
      n.parent.foreach { parent =>
        parent.removeChild(n)
      }
      deps.foreach(transform)
    }
    super.transform(n)
  }

}

class ControlPropogation(implicit design:PIR) extends Traversal with prism.traversal.HiearchicalTopologicalTraversal {

  type T = Controller

  override lazy val stream = newStream(s"ControlPropogation.log")

  override def shouldRun = true

  override def reset = super[Traversal].reset

  override def initPass = {
    super[HiearchicalTopologicalTraversal].reset
    controllerTraversal.reset
  }

  def depFunc(n:N):List[N] = visitOut(n) 

  override def runPass =  {
    controllerTraversal.traverseNode(design.newTop.topController, ())
    traverseNode(design.newTop, null)
  }

  override def check = {
    val contexts = collectDown[ComputeContext](design.newTop)
    contexts.foreach { context =>
      assert(context.ctrl != null, s"$context's controller is not set")
    }
  }

  override def isDepFree(n:N) = n match {
    case n:ArgFringe => true // heuristic breaking loop
    case _ => super.isDepFree(n)
  } 

  def resetController(n:Node, ctrl:Controller):Unit = n match {
    case n:ComputeContext => 
      dprintln(s"setting ${qtype(n)}.ctrl=$ctrl")
      n.setControl(ctrl)
      n.deps.foreach(d => resetController(d, ctrl))
    case n =>
  }

  val controllerTraversal = new ControllerTraversal {
    type T = Unit
    override def visitNode(n:N, prev:T):T = {
      n match {
        case n:LoopController => resetController(n.cchain, n)
        case _ =>
      }
      super.visitNode(n, prev)
    }
  }

  override def visitNode(n:N, prev:Controller):T = {
    dprintln(s"visitNode(${qtype(n)}, currentContext=$prev), isDepFree=${isDepFree(n)}")
    n match {
      case n:ComputeContext =>
        if (n.ctrl == null) {
          assert(prev != null)
          n.setControl(prev)
          dprintln(s"setting ${qtype(n)}.ctrl=$prev")
          super.visitNode(n, prev)
        } else {
          super.visitNode(n, n.ctrl)
        }
      case n => super.visitNode(n, null) 
    }
  }

}

class AccessLowering(implicit design:PIR) extends Transformer with prism.traversal.HiearchicalTopologicalTraversal {
  override def shouldRun = true

  override lazy val stream = newStream(s"AccessLowering.log")

  override def reset = super[Transformer].reset

  override def initPass = {
    super[Transformer].initPass
    super[HiearchicalTopologicalTraversal].reset
  }

  def depFunc(n:N):List[N] = visitOut(n) 

  override def runPass =  {
    traverseNode(design.newTop)
  }

  def retime(x:Def, cu:GlobalContainer, ctrl:Controller) = {
    x match {
      case x:Const[_] => x
      case Def(n:IterDef, IterDef(counter, offset)) =>
        mirror[IterDef](n, cu)
      case x =>
        val fifo = RetimingFIFO().setParent(cu)
        MemStore(fifo, None, x).setControl(x.ctrl).setParent(cu)
        MemLoad(fifo, None).setControl(ctrl).setParent(cu)
    }
  }

  override def transform(n:N):Unit = {
    n match {
      case Def(n:LoadDef, LoadDef(mems, addrs)) =>
        emitBlock(s"Lowering ${qdef(n)}") {
          val accessCU = collectUp[GlobalContainer](n).head
          mems.foreach { mem =>

            // Remote read address calculation
            val memCU = collectUp[GlobalContainer](mem).head
            val maddrs = addrs.map { addrs => addrs.map { addr => mirror[Def](addr, memCU) } }
            val access = MemLoad(mem, maddrs).setParent(memCU).setControl(n.ctrl).name(n.name)
            val newOut = if (memCU == accessCU) {
              access.out
            } else { // Remote memory, add Retiming FIFO
              retime(access, accessCU, n.ctrl).out
            }
            n.depeds.foreach { deped =>
              dprintln(s"$n.deped=$deped")
              swapConnection(deped, n.out, newOut)
            }
          }
        }
      case Def(n:StoreDef, StoreDef(mems, addrs, data)) =>
        emitBlock(s"Lowering ${qdef(n)}") {
          val accessCU = collectUp[GlobalContainer](n).head
          mems.foreach { mem =>
            // Local write address calculation
            val memCU = collectUp[GlobalContainer](mem).head
            val (saddrs, sdata) = if (memCU!=accessCU && addrs.nonEmpty) {
              val dataLoad = retime(data, memCU, n.ctrl)
              val addrLoad = addrs.map { addrs =>
                addrs.map { addr => retime(addr, memCU, n.ctrl) }
              }
              (addrLoad, dataLoad)
            } else {
              (addrs, data)
            }
            disconnect(mem, n)
            MemStore(mem, saddrs, sdata).setParent(memCU).setControl(n.ctrl).name(n.name)
          }
        }
      case n => super.transform(n)
    }
  }
}

class CUStatistics(implicit design:PIR) extends Pass {

  override lazy val stream = newStream(s"CUStatistics.log")

  type T = Unit

  def shouldRun = true

  override def dprintln(s:Any):Unit = {
    info(s"$s")
    super.dprintln(s)
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
    dprintln(s"number of cus=${cus.size}")
    cuMap.foreach { case (key, cus) =>
      dprintln(s"")
      dprintln(s"number of $key = ${cus.size}")
      dprintln(s"$key = ${cus}")
      val fanIns = cus.map { cu => cu.ins.size }
      dprintln(s"max fanIn of $key = ${fanIns.max}")
      dprintln(s"average fanIn of $key = ${fanIns.sum.toFloat / fanIns.size}")
      val fanOuts = cus.map { cu => cu.outs.size }
      dprintln(s"max fanOut of $key = ${fanOuts.max}")
      dprintln(s"average fanOut of $key = ${fanOuts.sum.toFloat / fanOuts.size}")
    }
  }

}

class IRCheck(implicit design:PIR) extends Pass {

  override lazy val stream = newStream(s"IRCheck.log")

  type T = Unit

  def shouldRun = true

  def warn(s:Any) = {
    dprintln(s"$s")
    pirc.util.warn(s)
  }

  def err(s:Any) = {
    dprintln(s"$s")
    pirc.util.err(s)
  }

  def assert(predicate:Boolean, info:Any) = {
    dprintln(s"$info")
    pirc.util.assert(predicate, info)
  }

  def checkCUIO(cu:GlobalContainer) = {
    cu.ins.foreach { in =>
      in.src match {
        case node:LocalStore =>
        case node =>
          dprintln(s"$cu's global input $in.src = $node")
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
        case mem if mem.readers.isEmpty =>
          warn(s"$mem in $cu does not have reader")
        case _ =>
      }
    }
  }

  def checkDefControl(cu:GlobalContainer) = {
    val stageDef = collectDown[StageDef](cu)
    stageDef.foreach { stageDef =>
      assert(stageDef.ctrl != null, 
        s"${qtype(stageDef)} in $cu.ctrl = null")
      assert(stageDef.ctrl.level == InnerControl, 
        s"${qtype(stageDef)}.ctrl.level = ${stageDef.ctrl.level}. stageDef.ctrl=${stageDef.ctrl}")
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
