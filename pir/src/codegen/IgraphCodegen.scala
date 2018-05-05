package pir.codegen

import prism.codegen._
import pir.node._


class IgraphCodegen(val fileName:String)(implicit design:PIR) extends PIRCodegen with PythonCodegen {
  import pirmeta._

  val vertices = ListBuffer[N]()

  override def emitNode(n:N) = {
    n match {
      case n:GlobalContainer => 
        if (n.children.size>20) {
          dbg(s"$n")
          super.visitNode(n)
        }
      case n:Top => super.visitNode(n) 
      case n:Primitive => emitVertex(n)
      case n =>
    }
  }

  def emitVertex(node:N) = {
    vertices += node
    emitln(s"""g.add_vertex("$node")""")
  }

  def emitInput(node:N) = {
    node.localDeps.foreach { dep =>
      emitln(s"""g.add_edge("$dep", "$node")""")
    }
  }

  override def runPass = {
    emitHeader
    super.runPass
    vertices.foreach(emitInput)
    emitFooter
  }

  def emitHeader = {
    emitln(s"""from igraph import *""")
    emitln(s"""g = Graph(directed=True)""")
  }

  def emitFooter = {
    emitln(s"""plot(g)""")
    emitln(s"""dendrogram = g.community_walktrap(steps=2)""")
    emitln(s"""print(dendrogram.optimal_count)""")
    emitln(s"""cluster = dendrogram.as_clustering()""")
    emitln(s"""plot(cluster)""")
  }

}
