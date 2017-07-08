package pir.plasticine.config
                          
import pir.plasticine.graph._
import pir.plasticine.main._

import scala.language.implicitConversions
import scala.language.reflectiveCalls
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.immutable.{Map => IMap}
import scala.reflect.runtime.universe._
import pir.util.enums._
import scala.util.{Try, Success, Failure}

object SN16x8 extends SwitchNetwork(new SwitchNetworkParam(numRows=16, numCols=8, numArgIns=40, numArgOuts=5)) {
  override lazy val vectorNetwork = new VectorNetwork() {
    // switch to switch channel width
    channelWidth("src"->"sb", "dst"->"sb") = 6
  }

  override lazy val scalarNetwork = new ScalarNetwork() {
    // switch to switch channel width
    channelWidth("src"->"sb", "dst"->"sb", "srcDir"->List("NS", "SN")) = 8

    // switch to SCU channel width
    channelWidth("pos"->List("left", "right"), "src"->"sb", "dst"->"scu") = 6
  }

  override lazy val ctrlNetwork = new CtrlNetwork() {
    // switch to switch channel width
    channelWidth("src"->"sb", "dst"->"sb") = 12

    // switch to CU channel width
    channelWidth("pos"->"center", "src"->"sb", "dst"->List("pcu", "mu", "mcu")) = 4

    // CU to Switch channel width
    channelWidth("pos"->"center", "src"->List("pcu", "mu", "mcu"), "dst"->"sb") = 2
      
    // OCU to switch channel width
    channelWidth("pos"->"center", "src"->"ocu", "dst"->"sb") = 4
    // switch to OCU channel width
    channelWidth("pos"->"center", "src"->"sb", "dst"->"ocu") = 6
  }

}
