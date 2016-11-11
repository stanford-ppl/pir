package pir.test

import pir._
import pir.typealias._
import pir.misc._
import pir.graph.enums._
import pir.graph._
import pir.graph.mapper._
import pir.graph.traversal._

import org.scalatest._
import scala.language.reflectiveCalls
import scala.util.Random
import scala.collection.mutable.ListBuffer

class MapperTest extends UnitTest { self =>

  "InOrderBind Test1" should "fail" in {
    new Design { self =>
      implicit val ctrler:CU = null 
      // Nodes
      // PNodes
      override val arch = null
      // Mapping
      val testMapper = new Mapper {
        val typeStr = "TestMapper"
        val design = self
      }
      val nodes = List.tabulate(5){i => i}; 
      val pnodes = List.tabulate(10){i => i}
      // Printer
      def cons(n:Int, r:Int, m:Map[Int,Int]):Map[Int, Int] = {
        if (r > pnodes.size/2 - n) m + (n -> r )
        else throw new { val mapper = testMapper; val msg = "Constrain failed" } with MappingException
      }
      intercept[MappingException] {
        val mp = testMapper.bindInOrder(pnodes, nodes, Map[Int, Int](), List(cons _), (m:Map[Int,Int]) => m)
      }
    }
  }

  "InOrderBind Test2" should "success" in {
    new Design { self =>
      implicit val ctrler:CU = null 
      // Nodes
      // PNodes
      override val arch = null
      // Mapping
      val testMapper = new Mapper {
        val typeStr = "TestMapper"
        val design = self
      }
      val nodes = List.tabulate(5){i => i}; 
      val pnodes = List.tabulate(10){i => i}
      var resMap = Map[Int, List[Int]]()
      val numRes = 5;
      nodes.zipWithIndex.foreach { case (n,i) =>
        resMap += n -> (pnodes.size/2-i until pnodes.size).toList
      }
      // Printer
      def cons(n:Int, r:Int, m:Map[Int,Int]):Map[Int, Int] = {
        if (resMap(n).contains(r)) {
          m + (n -> r )
        } else throw new { val mapper = testMapper; val msg = "Constrain failed" } with MappingException
      }
      val mp = testMapper.bindInOrder(pnodes, nodes, Map[Int, Int](), List(cons _), (m:Map[Int,Int]) => m)
      var curRes = -1;
      nodes.foreach { n =>
        assert(mp(n) > curRes)
        curRes = mp(n)
      }
    }
  }

}

