package pirc

import pirc.util._
import pirc.pass.Pass

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

}
