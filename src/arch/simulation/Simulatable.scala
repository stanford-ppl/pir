package pir.spade.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Logger
import pir.pass.Pass
import pir.spade.main._
import pir.spade.graph._
import pir.spade.traversal._
import pir.exceptions.PIRException
import pir.util.misc._
import pir.spade.util._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

trait Simulatable extends Module with Evaluation {
  spade.simulatable += this

  final def registerAll(implicit sim:Simulator):Unit = {
    register
    import sim.util._
    ins.foreach { in =>
      if (!in.v.isDefined) { fanInOf(in).foreach { out => in := out } }
    }
  }

  def register(implicit sim:Simulator):Unit

  // Check if a mapped simulatable has update function defined on all its io
  def check(implicit sim:Simulator):Unit = {
    import sim.util._
    ios.foreach { io =>
      if (isMapped(io) && !io.v.isDefined) {
        warn(s"Simulatable ${quote(io.v)} doesn't have a update function!")
      }
      io.check
    }
  }
  def updateModule(implicit sim:Simulator):Unit = {
    import sim.util._
    emitBlock(s"UpdateModule ${quote(this)} #$cycle") {
      ios.foreach { io => io.v.update }
    }
  }
  def reset(implicit sim:Simulator) = {
    ios.foreach { io => io.reset }
    clearModule
    zeroModule
  }
  def clearModule(implicit sim:Simulator):Unit = {
    ios.foreach { io => io.clearUpdate }
  }
  def zeroModule(implicit sim:Simulator) = {
    ios.foreach { _.zero }
  }
  def simCount = ios.size
}

