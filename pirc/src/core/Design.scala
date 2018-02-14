package pirc

import pirc.util._

import scala.collection.mutable

trait Design extends FileManager {

  implicit val design:Design = this

  def name = getClass().getSimpleName().replace("$", "")
  override def toString = name

  private var nextSym = 0
  def nextId = if (staging) {nextSym += 1; nextSym } else 0

  var staging = true

  /* Compiler Passes */
  val passes = mutable.Set[Pass]()
  val runPasses = mutable.ListBuffer[RunPass]()

  def addPass(pass:Pass):RunPass = {
    passes += pass
    val runPass = pass.newRun(runPasses.size)
    runPasses += runPass
    runPass
  }

  def reset = runPasses.foreach(_.reset)

  def handle(e:Exception):Unit

  def run = runPasses.foreach { _.run }

  val configs:List[GlobalConfig]

  def setArgs(args: Array[String]):Unit = {
    if (args.contains("--help")) {
      configs.foreach(_.printUsage)
      System.exit(0)
    }
    configs.foreach(_.setOption(args.toList))
  }

}
