import spatial.dsl._

class Kmeans_0 extends Kmeans

class Kmeans_1 extends Kmeans(ip=1)
class Kmeans_2 extends Kmeans(mp1=2)
class Kmeans_3 extends Kmeans(mp2=2)
class Kmeans_4 extends Kmeans(mp3=2)
class Kmeans_5 extends Kmeans(mp1=2,mp2=2)
class Kmeans_6 extends Kmeans(mp2=2,mp3=2)
class Kmeans_7 extends Kmeans(mp1=2,mp3=2)
class Kmeans_8 extends Kmeans(mp1=2,mp2=2,mp3=2)
class Kmeans_9 extends Kmeans(mp1=3,mp2=3,mp3=3)

@spatial abstract class Kmeans(
  K:scala.Int = 64,
  D:scala.Int = 64,
  ts:scala.Int = 128,
  ip:scala.Int = 16,
  op:scala.Int = 1,
  mp1:scala.Int = 1,
  mp2:scala.Int = 1,
  mp3:scala.Int = 1
) extends SpatialTest { self =>
  type X = Int

  override def runtimeArgs = "2 256"

  def DM1 = D - 1
  val element_max = 10
  val margin = (element_max * 0.2).to[X]

  def kmeans[T:Num](points_in: Array[T], I:Int, N:Int) = {

    val iters = ArgIn[Int]
    val numPoints     = ArgIn[Int]

    setArg(iters, I)
    setArg(numPoints, N)

    val points = DRAM[T](numPoints, D)    // Input points
    val centroids = DRAM[T](K, D) // Output centroids
    setMem(points, points_in)

    Accel {
      val cts = SRAM[T](K, D)

      // Load initial centroids (from points)
      cts load points(0::K, 0::D par ip)

      Sequential.Foreach(iters by 1){ epoch => // iter = 2
        // For each set of points
        val newCents = SRAM[T](K,D)
        if (ip == 1) {
          newCents.onlyblockcyclic.blockcyclic_Bs(Seq(63))
          ()
        }
        MemReduce(newCents par ip)(numPoints by ts par op){i => // iter = 9
          val pts = SRAM[T](ts, D)
          pts load points(i::i+ts, 0::D par ip)

          // For each point in this set
          MemReduce(SRAM[T](K,D) par ip)(ts par mp1){pt => // iter = 1024
            // Find the index of the closest centroid
            val dists = SRAM[T](K)
            Foreach(K by 1 par mp2) { ct =>
              val dist = Reduce(Reg[T])(D par ip){d => 
                mux(d==DM1, 0, (pts(pt,d) - cts(ct,d)) ** 2)
              }{_+_}
              dists(ct) = dist.value
            }
            val minDist = Reduce(Reg[T])(K by 1 par ip) { ct => dists(ct) } { (a,b) => min(a, b) }
            val minCent = Reduce(Reg[I32])(K by 1 par ip) { ct => 
              mux(dists(ct) == minDist.value, ct, -1)
            } { (a,b) => max(a,b) }

            // Store this point to the set of accumulators
            val localCent = SRAM[T](K,D)
            Foreach(K by 1, D par ip){(ct,d) =>
              localCent(ct, d) = mux(ct == minCent.value, pts(pt,d), 0.to[T])
            }
            localCent
          }{_+_} // Add the current point to the accumulators for this centroid
        }{_+_}

        // Average each new centroid
        Foreach(K by 1 par mp3){ ct =>
          Foreach(D-1 par ip){ d =>
            val centCount = newCents(ct,DM1)
            val cond = centCount == 0.to[T]
            cts(ct, d) = mux(cond, 0.to[T], newCents(ct,d) / mux(cond,1.to[T], centCount)) // prevent div by 0
          }
        }
      }

      // Store the centroids out
      centroids(0::K, D par ip) store cts
    }

    getMem(centroids)
  }


  def main(args: Array[String]): Unit = {

    val I = args(0).to[Int]
    val N = args(1).to[Int]

    //val r = scala.util.Random
    //val pts = scala.Seq.tabulate(N,D) { case (i, d) =>
      //if (d == DM1) 1 else r.nextInt(element_max) + i
    //}
    //val cts = scala.Array.tabulate(K,K) { case (i,d) => pts(i)(d) }
    //(0 until I).foreach { epoch =>
      //val accum = scala.Array.fill(K)(scala.Array.fill(D)(0))
      //pts.foreach { pt =>
        //val dists = cts.map { ct => dist(ct, pt) }
        //val idx = dists.view.zipWithIndex.min._2
        //(0 until K).foreach { k =>
          //accum(k) += pt(k)
        //}
      //}
      //(0 until K).foreach { k =>
        //val n = accum(DM1)
        //(0 until D).foreach { d =>
          //cent(d) = if (n == 0) 0 else accum(d)/n
        //}
      //}
    //}
    //writeCSVNow2D(cts, buildPath(IR.config.genDir, "tungsten", "cts.csv"))

    val pts = Array.tabulate(N){i => Array.tabulate(D){d => if (d == DM1) 1.to[X] else random[X](element_max) + i }}

    val result = kmeans(pts.flatten, I, N)

    // Initialize centroids
    val cts = Array.tabulate(K) { i => Array.empty[X](D) }
    for (k <- 0 until K) {
      for (d <- 0 until D) {
        val cent = cts(k)
        val point = pts(k)
        cent(d) = point(d)
      }
    }

    def dist[T:Num](p1: Array[T], p2: Array[T]) = {
      Array.tabulate(D) { i =>
        if (i==DM1) 0.to[T] else (p1(i) - p2(i)) ** 2
      }.reduce(_+_)
    }

    val ii = Array.tabulate(K){i => i}
    for(epoch <- 0 until I) {
      // Zero accumulator
      val accum = Array.tabulate(K) { i => Array.empty[X](D) }
      for (k <- 0 until K) {
        for (d <- 0 until D) {
          val acc = accum(k)
          acc(d) = 0.to[X]
        }
      }
      pts.foreach { pt =>
        val dists = cts.map{ct => dist(ct, pt) }
        val idx = dists.zip(ii){(a,b) => pack(a,b) }.reduce{(a,b) => if (a._1 <= b._1) a else b}._2 
        for (k <- 0 until K) {
          val acc = accum(idx)
          acc(k) = acc(k) + pt(k)
        }
      }

      // Average
      for (k <- 0 until K) {
        val acc = accum(k)
        val n = acc(DM1)
        for (d <- 0 until D) {
          val cent = cts(k)
          cent(d) = if (n == 0) 0.to[X] else acc(d)/n
        }
      }
    }

    val gold = cts.flatten

    println("\n\nOriginal Points:")
    (0 until N).foreach{ i => printArray(pts(i))}
    println("\n\nCorrect Centers:")
    (0 until K).foreach{ i => printArray(cts(i))}
    println("\n\nResult Centers:")
    (0 until K).foreach { i => 
      val resrow = Array.tabulate(D){j => result(i*D + j)}
      printArray(resrow)
    }

    val cksum = approxEql(gold, result)

    println("PASS: " + cksum + " (Kmeans)")
    assert(cksum)
  }
}
