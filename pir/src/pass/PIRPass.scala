package pir.pass

import pir._
import pir.node._

import spade._
import pirc._
import pirc.enums._
import pirc.util._
import pirc.{Def => _, _}

import prism.traversal._

import scala.collection.mutable
import scala.language.existentials
import scala.math.max
import scala.reflect._

abstract class PIRPass(implicit val design:PIR) extends Pass with PIRCollector {

  lazy val pirmeta = design.newTop.metadata

  def qdef(n:Any) = n match {
    case n:IR => s"${n.name.getOrElse(n.toString)} = ${n.productName}"
    case n => n.toString
  }

  def qtype(n:Any) = n match {
    case n:IR => n.name.map { name => s"${n.className}${n.id}[$name]" }.getOrElse(s"$n")
    case n => n.toString
  }

}

class CounterChainFilling(implicit design:PIR) extends PIRTransformer with ChildFirstTraversal with UnitTraversal {

  import pirmeta._

  override def shouldRun = true

  override def runPass =  {
    traverseNode(design.newTop)
  }

  //override def visitNode(n:N):Unit = n match {
    //case n:GlobalContainer => emitBlock(s"Visiting ${qdef(n)}") {
      //val chains = collectDown[CounterChain](n)
      //dbgs(s"chains: ${chains.mkString(qtype)}")
      //chains.map
    //}
    //case _ => super.visitNode(n)
  //}

}

//trait CUPartition {

  //type N
  //type R

  //trait CostMetric {
    //def evaluate(n:N, r:R):Either[Success, Failure]
  //}

  //object TypeCost extends CostMetric {
    //def evaluate(n:N, r:R):Either[Success, Failure] = {
    //}
  //}
  //object FanInCost extends CostMetric {
  //}

  //val metrics = mutable.ListBuffer[CostMetric]()

//}
