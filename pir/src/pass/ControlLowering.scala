package pir.pass

import pir._
import pir.node._

import prism._
import prism.traversal._

import scala.collection.mutable
import prism.util._

class ControlLowering(implicit compiler:PIR) extends ControlAnalysis with SiblingFirstTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

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

  def lowerContextEnable(ctxEnOut:ContextEnableOut):ContextEnable = dbgblk(s"lowerContextEnable($ctxEnOut)") {
    val context = contextOf(ctxEnOut).get
    val ctxEn = allocateContextEnable(context)
    swapNode(ctxEnOut, ctxEn)
  }

  def computeNotEmpties(context:ComputeContext) = dbgblk(s"computeNotEmpties") {
    val readMems = collectIn[Memory](context) // All read memories should be local to the context in the same GlobalContainer
    dbg(s"readMems:${readMems.map(qtype)}")
    readMems.map { mem => 
      allocateWithFields[NotEmpty](mem)(context)
    }.toList
  }

  def computeNotFulls(context:ComputeContext) = dbgblk(s"computeNotFulls") {
    var notFulls:List[Def] = collectDown[LocalStore](context).map {
      case Def(writer, LocalStore(mem::Nil, _, _)) => 
        val notFull = allocateWithFields[NotFull](mem)(context)
        dbg(s"localMem: $mem, notFull:$notFull")
        notFull
    }
    notFulls ++= collectDown[GlobalOutput](context).flatMap { gout =>
      collectOut[LocalStore](gout, visitFunc=(n:N) => n match { case n:Memory => Nil; case n => super.visitGlobalOut(n)}).flatMap {
        case Def(writer, LocalStore((mem:ArgOut)::Nil, _, _)) => None
        case Def(writer, LocalStore(mem::Nil, _, _)) => 
          val notFull:Def = if (busWithReady) {
            allocateWithFields[DataReady](gout)(context)
          } else {
            val writerCtx = contextOf(writer).get
            val notFull = allocateWithFields[NotFull](mem)(writerCtx)
            insertGlobalIO(notFull, context)(allocateWithFields[High]()(writerCtx))(allocateWithFields[High]()(context))
          }
          dbg(s"removeMem: $mem, notFull:$notFull")
          Some(notFull)
      }
    }
    notFulls
  }

  def allocateContextEnable(context:ComputeContext):ContextEnable = dbgblk(s"allocateContextEnable($context)") {
    allocate[ContextEnable](context) {
      var notEmpties = computeNotEmpties(context)
      if (notEmpties.isEmpty) dbgblk(s"No forward dependencies, duplicate all ancestor control's counter chains") {
        allocateControllerDone(context, compiler.top.topController)
        notEmpties = computeNotEmpties(context)
      }
      //if (notEmpties.isEmpty) // If still has no data dependencies, add a tokenIn from the top
      val notFulls = computeNotFulls(context)
      ContextEnable(notEmpties ++ notFulls)
    }
  }
}

