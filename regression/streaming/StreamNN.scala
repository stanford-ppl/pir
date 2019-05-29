import spatial.dsl._
import spatial.lang.{FileBus,FileBusLastBit}

case class StreamNNParam(
  field:scala.Int = 8,
  numBatch:scala.Int = 16,
  batch:scala.Int = 4,
  L1:scala.Int = 64,
  L2:scala.Int = 1,
  bp:scala.Int = 1,
  ip:scala.Int = 8
) extends StreamTemplateParam

//class StreamNN_0 extends StreamNN
//class StreamNN_1 extends StreamNN { override lazy val param = StreamNNParam(op=2) }
//class StreamNN_2 extends StreamNN { override lazy val param = StreamNNParam(ip=4) }
//class StreamNN_3 extends StreamNN { override lazy val param = StreamNNParam(ip=4, op=2) }
//class StreamNN_4 extends StreamNN { override lazy val param = StreamNNParam(ip=8, op=4) }

@spatial trait StreamNN extends StreamTemplate {

  lazy val param = StreamNNParam()
  import param._

  val W1 = Seq.tabulate(field, L1) { (i,j) => (i*L1 +j).to[T] }
  val W2 = Seq.tabulate(L1, L2) { (i,j) => (i*L2 +j).to[T] }
  val B1 = Seq.tabulate(L1) { i => i.to[T] }
  val B2 = Seq.tabulate(L2) { i => i.to[T] }

  def accelBody(insram:SRAM2[T]) = { // insram [batch, field]
    val L1 = 64
    val L2 = 1
    // Layer 1
    val w1 = LUT.fromSeq[T](W1)
    val b1 = LUT.fromSeq[T](B1)
    val w2 = LUT.fromSeq[T](W2)
    val b2 = LUT.fromSeq[T](B2)
    val outsram = SRAM[T](batch)
    Foreach(0 until batch par bp) { b =>
      val l1 = nnlayer(w1, b1, field, L1, 1, 1, read=Left({i => insram(b, i)})).get
      nnlayer(w2, b2, L1, L2, 1, 1,read=Right(l1), write=Some({ case (o,d) => outsram(b) = d }))
    }
    outsram
  }

  //def hostBody(inDataMat:Tensor3[T]):Matrix[T] = {
    //val wArray = Array.fromSeq(weights)
    //Matrix.tabulate(numBatch, batch) { (i, b) =>
      //Array.tabulate(field) { f => inDataMat(i, f, b) * wArray(f) }.reduce { _ + _ }
    //}
  //}

  /*                                                       
   *                     o
   *     i          +----------+             o
   *  +-----+   x  i|    w     |   =    +----------+
   *  +-----+       |          |        +----------+
   *                +----------+
   *
   *
   * */
  def nnlayer(
    w:LUT2[T], 
    b:LUT1[T], 
    I:scala.Int, 
    O:scala.Int, 
    outPar:scala.Int, 
    inPar:scala.Int,
    read:Either[I32 => T, SRAM1[T]],
    write:Option[(Idx, T) => Unit] = None
  ):Option[SRAM1[T]] = {
    val outsram = write.fold[Option[SRAM1[T]]] { Some(SRAM[T](O)) } { _ => None }
    Foreach(O par outPar) { o =>
      val dot = Reg[T]
      Reduce(dot)(I par inPar by ip) { io =>
        val dotInner = Reg[T]
        Reduce(dotInner)(ip par ip) { ii =>
          val i = io + ii
          val input = read match {
            case Left(read) => read(i)
            case Right(insram) => insram(i)
          }
          input * w(i, o)
        } { _ + _ }
        dotInner.value
      } { _ + _ }
      val d = dot.value + b(o)
      write.fold { outsram.get(o) = d } { _(o, d) }
    }
    outsram
  }

}
