package pir.codegen

import prism.codegen._
import pir.node._

import sys.process._

class IgraphCodegen(global:GlobalContainer)(implicit design:PIR) extends PIRCodegen with PythonCodegen {
  import pirmeta._

  val fileName = s"$global.py"
  lazy val resultPath = buildPath(dirName, s"$global.cluster") 

  val vertices = ListBuffer[N]()

  override def emitNode(n:N) = {
    n match {
      case `global` => super.visitNode(n)
      case n:GlobalContainer => 
      case n:Top => super.visitNode(n) 
      case n => emitVertex(n)
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

  override def finPass = {
    super.finPass
    s"python $outputPath" !

  }

  def emitHeader = {
    emitln(s"""from igraph import *""")
    emitln(s"""g = Graph(directed=True)""")
  }

  def emitFooter = {
    emitln(s"""# plot(g)""")
    emitln(s"""dendrogram = g.community_walktrap(steps=4)""")
    emitln(s"""# dendrogram = g.community_fastgreedy()""")
    emitln(s"""# print(dendrogram.optimal_count)""")
    emitln(s"""cluster = dendrogram.as_clustering()""")
    emitln(s"""# cluster = g.community_infomap()""")
    emitln(s"""# cluster = g.community_multilevel()""")
    emitln(s"""# plot(cluster)""")
    emitln(s"""with open("${resultPath}", 'w') as f:""")
    emitln(s"""    for v, mb in izip(g.vs, cluster.membership):""")
    emitln(s"""        f.write("{}={}\\n".format(v["name"], mb))""")
  }

  def getResult = {
    (vertices, getLines(resultPath)).zipped.map { case (vertex, line) =>
      val k::v::_ = line.trim.split("=").toList
      assert(k == s"$vertex", s"key=$k, vertex=$vertex")
      val mb = v.toInt
      (vertex, mb)
    }.toMap
  }

}
