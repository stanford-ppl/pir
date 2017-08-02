package pir.plasticine.config
                          
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.immutable.{Map => IMap}
import scala.reflect.runtime.universe._
import pir.util.enums._
import scala.util.{Try, Success, Failure}

sealed trait Pattern {
  def cuAt(sn:SwitchNetwork)(i:Int, j:Int):ComputeUnit
}
case object Checkerboard extends Pattern {
  def cuAt(sn:SwitchNetwork)(i:Int, j:Int):ComputeUnit = {
    if ((i+j) % 2 == 0) sn.pcuAt(i,j) 
    else sn.mcuAt(i,j) 
  }
}
case object MixAll extends Pattern {
  def cuAt(sn:SwitchNetwork)(i:Int, j:Int):ComputeUnit = {
    if (i % 2 == 0) {
      if (j % 2 == 0) sn.pcuAt(i,j)
      else sn.mcuAt(i,j)
    }
    else sn.scuAt(i,j)
  }
}
case object HalfHalf extends Pattern {
  def cuAt(sn:SwitchNetwork)(i:Int, j:Int):ComputeUnit = {
    if (i % 2 == 0) {
      if (j % 2 == 0) sn.pcuAt(i,j) else sn.mcuAt(i,j)
    } else {
      if (j % 2 == 0) sn.mcuAt(i,j) else sn.scuAt(i,j)
    }
  }
}
