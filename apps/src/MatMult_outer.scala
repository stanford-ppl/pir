import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object MatMult_outer extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x1588_argin = ArgIn("x1588")
    val x1649_b1777_x1660_b1780_scalar = Scalar("x1649_b1777_x1660_b1780")
    val x1706_b1796_x1717_b1801_scalar = Scalar("x1706_b1796_x1717_b1801")
    val x1710_argin = ArgIn("x1710")
    val x1706_b1797_x1717_b1802_scalar = Scalar("x1706_b1797_x1717_b1802")
    val x1650_x1661_scalar = Scalar("x1650_x1661")
    val x1617_x1643_vector = Vector("x1617_x1643")
    val x1678_x1693_x1701_vector = Vector("x1678_x1693_x1701")
    val x1621_b1768_x1632_b1771_scalar = Scalar("x1621_b1768_x1632_b1771")
    val x1613_x1700_vector = Vector("x1613_x1700")
    val x1652_argin = ArgIn("x1652")
    val x1707_x1718_scalar = Scalar("x1707_x1718")
    val x1649_b1778_x1660_b1781_scalar = Scalar("x1649_b1778_x1660_b1781")
    val x1621_b1769_x1632_b1772_scalar = Scalar("x1621_b1769_x1632_b1772")
    val x1622_x1633_scalar = Scalar("x1622_x1633")
    val x1590_argin = ArgIn("x1590")
    val x1621_b1770_x1632_b1773_scalar = Scalar("x1621_b1770_x1632_b1773")
    val x1678_x1688_vector = Vector("x1678_x1688")
    val x1599_oc = OffChip("x1599")
    val x1605_oc = OffChip("x1605")
    val x1613_x1722_x1727_vector = Vector("x1613_x1722_x1727")
    val x1596_oc = OffChip("x1596")
    val x1618_x1683_x1689_vector = Vector("x1618_x1683_x1689")
    val x1624_argin = ArgIn("x1624")
    val x1706_b1798_x1717_b1803_scalar = Scalar("x1706_b1798_x1717_b1803")
    val x1708_b1799_x1726_b1806_vector = Vector("x1708_b1799_x1726_b1806")
    val x1618_x1671_vector = Vector("x1618_x1671")
    val x1589_argin = ArgIn("x1589")
    val x1649_b1776_x1660_b1779_scalar = Scalar("x1649_b1776_x1660_b1779")
    val x1708_b1800_x1726_b1807_vector = Vector("x1708_b1800_x1726_b1807")
    val x1613_x1694_x1701_vector = Vector("x1613_x1694_x1701")
    val x1617_x1682_x1689_vector = Vector("x1617_x1682_x1689")
    val x1735 = Sequential(name="x1735",parent=top) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1734 = MetaPipeline(name="x1734",parent=x1735) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, x1588_x1610.load, Const("4i").out) // Counter
      val ctr2 = (Const("0i").out, x1589_x1608.load, Const("64i").out) // Counter
      val x1612 = CounterChain(name = "x1612", ctr1, ctr2)
      val x1589_x1608 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1589_argin).rdPort(None)
      val x1588_x1610 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1588_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1613_dsp0 = MemoryPipeline(name="x1613_dsp0",parent="x1702") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr204 = CU.temp
      val tr205 = CU.temp
      val tr207 = CU.temp
      val tr208 = CU.temp
      val x1677 = CounterChain.copy("x1702", "x1677")
      val x1692 = CounterChain.copy("x1701", "x1692")
      val x1705 = CounterChain.copy("x1733", "x1705")
      val x1721 = CounterChain.copy("x1727", "x1721")
      val x1613_x1722 = SRAM(size = 256, banking = Strided(1)).wtPort(x1613_x1700_vector).rdPort(x1613_x1722_x1727_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1613_x1722))
      Stage(stage(1), operands=List(x1692(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr204)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr204), CU.ctr(stage(1), x1692(1))), op=FixAdd, results=List(x1613_x1722.writeAddr, CU.temp(stage(2), tr205)))
      stage = stage0 +: RAStages(2, List(x1613_x1722))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1705(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr207)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr207), CU.ctr(stage(1), x1721(0))), op=FixAdd, results=List(x1613_x1722.readAddr, CU.temp(stage(2), tr208)))
    }
    val x1613_dsp1 = MemoryPipeline(name="x1613_dsp1",parent="x1702") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr211 = CU.temp
      val tr212 = CU.temp
      val tr214 = CU.temp
      val tr215 = CU.temp
      val x1677 = CounterChain.copy("x1702", "x1677")
      val x1692 = CounterChain.copy("x1701", "x1692")
      val x1705 = CounterChain.copy("x1733", "x1705")
      val x1721 = CounterChain.copy("x1727", "x1721")
      val x1613_x1694 = SRAM(size = 256, banking = Strided(1)).wtPort(x1613_x1700_vector).rdPort(x1613_x1722_x1727_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1613_x1694))
      Stage(stage(1), operands=List(x1692(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr211)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr211), CU.ctr(stage(1), x1692(1))), op=FixAdd, results=List(x1613_x1694.writeAddr, CU.temp(stage(2), tr212)))
      stage = stage0 +: RAStages(2, List(x1613_x1694))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1705(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr214)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr214), CU.ctr(stage(1), x1721(0))), op=FixAdd, results=List(x1613_x1694.readAddr, CU.temp(stage(2), tr215)))
    }
    val x1703 = MetaPipeline(name="x1703",parent=x1734) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr3 = (Const("0i").out, x1590_x1614.load, Const("64i").out) // Counter
      val x1616 = CounterChain(name = "x1616", ctr3)
      val x1590_x1614 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1590_argin).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1617_dsp0 = MemoryPipeline(name="x1617_dsp0",parent="x1703") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr218 = CU.temp
      val tr219 = CU.temp
      val tr221 = CU.temp
      val tr222 = CU.temp
      val x1620 = CounterChain.copy("x1646", "x1620")
      val x1639 = CounterChain.copy("x1644", "x1639")
      val x1677 = CounterChain.copy("x1702", "x1677")
      val x1681 = CounterChain.copy("x1689", "x1681")
      val x1617_x1682 = SRAM(size = 256, banking = Strided(1)).wtPort(x1617_x1643_vector).rdPort(x1617_x1682_x1689_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1617_x1682))
      Stage(stage(1), operands=List(x1620(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr218)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr218), CU.ctr(stage(1), x1639(0))), op=FixAdd, results=List(x1617_x1682.writeAddr, CU.temp(stage(2), tr219)))
      stage = stage0 +: RAStages(2, List(x1617_x1682))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1681(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr221)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr221), CU.ctr(stage(1), x1677(0))), op=FixAdd, results=List(x1617_x1682.readAddr, CU.temp(stage(2), tr222)))
    }
    val x1618_dsp0 = MemoryPipeline(name="x1618_dsp0",parent="x1703") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr225 = CU.temp
      val tr226 = CU.temp
      val tr228 = CU.temp
      val tr229 = CU.temp
      val x1648 = CounterChain.copy("x1674", "x1648")
      val x1667 = CounterChain.copy("x1672", "x1667")
      val x1677 = CounterChain.copy("x1702", "x1677")
      val x1681 = CounterChain.copy("x1689", "x1681")
      val x1618_x1683 = SRAM(size = 4096, banking = Strided(1)).wtPort(x1618_x1671_vector).rdPort(x1618_x1683_x1689_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1618_x1683))
      Stage(stage(1), operands=List(x1648(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr225)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr225), CU.ctr(stage(1), x1667(0))), op=FixAdd, results=List(x1618_x1683.writeAddr, CU.temp(stage(2), tr226)))
      stage = stage0 +: RAStages(2, List(x1618_x1683))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1677(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr228)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr228), CU.ctr(stage(1), x1681(1))), op=FixAdd, results=List(x1618_x1683.readAddr, CU.temp(stage(2), tr229)))
    }
    val x1646 = StreamController(name="x1646",parent=x1703) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr4 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1620 = CounterChain(name = "x1620", ctr4)
      var stage: List[Stage] = Nil
    }
    val x1634 = UnitPipeline(name="x1634",parent=x1646) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr231 = CU.temp
      val tr232 = CU.temp
      val tr233 = CU.temp
      val tr235 = CU.temp
      val tr236 = CU.temp
      val tr237 = CU.temp
      val tr238 = CU.temp
      val tr239 = CU.temp
      val tr240 = CU.temp
      val x1612 = CounterChain.copy("x1734", "x1612")
      val x1620 = CounterChain.copy("x1646", "x1620")
      val x1616 = CounterChain.copy("x1703", "x1616")
      val x1590_x1625 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1590_argin).rdPort(None)
      val x1624 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1624_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(10)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1612(0)), CU.ctr(stage(0), x1620(0))), op=FixAdd, results=List(CU.temp(stage(1), tr231)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr231), CU.load(stage(1), x1590_x1625)), op=FixMul, results=List(CU.temp(stage(2), tr232)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr232), CU.ctr(stage(2), x1616(0))), op=FixAdd, results=List(CU.temp(stage(3), tr233)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr233), Const("4i")), op=FixMul, results=List(CU.temp(stage(4), tr235)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr235), CU.load(stage(4), x1624)), op=FixAdd, results=List(CU.temp(stage(5), tr236)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr236), Const("4i")), op=FixMul, results=List(CU.temp(stage(6), tr237)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr238)), op=Bypass, results=List(CU.scalarOut(stage(7), x1621_b1768_x1632_b1771_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr239)), op=Bypass, results=List(CU.scalarOut(stage(8), x1621_b1769_x1632_b1772_scalar)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr240)), op=Bypass, results=List(CU.scalarOut(stage(9), x1621_b1770_x1632_b1773_scalar)))
      Stage(stage(10), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(10), x1622_x1633_scalar)))
    }
    val x1635 = Fringe(name="x1635",parent=x1646,dram=x1596_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1621_b1770_x1635 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1621_b1770_x1632_b1773_scalar).rdPort(None)
      val x1621_b1769_x1635 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1621_b1769_x1632_b1772_scalar).rdPort(None)
      val x1621_b1768_x1635 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1621_b1768_x1632_b1771_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1645 = Sequential(name="x1645",parent=x1646) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1637 = Sequential(name="x1637",parent=x1645) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1644 = Pipeline(name="x1644",parent=x1645) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1620 = CounterChain.copy("x1646", "x1620")
      val ctr5 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1639 = CounterChain(name = "x1639", ctr5)
      val x1623_x1640 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1623_x1640.load), op=Bypass, results=List(CU.vecOut(stage(1), x1617_x1643_vector)))
    }
    val x1674 = StreamController(name="x1674",parent=x1703) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr6 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1648 = CounterChain(name = "x1648", ctr6)
      var stage: List[Stage] = Nil
    }
    val x1662 = UnitPipeline(name="x1662",parent=x1674) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr242 = CU.temp
      val tr243 = CU.temp
      val tr244 = CU.temp
      val tr246 = CU.temp
      val tr247 = CU.temp
      val tr248 = CU.temp
      val tr249 = CU.temp
      val tr250 = CU.temp
      val tr251 = CU.temp
      val x1616 = CounterChain.copy("x1703", "x1616")
      val x1648 = CounterChain.copy("x1674", "x1648")
      val x1612 = CounterChain.copy("x1734", "x1612")
      val x1652 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1652_argin).rdPort(None)
      val x1589_x1653 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1589_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(10)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1616(0)), CU.ctr(stage(0), x1648(0))), op=FixAdd, results=List(CU.temp(stage(1), tr242)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr242), CU.load(stage(1), x1589_x1653)), op=FixMul, results=List(CU.temp(stage(2), tr243)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr243), CU.ctr(stage(2), x1612(1))), op=FixAdd, results=List(CU.temp(stage(3), tr244)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr244), Const("4i")), op=FixMul, results=List(CU.temp(stage(4), tr246)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr246), CU.load(stage(4), x1652)), op=FixAdd, results=List(CU.temp(stage(5), tr247)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr247), Const("4i")), op=FixMul, results=List(CU.temp(stage(6), tr248)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr249)), op=Bypass, results=List(CU.scalarOut(stage(7), x1649_b1776_x1660_b1779_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr250)), op=Bypass, results=List(CU.scalarOut(stage(8), x1649_b1777_x1660_b1780_scalar)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr251)), op=Bypass, results=List(CU.scalarOut(stage(9), x1649_b1778_x1660_b1781_scalar)))
      Stage(stage(10), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(10), x1650_x1661_scalar)))
    }
    val x1663 = Fringe(name="x1663",parent=x1674,dram=x1599_oc, mode=TileLoad) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1649_b1778_x1663 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1649_b1778_x1660_b1781_scalar).rdPort(None)
      val x1649_b1777_x1663 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1649_b1777_x1660_b1780_scalar).rdPort(None)
      val x1649_b1776_x1663 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1649_b1776_x1660_b1779_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1673 = Sequential(name="x1673",parent=x1674) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1665 = Sequential(name="x1665",parent=x1673) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1672 = Pipeline(name="x1672",parent=x1673) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1648 = CounterChain.copy("x1674", "x1648")
      val ctr7 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1667 = CounterChain(name = "x1667", ctr7)
      val x1651_x1668 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(None).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x1651_x1668.load), op=Bypass, results=List(CU.vecOut(stage(1), x1618_x1671_vector)))
    }
    val x1702 = MetaPipeline(name="x1702",parent=x1703) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr8 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1677 = CounterChain(name = "x1677", ctr8)
      var stage: List[Stage] = Nil
    }
    val x1678_dsp0 = MemoryPipeline(name="x1678_dsp0",parent="x1702") { implicit CU => 
      val stage0 = CU.emptyStage
      val tr254 = CU.temp
      val tr255 = CU.temp
      val tr257 = CU.temp
      val tr258 = CU.temp
      val x1677 = CounterChain.copy("x1702", "x1677")
      val x1681 = CounterChain.copy("x1689", "x1681")
      val x1692 = CounterChain.copy("x1701", "x1692")
      val x1678_x1693 = SRAM(size = 256, banking = Strided(1)).wtPort(x1678_x1688_vector).rdPort(x1678_x1693_x1701_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x1678_x1693))
      Stage(stage(1), operands=List(x1681(0), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr254)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr254), CU.ctr(stage(1), x1681(1))), op=FixAdd, results=List(x1678_x1693.writeAddr, CU.temp(stage(2), tr255)))
      stage = stage0 +: RAStages(2, List(x1678_x1693))
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1692(0)), Const("64i")), op=FixMul, results=List(CU.temp(stage(1), tr257)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr257), CU.ctr(stage(1), x1692(1))), op=FixAdd, results=List(x1678_x1693.readAddr, CU.temp(stage(2), tr258)))
    }
    val x1689 = Pipeline(name="x1689",parent=x1702) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr260 = CU.temp
      val x1677 = CounterChain.copy("x1702", "x1677")
      val ctr9 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val ctr10 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1681 = CounterChain(name = "x1681", ctr9, ctr10)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.vecIn(stage(0), x1617_x1682_x1689_vector), CU.vecIn(stage(0), x1618_x1683_x1689_vector)), op=FixMul, results=List(CU.vecOut(stage(1), x1678_x1688_vector), CU.temp(stage(1), tr260)))
    }
    val x1701 = Pipeline(name="x1701",parent=x1702) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr261 = CU.temp
      val tr262 = CU.temp
      val tr263 = CU.temp
      val x1677 = CounterChain.copy("x1702", "x1677")
      val ctr11 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val ctr12 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1692 = CounterChain(name = "x1692", ctr11, ctr12)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1677(0)), Const("0i")), op=FixEql, results=List(CU.temp(stage(1), tr261)))
      Stage(stage(2), operands=List(CU.vecIn(stage(1), x1678_x1693_x1701_vector), CU.vecIn(stage(1), x1613_x1694_x1701_vector)), op=FixAdd, results=List(CU.temp(stage(2), tr262)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr261), CU.vecIn(stage(2), x1678_x1693_x1701_vector), CU.temp(stage(2), tr262)), op=Mux, results=List(CU.vecOut(stage(3), x1613_x1700_vector), CU.temp(stage(3), tr263)))
    }
    val x1733 = StreamController(name="x1733",parent=x1734) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr13 = (Const("0i").out, Const("4i").out, Const("1i").out) // Counter
      val x1705 = CounterChain(name = "x1705", ctr13)
      var stage: List[Stage] = Nil
    }
    val x1728 = Sequential(name="x1728",parent=x1733) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    val x1719 = UnitPipeline(name="x1719",parent=x1728) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr264 = CU.temp
      val tr265 = CU.temp
      val tr266 = CU.temp
      val tr267 = CU.temp
      val tr269 = CU.temp
      val tr270 = CU.temp
      val tr271 = CU.temp
      val tr272 = CU.temp
      val x1612 = CounterChain.copy("x1734", "x1612")
      val x1705 = CounterChain.copy("x1733", "x1705")
      val x1710 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1710_argin).rdPort(None)
      val x1589_x1711 = ScalarBuffer(size = 1, banking = NoBanking()).wtPort(x1589_argin).rdPort(None)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(9)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x1612(0)), CU.ctr(stage(0), x1705(0))), op=FixAdd, results=List(CU.temp(stage(1), tr264)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr264), CU.load(stage(1), x1589_x1711)), op=FixMul, results=List(CU.temp(stage(2), tr265)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr265), CU.ctr(stage(2), x1612(1))), op=FixAdd, results=List(CU.temp(stage(3), tr266)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr266), CU.load(stage(3), x1710)), op=FixAdd, results=List(CU.temp(stage(4), tr267)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr267), Const("4i")), op=FixMul, results=List(CU.temp(stage(5), tr269)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr270)), op=Bypass, results=List(CU.scalarOut(stage(6), x1706_b1796_x1717_b1801_scalar)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr271)), op=Bypass, results=List(CU.scalarOut(stage(7), x1706_b1797_x1717_b1802_scalar)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr272)), op=Bypass, results=List(CU.scalarOut(stage(8), x1706_b1798_x1717_b1803_scalar)))
      Stage(stage(9), operands=List(Const("64i")), op=Bypass, results=List(CU.scalarOut(stage(9), x1707_x1718_scalar)))
    }
    val x1727 = Pipeline(name="x1727",parent=x1728) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr274 = CU.temp
      val tr275 = CU.temp
      val x1705 = CounterChain.copy("x1733", "x1705")
      val ctr14 = (Const("0i").out, Const("64i").out, Const("1i").out) // Counter
      val x1721 = CounterChain(name = "x1721", ctr14)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.temp(stage(0), tr274)), op=Bypass, results=List(CU.vecOut(stage(1), x1708_b1799_x1726_b1806_vector)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr275)), op=Bypass, results=List(CU.vecOut(stage(2), x1708_b1800_x1726_b1807_vector)))
    }
    val x1729 = Fringe(name="x1729",parent=x1733,dram=x1605_oc, mode=TileStore) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1708_b1800_x1729 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x1708_b1800_x1726_b1807_vector).rdPort(None)
      val x1706_b1797_x1729 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1706_b1797_x1717_b1802_scalar).rdPort(None)
      val x1708_b1799_x1729 = VectorFIFO(size = 1, banking = Strided(1)).wtPort(x1708_b1799_x1726_b1806_vector).rdPort(None)
      val x1706_b1796_x1729 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1706_b1796_x1717_b1801_scalar).rdPort(None)
      val x1706_b1798_x1729 = ScalarFIFO(size = 1, banking = Strided(1)).wtPort(x1706_b1798_x1717_b1803_scalar).rdPort(None)
      var stage: List[Stage] = Nil
    }
    val x1732 = Sequential(name="x1732",parent=x1733) { implicit CU => 
      val stage0 = CU.emptyStage
      var stage: List[Stage] = Nil
    }
    
  }
}
