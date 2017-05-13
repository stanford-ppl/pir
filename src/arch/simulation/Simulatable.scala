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

trait Simulatable extends Module {
  spade.simulatable += this
  def register(implicit sim:Simulator):Unit = {
    val fimap = sim.mapping.fimap
    ins.foreach { in =>
      if (in.v.func.isEmpty) {
        fimap.get(in).fold {
          if (in.fanIns.size==1) in.v <= in.fanIns.head
        } { 
          out => in.v <= out
        }
      }
    }
  }
  // Check if a mapped simulatable has update function defined on all its io
  def check(implicit sim:Simulator):Unit = {
    implicit val design:Design = sim.design
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
      if (isMapped(io) && io.v.func.isEmpty) warn(s"Simulatable ${quote(this)}'s $io doesn't have a update function!")
    }
  }
}

