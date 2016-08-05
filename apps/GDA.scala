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
    val sigmaTileVec = Vector()
    val sigmaBlkVec = Vector()
    val tlm0Vec = Vector()
    val tlm1Vec = Vector()
    val tlXVec = Vector()
    val tlYVec = Vector()
    val tlSigVec = Vector()
    val subVec = Vector()

    val accel = ComputeUnit(name="accel", parent=top, tpe=Sequential, deps=Nil) { implicit CU =>
      CounterChain(Const(1) by Const(1))
    }

    //mu0Tile := mu0(0::Cmax, subLoopPar)  // Load mu0
    val tlm0 = TileTransfer(name="tlm0", parent=accel, memctrl=m0, mctpe=TileLoad, deps=Nil, vec=tlm0Vec){ implicit CU =>
      val s0::_ = Stages(1)
      val cc = CounterChain(name="cc", Cmax by Const(1))
      Stage(s0, op1=CU.ctr(s0, cc(0)), op=Bypass, result=CU.scalarOut(s0, m0.saddr))
    }
    //mu1Tile := mu1(0::Cmax, subLoopPar)  // Load mu1
    val tlm1 = TileTransfer(name="tlm1", parent=accel, memctrl=m1, mctpe=TileLoad, deps=Nil, vec=tlm1Vec){ implicit CU =>
      val s0::_ = Stages(1)
      val cc = CounterChain(name="cc", Cmax by Const(1))
      Stage(s0, op1=CU.ctr(s0, cc(0)), op=Bypass, result=CU.scalarOut(s0, m1.saddr))
    }
    
    //Pipe.fold(rows by rTileSize par outerPar, outerAccumPar)(sigmaOut){ r =>
    //}{_+_}
    val outerBR = ComputeUnit (name="outerBR", parent=accel, tpe=MetaPipeline, deps=List(tlm0, tlm1)) { implicit CU =>
      // StateMachines / CounterChain
      val r = CounterChain(name="r", CU.scalarIn(rows) by rTileSize) //Local
      val acc = CounterChain(Cmax by Const(1)) //BlockReduce
      val rr = CounterChain.copy("innerBL", "rr")

      // SRAMs
      val sigmaBlk = SRAM(size=256, vec=sigmaBlkVec, readAddr=acc(0) , writeAddr=rr(0))
      val sigmaOut = SRAM(size=256, readAddr=acc(0) , writeAddr=acc(0))

      //// Pipeline Stages
      val x = sigmaBlk.load
      val y = sigmaOut.load
      val s0::s1::_ = Stages(2)
      Stage(s0, op1=x, op2=y, op=FixAdd, result=CU.store(s0, sigmaOut))
      Stage(s1, op1=CU.store(s0, sigmaOut), op=Bypass, result=CU.vecOut(s1, tlSigVec))
    tlSigVec}

    //yTile := y(r::r+rTileSize, subLoopPar)
    val tly = TileTransfer (name="tly", parent=outerBR, memctrl=y, mctpe=TileLoad, deps=Nil, vec=tlYVec) { implicit CU =>
      val r = CounterChain.copy(outerBR, "r")
      val rr = CounterChain(name="rr", Const(0) until rTileSize)
      val s0::_ = Stages(1)
      Stage(s0, op1=CU.ctr(s0, r(0)), op2=CU.ctr(s0, rr(0)), op=FixAdd, 
        result=CU.scalarOut(s0, y.saddr))
    }

    //xTile := x(r::r+rTileSize, 0::cols, subLoopPar)  // Load tile of x
    val tlx = TileTransfer (name="tlx", parent=outerBR, memctrl=x, mctpe=TileLoad, deps=Nil, vec=tlXVec) { implicit CU =>
      val r = CounterChain.copy(outerBR, "r")
      val rr = CounterChain(name="rr", Const(0) until rTileSize, Const(0) until CU.scalarIn(cols))
      val s0::s1::s2::_ = Stages(3)
      val t1 = CU.temp(s0)
      val t2 = CU.temp(s1)
      Stage(s0, op1=CU.ctr(s0, r(0)), op2=CU.ctr(s0, rr(0)), op=FixAdd, result=t1)
      Stage(s1, op1=t1, op2=CU.scalarIn(s0, cols), op=FixMul, result=t2)
      Stage(s2, op1=t2, op2=CU.ctr(s1, rr(1)), op=FixAdd, result=CU.scalarOut(s0, x.saddr))
    }

    //Pipe.fold(rTileSize par innerPar, prodLoopPar)(sigmaBlk){rr =>
    //}{_+_}
    val innerBL = ComputeUnit(name="innerBL", parent=outerBR, tpe=MetaPipeline, deps=List(tly, tlx)) { implicit CU =>
      // StateMachines / CounterChain
      val rr = CounterChain(name="rr", Const(0) until rTileSize)
      val actr = CounterChain(Cmax by Const(1)) //BlockReduce Ctr
      val c1 = CounterChain.copy("outProd", "c1")

      val ws0::ws1::_ = WAStages(2, "sigmaTile")
      val wa = CU.wtAddr(ws1)
      // SRAMs
      val sigmaTile = SRAM(name="sigmaTile", size=256, vec=sigmaTileVec, readAddr=actr(0), writeAddr=wa)
      val sigmaBlk = SRAM(size=256, readAddr=actr(0), writeAddr=actr(0))

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
    
    //Pipe(cols par subLoopPar){ cc =>
    //  subTile(cc) = xTile(rr,cc) - mux(yTile(rr), mu1Tile(cc), mu0Tile(cc))
    //}
    val subPipe = ComputeUnit (name="subPipe", parent=innerBL, tpe=Pipe, deps=Nil) { implicit CU =>
      // StateMachines / CounterChain
      val rr = CounterChain.copy("innerBL", "rr")
      val cc = CounterChain(name="cc", CU.scalarIn(cols) by Const(1)) //Local
      val cmu0 = CounterChain.copy(tlm0, "cc")
      val cmu1 = CounterChain.copy(tlm1, "cc")
      val cy = CounterChain.copy(tly, "rr")
      val cx = CounterChain.copy(tlx, "rr")

      val ws0::ws1::_ = WAStages(2, "xTile")
      val s0::s1::s2::s3::_ = Stages(4)

      val xWA = CU.wtAddr(ws1)
      val xRA = CU.rdAddr(s1)
      // SRAMs
      val xTile   = SRAM(size=4*16, name="xTile", vec=tlXVec, readAddr=xRA ,writeAddr=xWA)
      val yTile   = SRAM(size=4, vec=tlYVec, readAddr=rr(0), writeAddr = cy(0))
      val mu0Tile = SRAM(size=16, vec=tlm0Vec, readAddr=cc(0), writeAddr=cmu0(0))
      val mu1Tile = SRAM(size=16, vec=tlm1Vec, readAddr=cc(0), writeAddr=cmu1(0))

      // Pipeline Stages
      // xTile Write Addr Calculation
      val t1 = CU.temp(ws0)
      Stage(ws0, op1=cx(0), op2=Cmax, op=FixMul, result=t1)
      Stage(ws1, op1=t1, op2=cx(1), op=FixAdd, result=xWA)
      // xTile Read Addr Calculation
      val t2 = CU.temp(s1)
      Stage (s0, op1=rr(0), op2=Cmax, op=FixMul, result=t2)
      Stage (s1, op1=t2, op2=CU.ctr(s0, cc(0)), op=FixAdd, result=xRA)
      // Compute
      val y   = yTile.load
      val x   = xTile.load
      val t3 = CU.temp(s2)
      Stage (s2, op1=mu1Tile.load, op2=mu0Tile.load, op3=y, op=Mux, result=t3)
      Stage (s3, op1=CU.load(s2, xTile), op2=t3, op=FixSub, result=CU.vecOut(s3, subVec))
    }

    //Pipe(cols by 1, cols par prodLoopPar){ (ii,jj) =>
    //  sigmaTile(ii,jj) = subTile(ii) * subTile(jj)
    //}
    val outProd = ComputeUnit (name="outProd", parent=innerBL, tpe=Pipe, deps=List(subPipe)) { implicit CU =>
      // StateMachines / CounterChain
      val cc = CounterChain.copy("subPipe", "cc")
      val sCols = CU.scalarIn(cols)
      val c1 = CounterChain(name="c1", sCols by Const(1), sCols by Const(1))
      val ii = c1(0) 
      val jj = c1(1)

      val s0::_ = Stages(1)
      // SRAMs
      val subTile_ra   = SRAM(size=16, vec=subVec, readAddr=ii, writeAddr=cc(0))
      val subTile_ca   = SRAM(size=16, vec=subVec, readAddr=jj, writeAddr=cc(0))

      // Pipeline Stages
      Stage (s0, op1=subTile_ra.load, op2=subTile_ca.load, op=FixMul, result=CU.vecOut(s0, sigmaTileVec))
    }

    // sigma(0::Cmax, 0::Cmax, prodLoopPar) := sigmaOut
    val tlsig = TileTransfer(name="tlsig", parent=accel, memctrl=sigmaOut, mctpe=TileStore, deps=List(outerBR), vec=tlSigVec){ implicit CU =>
      val rr = CounterChain(Const(0) until Cmax, Const(0) until Cmax)
      val i = rr(0)
      val j = rr(1)
      val s0::s1::_ = Stages(2)
      val t1 = CU.temp(s0)
      Stage(s0, op1=CU.ctr(s0, i), op2=Cmax, op=FixMul, result=t1)
      Stage(s1, op1=t1, op2=CU.ctr(s0,j), op=FixAdd, result=CU.scalarOut(s1, sigmaOut.saddr))
    }


  }
}
