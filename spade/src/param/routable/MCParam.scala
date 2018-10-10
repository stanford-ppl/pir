package spade
package param

case class MCParam (
  wOffsetFifoParam:FIFOParam=FIFOParam(size=16),
  rOffsetFifoParam:FIFOParam=FIFOParam(size=16),
  wSizeFifoParam:FIFOParam=FIFOParam(size=16),
  rSizeFifoParam:FIFOParam=FIFOParam(size=16),
  sDataFifoParam:FIFOParam=FIFOParam(size=16),
  vDataFifoParam:FIFOParam=FIFOParam(size=16)
) extends Parameter
