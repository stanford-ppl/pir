package pir.graph.mapper
import pir._
import pir.codegen.Printer
import pir.typealias._

import pir.graph.traversal.PIRMapping

import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._

case class PIRMap(clmap:CLMap, vimap:VIMap, vomap:VOMap, smmap:SMMap, ctmap:CTMap, simap:SIMap,
  somap:SOMap, fbmap:FBMap, rcmap:RCMap, stmap:STMap, ipmap:IPMap, opmap:OPMap, 
  fpmap:FPMap, ucmap:UCMap, lumap:LUMap) {
  
  stmap.pirMap = this
  ipmap.pirMap = this
  opmap.pirMap = this
  ctmap.pirMap = this
  smmap.pirMap = this
  simap.pirMap = this
  somap.pirMap = this
  ucmap.pirMap = this
  lumap.pirMap = this

  def set(cp:CLMap):PIRMap = PIRMap(cp   , vimap, vomap, smmap, ctmap, simap, somap, fbmap, rcmap, stmap, ipmap, opmap, fpmap, ucmap, lumap)
  def set(cp:VIMap):PIRMap = PIRMap(clmap, cp   , vomap, smmap, ctmap, simap, somap, fbmap, rcmap, stmap, ipmap, opmap, fpmap, ucmap, lumap)
  def set(cp:VOMap):PIRMap = PIRMap(clmap, vimap, cp   , smmap, ctmap, simap, somap, fbmap, rcmap, stmap, ipmap, opmap, fpmap, ucmap, lumap)
  def set(cp:SMMap):PIRMap = PIRMap(clmap, vimap, vomap, cp   , ctmap, simap, somap, fbmap, rcmap, stmap, ipmap, opmap, fpmap, ucmap, lumap)
  def set(cp:CTMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, cp   , simap, somap, fbmap, rcmap, stmap, ipmap, opmap, fpmap, ucmap, lumap)
  def set(cp:SIMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, ctmap, cp   , somap, fbmap, rcmap, stmap, ipmap, opmap, fpmap, ucmap, lumap)
  def set(cp:SOMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, ctmap, simap, cp   , fbmap, rcmap, stmap, ipmap, opmap, fpmap, ucmap, lumap)
  def set(cp:FBMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, ctmap, simap, somap, cp   , rcmap, stmap, ipmap, opmap, fpmap, ucmap, lumap)
  def set(cp:RCMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, ctmap, simap, somap, fbmap, cp   , stmap, ipmap, opmap, fpmap, ucmap, lumap)
  def set(cp:STMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, ctmap, simap, somap, fbmap, rcmap, cp   , ipmap, opmap, fpmap, ucmap, lumap)
  def set(cp:IPMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, ctmap, simap, somap, fbmap, rcmap, stmap, cp   , opmap, fpmap, ucmap, lumap)
  def set(cp:OPMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, ctmap, simap, somap, fbmap, rcmap, stmap, ipmap, cp   , fpmap, ucmap, lumap)
  def set(cp:FPMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, ctmap, simap, somap, fbmap, rcmap, stmap, ipmap, opmap, cp   , ucmap, lumap)
  def set(cp:UCMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, ctmap, simap, somap, fbmap, rcmap, stmap, ipmap, opmap, fpmap, cp   , lumap)
  def set(cp:LUMap):PIRMap = PIRMap(clmap, vimap, vomap, smmap, ctmap, simap, somap, fbmap, rcmap, stmap, ipmap, opmap, fpmap, ucmap, cp   )

  def setCL(k:CLMap.K, v:CLMap.V):PIRMap = set(clmap + (k -> v))
  def setVI(k:VIMap.K, v:VIMap.V):PIRMap = set(vimap + (k -> v))
  def setVO(k:VOMap.K, v:VOMap.V):PIRMap = set(vomap + (k -> v))
  def setSM(k:SMMap.K, v:SMMap.V):PIRMap = set(smmap + (k -> v))
  def setCt(k:CTMap.K, v:CTMap.V):PIRMap = set(ctmap + (k -> v))
  def setSI(k:SIMap.K, v:SIMap.V):PIRMap = set(simap + (k -> v))
  def setSO(k:SOMap.K, v:SOMap.V):PIRMap = set(somap + (k -> v))
  def setFB(k:FBMap.K, v:FBMap.V):PIRMap = set(fbmap + (k -> v))
  def setRC(k:RCMap.K, v:RCMap.V):PIRMap = set(rcmap + (k -> v))
  def setST(k:STMap.K, v:STMap.V):PIRMap = set(stmap + (k -> v))
  def setIP(k:IPMap.K, v:IPMap.V):PIRMap = set(ipmap + (k -> v))
  def setOP(k:OPMap.K, v:OPMap.V):PIRMap = set(opmap + (k -> v))
  def setFP(k:FPMap.K, v:FPMap.V):PIRMap = set(fpmap + (k -> v))
  def setUC(k:UCMap.K, v:UCMap.V):PIRMap = set(ucmap + (k -> v))
  def setLU(k:LUMap.K, v:LUMap.V):PIRMap = set(lumap + (k -> v))

  def printMap(implicit p:Printer, design:Design):Unit = {
    fbmap.printMap
    design.top.ctrlers.foreach { cl => 
      if (clmap.map.contains(cl)) {
        val pcl = clmap.map(cl)
        p.emitBlock( s"$cl -> $pcl" ) {
          if (!cl.isInstanceOf[TT]) { //TODO
            simap.printMap(cl.sins)
            somap.printMap(cl.souts)
          }
          vimap.printMap(cl.vins)
          cl match {
            case cu:CU =>
              val pcu = clmap.map(cu).asInstanceOf[PCU]
              cu match {
                case icu:ICL => smmap.printMap(icu.srams)
                case _ =>
              }
              ctmap.printCCMap(cu.cchains)
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
    fbmap.printMap
    design.arch.ctrlers.foreach { pcl => 
      if (clmap.pmap.contains(pcl)) {
        val cl = clmap.pmap(pcl)
        p.emitBlock( s"$pcl <- $cl" ) {
          if (!cl.isInstanceOf[TT]) { //TODO
            simap.printMap(cl.sins)
            somap.printMap(cl.souts)
          }
          vimap.printMap(cl.vins)
          pcl match {
            case pcu:PCU =>
              val cu = clmap.pmap(pcu).asInstanceOf[CU]
              smmap.printPMap(pcu.srams)
              ctmap.printPMap(pcu.ctrs)
              rcmap.printMap(rcmap.keys.filter(k => k.ctrler==cu).toList)
              stmap.printPMap(pcu.stages)
              lumap.printPMap(pcu.ctrlBox.luts)
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
    PIRMap(CLMap.empty, VIMap.empty, VOMap.empty, SMMap.empty, CTMap.empty, 
           SIMap.empty, SOMap.empty, FBMap.empty,
           RCMap.empty, STMap.empty, IPMap.empty, OPMap.empty,
           FPMap.empty, UCMap.empty, LUMap.empty)
}

trait PMap {
  type K
  type V
  type M = Map[K, V]
  var pirMap:PIRMap = _
  val map:M
  def contains(k:K) = map.contains(k)
  def apply(k:K):V = map(k)
  val name:String = this.getClass().getSimpleName() 
  def keys = map.keys
  def get(k:K) = map.get(k)

  def check(rec:(K,V)):Unit =  {
    if (map.contains(rec._1) && map(rec._1)!=rec._2)
      throw PIRException(s"${name} already contains key ${rec._1} -> ${map(rec._1)} but try to rebind to ${rec._2}")
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
          if (pmap.contains(k))
            p.emitln(s"$k <- ${pmap(k)}")
          //if (!pmap.contains(k))
          //  p.emitln(s"$k <- no map")
          //else
          //  p.emitln(s"$k <- ${pmap(k)}")
        }
      }
    }
  }
  def printPMap(ks:List[V], lambda:V=>Unit)(implicit p:Printer):Unit = {
    if (ks.size!=0) {
      p.emitBlock(name) {
        ks.foreach{ k =>
          if (pmap.contains(k))
            p.emitBlock(s"$k <- ${pmap(k)}") { lambda(k) }
          //if (!pmap.contains(k))
          //  p.emitln(s"$k <- no map")
          //else {
          //  p.emitBlock(s"$k <- ${pmap(k)}") { lambda(k) }
          //}
        }
      }
    }
  }
}
trait BMapObj extends PMapObj {
  type PM = Map[V, K]
}

trait SMap extends PMap {
  type PM = Map[V,Set[K]]
  val pmap:PM
}
trait SMapObj extends PMapObj {
  type PM = Map[V,Set[K]]
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

/* A mapping between a Input (VecIn or ScalarIn) with PInBus */
case class VIMap(map:VIMap.M, pmap:VIMap.PM) extends BMap {
  type K = VIMap.K
  type V = VIMap.V
  override def + (rec:(K,V)) = { super.check(rec); VIMap(map + rec, pmap + rec.swap) }
}
object VIMap extends BMapObj {
  type K = VI
  type V = PIB
  def empty:VIMap = VIMap(Map.empty, Map.empty)
}

/* A mapping between a Input (VecIn or ScalarIn) with PInBus */
case class VOMap(map:VOMap.M, pmap:VOMap.PM) extends BMap {
  type K = VOMap.K
  type V = VOMap.V
  override def + (rec:(K,V)) = { super.check(rec); VOMap(map + rec, pmap + rec.swap) }
}
object VOMap extends BMapObj {
  type K = VO
  type V = POB
  def empty:VOMap = VOMap(Map.empty, Map.empty)
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
  def printPCtr(pctr:V)(implicit p:Printer) = {
    val ipmap = pirMap.ipmap 
    val opmap = pirMap.opmap
    ipmap.printInPort(pctr.min)
    ipmap.printInPort(pctr.max)
    ipmap.printInPort(pctr.step)
    opmap.printOutPort(pctr.out)
    if (pmap.contains(pctr)) {
      val ctr = pmap(pctr) 
      p.emitln(s"en: ${ctr.en.from}")
      p.emitln(s"done: ${ctr.done.to}")
    }
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
  override def printMap(ks:List[K])(implicit p:Printer):Unit = {
    val ipmap = pirMap.ipmap
    val opmap = pirMap.opmap
    def printScalarIn(s:K) = {
      opmap.printOutPort(s.out)
    }
    super.printMap(ks.asInstanceOf[List[K]], printScalarIn)
  }
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
  override def printMap(ks:List[K])(implicit p:Printer):Unit = {
    val ipmap = pirMap.ipmap
    val opmap = pirMap.opmap
    def printScalarOut(s:K) = {
      ipmap.printInPort(s.in)
    }
    super.printMap(ks.asInstanceOf[List[K]], printScalarOut)
  }
}
object SOMap extends PMapObj {
  type K = SO 
  type V = PSO 
  def empty:SOMap = SOMap(Map.empty)
}

/* A mapping between a scalar value and its writer's (OutBus, Index of Scalar Port in the Bus) */
case class RCMap(map:RCMap.M) extends PMap {
  type K = RCMap.K
  type V = RCMap.V
  override def + (rec:(K,V)) = { super.check(rec); RCMap(map + rec) }
}
object RCMap extends PMapObj {
  type K = Reg 
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
case class FBMap(map:FBMap.M) extends PMap {
  type K = FBMap.K
  type V = FBMap.V
  override def + (rec:(K,V)) = { super.check(rec); FBMap(map + rec) }
}
object FBMap extends PMapObj {
  type K = PIB
  type V = POB
  def empty:FBMap = FBMap(Map.empty)
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
    } //else {
      //p.emitln(s"${pip} -> no map")
    //}
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
    }// else {
      //p.emitln(s"${pop} <- no map")
    //}
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

case class UCMap(map:UCMap.M, pmap:UCMap.PM) extends BMap {
  type K = UCMap.K
  type V = UCMap.V
  override def + (rec:(K,V)) = { super.check(rec); UCMap(map + rec, pmap + rec.swap) }
}
object UCMap extends BMapObj {
  type K = UC
  type V = PUC
  def empty:UCMap = UCMap(Map.empty, Map.empty)
}

case class LUMap(map:LUMap.M, pmap:LUMap.PM) extends BMap {
  type K = LUMap.K
  type V = LUMap.V
  override def + (rec:(K,V)) = { super.check(rec); LUMap(map + rec, pmap + rec.swap) }
}
object LUMap extends BMapObj {
  type K = LUT
  type V = PLUT
  def empty:LUMap = LUMap(Map.empty, Map.empty)
}
