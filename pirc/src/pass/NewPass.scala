package prism.pass

import pirc.util._

import scala.collection.mutable

trait Pass {

  var runId = -1

  def shouldRun:Boolean
  lazy val name = this.getClass.getSimpleName

  var runCount:Int = 0
  def hasRun = runCount > 0
  val dependencies = mutable.ListBuffer[Pass]()
  def unfinishedDependencies = dependencies.filter { !_.hasRun }
  def isDependencyFree = unfinishedDependencies.isEmpty

  def reset:Unit = {
    runCount = 0
  }
  
  def run(id:Int):Unit = {
    runId = id
    run
  }

  final def run:Unit = {
    if (!isDependencyFree) 
      err(s"Cannot run pass $name due to dependencies=${unfinishedDependencies.mkString(",")} haven't run")
    startInfo(s"Begin $name ...")
    initPass
    runPass
    finPass
    runCount += 1
    endInfo(s"Finishing $name ...")
  }

  def initPass:Unit ={}

  def runPass:Unit

  def finPass:Unit ={}

}
