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
      ginsOf(n).zipWithIndex.foreach{ case (gin, idx) =>
        row(s"dst[$idx]") = globalOf(gin).get.id
      }
    case n => super.visitNode(n)
  }

  override def finPass = {
    super.finPass
    if (enablePlastiroute) {
      zipOption(PLASTIROUTE_HOME, psimOut). fold {
        warn(s"set PLASTIROUTE_HOME and PLASTISIM_HOME to launch plastiroute automatically!")
      } { case (prouteHome:String, psimOut:String) =>
        val log = s"$dirName/proute.log"
        val exitCode = shell("proute", s"$prouteHome/plastiroute -n $psimOut/node.csv -l $psimOut/link.csv -o $psimOut/proute.place -r $numRows -c $numCols -s1", log)
        if (exitCode != 0) {
          fail(s"Plastiroute failed. details in $log")
        }
      }
    }
  }

}
