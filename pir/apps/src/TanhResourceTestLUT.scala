import pir._
import pir.node._
import arch._
import prism.enums._

object TanhResourceTestLUT extends PIRApp {
  def main(implicit design:PIRDesign) = {
    import design.pirmeta._
    val x1039 = withCtrl(design.top.topController) { DRAM(dims=List(Const(512))).name("x1039").srcCtx("DataGenerator.scala:146:20:in") } // x1039 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x1043 = withCtrl(design.top.topController) { DRAM(dims=List(Const(512))).name("x1043").srcCtx("main.scala:17:22:out") } // x1043 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x1044 = withCtrl(design.top.topController) { DRAM(dims=List(Const(512))).name("x1044").srcCtx("main.scala:18:25:outRef") } // x1044 = DRAMNew(ArrayBuffer(Const(512)),Const(0))
    val x1124 = withCtrl(design.top.topController) { UnitController(style=SeqPipe, level=OuterControl).name("x1124").srcCtx("main.scala:20:11") } // Hwblock(Block(Const(())),false)
    val x1045_d0_b0 = withCtrl(x1124) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x1045_d0_b0").srcCtx("DataGenerator.scala:28:21:mem") } // x1045 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x1045_d0_b0) = false
    bufferDepthOf(x1045_d0_b0) = 1
    staticDimsOf(x1045_d0_b0) = List(512)
    val x1045_d1_b0 = withCtrl(x1124) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x1045_d1_b0").srcCtx("DataGenerator.scala:28:21:mem") } // x1045 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x1045_d1_b0) = false
    bufferDepthOf(x1045_d1_b0) = 1
    staticDimsOf(x1045_d1_b0) = List(512)
    val x1063 = withCtrl(x1124) { UnitController(style=StreamPipe, level=OuterControl).name("x1063").srcCtx("DataGenerator.scala:29:8") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b1129 = withCtrl(x1063) { StreamOut(field="offset").name("b1129").srcCtx("DataGenerator.scala:29:8") } // x1046 = StreamOutNew(BurstCmdBus)
    isAccum(b1129) = false
    bufferDepthOf(b1129) = 1
    val b1130 = withCtrl(x1063) { StreamOut(field="size").name("b1130").srcCtx("DataGenerator.scala:29:8") } // x1046 = StreamOutNew(BurstCmdBus)
    isAccum(b1130) = false
    bufferDepthOf(b1130) = 1
    val x1047 = withCtrl(x1063) { StreamIn(field="data").name("x1047").srcCtx("DataGenerator.scala:29:8") } // x1047 = StreamInNew(BurstDataBus())
    isAccum(x1047) = false
    bufferDepthOf(x1047) = 1
    val x1055 = withCtrl(x1063) { UnitController(style=SeqPipe, level=InnerControl).name("x1055").srcCtx("DataGenerator.scala:29:8") } // UnitPipe(List(Const(true)),Block(x1054))
    val x1048 = withCtrl(x1055) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x1049 = withCtrl(x1055) { OpDef(op=FixSla, inputs=List(x1048, Const(2))).name("x1049").srcCtx("DataGenerator.scala:29:8") } // FixLsh(x1048,Const(2))
    val x1050 = withCtrl(x1055) { x1049 } // FixConvert(x1049,TRUE,_64,_0)
    val x1051 = withCtrl(x1055) { DramAddress(x1039).name("x1051").srcCtx("DataGenerator.scala:29:8") } // GetDRAMAddress(x1039)
    val x1053_x1052 = withCtrl(x1055) { OpDef(op=FixAdd, inputs=List(x1050, x1051)).name("x1053_x1052").srcCtx("DataGenerator.scala:29:8") } // FixAdd(x1050,x1051)
    // x1053 = SimpleStruct(ArrayBuffer((offset,x1052), (size,Const(2048)), (isLoad,Const(true))))
    val x1054_b1131_b1129 = withCtrl(x1055) { WriteMem(b1129, x1053_x1052).name("x1054_b1131_b1129").srcCtx("DataGenerator.scala:29:8") } // StreamWrite(x1046,x1053,Const(true))
    val x1054_b1132_b1130 = withCtrl(x1055) { WriteMem(b1130, Const(2048)).name("x1054_b1132_b1130").srcCtx("DataGenerator.scala:29:8") } // StreamWrite(x1046,x1053,Const(true))
    val x1056 = withCtrl(x1063) { FringeDenseLoad(dram=List(x1039), cmdStream=List(b1129, b1130), dataStream=List(x1047)).name("x1056").srcCtx("DataGenerator.scala:29:8") } // FringeDenseLoad(x1039,x1046,x1047)
    val x1057 = withCtrl(x1063) { Counter(min=Const(0), max=Const(512), step=Const(1), par=16).name("x1057").srcCtx("DataGenerator.scala:29:8") } // CounterNew(Const(0),Const(512),Const(1),Const(16))
    val x1058 = withCtrl(x1063) { CounterChain(List(x1057)).name("x1058").srcCtx("DataGenerator.scala:29:8") } // CounterChainNew(List(x1057))
    val x1062 = withCtrl(x1063) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1058).name("x1062").srcCtx("DataGenerator.scala:29:8") } // UnrolledForeach(List(Const(true)),x1058,Block(Const(())),List(List(b601)),List(List(b602)))
    val b601 = withCtrl(x1062) { CounterIter(x1057, None).name("b601") } // b601
    val b602 = withCtrl(x1062) { Const(true).name("b602") } // b602
    val x1059_x1059 = withCtrl(x1062) { ReadMem(x1047).name("x1059_x1059").srcCtx("DataGenerator.scala:29:8") } // ParStreamRead(x1047,List(b602))
    val x1060_x1060 = withCtrl(x1062) { x1059_x1059 } // VectorApply(x1059,0)
    val x1061 = withCtrl(x1062) { StoreBanks(List(List(x1045_d0_b0), List(x1045_d1_b0)), List(b601), x1060_x1060).name("x1061").srcCtx("DataGenerator.scala:29:8") } // ParSRAMStore(x1045,List(List(b601)),List(x1060),List(b602))
    val x1064_d0_b0 = withCtrl(x1124) { SRAM(size=512, banking=Strided(banks=16, stride=1)).name("x1064_d0_b0").srcCtx("main.scala:22:27:outmem") } // x1064 = SRAMNew(ArrayBuffer(Const(512)))
    isAccum(x1064_d0_b0) = false
    bufferDepthOf(x1064_d0_b0) = 1
    staticDimsOf(x1064_d0_b0) = List(512)
    val x1065 = withCtrl(x1124) { Counter(min=Const(0), max=Const(512), step=Const(1), par=16).name("x1065").srcCtx("main.scala:23:27") } // CounterNew(Const(0),Const(512),Const(1),Const(16))
    val x1066 = withCtrl(x1124) { CounterChain(List(x1065)).name("x1066").srcCtx("main.scala:23:35") } // CounterChainNew(List(x1065))
    val x1079 = withCtrl(x1124) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1066).name("x1079").srcCtx("main.scala:23:35") } // UnrolledForeach(List(Const(true)),x1066,Block(Const(())),List(List(b611)),List(List(b612)))
    val b611 = withCtrl(x1079) { CounterIter(x1065, None).name("b611") } // b611
    val b612 = withCtrl(x1079) { Const(true).name("b612") } // b612
    val x1067 = withCtrl(x1079) { LoadBanks(List(x1045_d1_b0), List(b611)).name("x1067").srcCtx("main.scala:24:24:inEle") } // ParSRAMLoad(x1045,List(List(b611)),List(b612))
    val x1068 = withCtrl(x1079) { x1067 } // VectorApply(x1067,0)
    val x1069 = withCtrl(x1079) { OpDef(op=FixLt, inputs=List(Const(4.0), x1068)).name("x1069").srcCtx("main.scala:25:31") } // FixLt(Const(4),x1068)
    val x1070 = withCtrl(x1079) { OpDef(op=FixLt, inputs=List(x1068, Const(-4.0))).name("x1070").srcCtx("main.scala:26:21") } // FixLt(x1068,Const(-4))
    val x1071_d0_b0 = withCtrl(x1079) { LUT(inits=List(-0.99932927, -0.9993187, -0.999308, -0.9992971, -0.999286, -0.99927473, -0.9992633, -0.9992517, -0.9992399, -0.99922794, -0.9992158, -0.9992034, -0.99919087, -0.9991781, -0.9991652, -0.999152, -0.99913865, -0.99912506, -0.9991113, -0.9990973, -0.99908304, -0.9990686, -0.99905396, -0.99903905, -0.9990239, -0.99900854, -0.9989929, -0.998977, -0.9989609, -0.9989445, -0.9989279, -0.998911, -0.99889386, -0.99887645, -0.99885875, -0.99884075, -0.9988225, -0.998804, -0.99878514, -0.998766, -0.9987465, -0.9987268, -0.99870676, -0.9986864, -0.9986657, -0.99864465, -0.9986233, -0.9986016, -0.99857956, -0.9985572, -0.9985345, -0.99851143, -0.99848795, -0.99846417, -0.99843997, -0.9984154, -0.99839044, -0.99836504, -0.9983393, -0.9983132, -0.9982866, -0.9982596, -0.9982322, -0.99820435, -0.9981761, -0.99814737, -0.99811816, -0.99808854, -0.99805844, -0.99802786, -0.9979968, -0.9979653, -0.99793327, -0.9979007, -0.99786764, -0.9978341, -0.99779993, -0.9977653, -0.99773014, -0.9976944, -0.9976581, -0.99762124, -0.99758375, -0.9975457, -0.9975071, -0.9974678, -0.997428, -0.99738747, -0.99734634, -0.9973046, -0.9972622, -0.9972191, -0.9971753, -0.9971308, -0.9970857, -0.9970398, -0.9969932, -0.99694586, -0.9968978, -0.996849, -0.9967994, -0.99674904, -0.99669784, -0.9966459, -0.9965931, -0.99653953, -0.99648505, -0.99642974, -0.9963736, -0.99631655, -0.99625856, -0.9961997, -0.99613994, -0.9960792, -0.9960175, -0.9959549, -0.9958912, -0.9958266, -0.9957609, -0.9956943, -0.99562657, -0.9955578, -0.99548787, -0.99541694, -0.9953449, -0.9952716, -0.9951973, -0.9951218, -0.99504507, -0.99496716, -0.994888, -0.99480766, -0.994726, -0.9946431, -0.99455893, -0.9944734, -0.9943865, -0.9942983, -0.99420863, -0.9941176, -0.9940252, -0.9939313, -0.9938359, -0.99373907, -0.99364066, -0.99354076, -0.99343926, -0.9933362, -0.99323153, -0.9931252, -0.9930172, -0.9929075, -0.9927961, -0.992683, -0.9925681, -0.99245137, -0.9923328, -0.9922124, -0.99209017, -0.991966, -0.9918399, -0.99171174, -0.9915817, -0.99144953, -0.9913153, -0.99117905, -0.9910406, -0.99090004, -0.9907572, -0.9906122, -0.990465, -0.9903154, -0.9901635, -0.9900092, -0.9898525, -0.9896934, -0.98953176, -0.9893676, -0.98920095, -0.9890316, -0.9888597, -0.9886851, -0.98850775, -0.9883277, -0.98814476, -0.987959, -0.9877704, -0.9875788, -0.9873842, -0.9871866, -0.986986, -0.9867822, -0.98657525, -0.986365, -0.9861516, -0.98593485, -0.98571473, -0.98549116, -0.9852641, -0.9850336, -0.9847995, -0.98456174, -0.9843203, -0.98407507, -0.9838261, -0.9835732, -0.9833164, -0.98305565, -0.9827909, -0.982522, -0.9822489, -0.9819716, -0.98169005, -0.9814041, -0.98111373, -0.98081887, -0.9805195, -0.98021543, -0.9799067, -0.9795932, -0.97927487, -0.9789516, -0.97862333, -0.9782901, -0.9779516, -0.97760797, -0.977259, -0.97690463, -0.97654486, -0.97617954, -0.9758086, -0.975432, -0.9750495, -0.9746612, -0.9742669, -0.9738666, -0.9734601, -0.9730474, -0.97262836, -0.97220284, -0.9717709, -0.97133225, -0.97088695, -0.97043484, -0.96997577, -0.9695097, -0.9690365, -0.9685561, -0.96806836, -0.9675732, -0.96707046, -0.96656007, -0.9660419, -0.96551585, -0.9649818, -0.9644396, -0.9638892, -0.9633304, -0.96276313, -0.96218723, -0.9616026, -0.96100914, -0.9604067, -0.9597951, -0.95917434, -0.95854414, -0.9579044, -0.957255, -0.95659584, -0.9559267, -0.9552475, -0.95455813, -0.9538583, -0.953148, -0.952427, -0.95169526, -0.95095253, -0.9501986, -0.94943345, -0.9486568, -0.9478686, -0.9470686, -0.9462566, -0.94543254, -0.94459623, -0.94374746, -0.94288605, -0.94201183, -0.9411247, -0.94022435, -0.93931067, -0.9383835, -0.9374426, -0.9364878, -0.9355189, -0.93453574, -0.93353814, -0.9325258, -0.93149865, -0.9304564, -0.92939883, -0.92832583, -0.92723715, -0.9261325, -0.92501175, -0.92387474, -0.9227211, -0.92155075, -0.92036337, -0.91915876, -0.91793674, -0.916697, -0.9154394, -0.9141637, -0.9128696, -0.91155684, -0.9102253, -0.90887463, -0.9075046, -0.90611506, -0.90470564, -0.90327615, -0.9018263, -0.9003559, -0.89886457, -0.89735216, -0.8958184, -0.89426297, -0.89268565, -0.89108616, -0.8894642, -0.8878196, -0.8861519, -0.884461, -0.88274646, -0.88100815, -0.8792457, -0.8774589, -0.8756473, -0.87381077, -0.871949, -0.87006164, -0.86814845, -0.86620903, -0.8642432, -0.8622506, -0.86023104, -0.85818404, -0.8561094, -0.85400677, -0.8518759, -0.8497164, -0.8475281, -0.8453105, -0.8430635, -0.8407866, -0.8384795, -0.83614206, -0.8337738, -0.83137447, -0.8289437, -0.8264813, -0.82398677, -0.8214599, -0.8189004, -0.81630784, -0.813682, -0.8110226, -0.80832916, -0.8056015, -0.8028392, -0.8000421, -0.7972097, -0.79434174, -0.791438, -0.78849804, -0.7855216, -0.78250843, -0.7794581, -0.77637035, -0.7732449, -0.7700814, -0.7668796, -0.76363915, -0.76035976, -0.75704116, -0.75368303, -0.7502851, -0.746847, -0.7433685, -0.7398494, -0.7362893, -0.73268795, -0.72904515, -0.7253605, -0.72163385, -0.71786493, -0.7140534, -0.7101991, -0.70630175, -0.7023611, -0.6983769, -0.694349, -0.69027704, -0.6861609, -0.6820004, -0.6777953, -0.6735453, -0.66925037, -0.6649102, -0.66052467, -0.6560936, -0.6516169, -0.64709425, -0.6425256, -0.63791084, -0.63324976, -0.62854236, -0.6237884, -0.61898786, -0.6141406, -0.6092466, -0.6043057, -0.5993179, -0.5942831, -0.58920133, -0.5840725, -0.5788966, -0.5736736, -0.56840354, -0.56308645, -0.5577223, -0.5523111, -0.54685307, -0.5413481, -0.5357963, -0.5301978, -0.5245527, -0.5188611, -0.5131231, -0.5073389, -0.5015086, -0.49563235, -0.48971045, -0.48374295, -0.47773015, -0.4716723, -0.46556956, -0.45942217, -0.4532305, -0.44699484, -0.44071537, -0.43439245, -0.42802644, -0.4216177, -0.41516656, -0.40867335, -0.40213853, -0.39556253, -0.38894564, -0.3822884, -0.37559128, -0.36885464, -0.36207908, -0.35526502, -0.348413, -0.34152353, -0.33459717, -0.32763445, -0.32063597, -0.31360233, -0.30653405, -0.29943186, -0.2922963, -0.28512806, -0.27792776, -0.2706961, -0.26343375, -0.25614148, -0.24881989, -0.24146974, -0.23409182, -0.22668678, -0.2192555, -0.21179867, -0.2043171, -0.19681156, -0.1892829, -0.18173194, -0.17415947, -0.16656631, -0.15895337, -0.15132153, -0.14367151, -0.13600439, -0.12832087, -0.12062192, -0.11290848, -0.10518134, -0.097441494, -0.08968985, -0.08192736, -0.07415491, -0.06637341, -0.058583856, -0.05078715, -0.042984247, -0.03517604, -0.027363598, -0.01954782, -0.011729658, -0.003910005, 0.003910005, 0.011729658, 0.01954782, 0.027363598, 0.03517604, 0.042984247, 0.05078715, 0.058583856, 0.06637341, 0.07415491, 0.08192736, 0.08968985, 0.097441494, 0.10518134, 0.11290848, 0.12062192, 0.12832087, 0.13600439, 0.14367151, 0.15132153, 0.15895337, 0.16656631, 0.17415947, 0.18173194, 0.1892829, 0.19681156, 0.2043171, 0.21179867, 0.2192555, 0.22668678, 0.23409182, 0.24146974, 0.24881989, 0.25614148, 0.26343375, 0.2706961, 0.27792776, 0.28512806, 0.2922963, 0.29943186, 0.30653405, 0.31360233, 0.32063597, 0.32763445, 0.33459717, 0.34152353, 0.348413, 0.35526502, 0.36207908, 0.36885464, 0.37559128, 0.3822884, 0.38894564, 0.39556253, 0.40213853, 0.40867335, 0.41516656, 0.4216177, 0.42802644, 0.43439245, 0.44071537, 0.44699484, 0.4532305, 0.45942217, 0.46556956, 0.4716723, 0.47773015, 0.48374295, 0.48971045, 0.49563235, 0.5015086, 0.5073389, 0.5131231, 0.5188611, 0.5245527, 0.5301978, 0.5357963, 0.5413481, 0.54685307, 0.5523111, 0.5577223, 0.56308645, 0.56840354, 0.5736736, 0.5788966, 0.5840725, 0.58920133, 0.5942831, 0.5993179, 0.6043057, 0.6092466, 0.6141406, 0.61898786, 0.6237884, 0.62854236, 0.63324976, 0.63791084, 0.6425256, 0.64709425, 0.6516169, 0.6560936, 0.66052467, 0.6649102, 0.66925037, 0.6735453, 0.6777953, 0.6820004, 0.6861609, 0.69027704, 0.694349, 0.6983769, 0.7023611, 0.70630175, 0.7101991, 0.7140534, 0.71786493, 0.72163385, 0.7253605, 0.72904515, 0.73268795, 0.7362893, 0.7398494, 0.7433685, 0.746847, 0.7502851, 0.75368303, 0.75704116, 0.76035976, 0.76363915, 0.7668796, 0.7700814, 0.7732449, 0.77637035, 0.7794581, 0.78250843, 0.7855216, 0.78849804, 0.791438, 0.79434174, 0.7972097, 0.8000421, 0.8028392, 0.8056015, 0.80832916, 0.8110226, 0.813682, 0.81630784, 0.8189004, 0.8214599, 0.82398677, 0.8264813, 0.8289437, 0.83137447, 0.8337738, 0.83614206, 0.8384795, 0.8407866, 0.8430635, 0.8453105, 0.8475281, 0.8497164, 0.8518759, 0.85400677, 0.8561094, 0.85818404, 0.86023104, 0.8622506, 0.8642432, 0.86620903, 0.86814845, 0.87006164, 0.871949, 0.87381077, 0.8756473, 0.8774589, 0.8792457, 0.88100815, 0.88274646, 0.884461, 0.8861519, 0.8878196, 0.8894642, 0.89108616, 0.89268565, 0.89426297, 0.8958184, 0.89735216, 0.89886457, 0.9003559, 0.9018263, 0.90327615, 0.90470564, 0.90611506, 0.9075046, 0.90887463, 0.9102253, 0.91155684, 0.9128696, 0.9141637, 0.9154394, 0.916697, 0.91793674, 0.91915876, 0.92036337, 0.92155075, 0.9227211, 0.92387474, 0.92501175, 0.9261325, 0.92723715, 0.92832583, 0.92939883, 0.9304564, 0.93149865, 0.9325258, 0.93353814, 0.93453574, 0.9355189, 0.9364878, 0.9374426, 0.9383835, 0.93931067, 0.94022435, 0.9411247, 0.94201183, 0.94288605, 0.94374746, 0.94459623, 0.94543254, 0.9462566, 0.9470686, 0.9478686, 0.9486568, 0.94943345, 0.9501986, 0.95095253, 0.95169526, 0.952427, 0.953148, 0.9538583, 0.95455813, 0.9552475, 0.9559267, 0.95659584, 0.957255, 0.9579044, 0.95854414, 0.95917434, 0.9597951, 0.9604067, 0.96100914, 0.9616026, 0.96218723, 0.96276313, 0.9633304, 0.9638892, 0.9644396, 0.9649818, 0.96551585, 0.9660419, 0.96656007, 0.96707046, 0.9675732, 0.96806836, 0.9685561, 0.9690365, 0.9695097, 0.96997577, 0.97043484, 0.97088695, 0.97133225, 0.9717709, 0.97220284, 0.97262836, 0.9730474, 0.9734601, 0.9738666, 0.9742669, 0.9746612, 0.9750495, 0.975432, 0.9758086, 0.97617954, 0.97654486, 0.97690463, 0.977259, 0.97760797, 0.9779516, 0.9782901, 0.97862333, 0.9789516, 0.97927487, 0.9795932, 0.9799067, 0.98021543, 0.9805195, 0.98081887, 0.98111373, 0.9814041, 0.98169005, 0.9819716, 0.9822489, 0.982522, 0.9827909, 0.98305565, 0.9833164, 0.9835732, 0.9838261, 0.98407507, 0.9843203, 0.98456174, 0.9847995, 0.9850336, 0.9852641, 0.98549116, 0.98571473, 0.98593485, 0.9861516, 0.986365, 0.98657525, 0.9867822, 0.986986, 0.9871866, 0.9873842, 0.9875788, 0.9877704, 0.987959, 0.98814476, 0.9883277, 0.98850775, 0.9886851, 0.9888597, 0.9890316, 0.98920095, 0.9893676, 0.98953176, 0.9896934, 0.9898525, 0.9900092, 0.9901635, 0.9903154, 0.990465, 0.9906122, 0.9907572, 0.99090004, 0.9910406, 0.99117905, 0.9913153, 0.99144953, 0.9915817, 0.99171174, 0.9918399, 0.991966, 0.99209017, 0.9922124, 0.9923328, 0.99245137, 0.9925681, 0.992683, 0.9927961, 0.9929075, 0.9930172, 0.9931252, 0.99323153, 0.9933362, 0.99343926, 0.99354076, 0.99364066, 0.99373907, 0.9938359, 0.9939313, 0.9940252, 0.9941176, 0.99420863, 0.9942983, 0.9943865, 0.9944734, 0.99455893, 0.9946431, 0.994726, 0.99480766, 0.994888, 0.99496716, 0.99504507, 0.9951218, 0.9951973, 0.9952716, 0.9953449, 0.99541694, 0.99548787, 0.9955578, 0.99562657, 0.9956943, 0.9957609, 0.9958266, 0.9958912, 0.9959549, 0.9960175, 0.9960792, 0.99613994, 0.9961997, 0.99625856, 0.99631655, 0.9963736, 0.99642974, 0.99648505, 0.99653953, 0.9965931, 0.9966459, 0.99669784, 0.99674904, 0.9967994, 0.996849, 0.9968978, 0.99694586, 0.9969932, 0.9970398, 0.9970857, 0.9971308, 0.9971753, 0.9972191, 0.9972622, 0.9973046, 0.99734634, 0.99738747, 0.997428, 0.9974678, 0.9975071, 0.9975457, 0.99758375, 0.99762124, 0.9976581, 0.9976944, 0.99773014, 0.9977653, 0.99779993, 0.9978341, 0.99786764, 0.9979007, 0.99793327, 0.9979653, 0.9979968, 0.99802786, 0.99805844, 0.99808854, 0.99811816, 0.99814737, 0.9981761, 0.99820435, 0.9982322, 0.9982596, 0.9982866, 0.9983132, 0.9983393, 0.99836504, 0.99839044, 0.9984154, 0.99843997, 0.99846417, 0.99848795, 0.99851143, 0.9985345, 0.9985572, 0.99857956, 0.9986016, 0.9986233, 0.99864465, 0.9986657, 0.9986864, 0.99870676, 0.9987268, 0.9987465, 0.998766, 0.99878514, 0.998804, 0.9988225, 0.99884075, 0.99885875, 0.99887645, 0.99889386, 0.998911, 0.9989279, 0.9989445, 0.9989609, 0.998977, 0.9989929, 0.99900854, 0.9990239, 0.99903905, 0.99905396, 0.9990686, 0.99908304, 0.9990973, 0.9991113, 0.99912506, 0.99913865, 0.999152, 0.9991652, 0.9991781, 0.99919087, 0.9992034, 0.9992158, 0.99922794, 0.9992399, 0.9992517, 0.9992633, 0.99927473, 0.999286, 0.9992971, 0.999308, 0.9993187, 0.99932927), banking=Strided(banks=1, stride=1)).name("x1071_d0_b0").srcCtx("NonLinearity.scala:36:37:lut") } // x1071 = LUTNew(List(1024), Seq(Const(-0.999329268932342529296875),Const(-0.99931871891021728515625),Const(-0.99930799007415771484375)... [1021 more]))
    isAccum(x1071_d0_b0) = false
    bufferDepthOf(x1071_d0_b0) = 1
    staticDimsOf(x1071_d0_b0) = List(1024)
    val x1072 = withCtrl(x1079) { OpDef(op=FixSub, inputs=List(x1068, Const(-4.0))).name("x1072").srcCtx("NonLinearity.scala:38:22") } // FixSub(x1068,Const(-4))
    val x1073 = withCtrl(x1079) { OpDef(op=FixMul, inputs=List(x1072, Const(-128.0))).name("x1073").srcCtx("NonLinearity.scala:38:30") } // FixMul(x1072,Const(-128))
    // x1074 = FixConvert(x1073,TRUE,_32,_0) x.tp=FixPt[TRUE,_8,_24] {
    val x1074 = withCtrl(x1079) { OpDef(op=FixSra, inputs=List(x1073, Const("24"))).name("x1074").srcCtx("NonLinearity.scala:38:41:index") } // FixConvert(x1073,TRUE,_32,_0)
    // }
    val x1075 = withCtrl(x1079) { LoadBanks(List(x1071_d0_b0), List(x1074)).name("x1075").srcCtx("NonLinearity.scala:39:17:re") } // LUTLoad(x1071,List(x1074),b612)
    val x1076 = withCtrl(x1079) { OpDef(op=MuxOp, inputs=List(x1070, Const(-1.0), x1075)).name("x1076").srcCtx("main.scala:26:14") } // Mux(x1070,Const(-1),x1075)
    val x1077 = withCtrl(x1079) { OpDef(op=MuxOp, inputs=List(x1069, Const(1.0), x1076)).name("x1077").srcCtx("main.scala:25:24") } // Mux(x1069,Const(1),x1076)
    val x1078 = withCtrl(x1079) { StoreBanks(List(List(x1064_d0_b0)), List(b611), x1077).name("x1078").srcCtx("main.scala:25:19") } // ParSRAMStore(x1064,List(List(b611)),List(x1077),List(b612))
    val x1101 = withCtrl(x1124) { UnitController(style=StreamPipe, level=OuterControl).name("x1101").srcCtx("main.scala:29:21") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b1133 = withCtrl(x1101) { StreamOut(field="offset").name("b1133").srcCtx("main.scala:29:21") } // x1080 = StreamOutNew(BurstCmdBus)
    isAccum(b1133) = false
    bufferDepthOf(b1133) = 1
    val b1134 = withCtrl(x1101) { StreamOut(field="size").name("b1134").srcCtx("main.scala:29:21") } // x1080 = StreamOutNew(BurstCmdBus)
    isAccum(b1134) = false
    bufferDepthOf(b1134) = 1
    val x1081 = withCtrl(x1101) { StreamOut(field="data").name("x1081").srcCtx("main.scala:29:21") } // x1081 = StreamOutNew(BurstFullDataBus())
    isAccum(x1081) = false
    bufferDepthOf(x1081) = 1
    val x1082 = withCtrl(x1101) { StreamIn(field="ack").name("x1082").srcCtx("main.scala:29:21") } // x1082 = StreamInNew(BurstAckBus)
    isAccum(x1082) = false
    bufferDepthOf(x1082) = 1
    val x1090 = withCtrl(x1101) { UnitController(style=SeqPipe, level=InnerControl).name("x1090").srcCtx("main.scala:29:21") } // UnitPipe(List(Const(true)),Block(x1089))
    val x1083 = withCtrl(x1090) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x1084 = withCtrl(x1090) { OpDef(op=FixSla, inputs=List(x1083, Const(2))).name("x1084").srcCtx("main.scala:29:21") } // FixLsh(x1083,Const(2))
    val x1085 = withCtrl(x1090) { x1084 } // FixConvert(x1084,TRUE,_64,_0)
    val x1086 = withCtrl(x1090) { DramAddress(x1043).name("x1086").srcCtx("main.scala:29:21") } // GetDRAMAddress(x1043)
    val x1088_x1087 = withCtrl(x1090) { OpDef(op=FixAdd, inputs=List(x1085, x1086)).name("x1088_x1087").srcCtx("main.scala:29:21") } // FixAdd(x1085,x1086)
    // x1088 = SimpleStruct(ArrayBuffer((offset,x1087), (size,Const(2048)), (isLoad,Const(false))))
    val x1089_b1135_b1133 = withCtrl(x1090) { WriteMem(b1133, x1088_x1087).name("x1089_b1135_b1133").srcCtx("main.scala:29:21") } // StreamWrite(x1080,x1088,Const(true))
    val x1089_b1136_b1134 = withCtrl(x1090) { WriteMem(b1134, Const(2048)).name("x1089_b1136_b1134").srcCtx("main.scala:29:21") } // StreamWrite(x1080,x1088,Const(true))
    val x1091 = withCtrl(x1101) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x1091").srcCtx("main.scala:29:21") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x1092 = withCtrl(x1101) { CounterChain(List(x1091)).name("x1092").srcCtx("main.scala:29:21") } // CounterChainNew(List(x1091))
    val x1097 = withCtrl(x1101) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1092).name("x1097").srcCtx("main.scala:29:21") } // UnrolledForeach(List(Const(true)),x1092,Block(Const(())),List(List(b639)),List(List(b640)))
    val b639 = withCtrl(x1097) { CounterIter(x1091, None).name("b639") } // b639
    val b640 = withCtrl(x1097) { Const(true).name("b640") } // b640
    val x1093 = withCtrl(x1097) { LoadBanks(List(x1064_d0_b0), List(b639)).name("x1093").srcCtx("main.scala:29:21") } // ParSRAMLoad(x1064,List(List(b639)),List(b640))
    val x1095_x1094 = withCtrl(x1097) { x1093 } // VectorApply(x1093,0)
    // x1095 = SimpleStruct(ArrayBuffer((_1,x1094), (_2,Const(true))))
    val x1096_x1096_x1081 = withCtrl(x1097) { WriteMem(x1081, x1095_x1094).name("x1096_x1096_x1081").srcCtx("main.scala:29:21") } // ParStreamWrite(x1081,List(x1095),List(b640))
    val x1098 = withCtrl(x1101) { FringeDenseStore(dram=List(x1043), cmdStream=List(b1133, b1134), dataStream=List(x1081), ackStream=List(x1082)).name("x1098").srcCtx("main.scala:29:21") } // FringeDenseStore(x1043,x1080,x1081,x1082)
    val x1100 = withCtrl(x1101) { UnitController(style=SeqPipe, level=InnerControl).name("x1100").srcCtx("main.scala:29:21") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x1099_x1099 = withCtrl(x1100) { ReadMem(x1082).name("x1099_x1099").srcCtx("main.scala:29:21") } // StreamRead(x1082,Const(true))
    val x1123 = withCtrl(x1124) { UnitController(style=StreamPipe, level=OuterControl).name("x1123").srcCtx("main.scala:30:24") } // UnitPipe(List(Const(true)),Block(Const(())))
    val b1137 = withCtrl(x1123) { StreamOut(field="offset").name("b1137").srcCtx("main.scala:30:24") } // x1102 = StreamOutNew(BurstCmdBus)
    isAccum(b1137) = false
    bufferDepthOf(b1137) = 1
    val b1138 = withCtrl(x1123) { StreamOut(field="size").name("b1138").srcCtx("main.scala:30:24") } // x1102 = StreamOutNew(BurstCmdBus)
    isAccum(b1138) = false
    bufferDepthOf(b1138) = 1
    val x1103 = withCtrl(x1123) { StreamOut(field="data").name("x1103").srcCtx("main.scala:30:24") } // x1103 = StreamOutNew(BurstFullDataBus())
    isAccum(x1103) = false
    bufferDepthOf(x1103) = 1
    val x1104 = withCtrl(x1123) { StreamIn(field="ack").name("x1104").srcCtx("main.scala:30:24") } // x1104 = StreamInNew(BurstAckBus)
    isAccum(x1104) = false
    bufferDepthOf(x1104) = 1
    val x1112 = withCtrl(x1123) { UnitController(style=SeqPipe, level=InnerControl).name("x1112").srcCtx("main.scala:30:24") } // UnitPipe(List(Const(true)),Block(x1111))
    val x1105 = withCtrl(x1112) { Const(0) } // FixConvert(Const(0),TRUE,_32,_0) (Same Type. No op)
    val x1106 = withCtrl(x1112) { OpDef(op=FixSla, inputs=List(x1105, Const(2))).name("x1106").srcCtx("main.scala:30:24") } // FixLsh(x1105,Const(2))
    val x1107 = withCtrl(x1112) { x1106 } // FixConvert(x1106,TRUE,_64,_0)
    val x1108 = withCtrl(x1112) { DramAddress(x1044).name("x1108").srcCtx("main.scala:30:24") } // GetDRAMAddress(x1044)
    val x1110_x1109 = withCtrl(x1112) { OpDef(op=FixAdd, inputs=List(x1107, x1108)).name("x1110_x1109").srcCtx("main.scala:30:24") } // FixAdd(x1107,x1108)
    // x1110 = SimpleStruct(ArrayBuffer((offset,x1109), (size,Const(2048)), (isLoad,Const(false))))
    val x1111_b1139_b1137 = withCtrl(x1112) { WriteMem(b1137, x1110_x1109).name("x1111_b1139_b1137").srcCtx("main.scala:30:24") } // StreamWrite(x1102,x1110,Const(true))
    val x1111_b1140_b1138 = withCtrl(x1112) { WriteMem(b1138, Const(2048)).name("x1111_b1140_b1138").srcCtx("main.scala:30:24") } // StreamWrite(x1102,x1110,Const(true))
    val x1113 = withCtrl(x1123) { Counter(min=Const(0), max=Const(512), step=Const(1), par=1).name("x1113").srcCtx("main.scala:30:24") } // CounterNew(Const(0),Const(512),Const(1),Const(1))
    val x1114 = withCtrl(x1123) { CounterChain(List(x1113)).name("x1114").srcCtx("main.scala:30:24") } // CounterChainNew(List(x1113))
    val x1119 = withCtrl(x1123) { LoopController(style=InnerPipe, level=InnerControl, cchain=x1114).name("x1119").srcCtx("main.scala:30:24") } // UnrolledForeach(List(Const(true)),x1114,Block(Const(())),List(List(b663)),List(List(b664)))
    val b663 = withCtrl(x1119) { CounterIter(x1113, None).name("b663") } // b663
    val b664 = withCtrl(x1119) { Const(true).name("b664") } // b664
    val x1115 = withCtrl(x1119) { LoadBanks(List(x1045_d0_b0), List(b663)).name("x1115").srcCtx("main.scala:30:24") } // ParSRAMLoad(x1045,List(List(b663)),List(b664))
    val x1117_x1116 = withCtrl(x1119) { x1115 } // VectorApply(x1115,0)
    // x1117 = SimpleStruct(ArrayBuffer((_1,x1116), (_2,Const(true))))
    val x1118_x1118_x1103 = withCtrl(x1119) { WriteMem(x1103, x1117_x1116).name("x1118_x1118_x1103").srcCtx("main.scala:30:24") } // ParStreamWrite(x1103,List(x1117),List(b664))
    val x1120 = withCtrl(x1123) { FringeDenseStore(dram=List(x1044), cmdStream=List(b1137, b1138), dataStream=List(x1103), ackStream=List(x1104)).name("x1120").srcCtx("main.scala:30:24") } // FringeDenseStore(x1044,x1102,x1103,x1104)
    val x1122 = withCtrl(x1123) { UnitController(style=SeqPipe, level=InnerControl).name("x1122").srcCtx("main.scala:30:24") } // UnitPipe(List(Const(true)),Block(Const(())))
    val x1121_x1121 = withCtrl(x1122) { ReadMem(x1104).name("x1121_x1121").srcCtx("main.scala:30:24") } // StreamRead(x1104,Const(true))
    
  }
}
