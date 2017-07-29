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
  override def toString = "VectorNetwork"
  def io(prt:Routable) = prt.vectorIO

  // switch to switch channel width
  channelWidth("src"->"sb", "dst"->"sb") = 4

  // switch to PCU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->List("pcu")) = roundUp(pcuVins / 4.0)

  // PCU to Switch channel width
  channelWidth("pos"->"center", "src"->List("pcu"), "dst"->"sb") = roundUp(pcuVouts / 4.0)

  // switch to MCU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->List("mcu")) = roundUp(mcuVins / 4.0) 

  // MCU to Switch channel width
  channelWidth("pos"->"center", "src"->List("mcu"), "dst"->"sb") = roundUp(mcuVouts / 4.0)

  // MC to switch channel width
  channelWidth("pos"->List("left", "right"), "src"->"mc", "dst"->"sb") = 1
  // switch to MC channel width
  channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"mc") = 1
    
}

class ScalarNetwork()(implicit spade:SwitchNetwork) extends GridNetwork() {
  type P = ScalarIO.P
  override def toString = "ScalarNetwork"
  def io(prt:Routable) = prt.scalarIO

  // switch to switch channel width
  channelWidth("src"->"sb", "dst"->"sb") = 4

  // switch to PCU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->List("pcu", "scu")) = roundUp(pcuSins / 4.0) 

  // PCU to Switch channel width
  channelWidth("pos"->"center", "src"->List("pcu", "scu"), "dst"->"sb") = roundUp(pcuSouts / 4.0)

  // switch to MCU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->List("mcu")) = roundUp(mcuSins / 4.0) 

  // MCU to Switch channel width
  channelWidth("pos"->"center", "src"->List("mcu"), "dst"->"sb") = roundUp(mcuSouts / 4.0)
  
  // switch to SCU channel width
  channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"scu") = roundUp(ucuSins)

  // SCU to switch channel width
  channelWidth("pos"->List("left", "right"), "src"->"scu", "dst"->"sb") = roundUp(ucuSouts) - 2

  // MC to switch channel width
  channelWidth("pos"->List("left", "right"), "src"->"mc", "dst"->"sb") = 1
  // switch to MC channel width
  channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"mc") = 1
    
  // SCU to MC channel width
  channelWidth("pos"->List("left", "right"), "src"->"scu", "dst"->"mc") = 2
  
  //// switch to OCU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->"ocu") = 10
  
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
  override def toString = "ControlNetwork"
  def io(prt:Routable) = prt.ctrlIO

  // switch to switch channel width
  channelWidth("src"->"sb", "dst"->"sb") = 4

  // switch to CU channel width
  channelWidth("pos"->"center", "src"->"sb", "dst"->List("pcu", "mu", "mcu", "scu")) = 1

  // CU to Switch channel width
  channelWidth("pos"->"center", "src"->List("pcu", "mu", "mcu", "scu"), "dst"->"sb") = 2

  // SCU to switch channel width
  channelWidth("pos"->List("left", "right"), "src"->"scu", "dst"->"sb") = 1
  // switch to SCU channel width
  channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"scu") = 1

  // MC to switch channel width
  channelWidth("pos"->List("left", "right"), "src"->"mc", "dst"->"sb") = 2
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
