package pir
package pass

import pir.node._
import pir.mapper._
import prism.graph._

class CUPruning(implicit compiler:PIR) extends PIRPass with CostUtil {

  override def runPass = {
    pirTop.collectDown[GlobalContainer]().foreach { global =>
      global.getCost
    }
  }

}
