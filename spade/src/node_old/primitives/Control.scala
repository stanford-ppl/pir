package spade
package node
import param._

case class UpDownCounter()(implicit sapde:SpadeDesign) extends Module {
  val inc = Input[Vector](s"writePort")
  val dec = Input[Vector](s"writeAddr")
  val count = Input[Vector](s"readAddr")
}

