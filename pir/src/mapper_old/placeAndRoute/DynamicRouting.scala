package pir
package mapper

import pir.node._
import spade.node._

trait DynamicRouting extends Routing {

  import PIRConfig._
  
  override def set(
    pmap:PIRMap, 
    route:Route, 
    marker:MKMap.V, 
  ):EOption[PIRMap] = if (isDynamicLink(route)) dbgblk(s"set route for ${quote(marker)}",buffer=false){
    Right(setMarker(pmap, route, marker))
  } else super.set(pmap, route, marker)

}
