package spade
package test

import spade.node._
import spade.param._
import spade.codegen._
import spade.pass._

import prism.test._
import prism.graph.implicits._

class FactoryTest extends UnitTest with BaseFactory with Logging { self =>

  "FactoryTest" should "success" in {
    withLog(testOut, "create.log") {
      createNewState
      val topParam = TopParam()
      val top = create[Top](topParam)
      val path = s"$testOut/top_saved"
      saveToFile(top, path)
      val loaded = loadFromFile[Top](path)
      //implicit val compiler:Compiler = null
      //val dot = new NetworkDotGen {
        //val fileName = "top.dot"
        //val top = loaded
        //override lazy val dirName = testOut
      //}
      //dot.run
    }
  }

}
