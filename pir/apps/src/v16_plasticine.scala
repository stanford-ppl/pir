import pir._
import pir.node._
import arch._
import prism.enums._

object v16_plasticine extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x9725 = withCtrl(design.top.topController) { DRAM(dims=List(Const(1), Const(1024), Const(224), Const(224))).name("x9725").srcCtx("v16_plasticine.scala:41:28:temp_DRAM") } // x9725 = DRAMNew(ArrayBuffer(Const(1), Const(1024), Const(224), Const(224)),Const(0))
    val x9727 = withCtrl(design.top.topController) { DRAM(dims=List(Const(1), Const(2), Const(25088))).name("x9727").srcCtx("v16_plasticine.scala:44:27:out_DRAM") } // x9727 = DRAMNew(ArrayBuffer(Const(1), Const(2), Const(25088)),Const(0))
    val x9728 = withCtrl(design.top.topController) { DRAM(dims=List(Const(4224), Const(512), Const(16))).name("x9728").srcCtx("v16_plasticine.scala:47:30:weight_DRAM") } // x9728 = DRAMNew(ArrayBuffer(Const(4224), Const(512), Const(16)),Const(0))
    val x9730 = withCtrl(design.top.topController) { DRAM(dims=List(Const(13), Const(512))).name("x9730").srcCtx("v16_plasticine.scala:50:28:bias_DRAM") } // x9730 = DRAMNew(ArrayBuffer(Const(13), Const(512)),Const(0))
    val x9732 = withCtrl(design.top.topController) { DRAM(dims=List(Const(9192), Const(25088))).name("x9732").srcCtx("v16_plasticine.scala:53:33:fc_weight_DRAM") } // x9732 = DRAMNew(ArrayBuffer(Const(9192), Const(25088)),Const(0))
    val x9734 = withCtrl(design.top.topController) { DRAM(dims=List(Const(3), Const(4096))).name("x9734").srcCtx("v16_plasticine.scala:56:31:fc_bias_DRAM") } // x9734 = DRAMNew(ArrayBuffer(Const(3), Const(4096)),Const(0))
    val x10505 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x10505").srcCtx("v16_plasticine.scala:59:11") } // Hwblock(Block(Const(())),false)
    val x9736 = withCtrl(x10505) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9736").srcCtx("v16_plasticine.scala:147:37") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9737 = withCtrl(x10505) { CounterChain(List(x9736)).name("x9737").srcCtx("v16_plasticine.scala:147:43") } // CounterChainNew(List(x9736))
    val x10504 = withCtrl(x10505) { LoopController(style=SeqPipe, level=OuterControl, cchain=x9737).name("x10504").srcCtx("v16_plasticine.scala:147:43") } // UnrolledForeach(List(Const(true)),x9737,Block(Const(())),List(List(b5759)),List(List(b5760)))
    val b5759 = withCtrl(x10504) { CounterIter(x9736, Some(0)).name("b5759") } // b5759
    val b5760 = withCtrl(x10504) { Const(true).name("b5760") } // b5760
    val x9738 = withCtrl(x10504) { Counter(min=Const(0), max=Const(13), step=Const(1), par=1).name("x9738").srcCtx("v16_plasticine.scala:150:30") } // CounterNew(Const(0),Const(13),Const(1),Const(1))
    val x9739 = withCtrl(x10504) { CounterChain(List(x9738)).name("x9739").srcCtx("v16_plasticine.scala:150:40") } // CounterChainNew(List(x9738))
    val x10257 = withCtrl(x10504) { LoopController(style=SeqPipe, level=OuterControl, cchain=x9739).name("x10257").srcCtx("v16_plasticine.scala:150:40") } // UnrolledForeach(List(b5760),x9739,Block(Const(())),List(List(b5763)),List(List(b5764)))
    val b5763 = withCtrl(x10257) { CounterIter(x9738, Some(0)).name("b5763") } // b5763
    val b5764 = withCtrl(x10257) { Const(true).name("b5764") } // b5764
    val x9740_d0 = withCtrl(x10257) { Reg(init=Some(0)).name("x9740_d0").srcCtx("v16_plasticine.scala:150:40") } // x9740 = RegNew(Const(0))
    isAccum(x9740_d0) = false
    bufferDepthOf(x9740_d0) = 1
    val x9740_d1 = withCtrl(x10257) { Reg(init=Some(0)).name("x9740_d1").srcCtx("v16_plasticine.scala:150:40") } // x9740 = RegNew(Const(0))
    isAccum(x9740_d1) = false
    bufferDepthOf(x9740_d1) = 1
    val x9741_d0 = withCtrl(x10257) { Reg(init=Some(0)).name("x9741_d0").srcCtx("v16_plasticine.scala:150:40") } // x9741 = RegNew(Const(0))
    isAccum(x9741_d0) = false
    bufferDepthOf(x9741_d0) = 1
    val x9741_d1 = withCtrl(x10257) { Reg(init=Some(0)).name("x9741_d1").srcCtx("v16_plasticine.scala:150:40") } // x9741 = RegNew(Const(0))
    isAccum(x9741_d1) = false
    bufferDepthOf(x9741_d1) = 1
    val x9741_d2 = withCtrl(x10257) { Reg(init=Some(0)).name("x9741_d2").srcCtx("v16_plasticine.scala:150:40") } // x9741 = RegNew(Const(0))
    isAccum(x9741_d2) = false
    bufferDepthOf(x9741_d2) = 1
    val x9741_d3 = withCtrl(x10257) { Reg(init=Some(0)).name("x9741_d3").srcCtx("v16_plasticine.scala:150:40") } // x9741 = RegNew(Const(0))
    isAccum(x9741_d3) = false
    bufferDepthOf(x9741_d3) = 1
    val x9742_d0 = withCtrl(x10257) { Reg(init=Some(0)).name("x9742_d0").srcCtx("v16_plasticine.scala:150:40") } // x9742 = RegNew(Const(0))
    isAccum(x9742_d0) = false
    bufferDepthOf(x9742_d0) = 1
    val x9742_d1 = withCtrl(x10257) { Reg(init=Some(0)).name("x9742_d1").srcCtx("v16_plasticine.scala:150:40") } // x9742 = RegNew(Const(0))
    isAccum(x9742_d1) = false
    bufferDepthOf(x9742_d1) = 1
    val x9743 = withCtrl(x10257) { Reg(init=Some(0)).name("x9743").srcCtx("v16_plasticine.scala:150:40") } // x9743 = RegNew(Const(0))
    isAccum(x9743) = false
    bufferDepthOf(x9743) = 1
    val x9744 = withCtrl(x10257) { Reg(init=Some(0)).name("x9744").srcCtx("v16_plasticine.scala:150:40") } // x9744 = RegNew(Const(0))
    isAccum(x9744) = false
    bufferDepthOf(x9744) = 1
    val x9745 = withCtrl(x10257) { Reg(init=Some(0)).name("x9745").srcCtx("v16_plasticine.scala:150:40") } // x9745 = RegNew(Const(0))
    isAccum(x9745) = false
    bufferDepthOf(x9745) = 1
    val x9746_d0_b0 = withCtrl(x10257) { SRAM(size=512, banking=Strided(banks=1, stride=1)).name("x9746_d0_b0").srcCtx("v16_plasticine.scala:101:32:bias_SRAM") } // x9746 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x9746_d0_b0) = false
    bufferDepthOf(x9746_d0_b0) = 1
    staticDimsOf(x9746_d0_b0) = List(512)
    val x9871 = withCtrl(x10257) { UnitController(style=SeqPipe, level=InnerControl).name("x9871").srcCtx("v16_plasticine.scala:150:40") } // UnitPipe(List(b5764, b5760),Block(Const(())))
    val x9747 = withCtrl(x9871) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9748 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9747)).name("x9748").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9747)
    val x9749 = withCtrl(x9871) { Const(2) } // FixConvert(Const(2),TRUE,_32,_0) (Same Type. No op)
    val x9750 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9749)).name("x9750").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9749)
    val x9751 = withCtrl(x9871) { Const(4) } // FixConvert(Const(4),TRUE,_32,_0) (Same Type. No op)
    val x9752 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9751)).name("x9752").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9751)
    val x9753 = withCtrl(x9871) { Const(5) } // FixConvert(Const(5),TRUE,_32,_0) (Same Type. No op)
    val x9754 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9753)).name("x9754").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9753)
    val x9755 = withCtrl(x9871) { Const(7) } // FixConvert(Const(7),TRUE,_32,_0) (Same Type. No op)
    val x9756 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9755)).name("x9756").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9755)
    val x9757 = withCtrl(x9871) { Const(8) } // FixConvert(Const(8),TRUE,_32,_0) (Same Type. No op)
    val x9758 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9757)).name("x9758").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9757)
    val x9759 = withCtrl(x9871) { Const(10) } // FixConvert(Const(10),TRUE,_32,_0) (Same Type. No op)
    val x9760 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9759)).name("x9760").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9759)
    val x9761 = withCtrl(x9871) { Const(11) } // FixConvert(Const(11),TRUE,_32,_0) (Same Type. No op)
    val x9762 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9761)).name("x9762").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9761)
    val x9763 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9748, x9750)).name("x9763").srcCtx("v16_plasticine.scala:64:57") } // Or(x9748,x9750)
    val x9764 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9763, x9752)).name("x9764").srcCtx("v16_plasticine.scala:64:57") } // Or(x9763,x9752)
    val x9765 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9764, x9754)).name("x9765").srcCtx("v16_plasticine.scala:64:57") } // Or(x9764,x9754)
    val x9766 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9765, x9756)).name("x9766").srcCtx("v16_plasticine.scala:64:57") } // Or(x9765,x9756)
    val x9767 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9766, x9758)).name("x9767").srcCtx("v16_plasticine.scala:64:57") } // Or(x9766,x9758)
    val x9768 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9767, x9760)).name("x9768").srcCtx("v16_plasticine.scala:64:57") } // Or(x9767,x9760)
    val x9769 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9768, x9762)).name("x9769").srcCtx("v16_plasticine.scala:64:57") } // Or(x9768,x9762)
    val x9770 = withCtrl(x9871) { Const(1) } // FixConvert(Const(1),TRUE,_32,_0) (Same Type. No op)
    val x9771 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9769, x9770, x9749)).name("x9771").srcCtx("v16_plasticine.scala:64:14:pool_size") } // Mux(x9769,x9770,x9749)
    val x9772 = withCtrl(x9871) { Const(9) } // FixConvert(Const(9),TRUE,_32,_0) (Same Type. No op)
    val x9773 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9772)).name("x9773").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9772)
    val x9774 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9756, x9758)).name("x9774").srcCtx("v16_plasticine.scala:64:57") } // Or(x9756,x9758)
    val x9775 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9774, x9773)).name("x9775").srcCtx("v16_plasticine.scala:64:57") } // Or(x9774,x9773)
    val x9776 = withCtrl(x9871) { Const(28) } // FixConvert(Const(28),TRUE,_32,_0) (Same Type. No op)
    val x9777 = withCtrl(x9871) { Const(14) } // FixConvert(Const(14),TRUE,_32,_0) (Same Type. No op)
    val x9778 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9775, x9776, x9777)).name("x9778").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9775,x9776,x9777)
    val x9779 = withCtrl(x9871) { Const(6) } // FixConvert(Const(6),TRUE,_32,_0) (Same Type. No op)
    val x9780 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9779)).name("x9780").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9779)
    val x9781 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9752, x9754)).name("x9781").srcCtx("v16_plasticine.scala:64:57") } // Or(x9752,x9754)
    val x9782 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9781, x9780)).name("x9782").srcCtx("v16_plasticine.scala:64:57") } // Or(x9781,x9780)
    val x9783 = withCtrl(x9871) { Const(56) } // FixConvert(Const(56),TRUE,_32,_0) (Same Type. No op)
    val x9784 = withCtrl(x9871) { x9778 } // FixConvert(x9778,TRUE,_32,_0) (Same Type. No op)
    val x9785 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9782, x9783, x9784)).name("x9785").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9782,x9783,x9784)
    val x9786 = withCtrl(x9871) { Const(3) } // FixConvert(Const(3),TRUE,_32,_0) (Same Type. No op)
    val x9787 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9786)).name("x9787").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9786)
    val x9788 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9750, x9787)).name("x9788").srcCtx("v16_plasticine.scala:64:57") } // Or(x9750,x9787)
    val x9789 = withCtrl(x9871) { Const(112) } // FixConvert(Const(112),TRUE,_32,_0) (Same Type. No op)
    val x9790 = withCtrl(x9871) { x9785 } // FixConvert(x9785,TRUE,_32,_0) (Same Type. No op)
    val x9791 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9788, x9789, x9790)).name("x9791").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9788,x9789,x9790)
    val x9792 = withCtrl(x9871) { OpDef(op=FixEql, inputs=List(b5763, x9770)).name("x9792").srcCtx("v16_plasticine.scala:64:31") } // FixEql(b5763,x9770)
    val x9793 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9748, x9792)).name("x9793").srcCtx("v16_plasticine.scala:64:57") } // Or(x9748,x9792)
    val x9794 = withCtrl(x9871) { Const(224) } // FixConvert(Const(224),TRUE,_32,_0) (Same Type. No op)
    val x9795 = withCtrl(x9871) { x9791 } // FixConvert(x9791,TRUE,_32,_0) (Same Type. No op)
    val x9796 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9793, x9794, x9795)).name("x9796").srcCtx("v16_plasticine.scala:64:14:nc") } // Mux(x9793,x9794,x9795)
    val x9797 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9773, x9760)).name("x9797").srcCtx("v16_plasticine.scala:64:57") } // Or(x9773,x9760)
    val x9798 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9797, x9762)).name("x9798").srcCtx("v16_plasticine.scala:64:57") } // Or(x9797,x9762)
    val x9799 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9798, x9777, x9755)).name("x9799").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9798,x9777,x9755)
    val x9800 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9780, x9756)).name("x9800").srcCtx("v16_plasticine.scala:64:57") } // Or(x9780,x9756)
    val x9801 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9800, x9758)).name("x9801").srcCtx("v16_plasticine.scala:64:57") } // Or(x9800,x9758)
    val x9802 = withCtrl(x9871) { x9799 } // FixConvert(x9799,TRUE,_32,_0) (Same Type. No op)
    val x9803 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9801, x9776, x9802)).name("x9803").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9801,x9776,x9802)
    val x9804 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9787, x9752)).name("x9804").srcCtx("v16_plasticine.scala:64:57") } // Or(x9787,x9752)
    val x9805 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9804, x9754)).name("x9805").srcCtx("v16_plasticine.scala:64:57") } // Or(x9804,x9754)
    val x9806 = withCtrl(x9871) { x9803 } // FixConvert(x9803,TRUE,_32,_0) (Same Type. No op)
    val x9807 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9805, x9783, x9806)).name("x9807").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9805,x9783,x9806)
    val x9808 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9792, x9750)).name("x9808").srcCtx("v16_plasticine.scala:64:57") } // Or(x9792,x9750)
    val x9809 = withCtrl(x9871) { x9807 } // FixConvert(x9807,TRUE,_32,_0) (Same Type. No op)
    val x9810 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9808, x9789, x9809)).name("x9810").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9808,x9789,x9809)
    val x9811 = withCtrl(x9871) { x9810 } // FixConvert(x9810,TRUE,_32,_0) (Same Type. No op)
    val x9812 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9748, x9794, x9811)).name("x9812").srcCtx("v16_plasticine.scala:64:14:oc") } // Mux(x9748,x9794,x9811)
    val x9813 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9754, x9780)).name("x9813").srcCtx("v16_plasticine.scala:64:57") } // Or(x9754,x9780)
    val x9814 = withCtrl(x9871) { OpDef(op=BitOr, inputs=List(x9813, x9756)).name("x9814").srcCtx("v16_plasticine.scala:64:57") } // Or(x9813,x9756)
    val x9815 = withCtrl(x9871) { Const(256) } // FixConvert(Const(256),TRUE,_32,_0) (Same Type. No op)
    val x9816 = withCtrl(x9871) { Const(512) } // FixConvert(Const(512),TRUE,_32,_0) (Same Type. No op)
    val x9817 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9814, x9815, x9816)).name("x9817").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9814,x9815,x9816)
    val x9818 = withCtrl(x9871) { Const(128) } // FixConvert(Const(128),TRUE,_32,_0) (Same Type. No op)
    val x9819 = withCtrl(x9871) { x9817 } // FixConvert(x9817,TRUE,_32,_0) (Same Type. No op)
    val x9820 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9804, x9818, x9819)).name("x9820").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9804,x9818,x9819)
    val x9821 = withCtrl(x9871) { Const(64) } // FixConvert(Const(64),TRUE,_32,_0) (Same Type. No op)
    val x9822 = withCtrl(x9871) { x9820 } // FixConvert(x9820,TRUE,_32,_0) (Same Type. No op)
    val x9823 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9808, x9821, x9822)).name("x9823").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9808,x9821,x9822)
    val x9824 = withCtrl(x9871) { x9823 } // FixConvert(x9823,TRUE,_32,_0) (Same Type. No op)
    val x9825 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9748, x9786, x9824)).name("x9825").srcCtx("v16_plasticine.scala:64:14:in_channels") } // Mux(x9748,x9786,x9824)
    val x9826 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9782, x9815, x9816)).name("x9826").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9782,x9815,x9816)
    val x9827 = withCtrl(x9871) { x9826 } // FixConvert(x9826,TRUE,_32,_0) (Same Type. No op)
    val x9828 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9788, x9818, x9827)).name("x9828").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9788,x9818,x9827)
    val x9829 = withCtrl(x9871) { x9828 } // FixConvert(x9828,TRUE,_32,_0) (Same Type. No op)
    val x9830 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9793, x9821, x9829)).name("x9830").srcCtx("v16_plasticine.scala:64:14:out_channels") } // Mux(x9793,x9821,x9829)
    val x9831 = withCtrl(x9871) { Const(3200) } // FixConvert(Const(3200),TRUE,_32,_0) (Same Type. No op)
    val x9832 = withCtrl(x9871) { Const(3712) } // FixConvert(Const(3712),TRUE,_32,_0) (Same Type. No op)
    val x9833 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9762, x9831, x9832)).name("x9833").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9762,x9831,x9832)
    val x9834 = withCtrl(x9871) { Const(2688) } // FixConvert(Const(2688),TRUE,_32,_0) (Same Type. No op)
    val x9835 = withCtrl(x9871) { x9833 } // FixConvert(x9833,TRUE,_32,_0) (Same Type. No op)
    val x9836 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9760, x9834, x9835)).name("x9836").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9760,x9834,x9835)
    val x9837 = withCtrl(x9871) { Const(2176) } // FixConvert(Const(2176),TRUE,_32,_0) (Same Type. No op)
    val x9838 = withCtrl(x9871) { x9836 } // FixConvert(x9836,TRUE,_32,_0) (Same Type. No op)
    val x9839 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9773, x9837, x9838)).name("x9839").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9773,x9837,x9838)
    val x9840 = withCtrl(x9871) { Const(1664) } // FixConvert(Const(1664),TRUE,_32,_0) (Same Type. No op)
    val x9841 = withCtrl(x9871) { x9839 } // FixConvert(x9839,TRUE,_32,_0) (Same Type. No op)
    val x9842 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9758, x9840, x9841)).name("x9842").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9758,x9840,x9841)
    val x9843 = withCtrl(x9871) { Const(1152) } // FixConvert(Const(1152),TRUE,_32,_0) (Same Type. No op)
    val x9844 = withCtrl(x9871) { x9842 } // FixConvert(x9842,TRUE,_32,_0) (Same Type. No op)
    val x9845 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9756, x9843, x9844)).name("x9845").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9756,x9843,x9844)
    val x9846 = withCtrl(x9871) { Const(896) } // FixConvert(Const(896),TRUE,_32,_0) (Same Type. No op)
    val x9847 = withCtrl(x9871) { x9845 } // FixConvert(x9845,TRUE,_32,_0) (Same Type. No op)
    val x9848 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9780, x9846, x9847)).name("x9848").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9780,x9846,x9847)
    val x9849 = withCtrl(x9871) { Const(640) } // FixConvert(Const(640),TRUE,_32,_0) (Same Type. No op)
    val x9850 = withCtrl(x9871) { x9848 } // FixConvert(x9848,TRUE,_32,_0) (Same Type. No op)
    val x9851 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9754, x9849, x9850)).name("x9851").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9754,x9849,x9850)
    val x9852 = withCtrl(x9871) { Const(384) } // FixConvert(Const(384),TRUE,_32,_0) (Same Type. No op)
    val x9853 = withCtrl(x9871) { x9851 } // FixConvert(x9851,TRUE,_32,_0) (Same Type. No op)
    val x9854 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9752, x9852, x9853)).name("x9854").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9752,x9852,x9853)
    val x9855 = withCtrl(x9871) { x9854 } // FixConvert(x9854,TRUE,_32,_0) (Same Type. No op)
    val x9856 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9787, x9815, x9855)).name("x9856").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9787,x9815,x9855)
    val x9857 = withCtrl(x9871) { x9856 } // FixConvert(x9856,TRUE,_32,_0) (Same Type. No op)
    val x9858 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9750, x9818, x9857)).name("x9858").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9750,x9818,x9857)
    val x9859 = withCtrl(x9871) { x9858 } // FixConvert(x9858,TRUE,_32,_0) (Same Type. No op)
    val x9860 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9792, x9821, x9859)).name("x9860").srcCtx("v16_plasticine.scala:64:14") } // Mux(x9792,x9821,x9859)
    val x9861 = withCtrl(x9871) { x9860 } // FixConvert(x9860,TRUE,_32,_0) (Same Type. No op)
    val x9862 = withCtrl(x9871) { OpDef(op=MuxOp, inputs=List(x9748, x9747, x9861)).name("x9862").srcCtx("v16_plasticine.scala:64:14:weight_offset") } // Mux(x9748,x9747,x9861)
    val x9863 = withCtrl(x9871) { OpDef(op=FixAdd, inputs=List(b5763, Const(1))).name("x9863").srcCtx("v16_plasticine.scala:102:33") } // FixAdd(b5763,Const(1))
    val x9864 = withCtrl(x9871) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x9864").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x9865_x9740_d0 = withCtrl(x9871) { WriteMem(x9740_d0, x9771).name("x9865_x9740_d0").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9740,x9771,x9864)
    val x9865_x9740_d1 = withCtrl(x9871) { WriteMem(x9740_d1, x9771).name("x9865_x9740_d1").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9740,x9771,x9864)
    val x9866_x9741_d0 = withCtrl(x9871) { WriteMem(x9741_d0, x9796).name("x9866_x9741_d0").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9741,x9796,x9864)
    val x9866_x9741_d1 = withCtrl(x9871) { WriteMem(x9741_d1, x9796).name("x9866_x9741_d1").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9741,x9796,x9864)
    val x9866_x9741_d2 = withCtrl(x9871) { WriteMem(x9741_d2, x9796).name("x9866_x9741_d2").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9741,x9796,x9864)
    val x9866_x9741_d3 = withCtrl(x9871) { WriteMem(x9741_d3, x9796).name("x9866_x9741_d3").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9741,x9796,x9864)
    val x9867_x9742_d0 = withCtrl(x9871) { WriteMem(x9742_d0, x9812).name("x9867_x9742_d0").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9742,x9812,x9864)
    val x9867_x9742_d1 = withCtrl(x9871) { WriteMem(x9742_d1, x9812).name("x9867_x9742_d1").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9742,x9812,x9864)
    val x9868_x9743 = withCtrl(x9871) { WriteMem(x9743, x9825).name("x9868_x9743").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9743,x9825,x9864)
    val x9869_x9744 = withCtrl(x9871) { WriteMem(x9744, x9830).name("x9869_x9744").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9744,x9830,x9864)
    val x9870_x9745 = withCtrl(x9871) { WriteMem(x9745, x9862).name("x9870_x9745").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9745,x9862,x9864)
    val x9872 = withCtrl(x10257) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9872").srcCtx("v16_plasticine.scala:102:19") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9873 = withCtrl(x10257) { CounterChain(List(x9872)).name("x9873").srcCtx("v16_plasticine.scala:102:19") } // CounterChainNew(List(x9872))
    val x9900 = withCtrl(x10257) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9873).name("x9900").srcCtx("v16_plasticine.scala:102:19") } // UnrolledForeach(List(b5764, b5760),x9873,Block(Const(())),List(List(b5899)),List(List(b5900)))
    val b5899 = withCtrl(x9900) { CounterIter(x9872, Some(0)).name("b5899") } // b5899
    val b5900 = withCtrl(x9900) { Const(true).name("b5900") } // b5900
    val b10508 = withCtrl(x9900) { StreamOut(field="offset").name("b10508").srcCtx("v16_plasticine.scala:102:19") } // x9874 = StreamOutNew(BurstCmdBus)
    isAccum(b10508) = false
    bufferDepthOf(b10508) = 1
    val b10509 = withCtrl(x9900) { StreamOut(field="size").name("b10509").srcCtx("v16_plasticine.scala:102:19") } // x9874 = StreamOutNew(BurstCmdBus)
    isAccum(b10509) = false
    bufferDepthOf(b10509) = 1
    val x9875 = withCtrl(x9900) { StreamIn(field="data").name("x9875").srcCtx("v16_plasticine.scala:102:19") } // x9875 = StreamInNew(BurstDataBus())
    isAccum(x9875) = false
    bufferDepthOf(x9875) = 1
    val x9889 = withCtrl(x9900) { UnitController(style=SeqPipe, level=InnerControl).name("x9889").srcCtx("v16_plasticine.scala:102:19") } // UnitPipe(List(b5900, b5764, b5760),Block(x9888))
    val x9876 = withCtrl(x9889) { OpDef(op=FixAdd, inputs=List(b5763, b5899)).name("x9876").srcCtx("v16_plasticine.scala:102:19") } // FixAdd(b5763,b5899)
    val x9877 = withCtrl(x9889) { x9876 } // FixConvert(x9876,TRUE,_32,_0) (Same Type. No op)
    val x9878 = withCtrl(x9889) { OpDef(op=FixSla, inputs=List(x9877, Const(9))).name("x9878").srcCtx("v16_plasticine.scala:102:19") } // FixLsh(x9877,Const(9))
    val x9879 = withCtrl(x9889) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9880 = withCtrl(x9889) { OpDef(op=FixAdd, inputs=List(x9878, x9879)).name("x9880").srcCtx("v16_plasticine.scala:102:19") } // FixAdd(x9878,x9879)
    val x9881 = withCtrl(x9889) { OpDef(op=FixSla, inputs=List(x9880, Const(2))).name("x9881").srcCtx("v16_plasticine.scala:102:19") } // FixLsh(x9880,Const(2))
    val x9882 = withCtrl(x9889) { x9881 } // FixConvert(x9881,TRUE,_64,_0)
    val x9883 = withCtrl(x9889) { DramAddress(x9730).name("x9883").srcCtx("v16_plasticine.scala:102:19") } // GetDRAMAddress(x9730)
    val x9885_x9884 = withCtrl(x9889) { OpDef(op=FixAdd, inputs=List(x9882, x9883)).name("x9885_x9884").srcCtx("v16_plasticine.scala:102:19") } // FixAdd(x9882,x9883)
    // x9885 = SimpleStruct(ArrayBuffer((offset,x9884), (size,Const(2048)), (isLoad,Const(true))))
    val x9886 = withCtrl(x9889) { OpDef(op=BitAnd, inputs=List(b5900, b5764)).name("x9886").srcCtx("UnrollingBase.scala:28:66") } // And(b5900,b5764)
    val x9887 = withCtrl(x9889) { OpDef(op=BitAnd, inputs=List(x9886, b5760)).name("x9887").srcCtx("UnrollingBase.scala:28:66") } // And(x9886,b5760)
    val x9888_b10510_b10508 = withCtrl(x9889) { WriteMem(b10508, x9885_x9884).name("x9888_b10510_b10508").srcCtx("v16_plasticine.scala:102:19") } // StreamWrite(x9874,x9885,x9887)
    val x9888_b10511_b10509 = withCtrl(x9889) { WriteMem(b10509, Const(2048)).name("x9888_b10511_b10509").srcCtx("v16_plasticine.scala:102:19") } // StreamWrite(x9874,x9885,x9887)
    val x9890 = withCtrl(x9900) { FringeDenseLoad(dram=List(x9730), cmdStream=List(b10508, b10509), dataStream=List(x9875)).name("x9890").srcCtx("v16_plasticine.scala:102:19") } // FringeDenseLoad(x9730,x9874,x9875)
    val x9891 = withCtrl(x9900) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x9891").srcCtx("v16_plasticine.scala:102:19") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x9892 = withCtrl(x9900) { CounterChain(List(x9891)).name("x9892").srcCtx("v16_plasticine.scala:102:19") } // CounterChainNew(List(x9891))
    val x9899 = withCtrl(x9900) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9892).name("x9899").srcCtx("v16_plasticine.scala:102:19") } // UnrolledForeach(List(b5900, b5764, b5760),x9892,Block(Const(())),List(List(b5920)),List(List(b5921)))
    val b5920 = withCtrl(x9899) { CounterIter(x9891, None).name("b5920") } // b5920
    val b5921 = withCtrl(x9899) { Const(true).name("b5921") } // b5921
    val x9893 = withCtrl(x9899) { OpDef(op=BitAnd, inputs=List(b5921, b5900)).name("x9893").srcCtx("UnrollingBase.scala:28:66") } // And(b5921,b5900)
    val x9894 = withCtrl(x9899) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x9894").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x9895 = withCtrl(x9899) { OpDef(op=BitAnd, inputs=List(x9893, x9894)).name("x9895").srcCtx("UnrollingBase.scala:28:66") } // And(x9893,x9894)
    val x9896_x9896 = withCtrl(x9899) { ReadMem(x9875).name("x9896_x9896").srcCtx("v16_plasticine.scala:102:19") } // ParStreamRead(x9875,List(x9895))
    val x9897_x9897 = withCtrl(x9899) { x9896_x9896 } // VectorApply(x9896,0)
    val x9898 = withCtrl(x9899) { StoreBanks(List(List(x9746_d0_b0)), List(b5920), x9897_x9897).name("x9898").srcCtx("v16_plasticine.scala:102:19") } // ParSRAMStore(x9746,List(List(b5920)),List(x9897),List(x9895))
    val x9901 = withCtrl(x10257) { Reg(init=Some(0)).name("x9901").srcCtx("v16_plasticine.scala:150:40") } // x9901 = RegNew(Const(0))
    isAccum(x9901) = false
    bufferDepthOf(x9901) = 1
    val x9902 = withCtrl(x10257) { Reg(init=Some(0)).name("x9902").srcCtx("v16_plasticine.scala:150:40") } // x9902 = RegNew(Const(0))
    isAccum(x9902) = false
    bufferDepthOf(x9902) = 1
    val x9913 = withCtrl(x10257) { UnitController(style=SeqPipe, level=InnerControl).name("x9913").srcCtx("v16_plasticine.scala:150:40") } // UnitPipe(List(b5764, b5760),Block(Const(())))
    val x9903 = withCtrl(x9913) { b5763 } // FixConvert(b5763,TRUE,_32,_0) (Same Type. No op)
    val x9904 = x9903 // x9904 = DataAsBits(x9903)
    val x9905 = withCtrl(x9913) { OpDef(op=BitAnd, inputs=List(x9904, Const(0))).name("x9905").srcCtx("v16_plasticine.scala:106:42") } // VectorSlice(x9904,0,0) strMask=00000000000000000000000000000000
    val x9906 = x9905 // x9906 = BitsAsData(x9905,FixPt[TRUE,_32,_0])
    val x9907 = withCtrl(x9913) { OpDef(op=FixEql, inputs=List(x9906, Const(0))).name("x9907").srcCtx("v16_plasticine.scala:106:54") } // FixEql(x9906,Const(0))
    val x9908 = withCtrl(x9913) { OpDef(op=MuxOp, inputs=List(x9907, Const(0), Const(512))).name("x9908").srcCtx("v16_plasticine.scala:106:31:load_offset") } // Mux(x9907,Const(0),Const(512))
    val x9909 = withCtrl(x9913) { OpDef(op=MuxOp, inputs=List(x9907, Const(512), Const(0))).name("x9909").srcCtx("v16_plasticine.scala:107:31:store_offset") } // Mux(x9907,Const(512),Const(0))
    val x9910 = withCtrl(x9913) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x9910").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x9911_x9901 = withCtrl(x9913) { WriteMem(x9901, x9908).name("x9911_x9901").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9901,x9908,x9910)
    val x9912_x9902 = withCtrl(x9913) { WriteMem(x9902, x9909).name("x9912_x9902").srcCtx("v16_plasticine.scala:150:40") } // RegWrite(x9902,x9909,x9910)
    val x9914 = withCtrl(x10257) { ReadMem(x9744).name("x9914").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9744)
    val x9915 = withCtrl(x10257) { Counter(min=Const(0), max=x9914, step=Const(1), par=1).name("x9915").srcCtx("v16_plasticine.scala:108:35") } // CounterNew(Const(0),x9914,Const(1),Const(1))
    val x9916 = withCtrl(x10257) { CounterChain(List(x9915)).name("x9916").srcCtx("v16_plasticine.scala:108:54") } // CounterChainNew(List(x9915))
    val x10256 = withCtrl(x10257) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9916).name("x10256").srcCtx("v16_plasticine.scala:108:54") } // UnrolledForeach(List(b5764, b5760),x9916,Block(Const(())),List(List(b5944)),List(List(b5945)))
    val b5944 = withCtrl(x10256) { CounterIter(x9915, Some(0)).name("b5944") } // b5944
    val b5945 = withCtrl(x10256) { Const(true).name("b5945") } // b5945
    val x9917_d0_b0 = withCtrl(x10256) { SRAM(size=50176, banking=Strided(banks=1, stride=50176)).name("x9917_d0_b0").srcCtx("v16_plasticine.scala:109:38:tmp_SRAM_conv") } // x9917 = SRAMNew(ArrayBuffer(Const(1), Const(224), Const(224)))
    isAccum(x9917_d0_b0) = false
    bufferDepthOf(x9917_d0_b0) = 2
    staticDimsOf(x9917_d0_b0) = List(1, 224, 224)
    val x9917_d1_b0 = withCtrl(x10256) { SRAM(size=50176, banking=Strided(banks=1, stride=50176)).name("x9917_d1_b0").srcCtx("v16_plasticine.scala:109:38:tmp_SRAM_conv") } // x9917 = SRAMNew(ArrayBuffer(Const(1), Const(224), Const(224)))
    isAccum(x9917_d1_b0) = true
    bufferDepthOf(x9917_d1_b0) = 1
    staticDimsOf(x9917_d1_b0) = List(1, 224, 224)
    val x9918 = withCtrl(x10256) { ReadMem(x9743).name("x9918").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9743)
    val x9919 = withCtrl(x10256) { Counter(min=Const(0), max=x9918, step=Const(1), par=1).name("x9919").srcCtx("v16_plasticine.scala:110:79") } // CounterNew(Const(0),x9918,Const(1),Const(1))
    val x9920 = withCtrl(x10256) { CounterChain(List(x9919)).name("x9920").srcCtx("v16_plasticine.scala:130:12") } // CounterChainNew(List(x9919))
    val x10138 = withCtrl(x10256) { LoopController(style=MetaPipe, level=OuterControl, cchain=x9920).name("x10138").srcCtx("v16_plasticine.scala:130:12") } // UnrolledReduce(List(b5945, b5764, b5760),x9920,x9917,Block((x9917) => Const(())),List(List(b5955)),List(List(b5956)))
    val b5955 = withCtrl(x10138) { CounterIter(x9919, Some(0)).name("b5955") } // b5955
    val b5956 = withCtrl(x10138) { Const(true).name("b5956") } // b5956
    val x9921_d0_b0 = withCtrl(x10138) { SRAM(size=16, banking=Strided(banks=1, stride=16)).name("x9921_d0_b0").srcCtx("v16_plasticine.scala:112:38:weight_SRAM") } // x9921 = SRAMNew(ArrayBuffer(Const(1), Const(1), Const(16)))
    isAccum(x9921_d0_b0) = false
    bufferDepthOf(x9921_d0_b0) = 4
    staticDimsOf(x9921_d0_b0) = List(1, 1, 16)
    val x9922 = withCtrl(x10138) { Reg(init=Some(0)).name("x9922").srcCtx("v16_plasticine.scala:130:12") } // x9922 = RegNew(Const(0))
    isAccum(x9922) = false
    bufferDepthOf(x9922) = 2
    val x9931 = withCtrl(x10138) { UnitController(style=SeqPipe, level=InnerControl).name("x9931").srcCtx("v16_plasticine.scala:130:12") } // UnitPipe(List(b5956, b5945, b5764, b5760),Block(Const(())))
    val x9923 = withCtrl(x9931) { ReadMem(x9745).name("x9923").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9745)
    val x9924 = withCtrl(x9931) { OpDef(op=FixAdd, inputs=List(x9923, b5944)).name("x9924").srcCtx("v16_plasticine.scala:113:56:x$17") } // FixAdd(x9923,b5944)
    val x9925 = withCtrl(x9931) { OpDef(op=FixAdd, inputs=List(x9924, Const(1))).name("x9925").srcCtx("v16_plasticine.scala:113:91") } // FixAdd(x9924,Const(1))
    val x9926 = withCtrl(x9931) { OpDef(op=FixAdd, inputs=List(b5955, Const(1))).name("x9926").srcCtx("v16_plasticine.scala:113:108") } // FixAdd(b5955,Const(1))
    val x9927 = withCtrl(x9931) { OpDef(op=BitAnd, inputs=List(b5956, b5945)).name("x9927").srcCtx("UnrollingBase.scala:28:66") } // And(b5956,b5945)
    val x9928 = withCtrl(x9931) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x9928").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x9929 = withCtrl(x9931) { OpDef(op=BitAnd, inputs=List(x9927, x9928)).name("x9929").srcCtx("UnrollingBase.scala:28:66") } // And(x9927,x9928)
    val x9930_x9922 = withCtrl(x9931) { WriteMem(x9922, x9924).name("x9930_x9922").srcCtx("v16_plasticine.scala:130:12") } // RegWrite(x9922,x9924,x9929)
    val x9932 = withCtrl(x10138) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9932").srcCtx("v16_plasticine.scala:113:25") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9933 = withCtrl(x10138) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9933").srcCtx("v16_plasticine.scala:113:25") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9934 = withCtrl(x10138) { CounterChain(List(x9932,x9933)).name("x9934").srcCtx("v16_plasticine.scala:113:25") } // CounterChainNew(List(x9932, x9933))
    val x9972 = withCtrl(x10138) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9934).name("x9972").srcCtx("v16_plasticine.scala:113:25") } // UnrolledForeach(List(b5956, b5945, b5764, b5760),x9934,Block(Const(())),List(List(b5971), List(b5972)),List(List(b5973), List(b5974)))
    val b5971 = withCtrl(x9972) { CounterIter(x9932, Some(0)).name("b5971") } // b5971
    val b5973 = withCtrl(x9972) { Const(true).name("b5973") } // b5973
    val b5972 = withCtrl(x9972) { CounterIter(x9933, Some(0)).name("b5972") } // b5972
    val b5974 = withCtrl(x9972) { Const(true).name("b5974") } // b5974
    val b10512 = withCtrl(x9972) { StreamOut(field="offset").name("b10512").srcCtx("v16_plasticine.scala:113:25") } // x9935 = StreamOutNew(BurstCmdBus)
    isAccum(b10512) = false
    bufferDepthOf(b10512) = 1
    val b10513 = withCtrl(x9972) { StreamOut(field="size").name("b10513").srcCtx("v16_plasticine.scala:113:25") } // x9935 = StreamOutNew(BurstCmdBus)
    isAccum(b10513) = false
    bufferDepthOf(b10513) = 1
    val x9936 = withCtrl(x9972) { StreamIn(field="data").name("x9936").srcCtx("v16_plasticine.scala:113:25") } // x9936 = StreamInNew(BurstDataBus())
    isAccum(x9936) = false
    bufferDepthOf(x9936) = 1
    val x9958 = withCtrl(x9972) { UnitController(style=SeqPipe, level=InnerControl).name("x9958").srcCtx("v16_plasticine.scala:113:25") } // UnitPipe(List(b5973, b5974, b5956, b5945, b5764, b5760),Block(x9957))
    val x9937 = withCtrl(x9958) { ReadMem(x9922).name("x9937").srcCtx("v16_plasticine.scala:130:12") } // RegRead(x9922)
    val x9938 = withCtrl(x9958) { OpDef(op=FixAdd, inputs=List(x9937, b5971)).name("x9938").srcCtx("v16_plasticine.scala:113:25") } // FixAdd(x9937,b5971)
    val x9939 = withCtrl(x9958) { OpDef(op=FixAdd, inputs=List(b5955, b5972)).name("x9939").srcCtx("v16_plasticine.scala:113:25") } // FixAdd(b5955,b5972)
    val x9940 = withCtrl(x9958) { x9938 } // FixConvert(x9938,TRUE,_32,_0) (Same Type. No op)
    val x9941 = withCtrl(x9958) { OpDef(op=FixSla, inputs=List(x9940, Const(13))).name("x9941").srcCtx("v16_plasticine.scala:113:25") } // FixLsh(x9940,Const(13))
    val x9942 = withCtrl(x9958) { x9939 } // FixConvert(x9939,TRUE,_32,_0) (Same Type. No op)
    val x9943 = withCtrl(x9958) { OpDef(op=FixSla, inputs=List(x9942, Const(4))).name("x9943").srcCtx("v16_plasticine.scala:113:25") } // FixLsh(x9942,Const(4))
    val x9944 = withCtrl(x9958) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x9945 = withCtrl(x9958) { OpDef(op=FixAdd, inputs=List(x9941, x9943)).name("x9945").srcCtx("v16_plasticine.scala:113:25") } // FixAdd(x9941,x9943)
    val x9946 = withCtrl(x9958) { OpDef(op=FixAdd, inputs=List(x9945, x9944)).name("x9946").srcCtx("v16_plasticine.scala:113:25") } // FixAdd(x9945,x9944)
    val x9947 = withCtrl(x9958) { OpDef(op=FixSla, inputs=List(x9946, Const(2))).name("x9947").srcCtx("v16_plasticine.scala:113:25") } // FixLsh(x9946,Const(2))
    val x9948 = withCtrl(x9958) { x9947 } // FixConvert(x9947,TRUE,_64,_0)
    val x9949 = withCtrl(x9958) { DramAddress(x9728).name("x9949").srcCtx("v16_plasticine.scala:113:25") } // GetDRAMAddress(x9728)
    val x9951_x9950 = withCtrl(x9958) { OpDef(op=FixAdd, inputs=List(x9948, x9949)).name("x9951_x9950").srcCtx("v16_plasticine.scala:113:25") } // FixAdd(x9948,x9949)
    // x9951 = SimpleStruct(ArrayBuffer((offset,x9950), (size,Const(64)), (isLoad,Const(true))))
    val x9952 = withCtrl(x9958) { OpDef(op=BitAnd, inputs=List(b5973, b5974)).name("x9952").srcCtx("UnrollingBase.scala:28:66") } // And(b5973,b5974)
    val x9953 = withCtrl(x9958) { OpDef(op=BitAnd, inputs=List(b5956, b5945)).name("x9953").srcCtx("UnrollingBase.scala:28:66") } // And(b5956,b5945)
    val x9954 = withCtrl(x9958) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x9954").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x9955 = withCtrl(x9958) { OpDef(op=BitAnd, inputs=List(x9952, x9953)).name("x9955").srcCtx("UnrollingBase.scala:28:66") } // And(x9952,x9953)
    val x9956 = withCtrl(x9958) { OpDef(op=BitAnd, inputs=List(x9955, x9954)).name("x9956").srcCtx("UnrollingBase.scala:28:66") } // And(x9955,x9954)
    val x9957_b10514_b10512 = withCtrl(x9958) { WriteMem(b10512, x9951_x9950).name("x9957_b10514_b10512").srcCtx("v16_plasticine.scala:113:25") } // StreamWrite(x9935,x9951,x9956)
    val x9957_b10515_b10513 = withCtrl(x9958) { WriteMem(b10513, Const(64)).name("x9957_b10515_b10513").srcCtx("v16_plasticine.scala:113:25") } // StreamWrite(x9935,x9951,x9956)
    val x9959 = withCtrl(x9972) { FringeDenseLoad(dram=List(x9728), cmdStream=List(b10512, b10513), dataStream=List(x9936)).name("x9959").srcCtx("v16_plasticine.scala:113:25") } // FringeDenseLoad(x9728,x9935,x9936)
    val x9960 = withCtrl(x9972) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x9960").srcCtx("v16_plasticine.scala:113:25") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x9961 = withCtrl(x9972) { CounterChain(List(x9960)).name("x9961").srcCtx("v16_plasticine.scala:113:25") } // CounterChainNew(List(x9960))
    val x9971 = withCtrl(x9972) { LoopController(style=InnerPipe, level=InnerControl, cchain=x9961).name("x9971").srcCtx("v16_plasticine.scala:113:25") } // UnrolledForeach(List(b5973, b5974, b5956, b5945, b5764, b5760),x9961,Block(Const(())),List(List(b6002)),List(List(b6003)))
    val b6002 = withCtrl(x9971) { CounterIter(x9960, None).name("b6002") } // b6002
    val b6003 = withCtrl(x9971) { Const(true).name("b6003") } // b6003
    val x9962 = withCtrl(x9971) { OpDef(op=BitAnd, inputs=List(b6003, b5973)).name("x9962").srcCtx("UnrollingBase.scala:28:66") } // And(b6003,b5973)
    val x9963 = withCtrl(x9971) { OpDef(op=BitAnd, inputs=List(b5974, b5956)).name("x9963").srcCtx("UnrollingBase.scala:28:66") } // And(b5974,b5956)
    val x9964 = withCtrl(x9971) { OpDef(op=BitAnd, inputs=List(b5945, b5764)).name("x9964").srcCtx("UnrollingBase.scala:28:66") } // And(b5945,b5764)
    val x9965 = withCtrl(x9971) { OpDef(op=BitAnd, inputs=List(x9962, x9963)).name("x9965").srcCtx("UnrollingBase.scala:28:66") } // And(x9962,x9963)
    val x9966 = withCtrl(x9971) { OpDef(op=BitAnd, inputs=List(x9964, b5760)).name("x9966").srcCtx("UnrollingBase.scala:28:66") } // And(x9964,b5760)
    val x9967 = withCtrl(x9971) { OpDef(op=BitAnd, inputs=List(x9965, x9966)).name("x9967").srcCtx("UnrollingBase.scala:28:66") } // And(x9965,x9966)
    val x9968_x9968 = withCtrl(x9971) { ReadMem(x9936).name("x9968_x9968").srcCtx("v16_plasticine.scala:113:25") } // ParStreamRead(x9936,List(x9967))
    val x9969_x9969 = withCtrl(x9971) { x9968_x9968 } // VectorApply(x9968,0)
    val x9970 = withCtrl(x9971) { StoreBanks(List(List(x9921_d0_b0)), List(b5971, b5972, b6002), x9969_x9969).name("x9970").srcCtx("v16_plasticine.scala:113:25") } // ParSRAMStore(x9921,List(List(b5971, b5972, b6002)),List(x9969),List(x9967))
    val x9973_d0_b0 = withCtrl(x10138) { SRAM(size=50176, banking=Strided(banks=1, stride=224)).name("x9973_d0_b0").srcCtx("v16_plasticine.scala:115:32:img2D") } // x9973 = SRAMNew(ArrayBuffer(Const(224), Const(224)))
    isAccum(x9973_d0_b0) = false
    bufferDepthOf(x9973_d0_b0) = 2
    staticDimsOf(x9973_d0_b0) = List(224, 224)
    val x9974 = withCtrl(x10138) { Reg(init=Some(0)).name("x9974").srcCtx("v16_plasticine.scala:130:12") } // x9974 = RegNew(Const(0))
    isAccum(x9974) = false
    bufferDepthOf(x9974) = 2
    val x9983 = withCtrl(x10138) { UnitController(style=SeqPipe, level=InnerControl).name("x9983").srcCtx("v16_plasticine.scala:130:12") } // UnitPipe(List(b5956, b5945, b5764, b5760),Block(Const(())))
    val x9975 = withCtrl(x9983) { ReadMem(x9901).name("x9975").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9901)
    val x9976 = withCtrl(x9983) { OpDef(op=FixAdd, inputs=List(x9975, b5955)).name("x9976").srcCtx("v16_plasticine.scala:116:57") } // FixAdd(x9975,b5955)
    val x9977 = withCtrl(x9983) { OpDef(op=FixAdd, inputs=List(b5759, Const(1))).name("x9977").srcCtx("v16_plasticine.scala:116:33") } // FixAdd(b5759,Const(1))
    val x9978 = withCtrl(x9983) { OpDef(op=FixAdd, inputs=List(x9976, Const(1))).name("x9978").srcCtx("v16_plasticine.scala:116:33") } // FixAdd(x9976,Const(1))
    val x9979 = withCtrl(x9983) { OpDef(op=BitAnd, inputs=List(b5956, b5945)).name("x9979").srcCtx("UnrollingBase.scala:28:66") } // And(b5956,b5945)
    val x9980 = withCtrl(x9983) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x9980").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x9981 = withCtrl(x9983) { OpDef(op=BitAnd, inputs=List(x9979, x9980)).name("x9981").srcCtx("UnrollingBase.scala:28:66") } // And(x9979,x9980)
    val x9982_x9974 = withCtrl(x9983) { WriteMem(x9974, x9976).name("x9982_x9974").srcCtx("v16_plasticine.scala:130:12") } // RegWrite(x9974,x9976,x9981)
    val x9984 = withCtrl(x10138) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9984").srcCtx("v16_plasticine.scala:116:19") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9985 = withCtrl(x10138) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x9985").srcCtx("v16_plasticine.scala:116:19") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x9986 = withCtrl(x10138) { ReadMem(x9741_d2).name("x9986").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9741)
    val x9987 = withCtrl(x10138) { Counter(min=Const(0), max=x9986, step=Const(1), par=1).name("x9987").srcCtx("v16_plasticine.scala:116:19") } // CounterNew(Const(0),x9986,Const(1),Const(1))
    val x9988 = withCtrl(x10138) { CounterChain(List(x9984,x9985,x9987)).name("x9988").srcCtx("v16_plasticine.scala:116:19") } // CounterChainNew(List(x9984, x9985, x9987))
    val x10031 = withCtrl(x10138) { LoopController(style=StreamPipe, level=OuterControl, cchain=x9988).name("x10031").srcCtx("v16_plasticine.scala:116:19") } // UnrolledForeach(List(b5956, b5945, b5764, b5760),x9988,Block(Const(())),List(List(b6031), List(b6032), List(b6033)),List(List(b6034), List(b6035), List(b6036)))
    val b6031 = withCtrl(x10031) { CounterIter(x9984, Some(0)).name("b6031") } // b6031
    val b6034 = withCtrl(x10031) { Const(true).name("b6034") } // b6034
    val b6032 = withCtrl(x10031) { CounterIter(x9985, Some(0)).name("b6032") } // b6032
    val b6035 = withCtrl(x10031) { Const(true).name("b6035") } // b6035
    val b6033 = withCtrl(x10031) { CounterIter(x9987, Some(0)).name("b6033") } // b6033
    val b6036 = withCtrl(x10031) { Const(true).name("b6036") } // b6036
    val b10516 = withCtrl(x10031) { StreamOut(field="offset").name("b10516").srcCtx("v16_plasticine.scala:116:19") } // x9989 = StreamOutNew(BurstCmdBus)
    isAccum(b10516) = false
    bufferDepthOf(b10516) = 1
    val b10517 = withCtrl(x10031) { StreamOut(field="size").name("b10517").srcCtx("v16_plasticine.scala:116:19") } // x9989 = StreamOutNew(BurstCmdBus)
    isAccum(b10517) = false
    bufferDepthOf(b10517) = 1
    val x9990 = withCtrl(x10031) { StreamIn(field="data").name("x9990").srcCtx("v16_plasticine.scala:116:19") } // x9990 = StreamInNew(BurstDataBus())
    isAccum(x9990) = false
    bufferDepthOf(x9990) = 1
    val x10016 = withCtrl(x10031) { UnitController(style=SeqPipe, level=InnerControl).name("x10016").srcCtx("v16_plasticine.scala:116:19") } // UnitPipe(List(b6034, b6035, b6036, b5956, b5945, b5764, b5760),Block(x10015))
    val x9991 = withCtrl(x10016) { OpDef(op=FixAdd, inputs=List(b5759, b6031)).name("x9991").srcCtx("v16_plasticine.scala:116:19") } // FixAdd(b5759,b6031)
    val x9992 = withCtrl(x10016) { ReadMem(x9974).name("x9992").srcCtx("v16_plasticine.scala:130:12") } // RegRead(x9974)
    val x9993 = withCtrl(x10016) { OpDef(op=FixAdd, inputs=List(x9992, b6032)).name("x9993").srcCtx("v16_plasticine.scala:116:19") } // FixAdd(x9992,b6032)
    val x9994 = withCtrl(x10016) { x9991 } // FixConvert(x9991,TRUE,_32,_0) (Same Type. No op)
    val x9995 = withCtrl(x10016) { OpDef(op=FixMul, inputs=List(x9994, Const(51380224))).name("x9995").srcCtx("v16_plasticine.scala:116:19") } // FixMul(x9994,Const(51380224))
    val x9996 = withCtrl(x10016) { x9993 } // FixConvert(x9993,TRUE,_32,_0) (Same Type. No op)
    val x9997 = withCtrl(x10016) { OpDef(op=FixMul, inputs=List(x9996, Const(50176))).name("x9997").srcCtx("v16_plasticine.scala:116:19") } // FixMul(x9996,Const(50176))
    val x9998 = withCtrl(x10016) { b6033 } // FixConvert(b6033,TRUE,_32,_0) (Same Type. No op)
    val x9999 = withCtrl(x10016) { OpDef(op=FixMul, inputs=List(x9998, Const(224))).name("x9999").srcCtx("v16_plasticine.scala:116:19") } // FixMul(x9998,Const(224))
    val x10000 = withCtrl(x10016) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10001 = withCtrl(x10016) { OpDef(op=FixAdd, inputs=List(x9995, x9997)).name("x10001").srcCtx("v16_plasticine.scala:116:19") } // FixAdd(x9995,x9997)
    val x10002 = withCtrl(x10016) { OpDef(op=FixAdd, inputs=List(x9999, x10000)).name("x10002").srcCtx("v16_plasticine.scala:116:19") } // FixAdd(x9999,x10000)
    val x10003 = withCtrl(x10016) { OpDef(op=FixAdd, inputs=List(x10001, x10002)).name("x10003").srcCtx("v16_plasticine.scala:116:19") } // FixAdd(x10001,x10002)
    val x10004 = withCtrl(x10016) { OpDef(op=FixSla, inputs=List(x10003, Const(2))).name("x10004").srcCtx("v16_plasticine.scala:116:19") } // FixLsh(x10003,Const(2))
    val x10005 = withCtrl(x10016) { x10004 } // FixConvert(x10004,TRUE,_64,_0)
    val x10006 = withCtrl(x10016) { DramAddress(x9725).name("x10006").srcCtx("v16_plasticine.scala:116:19") } // GetDRAMAddress(x9725)
    val x10008_x10007 = withCtrl(x10016) { OpDef(op=FixAdd, inputs=List(x10005, x10006)).name("x10008_x10007").srcCtx("v16_plasticine.scala:116:19") } // FixAdd(x10005,x10006)
    // x10008 = SimpleStruct(ArrayBuffer((offset,x10007), (size,Const(896)), (isLoad,Const(true))))
    val x10009 = withCtrl(x10016) { OpDef(op=BitAnd, inputs=List(b6034, b6035)).name("x10009").srcCtx("UnrollingBase.scala:28:66") } // And(b6034,b6035)
    val x10010 = withCtrl(x10016) { OpDef(op=BitAnd, inputs=List(b6036, b5956)).name("x10010").srcCtx("UnrollingBase.scala:28:66") } // And(b6036,b5956)
    val x10011 = withCtrl(x10016) { OpDef(op=BitAnd, inputs=List(b5945, b5764)).name("x10011").srcCtx("UnrollingBase.scala:28:66") } // And(b5945,b5764)
    val x10012 = withCtrl(x10016) { OpDef(op=BitAnd, inputs=List(x10009, x10010)).name("x10012").srcCtx("UnrollingBase.scala:28:66") } // And(x10009,x10010)
    val x10013 = withCtrl(x10016) { OpDef(op=BitAnd, inputs=List(x10011, b5760)).name("x10013").srcCtx("UnrollingBase.scala:28:66") } // And(x10011,b5760)
    val x10014 = withCtrl(x10016) { OpDef(op=BitAnd, inputs=List(x10012, x10013)).name("x10014").srcCtx("UnrollingBase.scala:28:66") } // And(x10012,x10013)
    val x10015_b10518_b10516 = withCtrl(x10016) { WriteMem(b10516, x10008_x10007).name("x10015_b10518_b10516").srcCtx("v16_plasticine.scala:116:19") } // StreamWrite(x9989,x10008,x10014)
    val x10015_b10519_b10517 = withCtrl(x10016) { WriteMem(b10517, Const(896)).name("x10015_b10519_b10517").srcCtx("v16_plasticine.scala:116:19") } // StreamWrite(x9989,x10008,x10014)
    val x10017 = withCtrl(x10031) { FringeDenseLoad(dram=List(x9725), cmdStream=List(b10516, b10517), dataStream=List(x9990)).name("x10017").srcCtx("v16_plasticine.scala:116:19") } // FringeDenseLoad(x9725,x9989,x9990)
    val x10018 = withCtrl(x10031) { Counter(min=Const(0), max=Const(224), step=Const(1), par=1).name("x10018").srcCtx("v16_plasticine.scala:116:19") } // CounterNew(Const(0),Const(224),Const(1),Const(1))
    val x10019 = withCtrl(x10031) { CounterChain(List(x10018)).name("x10019").srcCtx("v16_plasticine.scala:116:19") } // CounterChainNew(List(x10018))
    val x10030 = withCtrl(x10031) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10019).name("x10030").srcCtx("v16_plasticine.scala:116:19") } // UnrolledForeach(List(b6034, b6035, b6036, b5956, b5945, b5764, b5760),x10019,Block(Const(())),List(List(b6068)),List(List(b6069)))
    val b6068 = withCtrl(x10030) { CounterIter(x10018, None).name("b6068") } // b6068
    val b6069 = withCtrl(x10030) { Const(true).name("b6069") } // b6069
    val x10020 = withCtrl(x10030) { OpDef(op=BitAnd, inputs=List(b6069, b6034)).name("x10020").srcCtx("UnrollingBase.scala:28:66") } // And(b6069,b6034)
    val x10021 = withCtrl(x10030) { OpDef(op=BitAnd, inputs=List(b6035, b6036)).name("x10021").srcCtx("UnrollingBase.scala:28:66") } // And(b6035,b6036)
    val x10022 = withCtrl(x10030) { OpDef(op=BitAnd, inputs=List(b5956, b5945)).name("x10022").srcCtx("UnrollingBase.scala:28:66") } // And(b5956,b5945)
    val x10023 = withCtrl(x10030) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x10023").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x10024 = withCtrl(x10030) { OpDef(op=BitAnd, inputs=List(x10020, x10021)).name("x10024").srcCtx("UnrollingBase.scala:28:66") } // And(x10020,x10021)
    val x10025 = withCtrl(x10030) { OpDef(op=BitAnd, inputs=List(x10022, x10023)).name("x10025").srcCtx("UnrollingBase.scala:28:66") } // And(x10022,x10023)
    val x10026 = withCtrl(x10030) { OpDef(op=BitAnd, inputs=List(x10024, x10025)).name("x10026").srcCtx("UnrollingBase.scala:28:66") } // And(x10024,x10025)
    val x10027_x10027 = withCtrl(x10030) { ReadMem(x9990).name("x10027_x10027").srcCtx("v16_plasticine.scala:116:19") } // ParStreamRead(x9990,List(x10026))
    val x10028_x10028 = withCtrl(x10030) { x10027_x10027 } // VectorApply(x10027,0)
    val x10029 = withCtrl(x10030) { StoreBanks(List(List(x9973_d0_b0)), List(b6033, b6068), x10028_x10028).name("x10029").srcCtx("v16_plasticine.scala:116:19") } // ParSRAMStore(x9973,List(List(b6033, b6068)),List(x10028),List(x10026))
    val x10032_d0_b0 = withCtrl(x10138) { SRAM(size=50176, banking=Strided(banks=1, stride=50176)).name("x10032_d0_b0").srcCtx("v16_plasticine.scala:118:33:result") } // x10032 = SRAMNew(ArrayBuffer(Const(1), Const(224), Const(224)))
    isAccum(x10032_d0_b0) = false
    bufferDepthOf(x10032_d0_b0) = 2
    staticDimsOf(x10032_d0_b0) = List(1, 224, 224)
    val x10033 = withCtrl(x10138) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10033").srcCtx("v16_plasticine.scala:119:47") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10034 = withCtrl(x10138) { ReadMem(x9741_d1).name("x10034").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9741)
    val x10035 = withCtrl(x10138) { Counter(min=Const(0), max=x10034, step=Const(1), par=1).name("x10035").srcCtx("v16_plasticine.scala:119:35") } // CounterNew(Const(0),x10034,Const(1),Const(1))
    val x10036 = withCtrl(x10138) { Counter(min=Const(0), max=x10034, step=Const(1), par=1).name("x10036").srcCtx("v16_plasticine.scala:119:23") } // CounterNew(Const(0),x10034,Const(1),Const(1))
    val x10037 = withCtrl(x10138) { CounterChain(List(x10036,x10035,x10033)).name("x10037").srcCtx("v16_plasticine.scala:119:56") } // CounterChainNew(List(x10036, x10035, x10033))
    val x10115 = withCtrl(x10138) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10037).name("x10115").srcCtx("v16_plasticine.scala:119:56") } // UnrolledForeach(List(b5956, b5945, b5764, b5760),x10037,Block(Const(())),List(List(b6088), List(b6089), List(b6090)),List(List(b6091), List(b6092), List(b6093)))
    val b6088 = withCtrl(x10115) { CounterIter(x10036, Some(0)).name("b6088") } // b6088
    val b6091 = withCtrl(x10115) { Const(true).name("b6091") } // b6091
    val b6089 = withCtrl(x10115) { CounterIter(x10035, Some(0)).name("b6089") } // b6089
    val b6092 = withCtrl(x10115) { Const(true).name("b6092") } // b6092
    val b6090 = withCtrl(x10115) { CounterIter(x10033, Some(0)).name("b6090") } // b6090
    val b6093 = withCtrl(x10115) { Const(true).name("b6093") } // b6093
    val x10038 = withCtrl(x10115) { Reg(init=Some(0)).name("x10038").srcCtx("v16_plasticine.scala:119:56") } // x10038 = RegNew(Const(0))
    isAccum(x10038) = false
    bufferDepthOf(x10038) = 2
    val x10039 = withCtrl(x10115) { Reg(init=Some(0)).name("x10039").srcCtx("v16_plasticine.scala:119:56") } // x10039 = RegNew(Const(0))
    isAccum(x10039) = false
    bufferDepthOf(x10039) = 2
    val x10040 = withCtrl(x10115) { Reg(init=Some(0)).name("x10040").srcCtx("v16_plasticine.scala:119:56") } // x10040 = RegNew(Const(0))
    isAccum(x10040) = false
    bufferDepthOf(x10040) = 2
    val x10041 = withCtrl(x10115) { Reg(init=Some(0)).name("x10041").srcCtx("v16_plasticine.scala:119:56") } // x10041 = RegNew(Const(0))
    isAccum(x10041) = false
    bufferDepthOf(x10041) = 2
    val x10042_d0 = withCtrl(x10115) { Reg(init=Some(0.0)).name("x10042_d0").srcCtx("v16_plasticine.scala:124:41:window") } // x10042 = RegNew(Const(0))
    isAccum(x10042_d0) = false
    bufferDepthOf(x10042_d0) = 2
    val x10042_d1 = withCtrl(x10115) { Reg(init=Some(0.0)).name("x10042_d1").srcCtx("v16_plasticine.scala:124:41:window") } // x10042 = RegNew(Const(0))
    isAccum(x10042_d1) = true
    bufferDepthOf(x10042_d1) = 1
    val x10067 = withCtrl(x10115) { UnitController(style=SeqPipe, level=InnerControl).name("x10067").srcCtx("v16_plasticine.scala:119:56") } // UnitPipe(List(b6091, b6092, b6093, b5956, b5945, b5764, b5760),Block(Const(())))
    val x10043 = withCtrl(x10067) { OpDef(op=FixSub, inputs=List(Const(1), b6088)).name("x10043").srcCtx("v16_plasticine.scala:120:70") } // FixSub(Const(1),b6088)
    val x10044 = withCtrl(x10067) { OpDef(op=FixMax, inputs=List(Const(0), x10043)).name("x10044").srcCtx("v16_plasticine.scala:120:49") } // Max(Const(0),x10043)
    val x10045 = withCtrl(x10067) { OpDef(op=FixMin, inputs=List(Const(2), x10044)).name("x10045").srcCtx("v16_plasticine.scala:120:34:row_start") } // Min(Const(2),x10044)
    val x10046 = withCtrl(x10067) { ReadMem(x9741_d0).name("x10046").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9741)
    val x10047 = withCtrl(x10067) { OpDef(op=FixAdd, inputs=List(Const(1), x10046)).name("x10047").srcCtx("v16_plasticine.scala:121:70") } // FixAdd(Const(1),x10046)
    val x10048 = withCtrl(x10067) { OpDef(op=FixSub, inputs=List(x10047, b6088)).name("x10048").srcCtx("v16_plasticine.scala:121:73") } // FixSub(x10047,b6088)
    val x10049 = withCtrl(x10067) { OpDef(op=FixMax, inputs=List(Const(1), x10048)).name("x10049").srcCtx("v16_plasticine.scala:121:49") } // Max(Const(1),x10048)
    val x10050 = withCtrl(x10067) { OpDef(op=FixMin, inputs=List(Const(3), x10049)).name("x10050").srcCtx("v16_plasticine.scala:121:34:row_end") } // Min(Const(3),x10049)
    val x10051 = withCtrl(x10067) { OpDef(op=FixSub, inputs=List(Const(1), b6089)).name("x10051").srcCtx("v16_plasticine.scala:122:70") } // FixSub(Const(1),b6089)
    val x10052 = withCtrl(x10067) { OpDef(op=FixMax, inputs=List(Const(0), x10051)).name("x10052").srcCtx("v16_plasticine.scala:122:49") } // Max(Const(0),x10051)
    val x10053 = withCtrl(x10067) { OpDef(op=FixMin, inputs=List(Const(2), x10052)).name("x10053").srcCtx("v16_plasticine.scala:122:34:col_start") } // Min(Const(2),x10052)
    val x10054 = withCtrl(x10067) { OpDef(op=FixSub, inputs=List(x10047, b6089)).name("x10054").srcCtx("v16_plasticine.scala:123:73") } // FixSub(x10047,b6089)
    val x10055 = withCtrl(x10067) { OpDef(op=FixMax, inputs=List(Const(1), x10054)).name("x10055").srcCtx("v16_plasticine.scala:123:49") } // Max(Const(1),x10054)
    val x10056 = withCtrl(x10067) { OpDef(op=FixMin, inputs=List(Const(3), x10055)).name("x10056").srcCtx("v16_plasticine.scala:123:34:col_end") } // Min(Const(3),x10055)
    val x10057 = withCtrl(x10067) { OpDef(op=BitAnd, inputs=List(b6091, b6092)).name("x10057").srcCtx("UnrollingBase.scala:28:66") } // And(b6091,b6092)
    val x10058 = withCtrl(x10067) { OpDef(op=BitAnd, inputs=List(b6093, b5956)).name("x10058").srcCtx("UnrollingBase.scala:28:66") } // And(b6093,b5956)
    val x10059 = withCtrl(x10067) { OpDef(op=BitAnd, inputs=List(b5945, b5764)).name("x10059").srcCtx("UnrollingBase.scala:28:66") } // And(b5945,b5764)
    val x10060 = withCtrl(x10067) { OpDef(op=BitAnd, inputs=List(x10057, x10058)).name("x10060").srcCtx("UnrollingBase.scala:28:66") } // And(x10057,x10058)
    val x10061 = withCtrl(x10067) { OpDef(op=BitAnd, inputs=List(x10059, b5760)).name("x10061").srcCtx("UnrollingBase.scala:28:66") } // And(x10059,b5760)
    val x10062 = withCtrl(x10067) { OpDef(op=BitAnd, inputs=List(x10060, x10061)).name("x10062").srcCtx("UnrollingBase.scala:28:66") } // And(x10060,x10061)
    val x10063_x10038 = withCtrl(x10067) { WriteMem(x10038, x10045).name("x10063_x10038").srcCtx("v16_plasticine.scala:119:56") } // RegWrite(x10038,x10045,x10062)
    val x10064_x10039 = withCtrl(x10067) { WriteMem(x10039, x10050).name("x10064_x10039").srcCtx("v16_plasticine.scala:119:56") } // RegWrite(x10039,x10050,x10062)
    val x10065_x10040 = withCtrl(x10067) { WriteMem(x10040, x10053).name("x10065_x10040").srcCtx("v16_plasticine.scala:119:56") } // RegWrite(x10040,x10053,x10062)
    val x10066_x10041 = withCtrl(x10067) { WriteMem(x10041, x10056).name("x10066_x10041").srcCtx("v16_plasticine.scala:119:56") } // RegWrite(x10041,x10056,x10062)
    val x10068 = withCtrl(x10115) { ReadMem(x10041).name("x10068").srcCtx("v16_plasticine.scala:119:56") } // RegRead(x10041)
    val x10069 = withCtrl(x10115) { ReadMem(x10040).name("x10069").srcCtx("v16_plasticine.scala:119:56") } // RegRead(x10040)
    val x10070 = withCtrl(x10115) { Counter(min=x10069, max=x10068, step=Const(1), par=1).name("x10070").srcCtx("v16_plasticine.scala:124:87") } // CounterNew(x10069,x10068,Const(1),Const(1))
    val x10071 = withCtrl(x10115) { ReadMem(x10039).name("x10071").srcCtx("v16_plasticine.scala:119:56") } // RegRead(x10039)
    val x10072 = withCtrl(x10115) { ReadMem(x10038).name("x10072").srcCtx("v16_plasticine.scala:119:56") } // RegRead(x10038)
    val x10073 = withCtrl(x10115) { Counter(min=x10072, max=x10071, step=Const(1), par=1).name("x10073").srcCtx("v16_plasticine.scala:124:62") } // CounterNew(x10072,x10071,Const(1),Const(1))
    val x10074 = withCtrl(x10115) { CounterChain(List(x10073,x10070)).name("x10074").srcCtx("v16_plasticine.scala:126:16") } // CounterChainNew(List(x10073, x10070))
    val x10105 = withCtrl(x10115) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10074).name("x10105").srcCtx("v16_plasticine.scala:126:16") } // UnrolledReduce(List(b6091, b6092, b6093, b5956, b5945, b5764, b5760),x10074,x10042,Block((x10042) => Const(())),List(List(b6131), List(b6132)),List(List(b6133), List(b6134)))
    val b6131 = withCtrl(x10105) { CounterIter(x10073, Some(0)).name("b6131") } // b6131
    val b6133 = withCtrl(x10105) { Const(true).name("b6133") } // b6133
    val b6132 = withCtrl(x10105) { CounterIter(x10070, None).name("b6132") } // b6132
    val b6134 = withCtrl(x10105) { Const(true).name("b6134") } // b6134
    val x10075 = withCtrl(x10105) { OpDef(op=FixSub, inputs=List(b6131, Const(1))).name("x10075").srcCtx("v16_plasticine.scala:125:24") } // FixSub(b6131,Const(1))
    val x10076 = withCtrl(x10105) { OpDef(op=FixAdd, inputs=List(x10075, b6088)).name("x10076").srcCtx("v16_plasticine.scala:125:26") } // FixAdd(x10075,b6088)
    val x10077 = withCtrl(x10105) { OpDef(op=FixSub, inputs=List(b6132, Const(1))).name("x10077").srcCtx("v16_plasticine.scala:125:31") } // FixSub(b6132,Const(1))
    val x10078 = withCtrl(x10105) { OpDef(op=FixAdd, inputs=List(x10077, b6089)).name("x10078").srcCtx("v16_plasticine.scala:125:33") } // FixAdd(x10077,b6089)
    val x10079 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(b6133, b6134)).name("x10079").srcCtx("UnrollingBase.scala:28:66") } // And(b6133,b6134)
    val x10080 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(b6091, b6092)).name("x10080").srcCtx("UnrollingBase.scala:28:66") } // And(b6091,b6092)
    val x10081 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(b6093, b5956)).name("x10081").srcCtx("UnrollingBase.scala:28:66") } // And(b6093,b5956)
    val x10082 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(b5945, b5764)).name("x10082").srcCtx("UnrollingBase.scala:28:66") } // And(b5945,b5764)
    val x10083 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(x10079, x10080)).name("x10083").srcCtx("UnrollingBase.scala:28:66") } // And(x10079,x10080)
    val x10084 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(x10081, x10082)).name("x10084").srcCtx("UnrollingBase.scala:28:66") } // And(x10081,x10082)
    val x10085 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(x10083, x10084)).name("x10085").srcCtx("UnrollingBase.scala:28:66") } // And(x10083,x10084)
    val x10086 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(x10085, b5760)).name("x10086").srcCtx("UnrollingBase.scala:28:66") } // And(x10085,b5760)
    val x10087 = withCtrl(x10105) { LoadBanks(List(x9973_d0_b0), List(x10076, x10078)).name("x10087").srcCtx("v16_plasticine.scala:125:22") } // ParSRAMLoad(x9973,List(List(x10076, x10078)),List(x10086))
    val x10088 = withCtrl(x10105) { x10087 } // VectorApply(x10087,0)
    val x10089 = withCtrl(x10105) { OpDef(op=FixMul, inputs=List(Const(3), b6131)).name("x10089").srcCtx("v16_plasticine.scala:125:58") } // FixMul(Const(3),b6131)
    val x10090 = withCtrl(x10105) { OpDef(op=FixAdd, inputs=List(x10089, b6132)).name("x10090").srcCtx("v16_plasticine.scala:125:61") } // FixAdd(x10089,b6132)
    val x10091 = withCtrl(x10105) { LoadBanks(List(x9921_d0_b0), List(b6090, Const(0), x10090)).name("x10091").srcCtx("v16_plasticine.scala:125:50") } // ParSRAMLoad(x9921,List(List(b6090, Const(0), x10090)),List(x10086))
    def split1 = {
    val x10092 = withCtrl(x10105) { x10091 } // VectorApply(x10091,0)
    val x10093 = withCtrl(x10105) { OpDef(op=FixMul, inputs=List(x10088, x10092)).name("x10093").srcCtx("v16_plasticine.scala:125:37") } // FixMul(x10088,x10092)
    val x10094 = withCtrl(x10105) { ReadMem(x10042_d1).name("x10094").srcCtx("v16_plasticine.scala:126:16") } // RegRead(x10042)
    val x10095 = withCtrl(x10105) { ReadMem(x10038).name("x10095").srcCtx("v16_plasticine.scala:119:56") } // RegRead(x10038)
    val x10096 = withCtrl(x10105) { OpDef(op=FixEql, inputs=List(b6131, x10095)).name("x10096").srcCtx("v16_plasticine.scala:126:16") } // FixEql(b6131,x10095)
    val x10097 = withCtrl(x10105) { ReadMem(x10040).name("x10097").srcCtx("v16_plasticine.scala:119:56") } // RegRead(x10040)
    val x10098 = withCtrl(x10105) { OpDef(op=FixEql, inputs=List(b6132, x10097)).name("x10098").srcCtx("v16_plasticine.scala:126:16") } // FixEql(b6132,x10097)
    val x10099 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(x10096, x10098)).name("x10099").srcCtx("v16_plasticine.scala:126:16") } // And(x10096,x10098)
    val x10100 = withCtrl(x10105) { ReduceAccumOp(op=FixAdd, input=x10093, accum=x10094).name("x10100").srcCtx("v16_plasticine.scala:126:18") } // FixAdd(x10093,x10094)
    val x10101 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(x10080, x10081)).name("x10101").srcCtx("UnrollingBase.scala:28:66") } // And(x10080,x10081)
    val x10102 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(x10082, b5760)).name("x10102").srcCtx("UnrollingBase.scala:28:66") } // And(x10082,b5760)
    val x10103 = withCtrl(x10105) { OpDef(op=BitAnd, inputs=List(x10101, x10102)).name("x10103").srcCtx("UnrollingBase.scala:28:66") } // And(x10101,x10102)
    val x10104_x10042_d0 = withCtrl(x10105) { WriteMem(x10042_d0, x10100).name("x10104_x10042_d0").srcCtx("v16_plasticine.scala:126:16") } // RegWrite(x10042,x10100,x10103)
    antiDepsOf(x10104_x10042_d0)=List(x10094)
    val x10104_x10042_d1 = withCtrl(x10105) { WriteMem(x10042_d1, x10100).name("x10104_x10042_d1").srcCtx("v16_plasticine.scala:126:16") } // RegWrite(x10042,x10100,x10103)
    antiDepsOf(x10104_x10042_d1)=List(x10094)
    val x10114 = withCtrl(x10115) { UnitController(style=SeqPipe, level=InnerControl).name("x10114").srcCtx("v16_plasticine.scala:119:56") } // UnitPipe(List(b6091, b6092, b6093, b5956, b5945, b5764, b5760),Block(Const(())))
    val x10106 = withCtrl(x10114) { ReadMem(x10042_d0).name("x10106").srcCtx("v16_plasticine.scala:127:40") } // RegRead(x10042)
    val x10107 = withCtrl(x10114) { OpDef(op=BitAnd, inputs=List(b6091, b6092)).name("x10107").srcCtx("UnrollingBase.scala:28:66") } // And(b6091,b6092)
    val x10108 = withCtrl(x10114) { OpDef(op=BitAnd, inputs=List(b6093, b5956)).name("x10108").srcCtx("UnrollingBase.scala:28:66") } // And(b6093,b5956)
    val x10109 = withCtrl(x10114) { OpDef(op=BitAnd, inputs=List(b5945, b5764)).name("x10109").srcCtx("UnrollingBase.scala:28:66") } // And(b5945,b5764)
    val x10110 = withCtrl(x10114) { OpDef(op=BitAnd, inputs=List(x10107, x10108)).name("x10110").srcCtx("UnrollingBase.scala:28:66") } // And(x10107,x10108)
    val x10111 = withCtrl(x10114) { OpDef(op=BitAnd, inputs=List(x10109, b5760)).name("x10111").srcCtx("UnrollingBase.scala:28:66") } // And(x10109,b5760)
    val x10112 = withCtrl(x10114) { OpDef(op=BitAnd, inputs=List(x10110, x10111)).name("x10112").srcCtx("UnrollingBase.scala:28:66") } // And(x10110,x10111)
    val x10113 = withCtrl(x10114) { StoreBanks(List(List(x10032_d0_b0)), List(b6090, b6088, b6089), x10106).name("x10113").srcCtx("v16_plasticine.scala:127:31") } // SRAMStore(x10032,ArrayBuffer(Const(1), Const(224), Const(224)),List(b6090, b6088, b6089),Const(0),x10106,x10112)
    val x10116 = withCtrl(x10138) { ReadMem(x9741_d3).name("x10116").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9741)
    val x10117 = withCtrl(x10138) { Counter(min=Const(0), max=x10116, step=Const(1), par=1).name("x10117").srcCtx("v16_plasticine.scala:110:41") } // CounterNew(Const(0),x10116,Const(1),Const(1))
    val x10118 = withCtrl(x10138) { Counter(min=Const(0), max=x10116, step=Const(1), par=1).name("x10118").srcCtx("v16_plasticine.scala:110:32") } // CounterNew(Const(0),x10116,Const(1),Const(1))
    val x10119 = withCtrl(x10138) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10119").srcCtx("v16_plasticine.scala:110:23") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10120 = withCtrl(x10138) { CounterChain(List(x10119,x10118,x10117)).name("x10120").srcCtx("v16_plasticine.scala:130:12") } // CounterChainNew(List(x10119, x10118, x10117))
    val x10137 = withCtrl(x10138) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10120).name("x10137").srcCtx("v16_plasticine.scala:130:12") } // UnrolledForeach(List(),x10120,Block(Const(())),List(List(b6174), List(b6175), List(b6176)),List(List(b6177), List(b6178), List(b6179)))
    val b6174 = withCtrl(x10137) { CounterIter(x10119, Some(0)).name("b6174") } // b6174
    val b6177 = withCtrl(x10137) { Const(true).name("b6177") } // b6177
    val b6175 = withCtrl(x10137) { CounterIter(x10118, Some(0)).name("b6175") } // b6175
    val b6178 = withCtrl(x10137) { Const(true).name("b6178") } // b6178
    val b6176 = withCtrl(x10137) { CounterIter(x10117, None).name("b6176") } // b6176
    val b6179 = withCtrl(x10137) { Const(true).name("b6179") } // b6179
    val x10121 = withCtrl(x10137) { OpDef(op=BitAnd, inputs=List(b6177, b6178)).name("x10121").srcCtx("UnrollingBase.scala:28:66") } // And(b6177,b6178)
    val x10122 = withCtrl(x10137) { OpDef(op=BitAnd, inputs=List(b6179, b5945)).name("x10122").srcCtx("UnrollingBase.scala:28:66") } // And(b6179,b5945)
    val x10123 = withCtrl(x10137) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x10123").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x10124 = withCtrl(x10137) { OpDef(op=BitAnd, inputs=List(x10121, x10122)).name("x10124").srcCtx("UnrollingBase.scala:28:66") } // And(x10121,x10122)
    val x10125 = withCtrl(x10137) { OpDef(op=BitAnd, inputs=List(x10124, x10123)).name("x10125").srcCtx("UnrollingBase.scala:28:66") } // And(x10124,x10123)
    val x10126 = withCtrl(x10137) { LoadBanks(List(x10032_d0_b0), List(b6174, b6175, b6176)).name("x10126").srcCtx("v16_plasticine.scala:130:12") } // ParSRAMLoad(x10032,List(List(b6174, b6175, b6176)),List(x10125))
    val x10127 = withCtrl(x10137) { x10126 } // VectorApply(x10126,0)
    val x10128 = withCtrl(x10137) { LoadBanks(List(x9917_d1_b0), List(b6174, b6175, b6176)).name("x10128").srcCtx("v16_plasticine.scala:130:12") } // ParSRAMLoad(x9917,List(List(b6174, b6175, b6176)),List(x10125))
    val x10129 = withCtrl(x10137) { x10128 } // VectorApply(x10128,0)
    val x10130 = withCtrl(x10137) { OpDef(op=BitAnd, inputs=List(b5956, b5945)).name("x10130").srcCtx("v16_plasticine.scala:130:12") } // And(b5956,b5945)
    val x10131 = withCtrl(x10137) { OpDef(op=BitAnd, inputs=List(x10130, x10123)).name("x10131").srcCtx("v16_plasticine.scala:130:12") } // And(x10130,x10123)
    val x10132 = withCtrl(x10137) { OpDef(op=BitAnd, inputs=List(x10131, x10125)).name("x10132").srcCtx("v16_plasticine.scala:130:12") } // And(x10131,x10125)
    val x10133 = withCtrl(x10137) { OpDef(op=FixEql, inputs=List(b5955, Const(0))).name("x10133").srcCtx("v16_plasticine.scala:130:12") } // FixEql(b5955,Const(0))
    val x10134 = withCtrl(x10137) { OpDef(op=FixAdd, inputs=List(x10127, x10129)).name("x10134").srcCtx("v16_plasticine.scala:130:14") } // FixAdd(x10127,x10129)
    val x10135 = withCtrl(x10137) { OpDef(op=MuxOp, inputs=List(x10133, x10127, x10134)).name("x10135").srcCtx("v16_plasticine.scala:130:12") } // Mux(x10133,x10127,x10134)
    val x10136 = withCtrl(x10137) { StoreBanks(List(List(x9917_d0_b0), List(x9917_d1_b0)), List(b6174, b6175, b6176), x10135).name("x10136").srcCtx("v16_plasticine.scala:130:12") } // ParSRAMStore(x9917,List(List(b6174, b6175, b6176)),List(x10135),List(x10125))
    antiDepsOf(x10136)=List(x10128)
    val x10139 = withCtrl(x10256) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10139").srcCtx("v16_plasticine.scala:132:21") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10140 = withCtrl(x10256) { CounterChain(List(x10139)).name("x10140").srcCtx("v16_plasticine.scala:132:27") } // CounterChainNew(List(x10139))
    val x10255 = withCtrl(x10256) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10140).name("x10255").srcCtx("v16_plasticine.scala:132:27") } // UnrolledForeach(List(b5945, b5764, b5760),x10140,Block(Const(())),List(List(b6200)),List(List(b6201)))
    val b6200 = withCtrl(x10255) { CounterIter(x10139, Some(0)).name("b6200") } // b6200
    val b6201 = withCtrl(x10255) { Const(true).name("b6201") } // b6201
    val x10141_d0_b0 = withCtrl(x10255) { SRAM(size=50176, banking=Strided(banks=1, stride=224)).name("x10141_d0_b0").srcCtx("v16_plasticine.scala:134:40:tmp_SRAM_pool") } // x10141 = SRAMNew(ArrayBuffer(Const(224), Const(224)))
    isAccum(x10141_d0_b0) = false
    bufferDepthOf(x10141_d0_b0) = 1
    staticDimsOf(x10141_d0_b0) = List(224, 224)
    val x10142 = withCtrl(x10255) { ReadMem(x9742_d1).name("x10142").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9742)
    val x10143 = withCtrl(x10255) { Counter(min=Const(0), max=x10142, step=Const(1), par=1).name("x10143").srcCtx("v16_plasticine.scala:135:33") } // CounterNew(Const(0),x10142,Const(1),Const(1))
    val x10144 = withCtrl(x10255) { Counter(min=Const(0), max=x10142, step=Const(1), par=1).name("x10144").srcCtx("v16_plasticine.scala:135:24") } // CounterNew(Const(0),x10142,Const(1),Const(1))
    val x10145 = withCtrl(x10255) { CounterChain(List(x10144,x10143)).name("x10145").srcCtx("v16_plasticine.scala:135:39") } // CounterChainNew(List(x10144, x10143))
    val x10185 = withCtrl(x10255) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10145).name("x10185").srcCtx("v16_plasticine.scala:135:39") } // UnrolledForeach(List(b6201, b5945, b5764, b5760),x10145,Block(Const(())),List(List(b6207), List(b6208)),List(List(b6209), List(b6210)))
    val b6207 = withCtrl(x10185) { CounterIter(x10144, Some(0)).name("b6207") } // b6207
    val b6209 = withCtrl(x10185) { Const(true).name("b6209") } // b6209
    val b6208 = withCtrl(x10185) { CounterIter(x10143, Some(0)).name("b6208") } // b6208
    val b6210 = withCtrl(x10185) { Const(true).name("b6210") } // b6210
    val x10146_d0 = withCtrl(x10185) { Reg(init=Some(0.0)).name("x10146_d0").srcCtx("v16_plasticine.scala:136:38:out") } // x10146 = RegNew(Const(0))
    isAccum(x10146_d0) = false
    bufferDepthOf(x10146_d0) = 2
    val x10146_d1 = withCtrl(x10185) { Reg(init=Some(0.0)).name("x10146_d1").srcCtx("v16_plasticine.scala:136:38:out") } // x10146 = RegNew(Const(0))
    isAccum(x10146_d1) = true
    bufferDepthOf(x10146_d1) = 1
    val x10147 = withCtrl(x10185) { ReadMem(x9740_d1).name("x10147").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9740)
    val x10148 = withCtrl(x10185) { Counter(min=Const(0), max=x10147, step=Const(1), par=1).name("x10148").srcCtx("v16_plasticine.scala:136:75") } // CounterNew(Const(0),x10147,Const(1),Const(1))
    val x10149 = withCtrl(x10185) { Counter(min=Const(0), max=x10147, step=Const(1), par=1).name("x10149").srcCtx("v16_plasticine.scala:136:59") } // CounterNew(Const(0),x10147,Const(1),Const(1))
    val x10150 = withCtrl(x10185) { CounterChain(List(x10149,x10148)).name("x10150").srcCtx("v16_plasticine.scala:138:17") } // CounterChainNew(List(x10149, x10148))
    val x10176 = withCtrl(x10185) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10150).name("x10176").srcCtx("v16_plasticine.scala:138:17") } // UnrolledReduce(List(b6209, b6210, b6201, b5945, b5764, b5760),x10150,x10146,Block((x10146) => Const(())),List(List(b6216), List(b6217)),List(List(b6218), List(b6219)))
    val b6216 = withCtrl(x10176) { CounterIter(x10149, Some(0)).name("b6216") } // b6216
    val b6218 = withCtrl(x10176) { Const(true).name("b6218") } // b6218
    val b6217 = withCtrl(x10176) { CounterIter(x10148, None).name("b6217") } // b6217
    val b6219 = withCtrl(x10176) { Const(true).name("b6219") } // b6219
    val x10151 = withCtrl(x10176) { ReadMem(x9740_d0).name("x10151").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9740)
    val x10152 = withCtrl(x10176) { OpDef(op=FixMul, inputs=List(b6207, x10151)).name("x10152").srcCtx("v16_plasticine.scala:137:48") } // FixMul(b6207,x10151)
    val x10153 = withCtrl(x10176) { OpDef(op=FixAdd, inputs=List(x10152, b6216)).name("x10153").srcCtx("v16_plasticine.scala:137:59") } // FixAdd(x10152,b6216)
    val x10154 = withCtrl(x10176) { OpDef(op=FixMul, inputs=List(b6208, x10151)).name("x10154").srcCtx("v16_plasticine.scala:137:66") } // FixMul(b6208,x10151)
    val x10155 = withCtrl(x10176) { OpDef(op=FixAdd, inputs=List(x10154, b6217)).name("x10155").srcCtx("v16_plasticine.scala:137:77") } // FixAdd(x10154,b6217)
    val x10156 = withCtrl(x10176) { OpDef(op=BitAnd, inputs=List(b6218, b6219)).name("x10156").srcCtx("UnrollingBase.scala:28:66") } // And(b6218,b6219)
    val x10157 = withCtrl(x10176) { OpDef(op=BitAnd, inputs=List(b6209, b6210)).name("x10157").srcCtx("UnrollingBase.scala:28:66") } // And(b6209,b6210)
    val x10158 = withCtrl(x10176) { OpDef(op=BitAnd, inputs=List(b6201, b5945)).name("x10158").srcCtx("UnrollingBase.scala:28:66") } // And(b6201,b5945)
    val x10159 = withCtrl(x10176) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x10159").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x10160 = withCtrl(x10176) { OpDef(op=BitAnd, inputs=List(x10156, x10157)).name("x10160").srcCtx("UnrollingBase.scala:28:66") } // And(x10156,x10157)
    val x10161 = withCtrl(x10176) { OpDef(op=BitAnd, inputs=List(x10158, x10159)).name("x10161").srcCtx("UnrollingBase.scala:28:66") } // And(x10158,x10159)
    val x10162 = withCtrl(x10176) { OpDef(op=BitAnd, inputs=List(x10160, x10161)).name("x10162").srcCtx("UnrollingBase.scala:28:66") } // And(x10160,x10161)
    val x10163 = withCtrl(x10176) { LoadBanks(List(x9917_d0_b0), List(b6200, x10153, x10155)).name("x10163").srcCtx("v16_plasticine.scala:137:43") } // ParSRAMLoad(x9917,List(List(b6200, x10153, x10155)),List(x10162))
    val x10164 = withCtrl(x10176) { x10163 } // VectorApply(x10163,0)
    val x10165 = withCtrl(x10176) { LoadBanks(List(x9746_d0_b0), List(b5944)).name("x10165").srcCtx("v16_plasticine.scala:137:94") } // SRAMLoad(x9746,ArrayBuffer(Const(512)),List(b5944),Const(0),x10162)
    val x10166 = withCtrl(x10176) { OpDef(op=FixAdd, inputs=List(x10164, x10165)).name("x10166").srcCtx("v16_plasticine.scala:137:83") } // FixAdd(x10164,x10165)
    val x10167 = withCtrl(x10176) { OpDef(op=FixMax, inputs=List(Const(0.0), x10166)).name("x10167").srcCtx("v16_plasticine.scala:137:20") } // Max(Const(0),x10166)
    val x10168 = withCtrl(x10176) { ReadMem(x10146_d1).name("x10168").srcCtx("v16_plasticine.scala:138:17") } // RegRead(x10146)
    val x10169 = withCtrl(x10176) { OpDef(op=FixEql, inputs=List(b6216, Const(0))).name("x10169").srcCtx("v16_plasticine.scala:138:17") } // FixEql(b6216,Const(0))
    val x10170 = withCtrl(x10176) { OpDef(op=FixEql, inputs=List(b6217, Const(0))).name("x10170").srcCtx("v16_plasticine.scala:138:17") } // FixEql(b6217,Const(0))
    val x10171 = withCtrl(x10176) { OpDef(op=BitAnd, inputs=List(x10169, x10170)).name("x10171").srcCtx("v16_plasticine.scala:138:17") } // And(x10169,x10170)
    val x10172 = withCtrl(x10176) { ReduceAccumOp(op=FixMax, input=x10167, accum=x10168).name("x10172").srcCtx("v16_plasticine.scala:138:31") } // Max(x10167,x10168)
    val x10173 = withCtrl(x10176) { OpDef(op=BitAnd, inputs=List(x10157, x10158)).name("x10173").srcCtx("UnrollingBase.scala:28:66") } // And(x10157,x10158)
    val x10174 = withCtrl(x10176) { OpDef(op=BitAnd, inputs=List(x10173, x10159)).name("x10174").srcCtx("UnrollingBase.scala:28:66") } // And(x10173,x10159)
    val x10175_x10146_d0 = withCtrl(x10176) { WriteMem(x10146_d0, x10172).name("x10175_x10146_d0").srcCtx("v16_plasticine.scala:138:17") } // RegWrite(x10146,x10172,x10174)
    antiDepsOf(x10175_x10146_d0)=List(x10168)
    val x10175_x10146_d1 = withCtrl(x10176) { WriteMem(x10146_d1, x10172).name("x10175_x10146_d1").srcCtx("v16_plasticine.scala:138:17") } // RegWrite(x10146,x10172,x10174)
    antiDepsOf(x10175_x10146_d1)=List(x10168)
    val x10184 = withCtrl(x10185) { UnitController(style=SeqPipe, level=InnerControl).name("x10184").srcCtx("v16_plasticine.scala:135:39") } // UnitPipe(List(b6209, b6210, b6201, b5945, b5764, b5760),Block(Const(())))
    val x10177 = withCtrl(x10184) { ReadMem(x10146_d0).name("x10177").srcCtx("v16_plasticine.scala:139:41") } // RegRead(x10146)
    val x10178 = withCtrl(x10184) { OpDef(op=BitAnd, inputs=List(b6209, b6210)).name("x10178").srcCtx("UnrollingBase.scala:28:66") } // And(b6209,b6210)
    val x10179 = withCtrl(x10184) { OpDef(op=BitAnd, inputs=List(b6201, b5945)).name("x10179").srcCtx("UnrollingBase.scala:28:66") } // And(b6201,b5945)
    val x10180 = withCtrl(x10184) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x10180").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x10181 = withCtrl(x10184) { OpDef(op=BitAnd, inputs=List(x10178, x10179)).name("x10181").srcCtx("UnrollingBase.scala:28:66") } // And(x10178,x10179)
    val x10182 = withCtrl(x10184) { OpDef(op=BitAnd, inputs=List(x10181, x10180)).name("x10182").srcCtx("UnrollingBase.scala:28:66") } // And(x10181,x10180)
    val x10183 = withCtrl(x10184) { StoreBanks(List(List(x10141_d0_b0)), List(b6207, b6208), x10177).name("x10183").srcCtx("v16_plasticine.scala:139:35") } // SRAMStore(x10141,ArrayBuffer(Const(224), Const(224)),List(b6207, b6208),Const(0),x10177,x10182)
    val x10186 = withCtrl(x10255) { Reg(init=Some(0)).name("x10186").srcCtx("v16_plasticine.scala:132:27") } // x10186 = RegNew(Const(0))
    isAccum(x10186) = false
    bufferDepthOf(x10186) = 1
    val x10196 = withCtrl(x10255) { UnitController(style=SeqPipe, level=InnerControl).name("x10196").srcCtx("v16_plasticine.scala:132:27") } // UnitPipe(List(b6201, b5945, b5764, b5760),Block(Const(())))
    val x10187 = withCtrl(x10196) { ReadMem(x9902).name("x10187").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9902)
    val x10188 = withCtrl(x10196) { OpDef(op=FixAdd, inputs=List(x10187, b5944)).name("x10188").srcCtx("v16_plasticine.scala:141:47") } // FixAdd(x10187,b5944)
    val x10189 = withCtrl(x10196) { OpDef(op=FixAdd, inputs=List(x10188, b6200)).name("x10189").srcCtx("v16_plasticine.scala:141:56") } // FixAdd(x10188,b6200)
    val x10190 = withCtrl(x10196) { OpDef(op=FixAdd, inputs=List(b5759, Const(1))).name("x10190").srcCtx("v16_plasticine.scala:141:22") } // FixAdd(b5759,Const(1))
    val x10191 = withCtrl(x10196) { OpDef(op=FixAdd, inputs=List(x10189, Const(1))).name("x10191").srcCtx("v16_plasticine.scala:141:22") } // FixAdd(x10189,Const(1))
    val x10192 = withCtrl(x10196) { OpDef(op=BitAnd, inputs=List(b6201, b5945)).name("x10192").srcCtx("UnrollingBase.scala:28:66") } // And(b6201,b5945)
    val x10193 = withCtrl(x10196) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x10193").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x10194 = withCtrl(x10196) { OpDef(op=BitAnd, inputs=List(x10192, x10193)).name("x10194").srcCtx("UnrollingBase.scala:28:66") } // And(x10192,x10193)
    val x10195_x10186 = withCtrl(x10196) { WriteMem(x10186, x10189).name("x10195_x10186").srcCtx("v16_plasticine.scala:132:27") } // RegWrite(x10186,x10189,x10194)
    val x10197 = withCtrl(x10255) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10197").srcCtx("v16_plasticine.scala:141:79") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10198 = withCtrl(x10255) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10198").srcCtx("v16_plasticine.scala:141:79") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10199 = withCtrl(x10255) { ReadMem(x9742_d0).name("x10199").srcCtx("v16_plasticine.scala:150:40") } // RegRead(x9742)
    val x10200 = withCtrl(x10255) { Counter(min=Const(0), max=x10199, step=Const(1), par=1).name("x10200").srcCtx("v16_plasticine.scala:141:79") } // CounterNew(Const(0),x10199,Const(1),Const(1))
    val x10201 = withCtrl(x10255) { CounterChain(List(x10197,x10198,x10200)).name("x10201").srcCtx("v16_plasticine.scala:141:79") } // CounterChainNew(List(x10197, x10198, x10200))
    val x10254 = withCtrl(x10255) { LoopController(style=StreamPipe, level=OuterControl, cchain=x10201).name("x10254").srcCtx("v16_plasticine.scala:141:79") } // UnrolledForeach(List(b6201, b5945, b5764, b5760),x10201,Block(Const(())),List(List(b6271), List(b6272), List(b6273)),List(List(b6274), List(b6275), List(b6276)))
    val b6271 = withCtrl(x10254) { CounterIter(x10197, Some(0)).name("b6271") } // b6271
    val b6274 = withCtrl(x10254) { Const(true).name("b6274") } // b6274
    val b6272 = withCtrl(x10254) { CounterIter(x10198, Some(0)).name("b6272") } // b6272
    val b6275 = withCtrl(x10254) { Const(true).name("b6275") } // b6275
    val b6273 = withCtrl(x10254) { CounterIter(x10200, Some(0)).name("b6273") } // b6273
    val b6276 = withCtrl(x10254) { Const(true).name("b6276") } // b6276
    val b10520 = withCtrl(x10254) { StreamOut(field="offset").name("b10520").srcCtx("v16_plasticine.scala:141:79") } // x10202 = StreamOutNew(BurstCmdBus)
    isAccum(b10520) = false
    bufferDepthOf(b10520) = 1
    val b10521 = withCtrl(x10254) { StreamOut(field="size").name("b10521").srcCtx("v16_plasticine.scala:141:79") } // x10202 = StreamOutNew(BurstCmdBus)
    isAccum(b10521) = false
    bufferDepthOf(b10521) = 1
    val x10203 = withCtrl(x10254) { StreamOut(field="data").name("x10203").srcCtx("v16_plasticine.scala:141:79") } // x10203 = StreamOutNew(BurstFullDataBus())
    isAccum(x10203) = false
    bufferDepthOf(x10203) = 1
    val x10204 = withCtrl(x10254) { StreamIn(field="ack").name("x10204").srcCtx("v16_plasticine.scala:141:79") } // x10204 = StreamInNew(BurstAckBus)
    isAccum(x10204) = false
    bufferDepthOf(x10204) = 1
    val x10230 = withCtrl(x10254) { UnitController(style=SeqPipe, level=InnerControl).name("x10230").srcCtx("v16_plasticine.scala:141:79") } // UnitPipe(List(b6274, b6275, b6276, b6201, b5945, b5764, b5760),Block(x10229))
    val x10205 = withCtrl(x10230) { OpDef(op=FixAdd, inputs=List(b5759, b6271)).name("x10205").srcCtx("v16_plasticine.scala:141:79") } // FixAdd(b5759,b6271)
    val x10206 = withCtrl(x10230) { ReadMem(x10186).name("x10206").srcCtx("v16_plasticine.scala:132:27") } // RegRead(x10186)
    val x10207 = withCtrl(x10230) { OpDef(op=FixAdd, inputs=List(x10206, b6272)).name("x10207").srcCtx("v16_plasticine.scala:141:79") } // FixAdd(x10206,b6272)
    val x10208 = withCtrl(x10230) { x10205 } // FixConvert(x10205,TRUE,_32,_0) (Same Type. No op)
    val x10209 = withCtrl(x10230) { OpDef(op=FixMul, inputs=List(x10208, Const(51380224))).name("x10209").srcCtx("v16_plasticine.scala:141:79") } // FixMul(x10208,Const(51380224))
    val x10210 = withCtrl(x10230) { x10207 } // FixConvert(x10207,TRUE,_32,_0) (Same Type. No op)
    val x10211 = withCtrl(x10230) { OpDef(op=FixMul, inputs=List(x10210, Const(50176))).name("x10211").srcCtx("v16_plasticine.scala:141:79") } // FixMul(x10210,Const(50176))
    val x10212 = withCtrl(x10230) { b6273 } // FixConvert(b6273,TRUE,_32,_0) (Same Type. No op)
    val x10213 = withCtrl(x10230) { OpDef(op=FixMul, inputs=List(x10212, Const(224))).name("x10213").srcCtx("v16_plasticine.scala:141:79") } // FixMul(x10212,Const(224))
    val x10214 = withCtrl(x10230) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10215 = withCtrl(x10230) { OpDef(op=FixAdd, inputs=List(x10209, x10211)).name("x10215").srcCtx("v16_plasticine.scala:141:79") } // FixAdd(x10209,x10211)
    val x10216 = withCtrl(x10230) { OpDef(op=FixAdd, inputs=List(x10213, x10214)).name("x10216").srcCtx("v16_plasticine.scala:141:79") } // FixAdd(x10213,x10214)
    val x10217 = withCtrl(x10230) { OpDef(op=FixAdd, inputs=List(x10215, x10216)).name("x10217").srcCtx("v16_plasticine.scala:141:79") } // FixAdd(x10215,x10216)
    val x10218 = withCtrl(x10230) { OpDef(op=FixSla, inputs=List(x10217, Const(2))).name("x10218").srcCtx("v16_plasticine.scala:141:79") } // FixLsh(x10217,Const(2))
    val x10219 = withCtrl(x10230) { x10218 } // FixConvert(x10218,TRUE,_64,_0)
    val x10220 = withCtrl(x10230) { DramAddress(x9725).name("x10220").srcCtx("v16_plasticine.scala:141:79") } // GetDRAMAddress(x9725)
    val x10222_x10221 = withCtrl(x10230) { OpDef(op=FixAdd, inputs=List(x10219, x10220)).name("x10222_x10221").srcCtx("v16_plasticine.scala:141:79") } // FixAdd(x10219,x10220)
    // x10222 = SimpleStruct(ArrayBuffer((offset,x10221), (size,Const(896)), (isLoad,Const(false))))
    val x10223 = withCtrl(x10230) { OpDef(op=BitAnd, inputs=List(b6274, b6275)).name("x10223").srcCtx("UnrollingBase.scala:28:66") } // And(b6274,b6275)
    val x10224 = withCtrl(x10230) { OpDef(op=BitAnd, inputs=List(b6276, b6201)).name("x10224").srcCtx("UnrollingBase.scala:28:66") } // And(b6276,b6201)
    val x10225 = withCtrl(x10230) { OpDef(op=BitAnd, inputs=List(b5945, b5764)).name("x10225").srcCtx("UnrollingBase.scala:28:66") } // And(b5945,b5764)
    val x10226 = withCtrl(x10230) { OpDef(op=BitAnd, inputs=List(x10223, x10224)).name("x10226").srcCtx("UnrollingBase.scala:28:66") } // And(x10223,x10224)
    val x10227 = withCtrl(x10230) { OpDef(op=BitAnd, inputs=List(x10225, b5760)).name("x10227").srcCtx("UnrollingBase.scala:28:66") } // And(x10225,b5760)
    val x10228 = withCtrl(x10230) { OpDef(op=BitAnd, inputs=List(x10226, x10227)).name("x10228").srcCtx("UnrollingBase.scala:28:66") } // And(x10226,x10227)
    val x10229_b10522_b10520 = withCtrl(x10230) { WriteMem(b10520, x10222_x10221).name("x10229_b10522_b10520").srcCtx("v16_plasticine.scala:141:79") } // StreamWrite(x10202,x10222,x10228)
    val x10229_b10523_b10521 = withCtrl(x10230) { WriteMem(b10521, Const(896)).name("x10229_b10523_b10521").srcCtx("v16_plasticine.scala:141:79") } // StreamWrite(x10202,x10222,x10228)
    val x10231 = withCtrl(x10254) { Counter(min=Const(0), max=Const(224), step=Const(1), par=1).name("x10231").srcCtx("v16_plasticine.scala:141:79") } // CounterNew(Const(0),Const(224),Const(1),Const(1))
    val x10232 = withCtrl(x10254) { CounterChain(List(x10231)).name("x10232").srcCtx("v16_plasticine.scala:141:79") } // CounterChainNew(List(x10231))
    val x10244 = withCtrl(x10254) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10232).name("x10244").srcCtx("v16_plasticine.scala:141:79") } // UnrolledForeach(List(b6274, b6275, b6276, b6201, b5945, b5764, b5760),x10232,Block(Const(())),List(List(b6308)),List(List(b6309)))
    val b6308 = withCtrl(x10244) { CounterIter(x10231, None).name("b6308") } // b6308
    val b6309 = withCtrl(x10244) { Const(true).name("b6309") } // b6309
    val x10233 = withCtrl(x10244) { OpDef(op=BitAnd, inputs=List(b6309, b6274)).name("x10233").srcCtx("UnrollingBase.scala:28:66") } // And(b6309,b6274)
    val x10234 = withCtrl(x10244) { OpDef(op=BitAnd, inputs=List(b6275, b6276)).name("x10234").srcCtx("UnrollingBase.scala:28:66") } // And(b6275,b6276)
    val x10235 = withCtrl(x10244) { OpDef(op=BitAnd, inputs=List(b6201, b5945)).name("x10235").srcCtx("UnrollingBase.scala:28:66") } // And(b6201,b5945)
    val x10236 = withCtrl(x10244) { OpDef(op=BitAnd, inputs=List(b5764, b5760)).name("x10236").srcCtx("UnrollingBase.scala:28:66") } // And(b5764,b5760)
    val x10237 = withCtrl(x10244) { OpDef(op=BitAnd, inputs=List(x10233, x10234)).name("x10237").srcCtx("UnrollingBase.scala:28:66") } // And(x10233,x10234)
    val x10238 = withCtrl(x10244) { OpDef(op=BitAnd, inputs=List(x10235, x10236)).name("x10238").srcCtx("UnrollingBase.scala:28:66") } // And(x10235,x10236)
    val x10239 = withCtrl(x10244) { OpDef(op=BitAnd, inputs=List(x10237, x10238)).name("x10239").srcCtx("UnrollingBase.scala:28:66") } // And(x10237,x10238)
    val x10240 = withCtrl(x10244) { LoadBanks(List(x10141_d0_b0), List(b6273, b6308)).name("x10240").srcCtx("v16_plasticine.scala:141:79") } // ParSRAMLoad(x10141,List(List(b6273, b6308)),List(x10239))
    val x10242_x10241 = withCtrl(x10244) { x10240 } // VectorApply(x10240,0)
    // x10242 = SimpleStruct(ArrayBuffer((_1,x10241), (_2,Const(true))))
    val x10243_x10243_x10203 = withCtrl(x10244) { WriteMem(x10203, x10242_x10241).name("x10243_x10243_x10203").srcCtx("v16_plasticine.scala:141:79") } // ParStreamWrite(x10203,List(x10242),List(x10239))
    val x10245 = withCtrl(x10254) { FringeDenseStore(dram=List(x9725), cmdStream=List(b10520, b10521), dataStream=List(x10203), ackStream=List(x10204)).name("x10245").srcCtx("v16_plasticine.scala:141:79") } // FringeDenseStore(x9725,x10202,x10203,x10204)
    val x10253 = withCtrl(x10254) { UnitController(style=SeqPipe, level=InnerControl).name("x10253").srcCtx("v16_plasticine.scala:141:79") } // UnitPipe(List(b6274, b6275, b6276, b6201, b5945, b5764, b5760),Block(Const(())))
    val x10246 = withCtrl(x10253) { OpDef(op=BitAnd, inputs=List(b6274, b6275)).name("x10246").srcCtx("UnrollingBase.scala:28:66") } // And(b6274,b6275)
    val x10247 = withCtrl(x10253) { OpDef(op=BitAnd, inputs=List(b6276, b6201)).name("x10247").srcCtx("UnrollingBase.scala:28:66") } // And(b6276,b6201)
    val x10248 = withCtrl(x10253) { OpDef(op=BitAnd, inputs=List(b5945, b5764)).name("x10248").srcCtx("UnrollingBase.scala:28:66") } // And(b5945,b5764)
    val x10249 = withCtrl(x10253) { OpDef(op=BitAnd, inputs=List(x10246, x10247)).name("x10249").srcCtx("UnrollingBase.scala:28:66") } // And(x10246,x10247)
    val x10250 = withCtrl(x10253) { OpDef(op=BitAnd, inputs=List(x10248, b5760)).name("x10250").srcCtx("UnrollingBase.scala:28:66") } // And(x10248,b5760)
    val x10251 = withCtrl(x10253) { OpDef(op=BitAnd, inputs=List(x10249, x10250)).name("x10251").srcCtx("UnrollingBase.scala:28:66") } // And(x10249,x10250)
    val x10252_x10252 = withCtrl(x10253) { ReadMem(x10204).name("x10252_x10252").srcCtx("v16_plasticine.scala:141:79") } // StreamRead(x10204,x10251)
    val x10258_d0_b0 = withCtrl(x10504) { SRAM(size=50176, banking=Strided(banks=1, stride=25088)).name("x10258_d0_b0").srcCtx("v16_plasticine.scala:158:34:tmp_SRAM_fc") } // x10258 = SRAMNew(ArrayBuffer(Const(2), Const(25088)))
    isAccum(x10258_d0_b0) = false
    bufferDepthOf(x10258_d0_b0) = 1
    staticDimsOf(x10258_d0_b0) = List(2, 25088)
    val x10258_d1_b0 = withCtrl(x10504) { SRAM(size=50176, banking=Strided(banks=1, stride=25088)).name("x10258_d1_b0").srcCtx("v16_plasticine.scala:158:34:tmp_SRAM_fc") } // x10258 = SRAMNew(ArrayBuffer(Const(2), Const(25088)))
    isAccum(x10258_d1_b0) = false
    bufferDepthOf(x10258_d1_b0) = 2
    staticDimsOf(x10258_d1_b0) = List(2, 25088)
    val x10259 = withCtrl(x10504) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x10259").srcCtx("v16_plasticine.scala:159:21") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x10260 = withCtrl(x10504) { CounterChain(List(x10259)).name("x10260").srcCtx("v16_plasticine.scala:159:27") } // CounterChainNew(List(x10259))
    val x10334 = withCtrl(x10504) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10260).name("x10334").srcCtx("v16_plasticine.scala:159:27") } // UnrolledForeach(List(b5760),x10260,Block(Const(())),List(List(b6338)),List(List(b6339)))
    val b6338 = withCtrl(x10334) { CounterIter(x10259, Some(0)).name("b6338") } // b6338
    val b6339 = withCtrl(x10334) { Const(true).name("b6339") } // b6339
    val x10261 = withCtrl(x10334) { Counter(min=Const(0), max=Const(7), step=Const(1), par=1).name("x10261").srcCtx("v16_plasticine.scala:160:29") } // CounterNew(Const(0),Const(7),Const(1),Const(1))
    val x10262 = withCtrl(x10334) { CounterChain(List(x10261)).name("x10262").srcCtx("v16_plasticine.scala:160:35") } // CounterChainNew(List(x10261))
    val x10333 = withCtrl(x10334) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10262).name("x10333").srcCtx("v16_plasticine.scala:160:35") } // UnrolledForeach(List(b6339, b5760),x10262,Block(Const(())),List(List(b6342)),List(List(b6343)))
    val b6342 = withCtrl(x10333) { CounterIter(x10261, Some(0)).name("b6342") } // b6342
    val b6343 = withCtrl(x10333) { Const(true).name("b6343") } // b6343
    val x10263_d0_b0 = withCtrl(x10333) { SRAM(size=7, banking=Strided(banks=1, stride=1)).name("x10263_d0_b0").srcCtx("v16_plasticine.scala:161:30:row") } // x10263 = SRAMNew(ArrayBuffer(Const(7)))
    isAccum(x10263_d0_b0) = false
    bufferDepthOf(x10263_d0_b0) = 2
    staticDimsOf(x10263_d0_b0) = List(7)
    val x10264 = withCtrl(x10333) { Reg(init=Some(0)).name("x10264").srcCtx("v16_plasticine.scala:160:35") } // x10264 = RegNew(Const(0))
    isAccum(x10264) = false
    bufferDepthOf(x10264) = 2
    val x10272 = withCtrl(x10333) { UnitController(style=SeqPipe, level=InnerControl).name("x10272").srcCtx("v16_plasticine.scala:160:35") } // UnitPipe(List(b6343, b6339, b5760),Block(Const(())))
    val x10265 = withCtrl(x10272) { OpDef(op=FixAdd, inputs=List(Const(512), b6338)).name("x10265").srcCtx("v16_plasticine.scala:162:47") } // FixAdd(Const(512),b6338)
    val x10266 = withCtrl(x10272) { OpDef(op=FixAdd, inputs=List(b5759, Const(1))).name("x10266").srcCtx("v16_plasticine.scala:162:31") } // FixAdd(b5759,Const(1))
    val x10267 = withCtrl(x10272) { OpDef(op=FixAdd, inputs=List(x10265, Const(1))).name("x10267").srcCtx("v16_plasticine.scala:162:31") } // FixAdd(x10265,Const(1))
    val x10268 = withCtrl(x10272) { OpDef(op=FixAdd, inputs=List(b6342, Const(1))).name("x10268").srcCtx("v16_plasticine.scala:162:31") } // FixAdd(b6342,Const(1))
    val x10269 = withCtrl(x10272) { OpDef(op=BitAnd, inputs=List(b6343, b6339)).name("x10269").srcCtx("UnrollingBase.scala:28:66") } // And(b6343,b6339)
    val x10270 = withCtrl(x10272) { OpDef(op=BitAnd, inputs=List(x10269, b5760)).name("x10270").srcCtx("UnrollingBase.scala:28:66") } // And(x10269,b5760)
    val x10271_x10264 = withCtrl(x10272) { WriteMem(x10264, x10265).name("x10271_x10264").srcCtx("v16_plasticine.scala:160:35") } // RegWrite(x10264,x10265,x10270)
    val x10273 = withCtrl(x10333) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10273").srcCtx("v16_plasticine.scala:162:17") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10274 = withCtrl(x10333) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10274").srcCtx("v16_plasticine.scala:162:17") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10275 = withCtrl(x10333) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10275").srcCtx("v16_plasticine.scala:162:17") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10276 = withCtrl(x10333) { CounterChain(List(x10273,x10274,x10275)).name("x10276").srcCtx("v16_plasticine.scala:162:17") } // CounterChainNew(List(x10273, x10274, x10275))
    val x10318 = withCtrl(x10333) { LoopController(style=StreamPipe, level=OuterControl, cchain=x10276).name("x10318").srcCtx("v16_plasticine.scala:162:17") } // UnrolledForeach(List(b6343, b6339, b5760),x10276,Block(Const(())),List(List(b6358), List(b6359), List(b6360)),List(List(b6361), List(b6362), List(b6363)))
    val b6358 = withCtrl(x10318) { CounterIter(x10273, Some(0)).name("b6358") } // b6358
    val b6361 = withCtrl(x10318) { Const(true).name("b6361") } // b6361
    val b6359 = withCtrl(x10318) { CounterIter(x10274, Some(0)).name("b6359") } // b6359
    val b6362 = withCtrl(x10318) { Const(true).name("b6362") } // b6362
    val b6360 = withCtrl(x10318) { CounterIter(x10275, Some(0)).name("b6360") } // b6360
    val b6363 = withCtrl(x10318) { Const(true).name("b6363") } // b6363
    val b10524 = withCtrl(x10318) { StreamOut(field="offset").name("b10524").srcCtx("v16_plasticine.scala:162:17") } // x10277 = StreamOutNew(BurstCmdBus)
    isAccum(b10524) = false
    bufferDepthOf(b10524) = 1
    val b10525 = withCtrl(x10318) { StreamOut(field="size").name("b10525").srcCtx("v16_plasticine.scala:162:17") } // x10277 = StreamOutNew(BurstCmdBus)
    isAccum(b10525) = false
    bufferDepthOf(b10525) = 1
    val x10278 = withCtrl(x10318) { StreamIn(field="data").name("x10278").srcCtx("v16_plasticine.scala:162:17") } // x10278 = StreamInNew(BurstDataBus())
    isAccum(x10278) = false
    bufferDepthOf(x10278) = 1
    val x10304 = withCtrl(x10318) { UnitController(style=SeqPipe, level=InnerControl).name("x10304").srcCtx("v16_plasticine.scala:162:17") } // UnitPipe(List(b6361, b6362, b6363, b6343, b6339, b5760),Block(x10303))
    val x10279 = withCtrl(x10304) { OpDef(op=FixAdd, inputs=List(b5759, b6358)).name("x10279").srcCtx("v16_plasticine.scala:162:17") } // FixAdd(b5759,b6358)
    val x10280 = withCtrl(x10304) { ReadMem(x10264).name("x10280").srcCtx("v16_plasticine.scala:160:35") } // RegRead(x10264)
    val x10281 = withCtrl(x10304) { OpDef(op=FixAdd, inputs=List(x10280, b6359)).name("x10281").srcCtx("v16_plasticine.scala:162:17") } // FixAdd(x10280,b6359)
    val x10282 = withCtrl(x10304) { OpDef(op=FixAdd, inputs=List(b6342, b6360)).name("x10282").srcCtx("v16_plasticine.scala:162:17") } // FixAdd(b6342,b6360)
    val x10283 = withCtrl(x10304) { x10279 } // FixConvert(x10279,TRUE,_32,_0) (Same Type. No op)
    val x10284 = withCtrl(x10304) { OpDef(op=FixMul, inputs=List(x10283, Const(51380224))).name("x10284").srcCtx("v16_plasticine.scala:162:17") } // FixMul(x10283,Const(51380224))
    val x10285 = withCtrl(x10304) { x10281 } // FixConvert(x10281,TRUE,_32,_0) (Same Type. No op)
    val x10286 = withCtrl(x10304) { OpDef(op=FixMul, inputs=List(x10285, Const(50176))).name("x10286").srcCtx("v16_plasticine.scala:162:17") } // FixMul(x10285,Const(50176))
    val x10287 = withCtrl(x10304) { x10282 } // FixConvert(x10282,TRUE,_32,_0) (Same Type. No op)
    val x10288 = withCtrl(x10304) { OpDef(op=FixMul, inputs=List(x10287, Const(224))).name("x10288").srcCtx("v16_plasticine.scala:162:17") } // FixMul(x10287,Const(224))
    val x10289 = withCtrl(x10304) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10290 = withCtrl(x10304) { OpDef(op=FixAdd, inputs=List(x10284, x10286)).name("x10290").srcCtx("v16_plasticine.scala:162:17") } // FixAdd(x10284,x10286)
    val x10291 = withCtrl(x10304) { OpDef(op=FixAdd, inputs=List(x10288, x10289)).name("x10291").srcCtx("v16_plasticine.scala:162:17") } // FixAdd(x10288,x10289)
    val x10292 = withCtrl(x10304) { OpDef(op=FixAdd, inputs=List(x10290, x10291)).name("x10292").srcCtx("v16_plasticine.scala:162:17") } // FixAdd(x10290,x10291)
    val x10293 = withCtrl(x10304) { OpDef(op=FixSla, inputs=List(x10292, Const(2))).name("x10293").srcCtx("v16_plasticine.scala:162:17") } // FixLsh(x10292,Const(2))
    val x10294 = withCtrl(x10304) { x10293 } // FixConvert(x10293,TRUE,_64,_0)
    val x10295 = withCtrl(x10304) { DramAddress(x9725).name("x10295").srcCtx("v16_plasticine.scala:162:17") } // GetDRAMAddress(x9725)
    val x10297_x10296 = withCtrl(x10304) { OpDef(op=FixAdd, inputs=List(x10294, x10295)).name("x10297_x10296").srcCtx("v16_plasticine.scala:162:17") } // FixAdd(x10294,x10295)
    // x10297 = SimpleStruct(ArrayBuffer((offset,x10296), (size,Const(64)), (isLoad,Const(true))))
    val x10298 = withCtrl(x10304) { OpDef(op=BitAnd, inputs=List(b6361, b6362)).name("x10298").srcCtx("UnrollingBase.scala:28:66") } // And(b6361,b6362)
    val x10299 = withCtrl(x10304) { OpDef(op=BitAnd, inputs=List(b6363, b6343)).name("x10299").srcCtx("UnrollingBase.scala:28:66") } // And(b6363,b6343)
    val x10300 = withCtrl(x10304) { OpDef(op=BitAnd, inputs=List(b6339, b5760)).name("x10300").srcCtx("UnrollingBase.scala:28:66") } // And(b6339,b5760)
    val x10301 = withCtrl(x10304) { OpDef(op=BitAnd, inputs=List(x10298, x10299)).name("x10301").srcCtx("UnrollingBase.scala:28:66") } // And(x10298,x10299)
    val x10302 = withCtrl(x10304) { OpDef(op=BitAnd, inputs=List(x10301, x10300)).name("x10302").srcCtx("UnrollingBase.scala:28:66") } // And(x10301,x10300)
    val x10303_b10526_b10524 = withCtrl(x10304) { WriteMem(b10524, x10297_x10296).name("x10303_b10526_b10524").srcCtx("v16_plasticine.scala:162:17") } // StreamWrite(x10277,x10297,x10302)
    val x10303_b10527_b10525 = withCtrl(x10304) { WriteMem(b10525, Const(64)).name("x10303_b10527_b10525").srcCtx("v16_plasticine.scala:162:17") } // StreamWrite(x10277,x10297,x10302)
    val x10305 = withCtrl(x10318) { FringeDenseLoad(dram=List(x9725), cmdStream=List(b10524, b10525), dataStream=List(x10278)).name("x10305").srcCtx("v16_plasticine.scala:162:17") } // FringeDenseLoad(x9725,x10277,x10278)
    val x10306 = withCtrl(x10318) { Counter(min=Const(0), max=Const(16), step=Const(1), par=1).name("x10306").srcCtx("v16_plasticine.scala:162:17") } // CounterNew(Const(0),Const(16),Const(1),Const(1))
    val x10307 = withCtrl(x10318) { CounterChain(List(x10306)).name("x10307").srcCtx("v16_plasticine.scala:162:17") } // CounterChainNew(List(x10306))
    val x10317 = withCtrl(x10318) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10307).name("x10317").srcCtx("v16_plasticine.scala:162:17") } // UnrolledForeach(List(b6361, b6362, b6363, b6343, b6339, b5760),x10307,Block(Const(())),List(List(b6395)),List(List(b6396)))
    val b6395 = withCtrl(x10317) { CounterIter(x10306, None).name("b6395") } // b6395
    val b6396 = withCtrl(x10317) { Const(true).name("b6396") } // b6396
    val x10308 = withCtrl(x10317) { OpDef(op=BitAnd, inputs=List(b6396, b6361)).name("x10308").srcCtx("UnrollingBase.scala:28:66") } // And(b6396,b6361)
    val x10309 = withCtrl(x10317) { OpDef(op=BitAnd, inputs=List(b6362, b6363)).name("x10309").srcCtx("UnrollingBase.scala:28:66") } // And(b6362,b6363)
    val x10310 = withCtrl(x10317) { OpDef(op=BitAnd, inputs=List(b6343, b6339)).name("x10310").srcCtx("UnrollingBase.scala:28:66") } // And(b6343,b6339)
    val x10311 = withCtrl(x10317) { OpDef(op=BitAnd, inputs=List(x10308, x10309)).name("x10311").srcCtx("UnrollingBase.scala:28:66") } // And(x10308,x10309)
    val x10312 = withCtrl(x10317) { OpDef(op=BitAnd, inputs=List(x10310, b5760)).name("x10312").srcCtx("UnrollingBase.scala:28:66") } // And(x10310,b5760)
    val x10313 = withCtrl(x10317) { OpDef(op=BitAnd, inputs=List(x10311, x10312)).name("x10313").srcCtx("UnrollingBase.scala:28:66") } // And(x10311,x10312)
    val x10314_x10314 = withCtrl(x10317) { ReadMem(x10278).name("x10314_x10314").srcCtx("v16_plasticine.scala:162:17") } // ParStreamRead(x10278,List(x10313))
    val x10315_x10315 = withCtrl(x10317) { x10314_x10314 } // VectorApply(x10314,0)
    val x10316 = withCtrl(x10317) { StoreBanks(List(List(x10263_d0_b0)), List(b6395), x10315_x10315).name("x10316").srcCtx("v16_plasticine.scala:162:17") } // ParSRAMStore(x10263,List(List(b6395)),List(x10315),List(x10313))
    val x10319 = withCtrl(x10333) { Counter(min=Const(0), max=Const(7), step=Const(1), par=1).name("x10319").srcCtx("v16_plasticine.scala:163:31") } // CounterNew(Const(0),Const(7),Const(1),Const(1))
    val x10320 = withCtrl(x10333) { CounterChain(List(x10319)).name("x10320").srcCtx("v16_plasticine.scala:163:37") } // CounterChainNew(List(x10319))
    val x10332 = withCtrl(x10333) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10320).name("x10332").srcCtx("v16_plasticine.scala:163:37") } // UnrolledForeach(List(b6343, b6339, b5760),x10320,Block(Const(())),List(List(b6410)),List(List(b6411)))
    val b6410 = withCtrl(x10332) { CounterIter(x10319, None).name("b6410") } // b6410
    val b6411 = withCtrl(x10332) { Const(true).name("b6411") } // b6411
    val x10321 = withCtrl(x10332) { OpDef(op=FixSla, inputs=List(b6410, Const(9))).name("x10321").srcCtx("v16_plasticine.scala:164:31") } // FixLsh(b6410,Const(9))
    val x10322 = withCtrl(x10332) { OpDef(op=FixMul, inputs=List(b6342, Const(7))).name("x10322").srcCtx("v16_plasticine.scala:164:39") } // FixMul(b6342,Const(7))
    val x10323 = withCtrl(x10332) { OpDef(op=FixSla, inputs=List(x10322, Const(9))).name("x10323").srcCtx("v16_plasticine.scala:164:49") } // FixLsh(x10322,Const(9))
    val x10324 = withCtrl(x10332) { OpDef(op=FixAdd, inputs=List(x10321, x10323)).name("x10324").srcCtx("v16_plasticine.scala:164:36") } // FixAdd(x10321,x10323)
    val x10325 = withCtrl(x10332) { OpDef(op=FixAdd, inputs=List(x10324, b6338)).name("x10325").srcCtx("v16_plasticine.scala:164:54") } // FixAdd(x10324,b6338)
    val x10326 = withCtrl(x10332) { OpDef(op=BitAnd, inputs=List(b6411, b6343)).name("x10326").srcCtx("UnrollingBase.scala:28:66") } // And(b6411,b6343)
    val x10327 = withCtrl(x10332) { OpDef(op=BitAnd, inputs=List(b6339, b5760)).name("x10327").srcCtx("UnrollingBase.scala:28:66") } // And(b6339,b5760)
    val x10328 = withCtrl(x10332) { OpDef(op=BitAnd, inputs=List(x10326, x10327)).name("x10328").srcCtx("UnrollingBase.scala:28:66") } // And(x10326,x10327)
    val x10329 = withCtrl(x10332) { LoadBanks(List(x10263_d0_b0), List(b6410)).name("x10329").srcCtx("v16_plasticine.scala:164:64") } // ParSRAMLoad(x10263,List(List(b6410)),List(x10328))
    val x10330 = withCtrl(x10332) { x10329 } // VectorApply(x10329,0)
    val x10331 = withCtrl(x10332) { StoreBanks(List(List(x10258_d0_b0), List(x10258_d1_b0)), List(Const(0), x10325), x10330).name("x10331").srcCtx("v16_plasticine.scala:164:59") } // ParSRAMStore(x10258,List(List(Const(0), x10325)),List(x10330),List(x10328))
    val x10335_d0_b0 = withCtrl(x10504) { LUT(inits=List(25088, 4096, 4096), banking=Strided(banks=1, stride=1)).name("x10335_d0_b0").srcCtx("v16_plasticine.scala:169:42:fc_in_channels") } // x10335 = LUTNew(List(3), Seq(Const(25088),Const(4096),Const(4096)))
    isAccum(x10335_d0_b0) = false
    bufferDepthOf(x10335_d0_b0) = 1
    staticDimsOf(x10335_d0_b0) = List(3)
    val x10336_d0_b0 = withCtrl(x10504) { LUT(inits=List(4096, 4096, 1000), banking=Strided(banks=1, stride=1)).name("x10336_d0_b0").srcCtx("v16_plasticine.scala:170:42:fc_out_channels") } // x10336 = LUTNew(List(3), Seq(Const(4096),Const(4096),Const(1000)))
    isAccum(x10336_d0_b0) = false
    bufferDepthOf(x10336_d0_b0) = 1
    staticDimsOf(x10336_d0_b0) = List(3)
    val x10337_d0_b0 = withCtrl(x10504) { LUT(inits=List(0, 4096, 8192), banking=Strided(banks=1, stride=1)).name("x10337_d0_b0").srcCtx("v16_plasticine.scala:171:44:fc_weight_offset") } // x10337 = LUTNew(List(3), Seq(Const(0),Const(4096),Const(8192)))
    isAccum(x10337_d0_b0) = false
    bufferDepthOf(x10337_d0_b0) = 1
    staticDimsOf(x10337_d0_b0) = List(3)
    val x10338 = withCtrl(x10504) { Counter(min=Const(0), max=Const(3), step=Const(1), par=1).name("x10338").srcCtx("v16_plasticine.scala:172:30") } // CounterNew(Const(0),Const(3),Const(1),Const(1))
    val x10339 = withCtrl(x10504) { CounterChain(List(x10338)).name("x10339").srcCtx("v16_plasticine.scala:172:39") } // CounterChainNew(List(x10338))
    val x10462 = withCtrl(x10504) { LoopController(style=SeqPipe, level=OuterControl, cchain=x10339).name("x10462").srcCtx("v16_plasticine.scala:172:39") } // UnrolledForeach(List(b5760),x10339,Block(Const(())),List(List(b6431)),List(List(b6432)))
    val b6431 = withCtrl(x10462) { CounterIter(x10338, Some(0)).name("b6431") } // b6431
    val b6432 = withCtrl(x10462) { Const(true).name("b6432") } // b6432
    val x10340_d0_b0 = withCtrl(x10462) { SRAM(size=4096, banking=Strided(banks=1, stride=1)).name("x10340_d0_b0").srcCtx("v16_plasticine.scala:175:34:bias_SRAM") } // x10340 = SRAMNew(ArrayBuffer(Const(4096)))
    isAccum(x10340_d0_b0) = false
    bufferDepthOf(x10340_d0_b0) = 1
    staticDimsOf(x10340_d0_b0) = List(4096)
    val x10342 = withCtrl(x10462) { UnitController(style=SeqPipe, level=InnerControl).name("x10342").srcCtx("v16_plasticine.scala:172:39") } // UnitPipe(List(b6432, b5760),Block(Const(())))
    val x10341 = withCtrl(x10342) { OpDef(op=FixAdd, inputs=List(b6431, Const(1))).name("x10341").srcCtx("v16_plasticine.scala:176:38") } // FixAdd(b6431,Const(1))
    val x10343 = withCtrl(x10462) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10343").srcCtx("v16_plasticine.scala:176:21") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10344 = withCtrl(x10462) { CounterChain(List(x10343)).name("x10344").srcCtx("v16_plasticine.scala:176:21") } // CounterChainNew(List(x10343))
    val x10371 = withCtrl(x10462) { LoopController(style=StreamPipe, level=OuterControl, cchain=x10344).name("x10371").srcCtx("v16_plasticine.scala:176:21") } // UnrolledForeach(List(b6432, b5760),x10344,Block(Const(())),List(List(b6438)),List(List(b6439)))
    val b6438 = withCtrl(x10371) { CounterIter(x10343, Some(0)).name("b6438") } // b6438
    val b6439 = withCtrl(x10371) { Const(true).name("b6439") } // b6439
    val b10528 = withCtrl(x10371) { StreamOut(field="offset").name("b10528").srcCtx("v16_plasticine.scala:176:21") } // x10345 = StreamOutNew(BurstCmdBus)
    isAccum(b10528) = false
    bufferDepthOf(b10528) = 1
    val b10529 = withCtrl(x10371) { StreamOut(field="size").name("b10529").srcCtx("v16_plasticine.scala:176:21") } // x10345 = StreamOutNew(BurstCmdBus)
    isAccum(b10529) = false
    bufferDepthOf(b10529) = 1
    val x10346 = withCtrl(x10371) { StreamIn(field="data").name("x10346").srcCtx("v16_plasticine.scala:176:21") } // x10346 = StreamInNew(BurstDataBus())
    isAccum(x10346) = false
    bufferDepthOf(x10346) = 1
    val x10360 = withCtrl(x10371) { UnitController(style=SeqPipe, level=InnerControl).name("x10360").srcCtx("v16_plasticine.scala:176:21") } // UnitPipe(List(b6439, b6432, b5760),Block(x10359))
    val x10347 = withCtrl(x10360) { OpDef(op=FixAdd, inputs=List(b6431, b6438)).name("x10347").srcCtx("v16_plasticine.scala:176:21") } // FixAdd(b6431,b6438)
    val x10348 = withCtrl(x10360) { x10347 } // FixConvert(x10347,TRUE,_32,_0) (Same Type. No op)
    val x10349 = withCtrl(x10360) { OpDef(op=FixSla, inputs=List(x10348, Const(12))).name("x10349").srcCtx("v16_plasticine.scala:176:21") } // FixLsh(x10348,Const(12))
    val x10350 = withCtrl(x10360) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10351 = withCtrl(x10360) { OpDef(op=FixAdd, inputs=List(x10349, x10350)).name("x10351").srcCtx("v16_plasticine.scala:176:21") } // FixAdd(x10349,x10350)
    val x10352 = withCtrl(x10360) { OpDef(op=FixSla, inputs=List(x10351, Const(2))).name("x10352").srcCtx("v16_plasticine.scala:176:21") } // FixLsh(x10351,Const(2))
    val x10353 = withCtrl(x10360) { x10352 } // FixConvert(x10352,TRUE,_64,_0)
    val x10354 = withCtrl(x10360) { DramAddress(x9734).name("x10354").srcCtx("v16_plasticine.scala:176:21") } // GetDRAMAddress(x9734)
    val x10356_x10355 = withCtrl(x10360) { OpDef(op=FixAdd, inputs=List(x10353, x10354)).name("x10356_x10355").srcCtx("v16_plasticine.scala:176:21") } // FixAdd(x10353,x10354)
    // x10356 = SimpleStruct(ArrayBuffer((offset,x10355), (size,Const(16384)), (isLoad,Const(true))))
    val x10357 = withCtrl(x10360) { OpDef(op=BitAnd, inputs=List(b6439, b6432)).name("x10357").srcCtx("UnrollingBase.scala:28:66") } // And(b6439,b6432)
    val x10358 = withCtrl(x10360) { OpDef(op=BitAnd, inputs=List(x10357, b5760)).name("x10358").srcCtx("UnrollingBase.scala:28:66") } // And(x10357,b5760)
    val x10359_b10530_b10528 = withCtrl(x10360) { WriteMem(b10528, x10356_x10355).name("x10359_b10530_b10528").srcCtx("v16_plasticine.scala:176:21") } // StreamWrite(x10345,x10356,x10358)
    val x10359_b10531_b10529 = withCtrl(x10360) { WriteMem(b10529, Const(16384)).name("x10359_b10531_b10529").srcCtx("v16_plasticine.scala:176:21") } // StreamWrite(x10345,x10356,x10358)
    val x10361 = withCtrl(x10371) { FringeDenseLoad(dram=List(x9734), cmdStream=List(b10528, b10529), dataStream=List(x10346)).name("x10361").srcCtx("v16_plasticine.scala:176:21") } // FringeDenseLoad(x9734,x10345,x10346)
    val x10362 = withCtrl(x10371) { Counter(min=Const(0), max=Const(4096), step=Const(1), par=1).name("x10362").srcCtx("v16_plasticine.scala:176:21") } // CounterNew(Const(0),Const(4096),Const(1),Const(1))
    val x10363 = withCtrl(x10371) { CounterChain(List(x10362)).name("x10363").srcCtx("v16_plasticine.scala:176:21") } // CounterChainNew(List(x10362))
    val x10370 = withCtrl(x10371) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10363).name("x10370").srcCtx("v16_plasticine.scala:176:21") } // UnrolledForeach(List(b6439, b6432, b5760),x10363,Block(Const(())),List(List(b6459)),List(List(b6460)))
    val b6459 = withCtrl(x10370) { CounterIter(x10362, None).name("b6459") } // b6459
    val b6460 = withCtrl(x10370) { Const(true).name("b6460") } // b6460
    val x10364 = withCtrl(x10370) { OpDef(op=BitAnd, inputs=List(b6460, b6439)).name("x10364").srcCtx("UnrollingBase.scala:28:66") } // And(b6460,b6439)
    val x10365 = withCtrl(x10370) { OpDef(op=BitAnd, inputs=List(b6432, b5760)).name("x10365").srcCtx("UnrollingBase.scala:28:66") } // And(b6432,b5760)
    val x10366 = withCtrl(x10370) { OpDef(op=BitAnd, inputs=List(x10364, x10365)).name("x10366").srcCtx("UnrollingBase.scala:28:66") } // And(x10364,x10365)
    val x10367_x10367 = withCtrl(x10370) { ReadMem(x10346).name("x10367_x10367").srcCtx("v16_plasticine.scala:176:21") } // ParStreamRead(x10346,List(x10366))
    val x10368_x10368 = withCtrl(x10370) { x10367_x10367 } // VectorApply(x10367,0)
    val x10369 = withCtrl(x10370) { StoreBanks(List(List(x10340_d0_b0)), List(b6459), x10368_x10368).name("x10369").srcCtx("v16_plasticine.scala:176:21") } // ParSRAMStore(x10340,List(List(b6459)),List(x10368),List(x10366))
    val x10372 = withCtrl(x10462) { Reg(init=Some(0)).name("x10372").srcCtx("v16_plasticine.scala:172:39") } // x10372 = RegNew(Const(0))
    isAccum(x10372) = false
    bufferDepthOf(x10372) = 1
    val x10376 = withCtrl(x10462) { UnitController(style=SeqPipe, level=InnerControl).name("x10376").srcCtx("v16_plasticine.scala:172:39") } // UnitPipe(List(b6432, b5760),Block(Const(())))
    val x10373 = withCtrl(x10376) { OpDef(op=BitAnd, inputs=List(b6432, b5760)).name("x10373").srcCtx("UnrollingBase.scala:28:66") } // And(b6432,b5760)
    val x10374 = withCtrl(x10376) { LoadBanks(List(x10336_d0_b0), List(b6431)).name("x10374").srcCtx("v16_plasticine.scala:179:34") } // LUTLoad(x10336,List(b6431),x10373)
    val x10375_x10372 = withCtrl(x10376) { WriteMem(x10372, x10374).name("x10375_x10372").srcCtx("v16_plasticine.scala:172:39") } // RegWrite(x10372,x10374,x10373)
    val x10377 = withCtrl(x10462) { ReadMem(x10372).name("x10377").srcCtx("v16_plasticine.scala:172:39") } // RegRead(x10372)
    val x10378 = withCtrl(x10462) { Counter(min=Const(0), max=x10377, step=Const(1), par=1).name("x10378").srcCtx("v16_plasticine.scala:179:43") } // CounterNew(Const(0),x10377,Const(1),Const(1))
    val x10379 = withCtrl(x10462) { CounterChain(List(x10378)).name("x10379").srcCtx("v16_plasticine.scala:179:60") } // CounterChainNew(List(x10378))
    val x10461 = withCtrl(x10462) { LoopController(style=MetaPipe, level=OuterControl, cchain=x10379).name("x10461").srcCtx("v16_plasticine.scala:179:60") } // UnrolledForeach(List(b6432, b5760),x10379,Block(Const(())),List(List(b6477)),List(List(b6478)))
    val b6477 = withCtrl(x10461) { CounterIter(x10378, Some(0)).name("b6477") } // b6477
    val b6478 = withCtrl(x10461) { Const(true).name("b6478") } // b6478
    val x10380_d0_b0 = withCtrl(x10461) { SRAM(size=25088, banking=Strided(banks=1, stride=1)).name("x10380_d0_b0").srcCtx("v16_plasticine.scala:181:38:weight_SRAM") } // x10380 = SRAMNew(ArrayBuffer(Const(25088)))
    isAccum(x10380_d0_b0) = false
    bufferDepthOf(x10380_d0_b0) = 3
    staticDimsOf(x10380_d0_b0) = List(25088)
    val x10381 = withCtrl(x10461) { Reg(init=Some(0)).name("x10381").srcCtx("v16_plasticine.scala:179:60") } // x10381 = RegNew(Const(0))
    isAccum(x10381) = false
    bufferDepthOf(x10381) = 2
    val x10388 = withCtrl(x10461) { UnitController(style=SeqPipe, level=InnerControl).name("x10388").srcCtx("v16_plasticine.scala:179:60") } // UnitPipe(List(b6478, b6432, b5760),Block(Const(())))
    val x10382 = withCtrl(x10388) { OpDef(op=BitAnd, inputs=List(b6478, b6432)).name("x10382").srcCtx("UnrollingBase.scala:28:66") } // And(b6478,b6432)
    val x10383 = withCtrl(x10388) { OpDef(op=BitAnd, inputs=List(x10382, b5760)).name("x10383").srcCtx("UnrollingBase.scala:28:66") } // And(x10382,b5760)
    val x10384 = withCtrl(x10388) { LoadBanks(List(x10337_d0_b0), List(b6431)).name("x10384").srcCtx("v16_plasticine.scala:182:61") } // LUTLoad(x10337,List(b6431),x10383)
    val x10385 = withCtrl(x10388) { OpDef(op=FixAdd, inputs=List(x10384, b6477)).name("x10385").srcCtx("v16_plasticine.scala:182:65") } // FixAdd(x10384,b6477)
    val x10386 = withCtrl(x10388) { OpDef(op=FixAdd, inputs=List(x10385, Const(1))).name("x10386").srcCtx("v16_plasticine.scala:182:44") } // FixAdd(x10385,Const(1))
    val x10387_x10381 = withCtrl(x10388) { WriteMem(x10381, x10385).name("x10387_x10381").srcCtx("v16_plasticine.scala:179:60") } // RegWrite(x10381,x10385,x10383)
    val x10389 = withCtrl(x10461) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10389").srcCtx("v16_plasticine.scala:182:25") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10390 = withCtrl(x10461) { CounterChain(List(x10389)).name("x10390").srcCtx("v16_plasticine.scala:182:25") } // CounterChainNew(List(x10389))
    val x10420 = withCtrl(x10461) { LoopController(style=StreamPipe, level=OuterControl, cchain=x10390).name("x10420").srcCtx("v16_plasticine.scala:182:25") } // UnrolledForeach(List(b6478, b6432, b5760),x10390,Block(Const(())),List(List(b6490)),List(List(b6491)))
    val b6490 = withCtrl(x10420) { CounterIter(x10389, Some(0)).name("b6490") } // b6490
    val b6491 = withCtrl(x10420) { Const(true).name("b6491") } // b6491
    val b10532 = withCtrl(x10420) { StreamOut(field="offset").name("b10532").srcCtx("v16_plasticine.scala:182:25") } // x10391 = StreamOutNew(BurstCmdBus)
    isAccum(b10532) = false
    bufferDepthOf(b10532) = 1
    val b10533 = withCtrl(x10420) { StreamOut(field="size").name("b10533").srcCtx("v16_plasticine.scala:182:25") } // x10391 = StreamOutNew(BurstCmdBus)
    isAccum(b10533) = false
    bufferDepthOf(b10533) = 1
    val x10392 = withCtrl(x10420) { StreamIn(field="data").name("x10392").srcCtx("v16_plasticine.scala:182:25") } // x10392 = StreamInNew(BurstDataBus())
    isAccum(x10392) = false
    bufferDepthOf(x10392) = 1
    val x10408 = withCtrl(x10420) { UnitController(style=SeqPipe, level=InnerControl).name("x10408").srcCtx("v16_plasticine.scala:182:25") } // UnitPipe(List(b6491, b6478, b6432, b5760),Block(x10407))
    val x10393 = withCtrl(x10408) { ReadMem(x10381).name("x10393").srcCtx("v16_plasticine.scala:179:60") } // RegRead(x10381)
    val x10394 = withCtrl(x10408) { OpDef(op=FixAdd, inputs=List(x10393, b6490)).name("x10394").srcCtx("v16_plasticine.scala:182:25") } // FixAdd(x10393,b6490)
    val x10395 = withCtrl(x10408) { x10394 } // FixConvert(x10394,TRUE,_32,_0) (Same Type. No op)
    val x10396 = withCtrl(x10408) { OpDef(op=FixMul, inputs=List(x10395, Const(25088))).name("x10396").srcCtx("v16_plasticine.scala:182:25") } // FixMul(x10395,Const(25088))
    val x10397 = withCtrl(x10408) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10398 = withCtrl(x10408) { OpDef(op=FixAdd, inputs=List(x10396, x10397)).name("x10398").srcCtx("v16_plasticine.scala:182:25") } // FixAdd(x10396,x10397)
    val x10399 = withCtrl(x10408) { OpDef(op=FixSla, inputs=List(x10398, Const(2))).name("x10399").srcCtx("v16_plasticine.scala:182:25") } // FixLsh(x10398,Const(2))
    val x10400 = withCtrl(x10408) { x10399 } // FixConvert(x10399,TRUE,_64,_0)
    val x10401 = withCtrl(x10408) { DramAddress(x9732).name("x10401").srcCtx("v16_plasticine.scala:182:25") } // GetDRAMAddress(x9732)
    val x10403_x10402 = withCtrl(x10408) { OpDef(op=FixAdd, inputs=List(x10400, x10401)).name("x10403_x10402").srcCtx("v16_plasticine.scala:182:25") } // FixAdd(x10400,x10401)
    // x10403 = SimpleStruct(ArrayBuffer((offset,x10402), (size,Const(100352)), (isLoad,Const(true))))
    val x10404 = withCtrl(x10408) { OpDef(op=BitAnd, inputs=List(b6491, b6478)).name("x10404").srcCtx("UnrollingBase.scala:28:66") } // And(b6491,b6478)
    val x10405 = withCtrl(x10408) { OpDef(op=BitAnd, inputs=List(b6432, b5760)).name("x10405").srcCtx("UnrollingBase.scala:28:66") } // And(b6432,b5760)
    val x10406 = withCtrl(x10408) { OpDef(op=BitAnd, inputs=List(x10404, x10405)).name("x10406").srcCtx("UnrollingBase.scala:28:66") } // And(x10404,x10405)
    val x10407_b10534_b10532 = withCtrl(x10408) { WriteMem(b10532, x10403_x10402).name("x10407_b10534_b10532").srcCtx("v16_plasticine.scala:182:25") } // StreamWrite(x10391,x10403,x10406)
    val x10407_b10535_b10533 = withCtrl(x10408) { WriteMem(b10533, Const(100352)).name("x10407_b10535_b10533").srcCtx("v16_plasticine.scala:182:25") } // StreamWrite(x10391,x10403,x10406)
    val x10409 = withCtrl(x10420) { FringeDenseLoad(dram=List(x9732), cmdStream=List(b10532, b10533), dataStream=List(x10392)).name("x10409").srcCtx("v16_plasticine.scala:182:25") } // FringeDenseLoad(x9732,x10391,x10392)
    val x10410 = withCtrl(x10420) { Counter(min=Const(0), max=Const(25088), step=Const(1), par=1).name("x10410").srcCtx("v16_plasticine.scala:182:25") } // CounterNew(Const(0),Const(25088),Const(1),Const(1))
    val x10411 = withCtrl(x10420) { CounterChain(List(x10410)).name("x10411").srcCtx("v16_plasticine.scala:182:25") } // CounterChainNew(List(x10410))
    val x10419 = withCtrl(x10420) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10411).name("x10419").srcCtx("v16_plasticine.scala:182:25") } // UnrolledForeach(List(b6491, b6478, b6432, b5760),x10411,Block(Const(())),List(List(b6513)),List(List(b6514)))
    val b6513 = withCtrl(x10419) { CounterIter(x10410, None).name("b6513") } // b6513
    val b6514 = withCtrl(x10419) { Const(true).name("b6514") } // b6514
    val x10412 = withCtrl(x10419) { OpDef(op=BitAnd, inputs=List(b6514, b6491)).name("x10412").srcCtx("UnrollingBase.scala:28:66") } // And(b6514,b6491)
    val x10413 = withCtrl(x10419) { OpDef(op=BitAnd, inputs=List(b6478, b6432)).name("x10413").srcCtx("UnrollingBase.scala:28:66") } // And(b6478,b6432)
    val x10414 = withCtrl(x10419) { OpDef(op=BitAnd, inputs=List(x10412, x10413)).name("x10414").srcCtx("UnrollingBase.scala:28:66") } // And(x10412,x10413)
    val x10415 = withCtrl(x10419) { OpDef(op=BitAnd, inputs=List(x10414, b5760)).name("x10415").srcCtx("UnrollingBase.scala:28:66") } // And(x10414,b5760)
    val x10416_x10416 = withCtrl(x10419) { ReadMem(x10392).name("x10416_x10416").srcCtx("v16_plasticine.scala:182:25") } // ParStreamRead(x10392,List(x10415))
    val x10417_x10417 = withCtrl(x10419) { x10416_x10416 } // VectorApply(x10416,0)
    val x10418 = withCtrl(x10419) { StoreBanks(List(List(x10380_d0_b0)), List(b6513), x10417_x10417).name("x10418").srcCtx("v16_plasticine.scala:182:25") } // ParSRAMStore(x10380,List(List(b6513)),List(x10417),List(x10415))
    val x10421_d0 = withCtrl(x10461) { Reg(init=Some(0.0)).name("x10421_d0").srcCtx("v16_plasticine.scala:184:37:prod") } // x10421 = RegNew(Const(0))
    isAccum(x10421_d0) = false
    bufferDepthOf(x10421_d0) = 2
    val x10421_d1 = withCtrl(x10461) { Reg(init=Some(0.0)).name("x10421_d1").srcCtx("v16_plasticine.scala:184:37:prod") } // x10421 = RegNew(Const(0))
    isAccum(x10421_d1) = true
    bufferDepthOf(x10421_d1) = 1
    val x10422 = withCtrl(x10461) { Reg(init=Some(0)).name("x10422").srcCtx("v16_plasticine.scala:179:60") } // x10422 = RegNew(Const(0))
    isAccum(x10422) = false
    bufferDepthOf(x10422) = 2
    val x10427 = withCtrl(x10461) { UnitController(style=SeqPipe, level=InnerControl).name("x10427").srcCtx("v16_plasticine.scala:179:60") } // UnitPipe(List(b6478, b6432, b5760),Block(Const(())))
    val x10423 = withCtrl(x10427) { OpDef(op=BitAnd, inputs=List(b6478, b6432)).name("x10423").srcCtx("UnrollingBase.scala:28:66") } // And(b6478,b6432)
    val x10424 = withCtrl(x10427) { OpDef(op=BitAnd, inputs=List(x10423, b5760)).name("x10424").srcCtx("UnrollingBase.scala:28:66") } // And(x10423,b5760)
    val x10425 = withCtrl(x10427) { LoadBanks(List(x10335_d0_b0), List(b6431)).name("x10425").srcCtx("v16_plasticine.scala:184:62") } // LUTLoad(x10335,List(b6431),x10424)
    val x10426_x10422 = withCtrl(x10427) { WriteMem(x10422, x10425).name("x10426_x10422").srcCtx("v16_plasticine.scala:179:60") } // RegWrite(x10422,x10425,x10424)
    val x10428 = withCtrl(x10461) { ReadMem(x10422).name("x10428").srcCtx("v16_plasticine.scala:179:60") } // RegRead(x10422)
    val x10429 = withCtrl(x10461) { Counter(min=Const(0), max=x10428, step=Const(1), par=1).name("x10429").srcCtx("v16_plasticine.scala:184:71") } // CounterNew(Const(0),x10428,Const(1),Const(1))
    val x10430 = withCtrl(x10461) { CounterChain(List(x10429)).name("x10430").srcCtx("v16_plasticine.scala:186:14") } // CounterChainNew(List(x10429))
    val x10448 = withCtrl(x10461) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10430).name("x10448").srcCtx("v16_plasticine.scala:186:14") } // UnrolledReduce(List(b6478, b6432, b5760),x10430,x10421,Block((x10421) => Const(())),List(List(b6534)),List(List(b6535)))
    val b6534 = withCtrl(x10448) { CounterIter(x10429, None).name("b6534") } // b6534
    val b6535 = withCtrl(x10448) { Const(true).name("b6535") } // b6535
    val x10431 = b6431 // x10431 = DataAsBits(b6431)
    val x10432 = withCtrl(x10448) { OpDef(op=BitAnd, inputs=List(x10431, Const(0))).name("x10432").srcCtx("v16_plasticine.scala:185:28") } // VectorSlice(x10431,0,0) strMask=00000000000000000000000000000000
    val x10433 = x10432 // x10433 = BitsAsData(x10432,FixPt[TRUE,_32,_0])
    val x10434 = withCtrl(x10448) { OpDef(op=BitAnd, inputs=List(b6535, b6478)).name("x10434").srcCtx("UnrollingBase.scala:28:66") } // And(b6535,b6478)
    val x10435 = withCtrl(x10448) { OpDef(op=BitAnd, inputs=List(b6432, b5760)).name("x10435").srcCtx("UnrollingBase.scala:28:66") } // And(b6432,b5760)
    val x10436 = withCtrl(x10448) { OpDef(op=BitAnd, inputs=List(x10434, x10435)).name("x10436").srcCtx("UnrollingBase.scala:28:66") } // And(x10434,x10435)
    val x10437 = withCtrl(x10448) { LoadBanks(List(x10258_d1_b0), List(x10433, b6534)).name("x10437").srcCtx("v16_plasticine.scala:185:26") } // ParSRAMLoad(x10258,List(List(x10433, b6534)),List(x10436))
    val x10438 = withCtrl(x10448) { x10437 } // VectorApply(x10437,0)
    val x10439 = withCtrl(x10448) { LoadBanks(List(x10380_d0_b0), List(b6534)).name("x10439").srcCtx("v16_plasticine.scala:185:51") } // ParSRAMLoad(x10380,List(List(b6534)),List(x10436))
    val x10440 = withCtrl(x10448) { x10439 } // VectorApply(x10439,0)
    val x10441 = withCtrl(x10448) { OpDef(op=FixMul, inputs=List(x10438, x10440)).name("x10441").srcCtx("v16_plasticine.scala:185:38") } // FixMul(x10438,x10440)
    val x10442 = withCtrl(x10448) { ReadMem(x10421_d1).name("x10442").srcCtx("v16_plasticine.scala:186:14") } // RegRead(x10421)
    val x10443 = withCtrl(x10448) { OpDef(op=FixEql, inputs=List(b6534, Const(0))).name("x10443").srcCtx("v16_plasticine.scala:186:14") } // FixEql(b6534,Const(0))
    val x10444 = withCtrl(x10448) { ReduceAccumOp(op=FixAdd, input=x10441, accum=x10442).name("x10444").srcCtx("v16_plasticine.scala:186:16") } // FixAdd(x10441,x10442)
    val x10445 = withCtrl(x10448) { OpDef(op=BitAnd, inputs=List(b6478, b6432)).name("x10445").srcCtx("UnrollingBase.scala:28:66") } // And(b6478,b6432)
    val x10446 = withCtrl(x10448) { OpDef(op=BitAnd, inputs=List(x10445, b5760)).name("x10446").srcCtx("UnrollingBase.scala:28:66") } // And(x10445,b5760)
    val x10447_x10421_d0 = withCtrl(x10448) { WriteMem(x10421_d0, x10444).name("x10447_x10421_d0").srcCtx("v16_plasticine.scala:186:14") } // RegWrite(x10421,x10444,x10446)
    antiDepsOf(x10447_x10421_d0)=List(x10442)
    val x10447_x10421_d1 = withCtrl(x10448) { WriteMem(x10421_d1, x10444).name("x10447_x10421_d1").srcCtx("v16_plasticine.scala:186:14") } // RegWrite(x10421,x10444,x10446)
    antiDepsOf(x10447_x10421_d1)=List(x10442)
    val x10460 = withCtrl(x10461) { UnitController(style=SeqPipe, level=InnerControl).name("x10460").srcCtx("v16_plasticine.scala:179:60") } // UnitPipe(List(b6478, b6432, b5760),Block(Const(())))
    val x10449 = b6431 // x10449 = DataAsBits(b6431)
    val x10450 = withCtrl(x10460) { OpDef(op=BitAnd, inputs=List(x10449, Const(0))).name("x10450").srcCtx("v16_plasticine.scala:187:28") } // VectorSlice(x10449,0,0) strMask=00000000000000000000000000000000
    def split2 = {
    val x10451 = x10450 // x10451 = BitsAsData(x10450,FixPt[TRUE,_32,_0])
    val x10452 = withCtrl(x10460) { OpDef(op=FixSub, inputs=List(Const(1), x10451)).name("x10452").srcCtx("v16_plasticine.scala:187:26") } // FixSub(Const(1),x10451)
    val x10453 = withCtrl(x10460) { ReadMem(x10421_d0).name("x10453").srcCtx("v16_plasticine.scala:187:59") } // RegRead(x10421)
    val x10454 = withCtrl(x10460) { OpDef(op=BitAnd, inputs=List(b6478, b6432)).name("x10454").srcCtx("UnrollingBase.scala:28:66") } // And(b6478,b6432)
    val x10455 = withCtrl(x10460) { OpDef(op=BitAnd, inputs=List(x10454, b5760)).name("x10455").srcCtx("UnrollingBase.scala:28:66") } // And(x10454,b5760)
    val x10456 = withCtrl(x10460) { LoadBanks(List(x10340_d0_b0), List(b6477)).name("x10456").srcCtx("v16_plasticine.scala:187:76") } // SRAMLoad(x10340,ArrayBuffer(Const(4096)),List(b6477),Const(0),x10455)
    val x10457 = withCtrl(x10460) { OpDef(op=FixAdd, inputs=List(x10453, x10456)).name("x10457").srcCtx("v16_plasticine.scala:187:65") } // FixAdd(x10453,x10456)
    val x10458 = withCtrl(x10460) { OpDef(op=FixMax, inputs=List(Const(0.0), x10457)).name("x10458").srcCtx("v16_plasticine.scala:187:44") } // Max(Const(0),x10457)
    val x10459 = withCtrl(x10460) { StoreBanks(List(List(x10258_d0_b0), List(x10258_d1_b0)), List(x10452, b6477), x10458).name("x10459").srcCtx("v16_plasticine.scala:187:39") } // SRAMStore(x10258,ArrayBuffer(Const(2), Const(25088)),List(x10452, b6477),Const(0),x10458,x10455)
    val x10464 = withCtrl(x10504) { UnitController(style=SeqPipe, level=InnerControl).name("x10464").srcCtx("v16_plasticine.scala:147:43") } // UnitPipe(List(b5760),Block(Const(())))
    val x10463 = withCtrl(x10464) { OpDef(op=FixAdd, inputs=List(b5759, Const(1))).name("x10463").srcCtx("v16_plasticine.scala:190:17") } // FixAdd(b5759,Const(1))
    val x10465 = withCtrl(x10504) { Counter(min=Const(0), max=Const(1), step=Const(1), par=1).name("x10465").srcCtx("v16_plasticine.scala:190:58") } // CounterNew(Const(0),Const(1),Const(1),Const(1))
    val x10466 = withCtrl(x10504) { Counter(min=Const(0), max=Const(2), step=Const(1), par=1).name("x10466").srcCtx("v16_plasticine.scala:190:58") } // CounterNew(Const(0),Const(2),Const(1),Const(1))
    val x10467 = withCtrl(x10504) { CounterChain(List(x10465,x10466)).name("x10467").srcCtx("v16_plasticine.scala:190:58") } // CounterChainNew(List(x10465, x10466))
    val x10503 = withCtrl(x10504) { LoopController(style=StreamPipe, level=OuterControl, cchain=x10467).name("x10503").srcCtx("v16_plasticine.scala:190:58") } // UnrolledForeach(List(b5760),x10467,Block(Const(())),List(List(b6569), List(b6570)),List(List(b6571), List(b6572)))
    val b6569 = withCtrl(x10503) { CounterIter(x10465, Some(0)).name("b6569") } // b6569
    val b6571 = withCtrl(x10503) { Const(true).name("b6571") } // b6571
    val b6570 = withCtrl(x10503) { CounterIter(x10466, Some(0)).name("b6570") } // b6570
    val b6572 = withCtrl(x10503) { Const(true).name("b6572") } // b6572
    val b10536 = withCtrl(x10503) { StreamOut(field="offset").name("b10536").srcCtx("v16_plasticine.scala:190:58") } // x10468 = StreamOutNew(BurstCmdBus)
    isAccum(b10536) = false
    bufferDepthOf(b10536) = 1
    val b10537 = withCtrl(x10503) { StreamOut(field="size").name("b10537").srcCtx("v16_plasticine.scala:190:58") } // x10468 = StreamOutNew(BurstCmdBus)
    isAccum(b10537) = false
    bufferDepthOf(b10537) = 1
    val x10469 = withCtrl(x10503) { StreamOut(field="data").name("x10469").srcCtx("v16_plasticine.scala:190:58") } // x10469 = StreamOutNew(BurstFullDataBus())
    isAccum(x10469) = false
    bufferDepthOf(x10469) = 1
    val x10470 = withCtrl(x10503) { StreamIn(field="ack").name("x10470").srcCtx("v16_plasticine.scala:190:58") } // x10470 = StreamInNew(BurstAckBus)
    isAccum(x10470) = false
    bufferDepthOf(x10470) = 1
    val x10487 = withCtrl(x10503) { UnitController(style=SeqPipe, level=InnerControl).name("x10487").srcCtx("v16_plasticine.scala:190:58") } // UnitPipe(List(b6571, b6572, b5760),Block(x10486))
    val x10471 = withCtrl(x10487) { OpDef(op=FixAdd, inputs=List(b5759, b6569)).name("x10471").srcCtx("v16_plasticine.scala:190:58") } // FixAdd(b5759,b6569)
    val x10472 = withCtrl(x10487) { x10471 } // FixConvert(x10471,TRUE,_32,_0) (Same Type. No op)
    val x10473 = withCtrl(x10487) { OpDef(op=FixMul, inputs=List(x10472, Const(50176))).name("x10473").srcCtx("v16_plasticine.scala:190:58") } // FixMul(x10472,Const(50176))
    val x10474 = withCtrl(x10487) { b6570 } // FixConvert(b6570,TRUE,_32,_0) (Same Type. No op)
    val x10475 = withCtrl(x10487) { OpDef(op=FixMul, inputs=List(x10474, Const(25088))).name("x10475").srcCtx("v16_plasticine.scala:190:58") } // FixMul(x10474,Const(25088))
    val x10476 = withCtrl(x10487) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x10477 = withCtrl(x10487) { OpDef(op=FixAdd, inputs=List(x10473, x10475)).name("x10477").srcCtx("v16_plasticine.scala:190:58") } // FixAdd(x10473,x10475)
    val x10478 = withCtrl(x10487) { OpDef(op=FixAdd, inputs=List(x10477, x10476)).name("x10478").srcCtx("v16_plasticine.scala:190:58") } // FixAdd(x10477,x10476)
    val x10479 = withCtrl(x10487) { OpDef(op=FixSla, inputs=List(x10478, Const(2))).name("x10479").srcCtx("v16_plasticine.scala:190:58") } // FixLsh(x10478,Const(2))
    val x10480 = withCtrl(x10487) { x10479 } // FixConvert(x10479,TRUE,_64,_0)
    val x10481 = withCtrl(x10487) { DramAddress(x9727).name("x10481").srcCtx("v16_plasticine.scala:190:58") } // GetDRAMAddress(x9727)
    val x10483_x10482 = withCtrl(x10487) { OpDef(op=FixAdd, inputs=List(x10480, x10481)).name("x10483_x10482").srcCtx("v16_plasticine.scala:190:58") } // FixAdd(x10480,x10481)
    // x10483 = SimpleStruct(ArrayBuffer((offset,x10482), (size,Const(100352)), (isLoad,Const(false))))
    val x10484 = withCtrl(x10487) { OpDef(op=BitAnd, inputs=List(b6571, b6572)).name("x10484").srcCtx("UnrollingBase.scala:28:66") } // And(b6571,b6572)
    val x10485 = withCtrl(x10487) { OpDef(op=BitAnd, inputs=List(x10484, b5760)).name("x10485").srcCtx("UnrollingBase.scala:28:66") } // And(x10484,b5760)
    val x10486_b10538_b10536 = withCtrl(x10487) { WriteMem(b10536, x10483_x10482).name("x10486_b10538_b10536").srcCtx("v16_plasticine.scala:190:58") } // StreamWrite(x10468,x10483,x10485)
    val x10486_b10539_b10537 = withCtrl(x10487) { WriteMem(b10537, Const(100352)).name("x10486_b10539_b10537").srcCtx("v16_plasticine.scala:190:58") } // StreamWrite(x10468,x10483,x10485)
    val x10488 = withCtrl(x10503) { Counter(min=Const(0), max=Const(25088), step=Const(1), par=1).name("x10488").srcCtx("v16_plasticine.scala:190:58") } // CounterNew(Const(0),Const(25088),Const(1),Const(1))
    val x10489 = withCtrl(x10503) { CounterChain(List(x10488)).name("x10489").srcCtx("v16_plasticine.scala:190:58") } // CounterChainNew(List(x10488))
    val x10497 = withCtrl(x10503) { LoopController(style=InnerPipe, level=InnerControl, cchain=x10489).name("x10497").srcCtx("v16_plasticine.scala:190:58") } // UnrolledForeach(List(b6571, b6572, b5760),x10489,Block(Const(())),List(List(b6595)),List(List(b6596)))
    val b6595 = withCtrl(x10497) { CounterIter(x10488, None).name("b6595") } // b6595
    val b6596 = withCtrl(x10497) { Const(true).name("b6596") } // b6596
    val x10490 = withCtrl(x10497) { OpDef(op=BitAnd, inputs=List(b6596, b6571)).name("x10490").srcCtx("UnrollingBase.scala:28:66") } // And(b6596,b6571)
    val x10491 = withCtrl(x10497) { OpDef(op=BitAnd, inputs=List(b6572, b5760)).name("x10491").srcCtx("UnrollingBase.scala:28:66") } // And(b6572,b5760)
    val x10492 = withCtrl(x10497) { OpDef(op=BitAnd, inputs=List(x10490, x10491)).name("x10492").srcCtx("UnrollingBase.scala:28:66") } // And(x10490,x10491)
    val x10493 = withCtrl(x10497) { LoadBanks(List(x10258_d0_b0), List(b6570, b6595)).name("x10493").srcCtx("v16_plasticine.scala:190:58") } // ParSRAMLoad(x10258,List(List(b6570, b6595)),List(x10492))
    val x10495_x10494 = withCtrl(x10497) { x10493 } // VectorApply(x10493,0)
    // x10495 = SimpleStruct(ArrayBuffer((_1,x10494), (_2,Const(true))))
    val x10496_x10496_x10469 = withCtrl(x10497) { WriteMem(x10469, x10495_x10494).name("x10496_x10496_x10469").srcCtx("v16_plasticine.scala:190:58") } // ParStreamWrite(x10469,List(x10495),List(x10492))
    val x10498 = withCtrl(x10503) { FringeDenseStore(dram=List(x9727), cmdStream=List(b10536, b10537), dataStream=List(x10469), ackStream=List(x10470)).name("x10498").srcCtx("v16_plasticine.scala:190:58") } // FringeDenseStore(x9727,x10468,x10469,x10470)
    val x10502 = withCtrl(x10503) { UnitController(style=SeqPipe, level=InnerControl).name("x10502").srcCtx("v16_plasticine.scala:190:58") } // UnitPipe(List(b6571, b6572, b5760),Block(Const(())))
    val x10499 = withCtrl(x10502) { OpDef(op=BitAnd, inputs=List(b6571, b6572)).name("x10499").srcCtx("UnrollingBase.scala:28:66") } // And(b6571,b6572)
    val x10500 = withCtrl(x10502) { OpDef(op=BitAnd, inputs=List(x10499, b5760)).name("x10500").srcCtx("UnrollingBase.scala:28:66") } // And(x10499,b5760)
    val x10501_x10501 = withCtrl(x10502) { ReadMem(x10470).name("x10501_x10501").srcCtx("v16_plasticine.scala:190:58") } // StreamRead(x10470,x10500)
    }; split2
    }; split1
    
  }
}
