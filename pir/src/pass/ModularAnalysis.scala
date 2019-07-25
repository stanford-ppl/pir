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
        cu.collectDown[StreamCommand]().headOption.nonEmpty
      }
      externs = externs.flatMap { _.descendentTree }
      externs.foreach { n =>
        n.isExtern := true
      }
      externs.collect { case cmd:StreamCommand => cmd }.foreach { cmd =>
        cmd.streams.view.zipWithIndex.foreach { case (stream, i) =>
          assertOneOrLess(stream.collect[GlobalOutput](), s"${dquote(stream)}.gout").foreach { gout =>
            if (cmd.isInstanceOf[FringeStreamWrite] || !gout.isExtern.get) {
              gout.externAlias := cmd.name.get + s"_${i+1}"
            }
          }
        }
      }
    }
  }

}
