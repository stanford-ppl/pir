package pir.test

import pir.{Design, Config}
import pir.typealias._
import pir.misc._
import pir.graph._
import pir.graph.enums._
import pir.codegen.{DotCodegen}
import pir.plasticine.main._
import pir.plasticine.config._
import pir.plasticine.graph.{ComputeUnit => PCU, Top => PTop, Node => PNode, SwitchBox}
import pir.graph.mapper._
import pir.graph.traversal._
import scala.language.reflectiveCalls

import org.scalatest.{Sequential => _, _}
import scala.util.{Try, Success, Failure}

class CUSwitchMapperTest extends UnitTest with Metadata {
  type Path = CUSwitchMapper.Path 
  type PathMap = CUSwitchMapper.PathMap 

  def quote(pne:PNE)(implicit design:Design) = DotCodegen.quote(pne)

  lazy val design = new Design {
    // PNodes
    override val arch = SN_4x4 
      val mapper:CUSwitchMapper = new CUSwitchMapper(new OutputMapper(), None)
    def checkRange(start:PCU, min:Int, max:Int, shouldContain:List[PCU], shouldNotContain:List[PCU]) = {
      def cuCons(toVin:PIB, path:CUSwitchMapper.Path) = { 
        val pcu = toVin.src
        (path.size >= min) && (path.size < max) && (pcu!=start)
      }
      def sbCons(psb:PSB, path:CUSwitchMapper.Path) = (path.size < max)
      val result = mapper.advance(start, cuCons _, sbCons _)
      // println(s"start: ${quote(start)}")
      //result.foreach { case (to, path) =>
      //  println(s"- hop:${path.size} to:${quote(to)} path:${CUSwitchMapper.quote(path)}")
      //}
      //println(s"number of options: ${result.size}")
      val neighbors = result.map(_._1)
      shouldContain.foreach { c =>
        assert(neighbors.contains(c))
      }
      shouldNotContain.foreach { c =>
        assert(!neighbors.contains(c))
      }
    }
    new CUDotPrinter("TestSwitch.dot").print
  }

  "SwitchBox Connection 1 hop" should "success" in {
    val arr = design.arch.cuArray
    val shouldContain = List(arr(2)(1), arr(1)(2));
    design.checkRange(arr(1)(1), 1, 2, shouldContain, design.arch.cus.diff(shouldContain))
  }
  "SwitchBox Connection 2 hop" should "success" in {
    val arr = design.arch.cuArray
    val shouldContain = List(arr(2)(0), arr(2)(1), arr(2)(2))
    design.checkRange(arr(1)(1), 2, 3, shouldContain, design.arch.cus.diff(shouldContain))
  }
  "SwitchBox Connection 3 hop" should "success" in {
    val arr = design.arch.cuArray
    val shouldContain = List(arr(1)(0), arr(2)(0), arr(3)(0),
                         arr(2)(1), arr(3)(1),
                         arr(1)(2), arr(2)(2), arr(3)(2),
                         arr(2)(3))
    design.checkRange(arr(1)(1), 3, 4, shouldContain, design.arch.cus.diff(shouldContain))
  }
  "SwitchBox Connection 4 hop" should "success" taggedAs(WIP) in {
    val arr = design.arch.cuArray
    val shouldNotContain = List(arr(0)(3), arr(1)(1), arr(2)(1))
    design.checkRange(arr(1)(1), 4, 5, design.arch.rcus.diff(shouldNotContain), shouldNotContain)
  }

  "SwitchBox Connection 5 hop" should "success" in {
    val arr = design.arch.cuArray
    val shouldNotContain = List(arr(1)(1))
    design.checkRange(arr(1)(1), 1, 7, design.arch.cus.diff(shouldNotContain), shouldNotContain)
  }

  "SwitchBox Connection 5 Compare BFS advance with DFS advance" should "success" in {
    val arr = design.arch.cuArray
    val start = arr(1)(1); val min = 1; val max = 7
    def cuCons(toVin:PIB, path:CUSwitchMapper.Path) = { 
      val pcu = toVin.src
      (path.size >= min) && (path.size < max) && (pcu!=start)
    }
    def sbCons(psb:PSB, path:CUSwitchMapper.Path) = (path.size < max)
    val result1 = CUSwitchMapper.advanceBFS((pne:PNE) => pne.vouts)(start, cuCons _, sbCons _)(design)
    val result2 = CUSwitchMapper.advanceDFS((pne:PNE) => pne.vouts)(start, cuCons _, sbCons _)(design)
    result1 should equal (result2)
  }

  "SwitchBox Mapping" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val vts = List.fill(5)(Vector())
      val outer = Sequential("outer", top, Nil) { implicit CU => }
      val c0 = Pipeline("c0", outer, Nil){ implicit CU => 
        CU.vecOut(vts(0)) 
      }
      val c1 = Pipeline("c1", outer, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        CU.vecOut(vts(1)) 
      }
      val c2 = Pipeline("c2", outer, Nil){ implicit CU => 
        CU.vecIn(vts(1))
        CU.vecIn(vts(0))
        CU.vecOut(vts(2)) 
      }
      val c3 = Pipeline("c3", outer, Nil){ implicit CU => 
        CU.vecIn(vts(2))
        CU.vecOut(vts(3)) 
        CU.vecIn(vts(1))
      }
      val c4 = Pipeline("c4", outer, Nil){ implicit CU => 
        CU.vecIn(vts(1))
        CU.vecIn(vts(3))
      }
      val cus = c0::c1::c2::c3::c4::Nil
      top.updateFields(cus, outer::Nil, Nil, vts, Nil)
      // PNodes
      override val arch = SN_4x4 
      // Mapping
      val mapper:CUSwitchMapper = new CUSwitchMapper(new OutputMapper(), None)

      new PIRNetworkDotGen().run
      Try {
        mapper.map(PIRMap.empty)
      } match {
        case Success(mapping) => 
          new CUDotPrinter("TestSwitchMapping.dot").print
        case Failure(e) =>
          println(e)
          new CUDotPrinter("TestSwitchMapping.dot").print; throw e
      }
    }
  }

  "SwitchBox Mapping - DotProduct" should "success" in {
    new Design {
      top = Top()
      val aos = List.tabulate(1) { i => ArgOut(s"ao$i") }
      val sls = Nil
      // Nodes
      val vts = List.fill(12)(Vector())
      val outer = Sequential("outer", top, Nil) { implicit CU => }
      val c00 = Pipeline("c00", outer, Nil){ implicit CU => 
        CU.vecOut(vts(4)) 
      }
      val c01 = Pipeline("c01", outer, Nil){ implicit CU => 
        CU.vecOut(vts(5)) 
      }
      val c0 = Pipeline("c0", outer, Nil){ implicit CU => 
        CU.vecOut(vts(0)) 
        CU.vecIn(vts(4))
        CU.vecIn(vts(5))
      }
      val c10 = Pipeline("c10", outer, Nil){ implicit CU => 
        CU.vecOut(vts(6)) 
      }
      val c11 = Pipeline("c11", outer, Nil){ implicit CU => 
        CU.vecOut(vts(7)) 
      }
      val c1 = Pipeline("c1", outer, Nil){ implicit CU => 
        CU.vecIn(vts(6))
        CU.vecIn(vts(7))
        CU.vecOut(vts(1)) 
      }
      val c20 = Pipeline("c20", outer, Nil){ implicit CU => 
        CU.vecOut(vts(8)) 
      }
      val c21 = Pipeline("c21", outer, Nil){ implicit CU => 
        CU.vecOut(vts(9)) 
      }
      val c2 = Pipeline("c2", outer, Nil){ implicit CU => 
        CU.vecOut(vts(2)) 
        CU.vecIn(vts(8))
        CU.vecIn(vts(9))
      }
      val c30 = Pipeline("c30", outer, Nil){ implicit CU => 
        CU.vecOut(vts(10)) 
      }
      val c31 = Pipeline("c31", outer, Nil){ implicit CU => 
        CU.vecOut(vts(11)) 
      }
      val c3 = Pipeline("c3", outer, Nil){ implicit CU => 
        CU.vecOut(vts(3)) 
        CU.vecIn(vts(10))
        CU.vecIn(vts(11))
      }
      val c4 = Pipeline("c4", outer, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        CU.vecIn(vts(1))
        CU.vecIn(vts(2))
        CU.vecIn(vts(3))
        CU.scalarOut(aos(0))
      }
      val cus = c00::c01::c0::c10::c11::c1::c20::c21::c2::c30::c31::c3::c4::Nil
      top.updateFields(cus, outer::Nil, sls ++ aos, vts, Nil)
      // PNodes
      implicit override val arch = SN_4x4 

      new ScalarBundling().run
      new PIRPrinter().run
      // Mapping
      val mapper:CUSwitchMapper = new CUSwitchMapper(new OutputMapper(), None)

      new PIRNetworkDotGen().run
      Try {
        mapper.map(PIRMap.empty)
      } match {
        case Success(mapping) => 
          new CUDotPrinter("TestDotProduct.dot").print(mapping)
        case Failure(e) => 
          MapperLogger.dprintln(e)
          MapperLogger.close
          new CUDotPrinter("TestDotProduct.dot").print; throw e
      }
    }
  }

  "SwitchBox Mapping: Dependency out of order" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val vts = List.fill(5)(Vector())
      val outer = Sequential("outer", top, Nil) { implicit CU => }
      val c0 = Pipeline("c0", outer, Nil){ implicit CU => 
        CU.vecOut(vts(0)) 
      }
      val c1 = Pipeline("c1", outer, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        CU.vecOut(vts(1)) 
      }
      val c2 = Pipeline("c2", outer, Nil){ implicit CU => 
        CU.vecIn(vts(1))
        CU.vecIn(vts(0))
        CU.vecOut(vts(2)) 
      }
      val c3 = Pipeline("c3", outer, Nil){ implicit CU => 
        CU.vecIn(vts(1))
        CU.vecIn(vts(2))
        CU.vecOut(vts(3)) 
      }
      val c4 = Pipeline("c4", outer, Nil){ implicit CU => 
        CU.vecIn(vts(1))
        CU.vecIn(vts(3))
      }
      val cus = (c0::c1::c2::c3::c4::Nil).reverse
      top.updateFields(cus, outer::Nil, Nil, vts, Nil)
      // PNodes
      implicit override val arch = SN_4x4 
      // Mapping
      val mapper:CUSwitchMapper = new CUSwitchMapper(new OutputMapper(), None)
      new PIRNetworkDotGen().run
      Try {
        mapper.map(PIRMap.empty)
      } match {
        case Success(mapping) => 
          new CUDotPrinter("TestOODependency.dot").print
        case Failure(e) => 
          new CUDotPrinter("TestOODependency.dot").print; throw e
      }
      MapperLogger.close
    }
  }

  "SwitchNetwork Connection" should "success" taggedAs(WIP) in {
    new Design {
      top = Top()
      val aos = Nil 
      val sls = Nil
      // Nodes
      val vts = Nil 
      val cus = Nil 
      top.updateFields(cus, Nil, sls ++ aos, vts, Nil)
      // PNodes
      implicit override val arch = SN_4x4 

      def advance(start:PNE, validCons:(PIB, Path) => Boolean, advanceCons:(PSB, Path) => Boolean):PathMap =
        CUSwitchMapper.advance((pne:PNE) => pne.vouts)(start, validCons, advanceCons)

      def advanceCons(psb:PSB, path:Path) = { 
        (path.size < 5) // path is within maximum hop to continue
      }
      {
        val start = arch.top
        def validCons(toVin:PIB, path:Path) = {
          val to = toVin.src
          coordOf.get(to).fold(false) { _ == (1,1) } &&
          (to!=start) // path doesn't end at depended CU
        }
        val paths = advance(start, validCons _, advanceCons _)
        assert(paths.size>0)
      }
      {
        val start = arch.ttcus(3) 
        def validCons(toVin:PIB, path:Path) = {
          val to = toVin.src
          to == arch.top &&
          (to!=start) // path doesn't end at depended CU
        }
        val paths = advance(start, validCons _, advanceCons _)
        assert(paths.size>0)
      }
      {
        val start = arch.ttcus(3) 
        val target = arch.cuArray(1)(1) 
        def validCons(toVin:PIB, path:Path) = {
          val to = toVin.src
          to == target &&
          (to!=start) // path doesn't end at depended CU
        }
        val paths = advance(start, validCons _, advanceCons _)
        assert(paths.size>0)
      }
      {
        val start = arch.cuArray(1)(1) 
        val target = arch.ttcus(3)  
        def validCons(toVin:PIB, path:Path) = {
          val to = toVin.src
          to == target &&
          (to!=start) // path doesn't end at depended CU
        }
        val paths = advance(start, validCons _, advanceCons _)
        assert(paths.size>0)
      }
      {
        val start = arch.ttcus(3) 
        val target = arch.cuArray(0)(1) 
        def validCons(toVin:PIB, path:Path) = {
          val to = toVin.src
          to == target &&
          (to!=start) // path doesn't end at depended CU
        }
        val paths = advance(start, validCons _, advanceCons _)
        assert(paths.size>0)
      }
      {
        val start = arch.cuArray(0)(1) 
        val target = arch.ttcus(3)  
        def validCons(toVin:PIB, path:Path) = {
          val to = toVin.src
          to == target &&
          (to!=start) // path doesn't end at depended CU
        }
        val paths = advance(start, validCons _, advanceCons _)
        assert(paths.size>0)
      }
    }
  }

}

