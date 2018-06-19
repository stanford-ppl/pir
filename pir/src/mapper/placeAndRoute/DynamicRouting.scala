package pir
package mapper

import pir.node._
import spade.node._

trait DynamicRouting extends Routing {

  import PIRConfig._
  
  override def set(
    pmap:PIRMap, 
    route:Route, 
    headP:GlobalIO, 
    tailP:GlobalIO
  ):EOption[PIRMap] = if (isDynamicLink(route)) dbgblk(s"set route from ${quote(headP)} to ${quote(tailP)}",buffer=false){
    Right(setMarker(pmap, route, headP, tailP))
  } else super.set(pmap, route, headP, tailP)

}
