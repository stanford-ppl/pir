package pir.pass
import pir.graph._
import pir._
import pir.util.misc._
import pir.exceptions.PIRException
import pir.codegen.Logger

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

class ContentionAnalysis(implicit design: Design) extends Pass with Logger {
  import pirmeta._

  def shouldRun = true
  override lazy val stream = newStream(s"ContentionAnalysis.log")

  val isolatedContention = Map[Controller,List[Int]]()

  def outerContention(x: Controller, par: => Int): Int = {
    x match {
      case x:InnerController => 0
      case x:Controller =>
        val ics = x.children.map { c => calcContention(c) * par }
        isolatedContention(x) = ics
        x match {
          case top:Top => assert(ics.size==1); ics.head
          case (_:MetaPipeline | _:StreamController) =>  ics.sum
          case sq:Sequential => ics.max
        }
    }
  }

  def calcContention(x: Controller): Int = x match {
    case x:Top => outerContention(x, 1)
    case x:OuterController => outerContention(x, 1) 
    case x:MemoryController => 1
    case x:InnerController  => 0 
  }

  def markPipe(x: Controller, parent: Int) {
    x match {
      case (_:MetaPipeline | _:StreamController) =>
        x.children.foreach { child => markContention(child,parent) }
      case _:Top => assert(x.children.size==1); markContention(x.children.head, parent)
      case _:Sequential =>
        val ics = isolatedContention(x)
        val mx = ics.max
        // Can just skip case where mx = 0 - no offchip memory accesses in this sequential anyway
        if (mx > 0) x.children.zip(ics).foreach{case (child,c) => markContention(child, (parent/mx)*c) }
    }
  }

  def markContention(x: Controller, parent: Int): Unit = {
    x match {
      case x:Top => markPipe(x, parent)
      case x:OuterController => markPipe(x, parent)
      case x:MemoryController => contentionOf(x) = parent
        dprintln(s"contentionOf($x) = ${contentionOf(x)}")
      case x:InnerController => contentionOf(x) = 0 
        dprintln(s"contentionOf($x) = ${contentionOf(x)}")
    }
  }

  addPass {
    val c = calcContention(design.top)
    markContention(design.top, c)
  } 

}
