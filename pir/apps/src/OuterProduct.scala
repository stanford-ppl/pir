import pir._
import pir.node._
import arch._
import prism.enums._

object OuterProduct extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1682 = ArgIn(init=0).name("x1682").ctrl(top).srcCtx("OuterProduct.scala:19:22:sizeA") // ArgInNew(Const(0))
    isAccum(x1682) = false
    bufferDepthOf(x1682) = 1
    boundOf(x1682) = 128
    val x1683_d0 = ArgIn(init=0).name("x1683_d0").ctrl(top).srcCtx("OuterProduct.scala:20:22:sizeB") // ArgInNew(Const(0))
    isAccum(x1683_d0) = false
    bufferDepthOf(x1683_d0) = 1
    boundOf(x1683_d0) = 64
    val x1686 = ReadMem(x1682).name("x1686").ctrl(top).srcCtx("OuterProduct.scala:24:24") // RegRead(x1682)
    val x1687 = DRAM().name("x1687").ctrl(top).srcCtx("OuterProduct.scala:24:23:vec1") // x1687 = DRAMNew(ArrayBuffer(x1686),Const(0))
    val x1688 = ReadMem(x1683_d0).name("x1688").ctrl(top).srcCtx("OuterProduct.scala:25:24") // RegRead(x1683)
    val x1689 = DRAM().name("x1689").ctrl(top).srcCtx("OuterProduct.scala:25:23:vec2") // x1689 = DRAMNew(ArrayBuffer(x1688),Const(0))
    val x1690 = ReadMem(x1683_d0).name("x1690").ctrl(top).srcCtx("OuterProduct.scala:26:30") // RegRead(x1683)
    val x1691 = ReadMem(x1682).name("x1691").ctrl(top).srcCtx("OuterProduct.scala:26:23") // RegRead(x1682)
    val x1692 = DRAM().name("x1692").ctrl(top).srcCtx("OuterProduct.scala:26:22:out") // x1692 = DRAMNew(ArrayBuffer(x1691, x1690),Const(0))
    val x1802 = UnitController(style=SeqPipe, level=OuterControl).name("x1802").ctrl(top).srcCtx("OuterProduct.scala:31:11") // Hwblock(Block(Const(())),false)
    val x1695 = ReadMem(x1683_d0).name("x1695").ctrl(x1802).srcCtx("OuterProduct.scala:32:37") // RegRead(x1683)
    val x1696 = Counter(min=Const(0), max=x1695, step=Const(16), par=1).name("x1696").ctrl(x1802).srcCtx("OuterProduct.scala:32:50") // CounterNew(Const(0),x1695,Const(16),Const(1))
    val x1697 = ReadMem(x1682).name("x1697").ctrl(x1802).srcCtx("OuterProduct.scala:32:15") // RegRead(x1682)
    val x1698 = Counter(min=Const(0), max=x1697, step=Const(32), par=1).name("x1698").ctrl(x1802).srcCtx("OuterProduct.scala:32:28") // CounterNew(Const(0),x1697,Const(32),Const(1))
    val x1699 = CounterChain(List(x1698,x1696)).name("x1699").ctrl(x1802).srcCtx("OuterProduct.scala:32:58") // CounterChainNew(List(x1698, x1696))
    val x1801 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1699).name("x1801").ctrl(x1802).srcCtx("OuterProduct.scala:32:58") // UnrolledForeach(List(Const(true)),x1699,Block(Const(())),List(List(b974), List(b975)),List(List(b976), List(b977)))
    val b974 = CounterIter(x1698, Some(0)).name("b974").ctrl(x1801) // b974
    val b976 = Const(true).name("b976").ctrl(x1801) // b976
    val b975 = CounterIter(x1696, Some(0)).name("b975").ctrl(x1801) // b975
    val b977 = Const(true).name("b977").ctrl(x1801) // b977
    val x1700_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1700_d0_b0").ctrl(x1801).srcCtx("OuterProduct.scala:33:25:b1") // x1700 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1700_d0_b0) = false
    bufferDepthOf(x1700_d0_b0) = 2
    val x1701_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x1701_d0_b0").ctrl(x1801).srcCtx("OuterProduct.scala:34:25:b2") // x1701 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x1701_d0_b0) = false
    bufferDepthOf(x1701_d0_b0) = 2
    val x1702_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x1702_d0_b0").ctrl(x1801).srcCtx("OuterProduct.scala:35:30:outTile") // x1702 = SRAMNew(ArrayBuffer(Const(32), Const(16)))
    isAccum(x1702_d0_b0) = false
    bufferDepthOf(x1702_d0_b0) = 3
    val x1749 = UnitController(style=ForkJoin, level=OuterControl).name("x1749").ctrl(x1801).srcCtx("OuterProduct.scala:36:18") // ParallelPipe(List(b976, b977),Block(Const(())))
    val x1704 = UnitController(style=SeqPipe, level=InnerControl).name("x1704").ctrl(x1749).srcCtx("OuterProduct.scala:36:18") // UnitPipe(List(b976, b977),Block(Const(())))
    val x1703 = OpDef(op=FixAdd, inputs=List(b974, Const(32))).name("x1703").ctrl(x1704).srcCtx("OuterProduct.scala:37:28") // FixAdd(b974,Const(32))
    val x1725 = UnitController(style=StreamPipe, level=OuterControl).name("x1725").ctrl(x1749).srcCtx("OuterProduct.scala:37:14") // UnitPipe(List(b976, b977),Block(Const(())))
    val b1819 = StreamOut(field="offset").name("b1819").ctrl(x1725).srcCtx("OuterProduct.scala:37:14") // x1705 = StreamOutNew(BurstCmdBus)
    isAccum(b1819) = false
    bufferDepthOf(b1819) = 1
    val b1820 = StreamOut(field="size").name("b1820").ctrl(x1725).srcCtx("OuterProduct.scala:37:14") // x1705 = StreamOutNew(BurstCmdBus)
    isAccum(b1820) = false
    bufferDepthOf(b1820) = 1
    val x1706 = StreamIn(field="data").name("x1706").ctrl(x1725).srcCtx("OuterProduct.scala:37:14") // x1706 = StreamInNew(BurstDataBus())
    isAccum(x1706) = false
    bufferDepthOf(x1706) = 1
    val x1715 = UnitController(style=SeqPipe, level=InnerControl).name("x1715").ctrl(x1725).srcCtx("OuterProduct.scala:37:14") // UnitPipe(List(b976, b977),Block(x1714))
    val x1707 = b974 // FixConvert(b974,TRUE,_32,_0) (Same Type. No op)
    val x1708 = OpDef(op=FixSla, inputs=List(x1707, Const(2))).name("x1708").ctrl(x1715).srcCtx("OuterProduct.scala:37:14") // FixLsh(x1707,Const(2))
    val x1709 = x1708 // FixConvert(x1708,TRUE,_64,_0)
    val x1710 = DramAddress(x1687).name("x1710").ctrl(x1715).srcCtx("OuterProduct.scala:37:14") // GetDRAMAddress(x1687)
    val x1712_x1711 = OpDef(op=FixAdd, inputs=List(x1709, x1710)).name("x1712_x1711").ctrl(x1715).srcCtx("OuterProduct.scala:37:14") // FixAdd(x1709,x1710)
    // x1712 = SimpleStruct(ArrayBuffer((offset,x1711), (size,Const(128)), (isLoad,Const(true))))
    val x1713 = OpDef(op=BitAnd, inputs=List(b976, b977)).name("x1713").ctrl(x1715).srcCtx("UnrollingBase.scala:28:66") // And(b976,b977)
    val x1714_b1821_b1819 = WriteMem(b1819, x1712_x1711).name("x1714_b1821_b1819").ctrl(x1715).srcCtx("OuterProduct.scala:37:14") // StreamWrite(x1705,x1712,x1713)
    val x1714_b1822_b1820 = WriteMem(b1820, Const(128)).name("x1714_b1822_b1820").ctrl(x1715).srcCtx("OuterProduct.scala:37:14") // StreamWrite(x1705,x1712,x1713)
    val x1716 = FringeDenseLoad(dram=List(x1687), cmdStream=List(b1819, b1820), dataStream=List(x1706)).name("x1716").ctrl(x1725).srcCtx("OuterProduct.scala:37:14") // FringeDenseLoad(x1687,x1705,x1706)
    val x1717 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1717").ctrl(x1725).srcCtx("OuterProduct.scala:37:14") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1718 = CounterChain(List(x1717)).name("x1718").ctrl(x1725).srcCtx("OuterProduct.scala:37:14") // CounterChainNew(List(x1717))
    val x1724 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1718).name("x1724").ctrl(x1725).srcCtx("OuterProduct.scala:37:14") // UnrolledForeach(List(b976, b977),x1718,Block(Const(())),List(List(b997)),List(List(b998)))
    val b997 = CounterIter(x1717, None).name("b997").ctrl(x1724) // b997
    val b998 = Const(true).name("b998").ctrl(x1724) // b998
    val x1719 = OpDef(op=BitAnd, inputs=List(b998, b976)).name("x1719").ctrl(x1724).srcCtx("UnrollingBase.scala:28:66") // And(b998,b976)
    val x1720 = OpDef(op=BitAnd, inputs=List(x1719, b977)).name("x1720").ctrl(x1724).srcCtx("UnrollingBase.scala:28:66") // And(x1719,b977)
    val x1721_x1721 = ReadMem(x1706).name("x1721_x1721").ctrl(x1724).srcCtx("OuterProduct.scala:37:14") // ParStreamRead(x1706,List(x1720))
    val x1722_x1722 = x1721_x1721 // x1722 = VectorApply(x1721,0)
    val x1723 = StoreBanks(List(x1700_d0_b0), List(b997), x1722_x1722).name("x1723").ctrl(x1724).srcCtx("OuterProduct.scala:37:14") // ParSRAMStore(x1700,List(List(b997)),List(x1722),List(x1720))
    val x1727 = UnitController(style=SeqPipe, level=InnerControl).name("x1727").ctrl(x1749).srcCtx("OuterProduct.scala:36:18") // UnitPipe(List(b976, b977),Block(Const(())))
    val x1726 = OpDef(op=FixAdd, inputs=List(b975, Const(16))).name("x1726").ctrl(x1727).srcCtx("OuterProduct.scala:38:28") // FixAdd(b975,Const(16))
    val x1748 = UnitController(style=StreamPipe, level=OuterControl).name("x1748").ctrl(x1749).srcCtx("OuterProduct.scala:38:14") // UnitPipe(List(b976, b977),Block(Const(())))
    val b1823 = StreamOut(field="offset").name("b1823").ctrl(x1748).srcCtx("OuterProduct.scala:38:14") // x1728 = StreamOutNew(BurstCmdBus)
    isAccum(b1823) = false
    bufferDepthOf(b1823) = 1
    val b1824 = StreamOut(field="size").name("b1824").ctrl(x1748).srcCtx("OuterProduct.scala:38:14") // x1728 = StreamOutNew(BurstCmdBus)
    isAccum(b1824) = false
    bufferDepthOf(b1824) = 1
    val x1729 = StreamIn(field="data").name("x1729").ctrl(x1748).srcCtx("OuterProduct.scala:38:14") // x1729 = StreamInNew(BurstDataBus())
    isAccum(x1729) = false
    bufferDepthOf(x1729) = 1
    val x1738 = UnitController(style=SeqPipe, level=InnerControl).name("x1738").ctrl(x1748).srcCtx("OuterProduct.scala:38:14") // UnitPipe(List(b976, b977),Block(x1737))
    val x1730 = b975 // FixConvert(b975,TRUE,_32,_0) (Same Type. No op)
    val x1731 = OpDef(op=FixSla, inputs=List(x1730, Const(2))).name("x1731").ctrl(x1738).srcCtx("OuterProduct.scala:38:14") // FixLsh(x1730,Const(2))
    val x1732 = x1731 // FixConvert(x1731,TRUE,_64,_0)
    val x1733 = DramAddress(x1689).name("x1733").ctrl(x1738).srcCtx("OuterProduct.scala:38:14") // GetDRAMAddress(x1689)
    val x1735_x1734 = OpDef(op=FixAdd, inputs=List(x1732, x1733)).name("x1735_x1734").ctrl(x1738).srcCtx("OuterProduct.scala:38:14") // FixAdd(x1732,x1733)
    // x1735 = SimpleStruct(ArrayBuffer((offset,x1734), (size,Const(64)), (isLoad,Const(true))))
    val x1736 = OpDef(op=BitAnd, inputs=List(b976, b977)).name("x1736").ctrl(x1738).srcCtx("UnrollingBase.scala:28:66") // And(b976,b977)
    val x1737_b1825_b1823 = WriteMem(b1823, x1735_x1734).name("x1737_b1825_b1823").ctrl(x1738).srcCtx("OuterProduct.scala:38:14") // StreamWrite(x1728,x1735,x1736)
    val x1737_b1826_b1824 = WriteMem(b1824, Const(64)).name("x1737_b1826_b1824").ctrl(x1738).srcCtx("OuterProduct.scala:38:14") // StreamWrite(x1728,x1735,x1736)
    val x1739 = FringeDenseLoad(dram=List(x1689), cmdStream=List(b1823, b1824), dataStream=List(x1729)).name("x1739").ctrl(x1748).srcCtx("OuterProduct.scala:38:14") // FringeDenseLoad(x1689,x1728,x1729)
    val x1740 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x1740").ctrl(x1748).srcCtx("OuterProduct.scala:38:14") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1741 = CounterChain(List(x1740)).name("x1741").ctrl(x1748).srcCtx("OuterProduct.scala:38:14") // CounterChainNew(List(x1740))
    val x1747 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1741).name("x1747").ctrl(x1748).srcCtx("OuterProduct.scala:38:14") // UnrolledForeach(List(b976, b977),x1741,Block(Const(())),List(List(b1022)),List(List(b1023)))
    val b1022 = CounterIter(x1740, None).name("b1022").ctrl(x1747) // b1022
    val b1023 = Const(true).name("b1023").ctrl(x1747) // b1023
    val x1742 = OpDef(op=BitAnd, inputs=List(b1023, b976)).name("x1742").ctrl(x1747).srcCtx("UnrollingBase.scala:28:66") // And(b1023,b976)
    val x1743 = OpDef(op=BitAnd, inputs=List(x1742, b977)).name("x1743").ctrl(x1747).srcCtx("UnrollingBase.scala:28:66") // And(x1742,b977)
    val x1744_x1744 = ReadMem(x1729).name("x1744_x1744").ctrl(x1747).srcCtx("OuterProduct.scala:38:14") // ParStreamRead(x1729,List(x1743))
    val x1745_x1745 = x1744_x1744 // x1745 = VectorApply(x1744,0)
    val x1746 = StoreBanks(List(x1701_d0_b0), List(b1022), x1745_x1745).name("x1746").ctrl(x1747).srcCtx("OuterProduct.scala:38:14") // ParSRAMStore(x1701,List(List(b1022)),List(x1745),List(x1743))
    val x1750 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x1750").ctrl(x1801).srcCtx("OuterProduct.scala:40:31") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1751 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x1751").ctrl(x1801).srcCtx("OuterProduct.scala:40:21") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x1752 = CounterChain(List(x1751,x1750)).name("x1752").ctrl(x1801).srcCtx("OuterProduct.scala:40:38") // CounterChainNew(List(x1751, x1750))
    val x1761 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1752).name("x1761").ctrl(x1801).srcCtx("OuterProduct.scala:40:38") // UnrolledForeach(List(b976, b977),x1752,Block(Const(())),List(List(b1035), List(b1036)),List(List(b1037), List(b1038)))
    val b1035 = CounterIter(x1751, Some(0)).name("b1035").ctrl(x1761) // b1035
    val b1037 = Const(true).name("b1037").ctrl(x1761) // b1037
    val b1036 = CounterIter(x1750, None).name("b1036").ctrl(x1761) // b1036
    val b1038 = Const(true).name("b1038").ctrl(x1761) // b1038
    val x1753 = OpDef(op=BitAnd, inputs=List(b1037, b1038)).name("x1753").ctrl(x1761).srcCtx("UnrollingBase.scala:28:66") // And(b1037,b1038)
    val x1754 = OpDef(op=BitAnd, inputs=List(b976, b977)).name("x1754").ctrl(x1761).srcCtx("UnrollingBase.scala:28:66") // And(b976,b977)
    val x1755 = OpDef(op=BitAnd, inputs=List(x1753, x1754)).name("x1755").ctrl(x1761).srcCtx("UnrollingBase.scala:28:66") // And(x1753,x1754)
    val x1756 = LoadBanks(List(x1700_d0_b0), List(b1035)).name("x1756").ctrl(x1761).srcCtx("OuterProduct.scala:40:71") // SRAMLoad(x1700,ArrayBuffer(Const(32)),List(b1035),Const(0),x1755)
    val x1757 = LoadBanks(List(x1701_d0_b0), List(b1036)).name("x1757").ctrl(x1761).srcCtx("OuterProduct.scala:40:80") // ParSRAMLoad(x1701,List(List(b1036)),List(x1755))
    val x1758 = x1757 // x1758 = VectorApply(x1757,0)
    val x1759 = OpDef(op=FixMul, inputs=List(x1756, x1758)).name("x1759").ctrl(x1761).srcCtx("OuterProduct.scala:40:76") // FixMul(x1756,x1758)
    val x1760 = StoreBanks(List(x1702_d0_b0), List(b1035, b1036), x1759).name("x1760").ctrl(x1761).srcCtx("OuterProduct.scala:40:67") // ParSRAMStore(x1702,List(List(b1035, b1036)),List(x1759),List(x1755))
    val x1764 = UnitController(style=SeqPipe, level=InnerControl).name("x1764").ctrl(x1801).srcCtx("OuterProduct.scala:32:58") // UnitPipe(List(b976, b977),Block(Const(())))
    val x1762 = OpDef(op=FixAdd, inputs=List(b974, Const(32))).name("x1762").ctrl(x1764).srcCtx("OuterProduct.scala:41:17") // FixAdd(b974,Const(32))
    val x1763 = OpDef(op=FixAdd, inputs=List(b975, Const(16))).name("x1763").ctrl(x1764).srcCtx("OuterProduct.scala:41:33") // FixAdd(b975,Const(16))
    val x1765 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x1765").ctrl(x1801).srcCtx("OuterProduct.scala:41:46") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x1766 = CounterChain(List(x1765)).name("x1766").ctrl(x1801).srcCtx("OuterProduct.scala:41:46") // CounterChainNew(List(x1765))
    val x1800 = LoopController(style=StreamPipe, level=OuterControl, cchain=x1766).name("x1800").ctrl(x1801).srcCtx("OuterProduct.scala:41:46") // UnrolledForeach(List(b976, b977),x1766,Block(Const(())),List(List(b1053)),List(List(b1054)))
    val b1053 = CounterIter(x1765, Some(0)).name("b1053").ctrl(x1800) // b1053
    val b1054 = Const(true).name("b1054").ctrl(x1800) // b1054
    val b1827 = StreamOut(field="offset").name("b1827").ctrl(x1800).srcCtx("OuterProduct.scala:41:46") // x1767 = StreamOutNew(BurstCmdBus)
    isAccum(b1827) = false
    bufferDepthOf(b1827) = 1
    val b1828 = StreamOut(field="size").name("b1828").ctrl(x1800).srcCtx("OuterProduct.scala:41:46") // x1767 = StreamOutNew(BurstCmdBus)
    isAccum(b1828) = false
    bufferDepthOf(b1828) = 1
    val x1768 = StreamOut(field="data").name("x1768").ctrl(x1800).srcCtx("OuterProduct.scala:41:46") // x1768 = StreamOutNew(BurstFullDataBus())
    isAccum(x1768) = false
    bufferDepthOf(x1768) = 1
    val x1769 = StreamIn(field="ack").name("x1769").ctrl(x1800).srcCtx("OuterProduct.scala:41:46") // x1769 = StreamInNew(BurstAckBus)
    isAccum(x1769) = false
    bufferDepthOf(x1769) = 1
    val x1784 = UnitController(style=SeqPipe, level=InnerControl).name("x1784").ctrl(x1800).srcCtx("OuterProduct.scala:41:46") // UnitPipe(List(b1054, b976, b977),Block(x1783))
    val x1770 = OpDef(op=FixAdd, inputs=List(b974, b1053)).name("x1770").ctrl(x1784).srcCtx("OuterProduct.scala:41:46") // FixAdd(b974,b1053)
    val x1771 = x1770 // FixConvert(x1770,TRUE,_32,_0) (Same Type. No op)
    val x1772 = ReadMem(x1683_d0).name("x1772").ctrl(x1784).srcCtx("OuterProduct.scala:26:30") // RegRead(x1683)
    val x1773 = OpDef(op=FixMul, inputs=List(x1771, x1772)).name("x1773").ctrl(x1784).srcCtx("OuterProduct.scala:41:46") // FixMul(x1771,x1772)
    val x1774 = b975 // FixConvert(b975,TRUE,_32,_0) (Same Type. No op)
    val x1775 = OpDef(op=FixAdd, inputs=List(x1773, x1774)).name("x1775").ctrl(x1784).srcCtx("OuterProduct.scala:41:46") // FixAdd(x1773,x1774)
    val x1776 = OpDef(op=FixSla, inputs=List(x1775, Const(2))).name("x1776").ctrl(x1784).srcCtx("OuterProduct.scala:41:46") // FixLsh(x1775,Const(2))
    val x1777 = x1776 // FixConvert(x1776,TRUE,_64,_0)
    val x1778 = DramAddress(x1692).name("x1778").ctrl(x1784).srcCtx("OuterProduct.scala:41:46") // GetDRAMAddress(x1692)
    val x1780_x1779 = OpDef(op=FixAdd, inputs=List(x1777, x1778)).name("x1780_x1779").ctrl(x1784).srcCtx("OuterProduct.scala:41:46") // FixAdd(x1777,x1778)
    // x1780 = SimpleStruct(ArrayBuffer((offset,x1779), (size,Const(64)), (isLoad,Const(false))))
    val x1781 = OpDef(op=BitAnd, inputs=List(b1054, b976)).name("x1781").ctrl(x1784).srcCtx("UnrollingBase.scala:28:66") // And(b1054,b976)
    val x1782 = OpDef(op=BitAnd, inputs=List(x1781, b977)).name("x1782").ctrl(x1784).srcCtx("UnrollingBase.scala:28:66") // And(x1781,b977)
    val x1783_b1829_b1827 = WriteMem(b1827, x1780_x1779).name("x1783_b1829_b1827").ctrl(x1784).srcCtx("OuterProduct.scala:41:46") // StreamWrite(x1767,x1780,x1782)
    val x1783_b1830_b1828 = WriteMem(b1828, Const(64)).name("x1783_b1830_b1828").ctrl(x1784).srcCtx("OuterProduct.scala:41:46") // StreamWrite(x1767,x1780,x1782)
    val x1785 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x1785").ctrl(x1800).srcCtx("OuterProduct.scala:41:46") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1786 = CounterChain(List(x1785)).name("x1786").ctrl(x1800).srcCtx("OuterProduct.scala:41:46") // CounterChainNew(List(x1785))
    val x1794 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1786).name("x1794").ctrl(x1800).srcCtx("OuterProduct.scala:41:46") // UnrolledForeach(List(b1054, b976, b977),x1786,Block(Const(())),List(List(b1075)),List(List(b1076)))
    val b1075 = CounterIter(x1785, None).name("b1075").ctrl(x1794) // b1075
    val b1076 = Const(true).name("b1076").ctrl(x1794) // b1076
    val x1787 = OpDef(op=BitAnd, inputs=List(b1076, b1054)).name("x1787").ctrl(x1794).srcCtx("UnrollingBase.scala:28:66") // And(b1076,b1054)
    val x1788 = OpDef(op=BitAnd, inputs=List(b976, b977)).name("x1788").ctrl(x1794).srcCtx("UnrollingBase.scala:28:66") // And(b976,b977)
    val x1789 = OpDef(op=BitAnd, inputs=List(x1787, x1788)).name("x1789").ctrl(x1794).srcCtx("UnrollingBase.scala:28:66") // And(x1787,x1788)
    val x1790 = LoadBanks(List(x1702_d0_b0), List(b1053, b1075)).name("x1790").ctrl(x1794).srcCtx("OuterProduct.scala:41:46") // ParSRAMLoad(x1702,List(List(b1053, b1075)),List(x1789))
    val x1792_x1791 = x1790 // x1791 = VectorApply(x1790,0)
    // x1792 = SimpleStruct(ArrayBuffer((_1,x1791), (_2,Const(true))))
    val x1793_x1793_x1768 = WriteMem(x1768, x1792_x1791).name("x1793_x1793_x1768").ctrl(x1794).srcCtx("OuterProduct.scala:41:46") // ParStreamWrite(x1768,List(x1792),List(x1789))
    val x1795 = FringeDenseStore(dram=List(x1692), cmdStream=List(b1827, b1828), dataStream=List(x1768), ackStream=List(x1769)).name("x1795").ctrl(x1800).srcCtx("OuterProduct.scala:41:46") // FringeDenseStore(x1692,x1767,x1768,x1769)
    val x1799 = UnitController(style=SeqPipe, level=InnerControl).name("x1799").ctrl(x1800).srcCtx("OuterProduct.scala:41:46") // UnitPipe(List(b1054, b976, b977),Block(Const(())))
    val x1796 = OpDef(op=BitAnd, inputs=List(b1054, b976)).name("x1796").ctrl(x1799).srcCtx("UnrollingBase.scala:28:66") // And(b1054,b976)
    val x1797 = OpDef(op=BitAnd, inputs=List(x1796, b977)).name("x1797").ctrl(x1799).srcCtx("UnrollingBase.scala:28:66") // And(x1796,b977)
    val x1798_x1798 = ReadMem(x1769).name("x1798_x1798").ctrl(x1799).srcCtx("OuterProduct.scala:41:46") // StreamRead(x1769,x1797)
    
  }
}
