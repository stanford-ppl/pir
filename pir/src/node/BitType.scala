package pir
package node

trait BitType
case class Fix(s:Boolean, i:Int, f:Int) extends BitType
case class Flt(m:Int, e:Int) extends BitType
case object Bool extends BitType
