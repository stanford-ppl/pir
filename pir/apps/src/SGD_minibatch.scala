import pir._
import pir.node._
import arch._
import prism.enums._

object SGD_minibatch extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x2582 = ArgIn(init=0).name("x2582").ctrl(top).srcCtx("SGD_minibatch.scala:57:18:E") // ArgInNew(Const(0))
    isAccum(x2582) = false
    bufferDepthOf(x2582) = 1
    boundOf(x2582) = 1
    val x2583 = ArgIn(init=0).name("x2583").ctrl(top).srcCtx("SGD_minibatch.scala:58:18:N") // ArgInNew(Const(0))
    isAccum(x2583) = false
    bufferDepthOf(x2583) = 1
    boundOf(x2583) = 1024
    val x2584 = ArgIn(init=0.0).name("x2584").ctrl(top).srcCtx("SGD_minibatch.scala:59:18:A") // ArgInNew(Const(0))
    isAccum(x2584) = false
    bufferDepthOf(x2584) = 1
    boundOf(x2584) = 0
    val x2588 = ReadMem(x2583).name("x2588").ctrl(top).srcCtx("SGD_minibatch.scala:65:22") // RegRead(x2583)
    val x2589 = DRAM().name("x2589").ctrl(top).srcCtx("SGD_minibatch.scala:65:21:x") // x2589 = DRAMNew(ArrayBuffer(x2588, Const(16)),Const(0))
    val x2590 = ReadMem(x2583).name("x2590").ctrl(top).srcCtx("SGD_minibatch.scala:66:22") // RegRead(x2583)
    val x2591 = DRAM().name("x2591").ctrl(top).srcCtx("SGD_minibatch.scala:66:21:y") // x2591 = DRAMNew(ArrayBuffer(x2590),Const(0))
    val x2592 = DRAM().name("x2592").ctrl(top).srcCtx("SGD_minibatch.scala:67:26:result") // x2592 = DRAMNew(ArrayBuffer(Const(16)),Const(0))
    val x2755 = UnitController(style=SeqPipe, level=OuterControl).name("x2755").ctrl(top).srcCtx("SGD_minibatch.scala:72:11") // Hwblock(Block(Const(())),false)
    val x2604_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x2604_d0_b0").ctrl(x2755).srcCtx("SGD_minibatch.scala:73:28:y_tile") // x2604 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2604_d0_b0) = false
    bufferDepthOf(x2604_d0_b0) = 1
    val x2605_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x2605_d0_b0").ctrl(x2755).srcCtx("SGD_minibatch.scala:74:30:sgdmodel") // x2605 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2605_d0_b0) = false
    bufferDepthOf(x2605_d0_b0) = 1
    val x2605_d1_b0 = SRAM(size=16, banking=Strided(banks=1, stride=1)).name("x2605_d1_b0").ctrl(x2755).srcCtx("SGD_minibatch.scala:74:30:sgdmodel") // x2605 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2605_d1_b0) = true
    bufferDepthOf(x2605_d1_b0) = 1
    val x2605_d2_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x2605_d2_b0").ctrl(x2755).srcCtx("SGD_minibatch.scala:74:30:sgdmodel") // x2605 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2605_d2_b0) = false
    bufferDepthOf(x2605_d2_b0) = 1
    val x2606_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2606_d0_b0").ctrl(x2755).srcCtx("SGD_minibatch.scala:75:28:x_tile") // x2606 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x2606_d0_b0) = false
    bufferDepthOf(x2606_d0_b0) = 1
    val x2606_d1_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2606_d1_b0").ctrl(x2755).srcCtx("SGD_minibatch.scala:75:28:x_tile") // x2606 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x2606_d1_b0) = false
    bufferDepthOf(x2606_d1_b0) = 1
    val x2607 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2607").ctrl(x2755).srcCtx("SGD_minibatch.scala:76:14") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2608 = CounterChain(List(x2607)).name("x2608").ctrl(x2755).srcCtx("SGD_minibatch.scala:76:20") // CounterChainNew(List(x2607))
    val x2610 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2608).name("x2610").ctrl(x2755).srcCtx("SGD_minibatch.scala:76:20") // UnrolledForeach(List(Const(true)),x2608,Block(Const(())),List(List(b1570)),List(List(b1571)))
    val b1570 = CounterIter(x2607, None).name("b1570").ctrl(x2610) // b1570
    val b1571 = Const(true).name("b1571").ctrl(x2610) // b1571
    val x2609 = StoreBanks(List(x2605_d0_b0, x2605_d1_b0, x2605_d2_b0), List(b1570), Const(0.0)).name("x2609").ctrl(x2610).srcCtx("SGD_minibatch.scala:76:39") // ParSRAMStore(x2605,List(List(b1570)),List(Const(0)),List(b1571))
    val x2611 = ReadMem(x2582).name("x2611").ctrl(x2755).srcCtx("SGD_minibatch.scala:77:26") // RegRead(x2582)
    val x2612 = Counter(min=Const(0), max=x2611, step=Const(1), par=1).name("x2612").ctrl(x2755).srcCtx("SGD_minibatch.scala:77:28") // CounterNew(Const(0),x2611,Const(1),Const(1))
    val x2613 = CounterChain(List(x2612)).name("x2613").ctrl(x2755).srcCtx("SGD_minibatch.scala:77:34") // CounterChainNew(List(x2612))
    val x2732 = LoopController(style=SeqPipe, level=OuterControl, cchain=x2613).name("x2732").ctrl(x2755).srcCtx("SGD_minibatch.scala:77:34") // UnrolledForeach(List(Const(true)),x2613,Block(Const(())),List(List(b1577)),List(List(b1578)))
    val b1577 = CounterIter(x2612, Some(0)).name("b1577").ctrl(x2732) // b1577
    val b1578 = Const(true).name("b1578").ctrl(x2732) // b1578
    val x2614 = ReadMem(x2583).name("x2614").ctrl(x2732).srcCtx("SGD_minibatch.scala:78:29") // RegRead(x2583)
    val x2615 = Counter(min=Const(0), max=x2614, step=Const(16), par=1).name("x2615").ctrl(x2732).srcCtx("SGD_minibatch.scala:78:31") // CounterNew(Const(0),x2614,Const(16),Const(1))
    val x2616 = CounterChain(List(x2615)).name("x2616").ctrl(x2732).srcCtx("SGD_minibatch.scala:78:38") // CounterChainNew(List(x2615))
    val x2731 = LoopController(style=SeqPipe, level=OuterControl, cchain=x2616).name("x2731").ctrl(x2732).srcCtx("SGD_minibatch.scala:78:38") // UnrolledForeach(List(b1578),x2616,Block(Const(())),List(List(b1582)),List(List(b1583)))
    val b1582 = CounterIter(x2615, Some(0)).name("b1582").ctrl(x2731) // b1582
    val b1583 = Const(true).name("b1583").ctrl(x2731) // b1583
    val x2618 = UnitController(style=SeqPipe, level=InnerControl).name("x2618").ctrl(x2731).srcCtx("SGD_minibatch.scala:78:38") // UnitPipe(List(b1583, b1578),Block(Const(())))
    val x2617 = OpDef(op=FixAdd, inputs=List(b1582, Const(16))).name("x2617").ctrl(x2618).srcCtx("SGD_minibatch.scala:79:29") // FixAdd(b1582,Const(16))
    val x2639 = UnitController(style=StreamPipe, level=OuterControl).name("x2639").ctrl(x2731).srcCtx("SGD_minibatch.scala:79:18") // UnitPipe(List(b1583, b1578),Block(Const(())))
    val b2781 = StreamOut(field="offset").name("b2781").ctrl(x2639).srcCtx("SGD_minibatch.scala:79:18") // x2619 = StreamOutNew(BurstCmdBus)
    isAccum(b2781) = false
    bufferDepthOf(b2781) = 1
    val b2782 = StreamOut(field="size").name("b2782").ctrl(x2639).srcCtx("SGD_minibatch.scala:79:18") // x2619 = StreamOutNew(BurstCmdBus)
    isAccum(b2782) = false
    bufferDepthOf(b2782) = 1
    val x2620 = StreamIn(field="data").name("x2620").ctrl(x2639).srcCtx("SGD_minibatch.scala:79:18") // x2620 = StreamInNew(BurstDataBus())
    isAccum(x2620) = false
    bufferDepthOf(x2620) = 1
    val x2629 = UnitController(style=SeqPipe, level=InnerControl).name("x2629").ctrl(x2639).srcCtx("SGD_minibatch.scala:79:18") // UnitPipe(List(b1583, b1578),Block(x2628))
    val x2621 = b1582 // FixConvert(b1582,TRUE,_32,_0) (Same Type. No op)
    val x2622 = OpDef(op=FixSla, inputs=List(x2621, Const(2))).name("x2622").ctrl(x2629).srcCtx("SGD_minibatch.scala:79:18") // FixLsh(x2621,Const(2))
    val x2623 = x2622 // FixConvert(x2622,TRUE,_64,_0)
    val x2624 = DramAddress(x2591).name("x2624").ctrl(x2629).srcCtx("SGD_minibatch.scala:79:18") // GetDRAMAddress(x2591)
    val x2626_x2625 = OpDef(op=FixAdd, inputs=List(x2623, x2624)).name("x2626_x2625").ctrl(x2629).srcCtx("SGD_minibatch.scala:79:18") // FixAdd(x2623,x2624)
    // x2626 = SimpleStruct(ArrayBuffer((offset,x2625), (size,Const(64)), (isLoad,Const(true))))
    val x2627 = OpDef(op=BitAnd, inputs=List(b1583, b1578)).name("x2627").ctrl(x2629).srcCtx("UnrollingBase.scala:28:66") // And(b1583,b1578)
    val x2628_b2783_b2781 = WriteMem(b2781, x2626_x2625).name("x2628_b2783_b2781").ctrl(x2629).srcCtx("SGD_minibatch.scala:79:18") // StreamWrite(x2619,x2626,x2627)
    val x2628_b2784_b2782 = WriteMem(b2782, Const(64)).name("x2628_b2784_b2782").ctrl(x2629).srcCtx("SGD_minibatch.scala:79:18") // StreamWrite(x2619,x2626,x2627)
    val x2630 = FringeDenseLoad(dram=List(x2591), cmdStream=List(b2781, b2782), dataStream=List(x2620)).name("x2630").ctrl(x2639).srcCtx("SGD_minibatch.scala:79:18") // FringeDenseLoad(x2591,x2619,x2620)
    val x2631 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2631").ctrl(x2639).srcCtx("SGD_minibatch.scala:79:18") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2632 = CounterChain(List(x2631)).name("x2632").ctrl(x2639).srcCtx("SGD_minibatch.scala:79:18") // CounterChainNew(List(x2631))
    val x2638 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2632).name("x2638").ctrl(x2639).srcCtx("SGD_minibatch.scala:79:18") // UnrolledForeach(List(b1583, b1578),x2632,Block(Const(())),List(List(b1600)),List(List(b1601)))
    val b1600 = CounterIter(x2631, None).name("b1600").ctrl(x2638) // b1600
    val b1601 = Const(true).name("b1601").ctrl(x2638) // b1601
    val x2633 = OpDef(op=BitAnd, inputs=List(b1601, b1583)).name("x2633").ctrl(x2638).srcCtx("UnrollingBase.scala:28:66") // And(b1601,b1583)
    val x2634 = OpDef(op=BitAnd, inputs=List(x2633, b1578)).name("x2634").ctrl(x2638).srcCtx("UnrollingBase.scala:28:66") // And(x2633,b1578)
    val x2635_x2635 = ReadMem(x2620).name("x2635_x2635").ctrl(x2638).srcCtx("SGD_minibatch.scala:79:18") // ParStreamRead(x2620,List(x2634))
    val x2636_x2636 = x2635_x2635 // x2636 = VectorApply(x2635,0)
    val x2637 = StoreBanks(List(x2604_d0_b0), List(b1600), x2636_x2636).name("x2637").ctrl(x2638).srcCtx("SGD_minibatch.scala:79:18") // ParSRAMStore(x2604,List(List(b1600)),List(x2636),List(x2634))
    val x2640 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2640").ctrl(x2731).srcCtx("SGD_minibatch.scala:80:18") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2641 = CounterChain(List(x2640)).name("x2641").ctrl(x2731).srcCtx("SGD_minibatch.scala:80:18") // CounterChainNew(List(x2640))
    val x2668 = LoopController(style=StreamPipe, level=OuterControl, cchain=x2641).name("x2668").ctrl(x2731).srcCtx("SGD_minibatch.scala:80:18") // UnrolledForeach(List(b1583, b1578),x2641,Block(Const(())),List(List(b1611)),List(List(b1612)))
    val b1611 = CounterIter(x2640, Some(0)).name("b1611").ctrl(x2668) // b1611
    val b1612 = Const(true).name("b1612").ctrl(x2668) // b1612
    val b2785 = StreamOut(field="offset").name("b2785").ctrl(x2668).srcCtx("SGD_minibatch.scala:80:18") // x2642 = StreamOutNew(BurstCmdBus)
    isAccum(b2785) = false
    bufferDepthOf(b2785) = 1
    val b2786 = StreamOut(field="size").name("b2786").ctrl(x2668).srcCtx("SGD_minibatch.scala:80:18") // x2642 = StreamOutNew(BurstCmdBus)
    isAccum(b2786) = false
    bufferDepthOf(b2786) = 1
    val x2643 = StreamIn(field="data").name("x2643").ctrl(x2668).srcCtx("SGD_minibatch.scala:80:18") // x2643 = StreamInNew(BurstDataBus())
    isAccum(x2643) = false
    bufferDepthOf(x2643) = 1
    val x2657 = UnitController(style=SeqPipe, level=InnerControl).name("x2657").ctrl(x2668).srcCtx("SGD_minibatch.scala:80:18") // UnitPipe(List(b1612, b1583, b1578),Block(x2656))
    val x2644 = OpDef(op=FixAdd, inputs=List(b1582, b1611)).name("x2644").ctrl(x2657).srcCtx("SGD_minibatch.scala:80:18") // FixAdd(b1582,b1611)
    val x2645 = x2644 // FixConvert(x2644,TRUE,_32,_0) (Same Type. No op)
    val x2646 = OpDef(op=FixSla, inputs=List(x2645, Const(4))).name("x2646").ctrl(x2657).srcCtx("SGD_minibatch.scala:80:18") // FixLsh(x2645,Const(4))
    val x2647 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x2648 = OpDef(op=FixAdd, inputs=List(x2646, x2647)).name("x2648").ctrl(x2657).srcCtx("SGD_minibatch.scala:80:18") // FixAdd(x2646,x2647)
    val x2649 = OpDef(op=FixSla, inputs=List(x2648, Const(2))).name("x2649").ctrl(x2657).srcCtx("SGD_minibatch.scala:80:18") // FixLsh(x2648,Const(2))
    val x2650 = x2649 // FixConvert(x2649,TRUE,_64,_0)
    val x2651 = DramAddress(x2589).name("x2651").ctrl(x2657).srcCtx("SGD_minibatch.scala:80:18") // GetDRAMAddress(x2589)
    val x2653_x2652 = OpDef(op=FixAdd, inputs=List(x2650, x2651)).name("x2653_x2652").ctrl(x2657).srcCtx("SGD_minibatch.scala:80:18") // FixAdd(x2650,x2651)
    // x2653 = SimpleStruct(ArrayBuffer((offset,x2652), (size,Const(64)), (isLoad,Const(true))))
    val x2654 = OpDef(op=BitAnd, inputs=List(b1612, b1583)).name("x2654").ctrl(x2657).srcCtx("UnrollingBase.scala:28:66") // And(b1612,b1583)
    val x2655 = OpDef(op=BitAnd, inputs=List(x2654, b1578)).name("x2655").ctrl(x2657).srcCtx("UnrollingBase.scala:28:66") // And(x2654,b1578)
    val x2656_b2787_b2785 = WriteMem(b2785, x2653_x2652).name("x2656_b2787_b2785").ctrl(x2657).srcCtx("SGD_minibatch.scala:80:18") // StreamWrite(x2642,x2653,x2655)
    val x2656_b2788_b2786 = WriteMem(b2786, Const(64)).name("x2656_b2788_b2786").ctrl(x2657).srcCtx("SGD_minibatch.scala:80:18") // StreamWrite(x2642,x2653,x2655)
    val x2658 = FringeDenseLoad(dram=List(x2589), cmdStream=List(b2785, b2786), dataStream=List(x2643)).name("x2658").ctrl(x2668).srcCtx("SGD_minibatch.scala:80:18") // FringeDenseLoad(x2589,x2642,x2643)
    val x2659 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2659").ctrl(x2668).srcCtx("SGD_minibatch.scala:80:18") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2660 = CounterChain(List(x2659)).name("x2660").ctrl(x2668).srcCtx("SGD_minibatch.scala:80:18") // CounterChainNew(List(x2659))
    val x2667 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2660).name("x2667").ctrl(x2668).srcCtx("SGD_minibatch.scala:80:18") // UnrolledForeach(List(b1612, b1583, b1578),x2660,Block(Const(())),List(List(b1632)),List(List(b1633)))
    val b1632 = CounterIter(x2659, None).name("b1632").ctrl(x2667) // b1632
    val b1633 = Const(true).name("b1633").ctrl(x2667) // b1633
    val x2661 = OpDef(op=BitAnd, inputs=List(b1633, b1612)).name("x2661").ctrl(x2667).srcCtx("UnrollingBase.scala:28:66") // And(b1633,b1612)
    val x2662 = OpDef(op=BitAnd, inputs=List(b1583, b1578)).name("x2662").ctrl(x2667).srcCtx("UnrollingBase.scala:28:66") // And(b1583,b1578)
    val x2663 = OpDef(op=BitAnd, inputs=List(x2661, x2662)).name("x2663").ctrl(x2667).srcCtx("UnrollingBase.scala:28:66") // And(x2661,x2662)
    val x2664_x2664 = ReadMem(x2643).name("x2664_x2664").ctrl(x2667).srcCtx("SGD_minibatch.scala:80:18") // ParStreamRead(x2643,List(x2663))
    val x2665_x2665 = x2664_x2664 // x2665 = VectorApply(x2664,0)
    val x2666 = StoreBanks(List(x2606_d0_b0, x2606_d1_b0), List(b1611, b1632), x2665_x2665).name("x2666").ctrl(x2667).srcCtx("SGD_minibatch.scala:80:18") // ParSRAMStore(x2606,List(List(b1611, b1632)),List(x2665),List(x2663))
    val x2669_d0_b0 = SRAM(size=16, banking=Strided(banks=1, stride=1)).name("x2669_d0_b0").ctrl(x2731).srcCtx("SGD_minibatch.scala:81:31:y_err") // x2669 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2669_d0_b0) = false
    bufferDepthOf(x2669_d0_b0) = 1
    val x2670 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2670").ctrl(x2731).srcCtx("SGD_minibatch.scala:82:27") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2671 = CounterChain(List(x2670)).name("x2671").ctrl(x2731).srcCtx("SGD_minibatch.scala:82:36") // CounterChainNew(List(x2670))
    val x2698 = LoopController(style=MetaPipe, level=OuterControl, cchain=x2671).name("x2698").ctrl(x2731).srcCtx("SGD_minibatch.scala:82:36") // UnrolledForeach(List(b1583, b1578),x2671,Block(Const(())),List(List(b1645)),List(List(b1646)))
    val b1645 = CounterIter(x2670, Some(0)).name("b1645").ctrl(x2698) // b1645
    val b1646 = Const(true).name("b1646").ctrl(x2698) // b1646
    val x2672_d0 = Reg(init=Some(0.0)).name("x2672_d0").ctrl(x2698).srcCtx("SGD_minibatch.scala:83:35:y_hat") // x2672 = RegNew(Const(0))
    isAccum(x2672_d0) = false
    bufferDepthOf(x2672_d0) = 2
    val x2672_d1 = Reg(init=Some(0.0)).name("x2672_d1").ctrl(x2698).srcCtx("SGD_minibatch.scala:83:35:y_hat") // x2672 = RegNew(Const(0))
    isAccum(x2672_d1) = true
    bufferDepthOf(x2672_d1) = 1
    val x2673 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2673").ctrl(x2698).srcCtx("SGD_minibatch.scala:83:48") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2674 = CounterChain(List(x2673)).name("x2674").ctrl(x2698).srcCtx("SGD_minibatch.scala:83:96") // CounterChainNew(List(x2673))
    val x2690 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2674).name("x2690").ctrl(x2698).srcCtx("SGD_minibatch.scala:83:96") // UnrolledReduce(List(b1646, b1583, b1578),x2674,x2672,Block((x2672) => Const(())),List(List(b1650)),List(List(b1651)))
    val b1650 = CounterIter(x2673, None).name("b1650").ctrl(x2690) // b1650
    val b1651 = Const(true).name("b1651").ctrl(x2690) // b1651
    val x2675 = OpDef(op=BitAnd, inputs=List(b1651, b1646)).name("x2675").ctrl(x2690).srcCtx("UnrollingBase.scala:28:66") // And(b1651,b1646)
    val x2676 = OpDef(op=BitAnd, inputs=List(b1583, b1578)).name("x2676").ctrl(x2690).srcCtx("UnrollingBase.scala:28:66") // And(b1583,b1578)
    val x2677 = OpDef(op=BitAnd, inputs=List(x2675, x2676)).name("x2677").ctrl(x2690).srcCtx("UnrollingBase.scala:28:66") // And(x2675,x2676)
    val x2678 = LoadBanks(List(x2606_d1_b0), List(b1645, b1650)).name("x2678").ctrl(x2690).srcCtx("SGD_minibatch.scala:83:68") // ParSRAMLoad(x2606,List(List(b1645, b1650)),List(x2677))
    val x2679 = x2678 // x2679 = VectorApply(x2678,0)
    val x2680 = LoadBanks(List(x2605_d2_b0), List(b1650)).name("x2680").ctrl(x2690).srcCtx("SGD_minibatch.scala:83:84") // ParSRAMLoad(x2605,List(List(b1650)),List(x2677))
    val x2681 = x2680 // x2681 = VectorApply(x2680,0)
    val x2682 = x2681 // FixConvert(x2681,TRUE,_16,_16) (Same Type. No op)
    val x2683 = OpDef(op=FixMul, inputs=List(x2679, x2682)).name("x2683").ctrl(x2690).srcCtx("SGD_minibatch.scala:83:74") // FixMul(x2679,x2682)
    val x2684 = ReadMem(x2672_d1).name("x2684").ctrl(x2690).srcCtx("SGD_minibatch.scala:83:96") // RegRead(x2672)
    val x2685 = OpDef(op=FixEql, inputs=List(b1650, Const(0))).name("x2685").ctrl(x2690).srcCtx("SGD_minibatch.scala:83:96") // FixEql(b1650,Const(0))
    val x2686 = ReduceAccumOp(op=FixAdd, input=x2683, accum=x2684).name("x2686").ctrl(x2690).srcCtx("SGD_minibatch.scala:83:98") // FixAdd(x2683,x2684)
    val x2687 = OpDef(op=BitAnd, inputs=List(b1646, b1583)).name("x2687").ctrl(x2690).srcCtx("UnrollingBase.scala:28:66") // And(b1646,b1583)
    val x2688 = OpDef(op=BitAnd, inputs=List(x2687, b1578)).name("x2688").ctrl(x2690).srcCtx("UnrollingBase.scala:28:66") // And(x2687,b1578)
    val x2689_x2672_d0 = WriteMem(x2672_d0, x2686).name("x2689_x2672_d0").ctrl(x2690).srcCtx("SGD_minibatch.scala:83:96") // RegWrite(x2672,x2686,x2688)
    val x2689_x2672_d1 = WriteMem(x2672_d1, x2686).name("x2689_x2672_d1").ctrl(x2690).srcCtx("SGD_minibatch.scala:83:96") // RegWrite(x2672,x2686,x2688)
    val x2697 = UnitController(style=SeqPipe, level=InnerControl).name("x2697").ctrl(x2698).srcCtx("SGD_minibatch.scala:82:36") // UnitPipe(List(b1646, b1583, b1578),Block(Const(())))
    val x2691 = OpDef(op=BitAnd, inputs=List(b1646, b1583)).name("x2691").ctrl(x2697).srcCtx("UnrollingBase.scala:28:66") // And(b1646,b1583)
    val x2692 = OpDef(op=BitAnd, inputs=List(x2691, b1578)).name("x2692").ctrl(x2697).srcCtx("UnrollingBase.scala:28:66") // And(x2691,b1578)
    val x2693 = LoadBanks(List(x2604_d0_b0), List(b1645)).name("x2693").ctrl(x2697).srcCtx("SGD_minibatch.scala:84:30") // SRAMLoad(x2604,ArrayBuffer(Const(16)),List(b1645),Const(0),x2692)
    val x2694 = ReadMem(x2672_d0).name("x2694").ctrl(x2697).srcCtx("SGD_minibatch.scala:84:42") // RegRead(x2672)
    val x2695 = OpDef(op=FixSub, inputs=List(x2693, x2694)).name("x2695").ctrl(x2697).srcCtx("SGD_minibatch.scala:84:34") // FixSub(x2693,x2694)
    val x2696 = StoreBanks(List(x2669_d0_b0), List(b1645), x2695).name("x2696").ctrl(x2697).srcCtx("SGD_minibatch.scala:84:22") // SRAMStore(x2669,ArrayBuffer(Const(16)),List(b1645),Const(0),x2695,x2692)
    val x2699 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2699").ctrl(x2731).srcCtx("SGD_minibatch.scala:86:37") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2700 = CounterChain(List(x2699)).name("x2700").ctrl(x2731).srcCtx("SGD_minibatch.scala:90:13") // CounterChainNew(List(x2699))
    val x2730 = LoopController(style=MetaPipe, level=OuterControl, cchain=x2700).name("x2730").ctrl(x2731).srcCtx("SGD_minibatch.scala:90:13") // UnrolledReduce(List(b1583, b1578),x2700,x2605,Block((x2605) => Const(())),List(List(b1680)),List(List(b1681)))
    val b1680 = CounterIter(x2699, Some(0)).name("b1680").ctrl(x2730) // b1680
    val b1681 = Const(true).name("b1681").ctrl(x2730) // b1681
    val x2701_d0_b0 = SRAM(size=1, banking=Strided(banks=16, stride=1)).name("x2701_d0_b0").ctrl(x2730).srcCtx("SGD_minibatch.scala:87:31:row") // x2701 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2701_d0_b0) = false
    bufferDepthOf(x2701_d0_b0) = 2
    val x2702 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2702").ctrl(x2730).srcCtx("SGD_minibatch.scala:88:28") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2703 = CounterChain(List(x2702)).name("x2703").ctrl(x2730).srcCtx("SGD_minibatch.scala:88:36") // CounterChainNew(List(x2702))
    val x2714 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2703).name("x2714").ctrl(x2730).srcCtx("SGD_minibatch.scala:88:36") // UnrolledForeach(List(b1681, b1583, b1578),x2703,Block(Const(())),List(List(b1685)),List(List(b1686)))
    val b1685 = CounterIter(x2702, None).name("b1685").ctrl(x2714) // b1685
    val b1686 = Const(true).name("b1686").ctrl(x2714) // b1686
    val x2704 = OpDef(op=BitAnd, inputs=List(b1686, b1681)).name("x2704").ctrl(x2714).srcCtx("UnrollingBase.scala:28:66") // And(b1686,b1681)
    val x2705 = OpDef(op=BitAnd, inputs=List(b1583, b1578)).name("x2705").ctrl(x2714).srcCtx("UnrollingBase.scala:28:66") // And(b1583,b1578)
    val x2706 = OpDef(op=BitAnd, inputs=List(x2704, x2705)).name("x2706").ctrl(x2714).srcCtx("UnrollingBase.scala:28:66") // And(x2704,x2705)
    val x2707 = LoadBanks(List(x2606_d0_b0), List(b1680, b1685)).name("x2707").ctrl(x2714).srcCtx("SGD_minibatch.scala:88:58") // ParSRAMLoad(x2606,List(List(b1680, b1685)),List(x2706))
    val x2708 = x2707 // x2708 = VectorApply(x2707,0)
    val x2709 = LoadBanks(List(x2669_d0_b0), List(b1680)).name("x2709").ctrl(x2714).srcCtx("SGD_minibatch.scala:88:71") // SRAMLoad(x2669,ArrayBuffer(Const(16)),List(b1680),Const(0),x2706)
    val x2710 = OpDef(op=FixMul, inputs=List(x2708, x2709)).name("x2710").ctrl(x2714).srcCtx("SGD_minibatch.scala:88:64") // FixMul(x2708,x2709)
    val x2711 = ReadMem(x2584).name("x2711").ctrl(x2714).srcCtx("SGD_minibatch.scala:88:77") // RegRead(x2584)
    val x2712 = OpDef(op=FixMul, inputs=List(x2710, x2711)).name("x2712").ctrl(x2714).srcCtx("SGD_minibatch.scala:88:75") // FixMul(x2710,x2711)
    val x2713 = StoreBanks(List(x2701_d0_b0), List(b1685), x2712).name("x2713").ctrl(x2714).srcCtx("SGD_minibatch.scala:88:50") // ParSRAMStore(x2701,List(List(b1685)),List(x2712),List(x2706))
    val x2715 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2715").ctrl(x2730).srcCtx("SGD_minibatch.scala:90:13") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2716 = CounterChain(List(x2715)).name("x2716").ctrl(x2730).srcCtx("SGD_minibatch.scala:90:13") // CounterChainNew(ArrayBuffer(x2715))
    val x2729 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2716).name("x2729").ctrl(x2730).srcCtx("SGD_minibatch.scala:90:13") // UnrolledForeach(List(),x2716,Block(Const(())),ArrayBuffer(List(b1698)),ArrayBuffer(List(b1699)))
    val b1698 = CounterIter(x2715, None).name("b1698").ctrl(x2729) // b1698
    val b1699 = Const(true).name("b1699").ctrl(x2729) // b1699
    val x2717 = OpDef(op=BitAnd, inputs=List(b1699, b1583)).name("x2717").ctrl(x2729).srcCtx("UnrollingBase.scala:28:66") // And(b1699,b1583)
    val x2718 = OpDef(op=BitAnd, inputs=List(x2717, b1578)).name("x2718").ctrl(x2729).srcCtx("UnrollingBase.scala:28:66") // And(x2717,b1578)
    val x2719 = LoadBanks(List(x2701_d0_b0), List(b1698)).name("x2719").ctrl(x2729).srcCtx("SGD_minibatch.scala:90:13") // ParSRAMLoad(x2701,List(ArrayBuffer(b1698)),List(x2718))
    val x2720 = x2719 // x2720 = VectorApply(x2719,0)
    val x2721 = LoadBanks(List(x2605_d1_b0), List(b1698)).name("x2721").ctrl(x2729).srcCtx("SGD_minibatch.scala:90:13") // ParSRAMLoad(x2605,List(ArrayBuffer(b1698)),List(x2718))
    val x2722 = x2721 // x2722 = VectorApply(x2721,0)
    val x2723 = OpDef(op=BitAnd, inputs=List(b1681, b1583)).name("x2723").ctrl(x2729).srcCtx("SGD_minibatch.scala:90:13") // And(b1681,b1583)
    val x2724 = OpDef(op=BitAnd, inputs=List(x2723, b1578)).name("x2724").ctrl(x2729).srcCtx("SGD_minibatch.scala:90:13") // And(x2723,b1578)
    val x2725 = OpDef(op=BitAnd, inputs=List(x2724, x2718)).name("x2725").ctrl(x2729).srcCtx("SGD_minibatch.scala:90:13") // And(x2724,x2718)
    val x2726 = OpDef(op=FixEql, inputs=List(b1680, Const(0))).name("x2726").ctrl(x2729).srcCtx("SGD_minibatch.scala:90:13") // FixEql(b1680,Const(0))
    val x2727 = ReduceAccumOp(op=FixAdd, input=x2720, accum=x2722).name("x2727").ctrl(x2729).srcCtx("SGD_minibatch.scala:90:17") // FixAdd(x2720,x2722)
    val x2728 = StoreBanks(List(x2605_d0_b0, x2605_d1_b0, x2605_d2_b0), List(b1698), x2727).name("x2728").ctrl(x2729).srcCtx("SGD_minibatch.scala:90:13") // ParSRAMStore(x2605,List(ArrayBuffer(b1698)),List(x2727),List(x2718))
    val x2754 = UnitController(style=StreamPipe, level=OuterControl).name("x2754").ctrl(x2755).srcCtx("SGD_minibatch.scala:93:27") // UnitPipe(List(Const(true)),Block(Const(())))
    val b2789 = StreamOut(field="offset").name("b2789").ctrl(x2754).srcCtx("SGD_minibatch.scala:93:27") // x2733 = StreamOutNew(BurstCmdBus)
    isAccum(b2789) = false
    bufferDepthOf(b2789) = 1
    val b2790 = StreamOut(field="size").name("b2790").ctrl(x2754).srcCtx("SGD_minibatch.scala:93:27") // x2733 = StreamOutNew(BurstCmdBus)
    isAccum(b2790) = false
    bufferDepthOf(b2790) = 1
    val x2734 = StreamOut(field="data").name("x2734").ctrl(x2754).srcCtx("SGD_minibatch.scala:93:27") // x2734 = StreamOutNew(BurstFullDataBus())
    isAccum(x2734) = false
    bufferDepthOf(x2734) = 1
    val x2735 = StreamIn(field="ack").name("x2735").ctrl(x2754).srcCtx("SGD_minibatch.scala:93:27") // x2735 = StreamInNew(BurstAckBus)
    isAccum(x2735) = false
    bufferDepthOf(x2735) = 1
    val x2743 = UnitController(style=SeqPipe, level=InnerControl).name("x2743").ctrl(x2754).srcCtx("SGD_minibatch.scala:93:27") // UnitPipe(List(Const(true)),Block(x2742))
    val x2736 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x2737 = OpDef(op=FixSla, inputs=List(x2736, Const(2))).name("x2737").ctrl(x2743).srcCtx("SGD_minibatch.scala:93:27") // FixLsh(x2736,Const(2))
    val x2738 = x2737 // FixConvert(x2737,TRUE,_64,_0)
    val x2739 = DramAddress(x2592).name("x2739").ctrl(x2743).srcCtx("SGD_minibatch.scala:93:27") // GetDRAMAddress(x2592)
    val x2741_x2740 = OpDef(op=FixAdd, inputs=List(x2738, x2739)).name("x2741_x2740").ctrl(x2743).srcCtx("SGD_minibatch.scala:93:27") // FixAdd(x2738,x2739)
    // x2741 = SimpleStruct(ArrayBuffer((offset,x2740), (size,Const(64)), (isLoad,Const(false))))
    val x2742_b2791_b2789 = WriteMem(b2789, x2741_x2740).name("x2742_b2791_b2789").ctrl(x2743).srcCtx("SGD_minibatch.scala:93:27") // StreamWrite(x2733,x2741,Const(true))
    val x2742_b2792_b2790 = WriteMem(b2790, Const(64)).name("x2742_b2792_b2790").ctrl(x2743).srcCtx("SGD_minibatch.scala:93:27") // StreamWrite(x2733,x2741,Const(true))
    val x2744 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2744").ctrl(x2754).srcCtx("SGD_minibatch.scala:93:27") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2745 = CounterChain(List(x2744)).name("x2745").ctrl(x2754).srcCtx("SGD_minibatch.scala:93:27") // CounterChainNew(List(x2744))
    val x2750 = LoopController(style=InnerPipe, level=InnerControl, cchain=x2745).name("x2750").ctrl(x2754).srcCtx("SGD_minibatch.scala:93:27") // UnrolledForeach(List(Const(true)),x2745,Block(Const(())),List(List(b1729)),List(List(b1730)))
    val b1729 = CounterIter(x2744, None).name("b1729").ctrl(x2750) // b1729
    val b1730 = Const(true).name("b1730").ctrl(x2750) // b1730
    val x2746 = LoadBanks(List(x2605_d0_b0), List(b1729)).name("x2746").ctrl(x2750).srcCtx("SGD_minibatch.scala:93:27") // ParSRAMLoad(x2605,List(List(b1729)),List(b1730))
    val x2748_x2747 = x2746 // x2747 = VectorApply(x2746,0)
    // x2748 = SimpleStruct(ArrayBuffer((_1,x2747), (_2,Const(true))))
    val x2749_x2749_x2734 = WriteMem(x2734, x2748_x2747).name("x2749_x2749_x2734").ctrl(x2750).srcCtx("SGD_minibatch.scala:93:27") // ParStreamWrite(x2734,List(x2748),List(b1730))
    val x2751 = FringeDenseStore(dram=List(x2592), cmdStream=List(b2789, b2790), dataStream=List(x2734), ackStream=List(x2735)).name("x2751").ctrl(x2754).srcCtx("SGD_minibatch.scala:93:27") // FringeDenseStore(x2592,x2733,x2734,x2735)
    val x2753 = UnitController(style=SeqPipe, level=InnerControl).name("x2753").ctrl(x2754).srcCtx("SGD_minibatch.scala:93:27") // UnitPipe(List(Const(true)),Block(Const(())))
    val x2752_x2752 = ReadMem(x2735).name("x2752_x2752").ctrl(x2753).srcCtx("SGD_minibatch.scala:93:27") // StreamRead(x2735,Const(true))
    
  }
}
