import pir._
import pir.node._
import arch._
import prism.enums._

object SGD_minibatch extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x2582 = withCtrl(design.top.topController) { ArgIn(init=0).name("x2582").srcCtx("SGD_minibatch.scala:24:18:E") } // ArgInNew(Const(0))
    isAccum(x2582) = false
    bufferDepthOf(x2582) = 1
    boundOf(x2582) = 1
    val x2583 = withCtrl(design.top.topController) { ArgIn(init=0).name("x2583").srcCtx("SGD_minibatch.scala:25:18:N") } // ArgInNew(Const(0))
    isAccum(x2583) = false
    bufferDepthOf(x2583) = 1
    boundOf(x2583) = 1024
    val x2584 = withCtrl(design.top.topController) { ArgIn(init=0.0).name("x2584").srcCtx("SGD_minibatch.scala:26:18:A") } // ArgInNew(Const(0))
    isAccum(x2584) = false
    bufferDepthOf(x2584) = 1
    boundOf(x2584) = 0
    val x2588 = withCtrl(design.top.topController) { ReadMem(x2583).name("x2588").srcCtx("SGD_minibatch.scala:32:22") } // RegRead(x2583)
    val x2589 = withCtrl(design.top.topController) { DRAM(dims=List(x2588, Const(16))).name("x2589").srcCtx("SGD_minibatch.scala:32:21:x") } // x2589 = DRAMNew(ArrayBuffer(x2588, Const(16)),Const(0))
    val x2590 = withCtrl(design.top.topController) { ReadMem(x2583).name("x2590").srcCtx("SGD_minibatch.scala:33:22") } // RegRead(x2583)
    val x2591 = withCtrl(design.top.topController) { DRAM(dims=List(x2590)).name("x2591").srcCtx("SGD_minibatch.scala:33:21:y") } // x2591 = DRAMNew(ArrayBuffer(x2590),Const(0))
    val x2592 = withCtrl(design.top.topController) { DRAM(dims=List(Const(16))).name("x2592").srcCtx("SGD_minibatch.scala:34:26:result") } // x2592 = DRAMNew(ArrayBuffer(Const(16)),Const(0))
    val x2755 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x2755").srcCtx("SGD_minibatch.scala:39:11") } // Hwblock(Block(Const(())),false)
    val x2604_d0_b0 = withCtrl(x2755) { SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2604_d0_b0").srcCtx("SGD_minibatch.scala:40:28:y_tile") } // x2604 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2604_d0_b0) = false
    bufferDepthOf(x2604_d0_b0) = 3
    staticDimsOf(x2604_d0_b0) = List(16)
    val x2605_d0_b0 = withCtrl(x2755) { SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2605_d0_b0").srcCtx("SGD_minibatch.scala:41:30:sgdmodel") } // x2605 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2605_d0_b0) = false
    bufferDepthOf(x2605_d0_b0) = 1
    staticDimsOf(x2605_d0_b0) = List(16)
    val x2605_d1_b0 = withCtrl(x2755) { SRAM(size=16, banking=Strided(banks=1, stride=1)).name("x2605_d1_b0").srcCtx("SGD_minibatch.scala:41:30:sgdmodel") } // x2605 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2605_d1_b0) = true
    bufferDepthOf(x2605_d1_b0) = 1
    staticDimsOf(x2605_d1_b0) = List(16)
    val x2605_d2_b0 = withCtrl(x2755) { SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2605_d2_b0").srcCtx("SGD_minibatch.scala:41:30:sgdmodel") } // x2605 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2605_d2_b0) = false
    bufferDepthOf(x2605_d2_b0) = 2
    staticDimsOf(x2605_d2_b0) = List(16)
    val x2606_d0_b0 = withCtrl(x2755) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x2606_d0_b0").srcCtx("SGD_minibatch.scala:42:28:x_tile") } // x2606 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x2606_d0_b0) = false
    bufferDepthOf(x2606_d0_b0) = 3
    staticDimsOf(x2606_d0_b0) = List(16, 16)
    val x2606_d1_b0 = withCtrl(x2755) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x2606_d1_b0").srcCtx("SGD_minibatch.scala:42:28:x_tile") } // x2606 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x2606_d1_b0) = false
    bufferDepthOf(x2606_d1_b0) = 2
    staticDimsOf(x2606_d1_b0) = List(16, 16)
    val x2607 = withCtrl(x2755) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2607").srcCtx("SGD_minibatch.scala:43:14") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2608 = withCtrl(x2755) { CounterChain(List(x2607)).name("x2608").srcCtx("SGD_minibatch.scala:43:20") } // CounterChainNew(List(x2607))
    val x2610 = withCtrl(x2755) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2608).name("x2610").srcCtx("SGD_minibatch.scala:43:20") } // UnrolledForeach(List(Const(true)),x2608,Block(Const(())),List(List(b1570)),List(List(b1571)))
    val b1570 = withCtrl(x2610) { CounterIter(x2607, None).name("b1570") } // b1570
    val b1571 = withCtrl(x2610) { Const(true).name("b1571") } // b1571
    val x2609 = withCtrl(x2610) { StoreBanks(List(List(x2605_d0_b0), List(x2605_d1_b0), List(x2605_d2_b0)), List(b1570), Const(0.0)).name("x2609").srcCtx("SGD_minibatch.scala:43:39") } // ParSRAMStore(x2605,List(List(b1570)),List(Const(0)),List(b1571))
    val x2611 = withCtrl(x2755) { ReadMem(x2582).name("x2611").srcCtx("SGD_minibatch.scala:44:26") } // RegRead(x2582)
    val x2612 = withCtrl(x2755) { Counter(min=Const(0), max=x2611, step=Const(1), par=1).name("x2612").srcCtx("SGD_minibatch.scala:44:28") } // CounterNew(Const(0),x2611,Const(1),Const(1))
    val x2613 = withCtrl(x2755) { CounterChain(List(x2612)).name("x2613").srcCtx("SGD_minibatch.scala:44:34") } // CounterChainNew(List(x2612))
    val x2732 = withCtrl(x2755) { LoopController(style=SeqPipe, level=OuterControl, cchain=x2613).name("x2732").srcCtx("SGD_minibatch.scala:44:34") } // UnrolledForeach(List(Const(true)),x2613,Block(Const(())),List(List(b1577)),List(List(b1578)))
    val b1577 = withCtrl(x2732) { CounterIter(x2612, Some(0)).name("b1577") } // b1577
    val b1578 = withCtrl(x2732) { Const(true).name("b1578") } // b1578
    val x2614 = withCtrl(x2732) { ReadMem(x2583).name("x2614").srcCtx("SGD_minibatch.scala:45:18") } // RegRead(x2583)
    val x2615 = withCtrl(x2732) { Counter(min=Const(0), max=x2614, step=Const(16), par=1).name("x2615").srcCtx("SGD_minibatch.scala:45:26") } // CounterNew(Const(0),x2614,Const(16),Const(1))
    val x2616 = withCtrl(x2732) { CounterChain(List(x2615)).name("x2616").srcCtx("SGD_minibatch.scala:45:34") } // CounterChainNew(List(x2615))
    val x2731 = withCtrl(x2732) { LoopController(style=MetaPipe, level=OuterControl, cchain=x2616).name("x2731").srcCtx("SGD_minibatch.scala:45:34") } // UnrolledForeach(List(b1578),x2616,Block(Const(())),List(List(b1582)),List(List(b1583)))
    val b1582 = withCtrl(x2731) { CounterIter(x2615, Some(0)).name("b1582") } // b1582
    val b1583 = withCtrl(x2731) { Const(true).name("b1583") } // b1583
    val x2618 = withCtrl(x2731) { UnitController(style=SeqPipe, level=InnerControl).name("x2618").srcCtx("SGD_minibatch.scala:45:34") } // UnitPipe(List(b1583, b1578),Block(Const(())))
    val x2617 = withCtrl(x2618) { OpDef(op=FixAdd, inputs=List(b1582, Const(16))).name("x2617").srcCtx("SGD_minibatch.scala:46:29") } // FixAdd(b1582,Const(16))
    val x2639 = withCtrl(x2731) { UnitController(style=StreamPipe, level=OuterControl).name("x2639").srcCtx("SGD_minibatch.scala:46:18") } // UnitPipe(List(b1583, b1578),Block(Const(())))
    val b2781 = withCtrl(x2639) { StreamOut(field="offset").name("b2781").srcCtx("SGD_minibatch.scala:46:18") } // x2619 = StreamOutNew(BurstCmdBus)
    isAccum(b2781) = false
    bufferDepthOf(b2781) = 1
    val b2782 = withCtrl(x2639) { StreamOut(field="size").name("b2782").srcCtx("SGD_minibatch.scala:46:18") } // x2619 = StreamOutNew(BurstCmdBus)
    isAccum(b2782) = false
    bufferDepthOf(b2782) = 1
    val x2620 = withCtrl(x2639) { StreamIn(field="data").name("x2620").srcCtx("SGD_minibatch.scala:46:18") } // x2620 = StreamInNew(BurstDataBus())
    isAccum(x2620) = false
    bufferDepthOf(x2620) = 1
    val x2629 = withCtrl(x2639) { UnitController(style=SeqPipe, level=InnerControl).name("x2629").srcCtx("SGD_minibatch.scala:46:18") } // UnitPipe(List(b1583, b1578),Block(x2628))
    val x2621 = withCtrl(x2629) { b1582 } // FixConvert(b1582,TRUE,_32,_0) (Same Type. No op)
    val x2622 = withCtrl(x2629) { OpDef(op=FixSla, inputs=List(x2621, Const(2))).name("x2622").srcCtx("SGD_minibatch.scala:46:18") } // FixLsh(x2621,Const(2))
    val x2623 = withCtrl(x2629) { x2622 } // FixConvert(x2622,TRUE,_64,_0)
    val x2624 = withCtrl(x2629) { DramAddress(x2591).name("x2624").srcCtx("SGD_minibatch.scala:46:18") } // GetDRAMAddress(x2591)
    val x2626_x2625 = withCtrl(x2629) { OpDef(op=FixAdd, inputs=List(x2623, x2624)).name("x2626_x2625").srcCtx("SGD_minibatch.scala:46:18") } // FixAdd(x2623,x2624)
    // x2626 = SimpleStruct(ArrayBuffer((offset,x2625), (size,Const(64)), (isLoad,Const(true))))
    val x2627 = withCtrl(x2629) { OpDef(op=BitAnd, inputs=List(b1583, b1578)).name("x2627").srcCtx("UnrollingBase.scala:28:66") } // And(b1583,b1578)
    val x2628_b2783_b2781 = withCtrl(x2629) { WriteMem(b2781, x2626_x2625).name("x2628_b2783_b2781").srcCtx("SGD_minibatch.scala:46:18") } // StreamWrite(x2619,x2626,x2627)
    val x2628_b2784_b2782 = withCtrl(x2629) { WriteMem(b2782, Const(64)).name("x2628_b2784_b2782").srcCtx("SGD_minibatch.scala:46:18") } // StreamWrite(x2619,x2626,x2627)
    val x2630 = withCtrl(x2639) { FringeDenseLoad(dram=List(x2591), cmdStream=List(b2781, b2782), dataStream=List(x2620)).name("x2630").srcCtx("SGD_minibatch.scala:46:18") } // FringeDenseLoad(x2591,x2619,x2620)
    val x2631 = withCtrl(x2639) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2631").srcCtx("SGD_minibatch.scala:46:18") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2632 = withCtrl(x2639) { CounterChain(List(x2631)).name("x2632").srcCtx("SGD_minibatch.scala:46:18") } // CounterChainNew(List(x2631))
    val x2638 = withCtrl(x2639) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2632).name("x2638").srcCtx("SGD_minibatch.scala:46:18") } // UnrolledForeach(List(b1583, b1578),x2632,Block(Const(())),List(List(b1600)),List(List(b1601)))
    val b1600 = withCtrl(x2638) { CounterIter(x2631, None).name("b1600") } // b1600
    val b1601 = withCtrl(x2638) { Const(true).name("b1601") } // b1601
    val x2633 = withCtrl(x2638) { OpDef(op=BitAnd, inputs=List(b1601, b1583)).name("x2633").srcCtx("UnrollingBase.scala:28:66") } // And(b1601,b1583)
    val x2634 = withCtrl(x2638) { OpDef(op=BitAnd, inputs=List(x2633, b1578)).name("x2634").srcCtx("UnrollingBase.scala:28:66") } // And(x2633,b1578)
    val x2635_x2635 = withCtrl(x2638) { ReadMem(x2620).name("x2635_x2635").srcCtx("SGD_minibatch.scala:46:18") } // ParStreamRead(x2620,List(x2634))
    val x2636_x2636 = withCtrl(x2638) { x2635_x2635 } // VectorApply(x2635,0)
    val x2637 = withCtrl(x2638) { StoreBanks(List(List(x2604_d0_b0)), List(b1600), x2636_x2636).name("x2637").srcCtx("SGD_minibatch.scala:46:18") } // ParSRAMStore(x2604,List(List(b1600)),List(x2636),List(x2634))
    val x2640 = withCtrl(x2731) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2640").srcCtx("SGD_minibatch.scala:47:18") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2641 = withCtrl(x2731) { CounterChain(List(x2640)).name("x2641").srcCtx("SGD_minibatch.scala:47:18") } // CounterChainNew(List(x2640))
    val x2668 = withCtrl(x2731) { LoopController(style=StreamPipe, level=OuterControl, cchain=x2641).name("x2668").srcCtx("SGD_minibatch.scala:47:18") } // UnrolledForeach(List(b1583, b1578),x2641,Block(Const(())),List(List(b1611)),List(List(b1612)))
    val b1611 = withCtrl(x2668) { CounterIter(x2640, Some(0)).name("b1611") } // b1611
    val b1612 = withCtrl(x2668) { Const(true).name("b1612") } // b1612
    val b2785 = withCtrl(x2668) { StreamOut(field="offset").name("b2785").srcCtx("SGD_minibatch.scala:47:18") } // x2642 = StreamOutNew(BurstCmdBus)
    isAccum(b2785) = false
    bufferDepthOf(b2785) = 1
    val b2786 = withCtrl(x2668) { StreamOut(field="size").name("b2786").srcCtx("SGD_minibatch.scala:47:18") } // x2642 = StreamOutNew(BurstCmdBus)
    isAccum(b2786) = false
    bufferDepthOf(b2786) = 1
    val x2643 = withCtrl(x2668) { StreamIn(field="data").name("x2643").srcCtx("SGD_minibatch.scala:47:18") } // x2643 = StreamInNew(BurstDataBus())
    isAccum(x2643) = false
    bufferDepthOf(x2643) = 1
    val x2657 = withCtrl(x2668) { UnitController(style=SeqPipe, level=InnerControl).name("x2657").srcCtx("SGD_minibatch.scala:47:18") } // UnitPipe(List(b1612, b1583, b1578),Block(x2656))
    val x2644 = withCtrl(x2657) { OpDef(op=FixAdd, inputs=List(b1582, b1611)).name("x2644").srcCtx("SGD_minibatch.scala:47:18") } // FixAdd(b1582,b1611)
    val x2645 = withCtrl(x2657) { x2644 } // FixConvert(x2644,TRUE,_32,_0) (Same Type. No op)
    val x2646 = withCtrl(x2657) { OpDef(op=FixSla, inputs=List(x2645, Const(4))).name("x2646").srcCtx("SGD_minibatch.scala:47:18") } // FixLsh(x2645,Const(4))
    val x2647 = withCtrl(x2657) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x2648 = withCtrl(x2657) { OpDef(op=FixAdd, inputs=List(x2646, x2647)).name("x2648").srcCtx("SGD_minibatch.scala:47:18") } // FixAdd(x2646,x2647)
    val x2649 = withCtrl(x2657) { OpDef(op=FixSla, inputs=List(x2648, Const(2))).name("x2649").srcCtx("SGD_minibatch.scala:47:18") } // FixLsh(x2648,Const(2))
    val x2650 = withCtrl(x2657) { x2649 } // FixConvert(x2649,TRUE,_64,_0)
    val x2651 = withCtrl(x2657) { DramAddress(x2589).name("x2651").srcCtx("SGD_minibatch.scala:47:18") } // GetDRAMAddress(x2589)
    val x2653_x2652 = withCtrl(x2657) { OpDef(op=FixAdd, inputs=List(x2650, x2651)).name("x2653_x2652").srcCtx("SGD_minibatch.scala:47:18") } // FixAdd(x2650,x2651)
    // x2653 = SimpleStruct(ArrayBuffer((offset,x2652), (size,Const(64)), (isLoad,Const(true))))
    val x2654 = withCtrl(x2657) { OpDef(op=BitAnd, inputs=List(b1612, b1583)).name("x2654").srcCtx("UnrollingBase.scala:28:66") } // And(b1612,b1583)
    val x2655 = withCtrl(x2657) { OpDef(op=BitAnd, inputs=List(x2654, b1578)).name("x2655").srcCtx("UnrollingBase.scala:28:66") } // And(x2654,b1578)
    val x2656_b2787_b2785 = withCtrl(x2657) { WriteMem(b2785, x2653_x2652).name("x2656_b2787_b2785").srcCtx("SGD_minibatch.scala:47:18") } // StreamWrite(x2642,x2653,x2655)
    val x2656_b2788_b2786 = withCtrl(x2657) { WriteMem(b2786, Const(64)).name("x2656_b2788_b2786").srcCtx("SGD_minibatch.scala:47:18") } // StreamWrite(x2642,x2653,x2655)
    val x2658 = withCtrl(x2668) { FringeDenseLoad(dram=List(x2589), cmdStream=List(b2785, b2786), dataStream=List(x2643)).name("x2658").srcCtx("SGD_minibatch.scala:47:18") } // FringeDenseLoad(x2589,x2642,x2643)
    val x2659 = withCtrl(x2668) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2659").srcCtx("SGD_minibatch.scala:47:18") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2660 = withCtrl(x2668) { CounterChain(List(x2659)).name("x2660").srcCtx("SGD_minibatch.scala:47:18") } // CounterChainNew(List(x2659))
    val x2667 = withCtrl(x2668) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2660).name("x2667").srcCtx("SGD_minibatch.scala:47:18") } // UnrolledForeach(List(b1612, b1583, b1578),x2660,Block(Const(())),List(List(b1632)),List(List(b1633)))
    val b1632 = withCtrl(x2667) { CounterIter(x2659, None).name("b1632") } // b1632
    val b1633 = withCtrl(x2667) { Const(true).name("b1633") } // b1633
    val x2661 = withCtrl(x2667) { OpDef(op=BitAnd, inputs=List(b1633, b1612)).name("x2661").srcCtx("UnrollingBase.scala:28:66") } // And(b1633,b1612)
    val x2662 = withCtrl(x2667) { OpDef(op=BitAnd, inputs=List(b1583, b1578)).name("x2662").srcCtx("UnrollingBase.scala:28:66") } // And(b1583,b1578)
    val x2663 = withCtrl(x2667) { OpDef(op=BitAnd, inputs=List(x2661, x2662)).name("x2663").srcCtx("UnrollingBase.scala:28:66") } // And(x2661,x2662)
    val x2664_x2664 = withCtrl(x2667) { ReadMem(x2643).name("x2664_x2664").srcCtx("SGD_minibatch.scala:47:18") } // ParStreamRead(x2643,List(x2663))
    val x2665_x2665 = withCtrl(x2667) { x2664_x2664 } // VectorApply(x2664,0)
    val x2666 = withCtrl(x2667) { StoreBanks(List(List(x2606_d0_b0), List(x2606_d1_b0)), List(b1611, b1632), x2665_x2665).name("x2666").srcCtx("SGD_minibatch.scala:47:18") } // ParSRAMStore(x2606,List(List(b1611, b1632)),List(x2665),List(x2663))
    val x2669_d0_b0 = withCtrl(x2731) { SRAM(size=16, banking=Strided(banks=1, stride=1)).name("x2669_d0_b0").srcCtx("SGD_minibatch.scala:48:31:y_err") } // x2669 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2669_d0_b0) = false
    bufferDepthOf(x2669_d0_b0) = 2
    staticDimsOf(x2669_d0_b0) = List(16)
    val x2670 = withCtrl(x2731) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2670").srcCtx("SGD_minibatch.scala:49:27") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2671 = withCtrl(x2731) { CounterChain(List(x2670)).name("x2671").srcCtx("SGD_minibatch.scala:49:36") } // CounterChainNew(List(x2670))
    val x2698 = withCtrl(x2731) { LoopController(style=MetaPipe, level=OuterControl, cchain=x2671).name("x2698").srcCtx("SGD_minibatch.scala:49:36") } // UnrolledForeach(List(b1583, b1578),x2671,Block(Const(())),List(List(b1645)),List(List(b1646)))
    val b1645 = withCtrl(x2698) { CounterIter(x2670, Some(0)).name("b1645") } // b1645
    val b1646 = withCtrl(x2698) { Const(true).name("b1646") } // b1646
    val x2672_d0 = withCtrl(x2698) { Reg(init=Some(0.0)).name("x2672_d0").srcCtx("SGD_minibatch.scala:50:35:y_hat") } // x2672 = RegNew(Const(0))
    isAccum(x2672_d0) = false
    bufferDepthOf(x2672_d0) = 2
    val x2672_d1 = withCtrl(x2698) { Reg(init=Some(0.0)).name("x2672_d1").srcCtx("SGD_minibatch.scala:50:35:y_hat") } // x2672 = RegNew(Const(0))
    isAccum(x2672_d1) = true
    bufferDepthOf(x2672_d1) = 1
    val x2673 = withCtrl(x2698) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2673").srcCtx("SGD_minibatch.scala:50:48") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2674 = withCtrl(x2698) { CounterChain(List(x2673)).name("x2674").srcCtx("SGD_minibatch.scala:50:96") } // CounterChainNew(List(x2673))
    val x2690 = withCtrl(x2698) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2674).name("x2690").srcCtx("SGD_minibatch.scala:50:96") } // UnrolledReduce(List(b1646, b1583, b1578),x2674,x2672,Block((x2672) => Const(())),List(List(b1650)),List(List(b1651)))
    val b1650 = withCtrl(x2690) { CounterIter(x2673, None).name("b1650") } // b1650
    val b1651 = withCtrl(x2690) { Const(true).name("b1651") } // b1651
    val x2675 = withCtrl(x2690) { OpDef(op=BitAnd, inputs=List(b1651, b1646)).name("x2675").srcCtx("UnrollingBase.scala:28:66") } // And(b1651,b1646)
    val x2676 = withCtrl(x2690) { OpDef(op=BitAnd, inputs=List(b1583, b1578)).name("x2676").srcCtx("UnrollingBase.scala:28:66") } // And(b1583,b1578)
    val x2677 = withCtrl(x2690) { OpDef(op=BitAnd, inputs=List(x2675, x2676)).name("x2677").srcCtx("UnrollingBase.scala:28:66") } // And(x2675,x2676)
    val x2678 = withCtrl(x2690) { LoadBanks(List(x2606_d1_b0), List(b1645, b1650)).name("x2678").srcCtx("SGD_minibatch.scala:50:68") } // ParSRAMLoad(x2606,List(List(b1645, b1650)),List(x2677))
    val x2679 = withCtrl(x2690) { x2678 } // VectorApply(x2678,0)
    val x2680 = withCtrl(x2690) { LoadBanks(List(x2605_d2_b0), List(b1650)).name("x2680").srcCtx("SGD_minibatch.scala:50:84") } // ParSRAMLoad(x2605,List(List(b1650)),List(x2677))
    val x2681 = withCtrl(x2690) { x2680 } // VectorApply(x2680,0)
    val x2682 = withCtrl(x2690) { x2681 } // FixConvert(x2681,TRUE,_16,_16) (Same Type. No op)
    val x2683 = withCtrl(x2690) { OpDef(op=FixMul, inputs=List(x2679, x2682)).name("x2683").srcCtx("SGD_minibatch.scala:50:74") } // FixMul(x2679,x2682)
    val x2684 = withCtrl(x2690) { ReadMem(x2672_d1).name("x2684").srcCtx("SGD_minibatch.scala:50:96") } // RegRead(x2672)
    val x2685 = withCtrl(x2690) { OpDef(op=FixEql, inputs=List(b1650, Const(0))).name("x2685").srcCtx("SGD_minibatch.scala:50:96") } // FixEql(b1650,Const(0))
    val x2686 = withCtrl(x2690) { ReduceAccumOp(op=FixAdd, input=x2683, accum=x2684).name("x2686").srcCtx("SGD_minibatch.scala:50:98") } // FixAdd(x2683,x2684)
    val x2687 = withCtrl(x2690) { OpDef(op=BitAnd, inputs=List(b1646, b1583)).name("x2687").srcCtx("UnrollingBase.scala:28:66") } // And(b1646,b1583)
    val x2688 = withCtrl(x2690) { OpDef(op=BitAnd, inputs=List(x2687, b1578)).name("x2688").srcCtx("UnrollingBase.scala:28:66") } // And(x2687,b1578)
    val x2689_x2672_d0 = withCtrl(x2690) { WriteMem(x2672_d0, x2686).name("x2689_x2672_d0").srcCtx("SGD_minibatch.scala:50:96") } // RegWrite(x2672,x2686,x2688)
    antiDepsOf(x2689_x2672_d0)=List(x2684)
    val x2689_x2672_d1 = withCtrl(x2690) { WriteMem(x2672_d1, x2686).name("x2689_x2672_d1").srcCtx("SGD_minibatch.scala:50:96") } // RegWrite(x2672,x2686,x2688)
    antiDepsOf(x2689_x2672_d1)=List(x2684)
    val x2697 = withCtrl(x2698) { UnitController(style=SeqPipe, level=InnerControl).name("x2697").srcCtx("SGD_minibatch.scala:49:36") } // UnitPipe(List(b1646, b1583, b1578),Block(Const(())))
    val x2691 = withCtrl(x2697) { OpDef(op=BitAnd, inputs=List(b1646, b1583)).name("x2691").srcCtx("UnrollingBase.scala:28:66") } // And(b1646,b1583)
    val x2692 = withCtrl(x2697) { OpDef(op=BitAnd, inputs=List(x2691, b1578)).name("x2692").srcCtx("UnrollingBase.scala:28:66") } // And(x2691,b1578)
    val x2693 = withCtrl(x2697) { LoadBanks(List(x2604_d0_b0), List(b1645)).name("x2693").srcCtx("SGD_minibatch.scala:51:30") } // SRAMLoad(x2604,ArrayBuffer(Const(16)),List(b1645),Const(0),x2692)
    val x2694 = withCtrl(x2697) { ReadMem(x2672_d0).name("x2694").srcCtx("SGD_minibatch.scala:51:42") } // RegRead(x2672)
    val x2695 = withCtrl(x2697) { OpDef(op=FixSub, inputs=List(x2693, x2694)).name("x2695").srcCtx("SGD_minibatch.scala:51:34") } // FixSub(x2693,x2694)
    val x2696 = withCtrl(x2697) { StoreBanks(List(List(x2669_d0_b0)), List(b1645), x2695).name("x2696").srcCtx("SGD_minibatch.scala:51:22") } // SRAMStore(x2669,ArrayBuffer(Const(16)),List(b1645),Const(0),x2695,x2692)
    val x2699 = withCtrl(x2731) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2699").srcCtx("SGD_minibatch.scala:53:37") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2700 = withCtrl(x2731) { CounterChain(List(x2699)).name("x2700").srcCtx("SGD_minibatch.scala:57:13") } // CounterChainNew(List(x2699))
    val x2730 = withCtrl(x2731) { LoopController(style=MetaPipe, level=OuterControl, cchain=x2700).name("x2730").srcCtx("SGD_minibatch.scala:57:13") } // UnrolledReduce(List(b1583, b1578),x2700,x2605,Block((x2605) => Const(())),List(List(b1680)),List(List(b1681)))
    val b1680 = withCtrl(x2730) { CounterIter(x2699, Some(0)).name("b1680") } // b1680
    val b1681 = withCtrl(x2730) { Const(true).name("b1681") } // b1681
    val x2701_d0_b0 = withCtrl(x2730) { SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2701_d0_b0").srcCtx("SGD_minibatch.scala:54:31:row") } // x2701 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2701_d0_b0) = false
    bufferDepthOf(x2701_d0_b0) = 2
    staticDimsOf(x2701_d0_b0) = List(16)
    val x2702 = withCtrl(x2730) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2702").srcCtx("SGD_minibatch.scala:55:28") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2703 = withCtrl(x2730) { CounterChain(List(x2702)).name("x2703").srcCtx("SGD_minibatch.scala:55:36") } // CounterChainNew(List(x2702))
    val x2714 = withCtrl(x2730) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2703).name("x2714").srcCtx("SGD_minibatch.scala:55:36") } // UnrolledForeach(List(b1681, b1583, b1578),x2703,Block(Const(())),List(List(b1685)),List(List(b1686)))
    val b1685 = withCtrl(x2714) { CounterIter(x2702, None).name("b1685") } // b1685
    val b1686 = withCtrl(x2714) { Const(true).name("b1686") } // b1686
    val x2704 = withCtrl(x2714) { OpDef(op=BitAnd, inputs=List(b1686, b1681)).name("x2704").srcCtx("UnrollingBase.scala:28:66") } // And(b1686,b1681)
    val x2705 = withCtrl(x2714) { OpDef(op=BitAnd, inputs=List(b1583, b1578)).name("x2705").srcCtx("UnrollingBase.scala:28:66") } // And(b1583,b1578)
    val x2706 = withCtrl(x2714) { OpDef(op=BitAnd, inputs=List(x2704, x2705)).name("x2706").srcCtx("UnrollingBase.scala:28:66") } // And(x2704,x2705)
    val x2707 = withCtrl(x2714) { LoadBanks(List(x2606_d0_b0), List(b1680, b1685)).name("x2707").srcCtx("SGD_minibatch.scala:55:58") } // ParSRAMLoad(x2606,List(List(b1680, b1685)),List(x2706))
    val x2708 = withCtrl(x2714) { x2707 } // VectorApply(x2707,0)
    val x2709 = withCtrl(x2714) { LoadBanks(List(x2669_d0_b0), List(b1680)).name("x2709").srcCtx("SGD_minibatch.scala:55:71") } // SRAMLoad(x2669,ArrayBuffer(Const(16)),List(b1680),Const(0),x2706)
    val x2710 = withCtrl(x2714) { OpDef(op=FixMul, inputs=List(x2708, x2709)).name("x2710").srcCtx("SGD_minibatch.scala:55:64") } // FixMul(x2708,x2709)
    val x2711 = withCtrl(x2714) { ReadMem(x2584).name("x2711").srcCtx("SGD_minibatch.scala:55:77") } // RegRead(x2584)
    val x2712 = withCtrl(x2714) { OpDef(op=FixMul, inputs=List(x2710, x2711)).name("x2712").srcCtx("SGD_minibatch.scala:55:75") } // FixMul(x2710,x2711)
    val x2713 = withCtrl(x2714) { StoreBanks(List(List(x2701_d0_b0)), List(b1685), x2712).name("x2713").srcCtx("SGD_minibatch.scala:55:50") } // ParSRAMStore(x2701,List(List(b1685)),List(x2712),List(x2706))
    val x2715 = withCtrl(x2730) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2715").srcCtx("SGD_minibatch.scala:57:13") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2716 = withCtrl(x2730) { CounterChain(List(x2715)).name("x2716").srcCtx("SGD_minibatch.scala:57:13") } // CounterChainNew(ArrayBuffer(x2715))
    val x2729 = withCtrl(x2730) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2716).name("x2729").srcCtx("SGD_minibatch.scala:57:13") } // UnrolledForeach(List(),x2716,Block(Const(())),ArrayBuffer(List(b1698)),ArrayBuffer(List(b1699)))
    val b1698 = withCtrl(x2729) { CounterIter(x2715, None).name("b1698") } // b1698
    val b1699 = withCtrl(x2729) { Const(true).name("b1699") } // b1699
    val x2717 = withCtrl(x2729) { OpDef(op=BitAnd, inputs=List(b1699, b1583)).name("x2717").srcCtx("UnrollingBase.scala:28:66") } // And(b1699,b1583)
    val x2718 = withCtrl(x2729) { OpDef(op=BitAnd, inputs=List(x2717, b1578)).name("x2718").srcCtx("UnrollingBase.scala:28:66") } // And(x2717,b1578)
    val x2719 = withCtrl(x2729) { LoadBanks(List(x2701_d0_b0), List(b1698)).name("x2719").srcCtx("SGD_minibatch.scala:57:13") } // ParSRAMLoad(x2701,List(ArrayBuffer(b1698)),List(x2718))
    val x2720 = withCtrl(x2729) { x2719 } // VectorApply(x2719,0)
    val x2721 = withCtrl(x2729) { LoadBanks(List(x2605_d1_b0), List(b1698)).name("x2721").srcCtx("SGD_minibatch.scala:57:13") } // ParSRAMLoad(x2605,List(ArrayBuffer(b1698)),List(x2718))
    val x2722 = withCtrl(x2729) { x2721 } // VectorApply(x2721,0)
    val x2723 = withCtrl(x2729) { OpDef(op=BitAnd, inputs=List(b1681, b1583)).name("x2723").srcCtx("SGD_minibatch.scala:57:13") } // And(b1681,b1583)
    val x2724 = withCtrl(x2729) { OpDef(op=BitAnd, inputs=List(x2723, b1578)).name("x2724").srcCtx("SGD_minibatch.scala:57:13") } // And(x2723,b1578)
    val x2725 = withCtrl(x2729) { OpDef(op=BitAnd, inputs=List(x2724, x2718)).name("x2725").srcCtx("SGD_minibatch.scala:57:13") } // And(x2724,x2718)
    val x2726 = withCtrl(x2729) { OpDef(op=FixEql, inputs=List(b1680, Const(0))).name("x2726").srcCtx("SGD_minibatch.scala:57:13") } // FixEql(b1680,Const(0))
    val x2727 = withCtrl(x2729) { OpDef(op=FixAdd, inputs=List(x2720, x2722)).name("x2727").srcCtx("SGD_minibatch.scala:57:17") } // FixAdd(x2720,x2722)
    val x2728 = withCtrl(x2729) { StoreBanks(List(List(x2605_d0_b0), List(x2605_d1_b0), List(x2605_d2_b0)), List(b1698), x2727).name("x2728").srcCtx("SGD_minibatch.scala:57:13") } // ParSRAMStore(x2605,List(ArrayBuffer(b1698)),List(x2727),List(x2718))
    antiDepsOf(x2728)=List(x2721)
    val x2754 = withCtrl(x2755) { UnitController(style=StreamPipe, level=OuterControl).name("x2754").srcCtx("SGD_minibatch.scala:60:27") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b2789 = withCtrl(x2754) { StreamOut(field="offset").name("b2789").srcCtx("SGD_minibatch.scala:60:27") } // x2733 = StreamOutNew(BurstCmdBus)
    isAccum(b2789) = false
    bufferDepthOf(b2789) = 1
    val b2790 = withCtrl(x2754) { StreamOut(field="size").name("b2790").srcCtx("SGD_minibatch.scala:60:27") } // x2733 = StreamOutNew(BurstCmdBus)
    isAccum(b2790) = false
    bufferDepthOf(b2790) = 1
    val x2734 = withCtrl(x2754) { StreamOut(field="data").name("x2734").srcCtx("SGD_minibatch.scala:60:27") } // x2734 = StreamOutNew(BurstFullDataBus())
    isAccum(x2734) = false
    bufferDepthOf(x2734) = 1
    val x2735 = withCtrl(x2754) { StreamIn(field="ack").name("x2735").srcCtx("SGD_minibatch.scala:60:27") } // x2735 = StreamInNew(BurstAckBus)
    isAccum(x2735) = false
    bufferDepthOf(x2735) = 1
    val x2743 = withCtrl(x2754) { UnitController(style=SeqPipe, level=InnerControl).name("x2743").srcCtx("SGD_minibatch.scala:60:27") } // UnitPipe(List(Const(true)),Block(x2742))
    val x2736 = withCtrl(x2743) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x2737 = withCtrl(x2743) { OpDef(op=FixSla, inputs=List(x2736, Const(2))).name("x2737").srcCtx("SGD_minibatch.scala:60:27") } // FixLsh(x2736,Const(2))
    val x2738 = withCtrl(x2743) { x2737 } // FixConvert(x2737,TRUE,_64,_0)
    val x2739 = withCtrl(x2743) { DramAddress(x2592).name("x2739").srcCtx("SGD_minibatch.scala:60:27") } // GetDRAMAddress(x2592)
    val x2741_x2740 = withCtrl(x2743) { OpDef(op=FixAdd, inputs=List(x2738, x2739)).name("x2741_x2740").srcCtx("SGD_minibatch.scala:60:27") } // FixAdd(x2738,x2739)
    // x2741 = SimpleStruct(ArrayBuffer((offset,x2740), (size,Const(64)), (isLoad,Const(false))))
    val x2742_b2791_b2789 = withCtrl(x2743) { WriteMem(b2789, x2741_x2740).name("x2742_b2791_b2789").srcCtx("SGD_minibatch.scala:60:27") } // StreamWrite(x2733,x2741,Const(true))
    val x2742_b2792_b2790 = withCtrl(x2743) { WriteMem(b2790, Const(64)).name("x2742_b2792_b2790").srcCtx("SGD_minibatch.scala:60:27") } // StreamWrite(x2733,x2741,Const(true))
    val x2744 = withCtrl(x2754) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2744").srcCtx("SGD_minibatch.scala:60:27") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2745 = withCtrl(x2754) { CounterChain(List(x2744)).name("x2745").srcCtx("SGD_minibatch.scala:60:27") } // CounterChainNew(List(x2744))
    val x2750 = withCtrl(x2754) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2745).name("x2750").srcCtx("SGD_minibatch.scala:60:27") } // UnrolledForeach(List(Const(true)),x2745,Block(Const(())),List(List(b1729)),List(List(b1730)))
    val b1729 = withCtrl(x2750) { CounterIter(x2744, None).name("b1729") } // b1729
    val b1730 = withCtrl(x2750) { Const(true).name("b1730") } // b1730
    val x2746 = withCtrl(x2750) { LoadBanks(List(x2605_d0_b0), List(b1729)).name("x2746").srcCtx("SGD_minibatch.scala:60:27") } // ParSRAMLoad(x2605,List(List(b1729)),List(b1730))
    val x2748_x2747 = withCtrl(x2750) { x2746 } // VectorApply(x2746,0)
    // x2748 = SimpleStruct(ArrayBuffer((_1,x2747), (_2,Const(true))))
    val x2749_x2749_x2734 = withCtrl(x2750) { WriteMem(x2734, x2748_x2747).name("x2749_x2749_x2734").srcCtx("SGD_minibatch.scala:60:27") } // ParStreamWrite(x2734,List(x2748),List(b1730))
    val x2751 = withCtrl(x2754) { FringeDenseStore(dram=List(x2592), cmdStream=List(b2789, b2790), dataStream=List(x2734), ackStream=List(x2735)).name("x2751").srcCtx("SGD_minibatch.scala:60:27") } // FringeDenseStore(x2592,x2733,x2734,x2735)
    val x2753 = withCtrl(x2754) { UnitController(style=SeqPipe, level=InnerControl).name("x2753").srcCtx("SGD_minibatch.scala:60:27") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x2752_x2752 = withCtrl(x2753) { ReadMem(x2735).name("x2752_x2752").srcCtx("SGD_minibatch.scala:60:27") } // StreamRead(x2735,Const(true))
    
  }
}
