package pir
package pass

import pir.node._
import scala.collection.mutable

abstract class PIRTransformer(implicit compiler:PIR) extends PIRPass with PIRWorld with BuildEnvironment with prism.traversal.GraphTransformer {
  import pirmeta._

  /*
   * TODO: Problematic if mirroring from one parent to another, then the second parent still seeps
   * the origin mapping from the previous parent. 
   * Mirror if not in mapping. Adding origins to mapping within the containers
   * */
  override def mirror[T](x:T, mapping:mutable.Map[N,N])(implicit design:Design):T = {
    currentParent.foreach { p =>
      p.children.foreach { 
        case m:N if originOf.contains(m) => mapping += originOf(m) -> m
        case x =>
      }
    }
    x match {
      case x:N =>
        dbgblk(s"mirror(${originOf(x)}) in=$currentParent") { 
          super.mirror(originOf(x), mapping).asInstanceOf[T]
        }
      case _ => super.mirror(x, mapping)
    }
  }

  override def mirrorX(n:N, mapping:mutable.Map[N,N])(implicit design:Design):N = {
    n match {
      case n:GlobalInput => 
        goutOf(n).foreach { gout => mapping += gout -> gout }
        super.mirrorX(n, mapping)
      case n:Memory if isRemoteMem(n) => 
        mapping += (n -> n)
        super.mirrorX(n, mapping)
      case n:Memory =>
        val m = super.mirrorX(n, mapping).asInstanceOf[Memory]
        val inAccesses = inAccessesOf(n)
        dbg(s"inAccesses of $n = ${inAccesses}")
        inAccesses.foreach { inaccess => 
          mapping += (n -> m)
          val minaccess = mirror(inaccess, mapping)
          // If minaccess is not newly created, add new written memory
          if (!memsOf(minaccess).contains(m)) minaccess.asInstanceOf[LocalInAccess].writes(m)
        }
        m
      case Def(n,LocalStore(mems, addrs, data)) => 
        // prevent mirroring of addrs and data
        addrs.foreach { _.foreach { addr => mapping += addr -> addr } }
        mapping += data -> data
        super.mirrorX(n, mapping)
      case n:Counter =>
        mapping.get(originOf(cchainOf(n))).fold {
          val m = super.mirrorX(n, mapping)
          val mcc = mirror(cchainOf(n), mapping)
          m.unsetParent
          m.setParent(mcc)
          m
        } { mcc =>
          withParent(mcc.asInstanceOf[CounterChain]) {
            mirror(n, mapping) // should be already mirrored 
          }
        }
      case n:CounterChain =>
        n.counters.foreach { ctr => mirror(ctr, mapping) }
        super.mirrorX(n, mapping)
      case n => super.mirrorX(n, mapping)
    }
  }

  override def mirrorX(n:N, args:List[Any])(implicit design:Design):N = {
    val m = super.mirrorX(n, args)
    originOf(m) = originOf.getOrElse(n,n)
    dbg(s"mirror ${qdef(n)} to ${qdef(m)}")
    pirmeta.mirror(n,m)
    m
  }

  def mirrored[T<:N](
    node:T, 
    init:Map[N,N]=Map.empty,
    mapping:mutable.Map[N,N]=mutable.Map.empty
  ):(T, Set[N]) = {
    mapping ++= init
    val origValues = mapping.values.toSet
    val m = mirror[T](node, mapping)
    // Moving newly created nodes into container
    val newNodes = mapping.values.toSet diff mapping.keys.toSet diff origValues
    (m, newNodes)
  }

  def mirror[T<:N](
    node:T, 
    init:Map[N,N]=Map.empty,
    mapping:mutable.Map[N,N]=mutable.Map.empty
  ):T = {
    mirrored(node, init, mapping)._1
  }

  def retimerOf(x:Def, cu:GlobalContainer) = {
    cu.collectDown[RetimingFIFO]().filter {
      case WithWriter(Def(w,LocalStore(mem, _, `x`))) => true
      case _ => false
    }.headOption.map{ fifo => readersOf(fifo).head }
  }

  def retime(
    x:Def, 
    cu:GlobalContainer
  ):Def = {
    val xCU = globalOf(x).get 
    retimerOf(x, cu).getOrElse(dbgblk(s"retime($x,cu=$cu)") {
      withParent(cu) {
        x match {
          case x if xCU == cu => x
          case x:Const[_] => mirror(x)
          case Def(x:CounterIter, CounterIter(counter, offset)) => mirror(x)
          case x =>
            val fifo = RetimingFIFO()
            val load = ReadMem(fifo)
            val store = WriteMem(fifo, x)
            dbg(s"add ${qtype(fifo)} in ${qtype(cu)}")
            dbg(s"add ${qtype(store)} in ${qtype(cu)}")
            dbg(s"add ${qtype(load)} in ${qtype(cu)}")
            pirmeta.mirror(x, load)
            pirmeta.mirror(x, store)
            load
        }
      }
    }).asInstanceOf[Def]
  }

  def swapUsage[T<:Primitive](from:Primitive, to:T, at:Option[List[Primitive]]=None):T = {
    if (from == to) return to
    val depeds = at.getOrElse(from.depeds)
    dbg(s"swapUsage: from:${qtype(from)} to:${qtype(to)} depeds=${depeds}")
    depeds.foreach { deped => 
      if (areConnected(deped, to)) {
        disconnect(deped, from)
      } else {
        swapConnection(deped, from.out, to.out)
      }
    }
    to
  }

  def lowerNode[T<:Primitive](from:Primitive)(to:T):T = {
    if (from == to) return to
    swapUsage(from, to)
    from.parent.foreach { parent =>
      to.setParent(parent)
      dbg(s"add ${quote(to)} in ${quote(parent)}")
    }
    pirmeta.migrate(from, to, logger=Some(this))
    removeNode(from)
    to
  }

  def allocate[T<:PIRNode:ClassTag:TypeTag](
    filter:T => Boolean = (n:T) => true
  )(newNode: => T):T = {
    val container = currentParent.getOrElse(throw PIRException(s"allocation without container scope!"))
    val nodes = container.collectDown[T]().filter(filter)
    assert(nodes.size <= 1, s"more than 1 node in container: $nodes")
    nodes.headOption.getOrElse { 
      val node = withParent(container) { newNode }
      dbg(s"allocate[${implicitly[ClassTag[T]]}](container=$container) = ${quote(node)}")
      node
    }
  }

  override def disconnect(a:A, b:A) = {
    dbg(s"disconnect ${quote(a)} ${quote(b)}")
    super.disconnect(a,b)
  }

  override def swapParent(node:N, newParent:N):Unit = {
    dbg(s"swapParent($node, $newParent)")
    super.swapParent(node, newParent)
  }

  override def swapOutputs[A1<:A](node:A, from:A1, to:A1) = {
    dbg(s"swapOutputs($node, $from, $to)")
    super.swapOutputs(node, from, to)
  }

  override def removeNode(node:N) = {
    super.removeNode(node)
    pirmeta.removeAll(node)
    dbg(s"removeNode($node)")
  }

}

