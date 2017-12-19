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
    passes.zipWithIndex.foreach{ case (pass, id) => if (pass.shouldRun) pass.run(id) }
    passes.foreach { _.checkRanAll }
  }

  val configs:List[GlobalConfig]

  def setArgs(args: Array[String]):Unit = {
    (0 until args.size).foreach { case i =>
      configs.foreach(_.setOption(args.splitAt(i)._2))
    }
  }

  def main(args: Array[String]): Unit

}
