package pir.newnode

import pir._

import pirc._
import pirc.enums._
import pirc.util._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._

trait Traversal extends pirc.node.Traversal {
  type N = Node 
  type P = Container

  def qdef(n:N) = s"$n = ${n.productName}"
  def qtype(n:N) = n.name.map { name => s"${n.className}[$name]" }.getOrElse(s"$n")
}

trait Collector extends Traversal with Logger {

  type N<:Node

  private def newTraversal[M<:N:ClassTag](vf:N => List[N]) = new pirc.node.BFSTraversal {
    type T = (Iterable[M], Int)
    type N = Collector.this.N
    override def visitNode(n:N, prev:T):T = {
      //emitBlock(s"collectUp($n)") {
        val (prevRes, depth) = super.visitNode(n, prev)
        n match {
          case n:M if depth > 0 => (prevRes ++ List(n), depth - 1)
          case _ if depth == 0 => (prevRes, 0)
          case _ => traverse(n, (prevRes, depth - 1))
        }
      //}
    }
    def visitFunc(n:N):List[N] = vf(n)
  }
 
  def collectUp[M<:N:ClassTag](n:N, depth:Int=10):Iterable[M] = {
    //emitBlock(s"collectUp($n)") {
      newTraversal(visitUp _).traverse(n, (Nil, depth))._1
    //}
  }

  def collectDown[M<:N:ClassTag](n:N, depth:Int=10):Iterable[M] = {
    //emitBlock(s"collectUp($n)") {
      newTraversal(visitDown _).traverse(n, (Nil, depth))._1
    //}
  }

  def collectOut[M<:N:ClassTag](n:N, depth:Int=10):Iterable[M] = {
    //emitBlock(s"collectUp($n)") {
      newTraversal(visitOut _).traverse(n, (Nil, depth))._1
    //}
  }
}


abstract class CUInsertion(implicit design:PIR) extends pir.pass.Pass with pirc.node.GraphTransformer with pirc.node.ChildLastTraversal with Traversal {

  override type N = Node
  override type P = Container

  override def shouldRun = true

  override def reset = {
    super[Pass].reset
    super[ChildLastTraversal].reset
  }

  addPass {
    visitNode(design.newTop)
  }

  def insertCU(n:N) = {
    val parent = n.parent.get
    parent.removeChild(n)
    parent.addChild(CUContainer(n))
  }

  override def transform(n:N):Unit = n match {
    case n:Controller => insertCU(n)
    case n:SRAM => insertCU(n)
    case _ => super.transform(n)
  }

}

abstract class AccessPulling(implicit design:PIR) extends pir.pass.Pass with pirc.node.GraphTransformer with pirc.node.ChildLastTraversal with Traversal with Collector with Logger {

  override lazy val stream = newStream(s"AccessPulling.log")

  override type N = Node
  override type P = Container
  type D = PIR

  override def shouldRun = true

  override def reset = {
    super[Pass].reset
    super[ChildLastTraversal].reset
  }

  addPass {
    new pir.pass.GlobalIRDotCodegen(s"usepull1.dot") {}.run
    transform(design.newTop)
    new pir.pass.GlobalIRDotCodegen(s"usepull2.dot") {}.run
    reset
    transform(design.newTop)
    new pir.pass.GlobalIRDotCodegen(s"usepull3.dot") {}.run
  }

  def pullNode[C<:N with Container:ClassTag](n:N):Unit = emitBlock(s"pullNode($n)") {
    val output = n match {
      case n:Def => n.out
      case n:Memory => n.out
    }
    val containerMaps = output.connected.flatMap { in =>
      collectUp[C](in.src.asInstanceOf[N]).headOption.map { c => (c, in) }
    }.groupBy { _._1 }
    dprintln(s"containerMaps=$containerMaps")
    if (containerMaps.size==1) {
      val container = containerMaps.keys.head
      dprintln(s"swapParent $n from ${n.parent} to $container")
      swapParent(n, container)
    }
    //if (containerMaps.size >= 1) {
      //val parent = n.parent.get
      //parent.removeChild(n)
      //val nodes = (n, List(n)) :: List.fill(containers.size - 1) { mirror(n) }
      //val container = containers.head
      //containers.zip(nodes).foreach { case (c, (m, ms)) => 
        //ms.foreach { m =>
          //container.addChild(m)
        //}
      //}
    //}
  }

  override def mirror[T<:N](n:T)(implicit ct:ClassTag[N], design:D):(T, List[N]) = {
    val (m, ms) = super.mirror(n)
    n.name.foreach { name => m.name(name) }
    (m, ms)
  }

  override def transform(n:N):Unit = n match {
    case n:Const[_] => pullNode[Controller](n)
    case n:DummyDef => pullNode[Controller](n)
    case n:IterDef => pullNode[Controller](n)
    case n:LoadDef => pullNode[Controller](n)
    case n:Reg => pullNode[CUContainer](n)
    case _ => super.transform(n)
  }

}

abstract class DeadCodeElimination(implicit design:PIR) extends pir.pass.Pass with pirc.node.GraphTransformer with pirc.node.HiearchicalTopologicalTraversal with Traversal with Logger with Collector {

  override lazy val stream = newStream(s"DeadCodeElimination.log")

  override type N = Node
  override type P = Container
  override type T = Unit

  override def shouldRun = true

  override def reset = {
    super[Pass].reset
    super[HiearchicalTopologicalTraversal].reset
  }

  def depFunc(n:N):List[N] = visitOut(n)

  addPass {
    visitNode(design.newTop)
    val containers = collectDown[CUContainer](design.newTop)
    val unvisited = containers.filterNot(isVisited)
    assert(unvisited.isEmpty, s"not all containers are visited! ${unvisited}")
  }

  override def isDepFree(n:N) = n match {
    case n:CUContainer if n.isArgContainer => true // heuristic breaking loop
    case _ => super.isDepFree(n)
  } 

  override def traverseChildren(n:N, prev:T):T = emitBlock(s"traverseChildren(${qdef(n)})") {
    dprintln(s"visitChild=${visitChild(n)}")
    super.traverseChildren(n, prev)
  }

  override def transform(n:N):Unit = {
    n match {
      case n:Def =>
        if (n.depeds.isEmpty) {
          dprintln(s"eliminate ${qdef(n)} from parent=${n.parent}")
          val deps = n.deps
          n.ins.foreach(_.disconnect)
          n.parent.foreach { parent =>
            parent.removeChild(n)
          }
          deps.foreach(transform)
        }
      case n => super.transform(n)
    }
  }

}

