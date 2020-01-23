//import spatial.dsl._
//import spatial.dsl._
//import utils.io.files._
//import spatial.lang.BlackBoxBus

//class TiledPageRank2_0 extends TiledPageRank2()(ipls=1, ip=1)
//class TiledPageRank2_1 extends TiledPageRank2()(ipls=16, ip=16)
//class TiledPageRank2_2 extends TiledPageRank2()(opts=2,ipls=16, ip=16)
//class TiledPageRank2_3 extends TiledPageRank2()(opN=3,opts=3,ipls=16, ip=16)
//class TiledPageRank2_4 extends TiledPageRank2(iters=2)(ipls=1, ip=1)

//// Preprocess graph. Sort graph with highest to lowest degree
//@spatial class TiledPageRank2(
  //val ts: scala.Int=32,
  //val iters:scala.Int = 1, // number of iterations
  //val damp:scala.Float = 0.125f,
//)(
  //opN:scala.Int = 1, // N / ts
  //opE:scala.Int = 1, // parallelized on edge
  //opts:scala.Int = 1, // tile par
  //ipls:scala.Int = 16, // load store par
  //ip:scala.Int = 16,
  //ets:scala.Int = 16,
//) extends SpatialTest with PageRankHost {

  //type T = FixPt[TRUE, _16, _16] // Float

  //def main(args: Array[String]): Unit = {
    //genData
    //compGold

    //val lenData = loadCSV1D[Int](lenPath)
    //val ofstData = loadCSV1D[Int](ofstPath)
    //val edgeData = loadCSV1D[Int](edgePath)

    //val N = lenData.length // Number of nodes

    //val initRank = (1.0f.to[T] / N.to[T]).to[T]
    //val argN = ArgIn[Int]
    //val argIR = ArgIn[T]
    //val argIters = ArgIn[Int]
    //setArg(argN, N)
    //setArg(argIR, initRank)
    //setArg(argIters, iters)

    //val pageranks = DRAM[T](2*N)
    //val lens = DRAM[Int](N)
    //val ofsts = DRAM[Int](N)
    //val edges = DRAM[Int](edgeData.length)
    //setMem(lens, lenData)
    //setMem(ofsts, ofstData)
    //setMem(edges, edgeData)

    //Accel { 
      //val lenRTile = SRAM[Int](ts)
      //lenRTile load lens(0 :: ts par ipls)
      //Sequential.Foreach(argIters.value by 1) { iter =>
        //val prRTile = SRAM[T](ts)
        //val dbofst = (iter % 2 * N)
        //prRTile load pageranks(dbofst :: dbofst + ts par ipls)
        //Foreach(0 until argN.value by ts par opN) { i =>
          //val lenTile = SRAM[Int](ts)
          //val ofstTile = SRAM[Int](ts)
          //val prWTile = SRAM[T](ts)
          //val tileStart = dbofst + i
          //ofstTile load ofsts(i :: i + ts par ipls)
          //lenTile load lens(i :: i + ts par ipls)
          //Foreach(ts by 1 par opts) { j =>
            //val start = ofstTile(j)
            //val len = Reg[Int]
            //Pipe {
              //len := lenTile(j)
            //}
            //val neighbors = SRAM[Int](maxEdge)
            //neighbors load edges(start::start+len.value)
            //val rankSum = Reg[T]
            //Reduce(rankSum)(len.value by ets par opE) { et =>
              //val farNeighbors = FIFO[Int](ets)
              //val farDBNeighbors = FIFO[Int](ets)
              //val etsBound = min(ets, len.value - et)
              //val nearNeighborRanks = FIFO[T](ets)
              //val nearNeighborLens = FIFO[Int](ets)
              //Foreach(0 until etsBound par ip) { kk => 
                //val k = et + kk
                //val neighbor = neighbors(k)
                //val far = neighbor >= ts
                //val near = !far
                //farDBNeighbors.enq(dbofst + neighbor, far)
                //farNeighbors.enq(neighbor, far)
                //val neighborRank = prRTile.read(Seq(neighbor), Set(near))
                //val neighborLen = lenRTile.read(Seq(neighbor), Set(near))
                //nearNeighborRanks.enq(neighborRank, near)
                //nearNeighborLens.enq(neighborLen, near)
              //}
              //val farNeighborRanks = StreamOut[T](BlackBoxBus[T]("farNeighborRanks"))
              //farNeighborRanksFIFO gather pageranks(farDBNeighbors par ip)
              //val farNeighborLens = StreamOut[Int](BlackBoxBus[Int]("farNeighborLens"))
              //farNeighborLensFIFO gather lens(farNeighbors par ip)
              //val neighborRanks = StreamIn[T](BlackBoxBus[T]("neighborRanks"))
              //val neighborLens = StreamIn[Int](BlackBoxBus[Int]("neighborLens"))
              //mergeFIFOs(neighborRanks, farNeighborRanks, nearNeighborRanks)
              //mergeFIFOs(neighborLens, farNeighborLens, nearNeighborLens)
              //val rank = Reg[T](0)
              //Reduce(rank)(0 until etsBound.value) { k =>
                //val neighborRank = neighborRanks.value
                //val neighborLen = neighborLens.value
                //val nrank = mux(iter===0, argIR.value, neighborRank)
                //nrank / neighborLen.to[T]
              //} { _ + _ }
              //rank.value
            //} { _ + _ }
            //Pipe {
              //prWTile(j) = rankSum.value * damp + ((1-damp).to[T] / argN.value.to[T])
            //}
          //}
          //val tileStart2 = ((iter+1) % 2 * N) + i
          //pageranks(tileStart2::tileStart2+ts par ipls) store prWTile
        //}
      //}
    //}

    //val ofst = if (iters % 2 == 0) 0 else N
    //val result = getMem(pageranks)
    //val pr = Array.tabulate[T](N) { i => result(ofst+i) }
    //checkGold(pr)
  //}

//}
