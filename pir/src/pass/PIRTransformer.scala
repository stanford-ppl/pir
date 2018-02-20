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

  override def mirrorX[T](n:T, mapping:mutable.Map[Any,Any]=mutable.Map.empty)(implicit design:D):T = {
    if (mapping.contains(n)) return mapping(n).asInstanceOf[T]
    // Nodes do not mirror
    n match {
      case n:Memory if isRemoteMem(n) => mapping += (n -> n)
      case n => 
    }
    dbgblk(s"mirrorX(${quote(n)})") {
      val m = super.mirrorX(n, mapping)
      dbg(s"${quote(n)} -> ${quote(m)}")
      (n, m) match {
        case (n:Memory, m:Memory) => 
          val writers = n.writers.map { 
            case Def(w,LocalStore(mems, addrs, data)) => 
              // prevent mirroring of addrs and data
              addrs.foreach { addr => mapping += addr -> addr }
              mapping += data -> data
              w
          }
          dbg(s"writers of $n = ${writers}")
          writers.foreach { writer => mirrorX(writer, mapping) }
        case (n:Counter, m:Counter) =>
          dbg(s"$m.parent = ${m.parent}")
          collectUp[CounterChain](n).foreach { cc => mirrorX(cc, mapping) }
        case (n:CounterChain, m:CounterChain) =>
          dbg(s"$m.counters=${m.counters.map { c => s"counter=$c"}}")
          n.counters.foreach { ctr => mirrorX(ctr, mapping) }
        case _ =>
      }
      m
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

  def mirror[T<:N](
    node:T, 
    container:Option[Container]=None, 
    mapping:mutable.Map[Any,Any]=mutable.Map.empty,
    mirrorRule:MirrorRule = NoneMatchRule
  )(implicit design:D):T = {
    val m = mirrorX(node, mapping)
    // Moving newly created nodes into container
    val newNodes = (mapping.values.toSet diff mapping.keys.toSet).collect { case n:N => n}.filter(_.parent.fold(true)(_.isInstanceOf[Top]))
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
    m
  }

  def retime(
    x:Def, 
    cu:GlobalContainer, 
    mapping:mutable.Map[Any,Any]=mutable.Map.empty
  ):Def = {
    val xCU = globalOf(x).get 
    val rx = x match {
      case x if xCU == cu => x
      case x:Const[_] => mirror(x, Some(cu), mapping)
      case Def(x:CounterIter, CounterIter(counter, offset)) => mirror(x, Some(cu), mapping)
      case x =>
        val fifo = RetimingFIFO().setParent(cu)
        val load = ReadMem(fifo).setParent(cu)
        val store = WriteMem(fifo, x).setParent(cu)
        dbg(s"add ${qtype(fifo)} in ${qtype(cu)}")
        dbg(s"add ${qtype(store)} in ${qtype(cu)}")
        dbg(s"add ${qtype(load)} in ${qtype(cu)}")
        pirmeta.mirror(x, store)
        mapping += (x -> load)
        load
    }
    mapping += x -> rx
    rx
  }

  def swapNode[T<:Primitive](from:Primitive, to:T, at:Option[List[Primitive]]=None, excludes:List[Primitive]=Nil):T = {
    if (from == to) return to
    dbg(s"swapNode: from:${qtype(from)} to:${qtype(to)}")
    pirmeta.mirror(from, to)
    at.getOrElse(from.depeds).foreach { 
      case deped if excludes.contains(deped) => 
      case deped => 
        if (areConnected(deped, to)) {
          disconnect(deped, from)
        } else {
          swapConnection(deped, from.out, to.out)
        }
    }
    to
  }

}

