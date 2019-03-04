package pir
package node

sealed trait BitType
case class Fix(s:Boolean, i:Int, f:Int) extends BitType
case class Flt(m:Int, e:Int) extends BitType
case object Bool extends BitType
case object Text extends BitType
case object Void extends BitType
