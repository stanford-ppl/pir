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
    val parts = bp.splitBanks(kcost, vcost)
    val bankPerCU = parts.map { _.size }.max
    val totalBanks = parts.map { _.size }.sum
    val bankMult = totalBanks /! kcost.bank
    val sizePerBank = kcost.size /! totalBanks
    val numCU = parts.size
    assert(bankPerCU <= vcost.bank, s"partitioner exceeds number of banks")
    assert(totalBanks >= kcost.bank, s"partitioner total banks < banks")
    assert(sizePerBank * bankPerCU <= vcost.size, s"partition size exceeds sram size")
  }

}
