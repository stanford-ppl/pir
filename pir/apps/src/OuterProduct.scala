import pir._
import pir.node._
import arch._
import prism.enums._

object OuterProduct extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1666 = ArgIn(init=0).name("x1666").ctrl(top).srcCtx("OuterProduct.scala:21:22:sizeA") // ArgInNew(Const(0))
    isAccum(x1666) = false
    bufferDepthOf(x1666) = 1
    boundOf(x1666) = 1024
    val x1667_d0 = ArgIn(init=0).name("x1667_d0").ctrl(top).srcCtx("OuterProduct.scala:22:22:sizeB") // ArgInNew(Const(0))
    isAccum(x1667_d0) = false
    bufferDepthOf(x1667_d0) = 1
    boundOf(x1667_d0) = 1024
    val x1670 = ReadMem(x1666).name("x1670").ctrl(top).srcCtx("OuterProduct.scala:26:24") // RegRead(x1666)
    val x1671 = DRAM(dims=List(x1670)).name("x1671").ctrl(top).srcCtx("OuterProduct.scala:26:23:vec1") // x1671 = DRAMNew(ArrayBuffer(x1670),Const(0))
    val x1672 = ReadMem(x1667_d0).name("x1672").ctrl(top).srcCtx("OuterProduct.scala:27:24") // RegRead(x1667)
    val x1673 = DRAM(dims=List(x1672)).name("x1673").ctrl(top).srcCtx("OuterProduct.scala:27:23:vec2") // x1673 = DRAMNew(ArrayBuffer(x1672),Const(0))
    val x1674 = ReadMem(x1667_d0).name("x1674").ctrl(top).srcCtx("OuterProduct.scala:28:30") // RegRead(x1667)
    val x1675 = ReadMem(x1666).name("x1675").ctrl(top).srcCtx("OuterProduct.scala:28:23") // RegRead(x1666)
    val x1676 = DRAM(dims=List(x1675, x1674)).name("x1676").ctrl(top).srcCtx("OuterProduct.scala:28:22:out") // x1676 = DRAMNew(ArrayBuffer(x1675, x1674),Const(0))
    val x1784 = UnitController(style=SeqPipe, level=OuterControl).name("x1784").ctrl(top).srcCtx("OuterProduct.scala:33:11") // Hwblock(Block(Const(())),false)
    val x1679 = ReadMem(x1666).name("x1679").ctrl(x1784).srcCtx("OuterProduct.scala:34:15") // RegRead(x1666)
    val x1680 = Counter(min=Const(0), max=x1679, step=Const(32), par=1).name("x1680").ctrl(x1784).srcCtx("OuterProduct.scala:34:28") // CounterNew(Const(0),x1679,Const(32),Const(1))
    val x1681 = CounterChain(List(x1680)).name("x1681").ctrl(x1784).srcCtx("OuterProduct.scala:34:36") // CounterChainNew(List(x1680))
    val x1783 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1681).name("x1783").ctrl(x1784).srcCtx("OuterProduct.scala:34:36") // UnrolledForeach(List(Const(true)),x1681,Block(Const(())),List(List(b966)),List(List(b967)))
    val b966 = CounterIter(x1680, Some(0)).name("b966").ctrl(x1783) // b966
    val b967 = Const(true).name("b967").ctrl(x1783) // b967
    val x1682_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1682_d0_b0").ctrl(x1783).srcCtx("OuterProduct.scala:35:25:b1") // x1682 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1682_d0_b0) = false
    bufferDepthOf(x1682_d0_b0) = 2
    val x1684 = UnitController(style=SeqPipe, level=InnerControl).name("x1684").ctrl(x1783).srcCtx("OuterProduct.scala:34:36") // UnitPipe(List(b967),Block(Const(())))
    val x1683 = OpDef(op=FixAdd, inputs=List(b966, Const(32))).name("x1683").ctrl(x1684).srcCtx("OuterProduct.scala:36:26") // FixAdd(b966,Const(32))
    val x1703 = UnitController(style=StreamPipe, level=OuterControl).name("x1703").ctrl(x1783).srcCtx("OuterProduct.scala:36:12") // UnitPipe(List(b967),Block(Const(())))
    val b1801 = StreamOut(field="offset").name("b1801").ctrl(x1703).srcCtx("OuterProduct.scala:36:12") // x1685 = StreamOutNew(BurstCmdBus)
    isAccum(b1801) = false
    bufferDepthOf(b1801) = 1
    val b1802 = StreamOut(field="size").name("b1802").ctrl(x1703).srcCtx("OuterProduct.scala:36:12") // x1685 = StreamOutNew(BurstCmdBus)
    isAccum(b1802) = false
    bufferDepthOf(b1802) = 1
    val x1686 = StreamIn(field="data").name("x1686").ctrl(x1703).srcCtx("OuterProduct.scala:36:12") // x1686 = StreamInNew(BurstDataBus())
    isAccum(x1686) = false
    bufferDepthOf(x1686) = 1
    val x1694 = UnitController(style=SeqPipe, level=InnerControl).name("x1694").ctrl(x1703).srcCtx("OuterProduct.scala:36:12") // UnitPipe(List(b967),Block(x1693))
    val x1687 = b966 // FixConvert(b966,TRUE,_32,_0) (Same Type. No op)
    val x1688 = OpDef(op=FixSla, inputs=List(x1687, Const(2))).name("x1688").ctrl(x1694).srcCtx("OuterProduct.scala:36:12") // FixLsh(x1687,Const(2))
    val x1689 = x1688 // FixConvert(x1688,TRUE,_64,_0)
    val x1690 = DramAddress(x1671).name("x1690").ctrl(x1694).srcCtx("OuterProduct.scala:36:12") // GetDRAMAddress(x1671)
    val x1692_x1691 = OpDef(op=FixAdd, inputs=List(x1689, x1690)).name("x1692_x1691").ctrl(x1694).srcCtx("OuterProduct.scala:36:12") // FixAdd(x1689,x1690)
    // x1692 = SimpleStruct(ArrayBuffer((offset,x1691), (size,Const(128)), (isLoad,Const(true))))
    val x1693_b1803_b1801 = WriteMem(b1801, x1692_x1691).name("x1693_b1803_b1801").ctrl(x1694).srcCtx("OuterProduct.scala:36:12") // StreamWrite(x1685,x1692,b967)
    val x1693_b1804_b1802 = WriteMem(b1802, Const(128)).name("x1693_b1804_b1802").ctrl(x1694).srcCtx("OuterProduct.scala:36:12") // StreamWrite(x1685,x1692,b967)
    val x1695 = FringeDenseLoad(dram=List(x1671), cmdStream=List(b1801, b1802), dataStream=List(x1686)).name("x1695").ctrl(x1703).srcCtx("OuterProduct.scala:36:12") // FringeDenseLoad(x1671,x1685,x1686)
    val x1696 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1696").ctrl(x1703).srcCtx("OuterProduct.scala:36:12") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1697 = CounterChain(List(x1696)).name("x1697").ctrl(x1703).srcCtx("OuterProduct.scala:36:12") // CounterChainNew(List(x1696))
    val x1702 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1697).name("x1702").ctrl(x1703).srcCtx("OuterProduct.scala:36:12") // UnrolledForeach(List(b967),x1697,Block(Const(())),List(List(b984)),List(List(b985)))
    val b984 = CounterIter(x1696, None).name("b984").ctrl(x1702) // b984
    val b985 = Const(true).name("b985").ctrl(x1702) // b985
    val x1698 = OpDef(op=BitAnd, inputs=List(b985, b967)).name("x1698").ctrl(x1702).srcCtx("UnrollingBase.scala:28:66") // And(b985,b967)
    val x1699_x1699 = ReadMem(x1686).name("x1699_x1699").ctrl(x1702).srcCtx("OuterProduct.scala:36:12") // ParStreamRead(x1686,List(x1698))
    val x1700_x1700 = x1699_x1699 // x1700 = VectorApply(x1699,0)
    val x1701 = StoreBanks(List(x1682_d0_b0), List(b984), x1700_x1700).name("x1701").ctrl(x1702).srcCtx("OuterProduct.scala:36:12") // ParSRAMStore(x1682,List(List(b984)),List(x1700),List(x1698))
    val x1704 = ReadMem(x1667_d0).name("x1704").ctrl(x1783).srcCtx("OuterProduct.scala:37:17") // RegRead(x1667)
    val x1705 = Counter(min=Const(0), max=x1704, step=Const(32), par=1).name("x1705").ctrl(x1783).srcCtx("OuterProduct.scala:37:30") // CounterNew(Const(0),x1704,Const(32),Const(1))
    val x1706 = CounterChain(List(x1705)).name("x1706").ctrl(x1783).srcCtx("OuterProduct.scala:37:39") // CounterChainNew(List(x1705))
    val x1782 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1706).name("x1782").ctrl(x1783).srcCtx("OuterProduct.scala:37:39") // UnrolledForeach(List(b967),x1706,Block(Const(())),List(List(b995)),List(List(b996)))
    val b995 = CounterIter(x1705, Some(0)).name("b995").ctrl(x1782) // b995
    val b996 = Const(true).name("b996").ctrl(x1782) // b996
    val x1707_d0_b0 = SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1707_d0_b0").ctrl(x1782).srcCtx("OuterProduct.scala:38:27:b2") // x1707 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1707_d0_b0) = false
    bufferDepthOf(x1707_d0_b0) = 2
    val x1709 = UnitController(style=SeqPipe, level=InnerControl).name("x1709").ctrl(x1782).srcCtx("OuterProduct.scala:37:39") // UnitPipe(List(b996, b967),Block(Const(())))
    val x1708 = OpDef(op=FixAdd, inputs=List(b995, Const(32))).name("x1708").ctrl(x1709).srcCtx("OuterProduct.scala:39:28") // FixAdd(b995,Const(32))
    val x1730 = UnitController(style=StreamPipe, level=OuterControl).name("x1730").ctrl(x1782).srcCtx("OuterProduct.scala:39:14") // UnitPipe(List(b996, b967),Block(Const(())))
    val b1805 = StreamOut(field="offset").name("b1805").ctrl(x1730).srcCtx("OuterProduct.scala:39:14") // x1710 = StreamOutNew(BurstCmdBus)
    isAccum(b1805) = false
    bufferDepthOf(b1805) = 1
    val b1806 = StreamOut(field="size").name("b1806").ctrl(x1730).srcCtx("OuterProduct.scala:39:14") // x1710 = StreamOutNew(BurstCmdBus)
    isAccum(b1806) = false
    bufferDepthOf(b1806) = 1
    val x1711 = StreamIn(field="data").name("x1711").ctrl(x1730).srcCtx("OuterProduct.scala:39:14") // x1711 = StreamInNew(BurstDataBus())
    isAccum(x1711) = false
    bufferDepthOf(x1711) = 1
    val x1720 = UnitController(style=SeqPipe, level=InnerControl).name("x1720").ctrl(x1730).srcCtx("OuterProduct.scala:39:14") // UnitPipe(List(b996, b967),Block(x1719))
    val x1712 = b995 // FixConvert(b995,TRUE,_32,_0) (Same Type. No op)
    val x1713 = OpDef(op=FixSla, inputs=List(x1712, Const(2))).name("x1713").ctrl(x1720).srcCtx("OuterProduct.scala:39:14") // FixLsh(x1712,Const(2))
    val x1714 = x1713 // FixConvert(x1713,TRUE,_64,_0)
    val x1715 = DramAddress(x1673).name("x1715").ctrl(x1720).srcCtx("OuterProduct.scala:39:14") // GetDRAMAddress(x1673)
    val x1717_x1716 = OpDef(op=FixAdd, inputs=List(x1714, x1715)).name("x1717_x1716").ctrl(x1720).srcCtx("OuterProduct.scala:39:14") // FixAdd(x1714,x1715)
    // x1717 = SimpleStruct(ArrayBuffer((offset,x1716), (size,Const(128)), (isLoad,Const(true))))
    val x1718 = OpDef(op=BitAnd, inputs=List(b996, b967)).name("x1718").ctrl(x1720).srcCtx("UnrollingBase.scala:28:66") // And(b996,b967)
    val x1719_b1807_b1805 = WriteMem(b1805, x1717_x1716).name("x1719_b1807_b1805").ctrl(x1720).srcCtx("OuterProduct.scala:39:14") // StreamWrite(x1710,x1717,x1718)
    val x1719_b1808_b1806 = WriteMem(b1806, Const(128)).name("x1719_b1808_b1806").ctrl(x1720).srcCtx("OuterProduct.scala:39:14") // StreamWrite(x1710,x1717,x1718)
    val x1721 = FringeDenseLoad(dram=List(x1673), cmdStream=List(b1805, b1806), dataStream=List(x1711)).name("x1721").ctrl(x1730).srcCtx("OuterProduct.scala:39:14") // FringeDenseLoad(x1673,x1710,x1711)
    val x1722 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1722").ctrl(x1730).srcCtx("OuterProduct.scala:39:14") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1723 = CounterChain(List(x1722)).name("x1723").ctrl(x1730).srcCtx("OuterProduct.scala:39:14") // CounterChainNew(List(x1722))
    val x1729 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1723).name("x1729").ctrl(x1730).srcCtx("OuterProduct.scala:39:14") // UnrolledForeach(List(b996, b967),x1723,Block(Const(())),List(List(b1014)),List(List(b1015)))
    val b1014 = CounterIter(x1722, None).name("b1014").ctrl(x1729) // b1014
    val b1015 = Const(true).name("b1015").ctrl(x1729) // b1015
    val x1724 = OpDef(op=BitAnd, inputs=List(b1015, b996)).name("x1724").ctrl(x1729).srcCtx("UnrollingBase.scala:28:66") // And(b1015,b996)
    val x1725 = OpDef(op=BitAnd, inputs=List(x1724, b967)).name("x1725").ctrl(x1729).srcCtx("UnrollingBase.scala:28:66") // And(x1724,b967)
    val x1726_x1726 = ReadMem(x1711).name("x1726_x1726").ctrl(x1729).srcCtx("OuterProduct.scala:39:14") // ParStreamRead(x1711,List(x1725))
    val x1727_x1727 = x1726_x1726 // x1727 = VectorApply(x1726,0)
    val x1728 = StoreBanks(List(x1707_d0_b0), List(b1014), x1727_x1727).name("x1728").ctrl(x1729).srcCtx("OuterProduct.scala:39:14") // ParSRAMStore(x1707,List(List(b1014)),List(x1727),List(x1725))
    val x1731_d0_b0 = SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x1731_d0_b0").ctrl(x1782).srcCtx("OuterProduct.scala:40:32:outTile") // x1731 = SRAMNew(ArrayBuffer(Const(32), Const(32)))
    isAccum(x1731_d0_b0) = false
    bufferDepthOf(x1731_d0_b0) = 3
    val x1732 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1732").ctrl(x1782).srcCtx("OuterProduct.scala:41:36") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1733 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x1733").ctrl(x1782).srcCtx("OuterProduct.scala:41:23") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x1734 = CounterChain(List(x1733,x1732)).name("x1734").ctrl(x1782).srcCtx("OuterProduct.scala:41:44") // CounterChainNew(List(x1733, x1732))
    val x1743 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1734).name("x1743").ctrl(x1782).srcCtx("OuterProduct.scala:41:44") // UnrolledForeach(List(b996, b967),x1734,Block(Const(())),List(List(b1027), List(b1028)),List(List(b1029), List(b1030)))
    val b1027 = CounterIter(x1733, Some(0)).name("b1027").ctrl(x1743) // b1027
    val b1029 = Const(true).name("b1029").ctrl(x1743) // b1029
    val b1028 = CounterIter(x1732, None).name("b1028").ctrl(x1743) // b1028
    val b1030 = Const(true).name("b1030").ctrl(x1743) // b1030
    val x1735 = OpDef(op=BitAnd, inputs=List(b1029, b1030)).name("x1735").ctrl(x1743).srcCtx("UnrollingBase.scala:28:66") // And(b1029,b1030)
    val x1736 = OpDef(op=BitAnd, inputs=List(b996, b967)).name("x1736").ctrl(x1743).srcCtx("UnrollingBase.scala:28:66") // And(b996,b967)
    val x1737 = OpDef(op=BitAnd, inputs=List(x1735, x1736)).name("x1737").ctrl(x1743).srcCtx("UnrollingBase.scala:28:66") // And(x1735,x1736)
    val x1738 = LoadBanks(List(x1682_d0_b0), List(b1027)).name("x1738").ctrl(x1743).srcCtx("OuterProduct.scala:41:77") // SRAMLoad(x1682,ArrayBuffer(Const(32)),List(b1027),Const(0),x1737)
    val x1739 = LoadBanks(List(x1707_d0_b0), List(b1028)).name("x1739").ctrl(x1743).srcCtx("OuterProduct.scala:41:86") // ParSRAMLoad(x1707,List(List(b1028)),List(x1737))
    val x1740 = x1739 // x1740 = VectorApply(x1739,0)
    val x1741 = OpDef(op=FixMul, inputs=List(x1738, x1740)).name("x1741").ctrl(x1743).srcCtx("OuterProduct.scala:41:82") // FixMul(x1738,x1740)
    val x1742 = StoreBanks(List(x1731_d0_b0), List(b1027, b1028), x1741).name("x1742").ctrl(x1743).srcCtx("OuterProduct.scala:41:73") // ParSRAMStore(x1731,List(List(b1027, b1028)),List(x1741),List(x1737))
    val x1745 = UnitController(style=SeqPipe, level=InnerControl).name("x1745").ctrl(x1782).srcCtx("OuterProduct.scala:37:39") // UnitPipe(List(b996, b967),Block(Const(())))
    val x1744 = OpDef(op=FixAdd, inputs=List(b966, Const(32))).name("x1744").ctrl(x1745).srcCtx("OuterProduct.scala:42:19") // FixAdd(b966,Const(32))
    val x1746 = Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x1746").ctrl(x1782).srcCtx("OuterProduct.scala:42:43") // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x1747 = CounterChain(List(x1746)).name("x1747").ctrl(x1782).srcCtx("OuterProduct.scala:42:43") // CounterChainNew(List(x1746))
    val x1781 = LoopController(style=StreamPipe, level=OuterControl, cchain=x1747).name("x1781").ctrl(x1782).srcCtx("OuterProduct.scala:42:43") // UnrolledForeach(List(b996, b967),x1747,Block(Const(())),List(List(b1044)),List(List(b1045)))
    val b1044 = CounterIter(x1746, Some(0)).name("b1044").ctrl(x1781) // b1044
    val b1045 = Const(true).name("b1045").ctrl(x1781) // b1045
    val b1809 = StreamOut(field="offset").name("b1809").ctrl(x1781).srcCtx("OuterProduct.scala:42:43") // x1748 = StreamOutNew(BurstCmdBus)
    isAccum(b1809) = false
    bufferDepthOf(b1809) = 1
    val b1810 = StreamOut(field="size").name("b1810").ctrl(x1781).srcCtx("OuterProduct.scala:42:43") // x1748 = StreamOutNew(BurstCmdBus)
    isAccum(b1810) = false
    bufferDepthOf(b1810) = 1
    val x1749 = StreamOut(field="data").name("x1749").ctrl(x1781).srcCtx("OuterProduct.scala:42:43") // x1749 = StreamOutNew(BurstFullDataBus())
    isAccum(x1749) = false
    bufferDepthOf(x1749) = 1
    val x1750 = StreamIn(field="ack").name("x1750").ctrl(x1781).srcCtx("OuterProduct.scala:42:43") // x1750 = StreamInNew(BurstAckBus)
    isAccum(x1750) = false
    bufferDepthOf(x1750) = 1
    val x1765 = UnitController(style=SeqPipe, level=InnerControl).name("x1765").ctrl(x1781).srcCtx("OuterProduct.scala:42:43") // UnitPipe(List(b1045, b996, b967),Block(x1764))
    val x1751 = OpDef(op=FixAdd, inputs=List(b966, b1044)).name("x1751").ctrl(x1765).srcCtx("OuterProduct.scala:42:43") // FixAdd(b966,b1044)
    val x1752 = x1751 // FixConvert(x1751,TRUE,_32,_0) (Same Type. No op)
    val x1753 = ReadMem(x1667_d0).name("x1753").ctrl(x1765).srcCtx("OuterProduct.scala:28:30") // RegRead(x1667)
    val x1754 = OpDef(op=FixMul, inputs=List(x1752, x1753)).name("x1754").ctrl(x1765).srcCtx("OuterProduct.scala:42:43") // FixMul(x1752,x1753)
    val x1755 = b995 // FixConvert(b995,TRUE,_32,_0) (Same Type. No op)
    val x1756 = OpDef(op=FixAdd, inputs=List(x1754, x1755)).name("x1756").ctrl(x1765).srcCtx("OuterProduct.scala:42:43") // FixAdd(x1754,x1755)
    val x1757 = OpDef(op=FixSla, inputs=List(x1756, Const(2))).name("x1757").ctrl(x1765).srcCtx("OuterProduct.scala:42:43") // FixLsh(x1756,Const(2))
    val x1758 = x1757 // FixConvert(x1757,TRUE,_64,_0)
    val x1759 = DramAddress(x1676).name("x1759").ctrl(x1765).srcCtx("OuterProduct.scala:42:43") // GetDRAMAddress(x1676)
    val x1761_x1760 = OpDef(op=FixAdd, inputs=List(x1758, x1759)).name("x1761_x1760").ctrl(x1765).srcCtx("OuterProduct.scala:42:43") // FixAdd(x1758,x1759)
    // x1761 = SimpleStruct(ArrayBuffer((offset,x1760), (size,Const(128)), (isLoad,Const(false))))
    val x1762 = OpDef(op=BitAnd, inputs=List(b1045, b996)).name("x1762").ctrl(x1765).srcCtx("UnrollingBase.scala:28:66") // And(b1045,b996)
    val x1763 = OpDef(op=BitAnd, inputs=List(x1762, b967)).name("x1763").ctrl(x1765).srcCtx("UnrollingBase.scala:28:66") // And(x1762,b967)
    val x1764_b1811_b1809 = WriteMem(b1809, x1761_x1760).name("x1764_b1811_b1809").ctrl(x1765).srcCtx("OuterProduct.scala:42:43") // StreamWrite(x1748,x1761,x1763)
    val x1764_b1812_b1810 = WriteMem(b1810, Const(128)).name("x1764_b1812_b1810").ctrl(x1765).srcCtx("OuterProduct.scala:42:43") // StreamWrite(x1748,x1761,x1763)
    val x1766 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1766").ctrl(x1781).srcCtx("OuterProduct.scala:42:43") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1767 = CounterChain(List(x1766)).name("x1767").ctrl(x1781).srcCtx("OuterProduct.scala:42:43") // CounterChainNew(List(x1766))
    val x1775 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1767).name("x1775").ctrl(x1781).srcCtx("OuterProduct.scala:42:43") // UnrolledForeach(List(b1045, b996, b967),x1767,Block(Const(())),List(List(b1066)),List(List(b1067)))
    val b1066 = CounterIter(x1766, None).name("b1066").ctrl(x1775) // b1066
    val b1067 = Const(true).name("b1067").ctrl(x1775) // b1067
    val x1768 = OpDef(op=BitAnd, inputs=List(b1067, b1045)).name("x1768").ctrl(x1775).srcCtx("UnrollingBase.scala:28:66") // And(b1067,b1045)
    val x1769 = OpDef(op=BitAnd, inputs=List(b996, b967)).name("x1769").ctrl(x1775).srcCtx("UnrollingBase.scala:28:66") // And(b996,b967)
    val x1770 = OpDef(op=BitAnd, inputs=List(x1768, x1769)).name("x1770").ctrl(x1775).srcCtx("UnrollingBase.scala:28:66") // And(x1768,x1769)
    val x1771 = LoadBanks(List(x1731_d0_b0), List(b1044, b1066)).name("x1771").ctrl(x1775).srcCtx("OuterProduct.scala:42:43") // ParSRAMLoad(x1731,List(List(b1044, b1066)),List(x1770))
    val x1773_x1772 = x1771 // x1772 = VectorApply(x1771,0)
    // x1773 = SimpleStruct(ArrayBuffer((_1,x1772), (_2,Const(true))))
    val x1774_x1774_x1749 = WriteMem(x1749, x1773_x1772).name("x1774_x1774_x1749").ctrl(x1775).srcCtx("OuterProduct.scala:42:43") // ParStreamWrite(x1749,List(x1773),List(x1770))
    val x1776 = FringeDenseStore(dram=List(x1676), cmdStream=List(b1809, b1810), dataStream=List(x1749), ackStream=List(x1750)).name("x1776").ctrl(x1781).srcCtx("OuterProduct.scala:42:43") // FringeDenseStore(x1676,x1748,x1749,x1750)
    val x1780 = UnitController(style=SeqPipe, level=InnerControl).name("x1780").ctrl(x1781).srcCtx("OuterProduct.scala:42:43") // UnitPipe(List(b1045, b996, b967),Block(Const(())))
    val x1777 = OpDef(op=BitAnd, inputs=List(b1045, b996)).name("x1777").ctrl(x1780).srcCtx("UnrollingBase.scala:28:66") // And(b1045,b996)
    val x1778 = OpDef(op=BitAnd, inputs=List(x1777, b967)).name("x1778").ctrl(x1780).srcCtx("UnrollingBase.scala:28:66") // And(x1777,b967)
    val x1779_x1779 = ReadMem(x1750).name("x1779_x1779").ctrl(x1780).srcCtx("OuterProduct.scala:42:43") // StreamRead(x1750,x1778)
    
  }
}
