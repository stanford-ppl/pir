package prism.test

import prism.collection.mutable
import prism.util._

import prism.exceptions._
import prism.graph._
import prism._

class ScalaUtilTest extends UnitTest {
  import opss._
  "SumMapTest1" should "success" in {
    val m1 = Map(1 -> 2, 3 -> 4)
    val m2 = Map(1 -> 3, 2 -> 4)
    val sum = sumMap(m1,m2) { _ + _ }
    assert(sum == Map(1 -> 5, 2 -> 4, 3 -> 4))
  }
  "SumMapTest2" should "success" in {
    val m1 = Map(1 -> List(1,2), 3 -> List(3,4))
    val m2 = Map(1 -> List(2,4), 2 -> Nil)
    val sum = sumMap(m1,m2) { _ ++ _ }
    assert(sum == Map(1 -> List(1,2,2,4), 2 -> Nil, 3 -> List(3,4)))
  }
  "SumMapTest3" should "success" in {
    val m1 = Map(1 -> Map("a"->List(1,2,3), "b"->List(2,3,4)), 3 -> Map("a"->List(1,2)))
    val m2 = Map(1 -> Map("a"->List(2),"b"->List(4,5)), 2 -> Map[String,List[Int]]())
    val sum = sumMap(m1,m2) { sumMap(_,_) { _ ++ _ } }
    assert(sum == Map(1 -> Map("a"->List(1,2,3,2),"b"->List(2,3,4,4,5)), 3 -> Map("a"->List(1,2)), 2 -> Map[String,List[Int]]()))
  }
}
