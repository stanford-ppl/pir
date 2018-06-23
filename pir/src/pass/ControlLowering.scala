package pir
package pass

import pir.node._

class ControlLowering(implicit compiler:PIR) extends ControlAnalysis with SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  val forward = true

  override def visitNode(n:N):Unit = {
    val node = transform(n)
    visited += node
    super.visitNode(node)
  }

  def transform(n:N):N = {
    n match {
      case Def(n:ContextEnableOut, ContextEnableOut()) => lowerContextEnable(n)
      case n => n
    }
  }

  def computeNotEmpties(context:ComputeContext) = dbgblk(s"computeNotEmpties") {
    val readMems = inMemsOf(context)// All read memories should be local to the context in the same GlobalContainer
    dbg(s"readMems:${readMems.map(qtype)}")
    readMems.map { mem => 
      allocateWithFields[NotEmpty](mem)(context)
    }.toList
  }

  def computeNotFulls(context:ComputeContext) = dbgblk(s"computeNotFulls") {
    var notFulls:List[Def] = localStoreAccessesOf(context).map {
      case Def(writer, LocalStore(mem::Nil, _, _)) => 
        val notFull = allocateWithFields[NotFull](mem)(context)
        dbg(s"localMem: $mem, notFull:$notFull")
        notFull
    }
    val remoteStores = remoteStoreAccessesOf(context)
    dbg(s"remoteStores=${quote(remoteStores)}")
    notFulls = notFulls ++ remoteStores.flatMap {
      case Def(writer, EnabledStoreMem(mem, _, _, writeNext)) => 
        val notFull:Def = if (compiler.arch.designParam.topParam.busWithReady) {
          val gout = writeNext.collect[GlobalOutput](visitFunc=visitGlobalIn _).head
          allocateWithFields[DataReady](gout)(context)
        } else {
          val writerCtx = contextOf(writer).get
          val notFull = allocateWithFields[NotFull](mem)(writerCtx)
          insertGlobalIO(notFull, context)(allocateWithFields[High]()(writerCtx))(allocateWithFields[High]()(context))
        }
        dbg(s"remoteMem: $mem, notFull:$notFull")
        Some(notFull)
    }.toSet
    notFulls
  }

  def lowerContextEnable(ctxEnOut:ContextEnableOut):ContextEnable = dbgblk(s"lowerContextEnable($ctxEnOut)") {
    lowerNode(ctxEnOut) {
      val context = contextOf(ctxEnOut).get
      var notEmpties = computeNotEmpties(context)
      // If still has no data dependencies, add a tokenIn from the top
      globalOf(ctxEnOut).get match {
        case _:ArgFringe =>
        case _:FringeStreamIn =>
        case _ if notEmpties.isEmpty => err(s"$context does not have forward dependencies")
        case _ =>
      }
      val notFulls = computeNotFulls(context)
      ContextEnable(notEmpties ++ notFulls)
    }
  }
}

