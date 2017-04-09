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
  def check(implicit design:Design):Unit = {
    this match {
      case n:Counter if isMapped(n) =>
        println(n.out.v.func)
      case _ =>
    }
    ios.foreach { io =>
      if (isMapped(io) && io.v.func.isEmpty) warn(s"Simulatable ${quote(this)}'s $io doesn't have a update function!")
    }
  }
}

