import pir.graph.{Mux => _, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object TPCHQ6_cb extends PIRApp {
  arch = {
    import pir.plasticine.graph._
    import pir.plasticine.main._
    new SwitchNetwork(
      new SwitchNetworkParam(numRows=4, numCols=4, numArgIns=5, numArgOuts=3, pattern=Checkerboard)
    ) {
      override def pcuAt(i:Int, j:Int) = {
        new PatternComputeUnit(new PatternComputeUnitParam{
          override val numRegs = 20
          override def config(cu:PatternComputeUnit)(implicit spade:Spade) = {
            cu.addRegstages(numStage=13, numOprds=3, ops)
            cu.addRdstages(numStage=4, numOprds=3, ops)
            cu.addRegstages(numStage=2, numOprds=3, ops)
            cu.numScalarBufs(4)
            cu.numVecBufs(cu.vins.size)
            cu.color(0 until numCtrs, CounterReg)
            cu.color(0, ReduceReg).color(1, AccumReg)
            cu.color(5 until 5 + cu.numScalarBufs, ScalarInReg)
            cu.color(5 until 5 + cu.souts.size, ScalarOutReg)
            cu.color(9 until 9 + cu.numVecBufs, VecInReg)
            cu.color(9 until 9 + cu.vouts.size, VecOutReg)
            cu.genConnections
          }
        })
      }
      config
    }
  }
  def main(top:Top) = {
    val x1563_argin = ArgIn("x1563").bound(32)
    val x1585_x1670_x1690_v = Vector("x1585_x1670_x1690")
    val x1573_x1699_argout = ArgOut("x1573_x1699")
    val x1624_b1734_x1632_b1736_s = Scalar("x1624_b1734_x1632_b1736")
    val x1582_x1667_x1690_v = Vector("x1582_x1667_x1690")
    val x1607_argin = ArgIn("x1607").bound(0)
    val x1605_b1729_x1613_b1731_s = Scalar("x1605_b1729_x1613_b1731")
    val x1572_oc = OffChip("x1572")
    val x1586_b1726_x1594_b1728_s = Scalar("x1586_b1726_x1594_b1728")
    val x1586_b1725_x1594_b1727_s = Scalar("x1586_b1725_x1594_b1727")
    val x1624_b1733_x1632_b1735_s = Scalar("x1624_b1733_x1632_b1735")
    val x1626_argin = ArgIn("x1626").bound(10)
    val x1606_x1615_data_v = Vector("x1606_x1615_data")
    val x1644_x1653_data_v = Vector("x1644_x1653_data")
    val x1605_b1730_x1613_b1732_s = Scalar("x1605_b1730_x1613_b1732")
    val x1643_b1738_x1651_b1740_s = Scalar("x1643_b1738_x1651_b1740")
    val x1587_x1596_data_v = Vector("x1587_x1596_data")
    val x1625_x1634_data_v = Vector("x1625_x1634_data")
    val x1570_oc = OffChip("x1570")
    val x1663_x1688_s = Scalar("x1663_x1688")
    val x1588_argin = ArgIn("x1588").bound(20)
    val x1643_b1737_x1651_b1739_s = Scalar("x1643_b1737_x1651_b1739")
    val x1583_x1669_x1690_v = Vector("x1583_x1669_x1690")
    val x1584_x1668_x1690_v = Vector("x1584_x1668_x1690")
    val x1566_oc = OffChip("x1566")
    val x1568_oc = OffChip("x1568")
    val x1645_argin = ArgIn("x1645").bound(30)
    val x1701 = Sequential(name="x1701",parent=top) { implicit CU => 
      val x1701_unit = CounterChain(name = "x1701_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1697 = MetaPipeline(name="x1697",parent=x1701) { implicit CU => 
      val x1563_x1579 =  ScalarBuffer().wtPort(x1563_argin)
      val ctr1 = Counter(min=Const(0), max=x1563_x1579.load, step=Const(32), par=1) // Counter
      val x1581 = CounterChain(name = "x1581", ctr1).iter(1)
    }
    val x1582_dsp0 = MemoryPipeline(name="x1582_dsp0",parent="x1697") { implicit CU => 
      val x1602_x1602 =  VectorFIFO(size=1).wtPort(x1587_x1596_data_v)
      val x1598 = CounterChain.copy("x1603", "x1598")
      val x1665 = CounterChain.copy("x1690", "x1665")
      val x1582_x1667 =  SRAM(size=32,banking = Strided(1)).wtPort(x1602_x1602.readPort).rdPort(x1582_x1667_x1690_v).rdAddr(x1665(0)).wtAddr(x1598(0))
    }
    val x1583_dsp0 = MemoryPipeline(name="x1583_dsp0",parent="x1697") { implicit CU => 
      val x1621_x1621 =  VectorFIFO(size=1).wtPort(x1606_x1615_data_v)
      val x1617 = CounterChain.copy("x1622", "x1617")
      val x1665 = CounterChain.copy("x1690", "x1665")
      val x1583_x1669 =  SRAM(size=32,banking = Strided(1)).wtPort(x1621_x1621.readPort).rdPort(x1583_x1669_x1690_v).rdAddr(x1665(0)).wtAddr(x1617(0))
    }
    val x1584_dsp0 = MemoryPipeline(name="x1584_dsp0",parent="x1697") { implicit CU => 
      val x1640_x1640 =  VectorFIFO(size=1).wtPort(x1625_x1634_data_v)
      val x1636 = CounterChain.copy("x1641", "x1636")
      val x1665 = CounterChain.copy("x1690", "x1665")
      val x1584_x1668 =  SRAM(size=32,banking = Strided(1)).wtPort(x1640_x1640.readPort).rdPort(x1584_x1668_x1690_v).rdAddr(x1665(0)).wtAddr(x1636(0))
    }
    val x1585_dsp0 = MemoryPipeline(name="x1585_dsp0",parent="x1697") { implicit CU => 
      val x1659_x1659 =  VectorFIFO(size=1).wtPort(x1644_x1653_data_v)
      val x1655 = CounterChain.copy("x1660", "x1655")
      val x1665 = CounterChain.copy("x1690", "x1665")
      val x1585_x1670 =  SRAM(size=32,banking = Strided(1)).wtPort(x1659_x1659.readPort).rdPort(x1585_x1670_x1690_v).rdAddr(x1665(0)).wtAddr(x1655(0))
    }
    val x1604 = StreamController(name="x1604",parent=x1697) { implicit CU => 
      val x1604_unit = CounterChain(name = "x1604_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1595 = Pipeline(name="x1595",parent=x1604) { implicit CU => 
      val x1589 = CU.temp
      val x1588 =  ScalarBuffer().wtPort(x1588_argin)
      val x1581 = CounterChain.copy("x1697", "x1581")
      val x1595_unit = CounterChain(name = "x1595_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1581(0)), Const(4)), op=FixMul, results=List(x1589))
      Stage(operands=List(x1589, CU.load(x1588)), op=FixAdd, results=List(CU.scalarOut(x1586_b1725_x1594_b1727_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x1586_b1726_x1594_b1728_s)))
    }
    val x1596 = MemoryController(name="x1596",parent=x1604,offchip=x1566_oc, mctpe=TileLoad) { implicit CU => 
      val x1586_b1725_x1596 =  ScalarFIFO(name="offset",size=1).wtPort(x1586_b1725_x1594_b1727_s)
      val x1586_b1726_x1596 =  ScalarFIFO(name="size",size=1).wtPort(x1586_b1726_x1594_b1728_s)
      CU.newVout("data", x1587_x1596_data_v)
    }
    val x1603 = Pipeline(name="x1603",parent=x1604) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16) // Counter
      val x1598 = CounterChain(name = "x1598", ctr2).iter(2)
    }
    val x1623 = StreamController(name="x1623",parent=x1697) { implicit CU => 
      val x1623_unit = CounterChain(name = "x1623_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1614 = Pipeline(name="x1614",parent=x1623) { implicit CU => 
      val x1608 = CU.temp
      val x1607 =  ScalarBuffer().wtPort(x1607_argin)
      val x1581 = CounterChain.copy("x1697", "x1581")
      val x1614_unit = CounterChain(name = "x1614_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1581(0)), Const(4)), op=FixMul, results=List(x1608))
      Stage(operands=List(x1608, CU.load(x1607)), op=FixAdd, results=List(CU.scalarOut(x1605_b1729_x1613_b1731_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x1605_b1730_x1613_b1732_s)))
    }
    val x1615 = MemoryController(name="x1615",parent=x1623,offchip=x1568_oc, mctpe=TileLoad) { implicit CU => 
      val x1605_b1730_x1615 =  ScalarFIFO(name="size",size=1).wtPort(x1605_b1730_x1613_b1732_s)
      val x1605_b1729_x1615 =  ScalarFIFO(name="offset",size=1).wtPort(x1605_b1729_x1613_b1731_s)
      CU.newVout("data", x1606_x1615_data_v)
    }
    val x1622 = Pipeline(name="x1622",parent=x1623) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16) // Counter
      val x1617 = CounterChain(name = "x1617", ctr3).iter(2)
    }
    val x1642 = StreamController(name="x1642",parent=x1697) { implicit CU => 
      val x1642_unit = CounterChain(name = "x1642_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1633 = Pipeline(name="x1633",parent=x1642) { implicit CU => 
      val x1627 = CU.temp
      val x1626 =  ScalarBuffer().wtPort(x1626_argin)
      val x1581 = CounterChain.copy("x1697", "x1581")
      val x1633_unit = CounterChain(name = "x1633_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1581(0)), Const(4)), op=FixMul, results=List(x1627))
      Stage(operands=List(x1627, CU.load(x1626)), op=FixAdd, results=List(CU.scalarOut(x1624_b1733_x1632_b1735_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x1624_b1734_x1632_b1736_s)))
    }
    val x1634 = MemoryController(name="x1634",parent=x1642,offchip=x1570_oc, mctpe=TileLoad) { implicit CU => 
      val x1624_b1734_x1634 =  ScalarFIFO(name="size",size=1).wtPort(x1624_b1734_x1632_b1736_s)
      val x1624_b1733_x1634 =  ScalarFIFO(name="offset",size=1).wtPort(x1624_b1733_x1632_b1735_s)
      CU.newVout("data", x1625_x1634_data_v)
    }
    val x1641 = Pipeline(name="x1641",parent=x1642) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16) // Counter
      val x1636 = CounterChain(name = "x1636", ctr4).iter(2)
    }
    val x1661 = StreamController(name="x1661",parent=x1697) { implicit CU => 
      val x1661_unit = CounterChain(name = "x1661_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x1652 = Pipeline(name="x1652",parent=x1661) { implicit CU => 
      val x1646 = CU.temp
      val x1645 =  ScalarBuffer().wtPort(x1645_argin)
      val x1581 = CounterChain.copy("x1697", "x1581")
      val x1652_unit = CounterChain(name = "x1652_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x1581(0)), Const(4)), op=FixMul, results=List(x1646))
      Stage(operands=List(x1646, CU.load(x1645)), op=FixAdd, results=List(CU.scalarOut(x1643_b1737_x1651_b1739_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x1643_b1738_x1651_b1740_s)))
    }
    val x1653 = MemoryController(name="x1653",parent=x1661,offchip=x1572_oc, mctpe=TileLoad) { implicit CU => 
      val x1643_b1737_x1653 =  ScalarFIFO(name="offset",size=1).wtPort(x1643_b1737_x1651_b1739_s)
      val x1643_b1738_x1653 =  ScalarFIFO(name="size",size=1).wtPort(x1643_b1738_x1651_b1740_s)
      CU.newVout("data", x1644_x1653_data_v)
    }
    val x1660 = Pipeline(name="x1660",parent=x1661) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16) // Counter
      val x1655 = CounterChain(name = "x1655", ctr5).iter(2)
    }
    val x1690 = Pipeline(name="x1690",parent=x1697) { implicit CU => 
      val x1677 = CU.temp
      val x1679 = CU.temp
      val x1682 = CU.temp
      val x1673 = CU.temp
      val x1685 = CU.temp
      val x1675 = CU.temp
      val x1680 = CU.temp
      val x1683 = CU.temp
      val x1674 = CU.temp
      val x1678 = CU.temp
      val x1668_x1668 =  VectorFIFO(size=1).wtPort(x1584_x1668_x1690_v)
      val x1670_x1670 =  VectorFIFO(size=1).wtPort(x1585_x1670_x1690_v)
      val x1667_x1667 =  VectorFIFO(size=1).wtPort(x1582_x1667_x1690_v)
      val x1669_x1669 =  VectorFIFO(size=1).wtPort(x1583_x1669_x1690_v)
      val ctr6 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16) // Counter
      val x1665 = CounterChain(name = "x1665", ctr6).iter(2)
      Stage(operands=List(Const(0), CU.load(x1667_x1667)), op=FixLt, results=List(x1673))
      Stage(operands=List(CU.load(x1667_x1667), Const(9999)), op=FixLt, results=List(x1674))
      Stage(operands=List(x1673, x1674), op=BitAnd, results=List(x1675))
      Stage(operands=List(Const(0), CU.load(x1668_x1668)), op=FixLeq, results=List(x1677))
      Stage(operands=List(x1675, x1677), op=BitAnd, results=List(x1678))
      Stage(operands=List(CU.load(x1668_x1668), Const(9999)), op=FixLeq, results=List(x1679))
      Stage(operands=List(x1678, x1679), op=BitAnd, results=List(x1680))
      Stage(operands=List(CU.load(x1669_x1669), Const(24)), op=FixLt, results=List(x1682))
      Stage(operands=List(x1680, x1682), op=BitAnd, results=List(x1683))
      Stage(operands=List(CU.load(x1670_x1670), CU.load(x1668_x1668)), op=FixMul, results=List(x1685))
      Stage(operands=List(x1683, x1685, Const(0)), op=Mux, results=List(CU.reduce))
      val (_, rr163) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1690")
      Stage(operands=List(rr163), op=Bypass, results=List(CU.scalarOut(x1663_x1688_s)))
    }
    val x1695 = Pipeline(name="x1695",parent=x1697) { implicit CU => 
      val x1663_x1692 =  ScalarBuffer().wtPort(x1663_x1688_s)
      val x1695_unit = CounterChain(name = "x1695_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x1663_x1692)), op=Bypass, results=List(CU.reduce))
      val (_, rr166) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x1697")
      Stage(operands=List(rr166), op=Bypass, results=List(CU.scalarOut(x1573_x1699_argout)))
    }
    
  }
}
