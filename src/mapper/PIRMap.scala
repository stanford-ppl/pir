package pir.mapper
import pir._
import pir.codegen.{Printer}
import pir.util.typealias._
import pir.util.maps._

import pir.pass.PIRMapping
import pir.exceptions._
import scala.collection.immutable.Set
import scala.collection.immutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.reflect.runtime.universe._
import scala.language.existentials

case class PIRMap(clmap:CLMap, vimap:VIMap, vomap:VOMap, 
  mkmap:MKMap, smmap:SMMap, ctmap:CTMap,
  fimap:FIMap, rcmap:RCMap, stmap:STMap, 
  ipmap:IPMap, opmap:OPMap, pmmap:PMMap, 
  rtmap:RTMap
  ) {
  
  //stmap.pirMap = this
  //ipmap.pirMap = this
  //opmap.pirMap = this
  //ctmap.pirMap = this
  //smmap.pirMap = this
  //ucmap.pirMap = this
  //pmmap.pirMap = this

  def set(cp:CLMap):PIRMap = PIRMap(cp   , vimap, vomap, mkmap, smmap, ctmap, fimap, rcmap, stmap, ipmap, opmap, pmmap, rtmap)
  def set(cp:VIMap):PIRMap = PIRMap(clmap, cp   , vomap, mkmap, smmap, ctmap, fimap, rcmap, stmap, ipmap, opmap, pmmap, rtmap)
  def set(cp:VOMap):PIRMap = PIRMap(clmap, vimap, cp   , mkmap, smmap, ctmap, fimap, rcmap, stmap, ipmap, opmap, pmmap, rtmap)
  def set(cp:MKMap):PIRMap = PIRMap(clmap, vimap, vomap, cp   , smmap, ctmap, fimap, rcmap, stmap, ipmap, opmap, pmmap, rtmap)
  def set(cp:SMMap):PIRMap = PIRMap(clmap, vimap, vomap, mkmap, cp   , ctmap, fimap, rcmap, stmap, ipmap, opmap, pmmap, rtmap)
  def set(cp:CTMap):PIRMap = PIRMap(clmap, vimap, vomap, mkmap, smmap, cp   , fimap, rcmap, stmap, ipmap, opmap, pmmap, rtmap)
  def set(cp:FIMap):PIRMap = PIRMap(clmap, vimap, vomap, mkmap, smmap, ctmap, cp   , rcmap, stmap, ipmap, opmap, pmmap, rtmap)
  def set(cp:RCMap):PIRMap = PIRMap(clmap, vimap, vomap, mkmap, smmap, ctmap, fimap, cp   , stmap, ipmap, opmap, pmmap, rtmap)
  def set(cp:STMap):PIRMap = PIRMap(clmap, vimap, vomap, mkmap, smmap, ctmap, fimap, rcmap, cp   , ipmap, opmap, pmmap, rtmap)
  def set(cp:IPMap):PIRMap = PIRMap(clmap, vimap, vomap, mkmap, smmap, ctmap, fimap, rcmap, stmap, cp   , opmap, pmmap, rtmap)
  def set(cp:OPMap):PIRMap = PIRMap(clmap, vimap, vomap, mkmap, smmap, ctmap, fimap, rcmap, stmap, ipmap, cp   , pmmap, rtmap)
  def set(cp:PMMap):PIRMap = PIRMap(clmap, vimap, vomap, mkmap, smmap, ctmap, fimap, rcmap, stmap, ipmap, opmap, cp   , rtmap)
  def set(cp:RTMap):PIRMap = PIRMap(clmap, vimap, vomap, mkmap, smmap, ctmap, fimap, rcmap, stmap, ipmap, opmap, pmmap, cp   )

  def setCL(k:CLMap.K, v:CLMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(clmap + ((k, v))))
  def setVI(k:VIMap.K, v:VIMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(vimap + ((k, v))))
  def setVO(k:VOMap.K, v:VOMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(vomap + ((k, v))))
  def setMK(k:MKMap.K, v:MKMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(mkmap + ((k, v))))
  def setSM(k:SMMap.K, v:SMMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(smmap + ((k, v))))
  def setCT(k:CTMap.K, v:CTMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(ctmap + ((k, v))))
  def setFI(k:FIMap.K, v:FIMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(fimap + ((k, v))))
  def setRC(k:RCMap.K, v:RCMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(rcmap + ((k, v))))
  def setST(k:STMap.K, v:STMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(stmap + ((k, v))))
  def setIP(k:IPMap.K, v:IPMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(ipmap + ((k, v))))
  def setOP(k:OPMap.K, v:OPMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(opmap + ((k, v))))
  def setPM(k:PMMap.K, v:PMMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(pmmap + ((k, v))))
  def setRT(k:RTMap.K, v:RTMap.V)(implicit mper:Mapper, design:Design):PIRMap = wrap(set(rtmap + ((k, v))))

  def wrap(func: => PIRMap)(implicit mapper:Mapper, design:Design):PIRMap = {
    try {
      func
    } catch {
      case e:Exception => throw PassThroughException(e, this) 
    }
  }

  def quote(n:Any)(implicit design:Design) = n match {
    case n:Node => pir.util.quote(n)
    case n:PNode => pir.plasticine.util.quote(n)(design.arch)
    case n => s"$n"
  }

  def printMap(implicit p:Printer, design:Design):Unit = {
    fimap.printMap(quote _)
    design.top.ctrlers.foreach { cl => 
      cl match {
        case cl:CL =>
          if (clmap.map.contains(cl)) {
            val pcl = clmap.map(cl)
            p.emitBlock( s"$cl -> ${quote(pcl)}" ) {
              vimap.printMap(quote _, cl.vins)
              cl match {
                case cu:CU =>
                  val pcu = clmap.map(cu).asInstanceOf[PCU]
                  cu match {
                    case icl:ICL => smmap.printMap(quote _, icl.mems)
                    case _ =>
                  }
                  rcmap.printMap(quote _, rcmap.keys.filter(k => k.ctrler==cu).toList)
                  //stmap.printPMap(pcu.stages)
                  stmap.printMap(quote _, stmap.keys.filter(k => k.ctrler==cu).toList)
                case _ =>
              }
            }
          } else {
            p.emitln(s"$cl <- failed")
          }
        case _ =>
          p.emitln(s"$cl <- no mapping")
      }
    }
  }

  def printStage(pst:PST)(implicit p:Printer, design:Design):Unit = {
    import p._
    emitBlock(s"${quote(pst)} <- ${stmap.pmap.get(pst)}"){
      pst.prs.foreach { ppr =>
        fimap.get(ppr.in).foreach { from =>
          emitln(s"${quote(ppr)}.in <= $from")
        }
      }
      pst.funcUnit.foreach { fu =>
        fu.operands.foreach { oprd =>
          fimap.get(oprd).foreach { from => 
            emitln(s"${oprd} <= $from")
          }
        }
        stmap.pmap.get(pst).foreach { st =>
          emitln(s"$fu.op=${st.fu.get.op}")
        }
      }
    }
  }

  def printPMap(implicit p:Printer, design:Design):Unit = {
    import p._
    //fimap.printMap(quote _)
    design.arch.ctrlers.foreach { pcl => 
      if (clmap.pmap.contains(pcl)) {
        val cl = clmap.pmap(pcl)
        emitBlock( s"${quote(pcl)} <- $cl" ) {
          vimap.printMap(quote _, cl.vins)
          pcl match {
            case pcu:PCU =>
              val cu = clmap.pmap(pcu).asInstanceOf[CU]
              smmap.printPMap(pcu.mems)
              ctmap.printPMap(pcu.ctrs)
              rcmap.printMap(quote _, rcmap.keys.filter(k => k.ctrler==cu).toList)
              //stmap.printPMap(pcu.stages)
              pcu.stages.foreach { pst =>
                printStage(pst)
              }
              //pmmap.printPMap(pcu.ctrlBox.luts)
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
    PIRMap(CLMap.empty, VIMap.empty, VOMap.empty, MKMap.empty,
           SMMap.empty, CTMap.empty, 
           FIMap.empty,
           RCMap.empty, STMap.empty, IPMap.empty, OPMap.empty,
           PMMap.empty, RTMap.empty)
}

case class CLMap(map:CLMap.M, pmp:CLMap.IM) extends IBiOneToOneMap {
  type K = CLMap.K
  type V = CLMap.V
  override type M = CLMap.M
  override type IM = CLMap.IM
  override def + (rec:(K,V)) = { super.check(rec); CLMap(map + rec, pmp + rec.swap) }
  def apply(k:PL):PCU = { map(k).asInstanceOf[PCU] }
  def apply(k:OCL):POCU = { map(k).asInstanceOf[POCU] }
  def apply(k:MC):PMC = { map(k).asInstanceOf[PMC] }
  def apply(k:MP):PMCU = { map(k).asInstanceOf[PMCU] }
  def apply(k:Top):PTop = { map(k).asInstanceOf[PTop] }
  def pmap:IM = pmp
  def pmap(v:PCU):CU = pmp(v).asInstanceOf[CU]
  def pmap(v:PMCU):MP = pmp(v).asInstanceOf[MP]
  def pmap(v:PMC):MC = pmp(v).asInstanceOf[MC]
}
object CLMap extends IBiOneToOneObj {
  type K = CL
  type V = PCL
  def empty:CLMap = CLMap(Map.empty, Map.empty)
}

/* A mapping between a Input (VecIn or ScalarIn) with PInBus */
case class VIMap(map:VIMap.M, pmap:VIMap.IM) extends IBiManyToOneMap {
  type K = VIMap.K
  type V = VIMap.V
  override type M = VIMap.M
  override type IM = VIMap.IM
  override def + (rec:(K,V)) = { 
    super.check(rec)
    val set:Set[K] = (pmap.getOrElse(rec._2, Set.empty) + rec._1)
    val v:V = rec._2
    val npmap:IM = pmap + ((v, set))
    VIMap(map + rec, npmap)
  }
  def apply(n:VI):PGI[PModule] = { map(n).asGlobal }
  def apply(n:SI):PGI[PModule] = { map(n).asGlobal }
}
object VIMap extends IBiManyToOneObj {
  type K = Node //InPort or VecIn
  type V = PGI[PModule]
  def empty:VIMap = VIMap(Map.empty, Map.empty)
}

/* A mapping between a Input (VecIn or ScalarIn) with PInBus */
case class VOMap(map:VOMap.M, pmap:VOMap.IM) extends IBiOneToManyMap {
  type K = VOMap.K
  type V = VOMap.V
  override type M = VOMap.M
  override type IM = VOMap.IM
  override def + (rec:(K,V)) = { 
    super.check(rec)
    val os:Set[V] = map.getOrElse(rec._1, Set.empty)
    val set:Set[V] = os + rec._2
    val newmap = map + (rec._1 -> set)
    VOMap(newmap, pmap + rec.swap)
  }
  def apply(n:VO):Set[PGO[PModule]] = { map(n) }
  def apply(n:SO):Set[PGO[PModule]] = { map(n) }
}
object VOMap extends IBiOneToManyObj {
  type K = Node //OutPort or VecOut
  type V = PGO[PModule]
  def empty:VOMap = VOMap(Map.empty, Map.empty)
}

/* A mapping between a Input (VecIn or ScalarIn) with PInBus */
case class MKMap(map:MKMap.M) extends IOneToOneMap {
  type K = MKMap.K
  type V = MKMap.V
  override type M = MKMap.M
  override def + (rec:(K,V)) = { super.check(rec); MKMap(map + rec) }
}
object MKMap extends IOneToOneObj {
  type K = PIO[PModule] //InPort or VecIn
  type V = Node
  def empty:MKMap = MKMap(Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
case class SMMap(map:SMMap.M, pmap:SMMap.IM) extends IBiOneToOneMap {
  type K = SMMap.K
  type V = SMMap.V
  override type M = SMMap.M
  override type IM = SMMap.IM
  override def + (rec:(K,V)) = { super.check(rec); SMMap(map + rec, pmap + rec.swap) }
  def apply(n:SRAM):PSRAM = { map(n).asSRAM }
  def apply(n:FIFO):PBuf = { map(n).asBuf }
}
object SMMap extends IBiOneToOneObj {
  type K = OCM
  type V = POCM
  def empty:SMMap = SMMap(Map.empty, Map.empty)
}

/* A Map between PIR Counter to Spade Counter */
case class CTMap(map:CTMap.M, pmap:CTMap.IM) extends IBiOneToOneMap {
  type K = CTMap.K
  type V = CTMap.V
  override type M = CTMap.M
  override type IM = CTMap.IM
  override def + (rec:(K,V)) = { super.check(rec); CTMap(map + rec, pmap + rec.swap) }
}
object CTMap extends IBiOneToOneObj {
  type K = Ctr
  type V = PCtr
  def empty:CTMap = CTMap(Map.empty, Map.empty)
}

/* A mapping between a scalar value and its writer's (OutBus, Index of Scalar Port in the Bus) */
case class RCMap(map:RCMap.M, pmap:RCMap.IM) extends IBiManyToManyMap {
  type K = RCMap.K
  type V = RCMap.V
  override type M = RCMap.M
  override def + (rec:(K,V)) = { 
    check(rec); 
    val fset:Set[V] = (map.getOrElse(rec._1, Set.empty) + rec._2)
    val rset:Set[K] = (pmap.getOrElse(rec._2, Set.empty) + rec._1)
    val k:K = rec._1
    val v:V = rec._2
    RCMap(map + ((k, fset)), pmap + ((v, rset)))
  }
}
object RCMap extends IBiManyToManyObj {
  type K = Reg 
  type V = PReg 
  def empty:RCMap = RCMap(Map.empty, Map.empty)
}
/* A mapping between Stage and PStage */
case class STMap(map:STMap.M, pmap:STMap.IM) extends IBiOneToOneMap {
  type K = STMap.K
  type V = STMap.V
  override type M = STMap.M
  override type IM = STMap.IM
  override def + (rec:(K,V)) = { super.check(rec); STMap(map + rec, pmap + rec.swap) }
}
object STMap extends IBiOneToOneObj {
  type K = ST 
  type V = PST
  def empty:STMap = STMap(Map.empty, Map.empty)
}
/* FanIn map: a mapping between a PInput and the POutput it connects to */
case class FIMap(map:FIMap.M, pmap:FIMap.IM) extends IBiManyToOneMap {
  type K = FIMap.K
  type V = FIMap.V
  override type M = FIMap.M
  override def check(rec:(K,V)):Unit =  {
    super.check(rec)
    val (i, o) = rec
    assert(i.canConnect(o), s"$i cannot connect to $o but trying map $i to $o in FIMap")
  }
  override def + (rec:(K,V)) = { 
    check(rec); 
    val set:Set[K] = (pmap.getOrElse(rec._2, Set.empty) + rec._1)
    val v:V = rec._2
    val npmap:IM = pmap + ((v, set))
    FIMap(map + rec, npmap)
  }
  def get(k:PGI[_<:PModule]) = { map.get(k).asInstanceOf[Option[PGO[_<:PModule]]] }
}
object FIMap extends IBiManyToOneObj {
  type K = PI[_<:PModule]
  type V = PO[_<:PModule]
  def empty:FIMap = FIMap(Map.empty, Map.empty)
}
/* A mapping between InPort and PInPort */
case class IPMap(map:IPMap.M, pmap:IPMap.IM) extends IBiOneToOneMap {
  type K = IPMap.K
  type V = IPMap.V
  override type M = IPMap.M
  override type IM = IPMap.IM
  override def + (rec:(K,V)) = { super.check(rec); IPMap(map + rec, pmap + rec.swap) }
}
object IPMap extends IBiOneToOneObj {
  type K = IP 
  type V = PI[_<:PModule]
  def empty:IPMap = IPMap(Map.empty, Map.empty)
}
/* A mapping between OutPort and the POutPort */
case class OPMap(map:OPMap.M, pmap:OPMap.IM) extends IBiOneToManyMap {
  type K = OPMap.K
  type V = OPMap.V
  override type M = OPMap.M
  override type IM = OPMap.IM
  override def + (rec:(K,V)):OPMap = { 
    val os:Set[V] = map.getOrElse(rec._1, Set.empty)
    val set:Set[V] = os + rec._2
    val newmap = map + (rec._1 -> set)
    OPMap(newmap, pmap + rec.swap)
  }
}
object OPMap extends IBiOneToManyObj {
  type K = OP
  type V = PO[PModule]
  def empty:OPMap = OPMap(Map.empty, Map.empty)
}

/* Primitive Node Mapping */
case class PMMap(map:PMMap.M, pmp:PMMap.IM) extends IBiOneToOneMap {
  type K = PMMap.K
  type V = PMMap.V
  override type M = PMMap.M
  override type IM = PMMap.IM
  override def + (rec:(K,V)) = { super.check(rec); PMMap(map + rec, pmp + rec.swap) }
  def apply(k:LUT):PLUT = { map(k).asInstanceOf[PLUT] }
  def apply(k:Const):PConst = { map(k).asInstanceOf[PConst] }
  def apply(k:UC):PUC = { map(k).asInstanceOf[PUC] }
  def apply(k:MCCB):PMCCB = { map(k).asInstanceOf[PMCCB] }
  def apply(k:OCB):POCB = { map(k).asInstanceOf[POCB] }
  def apply(k:D):PD = { map(k).asInstanceOf[PD] }
  def apply(k:CB):PCB = { map(k).asInstanceOf[PCB] }
  def pmap(v:PConst):Const = { pmp(v).asInstanceOf[Const] }
  def pmap(v:PUC):UC = { pmp(v).asInstanceOf[UC] }
  def pmap(v:PAT):AT = { pmp(v).asInstanceOf[AT] }
  def pmap(v:PPDU):PDU = { pmp(v).asInstanceOf[PDU] }
  def pmap:IM = pmp
  def get(v:PConst):Option[Const] = { pmp.get(v).asInstanceOf[Option[Const]] }
}
object PMMap extends IBiOneToOneObj {
  type K = Node
  type V = PNode
  def empty:PMMap = PMMap(Map.empty, Map.empty)
}

case class RTMap(map:RTMap.M) extends IOneToOneMap {
  type K = RTMap.K
  type V = RTMap.V
  override type M = RTMap.M
  override def + (rec:(K,V)) = { super.check(rec); RTMap(map + rec) }
}
object RTMap extends IOneToOneObj {
  type K = Any //InPort or VecIn or Delay
  type V = Int 
  def empty:RTMap = RTMap(Map.empty)
}
