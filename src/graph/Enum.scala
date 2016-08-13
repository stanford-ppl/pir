package pir.graph

sealed trait Op 
case object Mux extends Op
case object Bypass extends Op

sealed trait FixOp extends Op 
case object FixAdd extends FixOp 
case object FixSub extends FixOp 
case object FixMul extends FixOp 
case object FixDiv extends FixOp 
case object FixMin extends FixOp 
case object FixMax extends FixOp 
case object FixCmp extends FixOp 

sealed trait FltOp extends Op 
case object FltAdd extends FltOp 
case object FltSub extends FltOp 
case object FltMul extends FltOp 
case object FltDiv extends FltOp 
case object FltMin extends FltOp 
case object FltMax extends FltOp 
case object FltCmp extends FltOp 

object Ops {
  val fixOps:List[FixOp] = List(FixAdd, FixSub, FixMul, FixDiv, FixMin, FixMax, FixCmp)
  val fltOps:List[FltOp] = List(FltAdd, FltSub, FltMul, FltDiv, FltMin, FltMax, FltCmp)
  val allOps:List[Op] = fixOps ++ fltOps ++ List(Mux, Bypass) 
}

sealed trait CtrlType 
case object Pipe extends CtrlType
case object Sequential extends CtrlType
case object MetaPipeline extends CtrlType

sealed trait MCType 
case object TileLoad extends MCType 
case object TileStore extends MCType 

sealed trait Banking
case class Strided(stride:Int) extends Banking
case class Diagonal(stride1:Int, stride2:Int) extends Banking
case class Duplicated() extends Banking
case class NoBanking() extends Banking
