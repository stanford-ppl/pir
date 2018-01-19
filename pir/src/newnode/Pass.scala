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
  type N = Node 
  type P = Container
  type A = Module
  type D = PIR

  override def reset = super[Pass].reset

  override def initPass = super[Traversal].reset

  def qdef(n:N) = s"${n.name.getOrElse(n.toString)} = ${n.productName}"
  def qtype(n:N) = n.name.map { name => s"${n.className}${n.id}[$name]" }.getOrElse(s"$n")

}

abstract class Transformer(implicit design:PIR) extends Traversal with prism.traversal.GraphTransformer

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

  addPass {
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

  addPass {
    traverseNode(design.newTop)
  }

  def pullNode[C<:N with Container:ClassTag](n:Module):Unit = emitBlock(s"pullNode(${qtype(n)})") {
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
        ms.foreach { m => container.addChild(m) }
        dprintln(s"${qtype(container)}.children = ${container.children.map(qtype)}")
        dprintln(s"${qtype(container)} add ${ms.map(qtype)}")
        depeds.foreach { deped =>
          swapConnection(deped, from=n, to=m)
          dprintln(s"swapConnection(deped=${qtype(deped)}, from=${qtype(n)}, to=${qtype(m)})")
        }
      }
    }

  }

  override def mirrorArg(n:N, arg:N)(implicit ct:ClassTag[N], design:D):(N, List[N]) = (n, arg) match {
    case (n:Def, arg:Def) => mirror(arg)
    case (n, arg) => (arg, Nil)
  }

  override def mirror[T<:N](n:T)(implicit ct:ClassTag[N], design:D):(T, List[N]) = {
    val (m, ms) = super.mirror(n)
    n.name.foreach { name => m.name(name) }
    (m, n) match {
      case (m:Memory, n:Memory) =>
        m.in.connect(n.in.from)
      case _ =>
    }
    dprintln(s"mirror(${qtype(n)}) = ${qtype(m)}")
    (m, ms)
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

  addPass(runCount=2) {
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

  addPass {
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
