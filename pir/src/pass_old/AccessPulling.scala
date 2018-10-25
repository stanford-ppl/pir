package pir
package pass

import pir.node._

class AccessPulling(implicit compiler:PIR) extends PIRTransformer with DFSBottomUpTopologicalTraversal with UnitTraversal {

  val forward = false

  override def finPass = {
    // Checking for escaped nodes
    (compiler.top.collectDown[Primitive]()).foreach { n =>
      assert(within[GlobalContainer](n), s"$n is not contained by a CU")
    }
    super.finPass
  }

  def pullNode(dep:A, deped:A, container:GlobalContainer) = dbgblk(s"pullNode(${qtype(dep)}, ${qtype(deped)}, ${qtype(container)})") {
    dbg(s"dep.depeds=${dep.depeds}")
    val depedContainers = dep.depeds.flatMap { deped => globalOf(deped) }

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
    } else { //Multiple consumer or node cannot be moved, mirror new node into destination consumer containers and reconnect 
      val m = withParent(container) { mirror(dep) }
      swapConnection(deped, from=dep.out, to=m.out)
    }
  }

  override def visitNode(n:N):Unit = {
    dbgs(s"visitNode ${qdef(n)}")
    n match {
      case n:Memory =>
      case n:Primitive =>
        val portableDeps = n.deps.flatMap {
          case dep:Memory if isRemoteMem(dep) => None
          case dep:LocalStore => None
          case dep:LocalReset => None
          case dep:ArgInDef => None
          case dep:TokenInDef => None
          case dep => Some(dep)
        }
        dbg(s"${n}.parent=${n.parent}")
        val cu = globalOf(n).get 
        portableDeps.foreach { dep =>
          if (!cu.isAncestorOf(dep)) pullNode(dep, n, cu)
        }
      case _ =>
    }
    super.visitNode(n)
  }

}

