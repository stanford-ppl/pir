import pir.graph
import pir.graph._
import pir.graph.enums._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.misc._
import pir.PIRApp

object TPCHQ6Design extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x4010_oc = OffChip("x4010")
    val x4035_x4883_data_vector = Vector("x4035_x4883_data")
    val bus_1325_vector = Vector("bus_1325")
    val bus_1255_vector = Vector("bus_1255")
    val x4034_x4851_data_vector = Vector("x4034_x4851_data")
    val x4033_x4819_data_vector = Vector("x4033_x4819_data")
    val bus_1290_vector = Vector("bus_1290")
    val x4011_oc = OffChip("x4011")
    val x4032_x4787_data_vector = Vector("x4032_x4787_data")
    val x4013_oc = OffChip("x4013")
    val bus_1354_vector = Vector("bus_1354")
    val bus_1319_vector = Vector("bus_1319")
    val bus_1249_vector = Vector("bus_1249")
    val x4012_oc = OffChip("x4012")
    val bus_1284_vector = Vector("bus_1284")
    val x4770_scalar = Scalar("x4770")
    val x4014_argout = ArgOut("x4014")
    val x4772_scalar = Scalar("x4772")
    val x4773_scalar = Scalar("x4773")
    val x4771_scalar = Scalar("x4771")
    val bus_1360_vector = Vector("bus_1360")
    val x4609_mc = MemoryController(TileLoad, x4010_oc)
    val x4517_mc = MemoryController(TileLoad, x4012_oc)
    val x4335_mc = MemoryController(TileLoad, x4012_oc)
    val x4380_mc = MemoryController(TileLoad, x4013_oc)
    val x4562_mc = MemoryController(TileLoad, x4013_oc)
    val x4108_mc = MemoryController(TileLoad, x4011_oc)
    val x4153_mc = MemoryController(TileLoad, x4012_oc)
    val x4699_mc = MemoryController(TileLoad, x4012_oc)
    val x4245_mc = MemoryController(TileLoad, x4010_oc)
    val x4654_mc = MemoryController(TileLoad, x4011_oc)
    val x4744_mc = MemoryController(TileLoad, x4013_oc)
    val x4290_mc = MemoryController(TileLoad, x4011_oc)
    val x4472_mc = MemoryController(TileLoad, x4011_oc)
    val x4198_mc = MemoryController(TileLoad, x4013_oc)
    val x4063_mc = MemoryController(TileLoad, x4010_oc)
    val x4427_mc = MemoryController(TileLoad, x4010_oc)
    val x4931 = Sequential(name = "x4931", parent=top, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4931_unitcc = CounterChain(name = "x4931_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4925 = MetaPipeline(name = "x4925", parent=x4931, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr1 = (Const("0i").out, Const(s"${960000000/4}i").out, Const("2000i").out) // Counter
      val x4021 = CounterChain(name = "x4021", ctr1)
      var stage: List[Stage] = Nil
    }
    val x4084 = StreamController(name = "x4084", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4084_unitcc = CounterChain(name = "x4084_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4059_0 = UnitPipeline(name = "x4059_0", parent=x4084, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr868 = CU.temp
      val tr867 = CU.temp
      val tr865 = CU.temp
      val tr864 = CU.temp
      val tr862 = CU.temp
      val tr857 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4059_unitcc = CounterChain(name = "x4059_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr857)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr857)), op=FixSub, results=List(CU.scalarOut(stage(2), x4063_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr857), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr862)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr862), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr864)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr862), CU.temp(stage(4), tr864)), op=FixSub, results=List(CU.temp(stage(5), tr865)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr864), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr867)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr867), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr868)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr865), CU.temp(stage(7), tr868)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4063_mc.len)))
    }
    val x4129 = StreamController(name = "x4129", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4129_unitcc = CounterChain(name = "x4129_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4104_0 = UnitPipeline(name = "x4104_0", parent=x4129, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr891 = CU.temp
      val tr890 = CU.temp
      val tr888 = CU.temp
      val tr887 = CU.temp
      val tr885 = CU.temp
      val tr880 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4104_unitcc = CounterChain(name = "x4104_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr880)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr880)), op=FixSub, results=List(CU.scalarOut(stage(2), x4108_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr880), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr885)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr885), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr887)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr885), CU.temp(stage(4), tr887)), op=FixSub, results=List(CU.temp(stage(5), tr888)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr887), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr890)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr890), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr891)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr888), CU.temp(stage(7), tr891)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4108_mc.len)))
    }
    val x4174 = StreamController(name = "x4174", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4174_unitcc = CounterChain(name = "x4174_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4149_0 = UnitPipeline(name = "x4149_0", parent=x4174, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr914 = CU.temp
      val tr913 = CU.temp
      val tr911 = CU.temp
      val tr910 = CU.temp
      val tr908 = CU.temp
      val tr903 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4149_unitcc = CounterChain(name = "x4149_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr903)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr903)), op=FixSub, results=List(CU.scalarOut(stage(2), x4153_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr903), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr908)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr908), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr910)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr908), CU.temp(stage(4), tr910)), op=FixSub, results=List(CU.temp(stage(5), tr911)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr910), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr913)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr913), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr914)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr911), CU.temp(stage(7), tr914)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4153_mc.len)))
    }
    val x4219 = StreamController(name = "x4219", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4219_unitcc = CounterChain(name = "x4219_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4194_0 = UnitPipeline(name = "x4194_0", parent=x4219, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr937 = CU.temp
      val tr936 = CU.temp
      val tr934 = CU.temp
      val tr933 = CU.temp
      val tr931 = CU.temp
      val tr926 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4194_unitcc = CounterChain(name = "x4194_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr926)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr926)), op=FixSub, results=List(CU.scalarOut(stage(2), x4198_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr926), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr931)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr931), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr933)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr931), CU.temp(stage(4), tr933)), op=FixSub, results=List(CU.temp(stage(5), tr934)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr933), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr936)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr936), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr937)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr934), CU.temp(stage(7), tr937)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4198_mc.len)))
    }
    val x4266 = StreamController(name = "x4266", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4266_unitcc = CounterChain(name = "x4266_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4241_0 = UnitPipeline(name = "x4241_0", parent=x4266, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr960 = CU.temp
      val tr959 = CU.temp
      val tr957 = CU.temp
      val tr956 = CU.temp
      val tr954 = CU.temp
      val tr949 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4241_unitcc = CounterChain(name = "x4241_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr949)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr949)), op=FixSub, results=List(CU.scalarOut(stage(2), x4245_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr949), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr954)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr954), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr956)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr954), CU.temp(stage(4), tr956)), op=FixSub, results=List(CU.temp(stage(5), tr957)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr956), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr959)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr959), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr960)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr957), CU.temp(stage(7), tr960)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4245_mc.len)))
    }
    val x4311 = StreamController(name = "x4311", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4311_unitcc = CounterChain(name = "x4311_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4286_0 = UnitPipeline(name = "x4286_0", parent=x4311, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr983 = CU.temp
      val tr982 = CU.temp
      val tr980 = CU.temp
      val tr979 = CU.temp
      val tr977 = CU.temp
      val tr972 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4286_unitcc = CounterChain(name = "x4286_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr972)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr972)), op=FixSub, results=List(CU.scalarOut(stage(2), x4290_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr972), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr977)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr977), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr979)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr977), CU.temp(stage(4), tr979)), op=FixSub, results=List(CU.temp(stage(5), tr980)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr979), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr982)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr982), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr983)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr980), CU.temp(stage(7), tr983)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4290_mc.len)))
    }
    val x4356 = StreamController(name = "x4356", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4356_unitcc = CounterChain(name = "x4356_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4331_0 = UnitPipeline(name = "x4331_0", parent=x4356, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1006 = CU.temp
      val tr1005 = CU.temp
      val tr1003 = CU.temp
      val tr1002 = CU.temp
      val tr1000 = CU.temp
      val tr995 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4331_unitcc = CounterChain(name = "x4331_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr995)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr995)), op=FixSub, results=List(CU.scalarOut(stage(2), x4335_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr995), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr1000)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1000), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1002)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1000), CU.temp(stage(4), tr1002)), op=FixSub, results=List(CU.temp(stage(5), tr1003)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1002), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1005)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1005), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1006)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1003), CU.temp(stage(7), tr1006)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4335_mc.len)))
    }
    val x4401 = StreamController(name = "x4401", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4401_unitcc = CounterChain(name = "x4401_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4376_0 = UnitPipeline(name = "x4376_0", parent=x4401, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1029 = CU.temp
      val tr1028 = CU.temp
      val tr1026 = CU.temp
      val tr1025 = CU.temp
      val tr1023 = CU.temp
      val tr1018 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4376_unitcc = CounterChain(name = "x4376_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1018)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr1018)), op=FixSub, results=List(CU.scalarOut(stage(2), x4380_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1018), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr1023)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1023), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1025)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1023), CU.temp(stage(4), tr1025)), op=FixSub, results=List(CU.temp(stage(5), tr1026)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1025), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1028)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1028), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1029)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1026), CU.temp(stage(7), tr1029)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4380_mc.len)))
    }
    val x4448 = StreamController(name = "x4448", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4448_unitcc = CounterChain(name = "x4448_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4423_0 = UnitPipeline(name = "x4423_0", parent=x4448, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1052 = CU.temp
      val tr1051 = CU.temp
      val tr1049 = CU.temp
      val tr1048 = CU.temp
      val tr1046 = CU.temp
      val tr1041 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4423_unitcc = CounterChain(name = "x4423_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1041)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr1041)), op=FixSub, results=List(CU.scalarOut(stage(2), x4427_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1041), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr1046)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1046), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1048)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1046), CU.temp(stage(4), tr1048)), op=FixSub, results=List(CU.temp(stage(5), tr1049)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1048), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1051)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1051), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1052)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1049), CU.temp(stage(7), tr1052)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4427_mc.len)))
    }
    val x4493 = StreamController(name = "x4493", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4493_unitcc = CounterChain(name = "x4493_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4468_0 = UnitPipeline(name = "x4468_0", parent=x4493, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1075 = CU.temp
      val tr1074 = CU.temp
      val tr1072 = CU.temp
      val tr1071 = CU.temp
      val tr1069 = CU.temp
      val tr1064 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4468_unitcc = CounterChain(name = "x4468_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1064)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr1064)), op=FixSub, results=List(CU.scalarOut(stage(2), x4472_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1064), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr1069)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1069), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1071)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1069), CU.temp(stage(4), tr1071)), op=FixSub, results=List(CU.temp(stage(5), tr1072)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1071), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1074)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1074), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1075)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1072), CU.temp(stage(7), tr1075)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4472_mc.len)))
    }
    val x4538 = StreamController(name = "x4538", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4538_unitcc = CounterChain(name = "x4538_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4513_0 = UnitPipeline(name = "x4513_0", parent=x4538, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1098 = CU.temp
      val tr1097 = CU.temp
      val tr1095 = CU.temp
      val tr1094 = CU.temp
      val tr1092 = CU.temp
      val tr1087 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4513_unitcc = CounterChain(name = "x4513_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1087)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr1087)), op=FixSub, results=List(CU.scalarOut(stage(2), x4517_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1087), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr1092)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1092), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1094)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1092), CU.temp(stage(4), tr1094)), op=FixSub, results=List(CU.temp(stage(5), tr1095)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1094), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1097)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1097), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1098)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1095), CU.temp(stage(7), tr1098)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4517_mc.len)))
    }
    val x4583 = StreamController(name = "x4583", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4583_unitcc = CounterChain(name = "x4583_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4558_0 = UnitPipeline(name = "x4558_0", parent=x4583, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1121 = CU.temp
      val tr1120 = CU.temp
      val tr1118 = CU.temp
      val tr1117 = CU.temp
      val tr1115 = CU.temp
      val tr1110 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4558_unitcc = CounterChain(name = "x4558_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1110)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr1110)), op=FixSub, results=List(CU.scalarOut(stage(2), x4562_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1110), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr1115)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1115), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1117)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1115), CU.temp(stage(4), tr1117)), op=FixSub, results=List(CU.temp(stage(5), tr1118)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1117), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1120)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1120), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1121)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1118), CU.temp(stage(7), tr1121)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4562_mc.len)))
    }
    val x4630 = StreamController(name = "x4630", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4630_unitcc = CounterChain(name = "x4630_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4605_0 = UnitPipeline(name = "x4605_0", parent=x4630, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1144 = CU.temp
      val tr1143 = CU.temp
      val tr1141 = CU.temp
      val tr1140 = CU.temp
      val tr1138 = CU.temp
      val tr1133 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4605_unitcc = CounterChain(name = "x4605_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1133)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr1133)), op=FixSub, results=List(CU.scalarOut(stage(2), x4609_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1133), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr1138)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1138), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1140)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1138), CU.temp(stage(4), tr1140)), op=FixSub, results=List(CU.temp(stage(5), tr1141)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1140), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1143)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1143), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1144)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1141), CU.temp(stage(7), tr1144)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4609_mc.len)))
    }
    val x4675 = StreamController(name = "x4675", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4675_unitcc = CounterChain(name = "x4675_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4650_0 = UnitPipeline(name = "x4650_0", parent=x4675, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1167 = CU.temp
      val tr1166 = CU.temp
      val tr1164 = CU.temp
      val tr1163 = CU.temp
      val tr1161 = CU.temp
      val tr1156 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4650_unitcc = CounterChain(name = "x4650_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1156)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr1156)), op=FixSub, results=List(CU.scalarOut(stage(2), x4654_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1156), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr1161)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1161), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1163)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1161), CU.temp(stage(4), tr1163)), op=FixSub, results=List(CU.temp(stage(5), tr1164)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1163), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1166)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1166), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1167)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1164), CU.temp(stage(7), tr1167)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4654_mc.len)))
    }
    val x4720 = StreamController(name = "x4720", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4720_unitcc = CounterChain(name = "x4720_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4695_0 = UnitPipeline(name = "x4695_0", parent=x4720, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1190 = CU.temp
      val tr1189 = CU.temp
      val tr1187 = CU.temp
      val tr1186 = CU.temp
      val tr1184 = CU.temp
      val tr1179 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4695_unitcc = CounterChain(name = "x4695_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1179)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr1179)), op=FixSub, results=List(CU.scalarOut(stage(2), x4699_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1179), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr1184)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1184), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1186)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1184), CU.temp(stage(4), tr1186)), op=FixSub, results=List(CU.temp(stage(5), tr1187)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1186), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1189)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1189), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1190)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1187), CU.temp(stage(7), tr1190)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4699_mc.len)))
    }
    val x4765 = StreamController(name = "x4765", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4765_unitcc = CounterChain(name = "x4765_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    val x4740_0 = UnitPipeline(name = "x4740_0", parent=x4765, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1213 = CU.temp
      val tr1212 = CU.temp
      val tr1210 = CU.temp
      val tr1209 = CU.temp
      val tr1207 = CU.temp
      val tr1202 = CU.temp
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4740_unitcc = CounterChain(name = "x4740_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(CU.ctr(stage(0), x4021(0)), Const("64i")), op=FixMod, results=List(CU.temp(stage(1), tr1202)))
      Stage(stage(2), operands=List(CU.ctr(stage(1), x4021(0)), CU.temp(stage(1), tr1202)), op=FixSub, results=List(CU.scalarOut(stage(2), x4744_mc.ofs)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1202), Const("2000i")), op=FixAdd, results=List(CU.temp(stage(3), tr1207)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1207), Const("64i")), op=FixMod, results=List(CU.temp(stage(4), tr1209)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1207), CU.temp(stage(4), tr1209)), op=FixSub, results=List(CU.temp(stage(5), tr1210)))
      Stage(stage(6), operands=List(CU.temp(stage(5), tr1209), Const("0i")), op=FixNeq, results=List(CU.temp(stage(6), tr1212)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1212), Const("64i"), Const("0i")), op=Mux, results=List(CU.temp(stage(7), tr1213)))
      Stage(stage(8), operands=List(CU.temp(stage(7), tr1210), CU.temp(stage(7), tr1213)), op=FixAdd, results=List(CU.scalarOut(stage(8), x4744_mc.len)))
    }
    val x4813 = StreamController(name = "x4813", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr5 = (Const("0i").out, Const("2000i").out, Const("1i").out) // Counter
      val x4778 = CounterChain(name = "x4778", ctr5)
      var stage: List[Stage] = Nil
    }
    val x4813_0 = StreamPipeline(name = "x4813_0", parent=x4813, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1248 = CU.temp
      val tr1246 = CU.temp
      val tr1245 = CU.temp
      val tr1244 = CU.temp
      val tr1243 = CU.temp
      val tr1241 = CU.temp
      val x4778 = CounterChain.copy(x4813, "x4778")
      val x4032_x4787 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4778(0))).wtPort(x4153_mc.vdata).rdAddr(x4778(0))
      val x4024_x4784 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4778(0))).wtPort(x4063_mc.vdata).rdAddr(x4778(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(Const("0i"), x4024_x4784.load), op=FixLt, results=List(CU.temp(stage(1), tr1241)))
      Stage(stage(2), operands=List(CU.load(stage(1), x4024_x4784), Const("9999i")), op=FixLt, results=List(CU.temp(stage(2), tr1243)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1241), CU.temp(stage(2), tr1243)), op=BitAnd, results=List(CU.temp(stage(3), tr1244)))
      Stage(stage(4), operands=List(Const("0i"), CU.load(stage(3), x4032_x4787)), op=FixLeq, results=List(CU.temp(stage(4), tr1245)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1244), CU.temp(stage(4), tr1245)), op=BitAnd, results=List(CU.temp(stage(5), tr1246)))
      Stage(stage(6), operands=List(CU.load(stage(5), x4032_x4787), Const("9999i")), op=FixLeq, results=List(CU.temp(stage(6), tr1248)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1246), CU.temp(stage(6), tr1248)), op=BitAnd, results=List(CU.vecOut(stage(7), bus_1249_vector)))
      Stage(stage(8), operands=List(CU.load(stage(7), x4032_x4787)), op=Bypass, results=List(CU.vecOut(stage(8), x4032_x4787_data_vector)))
    }
    val x4813_1 = StreamPipeline(name = "x4813_1", parent=x4813, deps=List(x4813_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1253 = CU.temp
      val tr1252 = CU.temp
      val tr1251 = CU.temp
      val x4778 = CounterChain.copy(x4813, "x4778")
      val x4036_x4793 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4778(0))).wtPort(x4198_mc.vdata).rdAddr(x4778(0))
      val x4028_x4790 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4778(0))).wtPort(x4108_mc.vdata).rdAddr(x4778(0))
      val bus_1249_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1249_vector)
      val x4032_x4787_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4032_x4787_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(x4028_x4790.load, Const("24i")), op=FixLt, results=List(CU.temp(stage(1), tr1251)))
      Stage(stage(2), operands=List(bus_1249_fifo.load, CU.temp(stage(1), tr1251)), op=BitAnd, results=List(CU.temp(stage(2), tr1252)))
      Stage(stage(3), operands=List(CU.load(stage(2), x4036_x4793), x4032_x4787_data_fifo.load), op=FixMul, results=List(CU.temp(stage(3), tr1253)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1252), CU.temp(stage(3), tr1253), Const("0i")), op=Mux, results=List(CU.vecOut(stage(4), bus_1255_vector)))
    }
    val x4813_2 = StreamPipeline(name = "x4813_2", parent=x4813, deps=List(x4813_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4778 = CounterChain.copy(x4813, "x4778")
      val bus_1255_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1255_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_1255_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr1257) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr1257), op=Bypass, results=List(CU.scalarOut(stage(2), x4770_scalar)))
    }
    val x4845 = StreamController(name = "x4845", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr14 = (Const("0i").out, Const("2000i").out, Const("1i").out) // Counter
      val x4779 = CounterChain(name = "x4779", ctr14)
      var stage: List[Stage] = Nil
    }
    val x4845_0 = StreamPipeline(name = "x4845_0", parent=x4845, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1283 = CU.temp
      val tr1281 = CU.temp
      val tr1280 = CU.temp
      val tr1279 = CU.temp
      val tr1278 = CU.temp
      val tr1276 = CU.temp
      val x4779 = CounterChain.copy(x4845, "x4779")
      val x4033_x4819 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4779(0))).wtPort(x4335_mc.vdata).rdAddr(x4779(0))
      val x4025_x4816 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4779(0))).wtPort(x4245_mc.vdata).rdAddr(x4779(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(Const("0i"), x4025_x4816.load), op=FixLt, results=List(CU.temp(stage(1), tr1276)))
      Stage(stage(2), operands=List(CU.load(stage(1), x4025_x4816), Const("9999i")), op=FixLt, results=List(CU.temp(stage(2), tr1278)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1276), CU.temp(stage(2), tr1278)), op=BitAnd, results=List(CU.temp(stage(3), tr1279)))
      Stage(stage(4), operands=List(Const("0i"), CU.load(stage(3), x4033_x4819)), op=FixLeq, results=List(CU.temp(stage(4), tr1280)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1279), CU.temp(stage(4), tr1280)), op=BitAnd, results=List(CU.temp(stage(5), tr1281)))
      Stage(stage(6), operands=List(CU.load(stage(5), x4033_x4819), Const("9999i")), op=FixLeq, results=List(CU.temp(stage(6), tr1283)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1281), CU.temp(stage(6), tr1283)), op=BitAnd, results=List(CU.vecOut(stage(7), bus_1284_vector)))
      Stage(stage(8), operands=List(CU.load(stage(7), x4033_x4819)), op=Bypass, results=List(CU.vecOut(stage(8), x4033_x4819_data_vector)))
    }
    val x4845_1 = StreamPipeline(name = "x4845_1", parent=x4845, deps=List(x4845_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1288 = CU.temp
      val tr1287 = CU.temp
      val tr1286 = CU.temp
      val x4779 = CounterChain.copy(x4845, "x4779")
      val x4029_x4822 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4779(0))).wtPort(x4290_mc.vdata).rdAddr(x4779(0))
      val x4037_x4825 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4779(0))).wtPort(x4380_mc.vdata).rdAddr(x4779(0))
      val bus_1284_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1284_vector)
      val x4033_x4819_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4033_x4819_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(x4029_x4822.load, Const("24i")), op=FixLt, results=List(CU.temp(stage(1), tr1286)))
      Stage(stage(2), operands=List(bus_1284_fifo.load, CU.temp(stage(1), tr1286)), op=BitAnd, results=List(CU.temp(stage(2), tr1287)))
      Stage(stage(3), operands=List(CU.load(stage(2), x4037_x4825), x4033_x4819_data_fifo.load), op=FixMul, results=List(CU.temp(stage(3), tr1288)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1287), CU.temp(stage(3), tr1288), Const("0i")), op=Mux, results=List(CU.vecOut(stage(4), bus_1290_vector)))
    }
    val x4845_2 = StreamPipeline(name = "x4845_2", parent=x4845, deps=List(x4845_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4779 = CounterChain.copy(x4845, "x4779")
      val bus_1290_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1290_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_1290_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr1292) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr1292), op=Bypass, results=List(CU.scalarOut(stage(2), x4771_scalar)))
    }
    val x4877 = StreamController(name = "x4877", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr23 = (Const("0i").out, Const("2000i").out, Const("1i").out) // Counter
      val x4780 = CounterChain(name = "x4780", ctr23)
      var stage: List[Stage] = Nil
    }
    val x4877_0 = StreamPipeline(name = "x4877_0", parent=x4877, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1318 = CU.temp
      val tr1316 = CU.temp
      val tr1315 = CU.temp
      val tr1314 = CU.temp
      val tr1313 = CU.temp
      val tr1311 = CU.temp
      val x4780 = CounterChain.copy(x4877, "x4780")
      val x4026_x4848 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4780(0))).wtPort(x4427_mc.vdata).rdAddr(x4780(0))
      val x4034_x4851 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4780(0))).wtPort(x4517_mc.vdata).rdAddr(x4780(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(Const("0i"), x4026_x4848.load), op=FixLt, results=List(CU.temp(stage(1), tr1311)))
      Stage(stage(2), operands=List(CU.load(stage(1), x4026_x4848), Const("9999i")), op=FixLt, results=List(CU.temp(stage(2), tr1313)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1311), CU.temp(stage(2), tr1313)), op=BitAnd, results=List(CU.temp(stage(3), tr1314)))
      Stage(stage(4), operands=List(Const("0i"), CU.load(stage(3), x4034_x4851)), op=FixLeq, results=List(CU.temp(stage(4), tr1315)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1314), CU.temp(stage(4), tr1315)), op=BitAnd, results=List(CU.temp(stage(5), tr1316)))
      Stage(stage(6), operands=List(CU.load(stage(5), x4034_x4851), Const("9999i")), op=FixLeq, results=List(CU.temp(stage(6), tr1318)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1316), CU.temp(stage(6), tr1318)), op=BitAnd, results=List(CU.vecOut(stage(7), bus_1319_vector)))
      Stage(stage(8), operands=List(CU.load(stage(7), x4034_x4851)), op=Bypass, results=List(CU.vecOut(stage(8), x4034_x4851_data_vector)))
    }
    val x4877_1 = StreamPipeline(name = "x4877_1", parent=x4877, deps=List(x4877_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1323 = CU.temp
      val tr1322 = CU.temp
      val tr1321 = CU.temp
      val x4780 = CounterChain.copy(x4877, "x4780")
      val x4030_x4854 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4780(0))).wtPort(x4472_mc.vdata).rdAddr(x4780(0))
      val x4038_x4857 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4780(0))).wtPort(x4562_mc.vdata).rdAddr(x4780(0))
      val bus_1319_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1319_vector)
      val x4034_x4851_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4034_x4851_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(x4030_x4854.load, Const("24i")), op=FixLt, results=List(CU.temp(stage(1), tr1321)))
      Stage(stage(2), operands=List(bus_1319_fifo.load, CU.temp(stage(1), tr1321)), op=BitAnd, results=List(CU.temp(stage(2), tr1322)))
      Stage(stage(3), operands=List(CU.load(stage(2), x4038_x4857), x4034_x4851_data_fifo.load), op=FixMul, results=List(CU.temp(stage(3), tr1323)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1322), CU.temp(stage(3), tr1323), Const("0i")), op=Mux, results=List(CU.vecOut(stage(4), bus_1325_vector)))
    }
    val x4877_2 = StreamPipeline(name = "x4877_2", parent=x4877, deps=List(x4877_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4780 = CounterChain.copy(x4877, "x4780")
      val bus_1325_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1325_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_1325_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr1327) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr1327), op=Bypass, results=List(CU.scalarOut(stage(2), x4772_scalar)))
    }
    val x4909 = StreamController(name = "x4909", parent=x4925, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val ctr32 = (Const("0i").out, Const("2000i").out, Const("1i").out) // Counter
      val x4781 = CounterChain(name = "x4781", ctr32)
      var stage: List[Stage] = Nil
    }
    val x4909_0 = StreamPipeline(name = "x4909_0", parent=x4909, deps=List()) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1353 = CU.temp
      val tr1351 = CU.temp
      val tr1350 = CU.temp
      val tr1349 = CU.temp
      val tr1348 = CU.temp
      val tr1346 = CU.temp
      val x4781 = CounterChain.copy(x4909, "x4781")
      val x4035_x4883 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4781(0))).wtPort(x4699_mc.vdata).rdAddr(x4781(0))
      val x4027_x4880 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4781(0))).wtPort(x4609_mc.vdata).rdAddr(x4781(0))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(8)
      Stage(stage(1), operands=List(Const("0i"), x4027_x4880.load), op=FixLt, results=List(CU.temp(stage(1), tr1346)))
      Stage(stage(2), operands=List(CU.load(stage(1), x4027_x4880), Const("9999i")), op=FixLt, results=List(CU.temp(stage(2), tr1348)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1346), CU.temp(stage(2), tr1348)), op=BitAnd, results=List(CU.temp(stage(3), tr1349)))
      Stage(stage(4), operands=List(Const("0i"), CU.load(stage(3), x4035_x4883)), op=FixLeq, results=List(CU.temp(stage(4), tr1350)))
      Stage(stage(5), operands=List(CU.temp(stage(4), tr1349), CU.temp(stage(4), tr1350)), op=BitAnd, results=List(CU.temp(stage(5), tr1351)))
      Stage(stage(6), operands=List(CU.load(stage(5), x4035_x4883), Const("9999i")), op=FixLeq, results=List(CU.temp(stage(6), tr1353)))
      Stage(stage(7), operands=List(CU.temp(stage(6), tr1351), CU.temp(stage(6), tr1353)), op=BitAnd, results=List(CU.vecOut(stage(7), bus_1354_vector)))
      Stage(stage(8), operands=List(CU.load(stage(7), x4035_x4883)), op=Bypass, results=List(CU.vecOut(stage(8), x4035_x4883_data_vector)))
    }
    val x4909_1 = StreamPipeline(name = "x4909_1", parent=x4909, deps=List(x4909_0)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1358 = CU.temp
      val tr1357 = CU.temp
      val tr1356 = CU.temp
      val x4781 = CounterChain.copy(x4909, "x4781")
      val x4039_x4889 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4781(0))).wtPort(x4744_mc.vdata).rdAddr(x4781(0))
      val x4031_x4886 = SemiFIFO(size = 2000, banking = Strided(1), buffering = MultiBuffer(2, swapRead = x4781(0))).wtPort(x4654_mc.vdata).rdAddr(x4781(0))
      val bus_1354_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1354_vector)
      val x4035_x4883_data_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(x4035_x4883_data_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(x4031_x4886.load, Const("24i")), op=FixLt, results=List(CU.temp(stage(1), tr1356)))
      Stage(stage(2), operands=List(bus_1354_fifo.load, CU.temp(stage(1), tr1356)), op=BitAnd, results=List(CU.temp(stage(2), tr1357)))
      Stage(stage(3), operands=List(CU.load(stage(2), x4039_x4889), x4035_x4883_data_fifo.load), op=FixMul, results=List(CU.temp(stage(3), tr1358)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1357), CU.temp(stage(3), tr1358), Const("0i")), op=Mux, results=List(CU.vecOut(stage(4), bus_1360_vector)))
    }
    val x4909_2 = StreamPipeline(name = "x4909_2", parent=x4909, deps=List(x4909_1)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4781 = CounterChain.copy(x4909, "x4781")
      val bus_1360_fifo = FIFO(size = 4096, banking = Strided(1)).wtPort(bus_1360_vector)
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(2)
      Stage(stage(1), operands=List(bus_1360_fifo.load), op=Bypass, results=List(CU.reduce(stage(1))))
      val (rs1, rr1362) = Stage.reduce(op=FixAdd, init=Const("0i"))
      Stage(stage(2), operands=List(rr1362), op=Bypass, results=List(CU.scalarOut(stage(2), x4773_scalar)))
    }
    val x4923_0 = UnitPipeline(name = "x4923_0", parent=x4925, deps=List(x4813, x4845, x4877, x4909)) { implicit CU => 
      val stage0 = CU.emptyStage
      val tr1372 = CU.temp
      val tr1371 = CU.temp
      val tr1370 = CU.temp
      val ar1369 = CU.accum(init = Const("0i"))
      val x4923_unitcc = CounterChain(name = "x4923_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
      stage = stage0 +: Stages(4)
      Stage(stage(1), operands=List(CU.scalarIn(stage(0), x4770_scalar), CU.scalarIn(stage(0), x4771_scalar)), op=FixAdd, results=List(CU.temp(stage(1), tr1370)))
      Stage(stage(2), operands=List(CU.scalarIn(stage(1), x4772_scalar), CU.scalarIn(stage(1), x4773_scalar)), op=FixAdd, results=List(CU.temp(stage(2), tr1371)))
      Stage(stage(3), operands=List(CU.temp(stage(2), tr1370), CU.temp(stage(2), tr1371)), op=FixAdd, results=List(CU.temp(stage(3), tr1372)))
      Stage(stage(4), operands=List(CU.temp(stage(3), tr1372), CU.accum(stage(4), ar1369)), op=FixAdd, results=List(CU.scalarOut(stage(4), x4014_argout), CU.accum(stage(4), ar1369)))
    }
    val x4925_leaf = UnitPipeline(name = "x4925_leaf", parent=x4925, deps=List(x4448, x4538, x4356, x4311, x4630, x4583, x4129, x4401, x4923_0, x4266, x4174, x4219, x4675, x4765, x4493, x4084, x4720)) { implicit CU => 
      val stage0 = CU.emptyStage
      val x4021 = CounterChain.copy(x4925, "x4021")
      val x4925_unitcc = CounterChain(name = "x4925_unitcc", (Const("0i"), Const("1i"), Const("1i")))
      var stage: List[Stage] = Nil
    }
    
  }
}
