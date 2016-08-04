import pir.graph._
import pir.graph.{MetaPipeline => MetaPipe}
import pir.graph
import pir.codegen._
import pir.Design
import pir.PIRApp
import pir.PIRMisc._

object GDA extends PIRApp {
  def main(args: String*)(top:Top) = {
    val m0 = MemoryController("m0", TileLoad, OffChip())
    val m1 = MemoryController("m1", TileLoad, OffChip())
    val x = MemoryController("x", TileLoad, OffChip())
    val y = MemoryController("y", TileLoad, OffChip())
    val sigmaOut = MemoryController("sigmaOut", TileStore, OffChip())
    val rows = ArgIn()
    val cols = ArgIn()
    val rTileSize = Const(4l)
    val Cmax = Const(16)
    val CmaxSqr = Const(16^2)
    val sigmaTileVec = Vector()
    val sigmaBlkVec = Vector()

    //mu0Tile := mu0(0::Cmax, subLoopPar)  // Load mu0
    val tlm0 = TileTransfer(name="tlm0", parent=top, memctrl=m0, mctpe=TileLoad, deps=Nil){ implicit CU =>
      val s0::_ = Stages(1)
      val cc = CounterChain(name="cc", Cmax by Const(1))
      Stage(s0, op1=CU.ctr(s0, cc(0)), op=Bypass, result=CU.scalarOut(s0, m0.saddr))
    }
    //mu1Tile := mu1(0::Cmax, subLoopPar)  // Load mu1
    val tlm1 = TileTransfer(name="tlm1", parent=top, memctrl=m1, mctpe=TileLoad, deps=Nil){ implicit CU =>
      val s0::_ = Stages(1)
      val cc = CounterChain(name="cc", Cmax by Const(1))
      Stage(s0, op1=CU.ctr(s0, cc(0)), op=Bypass, result=CU.scalarOut(s0, m1.saddr))
    }
    
    //Pipe.fold(rows by rTileSize par outerPar, outerAccumPar)(sigmaOut){ r =>
    //}{_+_}
    val outerBR = ComputeUnit (name="outerBR", parent=top, tpe=MetaPipeline, deps=List(tlm0, tlm1)) { implicit CU =>
      // StateMachines / CounterChain
      val r = CounterChain(name="r", CU.scalarIn(rows) by rTileSize) //Local
      //val acc = CounterChain(sigmaOutWidth by Const(1)) //BlockReduce
      //TODOval rr = CounterChain.copy(ComputeUnit(2), "rr")

      // SRAMs
      //val sigmaBlk = SRAM(write = ComputeUnit(1), readAddr = acc(0) , writeAddr = rr(0))
      //val sigmaOut = SRAM(write = local, readAddr = acc(0) , writeAddr = acc(0))

      //// Pipeline Stages
      //val x = sigmaTile.load
      //val y = sigmaOut.load
      //Stage (op1=x, op2=y, op=Add, result=PR.store(sigmaOut))
    }

    //yTile := y(r::r+rTileSize, subLoopPar)
    val tly = TileTransfer (name="tly", parent=outerBR, memctrl=y, mctpe=TileLoad, deps=List("innerBL")) { implicit CU =>
      val r = CounterChain.copy(outerBR, "r")
      val rr = CounterChain(Const(0) until rTileSize)
      val s0::_ = Stages(1)
      Stage(s0, op1=CU.ctr(s0, r(0)), op2=CU.ctr(s0, rr(0)), op=FixAdd, 
        result=CU.scalarOut(s0, y.saddr))
    }

    //xTile := x(r::r+rTileSize, 0::cols, subLoopPar)  // Load tile of x
    val tlx = TileTransfer (name="tlx", parent=outerBR, memctrl=x, mctpe=TileLoad, deps=List("innerBL")) { implicit CU =>
      val r = CounterChain.copy(outerBR, "r")
      val rr = CounterChain(Const(0) until rTileSize)
      val c = CounterChain(Const(0) until CU.scalarIn(cols))
      val s0::s1::s2::_ = Stages(3)
      val t1 = CU.temp(s0)
      val t2 = CU.temp(s1)
      Stage(s0, op1=CU.ctr(s0, r(0)), op2=CU.ctr(s0, rr(0)), op=FixAdd, result=t1)
      Stage(s1, op1=t1, op2=Cmax, op=FixMul, result=t2)
      Stage(s2, op1=t2, op2=c(0), op=FixAdd, result=CU.scalarOut(s0, x.saddr))
    }

    //Pipe.fold(rTileSize par innerPar, prodLoopPar)(sigmaBlk){rr =>
    //}{_+_}
    val innerBL = ComputeUnit(name="innerBL", parent=outerBR, tpe=MetaPipeline, deps=List(tly, tlx)) { implicit CU =>
      // StateMachines / CounterChain
      val rr = CounterChain(Const(0) until rTileSize)
      val actr = CounterChain(Cmax by Const(1)) //BlockReduce Ctr
      val c1 = CounterChain.copy("outProd", "c1")

      val ws0::ws1::_ = WAStages(2, "sigmaTile")
      val wa = CU.wtAddr(ws1)
      // SRAMs
      val sigmaTile = SRAM("sigmaTile", size=16, vec=sigmaTileVec, readAddr=actr(0), writeAddr=wa)
      val sigmaBlk = SRAM(size=16, readAddr=actr(0), writeAddr=actr(0))

      // Remote Addr Calc for sigmaTile 
      val ii = c1(0)
      val jj = c1(1)
      val t1 = CU.temp(ws0)
      Stage (ws0, op1=ii, op2=Cmax, op=FixMul, result=t1)
      Stage (ws1, op1=t1, op2=jj, op=FixAdd, result=wa)

      val s0::s1::_ = Stages(2)
      // Accumulate
      val st = CU.store(s0, sigmaBlk)
      Stage (s0, op1=sigmaTile.load, op2=sigmaBlk.load, op=FixAdd, result=st)
      Stage (s1, op1=st, op=Bypass, result=CU.vecOut(s1, sigmaBlkVec))
    }
    
    /*
    //Pipe(cols par subLoopPar){ cc =>
    //  subTile(cc) = xTile(rr,cc) - mux(yTile(rr), mu1Tile(cc), mu0Tile(cc))
    //}
    ComputeUnit (id=2) {
      // StateMachines / CounterChain
      val rr = CounterChain.copy(ComputeUnit(1), rr)
      val cc = CounterChain(cols by 1, dep=rr, type=pipeline) //Local
      val cmu0 = CounterChain.copy(TileTransfer(0), "cc")
      val cmu1 = CounterChain.copy(TileTransfer(1), "cc")
      val cy = CounterChain.copy(TileTransfer(2), "rr")
      val cx = CounterChain.copy(TileTransfer(3), "rr")

      // SRAMs
      val xTile   = SRAM(write = TileTransfer(3), readAddr = PR(stage="xTileRA", id="xTileRA") , writeAddr = PR(stage="xTileWA", id="xTileRA"))
      val yTile = SRAM(write = TileTransfer(2), readAddr = rr(0), writeAddr = cy(0))
      val mu0Tile = SRAM(write = TileTransfer(0), readAddr = cc(0), writeAddr = cmu0(0))
      val mu1Tile = SRAM(write = TileTransfer(1), readAddr = cc(0), writeAddr = cmu1(0))

      // Pipeline Stages
        // Remote Addr Calc for xTile
      val temp1 = PR.temp
      val temp2 = PR.temp
      val s0 = Stage (op1=cx(0), op2=Const(xTile.size(1)), op=Mul, result=PR(temp1))
      val s1 = Stage (name="xTileWA", op1=PR(s0,temp1), op2=cx(1), op=Add, result=PR(temp2))
      // xTile readAddr calculation
      val temp3 = PR.temp
      val temp4 = PR.temp
      Stage (op1=rr(0), op2=Const(xTile.size(1)), op=Mul, result=temp3)
      Stage (name="xTileRA", op1=PR(temp3), op2=PR.ctr(cc(0)), op=Add, result=PR(temp4))
      // Compute
      val y   = yTile.load
      val x   = xTile.load
      val mu1 = mu1.load
      val mu0 = mu0.load
      val temp3 = PR.temp
      Stage (op1=mu1, op2=mu0, op3=y, op=mux, result=temp3)
      Stage (op1=x, op2=temp3, op=sub, result=PR.vecOut)
    }

    */
    //Pipe(cols by 1, cols par prodLoopPar){ (ii,jj) =>
    //  sigmaTile(ii,jj) = subTile(ii) * subTile(jj)
    //}
    val outProd = ComputeUnit (name="outProd", parent=innerBL, tpe=MetaPipeline, deps=Nil) { implicit CU =>
      // StateMachines / CounterChain
      //val c2 = CounterChain.copy(ComputeUnit(2), "cc")
      val sCols = CU.scalarIn(cols)
      val c1 = CounterChain(name="c1", sCols by Const(1), sCols by Const(1))
      val ii = c1(0) 
      val jj = c1(1)

      val s0::_ = Stages(1)
      // SRAMs
      //val subTile_ra   = SRAM(write=ComputeUnit(2), readAddr=ii, writeAddr=c2(0))
      //val subTile_ca   = SRAM(write=ComputeUnit(2), readAddr=jj, writeAddr=c2(0))

      // Pipeline Stages
      //val a = PR(-1).load(subTile_ra) // val a = subTile_ra.read
      //val b = PR(-1).load(subTile_ca) // val b = subTile_ca.read
      //Stage (op1=a, op2=b, op=Mul, result=PR.vecOut)
      
      Stage (s0, op1=Const(0), op=Bypass, result=CU.vecOut(s0, sigmaTileVec))
    }

    val tlsig = TileTransfer(name="tlsig", parent=top, memctrl=sigmaOut, mctpe=TileStore, deps=List(outProd)){ implicit CU =>
    }


  }
}
