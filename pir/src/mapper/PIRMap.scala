package pir.mapper

import pir._
import pir.util.typealias._

import spade.config._

import pirc._
import pirc.exceptions._
import pirc.collection.immutable._

case class PIRMap(vimap:VIMap, vomap:VOMap, 
  mkmap:MKMap, 
  fimap:FIMap, rcmap:RCMap,
  ipmap:IPMap, opmap:OPMap, pmmap:PMMap, 
  rtmap:RTMap, cfmap:CFMap
  ) extends SpadeMap {

  private def copy(
    vimap:VIMap=vimap,
    vomap:VOMap=vomap,
    mkmap:MKMap=mkmap,
    fimap:FIMap=fimap,
    rcmap:RCMap=rcmap,
    ipmap:IPMap=ipmap,
    opmap:OPMap=opmap,
    pmmap:PMMap=pmmap,
    rtmap:RTMap=rtmap,
    cfmap:CFMap=cfmap
  ) = {
    PIRMap(vimap, vomap, mkmap, fimap, rcmap, ipmap, opmap, pmmap, rtmap, cfmap)
  }

  def setVI(k:VIMap.K, v:VIMap.V):PIRMap = copy(vimap=vimap + ((k, v)))
  def setVO(k:VOMap.K, v:VOMap.V):PIRMap = copy(vomap=vomap + ((k, v)))
  def setMK(k:MKMap.K, v:MKMap.V):PIRMap = copy(mkmap=mkmap + ((k, v)))
  def setRC(k:RCMap.K, v:RCMap.V):PIRMap = copy(rcmap=rcmap + ((k, v)))
  def setIP(k:IPMap.K, v:IPMap.V):PIRMap = copy(ipmap=ipmap + ((k, v)))
  def setOP(k:OPMap.K, v:OPMap.V):PIRMap = copy(opmap=opmap + ((k, v)))
  def setPM(k:PMMap.K, v:PMMap.V):PIRMap = copy(pmmap=pmmap + ((k, v)))
  def setRT(k:RTMap.K, v:RTMap.V):PIRMap = copy(rtmap=rtmap + ((k, v)))

  override def setFI(k:FIMap.K, v:FIMap.V):PIRMap = copy(fimap=fimap + ((k, v)))
  override def setCF(k:CFMap.K, v:CFMap.V):PIRMap = copy(cfmap=cfmap + ((k, v)))

  def quote(n:Any)(implicit design:PIR) = n match {
    case n:Node => pir.util.quote(n)
    case n:PNode => spade.util.quote(n)(design.arch)
    case n => s"$n"
  }

  def printMap(implicit p:Printer, design:PIR):Unit = {
    fimap.printMap(quote _)
    design.top.ctrlers.foreach { cl => 
      cl match {
        case cl:CL =>
          if (pmmap.contains(cl)) {
            val pcl = pmmap.map(cl)
            p.emitBlock( s"$cl -> ${quote(pcl)}" ) {
              vimap.printMap(quote _, cl.vins.toList)
              cl match {
                case cu:CU =>
                  val pcu = pmmap.map(cu).asInstanceOf[PCU]
                  cu match {
                    case icl:ICL => pmmap.printMap(quote _, icl.mems)
                    case _ =>
                  }
                  rcmap.printMap(quote _, rcmap.keys.filter(k => k.ctrler==cu).toList)
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

  def printStage(pst:PST)(implicit p:Printer, design:PIR):Unit = {
    import p._
    emitBlock(s"${quote(pst)} <- ${pmmap.get(pst)}"){
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
        pmmap.get(pst).foreach { 
          case st:ST => emitln(s"$fu.op=${st.fu.get.op}")
          case _ =>
        }
      }
    }
  }

  def printPMap(implicit p:Printer, design:PIR):Unit = {
    import p._
    //fimap.printMap(quote _)
    design.arch.ctrlers.foreach { pcl => 
      if (pmmap.contains(pcl)) {
        val cl = pmmap(pcl)
        emitBlock( s"${quote(pcl)} <- $cl" ) {
          vimap.printMap(quote _, cl.vins.toList)
          pcl match {
            case pcu:PCU =>
              val cu = pmmap(pcu).asInstanceOf[CU]
              pmmap.printPMap(pcu.mems)
              pmmap.printPMap(pcu.ctrs)
              rcmap.printMap(quote _, rcmap.keys.filter(k => k.ctrler==cu).toList)
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
    PIRMap(VIMap.empty, VOMap.empty, MKMap.empty,
           FIMap.empty,
           RCMap.empty, IPMap.empty, OPMap.empty,
           PMMap.empty, RTMap.empty, CFMap.empty)
}

/* A mapping between a Input (VecIn or ScalarIn) with PInBus */
case class VIMap(map:VIMap.M, imap:VIMap.IM) extends IBiManyToOneMap {
  type K = VIMap.K
  type V = VIMap.V
  override type M = VIMap.M
  override type IM = VIMap.IM
  override def + (rec:(K,V)) = { 
    super.check(rec)
    val set:Set[K] = (imap.getOrElse(rec._2, Set.empty) + rec._1)
    val v:V = rec._2
    val npmap:IM = imap + ((v, set))
    VIMap(map + rec, npmap)
  }
  def apply(n:GI):PGI[PModule] = { map(n).asGlobal }

  def apply(v:V):KK = imap(v)
  def get(v:V):Option[KK]         = imap.get(v)
  def contains(v:V) = imap.contains(v)
}
object VIMap extends IBiManyToOneObj {
  type K = I 
  type V = PGI[PModule]
  def empty:VIMap = VIMap(Map.empty, Map.empty)
}

/* A mapping between a Input (VecIn or ScalarIn) with PInBus */
case class VOMap(map:VOMap.M, imap:VOMap.IM) extends IBiOneToManyMap {
  type K = VOMap.K
  type V = VOMap.V
  override type M = VOMap.M
  override type IM = VOMap.IM
  override def + (rec:(K,V)) = { 
    super.check(rec)
    val os:Set[V] = map.getOrElse(rec._1, Set.empty)
    val set:Set[V] = os + rec._2
    val newmap = map + (rec._1 -> set)
    VOMap(newmap, imap + rec.swap)
  }
  def apply(n:GO):Set[PGO[PModule]] = { map(n) }

  def apply(v:V):KK = imap(v)
  def get(v:V):Option[KK]         = imap.get(v)
  def contains(v:V) = imap.contains(v)
}
object VOMap extends IBiOneToManyObj {
  type K = O
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
  type K = PIO[PModule] //Input or VecIn
  type V = Node
  def empty:MKMap = MKMap(Map.empty)
}

/* A mapping between a scalar value and its writer's (OutBus, Index of Scalar Port in the Bus) */
case class RCMap(map:RCMap.M, imap:RCMap.IM) extends IBiManyToManyMap {
  type K = RCMap.K
  type V = RCMap.V
  override type M = RCMap.M
  override def + (rec:(K,V)) = { 
    check(rec); 
    val fset:Set[V] = (map.getOrElse(rec._1, Set.empty) + rec._2)
    val rset:Set[K] = (imap.getOrElse(rec._2, Set.empty) + rec._1)
    val k:K = rec._1
    val v:V = rec._2
    RCMap(map + ((k, fset)), imap + ((v, rset)))
  }

  def apply(v:V):KK = imap(v)
  def get(v:V):Option[KK]         = imap.get(v)
  def contains(v:V) = imap.contains(v)
}
object RCMap extends IBiManyToManyObj {
  type K = Reg 
  type V = PReg 
  def empty:RCMap = RCMap(Map.empty, Map.empty)
}
/* A mapping between Input and PInput */
case class IPMap(map:IPMap.M, imap:IPMap.IM) extends IBiOneToOneMap {
  type K = IPMap.K
  type V = IPMap.V
  override type M = IPMap.M
  override type IM = IPMap.IM
  override def + (rec:(K,V)) = { super.check(rec); IPMap(map + rec, imap + rec.swap) }

  def apply(v:V):KK = imap(v)
  def get(v:V):Option[KK]         = imap.get(v)
  def contains(v:V) = imap.contains(v)
}
object IPMap extends IBiOneToOneObj {
  type K = I 
  type V = PI[_<:PModule]
  def empty:IPMap = IPMap(Map.empty, Map.empty)
}
/* A mapping between Output and the POutput */
case class OPMap(map:OPMap.M, imap:OPMap.IM) extends IBiOneToManyMap {
  type K = OPMap.K
  type V = OPMap.V
  override type M = OPMap.M
  override type IM = OPMap.IM
  override def + (rec:(K,V)):OPMap = { 
    val os:Set[V] = map.getOrElse(rec._1, Set.empty)
    val set:Set[V] = os + rec._2
    val newmap = map + (rec._1 -> set)
    OPMap(newmap, imap + rec.swap)
  }

  def apply(v:V):KK = imap(v)
  def get(v:V):Option[KK]         = imap.get(v)
  def contains(v:V) = imap.contains(v)
}
object OPMap extends IBiOneToManyObj {
  type K = O
  type V = PO[PModule]
  def empty:OPMap = OPMap(Map.empty, Map.empty)
}

/* Primitive Node Mapping */
case class PMMap(map:PMMap.M, imap:PMMap.IM) extends IBiOneToOneMap {
  type K = PMMap.K
  type V = PMMap.V
  override type M = PMMap.M
  override type IM = PMMap.IM
  override def + (rec:(K,V)) = { super.check(rec); PMMap(map + rec, imap + rec.swap) }

  def cast[T](x:Any):T = x.asInstanceOf[T]

  def apply(k:CL):PCL       = cast(map(k))
  def apply(k:PL):PCU       = cast(map(k))
  def apply(k:MP):PMCU      = cast(map(k))
  def apply(k:Top):PTop     = cast(map(k))
  def apply(k:MC):PMC       = cast(map(k))
  def apply(k:Ctr):PCtr     = cast(map(k))
  def apply(k:OCM):POCM     = cast(map(k))
  def apply(k:ST):PST       = cast(map(k))
  def apply(k:Const):PConst = cast(map(k))
  def apply(k:LUT):PLUT     = cast(map(k))
  def apply(k:UC):PUC       = cast(map(k))
  def apply(k:MCCB):PMCCB   = cast(map(k))
  def apply(k:OCB):POCB     = cast(map(k))
  def apply(k:D):PD         = cast(map(k))
  def apply(k:CB):PCB       = cast(map(k))
  def apply(k:PDU):PPDU       = cast(map(k))

  def apply(v:PCL):CL       = cast(imap(v))
  def apply(v:PMCU):MP      = cast(imap(v))
  def apply(v:PTop):Top     = cast(imap(v))
  def apply(v:PMC):MC       = cast(imap(v))
  def apply(v:PCtr):Ctr     = cast(imap(v))
  def apply(v:POCM):OCM     = cast(imap(v))
  def apply(v:PST):ST       = cast(imap(v))
  def apply(v:PConst):Const = cast(imap(v))
  def apply(v:PLUT):LUT     = cast(imap(v))
  def apply(v:PUC):UC       = cast(imap(v))
  def apply(v:PAT):AT       = cast(imap(v))
  def apply(v:PPDU):PDU     = cast(imap(v))

  def get(v:CL):Option[PCL]       = cast(map.get(v))
  def get(v:MP):Option[PMCU]      = cast(map.get(v))
  def get(v:Top):Option[PTop]     = cast(map.get(v))
  def get(v:MC):Option[PMC]       = cast(map.get(v))
  def get(v:Ctr):Option[PCtr]     = cast(map.get(v))
  def get(v:OCM):Option[POCM]     = cast(map.get(v))
  def get(v:ST):Option[PST]       = cast(map.get(v))
  def get(v:Const):Option[PConst] = cast(map.get(v))
  def get(v:LUT):Option[PLUT]     = cast(map.get(v))
  def get(v:UC):Option[PUC]       = cast(map.get(v))
  def get(v:PDU):Option[PPDU]       = cast(map.get(v))

  def get(v:PCL):Option[CL]       = cast(imap.get(v))
  def get(v:PMCU):Option[MP]      = cast(imap.get(v))
  def get(v:PTop):Option[Top]     = cast(imap.get(v))
  def get(v:PMC):Option[MC]       = cast(imap.get(v))
  def get(v:PCtr):Option[Ctr]     = cast(imap.get(v))
  def get(v:POCM):Option[OCM]     = cast(imap.get(v))
  def get(v:PST):Option[ST]       = cast(imap.get(v))
  def get(v:PConst):Option[Const] = cast(imap.get(v))
  def get(v:PLUT):Option[LUT]     = cast(imap.get(v))
  def get(v:PUC):Option[UC]       = cast(imap.get(v))
  def get(v:PPDU):Option[PDU]       = cast(imap.get(v))

  def apply(v:V):KK = imap(v)
  def get(v:V):Option[KK]         = imap.get(v)
  def contains(v:V) = imap.contains(v)
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
  type K = Any //Input or VecIn or Delay
  type V = Int 
  def empty:RTMap = RTMap(Map.empty)
}
