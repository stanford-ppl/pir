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

trait PIRMap {
  type K
  type V
  type M = Map[K, V]
  val map:M
  def contains(k:K) = map.contains(k)
  def apply(k:K):V = map(k)
  def printMap(implicit p:Printer):Unit 

  def printMap(name:String, m:Map[_,_])(implicit p:Printer):Unit = {
    if (map.size!=0) {
      p.emitBlock(name) {
        map.foreach{ case (k,v) =>
          p.emitln(s"$k -> $v")
        }
      }
    }
  }
}

trait CLMap extends PIRMap {
  type K = CLMap.K
  type V = CLMap.V

  def getPcu(k:K):PCL = map(k)._1
  def getVImap(k:K):VIMap = map(k)._2
  def getSMmap(k:K):SRAMMap = map(k)._3
  def getCtmap(k:K):CtrMap = map(k)._4
  def getSImap(k:K):SIMap = map(k)._5
  def getSOmap(k:K):SOMap = map(k)._6
  def getSLmap(k:K):SLMap = map(k)._7
  def getIBmap(k:K):IBMap = map(k)._8

  def update(k:K, v:V):CLMap = CLMap(map + (k -> v))
  def setPcu(k:K, pcu:PCL):CLMap = CLMap(map + (k -> map.getOrElse(k, CLMap.emptyV).copy(_1=pcu)))
  def setVImap(k:K, mp:VIMap  ):CLMap = CLMap(map + (k -> map(k).copy(_2=mp)))
  def setSMmap(k:K, mp:SRAMMap):CLMap = CLMap(map + (k -> map(k).copy(_3=mp)))
  def setCtmap(k:K, mp:CtrMap ):CLMap = CLMap(map + (k -> map(k).copy(_4=mp)))
  def setSImap(k:K, mp:SIMap  ):CLMap = CLMap(map + (k -> map(k).copy(_5=mp)))
  def setSOmap(k:K, mp:SOMap  ):CLMap = CLMap(map + (k -> map(k).copy(_6=mp)))
  def setSLmap(k:K, mp:SLMap  ):CLMap = CLMap(map + (k -> map(k).copy(_7=mp)))
  def setIBmap(k:K, mp:IBMap  ):CLMap = CLMap(map + (k -> map(k).copy(_8=mp)))

  def setVI(k:K, kk:VIMap.K  , v:VIMap.V  ) = setVImap(k, getVImap(k) + (kk -> v))
  def setSM(k:K, kk:SRAMMap.K, v:SRAMMap.V) = setSMmap(k, getSMmap(k) + (kk -> v))
  def setCt(k:K, kk:CtrMap.K , v:CtrMap.V ) = setCtmap(k, getCtmap(k) + (kk -> v))
  def setSI(k:K, kk:SIMap.K  , v:SIMap.V  ) = setSImap(k, getSImap(k) + (kk -> v))
  def setSO(k:K, kk:SOMap.K  , v:SOMap.V  ) = setSOmap(k, getSOmap(k) + (kk -> v))
  def setSL(k:K, kk:SLMap.K  , v:SLMap.V  ) = setSLmap(k, getSLmap(k) + (kk -> v))
  def setIB(k:K, kk:IBMap.K  , v:IBMap.V  ) = setIBmap(k, getIBmap(k) + (kk -> v))

  def printMap(implicit p:Printer) = {
    p.emitBlock("clMap") {
      map.foreach{ case (cl, (pcl, vm, sm, cm, sim, som, slm, ibm)) =>
        p.emitBlock(s"$cl -> $pcl") {
          vm.printMap
          ibm.printMap
          sm.printMap
          cm.printMap
          slm.printMap
          sim.printMap
          som.printMap
        }
      }
    }
  }
}
object CLMap {
  type K = CL
  type V = (PCL, VIMap, SRAMMap, CtrMap, SIMap, SOMap, SLMap, IBMap)

  def apply(m:Map[K,V]) = new { override val map = m } with CLMap
  def empty:CLMap = CLMap(Map.empty)
  def emptyV:V = (null, VIMap.empty, SRAMMap.empty, CtrMap.empty, 
                  SIMap.empty, SOMap.empty, SLMap.empty, IBMap.empty)
}

/* A mapping between a PInBus and the POutBus it connects to */
trait IBMap extends PIRMap {
  type K = IBMap.K
  type V = IBMap.V

  def printMap(implicit p:Printer) = printMap("ibMap", map)
  def + (rec:(K,V)) = IBMap(map + rec)
}
object IBMap {
  type K = PIB
  type V = POB

  def apply(m:Map[K,V]) = new { override val map = m } with IBMap
  def empty:IBMap = IBMap(Map.empty)
}

/* A mapping between a Input (VecIn or ScalarIn) with PInBus and the POutBus where Input is 
 * connecting to */
trait VIMap extends PIRMap {
  type K = VIMap.K
  type V = VIMap.V

  def printMap(implicit p:Printer) = printMap("vecInMap", map)
  def + (rec:(K,V)) = VIMap(map + rec)
}
object VIMap {
  type K = I
  type V = PIB

  def apply(m:Map[K,V]) = new { override val map = m } with VIMap
  def empty:VIMap = VIMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait SRAMMap extends PIRMap {
  type K = SRAMMap.K
  type V = SRAMMap.V

  def printMap(implicit p:Printer) = printMap("sramMap", map)
  def + (rec:(K,V)) = SRAMMap(map + rec)

}
object SRAMMap {
  type K = SRAM
  type V = PSRAM

  def apply(m:Map[K,V]) = new { override val map = m } with SRAMMap
  def empty:SRAMMap = SRAMMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait CtrMap extends PIRMap {
  type K = CtrMap.K
  type V = CtrMap.V

  def printMap(implicit p:Printer) = printMap("ctrMap", map)
  def + (rec:(K,V)) = CtrMap(map + rec)

}
object CtrMap {
  type K = Ctr
  type V = PCtr

  def apply(m:Map[K,V]) = new { override val map = m } with CtrMap
  def empty:CtrMap = CtrMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait SIMap extends PIRMap {
  type K = SIMap.K
  type V = SIMap.V

  def printMap(implicit p:Printer) = printMap("siMap", map)
  def + (rec:(K,V)) = SIMap(map + rec)

}
object SIMap {
  type K = SI
  type V = PSI

  def apply(m:Map[K,V]) = new { override val map = m } with SIMap
  def empty:SIMap = SIMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait SOMap extends PIRMap {
  type K = SOMap.K
  type V = SOMap.V

  def printMap(implicit p:Printer) = printMap("soMap", map)
  def + (rec:(K,V)) = SOMap(map + rec)

}

object SOMap {
  type K = SO 
  type V = PSO 

  def apply(m:Map[K,V]) = new { override val map = m } with SOMap
  def empty:SOMap = SOMap(Map.empty)
}

/* A mapping between a scalar value and its writer's (OutBus, Index of Scalar Port in the Bus) */
trait SLMap extends PIRMap {
  type K = SLMap.K
  type V = SLMap.V

  def printMap(implicit p:Printer) = printMap("scalarMap", map)
  def + (rec:(K,V)) = SLMap(map + rec)
  def getOutBus(k:K) = map(k)._1
  def getIdx(k:K) = map(k)._2

}
object SLMap {
  type K = Scalar 
  type V = (POB, Int)

  def apply(m:Map[K,V]) = new { override val map = m } with SLMap
  def empty:SLMap = SLMap(Map.empty)
}
