package pir
package mapper

import pir.node._
import spade.node._
import prism.mapper._
import prism.collection.immutable._

trait MappingUtil extends RoutingUtil { self:Logging =>

  val pirmeta:PIRMetadata
  import pirmeta._

  def mappedTo[T](n:Any, mapping:Any=pirMap):Option[T] = /*dbgblk(s"mappedTo(${quote(n)}, $mapping)")*/{ // significant slow down with debug print
    ((n, mapping) match {
      case (n, Some(mapping)) => mappedTo[T](n, mapping)
      case (n, None) => None
      case (n, Left(mapping)) => mappedTo[T](n, mapping) 
      case (n, Right(mapping)) => mappedTo[T](n, mapping) 
      case (n, bt@BindingTrace(pnode, mapping)) => mappedTo[T](n, mapping)

      // cumap
      case (n:CUMap.V, mapping:PIRMap) => mappedTo[T](n, mapping.cumap)
      case (n:CUMap.V, mapping:CUMap) => mapping.usedMap.bmap.get(n).asInstanceOf[Option[T]]
      case (n:CUMap.K, mapping:PIRMap) => mappedTo[T](n, mapping.cumap)
      case (n:CUMap.K, mapping:CUMap) => mapping.usedMap.get(n).asInstanceOf[Option[T]]

      // mkmap
      case (edge:prism.node.Edge[_], mapping:PIRMap) => mappedTo[T](edge.src.asInstanceOf[MKMap.K], mapping)
      case (port:MKMap.K, mapping:PIRMap) => mappedTo[T](port, mapping.mkmap)
      case (port:MKMap.K, mapping:MKMap) => mapping.get(port).asInstanceOf[Option[T]]

      case ((from:Edge, to:Edge), mapping) => 
        zipMap(mappedTo[Set[MKMap.V]](from, mapping), mappedTo[Set[MKMap.V]](to, mapping)) { 
          case (tmk, fmk) if tmk == fmk => Some(tmk)
          case _ => None
        }.flatten.asInstanceOf[Option[T]]

      case (n:GlobalInput, mapping:PIRMap) => // Option[PT] input port
        val cuP = globalOf(n).get
        mappedTo[CUMap.V](cuP, mapping).flatMap { cuS =>
          val marker = markerOf(n)
          val ports = mapping.mkmap.bmap(marker).filter { port =>
            routableOf(port).get == cuS
          }
          assertOneOrLess(ports, s"${quote(n)} marker=${quote(marker)}").map { port =>
            assert(isInput(port), s"${quote(n)} is mapped to non input ${quote(port)}")
            port.asInstanceOf[T]
          }
        }

      case (n:GlobalOutput, mapping:PIRMap) => // Option[Set[PT]] output ports
        val cuP = globalOf(n).get
        mappedTo[CUMap.V](cuP, mapping).map { cuS =>
          val marker = markerOf(n)
          val ports = mapping.mkmap.bmap(marker).filter { port =>
            routableOf(port).get == cuS
          }
          ports.map { port =>
            assert(isOutput(port), s"${quote(n)} is mapped to non output ${quote(port)}")
            port
          }.asInstanceOf[T]
        }

      case ((in:GlobalInput, out:GlobalOutput), mapping:PIRMap) => // Option[List[(Outport, Inport)]]
        val cuP = globalOf(out).get
        mappedTo[CUMap.V](cuP, mapping).flatMap { cuS =>
          val marker = markerOf(out)
          mappedTo[MKMap.K](in).flatMap { inport => 
            dbgblk(s"extractRoute(in=${quote(in)} out=${quote(out)}, cuS=${quote(cuS)})") {
              extractRoute(inport, marker, cuS, mapping)
            }
          }
        }.asInstanceOf[Option[T]]

      case (n, mapping) => throw PIRException(s"don't know how to lookup mappedTo(${quote(n)}, $mapping)")
    })
  }

  private def extractRoute(
    inport:MKMap.K, 
    marker:MKMap.V, 
    cuS:CUMap.V, 
    mapping:PIRMap
  ):Option[List[(MKMap.K, MKMap.K)]] = dbgblk(s"extractRoute(${quote(inport)})") {
    val outports = inport.connected.filter { outport => 
      mapping.mkmap.get(outport).fold(false) { _.contains(marker) }
    }
    val outport = assertOneOrLess(outports, s"inport=${quote(inport)} marker=${quote(marker)}")
    outport.flatMap { outport =>
      if (routableOf(outport).get == cuS) {
        Some(List((outport, inport)))
      } else {
        val inports = outport.internal.connected.map{ _.src.asInstanceOf[MKMap.K] }.filter { inport =>
          mapping.mkmap.get(inport).fold(false) { _.contains(marker) }
        }
        assertOneOrLess(inports, s"outport=${quote(outport)} marker=${quote(marker)}").flatMap { nextInport =>
          extractRoute(nextInport, marker, cuS, mapping).map { route =>
            route :+ (outport, inport)
          }
        }
      }
    }
  }

  def isMapped(n:Any, mapping:Any=pirMap):Boolean = mappedTo[Any](n, mapping).nonEmpty
}
