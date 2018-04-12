import pir._
import pir.node._
import arch._
import prism.enums._

object OuterProduct extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1771 = top.argFringe.argIn(init=0).name("x1771").ctrl(top) // ArgInNew(Const(0))
    boundOf(x1771) = 38400
    val x1772_d0 = top.argFringe.argIn(init=0).name("x1772_d0").ctrl(top) // ArgInNew(Const(0))
    boundOf(x1772_d0) = 38400
    val x1775 = ReadMem(x1771).name("x1775").ctrl(top) // RegRead(x1771)
    val x1776 = DRAM().name("x1776").ctrl(top) // x1776 = DRAMNew(ArrayBuffer(x1775),Const(0))
    val x1777 = ReadMem(x1772_d0).name("x1777").ctrl(top) // RegRead(x1772)
    val x1778 = DRAM().name("x1778").ctrl(top) // x1778 = DRAMNew(ArrayBuffer(x1777),Const(0))
    val x1779 = ReadMem(x1772_d0).name("x1779").ctrl(top) // RegRead(x1772)
    val x1780 = ReadMem(x1771).name("x1780").ctrl(top) // RegRead(x1771)
    val x1781 = DRAM().name("x1781").ctrl(top) // x1781 = DRAMNew(ArrayBuffer(x1780, x1779),Const(0))
    val x1893 = UnitController(style=SeqPipe, level=OuterControl).name("x1893").ctrl(top) // Hwblock(Block(Const(())),false)
    val x1784 = ReadMem(x1772_d0).name("x1784").ctrl(x1893) // RegRead(x1772)
    val x1785 = Counter(min=Const(0), max=x1784, step=Const(16), par=1).name("x1785").ctrl(x1893) // CounterNew(Const(0),x1784,Const(16),Const(1))
    val x1786 = ReadMem(x1771).name("x1786").ctrl(x1893) // RegRead(x1771)
    val x1787 = Counter(min=Const(0), max=x1786, step=Const(16), par=1).name("x1787").ctrl(x1893) // CounterNew(Const(0),x1786,Const(16),Const(1))
    val x1788 = CounterChain(List(x1787,x1785)).name("x1788").ctrl(x1893) // CounterChainNew(List(x1787, x1785))
    val x1892 = LoopController(style=MetaPipe, level=OuterControl, cchain=x1788).name("x1892").ctrl(x1893) // UnrolledForeach(List(Const(true)),x1788,Block(Const(())),List(List(b1049), List(b1050)),List(List(b1051), List(b1052)))
    val b1049 = CounterIter(x1787, Some(0)).ctrl(x1892).name("b1049")
    val b1051 = DummyOp().ctrl(x1892).name("b1051")
    val b1050 = CounterIter(x1785, Some(0)).ctrl(x1892).name("b1050")
    val b1052 = DummyOp().ctrl(x1892).name("b1052")
    val x1789_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x1789_d0_b0").ctrl(x1892) // x1789 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x1789_d0_b0) = false
    bufferDepthOf(x1789_d0_b0) = 2
    val x1790_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x1790_d0_b0").ctrl(x1892) // x1790 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x1790_d0_b0) = false
    bufferDepthOf(x1790_d0_b0) = 2
    val x1791_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x1791_d0_b0").ctrl(x1892) // x1791 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x1791_d0_b0) = false
    bufferDepthOf(x1791_d0_b0) = 3
    val x1793 = UnitController(style=SeqPipe, level=InnerControl).name("x1793").ctrl(x1892) // UnitPipe(List(b1051, b1052),Block(Const(())))
    val x1792 = OpDef(op=FixAdd, inputs=List(b1049, Const(16))).name("x1792").ctrl(x1793) // FixAdd(b1049,Const(16))
    val x1815 = UnitController(style=StreamPipe, level=OuterControl).name("x1815").ctrl(x1892) // UnitPipe(List(b1051, b1052),Block(Const(())))
    val b1910 = StreamOut(field="offset").name("b1910").ctrl(x1815) // x1794 = StreamOutNew(BurstCmdBus)
    val b1911 = StreamOut(field="size").name("b1911").ctrl(x1815) // x1794 = StreamOutNew(BurstCmdBus)
    val x1795 = StreamIn(field="data").name("x1795").ctrl(x1815) // x1795 = StreamInNew(BurstDataBus())
    val x1805 = UnitController(style=SeqPipe, level=InnerControl).name("x1805").ctrl(x1815) // UnitPipe(List(b1051, b1052),Block(x1804))
    val x1796 = b1049 // FixConvert(b1049,TRUE,_32,_0)
    val x1797 = OpDef(op=FixSla, inputs=List(x1796, Const(2))).name("x1797").ctrl(x1805) // FixLsh(x1796,Const(2))
    val x1798 = x1797 // FixConvert(x1797,TRUE,_64,_0)
    val x1799 = top.argFringe.dramAddress(x1776).name("x1799").ctrl(x1805) // GetDRAMAddress(x1776)
    val x1800 = OpDef(op=FixAdd, inputs=List(x1798, x1799)).name("x1800").ctrl(x1805) // FixAdd(x1798,x1799)
    val x1802_x1801 = x1800 // FixConvert(x1800,TRUE,_64,_0)
    // x1802 = SimpleStruct(ArrayBuffer((offset,x1801), (size,Const(64)), (isLoad,Const(true))))
    val x1803 = OpDef(op=BitAnd, inputs=List(b1051, b1052)).name("x1803").ctrl(x1805) // And(b1051,b1052)
    val b1912_b1910 = WriteMem(b1910, x1802_x1801).name("b1912_b1910").ctrl(x1805) // StreamWrite(x1794,x1802,x1803)
    val b1913_b1911 = WriteMem(b1911, Const(64)).name("b1913_b1911").ctrl(x1805) // StreamWrite(x1794,x1802,x1803)
    val x1806 = FringeContainer(x1776,b1910,b1911,x1795).name("x1806").ctrl(x1815) // FringeDenseLoad(x1776,x1794,x1795)
    val x1807 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x1807").ctrl(x1815) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1808 = CounterChain(List(x1807)).name("x1808").ctrl(x1815) // CounterChainNew(List(x1807))
    val x1814 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1808).name("x1814").ctrl(x1815) // UnrolledForeach(List(b1051, b1052),x1808,Block(Const(())),List(List(b1073)),List(List(b1074)))
    val b1073 = CounterIter(x1807, None).ctrl(x1814).name("b1073")
    val b1074 = DummyOp().ctrl(x1814).name("b1074")
    val x1809 = OpDef(op=BitAnd, inputs=List(b1074, b1051)).name("x1809").ctrl(x1814) // And(b1074,b1051)
    val x1810 = OpDef(op=BitAnd, inputs=List(x1809, b1052)).name("x1810").ctrl(x1814) // And(x1809,b1052)
    val x1811_x1811 = ReadMem(x1795).name("x1811").ctrl(x1814) // ParStreamRead(x1795,List(x1810))
    val x1812_x1812 = x1811_x1811 // x1812 = VectorApply(x1811,0)
    val x1813 = StoreBanks(List(x1789_d0_b0), List(b1073), x1812_x1812).name("x1813").ctrl(x1814) // ParSRAMStore(x1789,List(List(b1073)),List(x1812),List(x1810))
    val x1817 = UnitController(style=SeqPipe, level=InnerControl).name("x1817").ctrl(x1892) // UnitPipe(List(b1051, b1052),Block(Const(())))
    val x1816 = OpDef(op=FixAdd, inputs=List(b1050, Const(16))).name("x1816").ctrl(x1817) // FixAdd(b1050,Const(16))
    val x1839 = UnitController(style=StreamPipe, level=OuterControl).name("x1839").ctrl(x1892) // UnitPipe(List(b1051, b1052),Block(Const(())))
    val b1914 = StreamOut(field="offset").name("b1914").ctrl(x1839) // x1818 = StreamOutNew(BurstCmdBus)
    val b1915 = StreamOut(field="size").name("b1915").ctrl(x1839) // x1818 = StreamOutNew(BurstCmdBus)
    val x1819 = StreamIn(field="data").name("x1819").ctrl(x1839) // x1819 = StreamInNew(BurstDataBus())
    val x1829 = UnitController(style=SeqPipe, level=InnerControl).name("x1829").ctrl(x1839) // UnitPipe(List(b1051, b1052),Block(x1828))
    val x1820 = b1050 // FixConvert(b1050,TRUE,_32,_0)
    val x1821 = OpDef(op=FixSla, inputs=List(x1820, Const(2))).name("x1821").ctrl(x1829) // FixLsh(x1820,Const(2))
    val x1822 = x1821 // FixConvert(x1821,TRUE,_64,_0)
    val x1823 = top.argFringe.dramAddress(x1778).name("x1823").ctrl(x1829) // GetDRAMAddress(x1778)
    val x1824 = OpDef(op=FixAdd, inputs=List(x1822, x1823)).name("x1824").ctrl(x1829) // FixAdd(x1822,x1823)
    val x1826_x1825 = x1824 // FixConvert(x1824,TRUE,_64,_0)
    // x1826 = SimpleStruct(ArrayBuffer((offset,x1825), (size,Const(64)), (isLoad,Const(true))))
    val x1827 = OpDef(op=BitAnd, inputs=List(b1051, b1052)).name("x1827").ctrl(x1829) // And(b1051,b1052)
    val b1916_b1914 = WriteMem(b1914, x1826_x1825).name("b1916_b1914").ctrl(x1829) // StreamWrite(x1818,x1826,x1827)
    val b1917_b1915 = WriteMem(b1915, Const(64)).name("b1917_b1915").ctrl(x1829) // StreamWrite(x1818,x1826,x1827)
    val x1830 = FringeContainer(x1778,b1914,b1915,x1819).name("x1830").ctrl(x1839) // FringeDenseLoad(x1778,x1818,x1819)
    val x1831 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x1831").ctrl(x1839) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1832 = CounterChain(List(x1831)).name("x1832").ctrl(x1839) // CounterChainNew(List(x1831))
    val x1838 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1832).name("x1838").ctrl(x1839) // UnrolledForeach(List(b1051, b1052),x1832,Block(Const(())),List(List(b1099)),List(List(b1100)))
    val b1099 = CounterIter(x1831, None).ctrl(x1838).name("b1099")
    val b1100 = DummyOp().ctrl(x1838).name("b1100")
    val x1833 = OpDef(op=BitAnd, inputs=List(b1100, b1051)).name("x1833").ctrl(x1838) // And(b1100,b1051)
    val x1834 = OpDef(op=BitAnd, inputs=List(x1833, b1052)).name("x1834").ctrl(x1838) // And(x1833,b1052)
    val x1835_x1835 = ReadMem(x1819).name("x1835").ctrl(x1838) // ParStreamRead(x1819,List(x1834))
    val x1836_x1836 = x1835_x1835 // x1836 = VectorApply(x1835,0)
    val x1837 = StoreBanks(List(x1790_d0_b0), List(b1099), x1836_x1836).name("x1837").ctrl(x1838) // ParSRAMStore(x1790,List(List(b1099)),List(x1836),List(x1834))
    val x1840 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x1840").ctrl(x1892) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1841 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x1841").ctrl(x1892) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x1842 = CounterChain(List(x1841,x1840)).name("x1842").ctrl(x1892) // CounterChainNew(List(x1841, x1840))
    val x1851 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1842).name("x1851").ctrl(x1892) // UnrolledForeach(List(b1051, b1052),x1842,Block(Const(())),List(List(b1112), List(b1113)),List(List(b1114), List(b1115)))
    val b1112 = CounterIter(x1841, Some(0)).ctrl(x1851).name("b1112")
    val b1114 = DummyOp().ctrl(x1851).name("b1114")
    val b1113 = CounterIter(x1840, None).ctrl(x1851).name("b1113")
    val b1115 = DummyOp().ctrl(x1851).name("b1115")
    val x1843 = OpDef(op=BitAnd, inputs=List(b1114, b1115)).name("x1843").ctrl(x1851) // And(b1114,b1115)
    val x1844 = OpDef(op=BitAnd, inputs=List(b1051, b1052)).name("x1844").ctrl(x1851) // And(b1051,b1052)
    val x1845 = OpDef(op=BitAnd, inputs=List(x1843, x1844)).name("x1845").ctrl(x1851) // And(x1843,x1844)
    val x1846 = LoadBanks(List(x1789_d0_b0), List(b1112)).name("x1846").ctrl(x1851) // SRAMLoad(x1789,ArrayBuffer(Const(16)),List(b1112),Const(0),x1845)
    val x1847 = LoadBanks(List(x1790_d0_b0), List(b1113)).name("x1847").ctrl(x1851) // ParSRAMLoad(x1790,List(List(b1113)),List(x1845))
    val x1848 = x1847 // x1848 = VectorApply(x1847,0)
    val x1849 = OpDef(op=FixMul, inputs=List(x1846, x1848)).name("x1849").ctrl(x1851) // FixMul(x1846,x1848)
    val x1850 = StoreBanks(List(x1791_d0_b0), List(b1112, b1113), x1849).name("x1850").ctrl(x1851) // ParSRAMStore(x1791,List(List(b1112, b1113)),List(x1849),List(x1845))
    val x1854 = UnitController(style=SeqPipe, level=InnerControl).name("x1854").ctrl(x1892) // UnitPipe(List(b1051, b1052),Block(Const(())))
    val x1852 = OpDef(op=FixAdd, inputs=List(b1049, Const(16))).name("x1852").ctrl(x1854) // FixAdd(b1049,Const(16))
    val x1853 = OpDef(op=FixAdd, inputs=List(b1050, Const(16))).name("x1853").ctrl(x1854) // FixAdd(b1050,Const(16))
    val x1855 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x1855").ctrl(x1892) // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x1856 = CounterChain(List(x1855)).name("x1856").ctrl(x1892) // CounterChainNew(List(x1855))
    val x1891 = LoopController(style=StreamPipe, level=OuterControl, cchain=x1856).name("x1891").ctrl(x1892) // UnrolledForeach(List(b1051, b1052),x1856,Block(Const(())),List(List(b1130)),List(List(b1131)))
    val b1130 = CounterIter(x1855, Some(0)).ctrl(x1891).name("b1130")
    val b1131 = DummyOp().ctrl(x1891).name("b1131")
    val b1918 = StreamOut(field="offset").name("b1918").ctrl(x1891) // x1857 = StreamOutNew(BurstCmdBus)
    val b1919 = StreamOut(field="size").name("b1919").ctrl(x1891) // x1857 = StreamOutNew(BurstCmdBus)
    val x1858 = StreamOut(field="data").name("x1858").ctrl(x1891) // x1858 = StreamOutNew(BurstFullDataBus())
    val x1859 = StreamIn(field="ack").name("x1859").ctrl(x1891) // x1859 = StreamInNew(BurstAckBus)
    val x1875 = UnitController(style=SeqPipe, level=InnerControl).name("x1875").ctrl(x1891) // UnitPipe(List(b1131, b1051, b1052),Block(x1874))
    val x1860 = OpDef(op=FixAdd, inputs=List(b1049, b1130)).name("x1860").ctrl(x1875) // FixAdd(b1049,b1130)
    val x1861 = x1860 // FixConvert(x1860,TRUE,_32,_0)
    val x1862 = ReadMem(x1772_d0).name("x1862").ctrl(x1875) // RegRead(x1772)
    val x1863 = OpDef(op=FixMul, inputs=List(x1861, x1862)).name("x1863").ctrl(x1875) // FixMul(x1861,x1862)
    val x1864 = b1050 // FixConvert(b1050,TRUE,_32,_0)
    val x1865 = OpDef(op=FixAdd, inputs=List(x1863, x1864)).name("x1865").ctrl(x1875) // FixAdd(x1863,x1864)
    val x1866 = OpDef(op=FixSla, inputs=List(x1865, Const(2))).name("x1866").ctrl(x1875) // FixLsh(x1865,Const(2))
    val x1867 = x1866 // FixConvert(x1866,TRUE,_64,_0)
    val x1868 = top.argFringe.dramAddress(x1781).name("x1868").ctrl(x1875) // GetDRAMAddress(x1781)
    val x1869 = OpDef(op=FixAdd, inputs=List(x1867, x1868)).name("x1869").ctrl(x1875) // FixAdd(x1867,x1868)
    val x1871_x1870 = x1869 // FixConvert(x1869,TRUE,_64,_0)
    // x1871 = SimpleStruct(ArrayBuffer((offset,x1870), (size,Const(64)), (isLoad,Const(false))))
    val x1872 = OpDef(op=BitAnd, inputs=List(b1131, b1051)).name("x1872").ctrl(x1875) // And(b1131,b1051)
    val x1873 = OpDef(op=BitAnd, inputs=List(x1872, b1052)).name("x1873").ctrl(x1875) // And(x1872,b1052)
    val b1920_b1918 = WriteMem(b1918, x1871_x1870).name("b1920_b1918").ctrl(x1875) // StreamWrite(x1857,x1871,x1873)
    val b1921_b1919 = WriteMem(b1919, Const(64)).name("b1921_b1919").ctrl(x1875) // StreamWrite(x1857,x1871,x1873)
    val x1876 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x1876").ctrl(x1891) // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x1877 = CounterChain(List(x1876)).name("x1877").ctrl(x1891) // CounterChainNew(List(x1876))
    val x1885 = LoopController(style=InnerPipe, level=InnerControl, cchain=x1877).name("x1885").ctrl(x1891) // UnrolledForeach(List(b1131, b1051, b1052),x1877,Block(Const(())),List(List(b1153)),List(List(b1154)))
    val b1153 = CounterIter(x1876, None).ctrl(x1885).name("b1153")
    val b1154 = DummyOp().ctrl(x1885).name("b1154")
    val x1878 = OpDef(op=BitAnd, inputs=List(b1154, b1131)).name("x1878").ctrl(x1885) // And(b1154,b1131)
    val x1879 = OpDef(op=BitAnd, inputs=List(b1051, b1052)).name("x1879").ctrl(x1885) // And(b1051,b1052)
    val x1880 = OpDef(op=BitAnd, inputs=List(x1878, x1879)).name("x1880").ctrl(x1885) // And(x1878,x1879)
    val x1881 = LoadBanks(List(x1791_d0_b0), List(b1130, b1153)).name("x1881").ctrl(x1885) // ParSRAMLoad(x1791,List(List(b1130, b1153)),List(x1880))
    val x1883_x1882 = x1881 // x1882 = VectorApply(x1881,0)
    // x1883 = SimpleStruct(ArrayBuffer((_1,x1882), (_2,Const(true))))
    val x1884_x1858 = WriteMem(x1858, x1883_x1882).name("x1884_x1858").ctrl(x1885) // ParStreamWrite(x1858,List(x1883),List(x1880))
    val x1886 = FringeContainer(x1781,b1918,b1919,x1858,x1859).name("x1886").ctrl(x1891) // FringeDenseStore(x1781,x1857,x1858,x1859)
    val x1890 = UnitController(style=SeqPipe, level=InnerControl).name("x1890").ctrl(x1891) // UnitPipe(List(b1131, b1051, b1052),Block(Const(())))
    val x1887 = OpDef(op=BitAnd, inputs=List(b1131, b1051)).name("x1887").ctrl(x1890) // And(b1131,b1051)
    val x1888 = OpDef(op=BitAnd, inputs=List(x1887, b1052)).name("x1888").ctrl(x1890) // And(x1887,b1052)
    val x1889_x1889 = ReadMem(x1859).name("x1889").ctrl(x1890) // StreamRead(x1859,x1888)
    
  }
}
