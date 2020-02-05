import spatial.dsl._
import spatial.lib.ML._
import utils.io.files._
import spatial.lang.{FileBus,FileEOFBus}
import spatial.metadata.bounds._

class StreamSimple_0 extends StreamSimple()

@spatial abstract class StreamSimple(
  val N:scala.Int = 10,
) extends SpatialTest {

  type T = Int

  def main(args: Array[String]): Unit = {
    val infile = buildPath(IR.config.genDir, "tungsten", "in.csv")
    val outfile = buildPath(IR.config.genDir, "tungsten", "out.csv")
    val goldfile = buildPath(IR.config.genDir, "tungsten", "gold.csv")
    createDirectories(dirName(infile))
    val inputs = List.tabulate(N) {i => 
      val lastBit = IfElse(i == N-1) {1} {0} // Unstaged if statement
      List(i, lastBit)
    }
    val gold = inputs.map { case List(data, lastBit) => List(data + 3, lastBit) }
    writeCSVNow2D(inputs, infile)
    writeCSVNow2D(gold, goldfile)

    val in  = StreamIn[Tup2[T,Bit]](FileEOFBus[Tup2[T,Bit]](infile))
    val out  = StreamOut[Tup2[T,Bit]](FileEOFBus[Tup2[T,Bit]](outfile))
    in.count = N
    Accel{
      Foreach(*) { _ =>
        val x = in.value
        out := Tup2(x._1 + 3, x._2)
      }
    }
    val outData = loadCSV2D[Int](outfile)
    val goldData = loadCSV2D[Int](goldfile)
    val cksum = approxEql[Int](outData,goldData)
    println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    assert(cksum)
  }
}

@spatial class StreamFake extends SpatialTest {
  val N = 10

  type T = Int

  def main(args: Array[String]): Unit = {
    val in = ArgIn[T]
    val out = ArgOut[T]
    setArg(in, N)
    Accel{
      val fifo = FIFO[T](16)
      Foreach(N by 1) { i =>
        fifo.enq(in.value)
      }
      Foreach(N by 1) { _ =>
        val x = fifo.deq()
        out := x
      }
    }
    val cksum = getArg(out) == N
    println("PASS: " + cksum + s" (${this.getClass.getSimpleName})")  
    assert(cksum)
  }
}
