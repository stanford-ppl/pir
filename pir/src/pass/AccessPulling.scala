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
      val m = mirror(dep, Some(container))
      swapConnection(deped, from=dep.out, to=m.out)
    }
  }

  override def visitNode(n:N):Unit = {
    dbgs(s"visitNode ${qdef(n)}")
    n match {
      case n:Def =>
        val localDeps = n.deps.flatMap {
          case n:Memory if isRemoteMem(n) => None
          case n:LocalStore => None
          case n:LocalReset => None
          case n:ArgInDef => None
          case n:TokenInDef => None
          case n => Some(n)
        }
        dbg(s"${n}.parent=${n.parent}")
        val cu = globalOf(n).get 
        localDeps.foreach { dep =>
          if (!cu.isAncestorOf(dep)) pullNode(dep, n, cu)
        }
      case _ =>
    }
    super.visitNode(n)
  }

}

