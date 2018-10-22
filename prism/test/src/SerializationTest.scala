package prism.test

import prism.collection.mutable
import prism.util._

import prism.exceptions._
import prism.graph._
import prism._

//class TestMetadata extends Metadata {
  //val nameOf = new mutable.OneToOneMap[String, String] with MetadataMap
//}

case class TestIR() extends Metadata {

  class Name extends Data[Name, String]
  val name = new Name
}

class SerializationTest extends UnitTest with Serialization {
  "TestMapSerialization" should "success" in {
    var map = new mutable.OneToOneMap[Int,String]()
    saveToFile(map, s"$testOut/saved")
    loadFromFile[Serializable]("out/test/saved")
  }

  //"TestMetadataSerialization" should "success" in {
    //val meta = new TestMetadata
    //meta.nameOf("x") = "X"
    //val path = s"$testOut/saved2"
    //saveToFile(meta, path)
    //val loaded = loadFromFile[TestMetadata](path)
    //assert(loaded.nameOf.map == meta.nameOf.map)
  //}

  "IRMetadataSerialization" should "success" in {
    val node = TestIR()
    node.name := "test"
    node.name.reset
    val n = node.name("myName")
    val path = s"$testOut/saved3"
    saveToFile(n, path)
    val loaded = loadFromFile[TestIR](path)
    assert(loaded.name.get=="myName")
  }

}

trait TestBuildEnv {
  trait Parameter extends ProductNode[Parameter] {
    override lazy val Nct = classTag[Parameter]
  }

  case class ParamA(a:Int, b:Int) extends Parameter
  case class ParamB(a:ParamA) extends Parameter
}

class InnerSerialization extends UnitTest with Serialization with TestBuildEnv {
  "InnerSerializationTest" should "fail" in {
    val b = ParamB(ParamA(3,4))
    val path = s"$testOut/innersaved"
    assertThrows[java.io.NotSerializableException] {
      saveToFile(b, path)
      val loaded = loadFromFile[ParamB](path)
      println(loaded)
    }
  }
}
