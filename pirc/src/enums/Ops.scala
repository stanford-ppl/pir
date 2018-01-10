package pirc.enums

sealed trait Op extends Enum { type T<:AnyVal }

sealed trait Op1 extends Op { def eval(a:T):AnyVal }
sealed trait Op2 extends Op { def eval(a:T, b:T):AnyVal }
sealed trait Op3 extends Op { def eval(a:T, b:T, c:T):AnyVal }

sealed trait CompOp extends Op

sealed trait FixOp extends Op { type T = Int }
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


sealed trait FltOp extends Op { type T = Float }
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


sealed trait BitOp  extends Op { type T = Boolean }
case object BitAnd  extends BitOp with Op2 { def eval(a:T, b:T) = a && b }
case object BitOr   extends BitOp with Op2 { def eval(a:T, b:T) = a || b }
case object BitNot  extends BitOp with Op1 { def eval(a:T     ) = !a     }
case object BitXnor extends BitOp with Op2 { def eval(a:T, b:T) = a == b }
case object BitXor  extends BitOp with Op2 { def eval(a:T, b:T) = a != b }


case object MuxOp   extends Op with Op3 { 
  type T = AnyVal 
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
  def eval(a:T) = a
}

trait Ops {
  val fixOps = List(FixAdd, FixSub, FixMul, FixDiv, FixMin, FixMax, FixLt, FixLeq, FixGt, FixGeq, FixEql, FixNeq, FixMod, FixSra, FixSla, FixNeg)
  val fltOps = List(FltAdd, FltSub, FltMul, FltDiv, FltMin, FltMax, FltLt, FltGt, FltGeq, FltEql, FltNeq, FltExp, FltAbs, FltLog, FltSqr, FltNeg)
  val bitOps = List(BitAnd, BitOr, BitNot, BitXnor, BitXor)
  val otherOps = List(MuxOp, Bypass)

  def ops = (fixOps ++ fltOps ++ bitOps ++ otherOps).toList
  def compOps = ops.collect {case op:CompOp => op}
  def fixCompOps = fixOps.collect {case op:CompOp => op}
  def fltCompOps = fltOps.collect {case op:CompOp => op}
}
