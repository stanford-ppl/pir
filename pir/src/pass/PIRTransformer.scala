package pir.pass

import pir._
import pir.node._

import prism._
import prism.util._

import prism.traversal._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max

abstract class PIRTransformer(implicit compiler:PIR) extends PIRPass with PIRWorld with GraphTransformer {
  import pirmeta._

  override def reset = {
    super.reset
    mirrorMapping.clear
  }

  override def mirrorX[T](n:T, mapping:mutable.Map[Any,Any]=mutable.Map.empty)(implicit design:Design):T = {
    implicit val pirdesign = design.asInstanceOf[PIRDesign]

    if (mapping.contains(n)) return mapping(n).asInstanceOf[T]
    // Nodes do not mirror
    
    n match {
      case n:PIRNode =>
        n match {
          case Def(n,ValidGlobalInput(globalOutput)) => mapping += globalOutput -> globalOutput
          case Def(n,ReadyValidGlobalInput(globalOutput, ready)) => mapping += globalOutput -> globalOutput
          case n:Memory if isRemoteMem(n) => mapping += (n -> n)
          case n => 
        }
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
  case class PresetRule(node:Any, map:MetadataMap, v:Any) extends MirrorRule {
    def isDefinedAt(n:Any):Boolean = n == node
    def mirror(n:Any, m:Any) = {
      pirmeta.mirrorExcept(n,m,None,List(map))
      map.asK(m).foreach { m =>
        map.update(m, v.asInstanceOf[map.V])
      }
    }
  }

  val mirrorMapping = mutable.Map[Container, mutable.Map[Any,Any]]()

  def mirrored[T<:N](
    node:T, 
    container:Option[Container]=None, 
    init:mutable.Map[Any,Any]=mutable.Map.empty,
    mirrorRule:MirrorRule = NoneMatchRule
  ):(T, Set[N]) = {
    val mapping = container.fold {
      mutable.Map[Any,Any]()
    } { container =>
      mirrorMapping.getOrElseUpdate(container, mutable.Map[Any,Any]())
    }
    mapping ++= init
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
    init ++= mapping
    (m, newNodes)
  }

  def mirror[T<:N](
    node:T, 
    container:Option[Container]=None, 
    init:mutable.Map[Any,Any]=mutable.Map.empty,
    mirrorRule:MirrorRule = NoneMatchRule
  ):T = {
    mirrored(node, container, init, mirrorRule)._1
  }

  def retimerOf(x:Def, cu:GlobalContainer) = {
    collectDown[RetimingFIFO](cu).filter {
      case WithWriter(Def(w,LocalStore(mem, _, `x`))) => true
      case _ => false
    }.headOption.map{ fifo => fifo.readers.head }
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

  def swapNode[T<:Primitive](from:Primitive, to:T, at:Option[List[Primitive]]=None, excludes:List[Primitive]=Nil):T = {
    if (from == to) return to
    pirmeta.mirror(from, to)
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
}

