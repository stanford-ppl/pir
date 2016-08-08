package pir.graph.traversal
import pir.graph._
import pir.Design
import pir.Config
import pir.PIRMisc._

import scala.collection.mutable.Set

trait Traversal {
  var isInit = false
  val visited = Set[Node]()
  var isTraversed = false

  def reset {
    visited.clear()
    isInit = false
    isTraversed = false
  }
  
  def run = {
    initPass
    traverse
    finPass
  }

  def traverse:Unit

  def initPass = {
    isInit = true
  }

  def finPass:Unit = { isTraversed = true }
}
