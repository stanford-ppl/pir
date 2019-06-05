package pir
package pass

import pir.node._
import prism.graph._
import spade.param._
import scala.collection.mutable

class ModularAnalysis(implicit compiler:PIR) extends PIRPass {

  override def runPass = {
    if (config.asModule) {
      var externs:Seq[PIRNode] = pirTop.collectChildren[DRAMFringe]
      externs = externs.filter { cu =>
        cu.collectDown[StreamCommand]().headOption.map { cmd =>
          cu.externAlias := cmd.name.get
          cmd.streams.zipWithIndex.foreach { case (stream, i) =>
            stream.collectFirst[GlobalIO]().externAlias := cmd.name.get + s"_$i"
          }
        }.nonEmpty
      }
      externs = externs.flatMap { _.descendentTree }
      externs.foreach { n =>
        n.isExtern := true
      }
    }
  }

}
