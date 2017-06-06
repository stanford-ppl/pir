package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Logger
import pir.pass.Pass
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.traversal._
import pir.exceptions.PIRException
import pir.util.misc._
import pir.plasticine.util._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

trait Simulatable extends Module with Evaluation {
  spade.simulatable += this
  def register(implicit sim:Simulator):Unit = {
    import sim.util._
    ins.foreach { in =>
      if (!in.v.isDefined) {
        fimap.get(in).fold {
          if (in.fanIns.size==1) {
            in := in.fanIns.head
          }
        } { out => 
          in := out
        }
      }
    }
  }
  // Check if a mapped simulatable has update function defined on all its io
  def check(implicit sim:Simulator):Unit = {
    import sim.util._
    implicit val mp:PIRMap = sim.mapping
    ios.foreach { io =>
      //this match {
        //case n:Counter if isMapped(n) =>
          //io match {
            //case io:Output[_,_] =>
              //println(s"${io} ${isMapped(io)} ${sim.mapping.opmap.pmap.contains(io)}")
            //case _ =>
          //}
        //case _ =>
      //}
      if (isMapped(io) && !io.v.isDefined) {
        warn(s"Simulatable ${quote(io.v)} doesn't have a update function!")
      }
    }
  }
  def updateModule(implicit sim:Simulator):Unit = {
    import sim.util._
    ios.foreach { io => io.update }
  }
  def clearModule(implicit sim:Simulator):Unit = {
    ios.foreach { io => io.clearUpdate }
  }
  def simCount = ios.size
}

