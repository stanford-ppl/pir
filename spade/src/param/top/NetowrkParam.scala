package spade
package param

import prism.node._
import prism.collection.mutable.Table

abstract class NetworkParam[B<:PinType:ClassTag] extends Parameter {
  val bct = implicitly[ClassTag[B]]

  val cuTypes = List("arg", "pcu", "ocu", "pmu", "scu", "mc", "dag", "sb", "rt")
  val fourDirections = List("W","N","E","S")
  val diagDirections = List("NW","NE","SE","SW")
  val eightDirections = List("W", "NW", "N", "NE", "E", "SE", "S", "SW")

  val channelWidth:Table[String, String, Int]

}
