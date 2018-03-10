package prism

import prism._
import prism.util._

import scala.collection.mutable

abstract class Pass(implicit val compiler:Compiler) extends Logging {

  implicit val pass:this.type = this
  def shouldRun:Boolean
  lazy val name = this.getClass.getSimpleName
  override def toString = name
  
  def reset = {}

  def initPass(runner:RunPass[_]):Unit = {
    infor(s"Running ${runner.name} ...")
  }

  def runPass(runner:RunPass[_]):Unit = runPass
  def runPass:Unit = {}

  def finPass(runner:RunPass[_]):Unit = check

  def check(runner:RunPass[_]):Unit = check
  def check:Unit = {}

  def run(runner:RunPass[_]) = {
    initPass(runner)
    runPass(runner)
    finPass(runner)
    check(runner)
  }

  def quote(n:Any):String

}
