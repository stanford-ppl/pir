import pir._
import pir.node._
import arch._
import prism.enums._

object OuterProduct extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1666 = withCtrl(design.top.topController) { ArgIn(init=0).name("x1666").srcCtx("OuterProduct.scala:21:22:sizeA") } // ArgInNew(Const(0))
    isAccum(x1666) = false
    bufferDepthOf(x1666) = 1
    boundOf(x1666) = 1024
    val x1667_d0 = withCtrl(design.top.topController) { ArgIn(init=0).name("x1667_d0").srcCtx("OuterProduct.scala:22:22:sizeB") } // ArgInNew(Const(0))
    isAccum(x1667_d0) = false
    bufferDepthOf(x1667_d0) = 1
    boundOf(x1667_d0) = 1024
    val x1667_d1 = withCtrl(design.top.topController) { ArgIn(init=0).name("x1667_d1").srcCtx("OuterProduct.scala:22:22:sizeB") } // ArgInNew(Const(0))
    isAccum(x1667_d1) = false
    bufferDepthOf(x1667_d1) = 1
    boundOf(x1667_d1) = 1024
    val x1670 = withCtrl(design.top.topController) { ReadMem(x1666).name("x1670").srcCtx("OuterProduct.scala:26:24") } // RegRead(x1666)
    val x1671 = withCtrl(design.top.topController) { DRAM(dims=List(x1670)).name("x1671").srcCtx("OuterProduct.scala:26:23:vec1") } // x1671 = DRAMNew(ArrayBuffer(x1670),Const(0))
    val x1672 = withCtrl(design.top.topController) { ReadMem(x1667_d0).name("x1672").srcCtx("OuterProduct.scala:27:24") } // RegRead(x1667)
    val x1673 = withCtrl(design.top.topController) { DRAM(dims=List(x1672)).name("x1673").srcCtx("OuterProduct.scala:27:23:vec2") } // x1673 = DRAMNew(ArrayBuffer(x1672),Const(0))
    val x1674 = withCtrl(design.top.topController) { ReadMem(x1667_d0).name("x1674").srcCtx("OuterProduct.scala:28:30") } // RegRead(x1667)
    val x1675 = withCtrl(design.top.topController) { ReadMem(x1666).name("x1675").srcCtx("OuterProduct.scala:28:23") } // RegRead(x1666)
    val x1676 = withCtrl(design.top.topController) { DRAM(dims=List(x1675, x1674)).name("x1676").srcCtx("OuterProduct.scala:28:22:out") } // x1676 = DRAMNew(ArrayBuffer(x1675, x1674),Const(0))
    val x1784 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x1784").srcCtx("OuterProduct.scala:33:11") } // Hwblock(Block(Const(())),false)
    val x1679 = withCtrl(x1784) { ReadMem(x1666).name("x1679").srcCtx("OuterProduct.scala:34:15") } // RegRead(x1666)
    val x1680 = withCtrl(x1784) { Counter(min=Const(0), max=x1679, step=Const(32), par=1).name("x1680").srcCtx("OuterProduct.scala:34:28") } // CounterNew(Const(0),x1679,Const(32),Const(1))
    val x1681 = withCtrl(x1784) { CounterChain(List(x1680)).name("x1681").srcCtx("OuterProduct.scala:34:36") } // CounterChainNew(List(x1680))
    val x1783 = withCtrl(x1784) { LoopController(style=MetaPipe, level=OuterControl, cchain=x1681).name("x1783").srcCtx("OuterProduct.scala:34:36") } // UnrolledForeach(List(Const(true)),x1681,Block(Const(())),List(List(b966)),List(List(b967)))
    val b966 = withCtrl(x1783) { CounterIter(x1680, Some(0)).name("b966") } // b966
    val b967 = withCtrl(x1783) { Const(true).name("b967") } // b967
    val x1682_d0_b0 = withCtrl(x1783) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1682_d0_b0").srcCtx("OuterProduct.scala:35:25:b1") } // x1682 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1682_d0_b0) = false
    bufferDepthOf(x1682_d0_b0) = 2
    staticDimsOf(x1682_d0_b0) = List(32)
    val x1684 = withCtrl(x1783) { UnitController(style=SeqPipe, level=InnerControl).name("x1684").srcCtx("OuterProduct.scala:34:36") } // UnitPipe(List(b967),Block(Const(())))
    val x1683 = withCtrl(x1684) { OpDef(op=FixAdd, inputs=List(b966, Const(32))).name("x1683").srcCtx("OuterProduct.scala:36:26") } // FixAdd(b966,Const(32))
    val x1703 = withCtrl(x1783) { UnitController(style=StreamPipe, level=OuterControl).name("x1703").srcCtx("OuterProduct.scala:36:12") } // UnitPipe(List(b967),Block(Const(())))
    val b1801 = withCtrl(x1703) { StreamOut(field="offset").name("b1801").srcCtx("OuterProduct.scala:36:12") } // x1685 = StreamOutNew(BurstCmdBus)
    isAccum(b1801) = false
    bufferDepthOf(b1801) = 1
    val b1802 = withCtrl(x1703) { StreamOut(field="size").name("b1802").srcCtx("OuterProduct.scala:36:12") } // x1685 = StreamOutNew(BurstCmdBus)
    isAccum(b1802) = false
    bufferDepthOf(b1802) = 1
    val x1686 = withCtrl(x1703) { StreamIn(field="data").name("x1686").srcCtx("OuterProduct.scala:36:12") } // x1686 = StreamInNew(BurstDataBus())
    isAccum(x1686) = false
    bufferDepthOf(x1686) = 1
    val x1694 = withCtrl(x1703) { UnitController(style=SeqPipe, level=InnerControl).name("x1694").srcCtx("OuterProduct.scala:36:12") } // UnitPipe(List(b967),Block(x1693))
    val x1687 = b966 // FixConvert(b966,TRUE,_32,_0) (Same Type. No op)
    val x1688 = withCtrl(x1694) { OpDef(op=FixSla, inputs=List(x1687, Const(2))).name("x1688").srcCtx("OuterProduct.scala:36:12") } // FixLsh(x1687,Const(2))
    val x1689 = x1688 // FixConvert(x1688,TRUE,_64,_0)
    val x1690 = withCtrl(x1694) { DramAddress(x1671).name("x1690").srcCtx("OuterProduct.scala:36:12") } // GetDRAMAddress(x1671)
    val x1692_x1691 = withCtrl(x1694) { OpDef(op=FixAdd, inputs=List(x1689, x1690)).name("x1692_x1691").srcCtx("OuterProduct.scala:36:12") } // FixAdd(x1689,x1690)
    // x1692 = SimpleStruct(ArrayBuffer((offset,x1691), (size,Const(128)), (isLoad,Const(true))))
    val x1693_b1803_b1801 = withCtrl(x1694) { WriteMem(b1801, x1692_x1691).name("x1693_b1803_b1801").srcCtx("OuterProduct.scala:36:12") } // StreamWrite(x1685,x1692,b967)
    val x1693_b1804_b1802 = withCtrl(x1694) { WriteMem(b1802, Const(128)).name("x1693_b1804_b1802").srcCtx("OuterProduct.scala:36:12") } // StreamWrite(x1685,x1692,b967)
    val x1695 = withCtrl(x1703) { FringeDenseLoad(dram=List(x1671), cmdStream=List(b1801, b1802), dataStream=List(x1686)).name("x1695").srcCtx("OuterProduct.scala:36:12") } // FringeDenseLoad(x1671,x1685,x1686)
    val x1696 = withCtrl(x1703) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1696").srcCtx("OuterProduct.scala:36:12") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1697 = withCtrl(x1703) { CounterChain(List(x1696)).name("x1697").srcCtx("OuterProduct.scala:36:12") } // CounterChainNew(List(x1696))
    val x1702 = withCtrl(x1703) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1697).name("x1702").srcCtx("OuterProduct.scala:36:12") } // UnrolledForeach(List(b967),x1697,Block(Const(())),List(List(b984)),List(List(b985)))
    val b984 = withCtrl(x1702) { CounterIter(x1696, None).name("b984") } // b984
    val b985 = withCtrl(x1702) { Const(true).name("b985") } // b985
    val x1698 = withCtrl(x1702) { OpDef(op=BitAnd, inputs=List(b985, b967)).name("x1698").srcCtx("UnrollingBase.scala:28:66") } // And(b985,b967)
    val x1699_x1699 = withCtrl(x1702) { ReadMem(x1686).name("x1699_x1699").srcCtx("OuterProduct.scala:36:12") } // ParStreamRead(x1686,List(x1698))
    val x1700_x1700 = x1699_x1699 // x1700 = VectorApply(x1699,0)
    val x1701 = withCtrl(x1702) { StoreBanks(List(List(x1682_d0_b0)), List(b984), x1700_x1700).name("x1701").srcCtx("OuterProduct.scala:36:12") } // ParSRAMStore(x1682,List(List(b984)),List(x1700),List(x1698))
    val x1704 = withCtrl(x1783) { ReadMem(x1667_d0).name("x1704").srcCtx("OuterProduct.scala:37:17") } // RegRead(x1667)
    val x1705 = withCtrl(x1783) { Counter(min=Const(0), max=x1704, step=Const(32), par=1).name("x1705").srcCtx("OuterProduct.scala:37:30") } // CounterNew(Const(0),x1704,Const(32),Const(1))
    val x1706 = withCtrl(x1783) { CounterChain(List(x1705)).name("x1706").srcCtx("OuterProduct.scala:37:39") } // CounterChainNew(List(x1705))
    val x1782 = withCtrl(x1783) { LoopController(style=MetaPipe, level=OuterControl, cchain=x1706).name("x1782").srcCtx("OuterProduct.scala:37:39") } // UnrolledForeach(List(b967),x1706,Block(Const(())),List(List(b995)),List(List(b996)))
    val b995 = withCtrl(x1782) { CounterIter(x1705, Some(0)).name("b995") } // b995
    val b996 = withCtrl(x1782) { Const(true).name("b996") } // b996
    val x1707_d0_b0 = withCtrl(x1782) { SRAM(size=32, banking=Strided(banks=16, stride=1)).name("x1707_d0_b0").srcCtx("OuterProduct.scala:38:27:b2") } // x1707 = SRAMNew(ArrayBuffer(Const(32)))
    isAccum(x1707_d0_b0) = false
    bufferDepthOf(x1707_d0_b0) = 2
    staticDimsOf(x1707_d0_b0) = List(32)
    val x1709 = withCtrl(x1782) { UnitController(style=SeqPipe, level=InnerControl).name("x1709").srcCtx("OuterProduct.scala:37:39") } // UnitPipe(List(b996, b967),Block(Const(())))
    val x1708 = withCtrl(x1709) { OpDef(op=FixAdd, inputs=List(b995, Const(32))).name("x1708").srcCtx("OuterProduct.scala:39:28") } // FixAdd(b995,Const(32))
    val x1730 = withCtrl(x1782) { UnitController(style=StreamPipe, level=OuterControl).name("x1730").srcCtx("OuterProduct.scala:39:14") } // UnitPipe(List(b996, b967),Block(Const(())))
    val b1805 = withCtrl(x1730) { StreamOut(field="offset").name("b1805").srcCtx("OuterProduct.scala:39:14") } // x1710 = StreamOutNew(BurstCmdBus)
    isAccum(b1805) = false
    bufferDepthOf(b1805) = 1
    val b1806 = withCtrl(x1730) { StreamOut(field="size").name("b1806").srcCtx("OuterProduct.scala:39:14") } // x1710 = StreamOutNew(BurstCmdBus)
    isAccum(b1806) = false
    bufferDepthOf(b1806) = 1
    val x1711 = withCtrl(x1730) { StreamIn(field="data").name("x1711").srcCtx("OuterProduct.scala:39:14") } // x1711 = StreamInNew(BurstDataBus())
    isAccum(x1711) = false
    bufferDepthOf(x1711) = 1
    val x1720 = withCtrl(x1730) { UnitController(style=SeqPipe, level=InnerControl).name("x1720").srcCtx("OuterProduct.scala:39:14") } // UnitPipe(List(b996, b967),Block(x1719))
    val x1712 = b995 // FixConvert(b995,TRUE,_32,_0) (Same Type. No op)
    val x1713 = withCtrl(x1720) { OpDef(op=FixSla, inputs=List(x1712, Const(2))).name("x1713").srcCtx("OuterProduct.scala:39:14") } // FixLsh(x1712,Const(2))
    val x1714 = x1713 // FixConvert(x1713,TRUE,_64,_0)
    val x1715 = withCtrl(x1720) { DramAddress(x1673).name("x1715").srcCtx("OuterProduct.scala:39:14") } // GetDRAMAddress(x1673)
    val x1717_x1716 = withCtrl(x1720) { OpDef(op=FixAdd, inputs=List(x1714, x1715)).name("x1717_x1716").srcCtx("OuterProduct.scala:39:14") } // FixAdd(x1714,x1715)
    // x1717 = SimpleStruct(ArrayBuffer((offset,x1716), (size,Const(128)), (isLoad,Const(true))))
    val x1718 = withCtrl(x1720) { OpDef(op=BitAnd, inputs=List(b996, b967)).name("x1718").srcCtx("UnrollingBase.scala:28:66") } // And(b996,b967)
    val x1719_b1807_b1805 = withCtrl(x1720) { WriteMem(b1805, x1717_x1716).name("x1719_b1807_b1805").srcCtx("OuterProduct.scala:39:14") } // StreamWrite(x1710,x1717,x1718)
    val x1719_b1808_b1806 = withCtrl(x1720) { WriteMem(b1806, Const(128)).name("x1719_b1808_b1806").srcCtx("OuterProduct.scala:39:14") } // StreamWrite(x1710,x1717,x1718)
    val x1721 = withCtrl(x1730) { FringeDenseLoad(dram=List(x1673), cmdStream=List(b1805, b1806), dataStream=List(x1711)).name("x1721").srcCtx("OuterProduct.scala:39:14") } // FringeDenseLoad(x1673,x1710,x1711)
    val x1722 = withCtrl(x1730) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1722").srcCtx("OuterProduct.scala:39:14") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1723 = withCtrl(x1730) { CounterChain(List(x1722)).name("x1723").srcCtx("OuterProduct.scala:39:14") } // CounterChainNew(List(x1722))
    val x1729 = withCtrl(x1730) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1723).name("x1729").srcCtx("OuterProduct.scala:39:14") } // UnrolledForeach(List(b996, b967),x1723,Block(Const(())),List(List(b1014)),List(List(b1015)))
    val b1014 = withCtrl(x1729) { CounterIter(x1722, None).name("b1014") } // b1014
    val b1015 = withCtrl(x1729) { Const(true).name("b1015") } // b1015
    val x1724 = withCtrl(x1729) { OpDef(op=BitAnd, inputs=List(b1015, b996)).name("x1724").srcCtx("UnrollingBase.scala:28:66") } // And(b1015,b996)
    val x1725 = withCtrl(x1729) { OpDef(op=BitAnd, inputs=List(x1724, b967)).name("x1725").srcCtx("UnrollingBase.scala:28:66") } // And(x1724,b967)
    val x1726_x1726 = withCtrl(x1729) { ReadMem(x1711).name("x1726_x1726").srcCtx("OuterProduct.scala:39:14") } // ParStreamRead(x1711,List(x1725))
    val x1727_x1727 = x1726_x1726 // x1727 = VectorApply(x1726,0)
    val x1728 = withCtrl(x1729) { StoreBanks(List(List(x1707_d0_b0)), List(b1014), x1727_x1727).name("x1728").srcCtx("OuterProduct.scala:39:14") } // ParSRAMStore(x1707,List(List(b1014)),List(x1727),List(x1725))
    val x1731_d0_b0 = withCtrl(x1782) { SRAM(size=1024, banking=Strided(banks=16, stride=1)).name("x1731_d0_b0").srcCtx("OuterProduct.scala:40:32:outTile") } // x1731 = SRAMNew(ArrayBuffer(Const(32), Const(32)))
    isAccum(x1731_d0_b0) = false
    bufferDepthOf(x1731_d0_b0) = 3
    staticDimsOf(x1731_d0_b0) = List(32, 32)
    val x1732 = withCtrl(x1782) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1732").srcCtx("OuterProduct.scala:41:36") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1733 = withCtrl(x1782) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x1733").srcCtx("OuterProduct.scala:41:23") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x1734 = withCtrl(x1782) { CounterChain(List(x1733,x1732)).name("x1734").srcCtx("OuterProduct.scala:41:44") } // CounterChainNew(List(x1733, x1732))
    val x1743 = withCtrl(x1782) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1734).name("x1743").srcCtx("OuterProduct.scala:41:44") } // UnrolledForeach(List(b996, b967),x1734,Block(Const(())),List(List(b1027), List(b1028)),List(List(b1029), List(b1030)))
    val b1027 = withCtrl(x1743) { CounterIter(x1733, Some(0)).name("b1027") } // b1027
    val b1029 = withCtrl(x1743) { Const(true).name("b1029") } // b1029
    val b1028 = withCtrl(x1743) { CounterIter(x1732, None).name("b1028") } // b1028
    val b1030 = withCtrl(x1743) { Const(true).name("b1030") } // b1030
    val x1735 = withCtrl(x1743) { OpDef(op=BitAnd, inputs=List(b1029, b1030)).name("x1735").srcCtx("UnrollingBase.scala:28:66") } // And(b1029,b1030)
    val x1736 = withCtrl(x1743) { OpDef(op=BitAnd, inputs=List(b996, b967)).name("x1736").srcCtx("UnrollingBase.scala:28:66") } // And(b996,b967)
    val x1737 = withCtrl(x1743) { OpDef(op=BitAnd, inputs=List(x1735, x1736)).name("x1737").srcCtx("UnrollingBase.scala:28:66") } // And(x1735,x1736)
    val x1738 = withCtrl(x1743) { LoadBanks(List(x1682_d0_b0), List(b1027)).name("x1738").srcCtx("OuterProduct.scala:41:77") } // SRAMLoad(x1682,ArrayBuffer(Const(32)),List(b1027),Const(0),x1737)
    val x1739 = withCtrl(x1743) { LoadBanks(List(x1707_d0_b0), List(b1028)).name("x1739").srcCtx("OuterProduct.scala:41:86") } // ParSRAMLoad(x1707,List(List(b1028)),List(x1737))
    val x1740 = x1739 // x1740 = VectorApply(x1739,0)
    val x1741 = withCtrl(x1743) { OpDef(op=FixMul, inputs=List(x1738, x1740)).name("x1741").srcCtx("OuterProduct.scala:41:82") } // FixMul(x1738,x1740)
    val x1742 = withCtrl(x1743) { StoreBanks(List(List(x1731_d0_b0)), List(b1027, b1028), x1741).name("x1742").srcCtx("OuterProduct.scala:41:73") } // ParSRAMStore(x1731,List(List(b1027, b1028)),List(x1741),List(x1737))
    val x1745 = withCtrl(x1782) { UnitController(style=SeqPipe, level=InnerControl).name("x1745").srcCtx("OuterProduct.scala:37:39") } // UnitPipe(List(b996, b967),Block(Const(())))
    val x1744 = withCtrl(x1745) { OpDef(op=FixAdd, inputs=List(b966, Const(32))).name("x1744").srcCtx("OuterProduct.scala:42:19") } // FixAdd(b966,Const(32))
    val x1746 = withCtrl(x1782) { Counter(min=Const(0), max=Const(32), step=Const(1), par=1).name("x1746").srcCtx("OuterProduct.scala:42:43") } // CounterNew(Const(0),Const(32),Const(1),Const(1))
    val x1747 = withCtrl(x1782) { CounterChain(List(x1746)).name("x1747").srcCtx("OuterProduct.scala:42:43") } // CounterChainNew(List(x1746))
    val x1781 = withCtrl(x1782) { LoopController(style=StreamPipe, level=OuterControl, cchain=x1747).name("x1781").srcCtx("OuterProduct.scala:42:43") } // UnrolledForeach(List(b996, b967),x1747,Block(Const(())),List(List(b1044)),List(List(b1045)))
    val b1044 = withCtrl(x1781) { CounterIter(x1746, Some(0)).name("b1044") } // b1044
    val b1045 = withCtrl(x1781) { Const(true).name("b1045") } // b1045
    val b1809 = withCtrl(x1781) { StreamOut(field="offset").name("b1809").srcCtx("OuterProduct.scala:42:43") } // x1748 = StreamOutNew(BurstCmdBus)
    isAccum(b1809) = false
    bufferDepthOf(b1809) = 1
    val b1810 = withCtrl(x1781) { StreamOut(field="size").name("b1810").srcCtx("OuterProduct.scala:42:43") } // x1748 = StreamOutNew(BurstCmdBus)
    isAccum(b1810) = false
    bufferDepthOf(b1810) = 1
    val x1749 = withCtrl(x1781) { StreamOut(field="data").name("x1749").srcCtx("OuterProduct.scala:42:43") } // x1749 = StreamOutNew(BurstFullDataBus())
    isAccum(x1749) = false
    bufferDepthOf(x1749) = 1
    val x1750 = withCtrl(x1781) { StreamIn(field="ack").name("x1750").srcCtx("OuterProduct.scala:42:43") } // x1750 = StreamInNew(BurstAckBus)
    isAccum(x1750) = false
    bufferDepthOf(x1750) = 1
    val x1765 = withCtrl(x1781) { UnitController(style=SeqPipe, level=InnerControl).name("x1765").srcCtx("OuterProduct.scala:42:43") } // UnitPipe(List(b1045, b996, b967),Block(x1764))
    val x1751 = withCtrl(x1765) { OpDef(op=FixAdd, inputs=List(b966, b1044)).name("x1751").srcCtx("OuterProduct.scala:42:43") } // FixAdd(b966,b1044)
    val x1752 = x1751 // FixConvert(x1751,TRUE,_32,_0) (Same Type. No op)
    val x1753 = withCtrl(x1765) { ReadMem(x1667_d0).name("x1753").srcCtx("OuterProduct.scala:28:30") } // RegRead(x1667)
    val x1754 = withCtrl(x1765) { OpDef(op=FixMul, inputs=List(x1752, x1753)).name("x1754").srcCtx("OuterProduct.scala:42:43") } // FixMul(x1752,x1753)
    val x1755 = b995 // FixConvert(b995,TRUE,_32,_0) (Same Type. No op)
    val x1756 = withCtrl(x1765) { OpDef(op=FixAdd, inputs=List(x1754, x1755)).name("x1756").srcCtx("OuterProduct.scala:42:43") } // FixAdd(x1754,x1755)
    val x1757 = withCtrl(x1765) { OpDef(op=FixSla, inputs=List(x1756, Const(2))).name("x1757").srcCtx("OuterProduct.scala:42:43") } // FixLsh(x1756,Const(2))
    val x1758 = x1757 // FixConvert(x1757,TRUE,_64,_0)
    val x1759 = withCtrl(x1765) { DramAddress(x1676).name("x1759").srcCtx("OuterProduct.scala:42:43") } // GetDRAMAddress(x1676)
    val x1761_x1760 = withCtrl(x1765) { OpDef(op=FixAdd, inputs=List(x1758, x1759)).name("x1761_x1760").srcCtx("OuterProduct.scala:42:43") } // FixAdd(x1758,x1759)
    // x1761 = SimpleStruct(ArrayBuffer((offset,x1760), (size,Const(128)), (isLoad,Const(false))))
    val x1762 = withCtrl(x1765) { OpDef(op=BitAnd, inputs=List(b1045, b996)).name("x1762").srcCtx("UnrollingBase.scala:28:66") } // And(b1045,b996)
    val x1763 = withCtrl(x1765) { OpDef(op=BitAnd, inputs=List(x1762, b967)).name("x1763").srcCtx("UnrollingBase.scala:28:66") } // And(x1762,b967)
    val x1764_b1811_b1809 = withCtrl(x1765) { WriteMem(b1809, x1761_x1760).name("x1764_b1811_b1809").srcCtx("OuterProduct.scala:42:43") } // StreamWrite(x1748,x1761,x1763)
    val x1764_b1812_b1810 = withCtrl(x1765) { WriteMem(b1810, Const(128)).name("x1764_b1812_b1810").srcCtx("OuterProduct.scala:42:43") } // StreamWrite(x1748,x1761,x1763)
    val x1766 = withCtrl(x1781) { Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x1766").srcCtx("OuterProduct.scala:42:43") } // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x1767 = withCtrl(x1781) { CounterChain(List(x1766)).name("x1767").srcCtx("OuterProduct.scala:42:43") } // CounterChainNew(List(x1766))
    val x1775 = withCtrl(x1781) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1767).name("x1775").srcCtx("OuterProduct.scala:42:43") } // UnrolledForeach(List(b1045, b996, b967),x1767,Block(Const(())),List(List(b1066)),List(List(b1067)))
    val b1066 = withCtrl(x1775) { CounterIter(x1766, None).name("b1066") } // b1066
    val b1067 = withCtrl(x1775) { Const(true).name("b1067") } // b1067
    val x1768 = withCtrl(x1775) { OpDef(op=BitAnd, inputs=List(b1067, b1045)).name("x1768").srcCtx("UnrollingBase.scala:28:66") } // And(b1067,b1045)
    val x1769 = withCtrl(x1775) { OpDef(op=BitAnd, inputs=List(b996, b967)).name("x1769").srcCtx("UnrollingBase.scala:28:66") } // And(b996,b967)
    val x1770 = withCtrl(x1775) { OpDef(op=BitAnd, inputs=List(x1768, x1769)).name("x1770").srcCtx("UnrollingBase.scala:28:66") } // And(x1768,x1769)
    val x1771 = withCtrl(x1775) { LoadBanks(List(x1731_d0_b0), List(b1044, b1066)).name("x1771").srcCtx("OuterProduct.scala:42:43") } // ParSRAMLoad(x1731,List(List(b1044, b1066)),List(x1770))
    val x1773_x1772 = x1771 // x1772 = VectorApply(x1771,0)
    // x1773 = SimpleStruct(ArrayBuffer((_1,x1772), (_2,Const(true))))
    val x1774_x1774_x1749 = withCtrl(x1775) { WriteMem(x1749, x1773_x1772).name("x1774_x1774_x1749").srcCtx("OuterProduct.scala:42:43") } // ParStreamWrite(x1749,List(x1773),List(x1770))
    val x1776 = withCtrl(x1781) { FringeDenseStore(dram=List(x1676), cmdStream=List(b1809, b1810), dataStream=List(x1749), ackStream=List(x1750)).name("x1776").srcCtx("OuterProduct.scala:42:43") } // FringeDenseStore(x1676,x1748,x1749,x1750)
    val x1780 = withCtrl(x1781) { UnitController(style=SeqPipe, level=InnerControl).name("x1780").srcCtx("OuterProduct.scala:42:43") } // UnitPipe(List(b1045, b996, b967),Block(Const(())))
    val x1777 = withCtrl(x1780) { OpDef(op=BitAnd, inputs=List(b1045, b996)).name("x1777").srcCtx("UnrollingBase.scala:28:66") } // And(b1045,b996)
    val x1778 = withCtrl(x1780) { OpDef(op=BitAnd, inputs=List(x1777, b967)).name("x1778").srcCtx("UnrollingBase.scala:28:66") } // And(x1777,b967)
    val x1779_x1779 = withCtrl(x1780) { ReadMem(x1750).name("x1779_x1779").srcCtx("OuterProduct.scala:42:43") } // StreamRead(x1750,x1778)
    
  }
}
