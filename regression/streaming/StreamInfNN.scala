import spatial.dsl._
import spatial.lib.ML._

class StreamInfNN_0 extends StreamInfNN[scala.Int,Int]
class StreamInfNN_1 extends StreamInfNN[scala.Int,Int](bp=2)
class StreamInfNN_2 extends StreamInfNN[scala.Int,Int](ip=4)
class StreamInfNN_3 extends StreamInfNN[scala.Int,Int](ip=4, P1=2)
class StreamInfNN_4 extends StreamInfNN[scala.Int,Int](ip=4, P1=2, bp=2)
class StreamInfNN_5 extends StreamInfNN[scala.Int,Int](L1=8, ip=8, P1=1, bp=2)
class StreamInfNN_6 extends StreamInfNN[scala.Float,Float]

@spatial abstract class StreamInfNN[HT:Numeric,T:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
  val L1:scala.Int = 64,
  val L2:scala.Int = 1,
  val P1:scala.Int = 1,
  val P2:scala.Int = 1,
  val bp:scala.Int = 1,
  val ip:scala.Int = 8
)(implicit ev:Cast[Text,T]) extends StreamInference[HT,T,T] {

  val W1 = Seq.tabulate(field, L1) { (i,j) => (i*L1 +j) }
  val W2 = Seq.tabulate(L1, L2) { (i,j) => (i*L2 +j) }
  val B1 = Seq.tabulate(L1) { i => i }
  val B2 = Seq.tabulate(L2) { i => i }

  def accelBody(insram:SRAM2[T]) = { // insram [batch, field]
    // Layer 1
    val w1 = LUT.fromSeq[T](W1.map { _.map { _.to[T]} })
    val b1 = LUT.fromSeq[T](B1.map { _.to[T]})
    val w2 = LUT.fromSeq[T](W2.map { _.map { _.to[T]} })
    val b2 = LUT.fromSeq[T](B2.map { _.to[T]})
    val outsram = SRAM[T](batch)
    Foreach(0 until batch par bp) { b =>
      val l1 = SRAM[T](L1)
      denselayer_tiled[T](w1, b1, ip, 1, P1, relu[T] _, out=l1){ i => insram(b,i) }
      denselayer_tiled[T](w2, b2, ip, P1, P2, activation[T](x => x), in=l1){ case (o,d) => outsram(b) = d }
    }
    outsram
  }

  def hostBody(inData:Seq[Seq[HT]]) = {
    inData.map { fields =>
      val l1 = unstaged_denselayer[HT](fields, W1, B1, unstaged_relu _)
      val l2 = unstaged_denselayer[HT](l1, W2, B2, { x => x })
      l2.head
    }
  }

}