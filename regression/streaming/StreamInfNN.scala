import spatial.dsl._
import spatial.lib.ML._
import scala.reflect._

class StreamInfNN_0 extends StreamInfNN[scala.Int,Int]()()
class StreamInfNN_1 extends StreamInfNN[scala.Int,Int]()(opb=2)
class StreamInfNN_2 extends StreamInfNN[scala.Int,Int]()(ipf=4, ip1=4)
class StreamInfNN_3 extends StreamInfNN[scala.Int,Int]()(ipf=4, ip1=4, op1=2)
class StreamInfNN_4 extends StreamInfNN[scala.Int,Int]()(ipf=4, ip1=4, op1=2, opb=2)
class StreamInfNN_5 extends StreamInfNN[scala.Int,Int](L1=8)(ipf=8, ip1=8, op1=1, opb=2)
class StreamInfNN_6 extends StreamInfNN[scala.Float,Float]()()
class StreamInfNN_7 extends StreamInfNN[scala.Float,Float]()()

// 2 layer MLP [field x L1] [L1 x 1]
@spatial abstract class StreamInfNN[HT:Numeric:ClassTag,T:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
  val L1:scala.Int = 32,
  val L2:scala.Int = 32,
  val L3:scala.Int = 1,
)(
  val op1:scala.Int = 1,
  val op2:scala.Int = 1,
  val op3:scala.Int = 1,
  val opb:scala.Int = 1,
  val ipf:scala.Int = math.min(field, 16),
  val ip1:scala.Int = math.min(L1,16),
  val ip2:scala.Int = math.min(L2,16),
  val ipb:scala.Int = math.min(16,batch),
)(implicit ev:Cast[Text,T]) extends StreamInference[HT,T,T] {

  val W1 = Seq.tabulate(field, L1) { (i,j) => (i*L1 +j) }
  val W2 = Seq.tabulate(L1, L2) { (i,j) => (i*L2 +j) }
  val W3 = Seq.tabulate(L1, L3) { (i,j) => (i*L3 +j) }
  val B1 = Seq.tabulate(L1) { i => i }
  val B2 = Seq.tabulate(L2) { i => i }
  val B3 = Seq.tabulate(L3) { i => i }

  def accelBody(insram:SRAM2[T]) = { // insram [batch, field]
    // Layer 1
    val w1 = LUT.fromSeq[T](W1.map { _.map { _.to[T]} })
    val b1 = LUT.fromSeq[T](B1.map { _.to[T]})
    val w2 = LUT.fromSeq[T](W2.map { _.map { _.to[T]} })
    val b2 = LUT.fromSeq[T](B2.map { _.to[T]})
    val w3 = LUT.fromSeq[T](W3.map { _.map { _.to[T]} })
    val b3 = LUT.fromSeq[T](B3.map { _.to[T]})
    val outsram = SRAM[T](batch)
    Foreach(0 until batch par opb) { b =>
      val l1 = SRAM[T](L1)
      val l2 = SRAM[T](L2)
      denselayer_tiled[T](w1, b1, ipf, 1, op1, relu[T] _, out=l1){ i => insram(b,i) }
      denselayer_tiled[T](w2, b2, ip1, op1, op2, relu[T] _, in=l1, out=l2)
      denselayer_tiled[T](w3, b3, ip2, op2, op3, activation[T](x => x), in=l2){ case (o,d) => outsram(b) = d }
    }
    outsram
  }

  def hostBody(inData:Seq[Seq[HT]]) = {
    inData.map { fields =>
      val l1 = unstaged_denselayer[HT](fields, W1, B1, unstaged_relu _)
      val l2 = unstaged_denselayer[HT](l1, W2, B2, unstaged_relu _)
      val l3 = unstaged_denselayer[HT](l2, W3, B3, { x => x })
      l3.head
    }
  }

}
