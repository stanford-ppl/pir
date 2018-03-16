package pir

import prism.enums._

trait PIREnums {
  sealed trait BundleType extends Enum
  case object Bit extends BundleType
  case object Word extends BundleType
  case object Vector extends BundleType
}

