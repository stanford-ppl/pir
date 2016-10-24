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

  var startTime:Long = _
  var endTime:Long = _
  def tic = {
    startTime = System.nanoTime()
  }
  def toc(info:String, unit:String) = {
    endTime = System.nanoTime()
    val timeUnit = unit match {
      case "ns" => 1
      case "us" => 1000
      case "ms" => 1000000
      case "s" => 1000000000
      case _ => throw PIRException(s"Unknown time unit!")
    }
    val time = (endTime - startTime) / timeUnit
    println(s"$info elapsed time: $time $unit")
  }
}
