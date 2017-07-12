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

  def shouldRun:Boolean
  lazy val name = this.getClass.getSimpleName

  def reset {
    passRanCount.foreach { case (id, (totalRun, currRun)) =>
      passRanCount(id) = (totalRun, 0)
    }
  }
  
  def run(id:Int):Unit = {
    runId = id
    run
  }

  final def run:Unit = {
    initPass
    runPasses
    finPass
  }

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
  def addPass(pass: => Any):Unit = addPass(canRun=true, runCount=1) (pass)
  def addPass(canRun: => Boolean)(pass: => Any):Unit = addPass(canRun, runCount=1) (pass)
  def addPass(runCount:Int)(pass: => Any):Unit = addPass(true, runCount)(pass)
  def addPass(canRun: => Boolean, runCount:Int)(pass: => Any):Unit = {
    passRanCount += passes.size -> (runCount, 0)
    passes += ((canRun _, pass _))
  }
  def runPasses = {
    passes.zipWithIndex.foreach { case ((canRun, pass), id) =>
      val (totalRun, currRun) = passRanCount(id)
      if (canRun() && (totalRun > currRun)) {
        print(s"Pass $id run ${currRun+1} .. ")
        pass()
        passRanCount(id) = (totalRun, currRun + 1)
      }
    }
  }
  def checkRanAll = {
    if (shouldRun) {
      passRanCount.foreach { case (id, (totalRun, currRun)) =>
        if (totalRun != currRun)
          errmsg(s"$name pass $id should run $totalRun times but only ran $currRun times!")
      }
    }
  }
  def hasRun = {
    //passRanCount.forall { case (id, (totalRun, currRun)) => totalRun == currRun }
    passRanCount.forall { case (id, (totalRun, currRun)) => currRun > 0 }
  }
  def hasRun(id:Int) = {
    val (totalRun, currRun) = passRanCount(id)
    //totalRun == currRun
    currRun > 0
  }

}
