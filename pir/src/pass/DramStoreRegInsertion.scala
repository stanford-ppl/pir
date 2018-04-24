package pir.pass

import pir.node._

class DramStoreRegInsertion(implicit compiler:PIR) extends PIRTransformer with ChildFirstTraversal with UnitTraversal {
  import pirmeta._

  override def runPass =  {
    val fringes = compiler.top.collectDown[DramFringe]()
    val dataStreams = fringes.flatMap { fringe => fringe.collectDown[StreamOut]().filter { _.field == "data" } }
    dataStreams.foreach(insertReg)
  }

  override def visitNode(n:N) = n match {
    case n:Top => super.visitNode(n)
    case FringeDenseLoad(dram, cmdStream, dataStream)  =>
    case FringeDenseLoad(dram, cmdStream, dataStream)  =>
    case n:GlobalContainer =>
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

