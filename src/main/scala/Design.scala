package pir

import pir.misc._
import graph._
import graph.traversal._
import graph.mapper._
import pir.codegen._
import plasticine.config._
import pir.plasticine.main._

//import analysis._

import scala.util.{Try, Success, Failure}

import scala.language.implicitConversions
import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack
import scala.collection.mutable.{Set,Map}
import java.nio.file.{Paths, Files}
import scala.io.Source

trait Design extends Metadata {

  implicit def design: Design = this

  private var nextSym = 0
  def nextId = {nextSym += 1; nextSym }

  //TODO use collect to implement this
  private val nodeStack = Stack[(Node => Boolean, ListBuffer[Node])]()
  val toUpdate = ListBuffer[(String, Node => Unit)]()
  val allNodes = ListBuffer[Node]()

  def reset() {
    nodeStack.foreach { case (f,i) => i.clear() }
    nodeStack.clear()
    allNodes.clear()
    nodeStack.push(((n:Node) => true), allNodes)
    toUpdate.clear()
    nextSym = 0
    top = null
    traversals.foreach(_.reset)
  }

  def addNode(n: Node) = { 
    nodeStack.foreach { case (f,i) => if (f(n)) i += n }
  }

  def removeNode(n:Node):Unit = {
    nodeStack.foreach { case (f,i) => if (f(n)) i -= n }
    n match {
      case n:OuterController => 
        design.top.removeCtrler(n)
        n.cchains.foreach { cc => design.removeNode(cc) }
      case n:CounterChain =>
        n.counters.foreach { ctr => 
          if (ctr.cchain==n) design.removeNode(ctr)
        }
      case n:Counter =>
        design.removeNode(n.min)
        design.removeNode(n.max)
        design.removeNode(n.step)
      case n:InPort =>
        val from = n.from
        n.disconnect
        if (!from.isConnected)
          design.removeNode(from)
      case n:OutPort =>
        n.disconnect
        design.removeNode(n.src)
      case _ =>
    }
  }
  def removeNodes(ns:List[Node]) = { ns.foreach { n => removeNode(n) } }

  //def addBlock(block: => Any, f1:Node => Boolean, filters: Node => Boolean *):List[List[Node]] = {
  //  nodeStack.push((f1, ListBuffer[Node]()))
  //  filters.foreach { f => 
  //    nodeStack.push( (f, ListBuffer[Node]()) )
  //  }
  //  block
  //  (0 to filters.size).foldLeft(List[List[Node]]()) { case (a, i) =>
  //    nodeStack.pop()._2.toList :: a 
  //  }
  //}

  def addBlock[T](block: => Any, filter: Node => Boolean):List[T] = {
    nodeStack.push((filter, ListBuffer[Node]()))
    block
    nodeStack.pop()._2.toList.asInstanceOf[List[T]]
  }

  def addBlock[T1, T2](block: => Any,
                       f1: Node => Boolean,
                       f2: Node => Boolean
                       ):(List[T1], List[T2]) = {
    nodeStack.push((f1, ListBuffer[Node]()))
    nodeStack.push((f2, ListBuffer[Node]()))
    block
    val l2 = nodeStack.pop()._2.toList.asInstanceOf[List[T2]]
    val l1 = nodeStack.pop()._2.toList.asInstanceOf[List[T1]]
    (l1, l2)
  }

  def addBlock[T1, T2, T3](block: => Any,
                       f1: Node => Boolean, 
                       f2: Node => Boolean,
                       f3: Node => Boolean
                       ):(List[T1], List[T2], List[T3]) = {
    nodeStack.push((f1, ListBuffer[Node]()))
    nodeStack.push((f2, ListBuffer[Node]()))
    nodeStack.push((f3, ListBuffer[Node]()))
    block
    val l3 = nodeStack.pop()._2.toList.asInstanceOf[List[T3]]
    val l2 = nodeStack.pop()._2.toList.asInstanceOf[List[T2]]
    val l1 = nodeStack.pop()._2.toList.asInstanceOf[List[T1]]
    (l1, l2, l3)
  }

  def addBlock[T1, T2, T3, T4](block: => Any,
                       f1: Node => Boolean, 
                       f2: Node => Boolean,
                       f3: Node => Boolean,
                       f4: Node => Boolean
                       ):(List[T1], List[T2], List[T3], List[T4]) = {
    nodeStack.push((f1, ListBuffer[Node]()))
    nodeStack.push((f2, ListBuffer[Node]()))
    nodeStack.push((f3, ListBuffer[Node]()))
    nodeStack.push((f4, ListBuffer[Node]()))
    block
    val l4 = nodeStack.pop()._2.toList.asInstanceOf[List[T4]]
    val l3 = nodeStack.pop()._2.toList.asInstanceOf[List[T3]]
    val l2 = nodeStack.pop()._2.toList.asInstanceOf[List[T2]]
    val l1 = nodeStack.pop()._2.toList.asInstanceOf[List[T1]]
    (l1, l2, l3, l4)
  }
  def addBlock[T1, T2, T3, T4, T5](block: => Any,
                       f1: Node => Boolean, 
                       f2: Node => Boolean,
                       f3: Node => Boolean,
                       f4: Node => Boolean,
                       f5: Node => Boolean
                       ):(List[T1], List[T2], List[T3], List[T4], List[T5]) = {
    nodeStack.push((f1, ListBuffer[Node]()))
    nodeStack.push((f2, ListBuffer[Node]()))
    nodeStack.push((f3, ListBuffer[Node]()))
    nodeStack.push((f4, ListBuffer[Node]()))
    nodeStack.push((f5, ListBuffer[Node]()))
    block
    val l5 = nodeStack.pop()._2.toList.asInstanceOf[List[T5]]
    val l4 = nodeStack.pop()._2.toList.asInstanceOf[List[T4]]
    val l3 = nodeStack.pop()._2.toList.asInstanceOf[List[T3]]
    val l2 = nodeStack.pop()._2.toList.asInstanceOf[List[T2]]
    val l1 = nodeStack.pop()._2.toList.asInstanceOf[List[T1]]
    (l1, l2, l3, l4, l5)
  }
  def addBlock[T1, T2, T3, T4, T5, T6](block: => Any,
                       f1: Node => Boolean, 
                       f2: Node => Boolean,
                       f3: Node => Boolean,
                       f4: Node => Boolean,
                       f5: Node => Boolean,
                       f6: Node => Boolean
                       ):(List[T1], List[T2], List[T3], List[T4], List[T5], List[T6]) = {
    nodeStack.push((f1, ListBuffer[Node]()))
    nodeStack.push((f2, ListBuffer[Node]()))
    nodeStack.push((f3, ListBuffer[Node]()))
    nodeStack.push((f4, ListBuffer[Node]()))
    nodeStack.push((f5, ListBuffer[Node]()))
    nodeStack.push((f6, ListBuffer[Node]()))
    block
    val l6 = nodeStack.pop()._2.toList.asInstanceOf[List[T6]]
    val l5 = nodeStack.pop()._2.toList.asInstanceOf[List[T5]]
    val l4 = nodeStack.pop()._2.toList.asInstanceOf[List[T4]]
    val l3 = nodeStack.pop()._2.toList.asInstanceOf[List[T3]]
    val l2 = nodeStack.pop()._2.toList.asInstanceOf[List[T2]]
    val l1 = nodeStack.pop()._2.toList.asInstanceOf[List[T1]]
    (l1, l2, l3, l4, l5, l6)
  }


  def updateLater(s:String, f:Node => Unit) = { val u = (s,f); toUpdate += u }

  val arch:Spade
  var top:Top = _

  lazy val scalMemInsertion = new ScalarMemInsertion()
  lazy val multiBufferAnalysis = new MultiBufferAnalysis()
  lazy val fusionTransform = new FusionTransform()
  lazy val ctrlDotPrinter = new CtrlDotGen()
  lazy val pirPrinter = new PIRPrinter()
  lazy val pirDataDotGen = new PIRDataDotGen()
  lazy val ctrlAlloc = new CtrlAlloc()
  lazy val pirCtrlDotGen = new PIRCtrlDotGen()
  lazy val pirMapping = new PIRMapping()
  lazy val argDotPrinter = new ArgDotPrinter()
  lazy val ctrDotPrinter = new CtrDotPrinter()
  lazy val spadeDotGen = new SpadeDotGen(new CUVectorDotPrinter(), new CUScalarDotPrinter(), new CUCtrlDotPrinter(), pirMapping)
  lazy val ctrlPrinter = new CtrlPrinter()

  def mapping = pirMapping.mapping

  val mappers = ListBuffer[Mapper]()
  val mapperLogger = new Logger {
    override val stream = newStream(Config.mapperLog)
  }

  /* Traversals */
  lazy val traversals = {
    val traversals = ListBuffer[Traversal]()
    if (Config.debug) traversals += new SpadePrinter()
    traversals += new ForwardRef()
    //traversals += scalMemInsertion
    if (Config.debug) traversals += new PIRPrinter("PIR_orig.txt") 
    //traversals += fusionTransform 
    //traversals += new ScalarBundling()
    traversals += multiBufferAnalysis 
    if (Config.debug) traversals += pirDataDotGen
    traversals += new LiveAnalysis()
    //traversals += new IRCheck()
    if (Config.ctrl) traversals += ctrlAlloc 
    //if (Config.debug && Config.ctrl) traversals += ctrlDotPrinter 
    if (Config.debug && Config.ctrl) traversals += pirCtrlDotGen
    if (Config.debug && Config.ctrl) traversals += ctrlPrinter 
    if (Config.debug) traversals += pirPrinter 
    if (Config.mapping) traversals += pirMapping 
    if (Config.debug) traversals += spadeDotGen 
    traversals += new SpadeCodegen()
    //if (Config.mapping && Config.genPisa) traversals += new PisaCodegen(pirMapping)
    traversals
  }

  def run = {
    try {
      traversals.foreach(_.run)
      if (pirMapping.fail) throw PIRException(s"Mapping Failed")
    } catch {
      case e:PIRException => 
        try {
          if (!pirPrinter.isTraversed) pirPrinter.run
          //if (!ctrlDotPrinter.isTraversed) ctrlDotPrinter.run
          if (!spadeDotGen.isTraversed) spadeDotGen.run
          if (!ctrlPrinter.isTraversed) ctrlPrinter.run
          throw e
        } catch {
          case ne:Throwable => throw e
        }
      case e:Throwable => throw e
    }
    if (Config.debug) DebugLogger.close
  }

  // Metadata Maps
  val indexMap:indexOf.M = Map.empty
  val vecMap:vecOf.M = Map.empty
}

trait PIRApp extends Design{
  override val arch:Spade = SN_8x8
  override def toString = this.getClass().getSimpleName().replace("$","")

  def main(args: String*)(top:Top): Any 
  def main(args: Array[String]): Unit = {
    println(args.mkString(", "))
    reset()
    top = Top().updateBlock(main(args:_*)) 
    endInfo(s"Finishing graph construction for ${this}")
    run
  }
}

trait Metadata extends IndexOf with VecOf {}

trait MetadataMap {
  type V
  type M = Map[Node, V]
  def map(implicit design:Design):M
  def update(n:Node, v:V)(implicit design:Design):Unit = map += (n -> v)
  def apply(n:Node)(implicit design:Design):V = { val m = map; m(n) }
  def get(n:Node)(implicit design:Design):Option[V] =  { val m = map; m.get(n) }
}

trait IndexOf {
  /* Index of a spade node. Used for pisa codegen */
  object indexOf extends MetadataMap {
    type V = Int
    def map(implicit design:Design) = design.indexMap
  }
}
trait VecOf {
  /* Index of a spade node. Used for pisa codegen */
  object vecOf extends MetadataMap {
    type V = VectorIO[_]
    def map(implicit design:Design) = design.vecMap
  }
}
