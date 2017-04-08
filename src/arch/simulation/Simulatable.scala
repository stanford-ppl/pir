package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Logger
import pir.pass.Pass
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.traversal._
import pir.exceptions.PIRException

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

trait Simulatable extends Module {
  spade.simulatable += this
  def register(implicit sim:Simulator):Unit = {
    val fimap = sim.mapping.fimap
    ins.foreach { in =>
      fimap.get(in).fold {
        if (in.fanIns.size==1) in.v <= in.fanIns.head
      } { 
        out => in.v <= out
      }
    }
  }
}

