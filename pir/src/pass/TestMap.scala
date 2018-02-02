package pir.pass

import prism.collection.immutable
import prism.collection.mutable
import pirc.exceptions._
object MapTest {

  def test = {
    testIOneToOne
    testIOneToMany
    testIBiOneToOne
    testIBiOneToMany
    testIBiManyToMany
    testMOneToOne
    //testMOneToMany
    //testMBiOneToOne
    //testMBiOneToMany
    //testMBiManyToMany
  }

  def testIOneToOne = {
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

  def testIOneToMany = {
    var map = immutable.OneToManyMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (1 -> "c")
    assert(map.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
  }

  def testIBiOneToOne = {
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

  def testIBiOneToMany = {
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
    println(map.fmap)
    println(map.bmap)
  }

  def testIBiManyToMany = {
    var map = immutable.BiManyToManyMap[Int,String]()
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (1 -> "c")
    map = map + (4 -> "a")
    assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b"), 4->Set("a")))
    assert(map.bmap.map == Map("a"->Set(1,4),"b"->Set(2),"c"->Set(1)))
  }

  def testMOneToOne = {
    //val map = mutable.OneToOneMap[Int,String]()
    //map += (1 -> "a")
    //map += (2 -> "b")
    //map += (3 -> "c")
    
    //try {
      //map += (1 -> "d")
    //} catch {
      //case e:RebindingException[_,_] =>
      //case e:Throwable => throw e
    //}

    //map += (4 -> "a")
    //assert(map.map == Map(1 -> "a", 2 -> "b", 3 -> "c", 4 -> "a"))
  }

  //def testMOneToMany = {
    //val map = mutable.OneToManyMap[Int,String]()
    //map = map + (1 -> "a")
    //map = map + (2 -> "b")
    //map = map + (1 -> "c")
    //assert(map.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
  //}

  //def testMBiOneToOne = {
    //val map = mutable.BiOneToOneMap[Int,String]()
    //map = map + (1 -> "a")
    //map = map + (2 -> "b")
    //map = map + (3 -> "c")
    
    //try {
      //map = map + (4 -> "a")
    //} catch {
      //case e:RebindingException[_,_] =>
      //case e:Throwable => throw e
    //}

    //assert(map.fmap.map == Map(1 -> "a", 2 -> "b", 3 -> "c"))
    //assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 3))
  //}

  //def testMBiOneToMany = {
    //val map = mutable.BiOneToManyMap[Int,String]()
    //map = map + (1 -> "a")
    //map = map + (2 -> "b")
    //map = map + (1 -> "c")
    //try {
      //map = map + (4 -> "a")
    //} catch {
      //case e:RebindingException[_,_] =>
      //case e:Throwable => throw e
    //}
    //assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
    //assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 1))

    //map = map ++ (1 -> Set("d", "e"))
    //println(map.fmap)
    //println(map.bmap)
  //}

  //def testMBiManyToMany = {
    //val map = mutable.BiManyToManyMap[Int,String]()
    //map = map + (1 -> "a")
    //map = map + (2 -> "b")
    //map = map + (1 -> "c")
    //map = map + (4 -> "a")
    //assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b"), 4->Set("a")))
    //assert(map.bmap.map == Map("a"->Set(1,4),"b"->Set(2),"c"->Set(1)))
  //}
}
