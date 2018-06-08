package pir
package mapper

import pir.node._
import spade.node._

trait DynamicRouting extends Routing {

  import PIRConfig._
  
  private def set(mkmap:MKMap, port:PT, marker:GlobalOutput):MKMap = {
    dbg(s"setMKMap: ${quote(port.src)}.${quote(port)} - ${quote(marker)}")
    mkmap + (port, marker)
  }

  override def set(
    pmap:PIRMap, 
    route:Route, 
    headP:GlobalIO, 
    tailP:GlobalIO
  ):EOption[PIRMap] = if (isDynamic(route)) dbgblk(s"set route from ${quote(headP)} to ${quote(tailP)}",buffer=false){
    Right(pmap).map { pmap =>
      pmap.map[MKMap] { mkmap =>
        var mk = mkmap
        val marker = markerOf(headP)
        route.foreach { case (tailS, headS) =>
          mk = set(mk, tailS, marker)
          mk = set(mk, headS, marker)
        }
        mk
      }
    }
  } else super.set(pmap, route, headP, tailP)

}
