import pir.graph._
import pir.graph
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.PIRMisc._
import pir.PIRApp

object GDACompilerDesign extends PIRApp {
  override val arch = Config0
  def main(args: String*)(top:Top) = {
    val x1907_argin = ArgIn()
    val x2655_vector = Vector()
    val x2595_vector = Vector()
    val x1967_vector = Vector()
    val x1918_oc = OffChip()
    val x2659_vector = Vector()
    val x1919_oc = OffChip()
    val x2652_vector = Vector()
    val x1921_oc = OffChip()
    val x2599_scalar = Scalar()
    val x2623_vector = Vector()
    val x2647_vector = Vector()
    val x3049_vector = Vector()
    val x2653_vector = Vector()
    val x1916_oc = OffChip()
    val x1908_argin = ArgIn()
    val x2654_vector = Vector()
    val x2649_vector = Vector()
    val x2008_vector = Vector()
    val x2646_vector = Vector()
    val x2648_vector = Vector()
    val x2650_vector = Vector()
    val x2651_vector = Vector()
    val x2657_vector = Vector()
    val x2580_vector = Vector()
    val x2658_vector = Vector()
    val x1986_vector = Vector()
    val x2660_vector = Vector()
    val x1917_oc = OffChip()
    val x2645_vector = Vector()
    val x2656_vector = Vector()
    val x3094_scalar = Scalar()
    val x2606_mc_mc = MemoryController(TileLoad, x1916_oc)
    val x2581_mc_mc = MemoryController(TileLoad, x1917_oc)
    val x1968_mc_mc = MemoryController(TileLoad, x1918_oc)
    val x3100_mc_mc = MemoryController(TileStore, x1921_oc)
    val x1987_mc_mc = MemoryController(TileLoad, x1919_oc)
    val x3105 = ComputeUnit(name="x3105", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3105_unitCC = CounterChain(name = "x3105_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1985 = ComputeUnit(name="x1985", parent=x3105, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1985_unitCC = CounterChain(name = "x1985_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1968 = TileTransfer(name="x1968", parent=x1985, memctrl=x1968_mc_mc, mctpe=TileLoad, deps=List(), vec=x1967_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1968_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1968_cc = CounterChain(name = "x1968_cc", x1968_ctr)
      val x1969 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1970 = CounterChain(name = "x1970", x1969).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("0i"), CU.ctr(stage(0), x1968_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1968_mc_mc.saddr)))
    }
    val x2004 = ComputeUnit(name="x2004", parent=x3105, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2004_unitCC = CounterChain(name = "x2004_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1987 = TileTransfer(name="x1987", parent=x2004, memctrl=x1987_mc_mc, mctpe=TileLoad, deps=List(), vec=x1986_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1987_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1987_cc = CounterChain(name = "x1987_cc", x1987_ctr)
      val x1988 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1989 = CounterChain(name = "x1989", x1988).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("0i"), CU.ctr(stage(0), x1987_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1987_mc_mc.saddr)))
    }
    val x3048 = ComputeUnit(name="x3048", parent=x3105, tpe = MetaPipeline, deps=List(x1985, x2004)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2009 = (Const("0i").out, CU.scalarIn(stage0, x1907_argin).out, Const("96i").out) // Counter
      val x2010 = CounterChain(name = "x2010", x2009)
    }
    val x2593 = ComputeUnit(name="x2593", parent=x3048, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2593_unitCC = CounterChain(name = "x2593_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2581 = TileTransfer(name="x2581", parent=x2593, memctrl=x2581_mc_mc, mctpe=TileLoad, deps=List(), vec=x2580_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2010 = CounterChain.copy(x3048, "x2010")
      val x2581_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x2581_cc = CounterChain(name = "x2581_cc", x2581_ctr)
      val x2582 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2583 = CounterChain(name = "x2583", x2582).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2010(0)), CU.ctr(stage(0), x2581_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2581_mc_mc.saddr)))
    }
    val x2620 = ComputeUnit(name="x2620", parent=x3048, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2596 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2597 = CounterChain(name = "x2597", x2596)
    }
    val x2604 = UnitComputeUnit(name ="x2604", parent=x2620, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr848 = CU.temp
      val x2010 = CounterChain.copy(x3048, "x2010")
      val x2597 = CounterChain.copy(x2620, "x2597")
      val x2604_unitCC = CounterChain(name = "x2604_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2010(0)), CU.ctr(stage(0), x2597(0))), op=FixAdd, results=List(CU.temp(stage(1), tr848)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr848), CU.scalarIn(stage(1), x1908_argin)), op=FixMul, results=List(CU.scalarOut(stage(2), x2599_scalar)))
    }
    val x2606 = TileTransfer(name="x2606", parent=x2620, memctrl=x2606_mc_mc, mctpe=TileLoad, deps=List(x2604), vec=x2595_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2606_ctr = (Const("0l").out, CU.scalarIn(stage0, x1908_argin).out, Const("1l").out) // Counter
      val x2606_cc = CounterChain(name = "x2606_cc", x2606_ctr)
      val x2607 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2608 = CounterChain(name = "x2608", x2607).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2599_scalar), CU.ctr(stage(0), x2606_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2606_mc_mc.saddr)))
    }
    val x3026 = ComputeUnit(name="x3026", parent=x3048, tpe = MetaPipeline, deps=List(x2593, x2620)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2624 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2625 = CounterChain(name = "x2625", x2624)
    }
    val x2696 = ComputeUnit(name="x2696", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr875 = CU.temp
      val tr860 = CU.temp
      val tr865 = CU.temp
      val x2583 = CounterChain.copy(x2581, "x2583")
      val x2608 = CounterChain.copy(x2606, "x2608")
      val x1989 = CounterChain.copy(x1987, "x1989")
      val x2661 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2669 = CounterChain(name = "x2669", x2661)
      val x2625 = CounterChain.copy(x3026, "x2625")
      val x1970 = CounterChain.copy(x1968, "x1970")
      val x2597 = CounterChain.copy(x2620, "x2597")
      val x1965_x2686 = SRAM(size = 96, swapCtr = x1970(0), writeCtr = x1970(0), banking = Strided(1), doubleBuffer = false).wtPort(x1967_vector).rdAddr(x2669(0)).wtAddr(x1970(0))
      val x1966_x2685 = SRAM(size = 96, swapCtr = x1989(0), writeCtr = x1989(0), banking = Strided(1), doubleBuffer = false).wtPort(x1986_vector).rdAddr(x2669(0)).wtAddr(x1989(0))
      val x2578_x2683 = SRAM(size = 96, swapCtr = x2583(0), writeCtr = x2583(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2580_vector).rdAddr(x2625(0)).wtAddr(x2583(0))
      val x2579_x2681 = SRAM(size = 9216, swapCtr = x2597(0), writeCtr = x2608(0), banking = Strided(1), doubleBuffer = true).wtPort(x2595_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2579_x2681))
      Stage(stage(1), operands=List(x2597(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr860)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr860), CU.ctr(stage(1), x2608(0))), op=FixAdd, results=List(x2579_x2681.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2625(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr865)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr865), CU.ctr(stage(1), x2669(0))), op=FixAdd, results=List(x2579_x2681.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2578_x2683), CU.load(stage(2), x1966_x2685), CU.load(stage(2), x1965_x2686)), op=Mux, results=List(CU.temp(stage(3), tr875)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2579_x2681), CU.temp(stage(3), tr875)), op=FltSub, results=List(CU.vecOut(stage(4), x2645_vector)))
    }
    val x2716 = ComputeUnit(name="x2716", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr897 = CU.temp
      val tr887 = CU.temp
      val tr880 = CU.temp
      val x2583 = CounterChain.copy(x2581, "x2583")
      val x2608 = CounterChain.copy(x2606, "x2608")
      val x1989 = CounterChain.copy(x1987, "x1989")
      val x2662 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2670 = CounterChain(name = "x2670", x2662)
      val x2625 = CounterChain.copy(x3026, "x2625")
      val x1970 = CounterChain.copy(x1968, "x1970")
      val x2597 = CounterChain.copy(x2620, "x2597")
      val x1965_x2706 = SRAM(size = 96, swapCtr = x1970(0), writeCtr = x1970(0), banking = Strided(1), doubleBuffer = false).wtPort(x1967_vector).rdAddr(x2670(0)).wtAddr(x1970(0))
      val x1966_x2705 = SRAM(size = 96, swapCtr = x1989(0), writeCtr = x1989(0), banking = Strided(1), doubleBuffer = false).wtPort(x1986_vector).rdAddr(x2670(0)).wtAddr(x1989(0))
      val x2578_x2703 = SRAM(size = 96, swapCtr = x2583(0), writeCtr = x2583(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2580_vector).rdAddr(x2625(0)).wtAddr(x2583(0))
      val x2579_x2701 = SRAM(size = 9216, swapCtr = x2597(0), writeCtr = x2608(0), banking = Strided(1), doubleBuffer = true).wtPort(x2595_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2579_x2701))
      Stage(stage(1), operands=List(x2597(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr880)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr880), CU.ctr(stage(1), x2608(0))), op=FixAdd, results=List(x2579_x2701.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2625(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr887)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr887), CU.ctr(stage(1), x2670(0))), op=FixAdd, results=List(x2579_x2701.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2578_x2703), CU.load(stage(2), x1966_x2705), CU.load(stage(2), x1965_x2706)), op=Mux, results=List(CU.temp(stage(3), tr897)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2579_x2701), CU.temp(stage(3), tr897)), op=FltSub, results=List(CU.vecOut(stage(4), x2646_vector)))
    }
    val x2736 = ComputeUnit(name="x2736", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr909 = CU.temp
      val tr901 = CU.temp
      val tr919 = CU.temp
      val x2583 = CounterChain.copy(x2581, "x2583")
      val x2663 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2671 = CounterChain(name = "x2671", x2663)
      val x2608 = CounterChain.copy(x2606, "x2608")
      val x1989 = CounterChain.copy(x1987, "x1989")
      val x2625 = CounterChain.copy(x3026, "x2625")
      val x1970 = CounterChain.copy(x1968, "x1970")
      val x2597 = CounterChain.copy(x2620, "x2597")
      val x1965_x2726 = SRAM(size = 96, swapCtr = x1970(0), writeCtr = x1970(0), banking = Strided(1), doubleBuffer = false).wtPort(x1967_vector).rdAddr(x2671(0)).wtAddr(x1970(0))
      val x1966_x2725 = SRAM(size = 96, swapCtr = x1989(0), writeCtr = x1989(0), banking = Strided(1), doubleBuffer = false).wtPort(x1986_vector).rdAddr(x2671(0)).wtAddr(x1989(0))
      val x2578_x2723 = SRAM(size = 96, swapCtr = x2583(0), writeCtr = x2583(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2580_vector).rdAddr(x2625(0)).wtAddr(x2583(0))
      val x2579_x2721 = SRAM(size = 9216, swapCtr = x2597(0), writeCtr = x2608(0), banking = Strided(1), doubleBuffer = true).wtPort(x2595_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2579_x2721))
      Stage(stage(1), operands=List(x2597(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr901)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr901), CU.ctr(stage(1), x2608(0))), op=FixAdd, results=List(x2579_x2721.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2625(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr909)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr909), CU.ctr(stage(1), x2671(0))), op=FixAdd, results=List(x2579_x2721.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2578_x2723), CU.load(stage(2), x1966_x2725), CU.load(stage(2), x1965_x2726)), op=Mux, results=List(CU.temp(stage(3), tr919)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2579_x2721), CU.temp(stage(3), tr919)), op=FltSub, results=List(CU.vecOut(stage(4), x2647_vector)))
    }
    val x2756 = ComputeUnit(name="x2756", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr941 = CU.temp
      val tr931 = CU.temp
      val tr926 = CU.temp
      val x2583 = CounterChain.copy(x2581, "x2583")
      val x2608 = CounterChain.copy(x2606, "x2608")
      val x2664 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2672 = CounterChain(name = "x2672", x2664)
      val x1989 = CounterChain.copy(x1987, "x1989")
      val x2625 = CounterChain.copy(x3026, "x2625")
      val x1970 = CounterChain.copy(x1968, "x1970")
      val x2597 = CounterChain.copy(x2620, "x2597")
      val x1965_x2746 = SRAM(size = 96, swapCtr = x1970(0), writeCtr = x1970(0), banking = Strided(1), doubleBuffer = false).wtPort(x1967_vector).rdAddr(x2672(0)).wtAddr(x1970(0))
      val x1966_x2745 = SRAM(size = 96, swapCtr = x1989(0), writeCtr = x1989(0), banking = Strided(1), doubleBuffer = false).wtPort(x1986_vector).rdAddr(x2672(0)).wtAddr(x1989(0))
      val x2578_x2743 = SRAM(size = 96, swapCtr = x2583(0), writeCtr = x2583(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2580_vector).rdAddr(x2625(0)).wtAddr(x2583(0))
      val x2579_x2741 = SRAM(size = 9216, swapCtr = x2597(0), writeCtr = x2608(0), banking = Strided(1), doubleBuffer = true).wtPort(x2595_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2579_x2741))
      Stage(stage(1), operands=List(x2597(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr926)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr926), CU.ctr(stage(1), x2608(0))), op=FixAdd, results=List(x2579_x2741.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2625(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr931)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr931), CU.ctr(stage(1), x2672(0))), op=FixAdd, results=List(x2579_x2741.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2578_x2743), CU.load(stage(2), x1966_x2745), CU.load(stage(2), x1965_x2746)), op=Mux, results=List(CU.temp(stage(3), tr941)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2579_x2741), CU.temp(stage(3), tr941)), op=FltSub, results=List(CU.vecOut(stage(4), x2648_vector)))
    }
    val x2776 = ComputeUnit(name="x2776", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr963 = CU.temp
      val tr953 = CU.temp
      val tr945 = CU.temp
      val x2583 = CounterChain.copy(x2581, "x2583")
      val x2665 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2673 = CounterChain(name = "x2673", x2665)
      val x2608 = CounterChain.copy(x2606, "x2608")
      val x1989 = CounterChain.copy(x1987, "x1989")
      val x2625 = CounterChain.copy(x3026, "x2625")
      val x1970 = CounterChain.copy(x1968, "x1970")
      val x2597 = CounterChain.copy(x2620, "x2597")
      val x1965_x2766 = SRAM(size = 96, swapCtr = x1970(0), writeCtr = x1970(0), banking = Strided(1), doubleBuffer = false).wtPort(x1967_vector).rdAddr(x2673(0)).wtAddr(x1970(0))
      val x1966_x2765 = SRAM(size = 96, swapCtr = x1989(0), writeCtr = x1989(0), banking = Strided(1), doubleBuffer = false).wtPort(x1986_vector).rdAddr(x2673(0)).wtAddr(x1989(0))
      val x2578_x2763 = SRAM(size = 96, swapCtr = x2583(0), writeCtr = x2583(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2580_vector).rdAddr(x2625(0)).wtAddr(x2583(0))
      val x2579_x2761 = SRAM(size = 9216, swapCtr = x2597(0), writeCtr = x2608(0), banking = Strided(1), doubleBuffer = true).wtPort(x2595_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2579_x2761))
      Stage(stage(1), operands=List(x2597(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr945)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr945), CU.ctr(stage(1), x2608(0))), op=FixAdd, results=List(x2579_x2761.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2625(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr953)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr953), CU.ctr(stage(1), x2673(0))), op=FixAdd, results=List(x2579_x2761.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2578_x2763), CU.load(stage(2), x1966_x2765), CU.load(stage(2), x1965_x2766)), op=Mux, results=List(CU.temp(stage(3), tr963)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2579_x2761), CU.temp(stage(3), tr963)), op=FltSub, results=List(CU.vecOut(stage(4), x2649_vector)))
    }
    val x2796 = ComputeUnit(name="x2796", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr985 = CU.temp
      val tr969 = CU.temp
      val tr975 = CU.temp
      val x2583 = CounterChain.copy(x2581, "x2583")
      val x2608 = CounterChain.copy(x2606, "x2608")
      val x2666 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2674 = CounterChain(name = "x2674", x2666)
      val x1989 = CounterChain.copy(x1987, "x1989")
      val x2625 = CounterChain.copy(x3026, "x2625")
      val x1970 = CounterChain.copy(x1968, "x1970")
      val x2597 = CounterChain.copy(x2620, "x2597")
      val x1965_x2786 = SRAM(size = 96, swapCtr = x1970(0), writeCtr = x1970(0), banking = Strided(1), doubleBuffer = false).wtPort(x1967_vector).rdAddr(x2674(0)).wtAddr(x1970(0))
      val x1966_x2785 = SRAM(size = 96, swapCtr = x1989(0), writeCtr = x1989(0), banking = Strided(1), doubleBuffer = false).wtPort(x1986_vector).rdAddr(x2674(0)).wtAddr(x1989(0))
      val x2578_x2783 = SRAM(size = 96, swapCtr = x2583(0), writeCtr = x2583(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2580_vector).rdAddr(x2625(0)).wtAddr(x2583(0))
      val x2579_x2781 = SRAM(size = 9216, swapCtr = x2597(0), writeCtr = x2608(0), banking = Strided(1), doubleBuffer = true).wtPort(x2595_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2579_x2781))
      Stage(stage(1), operands=List(x2597(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr969)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr969), CU.ctr(stage(1), x2608(0))), op=FixAdd, results=List(x2579_x2781.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2625(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr975)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr975), CU.ctr(stage(1), x2674(0))), op=FixAdd, results=List(x2579_x2781.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2578_x2783), CU.load(stage(2), x1966_x2785), CU.load(stage(2), x1965_x2786)), op=Mux, results=List(CU.temp(stage(3), tr985)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2579_x2781), CU.temp(stage(3), tr985)), op=FltSub, results=List(CU.vecOut(stage(4), x2650_vector)))
    }
    val x2816 = ComputeUnit(name="x2816", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1007 = CU.temp
      val tr997 = CU.temp
      val tr991 = CU.temp
      val x2583 = CounterChain.copy(x2581, "x2583")
      val x2667 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2675 = CounterChain(name = "x2675", x2667)
      val x2608 = CounterChain.copy(x2606, "x2608")
      val x1989 = CounterChain.copy(x1987, "x1989")
      val x2625 = CounterChain.copy(x3026, "x2625")
      val x1970 = CounterChain.copy(x1968, "x1970")
      val x2597 = CounterChain.copy(x2620, "x2597")
      val x1965_x2806 = SRAM(size = 96, swapCtr = x1970(0), writeCtr = x1970(0), banking = Strided(1), doubleBuffer = false).wtPort(x1967_vector).rdAddr(x2675(0)).wtAddr(x1970(0))
      val x1966_x2805 = SRAM(size = 96, swapCtr = x1989(0), writeCtr = x1989(0), banking = Strided(1), doubleBuffer = false).wtPort(x1986_vector).rdAddr(x2675(0)).wtAddr(x1989(0))
      val x2578_x2803 = SRAM(size = 96, swapCtr = x2583(0), writeCtr = x2583(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2580_vector).rdAddr(x2625(0)).wtAddr(x2583(0))
      val x2579_x2801 = SRAM(size = 9216, swapCtr = x2597(0), writeCtr = x2608(0), banking = Strided(1), doubleBuffer = true).wtPort(x2595_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2579_x2801))
      Stage(stage(1), operands=List(x2597(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr991)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr991), CU.ctr(stage(1), x2608(0))), op=FixAdd, results=List(x2579_x2801.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2625(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr997)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr997), CU.ctr(stage(1), x2675(0))), op=FixAdd, results=List(x2579_x2801.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2578_x2803), CU.load(stage(2), x1966_x2805), CU.load(stage(2), x1965_x2806)), op=Mux, results=List(CU.temp(stage(3), tr1007)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2579_x2801), CU.temp(stage(3), tr1007)), op=FltSub, results=List(CU.vecOut(stage(4), x2651_vector)))
    }
    val x2836 = ComputeUnit(name="x2836", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1029 = CU.temp
      val tr1011 = CU.temp
      val tr1019 = CU.temp
      val x2583 = CounterChain.copy(x2581, "x2583")
      val x2608 = CounterChain.copy(x2606, "x2608")
      val x2668 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2676 = CounterChain(name = "x2676", x2668)
      val x1989 = CounterChain.copy(x1987, "x1989")
      val x2625 = CounterChain.copy(x3026, "x2625")
      val x1970 = CounterChain.copy(x1968, "x1970")
      val x2597 = CounterChain.copy(x2620, "x2597")
      val x1965_x2826 = SRAM(size = 96, swapCtr = x1970(0), writeCtr = x1970(0), banking = Strided(1), doubleBuffer = false).wtPort(x1967_vector).rdAddr(x2676(0)).wtAddr(x1970(0))
      val x1966_x2825 = SRAM(size = 96, swapCtr = x1989(0), writeCtr = x1989(0), banking = Strided(1), doubleBuffer = false).wtPort(x1986_vector).rdAddr(x2676(0)).wtAddr(x1989(0))
      val x2578_x2823 = SRAM(size = 96, swapCtr = x2583(0), writeCtr = x2583(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2580_vector).rdAddr(x2625(0)).wtAddr(x2583(0))
      val x2579_x2821 = SRAM(size = 9216, swapCtr = x2597(0), writeCtr = x2608(0), banking = Strided(1), doubleBuffer = true).wtPort(x2595_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2579_x2821))
      Stage(stage(1), operands=List(x2597(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1011)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1011), CU.ctr(stage(1), x2608(0))), op=FixAdd, results=List(x2579_x2821.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2625(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1019)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1019), CU.ctr(stage(1), x2676(0))), op=FixAdd, results=List(x2579_x2821.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2578_x2823), CU.load(stage(2), x1966_x2825), CU.load(stage(2), x1965_x2826)), op=Mux, results=List(CU.temp(stage(3), tr1029)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2579_x2821), CU.temp(stage(3), tr1029)), op=FltSub, results=List(CU.vecOut(stage(4), x2652_vector)))
    }
    val x2878 = ComputeUnit(name="x2878", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2839 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2847 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2855 = CounterChain(name = "x2855", x2839, x2847)
      val x2669 = CounterChain.copy(x2696, "x2669")
      val x2645_x2866 = SRAM(size = 96, swapCtr = x2669(0), writeCtr = x2669(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2645_vector).rdAddr(x2855(0)).wtAddr(x2669(0))
      val x2645_x2868 = SRAM(size = 96, swapCtr = x2669(0), writeCtr = x2669(0), banking = Strided(1), doubleBuffer = true).wtPort(x2645_vector).rdAddr(x2855(1)).wtAddr(x2669(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2645_x2866.load, x2645_x2868.load), op=FltMul, results=List(CU.vecOut(stage(1), x2653_vector)))
    }
    val x2894 = ComputeUnit(name="x2894", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2840 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2848 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2856 = CounterChain(name = "x2856", x2840, x2848)
      val x2670 = CounterChain.copy(x2716, "x2670")
      val x2646_x2882 = SRAM(size = 96, swapCtr = x2670(0), writeCtr = x2670(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2646_vector).rdAddr(x2856(0)).wtAddr(x2670(0))
      val x2646_x2884 = SRAM(size = 96, swapCtr = x2670(0), writeCtr = x2670(0), banking = Strided(1), doubleBuffer = true).wtPort(x2646_vector).rdAddr(x2856(1)).wtAddr(x2670(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2646_x2882.load, x2646_x2884.load), op=FltMul, results=List(CU.vecOut(stage(1), x2654_vector)))
    }
    val x2910 = ComputeUnit(name="x2910", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2841 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2849 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2857 = CounterChain(name = "x2857", x2841, x2849)
      val x2671 = CounterChain.copy(x2736, "x2671")
      val x2647_x2898 = SRAM(size = 96, swapCtr = x2671(0), writeCtr = x2671(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2647_vector).rdAddr(x2857(0)).wtAddr(x2671(0))
      val x2647_x2900 = SRAM(size = 96, swapCtr = x2671(0), writeCtr = x2671(0), banking = Strided(1), doubleBuffer = true).wtPort(x2647_vector).rdAddr(x2857(1)).wtAddr(x2671(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2647_x2898.load, x2647_x2900.load), op=FltMul, results=List(CU.vecOut(stage(1), x2655_vector)))
    }
    val x2926 = ComputeUnit(name="x2926", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2842 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2850 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2858 = CounterChain(name = "x2858", x2842, x2850)
      val x2672 = CounterChain.copy(x2756, "x2672")
      val x2648_x2914 = SRAM(size = 96, swapCtr = x2672(0), writeCtr = x2672(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2648_vector).rdAddr(x2858(0)).wtAddr(x2672(0))
      val x2648_x2916 = SRAM(size = 96, swapCtr = x2672(0), writeCtr = x2672(0), banking = Strided(1), doubleBuffer = true).wtPort(x2648_vector).rdAddr(x2858(1)).wtAddr(x2672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2648_x2914.load, x2648_x2916.load), op=FltMul, results=List(CU.vecOut(stage(1), x2656_vector)))
    }
    val x2942 = ComputeUnit(name="x2942", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2673 = CounterChain.copy(x2776, "x2673")
      val x2843 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2851 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2859 = CounterChain(name = "x2859", x2843, x2851)
      val x2649_x2930 = SRAM(size = 96, swapCtr = x2673(0), writeCtr = x2673(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2649_vector).rdAddr(x2859(0)).wtAddr(x2673(0))
      val x2649_x2932 = SRAM(size = 96, swapCtr = x2673(0), writeCtr = x2673(0), banking = Strided(1), doubleBuffer = true).wtPort(x2649_vector).rdAddr(x2859(1)).wtAddr(x2673(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2649_x2930.load, x2649_x2932.load), op=FltMul, results=List(CU.vecOut(stage(1), x2657_vector)))
    }
    val x2958 = ComputeUnit(name="x2958", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2674 = CounterChain.copy(x2796, "x2674")
      val x2844 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2852 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2860 = CounterChain(name = "x2860", x2844, x2852)
      val x2650_x2946 = SRAM(size = 96, swapCtr = x2674(0), writeCtr = x2674(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2650_vector).rdAddr(x2860(0)).wtAddr(x2674(0))
      val x2650_x2948 = SRAM(size = 96, swapCtr = x2674(0), writeCtr = x2674(0), banking = Strided(1), doubleBuffer = true).wtPort(x2650_vector).rdAddr(x2860(1)).wtAddr(x2674(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2650_x2946.load, x2650_x2948.load), op=FltMul, results=List(CU.vecOut(stage(1), x2658_vector)))
    }
    val x2974 = ComputeUnit(name="x2974", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2845 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2853 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2861 = CounterChain(name = "x2861", x2845, x2853)
      val x2675 = CounterChain.copy(x2816, "x2675")
      val x2651_x2962 = SRAM(size = 96, swapCtr = x2675(0), writeCtr = x2675(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2651_vector).rdAddr(x2861(0)).wtAddr(x2675(0))
      val x2651_x2964 = SRAM(size = 96, swapCtr = x2675(0), writeCtr = x2675(0), banking = Strided(1), doubleBuffer = true).wtPort(x2651_vector).rdAddr(x2861(1)).wtAddr(x2675(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2651_x2962.load, x2651_x2964.load), op=FltMul, results=List(CU.vecOut(stage(1), x2659_vector)))
    }
    val x2990 = ComputeUnit(name="x2990", parent=x3026, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2676 = CounterChain.copy(x2836, "x2676")
      val x2846 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2854 = (Const("0i").out, CU.scalarIn(stage0, x1908_argin).out, Const("1i").out) // Counter
      val x2862 = CounterChain(name = "x2862", x2846, x2854)
      val x2652_x2978 = SRAM(size = 96, swapCtr = x2676(0), writeCtr = x2676(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2652_vector).rdAddr(x2862(0)).wtAddr(x2676(0))
      val x2652_x2980 = SRAM(size = 96, swapCtr = x2676(0), writeCtr = x2676(0), banking = Strided(1), doubleBuffer = true).wtPort(x2652_vector).rdAddr(x2862(1)).wtAddr(x2676(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2652_x2978.load, x2652_x2980.load), op=FltMul, results=List(CU.vecOut(stage(1), x2660_vector)))
    }
    val x3024 = ComputeUnit(name="x3024", parent=x3026, tpe = Pipe, deps=List(x2878, x2974, x2942, x2990, x2910, x2958, x2926, x2894)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1116 = CU.temp
      val tr1154 = CU.temp
      val tr1156 = CU.temp
      val tr1149 = CU.temp
      val tr1152 = CU.temp
      val tr1110 = CU.temp
      val tr1119 = CU.temp
      val tr1113 = CU.temp
      val tr1150 = CU.temp
      val tr1153 = CU.temp
      val tr1157 = CU.temp
      val tr1148 = CU.temp
      val tr1104 = CU.temp
      val tr1125 = CU.temp
      val tr1107 = CU.temp
      val tr1155 = CU.temp
      val tr1122 = CU.temp
      val tr1128 = CU.temp
      val x2857 = CounterChain.copy(x2910, "x2857")
      val x2861 = CounterChain.copy(x2974, "x2861")
      val x2626 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2627 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2628 = CounterChain(name = "x2628", x2626, x2627)
      val x2862 = CounterChain.copy(x2990, "x2862")
      val x2858 = CounterChain.copy(x2926, "x2858")
      val x2860 = CounterChain.copy(x2958, "x2860")
      val x2859 = CounterChain.copy(x2942, "x2859")
      val x2855 = CounterChain.copy(x2878, "x2855")
      val x2856 = CounterChain.copy(x2894, "x2856")
      val x2625 = CounterChain.copy(x3026, "x2625")
      val x2659_x3006 = SRAM(size = 9216, swapCtr = x2861(0), writeCtr = x2861(0), banking = Strided(1), doubleBuffer = true).wtPort(x2659_vector)
      val x2654_x3001 = SRAM(size = 9216, swapCtr = x2856(0), writeCtr = x2856(0), banking = Strided(1), doubleBuffer = true).wtPort(x2654_vector)
      val x2660_x3007 = SRAM(size = 9216, swapCtr = x2862(0), writeCtr = x2862(0), banking = Strided(1), doubleBuffer = true).wtPort(x2660_vector)
      val x2657_x3004 = SRAM(size = 9216, swapCtr = x2859(0), writeCtr = x2859(0), banking = Strided(1), doubleBuffer = true).wtPort(x2657_vector)
      val x2658_x3005 = SRAM(size = 9216, swapCtr = x2860(0), writeCtr = x2860(0), banking = Strided(1), doubleBuffer = true).wtPort(x2658_vector)
      val x2656_x3003 = SRAM(size = 9216, swapCtr = x2858(0), writeCtr = x2858(0), banking = Strided(1), doubleBuffer = true).wtPort(x2656_vector)
      val x2653_x3000 = SRAM(size = 9216, swapCtr = x2855(0), writeCtr = x2855(0), banking = Strided(1), doubleBuffer = true).wtPort(x2653_vector)
      val x2655_x3002 = SRAM(size = 9216, swapCtr = x2857(0), writeCtr = x2857(0), banking = Strided(1), doubleBuffer = true).wtPort(x2655_vector)
      val x2623_x3008 = SRAM(size = 9216, swapCtr = x2628(0), writeCtr = x2628(0), banking = Strided(1), doubleBuffer = false)
      val wr1160 = CU.wtAddr(x2623_x3008)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2653_x3000))
      Stage(stage(1), operands=List(x2855(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1104)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1104), CU.ctr(stage(1), x2855(1))), op=FixAdd, results=List(x2653_x3000.writeAddr))
      stage = stage0 +: WAStages(2, List(x2656_x3003))
      Stage(stage(1), operands=List(x2858(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1107)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1107), CU.ctr(stage(1), x2858(1))), op=FixAdd, results=List(x2656_x3003.writeAddr))
      stage = stage0 +: WAStages(2, List(x2659_x3006))
      Stage(stage(1), operands=List(x2861(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1110)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1110), CU.ctr(stage(1), x2861(1))), op=FixAdd, results=List(x2659_x3006.writeAddr))
      stage = stage0 +: WAStages(2, List(x2654_x3001))
      Stage(stage(1), operands=List(x2856(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1119)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1119), CU.ctr(stage(1), x2856(1))), op=FixAdd, results=List(x2654_x3001.writeAddr))
      stage = stage0 +: WAStages(2, List(x2657_x3004))
      Stage(stage(1), operands=List(x2859(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1116)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1116), CU.ctr(stage(1), x2859(1))), op=FixAdd, results=List(x2657_x3004.writeAddr))
      stage = stage0 +: WAStages(2, List(x2658_x3005))
      Stage(stage(1), operands=List(x2860(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1113)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1113), CU.ctr(stage(1), x2860(1))), op=FixAdd, results=List(x2658_x3005.writeAddr))
      stage = stage0 +: WAStages(2, List(x2660_x3007))
      Stage(stage(1), operands=List(x2862(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1122)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1122), CU.ctr(stage(1), x2862(1))), op=FixAdd, results=List(x2660_x3007.writeAddr))
      stage = stage0 +: WAStages(2, List(x2655_x3002))
      Stage(stage(1), operands=List(x2857(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1125)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1125), CU.ctr(stage(1), x2857(1))), op=FixAdd, results=List(x2655_x3002.writeAddr))
      stage = stage0 +: Stages(12)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2628(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1128)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1128), CU.ctr(stage(1), x2628(1))), op=FixAdd, results=List(CU.wtAddr(stage(2), wr1160), x2623_x3008.readAddr, x2660_x3007.readAddr, x2659_x3006.readAddr, x2658_x3005.readAddr, x2657_x3004.readAddr, x2656_x3003.readAddr, x2655_x3002.readAddr, x2654_x3001.readAddr, x2653_x3000.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x2625(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(3), tr1148)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x2628(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(4), tr1149)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1148), CU.temp(stage(4), tr1149)), op=BitAnd, results=List(CU.temp(stage(5), tr1150)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1150), CU.load(stage(5), x2653_x3000), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr1152)))
      Stage(stage(7), operands=List(CU.ctr(stage(6), x2625(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(7), tr1153)))
      Stage(stage(8), operands=List(CU.ctr(stage(7), x2628(1)), Const("96i")), op=FixLt, results=List(CU.temp(stage(8), tr1154)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr1153), CU.temp(stage(8), tr1154)), op=BitAnd, results=List(CU.temp(stage(9), tr1155)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr1155), CU.load(stage(9), x2654_x3001), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr1156)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr1152), CU.temp(stage(10), tr1156)), op=FltAdd, results=List(CU.temp(stage(11), tr1157)))
      Stage(stage(12), operands=List(CU.temp(stage(11), tr1157), CU.load(stage(11), x2623_x3008)), op=FltAdd, results=List(CU.vecOut(stage(12), x2623_vector), CU.store(stage(12), x2623_x3008)))
    }
    val x3046 = ComputeUnit(name="x3046", parent=x3048, tpe = Pipe, deps=List(x3026)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1176 = CU.temp
      val tr1172 = CU.temp
      val tr1166 = CU.temp
      val tr1173 = CU.temp
      val tr1174 = CU.temp
      val tr1162 = CU.temp
      val x2010 = CounterChain.copy(x3048, "x2010")
      val x2011 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2012 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2013 = CounterChain(name = "x2013", x2011, x2012)
      val x2628 = CounterChain.copy(x3024, "x2628")
      val x2623_x3035 = SRAM(size = 9216, swapCtr = x2628(0), writeCtr = x2628(0), banking = Strided(1), doubleBuffer = true).wtPort(x2623_vector)
      val x2008_x3038 = SRAM(size = 9216, swapCtr = x2013(0), writeCtr = x2013(0), banking = Strided(1), doubleBuffer = false)
      val wr1179 = CU.wtAddr(x2008_x3038)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2623_x3035))
      Stage(stage(1), operands=List(x2628(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1162)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1162), CU.ctr(stage(1), x2628(1))), op=FixAdd, results=List(x2623_x3035.writeAddr))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2013(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1166)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1166), CU.ctr(stage(1), x2013(1))), op=FixAdd, results=List(CU.wtAddr(stage(2), wr1179), x2008_x3038.readAddr, x2623_x3035.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x2010(0)), CU.scalarIn(stage(2), x1907_argin)), op=FixLt, results=List(CU.temp(stage(3), tr1172)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x2013(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(4), tr1173)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1172), CU.temp(stage(4), tr1173)), op=BitAnd, results=List(CU.temp(stage(5), tr1174)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1174), CU.load(stage(5), x2623_x3035), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr1176)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1176), CU.load(stage(6), x2008_x3038)), op=FltAdd, results=List(CU.vecOut(stage(7), x2008_vector), CU.store(stage(7), x2008_x3038)))
    }
    val x3103 = ComputeUnit(name="x3103", parent=x3105, tpe = MetaPipeline, deps=List(x3048)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3050 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x3051 = CounterChain(name = "x3051", x3050)
    }
    val x3093 = ComputeUnit(name="x3093", parent=x3103, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1181 = CU.temp
      val tr1184 = CU.temp
      val x3080 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x3081 = CounterChain(name = "x3081", x3080)
      val x2013 = CounterChain.copy(x3046, "x2013")
      val x3051 = CounterChain.copy(x3103, "x3051")
      val x2008_x3086 = SRAM(size = 9216, swapCtr = x2013(0), writeCtr = x2013(0), banking = Strided(1), doubleBuffer = false).wtPort(x2008_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2008_x3086))
      Stage(stage(1), operands=List(x2013(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1181)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1181), CU.ctr(stage(1), x2013(1))), op=FixAdd, results=List(x2008_x3086.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3051(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1184)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1184), CU.ctr(stage(1), x3081(0))), op=FixAdd, results=List(x2008_x3086.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2008_x3086)), op=Bypass, results=List(CU.vecOut(stage(3), x3049_vector)))
    }
    val x3098 = UnitComputeUnit(name ="x3098", parent=x3103, deps=List(x3093)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3051 = CounterChain.copy(x3103, "x3051")
      val x3098_unitCC = CounterChain(name = "x3098_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3051(0)), CU.scalarIn(stage(0), x1908_argin)), op=FixMul, results=List(CU.scalarOut(stage(1), x3094_scalar)))
    }
    val x3100 = TileTransfer(name="x3100", parent=x3103, memctrl=x3100_mc_mc, mctpe=TileStore, deps=List(x3098), vec=x3049_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3100_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x3100_cc = CounterChain(name = "x3100_cc", x3100_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3094_scalar), CU.ctr(stage(0), x3100_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3100_mc_mc.saddr)))
    }
    
  }
}
