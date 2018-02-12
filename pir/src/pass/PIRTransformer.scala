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
      pirmeta.mirror(n,m)
      dbg(s"${quote(n)} -> ${quote(m)}")
      (n, m) match {
        case (n:Memory, m:Memory) => 
          val writers = n.writers.map { 
            case Def(w,LocalStore(mems, addrs, data)) => 
              // prevent mirroring of writer
              mp += w -> w
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

  def mirrorX(n:Any)(implicit design:D):Map[Any,Any] = mirrorX(n, Map[Any,Any]())

  def mirrorX(n:Any, container:Container, init:Map[Any,Any])(implicit design:D):Map[Any,Any] = {
    val mapping = mirrorX(n, init)
    val newNodes = (mapping.values.toSet diff mapping.keys.toSet).collect { case n:N => n}.filter(_.parent.isEmpty)
    newNodes.foreach { m => 
      m.setParent(container)
      dbg(s"${qtype(container)} add ${qtype(m)}")
    }
    mapping
  }

  def mirrorX(n:Any, container:Container)(implicit design:D):Map[Any,Any] =
    mirrorX(n, container, Map[Any,Any]())


  def mirror[T<:N](n:T, container:Container, init:Map[Any,Any])(implicit design:D):T = {
    val mapping = mirrorX(n, container, init)
    mapping(n).asInstanceOf[T]
  }

  def mirror[T<:N](n:T, container:Container)(implicit design:D):T = 
    mirror(n, container, Map[Any, Any]())
}

