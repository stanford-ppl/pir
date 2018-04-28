package pir.codegen

import pir.node._
import pir.pass._

class LinkCSVCodegen(implicit compiler: PIR) extends PlastisimCodegen {
  import pirmeta._

  val fileName = s"link.csv"
}
