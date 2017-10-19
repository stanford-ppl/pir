package pir.mapper

import pir._
import pir.util.typealias._

import pirc.enums._

class FifoMapper(implicit val design:PIR) extends Mapper with LocalRouter {
  type N = LMem
  type R = PLMem
  val typeStr = "FifoMapper"
  override def debug = PIRConfig.debugSFifoMapper
  import pirmeta.{indexOf => _, nameOf => _, _}
  import spademeta._

  def finPass(cu:CL)(m:M):M = m 

  def constrain(n:N, r:R, map:M):M = {
    var mp = map
    mp = mp.setPM(n, r)
    mp = mapOutput(n.readPort, r.readPort, mp)
    mp = mapInput(n.writePort, r.writePort, mp)
    mp = mapMux(n.writePortMux, r.writePortMux, mp)
    mp
  }

  def resFunc(n:N, m:M, triedRes:List[R]):List[R] = log[List[R]](s"resFunc(${n.ctrler}.$n)"){
    (n match {
      case n:SMem => getFIFOs(n, m)
      case n => getFIFOs(n, m)
    }).filterNot { r => triedRes.contains(r) || m.pmmap.contains(r) }
  }

  def getFIFOs(n:N, m:M):List[R] = log[List[R]](s"getFIFOs($n)") {
    def visitOut(x:Any):Iterable[Any] = x match {
      case x:PVMux[_] => Set(x.out)
      case x:PGI[_] => Set(x.ic)
      case x:PAT => Set() // Add control primitive
      case x:PD => Set()
      case x => spade.util.visitOut(x)
    }
    val ins = pir.util.collectIn[GI](n.writePort)
    val pins = ins.map { in => m.vimap(in) }
    dprintln(s"pins=${quote(pins)}")
    pins.map { pin => 
      val pmems = spade.util.collectOut[R](pin, visitFunc=visitOut _) 
      dprintln(s"pin=${quote(pin)} pmems=${quote(pmems)}")
      pmems
    }.reduce { _ intersect _ }.toList
  }

  def matchName(n:N, r:R, prefix:String):Boolean = {
    val nname = n.name
    val rname = nameOf.get(r)
    if (nname.isEmpty || rname.isEmpty) return false
    val nn = nname.get
    val rn = rname.get
    if (!rn.contains(prefix)) return false
    nn.contains(rn.replace(prefix,""))
  }

  def getFIFOs(n:SMem, m:M):List[R] = log[List[R]](s"getFIFOs($n)"){
    val cu = n.ctrler
    val pcu = m.pmmap(cu)
    var reses = pcu.sfifos
    dprintln(s"sfifos=${quote(reses.map { f => (f, nameOf.get(f))})}")
    cu match {
      case cu:MC if n.name.get=="data" => 
        reses = reses.filter { fifo => matchName(n, fifo, "") }
      case cu:MC if cu.mctpe==TileLoad => 
        reses = reses.filter { fifo => matchName(n, fifo, "r") }
      case cu:MC if cu.mctpe==TileStore => 
        reses = reses.filter { fifo => matchName(n, fifo, "w") }
      case cu => 
    }
    dprintln(s"MC filtered reses=${quote(reses)}")
    reses
  }

  def map(cu:CU, pirMap:M):M = {
    log[M](cu, buffer=true) {
      bind[R,N,M](
        allNodes=cu.lmems,
        initMap=pirMap, 
        constrain=constrain _,
        resFunc=resFunc _, //(n, m, triedRes) => List[R]
        finPass=(m:M) => m
      )
    }
  }

  def map(cu:CL, pirMap:M):M = {
    cu match {
      case cu:CU => map(cu, pirMap)
      case cu => finPass(cu)(pirMap)
    }
  }
}
