package pir.test

import pir._
import pir.node._
import pir.mapper._
import pir.pass._
import pir.util.typealias._

import pirc.enums._
import pirc.util._
import pirc.test._

import org.scalatest._
import scala.language.reflectiveCalls
import scala.util.Random
import scala.collection.mutable.ListBuffer

class MapperTest extends UnitTest { self =>

  type R = Int
  type N = Int
  type M = Map[N,R]

  //"InOrderBind Test1" should "fail" in {
    //new PIR { self =>
      //implicit val ctrler:CU = null 
      //def main(args: Array[String]) = {}
      //// Nodes
      //// PNodes
      //override val arch = null
      //// Mapping
      //implicit val testMapper = new Mapper {
        //val typeStr = "TestMapper"
        //val design = self
      //}
      //val nodes = List.tabulate(5){i => i}; 
      //val pnodes = List.tabulate(10){i => i}
      //// Printer
      //def cons(n:R, r:R, m:M):M = {
        //if (r > pnodes.size/2 - n) m + (n -> r )
        //else throw MappingException[M](m, "constrain failed")
      //}
      //intercept[MappingException[M]] {
        //val mp = testMapper.bindInOrder(pnodes, nodes, Map[Int, Int](), List(cons _), (m:M) => m)
      //}
    //}
  //}

  //"InOrderBind Test2" should "success" in {
    //new PIR { self =>
      //implicit val ctrler:CU = null 
      //def main(args: Array[String]) = {}
      //// Nodes
      //// PNodes
      //override val arch = null
      //// Mapping
      //implicit val testMapper = new Mapper {
        //val typeStr = "TestMapper"
        //val design = self
      //}
      //val nodes = List.tabulate(5){i => i}; 
      //val pnodes = List.tabulate(10){i => i}
      //var resMap = Map[Int, List[Int]]()
      //val numRes = 5;
      //nodes.zipWithIndex.foreach { case (n,i) =>
        //resMap += n -> (pnodes.size/2-i until pnodes.size).toList
      //}
      //// Printer
      //def cons(n:Int, r:Int, m:M):M = {
        //if (resMap(n).contains(r)) {
          //m + (n -> r )
        //} else throw new {
          //val msg = "Constrain failed"
        //} with MappingException[M](m)
      //}
      //val mp = testMapper.bindInOrder(pnodes, nodes, Map[Int, Int](), List(cons _), (m:M) => m)
      //var curRes = -1;
      //nodes.foreach { n =>
        //assert(mp(n) > curRes)
        //curRes = mp(n)
      //}
    //}
  //}

}

