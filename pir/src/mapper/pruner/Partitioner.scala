package pir
package mapper
import pir.pass._
import prism.graph._

trait Partitioner extends CUCostUtil with Transformer with BufferAnalyzer {
  def recover(x:EOption[CUMap]):EOption[CUMap] = x

  override def removeNodes(nodes:Iterable[N]) = {
    super.removeNodes(nodes)
    nodes.foreach { removeCache }
  }

  def removeCache(n:Any) = {
    resetCacheOn {
      case `n` => true
      case (`n`, _) => true
      case _ => false
    }
  }

}
