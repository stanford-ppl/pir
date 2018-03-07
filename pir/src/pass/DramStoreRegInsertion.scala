package pir.pass

import pir._
import pir.node._

import prism._
import prism.util._

import prism.traversal._

import scala.collection.mutable

class DramStoreRegInsertion(implicit compiler:PIR) extends PIRTransformer {
  import pirmeta._

  override def shouldRun = true

  override def runPass =  {
    val fringes = collectDown[FringeContainer](compiler.top)
    val dataStreams = fringes.flatMap { fringe => collectDown[StreamOut](fringe).filter { _.field == "data" } }
    dataStreams.foreach(insertReg)
  }

  def insertReg(dataStream:StreamOut) = {
    val dataCtrl = ctrlOf(dataStream.writers.head)
    val fringe = globalOf(dataStream).get
    val ackStream = collectDown[StreamIn](fringe).filter { _.field == "ack" }.head
    val reader = ReadMem(ackStream).ctrl(dataCtrl)
    val count = CountAck(reader).ctrl(dataCtrl)
    val mem = compiler.top.argFringe.tokenOut()
    val writer = WriteMem(mem, count).ctrl(dataCtrl)
  }

}

