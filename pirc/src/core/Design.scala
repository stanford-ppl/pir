package pirc

import pirc._
import pirc.util._
import prism.pass._

import scala.collection.mutable.ListBuffer

trait Design extends FileManager {

  def name = getClass().getSimpleName().replace("$", "")
  override def toString = name

  private var nextSym = 0
  def nextId = if (staging) {nextSym += 1; nextSym } else 0

  var staging = true

  /* Compiler Passes */
  val runPasses = ListBuffer[RunPass]()

  def addPass(pass:prism.pass.Pass):RunPass = {
    val runPass = pass.newRun(runPasses.size)
    runPasses += runPass
    runPass
  }

  def reset = runPasses.foreach(_.reset)

  def handle(e:Exception):Unit

  def run = runPasses.foreach { _.run(this) }

  val configs:List[GlobalConfig]

  def setArgs(args: Array[String]):Unit = {
    if (args.contains("--help")) {
      configs.foreach(_.printUsage)
      System.exit(0)
    }
    configs.foreach(_.setOption(args.toList))
  }

}
