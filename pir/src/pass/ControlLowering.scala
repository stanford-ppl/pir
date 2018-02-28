package pir.pass

import pir._
import pir.node._

import pirc._
import prism.traversal._

import scala.collection.mutable
import scala.reflect._
import pirc.util._

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

  def allocateContextEnable(context:ComputeContext):ContextEnable = dbgblk(s"allocateContextEnable($context)") {
    allocate[ContextEnable](context) {
      val readMems = collectIn[Memory](context) // All read memories should be local to the context in the same GlobalContainer
      val writtenRemoteMems = collectOut[LocalStore](context, visitFunc=(n:N) => n match { case n:Memory => Nil; case n => super.visitGlobalOut(n)}).map {
        case Def(n, LocalStore(mem::Nil, _, _)) => (n, mem)
      }
      val writtenLocalMems = collectDown[LocalStore](context).map {
        case Def(n, LocalStore(mem::Nil, _, _)) => (n, mem)
      }
      dbg(s"readMems:${readMems.map(qtype)}")
      dbg(s"writtenRemoteMems:${writtenRemoteMems.map{ case (w, m) => (qtype(w), qtype(m)) }}")
      dbg(s"writtenLocalMems:${writtenLocalMems.map{ case (w, m) => (qtype(w), qtype(m)) }}")
      val notEmpties = readMems.map { mem => 
        allocate[NotEmpty](context, _.mem == mem)(NotEmpty(mem))
      }.toList
      val parentEnable = if (notEmpties.isEmpty) {
        val parentCtrl = ctrlOf(context).parent.get
        val parentNodes = ctrlOf.bmap(parentCtrl)
        val parentCUs = parentNodes.collect { case context:GlobalContainer => context }
        assert(parentCUs.size == 1)
        val parentCU = parentCUs.head
        val parentContext = collectDown[ComputeContext](parentCU).filter { ctx => ctrlOf(ctx) == parentCtrl }.headOption.getOrElse {
          assert(!parentCtrl.isInstanceOf[LoopController], s"Loop controller should have context, ctrl=${parentCtrl}")
          ComputeContext().setParent(parentCU)
        }
        val parentEnable = allocateContextEnable(parentContext)
        List(insertGlobalIO(parentEnable, context) { parentEnable })
      } else Nil
      dbg(s"parentEnable:${parentEnable}")
      val notFulls = (writtenRemoteMems ++ writtenLocalMems).flatMap { 
        case (writer, mem:ArgOut) => None
        case (writer, mem) =>
          val writerCtx = contextOf(writer).get
          val notFull = allocate[NotFull](writerCtx, _.mem == mem)(NotFull(mem))
          Some(insertGlobalIO(notFull, context)(allocate[High](writerCtx)(High())))
      }
      dbg(s"notFull:${notFulls}")
      ContextEnable(notEmpties ++ parentEnable ++ notFulls)
    }
  }

}

