package pir
package pass

import pir.node._

trait ControlUtil { self:PIRPass =>

  import pirmeta._
  def getCtrlOf(n:PIRNode) = ctrlOf.getOrElseUpdate(n) {
    n match {
      case n:Memory =>
        val accesses = accessesOf(n)
        dbg(s"accesses: ${accesses}")
        val accessCtrls = accesses.map { access => 
          dbg(s"access:$access ctrl=${ctrlOf(access)}")
          ctrlOf(access)
        }
        leastCommonAncesstor(accessCtrls).getOrElse {
          throw PIRException(s"${accessCtrls} do not share common ancestor")
        }
      case n => throw PIRException(s"ctrlOf is not defined on $n")
    }
  }

}
