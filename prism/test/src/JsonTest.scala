package prism
package test

import spray.json._
import DefaultJsonProtocol._

class JsonTest extends UnitTest {

  "JsonTest" should "success" in {
    implicit val paramA = jsonFormat2(ParamA)
    implicit val paramB = jsonFormat1(ParamB)
    val a = ParamB(ParamA(3,4))
    println(a.toJson)
  }

}

