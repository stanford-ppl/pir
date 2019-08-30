import spatial.dsl._
import spatial.lib.ML._
import scala.reflect._

class StreamInfNN4_0 extends StreamInfNN[scala.Int,Int]()()
class StreamInfNN4_1 extends StreamInfNN4[scala.Float,Float](field=16,numBatch=1024,batch=16,L1=16,L2=16,L3=16)(op1=16,op2=16,op3=16,mp2=1,mp3=1,opb=1,mp1=1)

// 2 layer MLP [field x L1] [L1 x 1]
@spatial abstract class StreamInfNN4[HT:Numeric:ClassTag,T:Num](
  val field:scala.Int = 8,
  val numBatch:scala.Int = 16,
  val batch:scala.Int = 4,
  val L1:scala.Int = 32,
  val L2:scala.Int = 32,
  val L3:scala.Int = 32
)(
  val op1:scala.Int = 1,
  val op2:scala.Int = 1,
  val op3:scala.Int = 1,
  val mp1:scala.Int = 1, // L1/ip1
  val mp2:scala.Int = 1, // L2/ip2
  val mp3:scala.Int = 1, // L2/ip2
  val opb:scala.Int = 1,
  val ipf:scala.Int = math.min(field, 16),
  val ip1:scala.Int = math.min(L1,16),
  val ip2:scala.Int = math.min(L2,16),
  val ip3:scala.Int = math.min(L3,16),
  val ipb:scala.Int = math.min(16,batch),
)(implicit ev:Cast[Text,T]) extends StreamInference[scala.Float,T,T] {

  val mpf = 1
  val L4 = 1
  val op4 = 1
  val W1:Seq[Seq[scala.Float]] = Seq.tabulate(field, L1) { (i,j) => (i*L1 +j) }
  val W2:Seq[Seq[scala.Float]] = Seq.tabulate(L1, L2) { (i,j) => (i*L2 +j) }
  val W3:Seq[Seq[scala.Float]] = Seq.tabulate(L2, L3) { (i,j) => (i*L3 +j) }
  val W4:Seq[Seq[scala.Float]] = Seq.tabulate(L3, L4) { (i,j) => (i*L4 +j) }
  val B1:Seq[scala.Float] = Seq.tabulate(L1) { i => i }
  val B2:Seq[scala.Float] = Seq.tabulate(L2) { i => i }
  val B3:Seq[scala.Float] = Seq.tabulate(L3) { i => i }
  val B4:Seq[scala.Float] = Seq.tabulate(L4) { i => i }

  def accelBody(insram:SRAM2[T]) = { // insram [batch, field]
    // Layer 1
    val w1 = LUT.fromSeq[T](W1.map { _.map { _.to[T]} })
    val b1 = LUT.fromSeq[T](B1.map { _.to[T]})
    val w2 = LUT.fromSeq[T](W2.map { _.map { _.to[T]} })
    val b2 = LUT.fromSeq[T](B2.map { _.to[T]})
    val w3 = LUT.fromSeq[T](W3.map { _.map { _.to[T]} })
    val b3 = LUT.fromSeq[T](B3.map { _.to[T]})
    val w4 = LUT.fromSeq[T](W4.map { _.map { _.to[T]} })
    val b4 = LUT.fromSeq[T](B4.map { _.to[T]})
    val outsram = SRAM[T](batch)
    Foreach(0 until batch par opb) { b =>
      val h1 = SRAM[T](L1)
      val h2 = SRAM[T](L2)
      val h3 = SRAM[T](L3)
      denselayer[T](w1, b1, relu[T] _, in=insram(b,_), nlout=h1.update)(ipf, mpf, op1)
      denselayer[T](w2, b2, relu[T] _, in=h1(_), nlout=h2.update)(ip1, mp1, op2)
      denselayer[T](w3, b3, relu[T] _, in=h2(_), nlout=h3.update)(ip2, mp2, op3)
      denselayer[T](w4, b4, identity[T], in=h3(_), nlout={ (i,d) => outsram.update(b, d) })(ip3, mp3, op4)
    }
    outsram
  }

  def hostBody(inData:Seq[Seq[scala.Float]]) = {
    inData.map { fields =>
      val (h1,l1) = unstaged_denselayer[scala.Float](fields, W1, B1, unstaged_relu _)
      val (h2,l2) = unstaged_denselayer[scala.Float](h1, W2, B2, unstaged_relu _)
      val (h3,l3) = unstaged_denselayer[scala.Float](h2, W3, B3, unstaged_relu _)
      val (h4,l4) = unstaged_denselayer[scala.Float](h3, W4, B4, unstaged_identity _)
      h4.head
    }
  }

}
