package prism.test

import prism.collection.mutable
import prism.util._

import prism.exceptions._
import prism.graph._
import prism._

class EnumTest extends UnitTest with Serialization {
  import opss._
  "Random" should "success" in {
    val path = s"$testOut/saved5"
    saveToFile(fixOps, path)
    val loaded = loadFromFile[Any](path)
    assert(fixOps == loaded)
  }
}

object opss extends Enumeration {
  sealed class Opcode extends Val
  sealed trait Op1 extends Opcode
  sealed trait Op2 extends Opcode
  sealed trait Op3 extends Opcode
  sealed trait Op4 extends Opcode
  sealed class FixOp extends Opcode
  sealed class FltOp extends Opcode
  sealed class BitOp  extends Opcode

  val FixInv = new FixOp with Op1
  val FixNeg = new FixOp with Op1

  val fixOps = values.collect { case op:FixOp => op }
}

