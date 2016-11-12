package pir.graph.traversal
import pir.graph._
import pir.Design
import pir.Config
import pir.misc._
import pir.graph.mapper.PIRException

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
    isTraversed = true
  }

  def traverse:Unit

  def initPass = {
    isInit = true
  }

  def finPass:Unit
}
