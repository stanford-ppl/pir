package spade
package codegen

import node._
import param._

class StatCodegen()(implicit compiler:Spade) extends SpadeCodegen {
  import SpadeConfig._
  val fileName:String = s"stat.txt"

  override def runPass = {
    val top = compiler.top
    val routable = top.collectDown[Routable]()
    dbg("")
    routable.groupBy { _.param }.foreach { case (param, routables) =>
      dbg(s"${param.className}")
      dbg(s" - quantity=${routables.size}")
      top.networks.foreach { net =>
        val bct = net.bct
        dbg(s" - [${bct}] ins=${top.minInputs(param)(bct)}/${top.maxInputs(param)(bct)} outs=${top.minOutputs(param)(bct)}/${top.maxOutputs(param)(bct)}")
      }
    }
  }

  override def dbg(s:Any) = {
    super.dbg(s)
    if (printStat) info(s"$s")
  }
}

