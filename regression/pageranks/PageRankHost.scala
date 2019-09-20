import spatial.dsl.SpatialTest
import utils.io.files._

trait PageRankHost extends SpatialTest {

  val iters:Int
  val damp:Float

  val maxEdge = 100

  def lenPath = buildPath(IR.config.genDir, "tungsten", "len.csv")
  def ofstPath = buildPath(IR.config.genDir, "tungsten", "ofst.csv")
  def edgePath = buildPath(IR.config.genDir, "tungsten", "edges.csv")
  def goldPath = buildPath(IR.config.genDir, "tungsten", "gold.csv")

  def genData = {
    val N = 128
    val lenData = Seq.tabulate(N) { i => i % (maxEdge-1)+1 }
    val ofstData = lenData.foldLeft(List(0)) { case (prev, len) => prev :+ (prev.last + len) }
    val edgeData = lenData.view.zipWithIndex.flatMap { case (len, i) => 
      Seq.tabulate(len) { i => 
        val n = 2*(i+1) % N
        if (n == i) (n + 1) % N else n 
      }
    }
    createDirectories(dirName(lenPath))
    writeCSVNow(lenData, lenPath)
    writeCSVNow(ofstData, ofstPath)
    writeCSVNow(edgeData, edgePath)
  }

  def compGold = {
    val ofstData = loadCSVNow(ofstPath, delim=",") { _.toInt }
    val edgeData = loadCSVNow(edgePath, delim=",") { _.toInt }
    val N = ofstData.size - 1

    // Gold Page Rank
    val initRank = 1.0f / N
    val goldRank = Iterator.tabulate(iters) { i => i }.foldLeft(Seq.fill(N)(initRank)) { case (prevRank, iter) =>
      prevRank.view.zipWithIndex.map { case (rank, n) =>
        val len = ofstData(n+1) - ofstData(n)
        val ofst = ofstData(n)
        val neighbors = edgeData.view.slice(ofst, ofst+len)
        val sum = neighbors.map { e =>
          val neighborRank = prevRank(e)
          val neighborLen = ofstData(e+1) - ofstData(e)
          neighborRank / neighborLen
        }.reduce { _ + _ }
        sum * damp + ((1 - damp) / N)
      }
    }
    createDirectories(dirName(goldPath))
    writeCSVNow(goldRank, goldPath, toString={ x:Float => f"$x%e" })
  }

  import spatial.dsl._
  def checkGold[T:Bits](pageranks:DRAM1[T])(implicit ev:Cast[Text,T]) = {
    val result = getMem(pageranks)
    val goldRank = loadCSV1D[T](goldPath)
    writeCSV1D(getMem(pageranks), buildPath(IR.config.genDir, "tungsten", "rank.csv"), delim="\n", format=Some("%e"))
    val cksum = approxEql[T](result, goldRank, margin=0.05)
    println("PASS: " + cksum)
    assert(cksum)
  }

}

