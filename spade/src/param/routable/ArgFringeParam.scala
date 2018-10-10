package spade
package param

import prism.enums._

import SpadeConfig._
case class ArgFringeParam(
  numArgIns:Int=option[Int]("argin"),
  numArgOuts:Int=option[Int]("argout"),
  numTokenOuts:Int=option[Int]("tokenout")
) extends Parameter
