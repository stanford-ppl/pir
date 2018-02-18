package pir.pass

import pir._
import pir.node._

import pirc._
import pirc.util._

import prism.traversal._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._

abstract class PIRTransformer(implicit design:PIR) extends PIRPass with PIRWorld with GraphTransformer {

  def quote(n:Any) = qtype(n)

  override def mirrorX(n:Any, mapping:Map[Any,Any])(implicit design:D):Map[Any,Any] = {
    if (mapping.contains(n)) return mapping
    var mp  = mapping
    // Nodes do not mirror
    mp = n match {
      case n@(_:SRAM | _:StreamIn | _:StreamOut) => mp + (n -> n)
      case n => mp
    }
    dbgblk(s"mirrorX(${quote(n)})") {
      mp = super.mirrorX(n, mp)
      val m = mp(n)
      dbg(s"${quote(n)} -> ${quote(m)}")
      (n, m) match {
        case (n:Memory, m:Memory) => 
          val writers = n.writers.map { 
            case Def(w,LocalStore(mems, addrs, data)) => 
              // prevent mirroring of addrs and data
              addrs.foreach { addr => mp += addr -> addr }
              mp += data -> data
              w
          }
          dbg(s"writers of $n = ${writers}")
          mp = writers.foldLeft(mp) { case (mp, writer) => mirrorX(writer, mp) }
        case (n:Counter, m:Counter) =>
          dbg(s"$m.parent = ${m.parent}")
          mp = collectUp[CounterChain](n).foldLeft(mp) { case (mp, cc) => mirrorX(cc, mp) }
        case (n:CounterChain, m:CounterChain) =>
          dbg(s"$m.counters=${m.counters.map { c => s"counter=$c"}}")
          mp = n.counters.foldLeft(mp) { case (mp, ctr) => mirrorX(ctr, mp) }
        case _ =>
      }
      mp(n)
    }
    mp
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

  def mirrorM(
    node:Any, 
    container:Option[Container]=None, 
    init:Map[Any,Any]=Map.empty,
    mirrorRule:MirrorRule = NoneMatchRule
  )(implicit design:D):Map[Any,Any] = {
    val mapping = mirrorX(node, init)
    // Moving newly created nodes into container
    val newNodes = (mapping.values.toSet diff mapping.keys.toSet).collect { case n:N => n}.filter(_.parent.isEmpty)
    container.foreach { container =>
      newNodes.foreach { m => 
        m.setParent(container)
        dbg(s"${qtype(container)} add ${qtype(m)}")
      }
    }
    // Mirror metadata
    mapping.foreach { 
      case (n, m) if mirrorRule.isDefinedAt(n) => mirrorRule.mirror(n, m)
      case (n, m) => pirmeta.mirror(n, m)
    }
    mapping
  }

  def mirror[T<:N](
    node:T, 
    container:Option[Container]=None, 
    init:Map[Any,Any] = Map.empty,
    mirrorRule:MirrorRule = NoneMatchRule
  )(implicit design:D):T = {
    val mapping = mirrorM(node, container, init, mirrorRule)
    mapping(node).asInstanceOf[T]
  }

  def retimeX(
    x:Def, 
    cu:GlobalContainer, 
    init:Map[Any,Any]=Map.empty
  ):Map[Any,Any] = {
    x match {
      case x:Const[_] => mirrorM(x, Some(cu), init)
      case Def(x:CounterIter, CounterIter(counter, offset)) => mirrorM(x, Some(cu), init)
      case x =>
        val xCU = globalOf(x).get 
        val fifo = RetimingFIFO().setParent(cu)
        val store = WriteMem(fifo, x).setParent(cu)
        val load = ReadMem(fifo).setParent(cu)
        dbg(s"add ${qtype(fifo)} in ${qtype(cu)}")
        dbg(s"add ${qtype(store)} in ${qtype(cu)}")
        dbg(s"add ${qtype(load)} in ${qtype(cu)}")
        pirmeta.mirror(x, store)
        pirmeta.mirror(x, load)
        init + (x -> load)
    }
  }

  def retime(
    x:Def, 
    cu:GlobalContainer,
    init:Map[Any, Any] = Map.empty
  ):Def = retimeX(x, cu, init)(x).asInstanceOf[Def]

  def swapNode[T<:Primitive](from:Primitive)(toFunc: => T):T = {
    from.deps.foreach { dep => disconnect(dep, from) }
    val to = toFunc
    pirmeta.mirror(from, to)
    from.depeds.foreach { deped => 
      if (areConnected(deped, to)) {
        disconnect(deped, from)
      } else {
        swapConnection(deped, from.out, to.out)
      }
    }
    removeNode(from)
    to
  }

}

