package spade
package node
import param._

case class MC(
  param:MCParam, 
  bundles:List[Bundle[_<:PinType]]
)(implicit design:SpadeDesign) extends Terminal {
  import param._
  val wOffset = Module(FIFO[Word](wOffsetFifoParam),"wOffset")
  val rOffset = Module(FIFO[Word](rOffsetFifoParam),"rOffset")
  val wSize   = Module(FIFO[Word](wSizeFifoParam  ),"wSize"  )
  val rSize   = Module(FIFO[Word](rSizeFifoParam  ),"rSize"  )
  val sData   = Module(FIFO[Word](sDataFifoParam  ),"sData"  )
  val vData   = Module(FIFO[Word](vDataFifoParam  ),"vData"  )
}
