package pirc.pass

import pirc._
import pirc.util._

import scala.collection.mutable

trait Pass extends prism.pass.Pass {

  def shouldRun:Boolean

  val passes = mutable.ListBuffer[(() => Boolean, () => Any)]()
  val passRanCount = mutable.Map[Int, (Int,Int)]()

  override def reset = {
    super.reset
    passRanCount.foreach { case (id, (totalRun, currRun)) =>
      passRanCount(id) = (totalRun, 0)
    }
  }
  
  def addPass(pass: => Any):Unit = addPass(canRun=true, runCount=1) (pass)
  def addPass(canRun: => Boolean)(pass: => Any):Unit = addPass(canRun, runCount=1) (pass)
  def addPass(runCount:Int)(pass: => Any):Unit = addPass(true, runCount)(pass)
  def addPass(canRun: => Boolean, runCount:Int)(pass: => Any):Unit = {
    passRanCount += passes.size -> (runCount, 0)
    passes += ((canRun _, pass _))
  }

  def runPass = runAll

  def runAll = {
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

  def run(implicit design:Design) = {
    newRun(0).run
  }

}
