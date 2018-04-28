package pir.codegen

import pir.node._
import pir.pass._
import prism.codegen._

class LinkCSVCodegen(implicit compiler: PIR) extends PlastisimCodegen with CSVCodegen {
  import pirmeta._

  val fileName = s"link.csv"

  override def emitNode(n:N) = n match {
    case n:GlobalOutput => 
      val stores = n.collectOutTillMem[LocalStore]()
      val count = assertUnify(stores, "count") { store => getCountsOf(store) }
      val gins = ginsOf(n)
      val row = newRow
      row("link") = n
      row("src") = globalOf(n).get
      row("count") = count
      ginsOf(n).zipWithIndex.foreach{ case (gin, idx) =>
        row(s"dst[$idx]") = gin
      }
    case n => super.visitNode(n)
  }

}
