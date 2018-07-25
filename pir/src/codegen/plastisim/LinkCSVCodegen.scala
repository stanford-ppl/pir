package pir
package codegen

import pir.node._
import pir.pass._
import prism.codegen._

class LinkCSVCodegen(implicit compiler: PIR) extends PlastisimCodegen with CSVCodegen {
  import pirmeta._
  import PIRConfig._

  val fileName = s"link.csv"

  override def emitNode(n:N) = n match {
    case n:GlobalOutput => 
      val count = constCountsOf(n)
      val gins = ginsOf(n)
      val row = newRow
      row("link") = n.id
      row("src") = globalOf(n).get.id
      row("count") = count
      val dstCUs = ginsOf(n).groupBy { gin => globalOf(gin).get }
      dstCUs.foreach { case (cu, gins) =>
        if (gins.size > 1) warn(s"duplicate $gins from the same source $n in the same cu=$cu")
      }
      dstCUs.keys.zipWithIndex.foreach{ case (cu, idx) =>
        row(s"dst[$idx]") = cu.id
      }
    case n => super.visitNode(n)
  }

}
