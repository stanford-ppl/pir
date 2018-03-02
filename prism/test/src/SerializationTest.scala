package prism.test

import prism.collection.mutable
import prism.util._

import prism.exceptions._

class SerializationTest extends UnitTest {
  "TestSerialization" should "success" in {
    var map = new mutable.OneToOneMap[Int,String]()
    saveToFile(map, "out/test")
    loadFromFile[Serializable]("out/test")
  }

}
