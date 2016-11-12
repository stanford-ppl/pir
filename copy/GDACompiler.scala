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
    val x3103_scalar = Scalar()
    val x2655_vector = Vector()
    val x2589_vector = Vector()
    val x2659_vector = Vector()
    val x2013_argin = ArgIn()
    val x1972_vector = Vector()
    val x2652_vector = Vector()
    val x1921_oc = OffChip()
    val x1913_argin = ArgIn()
    val x2647_vector = Vector()
    val x2653_vector = Vector()
    val x2654_vector = Vector()
    val x2649_vector = Vector()
    val x1923_oc = OffChip()
    val x2021_argin = ArgIn()
    val x2604_vector = Vector()
    val x2646_vector = Vector()
    val x2648_vector = Vector()
    val x2650_vector = Vector()
    val x1922_oc = OffChip()
    val x1991_vector = Vector()
    val x1924_oc = OffChip()
    val x1926_oc = OffChip()
    val x2651_vector = Vector()
    val x2657_vector = Vector()
    val x2012_vector = Vector()
    val x2658_vector = Vector()
    val x2661_vector = Vector()
    val x3058_vector = Vector()
    val x2660_vector = Vector()
    val x2656_vector = Vector()
    val x2632_vector = Vector()
    val x2608_scalar = Scalar()
    val x1973_mc_mc = MemoryController(TileLoad, x1923_oc)
    val x2590_mc_mc = MemoryController(TileLoad, x1922_oc)
    val x1992_mc_mc = MemoryController(TileLoad, x1924_oc)
    val x3109_mc_mc = MemoryController(TileStore, x1926_oc)
    val x2615_mc_mc = MemoryController(TileLoad, x1921_oc)
    val x3114 = ComputeUnit(name="x3114", parent=top, tpe = Sequential, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3114_unitCC = CounterChain(name = "x3114_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1990 = ComputeUnit(name="x1990", parent=x3114, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1990_unitCC = CounterChain(name = "x1990_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1973 = TileTransfer(name="x1973", parent=x1990, memctrl=x1973_mc_mc, mctpe=TileLoad, deps=List(), vec=x1972_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1973_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1973_cc = CounterChain(name = "x1973_cc", x1973_ctr)
      val x1974 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1975 = CounterChain(name = "x1975", x1974).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("0i"), CU.ctr(stage(0), x1973_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1973_mc_mc.saddr)))
    }
    val x2009 = ComputeUnit(name="x2009", parent=x3114, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2009_unitCC = CounterChain(name = "x2009_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x1992 = TileTransfer(name="x1992", parent=x2009, memctrl=x1992_mc_mc, mctpe=TileLoad, deps=List(), vec=x1991_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x1992_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x1992_cc = CounterChain(name = "x1992_cc", x1992_ctr)
      val x1993 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x1994 = CounterChain(name = "x1994", x1993).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(Const("0i"), CU.ctr(stage(0), x1992_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x1992_mc_mc.saddr)))
    }
    val x3057 = ComputeUnit(name="x3057", parent=x3114, tpe = MetaPipeline, deps=List(x1990, x2009)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2015 = (Const("0i").out, CU.scalarIn(stage0, x2013_argin).out, Const("96i").out) // Counter
      val x2016 = CounterChain(name = "x2016", x2015)
    }
    val x2602 = ComputeUnit(name="x2602", parent=x3057, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2602_unitCC = CounterChain(name = "x2602_unitCC", (Const("0i"), Const("1i"), Const("1i")))
    }
    val x2590 = TileTransfer(name="x2590", parent=x2602, memctrl=x2590_mc_mc, mctpe=TileLoad, deps=List(), vec=x2589_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2016 = CounterChain.copy(x3057, "x2016")
      val x2590_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x2590_cc = CounterChain(name = "x2590_cc", x2590_ctr)
      val x2591 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2592 = CounterChain(name = "x2592", x2591).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2016(0)), CU.ctr(stage(0), x2590_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2590_mc_mc.saddr)))
    }
    val x2629 = ComputeUnit(name="x2629", parent=x3057, tpe = MetaPipeline, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2605 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2606 = CounterChain(name = "x2606", x2605)
    }
    val x2613 = UnitComputeUnit(name ="x2613", parent=x2629, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr848 = CU.temp
      val x2016 = CounterChain.copy(x3057, "x2016")
      val x2606 = CounterChain.copy(x2629, "x2606")
      val x2613_unitCC = CounterChain(name = "x2613_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2016(0)), CU.ctr(stage(0), x2606(0))), op=FixAdd, results=List(CU.temp(stage(1), tr848)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr848), CU.scalarIn(stage(1), x1913_argin)), op=FixMul, results=List(CU.scalarOut(stage(2), x2608_scalar)))
    }
    val x2615 = TileTransfer(name="x2615", parent=x2629, memctrl=x2615_mc_mc, mctpe=TileLoad, deps=List(x2613), vec=x2604_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2615_ctr = (Const("0l").out, CU.scalarIn(stage0, x1913_argin).out, Const("1l").out) // Counter
      val x2615_cc = CounterChain(name = "x2615_cc", x2615_ctr)
      val x2616 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2617 = CounterChain(name = "x2617", x2616).isStreaming(true)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x2608_scalar), CU.ctr(stage(0), x2615_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x2615_mc_mc.saddr)))
    }
    val x3035 = ComputeUnit(name="x3035", parent=x3057, tpe = MetaPipeline, deps=List(x2602, x2629)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2633 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2634 = CounterChain(name = "x2634", x2633)
    }
    val x2705 = ComputeUnit(name="x2705", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr865 = CU.temp
      val tr859 = CU.temp
      val tr875 = CU.temp
      val x2662 = (Const("0i").out, CU.scalarIn(stage0, x2021_argin).out, Const("1i").out) // Counter
      val x2670 = CounterChain(name = "x2670", x2662)
      val x2592 = CounterChain.copy(x2590, "x2592")
      val x2634 = CounterChain.copy(x3035, "x2634")
      val x2606 = CounterChain.copy(x2629, "x2606")
      val x2617 = CounterChain.copy(x2615, "x2617")
      val x1975 = CounterChain.copy(x1973, "x1975")
      val x1994 = CounterChain.copy(x1992, "x1994")
      val x1970_x2695 = SRAM(size = 96, swapCtr = x1975(0), writeCtr = x1975(0), banking = Strided(1), doubleBuffer = false).wtPort(x1972_vector).rdAddr(x2670(0)).wtAddr(x1975(0))
      val x1971_x2694 = SRAM(size = 96, swapCtr = x1994(0), writeCtr = x1994(0), banking = Strided(1), doubleBuffer = false).wtPort(x1991_vector).rdAddr(x2670(0)).wtAddr(x1994(0))
      val x2587_x2692 = SRAM(size = 96, swapCtr = x2592(0), writeCtr = x2592(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2589_vector).rdAddr(x2634(0)).wtAddr(x2592(0))
      val x2588_x2690 = SRAM(size = 9216, swapCtr = x2606(0), writeCtr = x2617(0), banking = Strided(1), doubleBuffer = true).wtPort(x2604_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2588_x2690))
      Stage(stage(1), operands=List(x2606(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr859)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr859), CU.ctr(stage(1), x2617(0))), op=FixAdd, results=List(x2588_x2690.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2634(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr865)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr865), CU.ctr(stage(1), x2670(0))), op=FixAdd, results=List(x2588_x2690.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2587_x2692), CU.load(stage(2), x1971_x2694), CU.load(stage(2), x1970_x2695)), op=Mux, results=List(CU.temp(stage(3), tr875)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2588_x2690), CU.temp(stage(3), tr875)), op=FltSub, results=List(CU.vecOut(stage(4), x2646_vector)))
    }
    val x2725 = ComputeUnit(name="x2725", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr897 = CU.temp
      val tr880 = CU.temp
      val tr887 = CU.temp
      val x2592 = CounterChain.copy(x2590, "x2592")
      val x2634 = CounterChain.copy(x3035, "x2634")
      val x2606 = CounterChain.copy(x2629, "x2606")
      val x2617 = CounterChain.copy(x2615, "x2617")
      val x1975 = CounterChain.copy(x1973, "x1975")
      val x2663 = (Const("0i").out, CU.scalarIn(stage0, x2021_argin).out, Const("1i").out) // Counter
      val x2671 = CounterChain(name = "x2671", x2663)
      val x1994 = CounterChain.copy(x1992, "x1994")
      val x1970_x2715 = SRAM(size = 96, swapCtr = x1975(0), writeCtr = x1975(0), banking = Strided(1), doubleBuffer = false).wtPort(x1972_vector).rdAddr(x2671(0)).wtAddr(x1975(0))
      val x1971_x2714 = SRAM(size = 96, swapCtr = x1994(0), writeCtr = x1994(0), banking = Strided(1), doubleBuffer = false).wtPort(x1991_vector).rdAddr(x2671(0)).wtAddr(x1994(0))
      val x2587_x2712 = SRAM(size = 96, swapCtr = x2592(0), writeCtr = x2592(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2589_vector).rdAddr(x2634(0)).wtAddr(x2592(0))
      val x2588_x2710 = SRAM(size = 9216, swapCtr = x2606(0), writeCtr = x2617(0), banking = Strided(1), doubleBuffer = true).wtPort(x2604_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2588_x2710))
      Stage(stage(1), operands=List(x2606(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr880)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr880), CU.ctr(stage(1), x2617(0))), op=FixAdd, results=List(x2588_x2710.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2634(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr887)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr887), CU.ctr(stage(1), x2671(0))), op=FixAdd, results=List(x2588_x2710.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2587_x2712), CU.load(stage(2), x1971_x2714), CU.load(stage(2), x1970_x2715)), op=Mux, results=List(CU.temp(stage(3), tr897)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2588_x2710), CU.temp(stage(3), tr897)), op=FltSub, results=List(CU.vecOut(stage(4), x2647_vector)))
    }
    val x2745 = ComputeUnit(name="x2745", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr909 = CU.temp
      val tr902 = CU.temp
      val tr919 = CU.temp
      val x2592 = CounterChain.copy(x2590, "x2592")
      val x2634 = CounterChain.copy(x3035, "x2634")
      val x2606 = CounterChain.copy(x2629, "x2606")
      val x2664 = (Const("0i").out, CU.scalarIn(stage0, x2021_argin).out, Const("1i").out) // Counter
      val x2672 = CounterChain(name = "x2672", x2664)
      val x2617 = CounterChain.copy(x2615, "x2617")
      val x1975 = CounterChain.copy(x1973, "x1975")
      val x1994 = CounterChain.copy(x1992, "x1994")
      val x1970_x2735 = SRAM(size = 96, swapCtr = x1975(0), writeCtr = x1975(0), banking = Strided(1), doubleBuffer = false).wtPort(x1972_vector).rdAddr(x2672(0)).wtAddr(x1975(0))
      val x1971_x2734 = SRAM(size = 96, swapCtr = x1994(0), writeCtr = x1994(0), banking = Strided(1), doubleBuffer = false).wtPort(x1991_vector).rdAddr(x2672(0)).wtAddr(x1994(0))
      val x2587_x2732 = SRAM(size = 96, swapCtr = x2592(0), writeCtr = x2592(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2589_vector).rdAddr(x2634(0)).wtAddr(x2592(0))
      val x2588_x2730 = SRAM(size = 9216, swapCtr = x2606(0), writeCtr = x2617(0), banking = Strided(1), doubleBuffer = true).wtPort(x2604_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2588_x2730))
      Stage(stage(1), operands=List(x2606(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr902)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr902), CU.ctr(stage(1), x2617(0))), op=FixAdd, results=List(x2588_x2730.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2634(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr909)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr909), CU.ctr(stage(1), x2672(0))), op=FixAdd, results=List(x2588_x2730.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2587_x2732), CU.load(stage(2), x1971_x2734), CU.load(stage(2), x1970_x2735)), op=Mux, results=List(CU.temp(stage(3), tr919)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2588_x2730), CU.temp(stage(3), tr919)), op=FltSub, results=List(CU.vecOut(stage(4), x2648_vector)))
    }
    val x2765 = ComputeUnit(name="x2765", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr931 = CU.temp
      val tr941 = CU.temp
      val tr926 = CU.temp
      val x2592 = CounterChain.copy(x2590, "x2592")
      val x2634 = CounterChain.copy(x3035, "x2634")
      val x2606 = CounterChain.copy(x2629, "x2606")
      val x2665 = (Const("0i").out, CU.scalarIn(stage0, x2021_argin).out, Const("1i").out) // Counter
      val x2673 = CounterChain(name = "x2673", x2665)
      val x2617 = CounterChain.copy(x2615, "x2617")
      val x1975 = CounterChain.copy(x1973, "x1975")
      val x1994 = CounterChain.copy(x1992, "x1994")
      val x1970_x2755 = SRAM(size = 96, swapCtr = x1975(0), writeCtr = x1975(0), banking = Strided(1), doubleBuffer = false).wtPort(x1972_vector).rdAddr(x2673(0)).wtAddr(x1975(0))
      val x1971_x2754 = SRAM(size = 96, swapCtr = x1994(0), writeCtr = x1994(0), banking = Strided(1), doubleBuffer = false).wtPort(x1991_vector).rdAddr(x2673(0)).wtAddr(x1994(0))
      val x2587_x2752 = SRAM(size = 96, swapCtr = x2592(0), writeCtr = x2592(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2589_vector).rdAddr(x2634(0)).wtAddr(x2592(0))
      val x2588_x2750 = SRAM(size = 9216, swapCtr = x2606(0), writeCtr = x2617(0), banking = Strided(1), doubleBuffer = true).wtPort(x2604_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2588_x2750))
      Stage(stage(1), operands=List(x2606(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr926)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr926), CU.ctr(stage(1), x2617(0))), op=FixAdd, results=List(x2588_x2750.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2634(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr931)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr931), CU.ctr(stage(1), x2673(0))), op=FixAdd, results=List(x2588_x2750.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2587_x2752), CU.load(stage(2), x1971_x2754), CU.load(stage(2), x1970_x2755)), op=Mux, results=List(CU.temp(stage(3), tr941)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2588_x2750), CU.temp(stage(3), tr941)), op=FltSub, results=List(CU.vecOut(stage(4), x2649_vector)))
    }
    val x2785 = ComputeUnit(name="x2785", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr946 = CU.temp
      val tr963 = CU.temp
      val tr953 = CU.temp
      val x2592 = CounterChain.copy(x2590, "x2592")
      val x2634 = CounterChain.copy(x3035, "x2634")
      val x2606 = CounterChain.copy(x2629, "x2606")
      val x2666 = (Const("0i").out, CU.scalarIn(stage0, x2021_argin).out, Const("1i").out) // Counter
      val x2674 = CounterChain(name = "x2674", x2666)
      val x2617 = CounterChain.copy(x2615, "x2617")
      val x1975 = CounterChain.copy(x1973, "x1975")
      val x1994 = CounterChain.copy(x1992, "x1994")
      val x1970_x2775 = SRAM(size = 96, swapCtr = x1975(0), writeCtr = x1975(0), banking = Strided(1), doubleBuffer = false).wtPort(x1972_vector).rdAddr(x2674(0)).wtAddr(x1975(0))
      val x1971_x2774 = SRAM(size = 96, swapCtr = x1994(0), writeCtr = x1994(0), banking = Strided(1), doubleBuffer = false).wtPort(x1991_vector).rdAddr(x2674(0)).wtAddr(x1994(0))
      val x2587_x2772 = SRAM(size = 96, swapCtr = x2592(0), writeCtr = x2592(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2589_vector).rdAddr(x2634(0)).wtAddr(x2592(0))
      val x2588_x2770 = SRAM(size = 9216, swapCtr = x2606(0), writeCtr = x2617(0), banking = Strided(1), doubleBuffer = true).wtPort(x2604_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2588_x2770))
      Stage(stage(1), operands=List(x2606(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr946)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr946), CU.ctr(stage(1), x2617(0))), op=FixAdd, results=List(x2588_x2770.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2634(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr953)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr953), CU.ctr(stage(1), x2674(0))), op=FixAdd, results=List(x2588_x2770.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2587_x2772), CU.load(stage(2), x1971_x2774), CU.load(stage(2), x1970_x2775)), op=Mux, results=List(CU.temp(stage(3), tr963)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2588_x2770), CU.temp(stage(3), tr963)), op=FltSub, results=List(CU.vecOut(stage(4), x2650_vector)))
    }
    val x2805 = ComputeUnit(name="x2805", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr985 = CU.temp
      val tr975 = CU.temp
      val tr969 = CU.temp
      val x2592 = CounterChain.copy(x2590, "x2592")
      val x2634 = CounterChain.copy(x3035, "x2634")
      val x2606 = CounterChain.copy(x2629, "x2606")
      val x2667 = (Const("0i").out, CU.scalarIn(stage0, x2021_argin).out, Const("1i").out) // Counter
      val x2675 = CounterChain(name = "x2675", x2667)
      val x2617 = CounterChain.copy(x2615, "x2617")
      val x1975 = CounterChain.copy(x1973, "x1975")
      val x1994 = CounterChain.copy(x1992, "x1994")
      val x1970_x2795 = SRAM(size = 96, swapCtr = x1975(0), writeCtr = x1975(0), banking = Strided(1), doubleBuffer = false).wtPort(x1972_vector).rdAddr(x2675(0)).wtAddr(x1975(0))
      val x1971_x2794 = SRAM(size = 96, swapCtr = x1994(0), writeCtr = x1994(0), banking = Strided(1), doubleBuffer = false).wtPort(x1991_vector).rdAddr(x2675(0)).wtAddr(x1994(0))
      val x2587_x2792 = SRAM(size = 96, swapCtr = x2592(0), writeCtr = x2592(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2589_vector).rdAddr(x2634(0)).wtAddr(x2592(0))
      val x2588_x2790 = SRAM(size = 9216, swapCtr = x2606(0), writeCtr = x2617(0), banking = Strided(1), doubleBuffer = true).wtPort(x2604_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2588_x2790))
      Stage(stage(1), operands=List(x2606(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr969)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr969), CU.ctr(stage(1), x2617(0))), op=FixAdd, results=List(x2588_x2790.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2634(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr975)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr975), CU.ctr(stage(1), x2675(0))), op=FixAdd, results=List(x2588_x2790.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2587_x2792), CU.load(stage(2), x1971_x2794), CU.load(stage(2), x1970_x2795)), op=Mux, results=List(CU.temp(stage(3), tr985)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2588_x2790), CU.temp(stage(3), tr985)), op=FltSub, results=List(CU.vecOut(stage(4), x2651_vector)))
    }
    val x2825 = ComputeUnit(name="x2825", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr997 = CU.temp
      val tr992 = CU.temp
      val tr1007 = CU.temp
      val x2592 = CounterChain.copy(x2590, "x2592")
      val x2634 = CounterChain.copy(x3035, "x2634")
      val x2606 = CounterChain.copy(x2629, "x2606")
      val x2617 = CounterChain.copy(x2615, "x2617")
      val x2668 = (Const("0i").out, CU.scalarIn(stage0, x2021_argin).out, Const("1i").out) // Counter
      val x2676 = CounterChain(name = "x2676", x2668)
      val x1975 = CounterChain.copy(x1973, "x1975")
      val x1994 = CounterChain.copy(x1992, "x1994")
      val x1970_x2815 = SRAM(size = 96, swapCtr = x1975(0), writeCtr = x1975(0), banking = Strided(1), doubleBuffer = false).wtPort(x1972_vector).rdAddr(x2676(0)).wtAddr(x1975(0))
      val x1971_x2814 = SRAM(size = 96, swapCtr = x1994(0), writeCtr = x1994(0), banking = Strided(1), doubleBuffer = false).wtPort(x1991_vector).rdAddr(x2676(0)).wtAddr(x1994(0))
      val x2587_x2812 = SRAM(size = 96, swapCtr = x2592(0), writeCtr = x2592(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2589_vector).rdAddr(x2634(0)).wtAddr(x2592(0))
      val x2588_x2810 = SRAM(size = 9216, swapCtr = x2606(0), writeCtr = x2617(0), banking = Strided(1), doubleBuffer = true).wtPort(x2604_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2588_x2810))
      Stage(stage(1), operands=List(x2606(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr992)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr992), CU.ctr(stage(1), x2617(0))), op=FixAdd, results=List(x2588_x2810.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2634(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr997)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr997), CU.ctr(stage(1), x2676(0))), op=FixAdd, results=List(x2588_x2810.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2587_x2812), CU.load(stage(2), x1971_x2814), CU.load(stage(2), x1970_x2815)), op=Mux, results=List(CU.temp(stage(3), tr1007)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2588_x2810), CU.temp(stage(3), tr1007)), op=FltSub, results=List(CU.vecOut(stage(4), x2652_vector)))
    }
    val x2845 = ComputeUnit(name="x2845", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1013 = CU.temp
      val tr1029 = CU.temp
      val tr1019 = CU.temp
      val x2669 = (Const("0i").out, CU.scalarIn(stage0, x2021_argin).out, Const("1i").out) // Counter
      val x2677 = CounterChain(name = "x2677", x2669)
      val x2592 = CounterChain.copy(x2590, "x2592")
      val x2634 = CounterChain.copy(x3035, "x2634")
      val x2606 = CounterChain.copy(x2629, "x2606")
      val x2617 = CounterChain.copy(x2615, "x2617")
      val x1975 = CounterChain.copy(x1973, "x1975")
      val x1994 = CounterChain.copy(x1992, "x1994")
      val x1970_x2835 = SRAM(size = 96, swapCtr = x1975(0), writeCtr = x1975(0), banking = Strided(1), doubleBuffer = false).wtPort(x1972_vector).rdAddr(x2677(0)).wtAddr(x1975(0))
      val x1971_x2834 = SRAM(size = 96, swapCtr = x1994(0), writeCtr = x1994(0), banking = Strided(1), doubleBuffer = false).wtPort(x1991_vector).rdAddr(x2677(0)).wtAddr(x1994(0))
      val x2587_x2832 = SRAM(size = 96, swapCtr = x2592(0), writeCtr = x2592(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2589_vector).rdAddr(x2634(0)).wtAddr(x2592(0))
      val x2588_x2830 = SRAM(size = 9216, swapCtr = x2606(0), writeCtr = x2617(0), banking = Strided(1), doubleBuffer = true).wtPort(x2604_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2588_x2830))
      Stage(stage(1), operands=List(x2606(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1013)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1013), CU.ctr(stage(1), x2617(0))), op=FixAdd, results=List(x2588_x2830.writeAddr))
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2634(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1019)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1019), CU.ctr(stage(1), x2677(0))), op=FixAdd, results=List(x2588_x2830.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2587_x2832), CU.load(stage(2), x1971_x2834), CU.load(stage(2), x1970_x2835)), op=Mux, results=List(CU.temp(stage(3), tr1029)))
      Stage(stage(4), operands=List(CU.load(stage(3), x2588_x2830), CU.temp(stage(3), tr1029)), op=FltSub, results=List(CU.vecOut(stage(4), x2653_vector)))
    }
    val x2887 = ComputeUnit(name="x2887", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2848 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2856 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2864 = CounterChain(name = "x2864", x2848, x2856)
      val x2670 = CounterChain.copy(x2705, "x2670")
      val x2646_x2875 = SRAM(size = 96, swapCtr = x2670(0), writeCtr = x2670(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2646_vector).rdAddr(x2864(0)).wtAddr(x2670(0))
      val x2646_x2877 = SRAM(size = 96, swapCtr = x2670(0), writeCtr = x2670(0), banking = Strided(1), doubleBuffer = true).wtPort(x2646_vector).rdAddr(x2864(1)).wtAddr(x2670(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2646_x2875.load, x2646_x2877.load), op=FltMul, results=List(CU.vecOut(stage(1), x2654_vector)))
    }
    val x2903 = ComputeUnit(name="x2903", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2671 = CounterChain.copy(x2725, "x2671")
      val x2849 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2857 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2865 = CounterChain(name = "x2865", x2849, x2857)
      val x2647_x2891 = SRAM(size = 96, swapCtr = x2671(0), writeCtr = x2671(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2647_vector).rdAddr(x2865(0)).wtAddr(x2671(0))
      val x2647_x2893 = SRAM(size = 96, swapCtr = x2671(0), writeCtr = x2671(0), banking = Strided(1), doubleBuffer = true).wtPort(x2647_vector).rdAddr(x2865(1)).wtAddr(x2671(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2647_x2891.load, x2647_x2893.load), op=FltMul, results=List(CU.vecOut(stage(1), x2655_vector)))
    }
    val x2919 = ComputeUnit(name="x2919", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2850 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2858 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2866 = CounterChain(name = "x2866", x2850, x2858)
      val x2672 = CounterChain.copy(x2745, "x2672")
      val x2648_x2907 = SRAM(size = 96, swapCtr = x2672(0), writeCtr = x2672(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2648_vector).rdAddr(x2866(0)).wtAddr(x2672(0))
      val x2648_x2909 = SRAM(size = 96, swapCtr = x2672(0), writeCtr = x2672(0), banking = Strided(1), doubleBuffer = true).wtPort(x2648_vector).rdAddr(x2866(1)).wtAddr(x2672(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2648_x2907.load, x2648_x2909.load), op=FltMul, results=List(CU.vecOut(stage(1), x2656_vector)))
    }
    val x2935 = ComputeUnit(name="x2935", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2851 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2859 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2867 = CounterChain(name = "x2867", x2851, x2859)
      val x2673 = CounterChain.copy(x2765, "x2673")
      val x2649_x2923 = SRAM(size = 96, swapCtr = x2673(0), writeCtr = x2673(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2649_vector).rdAddr(x2867(0)).wtAddr(x2673(0))
      val x2649_x2925 = SRAM(size = 96, swapCtr = x2673(0), writeCtr = x2673(0), banking = Strided(1), doubleBuffer = true).wtPort(x2649_vector).rdAddr(x2867(1)).wtAddr(x2673(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2649_x2923.load, x2649_x2925.load), op=FltMul, results=List(CU.vecOut(stage(1), x2657_vector)))
    }
    val x2951 = ComputeUnit(name="x2951", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2852 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2860 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2868 = CounterChain(name = "x2868", x2852, x2860)
      val x2674 = CounterChain.copy(x2785, "x2674")
      val x2650_x2939 = SRAM(size = 96, swapCtr = x2674(0), writeCtr = x2674(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2650_vector).rdAddr(x2868(0)).wtAddr(x2674(0))
      val x2650_x2941 = SRAM(size = 96, swapCtr = x2674(0), writeCtr = x2674(0), banking = Strided(1), doubleBuffer = true).wtPort(x2650_vector).rdAddr(x2868(1)).wtAddr(x2674(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2650_x2939.load, x2650_x2941.load), op=FltMul, results=List(CU.vecOut(stage(1), x2658_vector)))
    }
    val x2967 = ComputeUnit(name="x2967", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2675 = CounterChain.copy(x2805, "x2675")
      val x2853 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2861 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2869 = CounterChain(name = "x2869", x2853, x2861)
      val x2651_x2955 = SRAM(size = 96, swapCtr = x2675(0), writeCtr = x2675(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2651_vector).rdAddr(x2869(0)).wtAddr(x2675(0))
      val x2651_x2957 = SRAM(size = 96, swapCtr = x2675(0), writeCtr = x2675(0), banking = Strided(1), doubleBuffer = true).wtPort(x2651_vector).rdAddr(x2869(1)).wtAddr(x2675(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2651_x2955.load, x2651_x2957.load), op=FltMul, results=List(CU.vecOut(stage(1), x2659_vector)))
    }
    val x2983 = ComputeUnit(name="x2983", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2676 = CounterChain.copy(x2825, "x2676")
      val x2854 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2862 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2870 = CounterChain(name = "x2870", x2854, x2862)
      val x2652_x2971 = SRAM(size = 96, swapCtr = x2676(0), writeCtr = x2676(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2652_vector).rdAddr(x2870(0)).wtAddr(x2676(0))
      val x2652_x2973 = SRAM(size = 96, swapCtr = x2676(0), writeCtr = x2676(0), banking = Strided(1), doubleBuffer = true).wtPort(x2652_vector).rdAddr(x2870(1)).wtAddr(x2676(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2652_x2971.load, x2652_x2973.load), op=FltMul, results=List(CU.vecOut(stage(1), x2660_vector)))
    }
    val x2999 = ComputeUnit(name="x2999", parent=x3035, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x2855 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2863 = (Const("0i").out, CU.scalarIn(stage0, x1913_argin).out, Const("1i").out) // Counter
      val x2871 = CounterChain(name = "x2871", x2855, x2863)
      val x2677 = CounterChain.copy(x2845, "x2677")
      val x2653_x2987 = SRAM(size = 96, swapCtr = x2677(0), writeCtr = x2677(0), banking = Duplicated(), doubleBuffer = true).wtPort(x2653_vector).rdAddr(x2871(0)).wtAddr(x2677(0))
      val x2653_x2989 = SRAM(size = 96, swapCtr = x2677(0), writeCtr = x2677(0), banking = Strided(1), doubleBuffer = true).wtPort(x2653_vector).rdAddr(x2871(1)).wtAddr(x2677(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(x2653_x2987.load, x2653_x2989.load), op=FltMul, results=List(CU.vecOut(stage(1), x2661_vector)))
    }
    val x3033 = ComputeUnit(name="x3033", parent=x3035, tpe = Pipe, deps=List(x2999, x2967, x2919, x2887, x2983, x2903, x2935, x2951)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1149 = CU.temp
      val tr1116 = CU.temp
      val tr1154 = CU.temp
      val tr1104 = CU.temp
      val tr1128 = CU.temp
      val tr1122 = CU.temp
      val tr1107 = CU.temp
      val tr1155 = CU.temp
      val tr1150 = CU.temp
      val tr1148 = CU.temp
      val tr1125 = CU.temp
      val tr1157 = CU.temp
      val tr1152 = CU.temp
      val tr1156 = CU.temp
      val tr1153 = CU.temp
      val tr1119 = CU.temp
      val tr1110 = CU.temp
      val tr1113 = CU.temp
      val x2865 = CounterChain.copy(x2903, "x2865")
      val x2634 = CounterChain.copy(x3035, "x2634")
      val x2869 = CounterChain.copy(x2967, "x2869")
      val x2868 = CounterChain.copy(x2951, "x2868")
      val x2871 = CounterChain.copy(x2999, "x2871")
      val x2635 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2636 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2637 = CounterChain(name = "x2637", x2635, x2636)
      val x2867 = CounterChain.copy(x2935, "x2867")
      val x2864 = CounterChain.copy(x2887, "x2864")
      val x2870 = CounterChain.copy(x2983, "x2870")
      val x2866 = CounterChain.copy(x2919, "x2866")
      val x2632_x3017 = SRAM(size = 9216, swapCtr = x2637(0), writeCtr = x2637(0), banking = Strided(1), doubleBuffer = false)
      val x2655_x3010 = SRAM(size = 9216, swapCtr = x2865(0), writeCtr = x2865(0), banking = Strided(1), doubleBuffer = true).wtPort(x2655_vector)
      val x2654_x3009 = SRAM(size = 9216, swapCtr = x2864(0), writeCtr = x2864(0), banking = Strided(1), doubleBuffer = true).wtPort(x2654_vector)
      val x2661_x3016 = SRAM(size = 9216, swapCtr = x2871(0), writeCtr = x2871(0), banking = Strided(1), doubleBuffer = true).wtPort(x2661_vector)
      val x2660_x3015 = SRAM(size = 9216, swapCtr = x2870(0), writeCtr = x2870(0), banking = Strided(1), doubleBuffer = true).wtPort(x2660_vector)
      val x2658_x3013 = SRAM(size = 9216, swapCtr = x2868(0), writeCtr = x2868(0), banking = Strided(1), doubleBuffer = true).wtPort(x2658_vector)
      val x2659_x3014 = SRAM(size = 9216, swapCtr = x2869(0), writeCtr = x2869(0), banking = Strided(1), doubleBuffer = true).wtPort(x2659_vector)
      val x2656_x3011 = SRAM(size = 9216, swapCtr = x2866(0), writeCtr = x2866(0), banking = Strided(1), doubleBuffer = true).wtPort(x2656_vector)
      val x2657_x3012 = SRAM(size = 9216, swapCtr = x2867(0), writeCtr = x2867(0), banking = Strided(1), doubleBuffer = true).wtPort(x2657_vector)
      val wr1160 = CU.wtAddr(x2632_x3017)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2660_x3015))
      Stage(stage(1), operands=List(x2870(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1104)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1104), CU.ctr(stage(1), x2870(1))), op=FixAdd, results=List(x2660_x3015.writeAddr))
      stage = stage0 +: WAStages(2, List(x2659_x3014))
      Stage(stage(1), operands=List(x2869(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1107)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1107), CU.ctr(stage(1), x2869(1))), op=FixAdd, results=List(x2659_x3014.writeAddr))
      stage = stage0 +: WAStages(2, List(x2656_x3011))
      Stage(stage(1), operands=List(x2866(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1110)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1110), CU.ctr(stage(1), x2866(1))), op=FixAdd, results=List(x2656_x3011.writeAddr))
      stage = stage0 +: WAStages(2, List(x2654_x3009))
      Stage(stage(1), operands=List(x2864(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1113)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1113), CU.ctr(stage(1), x2864(1))), op=FixAdd, results=List(x2654_x3009.writeAddr))
      stage = stage0 +: WAStages(2, List(x2655_x3010))
      Stage(stage(1), operands=List(x2865(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1119)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1119), CU.ctr(stage(1), x2865(1))), op=FixAdd, results=List(x2655_x3010.writeAddr))
      stage = stage0 +: WAStages(2, List(x2657_x3012))
      Stage(stage(1), operands=List(x2867(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1116)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1116), CU.ctr(stage(1), x2867(1))), op=FixAdd, results=List(x2657_x3012.writeAddr))
      stage = stage0 +: WAStages(2, List(x2661_x3016))
      Stage(stage(1), operands=List(x2871(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1122)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1122), CU.ctr(stage(1), x2871(1))), op=FixAdd, results=List(x2661_x3016.writeAddr))
      stage = stage0 +: WAStages(2, List(x2658_x3013))
      Stage(stage(1), operands=List(x2868(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1125)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1125), CU.ctr(stage(1), x2868(1))), op=FixAdd, results=List(x2658_x3013.writeAddr))
      stage = stage0 +: Stages(12)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2637(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1128)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1128), CU.ctr(stage(1), x2637(1))), op=FixAdd, results=List(CU.wtAddr(stage(2), wr1160), x2632_x3017.readAddr, x2661_x3016.readAddr, x2660_x3015.readAddr, x2659_x3014.readAddr, x2658_x3013.readAddr, x2657_x3012.readAddr, x2656_x3011.readAddr, x2655_x3010.readAddr, x2654_x3009.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x2634(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(3), tr1148)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x2637(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(4), tr1149)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1148), CU.temp(stage(4), tr1149)), op=BitAnd, results=List(CU.temp(stage(5), tr1150)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1150), CU.load(stage(5), x2654_x3009), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr1152)))
      Stage(stage(7), operands=List(CU.ctr(stage(6), x2634(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(7), tr1153)))
      Stage(stage(8), operands=List(CU.ctr(stage(7), x2637(1)), Const("96i")), op=FixLt, results=List(CU.temp(stage(8), tr1154)))
      Stage(stage(9), operands=List(CU.temp(stage(8), tr1153), CU.temp(stage(8), tr1154)), op=BitAnd, results=List(CU.temp(stage(9), tr1155)))
      Stage(stage(10), operands=List(CU.temp(stage(9), tr1155), CU.load(stage(9), x2655_x3010), Const("0i")), op=Mux, results=List(CU.temp(stage(10), tr1156)))
      Stage(stage(11), operands=List(CU.temp(stage(10), tr1152), CU.temp(stage(10), tr1156)), op=FltAdd, results=List(CU.temp(stage(11), tr1157)))
      Stage(stage(12), operands=List(CU.temp(stage(11), tr1157), CU.load(stage(11), x2632_x3017)), op=FltAdd, results=List(CU.vecOut(stage(12), x2632_vector), CU.store(stage(12), x2632_x3017)))
    }
    val x3055 = ComputeUnit(name="x3055", parent=x3057, tpe = Pipe, deps=List(x3035)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1173 = CU.temp
      val tr1176 = CU.temp
      val tr1172 = CU.temp
      val tr1162 = CU.temp
      val tr1166 = CU.temp
      val tr1174 = CU.temp
      val x2016 = CounterChain.copy(x3057, "x2016")
      val x2017 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2018 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x2019 = CounterChain(name = "x2019", x2017, x2018)
      val x2637 = CounterChain.copy(x3033, "x2637")
      val x2632_x3044 = SRAM(size = 9216, swapCtr = x2637(0), writeCtr = x2637(0), banking = Strided(1), doubleBuffer = true).wtPort(x2632_vector)
      val x2012_x3047 = SRAM(size = 9216, swapCtr = x2019(0), writeCtr = x2019(0), banking = Strided(1), doubleBuffer = false)
      val wr1179 = CU.wtAddr(x2012_x3047)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2632_x3044))
      Stage(stage(1), operands=List(x2637(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1162)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1162), CU.ctr(stage(1), x2637(1))), op=FixAdd, results=List(x2632_x3044.writeAddr))
      stage = stage0 +: Stages(7)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x2019(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1166)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1166), CU.ctr(stage(1), x2019(1))), op=FixAdd, results=List(CU.wtAddr(stage(2), wr1179), x2012_x3047.readAddr, x2632_x3044.readAddr))
      Stage(stage(3), operands=List(CU.ctr(stage(2), x2016(0)), CU.scalarIn(stage(2), x2013_argin)), op=FixLt, results=List(CU.temp(stage(3), tr1172)))
      Stage(stage(4), operands=List(CU.ctr(stage(3), x2019(0)), Const("96i")), op=FixLt, results=List(CU.temp(stage(4), tr1173)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1172), CU.temp(stage(4), tr1173)), op=BitAnd, results=List(CU.temp(stage(5), tr1174)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1174), CU.load(stage(5), x2632_x3044), Const("0i")), op=Mux, results=List(CU.temp(stage(6), tr1176)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1176), CU.load(stage(6), x2012_x3047)), op=FltAdd, results=List(CU.vecOut(stage(7), x2012_vector), CU.store(stage(7), x2012_x3047)))
    }
    val x3112 = ComputeUnit(name="x3112", parent=x3114, tpe = MetaPipeline, deps=List(x3057)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3059 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x3060 = CounterChain(name = "x3060", x3059)
    }
    val x3102 = ComputeUnit(name="x3102", parent=x3112, tpe = Pipe, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1184 = CU.temp
      val tr1181 = CU.temp
      val x3089 = (Const("0i").out, Const("96i").out, Const("1i").out) // Counter
      val x3090 = CounterChain(name = "x3090", x3089)
      val x3060 = CounterChain.copy(x3112, "x3060")
      val x2019 = CounterChain.copy(x3055, "x2019")
      val x2012_x3095 = SRAM(size = 9216, swapCtr = x2019(0), writeCtr = x2019(0), banking = Strided(1), doubleBuffer = false).wtPort(x2012_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: WAStages(2, List(x2012_x3095))
      Stage(stage(1), operands=List(x2019(0), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1181)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1181), CU.ctr(stage(1), x2019(1))), op=FixAdd, results=List(x2012_x3095.writeAddr))
      stage = stage0 +: Stages(3)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3060(0)), Const("96i")), op=FixMul, results=List(CU.temp(stage(1), tr1184)))
      Stage(stage(2), operands=List(CU.temp(stage(1), tr1184), CU.ctr(stage(1), x3090(0))), op=FixAdd, results=List(x2012_x3095.readAddr))
      Stage(stage(3), operands=List(CU.load(stage(2), x2012_x3095)), op=Bypass, results=List(CU.vecOut(stage(3), x3058_vector)))
    }
    val x3107 = UnitComputeUnit(name ="x3107", parent=x3112, deps=List(x3102)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3060 = CounterChain.copy(x3112, "x3060")
      val x3107_unitCC = CounterChain(name = "x3107_unitCC", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x3060(0)), CU.scalarIn(stage(0), x1913_argin)), op=FixMul, results=List(CU.scalarOut(stage(1), x3103_scalar)))
    }
    val x3109 = TileTransfer(name="x3109", parent=x3112, memctrl=x3109_mc_mc, mctpe=TileStore, deps=List(x3107), vec=x3058_vector) { implicit CU => 
      val stage0 = CU.emptyStage
      val x3109_ctr = (Const("0l").out, Const("96i").out, Const("1l").out) // Counter
      val x3109_cc = CounterChain(name = "x3109_cc", x3109_ctr)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(1)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x3103_scalar), CU.ctr(stage(0), x3109_cc(0))), op=FixAdd, results=List(CU.scalarOut(stage(1), x3109_mc_mc.saddr)))
    }
    
  }
}
