package pir.pass

import pir.node._

class FringeElaboration(implicit compiler:PIR) extends PIRTransformer with SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  override def runPass =  {
    traverseNode(compiler.top)
  }

  override def visitNode(n:N) = n match {
    case n:Top => super.visitNode(n)
    case n@(_:ArgIn | _:DramAddress) =>
      val argFringe = design.top.argFringe
      val argInCtrl = argFringe.argInController
      val argInDef = ArgInDef().setParent(argFringe).ctrl(argInCtrl)
      val writeMem = WriteMem(n.asInstanceOf[Memory], argInDef).setParent(argFringe).ctrl(argInCtrl)
    case n@(_:ArgOut | _:TokenOut) =>
      val argFringe = design.top.argFringe
      n.setParent(argFringe)
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
    case _ => super.visitNode(n)
  }

  def transformDense(fringe:DramFringe, streamOuts:List[StreamOut], streamIns:List[StreamIn]) = {
    val outerCtrl = ctrlOf(fringe)
    val par = fringe match {
      case FringeDenseLoad(dram,cmdStream,dataStream) =>
        parOf(readersOf(dataStream.head).head).get
      case FringeDenseStore(dram,cmdStream,dataStream,ackStream) =>
        parOf(writersOf(dataStream.head).head).get
    }
    val innerCtrl = DramController(par).setParent(outerCtrl)
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
    val reader = ReadMem(ackStream).setParent(fringe).ctrl(dataCtrl)
    val count = CountAck(reader).setParent(fringe).ctrl(dataCtrl)
    val mem = TokenOut()
    val writer = WriteMem(mem, count).setParent(fringe).ctrl(dataCtrl)
  }

}

