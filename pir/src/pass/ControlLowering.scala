package pir
package pass

import pir.node._

class ControlLowering(implicit compiler:PIR) extends ControlAnalysis with SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  val forward = true

  override def runPass =  {
    traverseNode(compiler.top)
  }

  override def visitNode(n:N):Unit = {
    val node = transform(n)
    visited += node
    super.visitNode(node)
  }

  def transform(n:N):N = dbgblk(s"transform($n)") {
    n match {
      case Def(n:ContextEnableOut, ContextEnableOut()) => lowerContextEnable(n)
      case n => n
    }
  }

  def computeNotEmpties(context:ComputeContext) = dbgblk(s"computeNotEmpties") {
    val readMems = context.collectIn[Memory]() // All read memories should be local to the context in the same GlobalContainer
    dbg(s"readMems:${readMems.map(qtype)}")
    readMems.map { mem => 
      allocateWithFields[NotEmpty](mem)(context)
    }.toList
  }

  def computeNotFulls(context:ComputeContext) = dbgblk(s"computeNotFulls") {
    var notFulls:List[Def] = context.collectDown[LocalStore]().map {
      case Def(writer, LocalStore(mem::Nil, _, _)) => 
        val notFull = allocateWithFields[NotFull](mem)(context)
        dbg(s"localMem: $mem, notFull:$notFull")
        notFull
    }
    val remoteStores = context.collectOutTillMem[LocalStore]()
    dbg(s"remoteStores=${quote(remoteStores)}")
    notFulls = notFulls ++ remoteStores.flatMap {
      case Def(writer, EnabledStoreMem(mem:ArgOut, _, _, writeNext)) => None
      case Def(writer, EnabledStoreMem(mem, _, _, writeNext)) => 
        val notFull:Def = if (compiler.arch.topParam.busWithReady) {
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
    val context = contextOf(ctxEnOut).get
    val ctxEn = allocate[ContextEnable](context) {
      var notEmpties = computeNotEmpties(context)
      if (notEmpties.isEmpty) dbgblk(s"No forward dependencies, duplicate all ancestor control's counter chains") {
        allocateControllerDone(context, compiler.top.topController)
        notEmpties = computeNotEmpties(context)
      }
      //if (notEmpties.isEmpty) // If still has no data dependencies, add a tokenIn from the top
      val notFulls = computeNotFulls(context)
      ContextEnable(notEmpties ++ notFulls)
    }
    swapNode(ctxEnOut, ctxEn)
  }
}

