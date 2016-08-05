package pir.graph.mapper
import pir._
import pir.graph._
import pir.graph.{ Controller => CL, ComputeUnit => CU, TileTransfer => TT, 
                  Input => I, VecOut => VO, 
                  SRAM,
                  Counter => Ctr, CounterChain => CC,
                  ScalarIn => SI, ScalarOut => SO, Port => PT,
                  Stage => ST, Reg => R, Const}
import pir.plasticine.graph.{ Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT, 
                            InBus => PIB, OutBus => POB,
                            Port => PPT, InPort => IP, OutPort => OP,
                            Counter => PCtr, SRAM => PSRAM,
                            ScalarIn => PSI, ScalarOut => PSO,
                            Stage => PST, Reg => PReg, FUInPort => PFIP}
import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer

case class PIRMap(clmap:CLMap, vimap:VIMap, smmap:SMMap, ctmap:CTMap, simap:SIMap,
  somap:SOMap, slmap:SLMap, ibmap:IBMap, limap:LIMap, igmap:IGMap, rcmap:RCMap,
  stmap:STMap, ipmap:IPMap, opmap:OPMap) {

  def set(cp:CLMap):PIRMap = PIRMap(cp   , vimap, smmap, ctmap, simap, somap, slmap, ibmap, limap, igmap, rcmap, stmap, ipmap, opmap)
  def set(cp:VIMap):PIRMap = PIRMap(clmap, cp   , smmap, ctmap, simap, somap, slmap, ibmap, limap, igmap, rcmap, stmap, ipmap, opmap)
  def set(cp:SMMap):PIRMap = PIRMap(clmap, vimap, cp   , ctmap, simap, somap, slmap, ibmap, limap, igmap, rcmap, stmap, ipmap, opmap)
  def set(cp:CTMap):PIRMap = PIRMap(clmap, vimap, smmap, cp   , simap, somap, slmap, ibmap, limap, igmap, rcmap, stmap, ipmap, opmap)
  def set(cp:SIMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, cp   , somap, slmap, ibmap, limap, igmap, rcmap, stmap, ipmap, opmap)
  def set(cp:SOMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, cp   , slmap, ibmap, limap, igmap, rcmap, stmap, ipmap, opmap)
  def set(cp:SLMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, cp   , ibmap, limap, igmap, rcmap, stmap, ipmap, opmap)
  def set(cp:IBMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, cp   , limap, igmap, rcmap, stmap, ipmap, opmap)
  def set(cp:LIMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, cp   , igmap, rcmap, stmap, ipmap, opmap)
  def set(cp:IGMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, limap, cp   , rcmap, stmap, ipmap, opmap)
  def set(cp:RCMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, limap, igmap, cp   , stmap, ipmap, opmap)
  def set(cp:STMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, limap, igmap, rcmap, cp   , ipmap, opmap)
  def set(cp:IPMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, limap, igmap, rcmap, stmap, cp   , opmap)
  def set(cp:OPMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, limap, igmap, rcmap, stmap, ipmap, cp  )
  def copy(cp:PMap):PIRMap = cp match {
    case m:CLMap => set(m) 
    case m:VIMap => set(m)    
    case m:SMMap => set(m)
    case m:CTMap => set(m)
    case m:SIMap => set(m)
    case m:SOMap => set(m)
    case m:SLMap => set(m)
    case m:IBMap => set(m)
    case m:LIMap => set(m)
    case m:IGMap => set(m)
    case m:RCMap => set(m)
    case m:STMap => set(m)
    case m:IPMap => set(m)
    case m:OPMap => set(m)
  } 

  def setCL(k:CLMap.K, v:CLMap.V):PIRMap = set(clmap + (k -> v))
  def setVI(k:VIMap.K, v:VIMap.V):PIRMap = set(vimap + (k -> v))
  def setSM(k:SMMap.K, v:SMMap.V):PIRMap = set(smmap + (k -> v))
  def setCt(k:CTMap.K, v:CTMap.V):PIRMap = set(ctmap + (k -> v))
  def setSI(k:SIMap.K, v:SIMap.V):PIRMap = set(simap + (k -> v))
  def setSO(k:SOMap.K, v:SOMap.V):PIRMap = set(somap + (k -> v))
  def setSL(k:SLMap.K, v:SLMap.V):PIRMap = set(slmap + (k -> v))
  def setIB(k:IBMap.K, v:IBMap.V):PIRMap = set(ibmap + (k -> v))
  def setLI(k:LIMap.K, v:LIMap.V):PIRMap = set(limap + (k -> v))
  def setIG(k:IGMap.K, v:IGMap.V):PIRMap = set(igmap + (k -> v))
  def setRC(k:RCMap.K, v:RCMap.V):PIRMap = set(rcmap + (k -> v))
  def setST(k:STMap.K, v:STMap.V):PIRMap = set(stmap + (k -> v))
  def setIP(k:IPMap.K, v:IPMap.V):PIRMap = set(ipmap + (k -> v))
  def setOP(k:OPMap.K, v:OPMap.V):PIRMap = set(opmap + (k -> v))

  def printMap(implicit p:Printer):Unit = {
    slmap.printMap
    ibmap.printMap
    ipmap.printMap
    clmap.map.foreach { case (cl, pcl) =>
      p.emitBlock( s"$cl -> $pcl" ) {
        if (!cl.isInstanceOf[TileTransfer]) { //TODO
          simap.printMap(cl.sins)
          somap.printMap(cl.souts)
        }
        vimap.printMap(cl.sins ++ cl.vins)
        cl match {
          case cu:CU =>
            smmap.printMap(cu.srams)
            ctmap.printCCMap(cu.cchains)
            limap.printMap(cu, cu.stages.toList)
            igmap.printMap(igmap.keys.filter(k => k.ctrler==cu).toList)
            rcmap.printMap(rcmap.keys.filter(k => k.ctrler==cu).toList)
            stmap.printMap(cu.stages.toList)
          case _ =>
        }
      }
    }
  }
}
object PIRMap {
  def empty:PIRMap = 
    PIRMap(CLMap.empty, VIMap.empty, SMMap.empty, CTMap.empty, 
           SIMap.empty, SOMap.empty, SLMap.empty, IBMap.empty, LIMap.empty,
           IGMap.empty, RCMap.empty, STMap.empty, IPMap.empty, OPMap.empty)
}

trait PMap {
  type K
  type V
  type M = Map[K, V]
  val map:M
  def contains(k:K) = map.contains(k)
  def apply(k:K):V = map(k)
  val name:String = this.getClass().getInterfaces().head.getSimpleName() 
  def keys = map.keys

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
  def printMap(ks:List[K])(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          p.emitln(s"$k -> ${map(k)}")
        }
      }
    }
  }
}

trait PMapObj {
  type K
  type V
  type M = Map[K,V]
}
trait CLMap extends PMap {
  type K = CLMap.K
  type V = CLMap.V
  override def + (rec:(K,V)) = { super.check(rec); CLMap(map + rec) }
}
object CLMap extends PMapObj {
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
object IBMap extends PMapObj {
  type K = PIB
  type V = POB
  def apply(m:Map[K,V]) = new { override val map = m } with IBMap
  def empty:IBMap = IBMap(Map.empty)
}
/* A mapping between InPort and the OutPort it connects to */
trait IPMap extends PMap {
  type K = IPMap.K
  type V = IPMap.V
  override def + (rec:(K,V)) = { super.check(rec); IPMap(map + rec) }
}
object IPMap extends PMapObj {
  type K = IP  
  type V = OP 
  def apply(m:Map[K,V]) = new { override val map = m } with IPMap
  def empty:IPMap = IPMap(Map.empty)
}
/* A mapping between Port and the POutPort */
trait OPMap extends PMap {
  type K = OPMap.K
  type V = OPMap.V
  override def + (rec:(K,V)):OPMap = { 
    if (!rec._1.src.isInstanceOf[Const]) {
      super.check(rec); OPMap(map + rec)
    } else this
  }
}
object OPMap extends PMapObj {
  type K = PT
  type V = OP 
  def apply(m:Map[K,V]) = new { override val map = m } with OPMap
  def empty:OPMap = OPMap(Map.empty)
}

/* A mapping between a Input (VecIn or ScalarIn) with PInBus and the POutBus where Input is 
 * connecting to */
trait VIMap extends PMap {
  type K = VIMap.K
  type V = VIMap.V
  override def + (rec:(K,V)) = { super.check(rec); VIMap(map + rec) }
}
object VIMap extends PMapObj {
  type K = I
  type V = PIB
  def apply(m:Map[K,V]) = new { override val map = m } with VIMap
  def empty:VIMap = VIMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait SMMap extends PMap {
  type K = SMMap.K
  type V = SMMap.V
  override def + (rec:(K,V)) = { super.check(rec); SMMap(map + rec) }
}
object SMMap extends PMapObj {
  type K = SRAM
  type V = PSRAM
  def apply(m:Map[K,V]) = new { override val map = m } with SMMap
  def empty:SMMap = SMMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait CTMap extends PMap {
  type K = CTMap.K
  type V = CTMap.V
  override def + (rec:(K,V)) = { super.check(rec); CTMap(map + rec) }
  def printCCMap(ccs:List[CC])(implicit p:Printer):Unit = {
    p.emitBlock(s"CCMap") {
      ccs.foreach { cc =>
        super.printMap(cc.counters)
      }
    }
  }
}
object CTMap extends PMapObj {
  type K = Ctr
  type V = PCtr
  def apply(m:Map[K,V]) = new { override val map = m } with CTMap
  def empty:CTMap = CTMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
trait SIMap extends PMap {
  type K = SIMap.K
  type V = SIMap.V
  override def + (rec:(K,V)) = { super.check(rec); SIMap(map + rec) }
}
object SIMap extends PMapObj {
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

object SOMap extends PMapObj {
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
object SLMap extends PMapObj {
  type K = Scalar 
  type V = (POB, Int)
  def apply(m:Map[K,V]) = new { override val map = m } with SLMap
  def empty:SLMap = SLMap(Map.empty)
}
/* A mapping between a scalar value and its writer's (OutBus, Index of Scalar Port in the Bus) */
trait LIMap extends PMap {
  type K = LIMap.K 
  type V = LIMap.V
  override def + (rec:(K,V)) = { super.check(rec); LIMap(map + rec) }
  def printMap(cu:CU, ks:List[K])(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          val defs = cu.stageDefs(k).mkString(",") 
          val uses = cu.stageUses(k).mkString(",")
          val lins = map(k)._1.mkString(",")
          val louts = map(k)._2.mkString(",")
          p.emitln(s"${k} def=[${defs}] use=[${uses}] liveIn=[${lins}]] liveOut=[${louts}]")
        }
      }
    }
  }
}
object LIMap extends PMapObj {
  type K = ST 
  type V = (Set[R], Set[R]) // Stage -> (LiveIn, LiveOut)
  def apply(m:Map[K,V]) = new { override val map = m } with LIMap
  def empty:LIMap = LIMap(Map.empty)
}
/* A mapping between a scalar value and its writer's (OutBus, Index of Scalar Port in the Bus) */
trait IGMap extends PMap {
  type K = IGMap.K
  type V = IGMap.V
  override def + (rec:(K,V)) = { super.check(rec); IGMap(map + rec) }
}
object IGMap extends PMapObj {
  type K = Reg  
  type V = Set[Reg] 
  def apply(m:Map[K,V]) = new { override val map = m } with IGMap
  def empty:IGMap = IGMap(Map.empty)
}
/* A mapping between a scalar value and its writer's (OutBus, Index of Scalar Port in the Bus) */
trait RCMap extends PMap {
  type K = RCMap.K
  type V = RCMap.V
  override def + (rec:(K,V)) = { super.check(rec); RCMap(map + rec) }
}
object RCMap extends PMapObj {
  type K = R 
  type V = PReg 
  def apply(m:Map[K,V]) = new { override val map = m } with RCMap
  def empty:RCMap = RCMap(Map.empty)
}
/* A mapping between Stage and PStage */
trait STMap extends PMap {
  type K = STMap.K
  type V = STMap.V
  override def + (rec:(K,V)) = { super.check(rec); STMap(map + rec) }
  def getStage(k:K):PST = map(k)._1
  def getOprdMap(k:K):STMap.OM = map(k)._2
  override def printMap(ks:List[K])(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          val (pst, om) = map(k)
          p.emitln(s"$k -> ${pst} oprd=[${om.map{case (kk,vv) => s"$kk->$vv"}.mkString(s", ")}]")
        }
      }
    }
  }
}
object STMap extends PMapObj {
  type K = ST 
  type OM = Map[PT, PFIP]
  type V = (PST, OM) 
  def apply(m:Map[K,V]) = new { override val map = m } with STMap
  def empty:STMap = STMap(Map.empty)
}
