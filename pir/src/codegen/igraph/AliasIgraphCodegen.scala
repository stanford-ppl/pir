package pir
package codegen

import prism.codegen._
import pir.node._

import sys.process._
import scala.collection.mutable

trait AliasIgraphCodegen extends IgraphCodegen {
  import pirmeta._

  val aliasMap = mutable.Map[N, N]()

  override def emitVertex(n:N) = {
    val a = alias(n)
    if (a == n) super.emitVertex(n)
  }

  def lookup(node:N) = aliasMap.getOrElse(node, node)

  def alias(node:N):N = aliasMap.getOrElseUpdate(node, 
    node match {
      case node:Const[_] if node.localDeps.size==1 => node.localDeps.head
      case node:LocalLoad => memsOf(node).head
      case node:LocalStore => memsOf(node).head
      case Def(n, CounterIter(counter, offset)) => alias(counter)
      case node => node
    }
  )

  def emitInput(node:N) = {
    node.localDeps.foreach { d =>
      val dep = lookup(d)
      if (dep != node) {
        emitln(s"""g.add_edge("$dep", "$node")""")
      }
    }
  }

  override def getResult = {
    val vertexMap = super.getResult
    aliasMap.map { case (node, alias) =>
      (node, vertexMap(alias))
    }.toMap
  }

  def weights:Option[List[Float]] = None

}
