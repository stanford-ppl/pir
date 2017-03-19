package pir.graph.traversal
import pir.graph._
import pir.Design
import pir.Config
import pir.mapper.PIRException
import pir.misc._

import scala.collection.mutable.Set

trait Traversal {
  var isInit = false
  val visited = Set[Node]()
  var isTraversed = false
  lazy val name = this.getClass.getSimpleName

  def reset {
    visited.clear()
    isInit = false
    isTraversed = false
  }
  
  def run = {
    initPass
    isInit = true
    traverse
    finPass
    isTraversed = true
  }

  def traverse:Unit

  def initPass:Unit = {
    startInfo(s"Begin $name ...")
  }

  def finPass:Unit = {
    endInfo(s"Finishing $name ...")
  }
}
