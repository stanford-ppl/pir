package prism.test

import prism.collection.mutable
import prism.util._

import prism.exceptions._

class TestMetadata extends Metadata {
  val nameOf = new mutable.OneToOneMap[String, String] with MetadataMap
}

class SerializationTest extends UnitTest with Serialization {
  "TestMapSerialization" should "success" in {
    var map = new mutable.OneToOneMap[Int,String]()
    saveToFile(map, "out/test/saved")
    loadFromFile[Serializable]("out/test/saved")
  }

  "TestMetadataSerialization" should "success" in {
    val meta = new TestMetadata
    meta.nameOf("x") = "X"
    saveToFile(meta, "out/test/saved")
    val loaded = loadFromFile[TestMetadata]("out/test/saved")
    assert(loaded.nameOf.map == meta.nameOf.map)
  }

}
