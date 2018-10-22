package spade
package test

import spade.node._
import spade.param2._
import spade.codegen._

import prism.test._
import prism.graph.implicits._

class FactoryTest extends UnitTest with BaseFactory with Logging { self =>

  "FactoryTest" should "success" in {
    withLog(testOut, "create.log") {
      val topParam = TopParam()
      val top = create[Top](topParam)
      val path = s"$testOut/top_saved"
      saveToFile(top, path)
      val loaded = loadFromFile[Top](path)
      val dot = new NetworkDotCodegen("top.dot", loaded)(null) {
        override lazy val dirName = testOut
      }
      dot.run
    }
  }

}
