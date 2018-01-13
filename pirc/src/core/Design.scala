package pirc

import pirc._
import pirc.util._

import scala.collection.mutable.ListBuffer

trait Design extends FileManager {

  def name = getClass().getSimpleName().replace("$", "")
  override def toString = name

  private var nextSym = 0
  def nextId = {nextSym += 1; nextSym }

  /* Compiler Passes */
  val passes = ListBuffer[Pass]()

    passes.foreach(_.reset)

  def reset = passes.foreach(_.reset)

  def handle(e:Exception):Unit

  def run = {
    passes.zipWithIndex.foreach{ case (pass, id) => if (pass.shouldRun) pass.run(id) }
    passes.foreach { _.checkRanAll }
  }

  val configs:List[GlobalConfig]

  def setArgs(args: Array[String]):Unit = {
    if (args.contains("--help")) {
      configs.foreach(_.printUsage)
      System.exit(0)
    }
    configs.foreach(_.setOption(args.toList))
  }

}
