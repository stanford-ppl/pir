package pir.graph.mapping
import pir.Design
import pir.Config
import pir.graph.{ComputeUnit => CU}
import pir.plasticine.graph.{ComputeUnit => PCU}
import pir.plasticine.graph.{Port => PPort}
import pir.graph.mapping.CUMapping.PrimMapping
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.immutable.Map

case class ScalarMapping(cu:CU, pcu:PCU, cuMap:Map[CU, PrimMapping])(implicit val design: Design) extends Mapping {

  type N = Int 
  type R = PPort
  type V = PPort

  lazy private val arch = design.arch
  lazy private val top = design.top
  lazy private val allNodes = design.allNodes

  def mapScalarIns(n:N, p:R, map:Map[N, V]) = {
    map + (n -> p)
  }
  def mapScalarOuts(n:N, p:R, map:Map[N, V]) = {
    map + (n -> p)
  }

  val sin = cu.pipeline.scalarIns.toList
  val psin = pcu.sinPorts
  val sout = cu.pipeline.scalarOuts.toList
  val psout = pcu.soutPorts
  override val mapping = if (sin.size > psin.size) {
    throw OutOfScalarIns(pcu)
  } else if (sout.size > psout.size) {
    throw OutOfScalarOuts(pcu)
  } else {
    val (isuc, imap) = simAneal(psin, sin, HashMap[N, V](), List(mapScalarIns _))
    val (osuc, omap) = simAneal(psout, sout, imap, List(mapScalarOuts _))
    omap
  }

  import PIRMapping._
  override def printMap = {
    emitBS("scalarMap")
    mapping.foreach{ case (k,v) =>
      emitln(s"($k -> $v)")
    }
    emitBE
  }

}
