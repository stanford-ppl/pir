package pir
package codegen

import prism.codegen._
import pir.node._

import sys.process._
import scala.collection.mutable

abstract class IgraphCodegen(global:GlobalContainer)(implicit compiler:PIR) extends PIRCodegen with PythonCodegen {
  import pirmeta._

  val fileName = s"split_$global.py"
  lazy val resultPath = buildPath(dirName, s"$global.cluster") 

  val vertices = ListBuffer[N]()
  override def initPass = {
    tic
    openFile(dirName, fileName, append=append)
  }

  override def finPass = {
    closeStream
    s"python $outputPath" !

    infor(s"Split $global in ${toc("ms")}ms")
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

  def emitVertex(node:N) = {
    assert(!vertices.contains(node), s"duplicate emitted vertex=$node")
    vertices += node
    emitln(s"""g.add_vertex("$node")""")
  }

  override def emitNode(n:N) = {
    n match {
      case `global` => super.visitNode(n)
      case n:GlobalContainer => 
      case n:Top => super.visitNode(n) 
      case n => emitVertex(n)
    }
  }

  def emitInput(node:N):Unit

  def weights:Option[List[Float]]

  def emitFooter = {
    val w = weights match {
      case Some(w) => s"[${w.mkString(",")}]"
      case None => s"None"
    }
    emitln(s"def fix_dendrogram(graph, cl):")
    emitln(s"    already_merged = set()")
    emitln(s"    for merge in cl.merges:")
    emitln(s"        already_merged.update(merge)")
    emitln(s"    num_dendrogram_nodes = graph.vcount() + len(cl.merges)")
    emitln(s"    not_merged_yet = sorted(set(xrange(num_dendrogram_nodes)) - already_merged)")
    emitln(s"    if len(not_merged_yet) < 2:")
    emitln(s"        return")
    emitln(s"    v1, v2 = not_merged_yet[:2]")
    emitln(s"    cl._merges.append((v1, v2))")
    emitln(s"    del not_merged_yet[:2]")
    emitln(s"    missing_nodes = xrange(num_dendrogram_nodes,")
    emitln(s"            num_dendrogram_nodes + len(not_merged_yet))")
    emitln(s"    cl._merges.extend(izip(not_merged_yet, missing_nodes))")
    emitln(s"    cl._nmerges = graph.vcount()-1")

    emitln(s"""weights=$w""")
    emitln(s"""# plot(g)""")
    emitln(s"""dendrogram = g.community_walktrap(steps=10, weights=weights)""")
    emitln(s"""# dendrogram = g.community_fastgreedy(weights=weights)""") // undirected
    emitln(s"""# dendrogram = g.community_edge_betweenness(weights=weights)""")
    emitln(s"""# print(dendrogram.optimal_count)""")
    emitln(s"""fix_dendrogram(g, dendrogram)""")
    emitln(s"""cluster = dendrogram.as_clustering(n=2)""")
    emitln(s"""# cluster = g.community_infomap(edge_weights=weights)""")
    emitln(s"""# cluster = g.community_leading_eigenvector(clusters=2, weights=weights)""") // undirected graph
    emitln(s"""# cluster = g.community_label_propagation(weights=weights)""") // too slow
    emitln(s"""# cluster = g.community_multilevel(weights=weights)""") // undirected
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
