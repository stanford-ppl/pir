package lattice

object SimplexHelper {
  private def sortNetworkMap(width: Int) = {
    def bitonicSort(in: List[Int]): List[List[Tuple2[Int, Int]]] = {
      if (in.length == 2) List(List((in.head, in(1)))) else {
        val (l, h) = in.splitAt(in.length / 2)
        val s = bitonicSort(l).zip(bitonicSort(h)).map { case (l, h) => l ::: h }
        l.zip(h) :: s
      }
    }

    def bitonicMerge(in: List[Int]): List[List[Tuple2[Int, Int]]] = {
      if (in.length == 2) bitonicSort(in) else {
        val (l, h) = in.splitAt(in.length / 2)
        val s = bitonicSort(l).zip(bitonicSort(h)).map { case (l, h) => l ::: h }
        l.zip(h.reverse) :: s
      }
    }

    def sort(in: List[Int]): List[List[Tuple2[Int, Int]]] = {
      if (in.length == 2) bitonicMerge(in) else {
        val (l, h) = in.splitAt(in.length / 2)
        val s = sort(l).zip(sort(h)).map { case (l, h) => l ::: h }
        s ::: bitonicMerge(in)
      }
    }

    sort(List.range(0, width))
  }

  def sort(width: Int): List[List[(Int, Int)]] = {

    // Because scala doesn't have log2 or clz
    var nextPow = 1
    while (nextPow <= width) {
      nextPow *= 2
    }

    val unfiltered = sortNetworkMap(nextPow)
    unfiltered.map {
      stage => {
        stage filter { pair => pair._1 < width && pair._2 < width }
      }
    }
  }

  def compute_complement(stage: List[(Int, Int)], num_lanes: Int): IndexedSeq[Int] = {
    val used = (stage map {
      _._1
    }) ++ (stage map {
      _._2
    })
    (0 until num_lanes) filterNot used.contains
  }


  def run(width: Int) = {
    val rand = scala.util.Random
    val values = ((0 until width) map { _ => rand.nextInt(64) }).toArray
    val stages = sort(width)
    stages foreach {
      stage => {
        stage foreach {
          pair => {
            val a = values(pair._1)
            val b = values(pair._2)
            values(pair._1) = scala.math.min(a, b)
            values(pair._2) = scala.math.max(a, b)
          }
        }
      }
    }
    println(values.toList)
  }

  def main(args: Array[String]): Unit = {
    run(5)
  }
}
