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

  def shouldRun = isMesh(compiler.arch.top) && isDynamic(compiler.arch.top)

  override def runPass(runner:RunPass[_]) =  {
    pirMap = pirMap.flatMap { pmap =>
      pmap.flatMap[CUMap] { cumap =>
        flatFold(cumap.freeKeys, cumap) { case (cumap, cuP) =>
          cumap.map(cuP, cumap.topFreeValue(cuP).get)
        }
      }
    }
  }

}
