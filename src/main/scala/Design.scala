package dhdl

import graph._

//import analysis._

import codegen._
import codegen.dot._

import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Stack
import java.nio.file.{Paths, Files}
import scala.io.Source

import scala.collection.mutable.{Set,Map}

trait Design { self =>

  implicit val design: Design = self

  private var nextSym = 0
  def nextId = {nextSym += 1; nextSym }

  //private var _checkIds = true
  //def checkIds = _checkIds && !Config.quick

  //val nodeBounds = Map[CombNode, Double]()
  private val allNodes = Stack[(Node => Boolean, ListBuffer[Node])]()

  def reset() {
    allNodes.foreach { case (f,i) => i.clear() }
    allNodes.clear()
    nextSym = 0
  }

  def addNode(n: Node) { 
    allNodes.foreach { case (f,i) => if (f(n)) i+= n }
  }

  def addBlock(block: => Any, f1:Node => Boolean, filters: Node => Boolean *):List[List[Node]] = {
    allNodes.push((f1, ListBuffer[Node]()))
    filters.foreach { f => 
      allNodes.push( (f, ListBuffer[Node]()) )
    }
    block
    val lists = ListBuffer[List[Node]]()
    for (i <- 0 to filters.size ) {i:Int =>
      lists += allNodes.pop()._2.toList
    }
    lists.toList
  }

  def addBlock(block: => Any, filter: Node => Boolean):List[Node] = {
    allNodes.push((filter, ListBuffer[Node]()))
    block
    allNodes.pop()._2.toList
  }

  def msg(x: String) = if (Config.dse) () else System.out.println(x)

  def main(args: String*): Any 
  def main(args: Array[String]): Unit = {

    msg(args.mkString(", "))
    def mainBlock = main(args:_*)
    val cuList = addBlock(mainBlock, (n:Node) => true)

    cuList.foreach {i => println(i)}
    //if (Config.genDot) {
    //  val origGraph = new GraphvizCodegen(s"orig")
    //  origGraph.run(top)
    //}

    //val transformedTop = processTop(top)

    //if (Config.genMaxJ) {
    //  if (Config.genDot) {
    //    msg("Generating dot graph")
    //    val dot = new GraphvizCodegen()
    //    dot.run(transformedTop)
    //  }
    //}
    
  }

}
