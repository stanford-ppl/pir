package pir.pass

import pir.node._

class DramStoreRegInsertion(implicit compiler:PIR) extends PIRTransformer {
  import pirmeta._

  override def shouldRun = true

  override def runPass =  {
    val fringes = compiler.top.collectDown[FringeContainer]()
    val dataStreams = fringes.flatMap { fringe => fringe.collectDown[StreamOut]().filter { _.field == "data" } }
    dataStreams.foreach(insertReg)
  }

  def insertReg(dataStream:StreamOut) = {
    val dataCtrl = ctrlOf(writersOf(dataStream).head)
    val fringe = globalOf(dataStream).get
    val ackStream = fringe.collectDown[StreamIn]().filter { _.field == "ack" }.head
    val reader = ReadMem(ackStream).ctrl(dataCtrl)
    val count = CountAck(reader).ctrl(dataCtrl)
    val mem = compiler.top.argFringe.tokenOut()
    val writer = WriteMem(mem, count).ctrl(dataCtrl)
  }

}

