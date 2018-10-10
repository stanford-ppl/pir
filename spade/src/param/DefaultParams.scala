package spade
package param

import SpadeConfig._
trait DefaultParams {
  def defaultTopParam:TopParam = {
    (option[String]("topo") , option[String]("net")) match {
      case ("mesh"  , "static") => StaticGridTopParam()
      case ("torus" , "static") => StaticGridTopParam()
      case ("cmesh" , "static") => StaticCMeshTopParam()
      case ("mesh"  , "dynamic")=> DynamicGridTopParam()
      case ("torus" , "dynamic") => DynamicGridTopParam()
      case ("cmesh" , "dynamic") => DynamicCMeshTopParam()
      case (_       , "asic") => AsicTopParam()
      case (_       , "p2p") => PointToPointTopParam()
      case _ => StaticGridTopParam()
    }
  }

  def defaultIsTorus = option[String]("topo") == "torus"

  def defaultFringePattern = if (option[Boolean]("dag")) MC_DramAG() else MCOnly()

  def defaultCentrolPattern = option[String]("pattern") match {
    case "checkerboard" => Checkerboard()
    case "cstrip" => ColumnStrip()
    case "rstrip" => RowStrip()
    case "mixall" => MixAll()
    case "half&half" => HalfAndHalf()
    case "MCMcstrip" => MCMColumnStrip()
  }

}
