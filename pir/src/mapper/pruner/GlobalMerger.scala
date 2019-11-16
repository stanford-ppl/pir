package pir
package mapper

import pir.node._
import spade.param._
import prism.collection.immutable._
import prism.graph._

trait GlobalMerging extends PIRTransformer with CUCostUtil with  MappingLogger { self =>

  def mergeGlobals(x:CUMap):CUMap = err(s"Unknown merge algorithm ${config.mergeAlgo}")

  def validate(x:CUMap) = {
    x.freeKeys.foreach { k =>
      val values = x.freeValuesOf(k)
      values.foreach { v =>
        val kcost = getCosts(k)
        val vcost = getCosts(v)
        if (!fit(kcost, vcost))
          bug(s"$k cannot fit ${v} after merging!\nkcost=$kcost\nvcost=$vcost")
      }
    }
  }

  def getCosts(x:Any) = {
    x.getCost[AFGCost] ::
    x.getCost[MCCost] ::
    x.getCost[MergeBufferCost] ::
    x.getCost[SplitterCost] ::
    x.getCost[LockCost] ::
    x.getCost[SRAMCost] ::
    x.getCost[InputCost] ::
    x.getCost[OutputCost] ::
    x.getCost[StageCost] ::
    x.getCost[LaneCost] ::
    x.getCost[OpCost] ::
    x.getCost[ActorCost] ::
    Nil
  }

  def isBackEdge(in:Input[PIRNode]):Boolean = {
    if (in.src.isUnder[ArgFringe]) true
    else {
      val out = in.singleConnected.get
      out.src.ctx.get.collectDown[LocalOutAccess]().exists { _.initToken.get }
    }
  }

  def getNewGlobs(globs:Iterable[GlobalContainer]) = {
    val newGlob = within(pirTop) { 
      val (other,comp) = globs.partition { _.isInstanceOf[ComputePartitioner] }
      val toMirror = other.headOption.getOrElse(comp.head)
      mirrorAll(List(toMirror))(toMirror).asInstanceOf[GlobalContainer]
    }
    globs.foreach { g =>
      g.children.foreach { c => 
        swapParent(c, newGlob)
      }
    }
    free(globs)
    newGlob
  }


}

class GlobalMerger(implicit compiler:PIR) extends GlobalMerging with ExternMerger { self =>

  def merge[T](x:T):T = (x match {
    case x:TopMap => x.mapAll(field => merge[Any](field))
    case x:CUMap if !spadeParam.isAsic => 
      logging(x)
      val newMap = mergeGlobals(x)
      logging(newMap)
      validate(newMap)
      newMap
    case x => x
  }).asInstanceOf[T]

  override def runPass = {
    topMap = topMap.map { tmap => merge[TopMap](tmap) }
  }

}
