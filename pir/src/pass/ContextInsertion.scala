package pir.pass

import pir._
import pir.node._

import prism._

import scala.collection.mutable
import prism.util._

class ContextInsertion(implicit compiler:PIR) extends PIRTransformer with DFSBottomUpTopologicalTraversal {
  import pirmeta._

  type T = Map[PIRNode, ComputeContext]

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    // Mark context
    val contextMap = traverseNode(compiler.top, Map[PIRNode, ComputeContext]())
    // Transform nodes into their contexts
    insertContext(contextMap)
  }

  /* Insert Context between parent CU and nodes with context */
  def insertContext(contexMap:T) = {
    contexMap.foreach { 
      case (n:Counter, context) => // swap parent of CounterChain 
      case (n, context) =>
        val cu = globalOf(n).get 
        val contextCU = globalOf(context)
        dbgblk(s"n=$n context=$context contextCU=$contextCU") {
          contextCU.map { contextCU =>
            if (contextCU==cu) {
              if (n.parent.get == cu) swapParent(n, context)
            } else {
              dbg(s"this shouldn't happen n=${qdef(n)}")
              dbg(s"node cu:${qtype(cu)} contextCU:${qtype(contextCU)}")
            }
          }.getOrElse {
            context.setParent(cu)
            swapParent(n, context)
          }
        }
    }
  }

  /* Mark context for each ComputeNode and inner accumulator */
  override def visitNode(n:N, prev:T):T = {
    var contextMap = prev
    val context = dbgblk(s"visitNode ${qdef(n)}") {
      n match {
        case n:Memory if !isInnerAccum(n) => None
        case n@(_:ComputeNode | _:Memory) =>
          // Group of nodes that should shair context with current nodes
          val peers = n.localDepeds ++ n.children
          dbg(s"$n.localDepeds=${n.localDepeds} n.siblings=${n.siblings} peers=${peers}")
          val sharedContexts = peers.flatMap { deped => contextMap.get(deped) }.toSet.toList
          dbg(s"sharedContexts=$sharedContexts")
          val context = if (sharedContexts.size == 0) {
            val newContext = ComputeContext()
            dbg(s"creating new context $newContext")
            newContext
          } else if (sharedContexts.size == 1) {
            sharedContexts.head
          } else {
            dbg(s"merge contexts ${sharedContexts}")
            val merged::rest = sharedContexts
            contextMap.foreach {
              case (n, ctx) if rest.contains(ctx)=> 
                contextMap = contextMap + (n -> merged)
                dbg(s"swap ${qtype(n)}'s context from ${ctx} to ${merged}")
              case (n, ctx) =>
            }
            merged
          }
          Some(context)
        case n => None
      }
    }
    context.foreach { context => contextMap += n -> context }
    super.visitNode(n, contextMap)
  }

  override def check = {
  }

}
