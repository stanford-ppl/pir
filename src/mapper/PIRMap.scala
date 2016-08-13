package pir.graph.mapper
import pir._
import pir.codegen.Printer
import pir.graph._
import pir.graph.{ Controller => CL, ComputeUnit => CU, TileTransfer => TT, 
                  Input => I, VecOut => VO,  SRAM,
                  Counter => Ctr, CounterChain => CC,
                  ScalarIn => SI, ScalarOut => SO, InPort => IP, OutPort => OP, Port => PT,
                  Stage => ST, Reg => R, Const }
import pir.plasticine.graph.{ Controller => PCL, ComputeUnit => PCU, TileTransfer => PTT, 
                            InBus => PIB, OutBus => POB,
                            Port => PPT, InPort => PIP, OutPort => POP,
                            Counter => PCtr, SRAM => PSRAM,
                            ScalarIn => PSI, ScalarOut => PSO,
                            Stage => PST, FUStage => PFUST, Reg => PReg, FUInPort => PFIP}

import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._

case class PIRMap(clmap:CLMap, vimap:VIMap, smmap:SMMap, ctmap:CTMap, simap:SIMap,
  somap:SOMap, slmap:SLMap, ibmap:IBMap, rcmap:RCMap,
  stmap:STMap, ipmap:IPMap, opmap:OPMap, fpmap:FPMap) {
  
  stmap.pirMap = this
  ipmap.pirMap = this
  opmap.pirMap = this
  ctmap.pirMap = this
  smmap.pirMap = this

  def set(cp:CLMap):PIRMap = PIRMap(cp   , vimap, smmap, ctmap, simap, somap, slmap, ibmap, rcmap, stmap, ipmap, opmap, fpmap)
  def set(cp:VIMap):PIRMap = PIRMap(clmap, cp   , smmap, ctmap, simap, somap, slmap, ibmap, rcmap, stmap, ipmap, opmap, fpmap)
  def set(cp:SMMap):PIRMap = PIRMap(clmap, vimap, cp   , ctmap, simap, somap, slmap, ibmap, rcmap, stmap, ipmap, opmap, fpmap)
  def set(cp:CTMap):PIRMap = PIRMap(clmap, vimap, smmap, cp   , simap, somap, slmap, ibmap, rcmap, stmap, ipmap, opmap, fpmap)
  def set(cp:SIMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, cp   , somap, slmap, ibmap, rcmap, stmap, ipmap, opmap, fpmap)
  def set(cp:SOMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, cp   , slmap, ibmap, rcmap, stmap, ipmap, opmap, fpmap)
  def set(cp:SLMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, cp   , ibmap, rcmap, stmap, ipmap, opmap, fpmap)
  def set(cp:IBMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, cp   , rcmap, stmap, ipmap, opmap, fpmap)
  def set(cp:RCMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, cp   , stmap, ipmap, opmap, fpmap)
  def set(cp:STMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, rcmap, cp   , ipmap, opmap, fpmap)
  def set(cp:IPMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, rcmap, stmap, cp   , opmap, fpmap)
  def set(cp:OPMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, rcmap, stmap, ipmap, cp   , fpmap)
  def set(cp:FPMap):PIRMap = PIRMap(clmap, vimap, smmap, ctmap, simap, somap, slmap, ibmap, rcmap, stmap, ipmap, opmap, cp   )

  def setCL(k:CLMap.K, v:CLMap.V):PIRMap = set(clmap + (k -> v))
  def setVI(k:VIMap.K, v:VIMap.V):PIRMap = set(vimap + (k -> v))
  def setSM(k:SMMap.K, v:SMMap.V):PIRMap = set(smmap + (k -> v))
  def setCt(k:CTMap.K, v:CTMap.V):PIRMap = set(ctmap + (k -> v))
  def setSI(k:SIMap.K, v:SIMap.V):PIRMap = set(simap + (k -> v))
  def setSO(k:SOMap.K, v:SOMap.V):PIRMap = set(somap + (k -> v))
  def setSL(k:SLMap.K, v:SLMap.V):PIRMap = set(slmap + (k -> v))
  def setIB(k:IBMap.K, v:IBMap.V):PIRMap = set(ibmap + (k -> v))
  def setRC(k:RCMap.K, v:RCMap.V):PIRMap = set(rcmap + (k -> v))
  def setST(k:STMap.K, v:STMap.V):PIRMap = set(stmap + (k -> v))
  def setIP(k:IPMap.K, v:IPMap.V):PIRMap = set(ipmap + (k -> v))
  def setOP(k:OPMap.K, v:OPMap.V):PIRMap = set(opmap + (k -> v))
  def setFP(k:FPMap.K, v:FPMap.V):PIRMap = set(fpmap + (k -> v))

  def printMap(implicit p:Printer, design:Design):Unit = {
    slmap.printMap
    ibmap.printMap
    design.top.ctrlers.foreach { cl => 
      if (clmap.map.contains(cl)) {
        val pcl = clmap.map(cl)
        p.emitBlock( s"$cl -> $pcl" ) {
          if (!cl.isInstanceOf[TileTransfer]) { //TODO
            simap.printMap(cl.sins)
            somap.printMap(cl.souts)
          }
          vimap.printMap(cl.sins ++ cl.vins)
          cl match {
            case cu:CU =>
              val pcu = clmap.map(cu).asInstanceOf[PCU]
              smmap.printMap(cu.srams)
              ctmap.printCCMap(cu.totalCChains)
              rcmap.printMap(rcmap.keys.filter(k => k.ctrler==cu).toList)
              //stmap.printPMap(pcu.stages)
              stmap.printMap(stmap.keys.filter(k => k.ctrler==cu).toList)
            case _ =>
          }
        }
      } else {
        p.emitln(s"$cl <- failed")
      }
    }
  }
  def printPMap(implicit p:Printer, design:Design):Unit = {
    slmap.printMap
    ibmap.printMap
    design.arch.ctrlers.foreach { pcl => 
      if (clmap.pmap.contains(pcl)) {
        val cl = clmap.pmap(pcl)
        p.emitBlock( s"$pcl <- $cl" ) {
          if (!cl.isInstanceOf[TileTransfer]) { //TODO
            simap.printMap(cl.sins)
            somap.printMap(cl.souts)
          }
          vimap.printMap(cl.sins ++ cl.vins)
          pcl match {
            case pcu:PCU =>
              val cu = clmap.pmap(pcu).asInstanceOf[CU]
              smmap.printPMap(pcu.srams)
              ctmap.printPMap(pcu.ctrs)
              rcmap.printMap(rcmap.keys.filter(k => k.ctrler==cu).toList)
              //stmap.printPMap(pcu.stages)
              stmap.printMap(stmap.keys.filter(k => k.ctrler==cu).toList)
            case _ =>
          }
        }
      } else {
        p.emitln(s"$pcl <- no mapping")
      }
    }
  }
}
object PIRMap {
  def empty:PIRMap = 
    PIRMap(CLMap.empty, VIMap.empty, SMMap.empty, CTMap.empty, 
           SIMap.empty, SOMap.empty, SLMap.empty, IBMap.empty,
           RCMap.empty, STMap.empty, IPMap.empty, OPMap.empty,
           FPMap.empty)
}

trait PMap {
  type K
  type V
  type M = Map[K, V]
  var pirMap:PIRMap = _
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
          if (!map.contains(k))
            p.emitln(s"$k -> failed")
          else
            p.emitln(s"$k -> ${map(k)}")
        }
      }
    }
  }
  def printMap(ks:List[K], lambda:K=>Unit)(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          if (!map.contains(k))
            p.emitln(s"$k -> failed")
          else {
            //p.emitln(s"$k -> ${map(k)}")
            p.emitBlock(s"$k -> ${map(k)}") { lambda(k) }
          }
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
trait BMap extends PMap {
  type PM = Map[V, K]
  val pmap:PM
  def printPMap(ks:List[V])(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          if (!pmap.contains(k))
            p.emitln(s"$k <- no map")
          else
            p.emitln(s"$k <- ${pmap(k)}")
        }
      }
    }
  }
  def printPMap(ks:List[V], lambda:V=>Unit)(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          if (!pmap.contains(k))
            p.emitln(s"$k <- no map")
          else {
            p.emitBlock(s"$k <- ${pmap(k)}") { lambda(k) }
          }
        }
      }
    }
  }
}
trait BMapObj extends PMapObj {
  type PM = Map[V, K]
}
case class CLMap(map:CLMap.M, pmap:CLMap.PM) extends BMap {
  type K = CLMap.K
  type V = CLMap.V
  override def + (rec:(K,V)) = { super.check(rec); CLMap(map + rec, pmap + rec.swap) }
}
object CLMap extends BMapObj {
  type K = CL
  type V = PCL
  def empty:CLMap = CLMap(Map.empty, Map.empty)
}

/* A mapping between a Input (VecIn or ScalarIn) with PInBus and the POutBus where Input is 
 * connecting to */
case class VIMap(map:VIMap.M) extends PMap {
  type K = VIMap.K
  type V = VIMap.V
  override def + (rec:(K,V)) = { super.check(rec); VIMap(map + rec) }
}
object VIMap extends PMapObj {
  type K = I
  type V = PIB
  def empty:VIMap = VIMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
case class SMMap(map:SMMap.M, pmap:SMMap.PM) extends BMap {
  type K = SMMap.K
  type V = SMMap.V
  override def + (rec:(K,V)) = { super.check(rec); SMMap(map + rec, pmap + rec.swap) }
  override def printMap(ks:List[K])(implicit p:Printer):Unit = {
    val ipmap = pirMap.ipmap
    val opmap = pirMap.opmap
    def printSRAM(sram:K) = {
      ipmap.printInPort(sram.readAddr)
      ipmap.printInPort(sram.writeAddr)
      ipmap.printInPort(sram.writePort)
      opmap.printOutPort(sram.readPort)
    }
    super.printMap(ks.asInstanceOf[List[K]], printSRAM)
  }
}
object SMMap extends BMapObj {
  type K = SRAM
  type V = PSRAM
  def empty:SMMap = SMMap(Map.empty, Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
case class CTMap(map:CTMap.M, pmap:CTMap.PM) extends BMap {
  type K = CTMap.K
  type V = CTMap.V
  override def + (rec:(K,V)) = { super.check(rec); CTMap(map + rec, pmap + rec.swap) }
  def printCCMap(ccs:List[CC])(implicit p:Printer):Unit = {
    p.emitBlock(s"CCMap") {
      ccs.foreach { cc =>
        super.printMap(cc.counters, printCtr)
      }
    }
  }
  def printCtr(ctr:K)(implicit c:Printer) = {
    val ipmap = pirMap.ipmap 
    val opmap = pirMap.opmap
    ipmap.printInPort(ctr.min)
    ipmap.printInPort(ctr.max)
    ipmap.printInPort(ctr.step)
    opmap.printOutPort(ctr.out)
  }
  override def printPMap(ks:List[V])(implicit p:Printer):Unit = {
    super.printPMap(ks, printPCtr)
  }
  def printPCtr(pctr:V)(implicit c:Printer) = {
    val ipmap = pirMap.ipmap 
    val opmap = pirMap.opmap
    ipmap.printInPort(pctr.min)
    ipmap.printInPort(pctr.max)
    ipmap.printInPort(pctr.step)
    opmap.printOutPort(pctr.out)
  }
}
object CTMap extends BMapObj {
  type K = Ctr
  type V = PCtr
  def empty:CTMap = CTMap(Map.empty, Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
case class SIMap(map:SIMap.M) extends PMap {
  type K = SIMap.K
  type V = SIMap.V
  override def + (rec:(K,V)) = { super.check(rec); SIMap(map + rec) }
}
object SIMap extends PMapObj {
  type K = SI
  type V = PSI
  def empty:SIMap = SIMap(Map.empty)
}

/* A Map between PIR ScalarOut to Spade ScalarOut */
case class SOMap(map:SOMap.M) extends PMap {
  type K = SOMap.K
  type V = SOMap.V
  override def + (rec:(K,V)) = { super.check(rec); SOMap(map + rec) }
}

object SOMap extends PMapObj {
  type K = SO 
  type V = PSO 
  def empty:SOMap = SOMap(Map.empty)
}

/* A mapping between a scalar value and its writer's (OutBus, Index of Scalar Port in the Bus) */
case class SLMap(map:SLMap.M) extends PMap {
  type K = SLMap.K
  type V = SLMap.V
  override def + (rec:(K,V)) = { super.check(rec); SLMap(map + rec) }
  def getOutBus(k:K) = map(k)._1
  def getIdx(k:K) = map(k)._2

}
object SLMap extends PMapObj {
  type K = Scalar 
  type V = (POB, Int)
  def empty:SLMap = SLMap(Map.empty)
}
/* A mapping between a scalar value and its writer's (OutBus, Index of Scalar Port in the Bus) */
case class RCMap(map:RCMap.M) extends PMap {
  type K = RCMap.K
  type V = RCMap.V
  override def + (rec:(K,V)) = { super.check(rec); RCMap(map + rec) }
}
object RCMap extends PMapObj {
  type K = R 
  type V = PReg 
  def empty:RCMap = RCMap(Map.empty)
}
/* A mapping between Stage and PStage */
case class STMap(map:STMap.M, pmap:STMap.PM) extends BMap {
  type K = STMap.K
  type V = STMap.V
  override def + (rec:(K,V)) = { super.check(rec); STMap(map + rec, pmap + rec.swap) }
  override def printMap(ks:List[K])(implicit p:Printer):Unit = {
    val ipmap = pirMap.ipmap 
    val fpmap = pirMap.fpmap
    val opmap = pirMap.opmap
    def printStage(stage:K) = {
      stage.fu.foreach { f =>
        f.operands.foreach { oprd => ipmap.printInPort(oprd) }
        opmap.printOutPort(f.out)
      }
      val pstage = map(stage)
      stage.prs.foreach{ case (reg, pr) =>
        ipmap.printInPort(pr.in)
        opmap.printOutPort(pr.out)
      }
    }
    super.printMap(ks.asInstanceOf[List[K]], printStage)
  }
  override def printPMap(ks:List[V])(implicit p:Printer):Unit = {
    val ipmap = pirMap.ipmap 
    val fpmap = pirMap.fpmap
    val opmap = pirMap.opmap
    def printStage(pstage:V) = {
      pstage match {
        case fs:PFUST =>
          fs.fu.operands.foreach { oprd => ipmap.printInPort(oprd) }
          opmap.printOutPort(fs.fu.out)
        case s =>
      }
      pstage.prs.foreach { case (reg, ppr) =>
        ipmap.printInPort(ppr.in)
        opmap.printOutPort(ppr.out)
      }
    }
    super.printPMap(ks, printStage)
  }
}
object STMap extends BMapObj {
  type K = ST 
  type V = PST
  def empty:STMap = STMap(Map.empty, Map.empty)
}
/* A mapping between a PInBus and the POutBus it connects to */
case class IBMap(map:IBMap.M) extends PMap {
  type K = IBMap.K
  type V = IBMap.V
  override def + (rec:(K,V)) = { super.check(rec); IBMap(map + rec) }
}
object IBMap extends PMapObj {
  type K = PIB
  type V = POB
  def empty:IBMap = IBMap(Map.empty)
}
/* A mapping between InPort and PInPort */
case class IPMap(map:IPMap.M, pmap:IPMap.PM) extends BMap {
  type K = IPMap.K
  type V = IPMap.V
  override def + (rec:(K,V)) = { super.check(rec); IPMap(map + rec, pmap + rec.swap) }
  def printInPort(ip:IP)(implicit p:Printer) = {
    if (map.contains(ip)) {
      val pip = map(ip)
      val fpmap = pirMap.fpmap
      if (fpmap.contains(pip)) {
        val pop = fpmap(pip)
        p.emitln(s"${ip} -> ${pip} <- ${pop}")
      } else {
        p.emitln(s"${ip} -> ${pip} <- failed")
      }
    } else {
      p.emitln(s"${ip} -> failed")
    }
  }
  def printInPort(pip:PIP)(implicit p:Printer) = {
    if (pmap.contains(pip)) {
      val ip = pmap(pip)
      val fpmap = pirMap.fpmap
      if (fpmap.contains(pip)) {
        val pop = fpmap(pip)
        p.emitln(s"${pip}(${ip}) <- ${pop}")
      } else {
        p.emitln(s"${pip}(${ip}) <- failed")
      }
    } else {
      p.emitln(s"${pip} -> no map")
    }
  }
}
object IPMap extends BMapObj {
  type K = IP 
  type V = PIP 
  def empty:IPMap = IPMap(Map.empty, Map.empty)
}
/* A mapping between OutPort and the POutPort */
case class OPMap(map:OPMap.M, pmap:OPMap.PM) extends BMap {
  type K = OPMap.K
  type V = OPMap.V
  override def + (rec:(K,V)):OPMap = { 
    if (!rec._1.src.isInstanceOf[Const]) {
      super.check(rec); OPMap(map + rec, pmap + rec.swap)
    } else this
  }
  def printOutPort(op:OP)(implicit p:Printer) = {
    if (map.contains(op)) {
      p.emitln(s"${op} -> ${map(op)}")
    } else {
      p.emitln(s"${op} -> failed")
    }
  }
  def printOutPort(pop:POP)(implicit p:Printer) = {
    if (pmap.contains(pop)) {
      p.emitln(s"${pop} <- ${pmap(pop)}")
    } else {
      p.emitln(s"${pop} <- no map")
    }
  }
}
object OPMap extends BMapObj {
  type K = OP
  type V = POP 
  def empty:OPMap = OPMap(Map.empty, Map.empty)
}
/* A mapping between PInPort and the POutPort it connects to*/
case class FPMap(map:FPMap.M) extends PMap {
  type K = FPMap.K
  type V = FPMap.V
  override def + (rec:(K,V)) = { super.check(rec); FPMap(map + rec) }
}
object FPMap extends PMapObj {
  type K = PIP
  type V = POP 
  def empty:FPMap = FPMap(Map.empty)
}
