package pir.graph

package object enums {

  sealed trait Op 
  case object Mux extends Op
  case object Bypass extends Op
  case object BitAnd extends Op // &
  case object BitOr  extends Op // |
  
  sealed trait FixOp extends Op 
  case object FixAdd extends FixOp 
  case object FixSub extends FixOp 
  case object FixMul extends FixOp 
  case object FixDiv extends FixOp 
  case object FixMin extends FixOp
  case object FixMax extends FixOp 
  case object FixLt  extends FixOp
  case object FixLeq extends FixOp
  case object FixEql extends FixOp
  case object FixNeq extends FixOp
  
  sealed trait FltOp extends Op 
  case object FltAdd extends FltOp 
  case object FltSub extends FltOp 
  case object FltMul extends FltOp 
  case object FltDiv extends FltOp 
  case object FltMin extends FltOp 
  case object FltMax extends FltOp 
  case object FltLt  extends FltOp
  case object FltLeq extends FltOp
  case object FltEql extends FltOp
  case object FltNeq extends FltOp
  case object FltExp extends FltOp
  case object FltAbs extends FltOp

  val fixOps:List[FixOp] = 
    List(FixAdd, FixSub, FixMul, FixDiv, FixMin, FixMax, FixLt, FixLeq, FixEql,
        FixNeq)
  val fltOps:List[FltOp] = 
    List(FltAdd, FltSub, FltMul, FltDiv, FltMin, FltMax, FltLt, FltLeq, FltEql,
      FltNeq, FltExp, FltAbs)
  val ops:List[Op] = fixOps ++ fltOps ++ List(Mux, Bypass, BitAnd, BitOr) 

  //sealed trait CtrlType 
  //case object Pipe extends CtrlType
  //case object Sequential extends CtrlType
  //case object MetaPipeline extends CtrlType
  
  sealed trait MCType 
  case object TileLoad extends MCType 
  case object TileStore extends MCType 
  
  sealed trait Banking
  case class Strided(stride:Int) extends Banking
  case class Diagonal(stride1:Int, stride2:Int) extends Banking
  case class Duplicated() extends Banking
  case class NoBanking() extends Banking

  sealed trait Buffering 
  case class MultiBuffer(depth:Int, swapRead:Counter, swapWrite:Counter) extends Buffering
  case class DoubleBuffer(swapRead:Counter, swapWrite:Counter) extends Buffering
  case class SingleBuffer() extends Buffering
}

