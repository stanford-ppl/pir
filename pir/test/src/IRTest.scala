package pir
package test

import pir._
import pir.node._
import prism.graph._
import prism.util._
import prism.test._

class IRTest extends UnitTest with Serialization with TestEnv with Transformer {

  "PIRTest" should "success" in {
    val top = Top()
    within(top) {
      val mem = InputBuffer(false)
      BufferRead().mem(mem)
      BufferWrite().mem(mem)
      val sram = SRAM()
      val sramRead = BankedRead().mem(sram).bank(Nil)
      val sramWrite = BankedWrite().mem(sram)
      assert(sramRead.mem.T == sram)
      assert(sramRead.bank.T == Nil)
      assert(sramWrite.bank.T == Nil)
    }
    val path = s"$testOut/irtest"
    saveToFile(top, path)
    val loaded = loadFromFile[Top](path)
    new prism.codegen.BasicIRDotGen(testOut, s"irtest.dot", loaded).run
  }

  "PIRMirrorTest1" should "success" in {
    val sram = SRAM()
    val sramRead = BankedRead().mem(sram).bank(Nil)
    val sramWrite = BankedWrite().mem(sram)
    sram.name := "sram"
    val mapping = mirrorAll(List(sramRead, sram))
    assert(mapping(sram).as[SRAM].name.get=="sram")
    assert(mapping(sram).as[SRAM].in.T.head==sramWrite)
  }
  
  "PIRMirrorTest2" should "success" in {
    val sram = SRAM()
    val sramRead = BankedRead().mem(sram).bank(Nil)
    val sramWrite = BankedWrite().mem(sram)
    val mapping = mirrorAll(List(sramRead, sram, sramWrite))
    assert(mapping(sram).as[SRAM].in.T.head==mapping(sramWrite))
  }

  "PIRMirrorTest3" should "success" in {
    val sram = SRAM()
    val sramRead = BankedRead().mem(sram).bank(Nil)
    val sramWrite = BankedWrite().mem(sram)
    val mapping = mirrorAll(List(sram, sramWrite))
    assert(mapping(sram).as[SRAM].out.T.isEmpty)
  }

}
