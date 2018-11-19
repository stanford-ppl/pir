package pir
package mapper
import prism.graph._

trait Partitioner extends CUCostUtil with Transformer {
  def recover(x:EOption[CUMap]):EOption[CUMap] = x
}
