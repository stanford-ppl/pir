package pir.newnode

import pir._

import pirc._
import pirc.enums._
import pirc.util._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._

abstract class Traversal(implicit design:PIR) extends pir.pass.Pass with prism.traversal.Traversal with prism.traversal.GraphCollector with Logger {
  type N = Node with Product
  implicit val nct = classTag[N]
  type P = Container
  type A = Module
  type D = PIR

  override def reset = super[Pass].reset

  override def initPass = super[Traversal].reset

  def qdef(n:N) = s"${n.name.getOrElse(n.toString)} = ${n.productName}"
  def qtype(n:N) = n.name.map { name => s"${n.className}${n.id}[$name]" }.getOrElse(s"$n")

}

abstract class Transformer(implicit design:PIR) extends Traversal with prism.traversal.GraphTransformer { self:Logger =>
  override lazy val arch = design.arch // shadow implicit arch TODO

  override def mirrorX(n:Any, mapping:Map[Any,Any]=Map.empty)(implicit design:D):Map[Any,Any] = {
    if (mapping.contains(n)) return mapping
    var mp = n match {
      case n@(_:SRAM | _:StreamIn) => mapping + (n -> n)
      case n => super.mirrorX(n, mapping)
    }
    val m = mp(n)
    (n,m) match {
      case (n:N,m:N) => 
        dprintln(s"mirror(${qtype(n)}) = ${qtype(m)}")
        if (n!=m) n.name.foreach { name => m.name(name) }
      case _ =>
    }
    (n, m) match {
      case (n:ComputeContext, m:ComputeContext) if n != m => m.setControl(n.ctrl)
      case _ =>
    }
    (n, m) match {
      case (n:ArgIn, m:ArgIn) => 
        m.in.connect(n.in.from)
      case (n:Memory, m:Memory) => 
        n.depeds.collect { case writer:StoreDef => writer}.foldLeft(mp) { case (prev, writer) =>
          dprintln(s"mirroring $writer of $n")
          val Def(_,StoreDef(mems, addrs,data)) = writer
          // prevent mirroring of addrs and data
          var mp = prev
          mp += addrs -> addrs
          mp += data -> data
          mirrorX(writer, mp)
        }
      case (n:Counter, m:Counter) =>
        mp = collectUp[CounterChain](n).foldLeft(mp) { case (mp, cc) => 
          dprintln(s"mirroring $cc")
          mirrorX(cc, mp)
        }
        dprintln(s"$m.parent = ${m.parent}")
      case (n:CounterChain, m:CounterChain) =>
        dprintln(s"$m.counters=${m.counters.map { c => s"counter=$c"}}")
      case _ =>
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
  
  val cuMap = mutable.Map[Controller, CUContainer]()

  val controllerTraversal = new ControllerTraversal {
    type T = Unit
    override def visitNode(n:N, prev:T):T = {
      val cu = CUContainer().setParent(design.newTop)
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

  def insertCU(n:N) = {
    val parent = n.parent.get
    parent.removeChild(n)
    parent.addChild(CUContainer(n))
  }

  override def transform(n:N):Unit = n match {
    case n:SRAM => insertCU(n)
    case n:ComputeContext => swapParent(n, cuMap(n.ctrl))
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

  def pullNode[C<:N with Container:ClassTag](n:Module with Product):Unit = emitBlock(s"pullNode(${qtype(n)})") {
    val output = n match {
      case n:Def => n.out
      case n:Memory => n.out
    }
    dprintln(s"n.depeds=${n.depeds}")
    val containerMaps = n.depeds.flatMap { 
      case deped:StoreDef => None
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

  def pull(n:N) = n match {
    case n:Const[_] => pullNode[CUContainer](n)
    case n:DummyDef => pullNode[CUContainer](n)
    case n:IterDef => pullNode[CUContainer](n)
    case n:LoadDef => pullNode[CUContainer](n)
    case n:Reg => pullNode[CUContainer](n)
    case n:ArgIn => pullNode[CUContainer](n)
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
    val containers = collectDown[CUContainer](design.newTop)
    val unvisited = containers.filterNot(isVisited)
    assert(unvisited.isEmpty, s"not all containers are visited! ${unvisited}")
  }

  override def isDepFree(n:N) = n match {
    case n:ArgContainer => true // heuristic breaking loop
    case _ => super.isDepFree(n)
  } 

  override def traverseChildren(n:N, prev:T):T = emitBlock(s"traverseChildren(${qdef(n)})") {
    dprintln(s"visitChild=${visitChild(n)}")
    super.traverseChildren(n, prev)
  }

  def isUseFree(n:N) = n match {
    case n:ArgInFringe => false
    case n:ArgContainer => false
    case n:ArgOut => false
    case n:StreamIn => false
    case n:StreamOut => false
    case n:Counter => false
    case n:Top => false
    case n:Container => n.children.isEmpty 
    case Def(n:StoreDef, StoreDef(mems, addrs, data)) => mems.isEmpty
    case n:MemStore => false //TODO
    case n => n.depeds.isEmpty
  }

  override def transform(n:N):Unit = {
    removeUnusedIOs(n)
    if (isUseFree(n)) {
      dprintln(s"eliminate ${qdef(n)} from parent=${n.parent} ${isUseFree(n)}")
      val deps = n.deps
      n.ins.foreach(_.disconnect)
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
    val contexts = collectDown[ComputeContext](design.newTop)
    contexts.foreach { context =>
      assert(context.ctrl != null, s"$context's controller is not set")
    }
  }

  override def isDepFree(n:N) = n match {
    case n:ArgContainer => true // heuristic breaking loop
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
      resetController(n.cchain, n)
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
        fifo.in.connect(x.out)
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
            val access = MemLoad(mem, maddrs).setParent(memCU).setControl(n.ctrl)
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
            MemStore(mem, saddrs, sdata).setParent(memCU).setControl(n.ctrl)
          }
        }
      case n => super.transform(n)
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
