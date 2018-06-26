import pir._
import pir.node._
import arch._
import prism.enums._

object CharRNNStandard_Zynq extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x9378 = DRAM().name("x9378").ctrl(top).srcCtx("CharRNN.scala:30:25:i2h_1w") // x9378 = DRAMNew(ArrayBuffer(Const(64), Const(512)),Const(0))
    val x9379 = DRAM().name("x9379").ctrl(top).srcCtx("CharRNN.scala:31:25:i2h_1b") // x9379 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x9380 = DRAM().name("x9380").ctrl(top).srcCtx("CharRNN.scala:32:25:i2h_2w") // x9380 = DRAMNew(ArrayBuffer(Const(128), Const(512)),Const(0))
    val x9381 = DRAM().name("x9381").ctrl(top).srcCtx("CharRNN.scala:33:25:i2h_2b") // x9381 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x9382 = DRAM().name("x9382").ctrl(top).srcCtx("CharRNN.scala:34:25:h2h_1w") // x9382 = DRAMNew(ArrayBuffer(Const(128), Const(512)),Const(0))
    val x9383 = DRAM().name("x9383").ctrl(top).srcCtx("CharRNN.scala:35:25:h2h_1b") // x9383 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x9384 = DRAM().name("x9384").ctrl(top).srcCtx("CharRNN.scala:36:25:h2h_2w") // x9384 = DRAMNew(ArrayBuffer(Const(128), Const(512)),Const(0))
    val x9385 = DRAM().name("x9385").ctrl(top).srcCtx("CharRNN.scala:37:25:h2h_2b") // x9385 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x9386 = DRAM().name("x9386").ctrl(top).srcCtx("CharRNN.scala:38:29:decoder_w_") // x9386 = DRAMNew(ArrayBuffer(Const(128), Const(64)),Const(0))
    val x9387 = DRAM().name("x9387").ctrl(top).srcCtx("CharRNN.scala:39:29:decoder_b_") // x9387 = DRAMNew(ArrayBuffer(Const(64)),Const(0))
    val x9388 = DRAM().name("x9388").ctrl(top).srcCtx("CharRNN.scala:40:21:c_") // x9388 = DRAMNew(ArrayBuffer(Const(1), Const(128)),Const(0))
    val x9389 = DRAM().name("x9389").ctrl(top).srcCtx("CharRNN.scala:41:21:h_") // x9389 = DRAMNew(ArrayBuffer(Const(1), Const(128)),Const(0))
    val x9390 = DRAM().name("x9390").ctrl(top).srcCtx("CharRNN.scala:42:25:inputs") // x9390 = DRAMNew(ArrayBuffer(Const(3), Const(1), Const(64)),Const(0))
    val x9490 = DRAM().name("x9490").ctrl(top).srcCtx("CharRNN.scala:53:26:results") // x9490 = DRAMNew(ArrayBuffer(Const(3), Const(64), Const(1)),Const(0))
    val x10147 = UnitController(style=SeqPipe, level=OuterControl).name("x10147").ctrl(top).srcCtx("CharRNN.scala:55:11") // Hwblock(Block(Const(())),false)
    val x9491_d0_b0 = SRAM(size=32768, banking=Strided(banks=1, stride=512)).name("x9491_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:56:26:i2h1w") // x9491 = SRAMNew(ArrayBuffer(Const(64), Const(512)))
    isAccum(x9491_d0_b0) = false
    bufferDepthOf(x9491_d0_b0) = 1
    val x9492_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9492_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:57:26:i2h1b") // x9492 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9492_d0_b0) = false
    bufferDepthOf(x9492_d0_b0) = 1
    val x9493_d0_b0 = SRAM(size=65536, banking=Strided(banks=1, stride=512)).name("x9493_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:58:26:h2h1w") // x9493 = SRAMNew(ArrayBuffer(Const(128), Const(512)))
    isAccum(x9493_d0_b0) = false
    bufferDepthOf(x9493_d0_b0) = 1
    val x9494_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9494_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:59:26:h2h1b") // x9494 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9494_d0_b0) = false
    bufferDepthOf(x9494_d0_b0) = 1
    val x9495_d0_b0 = SRAM(size=65536, banking=Strided(banks=1, stride=512)).name("x9495_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:60:26:i2h2w") // x9495 = SRAMNew(ArrayBuffer(Const(128), Const(512)))
    isAccum(x9495_d0_b0) = false
    bufferDepthOf(x9495_d0_b0) = 1
    val x9496_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9496_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:61:26:i2h2b") // x9496 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9496_d0_b0) = false
    bufferDepthOf(x9496_d0_b0) = 1
    val x9497_d0_b0 = SRAM(size=65536, banking=Strided(banks=1, stride=512)).name("x9497_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:62:26:h2h2w") // x9497 = SRAMNew(ArrayBuffer(Const(128), Const(512)))
    isAccum(x9497_d0_b0) = false
    bufferDepthOf(x9497_d0_b0) = 1
    val x9498_d0_b0 = SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9498_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:63:26:h2h2b") // x9498 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9498_d0_b0) = false
    bufferDepthOf(x9498_d0_b0) = 1
    val x9499_d0_b0 = SRAM(size=8192, banking=Strided(banks=1, stride=64)).name("x9499_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:64:30:decoder_w") // x9499 = SRAMNew(ArrayBuffer(Const(128), Const(64)))
    isAccum(x9499_d0_b0) = false
    bufferDepthOf(x9499_d0_b0) = 1
    val x9500_d0_b0 = SRAM(size=64, banking=Strided(banks=1, stride=1)).name("x9500_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:65:30:decoder_b") // x9500 = SRAMNew(ArrayBuffer(Const(64)))
    isAccum(x9500_d0_b0) = false
    bufferDepthOf(x9500_d0_b0) = 1
    val x9501_d0_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9501_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:66:22:c") // x9501 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9501_d0_b0) = true
    bufferDepthOf(x9501_d0_b0) = 1
    val x9501_d1_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9501_d1_b0").ctrl(x10147).srcCtx("CharRNN.scala:66:22:c") // x9501 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9501_d1_b0) = true
    bufferDepthOf(x9501_d1_b0) = 1
    val x9502_d0_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9502_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:67:22:h") // x9502 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9502_d0_b0) = false
    bufferDepthOf(x9502_d0_b0) = 1
    val x9502_d1_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9502_d1_b0").ctrl(x10147).srcCtx("CharRNN.scala:67:22:h") // x9502 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9502_d1_b0) = false
    bufferDepthOf(x9502_d1_b0) = 2
    val x9502_d2_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9502_d2_b0").ctrl(x10147).srcCtx("CharRNN.scala:67:22:h") // x9502 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9502_d2_b0) = false
    bufferDepthOf(x9502_d2_b0) = 1
    val x9502_d3_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9502_d3_b0").ctrl(x10147).srcCtx("CharRNN.scala:67:22:h") // x9502 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9502_d3_b0) = false
    bufferDepthOf(x9502_d3_b0) = 1
    val x9503_d0_b0 = SRAM(size=64, banking=Strided(banks=1, stride=64)).name("x9503_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:68:22:x") // x9503 = SRAMNew(ArrayBuffer(Const(1), Const(64)))
    isAccum(x9503_d0_b0) = false
    bufferDepthOf(x9503_d0_b0) = 1
    val x9503_d1_b0 = SRAM(size=64, banking=Strided(banks=1, stride=64)).name("x9503_d1_b0").ctrl(x10147).srcCtx("CharRNN.scala:68:22:x") // x9503 = SRAMNew(ArrayBuffer(Const(1), Const(64)))
    isAccum(x9503_d1_b0) = false
    bufferDepthOf(x9503_d1_b0) = 1
    val x9504_d0_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9504_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:69:28:in_gate") // x9504 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9504_d0_b0) = false
    bufferDepthOf(x9504_d0_b0) = 1
    val x9504_d1_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9504_d1_b0").ctrl(x10147).srcCtx("CharRNN.scala:69:28:in_gate") // x9504 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9504_d1_b0) = false
    bufferDepthOf(x9504_d1_b0) = 1
    val x9505_d0_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9505_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:70:32:forget_gate") // x9505 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9505_d0_b0) = false
    bufferDepthOf(x9505_d0_b0) = 1
    val x9505_d1_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9505_d1_b0").ctrl(x10147).srcCtx("CharRNN.scala:70:32:forget_gate") // x9505 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9505_d1_b0) = false
    bufferDepthOf(x9505_d1_b0) = 1
    val x9506_d0_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9506_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:71:29:out_gate") // x9506 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9506_d0_b0) = false
    bufferDepthOf(x9506_d0_b0) = 1
    val x9506_d1_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9506_d1_b0").ctrl(x10147).srcCtx("CharRNN.scala:71:29:out_gate") // x9506 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9506_d1_b0) = false
    bufferDepthOf(x9506_d1_b0) = 1
    val x9507_d0_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9507_d0_b0").ctrl(x10147).srcCtx("CharRNN.scala:72:33:in_transform") // x9507 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9507_d0_b0) = false
    bufferDepthOf(x9507_d0_b0) = 1
    val x9507_d1_b0 = SRAM(size=128, banking=Strided(banks=1, stride=128)).name("x9507_d1_b0").ctrl(x10147).srcCtx("CharRNN.scala:72:33:in_transform") // x9507 = SRAMNew(ArrayBuffer(Const(1), Const(128)))
    isAccum(x9507_d1_b0) = false
    bufferDepthOf(x9507_d1_b0) = 1
    val x9508 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x9508").ctrl(x10147).srcCtx("CharRNN.scala:74:13") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x9509 = CounterChain(List(x9508)).name("x9509").ctrl(x10147).srcCtx("CharRNN.scala:74:13") // CounterChainNew(List(x9508))
    val x9531 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9509).name("x9531").ctrl(x10147).srcCtx("CharRNN.scala:74:13") // UnrolledForeach(List(Const(true)),x9509,Block(Const(())),List(List(b5538)),List(List(b5539)))
    val b5538 = CounterIter(x9508, Some(0)).name("b5538").ctrl(x9531) // b5538
    val b5539 = Const(true).name("b5539").ctrl(x9531) // b5539
    val b10156 = StreamOut(field="offset").name("b10156").ctrl(x9531).srcCtx("CharRNN.scala:74:13") // x9510 = StreamOutNew(BurstCmdBus)
    isAccum(b10156) = false
    bufferDepthOf(b10156) = 1
    val b10157 = StreamOut(field="size").name("b10157").ctrl(x9531).srcCtx("CharRNN.scala:74:13") // x9510 = StreamOutNew(BurstCmdBus)
    isAccum(b10157) = false
    bufferDepthOf(b10157) = 1
    val x9511 = StreamIn(field="data").name("x9511").ctrl(x9531).srcCtx("CharRNN.scala:74:13") // x9511 = StreamInNew(BurstDataBus())
    isAccum(x9511) = false
    bufferDepthOf(x9511) = 1
    val x9522 = UnitController(style=SeqPipe, level=InnerControl).name("x9522").ctrl(x9531).srcCtx("CharRNN.scala:74:13") // UnitPipe(List(b5539),Block(x9521))
    val x9512 = b5538 // FixConvert(b5538,TRUE,_32,_0) (Same Type. No op)
    val x9513 = OpDef(op=FixSla, inputs=List(x9512, Const(9))).name("x9513").ctrl(x9522).srcCtx("CharRNN.scala:74:13") // FixLsh(x9512,Const(9))
    val x9514 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9515 = OpDef(op=FixAdd, inputs=List(x9513, x9514)).name("x9515").ctrl(x9522).srcCtx("CharRNN.scala:74:13") // FixAdd(x9513,x9514)
    val x9516 = OpDef(op=FixSla, inputs=List(x9515, Const(2))).name("x9516").ctrl(x9522).srcCtx("CharRNN.scala:74:13") // FixLsh(x9515,Const(2))
    val x9517 = x9516 // FixConvert(x9516,TRUE,_64,_0)
    val x9518 = DramAddress(x9378).name("x9518").ctrl(x9522).srcCtx("CharRNN.scala:74:13") // GetDRAMAddress(x9378)
    val x9520_x9519 = OpDef(op=FixAdd, inputs=List(x9517, x9518)).name("x9520_x9519").ctrl(x9522).srcCtx("CharRNN.scala:74:13") // FixAdd(x9517,x9518)
    // x9520 = SimpleStruct(ArrayBuffer((offset,x9519), (size,Const(2048)), (isLoad,Const(true))))
    val x9521_b10158_b10156 = WriteMem(b10156, x9520_x9519).name("x9521_b10158_b10156").ctrl(x9522).srcCtx("CharRNN.scala:74:13") // StreamWrite(x9510,x9520,b5539)
    val x9521_b10159_b10157 = WriteMem(b10157, Const(2048)).name("x9521_b10159_b10157").ctrl(x9522).srcCtx("CharRNN.scala:74:13") // StreamWrite(x9510,x9520,b5539)
    val x9523 = FringeDenseLoad(dram=List(x9378), cmdStream=List(b10156, b10157), dataStream=List(x9511)).name("x9523").ctrl(x9531).srcCtx("CharRNN.scala:74:13") // FringeDenseLoad(x9378,x9510,x9511)
    val x9524 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9524").ctrl(x9531).srcCtx("CharRNN.scala:74:13") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9525 = CounterChain(List(x9524)).name("x9525").ctrl(x9531).srcCtx("CharRNN.scala:74:13") // CounterChainNew(List(x9524))
    val x9530 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9525).name("x9530").ctrl(x9531).srcCtx("CharRNN.scala:74:13") // UnrolledForeach(List(b5539),x9525,Block(Const(())),List(List(b5556)),List(List(b5557)))
    val b5556 = CounterIter(x9524, None).name("b5556").ctrl(x9530) // b5556
    val b5557 = Const(true).name("b5557").ctrl(x9530) // b5557
    val x9526 = OpDef(op=BitAnd, inputs=List(b5557, b5539)).name("x9526").ctrl(x9530).srcCtx("UnrollingBase.scala:28:66") // And(b5557,b5539)
    val x9527_x9527 = ReadMem(x9511).name("x9527_x9527").ctrl(x9530).srcCtx("CharRNN.scala:74:13") // ParStreamRead(x9511,List(x9526))
    val x9528_x9528 = x9527_x9527 // x9528 = VectorApply(x9527,0)
    val x9529 = StoreBanks(List(x9491_d0_b0), List(b5538, b5556), x9528_x9528).name("x9529").ctrl(x9530).srcCtx("CharRNN.scala:74:13") // ParSRAMStore(x9491,List(List(b5538, b5556)),List(x9528),List(x9526))
    val x9549 = UnitController(style=StreamPipe, level=OuterControl).name("x9549").ctrl(x10147).srcCtx("CharRNN.scala:75:13") // UnitPipe(List(Const(true)),Block(Const(())))
    val b10160 = StreamOut(field="offset").name("b10160").ctrl(x9549).srcCtx("CharRNN.scala:75:13") // x9532 = StreamOutNew(BurstCmdBus)
    isAccum(b10160) = false
    bufferDepthOf(b10160) = 1
    val b10161 = StreamOut(field="size").name("b10161").ctrl(x9549).srcCtx("CharRNN.scala:75:13") // x9532 = StreamOutNew(BurstCmdBus)
    isAccum(b10161) = false
    bufferDepthOf(b10161) = 1
    val x9533 = StreamIn(field="data").name("x9533").ctrl(x9549).srcCtx("CharRNN.scala:75:13") // x9533 = StreamInNew(BurstDataBus())
    isAccum(x9533) = false
    bufferDepthOf(x9533) = 1
    val x9541 = UnitController(style=SeqPipe, level=InnerControl).name("x9541").ctrl(x9549).srcCtx("CharRNN.scala:75:13") // UnitPipe(List(Const(true)),Block(x9540))
    val x9534 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9535 = OpDef(op=FixSla, inputs=List(x9534, Const(2))).name("x9535").ctrl(x9541).srcCtx("CharRNN.scala:75:13") // FixLsh(x9534,Const(2))
    val x9536 = x9535 // FixConvert(x9535,TRUE,_64,_0)
    val x9537 = DramAddress(x9379).name("x9537").ctrl(x9541).srcCtx("CharRNN.scala:75:13") // GetDRAMAddress(x9379)
    val x9539_x9538 = OpDef(op=FixAdd, inputs=List(x9536, x9537)).name("x9539_x9538").ctrl(x9541).srcCtx("CharRNN.scala:75:13") // FixAdd(x9536,x9537)
    // x9539 = SimpleStruct(ArrayBuffer((offset,x9538), (size,Const(2048)), (isLoad,Const(true))))
    val x9540_b10162_b10160 = WriteMem(b10160, x9539_x9538).name("x9540_b10162_b10160").ctrl(x9541).srcCtx("CharRNN.scala:75:13") // StreamWrite(x9532,x9539,Const(true))
    val x9540_b10163_b10161 = WriteMem(b10161, Const(2048)).name("x9540_b10163_b10161").ctrl(x9541).srcCtx("CharRNN.scala:75:13") // StreamWrite(x9532,x9539,Const(true))
    val x9542 = FringeDenseLoad(dram=List(x9379), cmdStream=List(b10160, b10161), dataStream=List(x9533)).name("x9542").ctrl(x9549).srcCtx("CharRNN.scala:75:13") // FringeDenseLoad(x9379,x9532,x9533)
    val x9543 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9543").ctrl(x9549).srcCtx("CharRNN.scala:75:13") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9544 = CounterChain(List(x9543)).name("x9544").ctrl(x9549).srcCtx("CharRNN.scala:75:13") // CounterChainNew(List(x9543))
    val x9548 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9544).name("x9548").ctrl(x9549).srcCtx("CharRNN.scala:75:13") // UnrolledForeach(List(Const(true)),x9544,Block(Const(())),List(List(b5577)),List(List(b5578)))
    val b5577 = CounterIter(x9543, None).name("b5577").ctrl(x9548) // b5577
    val b5578 = Const(true).name("b5578").ctrl(x9548) // b5578
    val x9545_x9545 = ReadMem(x9533).name("x9545_x9545").ctrl(x9548).srcCtx("CharRNN.scala:75:13") // ParStreamRead(x9533,List(b5578))
    val x9546_x9546 = x9545_x9545 // x9546 = VectorApply(x9545,0)
    val x9547 = StoreBanks(List(x9492_d0_b0), List(b5577), x9546_x9546).name("x9547").ctrl(x9548).srcCtx("CharRNN.scala:75:13") // ParSRAMStore(x9492,List(List(b5577)),List(x9546),List(b5578))
    val x9550 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x9550").ctrl(x10147).srcCtx("CharRNN.scala:76:13") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x9551 = CounterChain(List(x9550)).name("x9551").ctrl(x10147).srcCtx("CharRNN.scala:76:13") // CounterChainNew(List(x9550))
    val x9573 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9551).name("x9573").ctrl(x10147).srcCtx("CharRNN.scala:76:13") // UnrolledForeach(List(Const(true)),x9551,Block(Const(())),List(List(b5586)),List(List(b5587)))
    val b5586 = CounterIter(x9550, Some(0)).name("b5586").ctrl(x9573) // b5586
    val b5587 = Const(true).name("b5587").ctrl(x9573) // b5587
    val b10164 = StreamOut(field="offset").name("b10164").ctrl(x9573).srcCtx("CharRNN.scala:76:13") // x9552 = StreamOutNew(BurstCmdBus)
    isAccum(b10164) = false
    bufferDepthOf(b10164) = 1
    val b10165 = StreamOut(field="size").name("b10165").ctrl(x9573).srcCtx("CharRNN.scala:76:13") // x9552 = StreamOutNew(BurstCmdBus)
    isAccum(b10165) = false
    bufferDepthOf(b10165) = 1
    val x9553 = StreamIn(field="data").name("x9553").ctrl(x9573).srcCtx("CharRNN.scala:76:13") // x9553 = StreamInNew(BurstDataBus())
    isAccum(x9553) = false
    bufferDepthOf(x9553) = 1
    val x9564 = UnitController(style=SeqPipe, level=InnerControl).name("x9564").ctrl(x9573).srcCtx("CharRNN.scala:76:13") // UnitPipe(List(b5587),Block(x9563))
    val x9554 = b5586 // FixConvert(b5586,TRUE,_32,_0) (Same Type. No op)
    val x9555 = OpDef(op=FixSla, inputs=List(x9554, Const(9))).name("x9555").ctrl(x9564).srcCtx("CharRNN.scala:76:13") // FixLsh(x9554,Const(9))
    val x9556 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9557 = OpDef(op=FixAdd, inputs=List(x9555, x9556)).name("x9557").ctrl(x9564).srcCtx("CharRNN.scala:76:13") // FixAdd(x9555,x9556)
    val x9558 = OpDef(op=FixSla, inputs=List(x9557, Const(2))).name("x9558").ctrl(x9564).srcCtx("CharRNN.scala:76:13") // FixLsh(x9557,Const(2))
    val x9559 = x9558 // FixConvert(x9558,TRUE,_64,_0)
    val x9560 = DramAddress(x9382).name("x9560").ctrl(x9564).srcCtx("CharRNN.scala:76:13") // GetDRAMAddress(x9382)
    val x9562_x9561 = OpDef(op=FixAdd, inputs=List(x9559, x9560)).name("x9562_x9561").ctrl(x9564).srcCtx("CharRNN.scala:76:13") // FixAdd(x9559,x9560)
    // x9562 = SimpleStruct(ArrayBuffer((offset,x9561), (size,Const(2048)), (isLoad,Const(true))))
    val x9563_b10166_b10164 = WriteMem(b10164, x9562_x9561).name("x9563_b10166_b10164").ctrl(x9564).srcCtx("CharRNN.scala:76:13") // StreamWrite(x9552,x9562,b5587)
    val x9563_b10167_b10165 = WriteMem(b10165, Const(2048)).name("x9563_b10167_b10165").ctrl(x9564).srcCtx("CharRNN.scala:76:13") // StreamWrite(x9552,x9562,b5587)
    val x9565 = FringeDenseLoad(dram=List(x9382), cmdStream=List(b10164, b10165), dataStream=List(x9553)).name("x9565").ctrl(x9573).srcCtx("CharRNN.scala:76:13") // FringeDenseLoad(x9382,x9552,x9553)
    val x9566 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9566").ctrl(x9573).srcCtx("CharRNN.scala:76:13") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9567 = CounterChain(List(x9566)).name("x9567").ctrl(x9573).srcCtx("CharRNN.scala:76:13") // CounterChainNew(List(x9566))
    val x9572 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9567).name("x9572").ctrl(x9573).srcCtx("CharRNN.scala:76:13") // UnrolledForeach(List(b5587),x9567,Block(Const(())),List(List(b5604)),List(List(b5605)))
    val b5604 = CounterIter(x9566, None).name("b5604").ctrl(x9572) // b5604
    val b5605 = Const(true).name("b5605").ctrl(x9572) // b5605
    val x9568 = OpDef(op=BitAnd, inputs=List(b5605, b5587)).name("x9568").ctrl(x9572).srcCtx("UnrollingBase.scala:28:66") // And(b5605,b5587)
    val x9569_x9569 = ReadMem(x9553).name("x9569_x9569").ctrl(x9572).srcCtx("CharRNN.scala:76:13") // ParStreamRead(x9553,List(x9568))
    val x9570_x9570 = x9569_x9569 // x9570 = VectorApply(x9569,0)
    val x9571 = StoreBanks(List(x9493_d0_b0), List(b5586, b5604), x9570_x9570).name("x9571").ctrl(x9572).srcCtx("CharRNN.scala:76:13") // ParSRAMStore(x9493,List(List(b5586, b5604)),List(x9570),List(x9568))
    val x9591 = UnitController(style=StreamPipe, level=OuterControl).name("x9591").ctrl(x10147).srcCtx("CharRNN.scala:77:13") // UnitPipe(List(Const(true)),Block(Const(())))
    val b10168 = StreamOut(field="offset").name("b10168").ctrl(x9591).srcCtx("CharRNN.scala:77:13") // x9574 = StreamOutNew(BurstCmdBus)
    isAccum(b10168) = false
    bufferDepthOf(b10168) = 1
    val b10169 = StreamOut(field="size").name("b10169").ctrl(x9591).srcCtx("CharRNN.scala:77:13") // x9574 = StreamOutNew(BurstCmdBus)
    isAccum(b10169) = false
    bufferDepthOf(b10169) = 1
    val x9575 = StreamIn(field="data").name("x9575").ctrl(x9591).srcCtx("CharRNN.scala:77:13") // x9575 = StreamInNew(BurstDataBus())
    isAccum(x9575) = false
    bufferDepthOf(x9575) = 1
    val x9583 = UnitController(style=SeqPipe, level=InnerControl).name("x9583").ctrl(x9591).srcCtx("CharRNN.scala:77:13") // UnitPipe(List(Const(true)),Block(x9582))
    val x9576 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9577 = OpDef(op=FixSla, inputs=List(x9576, Const(2))).name("x9577").ctrl(x9583).srcCtx("CharRNN.scala:77:13") // FixLsh(x9576,Const(2))
    val x9578 = x9577 // FixConvert(x9577,TRUE,_64,_0)
    val x9579 = DramAddress(x9383).name("x9579").ctrl(x9583).srcCtx("CharRNN.scala:77:13") // GetDRAMAddress(x9383)
    val x9581_x9580 = OpDef(op=FixAdd, inputs=List(x9578, x9579)).name("x9581_x9580").ctrl(x9583).srcCtx("CharRNN.scala:77:13") // FixAdd(x9578,x9579)
    // x9581 = SimpleStruct(ArrayBuffer((offset,x9580), (size,Const(2048)), (isLoad,Const(true))))
    val x9582_b10170_b10168 = WriteMem(b10168, x9581_x9580).name("x9582_b10170_b10168").ctrl(x9583).srcCtx("CharRNN.scala:77:13") // StreamWrite(x9574,x9581,Const(true))
    val x9582_b10171_b10169 = WriteMem(b10169, Const(2048)).name("x9582_b10171_b10169").ctrl(x9583).srcCtx("CharRNN.scala:77:13") // StreamWrite(x9574,x9581,Const(true))
    val x9584 = FringeDenseLoad(dram=List(x9383), cmdStream=List(b10168, b10169), dataStream=List(x9575)).name("x9584").ctrl(x9591).srcCtx("CharRNN.scala:77:13") // FringeDenseLoad(x9383,x9574,x9575)
    val x9585 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9585").ctrl(x9591).srcCtx("CharRNN.scala:77:13") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9586 = CounterChain(List(x9585)).name("x9586").ctrl(x9591).srcCtx("CharRNN.scala:77:13") // CounterChainNew(List(x9585))
    val x9590 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9586).name("x9590").ctrl(x9591).srcCtx("CharRNN.scala:77:13") // UnrolledForeach(List(Const(true)),x9586,Block(Const(())),List(List(b5625)),List(List(b5626)))
    val b5625 = CounterIter(x9585, None).name("b5625").ctrl(x9590) // b5625
    val b5626 = Const(true).name("b5626").ctrl(x9590) // b5626
    val x9587_x9587 = ReadMem(x9575).name("x9587_x9587").ctrl(x9590).srcCtx("CharRNN.scala:77:13") // ParStreamRead(x9575,List(b5626))
    val x9588_x9588 = x9587_x9587 // x9588 = VectorApply(x9587,0)
    val x9589 = StoreBanks(List(x9494_d0_b0), List(b5625), x9588_x9588).name("x9589").ctrl(x9590).srcCtx("CharRNN.scala:77:13") // ParSRAMStore(x9494,List(List(b5625)),List(x9588),List(b5626))
    val x9592 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x9592").ctrl(x10147).srcCtx("CharRNN.scala:78:13") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x9593 = CounterChain(List(x9592)).name("x9593").ctrl(x10147).srcCtx("CharRNN.scala:78:13") // CounterChainNew(List(x9592))
    val x9615 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9593).name("x9615").ctrl(x10147).srcCtx("CharRNN.scala:78:13") // UnrolledForeach(List(Const(true)),x9593,Block(Const(())),List(List(b5634)),List(List(b5635)))
    val b5634 = CounterIter(x9592, Some(0)).name("b5634").ctrl(x9615) // b5634
    val b5635 = Const(true).name("b5635").ctrl(x9615) // b5635
    val b10172 = StreamOut(field="offset").name("b10172").ctrl(x9615).srcCtx("CharRNN.scala:78:13") // x9594 = StreamOutNew(BurstCmdBus)
    isAccum(b10172) = false
    bufferDepthOf(b10172) = 1
    val b10173 = StreamOut(field="size").name("b10173").ctrl(x9615).srcCtx("CharRNN.scala:78:13") // x9594 = StreamOutNew(BurstCmdBus)
    isAccum(b10173) = false
    bufferDepthOf(b10173) = 1
    val x9595 = StreamIn(field="data").name("x9595").ctrl(x9615).srcCtx("CharRNN.scala:78:13") // x9595 = StreamInNew(BurstDataBus())
    isAccum(x9595) = false
    bufferDepthOf(x9595) = 1
    val x9606 = UnitController(style=SeqPipe, level=InnerControl).name("x9606").ctrl(x9615).srcCtx("CharRNN.scala:78:13") // UnitPipe(List(b5635),Block(x9605))
    val x9596 = b5634 // FixConvert(b5634,TRUE,_32,_0) (Same Type. No op)
    val x9597 = OpDef(op=FixSla, inputs=List(x9596, Const(9))).name("x9597").ctrl(x9606).srcCtx("CharRNN.scala:78:13") // FixLsh(x9596,Const(9))
    val x9598 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9599 = OpDef(op=FixAdd, inputs=List(x9597, x9598)).name("x9599").ctrl(x9606).srcCtx("CharRNN.scala:78:13") // FixAdd(x9597,x9598)
    val x9600 = OpDef(op=FixSla, inputs=List(x9599, Const(2))).name("x9600").ctrl(x9606).srcCtx("CharRNN.scala:78:13") // FixLsh(x9599,Const(2))
    val x9601 = x9600 // FixConvert(x9600,TRUE,_64,_0)
    val x9602 = DramAddress(x9380).name("x9602").ctrl(x9606).srcCtx("CharRNN.scala:78:13") // GetDRAMAddress(x9380)
    val x9604_x9603 = OpDef(op=FixAdd, inputs=List(x9601, x9602)).name("x9604_x9603").ctrl(x9606).srcCtx("CharRNN.scala:78:13") // FixAdd(x9601,x9602)
    // x9604 = SimpleStruct(ArrayBuffer((offset,x9603), (size,Const(2048)), (isLoad,Const(true))))
    val x9605_b10174_b10172 = WriteMem(b10172, x9604_x9603).name("x9605_b10174_b10172").ctrl(x9606).srcCtx("CharRNN.scala:78:13") // StreamWrite(x9594,x9604,b5635)
    val x9605_b10175_b10173 = WriteMem(b10173, Const(2048)).name("x9605_b10175_b10173").ctrl(x9606).srcCtx("CharRNN.scala:78:13") // StreamWrite(x9594,x9604,b5635)
    val x9607 = FringeDenseLoad(dram=List(x9380), cmdStream=List(b10172, b10173), dataStream=List(x9595)).name("x9607").ctrl(x9615).srcCtx("CharRNN.scala:78:13") // FringeDenseLoad(x9380,x9594,x9595)
    val x9608 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9608").ctrl(x9615).srcCtx("CharRNN.scala:78:13") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9609 = CounterChain(List(x9608)).name("x9609").ctrl(x9615).srcCtx("CharRNN.scala:78:13") // CounterChainNew(List(x9608))
    val x9614 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9609).name("x9614").ctrl(x9615).srcCtx("CharRNN.scala:78:13") // UnrolledForeach(List(b5635),x9609,Block(Const(())),List(List(b5652)),List(List(b5653)))
    val b5652 = CounterIter(x9608, None).name("b5652").ctrl(x9614) // b5652
    val b5653 = Const(true).name("b5653").ctrl(x9614) // b5653
    val x9610 = OpDef(op=BitAnd, inputs=List(b5653, b5635)).name("x9610").ctrl(x9614).srcCtx("UnrollingBase.scala:28:66") // And(b5653,b5635)
    val x9611_x9611 = ReadMem(x9595).name("x9611_x9611").ctrl(x9614).srcCtx("CharRNN.scala:78:13") // ParStreamRead(x9595,List(x9610))
    val x9612_x9612 = x9611_x9611 // x9612 = VectorApply(x9611,0)
    val x9613 = StoreBanks(List(x9495_d0_b0), List(b5634, b5652), x9612_x9612).name("x9613").ctrl(x9614).srcCtx("CharRNN.scala:78:13") // ParSRAMStore(x9495,List(List(b5634, b5652)),List(x9612),List(x9610))
    val x9633 = UnitController(style=StreamPipe, level=OuterControl).name("x9633").ctrl(x10147).srcCtx("CharRNN.scala:79:13") // UnitPipe(List(Const(true)),Block(Const(())))
    val b10176 = StreamOut(field="offset").name("b10176").ctrl(x9633).srcCtx("CharRNN.scala:79:13") // x9616 = StreamOutNew(BurstCmdBus)
    isAccum(b10176) = false
    bufferDepthOf(b10176) = 1
    val b10177 = StreamOut(field="size").name("b10177").ctrl(x9633).srcCtx("CharRNN.scala:79:13") // x9616 = StreamOutNew(BurstCmdBus)
    isAccum(b10177) = false
    bufferDepthOf(b10177) = 1
    val x9617 = StreamIn(field="data").name("x9617").ctrl(x9633).srcCtx("CharRNN.scala:79:13") // x9617 = StreamInNew(BurstDataBus())
    isAccum(x9617) = false
    bufferDepthOf(x9617) = 1
    val x9625 = UnitController(style=SeqPipe, level=InnerControl).name("x9625").ctrl(x9633).srcCtx("CharRNN.scala:79:13") // UnitPipe(List(Const(true)),Block(x9624))
    val x9618 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9619 = OpDef(op=FixSla, inputs=List(x9618, Const(2))).name("x9619").ctrl(x9625).srcCtx("CharRNN.scala:79:13") // FixLsh(x9618,Const(2))
    val x9620 = x9619 // FixConvert(x9619,TRUE,_64,_0)
    val x9621 = DramAddress(x9381).name("x9621").ctrl(x9625).srcCtx("CharRNN.scala:79:13") // GetDRAMAddress(x9381)
    val x9623_x9622 = OpDef(op=FixAdd, inputs=List(x9620, x9621)).name("x9623_x9622").ctrl(x9625).srcCtx("CharRNN.scala:79:13") // FixAdd(x9620,x9621)
    // x9623 = SimpleStruct(ArrayBuffer((offset,x9622), (size,Const(2048)), (isLoad,Const(true))))
    val x9624_b10178_b10176 = WriteMem(b10176, x9623_x9622).name("x9624_b10178_b10176").ctrl(x9625).srcCtx("CharRNN.scala:79:13") // StreamWrite(x9616,x9623,Const(true))
    val x9624_b10179_b10177 = WriteMem(b10177, Const(2048)).name("x9624_b10179_b10177").ctrl(x9625).srcCtx("CharRNN.scala:79:13") // StreamWrite(x9616,x9623,Const(true))
    val x9626 = FringeDenseLoad(dram=List(x9381), cmdStream=List(b10176, b10177), dataStream=List(x9617)).name("x9626").ctrl(x9633).srcCtx("CharRNN.scala:79:13") // FringeDenseLoad(x9381,x9616,x9617)
    val x9627 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9627").ctrl(x9633).srcCtx("CharRNN.scala:79:13") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9628 = CounterChain(List(x9627)).name("x9628").ctrl(x9633).srcCtx("CharRNN.scala:79:13") // CounterChainNew(List(x9627))
    val x9632 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9628).name("x9632").ctrl(x9633).srcCtx("CharRNN.scala:79:13") // UnrolledForeach(List(Const(true)),x9628,Block(Const(())),List(List(b5673)),List(List(b5674)))
    val b5673 = CounterIter(x9627, None).name("b5673").ctrl(x9632) // b5673
    val b5674 = Const(true).name("b5674").ctrl(x9632) // b5674
    val x9629_x9629 = ReadMem(x9617).name("x9629_x9629").ctrl(x9632).srcCtx("CharRNN.scala:79:13") // ParStreamRead(x9617,List(b5674))
    val x9630_x9630 = x9629_x9629 // x9630 = VectorApply(x9629,0)
    val x9631 = StoreBanks(List(x9496_d0_b0), List(b5673), x9630_x9630).name("x9631").ctrl(x9632).srcCtx("CharRNN.scala:79:13") // ParSRAMStore(x9496,List(List(b5673)),List(x9630),List(b5674))
    val x9634 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x9634").ctrl(x10147).srcCtx("CharRNN.scala:80:13") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x9635 = CounterChain(List(x9634)).name("x9635").ctrl(x10147).srcCtx("CharRNN.scala:80:13") // CounterChainNew(List(x9634))
    val x9657 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9635).name("x9657").ctrl(x10147).srcCtx("CharRNN.scala:80:13") // UnrolledForeach(List(Const(true)),x9635,Block(Const(())),List(List(b5682)),List(List(b5683)))
    val b5682 = CounterIter(x9634, Some(0)).name("b5682").ctrl(x9657) // b5682
    val b5683 = Const(true).name("b5683").ctrl(x9657) // b5683
    val b10180 = StreamOut(field="offset").name("b10180").ctrl(x9657).srcCtx("CharRNN.scala:80:13") // x9636 = StreamOutNew(BurstCmdBus)
    isAccum(b10180) = false
    bufferDepthOf(b10180) = 1
    val b10181 = StreamOut(field="size").name("b10181").ctrl(x9657).srcCtx("CharRNN.scala:80:13") // x9636 = StreamOutNew(BurstCmdBus)
    isAccum(b10181) = false
    bufferDepthOf(b10181) = 1
    val x9637 = StreamIn(field="data").name("x9637").ctrl(x9657).srcCtx("CharRNN.scala:80:13") // x9637 = StreamInNew(BurstDataBus())
    isAccum(x9637) = false
    bufferDepthOf(x9637) = 1
    val x9648 = UnitController(style=SeqPipe, level=InnerControl).name("x9648").ctrl(x9657).srcCtx("CharRNN.scala:80:13") // UnitPipe(List(b5683),Block(x9647))
    val x9638 = b5682 // FixConvert(b5682,TRUE,_32,_0) (Same Type. No op)
    val x9639 = OpDef(op=FixSla, inputs=List(x9638, Const(9))).name("x9639").ctrl(x9648).srcCtx("CharRNN.scala:80:13") // FixLsh(x9638,Const(9))
    val x9640 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9641 = OpDef(op=FixAdd, inputs=List(x9639, x9640)).name("x9641").ctrl(x9648).srcCtx("CharRNN.scala:80:13") // FixAdd(x9639,x9640)
    val x9642 = OpDef(op=FixSla, inputs=List(x9641, Const(2))).name("x9642").ctrl(x9648).srcCtx("CharRNN.scala:80:13") // FixLsh(x9641,Const(2))
    val x9643 = x9642 // FixConvert(x9642,TRUE,_64,_0)
    val x9644 = DramAddress(x9384).name("x9644").ctrl(x9648).srcCtx("CharRNN.scala:80:13") // GetDRAMAddress(x9384)
    val x9646_x9645 = OpDef(op=FixAdd, inputs=List(x9643, x9644)).name("x9646_x9645").ctrl(x9648).srcCtx("CharRNN.scala:80:13") // FixAdd(x9643,x9644)
    // x9646 = SimpleStruct(ArrayBuffer((offset,x9645), (size,Const(2048)), (isLoad,Const(true))))
    val x9647_b10182_b10180 = WriteMem(b10180, x9646_x9645).name("x9647_b10182_b10180").ctrl(x9648).srcCtx("CharRNN.scala:80:13") // StreamWrite(x9636,x9646,b5683)
    val x9647_b10183_b10181 = WriteMem(b10181, Const(2048)).name("x9647_b10183_b10181").ctrl(x9648).srcCtx("CharRNN.scala:80:13") // StreamWrite(x9636,x9646,b5683)
    val x9649 = FringeDenseLoad(dram=List(x9384), cmdStream=List(b10180, b10181), dataStream=List(x9637)).name("x9649").ctrl(x9657).srcCtx("CharRNN.scala:80:13") // FringeDenseLoad(x9384,x9636,x9637)
    val x9650 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9650").ctrl(x9657).srcCtx("CharRNN.scala:80:13") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9651 = CounterChain(List(x9650)).name("x9651").ctrl(x9657).srcCtx("CharRNN.scala:80:13") // CounterChainNew(List(x9650))
    val x9656 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9651).name("x9656").ctrl(x9657).srcCtx("CharRNN.scala:80:13") // UnrolledForeach(List(b5683),x9651,Block(Const(())),List(List(b5700)),List(List(b5701)))
    val b5700 = CounterIter(x9650, None).name("b5700").ctrl(x9656) // b5700
    val b5701 = Const(true).name("b5701").ctrl(x9656) // b5701
    val x9652 = OpDef(op=BitAnd, inputs=List(b5701, b5683)).name("x9652").ctrl(x9656).srcCtx("UnrollingBase.scala:28:66") // And(b5701,b5683)
    val x9653_x9653 = ReadMem(x9637).name("x9653_x9653").ctrl(x9656).srcCtx("CharRNN.scala:80:13") // ParStreamRead(x9637,List(x9652))
    val x9654_x9654 = x9653_x9653 // x9654 = VectorApply(x9653,0)
    val x9655 = StoreBanks(List(x9497_d0_b0), List(b5682, b5700), x9654_x9654).name("x9655").ctrl(x9656).srcCtx("CharRNN.scala:80:13") // ParSRAMStore(x9497,List(List(b5682, b5700)),List(x9654),List(x9652))
    val x9675 = UnitController(style=StreamPipe, level=OuterControl).name("x9675").ctrl(x10147).srcCtx("CharRNN.scala:81:13") // UnitPipe(List(Const(true)),Block(Const(())))
    val b10184 = StreamOut(field="offset").name("b10184").ctrl(x9675).srcCtx("CharRNN.scala:81:13") // x9658 = StreamOutNew(BurstCmdBus)
    isAccum(b10184) = false
    bufferDepthOf(b10184) = 1
    val b10185 = StreamOut(field="size").name("b10185").ctrl(x9675).srcCtx("CharRNN.scala:81:13") // x9658 = StreamOutNew(BurstCmdBus)
    isAccum(b10185) = false
    bufferDepthOf(b10185) = 1
    val x9659 = StreamIn(field="data").name("x9659").ctrl(x9675).srcCtx("CharRNN.scala:81:13") // x9659 = StreamInNew(BurstDataBus())
    isAccum(x9659) = false
    bufferDepthOf(x9659) = 1
    val x9667 = UnitController(style=SeqPipe, level=InnerControl).name("x9667").ctrl(x9675).srcCtx("CharRNN.scala:81:13") // UnitPipe(List(Const(true)),Block(x9666))
    val x9660 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9661 = OpDef(op=FixSla, inputs=List(x9660, Const(2))).name("x9661").ctrl(x9667).srcCtx("CharRNN.scala:81:13") // FixLsh(x9660,Const(2))
    val x9662 = x9661 // FixConvert(x9661,TRUE,_64,_0)
    val x9663 = DramAddress(x9385).name("x9663").ctrl(x9667).srcCtx("CharRNN.scala:81:13") // GetDRAMAddress(x9385)
    val x9665_x9664 = OpDef(op=FixAdd, inputs=List(x9662, x9663)).name("x9665_x9664").ctrl(x9667).srcCtx("CharRNN.scala:81:13") // FixAdd(x9662,x9663)
    // x9665 = SimpleStruct(ArrayBuffer((offset,x9664), (size,Const(2048)), (isLoad,Const(true))))
    val x9666_b10186_b10184 = WriteMem(b10184, x9665_x9664).name("x9666_b10186_b10184").ctrl(x9667).srcCtx("CharRNN.scala:81:13") // StreamWrite(x9658,x9665,Const(true))
    val x9666_b10187_b10185 = WriteMem(b10185, Const(2048)).name("x9666_b10187_b10185").ctrl(x9667).srcCtx("CharRNN.scala:81:13") // StreamWrite(x9658,x9665,Const(true))
    val x9668 = FringeDenseLoad(dram=List(x9385), cmdStream=List(b10184, b10185), dataStream=List(x9659)).name("x9668").ctrl(x9675).srcCtx("CharRNN.scala:81:13") // FringeDenseLoad(x9385,x9658,x9659)
    val x9669 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9669").ctrl(x9675).srcCtx("CharRNN.scala:81:13") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9670 = CounterChain(List(x9669)).name("x9670").ctrl(x9675).srcCtx("CharRNN.scala:81:13") // CounterChainNew(List(x9669))
    val x9674 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9670).name("x9674").ctrl(x9675).srcCtx("CharRNN.scala:81:13") // UnrolledForeach(List(Const(true)),x9670,Block(Const(())),List(List(b5721)),List(List(b5722)))
    val b5721 = CounterIter(x9669, None).name("b5721").ctrl(x9674) // b5721
    val b5722 = Const(true).name("b5722").ctrl(x9674) // b5722
    val x9671_x9671 = ReadMem(x9659).name("x9671_x9671").ctrl(x9674).srcCtx("CharRNN.scala:81:13") // ParStreamRead(x9659,List(b5722))
    val x9672_x9672 = x9671_x9671 // x9672 = VectorApply(x9671,0)
    val x9673 = StoreBanks(List(x9498_d0_b0), List(b5721), x9672_x9672).name("x9673").ctrl(x9674).srcCtx("CharRNN.scala:81:13") // ParSRAMStore(x9498,List(List(b5721)),List(x9672),List(b5722))
    val x9676 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x9676").ctrl(x10147).srcCtx("CharRNN.scala:82:17") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x9677 = CounterChain(List(x9676)).name("x9677").ctrl(x10147).srcCtx("CharRNN.scala:82:17") // CounterChainNew(List(x9676))
    val x9699 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9677).name("x9699").ctrl(x10147).srcCtx("CharRNN.scala:82:17") // UnrolledForeach(List(Const(true)),x9677,Block(Const(())),List(List(b5730)),List(List(b5731)))
    val b5730 = CounterIter(x9676, Some(0)).name("b5730").ctrl(x9699) // b5730
    val b5731 = Const(true).name("b5731").ctrl(x9699) // b5731
    val b10188 = StreamOut(field="offset").name("b10188").ctrl(x9699).srcCtx("CharRNN.scala:82:17") // x9678 = StreamOutNew(BurstCmdBus)
    isAccum(b10188) = false
    bufferDepthOf(b10188) = 1
    val b10189 = StreamOut(field="size").name("b10189").ctrl(x9699).srcCtx("CharRNN.scala:82:17") // x9678 = StreamOutNew(BurstCmdBus)
    isAccum(b10189) = false
    bufferDepthOf(b10189) = 1
    val x9679 = StreamIn(field="data").name("x9679").ctrl(x9699).srcCtx("CharRNN.scala:82:17") // x9679 = StreamInNew(BurstDataBus())
    isAccum(x9679) = false
    bufferDepthOf(x9679) = 1
    val x9690 = UnitController(style=SeqPipe, level=InnerControl).name("x9690").ctrl(x9699).srcCtx("CharRNN.scala:82:17") // UnitPipe(List(b5731),Block(x9689))
    val x9680 = b5730 // FixConvert(b5730,TRUE,_32,_0) (Same Type. No op)
    val x9681 = OpDef(op=FixSla, inputs=List(x9680, Const(6))).name("x9681").ctrl(x9690).srcCtx("CharRNN.scala:82:17") // FixLsh(x9680,Const(6))
    val x9682 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9683 = OpDef(op=FixAdd, inputs=List(x9681, x9682)).name("x9683").ctrl(x9690).srcCtx("CharRNN.scala:82:17") // FixAdd(x9681,x9682)
    val x9684 = OpDef(op=FixSla, inputs=List(x9683, Const(2))).name("x9684").ctrl(x9690).srcCtx("CharRNN.scala:82:17") // FixLsh(x9683,Const(2))
    val x9685 = x9684 // FixConvert(x9684,TRUE,_64,_0)
    val x9686 = DramAddress(x9386).name("x9686").ctrl(x9690).srcCtx("CharRNN.scala:82:17") // GetDRAMAddress(x9386)
    val x9688_x9687 = OpDef(op=FixAdd, inputs=List(x9685, x9686)).name("x9688_x9687").ctrl(x9690).srcCtx("CharRNN.scala:82:17") // FixAdd(x9685,x9686)
    // x9688 = SimpleStruct(ArrayBuffer((offset,x9687), (size,Const(2048)), (isLoad,Const(true))))
    val x9689_b10190_b10188 = WriteMem(b10188, x9688_x9687).name("x9689_b10190_b10188").ctrl(x9690).srcCtx("CharRNN.scala:82:17") // StreamWrite(x9678,x9688,b5731)
    val x9689_b10191_b10189 = WriteMem(b10189, Const(2048)).name("x9689_b10191_b10189").ctrl(x9690).srcCtx("CharRNN.scala:82:17") // StreamWrite(x9678,x9688,b5731)
    val x9691 = FringeDenseLoad(dram=List(x9386), cmdStream=List(b10188, b10189), dataStream=List(x9679)).name("x9691").ctrl(x9699).srcCtx("CharRNN.scala:82:17") // FringeDenseLoad(x9386,x9678,x9679)
    val x9692 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9692").ctrl(x9699).srcCtx("CharRNN.scala:82:17") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9693 = CounterChain(List(x9692)).name("x9693").ctrl(x9699).srcCtx("CharRNN.scala:82:17") // CounterChainNew(List(x9692))
    val x9698 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9693).name("x9698").ctrl(x9699).srcCtx("CharRNN.scala:82:17") // UnrolledForeach(List(b5731),x9693,Block(Const(())),List(List(b5748)),List(List(b5749)))
    val b5748 = CounterIter(x9692, None).name("b5748").ctrl(x9698) // b5748
    val b5749 = Const(true).name("b5749").ctrl(x9698) // b5749
    val x9694 = OpDef(op=BitAnd, inputs=List(b5749, b5731)).name("x9694").ctrl(x9698).srcCtx("UnrollingBase.scala:28:66") // And(b5749,b5731)
    val x9695_x9695 = ReadMem(x9679).name("x9695_x9695").ctrl(x9698).srcCtx("CharRNN.scala:82:17") // ParStreamRead(x9679,List(x9694))
    val x9696_x9696 = x9695_x9695 // x9696 = VectorApply(x9695,0)
    val x9697 = StoreBanks(List(x9499_d0_b0), List(b5730, b5748), x9696_x9696).name("x9697").ctrl(x9698).srcCtx("CharRNN.scala:82:17") // ParSRAMStore(x9499,List(List(b5730, b5748)),List(x9696),List(x9694))
    val x9717 = UnitController(style=StreamPipe, level=OuterControl).name("x9717").ctrl(x10147).srcCtx("CharRNN.scala:83:17") // UnitPipe(List(Const(true)),Block(Const(())))
    val b10192 = StreamOut(field="offset").name("b10192").ctrl(x9717).srcCtx("CharRNN.scala:83:17") // x9700 = StreamOutNew(BurstCmdBus)
    isAccum(b10192) = false
    bufferDepthOf(b10192) = 1
    val b10193 = StreamOut(field="size").name("b10193").ctrl(x9717).srcCtx("CharRNN.scala:83:17") // x9700 = StreamOutNew(BurstCmdBus)
    isAccum(b10193) = false
    bufferDepthOf(b10193) = 1
    val x9701 = StreamIn(field="data").name("x9701").ctrl(x9717).srcCtx("CharRNN.scala:83:17") // x9701 = StreamInNew(BurstDataBus())
    isAccum(x9701) = false
    bufferDepthOf(x9701) = 1
    val x9709 = UnitController(style=SeqPipe, level=InnerControl).name("x9709").ctrl(x9717).srcCtx("CharRNN.scala:83:17") // UnitPipe(List(Const(true)),Block(x9708))
    val x9702 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9703 = OpDef(op=FixSla, inputs=List(x9702, Const(2))).name("x9703").ctrl(x9709).srcCtx("CharRNN.scala:83:17") // FixLsh(x9702,Const(2))
    val x9704 = x9703 // FixConvert(x9703,TRUE,_64,_0)
    val x9705 = DramAddress(x9387).name("x9705").ctrl(x9709).srcCtx("CharRNN.scala:83:17") // GetDRAMAddress(x9387)
    val x9707_x9706 = OpDef(op=FixAdd, inputs=List(x9704, x9705)).name("x9707_x9706").ctrl(x9709).srcCtx("CharRNN.scala:83:17") // FixAdd(x9704,x9705)
    // x9707 = SimpleStruct(ArrayBuffer((offset,x9706), (size,Const(2048)), (isLoad,Const(true))))
    val x9708_b10194_b10192 = WriteMem(b10192, x9707_x9706).name("x9708_b10194_b10192").ctrl(x9709).srcCtx("CharRNN.scala:83:17") // StreamWrite(x9700,x9707,Const(true))
    val x9708_b10195_b10193 = WriteMem(b10193, Const(2048)).name("x9708_b10195_b10193").ctrl(x9709).srcCtx("CharRNN.scala:83:17") // StreamWrite(x9700,x9707,Const(true))
    val x9710 = FringeDenseLoad(dram=List(x9387), cmdStream=List(b10192, b10193), dataStream=List(x9701)).name("x9710").ctrl(x9717).srcCtx("CharRNN.scala:83:17") // FringeDenseLoad(x9387,x9700,x9701)
    val x9711 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9711").ctrl(x9717).srcCtx("CharRNN.scala:83:17") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9712 = CounterChain(List(x9711)).name("x9712").ctrl(x9717).srcCtx("CharRNN.scala:83:17") // CounterChainNew(List(x9711))
    val x9716 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9712).name("x9716").ctrl(x9717).srcCtx("CharRNN.scala:83:17") // UnrolledForeach(List(Const(true)),x9712,Block(Const(())),List(List(b5769)),List(List(b5770)))
    val b5769 = CounterIter(x9711, None).name("b5769").ctrl(x9716) // b5769
    val b5770 = Const(true).name("b5770").ctrl(x9716) // b5770
    val x9713_x9713 = ReadMem(x9701).name("x9713_x9713").ctrl(x9716).srcCtx("CharRNN.scala:83:17") // ParStreamRead(x9701,List(b5770))
    val x9714_x9714 = x9713_x9713 // x9714 = VectorApply(x9713,0)
    val x9715 = StoreBanks(List(x9500_d0_b0), List(b5769), x9714_x9714).name("x9715").ctrl(x9716).srcCtx("CharRNN.scala:83:17") // ParSRAMStore(x9500,List(List(b5769)),List(x9714),List(b5770))
    val x9718 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9718").ctrl(x10147).srcCtx("CharRNN.scala:84:9") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9719 = CounterChain(List(x9718)).name("x9719").ctrl(x10147).srcCtx("CharRNN.scala:84:9") // CounterChainNew(List(x9718))
    val x9741 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9719).name("x9741").ctrl(x10147).srcCtx("CharRNN.scala:84:9") // UnrolledForeach(List(Const(true)),x9719,Block(Const(())),List(List(b5778)),List(List(b5779)))
    val b5778 = CounterIter(x9718, Some(0)).name("b5778").ctrl(x9741) // b5778
    val b5779 = Const(true).name("b5779").ctrl(x9741) // b5779
    val b10196 = StreamOut(field="offset").name("b10196").ctrl(x9741).srcCtx("CharRNN.scala:84:9") // x9720 = StreamOutNew(BurstCmdBus)
    isAccum(b10196) = false
    bufferDepthOf(b10196) = 1
    val b10197 = StreamOut(field="size").name("b10197").ctrl(x9741).srcCtx("CharRNN.scala:84:9") // x9720 = StreamOutNew(BurstCmdBus)
    isAccum(b10197) = false
    bufferDepthOf(b10197) = 1
    val x9721 = StreamIn(field="data").name("x9721").ctrl(x9741).srcCtx("CharRNN.scala:84:9") // x9721 = StreamInNew(BurstDataBus())
    isAccum(x9721) = false
    bufferDepthOf(x9721) = 1
    val x9732 = UnitController(style=SeqPipe, level=InnerControl).name("x9732").ctrl(x9741).srcCtx("CharRNN.scala:84:9") // UnitPipe(List(b5779),Block(x9731))
    val x9722 = b5778 // FixConvert(b5778,TRUE,_32,_0) (Same Type. No op)
    val x9723 = OpDef(op=FixSla, inputs=List(x9722, Const(7))).name("x9723").ctrl(x9732).srcCtx("CharRNN.scala:84:9") // FixLsh(x9722,Const(7))
    val x9724 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9725 = OpDef(op=FixAdd, inputs=List(x9723, x9724)).name("x9725").ctrl(x9732).srcCtx("CharRNN.scala:84:9") // FixAdd(x9723,x9724)
    val x9726 = OpDef(op=FixSla, inputs=List(x9725, Const(2))).name("x9726").ctrl(x9732).srcCtx("CharRNN.scala:84:9") // FixLsh(x9725,Const(2))
    val x9727 = x9726 // FixConvert(x9726,TRUE,_64,_0)
    val x9728 = DramAddress(x9388).name("x9728").ctrl(x9732).srcCtx("CharRNN.scala:84:9") // GetDRAMAddress(x9388)
    val x9730_x9729 = OpDef(op=FixAdd, inputs=List(x9727, x9728)).name("x9730_x9729").ctrl(x9732).srcCtx("CharRNN.scala:84:9") // FixAdd(x9727,x9728)
    // x9730 = SimpleStruct(ArrayBuffer((offset,x9729), (size,Const(512)), (isLoad,Const(true))))
    val x9731_b10198_b10196 = WriteMem(b10196, x9730_x9729).name("x9731_b10198_b10196").ctrl(x9732).srcCtx("CharRNN.scala:84:9") // StreamWrite(x9720,x9730,b5779)
    val x9731_b10199_b10197 = WriteMem(b10197, Const(512)).name("x9731_b10199_b10197").ctrl(x9732).srcCtx("CharRNN.scala:84:9") // StreamWrite(x9720,x9730,b5779)
    val x9733 = FringeDenseLoad(dram=List(x9388), cmdStream=List(b10196, b10197), dataStream=List(x9721)).name("x9733").ctrl(x9741).srcCtx("CharRNN.scala:84:9") // FringeDenseLoad(x9388,x9720,x9721)
    val x9734 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x9734").ctrl(x9741).srcCtx("CharRNN.scala:84:9") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x9735 = CounterChain(List(x9734)).name("x9735").ctrl(x9741).srcCtx("CharRNN.scala:84:9") // CounterChainNew(List(x9734))
    val x9740 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9735).name("x9740").ctrl(x9741).srcCtx("CharRNN.scala:84:9") // UnrolledForeach(List(b5779),x9735,Block(Const(())),List(List(b5796)),List(List(b5797)))
    val b5796 = CounterIter(x9734, None).name("b5796").ctrl(x9740) // b5796
    val b5797 = Const(true).name("b5797").ctrl(x9740) // b5797
    val x9736 = OpDef(op=BitAnd, inputs=List(b5797, b5779)).name("x9736").ctrl(x9740).srcCtx("UnrollingBase.scala:28:66") // And(b5797,b5779)
    val x9737_x9737 = ReadMem(x9721).name("x9737_x9737").ctrl(x9740).srcCtx("CharRNN.scala:84:9") // ParStreamRead(x9721,List(x9736))
    val x9738_x9738 = x9737_x9737 // x9738 = VectorApply(x9737,0)
    val x9739 = StoreBanks(List(x9501_d0_b0, x9501_d1_b0), List(b5778, b5796), x9738_x9738).name("x9739").ctrl(x9740).srcCtx("CharRNN.scala:84:9") // ParSRAMStore(x9501,List(List(b5778, b5796)),List(x9738),List(x9736))
    val x9742 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9742").ctrl(x10147).srcCtx("CharRNN.scala:85:9") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9743 = CounterChain(List(x9742)).name("x9743").ctrl(x10147).srcCtx("CharRNN.scala:85:9") // CounterChainNew(List(x9742))
    val x9765 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9743).name("x9765").ctrl(x10147).srcCtx("CharRNN.scala:85:9") // UnrolledForeach(List(Const(true)),x9743,Block(Const(())),List(List(b5806)),List(List(b5807)))
    val b5806 = CounterIter(x9742, Some(0)).name("b5806").ctrl(x9765) // b5806
    val b5807 = Const(true).name("b5807").ctrl(x9765) // b5807
    val b10200 = StreamOut(field="offset").name("b10200").ctrl(x9765).srcCtx("CharRNN.scala:85:9") // x9744 = StreamOutNew(BurstCmdBus)
    isAccum(b10200) = false
    bufferDepthOf(b10200) = 1
    val b10201 = StreamOut(field="size").name("b10201").ctrl(x9765).srcCtx("CharRNN.scala:85:9") // x9744 = StreamOutNew(BurstCmdBus)
    isAccum(b10201) = false
    bufferDepthOf(b10201) = 1
    val x9745 = StreamIn(field="data").name("x9745").ctrl(x9765).srcCtx("CharRNN.scala:85:9") // x9745 = StreamInNew(BurstDataBus())
    isAccum(x9745) = false
    bufferDepthOf(x9745) = 1
    val x9756 = UnitController(style=SeqPipe, level=InnerControl).name("x9756").ctrl(x9765).srcCtx("CharRNN.scala:85:9") // UnitPipe(List(b5807),Block(x9755))
    val x9746 = b5806 // FixConvert(b5806,TRUE,_32,_0) (Same Type. No op)
    val x9747 = OpDef(op=FixSla, inputs=List(x9746, Const(7))).name("x9747").ctrl(x9756).srcCtx("CharRNN.scala:85:9") // FixLsh(x9746,Const(7))
    val x9748 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9749 = OpDef(op=FixAdd, inputs=List(x9747, x9748)).name("x9749").ctrl(x9756).srcCtx("CharRNN.scala:85:9") // FixAdd(x9747,x9748)
    val x9750 = OpDef(op=FixSla, inputs=List(x9749, Const(2))).name("x9750").ctrl(x9756).srcCtx("CharRNN.scala:85:9") // FixLsh(x9749,Const(2))
    val x9751 = x9750 // FixConvert(x9750,TRUE,_64,_0)
    val x9752 = DramAddress(x9389).name("x9752").ctrl(x9756).srcCtx("CharRNN.scala:85:9") // GetDRAMAddress(x9389)
    val x9754_x9753 = OpDef(op=FixAdd, inputs=List(x9751, x9752)).name("x9754_x9753").ctrl(x9756).srcCtx("CharRNN.scala:85:9") // FixAdd(x9751,x9752)
    // x9754 = SimpleStruct(ArrayBuffer((offset,x9753), (size,Const(512)), (isLoad,Const(true))))
    val x9755_b10202_b10200 = WriteMem(b10200, x9754_x9753).name("x9755_b10202_b10200").ctrl(x9756).srcCtx("CharRNN.scala:85:9") // StreamWrite(x9744,x9754,b5807)
    val x9755_b10203_b10201 = WriteMem(b10201, Const(512)).name("x9755_b10203_b10201").ctrl(x9756).srcCtx("CharRNN.scala:85:9") // StreamWrite(x9744,x9754,b5807)
    val x9757 = FringeDenseLoad(dram=List(x9389), cmdStream=List(b10200, b10201), dataStream=List(x9745)).name("x9757").ctrl(x9765).srcCtx("CharRNN.scala:85:9") // FringeDenseLoad(x9389,x9744,x9745)
    val x9758 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x9758").ctrl(x9765).srcCtx("CharRNN.scala:85:9") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x9759 = CounterChain(List(x9758)).name("x9759").ctrl(x9765).srcCtx("CharRNN.scala:85:9") // CounterChainNew(List(x9758))
    val x9764 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9759).name("x9764").ctrl(x9765).srcCtx("CharRNN.scala:85:9") // UnrolledForeach(List(b5807),x9759,Block(Const(())),List(List(b5824)),List(List(b5825)))
    val b5824 = CounterIter(x9758, None).name("b5824").ctrl(x9764) // b5824
    val b5825 = Const(true).name("b5825").ctrl(x9764) // b5825
    val x9760 = OpDef(op=BitAnd, inputs=List(b5825, b5807)).name("x9760").ctrl(x9764).srcCtx("UnrollingBase.scala:28:66") // And(b5825,b5807)
    val x9761_x9761 = ReadMem(x9745).name("x9761_x9761").ctrl(x9764).srcCtx("CharRNN.scala:85:9") // ParStreamRead(x9745,List(x9760))
    val x9762_x9762 = x9761_x9761 // x9762 = VectorApply(x9761,0)
    val x9763 = StoreBanks(List(x9502_d0_b0, x9502_d1_b0, x9502_d2_b0, x9502_d3_b0), List(b5806, b5824), x9762_x9762).name("x9763").ctrl(x9764).srcCtx("CharRNN.scala:85:9") // ParSRAMStore(x9502,List(List(b5806, b5824)),List(x9762),List(x9760))
    val x9766 = Counter(min=Const(0), max=Const(3), step=Const(1), par=1).name("x9766").ctrl(x10147).srcCtx("CharRNN.scala:87:37") // CounterNew(Const(0),Const(3),Const(1),Const(1))
    val x9767 = CounterChain(List(x9766)).name("x9767").ctrl(x10147).srcCtx("CharRNN.scala:87:43") // CounterChainNew(List(x9766))
    val x10146 = LoopController(style=SeqPipe, level=OuterControl, cchain=x9767).name("x10146").ctrl(x10147).srcCtx("CharRNN.scala:87:43") // UnrolledForeach(List(Const(true)),x9767,Block(Const(())),List(List(b5834)),List(List(b5835)))
    val b5834 = CounterIter(x9766, Some(0)).name("b5834").ctrl(x10146) // b5834
    val b5835 = Const(true).name("b5835").ctrl(x10146) // b5835
    val x9769 = UnitController(style=SeqPipe, level=InnerControl).name("x9769").ctrl(x10146).srcCtx("CharRNN.scala:87:43") // UnitPipe(List(b5835),Block(Const(())))
    val x9768 = OpDef(op=FixAdd, inputs=List(b5834, Const(1))).name("x9768").ctrl(x9769).srcCtx("CharRNN.scala:88:22") // FixAdd(b5834,Const(1))
    val x9770 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9770").ctrl(x10146).srcCtx("CharRNN.scala:88:11") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9771 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9771").ctrl(x10146).srcCtx("CharRNN.scala:88:11") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9772 = CounterChain(List(x9770,x9771)).name("x9772").ctrl(x10146).srcCtx("CharRNN.scala:88:11") // CounterChainNew(List(x9770, x9771))
    val x9802 = LoopController(style=StreamPipe, level=OuterControl, cchain=x9772).name("x9802").ctrl(x10146).srcCtx("CharRNN.scala:88:11") // UnrolledForeach(List(b5835),x9772,Block(Const(())),List(List(b5841), List(b5842)),List(List(b5843), List(b5844)))
    val b5841 = CounterIter(x9770, Some(0)).name("b5841").ctrl(x9802) // b5841
    val b5843 = Const(true).name("b5843").ctrl(x9802) // b5843
    val b5842 = CounterIter(x9771, Some(0)).name("b5842").ctrl(x9802) // b5842
    val b5844 = Const(true).name("b5844").ctrl(x9802) // b5844
    val b10204 = StreamOut(field="offset").name("b10204").ctrl(x9802).srcCtx("CharRNN.scala:88:11") // x9773 = StreamOutNew(BurstCmdBus)
    isAccum(b10204) = false
    bufferDepthOf(b10204) = 1
    val b10205 = StreamOut(field="size").name("b10205").ctrl(x9802).srcCtx("CharRNN.scala:88:11") // x9773 = StreamOutNew(BurstCmdBus)
    isAccum(b10205) = false
    bufferDepthOf(b10205) = 1
    val x9774 = StreamIn(field="data").name("x9774").ctrl(x9802).srcCtx("CharRNN.scala:88:11") // x9774 = StreamInNew(BurstDataBus())
    isAccum(x9774) = false
    bufferDepthOf(x9774) = 1
    val x9791 = UnitController(style=SeqPipe, level=InnerControl).name("x9791").ctrl(x9802).srcCtx("CharRNN.scala:88:11") // UnitPipe(List(b5843, b5844, b5835),Block(x9790))
    val x9775 = OpDef(op=FixAdd, inputs=List(b5834, b5841)).name("x9775").ctrl(x9791).srcCtx("CharRNN.scala:88:11") // FixAdd(b5834,b5841)
    val x9776 = x9775 // FixConvert(x9775,TRUE,_32,_0) (Same Type. No op)
    val x9777 = OpDef(op=FixSla, inputs=List(x9776, Const(6))).name("x9777").ctrl(x9791).srcCtx("CharRNN.scala:88:11") // FixLsh(x9776,Const(6))
    val x9778 = b5842 // FixConvert(b5842,TRUE,_32,_0) (Same Type. No op)
    val x9779 = OpDef(op=FixSla, inputs=List(x9778, Const(6))).name("x9779").ctrl(x9791).srcCtx("CharRNN.scala:88:11") // FixLsh(x9778,Const(6))
    val x9780 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9781 = OpDef(op=FixAdd, inputs=List(x9777, x9779)).name("x9781").ctrl(x9791).srcCtx("CharRNN.scala:88:11") // FixAdd(x9777,x9779)
    val x9782 = OpDef(op=FixAdd, inputs=List(x9781, x9780)).name("x9782").ctrl(x9791).srcCtx("CharRNN.scala:88:11") // FixAdd(x9781,x9780)
    val x9783 = OpDef(op=FixSla, inputs=List(x9782, Const(2))).name("x9783").ctrl(x9791).srcCtx("CharRNN.scala:88:11") // FixLsh(x9782,Const(2))
    val x9784 = x9783 // FixConvert(x9783,TRUE,_64,_0)
    val x9785 = DramAddress(x9390).name("x9785").ctrl(x9791).srcCtx("CharRNN.scala:88:11") // GetDRAMAddress(x9390)
    val x9787_x9786 = OpDef(op=FixAdd, inputs=List(x9784, x9785)).name("x9787_x9786").ctrl(x9791).srcCtx("CharRNN.scala:88:11") // FixAdd(x9784,x9785)
    // x9787 = SimpleStruct(ArrayBuffer((offset,x9786), (size,Const(256)), (isLoad,Const(true))))
    val x9788 = OpDef(op=BitAnd, inputs=List(b5843, b5844)).name("x9788").ctrl(x9791).srcCtx("UnrollingBase.scala:28:66") // And(b5843,b5844)
    val x9789 = OpDef(op=BitAnd, inputs=List(x9788, b5835)).name("x9789").ctrl(x9791).srcCtx("UnrollingBase.scala:28:66") // And(x9788,b5835)
    val x9790_b10206_b10204 = WriteMem(b10204, x9787_x9786).name("x9790_b10206_b10204").ctrl(x9791).srcCtx("CharRNN.scala:88:11") // StreamWrite(x9773,x9787,x9789)
    val x9790_b10207_b10205 = WriteMem(b10205, Const(256)).name("x9790_b10207_b10205").ctrl(x9791).srcCtx("CharRNN.scala:88:11") // StreamWrite(x9773,x9787,x9789)
    val x9792 = FringeDenseLoad(dram=List(x9390), cmdStream=List(b10204, b10205), dataStream=List(x9774)).name("x9792").ctrl(x9802).srcCtx("CharRNN.scala:88:11") // FringeDenseLoad(x9390,x9773,x9774)
    val x9793 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x9793").ctrl(x9802).srcCtx("CharRNN.scala:88:11") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x9794 = CounterChain(List(x9793)).name("x9794").ctrl(x9802).srcCtx("CharRNN.scala:88:11") // CounterChainNew(List(x9793))
    val x9801 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9794).name("x9801").ctrl(x9802).srcCtx("CharRNN.scala:88:11") // UnrolledForeach(List(b5843, b5844, b5835),x9794,Block(Const(())),List(List(b5867)),List(List(b5868)))
    val b5867 = CounterIter(x9793, None).name("b5867").ctrl(x9801) // b5867
    val b5868 = Const(true).name("b5868").ctrl(x9801) // b5868
    val x9795 = OpDef(op=BitAnd, inputs=List(b5868, b5843)).name("x9795").ctrl(x9801).srcCtx("UnrollingBase.scala:28:66") // And(b5868,b5843)
    val x9796 = OpDef(op=BitAnd, inputs=List(b5844, b5835)).name("x9796").ctrl(x9801).srcCtx("UnrollingBase.scala:28:66") // And(b5844,b5835)
    val x9797 = OpDef(op=BitAnd, inputs=List(x9795, x9796)).name("x9797").ctrl(x9801).srcCtx("UnrollingBase.scala:28:66") // And(x9795,x9796)
    val x9798_x9798 = ReadMem(x9774).name("x9798_x9798").ctrl(x9801).srcCtx("CharRNN.scala:88:11") // ParStreamRead(x9774,List(x9797))
    val x9799_x9799 = x9798_x9798 // x9799 = VectorApply(x9798,0)
    val x9800 = StoreBanks(List(x9503_d0_b0, x9503_d1_b0), List(b5842, b5867), x9799_x9799).name("x9800").ctrl(x9801).srcCtx("CharRNN.scala:88:11") // ParSRAMStore(x9503,List(List(b5842, b5867)),List(x9799),List(x9797))
    val x9803 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9803").ctrl(x10146).srcCtx("CharRNN.scala:91:40") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9804 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9804").ctrl(x10146).srcCtx("CharRNN.scala:91:29") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9805 = CounterChain(List(x9804,x9803)).name("x9805").ctrl(x10146).srcCtx("CharRNN.scala:91:46") // CounterChainNew(List(x9804, x9803))
    val x9918 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9805).name("x9918").ctrl(x10146).srcCtx("CharRNN.scala:91:46") // UnrolledForeach(List(b5835),x9805,Block(Const(())),List(List(b5880), List(b5881)),List(List(b5882), List(b5883)))
    val b5880 = CounterIter(x9804, Some(0)).name("b5880").ctrl(x9918) // b5880
    val b5882 = Const(true).name("b5882").ctrl(x9918) // b5882
    val b5881 = CounterIter(x9803, Some(0)).name("b5881").ctrl(x9918) // b5881
    val b5883 = Const(true).name("b5883").ctrl(x9918) // b5883
    val x9806_d0 = Reg(init=Some(0.0)).name("x9806_d0").ctrl(x9918).srcCtx("CharRNN.scala:92:36:i2h_prod") // x9806 = RegNew(Const(0))
    isAccum(x9806_d0) = false
    bufferDepthOf(x9806_d0) = 2
    val x9806_d1 = Reg(init=Some(0.0)).name("x9806_d1").ctrl(x9918).srcCtx("CharRNN.scala:92:36:i2h_prod") // x9806 = RegNew(Const(0))
    isAccum(x9806_d1) = true
    bufferDepthOf(x9806_d1) = 1
    val x9807 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x9807").ctrl(x9918).srcCtx("CharRNN.scala:92:52") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x9808 = CounterChain(List(x9807)).name("x9808").ctrl(x9918).srcCtx("CharRNN.scala:92:89") // CounterChainNew(List(x9807))
    val x9823 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9808).name("x9823").ctrl(x9918).srcCtx("CharRNN.scala:92:89") // UnrolledReduce(List(b5882, b5883, b5835),x9808,x9806,Block((x9806) => Const(())),List(List(b5887)),List(List(b5888)))
    val b5887 = CounterIter(x9807, None).name("b5887").ctrl(x9823) // b5887
    val b5888 = Const(true).name("b5888").ctrl(x9823) // b5888
    val x9809 = OpDef(op=BitAnd, inputs=List(b5888, b5882)).name("x9809").ctrl(x9823).srcCtx("UnrollingBase.scala:28:66") // And(b5888,b5882)
    val x9810 = OpDef(op=BitAnd, inputs=List(b5883, b5835)).name("x9810").ctrl(x9823).srcCtx("UnrollingBase.scala:28:66") // And(b5883,b5835)
    val x9811 = OpDef(op=BitAnd, inputs=List(x9809, x9810)).name("x9811").ctrl(x9823).srcCtx("UnrollingBase.scala:28:66") // And(x9809,x9810)
    val x9812 = LoadBanks(List(x9503_d1_b0), List(b5880, b5887)).name("x9812").ctrl(x9823).srcCtx("CharRNN.scala:92:66") // ParSRAMLoad(x9503,List(List(b5880, b5887)),List(x9811))
    val x9813 = x9812 // x9813 = VectorApply(x9812,0)
    val x9814 = LoadBanks(List(x9491_d0_b0), List(b5887, b5881)).name("x9814").ctrl(x9823).srcCtx("CharRNN.scala:92:80") // ParSRAMLoad(x9491,List(List(b5887, b5881)),List(x9811))
    val x9815 = x9814 // x9815 = VectorApply(x9814,0)
    val x9816 = OpDef(op=FixMul, inputs=List(x9813, x9815)).name("x9816").ctrl(x9823).srcCtx("CharRNN.scala:92:73") // FixMul(x9813,x9815)
    val x9817 = ReadMem(x9806_d1).name("x9817").ctrl(x9823).srcCtx("CharRNN.scala:92:89") // RegRead(x9806)
    val x9818 = OpDef(op=FixEql, inputs=List(b5887, Const(0))).name("x9818").ctrl(x9823).srcCtx("CharRNN.scala:92:89") // FixEql(b5887,Const(0))
    val x9819 = ReduceAccumOp(op=FixAdd, input=x9816, accum=x9817).name("x9819").ctrl(x9823).srcCtx("CharRNN.scala:92:91") // FixAdd(x9816,x9817)
    val x9820 = OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x9820").ctrl(x9823).srcCtx("UnrollingBase.scala:28:66") // And(b5882,b5883)
    val x9821 = OpDef(op=BitAnd, inputs=List(x9820, b5835)).name("x9821").ctrl(x9823).srcCtx("UnrollingBase.scala:28:66") // And(x9820,b5835)
    val x9822_x9806_d0 = WriteMem(x9806_d0, x9819).name("x9822_x9806_d0").ctrl(x9823).srcCtx("CharRNN.scala:92:89") // RegWrite(x9806,x9819,x9821)
    val x9822_x9806_d1 = WriteMem(x9806_d1, x9819).name("x9822_x9806_d1").ctrl(x9823).srcCtx("CharRNN.scala:92:89") // RegWrite(x9806,x9819,x9821)
    val x9824 = Reg(init=Some(0.0)).name("x9824").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9824 = RegNew(Const(0))
    isAccum(x9824) = false
    bufferDepthOf(x9824) = 3
    val x9825_d0 = Reg(init=Some(0.0)).name("x9825_d0").ctrl(x9918).srcCtx("CharRNN.scala:94:36:h2h_prod") // x9825 = RegNew(Const(0))
    isAccum(x9825_d0) = false
    bufferDepthOf(x9825_d0) = 2
    val x9825_d1 = Reg(init=Some(0.0)).name("x9825_d1").ctrl(x9918).srcCtx("CharRNN.scala:94:36:h2h_prod") // x9825 = RegNew(Const(0))
    isAccum(x9825_d1) = true
    bufferDepthOf(x9825_d1) = 1
    val x9832 = UnitController(style=SeqPipe, level=InnerControl).name("x9832").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // UnitPipe(List(b5882, b5883, b5835),Block(Const(())))
    val x9826 = OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x9826").ctrl(x9832).srcCtx("UnrollingBase.scala:28:66") // And(b5882,b5883)
    val x9827 = OpDef(op=BitAnd, inputs=List(x9826, b5835)).name("x9827").ctrl(x9832).srcCtx("UnrollingBase.scala:28:66") // And(x9826,b5835)
    val x9828 = LoadBanks(List(x9492_d0_b0), List(b5881)).name("x9828").ctrl(x9832).srcCtx("CharRNN.scala:93:30") // SRAMLoad(x9492,ArrayBuffer(Const(512)),List(b5881),Const(0),x9827)
    val x9829 = ReadMem(x9806_d0).name("x9829").ctrl(x9832).srcCtx("CharRNN.scala:93:45") // RegRead(x9806)
    val x9830 = OpDef(op=FixAdd, inputs=List(x9828, x9829)).name("x9830").ctrl(x9832).srcCtx("CharRNN.scala:93:34:i2h_ele") // FixAdd(x9828,x9829)
    val x9831_x9824 = WriteMem(x9824, x9830).name("x9831_x9824").ctrl(x9832).srcCtx("CharRNN.scala:91:46") // RegWrite(x9824,x9830,x9827)
    val x9833 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x9833").ctrl(x9918).srcCtx("CharRNN.scala:94:50") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x9834 = CounterChain(List(x9833)).name("x9834").ctrl(x9918).srcCtx("CharRNN.scala:94:87") // CounterChainNew(List(x9833))
    val x9849 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9834).name("x9849").ctrl(x9918).srcCtx("CharRNN.scala:94:87") // UnrolledReduce(List(b5882, b5883, b5835),x9834,x9825,Block((x9825) => Const(())),List(List(b5915)),List(List(b5916)))
    val b5915 = CounterIter(x9833, None).name("b5915").ctrl(x9849) // b5915
    val b5916 = Const(true).name("b5916").ctrl(x9849) // b5916
    val x9835 = OpDef(op=BitAnd, inputs=List(b5916, b5882)).name("x9835").ctrl(x9849).srcCtx("UnrollingBase.scala:28:66") // And(b5916,b5882)
    val x9836 = OpDef(op=BitAnd, inputs=List(b5883, b5835)).name("x9836").ctrl(x9849).srcCtx("UnrollingBase.scala:28:66") // And(b5883,b5835)
    val x9837 = OpDef(op=BitAnd, inputs=List(x9835, x9836)).name("x9837").ctrl(x9849).srcCtx("UnrollingBase.scala:28:66") // And(x9835,x9836)
    val x9838 = LoadBanks(List(x9502_d3_b0), List(b5880, b5915)).name("x9838").ctrl(x9849).srcCtx("CharRNN.scala:94:64") // ParSRAMLoad(x9502,List(List(b5880, b5915)),List(x9837))
    val x9839 = x9838 // x9839 = VectorApply(x9838,0)
    val x9840 = LoadBanks(List(x9493_d0_b0), List(b5915, b5881)).name("x9840").ctrl(x9849).srcCtx("CharRNN.scala:94:78") // ParSRAMLoad(x9493,List(List(b5915, b5881)),List(x9837))
    val x9841 = x9840 // x9841 = VectorApply(x9840,0)
    val x9842 = OpDef(op=FixMul, inputs=List(x9839, x9841)).name("x9842").ctrl(x9849).srcCtx("CharRNN.scala:94:71") // FixMul(x9839,x9841)
    val x9843 = ReadMem(x9825_d1).name("x9843").ctrl(x9849).srcCtx("CharRNN.scala:94:87") // RegRead(x9825)
    val x9844 = OpDef(op=FixEql, inputs=List(b5915, Const(0))).name("x9844").ctrl(x9849).srcCtx("CharRNN.scala:94:87") // FixEql(b5915,Const(0))
    val x9845 = ReduceAccumOp(op=FixAdd, input=x9842, accum=x9843).name("x9845").ctrl(x9849).srcCtx("CharRNN.scala:94:89") // FixAdd(x9842,x9843)
    val x9846 = OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x9846").ctrl(x9849).srcCtx("UnrollingBase.scala:28:66") // And(b5882,b5883)
    val x9847 = OpDef(op=BitAnd, inputs=List(x9846, b5835)).name("x9847").ctrl(x9849).srcCtx("UnrollingBase.scala:28:66") // And(x9846,b5835)
    val x9848_x9825_d0 = WriteMem(x9825_d0, x9845).name("x9848_x9825_d0").ctrl(x9849).srcCtx("CharRNN.scala:94:87") // RegWrite(x9825,x9845,x9847)
    val x9848_x9825_d1 = WriteMem(x9825_d1, x9845).name("x9848_x9825_d1").ctrl(x9849).srcCtx("CharRNN.scala:94:87") // RegWrite(x9825,x9845,x9847)
    val x9850_d0 = Reg(init=Some(0.0)).name("x9850_d0").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9850 = RegNew(Const(0))
    isAccum(x9850_d0) = false
    bufferDepthOf(x9850_d0) = 2
    val x9850_d1 = Reg(init=Some(0.0)).name("x9850_d1").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9850 = RegNew(Const(0))
    isAccum(x9850_d1) = false
    bufferDepthOf(x9850_d1) = 2
    val x9850_d2 = Reg(init=Some(0.0)).name("x9850_d2").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9850 = RegNew(Const(0))
    isAccum(x9850_d2) = false
    bufferDepthOf(x9850_d2) = 2
    val x9850_d3 = Reg(init=Some(0.0)).name("x9850_d3").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9850 = RegNew(Const(0))
    isAccum(x9850_d3) = false
    bufferDepthOf(x9850_d3) = 2
    val x9851_d0 = Reg(init=Some(false)).name("x9851_d0").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9851 = RegNew(Const(false))
    isAccum(x9851_d0) = false
    bufferDepthOf(x9851_d0) = 2
    val x9851_d1 = Reg(init=Some(false)).name("x9851_d1").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9851 = RegNew(Const(false))
    isAccum(x9851_d1) = false
    bufferDepthOf(x9851_d1) = 2
    val x9852_d0 = Reg(init=Some(false)).name("x9852_d0").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9852 = RegNew(Const(false))
    isAccum(x9852_d0) = false
    bufferDepthOf(x9852_d0) = 2
    val x9852_d1 = Reg(init=Some(false)).name("x9852_d1").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9852 = RegNew(Const(false))
    isAccum(x9852_d1) = false
    bufferDepthOf(x9852_d1) = 2
    val x9853_d0 = Reg(init=Some(false)).name("x9853_d0").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9853 = RegNew(Const(false))
    isAccum(x9853_d0) = false
    bufferDepthOf(x9853_d0) = 2
    val x9853_d1 = Reg(init=Some(false)).name("x9853_d1").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9853 = RegNew(Const(false))
    isAccum(x9853_d1) = false
    bufferDepthOf(x9853_d1) = 2
    val x9854_d0 = Reg(init=Some(false)).name("x9854_d0").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9854 = RegNew(Const(false))
    isAccum(x9854_d0) = false
    bufferDepthOf(x9854_d0) = 2
    val x9854_d1 = Reg(init=Some(false)).name("x9854_d1").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // x9854 = RegNew(Const(false))
    isAccum(x9854_d1) = false
    bufferDepthOf(x9854_d1) = 2
    val x9881 = UnitController(style=SeqPipe, level=InnerControl).name("x9881").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // UnitPipe(List(b5882, b5883, b5835),Block(Const(())))
    val x9855 = OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x9855").ctrl(x9881).srcCtx("UnrollingBase.scala:28:66") // And(b5882,b5883)
    val x9856 = OpDef(op=BitAnd, inputs=List(x9855, b5835)).name("x9856").ctrl(x9881).srcCtx("UnrollingBase.scala:28:66") // And(x9855,b5835)
    val x9857 = LoadBanks(List(x9494_d0_b0), List(b5881)).name("x9857").ctrl(x9881).srcCtx("CharRNN.scala:95:26") // SRAMLoad(x9494,ArrayBuffer(Const(512)),List(b5881),Const(0),x9856)
    val x9858 = ReadMem(x9825_d0).name("x9858").ctrl(x9881).srcCtx("CharRNN.scala:95:41") // RegRead(x9825)
    val x9859 = OpDef(op=FixAdd, inputs=List(x9857, x9858)).name("x9859").ctrl(x9881).srcCtx("CharRNN.scala:95:30") // FixAdd(x9857,x9858)
    val x9860 = ReadMem(x9824).name("x9860").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegRead(x9824)
    val x9861 = OpDef(op=FixAdd, inputs=List(x9859, x9860)).name("x9861").ctrl(x9881).srcCtx("CharRNN.scala:95:47:ele") // FixAdd(x9859,x9860)
    val x9862 = OpDef(op=FixLt, inputs=List(b5881, Const(128))).name("x9862").ctrl(x9881).srcCtx("CharRNN.scala:96:17") // FixLt(b5881,Const(128))
    val x9863 = OpDef(op=BitNot, inputs=List(x9862)).name("x9863").ctrl(x9881).srcCtx("CharRNN.scala:96:11") // Not(x9862)
    val x9864 = OpDef(op=FixLeq, inputs=List(Const(128), b5881)).name("x9864").ctrl(x9881).srcCtx("CharRNN.scala:98:29") // FixLeq(Const(128),b5881)
    val x9865 = OpDef(op=FixLt, inputs=List(b5881, Const(256))).name("x9865").ctrl(x9881).srcCtx("CharRNN.scala:98:39") // FixLt(b5881,Const(256))
    val x9866 = OpDef(op=BitAnd, inputs=List(x9864, x9865)).name("x9866").ctrl(x9881).srcCtx("CharRNN.scala:98:34") // And(x9864,x9865)
    val x9867 = OpDef(op=BitAnd, inputs=List(x9866, x9863)).name("x9867").ctrl(x9881).srcCtx("CharRNN.scala:96:11") // And(x9866,x9863)
    val x9868 = OpDef(op=BitNot, inputs=List(x9866)).name("x9868").ctrl(x9881).srcCtx("CharRNN.scala:96:11") // Not(x9866)
    val x9869 = OpDef(op=BitAnd, inputs=List(x9868, x9863)).name("x9869").ctrl(x9881).srcCtx("CharRNN.scala:96:11") // And(x9868,x9863)
    val x9870 = OpDef(op=FixLeq, inputs=List(Const(256), b5881)).name("x9870").ctrl(x9881).srcCtx("CharRNN.scala:100:33") // FixLeq(Const(256),b5881)
    val x9871 = OpDef(op=FixLt, inputs=List(b5881, Const(384))).name("x9871").ctrl(x9881).srcCtx("CharRNN.scala:100:43") // FixLt(b5881,Const(384))
    val x9872 = OpDef(op=BitAnd, inputs=List(x9870, x9871)).name("x9872").ctrl(x9881).srcCtx("CharRNN.scala:100:38") // And(x9870,x9871)
    val x9873 = OpDef(op=BitAnd, inputs=List(x9872, x9869)).name("x9873").ctrl(x9881).srcCtx("CharRNN.scala:96:11") // And(x9872,x9869)
    val x9874 = OpDef(op=BitNot, inputs=List(x9872)).name("x9874").ctrl(x9881).srcCtx("CharRNN.scala:96:11") // Not(x9872)
    val x9875 = OpDef(op=BitAnd, inputs=List(x9874, x9869)).name("x9875").ctrl(x9881).srcCtx("CharRNN.scala:96:11") // And(x9874,x9869)
    val x9876_x9850_d0 = WriteMem(x9850_d0, x9861).name("x9876_x9850_d0").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9850,x9861,x9856)
    val x9876_x9850_d1 = WriteMem(x9850_d1, x9861).name("x9876_x9850_d1").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9850,x9861,x9856)
    val x9876_x9850_d2 = WriteMem(x9850_d2, x9861).name("x9876_x9850_d2").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9850,x9861,x9856)
    val x9876_x9850_d3 = WriteMem(x9850_d3, x9861).name("x9876_x9850_d3").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9850,x9861,x9856)
    val x9877_x9851_d0 = WriteMem(x9851_d0, x9862).name("x9877_x9851_d0").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9851,x9862,x9856)
    val x9877_x9851_d1 = WriteMem(x9851_d1, x9862).name("x9877_x9851_d1").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9851,x9862,x9856)
    val x9878_x9852_d0 = WriteMem(x9852_d0, x9867).name("x9878_x9852_d0").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9852,x9867,x9856)
    val x9878_x9852_d1 = WriteMem(x9852_d1, x9867).name("x9878_x9852_d1").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9852,x9867,x9856)
    val x9879_x9853_d0 = WriteMem(x9853_d0, x9873).name("x9879_x9853_d0").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9853,x9873,x9856)
    val x9879_x9853_d1 = WriteMem(x9853_d1, x9873).name("x9879_x9853_d1").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9853,x9873,x9856)
    val x9880_x9854_d0 = WriteMem(x9854_d0, x9875).name("x9880_x9854_d0").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9854,x9875,x9856)
    val x9880_x9854_d1 = WriteMem(x9854_d1, x9875).name("x9880_x9854_d1").ctrl(x9881).srcCtx("CharRNN.scala:91:46") // RegWrite(x9854,x9875,x9856)
    val x9882 = ReadMem(x9854_d1).name("x9882").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // RegRead(x9854)
    val x9883 = ReadMem(x9853_d1).name("x9883").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // RegRead(x9853)
    val x9884 = ReadMem(x9852_d1).name("x9884").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // RegRead(x9852)
    val x9885 = ReadMem(x9851_d1).name("x9885").ctrl(x9918).srcCtx("CharRNN.scala:91:46") // RegRead(x9851)
    val x9917 = UnitController(style=ForkSwitch, level=OuterControl).name("x9917").ctrl(x9918).srcCtx("CharRNN.scala:96:11") // //TODO Switch(Block(x9916),List(x9885, x9884, x9883, x9882),List(x9892, x9900, x9908, x9916))
    val x9892 = UnitController(style=MetaPipe, level=InnerControl).name("x9892").ctrl(x9917).srcCtx("CharRNN.scala:96:11") // //TODO SwitchCase(Block(x9891))
    val x9886 = ReadMem(x9851_d0).name("x9886").ctrl(x9892).srcCtx("CharRNN.scala:91:46") // RegRead(x9851)
    val x9887 = ReadMem(x9850_d3).name("x9887").ctrl(x9892).srcCtx("CharRNN.scala:91:46") // RegRead(x9850)
    val x9888 = OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x9888").ctrl(x9892).srcCtx("UnrollingBase.scala:28:66") // And(b5882,b5883)
    val x9889 = OpDef(op=BitAnd, inputs=List(x9888, b5835)).name("x9889").ctrl(x9892).srcCtx("UnrollingBase.scala:28:66") // And(x9888,b5835)
    val x9890 = OpDef(op=BitAnd, inputs=List(x9886, x9889)).name("x9890").ctrl(x9892) // And(x9886,x9889)
    val x9891 = StoreBanks(List(x9504_d0_b0, x9504_d1_b0), List(b5880, b5881), x9887).name("x9891").ctrl(x9892).srcCtx("CharRNN.scala:97:27") // SRAMStore(x9504,ArrayBuffer(Const(1), Const(128)),List(b5880, b5881),Const(0),x9887,x9890)
    val x9900 = UnitController(style=MetaPipe, level=InnerControl).name("x9900").ctrl(x9917).srcCtx("CharRNN.scala:96:11") // //TODO SwitchCase(Block(x9899))
    val x9893 = OpDef(op=FixSub, inputs=List(b5881, Const(128))).name("x9893").ctrl(x9900).srcCtx("CharRNN.scala:99:30") // FixSub(b5881,Const(128))
    val x9894 = ReadMem(x9852_d0).name("x9894").ctrl(x9900).srcCtx("CharRNN.scala:91:46") // RegRead(x9852)
    val x9895 = ReadMem(x9850_d2).name("x9895").ctrl(x9900).srcCtx("CharRNN.scala:91:46") // RegRead(x9850)
    val x9896 = OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x9896").ctrl(x9900).srcCtx("UnrollingBase.scala:28:66") // And(b5882,b5883)
    val x9897 = OpDef(op=BitAnd, inputs=List(x9896, b5835)).name("x9897").ctrl(x9900).srcCtx("UnrollingBase.scala:28:66") // And(x9896,b5835)
    val x9898 = OpDef(op=BitAnd, inputs=List(x9894, x9897)).name("x9898").ctrl(x9900) // And(x9894,x9897)
    val x9899 = StoreBanks(List(x9505_d0_b0, x9505_d1_b0), List(b5880, x9893), x9895).name("x9899").ctrl(x9900).srcCtx("CharRNN.scala:99:42") // SRAMStore(x9505,ArrayBuffer(Const(1), Const(128)),List(b5880, x9893),Const(0),x9895,x9898)
    val x9908 = UnitController(style=MetaPipe, level=InnerControl).name("x9908").ctrl(x9917).srcCtx("CharRNN.scala:96:11") // //TODO SwitchCase(Block(x9907))
    val x9901 = OpDef(op=FixSub, inputs=List(b5881, Const(256))).name("x9901").ctrl(x9908).srcCtx("CharRNN.scala:101:27") // FixSub(b5881,Const(256))
    val x9902 = ReadMem(x9853_d0).name("x9902").ctrl(x9908).srcCtx("CharRNN.scala:91:46") // RegRead(x9853)
    val x9903 = ReadMem(x9850_d1).name("x9903").ctrl(x9908).srcCtx("CharRNN.scala:91:46") // RegRead(x9850)
    val x9904 = OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x9904").ctrl(x9908).srcCtx("UnrollingBase.scala:28:66") // And(b5882,b5883)
    val x9905 = OpDef(op=BitAnd, inputs=List(x9904, b5835)).name("x9905").ctrl(x9908).srcCtx("UnrollingBase.scala:28:66") // And(x9904,b5835)
    val x9906 = OpDef(op=BitAnd, inputs=List(x9902, x9905)).name("x9906").ctrl(x9908) // And(x9902,x9905)
    val x9907 = StoreBanks(List(x9506_d0_b0, x9506_d1_b0), List(b5880, x9901), x9903).name("x9907").ctrl(x9908).srcCtx("CharRNN.scala:101:43") // SRAMStore(x9506,ArrayBuffer(Const(1), Const(128)),List(b5880, x9901),Const(0),x9903,x9906)
    val x9916 = UnitController(style=MetaPipe, level=InnerControl).name("x9916").ctrl(x9917).srcCtx("CharRNN.scala:96:11") // //TODO SwitchCase(Block(x9915))
    val x9909 = OpDef(op=FixSub, inputs=List(b5881, Const(384))).name("x9909").ctrl(x9916).srcCtx("CharRNN.scala:103:31") // FixSub(b5881,Const(384))
    val x9910 = ReadMem(x9854_d0).name("x9910").ctrl(x9916).srcCtx("CharRNN.scala:91:46") // RegRead(x9854)
    val x9911 = ReadMem(x9850_d0).name("x9911").ctrl(x9916).srcCtx("CharRNN.scala:91:46") // RegRead(x9850)
    val x9912 = OpDef(op=BitAnd, inputs=List(b5882, b5883)).name("x9912").ctrl(x9916).srcCtx("UnrollingBase.scala:28:66") // And(b5882,b5883)
    val x9913 = OpDef(op=BitAnd, inputs=List(x9912, b5835)).name("x9913").ctrl(x9916).srcCtx("UnrollingBase.scala:28:66") // And(x9912,b5835)
    val x9914 = OpDef(op=BitAnd, inputs=List(x9910, x9913)).name("x9914").ctrl(x9916) // And(x9910,x9913)
    val x9915 = StoreBanks(List(x9507_d0_b0, x9507_d1_b0), List(b5880, x9909), x9911).name("x9915").ctrl(x9916).srcCtx("CharRNN.scala:103:47") // SRAMStore(x9507,ArrayBuffer(Const(1), Const(128)),List(b5880, x9909),Const(0),x9911,x9914)
    val x9919 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x9919").ctrl(x10146).srcCtx("CharRNN.scala:107:44") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x9920 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9920").ctrl(x10146).srcCtx("CharRNN.scala:107:29") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9921 = CounterChain(List(x9920,x9919)).name("x9921").ctrl(x10146).srcCtx("CharRNN.scala:107:50") // CounterChainNew(List(x9920, x9919))
    val x9940 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9921).name("x9940").ctrl(x10146).srcCtx("CharRNN.scala:107:50") // UnrolledForeach(List(b5835),x9921,Block(Const(())),List(List(b6004), List(b6005)),List(List(b6006), List(b6007)))
    val b6004 = CounterIter(x9920, Some(0)).name("b6004").ctrl(x9940) // b6004
    val b6006 = Const(true).name("b6006").ctrl(x9940) // b6006
    val b6005 = CounterIter(x9919, None).name("b6005").ctrl(x9940) // b6005
    val b6007 = Const(true).name("b6007").ctrl(x9940) // b6007
    val x9922 = OpDef(op=BitAnd, inputs=List(b6006, b6007)).name("x9922").ctrl(x9940).srcCtx("UnrollingBase.scala:28:66") // And(b6006,b6007)
    val x9923 = OpDef(op=BitAnd, inputs=List(x9922, b5835)).name("x9923").ctrl(x9940).srcCtx("UnrollingBase.scala:28:66") // And(x9922,b5835)
    val x9924 = LoadBanks(List(x9501_d1_b0), List(b6004, b6005)).name("x9924").ctrl(x9940).srcCtx("CharRNN.scala:108:21") // ParSRAMLoad(x9501,List(List(b6004, b6005)),List(x9923))
    val x9925 = x9924 // x9925 = VectorApply(x9924,0)
    val x9926 = LoadBanks(List(x9505_d1_b0), List(b6004, b6005)).name("x9926").ctrl(x9940).srcCtx("CharRNN.scala:108:41") // ParSRAMLoad(x9505,List(List(b6004, b6005)),List(x9923))
    val x9927 = x9926 // x9927 = VectorApply(x9926,0)
    val x9928 = OpDef(op=FixMul, inputs=List(x9925, x9927)).name("x9928").ctrl(x9940).srcCtx("CharRNN.scala:108:28") // FixMul(x9925,x9927)
    val x9929 = LoadBanks(List(x9504_d1_b0), List(b6004, b6005)).name("x9929").ctrl(x9940).srcCtx("CharRNN.scala:108:57") // ParSRAMLoad(x9504,List(List(b6004, b6005)),List(x9923))
    val x9930 = x9929 // x9930 = VectorApply(x9929,0)
    val x9931 = LoadBanks(List(x9507_d1_b0), List(b6004, b6005)).name("x9931").ctrl(x9940).srcCtx("CharRNN.scala:108:78") // ParSRAMLoad(x9507,List(List(b6004, b6005)),List(x9923))
    val x9932 = x9931 // x9932 = VectorApply(x9931,0)
    val x9933 = OpDef(op=FixMul, inputs=List(x9930, x9932)).name("x9933").ctrl(x9940).srcCtx("CharRNN.scala:108:64") // FixMul(x9930,x9932)
    val x9934 = OpDef(op=FixAdd, inputs=List(x9928, x9933)).name("x9934").ctrl(x9940).srcCtx("CharRNN.scala:108:48:cc") // FixAdd(x9928,x9933)
    val x9935 = StoreBanks(List(x9501_d0_b0, x9501_d1_b0), List(b6004, b6005), x9934).name("x9935").ctrl(x9940).srcCtx("CharRNN.scala:109:19") // ParSRAMStore(x9501,List(List(b6004, b6005)),List(x9934),List(x9923))
    val x9936 = LoadBanks(List(x9506_d1_b0), List(b6004, b6005)).name("x9936").ctrl(x9940).srcCtx("CharRNN.scala:110:41") // ParSRAMLoad(x9506,List(List(b6004, b6005)),List(x9923))
    val x9937 = x9936 // x9937 = VectorApply(x9936,0)
    val x9938 = OpDef(op=FixMul, inputs=List(x9934, x9937)).name("x9938").ctrl(x9940).srcCtx("CharRNN.scala:110:31") // FixMul(x9934,x9937)
    val x9939 = StoreBanks(List(x9502_d0_b0, x9502_d1_b0, x9502_d2_b0, x9502_d3_b0), List(b6004, b6005), x9938).name("x9939").ctrl(x9940).srcCtx("CharRNN.scala:110:19") // ParSRAMStore(x9502,List(List(b6004, b6005)),List(x9938),List(x9923))
    val x9941 = Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9941").ctrl(x10146).srcCtx("CharRNN.scala:114:40") // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9942 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9942").ctrl(x10146).srcCtx("CharRNN.scala:114:29") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9943 = CounterChain(List(x9942,x9941)).name("x9943").ctrl(x10146).srcCtx("CharRNN.scala:114:46") // CounterChainNew(List(x9942, x9941))
    val x10056 = LoopController(style=MetaPipe, level=OuterControl, cchain=x9943).name("x10056").ctrl(x10146).srcCtx("CharRNN.scala:114:46") // UnrolledForeach(List(b5835),x9943,Block(Const(())),List(List(b6030), List(b6031)),List(List(b6032), List(b6033)))
    val b6030 = CounterIter(x9942, Some(0)).name("b6030").ctrl(x10056) // b6030
    val b6032 = Const(true).name("b6032").ctrl(x10056) // b6032
    val b6031 = CounterIter(x9941, Some(0)).name("b6031").ctrl(x10056) // b6031
    val b6033 = Const(true).name("b6033").ctrl(x10056) // b6033
    val x9944_d0 = Reg(init=Some(0.0)).name("x9944_d0").ctrl(x10056).srcCtx("CharRNN.scala:115:36:i2h_prod") // x9944 = RegNew(Const(0))
    isAccum(x9944_d0) = false
    bufferDepthOf(x9944_d0) = 2
    val x9944_d1 = Reg(init=Some(0.0)).name("x9944_d1").ctrl(x10056).srcCtx("CharRNN.scala:115:36:i2h_prod") // x9944 = RegNew(Const(0))
    isAccum(x9944_d1) = true
    bufferDepthOf(x9944_d1) = 1
    val x9945 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x9945").ctrl(x10056).srcCtx("CharRNN.scala:115:50") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x9946 = CounterChain(List(x9945)).name("x9946").ctrl(x10056).srcCtx("CharRNN.scala:115:87") // CounterChainNew(List(x9945))
    val x9961 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9946).name("x9961").ctrl(x10056).srcCtx("CharRNN.scala:115:87") // UnrolledReduce(List(b6032, b6033, b5835),x9946,x9944,Block((x9944) => Const(())),List(List(b6037)),List(List(b6038)))
    val b6037 = CounterIter(x9945, None).name("b6037").ctrl(x9961) // b6037
    val b6038 = Const(true).name("b6038").ctrl(x9961) // b6038
    val x9947 = OpDef(op=BitAnd, inputs=List(b6038, b6032)).name("x9947").ctrl(x9961).srcCtx("UnrollingBase.scala:28:66") // And(b6038,b6032)
    val x9948 = OpDef(op=BitAnd, inputs=List(b6033, b5835)).name("x9948").ctrl(x9961).srcCtx("UnrollingBase.scala:28:66") // And(b6033,b5835)
    val x9949 = OpDef(op=BitAnd, inputs=List(x9947, x9948)).name("x9949").ctrl(x9961).srcCtx("UnrollingBase.scala:28:66") // And(x9947,x9948)
    val x9950 = LoadBanks(List(x9503_d0_b0), List(b6030, b6037)).name("x9950").ctrl(x9961).srcCtx("CharRNN.scala:115:64") // ParSRAMLoad(x9503,List(List(b6030, b6037)),List(x9949))
    val x9951 = x9950 // x9951 = VectorApply(x9950,0)
    val x9952 = LoadBanks(List(x9495_d0_b0), List(b6037, b6031)).name("x9952").ctrl(x9961).srcCtx("CharRNN.scala:115:78") // ParSRAMLoad(x9495,List(List(b6037, b6031)),List(x9949))
    val x9953 = x9952 // x9953 = VectorApply(x9952,0)
    val x9954 = OpDef(op=FixMul, inputs=List(x9951, x9953)).name("x9954").ctrl(x9961).srcCtx("CharRNN.scala:115:71") // FixMul(x9951,x9953)
    val x9955 = ReadMem(x9944_d1).name("x9955").ctrl(x9961).srcCtx("CharRNN.scala:115:87") // RegRead(x9944)
    val x9956 = OpDef(op=FixEql, inputs=List(b6037, Const(0))).name("x9956").ctrl(x9961).srcCtx("CharRNN.scala:115:87") // FixEql(b6037,Const(0))
    val x9957 = ReduceAccumOp(op=FixAdd, input=x9954, accum=x9955).name("x9957").ctrl(x9961).srcCtx("CharRNN.scala:115:89") // FixAdd(x9954,x9955)
    val x9958 = OpDef(op=BitAnd, inputs=List(b6032, b6033)).name("x9958").ctrl(x9961).srcCtx("UnrollingBase.scala:28:66") // And(b6032,b6033)
    val x9959 = OpDef(op=BitAnd, inputs=List(x9958, b5835)).name("x9959").ctrl(x9961).srcCtx("UnrollingBase.scala:28:66") // And(x9958,b5835)
    val x9960_x9944_d0 = WriteMem(x9944_d0, x9957).name("x9960_x9944_d0").ctrl(x9961).srcCtx("CharRNN.scala:115:87") // RegWrite(x9944,x9957,x9959)
    val x9960_x9944_d1 = WriteMem(x9944_d1, x9957).name("x9960_x9944_d1").ctrl(x9961).srcCtx("CharRNN.scala:115:87") // RegWrite(x9944,x9957,x9959)
    val x9962 = Reg(init=Some(0.0)).name("x9962").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9962 = RegNew(Const(0))
    isAccum(x9962) = false
    bufferDepthOf(x9962) = 3
    val x9963_d0 = Reg(init=Some(0.0)).name("x9963_d0").ctrl(x10056).srcCtx("CharRNN.scala:117:36:h2h_prod") // x9963 = RegNew(Const(0))
    isAccum(x9963_d0) = false
    bufferDepthOf(x9963_d0) = 2
    val x9963_d1 = Reg(init=Some(0.0)).name("x9963_d1").ctrl(x10056).srcCtx("CharRNN.scala:117:36:h2h_prod") // x9963 = RegNew(Const(0))
    isAccum(x9963_d1) = true
    bufferDepthOf(x9963_d1) = 1
    val x9970 = UnitController(style=SeqPipe, level=InnerControl).name("x9970").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // UnitPipe(List(b6032, b6033, b5835),Block(Const(())))
    val x9964 = OpDef(op=BitAnd, inputs=List(b6032, b6033)).name("x9964").ctrl(x9970).srcCtx("UnrollingBase.scala:28:66") // And(b6032,b6033)
    val x9965 = OpDef(op=BitAnd, inputs=List(x9964, b5835)).name("x9965").ctrl(x9970).srcCtx("UnrollingBase.scala:28:66") // And(x9964,b5835)
    val x9966 = LoadBanks(List(x9496_d0_b0), List(b6031)).name("x9966").ctrl(x9970).srcCtx("CharRNN.scala:116:30") // SRAMLoad(x9496,ArrayBuffer(Const(512)),List(b6031),Const(0),x9965)
    val x9967 = ReadMem(x9944_d0).name("x9967").ctrl(x9970).srcCtx("CharRNN.scala:116:45") // RegRead(x9944)
    val x9968 = OpDef(op=FixAdd, inputs=List(x9966, x9967)).name("x9968").ctrl(x9970).srcCtx("CharRNN.scala:116:34:i2h_ele") // FixAdd(x9966,x9967)
    val x9969_x9962 = WriteMem(x9962, x9968).name("x9969_x9962").ctrl(x9970).srcCtx("CharRNN.scala:114:46") // RegWrite(x9962,x9968,x9965)
    val x9971 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x9971").ctrl(x10056).srcCtx("CharRNN.scala:117:50") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x9972 = CounterChain(List(x9971)).name("x9972").ctrl(x10056).srcCtx("CharRNN.scala:117:87") // CounterChainNew(List(x9971))
    val x9987 = LoopController(style=InnerPipe, level=InnerControl, cchain=x9972).name("x9987").ctrl(x10056).srcCtx("CharRNN.scala:117:87") // UnrolledReduce(List(b6032, b6033, b5835),x9972,x9963,Block((x9963) => Const(())),List(List(b6065)),List(List(b6066)))
    val b6065 = CounterIter(x9971, None).name("b6065").ctrl(x9987) // b6065
    val b6066 = Const(true).name("b6066").ctrl(x9987) // b6066
    val x9973 = OpDef(op=BitAnd, inputs=List(b6066, b6032)).name("x9973").ctrl(x9987).srcCtx("UnrollingBase.scala:28:66") // And(b6066,b6032)
    val x9974 = OpDef(op=BitAnd, inputs=List(b6033, b5835)).name("x9974").ctrl(x9987).srcCtx("UnrollingBase.scala:28:66") // And(b6033,b5835)
    val x9975 = OpDef(op=BitAnd, inputs=List(x9973, x9974)).name("x9975").ctrl(x9987).srcCtx("UnrollingBase.scala:28:66") // And(x9973,x9974)
    val x9976 = LoadBanks(List(x9502_d2_b0), List(b6030, b6065)).name("x9976").ctrl(x9987).srcCtx("CharRNN.scala:117:64") // ParSRAMLoad(x9502,List(List(b6030, b6065)),List(x9975))
    val x9977 = x9976 // x9977 = VectorApply(x9976,0)
    def split1 = {
    val x9978 = LoadBanks(List(x9497_d0_b0), List(b6065, b6031)).name("x9978").ctrl(x9987).srcCtx("CharRNN.scala:117:78") // ParSRAMLoad(x9497,List(List(b6065, b6031)),List(x9975))
    val x9979 = x9978 // x9979 = VectorApply(x9978,0)
    val x9980 = OpDef(op=FixMul, inputs=List(x9977, x9979)).name("x9980").ctrl(x9987).srcCtx("CharRNN.scala:117:71") // FixMul(x9977,x9979)
    val x9981 = ReadMem(x9963_d1).name("x9981").ctrl(x9987).srcCtx("CharRNN.scala:117:87") // RegRead(x9963)
    val x9982 = OpDef(op=FixEql, inputs=List(b6065, Const(0))).name("x9982").ctrl(x9987).srcCtx("CharRNN.scala:117:87") // FixEql(b6065,Const(0))
    val x9983 = ReduceAccumOp(op=FixAdd, input=x9980, accum=x9981).name("x9983").ctrl(x9987).srcCtx("CharRNN.scala:117:89") // FixAdd(x9980,x9981)
    val x9984 = OpDef(op=BitAnd, inputs=List(b6032, b6033)).name("x9984").ctrl(x9987).srcCtx("UnrollingBase.scala:28:66") // And(b6032,b6033)
    val x9985 = OpDef(op=BitAnd, inputs=List(x9984, b5835)).name("x9985").ctrl(x9987).srcCtx("UnrollingBase.scala:28:66") // And(x9984,b5835)
    val x9986_x9963_d0 = WriteMem(x9963_d0, x9983).name("x9986_x9963_d0").ctrl(x9987).srcCtx("CharRNN.scala:117:87") // RegWrite(x9963,x9983,x9985)
    val x9986_x9963_d1 = WriteMem(x9963_d1, x9983).name("x9986_x9963_d1").ctrl(x9987).srcCtx("CharRNN.scala:117:87") // RegWrite(x9963,x9983,x9985)
    val x9988_d0 = Reg(init=Some(0.0)).name("x9988_d0").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9988 = RegNew(Const(0))
    isAccum(x9988_d0) = false
    bufferDepthOf(x9988_d0) = 2
    val x9988_d1 = Reg(init=Some(0.0)).name("x9988_d1").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9988 = RegNew(Const(0))
    isAccum(x9988_d1) = false
    bufferDepthOf(x9988_d1) = 2
    val x9988_d2 = Reg(init=Some(0.0)).name("x9988_d2").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9988 = RegNew(Const(0))
    isAccum(x9988_d2) = false
    bufferDepthOf(x9988_d2) = 2
    val x9988_d3 = Reg(init=Some(0.0)).name("x9988_d3").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9988 = RegNew(Const(0))
    isAccum(x9988_d3) = false
    bufferDepthOf(x9988_d3) = 2
    val x9989_d0 = Reg(init=Some(false)).name("x9989_d0").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9989 = RegNew(Const(false))
    isAccum(x9989_d0) = false
    bufferDepthOf(x9989_d0) = 2
    val x9989_d1 = Reg(init=Some(false)).name("x9989_d1").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9989 = RegNew(Const(false))
    isAccum(x9989_d1) = false
    bufferDepthOf(x9989_d1) = 2
    val x9990_d0 = Reg(init=Some(false)).name("x9990_d0").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9990 = RegNew(Const(false))
    isAccum(x9990_d0) = false
    bufferDepthOf(x9990_d0) = 2
    val x9990_d1 = Reg(init=Some(false)).name("x9990_d1").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9990 = RegNew(Const(false))
    isAccum(x9990_d1) = false
    bufferDepthOf(x9990_d1) = 2
    val x9991_d0 = Reg(init=Some(false)).name("x9991_d0").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9991 = RegNew(Const(false))
    isAccum(x9991_d0) = false
    bufferDepthOf(x9991_d0) = 2
    val x9991_d1 = Reg(init=Some(false)).name("x9991_d1").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9991 = RegNew(Const(false))
    isAccum(x9991_d1) = false
    bufferDepthOf(x9991_d1) = 2
    val x9992_d0 = Reg(init=Some(false)).name("x9992_d0").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9992 = RegNew(Const(false))
    isAccum(x9992_d0) = false
    bufferDepthOf(x9992_d0) = 2
    val x9992_d1 = Reg(init=Some(false)).name("x9992_d1").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // x9992 = RegNew(Const(false))
    isAccum(x9992_d1) = false
    bufferDepthOf(x9992_d1) = 2
    val x10019 = UnitController(style=SeqPipe, level=InnerControl).name("x10019").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // UnitPipe(List(b6032, b6033, b5835),Block(Const(())))
    val x9993 = OpDef(op=BitAnd, inputs=List(b6032, b6033)).name("x9993").ctrl(x10019).srcCtx("UnrollingBase.scala:28:66") // And(b6032,b6033)
    val x9994 = OpDef(op=BitAnd, inputs=List(x9993, b5835)).name("x9994").ctrl(x10019).srcCtx("UnrollingBase.scala:28:66") // And(x9993,b5835)
    val x9995 = LoadBanks(List(x9498_d0_b0), List(b6031)).name("x9995").ctrl(x10019).srcCtx("CharRNN.scala:118:26") // SRAMLoad(x9498,ArrayBuffer(Const(512)),List(b6031),Const(0),x9994)
    val x9996 = ReadMem(x9963_d0).name("x9996").ctrl(x10019).srcCtx("CharRNN.scala:118:41") // RegRead(x9963)
    val x9997 = OpDef(op=FixAdd, inputs=List(x9995, x9996)).name("x9997").ctrl(x10019).srcCtx("CharRNN.scala:118:30") // FixAdd(x9995,x9996)
    val x9998 = ReadMem(x9962).name("x9998").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegRead(x9962)
    val x9999 = OpDef(op=FixAdd, inputs=List(x9997, x9998)).name("x9999").ctrl(x10019).srcCtx("CharRNN.scala:118:47:ele") // FixAdd(x9997,x9998)
    val x10000 = OpDef(op=FixLt, inputs=List(b6031, Const(128))).name("x10000").ctrl(x10019).srcCtx("CharRNN.scala:119:17") // FixLt(b6031,Const(128))
    val x10001 = OpDef(op=BitNot, inputs=List(x10000)).name("x10001").ctrl(x10019).srcCtx("CharRNN.scala:119:11") // Not(x10000)
    val x10002 = OpDef(op=FixLeq, inputs=List(Const(128), b6031)).name("x10002").ctrl(x10019).srcCtx("CharRNN.scala:121:29") // FixLeq(Const(128),b6031)
    val x10003 = OpDef(op=FixLt, inputs=List(b6031, Const(256))).name("x10003").ctrl(x10019).srcCtx("CharRNN.scala:121:39") // FixLt(b6031,Const(256))
    val x10004 = OpDef(op=BitAnd, inputs=List(x10002, x10003)).name("x10004").ctrl(x10019).srcCtx("CharRNN.scala:121:34") // And(x10002,x10003)
    val x10005 = OpDef(op=BitAnd, inputs=List(x10004, x10001)).name("x10005").ctrl(x10019).srcCtx("CharRNN.scala:119:11") // And(x10004,x10001)
    val x10006 = OpDef(op=BitNot, inputs=List(x10004)).name("x10006").ctrl(x10019).srcCtx("CharRNN.scala:119:11") // Not(x10004)
    val x10007 = OpDef(op=BitAnd, inputs=List(x10006, x10001)).name("x10007").ctrl(x10019).srcCtx("CharRNN.scala:119:11") // And(x10006,x10001)
    val x10008 = OpDef(op=FixLeq, inputs=List(Const(256), b6031)).name("x10008").ctrl(x10019).srcCtx("CharRNN.scala:123:33") // FixLeq(Const(256),b6031)
    val x10009 = OpDef(op=FixLt, inputs=List(b6031, Const(384))).name("x10009").ctrl(x10019).srcCtx("CharRNN.scala:123:43") // FixLt(b6031,Const(384))
    val x10010 = OpDef(op=BitAnd, inputs=List(x10008, x10009)).name("x10010").ctrl(x10019).srcCtx("CharRNN.scala:123:38") // And(x10008,x10009)
    val x10011 = OpDef(op=BitAnd, inputs=List(x10010, x10007)).name("x10011").ctrl(x10019).srcCtx("CharRNN.scala:119:11") // And(x10010,x10007)
    val x10012 = OpDef(op=BitNot, inputs=List(x10010)).name("x10012").ctrl(x10019).srcCtx("CharRNN.scala:119:11") // Not(x10010)
    val x10013 = OpDef(op=BitAnd, inputs=List(x10012, x10007)).name("x10013").ctrl(x10019).srcCtx("CharRNN.scala:119:11") // And(x10012,x10007)
    val x10014_x9988_d0 = WriteMem(x9988_d0, x9999).name("x10014_x9988_d0").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9988,x9999,x9994)
    val x10014_x9988_d1 = WriteMem(x9988_d1, x9999).name("x10014_x9988_d1").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9988,x9999,x9994)
    val x10014_x9988_d2 = WriteMem(x9988_d2, x9999).name("x10014_x9988_d2").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9988,x9999,x9994)
    val x10014_x9988_d3 = WriteMem(x9988_d3, x9999).name("x10014_x9988_d3").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9988,x9999,x9994)
    val x10015_x9989_d0 = WriteMem(x9989_d0, x10000).name("x10015_x9989_d0").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9989,x10000,x9994)
    val x10015_x9989_d1 = WriteMem(x9989_d1, x10000).name("x10015_x9989_d1").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9989,x10000,x9994)
    val x10016_x9990_d0 = WriteMem(x9990_d0, x10005).name("x10016_x9990_d0").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9990,x10005,x9994)
    val x10016_x9990_d1 = WriteMem(x9990_d1, x10005).name("x10016_x9990_d1").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9990,x10005,x9994)
    val x10017_x9991_d0 = WriteMem(x9991_d0, x10011).name("x10017_x9991_d0").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9991,x10011,x9994)
    val x10017_x9991_d1 = WriteMem(x9991_d1, x10011).name("x10017_x9991_d1").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9991,x10011,x9994)
    val x10018_x9992_d0 = WriteMem(x9992_d0, x10013).name("x10018_x9992_d0").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9992,x10013,x9994)
    val x10018_x9992_d1 = WriteMem(x9992_d1, x10013).name("x10018_x9992_d1").ctrl(x10019).srcCtx("CharRNN.scala:114:46") // RegWrite(x9992,x10013,x9994)
    val x10020 = ReadMem(x9992_d1).name("x10020").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // RegRead(x9992)
    val x10021 = ReadMem(x9991_d1).name("x10021").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // RegRead(x9991)
    val x10022 = ReadMem(x9990_d1).name("x10022").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // RegRead(x9990)
    val x10023 = ReadMem(x9989_d1).name("x10023").ctrl(x10056).srcCtx("CharRNN.scala:114:46") // RegRead(x9989)
    val x10055 = UnitController(style=ForkSwitch, level=OuterControl).name("x10055").ctrl(x10056).srcCtx("CharRNN.scala:119:11") // //TODO Switch(Block(x10054),List(x10023, x10022, x10021, x10020),List(x10030, x10038, x10046, x10054))
    val x10030 = UnitController(style=MetaPipe, level=InnerControl).name("x10030").ctrl(x10055).srcCtx("CharRNN.scala:119:11") // //TODO SwitchCase(Block(x10029))
    val x10024 = ReadMem(x9989_d0).name("x10024").ctrl(x10030).srcCtx("CharRNN.scala:114:46") // RegRead(x9989)
    val x10025 = ReadMem(x9988_d3).name("x10025").ctrl(x10030).srcCtx("CharRNN.scala:114:46") // RegRead(x9988)
    val x10026 = OpDef(op=BitAnd, inputs=List(b6032, b6033)).name("x10026").ctrl(x10030).srcCtx("UnrollingBase.scala:28:66") // And(b6032,b6033)
    val x10027 = OpDef(op=BitAnd, inputs=List(x10026, b5835)).name("x10027").ctrl(x10030).srcCtx("UnrollingBase.scala:28:66") // And(x10026,b5835)
    val x10028 = OpDef(op=BitAnd, inputs=List(x10024, x10027)).name("x10028").ctrl(x10030) // And(x10024,x10027)
    val x10029 = StoreBanks(List(x9504_d0_b0, x9504_d1_b0), List(b6030, b6031), x10025).name("x10029").ctrl(x10030).srcCtx("CharRNN.scala:120:27") // SRAMStore(x9504,ArrayBuffer(Const(1), Const(128)),List(b6030, b6031),Const(0),x10025,x10028)
    val x10038 = UnitController(style=MetaPipe, level=InnerControl).name("x10038").ctrl(x10055).srcCtx("CharRNN.scala:119:11") // //TODO SwitchCase(Block(x10037))
    val x10031 = OpDef(op=FixSub, inputs=List(b6031, Const(128))).name("x10031").ctrl(x10038).srcCtx("CharRNN.scala:122:30") // FixSub(b6031,Const(128))
    val x10032 = ReadMem(x9990_d0).name("x10032").ctrl(x10038).srcCtx("CharRNN.scala:114:46") // RegRead(x9990)
    val x10033 = ReadMem(x9988_d2).name("x10033").ctrl(x10038).srcCtx("CharRNN.scala:114:46") // RegRead(x9988)
    val x10034 = OpDef(op=BitAnd, inputs=List(b6032, b6033)).name("x10034").ctrl(x10038).srcCtx("UnrollingBase.scala:28:66") // And(b6032,b6033)
    val x10035 = OpDef(op=BitAnd, inputs=List(x10034, b5835)).name("x10035").ctrl(x10038).srcCtx("UnrollingBase.scala:28:66") // And(x10034,b5835)
    val x10036 = OpDef(op=BitAnd, inputs=List(x10032, x10035)).name("x10036").ctrl(x10038) // And(x10032,x10035)
    val x10037 = StoreBanks(List(x9505_d0_b0, x9505_d1_b0), List(b6030, x10031), x10033).name("x10037").ctrl(x10038).srcCtx("CharRNN.scala:122:42") // SRAMStore(x9505,ArrayBuffer(Const(1), Const(128)),List(b6030, x10031),Const(0),x10033,x10036)
    val x10046 = UnitController(style=MetaPipe, level=InnerControl).name("x10046").ctrl(x10055).srcCtx("CharRNN.scala:119:11") // //TODO SwitchCase(Block(x10045))
    val x10039 = OpDef(op=FixSub, inputs=List(b6031, Const(256))).name("x10039").ctrl(x10046).srcCtx("CharRNN.scala:124:27") // FixSub(b6031,Const(256))
    val x10040 = ReadMem(x9991_d0).name("x10040").ctrl(x10046).srcCtx("CharRNN.scala:114:46") // RegRead(x9991)
    val x10041 = ReadMem(x9988_d1).name("x10041").ctrl(x10046).srcCtx("CharRNN.scala:114:46") // RegRead(x9988)
    val x10042 = OpDef(op=BitAnd, inputs=List(b6032, b6033)).name("x10042").ctrl(x10046).srcCtx("UnrollingBase.scala:28:66") // And(b6032,b6033)
    val x10043 = OpDef(op=BitAnd, inputs=List(x10042, b5835)).name("x10043").ctrl(x10046).srcCtx("UnrollingBase.scala:28:66") // And(x10042,b5835)
    val x10044 = OpDef(op=BitAnd, inputs=List(x10040, x10043)).name("x10044").ctrl(x10046) // And(x10040,x10043)
    val x10045 = StoreBanks(List(x9506_d0_b0, x9506_d1_b0), List(b6030, x10039), x10041).name("x10045").ctrl(x10046).srcCtx("CharRNN.scala:124:43") // SRAMStore(x9506,ArrayBuffer(Const(1), Const(128)),List(b6030, x10039),Const(0),x10041,x10044)
    val x10054 = UnitController(style=MetaPipe, level=InnerControl).name("x10054").ctrl(x10055).srcCtx("CharRNN.scala:119:11") // //TODO SwitchCase(Block(x10053))
    val x10047 = OpDef(op=FixSub, inputs=List(b6031, Const(384))).name("x10047").ctrl(x10054).srcCtx("CharRNN.scala:126:31") // FixSub(b6031,Const(384))
    val x10048 = ReadMem(x9992_d0).name("x10048").ctrl(x10054).srcCtx("CharRNN.scala:114:46") // RegRead(x9992)
    val x10049 = ReadMem(x9988_d0).name("x10049").ctrl(x10054).srcCtx("CharRNN.scala:114:46") // RegRead(x9988)
    val x10050 = OpDef(op=BitAnd, inputs=List(b6032, b6033)).name("x10050").ctrl(x10054).srcCtx("UnrollingBase.scala:28:66") // And(b6032,b6033)
    val x10051 = OpDef(op=BitAnd, inputs=List(x10050, b5835)).name("x10051").ctrl(x10054).srcCtx("UnrollingBase.scala:28:66") // And(x10050,b5835)
    val x10052 = OpDef(op=BitAnd, inputs=List(x10048, x10051)).name("x10052").ctrl(x10054) // And(x10048,x10051)
    val x10053 = StoreBanks(List(x9507_d0_b0, x9507_d1_b0), List(b6030, x10047), x10049).name("x10053").ctrl(x10054).srcCtx("CharRNN.scala:126:47") // SRAMStore(x9507,ArrayBuffer(Const(1), Const(128)),List(b6030, x10047),Const(0),x10049,x10052)
    val x10057 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x10057").ctrl(x10146).srcCtx("CharRNN.scala:129:44") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x10058 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10058").ctrl(x10146).srcCtx("CharRNN.scala:129:29") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10059 = CounterChain(List(x10058,x10057)).name("x10059").ctrl(x10146).srcCtx("CharRNN.scala:129:50") // CounterChainNew(List(x10058, x10057))
    val x10078 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10059).name("x10078").ctrl(x10146).srcCtx("CharRNN.scala:129:50") // UnrolledForeach(List(b5835),x10059,Block(Const(())),List(List(b6154), List(b6155)),List(List(b6156), List(b6157)))
    val b6154 = CounterIter(x10058, Some(0)).name("b6154").ctrl(x10078) // b6154
    val b6156 = Const(true).name("b6156").ctrl(x10078) // b6156
    val b6155 = CounterIter(x10057, None).name("b6155").ctrl(x10078) // b6155
    val b6157 = Const(true).name("b6157").ctrl(x10078) // b6157
    val x10060 = OpDef(op=BitAnd, inputs=List(b6156, b6157)).name("x10060").ctrl(x10078).srcCtx("UnrollingBase.scala:28:66") // And(b6156,b6157)
    val x10061 = OpDef(op=BitAnd, inputs=List(x10060, b5835)).name("x10061").ctrl(x10078).srcCtx("UnrollingBase.scala:28:66") // And(x10060,b5835)
    val x10062 = LoadBanks(List(x9501_d0_b0), List(b6154, b6155)).name("x10062").ctrl(x10078).srcCtx("CharRNN.scala:130:21") // ParSRAMLoad(x9501,List(List(b6154, b6155)),List(x10061))
    val x10063 = x10062 // x10063 = VectorApply(x10062,0)
    val x10064 = LoadBanks(List(x9505_d0_b0), List(b6154, b6155)).name("x10064").ctrl(x10078).srcCtx("CharRNN.scala:130:41") // ParSRAMLoad(x9505,List(List(b6154, b6155)),List(x10061))
    val x10065 = x10064 // x10065 = VectorApply(x10064,0)
    val x10066 = OpDef(op=FixMul, inputs=List(x10063, x10065)).name("x10066").ctrl(x10078).srcCtx("CharRNN.scala:130:28") // FixMul(x10063,x10065)
    val x10067 = LoadBanks(List(x9504_d0_b0), List(b6154, b6155)).name("x10067").ctrl(x10078).srcCtx("CharRNN.scala:130:57") // ParSRAMLoad(x9504,List(List(b6154, b6155)),List(x10061))
    val x10068 = x10067 // x10068 = VectorApply(x10067,0)
    val x10069 = LoadBanks(List(x9507_d0_b0), List(b6154, b6155)).name("x10069").ctrl(x10078).srcCtx("CharRNN.scala:130:78") // ParSRAMLoad(x9507,List(List(b6154, b6155)),List(x10061))
    val x10070 = x10069 // x10070 = VectorApply(x10069,0)
    val x10071 = OpDef(op=FixMul, inputs=List(x10068, x10070)).name("x10071").ctrl(x10078).srcCtx("CharRNN.scala:130:64") // FixMul(x10068,x10070)
    val x10072 = OpDef(op=FixAdd, inputs=List(x10066, x10071)).name("x10072").ctrl(x10078).srcCtx("CharRNN.scala:130:48:cc") // FixAdd(x10066,x10071)
    val x10073 = StoreBanks(List(x9501_d0_b0, x9501_d1_b0), List(b6154, b6155), x10072).name("x10073").ctrl(x10078).srcCtx("CharRNN.scala:131:19") // ParSRAMStore(x9501,List(List(b6154, b6155)),List(x10072),List(x10061))
    val x10074 = LoadBanks(List(x9506_d0_b0), List(b6154, b6155)).name("x10074").ctrl(x10078).srcCtx("CharRNN.scala:132:41") // ParSRAMLoad(x9506,List(List(b6154, b6155)),List(x10061))
    val x10075 = x10074 // x10075 = VectorApply(x10074,0)
    val x10076 = OpDef(op=FixMul, inputs=List(x10072, x10075)).name("x10076").ctrl(x10078).srcCtx("CharRNN.scala:132:31") // FixMul(x10072,x10075)
    val x10077 = StoreBanks(List(x9502_d0_b0, x9502_d1_b0, x9502_d2_b0, x9502_d3_b0), List(b6154, b6155), x10076).name("x10077").ctrl(x10078).srcCtx("CharRNN.scala:132:19") // ParSRAMStore(x9502,List(List(b6154, b6155)),List(x10076),List(x10061))
    val x10079 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1).name("x10079").ctrl(x10146).srcCtx("CharRNN.scala:136:45") // CounterNew(Const(0),Const(64),Const(1),Const(1))
    val x10080 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10080").ctrl(x10146).srcCtx("CharRNN.scala:136:28") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10081 = CounterChain(List(x10080,x10079)).name("x10081").ctrl(x10146).srcCtx("CharRNN.scala:136:51") // CounterChainNew(List(x10080, x10079))
    val x10107 = LoopController(style=MetaPipe, level=OuterControl, cchain=x10081).name("x10107").ctrl(x10146).srcCtx("CharRNN.scala:136:51") // UnrolledForeach(List(b5835),x10081,Block(Const(())),List(List(b6180), List(b6181)),List(List(b6182), List(b6183)))
    val b6180 = CounterIter(x10080, Some(0)).name("b6180").ctrl(x10107) // b6180
    val b6182 = Const(true).name("b6182").ctrl(x10107) // b6182
    val b6181 = CounterIter(x10079, Some(0)).name("b6181").ctrl(x10107) // b6181
    val b6183 = Const(true).name("b6183").ctrl(x10107) // b6183
    val x10082_d0 = Reg(init=Some(0.0)).name("x10082_d0").ctrl(x10107).srcCtx("CharRNN.scala:137:32:proj") // x10082 = RegNew(Const(0))
    isAccum(x10082_d0) = false
    bufferDepthOf(x10082_d0) = 2
    val x10082_d1 = Reg(init=Some(0.0)).name("x10082_d1").ctrl(x10107).srcCtx("CharRNN.scala:137:32:proj") // x10082 = RegNew(Const(0))
    isAccum(x10082_d1) = true
    bufferDepthOf(x10082_d1) = 1
    val x10083 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x10083").ctrl(x10107).srcCtx("CharRNN.scala:137:46") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x10084 = CounterChain(List(x10083)).name("x10084").ctrl(x10107).srcCtx("CharRNN.scala:137:87") // CounterChainNew(List(x10083))
    val x10099 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10084).name("x10099").ctrl(x10107).srcCtx("CharRNN.scala:137:87") // UnrolledReduce(List(b6182, b6183, b5835),x10084,x10082,Block((x10082) => Const(())),List(List(b6187)),List(List(b6188)))
    val b6187 = CounterIter(x10083, None).name("b6187").ctrl(x10099) // b6187
    val b6188 = Const(true).name("b6188").ctrl(x10099) // b6188
    val x10085 = OpDef(op=BitAnd, inputs=List(b6188, b6182)).name("x10085").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(b6188,b6182)
    val x10086 = OpDef(op=BitAnd, inputs=List(b6183, b5835)).name("x10086").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(b6183,b5835)
    val x10087 = OpDef(op=BitAnd, inputs=List(x10085, x10086)).name("x10087").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(x10085,x10086)
    val x10088 = LoadBanks(List(x9502_d1_b0), List(b6180, b6187)).name("x10088").ctrl(x10099).srcCtx("CharRNN.scala:137:60") // ParSRAMLoad(x9502,List(List(b6180, b6187)),List(x10087))
    val x10089 = x10088 // x10089 = VectorApply(x10088,0)
    val x10090 = LoadBanks(List(x9499_d0_b0), List(b6187, b6181)).name("x10090").ctrl(x10099).srcCtx("CharRNN.scala:137:78") // ParSRAMLoad(x9499,List(List(b6187, b6181)),List(x10087))
    val x10091 = x10090 // x10091 = VectorApply(x10090,0)
    val x10092 = OpDef(op=FixMul, inputs=List(x10089, x10091)).name("x10092").ctrl(x10099).srcCtx("CharRNN.scala:137:67") // FixMul(x10089,x10091)
    val x10093 = ReadMem(x10082_d1).name("x10093").ctrl(x10099).srcCtx("CharRNN.scala:137:87") // RegRead(x10082)
    val x10094 = OpDef(op=FixEql, inputs=List(b6187, Const(0))).name("x10094").ctrl(x10099).srcCtx("CharRNN.scala:137:87") // FixEql(b6187,Const(0))
    val x10095 = ReduceAccumOp(op=FixAdd, input=x10092, accum=x10093).name("x10095").ctrl(x10099).srcCtx("CharRNN.scala:137:89") // FixAdd(x10092,x10093)
    val x10096 = OpDef(op=BitAnd, inputs=List(b6182, b6183)).name("x10096").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(b6182,b6183)
    val x10097 = OpDef(op=BitAnd, inputs=List(x10096, b5835)).name("x10097").ctrl(x10099).srcCtx("UnrollingBase.scala:28:66") // And(x10096,b5835)
    val x10098_x10082_d0 = WriteMem(x10082_d0, x10095).name("x10098_x10082_d0").ctrl(x10099).srcCtx("CharRNN.scala:137:87") // RegWrite(x10082,x10095,x10097)
    val x10098_x10082_d1 = WriteMem(x10082_d1, x10095).name("x10098_x10082_d1").ctrl(x10099).srcCtx("CharRNN.scala:137:87") // RegWrite(x10082,x10095,x10097)
    val x10106 = UnitController(style=SeqPipe, level=InnerControl).name("x10106").ctrl(x10107).srcCtx("CharRNN.scala:136:51") // UnitPipe(List(b6182, b6183, b5835),Block(Const(())))
    val x10100 = OpDef(op=BitAnd, inputs=List(b6182, b6183)).name("x10100").ctrl(x10106).srcCtx("UnrollingBase.scala:28:66") // And(b6182,b6183)
    val x10101 = OpDef(op=BitAnd, inputs=List(x10100, b5835)).name("x10101").ctrl(x10106).srcCtx("UnrollingBase.scala:28:66") // And(x10100,b5835)
    val x10102 = LoadBanks(List(x9500_d0_b0), List(b6181)).name("x10102").ctrl(x10106).srcCtx("CharRNN.scala:138:37") // SRAMLoad(x9500,ArrayBuffer(Const(64)),List(b6181),Const(0),x10101)
    val x10103 = ReadMem(x10082_d0).name("x10103").ctrl(x10106).srcCtx("CharRNN.scala:138:21") // RegRead(x10082)
    val x10104 = OpDef(op=FixAdd, inputs=List(x10103, x10102)).name("x10104").ctrl(x10106).srcCtx("CharRNN.scala:138:26") // FixAdd(x10103,x10102)
    val x10105 = StoreBanks(List(x9502_d0_b0, x9502_d1_b0, x9502_d2_b0, x9502_d3_b0), List(b6180, b6181), x10104).name("x10105").ctrl(x10106).srcCtx("CharRNN.scala:138:19") // SRAMStore(x9502,ArrayBuffer(Const(1), Const(128)),List(b6180, b6181),Const(0),x10104,x10101)
    val x10108 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10108").ctrl(x10146).srcCtx("CharRNN.scala:141:50") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10109 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10109").ctrl(x10146).srcCtx("CharRNN.scala:141:50") // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10110 = CounterChain(List(x10108,x10109)).name("x10110").ctrl(x10146).srcCtx("CharRNN.scala:141:50") // CounterChainNew(List(x10108, x10109))
    val x10145 = LoopController(style=StreamPipe, level=OuterControl, cchain=x10110).name("x10145").ctrl(x10146).srcCtx("CharRNN.scala:141:50") // UnrolledForeach(List(b5835),x10110,Block(Const(())),List(List(b6215), List(b6216)),List(List(b6217), List(b6218)))
    val b6215 = CounterIter(x10108, Some(0)).name("b6215").ctrl(x10145) // b6215
    val b6217 = Const(true).name("b6217").ctrl(x10145) // b6217
    val b6216 = CounterIter(x10109, Some(0)).name("b6216").ctrl(x10145) // b6216
    val b6218 = Const(true).name("b6218").ctrl(x10145) // b6218
    val b10208 = StreamOut(field="offset").name("b10208").ctrl(x10145).srcCtx("CharRNN.scala:141:50") // x10111 = StreamOutNew(BurstCmdBus)
    isAccum(b10208) = false
    bufferDepthOf(b10208) = 1
    val b10209 = StreamOut(field="size").name("b10209").ctrl(x10145).srcCtx("CharRNN.scala:141:50") // x10111 = StreamOutNew(BurstCmdBus)
    isAccum(b10209) = false
    bufferDepthOf(b10209) = 1
    val x10112 = StreamOut(field="data").name("x10112").ctrl(x10145).srcCtx("CharRNN.scala:141:50") // x10112 = StreamOutNew(BurstFullDataBus())
    isAccum(x10112) = false
    bufferDepthOf(x10112) = 1
    val x10113 = StreamIn(field="ack").name("x10113").ctrl(x10145).srcCtx("CharRNN.scala:141:50") // x10113 = StreamInNew(BurstAckBus)
    isAccum(x10113) = false
    bufferDepthOf(x10113) = 1
    val x10129 = UnitController(style=SeqPipe, level=InnerControl).name("x10129").ctrl(x10145).srcCtx("CharRNN.scala:141:50") // UnitPipe(List(b6217, b6218, b5835),Block(x10128))
    val x10114 = OpDef(op=FixAdd, inputs=List(b5834, b6215)).name("x10114").ctrl(x10129).srcCtx("CharRNN.scala:141:50") // FixAdd(b5834,b6215)
    val x10115 = x10114 // FixConvert(x10114,TRUE,_32,_0) (Same Type. No op)
    val x10116 = OpDef(op=FixSla, inputs=List(x10115, Const(6))).name("x10116").ctrl(x10129).srcCtx("CharRNN.scala:141:50") // FixLsh(x10115,Const(6))
    val x10117 = b6216 // FixConvert(b6216,TRUE,_32,_0) (Same Type. No op)
    val x10118 = Const(0) // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10119 = OpDef(op=FixAdd, inputs=List(x10116, x10117)).name("x10119").ctrl(x10129).srcCtx("CharRNN.scala:141:50") // FixAdd(x10116,x10117)
    val x10120 = OpDef(op=FixAdd, inputs=List(x10119, x10118)).name("x10120").ctrl(x10129).srcCtx("CharRNN.scala:141:50") // FixAdd(x10119,x10118)
    val x10121 = OpDef(op=FixSla, inputs=List(x10120, Const(2))).name("x10121").ctrl(x10129).srcCtx("CharRNN.scala:141:50") // FixLsh(x10120,Const(2))
    val x10122 = x10121 // FixConvert(x10121,TRUE,_64,_0)
    val x10123 = DramAddress(x9490).name("x10123").ctrl(x10129).srcCtx("CharRNN.scala:141:50") // GetDRAMAddress(x9490)
    val x10125_x10124 = OpDef(op=FixAdd, inputs=List(x10122, x10123)).name("x10125_x10124").ctrl(x10129).srcCtx("CharRNN.scala:141:50") // FixAdd(x10122,x10123)
    // x10125 = SimpleStruct(ArrayBuffer((offset,x10124), (size,Const(512)), (isLoad,Const(false))))
    val x10126 = OpDef(op=BitAnd, inputs=List(b6217, b6218)).name("x10126").ctrl(x10129).srcCtx("UnrollingBase.scala:28:66") // And(b6217,b6218)
    val x10127 = OpDef(op=BitAnd, inputs=List(x10126, b5835)).name("x10127").ctrl(x10129).srcCtx("UnrollingBase.scala:28:66") // And(x10126,b5835)
    val x10128_b10210_b10208 = WriteMem(b10208, x10125_x10124).name("x10128_b10210_b10208").ctrl(x10129).srcCtx("CharRNN.scala:141:50") // StreamWrite(x10111,x10125,x10127)
    val x10128_b10211_b10209 = WriteMem(b10209, Const(512)).name("x10128_b10211_b10209").ctrl(x10129).srcCtx("CharRNN.scala:141:50") // StreamWrite(x10111,x10125,x10127)
    val x10130 = Counter(min=Const(0), max=Const(128), step=Const(1), par=1).name("x10130").ctrl(x10145).srcCtx("CharRNN.scala:141:50") // CounterNew(Const(0),Const(128),Const(1),Const(1))
    val x10131 = CounterChain(List(x10130)).name("x10131").ctrl(x10145).srcCtx("CharRNN.scala:141:50") // CounterChainNew(List(x10130))
    val x10139 = LoopController(style=InnerPipe, level=InnerControl, cchain=x10131).name("x10139").ctrl(x10145).srcCtx("CharRNN.scala:141:50") // UnrolledForeach(List(b6217, b6218, b5835),x10131,Block(Const(())),List(List(b6240)),List(List(b6241)))
    val b6240 = CounterIter(x10130, None).name("b6240").ctrl(x10139) // b6240
    val b6241 = Const(true).name("b6241").ctrl(x10139) // b6241
    val x10132 = OpDef(op=BitAnd, inputs=List(b6241, b6217)).name("x10132").ctrl(x10139).srcCtx("UnrollingBase.scala:28:66") // And(b6241,b6217)
    val x10133 = OpDef(op=BitAnd, inputs=List(b6218, b5835)).name("x10133").ctrl(x10139).srcCtx("UnrollingBase.scala:28:66") // And(b6218,b5835)
    val x10134 = OpDef(op=BitAnd, inputs=List(x10132, x10133)).name("x10134").ctrl(x10139).srcCtx("UnrollingBase.scala:28:66") // And(x10132,x10133)
    val x10135 = LoadBanks(List(x9502_d0_b0), List(b6216, b6240)).name("x10135").ctrl(x10139).srcCtx("CharRNN.scala:141:50") // ParSRAMLoad(x9502,List(List(b6216, b6240)),List(x10134))
    val x10137_x10136 = x10135 // x10136 = VectorApply(x10135,0)
    // x10137 = SimpleStruct(ArrayBuffer((_1,x10136), (_2,Const(true))))
    val x10138_x10138_x10112 = WriteMem(x10112, x10137_x10136).name("x10138_x10138_x10112").ctrl(x10139).srcCtx("CharRNN.scala:141:50") // ParStreamWrite(x10112,List(x10137),List(x10134))
    val x10140 = FringeDenseStore(dram=List(x9490), cmdStream=List(b10208, b10209), dataStream=List(x10112), ackStream=List(x10113)).name("x10140").ctrl(x10145).srcCtx("CharRNN.scala:141:50") // FringeDenseStore(x9490,x10111,x10112,x10113)
    val x10144 = UnitController(style=SeqPipe, level=InnerControl).name("x10144").ctrl(x10145).srcCtx("CharRNN.scala:141:50") // UnitPipe(List(b6217, b6218, b5835),Block(Const(())))
    val x10141 = OpDef(op=BitAnd, inputs=List(b6217, b6218)).name("x10141").ctrl(x10144).srcCtx("UnrollingBase.scala:28:66") // And(b6217,b6218)
    val x10142 = OpDef(op=BitAnd, inputs=List(x10141, b5835)).name("x10142").ctrl(x10144).srcCtx("UnrollingBase.scala:28:66") // And(x10141,b5835)
    val x10143_x10143 = ReadMem(x10113).name("x10143_x10143").ctrl(x10144).srcCtx("CharRNN.scala:141:50") // StreamRead(x10113,x10142)
    // x10154 = WriteTokens(x10150,Const(","),x10151,Block((b415) => x10153),b415) TODO: Unmatched Node
    }; split1
    
  }
}
