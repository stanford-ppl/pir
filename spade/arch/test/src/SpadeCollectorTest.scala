package arch.test

import arch._
import spade.node._
import prism.test._

import org.scalatest._

class SpadeCollectorTest extends UnitTest with Logging { self =>

  "SpadeCollectorTest" should "success" in {
    logger.withOpen(s"out/arch_test", "SpadeCollectorTest.log", false) {
      val arch = SMeshCB2x2
      arch.newDesign
      val cu = arch.design.top.collectDown[CU]().head
      timer(s"collectDown Stage", "ms"){
        cu.collectDown[Stage](logger=Some(this))
      }
    }
  }

}


