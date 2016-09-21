package pir.test

import pir._
import pir.typealias._
import pir.misc._
import pir.graph._
import pir.graph.enums._
import pir.graph.traversal.{CUDotPrinter}
import plasticine.main._
import plasticine.config._
import pir.plasticine.graph.{ComputeUnit => PCU, Top => PTop, SwitchBoxes}
import pir.graph.mapper._
import pir.graph.traversal._
import scala.language.reflectiveCalls

import org.scalatest._
import scala.util.{Try, Success, Failure}

class CUMapperTest extends UnitTest with Metadata {

  "Point-to-point connection mapping" should "success" in {
    new Design {
      top = Top()
      // Nodes
      val sls = List.fill(8)(Scalar())
      val vts = List.fill(2)(Vector())
      val c0 = Pipeline("c0", top, Nil){ implicit CU => 
        CU.vecOut(vts(0)) 
        CU.scalarOut(sls(0)) 
        CU.scalarOut(sls(1)) 
      }
      val c1 = Pipeline("c1", top, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        CU.scalarIn(sls(0))
        //CU.vecOut(vts(1)) 
        CU.scalarOut(sls(6))
        CU.scalarOut(sls(7))
      }
      val c2 = Pipeline("c2", top, Nil){ implicit CU => 
        CU.scalarIn(sls(2))
        CU.scalarIn(sls(3))
        CU.scalarIn(sls(4))
        CU.scalarIn(sls(5))
        CU.scalarIn(sls(6))
        CU.scalarIn(sls(7))
      }
      val c3 = Pipeline("c3", top, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        //CU.vecIn(vts(1))
        CU.scalarIn(sls(6))
        CU.scalarIn(sls(7))
      }
      val c4 = Pipeline("c4", top, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        CU.scalarIn(sls(0))
        CU.scalarIn(sls(1))
        CU.scalarOut(sls(2))
        CU.scalarOut(sls(3))
        CU.scalarOut(sls(4))
        CU.scalarOut(sls(5))
      }
      val cus = c0::c1::c2::c3::c4::Nil

      // PNodes
      override val arch = new Spade {
        val numLanes = 4
        val numRCUs = 5
        val numVins = 2
        val numRegs = 10
        val rcus = List.tabulate(numRCUs) { i =>
          ConfigFactory.genRCU(numLanes, numVins, 0, numRegs)
        } 
        val ttcus = Nil
        val sbs = Nil 
        val top = PTop(numLanes, 0, 0)
        val wordWidth = 32

        /* Network Constrain */ 
        rcus(1).vins(0) <== rcus(0).vout 
        rcus(1).vins(1) <== rcus(0).vout
        rcus(2).vins(0) <== rcus(1).vout 
        rcus(2).vins(1) <== rcus(4).vout
        rcus(3).vins(0) <== rcus(0).vout 
        rcus(3).vins(1) <== rcus(1).vout
        rcus(4).vins(0) <== rcus(0).vout 
        rcus(4).vins(1) <== rcus(0).vout
      }
      val pcus = arch.rcus

      // Mapping
      val soMapper = new ScalarOutMapper()
      val viMapper = new VecInMapper()
      val mapper = new CUMapper(soMapper, viMapper)
      Try {
        mapper.mapCUs(pcus, cus, PIRMap.empty, (m:PIRMap) => m)
      } match {
        case Success(mapping) =>
          new CUDotPrinter("TestP2P.dot").print(pcus, cus, mapping)
        case Failure(e) =>
          new CUDotPrinter("TestP2P.dot").print(pcus, cus)
          throw e
      }
      // Printer
    }
  }

  def genSwitchNetworkConfig = new Spade {
    val numLanes = 4
    val numRowCUs = 4
    val numColCUs = 4
    val numRCUs = numRowCUs * numColCUs
    val numVins = 4
    val numRegs = 20
    val wordWidth = 32
    val top = PTop(numLanes, 0, 0)
    val ttcus = Nil
    val switchBoxes = SwitchBoxes(numRowCUs+1, numColCUs+1, numLanes)
    override val sbs = switchBoxes.flatten 
    for (i <- 0 until switchBoxes.size) {
      for (j <- 0 until switchBoxes.head.size) {
        coordOf(switchBoxes(i)(j)) = (i,j) 
      }
    }
    val cuArray = List.tabulate(numRowCUs, numColCUs) { case (i, j) =>
      ConfigFactory.genRCU(numLanes, numVins, 0, numRegs).coord(i,j)
    }
    /* Network Constrain */ 
    ConfigFactory.genSwitchNetwork(cuArray, switchBoxes)
    val rcus = cuArray.flatten
  }

  def quote(pne:PNE)(implicit spade:Spade) = CUDotPrinter.quote(pne)

  lazy val design = new Design {
    // PNodes
    implicit override val arch = genSwitchNetworkConfig
    val mapper:CUSwitchMapper = new CUSwitchMapper(new ScalarOutMapper())
    def checkRange(start:PCU, min:Int, max:Int, shouldContain:List[PCU], shouldNotContain:List[PCU]) = {
      def cuCons(pcu:PCU, path:CUSwitchMapper.Path) = (path.size >= min) && (path.size < max) && (pcu!=start)
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
    new CUDotPrinter("TestSwitch.dot").print((arch.cus, arch.sbs))
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
  "SwitchBox Connection 4 hop" should "success" in {
    val arr = design.arch.cuArray
    val shouldNotContain = List(arr(0)(3), arr(1)(1), arr(2)(1))
    design.checkRange(arr(1)(1), 4, 5, design.arch.cus.diff(shouldNotContain), shouldNotContain)
  }

  "SwitchBox Connection 5 hop" should "success" in {
    val arr = design.arch.cuArray
    val shouldNotContain = List(arr(1)(1))
    design.checkRange(arr(1)(1), 1, 7, design.arch.cus.diff(shouldNotContain), shouldNotContain)
  }

  "SwitchBox Connection 5 Compare BFS advance with DFS advance" should "success" in {
    val arr = design.arch.cuArray
    val start = arr(1)(1); val min = 1; val max = 7
    def cuCons(pcu:PCU, path:CUSwitchMapper.Path) = (path.size >= min) && (path.size < max) && (pcu!=start)
    def sbCons(psb:PSB, path:CUSwitchMapper.Path) = (path.size < max)
    val result1 = design.mapper.advanceBFS(start, cuCons _, sbCons _)
    val result2 = design.mapper.advanceDFS(start, cuCons _, sbCons _)
    result1 should equal (result2)
  }

  "SwitchBox Mapping" should "success" taggedAs(WIP) in {
    new Design {
      top = Top()
      // Nodes
      val sls = List.fill(8)(Scalar())
      val vts = List.fill(5)(Vector())
      val c0 = Pipeline("c0", top, Nil){ implicit CU => 
        CU.vecOut(vts(0)) 
      }
      val c1 = Pipeline("c1", top, Nil){ implicit CU => 
        CU.vecIn(vts(0))
        CU.vecOut(vts(1)) 
      }
      val c2 = Pipeline("c2", top, Nil){ implicit CU => 
        CU.vecIn(vts(1))
        CU.vecIn(vts(0))
        CU.vecOut(vts(2)) 
      }
      val c3 = Pipeline("c3", top, Nil){ implicit CU => 
        CU.vecIn(vts(2))
        CU.vecOut(vts(3)) 
      }
      val c4 = Pipeline("c4", top, Nil){ implicit CU => 
        CU.vecIn(vts(3))
      }
      val cus = c0::c1::c2::c3::c4::Nil
      // PNodes
      implicit override val arch = genSwitchNetworkConfig
      // Mapping
      val mapper:CUSwitchMapper = new CUSwitchMapper(new ScalarOutMapper())
      Try {
        mapper.mapCUs(arch.cus, cus, PIRMap.empty, (m:PIRMap) => m)
      } match {
        case Success(mapping) =>
          new CUDotPrinter("TestSwitchMapping.dot").print((arch.cus, arch.sbs), cus, mapping)
        case Failure(e) =>
          new CUDotPrinter("TestSwitchMapping.dot").print((arch.cus, arch.sbs), cus)
          throw e
      }
    }
  }

}

