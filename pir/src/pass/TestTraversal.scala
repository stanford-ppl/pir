package pir.pass

import pir._
import pir.node._

import pirc._
import pirc.util._
import prism.traversal._

import scala.collection.mutable
import scala.reflect._

class TestTraversal(implicit design:PIR) extends PIRTraversal with BottomUpTopologicalTraversal with BFSTraversal with UnitTraversal {
//class TestTraversal(implicit design:PIR) extends Pass with DFSTopDownTopologicalTraversal with BFSTraversal with UnitTraversal {
  import pirmeta._

  override def shouldRun = true

  val forward = false

  override def runPass =  {
    tic
    traverseScope(design.newTop, ())
    //traverseNode(design.newTop, ())
    toc("TestPass", "ms")
    check
  }

  override def check = {
    val top = design.newTop
    val allNodes = top::top.descendents
    val unvisited = allNodes.filterNot(isVisited)
    assert(unvisited.isEmpty, s"not all nodes are visited! unvisited=${unvisited}")
  }

  override def visitNode(n:N) = {
    dbg(s"Visiting ${qdef(n)}")
    super.visitNode(n)
  }

}

