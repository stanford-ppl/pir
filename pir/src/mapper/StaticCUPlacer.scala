package pir.mapper

import pir._
import pir.node._

import spade.node._
import spade.network._

import prism._
import prism.util._

import scala.collection.mutable

class StaticCUPlacer(implicit compiler:PIR) extends PIRPass with BackTracking {
  import pirmeta._

  def shouldRun = isMesh(compiler.arch.top) && isStatic(compiler.arch.top)

  override def runPass(runner:RunPass[_]) =  {
    //val cusP = cumap.freeKeys
  }

}
