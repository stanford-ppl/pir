package pir
package test

import pir.node._
import pir.mapper._
import prism.test._

class BankTest extends UnitTest {

  val bp = new BankPartitioner {}

  "BankTest" should "success" in {
    check(SRAMCost(count=1, bank=36, size=1024*2052), SRAMCost(count=1,bank=16,size=65536))
    check(SRAMCost(count=1, bank=1, size=1024*10), SRAMCost(count=1,bank=16,size=65536))
    check(SRAMCost(count=1, bank=16, size=1024*10), SRAMCost(count=1,bank=16,size=65536))
    check(SRAMCost(count=1, bank=16, size=1024*100), SRAMCost(count=1,bank=16,size=65536))
    check(SRAMCost(count=1, bank=1, size=1024*100), SRAMCost(count=1,bank=16,size=65536))
  }

  def check(kcost:SRAMCost, vcost:SRAMCost) = {
    val (totalBanks, bankPerCU, numCU, sizePerBank, bankMult) = bp.splitBanks(kcost, vcost)
    assert(bankPerCU <= vcost.bank, s"partitioner exceeds number of banks")
    assert(totalBanks >= kcost.bank, s"partitioner total banks < banks")
    assert(sizePerBank * bankPerCU <= vcost.size, s"partition size exceeds sram size")
  }

  "MergeTest1" should "success" in {
    val g1 = List.fill(3)(1)
    val g2 = List.fill(3)(2)
    val list = Set(g1,g2)
    val res = bp.merge(list, sizeLimit=5)
    assert(res == list)
  }

  "MergeTest2" should "success" in {
    val g1 = List.fill(3)(1)
    val g2 = List.fill(3)(2)
    val list = Set(g1,g2)
    val res = bp.merge(list, sizeLimit=6)
    assert(res == Set(g1 ++ g2))
  }

  "MergeTest3" should "success" in {
    val g1 = List.fill(1)(1)
    val g2 = List.fill(4)(2)
    val g3 = List.fill(5)(3)
    val g4 = List.fill(2)(4)
    val list = Set(g1,g2,g3,g4)
    val res = bp.merge(list, sizeLimit=6)
    assert(res == Set(g1 ++ g3, g2++g4))
  }

}
