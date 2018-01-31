package prism.pass

import pirc._
import pirc.util._
import prism.codegen.Logging

import scala.collection.mutable

case class RunPass(pass:Pass, id:Int) {
  def name = s"$pass-$id"
  var hasRun = false
  def reset = { hasRun = false }
  val dependencies = mutable.ListBuffer[RunPass]()
  def addDependency(deps:Pass*) = {
    deps.foreach { dep =>
      dependencies += dep.runPasses.last
    }
    this
  }
  def unfinishedDependencies = dependencies.filter { !_.hasRun }
  def isDependencyFree = unfinishedDependencies.isEmpty

  def run(implicit design:Design):Unit = {
    if (!pass.shouldRun) return
    if (!isDependencyFree) 
      err(s"Cannot run pass $name due to dependencies=${unfinishedDependencies.map(_.name).mkString(",")} haven't run")
    pass.logger.withOpen(s"$name.log") {
      dependencies.foreach(_.pass.check)
      startInfo(s"Begin $name ...")
      pass.initPass
      pass.runPass
      pass.finPass
      hasRun = true
      endInfo(s"Finishing $name ...")
    }
  }
}

trait Pass extends Logging {

  def shouldRun:Boolean
  lazy val name = this.getClass.getSimpleName
  override def toString = name
  
  val runPasses = mutable.ListBuffer[RunPass]()

  def reset:Unit = runPasses.foreach(_.reset)

  def newRun(id:Int):RunPass = {
    val runPass = RunPass(this, id)
    runPasses += runPass
    runPass
  }
  
  def initPass:Unit ={}

  def runPass:Unit

  def finPass:Unit ={}

  def check:Unit = {}

}
