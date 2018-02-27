package pirc

import pirc._
import pirc.util._

import scala.reflect._
import scala.collection.mutable

trait Pass extends Logging {

  def shouldRun:Boolean
  lazy val name = this.getClass.getSimpleName
  override def toString = name
  
  def reset = {}

  def initPass(runner:RunPass[_]):Unit = {
    info(s"Running ${runner.name} ...")
    initPass
  }
  def initPass:Unit = {}

  def runPass(runner:RunPass[_]):Unit = runPass
  def runPass:Unit = {}

  def finPass(runner:RunPass[_]):Unit = { finPass; check } 
  def finPass:Unit = {}

  def check(runner:RunPass[_]):Unit = check
  def check:Unit = {}

}
