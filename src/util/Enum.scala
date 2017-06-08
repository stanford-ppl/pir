package pir.util

import scala.collection.mutable.ListBuffer

trait Ops {
  val _fixOps = ListBuffer[FixOp]()
  val _fltOps = ListBuffer[FltOp]()
  val _bitOps = ListBuffer[BitOp]()
  val _otherOps = ListBuffer[Op]()

  sealed trait Op 
  
  sealed trait FixOp extends Op
  case object FixAdd extends FixOp; _fixOps += FixAdd 
  case object FixSub extends FixOp; _fixOps += FixSub 
  case object FixMul extends FixOp; _fixOps += FixMul 
  case object FixDiv extends FixOp; _fixOps += FixDiv 
  case object FixMin extends FixOp; _fixOps += FixMin
  case object FixMax extends FixOp; _fixOps += FixMax 
  case object FixLt  extends FixOp; _fixOps += FixLt 
  case object FixLeq extends FixOp; _fixOps += FixLeq
  case object FixGt  extends FixOp; _fixOps += FixGt 
  case object FixGeq extends FixOp; _fixOps += FixGeq
  case object FixEql extends FixOp; _fixOps += FixEql
  case object FixNeq extends FixOp; _fixOps += FixNeq
  case object FixMod extends FixOp; _fixOps += FixMod
  case object FixSra extends FixOp; _fixOps += FixSra
  case object FixNeg extends FixOp; _fixOps += FixNeg
  
  sealed trait FltOp extends Op
  case object FltAdd extends FltOp; _fltOps += FltAdd 
  case object FltSub extends FltOp; _fltOps += FltSub 
  case object FltMul extends FltOp; _fltOps += FltMul 
  case object FltDiv extends FltOp; _fltOps += FltDiv 
  case object FltMin extends FltOp; _fltOps += FltMin 
  case object FltMax extends FltOp; _fltOps += FltMax 
  case object FltLt  extends FltOp; _fltOps += FltLt 
  case object FltLeq extends FltOp; _fltOps += FltLeq
  case object FltGt  extends FltOp; _fltOps += FltGt 
  case object FltGeq extends FltOp; _fltOps += FltGeq
  case object FltEql extends FltOp; _fltOps += FltEql
  case object FltNeq extends FltOp; _fltOps += FltNeq
  case object FltExp extends FltOp; _fltOps += FltExp
  case object FltAbs extends FltOp; _fltOps += FltAbs
  case object FltLog extends FltOp; _fltOps += FltLog
  case object FltSqr extends FltOp; _fltOps += FltSqr
  case object FltNeg extends FltOp; _fltOps += FltNeg 

  sealed trait BitOp  extends Op
  case object BitAnd  extends BitOp; _bitOps += BitAnd  // &
  case object BitOr   extends BitOp; _bitOps += BitOr   // |
  case object BitNot  extends BitOp; _bitOps += BitNot  // ~
  case object BitXnor extends BitOp; _bitOps += BitXnor // ==
  case object BitXor  extends BitOp; _bitOps += BitXor  // !=

  case object Mux    extends Op; _otherOps += Mux
  case object Bypass extends Op; _otherOps += Bypass 

  def fixOps = _fixOps.toList
  def fltOps = _fltOps.toList
  def bitOps = _bitOps.toList
  def otherOps = _otherOps.toList
  def ops = (fixOps ++ fltOps ++ bitOps ++ otherOps).toList

}

package object enums extends Ops {

  //sealed trait CtrlType 
  //case object Pipe extends CtrlType
  //case object Sequential extends CtrlType
  //case object MetaPipeline extends CtrlType
  
  sealed trait MCType {
    def isDense:Boolean = {
      this match {
        case TileLoad | TileStore => true
        case Gather | Scatter => false
      }
    }
    def isSparse:Boolean = {
      this match {
        case Gather | Scatter => true
        case TileLoad | TileStore => false
      }
    }
    def isLoad:Boolean = {
      this match {
        case TileLoad | Gather => true
        case TileStore | Scatter => false
      }
    }
    def isStore:Boolean = {
      this match {
        case TileStore | Scatter => true 
        case TileLoad | Gather => false
      }
    }
  }
  case object TileLoad extends MCType 
  case object TileStore extends MCType 
  case object Scatter extends MCType 
  case object Gather extends MCType 
  
  sealed trait Banking
  case class Strided(stride:Int) extends Banking
  case class Diagonal(stride1:Int, stride2:Int) extends Banking
  case class Duplicated() extends Banking
  case class NoBanking() extends Banking

  //sealed trait SramMode
  //case object Fifo extends SramMode
  //case object FifoOnWrite extends SramMode 
  //case object Sram extends SramMode

  trait RegColor
  case object VecInReg extends RegColor
  case object VecOutReg extends RegColor
  case object ScalarInReg extends RegColor
  case object ScalarOutReg extends RegColor
  //case object LoadReg extends RegColor
  //case object StoreReg extends RegColor
  case object ReadAddrReg extends RegColor
  case object WriteAddrReg extends RegColor
  case object CounterReg extends RegColor
  case object ReduceReg extends RegColor
  case object AccumReg extends RegColor
}

