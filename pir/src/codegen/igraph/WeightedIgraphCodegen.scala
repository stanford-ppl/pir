package pir
package codegen

import prism.codegen._
import pir.node._

import sys.process._
import scala.collection.mutable

trait WeightedIgraphCodegen extends IgraphCodegen {
  import pirmeta._

  val edgeWeights = ListBuffer[Float]()

  def addWeights(src:N, dst:N) = {
    val weight = 1.0f / src.depeds.size
    edgeWeights += weight
  }

  def emitInput(node:N) = {
    node.localDeps.foreach { dep =>
      addWeights(dep, node)
      emitln(s"""g.add_edge("$dep", "$node")""")
    }
  }

  def weights:Option[List[Float]] = Some(edgeWeights.toList)

}
