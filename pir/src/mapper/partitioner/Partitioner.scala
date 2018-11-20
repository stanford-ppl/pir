package pir
package mapper
import pir.pass._
import prism.graph._

trait Partitioner extends CUCostUtil with Transformer with BufferAnalyzer {
  def recover(x:EOption[CUMap]):EOption[CUMap] = x

  override def removeNode(n:N) = {
    super.removeNode(n)
    removeCache(n)
  }

  def removeCache(n:Any) = {
    resetCacheOn {
      case `n` => true
      case (`n`, _) => true
      case _ => false
    }
  }

}
