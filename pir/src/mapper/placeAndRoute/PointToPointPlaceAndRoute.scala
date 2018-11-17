package pir
package mapper

import pir.node._
import prism.mapper._

trait PointToPointPlaceAndRoute extends Mapper {

  private def bindLambda(cuP:CUMap.K, cuS:CUMap.V, cumap:CUMap) = {
    dbgblk(s"set ${quote(cuP)} -> ${quote(cuS)}") { cumap.set(cuP,cuS) }
  }

  override def bind[T](x:T):EOption[T] = x match {
    case x:CUMap if !spadeParam.isAsic =>
      bind[CUMap.K, CUMap.V, CUMap, Int,Int](
        pnodes=x.freeKeys.toSeq,
        snodes=(cuP:CUMap.K, m:CUMap) => m.freeValuesOf(cuP).toList,
        init=x,
        bindLambda=bindLambda _
      )(
        rankP = { case (cuP, m) => -x.freeValuesOf(cuP).size },
        rankS = { case (cuP, cuS, m) => -x.freeKeysOf(cuS).size }
      ).asInstanceOf[EOption[T]]
    case x => super.bind[T](x)
  }

}

