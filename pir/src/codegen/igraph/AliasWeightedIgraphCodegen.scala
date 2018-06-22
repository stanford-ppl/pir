package pir
package codegen

import prism.codegen._
import pir.node._

import sys.process._
import scala.collection.mutable

trait AliasWeightedIgraphCodegen extends IgraphCodegen with WeightedIgraphCodegen with AliasIgraphCodegen {
  import pirmeta._

  override def emitInput(node:N) = {
    node.localDeps.foreach { dep =>
      val adep = lookup(dep)
      if (adep != node) {
        addWeights(dep, node)
        emitln(s"""g.add_edge("$adep", "$node")""")
      }
    }
  }

  override def getResult = super[AliasIgraphCodegen].getResult

  override def weights = super[WeightedIgraphCodegen].weights

}
