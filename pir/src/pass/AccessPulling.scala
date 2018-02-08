package pir.pass

import pir._
import pir.node._

import pirc._

import prism.traversal._

import scala.collection.mutable
import scala.reflect._

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

