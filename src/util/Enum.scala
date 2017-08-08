package pir.util

import scala.collection.mutable.ListBuffer

package object enums extends Ops {

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
  case class Strided(stride:Int, banks:Int) extends Banking
  object Strided {
    def apply(stride:Int):Strided = {
      Strided(stride, 16) //TODO: rewrite app and fix this
    }
  }
  case class Diagonal(stride1:Int, stride2:Int) extends Banking
  case class Duplicated() extends Banking
  case class NoBanking() extends Banking

  trait RegColor
  case object VecInReg extends RegColor
  case object VecOutReg extends RegColor
  case object ScalarInReg extends RegColor
  case object ScalarOutReg extends RegColor
  case object ReadAddrReg extends RegColor
  case object WriteAddrReg extends RegColor
  case object CounterReg extends RegColor
  case object ReduceReg extends RegColor
  case object AccumReg extends RegColor
}

trait Ops {
  val _fixOps = ListBuffer[FixOp]()
  val _fltOps = ListBuffer[FltOp]()
  val _bitOps = ListBuffer[BitOp]()
  val _otherOps = ListBuffer[Op]()

  sealed trait Op { type T<:AnyVal }
 
  sealed trait Op1 extends Op { def eval(a:T):AnyVal }
  sealed trait Op2 extends Op { def eval(a:T, b:T):AnyVal }
  sealed trait Op3 extends Op { def eval(a:T, b:T, c:T):AnyVal }

  sealed trait CompOp extends Op

  sealed trait FixOp extends Op { type T = Int; _fixOps += this }
  case object FixAdd extends FixOp             with Op2 { def eval(a:T, b:T) = a + b          }
  case object FixSub extends FixOp             with Op2 { def eval(a:T, b:T) = a - b          }
  case object FixMul extends FixOp             with Op2 { def eval(a:T, b:T) = a * b          }
  case object FixDiv extends FixOp             with Op2 { def eval(a:T, b:T) = a / b          }
  case object FixMin extends FixOp             with Op2 { def eval(a:T, b:T) = Math.min(a, b) }
  case object FixMax extends FixOp             with Op2 { def eval(a:T, b:T) = Math.max(a, b) }
  case object FixLt  extends FixOp with CompOp with Op2 { def eval(a:T, b:T) = a < b          }
  case object FixLeq extends FixOp with CompOp with Op2 { def eval(a:T, b:T) = a <= b         }
  case object FixGt  extends FixOp with CompOp with Op2 { def eval(a:T, b:T) = a > b          }
  case object FixGeq extends FixOp with CompOp with Op2 { def eval(a:T, b:T) = a >= b         }
  case object FixEql extends FixOp with CompOp with Op2 { def eval(a:T, b:T) = a == b         }
  case object FixNeq extends FixOp with CompOp with Op2 { def eval(a:T, b:T) = a != b         }
  case object FixMod extends FixOp             with Op2 { def eval(a:T, b:T) = a % b          }
  case object FixSra extends FixOp             with Op2 { def eval(a:T, b:T) = a >> b}
  case object FixSla extends FixOp             with Op2 { def eval(a:T, b:T) = a << b}
  case object FixNeg extends FixOp             with Op1 { def eval(a:T     ) = -a             }
  
  sealed trait FltOp extends Op { type T = Float; _fltOps += this }
  case object FltAdd extends FltOp             with Op2 { def eval(a:T, b:T) = a + b           }
  case object FltSub extends FltOp             with Op2 { def eval(a:T, b:T) = a - b           }
  case object FltMul extends FltOp             with Op2 { def eval(a:T, b:T) = a * b           }
  case object FltDiv extends FltOp             with Op2 { def eval(a:T, b:T) = a / b           }
  case object FltMin extends FltOp             with Op2 { def eval(a:T, b:T) = Math.min(a , b) }
  case object FltMax extends FltOp             with Op2 { def eval(a:T, b:T) = Math.max(a , b) }
  case object FltLt  extends FltOp with CompOp with Op2 { def eval(a:T, b:T) = a < b           }
  case object FltLeq extends FltOp with CompOp with Op2 { def eval(a:T, b:T) = a <= b          }
  case object FltGt  extends FltOp with CompOp with Op2 { def eval(a:T, b:T) = a > b           }
  case object FltGeq extends FltOp with CompOp with Op2 { def eval(a:T, b:T) = a >= b          }
  case object FltEql extends FltOp with CompOp with Op2 { def eval(a:T, b:T) = a == b          }
  case object FltNeq extends FltOp with CompOp with Op2 { def eval(a:T, b:T) = a != b          }
  case object FltExp extends FltOp             with Op1 { def eval(a:T     ) = Math.exp(a)     }
  case object FltAbs extends FltOp             with Op1 { def eval(a:T     ) = Math.abs(a)     }
  case object FltLog extends FltOp             with Op1 { def eval(a:T     ) = Math.log(a)     }
  case object FltSqr extends FltOp             with Op1 { def eval(a:T     ) = Math.sqrt(a)    }
  case object FltNeg extends FltOp             with Op1 { def eval(a:T     ) = -a              }

  sealed trait BitOp  extends Op { type T = Boolean; _bitOps += this }
  case object BitAnd  extends BitOp with Op2 { def eval(a:T, b:T) = a && b }
  case object BitOr   extends BitOp with Op2 { def eval(a:T, b:T) = a || b }
  case object BitNot  extends BitOp with Op1 { def eval(a:T     ) = !a     }
  case object BitXnor extends BitOp with Op2 { def eval(a:T, b:T) = a == b }
  case object BitXor  extends BitOp with Op2 { def eval(a:T, b:T) = a != b }

  case object Mux    extends Op with Op3 { 
    type T = AnyVal 
    _otherOps += this
    def eval(s:T, a:T, b:T) = {
      s match {
        case s:Boolean => if (s) a else b
        case s:Int => if (s>0) a else b
        case s:Float => if (s>0) a else b
      }
    }
  }
  case object Bypass extends Op with Op1 { 
    type T = AnyVal
    _otherOps += this
    def eval(a:T) = a
  }

  def fixOps = _fixOps.toList
  def fltOps = _fltOps.toList
  def bitOps = _bitOps.toList
  def otherOps = _otherOps.toList
  def ops = (fixOps ++ fltOps ++ bitOps ++ otherOps).toList
  def compOps = ops.collect {case op:CompOp => op}
  def fixCompOps = fixOps.collect {case op:CompOp => op}
  def fltCompOps = fltOps.collect {case op:CompOp => op}

FixAdd 
FixSub 
FixMul 
FixDiv 
FixMin 
FixMax 
FixLt  
FixLeq 
FixGt  
FixGeq 
FixEql 
FixNeq 
FixMod 
FixSra 
FixSla 
FixNeg 

FltAdd
FltSub
FltMul
FltDiv
FltMin
FltMax
FltLt 
FltLeq
FltGt 
FltGeq
FltEql
FltNeq
FltExp
FltAbs
FltLog
FltSqr
FltNeg

BitAnd 
BitOr  
BitNot 
BitXnor
BitXor 
       
Mux    
Bypass 

}


