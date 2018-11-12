package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.codegen._

class PlastirouteLinkGen(implicit compiler: PIR) extends PIRTraversal with CSVCodegen with ChildFirstTraversal with PlastisimUtil {
  override def dirName = psimOut
  override def fileName = prouteName

  override def emitNode(n:N) = n match {
    case n:GlobalOutput =>
      val row = newRow
      row("link") = n.id
      row("src") = n.global.get.id
      row("tp") = if (n.getVec == 1) 1 else 2 // 1 for scalar, 2 for vector
      row("count") = n.constCount
      n.out.T.zipWithIndex.foreach { case (gin, idx) =>
        row(s"dst[$idx]") = gin.global.get.id
      }
    case n => visitNode(n)
  }
}
