package pir
package mapper

import prism.collection.immutable._

class MatchPruner(implicit compiler:PIR) extends MatchingConstrainPruner {
  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic => prune[CUMap.K,CUMap.V,CUMap](x).asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }
  
  override def fail(f:Any) = {
    f match {
      case e@MatchConstrainFailure(fg:CUMap, ks:Set[_], vs:Set[_]) =>
        runner.setFailed
        val params = vs.map { _.asInstanceOf[CUMap.V].params.get }
        err(s"Not enough resource of type ${params.mkString(",")} Required ${ks.size}. Possesed ${vs.size}", exception=false)
      case _ => super.fail(f)
    }
  }
}

