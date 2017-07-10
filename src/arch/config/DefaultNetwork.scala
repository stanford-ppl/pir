package pir.plasticine.config

import pir.plasticine.main._
import pir.plasticine.graph._
import scala.language.implicitConversions
import scala.collection.mutable.Map
import pir.plasticine.config._
import scala.collection.immutable.{Map => IMap}
import pir.util.enums._
import scala.language.existentials

class VectorNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  type P = VectorIO.P
  def io(prt:Routable) = prt.vectorIO

  // switch to switch channel width
  channelWidth("src"->"sb", "dst"->"sb") = 4

  // switch to CU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->List("pcu", "mu", "mcu")) = 1

  // CU to Switch channel width
  channelWidth("pos"->"center", "src"->List("pcu", "mu", "mcu"), "dst"->"sb") = 1

  // MC to switch channel width
  channelWidth("pos"->List("left", "right"), "src"->"mc", "dst"->"sb") = 1
  // switch to MC channel width
  channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"mc") = 1
    
}

class ScalarNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  type P = ScalarIO.P
  def io(prt:Routable) = prt.scalarIO

  // switch to switch channel width
  channelWidth("src"->"sb", "dst"->"sb") = 4

  // switch to CU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->List("pcu", "mu", "mcu", "scu")) = 1

  // CU to Switch channel width
  channelWidth("pos"->"center", "src"->List("pcu", "mu", "mcu", "scu"), "dst"->"sb") = 1
  
  // SCU to switch channel width
  channelWidth("pos"->List("left", "right"), "src"->"scu", "dst"->"sb") = 2
  // switch to SCU channel width
  channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"scu") = 3

  // MC to switch channel width
  channelWidth("pos"->List("left", "right"), "src"->"mc", "dst"->"sb") = 1
  // switch to MC channel width
  channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"mc") = 1
    
  // SCU to MC channel width
  channelWidth("pos"->List("left", "right"), "src"->"scu", "dst"->"mc") = 2
  
  //// switch to OCU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->"ocu") = 2
  
  //// Top to switch channel width
  channelWidth("pos"->List("top", "bottom"), "src"->"Top", "dst"->"sb") = 1
  //// switch to Top channel width
  channelWidth("pos"->List("top", "bottom"), "src"->"sb", "dst"->"Top") = 1
  
  override def config = {
    // Add ins and outs to Top
    io(top).addInAt("S", spade.param.numArgOuts)
    io(top).addOutAt("S", spade.param.numArgIns)
    super.config
  }
}

class CtrlNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  type P = ControlIO.P
  def io(prt:Routable) = prt.ctrlIO

  // switch to switch channel width
  channelWidth("src"->"sb", "dst"->"sb") = 4

  // switch to CU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->List("pcu", "mu", "mcu", "scu")) = 1

  // CU to Switch channel width
  channelWidth("pos"->"center", "src"->List("pcu", "mu", "mcu", "scu"), "dst"->"sb") = 1

  // SCU to switch channel width
  channelWidth("pos"->List("left", "right"), "src"->"scu", "dst"->"sb") = 1
  // switch to SCU channel width
  channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"scu") = 1

  // MC to switch channel width
  channelWidth("pos"->List("left", "right"), "src"->"mc", "dst"->"sb") = 1
  // switch to MC channel width
  channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"mc") = 1

  // MC to SCU channel width
  channelWidth("pos"->List("left", "right"), "src"->"mc", "dst"->"scu") = 2
    
  // OCU to switch channel width
  channelWidth("pos"->"center", "src"->"ocu", "dst"->"sb") = 2
  // switch to OCU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->"ocu") = 4

  // Top to switch channel width
  channelWidth("pos"->List("top", "bottom"), "src"->"Top", "dst"->"sb") = 1
  // switch to Top channel width
  channelWidth("pos"->List("top", "bottom"), "src"->"sb", "dst"->"Top") = 1
  override def config = {
    // Add ins and outs to Top
    io(top).addInAt("S", 1) // status 
    io(top).addOutAt("S", 1) // command
    super.config
  }
}
