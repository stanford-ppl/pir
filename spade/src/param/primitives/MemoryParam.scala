package spade
package param

trait OnChipMemParam extends Parameter { 
  val size:Int // Total capacity in word
  val depth:Int // Buffer depth
}
case class SRAMParam(
  size:Int,
  depth:Int
) extends OnChipMemParam
case class FIFOParam(
  size:Int
) extends OnChipMemParam {
  val depth = size
}
//case object RegParam extends OnChipMemParam {
  //val size = 1
  //val depth = 1
//}
