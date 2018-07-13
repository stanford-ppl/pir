package pir
package pass

import pir.node._
import scala.collection.mutable

abstract class PIRTransformer(implicit compiler:PIR) extends PIRPass with PIRWorld with prism.traversal.GraphTransformer {
  import pirmeta._

  override def mirrorX[T](x:T, mapping:mutable.Map[N,N]=mutable.Map.empty)(implicit design:Design):T = {
    x match {
      case x:PIRNode =>
        (getOrElseUpdate(mapping, x) { dbgblk(s"mirrorX(${quote(x)})") {
          val m = x match {
            case n:GlobalInput => 
              goutOf(n).foreach { gout => mapping += gout -> gout }
              super.mirrorX(x, mapping)
            case n:Memory if isRemoteMem(n) => 
              mapping += (n -> n)
              super.mirrorX(x, mapping)
            case n:Memory =>
              val m = super.mirrorX(x, mapping)
              val writers = writersOf(n).map { 
                case Def(w,LocalStore(mems, addrs, data)) => 
                  // prevent mirroring of addrs and data
                  addrs.foreach { 
                    _.foreach { addr => mapping += addr -> addr }
                  }
                  mapping += data -> data
                  w
              }
              dbg(s"writers of $n = ${writers}")
              writers.foreach { writer => mirrorX(writer, mapping) }
              m
            case n:Counter =>
              val m = super.mirrorX(x, mapping)
              n.collectUp[CounterChain]().foreach { cc => mirrorX(cc, mapping) }
              m
            case n:CounterChain =>
              n.counters.foreach { ctr => mirrorX(ctr, mapping) }
              super.mirrorX(x, mapping)
            case n => super.mirrorX(x, mapping)
          }
          originOf(m) = x
          dbg(s"mirror ${qdef(x)} to ${qdef(m)}")
          m
        }}).asInstanceOf[T]
      case x => super.mirrorX(x, mapping)
    }
  }

  trait MirrorRule {
    def isDefinedAt(n:Any):Boolean
    def mirror(n:Any, m:Any)
  }
  case class NodeMatchRule(node:Any, lambda:(Any, Any) => Unit) extends MirrorRule {
    def isDefinedAt(n:Any):Boolean = n == node
    def mirror(n:Any, m:Any) = lambda(n,m)
  }
  case object NoneMatchRule extends MirrorRule {
    def isDefinedAt(n:Any):Boolean = false 
    def mirror(n:Any, m:Any) = {} 
  }
  case class PresetRule(node:Any, map:MetadataMap, v:Any) extends MirrorRule {
    def isDefinedAt(n:Any):Boolean = n == node
    def mirror(n:Any, m:Any) = {
      pirmeta.mirrorExcept(n,m,None,List(map))
      map.asK(m).foreach { m =>
        map.update(m, v.asInstanceOf[map.V])
      }
    }
  }

  def mirrored[T<:N](
    node:T, 
    container:Option[Container]=None, 
    init:Map[N,N]=Map.empty,
    mapping:mutable.Map[N,N]=mutable.Map.empty,
    mirrorRule:MirrorRule = NoneMatchRule
  ):(T, Set[N]) = {
    mapping ++= init
    container.foreach {
      _.descendents.foreach { 
        case m if originOf.contains(m) => mapping += originOf(m) -> m
        case n =>
      }
    }
    val origValues = mapping.values.toSet
    val m = mirrorX(node, mapping)
    // Moving newly created nodes into container
    val newNodes = mapping.values.toSet diff mapping.keys.toSet diff origValues
    container.foreach { container =>
      newNodes.foreach { m => 
        if (m.parent.isEmpty || m.parent.get.isInstanceOf[Top]) {
          m.setParent(container)
          dbg(s"${qtype(container)} add ${qtype(m)}")
        }
      }
    }
    // Mirror metadata
    mapping.foreach { 
      case (n, m) if origValues.contains(m) =>
      case (n, m) if mirrorRule.isDefinedAt(n) => mirrorRule.mirror(n, m)
      case (n, m) => pirmeta.mirror(n, m)
    }
    (m, newNodes)
  }

  def mirror[T<:N](
    node:T, 
    container:Option[Container]=None, 
    init:Map[N,N]=Map.empty,
    mapping:mutable.Map[N,N]=mutable.Map.empty,
    mirrorRule:MirrorRule = NoneMatchRule
  ):T = {
    mirrored(node, container, init, mapping, mirrorRule)._1
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
      x match {
        case x if xCU == cu => x
        case x:Const[_] => mirror(x, Some(cu))
        case Def(x:CounterIter, CounterIter(counter, offset)) => mirror(x, Some(cu))
        case x =>
          val fifo = RetimingFIFO().setParent(cu)
          val load = ReadMem(fifo).setParent(cu)
          val store = WriteMem(fifo, x).setParent(cu)
          dbg(s"add ${qtype(fifo)} in ${qtype(cu)}")
          dbg(s"add ${qtype(store)} in ${qtype(cu)}")
          dbg(s"add ${qtype(load)} in ${qtype(cu)}")
          pirmeta.mirror(x, load)
          pirmeta.mirror(x, store)
          load
      }
    }).asInstanceOf[Def]
  }

  def swapNode[T<:Primitive](from:Primitive, to:T, at:Option[List[Primitive]]=None, excludes:List[Primitive]=Nil, mirrorMeta:Boolean=true):T = {
    if (from == to) return to
    if (mirrorMeta) pirmeta.mirror(from, to)
    val depeds = at.getOrElse(from.depeds).filterNot{ d => excludes.contains(d) }
    dbg(s"swapNode: from:${qtype(from)} to:${qtype(to)} depeds=${depeds}")
    depeds.foreach { deped => 
      if (areConnected(deped, to)) {
        disconnect(deped, from)
      } else {
        swapConnection(deped, from.out, to.out)
      }
    }
    to
  }

  def lowerNode[T<:Primitive](from:Primitive, at:Option[List[Primitive]]=None)(to:T):T = {
    if (from == to) return to
    swapNode(from, to, at=at)
    from.parent.foreach { parent =>
      to.setParent(parent)
      dbg(s"add ${quote(to)} in ${quote(parent)}")
    }
    removeNode(from)
    to
  }

  def allocate[T<:PIRNode:ClassTag:TypeTag](
    container:Container, 
    filter:T => Boolean = (n:T) => true
  )(newNode: => T):T = {
    val nodes = container.collectDown[T]().filter(filter)
    assert(nodes.size <= 1, s"more than 1 node in container: $nodes")
    nodes.headOption.getOrElse { 
      val node = newNode.setParent(container)
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

