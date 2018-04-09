package prism.test

import prism.collection.immutable
import prism.collection.mutable
import prism.util._

import prism.exceptions._

class MapTest extends UnitTest with Serialization {

  "TestIOneToOne" should "success" in {
    var map = immutable.OneToOneMap.empty[Int,String]
    map += (1 -> "a")
    map += (2 -> "b")
    map += (3 -> "c")
    intercept[RebindingException[_,_]] {
      map += (1 -> "d")
    }

    map += (4 -> "a")
    assert(map.map == Map(1 -> "a", 2 -> "b", 3 -> "c", 4 -> "a"))
  }

  "TestIOneToMany" should "success" in {
    var map = immutable.OneToManyMap.empty[Int,String]
    map += (1 -> "a")
    map += (2 -> "b")
    map += (1 -> "c")
    assert(map.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
    map ++= (1 -> Set("d","e"))
    assert(map.map == Map(1 -> Set("a","c","d","e"), 2 -> Set("b")))
  }

  "TestIBiOneToOne" should "success" in {
    var map = immutable.BiOneToOneMap.empty[Int,String]
    map += (1 -> "a")
    map += (2 -> "b")
    map += (3 -> "c")
    intercept[RebindingException[_,_]] {
      map = map + (4 -> "a")
    }

    assert(map.fmap.map == Map(1 -> "a", 2 -> "b", 3 -> "c"))
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 3))
  }

  "TestIBiOneToMany" should "success" in {
    var map = immutable.BiOneToManyMap.empty[Int,String]
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    map = map + (1 -> "c")
    intercept[RebindingException[_,_]] {
      map = map + (4 -> "a")
    }

    assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b")))
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 1))

    map = map ++ (1 -> Set("d", "e"))
    assert(map.fmap.map == Map(1 -> Set("a","c","d","e"), 2 -> Set("b")))
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 1,"d" -> 1, "e" -> 1))
  }

  "TestIBiManyToOne" should "success" in {
    var map = immutable.BiManyToOneMap.empty[Int,String]
    map = map + (1 -> "a")
    map = map + (2 -> "b")
    intercept[RebindingException[_,_]] {
      map = map + (1 -> "c")
    }
    map = map + (4 -> "a")
    assert(map.fmap.map == Map(1 -> "a", 2 -> "b", 4 -> "a"))
    assert(map.bmap.map == Map("a" -> Set(1,4), "b" -> Set(2)))

    intercept[RebindingException[_,_]] {
      map = map ++ (Set(1,4) -> "e")
    }
    map = map ++ (Set(3,5) -> "e")
    assert(map.fmap.map == Map(1 -> "a", 2 -> "b", 4 -> "a", 3 -> "e", 5 -> "e"))
    assert(map.bmap.map == Map("a" -> Set(1,4), "b" -> Set(2), "e" -> Set(3,5)))
  }

  "TestIBiManyToMany" should "success" in {
    var map = immutable.BiManyToManyMap.empty[Int,String]
    map += (1 -> "a")
    map += (2 -> "b")
    map += (1 -> "c")
    map += (4 -> "a")
    assert(map.fmap.map == Map(1 -> Set("a","c"), 2 -> Set("b"), 4->Set("a")))
    assert(map.bmap.map == Map("a"->Set(1,4),"b"->Set(2),"c"->Set(1)))

    map ++= (Set(1,2), Set("e","a"))
    assert(map.fmap.map == Map(1 -> Set("a","c","e"), 2 -> Set("a","b","e"), 4->Set("a")))
    assert(map.bmap.map == Map("a"->Set(1,2,4),"b"->Set(2),"c"->Set(1), "e"->Set(1,2)))
  }

  "TestMOneToOne" should "success" in {
    val map = new mutable.OneToOneMap[Int,String]()
    map += (1 -> "a")
    map += (2 -> "b")
    map += (3 -> "c")
    intercept[RebindingException[_,_]] {
      map += (1 -> "d")
    }

    map += (4 -> "a")
    assert(map.map == Map(1 -> "a", 2 -> "b", 3 -> "c", 4 -> "a"))
  }

  "TestMOneToMany" should "success" in {
    val map = new mutable.OneToManyMap[Int,String]
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
    intercept[RebindingException[_,_]] {
      map += (4 -> "a")
    }

    assert(map.fmap.map == Map(1 -> "a", 2 -> "b", 3 -> "c"), map.fmap.map)
    assert(map.bmap.map == Map("a" -> 1, "b" -> 2, "c" -> 3))
  }

  "TestMBiOneToMany" should "success" in {
    val map = new mutable.BiOneToManyMap[Int,String]()
    map += (1 -> "a")
    map += (2 -> "b")
    map += (1 -> "c")
    intercept[RebindingException[_,_]] {
      map += (4 -> "a")
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

  "TestMapSerialzation" should "success" in {
    val map = new mutable.BiManyToManyMap[Int,String]()
    map += (1 -> "a")
    map += (2 -> "b")
    map += (1 -> "c")
    map += (4 -> "a")
    val path = s"${mkdir(s"out/MapTest")}/saved"
    saveToFile(map, path)
    val loaded = loadFromFile[mutable.BiManyToManyMap[Int,String]](path)
    assert(loaded.fmap.map == map.fmap.map, loaded.bmap.map == map.bmap.map)
  }

  "TestIBiManyToManySub" should "success" in {
    var map = immutable.BiManyToManyMap.empty[Int,String]
    val keys = List.tabulate(3){i => i}.toSet
    val values = List.tabulate(3){i => i.toString}.toSet
    map ++= (keys -> values)
    map -= (1 -> "2")
    map -= (2 -> "2")
    assert(map.fmap.map==Map(0 -> Set("0", "1", "2"), 1 -> Set("0", "1"), 2 -> Set("0", "1")))
    assert(map.bmap.map==Map("0" -> Set(0, 1, 2), "1" -> Set(0, 1, 2), "2" -> Set(0)))
  }

  "TestFactorGraphSpeed" should "success" in {
    var fg = immutable.OneToOneFactorGraph.empty[Int,String]
    val keys = List.tabulate(100){i => i}.toSet
    val values = List.tabulate(100){i => i.toString}.toSet
    //timer(s"addValues", "ms") {
      fg ++= (keys -> values)
    //}
    //println(fg(0).size)
    //timer(s"filterNot", "ms") {
      val lambda:(Int, String) => Boolean = { case (k:Int,v:String) => v.toInt % 2 == 0 }
      fg = fg.filterNot(lambda).right.get
    //}
    //println(fg(0).size)
    //timer(s"set", "ms") {
      fg = fg.set(0, "1").right.get
    //}
  }

}

