package pir.pass

import pir._
import pir.node._
import pir.mapper._

import prism._
import prism.util._

import scala.collection.mutable

class CUPruning(implicit compiler:PIR) extends PIRPass with ResourcePruning {
  import pirmeta._

  type N = PIRNode with Product
  def shouldRun = true

  val constrains = Nil

  override def runPass(runner:RunPass[_]) =  {
    import runner._
    pirmeta.pirMap = Some(PIRMap.empty(this))
  }

}
