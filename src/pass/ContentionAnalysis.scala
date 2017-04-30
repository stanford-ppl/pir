package pir.pass
import pir.graph._
import pir._
import pir.util.misc._
import pir.exceptions.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

class ContentionAnalysis(implicit design: Design) extends Pass  {
  import pirmeta._

  def shouldRun = true

  val isolatedContention = Map[Controller,List[Int]]()

  def outerContention(x: Controller, P: => Int): Int = {
    x match {
      case x:InnerController => 0
      case x:Controller =>
        val ics = x.children.map { c => calcContention(c) * P }
        isolatedContention(x) = ics
        x match {
          case top:Top => assert(ics.size==1); ics.head
          case (_:MetaPipeline | _:StreamController) =>  ics.sum
          case sq:Sequential => ics.max
        }
    }
  }

  def calcContention(x: Controller): Int = x match {
    //case Deff(_:ParallelPipe)     => childrenOf(x).map(calcContention).sum
    //case Deff(e:OpForeach)        => outerContention(x, parsOf(e.cchain).reduce{_*_})
    //case Deff(e:OpReduce[_,_])    => outerContention(x, parsOf(e.cchain).reduce{_*_})
    //case Deff(e:OpMemReduce[_,_]) => outerContention(x, parsOf(e.ccOuter).reduce{_*_})
    case x:Top => outerContention(x, 1)
    case x:OuterController => outerContention(x, 1) //??
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

  def markContention(x: Controller, parent: Int): Unit = x match {
    //case Deff(_:ParallelPipe)     => childrenOf(x).foreach{child => markContention(child,parent)}
    case x:Top => markPipe(x, parent)
    case x:OuterController => markPipe(x, parent)
    case x:MemoryController => contentionOf(x) = parent
    case x:InnerController => contentionOf(x) = 0 
  }

  override def traverse:Unit = {
    val c = calcContention(design.top)
    markContention(design.top, c)
  } 

}
