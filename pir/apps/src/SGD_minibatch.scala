import pir._
import pir.node._
import arch._
import prism.enums._

object SGD_minibatch extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x2550 = withCtrl(design.top.topController) { ArgIn(init=0).name("x2550").srcCtx("SGD_minibatch.scala:23:18:E") } // ArgInNew(Const(0))
    isAccum(x2550) = false
    bufferDepthOf(x2550) = 1
    boundOf(x2550) = 1
    val x2551 = withCtrl(design.top.topController) { ArgIn(init=0).name("x2551").srcCtx("SGD_minibatch.scala:24:18:N") } // ArgInNew(Const(0))
    isAccum(x2551) = false
    bufferDepthOf(x2551) = 1
    boundOf(x2551) = 1024
    val x2552 = withCtrl(design.top.topController) { ArgIn(init=0.0).name("x2552").srcCtx("SGD_minibatch.scala:25:18:A") } // ArgInNew(Const(0))
    isAccum(x2552) = false
    bufferDepthOf(x2552) = 1
    boundOf(x2552) = 0
    val x2556 = withCtrl(design.top.topController) { ReadMem(x2551).name("x2556").srcCtx("SGD_minibatch.scala:31:22") } // RegRead(x2551)
    val x2557 = withCtrl(design.top.topController) { DRAM(dims=List(x2556, Const(16))).name("x2557").srcCtx("SGD_minibatch.scala:31:21:x") } // x2557 = DRAMNew(ArrayBuffer(x2556, Const(16)),Const(0))
    val x2558 = withCtrl(design.top.topController) { ReadMem(x2551).name("x2558").srcCtx("SGD_minibatch.scala:32:22") } // RegRead(x2551)
    val x2559 = withCtrl(design.top.topController) { DRAM(dims=List(x2558)).name("x2559").srcCtx("SGD_minibatch.scala:32:21:y") } // x2559 = DRAMNew(ArrayBuffer(x2558),Const(0))
    val x2560 = withCtrl(design.top.topController) { DRAM(dims=List(Const(16))).name("x2560").srcCtx("SGD_minibatch.scala:33:26:result") } // x2560 = DRAMNew(ArrayBuffer(Const(16)),Const(0))
    val x2721 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x2721").srcCtx("SGD_minibatch.scala:38:11") } // Hwblock(Block(Const(())),false)
    val x2572_d0_b0 = withCtrl(x2721) { SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2572_d0_b0").srcCtx("SGD_minibatch.scala:39:28:y_tile") } // x2572 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2572_d0_b0) = false
    bufferDepthOf(x2572_d0_b0) = 1
    staticDimsOf(x2572_d0_b0) = List(16)
    val x2573_d0_b0 = withCtrl(x2721) { SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2573_d0_b0").srcCtx("SGD_minibatch.scala:40:30:sgdmodel") } // x2573 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2573_d0_b0) = false
    bufferDepthOf(x2573_d0_b0) = 1
    staticDimsOf(x2573_d0_b0) = List(16)
    val x2573_d1_b0 = withCtrl(x2721) { SRAM(size=16, banking=Strided(banks=1, stride=1)).name("x2573_d1_b0").srcCtx("SGD_minibatch.scala:40:30:sgdmodel") } // x2573 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2573_d1_b0) = true
    bufferDepthOf(x2573_d1_b0) = 1
    staticDimsOf(x2573_d1_b0) = List(16)
    val x2573_d2_b0 = withCtrl(x2721) { SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2573_d2_b0").srcCtx("SGD_minibatch.scala:40:30:sgdmodel") } // x2573 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2573_d2_b0) = false
    bufferDepthOf(x2573_d2_b0) = 1
    staticDimsOf(x2573_d2_b0) = List(16)
    val x2574_d0_b0 = withCtrl(x2721) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x2574_d0_b0").srcCtx("SGD_minibatch.scala:41:28:x_tile") } // x2574 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x2574_d0_b0) = false
    bufferDepthOf(x2574_d0_b0) = 1
    staticDimsOf(x2574_d0_b0) = List(16, 16)
    val x2574_d1_b0 = withCtrl(x2721) { SRAM(size=256, banking=Strided(banks=16, stride=1)).name("x2574_d1_b0").srcCtx("SGD_minibatch.scala:41:28:x_tile") } // x2574 = SRAMNew(ArrayBuffer(Const(16), Const(16)))
    isAccum(x2574_d1_b0) = false
    bufferDepthOf(x2574_d1_b0) = 1
    staticDimsOf(x2574_d1_b0) = List(16, 16)
    val x2575 = withCtrl(x2721) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2575").srcCtx("SGD_minibatch.scala:42:14") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2576 = withCtrl(x2721) { CounterChain(List(x2575)).name("x2576").srcCtx("SGD_minibatch.scala:42:20") } // CounterChainNew(List(x2575))
    val x2578 = withCtrl(x2721) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2576).name("x2578").srcCtx("SGD_minibatch.scala:42:20") } // UnrolledForeach(List(Const(true)),x2576,Block(Const(())),List(List(b1548)),List(List(b1549)))
    val b1548 = withCtrl(x2578) { CounterIter(x2575, None).name("b1548") } // b1548
    val b1549 = withCtrl(x2578) { Const(true).name("b1549") } // b1549
    val x2577 = withCtrl(x2578) { StoreBanks(List(List(x2573_d0_b0), List(x2573_d1_b0), List(x2573_d2_b0)), List(b1548), Const(0.0)).name("x2577").srcCtx("SGD_minibatch.scala:42:39") } // ParSRAMStore(x2573,List(List(b1548)),List(Const(0)),List(b1549))
    val x2579 = withCtrl(x2721) { ReadMem(x2551).name("x2579").srcCtx("SGD_minibatch.scala:43:34") } // RegRead(x2551)
    val x2580 = withCtrl(x2721) { Counter(min=Const(0), max=x2579, step=Const(16), par=1).name("x2580").srcCtx("SGD_minibatch.scala:43:36") } // CounterNew(Const(0),x2579,Const(16),Const(1))
    val x2581 = withCtrl(x2721) { ReadMem(x2550).name("x2581").srcCtx("SGD_minibatch.scala:43:26") } // RegRead(x2550)
    val x2582 = withCtrl(x2721) { Counter(min=Const(0), max=x2581, step=Const(1), par=1).name("x2582").srcCtx("SGD_minibatch.scala:43:28") } // CounterNew(Const(0),x2581,Const(1),Const(1))
    val x2583 = withCtrl(x2721) { CounterChain(List(x2582,x2580)).name("x2583").srcCtx("SGD_minibatch.scala:43:43") } // CounterChainNew(List(x2582, x2580))
    val x2698 = withCtrl(x2721) { LoopController(style=SeqPipe, level=OuterControl, cchain=x2583).name("x2698").srcCtx("SGD_minibatch.scala:43:43") } // UnrolledForeach(List(Const(true)),x2583,Block(Const(())),List(List(b1557), List(b1558)),List(List(b1559), List(b1560)))
    val b1557 = withCtrl(x2698) { CounterIter(x2582, Some(0)).name("b1557") } // b1557
    val b1559 = withCtrl(x2698) { Const(true).name("b1559") } // b1559
    val b1558 = withCtrl(x2698) { CounterIter(x2580, Some(0)).name("b1558") } // b1558
    val b1560 = withCtrl(x2698) { Const(true).name("b1560") } // b1560
    val x2585 = withCtrl(x2698) { UnitController(style=SeqPipe, level=InnerControl).name("x2585").srcCtx("SGD_minibatch.scala:43:43") } // UnitPipe(List(b1559, b1560),Block(Const(())))
    val x2584 = withCtrl(x2585) { OpDef(op=FixAdd, inputs=List(b1558, Const(16))).name("x2584").srcCtx("SGD_minibatch.scala:44:27") } // FixAdd(b1558,Const(16))
    val x2606 = withCtrl(x2698) { UnitController(style=StreamPipe, level=OuterControl).name("x2606").srcCtx("SGD_minibatch.scala:44:16") } // UnitPipe(List(b1559, b1560),Block(Const(())))
    val b2747 = withCtrl(x2606) { StreamOut(field="offset").name("b2747").srcCtx("SGD_minibatch.scala:44:16") } // x2586 = StreamOutNew(BurstCmdBus)
    isAccum(b2747) = false
    bufferDepthOf(b2747) = 1
    val b2748 = withCtrl(x2606) { StreamOut(field="size").name("b2748").srcCtx("SGD_minibatch.scala:44:16") } // x2586 = StreamOutNew(BurstCmdBus)
    isAccum(b2748) = false
    bufferDepthOf(b2748) = 1
    val x2587 = withCtrl(x2606) { StreamIn(field="data").name("x2587").srcCtx("SGD_minibatch.scala:44:16") } // x2587 = StreamInNew(BurstDataBus())
    isAccum(x2587) = false
    bufferDepthOf(x2587) = 1
    val x2596 = withCtrl(x2606) { UnitController(style=SeqPipe, level=InnerControl).name("x2596").srcCtx("SGD_minibatch.scala:44:16") } // UnitPipe(List(b1559, b1560),Block(x2595))
    val x2588 = withCtrl(x2596) { b1558 } // FixConvert(b1558,TRUE,_32,_0) (Same Type. No op)
    val x2589 = withCtrl(x2596) { OpDef(op=FixSla, inputs=List(x2588, Const(2))).name("x2589").srcCtx("SGD_minibatch.scala:44:16") } // FixLsh(x2588,Const(2))
    val x2590 = withCtrl(x2596) { x2589 } // FixConvert(x2589,TRUE,_64,_0)
    val x2591 = withCtrl(x2596) { DramAddress(x2559).name("x2591").srcCtx("SGD_minibatch.scala:44:16") } // GetDRAMAddress(x2559)
    val x2593_x2592 = withCtrl(x2596) { OpDef(op=FixAdd, inputs=List(x2590, x2591)).name("x2593_x2592").srcCtx("SGD_minibatch.scala:44:16") } // FixAdd(x2590,x2591)
    // x2593 = SimpleStruct(ArrayBuffer((offset,x2592), (size,Const(64)), (isLoad,Const(true))))
    val x2594 = withCtrl(x2596) { OpDef(op=BitAnd, inputs=List(b1559, b1560)).name("x2594").srcCtx("UnrollingBase.scala:28:66") } // And(b1559,b1560)
    val x2595_b2749_b2747 = withCtrl(x2596) { WriteMem(b2747, x2593_x2592).name("x2595_b2749_b2747").srcCtx("SGD_minibatch.scala:44:16") } // StreamWrite(x2586,x2593,x2594)
    val x2595_b2750_b2748 = withCtrl(x2596) { WriteMem(b2748, Const(64)).name("x2595_b2750_b2748").srcCtx("SGD_minibatch.scala:44:16") } // StreamWrite(x2586,x2593,x2594)
    val x2597 = withCtrl(x2606) { FringeDenseLoad(dram=List(x2559), cmdStream=List(b2747, b2748), dataStream=List(x2587)).name("x2597").srcCtx("SGD_minibatch.scala:44:16") } // FringeDenseLoad(x2559,x2586,x2587)
    val x2598 = withCtrl(x2606) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2598").srcCtx("SGD_minibatch.scala:44:16") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2599 = withCtrl(x2606) { CounterChain(List(x2598)).name("x2599").srcCtx("SGD_minibatch.scala:44:16") } // CounterChainNew(List(x2598))
    val x2605 = withCtrl(x2606) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2599).name("x2605").srcCtx("SGD_minibatch.scala:44:16") } // UnrolledForeach(List(b1559, b1560),x2599,Block(Const(())),List(List(b1577)),List(List(b1578)))
    val b1577 = withCtrl(x2605) { CounterIter(x2598, None).name("b1577") } // b1577
    val b1578 = withCtrl(x2605) { Const(true).name("b1578") } // b1578
    val x2600 = withCtrl(x2605) { OpDef(op=BitAnd, inputs=List(b1578, b1559)).name("x2600").srcCtx("UnrollingBase.scala:28:66") } // And(b1578,b1559)
    val x2601 = withCtrl(x2605) { OpDef(op=BitAnd, inputs=List(x2600, b1560)).name("x2601").srcCtx("UnrollingBase.scala:28:66") } // And(x2600,b1560)
    val x2602_x2602 = withCtrl(x2605) { ReadMem(x2587).name("x2602_x2602").srcCtx("SGD_minibatch.scala:44:16") } // ParStreamRead(x2587,List(x2601))
    val x2603_x2603 = withCtrl(x2605) { x2602_x2602 } // VectorApply(x2602,0)
    val x2604 = withCtrl(x2605) { StoreBanks(List(List(x2572_d0_b0)), List(b1577), x2603_x2603).name("x2604").srcCtx("SGD_minibatch.scala:44:16") } // ParSRAMStore(x2572,List(List(b1577)),List(x2603),List(x2601))
    val x2607 = withCtrl(x2698) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2607").srcCtx("SGD_minibatch.scala:45:16") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2608 = withCtrl(x2698) { CounterChain(List(x2607)).name("x2608").srcCtx("SGD_minibatch.scala:45:16") } // CounterChainNew(List(x2607))
    val x2635 = withCtrl(x2698) { LoopController(style=StreamPipe, level=OuterControl, cchain=x2608).name("x2635").srcCtx("SGD_minibatch.scala:45:16") } // UnrolledForeach(List(b1559, b1560),x2608,Block(Const(())),List(List(b1588)),List(List(b1589)))
    val b1588 = withCtrl(x2635) { CounterIter(x2607, Some(0)).name("b1588") } // b1588
    val b1589 = withCtrl(x2635) { Const(true).name("b1589") } // b1589
    val b2751 = withCtrl(x2635) { StreamOut(field="offset").name("b2751").srcCtx("SGD_minibatch.scala:45:16") } // x2609 = StreamOutNew(BurstCmdBus)
    isAccum(b2751) = false
    bufferDepthOf(b2751) = 1
    val b2752 = withCtrl(x2635) { StreamOut(field="size").name("b2752").srcCtx("SGD_minibatch.scala:45:16") } // x2609 = StreamOutNew(BurstCmdBus)
    isAccum(b2752) = false
    bufferDepthOf(b2752) = 1
    val x2610 = withCtrl(x2635) { StreamIn(field="data").name("x2610").srcCtx("SGD_minibatch.scala:45:16") } // x2610 = StreamInNew(BurstDataBus())
    isAccum(x2610) = false
    bufferDepthOf(x2610) = 1
    val x2624 = withCtrl(x2635) { UnitController(style=SeqPipe, level=InnerControl).name("x2624").srcCtx("SGD_minibatch.scala:45:16") } // UnitPipe(List(b1589, b1559, b1560),Block(x2623))
    val x2611 = withCtrl(x2624) { OpDef(op=FixAdd, inputs=List(b1558, b1588)).name("x2611").srcCtx("SGD_minibatch.scala:45:16") } // FixAdd(b1558,b1588)
    val x2612 = withCtrl(x2624) { x2611 } // FixConvert(x2611,TRUE,_32,_0) (Same Type. No op)
    val x2613 = withCtrl(x2624) { OpDef(op=FixSla, inputs=List(x2612, Const(4))).name("x2613").srcCtx("SGD_minibatch.scala:45:16") } // FixLsh(x2612,Const(4))
    val x2614 = withCtrl(x2624) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x2615 = withCtrl(x2624) { OpDef(op=FixAdd, inputs=List(x2613, x2614)).name("x2615").srcCtx("SGD_minibatch.scala:45:16") } // FixAdd(x2613,x2614)
    val x2616 = withCtrl(x2624) { OpDef(op=FixSla, inputs=List(x2615, Const(2))).name("x2616").srcCtx("SGD_minibatch.scala:45:16") } // FixLsh(x2615,Const(2))
    val x2617 = withCtrl(x2624) { x2616 } // FixConvert(x2616,TRUE,_64,_0)
    val x2618 = withCtrl(x2624) { DramAddress(x2557).name("x2618").srcCtx("SGD_minibatch.scala:45:16") } // GetDRAMAddress(x2557)
    val x2620_x2619 = withCtrl(x2624) { OpDef(op=FixAdd, inputs=List(x2617, x2618)).name("x2620_x2619").srcCtx("SGD_minibatch.scala:45:16") } // FixAdd(x2617,x2618)
    // x2620 = SimpleStruct(ArrayBuffer((offset,x2619), (size,Const(64)), (isLoad,Const(true))))
    val x2621 = withCtrl(x2624) { OpDef(op=BitAnd, inputs=List(b1589, b1559)).name("x2621").srcCtx("UnrollingBase.scala:28:66") } // And(b1589,b1559)
    val x2622 = withCtrl(x2624) { OpDef(op=BitAnd, inputs=List(x2621, b1560)).name("x2622").srcCtx("UnrollingBase.scala:28:66") } // And(x2621,b1560)
    val x2623_b2753_b2751 = withCtrl(x2624) { WriteMem(b2751, x2620_x2619).name("x2623_b2753_b2751").srcCtx("SGD_minibatch.scala:45:16") } // StreamWrite(x2609,x2620,x2622)
    val x2623_b2754_b2752 = withCtrl(x2624) { WriteMem(b2752, Const(64)).name("x2623_b2754_b2752").srcCtx("SGD_minibatch.scala:45:16") } // StreamWrite(x2609,x2620,x2622)
    val x2625 = withCtrl(x2635) { FringeDenseLoad(dram=List(x2557), cmdStream=List(b2751, b2752), dataStream=List(x2610)).name("x2625").srcCtx("SGD_minibatch.scala:45:16") } // FringeDenseLoad(x2557,x2609,x2610)
    val x2626 = withCtrl(x2635) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2626").srcCtx("SGD_minibatch.scala:45:16") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2627 = withCtrl(x2635) { CounterChain(List(x2626)).name("x2627").srcCtx("SGD_minibatch.scala:45:16") } // CounterChainNew(List(x2626))
    val x2634 = withCtrl(x2635) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2627).name("x2634").srcCtx("SGD_minibatch.scala:45:16") } // UnrolledForeach(List(b1589, b1559, b1560),x2627,Block(Const(())),List(List(b1609)),List(List(b1610)))
    val b1609 = withCtrl(x2634) { CounterIter(x2626, None).name("b1609") } // b1609
    val b1610 = withCtrl(x2634) { Const(true).name("b1610") } // b1610
    val x2628 = withCtrl(x2634) { OpDef(op=BitAnd, inputs=List(b1610, b1589)).name("x2628").srcCtx("UnrollingBase.scala:28:66") } // And(b1610,b1589)
    val x2629 = withCtrl(x2634) { OpDef(op=BitAnd, inputs=List(b1559, b1560)).name("x2629").srcCtx("UnrollingBase.scala:28:66") } // And(b1559,b1560)
    val x2630 = withCtrl(x2634) { OpDef(op=BitAnd, inputs=List(x2628, x2629)).name("x2630").srcCtx("UnrollingBase.scala:28:66") } // And(x2628,x2629)
    val x2631_x2631 = withCtrl(x2634) { ReadMem(x2610).name("x2631_x2631").srcCtx("SGD_minibatch.scala:45:16") } // ParStreamRead(x2610,List(x2630))
    val x2632_x2632 = withCtrl(x2634) { x2631_x2631 } // VectorApply(x2631,0)
    val x2633 = withCtrl(x2634) { StoreBanks(List(List(x2574_d0_b0), List(x2574_d1_b0)), List(b1588, b1609), x2632_x2632).name("x2633").srcCtx("SGD_minibatch.scala:45:16") } // ParSRAMStore(x2574,List(List(b1588, b1609)),List(x2632),List(x2630))
    val x2636_d0_b0 = withCtrl(x2698) { SRAM(size=16, banking=Strided(banks=1, stride=1)).name("x2636_d0_b0").srcCtx("SGD_minibatch.scala:46:29:y_err") } // x2636 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2636_d0_b0) = false
    bufferDepthOf(x2636_d0_b0) = 1
    staticDimsOf(x2636_d0_b0) = List(16)
    val x2637 = withCtrl(x2698) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2637").srcCtx("SGD_minibatch.scala:47:25") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2638 = withCtrl(x2698) { CounterChain(List(x2637)).name("x2638").srcCtx("SGD_minibatch.scala:47:34") } // CounterChainNew(List(x2637))
    val x2665 = withCtrl(x2698) { LoopController(style=MetaPipe, level=OuterControl, cchain=x2638).name("x2665").srcCtx("SGD_minibatch.scala:47:34") } // UnrolledForeach(List(b1559, b1560),x2638,Block(Const(())),List(List(b1622)),List(List(b1623)))
    val b1622 = withCtrl(x2665) { CounterIter(x2637, Some(0)).name("b1622") } // b1622
    val b1623 = withCtrl(x2665) { Const(true).name("b1623") } // b1623
    val x2639_d0 = withCtrl(x2665) { Reg(init=Some(0.0)).name("x2639_d0").srcCtx("SGD_minibatch.scala:48:33:y_hat") } // x2639 = RegNew(Const(0))
    isAccum(x2639_d0) = false
    bufferDepthOf(x2639_d0) = 2
    val x2639_d1 = withCtrl(x2665) { Reg(init=Some(0.0)).name("x2639_d1").srcCtx("SGD_minibatch.scala:48:33:y_hat") } // x2639 = RegNew(Const(0))
    isAccum(x2639_d1) = true
    bufferDepthOf(x2639_d1) = 1
    val x2640 = withCtrl(x2665) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2640").srcCtx("SGD_minibatch.scala:48:46") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2641 = withCtrl(x2665) { CounterChain(List(x2640)).name("x2641").srcCtx("SGD_minibatch.scala:48:94") } // CounterChainNew(List(x2640))
    val x2657 = withCtrl(x2665) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2641).name("x2657").srcCtx("SGD_minibatch.scala:48:94") } // UnrolledReduce(List(b1623, b1559, b1560),x2641,x2639,Block((x2639) => Const(())),List(List(b1627)),List(List(b1628)))
    val b1627 = withCtrl(x2657) { CounterIter(x2640, None).name("b1627") } // b1627
    val b1628 = withCtrl(x2657) { Const(true).name("b1628") } // b1628
    val x2642 = withCtrl(x2657) { OpDef(op=BitAnd, inputs=List(b1628, b1623)).name("x2642").srcCtx("UnrollingBase.scala:28:66") } // And(b1628,b1623)
    val x2643 = withCtrl(x2657) { OpDef(op=BitAnd, inputs=List(b1559, b1560)).name("x2643").srcCtx("UnrollingBase.scala:28:66") } // And(b1559,b1560)
    val x2644 = withCtrl(x2657) { OpDef(op=BitAnd, inputs=List(x2642, x2643)).name("x2644").srcCtx("UnrollingBase.scala:28:66") } // And(x2642,x2643)
    val x2645 = withCtrl(x2657) { LoadBanks(List(x2574_d1_b0), List(b1622, b1627)).name("x2645").srcCtx("SGD_minibatch.scala:48:66") } // ParSRAMLoad(x2574,List(List(b1622, b1627)),List(x2644))
    val x2646 = withCtrl(x2657) { x2645 } // VectorApply(x2645,0)
    val x2647 = withCtrl(x2657) { LoadBanks(List(x2573_d2_b0), List(b1627)).name("x2647").srcCtx("SGD_minibatch.scala:48:82") } // ParSRAMLoad(x2573,List(List(b1627)),List(x2644))
    val x2648 = withCtrl(x2657) { x2647 } // VectorApply(x2647,0)
    val x2649 = withCtrl(x2657) { x2648 } // FixConvert(x2648,TRUE,_16,_16) (Same Type. No op)
    val x2650 = withCtrl(x2657) { OpDef(op=FixMul, inputs=List(x2646, x2649)).name("x2650").srcCtx("SGD_minibatch.scala:48:72") } // FixMul(x2646,x2649)
    val x2651 = withCtrl(x2657) { ReadMem(x2639_d1).name("x2651").srcCtx("SGD_minibatch.scala:48:94") } // RegRead(x2639)
    val x2652 = withCtrl(x2657) { OpDef(op=FixEql, inputs=List(b1627, Const(0))).name("x2652").srcCtx("SGD_minibatch.scala:48:94") } // FixEql(b1627,Const(0))
    val x2653 = withCtrl(x2657) { ReduceAccumOp(op=FixAdd, input=x2650, accum=x2651).name("x2653").srcCtx("SGD_minibatch.scala:48:96") } // FixAdd(x2650,x2651)
    val x2654 = withCtrl(x2657) { OpDef(op=BitAnd, inputs=List(b1623, b1559)).name("x2654").srcCtx("UnrollingBase.scala:28:66") } // And(b1623,b1559)
    val x2655 = withCtrl(x2657) { OpDef(op=BitAnd, inputs=List(x2654, b1560)).name("x2655").srcCtx("UnrollingBase.scala:28:66") } // And(x2654,b1560)
    val x2656_x2639_d0 = withCtrl(x2657) { WriteMem(x2639_d0, x2653).name("x2656_x2639_d0").srcCtx("SGD_minibatch.scala:48:94") } // RegWrite(x2639,x2653,x2655)
    antiDepsOf(x2656_x2639_d0)=List(x2651)
    val x2656_x2639_d1 = withCtrl(x2657) { WriteMem(x2639_d1, x2653).name("x2656_x2639_d1").srcCtx("SGD_minibatch.scala:48:94") } // RegWrite(x2639,x2653,x2655)
    antiDepsOf(x2656_x2639_d1)=List(x2651)
    val x2664 = withCtrl(x2665) { UnitController(style=SeqPipe, level=InnerControl).name("x2664").srcCtx("SGD_minibatch.scala:47:34") } // UnitPipe(List(b1623, b1559, b1560),Block(Const(())))
    val x2658 = withCtrl(x2664) { OpDef(op=BitAnd, inputs=List(b1623, b1559)).name("x2658").srcCtx("UnrollingBase.scala:28:66") } // And(b1623,b1559)
    val x2659 = withCtrl(x2664) { OpDef(op=BitAnd, inputs=List(x2658, b1560)).name("x2659").srcCtx("UnrollingBase.scala:28:66") } // And(x2658,b1560)
    val x2660 = withCtrl(x2664) { LoadBanks(List(x2572_d0_b0), List(b1622)).name("x2660").srcCtx("SGD_minibatch.scala:49:28") } // SRAMLoad(x2572,ArrayBuffer(Const(16)),List(b1622),Const(0),x2659)
    val x2661 = withCtrl(x2664) { ReadMem(x2639_d0).name("x2661").srcCtx("SGD_minibatch.scala:49:40") } // RegRead(x2639)
    val x2662 = withCtrl(x2664) { OpDef(op=FixSub, inputs=List(x2660, x2661)).name("x2662").srcCtx("SGD_minibatch.scala:49:32") } // FixSub(x2660,x2661)
    val x2663 = withCtrl(x2664) { StoreBanks(List(List(x2636_d0_b0)), List(b1622), x2662).name("x2663").srcCtx("SGD_minibatch.scala:49:20") } // SRAMStore(x2636,ArrayBuffer(Const(16)),List(b1622),Const(0),x2662,x2659)
    val x2666 = withCtrl(x2698) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2666").srcCtx("SGD_minibatch.scala:51:35") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2667 = withCtrl(x2698) { CounterChain(List(x2666)).name("x2667").srcCtx("SGD_minibatch.scala:55:11") } // CounterChainNew(List(x2666))
    val x2697 = withCtrl(x2698) { LoopController(style=MetaPipe, level=OuterControl, cchain=x2667).name("x2697").srcCtx("SGD_minibatch.scala:55:11") } // UnrolledReduce(List(b1559, b1560),x2667,x2573,Block((x2573) => Const(())),List(List(b1657)),List(List(b1658)))
    val b1657 = withCtrl(x2697) { CounterIter(x2666, Some(0)).name("b1657") } // b1657
    val b1658 = withCtrl(x2697) { Const(true).name("b1658") } // b1658
    val x2668_d0_b0 = withCtrl(x2697) { SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x2668_d0_b0").srcCtx("SGD_minibatch.scala:52:29:row") } // x2668 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x2668_d0_b0) = false
    bufferDepthOf(x2668_d0_b0) = 2
    staticDimsOf(x2668_d0_b0) = List(16)
    val x2669 = withCtrl(x2697) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2669").srcCtx("SGD_minibatch.scala:53:26") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2670 = withCtrl(x2697) { CounterChain(List(x2669)).name("x2670").srcCtx("SGD_minibatch.scala:53:34") } // CounterChainNew(List(x2669))
    val x2681 = withCtrl(x2697) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2670).name("x2681").srcCtx("SGD_minibatch.scala:53:34") } // UnrolledForeach(List(b1658, b1559, b1560),x2670,Block(Const(())),List(List(b1662)),List(List(b1663)))
    val b1662 = withCtrl(x2681) { CounterIter(x2669, None).name("b1662") } // b1662
    val b1663 = withCtrl(x2681) { Const(true).name("b1663") } // b1663
    val x2671 = withCtrl(x2681) { OpDef(op=BitAnd, inputs=List(b1663, b1658)).name("x2671").srcCtx("UnrollingBase.scala:28:66") } // And(b1663,b1658)
    val x2672 = withCtrl(x2681) { OpDef(op=BitAnd, inputs=List(b1559, b1560)).name("x2672").srcCtx("UnrollingBase.scala:28:66") } // And(b1559,b1560)
    val x2673 = withCtrl(x2681) { OpDef(op=BitAnd, inputs=List(x2671, x2672)).name("x2673").srcCtx("UnrollingBase.scala:28:66") } // And(x2671,x2672)
    val x2674 = withCtrl(x2681) { LoadBanks(List(x2574_d0_b0), List(b1657, b1662)).name("x2674").srcCtx("SGD_minibatch.scala:53:56") } // ParSRAMLoad(x2574,List(List(b1657, b1662)),List(x2673))
    val x2675 = withCtrl(x2681) { x2674 } // VectorApply(x2674,0)
    val x2676 = withCtrl(x2681) { LoadBanks(List(x2636_d0_b0), List(b1657)).name("x2676").srcCtx("SGD_minibatch.scala:53:69") } // SRAMLoad(x2636,ArrayBuffer(Const(16)),List(b1657),Const(0),x2673)
    val x2677 = withCtrl(x2681) { OpDef(op=FixMul, inputs=List(x2675, x2676)).name("x2677").srcCtx("SGD_minibatch.scala:53:62") } // FixMul(x2675,x2676)
    val x2678 = withCtrl(x2681) { ReadMem(x2552).name("x2678").srcCtx("SGD_minibatch.scala:53:75") } // RegRead(x2552)
    val x2679 = withCtrl(x2681) { OpDef(op=FixMul, inputs=List(x2677, x2678)).name("x2679").srcCtx("SGD_minibatch.scala:53:73") } // FixMul(x2677,x2678)
    val x2680 = withCtrl(x2681) { StoreBanks(List(List(x2668_d0_b0)), List(b1662), x2679).name("x2680").srcCtx("SGD_minibatch.scala:53:48") } // ParSRAMStore(x2668,List(List(b1662)),List(x2679),List(x2673))
    val x2682 = withCtrl(x2697) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x2682").srcCtx("SGD_minibatch.scala:55:11") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x2683 = withCtrl(x2697) { CounterChain(List(x2682)).name("x2683").srcCtx("SGD_minibatch.scala:55:11") } // CounterChainNew(ArrayBuffer(x2682))
    val x2696 = withCtrl(x2697) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2683).name("x2696").srcCtx("SGD_minibatch.scala:55:11") } // UnrolledForeach(List(),x2683,Block(Const(())),ArrayBuffer(List(b1675)),ArrayBuffer(List(b1676)))
    val b1675 = withCtrl(x2696) { CounterIter(x2682, None).name("b1675") } // b1675
    val b1676 = withCtrl(x2696) { Const(true).name("b1676") } // b1676
    val x2684 = withCtrl(x2696) { OpDef(op=BitAnd, inputs=List(b1676, b1559)).name("x2684").srcCtx("UnrollingBase.scala:28:66") } // And(b1676,b1559)
    val x2685 = withCtrl(x2696) { OpDef(op=BitAnd, inputs=List(x2684, b1560)).name("x2685").srcCtx("UnrollingBase.scala:28:66") } // And(x2684,b1560)
    val x2686 = withCtrl(x2696) { LoadBanks(List(x2668_d0_b0), List(b1675)).name("x2686").srcCtx("SGD_minibatch.scala:55:11") } // ParSRAMLoad(x2668,List(ArrayBuffer(b1675)),List(x2685))
    val x2687 = withCtrl(x2696) { x2686 } // VectorApply(x2686,0)
    val x2688 = withCtrl(x2696) { LoadBanks(List(x2573_d1_b0), List(b1675)).name("x2688").srcCtx("SGD_minibatch.scala:55:11") } // ParSRAMLoad(x2573,List(ArrayBuffer(b1675)),List(x2685))
    val x2689 = withCtrl(x2696) { x2688 } // VectorApply(x2688,0)
    val x2690 = withCtrl(x2696) { OpDef(op=BitAnd, inputs=List(b1658, b1559)).name("x2690").srcCtx("SGD_minibatch.scala:55:11") } // And(b1658,b1559)
    val x2691 = withCtrl(x2696) { OpDef(op=BitAnd, inputs=List(x2690, b1560)).name("x2691").srcCtx("SGD_minibatch.scala:55:11") } // And(x2690,b1560)
    val x2692 = withCtrl(x2696) { OpDef(op=BitAnd, inputs=List(x2691, x2685)).name("x2692").srcCtx("SGD_minibatch.scala:55:11") } // And(x2691,x2685)
    val x2693 = withCtrl(x2696) { OpDef(op=FixEql, inputs=List(b1657, Const(0))).name("x2693").srcCtx("SGD_minibatch.scala:55:11") } // FixEql(b1657,Const(0))
    val x2694 = withCtrl(x2696) { OpDef(op=FixAdd, inputs=List(x2687, x2689)).name("x2694").srcCtx("SGD_minibatch.scala:55:15") } // FixAdd(x2687,x2689)
    val x2695 = withCtrl(x2696) { StoreBanks(List(List(x2573_d0_b0), List(x2573_d1_b0), List(x2573_d2_b0)), List(b1675), x2694).name("x2695").srcCtx("SGD_minibatch.scala:55:11") } // ParSRAMStore(x2573,List(ArrayBuffer(b1675)),List(x2694),List(x2685))
    antiDepsOf(x2695)=List(x2688)
    val x2720 = withCtrl(x2721) { UnitController(style=StreamPipe, level=OuterControl).name("x2720").srcCtx("SGD_minibatch.scala:57:27") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b2755 = withCtrl(x2720) { StreamOut(field="offset").name("b2755").srcCtx("SGD_minibatch.scala:57:27") } // x2699 = StreamOutNew(BurstCmdBus)
    isAccum(b2755) = false
    bufferDepthOf(b2755) = 1
    val b2756 = withCtrl(x2720) { StreamOut(field="size").name("b2756").srcCtx("SGD_minibatch.scala:57:27") } // x2699 = StreamOutNew(BurstCmdBus)
    isAccum(b2756) = false
    bufferDepthOf(b2756) = 1
    val x2700 = withCtrl(x2720) { StreamOut(field="data").name("x2700").srcCtx("SGD_minibatch.scala:57:27") } // x2700 = StreamOutNew(BurstFullDataBus())
    isAccum(x2700) = false
    bufferDepthOf(x2700) = 1
    val x2701 = withCtrl(x2720) { StreamIn(field="ack").name("x2701").srcCtx("SGD_minibatch.scala:57:27") } // x2701 = StreamInNew(BurstAckBus)
    isAccum(x2701) = false
    bufferDepthOf(x2701) = 1
    val x2709 = withCtrl(x2720) { UnitController(style=SeqPipe, level=InnerControl).name("x2709").srcCtx("SGD_minibatch.scala:57:27") } // UnitPipe(List(Const(true)),Block(x2708))
    val x2702 = withCtrl(x2709) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x2703 = withCtrl(x2709) { OpDef(op=FixSla, inputs=List(x2702, Const(2))).name("x2703").srcCtx("SGD_minibatch.scala:57:27") } // FixLsh(x2702,Const(2))
    val x2704 = withCtrl(x2709) { x2703 } // FixConvert(x2703,TRUE,_64,_0)
    val x2705 = withCtrl(x2709) { DramAddress(x2560).name("x2705").srcCtx("SGD_minibatch.scala:57:27") } // GetDRAMAddress(x2560)
    val x2707_x2706 = withCtrl(x2709) { OpDef(op=FixAdd, inputs=List(x2704, x2705)).name("x2707_x2706").srcCtx("SGD_minibatch.scala:57:27") } // FixAdd(x2704,x2705)
    // x2707 = SimpleStruct(ArrayBuffer((offset,x2706), (size,Const(64)), (isLoad,Const(false))))
    val x2708_b2757_b2755 = withCtrl(x2709) { WriteMem(b2755, x2707_x2706).name("x2708_b2757_b2755").srcCtx("SGD_minibatch.scala:57:27") } // StreamWrite(x2699,x2707,Const(true))
    val x2708_b2758_b2756 = withCtrl(x2709) { WriteMem(b2756, Const(64)).name("x2708_b2758_b2756").srcCtx("SGD_minibatch.scala:57:27") } // StreamWrite(x2699,x2707,Const(true))
    val x2710 = withCtrl(x2720) { Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x2710").srcCtx("SGD_minibatch.scala:57:27") } // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x2711 = withCtrl(x2720) { CounterChain(List(x2710)).name("x2711").srcCtx("SGD_minibatch.scala:57:27") } // CounterChainNew(List(x2710))
    val x2716 = withCtrl(x2720) { LoopController(style=InnerPipe, level=InnerControl, cchain=x2711).name("x2716").srcCtx("SGD_minibatch.scala:57:27") } // UnrolledForeach(List(Const(true)),x2711,Block(Const(())),List(List(b1705)),List(List(b1706)))
    val b1705 = withCtrl(x2716) { CounterIter(x2710, None).name("b1705") } // b1705
    val b1706 = withCtrl(x2716) { Const(true).name("b1706") } // b1706
    val x2712 = withCtrl(x2716) { LoadBanks(List(x2573_d0_b0), List(b1705)).name("x2712").srcCtx("SGD_minibatch.scala:57:27") } // ParSRAMLoad(x2573,List(List(b1705)),List(b1706))
    val x2714_x2713 = withCtrl(x2716) { x2712 } // VectorApply(x2712,0)
    // x2714 = SimpleStruct(ArrayBuffer((_1,x2713), (_2,Const(true))))
    val x2715_x2715_x2700 = withCtrl(x2716) { WriteMem(x2700, x2714_x2713).name("x2715_x2715_x2700").srcCtx("SGD_minibatch.scala:57:27") } // ParStreamWrite(x2700,List(x2714),List(b1706))
    val x2717 = withCtrl(x2720) { FringeDenseStore(dram=List(x2560), cmdStream=List(b2755, b2756), dataStream=List(x2700), ackStream=List(x2701)).name("x2717").srcCtx("SGD_minibatch.scala:57:27") } // FringeDenseStore(x2560,x2699,x2700,x2701)
    val x2719 = withCtrl(x2720) { UnitController(style=SeqPipe, level=InnerControl).name("x2719").srcCtx("SGD_minibatch.scala:57:27") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x2718_x2718 = withCtrl(x2719) { ReadMem(x2701).name("x2718_x2718").srcCtx("SGD_minibatch.scala:57:27") } // StreamRead(x2701,Const(true))
    
  }
}
