package pirc

import pirc._
import pirc.util._

import scala.collection.mutable.ListBuffer

trait Design {

  def name = getClass().getSimpleName().replace("$", "")
  override def toString = name

  /* Compiler Passes */
  val passes = ListBuffer[Pass]()

    passes.foreach(_.reset)

  def reset = passes.foreach(_.reset)

  def handle(e:Exception):Unit

  def run = {
    try {
      passes.zipWithIndex.foreach{ case (pass, id) => if (pass.shouldRun) pass.run(id) }
      passes.foreach { _.checkRanAll }
    } catch {
      case e:Exception => 
        errmsg(e)
        handle(e)
    }
  }

  val configs:List[GlobalConfig]

  def setArgs(args: Array[String]):Unit = {
    args.foreach { 
      case arg if arg.contains("--") => 
        configs.foreach(_.setOption(arg.replace("--", "")))
      case arg =>
    }
  }

  def main(args: Array[String]): Unit

}
