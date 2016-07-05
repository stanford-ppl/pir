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
  private val allNodes = Stack[ListBuffer[Node]]()

  def reset() {
    allNodes.foreach {i => i.clear()}
    allNodes.clear()
    nextSym = 0
  }

  def addNode(n: Node) { 
    allNodes.foreach {i => i+= n}
  }

  def addBlock(block: => Any):ListBuffer[Node] = {
    val list = ListBuffer[Node]()
    allNodes.push(list)
    block
    allNodes.pop()
  }

  def msg(x: String) = if (Config.dse) () else System.out.println(x)

  def processTop(top: Node): Node = {

    top
  }

  def main(args: String*): Any 
  def main(args: Array[String]): Unit = {
    msg(args.mkString(", "))
    main(args:_*)

    val cuList = allNodes.pop

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
