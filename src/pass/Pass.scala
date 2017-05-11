package pir.pass
import pir.graph._
import pir.Design
import pir.Config
import pir.util._
import pir.util.misc._
import pir.plasticine.util.SpadeMetadata
import pir.plasticine.main._
import pir.util.typealias._
import pir.util.PIRMetadata

import scala.collection.mutable

abstract class Pass(implicit val design:Design) {
  implicit lazy val spade:Spade = design.arch
  lazy val spademeta: SpadeMetadata = design.arch
  lazy val pirmeta:PIRMetadata = design
  var runId = -1

  var isInit = false
  var hasRun = false
  def shouldRun:Boolean
  lazy val name = this.getClass.getSimpleName

  def reset {
    isInit = false
    hasRun = false
  }
  
  def run(id:Int):Unit = {
    runId = id
    run
  }

  final def run:Unit = {
    initPass
    isInit = true
    traverse
    finPass
    hasRun = true
  }

  def traverse:Unit

  def initPass:Unit = {
    startInfo(s"Begin $name ...")
  }

  def finPass:Unit = {
    endInfo(s"Finishing $name ...")
  }

  def quote(n:Any):String = n match {
    case n:Node => pir.util.quote(n) 
    case n:PNode => pir.plasticine.util.quote(n)
  }

  val passes = mutable.ListBuffer[(() => Boolean, () => Any)]()
  val passRanCount = mutable.Map[Int, (Int,Int)]()
  def addPass(canRun: => Boolean, runCount:Int)(pass: => Any) = {
    passRanCount += passes.size -> (runCount, 0)
    passes += ((canRun _, pass _))
  }
  def runPasses = {
    passes.zipWithIndex.foreach { case ((canRun, pass), i) =>
      val (totalRun, currRun) = passRanCount(i)
      if (canRun() && (totalRun > currRun)) {
        print(s"$name pass $i run for the ${currRun+1} times .. ")
        pass()
        passRanCount(i) = (totalRun, currRun + 1)
      }
    }
  }
}
