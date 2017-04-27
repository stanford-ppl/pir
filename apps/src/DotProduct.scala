import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object DotProduct extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1725_x1734_data_v = Vector("x1725_x1734_data")
    val x1609_argin = ArgIn("x1609")
    val x1606_x1811_x1819_v = Vector("x1606_x1811_x1819")
    val x1744_x1753_data_v = Vector("x1744_x1753_data")
    val x1687_argin = ArgIn("x1687")
    val x1648_argin = ArgIn("x1648")
    val x1647_x1656_data_v = Vector("x1647_x1656_data")
    val x1599_x1771_x1780_v = Vector("x1599_x1771_x1780")
    val x1601_x1797_x1806_v = Vector("x1601_x1797_x1806")
    val x1667_argin = ArgIn("x1667")
    val x1685_b1877_x1693_b1879_s = Scalar("x1685_b1877_x1693_b1879")
    val x1626_b1865_x1634_b1867_s = Scalar("x1626_b1865_x1634_b1867")
    val x1607_b1860_x1615_b1862_s = Scalar("x1607_b1860_x1615_b1862")
    val x1626_b1864_x1634_b1866_s = Scalar("x1626_b1864_x1634_b1866")
    val x1628_argin = ArgIn("x1628")
    val x1685_b1876_x1693_b1878_s = Scalar("x1685_b1876_x1693_b1878")
    val x1743_b1889_x1751_b1891_s = Scalar("x1743_b1889_x1751_b1891")
    val x1724_b1884_x1732_b1886_s = Scalar("x1724_b1884_x1732_b1886")
    val x1585_argin = ArgIn("x1585")
    val x1646_b1869_x1654_b1871_s = Scalar("x1646_b1869_x1654_b1871")
    val x1686_x1695_data_v = Vector("x1686_x1695_data")
    val x1665_b1873_x1673_b1875_s = Scalar("x1665_b1873_x1673_b1875")
    val x1766_x1804_s = Scalar("x1766_x1804")
    val x1767_x1817_s = Scalar("x1767_x1817")
    val x1765_x1791_s = Scalar("x1765_x1791")
    val x1602_x1810_x1819_v = Vector("x1602_x1810_x1819")
    val x1743_b1888_x1751_b1890_s = Scalar("x1743_b1888_x1751_b1890")
    val x1600_x1784_x1793_v = Vector("x1600_x1784_x1793")
    val x1608_x1617_data_v = Vector("x1608_x1617_data")
    val x1591_oc = OffChip("x1591")
    val x1605_x1798_x1806_v = Vector("x1605_x1798_x1806")
    val x1704_b1880_x1712_b1882_s = Scalar("x1704_b1880_x1712_b1882")
    val x1764_x1778_s = Scalar("x1764_x1778")
    val x1627_x1636_data_v = Vector("x1627_x1636_data")
    val x1603_x1772_x1780_v = Vector("x1603_x1772_x1780")
    val x1604_x1785_x1793_v = Vector("x1604_x1785_x1793")
    val x1705_x1714_data_v = Vector("x1705_x1714_data")
    val x1589_oc = OffChip("x1589")
    val x1665_b1872_x1673_b1874_s = Scalar("x1665_b1872_x1673_b1874")
    val x1745_argin = ArgIn("x1745")
    val x1592_x1839_argout = ArgOut("x1592_x1839")
    val x1726_argin = ArgIn("x1726")
    val x1724_b1885_x1732_b1887_s = Scalar("x1724_b1885_x1732_b1887")
    val x1704_b1881_x1712_b1883_s = Scalar("x1704_b1881_x1712_b1883")
    val x1646_b1868_x1654_b1870_s = Scalar("x1646_b1868_x1654_b1870")
    val x1706_argin = ArgIn("x1706")
    val x1607_b1861_x1615_b1863_s = Scalar("x1607_b1861_x1615_b1863")
    val x1666_x1675_data_v = Vector("x1666_x1675_data")
    val x1841 = Sequential(name="x1841",parent=top) { implicit CU => 
      val ctr1 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1841_unit = CounterChain(name = "x1841_unit", ctr1)
    }
    val x1837 = MetaPipeline(name="x1837",parent=x1841) { implicit CU => 
      val x1585_x1596 =  ScalarBuffer().wtPort(x1585_argin)
      val ctr2 = Counter(min=Const(0), max=x1585_x1596.load, step=Const(320), par=4) // Counter
      val x1598 = CounterChain(name = "x1598", ctr2)
    }
    val x1599_dsp0 = MemoryPipeline(name="x1599_dsp0",parent="x1837") { implicit CU => 
      val x1623_x1623 =  VectorFIFO(size=1).wtPort(x1608_x1617_data_v)
      val x1619 = CounterChain.copy("x1624", "x1619")
      val x1769 = CounterChain.copy("x1780", "x1769")
      val x1599_x1771 =  SRAM(size=320,banking = Strided(1)).wtPort(x1623_x1623.readPort).rdPort(x1599_x1771_x1780_v).rdAddr(x1769(0)).wtAddr(x1619(0))
      var stage: List[Stage] = Nil
    }
    val x1600_dsp0 = MemoryPipeline(name="x1600_dsp0",parent="x1837") { implicit CU => 
      val x1662_x1662 =  VectorFIFO(size=1).wtPort(x1647_x1656_data_v)
      val x1658 = CounterChain.copy("x1663", "x1658")
      val x1782 = CounterChain.copy("x1793", "x1782")
      val x1600_x1784 =  SRAM(size=320,banking = Strided(1)).wtPort(x1662_x1662.readPort).rdPort(x1600_x1784_x1793_v).rdAddr(x1782(0)).wtAddr(x1658(0))
      var stage: List[Stage] = Nil
    }
    val x1601_dsp0 = MemoryPipeline(name="x1601_dsp0",parent="x1837") { implicit CU => 
      val x1701_x1701 =  VectorFIFO(size=1).wtPort(x1686_x1695_data_v)
      val x1697 = CounterChain.copy("x1702", "x1697")
      val x1795 = CounterChain.copy("x1806", "x1795")
      val x1601_x1797 =  SRAM(size=320,banking = Strided(1)).wtPort(x1701_x1701.readPort).rdPort(x1601_x1797_x1806_v).rdAddr(x1795(0)).wtAddr(x1697(0))
      var stage: List[Stage] = Nil
    }
    val x1602_dsp0 = MemoryPipeline(name="x1602_dsp0",parent="x1837") { implicit CU => 
      val x1740_x1740 =  VectorFIFO(size=1).wtPort(x1725_x1734_data_v)
      val x1736 = CounterChain.copy("x1741", "x1736")
      val x1808 = CounterChain.copy("x1819", "x1808")
      val x1602_x1810 =  SRAM(size=320,banking = Strided(1)).wtPort(x1740_x1740.readPort).rdPort(x1602_x1810_x1819_v).rdAddr(x1808(0)).wtAddr(x1736(0))
      var stage: List[Stage] = Nil
    }
    val x1603_dsp0 = MemoryPipeline(name="x1603_dsp0",parent="x1837") { implicit CU => 
      val x1642_x1642 =  VectorFIFO(size=1).wtPort(x1627_x1636_data_v)
      val x1638 = CounterChain.copy("x1643", "x1638")
      val x1769 = CounterChain.copy("x1780", "x1769")
      val x1603_x1772 =  SRAM(size=320,banking = Strided(1)).wtPort(x1642_x1642.readPort).rdPort(x1603_x1772_x1780_v).rdAddr(x1769(0)).wtAddr(x1638(0))
      var stage: List[Stage] = Nil
    }
    val x1604_dsp0 = MemoryPipeline(name="x1604_dsp0",parent="x1837") { implicit CU => 
      val x1681_x1681 =  VectorFIFO(size=1).wtPort(x1666_x1675_data_v)
      val x1677 = CounterChain.copy("x1682", "x1677")
      val x1782 = CounterChain.copy("x1793", "x1782")
      val x1604_x1785 =  SRAM(size=320,banking = Strided(1)).wtPort(x1681_x1681.readPort).rdPort(x1604_x1785_x1793_v).rdAddr(x1782(0)).wtAddr(x1677(0))
      var stage: List[Stage] = Nil
    }
    val x1605_dsp0 = MemoryPipeline(name="x1605_dsp0",parent="x1837") { implicit CU => 
      val x1720_x1720 =  VectorFIFO(size=1).wtPort(x1705_x1714_data_v)
      val x1716 = CounterChain.copy("x1721", "x1716")
      val x1795 = CounterChain.copy("x1806", "x1795")
      val x1605_x1798 =  SRAM(size=320,banking = Strided(1)).wtPort(x1720_x1720.readPort).rdPort(x1605_x1798_x1806_v).rdAddr(x1795(0)).wtAddr(x1716(0))
      var stage: List[Stage] = Nil
    }
    val x1606_dsp0 = MemoryPipeline(name="x1606_dsp0",parent="x1837") { implicit CU => 
      val x1759_x1759 =  VectorFIFO(size=1).wtPort(x1744_x1753_data_v)
      val x1755 = CounterChain.copy("x1760", "x1755")
      val x1808 = CounterChain.copy("x1819", "x1808")
      val x1606_x1811 =  SRAM(size=320,banking = Strided(1)).wtPort(x1759_x1759.readPort).rdPort(x1606_x1811_x1819_v).rdAddr(x1808(0)).wtAddr(x1755(0))
      var stage: List[Stage] = Nil
    }
    val x1625 = StreamController(name="x1625",parent=x1837) { implicit CU => 
      val ctr3 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1625_unit = CounterChain(name = "x1625_unit", ctr3)
    }
    val x1616 = Pipeline(name="x1616",parent=x1625) { implicit CU => 
      val x1610 = CU.temp
      val x1609 =  ScalarBuffer().wtPort(x1609_argin)
      val x1598 = CounterChain.copy("x1837", "x1598")
      val ctr4 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1616_unit = CounterChain(name = "x1616_unit", ctr4)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1598(0)), Const(4)), op=FixMul, results=List(x1610))
      Stage(operands=List(x1610, CU.load(x1609)), op=FixAdd, results=List(CU.scalarOut(x1607_b1860_x1615_b1862_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x1607_b1861_x1615_b1863_s)))
    }
    val x1617 = MemoryController(name="x1617",parent=x1625,offchip=x1589_oc, mctpe=TileLoad) { implicit CU => 
      val x1607_b1861_x1617 =  ScalarFIFO(name="size",size=1).wtPort(x1607_b1861_x1615_b1863_s)
      val x1607_b1860_x1617 =  ScalarFIFO(name="offset",size=1).wtPort(x1607_b1860_x1615_b1862_s)
      CU.newVout("data", x1608_x1617_data_v)
    }
    val x1624 = Pipeline(name="x1624",parent=x1625) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1619 = CounterChain(name = "x1619", ctr5)
      var stage: List[Stage] = Nil
    }
    val x1644 = StreamController(name="x1644",parent=x1837) { implicit CU => 
      val ctr6 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1644_unit = CounterChain(name = "x1644_unit", ctr6)
    }
    val x1635 = Pipeline(name="x1635",parent=x1644) { implicit CU => 
      val x1629 = CU.temp
      val x1628 =  ScalarBuffer().wtPort(x1628_argin)
      val x1598 = CounterChain.copy("x1837", "x1598")
      val ctr7 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1635_unit = CounterChain(name = "x1635_unit", ctr7)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1598(0)), Const(4)), op=FixMul, results=List(x1629))
      Stage(operands=List(x1629, CU.load(x1628)), op=FixAdd, results=List(CU.scalarOut(x1626_b1864_x1634_b1866_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x1626_b1865_x1634_b1867_s)))
    }
    val x1636 = MemoryController(name="x1636",parent=x1644,offchip=x1591_oc, mctpe=TileLoad) { implicit CU => 
      val x1626_b1865_x1636 =  ScalarFIFO(name="size",size=1).wtPort(x1626_b1865_x1634_b1867_s)
      val x1626_b1864_x1636 =  ScalarFIFO(name="offset",size=1).wtPort(x1626_b1864_x1634_b1866_s)
      CU.newVout("data", x1627_x1636_data_v)
    }
    val x1643 = Pipeline(name="x1643",parent=x1644) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1638 = CounterChain(name = "x1638", ctr8)
      var stage: List[Stage] = Nil
    }
    val x1664 = StreamController(name="x1664",parent=x1837) { implicit CU => 
      val ctr9 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1664_unit = CounterChain(name = "x1664_unit", ctr9)
    }
    val x1655 = Pipeline(name="x1655",parent=x1664) { implicit CU => 
      val x1649 = CU.temp
      val x1648 =  ScalarBuffer().wtPort(x1648_argin)
      val x1598 = CounterChain.copy("x1837", "x1598")
      val ctr10 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1655_unit = CounterChain(name = "x1655_unit", ctr10)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1598(0)), Const(4)), op=FixMul, results=List(x1649))
      Stage(operands=List(x1649, CU.load(x1648)), op=FixAdd, results=List(CU.scalarOut(x1646_b1868_x1654_b1870_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x1646_b1869_x1654_b1871_s)))
    }
    val x1656 = MemoryController(name="x1656",parent=x1664,offchip=x1589_oc, mctpe=TileLoad) { implicit CU => 
      val x1646_b1868_x1656 =  ScalarFIFO(name="offset",size=1).wtPort(x1646_b1868_x1654_b1870_s)
      val x1646_b1869_x1656 =  ScalarFIFO(name="size",size=1).wtPort(x1646_b1869_x1654_b1871_s)
      CU.newVout("data", x1647_x1656_data_v)
    }
    val x1663 = Pipeline(name="x1663",parent=x1664) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1658 = CounterChain(name = "x1658", ctr11)
      var stage: List[Stage] = Nil
    }
    val x1683 = StreamController(name="x1683",parent=x1837) { implicit CU => 
      val ctr12 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1683_unit = CounterChain(name = "x1683_unit", ctr12)
    }
    val x1674 = Pipeline(name="x1674",parent=x1683) { implicit CU => 
      val x1668 = CU.temp
      val x1667 =  ScalarBuffer().wtPort(x1667_argin)
      val x1598 = CounterChain.copy("x1837", "x1598")
      val ctr13 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1674_unit = CounterChain(name = "x1674_unit", ctr13)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1598(0)), Const(4)), op=FixMul, results=List(x1668))
      Stage(operands=List(x1668, CU.load(x1667)), op=FixAdd, results=List(CU.scalarOut(x1665_b1872_x1673_b1874_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x1665_b1873_x1673_b1875_s)))
    }
    val x1675 = MemoryController(name="x1675",parent=x1683,offchip=x1591_oc, mctpe=TileLoad) { implicit CU => 
      val x1665_b1873_x1675 =  ScalarFIFO(name="size",size=1).wtPort(x1665_b1873_x1673_b1875_s)
      val x1665_b1872_x1675 =  ScalarFIFO(name="offset",size=1).wtPort(x1665_b1872_x1673_b1874_s)
      CU.newVout("data", x1666_x1675_data_v)
    }
    val x1682 = Pipeline(name="x1682",parent=x1683) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1677 = CounterChain(name = "x1677", ctr14)
      var stage: List[Stage] = Nil
    }
    val x1703 = StreamController(name="x1703",parent=x1837) { implicit CU => 
      val ctr15 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1703_unit = CounterChain(name = "x1703_unit", ctr15)
    }
    val x1694 = Pipeline(name="x1694",parent=x1703) { implicit CU => 
      val x1688 = CU.temp
      val x1687 =  ScalarBuffer().wtPort(x1687_argin)
      val x1598 = CounterChain.copy("x1837", "x1598")
      val ctr16 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1694_unit = CounterChain(name = "x1694_unit", ctr16)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1598(0)), Const(4)), op=FixMul, results=List(x1688))
      Stage(operands=List(x1688, CU.load(x1687)), op=FixAdd, results=List(CU.scalarOut(x1685_b1876_x1693_b1878_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x1685_b1877_x1693_b1879_s)))
    }
    val x1695 = MemoryController(name="x1695",parent=x1703,offchip=x1589_oc, mctpe=TileLoad) { implicit CU => 
      val x1685_b1877_x1695 =  ScalarFIFO(name="size",size=1).wtPort(x1685_b1877_x1693_b1879_s)
      val x1685_b1876_x1695 =  ScalarFIFO(name="offset",size=1).wtPort(x1685_b1876_x1693_b1878_s)
      CU.newVout("data", x1686_x1695_data_v)
    }
    val x1702 = Pipeline(name="x1702",parent=x1703) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1697 = CounterChain(name = "x1697", ctr17)
      var stage: List[Stage] = Nil
    }
    val x1722 = StreamController(name="x1722",parent=x1837) { implicit CU => 
      val ctr18 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1722_unit = CounterChain(name = "x1722_unit", ctr18)
    }
    val x1713 = Pipeline(name="x1713",parent=x1722) { implicit CU => 
      val x1707 = CU.temp
      val x1706 =  ScalarBuffer().wtPort(x1706_argin)
      val x1598 = CounterChain.copy("x1837", "x1598")
      val ctr19 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1713_unit = CounterChain(name = "x1713_unit", ctr19)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1598(0)), Const(4)), op=FixMul, results=List(x1707))
      Stage(operands=List(x1707, CU.load(x1706)), op=FixAdd, results=List(CU.scalarOut(x1704_b1880_x1712_b1882_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x1704_b1881_x1712_b1883_s)))
    }
    val x1714 = MemoryController(name="x1714",parent=x1722,offchip=x1591_oc, mctpe=TileLoad) { implicit CU => 
      val x1704_b1880_x1714 =  ScalarFIFO(name="offset",size=1).wtPort(x1704_b1880_x1712_b1882_s)
      val x1704_b1881_x1714 =  ScalarFIFO(name="size",size=1).wtPort(x1704_b1881_x1712_b1883_s)
      CU.newVout("data", x1705_x1714_data_v)
    }
    val x1721 = Pipeline(name="x1721",parent=x1722) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1716 = CounterChain(name = "x1716", ctr20)
      var stage: List[Stage] = Nil
    }
    val x1742 = StreamController(name="x1742",parent=x1837) { implicit CU => 
      val ctr21 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1742_unit = CounterChain(name = "x1742_unit", ctr21)
    }
    val x1733 = Pipeline(name="x1733",parent=x1742) { implicit CU => 
      val x1727 = CU.temp
      val x1726 =  ScalarBuffer().wtPort(x1726_argin)
      val x1598 = CounterChain.copy("x1837", "x1598")
      val ctr22 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1733_unit = CounterChain(name = "x1733_unit", ctr22)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1598(0)), Const(4)), op=FixMul, results=List(x1727))
      Stage(operands=List(x1727, CU.load(x1726)), op=FixAdd, results=List(CU.scalarOut(x1724_b1884_x1732_b1886_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x1724_b1885_x1732_b1887_s)))
    }
    val x1734 = MemoryController(name="x1734",parent=x1742,offchip=x1589_oc, mctpe=TileLoad) { implicit CU => 
      val x1724_b1885_x1734 =  ScalarFIFO(name="size",size=1).wtPort(x1724_b1885_x1732_b1887_s)
      val x1724_b1884_x1734 =  ScalarFIFO(name="offset",size=1).wtPort(x1724_b1884_x1732_b1886_s)
      CU.newVout("data", x1725_x1734_data_v)
    }
    val x1741 = Pipeline(name="x1741",parent=x1742) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1736 = CounterChain(name = "x1736", ctr23)
      var stage: List[Stage] = Nil
    }
    val x1761 = StreamController(name="x1761",parent=x1837) { implicit CU => 
      val ctr24 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1761_unit = CounterChain(name = "x1761_unit", ctr24)
    }
    val x1752 = Pipeline(name="x1752",parent=x1761) { implicit CU => 
      val x1746 = CU.temp
      val x1745 =  ScalarBuffer().wtPort(x1745_argin)
      val x1598 = CounterChain.copy("x1837", "x1598")
      val ctr25 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1752_unit = CounterChain(name = "x1752_unit", ctr25)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x1598(0)), Const(4)), op=FixMul, results=List(x1746))
      Stage(operands=List(x1746, CU.load(x1745)), op=FixAdd, results=List(CU.scalarOut(x1743_b1888_x1751_b1890_s)))
      Stage(operands=List(Const(1280)), op=Bypass, results=List(CU.scalarOut(x1743_b1889_x1751_b1891_s)))
    }
    val x1753 = MemoryController(name="x1753",parent=x1761,offchip=x1591_oc, mctpe=TileLoad) { implicit CU => 
      val x1743_b1889_x1753 =  ScalarFIFO(name="size",size=1).wtPort(x1743_b1889_x1751_b1891_s)
      val x1743_b1888_x1753 =  ScalarFIFO(name="offset",size=1).wtPort(x1743_b1888_x1751_b1890_s)
      CU.newVout("data", x1744_x1753_data_v)
    }
    val x1760 = Pipeline(name="x1760",parent=x1761) { implicit CU => 
      val ctr26 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1755 = CounterChain(name = "x1755", ctr26)
      var stage: List[Stage] = Nil
    }
    val x1780 = Pipeline(name="x1780",parent=x1837) { implicit CU => 
      val x1772_x1772 =  VectorFIFO(size=1).wtPort(x1603_x1772_x1780_v)
      val x1771_x1771 =  VectorFIFO(size=1).wtPort(x1599_x1771_x1780_v)
      val ctr27 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1769 = CounterChain(name = "x1769", ctr27)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1771_x1771), CU.load(x1772_x1772)), op=FixMul, results=List(CU.reduce))
      val (_, rr504) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr504), op=Bypass, results=List(CU.scalarOut(x1764_x1778_s)))
    }
    val x1793 = Pipeline(name="x1793",parent=x1837) { implicit CU => 
      val x1784_x1784 =  VectorFIFO(size=1).wtPort(x1600_x1784_x1793_v)
      val x1785_x1785 =  VectorFIFO(size=1).wtPort(x1604_x1785_x1793_v)
      val ctr28 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1782 = CounterChain(name = "x1782", ctr28)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1784_x1784), CU.load(x1785_x1785)), op=FixMul, results=List(CU.reduce))
      val (_, rr509) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr509), op=Bypass, results=List(CU.scalarOut(x1765_x1791_s)))
    }
    val x1806 = Pipeline(name="x1806",parent=x1837) { implicit CU => 
      val x1797_x1797 =  VectorFIFO(size=1).wtPort(x1601_x1797_x1806_v)
      val x1798_x1798 =  VectorFIFO(size=1).wtPort(x1605_x1798_x1806_v)
      val ctr29 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1795 = CounterChain(name = "x1795", ctr29)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1797_x1797), CU.load(x1798_x1798)), op=FixMul, results=List(CU.reduce))
      val (_, rr514) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr514), op=Bypass, results=List(CU.scalarOut(x1766_x1804_s)))
    }
    val x1819 = Pipeline(name="x1819",parent=x1837) { implicit CU => 
      val x1811_x1811 =  VectorFIFO(size=1).wtPort(x1606_x1811_x1819_v)
      val x1810_x1810 =  VectorFIFO(size=1).wtPort(x1602_x1810_x1819_v)
      val ctr30 = Counter(min=Const(0), max=Const(320), step=Const(1), par=16) // Counter
      val x1808 = CounterChain(name = "x1808", ctr30)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1810_x1810), CU.load(x1811_x1811)), op=FixMul, results=List(CU.reduce))
      val (_, rr519) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr519), op=Bypass, results=List(CU.scalarOut(x1767_x1817_s)))
    }
    val x1835 = Pipeline(name="x1835",parent=x1837) { implicit CU => 
      val x1826 = CU.temp
      val x1829 = CU.temp
      val x1764_x1823 =  ScalarBuffer().wtPort(x1764_x1778_s)
      val x1766_x1825 =  ScalarBuffer().wtPort(x1766_x1804_s)
      val x1765_x1822 =  ScalarBuffer().wtPort(x1765_x1791_s)
      val x1767_x1824 =  ScalarBuffer().wtPort(x1767_x1817_s)
      val ctr31 = Counter(min=Const(1), max=Const(1), step=Const(1), par=1) // Counter
      val x1835_unit = CounterChain(name = "x1835_unit", ctr31)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x1764_x1823), CU.load(x1765_x1822)), op=FixAdd, results=List(x1826))
      Stage(operands=List(CU.load(x1766_x1825), CU.load(x1767_x1824)), op=FixAdd, results=List(x1829))
      Stage(operands=List(x1826, x1829), op=FixAdd, results=List(CU.reduce))
      val (_, rr526) = Stage.reduce(op=FixAdd, init=Const(0))
      Stage(operands=List(rr526), op=Bypass, results=List(CU.scalarOut(x1592_x1839_argout)))
    }
    
  }
}
