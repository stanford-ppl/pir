package pirc.test

import prism.collection.immutable
import prism.collection.mutable
import pirc.util._

import pirc.exceptions._
import scala.reflect._

class MapTest extends UnitTest {

  "TestIOneToOne" should "success" in {
    var map = immutable.OneToOneMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (3 -> "c")
    
    try {
      map = map + (1 -> "d")
    } catch {
      case e:RebindingException[_,_] =>
      case e:Throwable => throw e
    }

    map = map + (4 -> "a")
    assert(map.map == Map(1 -> "a", 2 -> "b", 3 -> "c", 4 -> "a"))
  }

  "TestIOneToMany" should "success" in {
    var map = immutable.OneToManyMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (1 -> "c")
    assert(map.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
  }

  "TestIBiOneToOne" should "success" in {
    var map = immutable.BiOneToOneMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (3 -> "c")
    
    try {
      map = map + (4 -> "a")
    } catch {
      case e:RebindingException[_,_] =>
      case e:Throwable => throw e
    }

    assert(map.fmap.map == Map(1 -> "a", 2 -> "b", 3 -> "c"))
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 3))
  }

  "TestIBiOneToMany" should "success" in {
    var map = immutable.BiOneToManyMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (1 -> "c")
    try {
      map = map + (4 -> "a")
    } catch {
      case e:RebindingException[_,_] =>
      case e:Throwable => throw e
    }
    assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 1))

    map = map ++ (1 -> Set("d", "e"))
    assert(map.fmap.map == Map(1 -> Set("a","c","d","e"), 2 -> Set("b")))
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 1,"d" -> 1, "e" -> 1))
  }

  "TestIBiManyToMany" should "success" in {
    var map = immutable.BiManyToManyMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (1 -> "c")
    map = map + (4 -> "a")
    assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b"), 4->Set("a")))
    assert(map.bmap.map == Map("a"->Set(1,4),"b"->Set(2),"c"->Set(1)))
  }

  "TestMOneToOne" should "success" in {
    val map = new mutable.OneToOneMap[Int,String]()
    map += (1 -> "a")
    map += (2 -> "b")
    map += (3 -> "c")
    
    try {
      map += (1 -> "d")
    } catch {
      case e:RebindingException[_,_] =>
      case e:Throwable => throw e
    }

    map += (4 -> "a")
    assert(map.map == Map(1 -> "a", 2 -> "b", 3 -> "c", 4 -> "a"))
  }

  "TestMOneToMany" should "success" in {
    val map = new mutable.OneToManyMap[Int,String]()
    map += (1 -> "a")
    map += (2 -> "b")
    map += (1 -> "c")
    assert(map.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
  }

  "TestMBiOneToOne" should "success" in {
    val map = new mutable.BiOneToOneMap[Int,String]()
    map += (1 -> "a")
    map += (2 -> "b")
    map += (3 -> "c")
    
    try {
      map += (4 -> "a")
    } catch {
      case e:RebindingException[_,_] =>
      case e:Throwable => throw e
    }

    assert(map.fmap.map == Map(1 -> "a", 2 -> "b", 3 -> "c"), map.fmap.map)
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 3))
  }

  "TestMBiOneToMany" should "success" in {
    val map = new mutable.BiOneToManyMap[Int,String]()
    map += (1 -> "a")
    map += (2 -> "b")
    map += (1 -> "c")
    try {
      map += (4 -> "a")
    } catch {
      case e:RebindingException[_,_] =>
      case e:Throwable => throw e
    }
    assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 1))

    map ++= (1 -> Set("d", "e"))
    assert(map.fmap.map == Map(1 -> Set("a","c","d","e"), 2 -> Set("b")))
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 1,"d" -> 1, "e" -> 1))
  }

  "TestMBiManyToMany" should "success" in {
    val map = new mutable.BiManyToManyMap[Int,String]()
    map += (1 -> "a")
    map += (2 -> "b")
    map += (1 -> "c")
    map += (4 -> "a")
    assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b"), 4->Set("a")))
    assert(map.bmap.map == Map("a"->Set(1,4),"b"->Set(2),"c"->Set(1)))
  }
}

