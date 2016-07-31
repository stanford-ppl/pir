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
  type K <: Node
  type V
  type M = Map[K, V]
  val map:M
  def printMap(implicit p:Printer):Unit 
  def contains(k:K) = map.contains(k)
  def apply(k:K):V = map(k)
}

trait CLMap extends PIRMap {
  type K = CLMap.K
  type V = CLMap.V

  val map: M 

  def getPcu(k:K):PCL = map(k)._1
  def getVmap(k:K):VecMap = map(k)._2
  def getSmap(k:K):SRAMMap = map(k)._3
  def getCtmap(k:K):CtrMap = map(k)._4
  def getSImap(k:K):SIMap = map(k)._5
  def getSOmap(k:K):SOMap = map(k)._6

  def update(k:K, v:V):CLMap = CLMap(map + (k -> v))
  def setPcu(k:K, pcu:PCL):CLMap = CLMap(map + (k -> map.getOrElse(k, CLMap.emptyV).copy(_1=pcu)))
  def setVmap(k:K, mp:VecMap):CLMap = CLMap(map + (k -> map(k).copy(_2=mp))) 
  def setSmap(k:K, mp:SRAMMap):CLMap = CLMap(map + (k -> map(k).copy(_3=mp)))
  def setCtmap(k:K, mp:CtrMap):CLMap = CLMap(map + (k -> map(k).copy(_4=mp)))
  def setSImap(k:K, mp:SIMap):CLMap = CLMap(map + (k -> map(k).copy(_5=mp)))
  def setSOmap(k:K, mp:SOMap):CLMap = CLMap(map + (k -> map(k).copy(_6=mp)))

  def printMap(implicit p:Printer) = {
    p.emitBlock("clMap") {
      map.foreach{ case (cl, (pcl, vm, sm, cm, sim, som)) =>
        p.emitBlock(s"$cl -> $pcl") {
          vm.printMap
          sm.printMap
          cm.printMap
          sim.printMap
          som.printMap
        }
      }
    }
  p.close
  }
}
object CLMap {
  type K = CL
  type V = (PCL, VecMap,
                 SRAMMap, 
                 CtrMap, 
                 SIMap, 
                 SOMap)

  def apply(m:Map[K,V]) = new { override val map = m } with CLMap
  def empty:CLMap = CLMap(Map.empty)
  def emptyV:V = (null, VecMap.empty, SRAMMap.empty, CtrMap.empty, SIMap.empty, SOMap.empty)
}

trait VecMap extends PIRMap {
  type K = VecMap.K
  type V = VecMap.V

  val map: M 

  def printMap(implicit p:Printer) = {
    p.emitBlock("vecMap") {
      map.foreach{ case (k,v) =>
        p.emitln(s"$k -> $v")
      }
    }
    p.close
  }
  def + (rec:(K,V)) = VecMap(map + rec)
  def getIB(k:K) = map(k)._1
  def getOB(k:K) = map(k)._2

}
object VecMap {
  type K = I
  type V = (PIB, POB)

  def apply(m:Map[K,V]) = new { override val map = m } with VecMap
  def empty:VecMap = VecMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait SRAMMap extends PIRMap {
  type K = SRAMMap.K
  type V = SRAMMap.V

  val map: M 

  def printMap(implicit p:Printer) = {
    p.emitBlock("sramMap") {
      map.foreach{ case (k,v) =>
        p.emitln(s"$k -> $v")
      }
    }
    p.close
  }
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

  val map: M 

  def printMap(implicit p:Printer) = {
    p.emitBlock("ctrMap") {
      map.foreach{ case (k,v) =>
        p.emitln(s"$k -> $v")
      }
    }
    p.close
  }
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

  val map: M 

  def printMap(implicit p:Printer) = {
    p.emitBlock("siMap") {
      map.foreach{ case (k,v) =>
        p.emitln(s"$k -> $v")
      }
    }
    p.close
  }
  def + (rec:(K,V)) = SIMap(map + rec)

}
object SIMap {
  type K = SI
  type V = (PSI, PSO) 

  def apply(m:Map[K,V]) = new { override val map = m } with SIMap
  def empty:SIMap = SIMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait SOMap extends PIRMap {
  type K = SOMap.K
  type V = SOMap.V

  val map: M 

  def printMap(implicit p:Printer) = {
    p.emitBlock("soMap") {
      map.foreach{ case (k,v) =>
        p.emitln(s"$k -> $v")
      }
    }
    p.close
  }
  def + (rec:(K,V)) = SOMap(map + rec)

}
object SOMap {
  type K = SO 
  type V = PSO 

  def apply(m:Map[K,V]) = new { override val map = m } with SOMap
  def empty:SOMap = SOMap(Map.empty)
}
