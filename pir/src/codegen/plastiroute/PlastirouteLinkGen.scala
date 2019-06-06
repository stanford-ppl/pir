package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.codegen._

class PlastirouteLinkGen(implicit compiler: PIR) extends PlastisimCodegen with CSVCodegen {
  override def fileName = config.prouteLinkName

  override def runPass = {
    if (!noPlaceAndRoute) super.runPass
  }

  override def emitNode(n:N) = n match {
    case n:GlobalOutput => emitLink(n)
    case n => visitNode(n)
  }

  def emitLink(n:GlobalOutput) = {
    if (n.isExtern.get) {
      if (n.isEscaped) {
        n.out.T.filter { !_.isExtern.get }.map { gin =>
          gin.global.get.id
        }
        //TODO
      }
    } else {
      val ctx = n.in.T.ctx.get
      val row = newRow
      row("link") = n.id
      row("ctx") = ctx.id
      row("src") = n.global.get.id
      row("tp") = if (n.getVec == 1) 1 else 2 // 1 for scalar, 2 for vector
      //row("count") = n.constCount
      row("count") = n.count.get.getOrElse(1000000) //TODO: use more reasonable heuristic when count is not available
      n.out.T.zipWithIndex.foreach { case (gin, idx) =>
        row(s"dst[$idx]") = gin.global.get.id
        row(s"out[$idx]") = gin.id
      }
    }
  }
}
