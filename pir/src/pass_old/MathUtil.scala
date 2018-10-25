package pir
package pass

import prism.util._
import pir.node._

trait MathUtil {
  val pirmeta:PIRMetadata
  val spademeta:SpadeMetadata
  import pirmeta._
  import spademeta._

  implicit class LongOp(i:Long) {
    // Round up division
    def /! (d:Long) = (i.toDouble / d.toDouble).ceil.toLong
  }

  implicit class IntOp(i:Int) {
    // Round up division
    def /! (d:Int) = (i.toFloat / d.toFloat).ceil.toInt
  }

  def log2(x:Int) = (Math.log(x) / Math.log(2)).ceil.toInt

  final val SINGLE_PRECISION = 32
  
}

