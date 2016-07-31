package pir.graph.mapper
import pir._
import pir.graph._
import pir.graph.{ Controller => CL, ComputeUnit => CU, TileTransfer => TT, 
                  Input => I, VecOut => VO, 
                  SRAM,
                  Counter => Ctr,
                  ScalarIn => SI, ScalarOut => SO}
import pir.plasticine.graph.{ Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT, 
                            InBus => PIB, OutBus => POB,
                            Counter => PCtr, SRAM => PSRAM,
                            ScalarIn => PSI, ScalarOut => PSO }
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

case class PIRMap(clmap:CLMap, vimap:VIMap, smmap:SRAMMap, ctmap:CtrMap, simap:SIMap,
  somap:SOMap, slmap:SLMap, ibmap:IBMap) {

  def set(cp:CLMap  ):PIRMap = PIRMap(cp   , vimap, smmap, ctmap, simap, somap, slmap, ibmap)
  def set(cp:VIMap  ):PIRMap = PIRMap(clmap, cp   , smmap, ctmap, simap, somap, slmap, ibmap)
  def set(cp:SRAMMap):PIRMap = PIRMap(clmap, vimap, cp   , ctmap, simap, somap, slmap, ibmap)
  def set(cp:CtrMap ):PIRMap = PIRMap(clmap, vimap, smmap, cp   , simap, somap, slmap, ibmap)
  def set(cp:SIMap  ):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, cp   , somap, slmap, ibmap)
  def set(cp:SOMap  ):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, cp   , slmap, ibmap)
  def set(cp:SLMap  ):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, cp   , ibmap)
  def set(cp:IBMap  ):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, cp   )

  def setCL(k:CLMap.K  , v:CLMap.V  ):PIRMap = set(clmap + (k -> v))
  def setVI(k:VIMap.K  , v:VIMap.V  ):PIRMap = set(vimap + (k -> v))
  def setSM(k:SRAMMap.K, v:SRAMMap.V):PIRMap = set(smmap + (k -> v))
  def setCt(k:CtrMap.K , v:CtrMap.V ):PIRMap = set(ctmap + (k -> v))
  def setSI(k:SIMap.K  , v:SIMap.V  ):PIRMap = set(simap + (k -> v))
  def setSO(k:SOMap.K  , v:SOMap.V  ):PIRMap = set(somap + (k -> v))
  def setSL(k:SLMap.K  , v:SLMap.V  ):PIRMap = set(slmap + (k -> v))
  def setIB(k:IBMap.K  , v:IBMap.V  ):PIRMap = set(ibmap + (k -> v))

  def printMap(implicit p:Printer):Unit = {
    slmap.printMap
    ibmap.printMap
    clmap.map.foreach { case (cl, pcl) =>
      p.emitBlock( s"$cl -> $pcl" ) {
        if (!cl.isInstanceOf[TileTransfer]) { //TODO
          p.emitBlock(s"SIMap") {
            cl.sins.foreach { sin =>
              p.emitln(s"${sin} -> ${simap(sin)}")
            } 
          }
          p.emitBlock(s"SOMap") {
            cl.souts.foreach { sout =>
              p.emitln(s"${sout} -> ${somap(sout)}")
            } 
          }
        }
        p.emitBlock(s"VIMap") {
          (cl.sins ++ cl.vins).foreach { in =>
            p.emitln(s"${in} -> ${vimap(in)}")
          } 
        }
        cl match {
          case cu:CU =>
            p.emitBlock(s"SMMap") {
              cu.srams.foreach { sram =>
                p.emitln(s"${sram} -> ${smmap(sram)}")
              }
            }
            p.emitBlock(s"CCMap") {
              cu.cchains.foreach { cc =>
                p.emitBlock(s"${cc}") {
                  cc.counters.foreach { ct =>
                    p.emitln(s"${ct} -> ${ctmap(ct)}")
                  }
                }
              }
            }
          case _ =>
        }
      }
    }
  }
}
object PIRMap {
  def empty:PIRMap = PIRMap(CLMap.empty, VIMap.empty, SRAMMap.empty, CtrMap.empty, 
                  SIMap.empty, SOMap.empty, SLMap.empty, IBMap.empty)
}

trait PMap {
  type K
  type V
  type M = Map[K, V]
  val map:M
  def contains(k:K) = map.contains(k)
  def apply(k:K):V = map(k)
  val name:String = this.getClass().getInterfaces().head.getSimpleName() 

  def check(rec:(K,V)):Unit =  {
    assert(!map.contains(rec._1), s"${name} already contains key ${rec._1} -> ${map(rec._1)} but try to rebind to ${rec._2}")
  }
  def + (rec:(K,V)):PMap 
  def printMap(implicit p:Printer):Unit = {
    if (map.size!=0) {
      p.emitBlock(name) {
        map.foreach{ case (k,v) =>
          p.emitln(s"$k -> $v")
        }
      }
    }
  }
}

trait OPMap {
  type K
  type V
  type M = Map[K,V]
}
trait CLMap extends PMap {
  type K = CLMap.K
  type V = CLMap.V
  override def + (rec:(K,V)) = { super.check(rec); CLMap(map + rec) }
}
object CLMap extends OPMap {
  type K = CL
  type V = PCL
  def apply(m:Map[K,V]) = new { override val map = m } with CLMap
  def empty:CLMap = CLMap(Map.empty)
}

/* A mapping between a PInBus and the POutBus it connects to */
trait IBMap extends PMap {
  type K = IBMap.K
  type V = IBMap.V
  override def + (rec:(K,V)) = { super.check(rec); IBMap(map + rec) }
}
object IBMap extends OPMap {
  type K = PIB
  type V = POB
  def apply(m:Map[K,V]) = new { override val map = m } with IBMap
  def empty:IBMap = IBMap(Map.empty)
}

/* A mapping between a Input (VecIn or ScalarIn) with PInBus and the POutBus where Input is 
 * connecting to */
trait VIMap extends PMap {
  type K = VIMap.K
  type V = VIMap.V
  override def + (rec:(K,V)) = { super.check(rec); VIMap(map + rec) }
}
object VIMap extends OPMap {
  type K = I
  type V = PIB
  def apply(m:Map[K,V]) = new { override val map = m } with VIMap
  def empty:VIMap = VIMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait SRAMMap extends PMap {
  type K = SRAMMap.K
  type V = SRAMMap.V
  override def + (rec:(K,V)) = { super.check(rec); SRAMMap(map + rec) }
}
object SRAMMap extends OPMap {
  type K = SRAM
  type V = PSRAM
  def apply(m:Map[K,V]) = new { override val map = m } with SRAMMap
  def empty:SRAMMap = SRAMMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait CtrMap extends PMap {
  type K = CtrMap.K
  type V = CtrMap.V
  override def + (rec:(K,V)) = { super.check(rec); CtrMap(map + rec) }
}
object CtrMap extends OPMap {
  type K = Ctr
  type V = PCtr
  def apply(m:Map[K,V]) = new { override val map = m } with CtrMap
  def empty:CtrMap = CtrMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait SIMap extends PMap {
  type K = SIMap.K
  type V = SIMap.V
  override def + (rec:(K,V)) = { super.check(rec); SIMap(map + rec) }
}
object SIMap extends OPMap {
  type K = SI
  type V = PSI
  def apply(m:Map[K,V]) = new { override val map = m } with SIMap
  def empty:SIMap = SIMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait SOMap extends PMap {
  type K = SOMap.K
  type V = SOMap.V
  override def + (rec:(K,V)) = { super.check(rec); SOMap(map + rec) }
}

object SOMap extends OPMap {
  type K = SO 
  type V = PSO 
  def apply(m:Map[K,V]) = new { override val map = m } with SOMap
  def empty:SOMap = SOMap(Map.empty)
}

/* A mapping between a scalar value and its writer's (OutBus, Index of Scalar Port in the Bus) */
trait SLMap extends PMap {
  type K = SLMap.K
  type V = SLMap.V
  override def + (rec:(K,V)) = { super.check(rec); SLMap(map + rec) }
  def getOutBus(k:K) = map(k)._1
  def getIdx(k:K) = map(k)._2

}
object SLMap extends OPMap {
  type K = Scalar 
  type V = (POB, Int)
  def apply(m:Map[K,V]) = new { override val map = m } with SLMap
  def empty:SLMap = SLMap(Map.empty)
}
