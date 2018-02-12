package pir.pass

import pir._
import pir.node._

import pirc.enums._
import pirc.util._

import prism.traversal._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._
import prism.codegen.Logging

trait PIRTraversal extends PIRPass with PIRWorld with prism.traversal.Traversal

trait TopologicalTraversal extends PIRTraversal with prism.traversal.TopologicalTraversal {
  override def selectFrontier = {
    var frontier = super.selectFrontier
    frontier = frontier.collect { case store:LocalStore => store }
    if (frontier.isEmpty) frontier = super.selectFrontier
    frontier
  }
}

trait DFSTopDownTopologicalTraversal extends TopologicalTraversal with prism.traversal.DFSTopDownTopologicalTraversal
trait BFSTopDownTopDownTopologicalTraversal extends TopologicalTraversal with prism.traversal.BFSTopDownTopDownTopologicalTraversal
trait BottomUpTopologicalTraversal extends TopologicalTraversal with prism.traversal.BottomUpTopologicalTraversal

