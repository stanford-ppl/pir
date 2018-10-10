package spade

sealed trait PinType extends prism.enums.Enum
trait Bit extends PinType
trait Word extends PinType
trait Vector extends PinType
