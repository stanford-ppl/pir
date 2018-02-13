
import pir._
//import pir.util.enums._
//import pir.util._
//import pir.graph._

import spade._
import arch._

import pirc.test._
import pirc.util._
import pirc.codegen.Logger

import org.scalatest._
import scala.language.reflectiveCalls

class AppTests extends UnitTest { self =>

  def logDRAM(app:PIRApp, dram:Array[Option[AnyVal]]) = {
    val logger = new Logger {
      override lazy val stream = newStream("dram.log")(app)
    }
    dram.zipWithIndex.foreach { case (data, addr) => logger.dprintln(s"DRAM[$addr] = $data") }
    logger.close
  }

  def checkDram(start:Int, gold:List[AnyVal])(dram:Array[Option[AnyVal]]):Boolean = {
    var correct = true
    val N = gold.size
    for (i <- 0 until N) {
      val addr = start + i
      val dramVal = dram(addr)
      val goldVal = Some(gold(i))
      if (dramVal!=goldVal) {
        errmsg(s"DRAM($addr)($dramVal) != gold($i)($goldVal)")
        correct = false
      }
    }
    correct
  }
  def test(
      app:PIRApp, 
      arch:Option[Spade]=None,
      args:String="", 
      argOuts:String="", 
      checkDram: Option[Array[Option[AnyVal]] => Boolean]=None, 
      timeOut:Int=60,
      mapping:Boolean=true,
      verbose:Boolean=false,
      debug:Boolean=false
    ) = {
    s"$app [$args] ($argOuts)" should "success" in { 
      def runApp = {
        //if (app.pirMapping.hasRun) {
          //if (simulate) {
            //app.setArgs(args.split(" "))
            //app.simulator.reset
            //app.simulator.run
          //}
        //} else {
          var configs = ""
          configs += s" --simulate=$simulate"
          configs += s" --simulationTimeOut=$timeOut"
          configs += s" --debug=$debug"
          configs += s" --waveform=true"
          configs += s" --verbose=$verbose"
          configs += s" --mapping=$mapping"
          configs += s" --codegen=false"
          arch.foreach { arch => configs += s" --arch=$arch" }
          app.main((args + configs).split(" "))
        }
      //}
      def checkResult = {
        //assert(!app.simulator.simulator.timeOut)
        //if (argOuts != "") {
          //argOuts.split(" ").foreach { aos =>
            //val aon::aov::_ = aos.split("=").toList
            //val ao = app.top.sins.filter { _.variable.name==Some(aon) }.head
            //val pao = app.mapping.get.vimap(ao)
            //assert(pao.values(0).asBus.head.value==Some(toValue(aov)), s"ArgOut!=gold result incorrect")
          //}
        //}
        //checkDram.foreach { checkDram =>
          //val dram = app.arch.top.dram.getValue
          //logDRAM(app, dram)
          //assert(checkDram(dram), s"DRAM result incorrect")
        //}
      }
      try {
        runApp
        if (simulate) checkResult
      } catch {
        case e:Exception => errmsg(s"$e"); throw e
      }
    }
  }

  def testDotProduct(app:PIRApp, startA:Int, startB:Int, N:Int, debug:Boolean=false) = {
    val a = (startA until startA+N).toList
    val b = (startB until startB+N).toList
    val gold = a.zip(b).map{ case (aa,bb) => aa * bb }.sum
    val default = SpadeConfig.simulationTimeOut
    test(
      app, 
      args=s"x1019=$N x1037=${startA*4} x1056=${startB*4}", 
      argOuts=s"x1026_x1096=$gold", 
      timeOut=100, 
      debug=debug
    )
  }

  def testTPCHQ6(app:PIRApp, startA:Int, startB:Int, startC:Int, startD:Int, N:Int, debug:Boolean=false) = {
    val a = (startA until startA+N).toList
    val b = (startB until startB+N).toList
    val c = (startC until startC+N).toList
    val d = (startD until startD+N).toList
    val minDate = 0
    val maxDate = 9999
    val MIN_DISC = 0
    val MAX_DISC = 9999
    val gold = ((a,b,c).zipped,d).zipped.map{ case ((quant,disct,date),price) => 
      val valid = (date > minDate) && (date < maxDate) && (disct >= MIN_DISC) && (disct <= MAX_DISC) && (quant<24)
      if (valid) price * disct else 0
    }.sum.toFloat
    test(
      app, 
      args=s"x1563=$N x1607=${startA*4} x1626=${startB*4} x1588=${startC*4} x1645=${startD*4}", 
      argOuts=s"x1573_x1699=$gold",
      timeOut=100 * (N / 32),
      debug=debug
    )
  }

  def testOuterProduct(app:PIRApp, startA:Int, startB:Int, startC:Int, N:Int, debug:Boolean=false) = {
    val a = (startA until startA+N).toList
    val b = (startB until startB+N).toList

    val gold = List.tabulate(a.size, b.size) { case (i, j) =>
      a(i) * b(j)
    }.flatten

    test(
      app, 
      args=s"x1203=$N x1204=$N x1226=${startA*4} x1247=${startB*4} x1284=${startC*4}", 
      checkDram = Some(checkDram(startC, gold) _),
      timeOut=200,
      debug=debug
    )
  }

  def testMatMult(app:PIRApp, N:Int, M:Int, P:Int, startA:Int, startB:Int, startC:Int, debug:Boolean=false) = {
    val a = Array.tabulate(M, P){ case (i, j) => startA + i*P + j }
    val b = Array.tabulate(P, N){ case (i, j) => startB + i*N + j }

    val gold = List.tabulate(M, N){ case (i,j) =>
      val bCol = b.map{ row => row(j) }
      a(i).zip(bCol).map{ case (aa, bb) => aa * bb }.reduce{_+_}
    }

    test(
      app, 
      args=s"N=$N M=$M P=$P a_addr=${startA*4} b_addr=${startB*4} c_addr=${startC*4}", 
      checkDram = Some(checkDram(startC, gold.flatten) _),
      timeOut=600,
      debug=debug
    )
  }

  def testBlockReduce1D(app:PIRApp, numTile:Int, tileSize:Int, startSrc:Int, startDst:Int, debug:Boolean=false) = {
    val block = List.tabulate(numTile, tileSize){ case (i,j) => i * tileSize + j }
    app.setDram(startSrc, block.flatten)

    val gold = block.reduce[List[Int]] { case (t1, t2) => t1.zip(t2).map { case (e1, e2) => e1 + e2 } } 

    test(
      app, 
      args=s"sizeIn=${numTile*tileSize} dstFPGA_addr=${startDst*4} srcFPGA_addr=${startSrc*4}", 
      checkDram = Some(checkDram(startDst, gold) _),
      timeOut=120,
      debug=debug
    )
  }

  def testSRAMReadWrite2D(app:PIRApp, M:Int, N:Int, debug:Boolean=false) = {
    val block = Array.tabulate(M, N) { case (i,j) => i*N + j }

    val gold = block.flatten.map{ e => e*e }.reduce {_+_}

    test(
      app, 
      args=s"M=$M N=$N", 
      argOuts=s"x1026_x1096=$gold",
      timeOut=120,
      debug=debug
    )
  }

  //intercept[PIRException] {

  val simulate = false
  //val simulate = true
  // UnitTest 
  //test(InOutArg, args="x=4", argOuts="x342_x348=4", timeOut=30, debug=true)
  //test(ParSRAMReadWrite_cb, argOuts="x1026_x1096=10416", timeOut=60, debug=true)
  //testSRAMReadWrite2D(ParSRAMReadWrite2D_cb, M=2, N=32, debug=true) //TODO: fix predicate unit
  //test(SimpleSequential_cb, args="x343=2 x342=10", argOuts="x344_x356=20", debug=false)
  //test(SimpleSequential_cb, args="x343=1 x342=10", argOuts="x344_x356=10", debug=false)
  //test(SimpleReduce_cb, args="x350=10", argOuts="x351_x365=1200", debug=false)
  //testDotProduct(DotProductSeq_cb, startA=0, startB=16, N=32, debug=false)
  //testDotProduct(DotProductSeq_cb, startA=0, startB=16, N=64, debug=false)
  //testDotProduct(DotProductMeta_cb, startA=0, startB=16, N=32, debug=true)
  //testDotProduct(DotProductMeta_cb, startA=0, startB=16, N=64, debug=false)
  //testTPCHQ6(TPCHQ6_cb, startA=0, startB=10, startC=20, startD=30, N=32, debug=false)
  //testTPCHQ6(TPCHQ6_cb, startA=0, startB=10, startC=20, startD=30, N=64, debug=false)
  //testOuterProduct(OuterProduct_cb, startA=0, startB=100, startC=200, N=16, debug=false)
  //testMatMult(MatMult_inner, N=16, M=16, P=16, startA=0, startB=20, startC=40, debug=true)
  //testBlockReduce1D(BlockReduce1D, numTile=2, tileSize=16, startSrc=20, startDst=0, debug=false)
  //testBlockReduce1D(ParBlockReduce1D, numTile=2, tileSize=16, startSrc=20, startDst=0, debug=false)
  //test(MetaPipeTest, args="x222=4", argOuts="x223_x227=3", timeOut=40, debug=false)
  
  //test(InOutArg, args="x222=4", argOuts="x223_x227=8.0", debug=false)
  //test(ChainTest, args="ai_in=3 ai_out=3", argOuts="x223_x227=8", debug=true, timeOut=80)
  //test(ChainTest, args="ai_in=1 ai_out=3", argOuts="x223_x227=0", debug=true, timeOut=80)
  //test(SRAMReadWrite, argOuts="x1026_x1096=41", timeOut=60, debug=true)
  //test(ParSRAMReadWrite, argOuts="x1026_x1096=10416", timeOut=60, debug=false)
  
  val verbose = true
  val mapping = false
  //val arch = SN16x8_LD
  //val arch = SN16x13_LD
  //val arch = SN8x8_LD
  //val arch = SN4x4
  //val arch = new SN(numRows=2, numCols=2, pattern=Checkerboard)
  // Mapping Test
  // Working
  test(DotProduct         , arch=Some(SN2x2), verbose=verbose, mapping=mapping, debug=true)
  test(OuterProduct       , arch=Some(SN4x4), verbose=verbose, mapping=mapping, debug=true)
  test(TPCHQ6             , arch=Some(SN8x8), verbose=verbose, mapping=mapping, debug=true)
  test(GDA                , arch=None, verbose=verbose, mapping=mapping, debug=true)
  test(BlackScholes       , arch=Some(SN16x8_LD), verbose=verbose, mapping=mapping, debug=true)
  // Not Working
  //test(SimpleIf           , arch=Some(SN2x2), verbose=verbose, mapping=mapping, debug=true)
  //test(SPMV_CRS           , arch=Some(arch), verbose=verbose, mapping=mapping, debug=true)
  //test(Backprop           , arch=Some(SN8x8), verbose=verbose, mapping=mapping, debug=true)
  //test(Gibbs_Ising2D      , arch=Some(arch), verbose=verbose, mapping=mapping, debug=true)
  //test(Kmeans_plasticine  , arch=Some(arch), verbose=verbose, mapping=mapping, debug=true)
  //test(PageRank_plasticine, arch=Some(arch), verbose=verbose, mapping=mapping, debug=true)
  //test(GEMM_Blocked       , arch=Some(arch), verbose=verbose, mapping=mapping, debug=true)
  //test(SYRK_col                , arch=Some(arch), verbose=verbose, mapping=mapping, debug=true)
}

