package pirc.test

import prism.collection.mutable
import pirc.util._

import pirc.exceptions._
import scala.reflect._

class A[T1:ClassTag, T2:ClassTag] extends Serializable

class SerializationTest extends UnitTest {
  "TestSerialization" should "success" in {
    var map = new mutable.OneToOneMap[Int,String]()
    saveToFile(map, "out/test")
    loadFromFile[Serializable]("out/test")
  }

}
