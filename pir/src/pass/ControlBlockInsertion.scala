package pir
package pass

import pir.node._
import prism.graph._
import spade.param._

import scala.collection.mutable

class ControlBlockInsertion(implicit compiler:PIR) extends PIRTransformer with BufferAnalyzer with GlobalIOInsertion {
  
  override def runPass = {
    val globals = pirTop.collectChildren[GlobalContainer]
    globals.foreach { global => 
      val ctxs = global.collectChildren[Context]
      ctxs.foreach { insertControBlock(_) }
      insertGlobalIO(global)
    }
  }

  def insertControBlock(ctx:Context):Unit = dbgblk(s"insertControlBlock($ctx)"){
    // Remove existing control block
    ctx.collectChildren[ControlBlock].foreach { cb =>
      cb.children.foreach { c => swapParent(c, ctx) }
      removeNodes(List(cb))
    }

    val (consts, nodes) = ctx.children.partition { case c:Const => true; case _ => false }
    // Insert one control block per ctrl. Controller and buffer reads are outside control block
    val map = nodes.groupBy { 
      case n:LocalOutAccess =>
        n.done.T.flatMap { 
          case ctrler:Controller => Some(ctrler.getCtrl) // Branch independent pop
          case _ => None
        } getOrElse { n.getCtrl }
      case n => n.getCtrl
    }
    val ctrls = map.keys.toSeq.sortBy { _.ancestors.size }

    nodes.foreach {
      case ctrler:Controller => setLaneValid(ctrler)
      case _ =>
    }

    ctrls.foldLeft[PIRNode](ctx) { case (prev, ctrl) =>
      val (extern, intern) = map(ctrl).partition { 
        case ctrler:Controller => true
        case buffer:LocalOutAccess => true
        case const:Const => true
        case _ => false
      }
      extern.foreach { n => swapParent(n, prev) }
      val ctrlers = assertOneOrLess(extern.collect { case ctrler:Controller => ctrler }, s"ctrler for $ctrl in $ctx")
      val ctrler = ctrlers.getOrElse {
        // Make it easier for codegen. One to one correspondance between ctrl and controller
        within(prev, ctrl) { stage(UnitController().par(1)) }
      }
      // Create dummy dependency to make sure ctrler is generated before ControlBlock
      val cb = within(prev, ctrl) { stage(ControlBlock().dummy(ctrler.laneValid)) }
      intern.foreach { n => swapParent(n, cb) }
      cb
    }
  }

  def setLaneValid(ctrler:Controller) = {
    ctrler.en.neighbors.collect { case v:CounterValid => v }.foreach { v =>
      disconnect(ctrler.en, v)
      ctrler.uen(v.out)
    }
  }

}

