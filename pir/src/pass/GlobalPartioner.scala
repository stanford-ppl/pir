package pir.pass

import pir.node._
import pir.mapper._
import pir.codegen._

class GlobalPartioner(implicit compiler:PIR) extends PIRTransformer {
  import pirmeta._

  override def runPass =  {
    val topP = compiler.top
    val pnodes = topP.collectDown[CUMap.K]()
    val cu = pnodes.filter { _.children.size > 20 }.head
    val igraph = new IgraphCodegen(cu)
    compiler.session.getCurrentRunner(igraph).run
    val map = igraph.getResult
    dbgblk(s"loadResult") {
      map.foreach { case (k,v) => dbg(s"$k -> $v") }
    }
  }
}
