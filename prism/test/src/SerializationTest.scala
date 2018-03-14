package prism.test

import prism.collection.mutable
import prism.util._

import prism.exceptions._

class TestMetadata extends Metadata {
  val nameOf = new mutable.OneToOneMap[String, String] with MetadataMap
}

class SerializationTest extends UnitTest {
  "TestMapSerialization" should "success" in {
    var map = new mutable.OneToOneMap[Int,String]()
    saveToFile(map, "out/test")
    loadFromFile[Serializable]("out/test")
  }

  "TestMetadataSerialization" should "success" in {
    val meta = new TestMetadata
    meta.nameOf("x") = "X"
    saveToFile(meta, "out/test")
    val loaded = loadFromFile[TestMetadata]("out/test")
    assert(loaded.nameOf.map == meta.nameOf.map)
  }

}
