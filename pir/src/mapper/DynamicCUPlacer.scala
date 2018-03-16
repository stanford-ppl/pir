package pir.mapper

import pir._
import pir.node._

import spade.node._
import spade.network._

import prism._
import prism.util._

import scala.collection.mutable

class DynamicCUPlacer(implicit compiler:PIR) extends PIRPass {
  import pirmeta._

  def shouldRun = isMesh(compiler.arch.top) && isDynamic(compiler.arch.top) && pirMap.nonEmpty

  override def runPass(runner:RunPass[_]) =  {
    val newCumap = cumap.freeKeys.foldLeft(cumap) { case (cumap, cuP) => cumap.map(cuP, cumap.topFree(cuP).get) }
    pirmeta.pirMap = Some(pmap.set[CUMap](newCumap))
  }


}
