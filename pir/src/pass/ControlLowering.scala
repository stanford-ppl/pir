package pir
package pass

import pir.node._

class ControlLowering(implicit compiler:PIR) extends ControlTransformer with SiblingFirstTraversal with UnitTraversal {
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
    val inMems = inMemsOf(context)// All read memories should be local to the context in the same GlobalContainer
    dbg(s"inMems:${inMems.keys.map(qtype)}")
    inMems.keys.map { mem => allocateWithFields[NotEmpty](mem) }.toList
  }

  def computeNotFulls(context:ComputeContext) = dbgblk(s"computeNotFulls") {
    val local = localOutMemsOf(context)
    dbg(s"outMems (local):${local.keys.map(qtype)}")
    var notFulls:List[Def] = local.map { case (mem, accesses) =>
      val notFull = allocateWithFields[NotFull](mem)
      dbg(s"localMem: $mem, notFull:$notFull")
      notFull
    }.toList
    val remote = remoteOutMemsOf(context)
    dbg(s"outMems (remote):${remote.keys.map(qtype)}")
    notFulls = notFulls ++ remote.values.flatten.flatMap {
      case Def(writer, EnabledStoreMem(mem, _, _, writeNext)) => 
        val notFull:Def = if (compiler.arch.designParam.topParam.busWithReady) {
          val gout = writeNext.collect[GlobalOutput](visitFunc=visitGlobalIn _).head
          allocateWithFields[DataReady](gout)
        } else {
          val writerCtx = contextOf(writer).get
          withParentCtrl(writerCtx, ctrlOf(writer)) {
            val notFull = allocateWithFields[NotFull](mem)
            insertGlobalIO(notFull, context)(allocateWithFields[High]())(allocateWithFields[High]())
          }
        }
        dbg(s"remoteMem: $mem, notFull:$notFull")
        Some(notFull)
    }.toSet
    notFulls
  }

  def lowerContextEnable(ctxEnOut:ContextEnableOut):ContextEnable = dbgblk(s"lowerContextEnable($ctxEnOut)") {
    lowerNode(ctxEnOut) {
      val context = contextOf(ctxEnOut).get
      withParentCtrl(context, ctrlOf(ctxEnOut)) {
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
}

