package spade

import spade.param._

trait SpadeLocalAlias extends ParamUtil {
  //type SpadeMapLike = spade.config.SpadeMapLike
}

//trait SpadeAlias extends SpadeLocalAlias with spade.node.SpadeNodeUtil {
trait SpadeAlias extends SpadeLocalAlias {
  type Spade = spade.Spade
  type SNode = spade.node.SpadeNode

  //type FIMap = spade.config.FIMap
  //val FIMap = spade.config.FIMap
  //type ConfigMap = spade.config.ConfigMap
  //val ConfigMap = spade.config.ConfigMap
  //val SpadeConfig = spade.SpadeConfig

  //type Bit = spade.Bit
  //type Word = spade.Word
  //type Vector = spade.Vector
  //type PinType = spade.PinType
}

