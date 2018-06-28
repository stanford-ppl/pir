package pir
package pass

import pir.node._

class FringeElaboration(implicit compiler:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal with ConstantPropogator {
  import pirmeta._

  override def visitNode(n:N) = dbgblk(s"visitNode($n)") { 
    n match {
      case n:Top =>
        n.argFringe // HACK: create lazy value
        super.visitNode(n)
      case n:ArgIn => transformInMem(n)
      case n:DramAddress => transformInMem(n)
      case n:LUT => transformInMem(n)
      case n:ArgOut => transformOutMem(n)
      case n:TokenOut => transformOutMem(n)
      case n@FringeDenseLoad(dram,cmdStream,dataStream) =>
        dram.foreach { dram => transformDram(dram) }
        transformDense(n, cmdStream, dataStream)
      case n@FringeSparseLoad(dram,addrStream,dataStream) =>
        dram.foreach { dram => transformDram(dram) }
        transformSparse(n, addrStream, dataStream)
      case n@FringeDenseStore(dram,cmdStream,dataStream,ackStream) =>
        dram.foreach { dram => transformDram(dram) }
        transformDense(n, cmdStream ++ dataStream, ackStream)
        insertCountAck(n, dataStream.head, ackStream.head)
      case n@FringeSparseStore(dram,cmdStream,ackStream) =>
        dram.foreach { dram => transformDram(dram) }
        transformSparse(n, cmdStream, ackStream)
        insertCountAck(n, cmdStream.head, ackStream.head)
      case n:StreamIn => transformStreamIn(n)
      case n:StreamOut => transformStreamOut(n)
      case _ => super.visitNode(n)
    }
  }

  def transformDram(dram:DRAM) = {
    val dimsBounds = dram.dims.map { 
      case Const(c) => Some(c)
      case Def(n, LocalLoad(mem::Nil, None)) if boundOf.contains(mem) =>
        Some(boundOf(mem))
      case d => None
    }
    if (dimsBounds.forall(_.nonEmpty)) {
      staticDimsOf(dram) = dimsBounds.map(_.get.asInstanceOf[Int])
    }
  }

  def transformInMem(n:Memory) = {
    val argFringe = designP.top.argFringe
    val argInCtrl = argFringe.argInController
    n match {
      case n:ArgIn =>
        val argInDef = ArgInDef().setParent(argFringe).ctrl(argInCtrl)
        WriteMem(n, argInDef).setParent(argFringe).ctrl(argInCtrl)
        boundOf.get(n).foreach { b => boundOf(argInDef) = b }
      case n:DramAddress => 
        val argInDef = ArgInDef().setParent(argFringe).ctrl(argInCtrl)
        WriteMem(n, argInDef).setParent(argFringe).ctrl(argInCtrl)
        boundOf.get(n).foreach { b => boundOf(argInDef) = b }
      case n:LUT =>
        ResetMem(n, argFringe.tokenInDef).setParent(argFringe).ctrl(argInCtrl)
    }
  }

  def transformOutMem(n:Memory) = {
    val argFringe = designP.top.argFringe
    val argOutCtrl = argFringe.argOutController
    n.setParent(argFringe)
    val readMem = ReadMem(n).setParent(argFringe).ctrl(argOutCtrl)
    argFringe.hostRead.connect(readMem.out)
  }

  def transformDense(fringe:DramFringe, streamOuts:List[StreamOut], streamIns:List[StreamIn]) = {
    val outerCtrl = ctrlOf.map(fringe)
    val size = fringe.collectDown[StreamOut]().filter { _.field == "size" }.head
    val csize = getBoundOf(size, logger=Some(this)).asInstanceOf[Option[Int]]
    val par = fringe match {
      case FringeDenseLoad(dram,cmdStream,dataStream) =>
        getParOf(ctrlOf(readersOf(dataStream.head).head))
      case FringeDenseStore(dram,cmdStream,dataStream,ackStream) =>
        getParOf(ctrlOf(writersOf(dataStream.head).head))
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
    val argFringe = designP.top.argFringe
    val mem = TokenOut().setParent(argFringe)
    val writer = WriteMem(mem, count).setParent(cu).ctrl(dataCtrl)
  }

  def transformStreamIn(streamIn:StreamIn) = {
    val outerCtrl = compiler.top.topController
    val innerCtrl = UnitController(style=StreamPipe,level=InnerControl).setParent(outerCtrl)
    val streamInDef = StreamInDef().ctrl(innerCtrl)
    val counts = countsOf.get(streamIn).getOrElse(None)
    countsOf(innerCtrl) = counts
    countsOf(streamInDef) = counts
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
