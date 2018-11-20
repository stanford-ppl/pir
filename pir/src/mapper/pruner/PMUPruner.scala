package pir
package mapper

class PMUPruner(implicit compiler:PIR) extends ConstrainPruner with CUCostUtil with PMUPartitioner {

  override def prune[T](x:T):EOption[T] = super.prune[T](x).flatMap {
    case x:CUMap if !spadeParam.isAsic & false => //TODO
      flatFold(x.freeKeys, x) { case (x, k) =>
        val kc = k.getCost[SRAMCost]
        recover(x.filterNotAtKey(k) { v => notFit(kc, v.getCost[SRAMCost]) })
      }.asInstanceOf[EOption[T]]
    case x => super.prune(x)
  }

}

