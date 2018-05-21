package pir
package pass

import pir.node._

class FringeElaboration(implicit compiler:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal with ConstantPropogator {
  import pirmeta._

  override def runPass =  {
    traverseNode(compiler.top)
  }

  override def visitNode(n:N) = dbgblk(s"visitNode($n)") { 
    n match {
      case n:Top =>
        n.argFringe // create lazy value
        super.visitNode(n)
      case n@(_:ArgIn | _:DramAddress) =>
        val argFringe = design.top.argFringe
        val argInCtrl = argFringe.argInController
        val argInDef = ArgInDef().setParent(argFringe).ctrl(argInCtrl)
        val writeMem = WriteMem(n.asInstanceOf[Memory], argInDef).setParent(argFringe).ctrl(argInCtrl)
      case n@(_:ArgOut | _:TokenOut) =>
        val argFringe = design.top.argFringe
        val argOutCtrl = argFringe.argOutController
        n.setParent(argFringe)
        val readMem = ReadMem(n.asInstanceOf[Memory]).setParent(argFringe).ctrl(argOutCtrl)
        argFringe.hostRead.connect(readMem.out)
      case n@FringeDenseLoad(dram,cmdStream,dataStream) =>
        transformDense(n, cmdStream, dataStream)
      case n@FringeSparseLoad(dram,addrStream,dataStream) =>
        transformSparse(n, addrStream, dataStream)
      case n@FringeDenseStore(dram,cmdStream,dataStream,ackStream) =>
        transformDense(n, cmdStream ++ dataStream, ackStream)
        insertCountAck(n, dataStream.head, ackStream.head)
      case n@FringeSparseStore(dram,cmdStream,ackStream) =>
        transformSparse(n, cmdStream, ackStream)
        insertCountAck(n, cmdStream.head, ackStream.head)
      case n:StreamIn => transformStreamIn(n)
      case n:StreamOut => transformStreamOut(n)
      case _ => super.visitNode(n)
    }
  }

  def transformDense(fringe:DramFringe, streamOuts:List[StreamOut], streamIns:List[StreamIn]) = {
    val outerCtrl = ctrlOf(fringe)
    val size = fringe.collectDown[StreamOut]().filter { _.field == "size" }.head
    val csize = getBoundOf(size, logger=Some(this)).asInstanceOf[Option[Int]]
    val par = fringe match {
      case FringeDenseLoad(dram,cmdStream,dataStream) =>
        getParOf(readersOf(dataStream.head).head)
      case FringeDenseStore(dram,cmdStream,dataStream,ackStream) =>
        getParOf(writersOf(dataStream.head).head)
    }
    val innerCtrl = DramController(csize, par).setParent(outerCtrl)
    val loads = streamOuts.map { mem =>
      mem.field match {
        case "size" | "offset" => ReadMem(mem).setParent(fringe).ctrl(outerCtrl)
        case "data" => ReadMem(mem).setParent(fringe).ctrl(innerCtrl)
      }
    }
    val pdc = ProcessDramCommand(loads).setParent(fringe).ctrl(innerCtrl)
    streamIns.foreach { mem =>
      WriteMem(mem, pdc).setParent(fringe).ctrl(innerCtrl)
    }
  }

  def transformSparse(fringe:DramFringe, streamOuts:List[StreamOut], streamIns:List[StreamIn]) = {
    val outerCtrl = ctrlOf(fringe)
    val loads = streamOuts.map { mem =>
      ReadMem(mem).setParent(fringe).ctrl(outerCtrl)
    }
    val pdc = ProcessDramCommand(loads).setParent(fringe).ctrl(outerCtrl)
    streamIns.foreach { mem =>
      WriteMem(mem, pdc).setParent(fringe).ctrl(outerCtrl)
    }
  }

  def insertCountAck(fringe:DramFringe, dataStream:StreamOut, ackStream:StreamIn) = {
    val dataCtrl = ctrlOf(writersOf(dataStream).head)
    val cu = CUContainer().setParent(compiler.top)
    val reader = ReadMem(ackStream).setParent(cu).ctrl(dataCtrl)
    val count = CountAck(reader).setParent(cu).ctrl(dataCtrl)
    val argFringe = design.top.argFringe
    val mem = TokenOut().setParent(argFringe)
    val writer = WriteMem(mem, count).setParent(cu).ctrl(dataCtrl)
  }

  def transformStreamIn(streamIn:StreamIn) = {
    val outerCtrl = compiler.top.topController
    val innerCtrl = UnitController(style=StreamPipe,level=InnerControl).setParent(outerCtrl)
    val streamInDef = StreamInDef().ctrl(innerCtrl)
    countsOf.get(streamIn).foreach { counts => countsOf(streamInDef) = counts }
    val fringe = FringeStreamIn(streamIn, streamInDef)
    WriteMem(streamIn, streamInDef).setParent(fringe).ctrl(innerCtrl)
  }

  def transformStreamOut(streamOut:StreamOut) = {
    val outerCtrl = compiler.top.topController
    val innerCtrl = UnitController(style=StreamPipe,level=InnerControl).setParent(outerCtrl)
    val load = ReadMem(streamOut).ctrl(innerCtrl)
    val processStreamOut = ProcessStreamOut(load).ctrl(innerCtrl)
    val fringe = FringeStreamOut(streamOut, processStreamOut)
    load.setParent(fringe)
  }

}
