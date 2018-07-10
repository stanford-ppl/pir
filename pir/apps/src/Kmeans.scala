import pir._
import pir.node._
import arch._
import prism.enums._

object Kmeans extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x4690 = ArgIn(init=0).name("x4690").ctrl(top).srcCtx("Kmeans.scala:27:22:iters") // ArgInNew(Const(0))
    isAccum(x4690) = false
    bufferDepthOf(x4690) = 1
    boundOf(x4690) = 2
    val x4691 = ArgIn(init=0).name("x4691").ctrl(top).srcCtx("Kmeans.scala:28:22:N") // ArgInNew(Const(0))
    isAccum(x4691) = false
    bufferDepthOf(x4691) = 1
    boundOf(x4691) = 64
    val x4694 = ReadMem(x4691).name("x4694").ctrl(top).srcCtx("Kmeans.scala:33:26") // RegRead(x4691)
    val x4695 = DRAM(dims=List(x4694, Const(32))).name("x4695").ctrl(top).srcCtx("Kmeans.scala:33:25:points") // x4695 = DRAMNew(ArrayBuffer(x4694, Const(32)),Const(0))
    val x4696 = DRAM(dims=List(Const(16), Const(32))).name("x4696").ctrl(top).srcCtx("Kmeans.scala:34:28:centroids") // x4696 = DRAMNew(ArrayBuffer(Const(16), Const(32)),Const(0))
    val x4932 = UnitController(style=SeqPipe, level=OuterControl).name("x4932").ctrl(top).srcCtx("Kmeans.scala:38:11") // Hwblock(Block(Const(())),false)
    val x4700_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4700_d0_b0").ctrl(x4932).srcCtx("Kmeans.scala:39:24:cts") // x4700 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x4700_d0_b0) = false
    bufferDepthOf(x4700_d0_b0) = 1
    val x4700_d1_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4700_d1_b0").ctrl(x4932).srcCtx("Kmeans.scala:39:24:cts") // x4700 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x4700_d1_b0) = false
    bufferDepthOf(x4700_d1_b0) = 1
    val x4701 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x4701").ctrl(x4932).srcCtx("Kmeans.scala:42:11") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x4702 = CounterChain(List(x4701)).name("x4702").ctrl(x4932).srcCtx("Kmeans.scala:42:11") // CounterChainNew(List(x4701))
    val x4724 = LoopController(style=StreamPipe, level=OuterControl, cchain=x4702).name("x4724").ctrl(x4932).srcCtx("Kmeans.scala:42:11") // UnrolledForeach(List(Const(true)),x4702,Block(Const(())),List(List(b2881)),List(List(b2882)))
    val b2881 = CounterIter(x4701, Some(0)).name("b2881").ctrl(x4724) // b2881
    val b2882 = Const(true).name("b2882").ctrl(x4724) // b2882
    val b5036 = StreamOut(field="offset").name("b5036").ctrl(x4724).srcCtx("Kmeans.scala:42:11") // x4703 = StreamOutNew(BurstCmdBus)
    isAccum(b5036) = false
    bufferDepthOf(b5036) = 1
    val b5037 = StreamOut(field="size").name("b5037").ctrl(x4724).srcCtx("Kmeans.scala:42:11") // x4703 = StreamOutNew(BurstCmdBus)
    isAccum(b5037) = false
    bufferDepthOf(b5037) = 1
    val x4704 = StreamIn(field="data").name("x4704").ctrl(x4724).srcCtx("Kmeans.scala:42:11") // x4704 = StreamInNew(BurstDataBus())
    isAccum(x4704) = false
    bufferDepthOf(x4704) = 1
    val x4715 = UnitController(style=SeqPipe, level=InnerControl).name("x4715").ctrl(x4724).srcCtx("Kmeans.scala:42:11") // UnitPipe(List(b2882),Block(x4714))
    val x4705 = b2881 // FixConvert(b2881,TRUE,_32,_0) (Same Type. No op)
    val x4706 = OpDef(op=FixSla, inputs=List(x4705, Const(5))).name("x4706").ctrl(x4715).srcCtx("Kmeans.scala:42:11") // FixLsh(x4705,Const(5))
    val x4707 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4708 = OpDef(op=FixAdd, inputs=List(x4706, x4707)).name("x4708").ctrl(x4715).srcCtx("Kmeans.scala:42:11") // FixAdd(x4706,x4707)
    val x4709 = OpDef(op=FixSla, inputs=List(x4708, Const(2))).name("x4709").ctrl(x4715).srcCtx("Kmeans.scala:42:11") // FixLsh(x4708,Const(2))
    val x4710 = x4709 // FixConvert(x4709,TRUE,_64,_0)
    val x4711 = DramAddress(x4695).name("x4711").ctrl(x4715).srcCtx("Kmeans.scala:42:11") // GetDRAMAddress(x4695)
    val x4713_x4712 = OpDef(op=FixAdd, inputs=List(x4710, x4711)).name("x4713_x4712").ctrl(x4715).srcCtx("Kmeans.scala:42:11") // FixAdd(x4710,x4711)
    // x4713 = SimpleStruct(ArrayBuffer((offset,x4712), (size,Const(128)), (isLoad,Const(true))))
    val x4714_b5038_b5036 = WriteMem(b5036, x4713_x4712).name("x4714_b5038_b5036").ctrl(x4715).srcCtx("Kmeans.scala:42:11") // StreamWrite(x4703,x4713,b2882)
    val x4714_b5039_b5037 = WriteMem(b5037, Const(128)).name("x4714_b5039_b5037").ctrl(x4715).srcCtx("Kmeans.scala:42:11") // StreamWrite(x4703,x4713,b2882)
    val x4716 = FringeDenseLoad(dram=List(x4695), cmdStream=List(b5036, b5037), dataStream=List(x4704)).name("x4716").ctrl(x4724).srcCtx("Kmeans.scala:42:11") // FringeDenseLoad(x4695,x4703,x4704)
    val x4717 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x4717").ctrl(x4724).srcCtx("Kmeans.scala:42:11") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x4718 = CounterChain(List(x4717)).name("x4718").ctrl(x4724).srcCtx("Kmeans.scala:42:11") // CounterChainNew(List(x4717))
    val x4723 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4718).name("x4723").ctrl(x4724).srcCtx("Kmeans.scala:42:11") // UnrolledForeach(List(b2882),x4718,Block(Const(())),List(List(b2899)),List(List(b2900)))
    val b2899 = CounterIter(x4717, None).name("b2899").ctrl(x4723) // b2899
    val b2900 = Const(true).name("b2900").ctrl(x4723) // b2900
    val x4719 = OpDef(op=BitAnd, inputs=List(b2900, b2882)).name("x4719").ctrl(x4723).srcCtx("UnrollingBase.scala:28:66") // And(b2900,b2882)
    val x4720_x4720 = ReadMem(x4704).name("x4720_x4720").ctrl(x4723).srcCtx("Kmeans.scala:42:11") // ParStreamRead(x4704,List(x4719))
    val x4721_x4721 = x4720_x4720 // x4721 = VectorApply(x4720,0)
    val x4722 = StoreBanks(List(x4700_d0_b0, x4700_d1_b0), List(b2881, b2899), x4721_x4721).name("x4722").ctrl(x4723).srcCtx("Kmeans.scala:42:11") // ParSRAMStore(x4700,List(List(b2881, b2899)),List(x4721),List(x4719))
    val x4725 = ReadMem(x4690).name("x4725").ctrl(x4932).srcCtx("Kmeans.scala:44:26") // RegRead(x4690)
    val x4726 = Counter(min=Const(0), max=x4725, step=Const(1), par=1).name("x4726").ctrl(x4932).srcCtx("Kmeans.scala:44:32") // CounterNew(Const(0),x4725,Const(1),Const(1))
    val x4727 = CounterChain(List(x4726)).name("x4727").ctrl(x4932).srcCtx("Kmeans.scala:44:37") // CounterChainNew(List(x4726))
    val x4903 = LoopController(style=SeqPipe, level=OuterControl, cchain=x4727).name("x4903").ctrl(x4932).srcCtx("Kmeans.scala:44:37") // UnrolledForeach(List(Const(true)),x4727,Block(Const(())),List(List(b2910)),List(List(b2911)))
    val b2910 = CounterIter(x4726, Some(0)).name("b2910").ctrl(x4903) // b2910
    val b2911 = Const(true).name("b2911").ctrl(x4903) // b2911
    val x4728_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4728_d0_b0").ctrl(x4903).srcCtx("Kmeans.scala:46:41:newCents") // x4728 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x4728_d0_b0) = false
    bufferDepthOf(x4728_d0_b0) = 1
    val x4728_d1_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4728_d1_b0").ctrl(x4903).srcCtx("Kmeans.scala:46:41:newCents") // x4728 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x4728_d1_b0) = false
    bufferDepthOf(x4728_d1_b0) = 1
    val x4728_d2_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4728_d2_b0").ctrl(x4903).srcCtx("Kmeans.scala:46:41:newCents") // x4728 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x4728_d2_b0) = true
    bufferDepthOf(x4728_d2_b0) = 1
    val x4729 = ReadMem(x4691).name("x4729").ctrl(x4903).srcCtx("Kmeans.scala:46:55") // RegRead(x4691)
    val x4730 = Counter(min=Const(0), max=x4729, step=Const(16), par=1).name("x4730").ctrl(x4903).srcCtx("Kmeans.scala:46:63") // CounterNew(Const(0),x4729,Const(16),Const(1))
    val x4731 = CounterChain(List(x4730)).name("x4731").ctrl(x4903).srcCtx("Kmeans.scala:70:10") // CounterChainNew(List(x4730))
    val x4881 = LoopController(style=MetaPipe, level=OuterControl, cchain=x4731).name("x4881").ctrl(x4903).srcCtx("Kmeans.scala:70:10") // UnrolledReduce(List(b2911),x4731,x4728,Block((x4728) => Const(())),List(List(b2919)),List(List(b2920)))
    val b2919 = CounterIter(x4730, Some(0)).name("b2919").ctrl(x4881) // b2919
    val b2920 = Const(true).name("b2920").ctrl(x4881) // b2920
    val x4732_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4732_d0_b0").ctrl(x4881).srcCtx("Kmeans.scala:47:28:pts") // x4732 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x4732_d0_b0) = false
    bufferDepthOf(x4732_d0_b0) = 2
    val x4732_d1_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4732_d1_b0").ctrl(x4881).srcCtx("Kmeans.scala:47:28:pts") // x4732 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x4732_d1_b0) = false
    bufferDepthOf(x4732_d1_b0) = 2
    val x4734 = UnitController(style=SeqPipe, level=InnerControl).name("x4734").ctrl(x4881).srcCtx("Kmeans.scala:70:10") // UnitPipe(List(b2920, b2911),Block(Const(())))
    val x4733 = OpDef(op=FixAdd, inputs=List(b2919, Const(16))).name("x4733").ctrl(x4734).srcCtx("Kmeans.scala:48:31") // FixAdd(b2919,Const(16))
    val x4735 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x4735").ctrl(x4881).srcCtx("Kmeans.scala:48:15") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x4736 = CounterChain(List(x4735)).name("x4736").ctrl(x4881).srcCtx("Kmeans.scala:48:15") // CounterChainNew(List(x4735))
    val x4763 = LoopController(style=StreamPipe, level=OuterControl, cchain=x4736).name("x4763").ctrl(x4881).srcCtx("Kmeans.scala:48:15") // UnrolledForeach(List(b2920, b2911),x4736,Block(Const(())),List(List(b2926)),List(List(b2927)))
    val b2926 = CounterIter(x4735, Some(0)).name("b2926").ctrl(x4763) // b2926
    val b2927 = Const(true).name("b2927").ctrl(x4763) // b2927
    val b5040 = StreamOut(field="offset").name("b5040").ctrl(x4763).srcCtx("Kmeans.scala:48:15") // x4737 = StreamOutNew(BurstCmdBus)
    isAccum(b5040) = false
    bufferDepthOf(b5040) = 1
    val b5041 = StreamOut(field="size").name("b5041").ctrl(x4763).srcCtx("Kmeans.scala:48:15") // x4737 = StreamOutNew(BurstCmdBus)
    isAccum(b5041) = false
    bufferDepthOf(b5041) = 1
    val x4738 = StreamIn(field="data").name("x4738").ctrl(x4763).srcCtx("Kmeans.scala:48:15") // x4738 = StreamInNew(BurstDataBus())
    isAccum(x4738) = false
    bufferDepthOf(x4738) = 1
    val x4752 = UnitController(style=SeqPipe, level=InnerControl).name("x4752").ctrl(x4763).srcCtx("Kmeans.scala:48:15") // UnitPipe(List(b2927, b2920, b2911),Block(x4751))
    val x4739 = OpDef(op=FixAdd, inputs=List(b2919, b2926)).name("x4739").ctrl(x4752).srcCtx("Kmeans.scala:48:15") // FixAdd(b2919,b2926)
    val x4740 = x4739 // FixConvert(x4739,TRUE,_32,_0) (Same Type. No op)
    val x4741 = OpDef(op=FixSla, inputs=List(x4740, Const(5))).name("x4741").ctrl(x4752).srcCtx("Kmeans.scala:48:15") // FixLsh(x4740,Const(5))
    val x4742 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4743 = OpDef(op=FixAdd, inputs=List(x4741, x4742)).name("x4743").ctrl(x4752).srcCtx("Kmeans.scala:48:15") // FixAdd(x4741,x4742)
    val x4744 = OpDef(op=FixSla, inputs=List(x4743, Const(2))).name("x4744").ctrl(x4752).srcCtx("Kmeans.scala:48:15") // FixLsh(x4743,Const(2))
    val x4745 = x4744 // FixConvert(x4744,TRUE,_64,_0)
    val x4746 = DramAddress(x4695).name("x4746").ctrl(x4752).srcCtx("Kmeans.scala:48:15") // GetDRAMAddress(x4695)
    val x4748_x4747 = OpDef(op=FixAdd, inputs=List(x4745, x4746)).name("x4748_x4747").ctrl(x4752).srcCtx("Kmeans.scala:48:15") // FixAdd(x4745,x4746)
    // x4748 = SimpleStruct(ArrayBuffer((offset,x4747), (size,Const(128)), (isLoad,Const(true))))
    val x4749 = OpDef(op=BitAnd, inputs=List(b2927, b2920)).name("x4749").ctrl(x4752).srcCtx("UnrollingBase.scala:28:66") // And(b2927,b2920)
    val x4750 = OpDef(op=BitAnd, inputs=List(x4749, b2911)).name("x4750").ctrl(x4752).srcCtx("UnrollingBase.scala:28:66") // And(x4749,b2911)
    val x4751_b5042_b5040 = WriteMem(b5040, x4748_x4747).name("x4751_b5042_b5040").ctrl(x4752).srcCtx("Kmeans.scala:48:15") // StreamWrite(x4737,x4748,x4750)
    val x4751_b5043_b5041 = WriteMem(b5041, Const(128)).name("x4751_b5043_b5041").ctrl(x4752).srcCtx("Kmeans.scala:48:15") // StreamWrite(x4737,x4748,x4750)
    val x4753 = FringeDenseLoad(dram=List(x4695), cmdStream=List(b5040, b5041), dataStream=List(x4738)).name("x4753").ctrl(x4763).srcCtx("Kmeans.scala:48:15") // FringeDenseLoad(x4695,x4737,x4738)
    val x4754 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x4754").ctrl(x4763).srcCtx("Kmeans.scala:48:15") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x4755 = CounterChain(List(x4754)).name("x4755").ctrl(x4763).srcCtx("Kmeans.scala:48:15") // CounterChainNew(List(x4754))
    val x4762 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4755).name("x4762").ctrl(x4763).srcCtx("Kmeans.scala:48:15") // UnrolledForeach(List(b2927, b2920, b2911),x4755,Block(Const(())),List(List(b2947)),List(List(b2948)))
    val b2947 = CounterIter(x4754, None).name("b2947").ctrl(x4762) // b2947
    val b2948 = Const(true).name("b2948").ctrl(x4762) // b2948
    val x4756 = OpDef(op=BitAnd, inputs=List(b2948, b2927)).name("x4756").ctrl(x4762).srcCtx("UnrollingBase.scala:28:66") // And(b2948,b2927)
    val x4757 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x4757").ctrl(x4762).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x4758 = OpDef(op=BitAnd, inputs=List(x4756, x4757)).name("x4758").ctrl(x4762).srcCtx("UnrollingBase.scala:28:66") // And(x4756,x4757)
    val x4759_x4759 = ReadMem(x4738).name("x4759_x4759").ctrl(x4762).srcCtx("Kmeans.scala:48:15") // ParStreamRead(x4738,List(x4758))
    val x4760_x4760 = x4759_x4759 // x4760 = VectorApply(x4759,0)
    val x4761 = StoreBanks(List(x4732_d0_b0, x4732_d1_b0), List(b2926, b2947), x4760_x4760).name("x4761").ctrl(x4762).srcCtx("Kmeans.scala:48:15") // ParSRAMStore(x4732,List(List(b2926, b2947)),List(x4760),List(x4758))
    val x4764_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4764_d0_b0").ctrl(x4881).srcCtx("Kmeans.scala:51:28") // x4764 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x4764_d0_b0) = false
    bufferDepthOf(x4764_d0_b0) = 2
    val x4764_d1_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4764_d1_b0").ctrl(x4881).srcCtx("Kmeans.scala:51:28") // x4764 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x4764_d1_b0) = true
    bufferDepthOf(x4764_d1_b0) = 1
    val x4765 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x4765").ctrl(x4881).srcCtx("Kmeans.scala:51:45") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x4766 = CounterChain(List(x4765)).name("x4766").ctrl(x4881).srcCtx("Kmeans.scala:69:12") // CounterChainNew(List(x4765))
    val x4864 = LoopController(style=MetaPipe, level=OuterControl, cchain=x4766).name("x4864").ctrl(x4881).srcCtx("Kmeans.scala:69:12") // UnrolledReduce(List(b2920, b2911),x4766,x4764,Block((x4764) => Const(())),List(List(b2963)),List(List(b2964)))
    val b2963 = CounterIter(x4765, Some(0)).name("b2963").ctrl(x4864) // b2963
    val b2964 = Const(true).name("b2964").ctrl(x4864) // b2964
    val x4767_d0_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x4767_d0_b0").ctrl(x4864).srcCtx("Kmeans.scala:53:32:dists") // x4767 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x4767_d0_b0) = false
    bufferDepthOf(x4767_d0_b0) = 3
    val x4767_d1_b0 = SRAM(size=16, banking=Strided(banks=16, stride=1)).name("x4767_d1_b0").ctrl(x4864).srcCtx("Kmeans.scala:53:32:dists") // x4767 = SRAMNew(ArrayBuffer(Const(16)))
    isAccum(x4767_d1_b0) = false
    bufferDepthOf(x4767_d1_b0) = 2
    val x4768 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x4768").ctrl(x4864).srcCtx("Kmeans.scala:54:28") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x4769 = CounterChain(List(x4768)).name("x4769").ctrl(x4864).srcCtx("Kmeans.scala:54:37") // CounterChainNew(List(x4768))
    val x4797 = LoopController(style=MetaPipe, level=OuterControl, cchain=x4769).name("x4797").ctrl(x4864).srcCtx("Kmeans.scala:54:37") // UnrolledForeach(List(b2964, b2920, b2911),x4769,Block(Const(())),List(List(b2968)),List(List(b2969)))
    val b2968 = CounterIter(x4768, Some(0)).name("b2968").ctrl(x4797) // b2968
    val b2969 = Const(true).name("b2969").ctrl(x4797) // b2969
    val x4770_d0 = Reg(init=Some(0)).name("x4770_d0").ctrl(x4797).srcCtx("Kmeans.scala:55:36:dist") // x4770 = RegNew(Const(0))
    isAccum(x4770_d0) = false
    bufferDepthOf(x4770_d0) = 2
    val x4770_d1 = Reg(init=Some(0)).name("x4770_d1").ctrl(x4797).srcCtx("Kmeans.scala:55:36:dist") // x4770 = RegNew(Const(0))
    isAccum(x4770_d1) = true
    bufferDepthOf(x4770_d1) = 1
    val x4771 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x4771").ctrl(x4797).srcCtx("Kmeans.scala:55:43") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x4772 = CounterChain(List(x4771)).name("x4772").ctrl(x4797).srcCtx("Kmeans.scala:55:86") // CounterChainNew(List(x4771))
    val x4790 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4772).name("x4790").ctrl(x4797).srcCtx("Kmeans.scala:55:86") // UnrolledReduce(List(b2969, b2964, b2920, b2911),x4772,x4770,Block((x4770) => Const(())),List(List(b2973)),List(List(b2974)))
    val b2973 = CounterIter(x4771, None).name("b2973").ctrl(x4790) // b2973
    val b2974 = Const(true).name("b2974").ctrl(x4790) // b2974
    val x4773 = OpDef(op=BitAnd, inputs=List(b2974, b2969)).name("x4773").ctrl(x4790).srcCtx("UnrollingBase.scala:28:66") // And(b2974,b2969)
    val x4774 = OpDef(op=BitAnd, inputs=List(b2964, b2920)).name("x4774").ctrl(x4790).srcCtx("UnrollingBase.scala:28:66") // And(b2964,b2920)
    val x4775 = OpDef(op=BitAnd, inputs=List(x4773, x4774)).name("x4775").ctrl(x4790).srcCtx("UnrollingBase.scala:28:66") // And(x4773,x4774)
    val x4776 = OpDef(op=BitAnd, inputs=List(x4775, b2911)).name("x4776").ctrl(x4790).srcCtx("UnrollingBase.scala:28:66") // And(x4775,b2911)
    val x4777 = LoadBanks(List(x4732_d1_b0), List(b2963, b2973)).name("x4777").ctrl(x4790).srcCtx("Kmeans.scala:55:60") // ParSRAMLoad(x4732,List(List(b2963, b2973)),List(x4776))
    val x4778 = x4777 // x4778 = VectorApply(x4777,0)
    val x4779 = LoadBanks(List(x4700_d1_b0), List(b2968, b2973)).name("x4779").ctrl(x4790).srcCtx("Kmeans.scala:55:72") // ParSRAMLoad(x4700,List(List(b2968, b2973)),List(x4776))
    val x4780 = x4779 // x4780 = VectorApply(x4779,0)
    val x4781 = OpDef(op=FixSub, inputs=List(x4778, x4780)).name("x4781").ctrl(x4790).srcCtx("Kmeans.scala:55:67") // FixSub(x4778,x4780)
    val x4782 = OpDef(op=FixMul, inputs=List(x4781, x4781)).name("x4782").ctrl(x4790).srcCtx("Kmeans.scala:55:80") // FixMul(x4781,x4781)
    val x4783 = ReadMem(x4770_d1).name("x4783").ctrl(x4790).srcCtx("Kmeans.scala:55:86") // RegRead(x4770)
    val x4784 = OpDef(op=FixEql, inputs=List(b2973, Const(0))).name("x4784").ctrl(x4790).srcCtx("Kmeans.scala:55:86") // FixEql(b2973,Const(0))
    val x4785 = ReduceAccumOp(op=FixAdd, input=x4782, accum=x4783).name("x4785").ctrl(x4790).srcCtx("Kmeans.scala:55:88") // FixAdd(x4782,x4783)
    val x4786 = OpDef(op=BitAnd, inputs=List(b2969, b2964)).name("x4786").ctrl(x4790).srcCtx("UnrollingBase.scala:28:66") // And(b2969,b2964)
    val x4787 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x4787").ctrl(x4790).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x4788 = OpDef(op=BitAnd, inputs=List(x4786, x4787)).name("x4788").ctrl(x4790).srcCtx("UnrollingBase.scala:28:66") // And(x4786,x4787)
    val x4789_x4770_d0 = WriteMem(x4770_d0, x4785).name("x4789_x4770_d0").ctrl(x4790).srcCtx("Kmeans.scala:55:86") // RegWrite(x4770,x4785,x4788)
    val x4789_x4770_d1 = WriteMem(x4770_d1, x4785).name("x4789_x4770_d1").ctrl(x4790).srcCtx("Kmeans.scala:55:86") // RegWrite(x4770,x4785,x4788)
    val x4796 = UnitController(style=SeqPipe, level=InnerControl).name("x4796").ctrl(x4797).srcCtx("Kmeans.scala:54:37") // UnitPipe(List(b2969, b2964, b2920, b2911),Block(Const(())))
    val x4791 = ReadMem(x4770_d0).name("x4791").ctrl(x4796).srcCtx("Kmeans.scala:56:32") // RegRead(x4770)
    val x4792 = OpDef(op=BitAnd, inputs=List(b2969, b2964)).name("x4792").ctrl(x4796).srcCtx("UnrollingBase.scala:28:66") // And(b2969,b2964)
    val x4793 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x4793").ctrl(x4796).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x4794 = OpDef(op=BitAnd, inputs=List(x4792, x4793)).name("x4794").ctrl(x4796).srcCtx("UnrollingBase.scala:28:66") // And(x4792,x4793)
    val x4795 = StoreBanks(List(x4767_d0_b0, x4767_d1_b0), List(b2968), x4791).name("x4795").ctrl(x4796).srcCtx("Kmeans.scala:56:25") // SRAMStore(x4767,ArrayBuffer(Const(16)),List(b2968),Const(0),x4791,x4794)
    val x4798_d0 = Reg(init=Some(0)).name("x4798_d0").ctrl(x4864).srcCtx("Kmeans.scala:58:37:minDist") // x4798 = RegNew(Const(0))
    isAccum(x4798_d0) = false
    bufferDepthOf(x4798_d0) = 2
    val x4798_d1 = Reg(init=Some(0)).name("x4798_d1").ctrl(x4864).srcCtx("Kmeans.scala:58:37:minDist") // x4798 = RegNew(Const(0))
    isAccum(x4798_d1) = true
    bufferDepthOf(x4798_d1) = 1
    val x4799 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x4799").ctrl(x4864).srcCtx("Kmeans.scala:58:49") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x4800 = CounterChain(List(x4799)).name("x4800").ctrl(x4864).srcCtx("Kmeans.scala:58:77") // CounterChainNew(List(x4799))
    val x4812 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4800).name("x4812").ctrl(x4864).srcCtx("Kmeans.scala:58:77") // UnrolledReduce(List(b2964, b2920, b2911),x4800,x4798,Block((x4798) => Const(())),List(List(b3003)),List(List(b3004)))
    val b3003 = CounterIter(x4799, None).name("b3003").ctrl(x4812) // b3003
    val b3004 = Const(true).name("b3004").ctrl(x4812) // b3004
    val x4801 = OpDef(op=BitAnd, inputs=List(b3004, b2964)).name("x4801").ctrl(x4812).srcCtx("UnrollingBase.scala:28:66") // And(b3004,b2964)
    val x4802 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x4802").ctrl(x4812).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x4803 = OpDef(op=BitAnd, inputs=List(x4801, x4802)).name("x4803").ctrl(x4812).srcCtx("UnrollingBase.scala:28:66") // And(x4801,x4802)
    val x4804 = LoadBanks(List(x4767_d1_b0), List(b3003)).name("x4804").ctrl(x4812).srcCtx("Kmeans.scala:58:70") // ParSRAMLoad(x4767,List(List(b3003)),List(x4803))
    val x4805 = x4804 // x4805 = VectorApply(x4804,0)
    val x4806 = ReadMem(x4798_d1).name("x4806").ctrl(x4812).srcCtx("Kmeans.scala:58:77") // RegRead(x4798)
    val x4807 = OpDef(op=FixEql, inputs=List(b3003, Const(0))).name("x4807").ctrl(x4812).srcCtx("Kmeans.scala:58:77") // FixEql(b3003,Const(0))
    val x4808 = ReduceAccumOp(op=FixMin, input=x4805, accum=x4806).name("x4808").ctrl(x4812).srcCtx("Kmeans.scala:58:91") // Min(x4805,x4806)
    val x4809 = OpDef(op=BitAnd, inputs=List(b2964, b2920)).name("x4809").ctrl(x4812).srcCtx("UnrollingBase.scala:28:66") // And(b2964,b2920)
    val x4810 = OpDef(op=BitAnd, inputs=List(x4809, b2911)).name("x4810").ctrl(x4812).srcCtx("UnrollingBase.scala:28:66") // And(x4809,b2911)
    val x4811_x4798_d0 = WriteMem(x4798_d0, x4808).name("x4811_x4798_d0").ctrl(x4812).srcCtx("Kmeans.scala:58:77") // RegWrite(x4798,x4808,x4810)
    val x4811_x4798_d1 = WriteMem(x4798_d1, x4808).name("x4811_x4798_d1").ctrl(x4812).srcCtx("Kmeans.scala:58:77") // RegWrite(x4798,x4808,x4810)
    val x4813_d0 = Reg(init=Some(0)).name("x4813_d0").ctrl(x4864).srcCtx("Kmeans.scala:59:37:minCent") // x4813 = RegNew(Const(0))
    isAccum(x4813_d0) = false
    bufferDepthOf(x4813_d0) = 2
    val x4813_d1 = Reg(init=Some(0)).name("x4813_d1").ctrl(x4864).srcCtx("Kmeans.scala:59:37:minCent") // x4813 = RegNew(Const(0))
    isAccum(x4813_d1) = true
    bufferDepthOf(x4813_d1) = 1
    val x4814 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16).name("x4814").ctrl(x4864).srcCtx("Kmeans.scala:59:53") // CounterNew(Const(0),Const(16),Const(1),Const(16))
    val x4815 = CounterChain(List(x4814)).name("x4815").ctrl(x4864).srcCtx("Kmeans.scala:61:15") // CounterChainNew(List(x4814))
    val x4830 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4815).name("x4830").ctrl(x4864).srcCtx("Kmeans.scala:61:15") // UnrolledReduce(List(b2964, b2920, b2911),x4815,x4813,Block((x4813) => Const(())),List(List(b3020)),List(List(b3021)))
    val b3020 = CounterIter(x4814, None).name("b3020").ctrl(x4830) // b3020
    val b3021 = Const(true).name("b3021").ctrl(x4830) // b3021
    val x4816 = OpDef(op=BitAnd, inputs=List(b3021, b2964)).name("x4816").ctrl(x4830).srcCtx("UnrollingBase.scala:28:66") // And(b3021,b2964)
    val x4817 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x4817").ctrl(x4830).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x4818 = OpDef(op=BitAnd, inputs=List(x4816, x4817)).name("x4818").ctrl(x4830).srcCtx("UnrollingBase.scala:28:66") // And(x4816,x4817)
    val x4819 = LoadBanks(List(x4767_d0_b0), List(b3020)).name("x4819").ctrl(x4830).srcCtx("Kmeans.scala:60:24") // ParSRAMLoad(x4767,List(List(b3020)),List(x4818))
    val x4820 = x4819 // x4820 = VectorApply(x4819,0)
    val x4821 = ReadMem(x4798_d0).name("x4821").ctrl(x4830).srcCtx("Kmeans.scala:60:40") // RegRead(x4798)
    val x4822 = OpDef(op=FixEql, inputs=List(x4820, x4821)).name("x4822").ctrl(x4830).srcCtx("Kmeans.scala:60:29") // FixEql(x4820,x4821)
    val x4823 = OpDef(op=MuxOp, inputs=List(x4822, b3020, Const(-1))).name("x4823").ctrl(x4830).srcCtx("Kmeans.scala:60:18") // Mux(x4822,b3020,Const(-1))
    val x4824 = ReadMem(x4813_d1).name("x4824").ctrl(x4830).srcCtx("Kmeans.scala:61:15") // RegRead(x4813)
    val x4825 = OpDef(op=FixEql, inputs=List(b3020, Const(0))).name("x4825").ctrl(x4830).srcCtx("Kmeans.scala:61:15") // FixEql(b3020,Const(0))
    val x4826 = ReduceAccumOp(op=FixMax, input=x4823, accum=x4824).name("x4826").ctrl(x4830).srcCtx("Kmeans.scala:61:29") // Max(x4823,x4824)
    val x4827 = OpDef(op=BitAnd, inputs=List(b2964, b2920)).name("x4827").ctrl(x4830).srcCtx("UnrollingBase.scala:28:66") // And(b2964,b2920)
    val x4828 = OpDef(op=BitAnd, inputs=List(x4827, b2911)).name("x4828").ctrl(x4830).srcCtx("UnrollingBase.scala:28:66") // And(x4827,b2911)
    val x4829_x4813_d0 = WriteMem(x4813_d0, x4826).name("x4829_x4813_d0").ctrl(x4830).srcCtx("Kmeans.scala:61:15") // RegWrite(x4813,x4826,x4828)
    val x4829_x4813_d1 = WriteMem(x4813_d1, x4826).name("x4829_x4813_d1").ctrl(x4830).srcCtx("Kmeans.scala:61:15") // RegWrite(x4813,x4826,x4828)
    val x4831_d0_b0 = SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x4831_d0_b0").ctrl(x4864).srcCtx("Kmeans.scala:64:36:localCent") // x4831 = SRAMNew(ArrayBuffer(Const(16), Const(32)))
    isAccum(x4831_d0_b0) = false
    bufferDepthOf(x4831_d0_b0) = 2
    val x4832 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x4832").ctrl(x4864).srcCtx("Kmeans.scala:65:31") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x4833 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x4833").ctrl(x4864).srcCtx("Kmeans.scala:65:23") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x4834 = CounterChain(List(x4833,x4832)).name("x4834").ctrl(x4864).srcCtx("Kmeans.scala:65:38") // CounterChainNew(List(x4833, x4832))
    val x4845 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4834).name("x4845").ctrl(x4864).srcCtx("Kmeans.scala:65:38") // UnrolledForeach(List(b2964, b2920, b2911),x4834,Block(Const(())),List(List(b3041), List(b3042)),List(List(b3043), List(b3044)))
    val b3041 = CounterIter(x4833, Some(0)).name("b3041").ctrl(x4845) // b3041
    val b3043 = Const(true).name("b3043").ctrl(x4845) // b3043
    val b3042 = CounterIter(x4832, None).name("b3042").ctrl(x4845) // b3042
    val b3044 = Const(true).name("b3044").ctrl(x4845) // b3044
    val x4835 = ReadMem(x4813_d0).name("x4835").ctrl(x4845).srcCtx("Kmeans.scala:66:52") // RegRead(x4813)
    val x4836 = OpDef(op=FixEql, inputs=List(b3041, x4835)).name("x4836").ctrl(x4845).srcCtx("Kmeans.scala:66:41") // FixEql(b3041,x4835)
    val x4837 = OpDef(op=BitAnd, inputs=List(b3043, b3044)).name("x4837").ctrl(x4845).srcCtx("UnrollingBase.scala:28:66") // And(b3043,b3044)
    val x4838 = OpDef(op=BitAnd, inputs=List(b2964, b2920)).name("x4838").ctrl(x4845).srcCtx("UnrollingBase.scala:28:66") // And(b2964,b2920)
    val x4839 = OpDef(op=BitAnd, inputs=List(x4837, x4838)).name("x4839").ctrl(x4845).srcCtx("UnrollingBase.scala:28:66") // And(x4837,x4838)
    val x4840 = OpDef(op=BitAnd, inputs=List(x4839, b2911)).name("x4840").ctrl(x4845).srcCtx("UnrollingBase.scala:28:66") // And(x4839,b2911)
    val x4841 = LoadBanks(List(x4732_d0_b0), List(b2963, b3042)).name("x4841").ctrl(x4845).srcCtx("Kmeans.scala:66:62") // ParSRAMLoad(x4732,List(List(b2963, b3042)),List(x4840))
    val x4842 = x4841 // x4842 = VectorApply(x4841,0)
    val x4843 = OpDef(op=MuxOp, inputs=List(x4836, x4842, Const(0))).name("x4843").ctrl(x4845).srcCtx("Kmeans.scala:66:37") // Mux(x4836,x4842,Const(0))
    val x4844 = StoreBanks(List(x4831_d0_b0), List(b3041, b3042), x4843).name("x4844").ctrl(x4845).srcCtx("Kmeans.scala:66:32") // ParSRAMStore(x4831,List(List(b3041, b3042)),List(x4843),List(x4840))
    val x4846 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x4846").ctrl(x4864).srcCtx("Kmeans.scala:69:12") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x4847 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x4847").ctrl(x4864).srcCtx("Kmeans.scala:69:12") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x4848 = CounterChain(List(x4847,x4846)).name("x4848").ctrl(x4864).srcCtx("Kmeans.scala:69:12") // CounterChainNew(ArrayBuffer(x4847, x4846))
    val x4863 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4848).name("x4863").ctrl(x4864).srcCtx("Kmeans.scala:69:12") // UnrolledForeach(List(),x4848,Block(Const(())),ArrayBuffer(List(b3056), List(b3057)),ArrayBuffer(List(b3058), List(b3059)))
    val b3056 = CounterIter(x4847, Some(0)).name("b3056").ctrl(x4863) // b3056
    val b3058 = Const(true).name("b3058").ctrl(x4863) // b3058
    val b3057 = CounterIter(x4846, None).name("b3057").ctrl(x4863) // b3057
    val b3059 = Const(true).name("b3059").ctrl(x4863) // b3059
    val x4849 = OpDef(op=BitAnd, inputs=List(b3058, b3059)).name("x4849").ctrl(x4863).srcCtx("UnrollingBase.scala:28:66") // And(b3058,b3059)
    val x4850 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x4850").ctrl(x4863).srcCtx("UnrollingBase.scala:28:66") // And(b2920,b2911)
    val x4851 = OpDef(op=BitAnd, inputs=List(x4849, x4850)).name("x4851").ctrl(x4863).srcCtx("UnrollingBase.scala:28:66") // And(x4849,x4850)
    val x4852 = LoadBanks(List(x4831_d0_b0), List(b3056, b3057)).name("x4852").ctrl(x4863).srcCtx("Kmeans.scala:69:12") // ParSRAMLoad(x4831,List(ArrayBuffer(b3056, b3057)),List(x4851))
    val x4853 = x4852 // x4853 = VectorApply(x4852,0)
    val x4854 = LoadBanks(List(x4764_d1_b0), List(b3056, b3057)).name("x4854").ctrl(x4863).srcCtx("Kmeans.scala:69:12") // ParSRAMLoad(x4764,List(ArrayBuffer(b3056, b3057)),List(x4851))
    val x4855 = x4854 // x4855 = VectorApply(x4854,0)
    val x4856 = OpDef(op=BitAnd, inputs=List(b2964, b2920)).name("x4856").ctrl(x4863).srcCtx("Kmeans.scala:69:12") // And(b2964,b2920)
    val x4857 = OpDef(op=BitAnd, inputs=List(x4856, b2911)).name("x4857").ctrl(x4863).srcCtx("Kmeans.scala:69:12") // And(x4856,b2911)
    val x4858 = OpDef(op=BitAnd, inputs=List(x4857, x4851)).name("x4858").ctrl(x4863).srcCtx("Kmeans.scala:69:12") // And(x4857,x4851)
    val x4859 = OpDef(op=FixEql, inputs=List(b2963, Const(0))).name("x4859").ctrl(x4863).srcCtx("Kmeans.scala:69:12") // FixEql(b2963,Const(0))
    val x4860 = OpDef(op=FixAdd, inputs=List(x4853, x4855)).name("x4860").ctrl(x4863).srcCtx("Kmeans.scala:69:14") // FixAdd(x4853,x4855)
    val x4861 = OpDef(op=MuxOp, inputs=List(x4859, x4853, x4860)).name("x4861").ctrl(x4863).srcCtx("Kmeans.scala:69:12") // Mux(x4859,x4853,x4860)
    val x4862 = StoreBanks(List(x4764_d0_b0, x4764_d1_b0), List(b3056, b3057), x4861).name("x4862").ctrl(x4863).srcCtx("Kmeans.scala:69:12") // ParSRAMStore(x4764,List(ArrayBuffer(b3056, b3057)),List(x4861),List(x4851))
    val x4865 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x4865").ctrl(x4881).srcCtx("Kmeans.scala:70:10") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x4866 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x4866").ctrl(x4881).srcCtx("Kmeans.scala:70:10") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x4867 = CounterChain(List(x4866,x4865)).name("x4867").ctrl(x4881).srcCtx("Kmeans.scala:70:10") // CounterChainNew(ArrayBuffer(x4866, x4865))
    val x4880 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4867).name("x4880").ctrl(x4881).srcCtx("Kmeans.scala:70:10") // UnrolledForeach(List(),x4867,Block(Const(())),ArrayBuffer(List(b3076), List(b3077)),ArrayBuffer(List(b3078), List(b3079)))
    val b3076 = CounterIter(x4866, Some(0)).name("b3076").ctrl(x4880) // b3076
    val b3078 = Const(true).name("b3078").ctrl(x4880) // b3078
    val b3077 = CounterIter(x4865, None).name("b3077").ctrl(x4880) // b3077
    val b3079 = Const(true).name("b3079").ctrl(x4880) // b3079
    val x4868 = OpDef(op=BitAnd, inputs=List(b3078, b3079)).name("x4868").ctrl(x4880).srcCtx("UnrollingBase.scala:28:66") // And(b3078,b3079)
    val x4869 = OpDef(op=BitAnd, inputs=List(x4868, b2911)).name("x4869").ctrl(x4880).srcCtx("UnrollingBase.scala:28:66") // And(x4868,b2911)
    val x4870 = LoadBanks(List(x4764_d0_b0), List(b3076, b3077)).name("x4870").ctrl(x4880).srcCtx("Kmeans.scala:70:10") // ParSRAMLoad(x4764,List(ArrayBuffer(b3076, b3077)),List(x4869))
    val x4871 = x4870 // x4871 = VectorApply(x4870,0)
    val x4872 = LoadBanks(List(x4728_d2_b0), List(b3076, b3077)).name("x4872").ctrl(x4880).srcCtx("Kmeans.scala:70:10") // ParSRAMLoad(x4728,List(ArrayBuffer(b3076, b3077)),List(x4869))
    val x4873 = x4872 // x4873 = VectorApply(x4872,0)
    val x4874 = OpDef(op=BitAnd, inputs=List(b2920, b2911)).name("x4874").ctrl(x4880).srcCtx("Kmeans.scala:70:10") // And(b2920,b2911)
    val x4875 = OpDef(op=BitAnd, inputs=List(x4874, x4869)).name("x4875").ctrl(x4880).srcCtx("Kmeans.scala:70:10") // And(x4874,x4869)
    val x4876 = OpDef(op=FixEql, inputs=List(b2919, Const(0))).name("x4876").ctrl(x4880).srcCtx("Kmeans.scala:70:10") // FixEql(b2919,Const(0))
    val x4877 = OpDef(op=FixAdd, inputs=List(x4871, x4873)).name("x4877").ctrl(x4880).srcCtx("Kmeans.scala:70:12") // FixAdd(x4871,x4873)
    val x4878 = OpDef(op=MuxOp, inputs=List(x4876, x4871, x4877)).name("x4878").ctrl(x4880).srcCtx("Kmeans.scala:70:10") // Mux(x4876,x4871,x4877)
    val x4879 = StoreBanks(List(x4728_d0_b0, x4728_d1_b0, x4728_d2_b0), List(b3076, b3077), x4878).name("x4879").ctrl(x4880).srcCtx("Kmeans.scala:70:10") // ParSRAMStore(x4728,List(ArrayBuffer(b3076, b3077)),List(x4878),List(x4869))
    val x4882 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x4882").ctrl(x4903).srcCtx("Kmeans.scala:73:24") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x4883 = CounterChain(List(x4882)).name("x4883").ctrl(x4903).srcCtx("Kmeans.scala:73:32") // CounterChainNew(List(x4882))
    val x4902 = LoopController(style=MetaPipe, level=OuterControl, cchain=x4883).name("x4902").ctrl(x4903).srcCtx("Kmeans.scala:73:32") // UnrolledForeach(List(b2911),x4883,Block(Const(())),List(List(b3096)),List(List(b3097)))
    val b3096 = CounterIter(x4882, Some(0)).name("b3096").ctrl(x4902) // b3096
    val b3097 = Const(true).name("b3097").ctrl(x4902) // b3097
    val x4884 = Reg(init=Some(0)).name("x4884").ctrl(x4902).srcCtx("Kmeans.scala:74:33:centCount") // x4884 = RegNew(Const(0))
    isAccum(x4884) = false
    bufferDepthOf(x4884) = 2
    val x4889 = UnitController(style=SeqPipe, level=InnerControl).name("x4889").ctrl(x4902).srcCtx("Kmeans.scala:75:16") // UnitPipe(List(b3097, b2911),Block(x4888))
    val x4885 = OpDef(op=BitAnd, inputs=List(b3097, b2911)).name("x4885").ctrl(x4889).srcCtx("UnrollingBase.scala:28:66") // And(b3097,b2911)
    val x4886 = LoadBanks(List(x4728_d1_b0), List(b3096, Const(31))).name("x4886").ctrl(x4889).srcCtx("Kmeans.scala:76:38") // SRAMLoad(x4728,ArrayBuffer(Const(16), Const(32)),List(b3096, Const(31)),Const(0),x4885)
    val x4887 = OpDef(op=FixMax, inputs=List(x4886, Const(1))).name("x4887").ctrl(x4889).srcCtx("Kmeans.scala:76:29") // Max(x4886,Const(1))
    val x4888_x4884 = WriteMem(x4884, x4887).name("x4888_x4884").ctrl(x4889).srcCtx("Kmeans.scala:76:23") // RegWrite(x4884,x4887,x4885)
    val x4890 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x4890").ctrl(x4902).srcCtx("Kmeans.scala:78:21") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x4891 = CounterChain(List(x4890)).name("x4891").ctrl(x4902).srcCtx("Kmeans.scala:78:28") // CounterChainNew(List(x4890))
    val x4901 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4891).name("x4901").ctrl(x4902).srcCtx("Kmeans.scala:78:28") // UnrolledForeach(List(b3097, b2911),x4891,Block(Const(())),List(List(b3106)),List(List(b3107)))
    val b3106 = CounterIter(x4890, None).name("b3106").ctrl(x4901) // b3106
    val b3107 = Const(true).name("b3107").ctrl(x4901) // b3107
    val x4892 = ReadMem(x4884).name("x4892").ctrl(x4901).srcCtx("Kmeans.scala:79:40") // RegRead(x4884)
    val x4893 = OpDef(op=FixEql, inputs=List(x4892, Const(0))).name("x4893").ctrl(x4901).srcCtx("Kmeans.scala:79:40") // FixEql(x4892,Const(0))
    val x4894 = OpDef(op=BitAnd, inputs=List(b3107, b3097)).name("x4894").ctrl(x4901).srcCtx("UnrollingBase.scala:28:66") // And(b3107,b3097)
    val x4895 = OpDef(op=BitAnd, inputs=List(x4894, b2911)).name("x4895").ctrl(x4901).srcCtx("UnrollingBase.scala:28:66") // And(x4894,b2911)
    val x4896 = LoadBanks(List(x4728_d0_b0), List(b3096, b3106)).name("x4896").ctrl(x4901).srcCtx("Kmeans.scala:79:69") // ParSRAMLoad(x4728,List(List(b3096, b3106)),List(x4895))
    val x4897 = x4896 // x4897 = VectorApply(x4896,0)
    val x4898 = OpDef(op=FixDiv, inputs=List(x4897, x4892)).name("x4898").ctrl(x4901).srcCtx("Kmeans.scala:79:76") // FixDiv(x4897,x4892)
    val x4899 = OpDef(op=MuxOp, inputs=List(x4893, Const(0), x4898)).name("x4899").ctrl(x4901).srcCtx("Kmeans.scala:79:29") // Mux(x4893,Const(0),x4898)
    val x4900 = StoreBanks(List(x4700_d0_b0, x4700_d1_b0), List(b3096, b3106), x4899).name("x4900").ctrl(x4901).srcCtx("Kmeans.scala:79:24") // ParSRAMStore(x4700,List(List(b3096, b3106)),List(x4899),List(x4895))
    val x4904 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x4904").ctrl(x4932).srcCtx("Kmeans.scala:85:33") // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x4905 = CounterChain(List(x4904)).name("x4905").ctrl(x4932).srcCtx("Kmeans.scala:85:33") // CounterChainNew(List(x4904))
    val x4931 = LoopController(style=StreamPipe, level=OuterControl, cchain=x4905).name("x4931").ctrl(x4932).srcCtx("Kmeans.scala:85:33") // UnrolledForeach(List(Const(true)),x4905,Block(Const(())),List(List(b3122)),List(List(b3123)))
    val b3122 = CounterIter(x4904, Some(0)).name("b3122").ctrl(x4931) // b3122
    val b3123 = Const(true).name("b3123").ctrl(x4931) // b3123
    val b5044 = StreamOut(field="offset").name("b5044").ctrl(x4931).srcCtx("Kmeans.scala:85:33") // x4906 = StreamOutNew(BurstCmdBus)
    isAccum(b5044) = false
    bufferDepthOf(b5044) = 1
    val b5045 = StreamOut(field="size").name("b5045").ctrl(x4931).srcCtx("Kmeans.scala:85:33") // x4906 = StreamOutNew(BurstCmdBus)
    isAccum(b5045) = false
    bufferDepthOf(b5045) = 1
    val x4907 = StreamOut(field="data").name("x4907").ctrl(x4931).srcCtx("Kmeans.scala:85:33") // x4907 = StreamOutNew(BurstFullDataBus())
    isAccum(x4907) = false
    bufferDepthOf(x4907) = 1
    val x4908 = StreamIn(field="ack").name("x4908").ctrl(x4931).srcCtx("Kmeans.scala:85:33") // x4908 = StreamInNew(BurstAckBus)
    isAccum(x4908) = false
    bufferDepthOf(x4908) = 1
    val x4919 = UnitController(style=SeqPipe, level=InnerControl).name("x4919").ctrl(x4931).srcCtx("Kmeans.scala:85:33") // UnitPipe(List(b3123),Block(x4918))
    val x4909 = b3122 // FixConvert(b3122,TRUE,_32,_0) (Same Type. No op)
    val x4910 = OpDef(op=FixSla, inputs=List(x4909, Const(5))).name("x4910").ctrl(x4919).srcCtx("Kmeans.scala:85:33") // FixLsh(x4909,Const(5))
    val x4911 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x4912 = OpDef(op=FixAdd, inputs=List(x4910, x4911)).name("x4912").ctrl(x4919).srcCtx("Kmeans.scala:85:33") // FixAdd(x4910,x4911)
    val x4913 = OpDef(op=FixSla, inputs=List(x4912, Const(2))).name("x4913").ctrl(x4919).srcCtx("Kmeans.scala:85:33") // FixLsh(x4912,Const(2))
    val x4914 = x4913 // FixConvert(x4913,TRUE,_64,_0)
    val x4915 = DramAddress(x4696).name("x4915").ctrl(x4919).srcCtx("Kmeans.scala:85:33") // GetDRAMAddress(x4696)
    val x4917_x4916 = OpDef(op=FixAdd, inputs=List(x4914, x4915)).name("x4917_x4916").ctrl(x4919).srcCtx("Kmeans.scala:85:33") // FixAdd(x4914,x4915)
    // x4917 = SimpleStruct(ArrayBuffer((offset,x4916), (size,Const(128)), (isLoad,Const(false))))
    val x4918_b5046_b5044 = WriteMem(b5044, x4917_x4916).name("x4918_b5046_b5044").ctrl(x4919).srcCtx("Kmeans.scala:85:33") // StreamWrite(x4906,x4917,b3123)
    val x4918_b5047_b5045 = WriteMem(b5045, Const(128)).name("x4918_b5047_b5045").ctrl(x4919).srcCtx("Kmeans.scala:85:33") // StreamWrite(x4906,x4917,b3123)
    val x4920 = Counter(min=Const(0), max=Const(32), step=Const(1), par=16).name("x4920").ctrl(x4931).srcCtx("Kmeans.scala:85:33") // CounterNew(Const(0),Const(32),Const(1),Const(16))
    val x4921 = CounterChain(List(x4920)).name("x4921").ctrl(x4931).srcCtx("Kmeans.scala:85:33") // CounterChainNew(List(x4920))
    val x4927 = LoopController(style=InnerPipe, level=InnerControl, cchain=x4921).name("x4927").ctrl(x4931).srcCtx("Kmeans.scala:85:33") // UnrolledForeach(List(b3123),x4921,Block(Const(())),List(List(b3140)),List(List(b3141)))
    val b3140 = CounterIter(x4920, None).name("b3140").ctrl(x4927) // b3140
    val b3141 = Const(true).name("b3141").ctrl(x4927) // b3141
    val x4922 = OpDef(op=BitAnd, inputs=List(b3141, b3123)).name("x4922").ctrl(x4927).srcCtx("UnrollingBase.scala:28:66") // And(b3141,b3123)
    val x4923 = LoadBanks(List(x4700_d0_b0), List(b3122, b3140)).name("x4923").ctrl(x4927).srcCtx("Kmeans.scala:85:33") // ParSRAMLoad(x4700,List(List(b3122, b3140)),List(x4922))
    val x4925_x4924 = x4923 // x4924 = VectorApply(x4923,0)
    // x4925 = SimpleStruct(ArrayBuffer((_1,x4924), (_2,Const(true))))
    val x4926_x4926_x4907 = WriteMem(x4907, x4925_x4924).name("x4926_x4926_x4907").ctrl(x4927).srcCtx("Kmeans.scala:85:33") // ParStreamWrite(x4907,List(x4925),List(x4922))
    val x4928 = FringeDenseStore(dram=List(x4696), cmdStream=List(b5044, b5045), dataStream=List(x4907), ackStream=List(x4908)).name("x4928").ctrl(x4931).srcCtx("Kmeans.scala:85:33") // FringeDenseStore(x4696,x4906,x4907,x4908)
    val x4930 = UnitController(style=SeqPipe, level=InnerControl).name("x4930").ctrl(x4931).srcCtx("Kmeans.scala:85:33") // UnitPipe(List(b3123),Block(Const(())))
    val x4929_x4929 = ReadMem(x4908).name("x4929_x4929").ctrl(x4930).srcCtx("Kmeans.scala:85:33") // StreamRead(x4908,b3123)
    
  }
}
