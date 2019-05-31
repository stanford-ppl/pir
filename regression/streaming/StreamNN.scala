import spatial.dsl._
import spatial.lang.{FileBus,FileBusLastBit}
import spatial.lib.NN._

case class StreamNNParam(
  field:scala.Int = 8,
  numBatch:scala.Int = 16,
  batch:scala.Int = 4,
  L1:scala.Int = 64,
  L2:scala.Int = 1,
  P1:scala.Int = 1,
  P2:scala.Int = 1,
  bp:scala.Int = 1,
  ip:scala.Int = 8
) extends StreamTemplateParam

class StreamNN_0 extends StreamNN[scala.Int,Int]
class StreamNN_1 extends StreamNN[scala.Int,Int] { override lazy val param = StreamNNParam(bp=2) }
class StreamNN_2 extends StreamNN[scala.Int,Int] { override lazy val param = StreamNNParam(ip=4) }
class StreamNN_3 extends StreamNN[scala.Int,Int] { override lazy val param = StreamNNParam(ip=4, P1=2) }
class StreamNN_4 extends StreamNN[scala.Int,Int] { override lazy val param = StreamNNParam(ip=4, P1=2, bp=2) }
class StreamNN_5 extends StreamNN[scala.Int,Int] { override lazy val param = StreamNNParam(L1=8, ip=8, P1=1, bp=2) }

@spatial abstract class StreamNN[HT:Numeric,T:Num](implicit ev:Cast[Text,T]) extends StreamInference[HT,T,T] {

  lazy val param = StreamNNParam()
  import param._

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
      denselayer_tiled[T](w1, b1, ip, 1, P1, relu _, in=Left({i => insram(b, i)}), out=l1)
      denselayer_tiled[T](w2, b2, ip, P1, P2, { x => x } ,in=l1, out=Left({ case (o,d) => outsram(b) = d }))
    }
    outsram
  }

  def hostBody(inData:Seq[Seq[HT]]) = {
    inData.map { fields =>
      val l1 = host_denselayer[HT](fields, W1, B1, host_relu _)
      val l2 = host_denselayer[HT](l1, W2, B2, { x => x })
      l2.head
    }
  }

}
