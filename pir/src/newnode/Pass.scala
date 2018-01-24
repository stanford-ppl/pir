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

abstract class Transformer(implicit design:PIR) extends Traversal with prism.traversal.GraphTransformer {
  override def mirrorX(n:Any, mapping:Map[Any,Any]=Map.empty)(implicit design:D):Map[Any,Any] = {
    if (mapping.contains(n)) return mapping
    val mp = super.mirrorX(n, mapping)
    val m = mp(n)
    (n,m) match {
      case (n:N,m:N) if n!=m => n.name.foreach { name => m.name(name) }
      case _ =>
    }
    (n, m) match {
      case (n:ComputeContext, m:ComputeContext) if n != m => m.controller = n.controller
      case _ =>
    }
    mp
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
    case n:ComputeContext => swapParent(n, cuMap(n.controller))
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
    val containerMaps = n.depeds.flatMap { deped =>
      collectUp[C](deped).headOption.map { c => (c, deped) }
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
        val (m, ms) = mirror(n)
        ms.filter{_.parent.isEmpty}.foreach { m => container.addChild(m) }
        dprintln(s"${qtype(container)}.children = ${container.children.map(qtype)}")
        dprintln(s"${qtype(container)} add ${ms.map(qtype)}")
        depeds.foreach { deped =>
          swapConnection(deped, from=n, to=m)
          dprintln(s"swapConnection(deped=${qtype(deped)}, from=${qtype(n)}, to=${qtype(m)})")
        }
      }
    }

  }

  override def mirrorX(n:Any, mapping:Map[Any,Any]=Map.empty)(implicit design:D):Map[Any,Any] = {
    if (mapping.contains(n)) return mapping
    var mp = n match {
      case n@(_:SRAM | _:StreamIn) => mapping + (n -> n)
      case n => super.mirrorX(n, mapping)
    }
    val m = mp(n)
    (n,m) match {
      case (n:N,m:N) => dprintln(s"mirror(${qtype(n)}) = ${qtype(m)}")
      case _ =>
    }
    (n, m) match {
      case (n:Memory, m:Memory) => m.in.connect(n.in.from)
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

  def pull(n:N) = n match {
    case n:Const[_] => pullNode[CUContainer](n)
    case n:DummyDef => pullNode[CUContainer](n)
    case n:IterDef => pullNode[CUContainer](n)
    case n:LoadDef => pullNode[CUContainer](n)
    case n:Reg => pullNode[CUContainer](n)
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
      assert(context.controller != null, s"$context's controller is not set")
    }
  }

  override def isDepFree(n:N) = n match {
    case n:ArgContainer => true // heuristic breaking loop
    case _ => super.isDepFree(n)
  } 

  def resetController(n:Node, controller:Controller):Unit = n match {
    case n:ComputeContext => 
      dprintln(s"setting ${qtype(n)}.controller=$controller")
      n.controller = controller
      n.deps.foreach(d => resetController(d, controller))
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
        if (n.controller == null) {
          assert(prev != null)
          n.controller = prev
          dprintln(s"setting ${qtype(n)}.controller=$prev")
          super.visitNode(n, prev)
        } else {
          super.visitNode(n, n.controller)
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

  override def transform(n:N):Unit = {
    //n match {
      //case Def(LoadDef(mems, addrs)) =>
        //emitBlock(s"Lowering $n") {
          //mems.foreach { mem =>
            //dprintln(s"$mem")
            //val cu = collectUp[GlobalContainer](mem).head
            //val maddrs = addrs.map { addrs =>
              //addrs.map { addr =>
                //val (m, ms) = mirror[Def](addr)
                //ms.foreach(cu.addChild)
                //m
              //}
            //}
            //val load = MemLoad(mems.head, maddrs)
            //cu.addChild(load)
            //n.depeds.foreach { deped =>
              //dprintln(s"$n.deped=$deped")
              //swapConnection(deped, n.asInstanceOf[LoadDef].out, load.out)
            //}
          //}
        //}
      //case Def(StoreDef(mems, addrs, data)) =>
      //case n => super.transform(n)
    //}
  }
}
