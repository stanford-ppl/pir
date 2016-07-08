package dhdl

import graph._

//import analysis._

import codegen._
import codegen.dot._

import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack
import java.nio.file.{Paths, Files}
import scala.io.Source

import scala.collection.mutable.{Set,Map}

trait Design { self =>

  implicit val design: Design = self

  private var nextSym = 0
  def nextId = {nextSym += 1; nextSym }

  private val nodeStack = Stack[(Node => Boolean, ListBuffer[Node])]()
  private val nameMap = HashMap[String, Node]()
  private val toUpdate = HashMap[String, Node => Unit]()

  def reset() {
    nodeStack.foreach { case (f,i) => i.clear() }
    nodeStack.clear()
    nameMap.clear()
    toUpdate.clear()
    nextSym = 0
  }

  def addNode(n: Node) { 
    nodeStack.foreach { case (f,i) => if (f(n)) i+= n }
    //println(s"addNode:${title}")
  }

  def addBlock(block: => Any, f1:Node => Boolean, filters: Node => Boolean *):List[List[Node]] = {
    nodeStack.push((f1, ListBuffer[Node]()))
    filters.foreach { f => 
      nodeStack.push( (f, ListBuffer[Node]()) )
    }
    block
    (0 to filters.size).foldLeft(List[List[Node]]()) { case (a, i) =>
      nodeStack.pop()._2.toList :: a 
    }
  }

  def addBlock(block: => Any, filter: Node => Boolean):List[Node] = {
    nodeStack.push((filter, ListBuffer[Node]()))
    block
    nodeStack.pop()._2.toList
  }

  def addName(n:Node):Unit = if (n.name.isDefined) {
    n match {
      case c:Controller => 
        val s = n.name.get  
        assert(!nameMap.contains(s), s"Already create controller with name ${s}: ${n.title}")
        nameMap += (s -> c)
      case p:Primitive =>
        assert(p.ctrler!=null, "")
        val s = s"${p.ctrler.title}_${n.name.get}"
        assert(!nameMap.contains(s),
          s"Already create primitive with name ${s} for controller ${p.ctrler}")
        nameMap += (s -> p)
      case w:Wire =>
        //assert(false, "No support for adding name for wire yet!")
    }
  }

  def getByName(s:String):Node = {
    assert(nameMap.contains(s), s"No node defined with name:${s}")
    nameMap(s)
  }

  def updateLater(s:String, f:Node => Unit) = toUpdate += (s -> f)

  def msg(x: String) = if (Config.dse) () else println(x)

  def main(args: String*): Any 
  def main(args: Array[String]): Unit = {

    msg(args.mkString(", "))
    val top:Top = Top(main(args:_*))

    println("-------- Finishing graph construction ----------")
    addName(top)
    top.nodes.foreach(n => addName(n))
    toUpdate.foreach { case (k,f) =>
      val n:Node = getByName(k)
      f(n)
    }
    toUpdate.clear()
    println("-------- Finishing updating nodes ----------")
     
    //nodes.foreach {i => println(i)}
    //cuList.foreach {i => println(i)}
    //mcList.foreach {i => println(i)}
    top.ctrlList.foreach {i => println(i)}
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
