import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object PageRank extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x3909_x3918_s = Scalar("x3909_x3918")
    val x4155_b4283_x4165_b4285_s = Scalar("x4155_b4283_x4165_b4285")
    val x3925_argin = ArgIn("x3925")
    val x3923_b4259_x3952_b4267_s = Scalar("x3923_b4259_x3952_b4267")
    val x3816_oc = OffChip("x3816")
    val bus_551_s = Scalar("bus_551")
    val x3864_x3874_data_v = Vector("x3864_x3874_data")
    val x3989_x4019_data_v = Vector("x3989_x4019_data")
    val x3922_b4256_x3945_b4264_s = Scalar("x3922_b4256_x3945_b4264")
    val x4115_x4142_s = Scalar("x4115_x4142")
    val x4090_x4104_data_v = Vector("x4090_x4104_data")
    val x3885_x3895_data_v = Vector("x3885_x3895_data")
    val x3863_b4249_x3872_b4251_s = Scalar("x3863_b4249_x3872_b4251")
    val x4022_x4031_s = Scalar("x4022_x4031")
    val x3956_x3964_s = Scalar("x3956_x3964")
    val x3841_x3917_x3919_v = Vector("x3841_x3917_x3919")
    val bus_517_s = Scalar("bus_517")
    val x3923_b4258_x3952_b4266_s = Scalar("x3923_b4258_x3952_b4266")
    val bus_543_s = Scalar("bus_543")
    val x3820_oc = OffChip("x3820")
    val x3807_argin = ArgIn("x3807")
    val x3922_b4257_x3945_b4265_s = Scalar("x3922_b4257_x3945_b4265")
    val x3957_x3966_s = Scalar("x3957_x3966")
    val x4053_x4123_x4088_v = Vector("x4053_x4123_x4088")
    val x3842_b4245_x3851_b4247_s = Scalar("x3842_b4245_x3851_b4247")
    val x3908_x3913_s = Scalar("x3908_x3913")
    val x3843_x3853_data_v = Vector("x3843_x3853_data")
    val x3818_oc = OffChip("x3818")
    val x3988_b4272_x4017_b4280_s = Scalar("x3988_b4272_x4017_b4280")
    val x3955_x3962_s = Scalar("x3955_x3962")
    val x3920_x4061_x4074_v = Vector("x3920_x4061_x4074")
    val x4053_x4073_v = Vector("x4053_x4073")
    val x4158_argin = ArgIn("x4158")
    val x3884_b4252_x3893_b4254_s = Scalar("x3884_b4252_x3893_b4254")
    val bus_528_s = Scalar("bus_528")
    val x4155_b4282_x4165_b4284_s = Scalar("x4155_b4282_x4165_b4284")
    val x3987_b4269_x4010_b4277_s = Scalar("x3987_b4269_x4010_b4277")
    val x3924_x3954_data_v = Vector("x3924_x3954_data")
    val x3839_x4124_x4144_v = Vector("x3839_x4124_x4144")
    val x4089_x4102_s = Scalar("x4089_x4102")
    val x4053_x4082_x4075_v = Vector("x4053_x4082_x4075")
    val x3884_b4253_x3893_b4255_s = Scalar("x3884_b4253_x3893_b4255")
    val bus_519_s = Scalar("bus_519")
    val x3844_argin = ArgIn("x3844")
    val bus_542_s = Scalar("bus_542")
    val bus_544_s = Scalar("bus_544")
    val x4021_x4029_s = Scalar("x4021_x4029")
    val x3920_x4122_x4144_v = Vector("x3920_x4122_x4144")
    val x3808_argin = ArgIn("x3808")
    val bus_521_s = Scalar("bus_521")
    val x4020_x4027_s = Scalar("x4020_x4027")
    val x3988_b4273_x4017_b4281_s = Scalar("x3988_b4273_x4017_b4281")
    val x3990_argin = ArgIn("x3990")
    val x3838_x4171_x4175_v = Vector("x3838_x4171_x4175")
    val x4097_argin = ArgIn("x4097")
    val bus_520_s = Scalar("bus_520")
    val x3988_b4271_x4017_b4279_s = Scalar("x3988_b4271_x4017_b4279")
    val x3842_b4244_x3851_b4246_s = Scalar("x3842_b4244_x3851_b4246")
    val x3838_x4125_x4144_v = Vector("x3838_x4125_x4144")
    val x3814_oc = OffChip("x3814")
    val x3865_argin = ArgIn("x3865")
    val x3838_x4152_v = Vector("x3838_x4152")
    val x3921_x4128_x4144_v = Vector("x3921_x4128_x4144")
    val x3863_b4248_x3872_b4250_s = Scalar("x3863_b4248_x3872_b4250")
    val x3923_b4260_x3952_b4268_s = Scalar("x3923_b4260_x3952_b4268")
    val x3809_argin = ArgIn("x3809")
    val x3840_x3912_x3914_v = Vector("x3840_x3912_x3914")
    val bus_607_s = Scalar("bus_607")
    val x3987_b4270_x4010_b4278_s = Scalar("x3987_b4270_x4010_b4278")
    val bus_540_s = Scalar("bus_540")
    val x4075_x4096_x4103_v = Vector("x4075_x4096_x4103")
    val x3920_x4083_x4087_v = Vector("x3920_x4083_x4087")
    val x4088_x4127_x4144_v = Vector("x4088_x4127_x4144")
    val bus_602_s = Scalar("bus_602")
    val x3886_argin = ArgIn("x3886")
    val x3815_oc = OffChip("x3815")
    val x4188 = Sequential(name="x4188",parent=top) { implicit CU => 
    }
    val x4187 = Sequential(name="x4187",parent=x4188) { implicit CU => 
      val x3807_x3832 =  ScalarBuffer().wtPort(x3807_argin)
      val ctr1 = Counter(min=Const(0), max=x3807_x3832.load, step=Const(1), par=1) // Counter
      val x3834 = CounterChain(name = "x3834", ctr1).iter(1)
    }
    val x4186 = Sequential(name="x4186",parent=x4187) { implicit CU => 
      val x3808_x3835 =  ScalarBuffer().wtPort(x3808_argin)
      val ctr2 = Counter(min=Const(0), max=x3808_x3835.load, step=Const(768), par=1) // Counter
      val x3837 = CounterChain(name = "x3837", ctr2).iter(1)
    }
    val x3838_dsp0 = MemoryPipeline(name="x3838_dsp0",parent="x4186") { implicit CU => 
      val x4152_x4152 =  VectorFIFO(size=1).wtPort(x3838_x4152_v)
      val x3907 = CounterChain.copy("x4154", "x3907")
      val x4168 = CounterChain.copy("x4175", "x4168")
      val x3838_x4171 =  SRAM(size=768,banking = NoBanking()).wtPort(x4152_x4152.readPort).rdPort(x3838_x4171_x4175_v).rdAddr(x4168(0)).wtAddr(x3907(0))
      var stage: List[Stage] = Nil
    }
    val x3838_dsp1 = MemoryPipeline(name="x3838_dsp1",parent="x4186") { implicit CU => 
      val x4152_x4152 =  VectorFIFO(size=1).wtPort(x3838_x4152_v)
      val x3907 = CounterChain.copy("x4154", "x3907")
      val x3838_x4125 =  SRAM(size=768,banking = NoBanking()).wtPort(x4152_x4152.readPort).rdPort(x3838_x4125_x4144_v).rdAddr(x3907(0)).wtAddr(x3907(0))
      var stage: List[Stage] = Nil
    }
    val x3839_dsp0 = MemoryPipeline(name="x3839_dsp0",parent="x4186") { implicit CU => 
      val x3860_x3860 =  VectorFIFO(size=1).wtPort(x3843_x3853_data_v)
      val x3855 = CounterChain.copy("x3861", "x3855")
      val x3907 = CounterChain.copy("x4154", "x3907")
      val x3839_x4124 =  SRAM(size=768,banking = NoBanking()).wtPort(x3860_x3860.readPort).rdPort(x3839_x4124_x4144_v).rdAddr(x3907(0)).wtAddr(x3855(0))
      var stage: List[Stage] = Nil
    }
    val x3840_dsp0 = MemoryPipeline(name="x3840_dsp0",parent="x4186") { implicit CU => 
      val x3881_x3881 =  VectorFIFO(size=1).wtPort(x3864_x3874_data_v)
      val x3876 = CounterChain.copy("x3882", "x3876")
      val x3907 = CounterChain.copy("x4154", "x3907")
      val x3840_x3912 =  SRAM(size=768,banking = NoBanking()).wtPort(x3881_x3881.readPort).rdPort(x3840_x3912_x3914_v).rdAddr(x3907(0)).wtAddr(x3876(0))
      var stage: List[Stage] = Nil
    }
    val x3841_dsp0 = MemoryPipeline(name="x3841_dsp0",parent="x4186") { implicit CU => 
      val x3902_x3902 =  VectorFIFO(size=1).wtPort(x3885_x3895_data_v)
      val x3897 = CounterChain.copy("x3903", "x3897")
      val x3907 = CounterChain.copy("x4154", "x3907")
      val x3841_x3917 =  SRAM(size=768,banking = NoBanking()).wtPort(x3902_x3902.readPort).rdPort(x3841_x3917_x3919_v).rdAddr(x3907(0)).wtAddr(x3897(0))
      var stage: List[Stage] = Nil
    }
    val x3862 = StreamController(name="x3862",parent=x4186) { implicit CU => 
    }
    val x3852_0 = Pipeline(name="x3852_0",parent=x3862) { implicit CU => 
      val x3845 = CU.temp
      val x3844 =  ScalarBuffer().wtPort(x3844_argin)
      val x3837 = CounterChain.copy("x4186", "x3837")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3837(0)), Const(4)), op=FixMul, results=List(x3845))
      Stage(operands=List(x3845, CU.load(x3844)), op=FixAdd, results=List(CU.scalarOut(x3842_b4244_x3851_b4246_s)))
      Stage(operands=List(Const(3072)), op=Bypass, results=List(CU.scalarOut(x3842_b4245_x3851_b4247_s)))
    }
    val x3853 = MemoryController(name="x3853",parent=x3862,offchip=x3814_oc, mctpe=TileLoad) { implicit CU => 
      val x3842_b4245_x3853 =  ScalarFIFO(name="size",size=1).wtPort(x3842_b4245_x3851_b4247_s)
      val x3842_b4244_x3853 =  ScalarFIFO(name="offset",size=1).wtPort(x3842_b4244_x3851_b4246_s)
      CU.newVout("data", x3843_x3853_data_v)
    }
    val x3861 = Pipeline(name="x3861",parent=x3862) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3855 = CounterChain(name = "x3855", ctr3).iter(768)
      var stage: List[Stage] = Nil
    }
    val x3883 = StreamController(name="x3883",parent=x4186) { implicit CU => 
    }
    val x3873_0 = Pipeline(name="x3873_0",parent=x3883) { implicit CU => 
      val x3866 = CU.temp
      val x3865 =  ScalarBuffer().wtPort(x3865_argin)
      val x3837 = CounterChain.copy("x4186", "x3837")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3837(0)), Const(4)), op=FixMul, results=List(x3866))
      Stage(operands=List(x3866, CU.load(x3865)), op=FixAdd, results=List(CU.scalarOut(x3863_b4248_x3872_b4250_s)))
      Stage(operands=List(Const(3072)), op=Bypass, results=List(CU.scalarOut(x3863_b4249_x3872_b4251_s)))
    }
    val x3874 = MemoryController(name="x3874",parent=x3883,offchip=x3818_oc, mctpe=TileLoad) { implicit CU => 
      val x3863_b4249_x3874 =  ScalarFIFO(name="size",size=1).wtPort(x3863_b4249_x3872_b4251_s)
      val x3863_b4248_x3874 =  ScalarFIFO(name="offset",size=1).wtPort(x3863_b4248_x3872_b4250_s)
      CU.newVout("data", x3864_x3874_data_v)
    }
    val x3882 = Pipeline(name="x3882",parent=x3883) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3876 = CounterChain(name = "x3876", ctr4).iter(768)
      var stage: List[Stage] = Nil
    }
    val x3904 = StreamController(name="x3904",parent=x4186) { implicit CU => 
    }
    val x3894_0 = Pipeline(name="x3894_0",parent=x3904) { implicit CU => 
      val x3887 = CU.temp
      val x3886 =  ScalarBuffer().wtPort(x3886_argin)
      val x3837 = CounterChain.copy("x4186", "x3837")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3837(0)), Const(4)), op=FixMul, results=List(x3887))
      Stage(operands=List(x3887, CU.load(x3886)), op=FixAdd, results=List(CU.scalarOut(x3884_b4252_x3893_b4254_s)))
      Stage(operands=List(Const(3072)), op=Bypass, results=List(CU.scalarOut(x3884_b4253_x3893_b4255_s)))
    }
    val x3895 = MemoryController(name="x3895",parent=x3904,offchip=x3820_oc, mctpe=TileLoad) { implicit CU => 
      val x3884_b4252_x3895 =  ScalarFIFO(name="offset",size=1).wtPort(x3884_b4252_x3893_b4254_s)
      val x3884_b4253_x3895 =  ScalarFIFO(name="size",size=1).wtPort(x3884_b4253_x3893_b4255_s)
      CU.newVout("data", x3885_x3895_data_v)
    }
    val x3903 = Pipeline(name="x3903",parent=x3904) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3897 = CounterChain(name = "x3897", ctr5).iter(768)
      var stage: List[Stage] = Nil
    }
    val x4154 = Sequential(name="x4154",parent=x4186) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x3907 = CounterChain(name = "x3907", ctr6).iter(768)
    }
    val x3914_0 = Pipeline(name="x3914_0",parent=x4154) { implicit CU => 
      val x3912_x3912 =  VectorFIFO(size=1).wtPort(x3840_x3912_x3914_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3912_x3912)), op=Bypass, results=List(CU.scalarOut(x3908_x3913_s)))
    }
    val x3919_0 = Pipeline(name="x3919_0",parent=x4154) { implicit CU => 
      val x3917_x3917 =  VectorFIFO(size=1).wtPort(x3841_x3917_x3919_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3917_x3917)), op=Bypass, results=List(CU.scalarOut(x3909_x3918_s)))
    }
    val x3920_dsp0 = MemoryPipeline(name="x3920_dsp0",parent="x4154") { implicit CU => 
      val x3955_x3971 =  ScalarBuffer().wtPort(x3955_x3962_s)
      val x3983_x3983 =  VectorFIFO(size=1).wtPort(x3924_x3954_data_v)
      val x3970 = CounterChain.copy("x3984", "x3970")
      val x4118 = CounterChain.copy("x4144", "x4118")
      val x3920_x4122 =  SRAM(size=768,banking = NoBanking()).wtPort(x3983_x3983.readPort).rdPort(x3920_x4122_x4144_v).rdAddr(x4118(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3970(0)), CU.load(x3955_x3971)), op=FixSub, results=List(x3920_x4122.writeAddr))
    }
    val x3920_dsp1 = MemoryPipeline(name="x3920_dsp1",parent="x4154") { implicit CU => 
      val x3955_x3971 =  ScalarBuffer().wtPort(x3955_x3962_s)
      val x3983_x3983 =  VectorFIFO(size=1).wtPort(x3924_x3954_data_v)
      val x3970 = CounterChain.copy("x3984", "x3970")
      val x4078 = CounterChain.copy("x4087", "x4078")
      val x3920_x4083 =  SRAM(size=768,banking = NoBanking()).wtPort(x3983_x3983.readPort).rdPort(x3920_x4083_x4087_v).rdAddr(x4078(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3970(0)), CU.load(x3955_x3971)), op=FixSub, results=List(x3920_x4083.writeAddr))
    }
    val x3920_dsp2 = MemoryPipeline(name="x3920_dsp2",parent="x4154") { implicit CU => 
      val x3955_x3971 =  ScalarBuffer().wtPort(x3955_x3962_s)
      val x3983_x3983 =  VectorFIFO(size=1).wtPort(x3924_x3954_data_v)
      val x3970 = CounterChain.copy("x3984", "x3970")
      val x4057 = CounterChain.copy("x4074_0", "x4057")
      val x3920_x4061 =  SRAM(size=768,banking = NoBanking()).wtPort(x3983_x3983.readPort).rdPort(x3920_x4061_x4074_v).rdAddr(x4057(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x3970(0)), CU.load(x3955_x3971)), op=FixSub, results=List(x3920_x4061.writeAddr))
    }
    val x3921_dsp0 = MemoryPipeline(name="x3921_dsp0",parent="x4154") { implicit CU => 
      val x4048_x4048 =  VectorFIFO(size=1).wtPort(x3989_x4019_data_v)
      val x4020_x4036 =  ScalarBuffer().wtPort(x4020_x4027_s)
      val x4118 = CounterChain.copy("x4144", "x4118")
      val x4035 = CounterChain.copy("x4049", "x4035")
      val x3921_x4128 =  SRAM(size=768,banking = NoBanking()).wtPort(x4048_x4048.readPort).rdPort(x3921_x4128_x4144_v).rdAddr(x4118(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x4035(0)), CU.load(x4020_x4036)), op=FixSub, results=List(x3921_x4128.writeAddr))
    }
    val x3986 = StreamController(name="x3986",parent=x4154) { implicit CU => 
    }
    val x3953 = StreamController(name="x3953",parent=x3986) { implicit CU => 
    }
    val x3953_0 = Pipeline(name="x3953_0",parent=x3953) { implicit CU => 
      val x3928 = CU.temp
      val x3935 = CU.temp
      val x3908_x3926 =  ScalarBuffer().wtPort(x3908_x3913_s)
      val x3909_x3927 =  ScalarBuffer().wtPort(x3909_x3918_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3908_x3926), Const(4)), op=FixMul, results=List(x3928, CU.scalarOut(bus_517_s)))
      Stage(operands=List(x3928, Const(64)), op=FixMod, results=List(x3935, CU.scalarOut(bus_519_s)))
      Stage(operands=List(x3935, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_520_s), CU.scalarOut(x3923_b4259_x3952_b4267_s)))
      Stage(operands=List(CU.load(x3909_x3927), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_521_s)))
    }
    val x3953_1 = Pipeline(name="x3953_1",parent=x3953) { implicit CU => 
      val x3931 = CU.temp
      val x3930 = CU.temp
      val x3933 = CU.temp
      val x3932 = CU.temp
      val x3928 =  ScalarFIFO(size=1).wtPort(bus_517_s)
      val x3929 =  ScalarFIFO(size=1).wtPort(bus_521_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3928), CU.load(x3929)), op=FixAdd, results=List(x3930))
      Stage(operands=List(x3930, Const(4)), op=FixDiv, results=List(CU.scalarOut(x3923_b4260_x3952_b4268_s)))
      Stage(operands=List(x3930, Const(64)), op=FixMod, results=List(x3931))
      Stage(operands=List(x3931, Const(0)), op=FixEql, results=List(x3932))
      Stage(operands=List(Const(64), x3931), op=FixSub, results=List(x3933))
      Stage(operands=List(x3932, Const(0), x3933), op=Mux, results=List(CU.scalarOut(bus_528_s)))
    }
    val x3953_2 = Pipeline(name="x3953_2",parent=x3953) { implicit CU => 
      val x3936 = CU.temp
      val x3949 = CU.temp
      val x3948 = CU.temp
      val x3929 =  ScalarFIFO(size=1).wtPort(bus_521_s)
      val x3934 =  ScalarFIFO(size=1).wtPort(bus_528_s)
      val x3946 =  ScalarFIFO(size=1).wtPort(bus_520_s)
      val x3935 =  ScalarFIFO(size=1).wtPort(bus_519_s)
      val x3909_x3927 =  ScalarBuffer().wtPort(x3909_x3918_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3934), Const(4)), op=FixDiv, results=List(x3948))
      Stage(operands=List(CU.load(x3909_x3927), CU.load(x3946)), op=FixAdd, results=List(x3949))
      Stage(operands=List(x3949, x3948), op=FixAdd, results=List(CU.scalarOut(x3923_b4258_x3952_b4266_s)))
      Stage(operands=List(CU.load(x3929), CU.load(x3935)), op=FixAdd, results=List(x3936))
      Stage(operands=List(x3936, CU.load(x3934)), op=FixAdd, results=List(CU.scalarOut(x3922_b4257_x3945_b4265_s)))
    }
    val x3953_3 = Pipeline(name="x3953_3",parent=x3953) { implicit CU => 
      val x3938 = CU.temp
      val x3935 =  ScalarFIFO(size=1).wtPort(bus_519_s)
      val x3928 =  ScalarFIFO(size=1).wtPort(bus_517_s)
      val x3925 =  ScalarBuffer().wtPort(x3925_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3928), CU.load(x3935)), op=FixSub, results=List(x3938))
      Stage(operands=List(x3938, CU.load(x3925)), op=FixAdd, results=List(CU.scalarOut(x3922_b4256_x3945_b4264_s)))
    }
    val x3954 = MemoryController(name="x3954",parent=x3986,offchip=x3815_oc, mctpe=TileLoad) { implicit CU => 
      val x3922_b4257_x3954 =  ScalarFIFO(name="size",size=1).wtPort(x3922_b4257_x3945_b4265_s)
      val x3922_b4256_x3954 =  ScalarFIFO(name="offset",size=1).wtPort(x3922_b4256_x3945_b4264_s)
      CU.newVout("data", x3924_x3954_data_v)
    }
    val x3985 = Sequential(name="x3985",parent=x3986) { implicit CU => 
    }
    val x3967_0 = Pipeline(name="x3967_0",parent=x3985) { implicit CU => 
      val x3923_b4258_x3960_b4261 =  ScalarFIFO(size=16).wtPort(x3923_b4258_x3952_b4266_s)
      val x3923_b4260_x3960_b4263 =  ScalarFIFO(size=16).wtPort(x3923_b4260_x3952_b4268_s)
      val x3923_b4259_x3960_b4262 =  ScalarFIFO(size=16).wtPort(x3923_b4259_x3952_b4267_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3923_b4259_x3960_b4262)), op=Bypass, results=List(CU.scalarOut(x3955_x3962_s)))
      Stage(operands=List(CU.load(x3923_b4260_x3960_b4263)), op=Bypass, results=List(CU.scalarOut(x3956_x3964_s)))
      Stage(operands=List(CU.load(x3923_b4258_x3960_b4261)), op=Bypass, results=List(CU.scalarOut(x3957_x3966_s)))
    }
    val x3984 = Pipeline(name="x3984",parent=x3985) { implicit CU => 
      val x3956_x3972 =  ScalarBuffer().wtPort(x3956_x3964_s)
      val x3955_x3971 =  ScalarBuffer().wtPort(x3955_x3962_s)
      val x3957_x3968 =  ScalarBuffer().wtPort(x3957_x3966_s)
      val ctr7 = Counter(min=Const(0), max=x3957_x3968.load, step=Const(1), par=1) // Counter
      val x3970 = CounterChain(name = "x3970", ctr7).iter(1)
      var stage: List[Stage] = Nil
    }
    val x4051 = StreamController(name="x4051",parent=x4154) { implicit CU => 
    }
    val x4018 = StreamController(name="x4018",parent=x4051) { implicit CU => 
    }
    val x4018_0 = Pipeline(name="x4018_0",parent=x4018) { implicit CU => 
      val x3993 = CU.temp
      val x4000 = CU.temp
      val x3908_x3991 =  ScalarBuffer().wtPort(x3908_x3913_s)
      val x3909_x3992 =  ScalarBuffer().wtPort(x3909_x3918_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3908_x3991), Const(4)), op=FixMul, results=List(x3993, CU.scalarOut(bus_540_s)))
      Stage(operands=List(x3993, Const(64)), op=FixMod, results=List(x4000, CU.scalarOut(bus_542_s)))
      Stage(operands=List(x4000, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_543_s), CU.scalarOut(x3988_b4272_x4017_b4280_s)))
      Stage(operands=List(CU.load(x3909_x3992), Const(4)), op=FixMul, results=List(CU.scalarOut(bus_544_s)))
    }
    val x4018_1 = Pipeline(name="x4018_1",parent=x4018) { implicit CU => 
      val x3995 = CU.temp
      val x3997 = CU.temp
      val x3996 = CU.temp
      val x3998 = CU.temp
      val x3993 =  ScalarFIFO(size=1).wtPort(bus_540_s)
      val x3994 =  ScalarFIFO(size=1).wtPort(bus_544_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3993), CU.load(x3994)), op=FixAdd, results=List(x3995))
      Stage(operands=List(x3995, Const(4)), op=FixDiv, results=List(CU.scalarOut(x3988_b4273_x4017_b4281_s)))
      Stage(operands=List(x3995, Const(64)), op=FixMod, results=List(x3996))
      Stage(operands=List(x3996, Const(0)), op=FixEql, results=List(x3997))
      Stage(operands=List(Const(64), x3996), op=FixSub, results=List(x3998))
      Stage(operands=List(x3997, Const(0), x3998), op=Mux, results=List(CU.scalarOut(bus_551_s)))
    }
    val x4018_2 = Pipeline(name="x4018_2",parent=x4018) { implicit CU => 
      val x4001 = CU.temp
      val x4013 = CU.temp
      val x4014 = CU.temp
      val x3999 =  ScalarFIFO(size=1).wtPort(bus_551_s)
      val x3909_x3992 =  ScalarBuffer().wtPort(x3909_x3918_s)
      val x3994 =  ScalarFIFO(size=1).wtPort(bus_544_s)
      val x4011 =  ScalarFIFO(size=1).wtPort(bus_543_s)
      val x4000 =  ScalarFIFO(size=1).wtPort(bus_542_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3999), Const(4)), op=FixDiv, results=List(x4013))
      Stage(operands=List(CU.load(x3909_x3992), CU.load(x4011)), op=FixAdd, results=List(x4014))
      Stage(operands=List(x4014, x4013), op=FixAdd, results=List(CU.scalarOut(x3988_b4271_x4017_b4279_s)))
      Stage(operands=List(CU.load(x3994), CU.load(x4000)), op=FixAdd, results=List(x4001))
      Stage(operands=List(x4001, CU.load(x3999)), op=FixAdd, results=List(CU.scalarOut(x3987_b4270_x4010_b4278_s)))
    }
    val x4018_3 = Pipeline(name="x4018_3",parent=x4018) { implicit CU => 
      val x4003 = CU.temp
      val x3993 =  ScalarFIFO(size=1).wtPort(bus_540_s)
      val x4000 =  ScalarFIFO(size=1).wtPort(bus_542_s)
      val x3990 =  ScalarBuffer().wtPort(x3990_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3993), CU.load(x4000)), op=FixSub, results=List(x4003))
      Stage(operands=List(x4003, CU.load(x3990)), op=FixAdd, results=List(CU.scalarOut(x3987_b4269_x4010_b4277_s)))
    }
    val x4019 = MemoryController(name="x4019",parent=x4051,offchip=x3816_oc, mctpe=TileLoad) { implicit CU => 
      val x3987_b4270_x4019 =  ScalarFIFO(name="size",size=1).wtPort(x3987_b4270_x4010_b4278_s)
      val x3987_b4269_x4019 =  ScalarFIFO(name="offset",size=1).wtPort(x3987_b4269_x4010_b4277_s)
      CU.newVout("data", x3989_x4019_data_v)
    }
    val x4050 = Sequential(name="x4050",parent=x4051) { implicit CU => 
    }
    val x4032_0 = Pipeline(name="x4032_0",parent=x4050) { implicit CU => 
      val x3988_b4273_x4025_b4276 =  ScalarFIFO(size=16).wtPort(x3988_b4273_x4017_b4281_s)
      val x3988_b4272_x4025_b4275 =  ScalarFIFO(size=16).wtPort(x3988_b4272_x4017_b4280_s)
      val x3988_b4271_x4025_b4274 =  ScalarFIFO(size=16).wtPort(x3988_b4271_x4017_b4279_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x3988_b4272_x4025_b4275)), op=Bypass, results=List(CU.scalarOut(x4020_x4027_s)))
      Stage(operands=List(CU.load(x3988_b4273_x4025_b4276)), op=Bypass, results=List(CU.scalarOut(x4021_x4029_s)))
      Stage(operands=List(CU.load(x3988_b4271_x4025_b4274)), op=Bypass, results=List(CU.scalarOut(x4022_x4031_s)))
    }
    val x4049 = Pipeline(name="x4049",parent=x4050) { implicit CU => 
      val x4022_x4033 =  ScalarBuffer().wtPort(x4022_x4031_s)
      val x4021_x4037 =  ScalarBuffer().wtPort(x4021_x4029_s)
      val x4020_x4036 =  ScalarBuffer().wtPort(x4020_x4027_s)
      val ctr8 = Counter(min=Const(0), max=x4022_x4033.load, step=Const(1), par=1) // Counter
      val x4035 = CounterChain(name = "x4035", ctr8).iter(1)
      var stage: List[Stage] = Nil
    }
    val x4053_dsp0 = MemoryPipeline(name="x4053_dsp0",parent="x4154") { implicit CU => 
      val x4073_x4073 =  VectorFIFO(size=1).wtPort(x4053_x4073_v)
      val x4118 = CounterChain.copy("x4144", "x4118")
      val x4057 = CounterChain.copy("x4074_0", "x4057")
      val x4053_x4123 =  SRAM(size=768,banking = NoBanking()).wtPort(x4073_x4073.readPort).rdPort(x4053_x4123_x4088_v).rdAddr(x4118(0)).wtAddr(x4057(0))
      var stage: List[Stage] = Nil
    }
    val x4053_dsp1 = MemoryPipeline(name="x4053_dsp1",parent="x4154") { implicit CU => 
      val x4073_x4073 =  VectorFIFO(size=1).wtPort(x4053_x4073_v)
      val x4057 = CounterChain.copy("x4074_0", "x4057")
      val x4078 = CounterChain.copy("x4087", "x4078")
      val x4053_x4082 =  SRAM(size=768,banking = NoBanking()).wtPort(x4073_x4073.readPort).rdPort(x4053_x4082_x4075_v).rdAddr(x4078(0)).wtAddr(x4057(0))
      var stage: List[Stage] = Nil
    }
    val x4074_0 = Pipeline(name="x4074_0",parent=x4154) { implicit CU => 
      val x4064 = CU.temp
      val x4067 = CU.temp
      val x4065 = CU.temp
      val x4066 = CU.temp
      val ar186 =  ScalarFIFO(size=1).wtPort(x3909_x3918_s)
      val x3909_x4055 =  ScalarBuffer().wtPort(x3909_x3918_s)
      val x4061_x4061 =  VectorFIFO(size=1).wtPort(x3920_x4061_x4074_v)
      val x3837 = CounterChain.copy("x4186", "x3837")
      val ctr9 = Counter(min=Const(0), max=x3909_x4055.load, step=Const(1), par=1) // Counter
      val x4057 = CounterChain(name = "x4057", ctr9).iter(1)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3837(0)), CU.load(x4061_x4061)), op=FixLeq, results=List(x4064))
      Stage(operands=List(CU.ctr(x3837(0)), Const(768)), op=FixAdd, results=List(x4065))
      Stage(operands=List(CU.load(x4061_x4061), x4065), op=FixLt, results=List(x4066))
      Stage(operands=List(x4064, x4066), op=BitAnd, results=List(x4067))
      Stage(operands=List(x4067, CU.load(ar186), Const(-1)), op=Mux, results=List(CU.vecOut(x4053_x4073_v)))
    }
    val x4075_dsp0 = MemoryPipeline(name="x4075_dsp0",parent="x4154") { implicit CU => 
      val x4086_x4086 =  VectorFIFO(size=1).wtPort(x3920_x4083_x4087_v)
      val x4082_x4082 =  VectorFIFO(size=1).wtPort(x4053_x4082_x4075_v)
      val x4092 = CounterChain.copy("x4103_0", "x4092")
      val x4075_x4096 =  SRAM(size=768,banking = NoBanking()).wtPort(x4086_x4086.readPort).rdPort(x4075_x4096_x4103_v).rdAddr(x4092(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.load(x4082_x4082)), op=Bypass, results=List(x4075_x4096.writeAddr))
    }
    val x4087 = Pipeline(name="x4087",parent=x4154) { implicit CU => 
      val x3909_x4076 =  ScalarBuffer().wtPort(x3909_x3918_s)
      val ctr10 = Counter(min=Const(0), max=x3909_x4076.load, step=Const(1), par=1) // Counter
      val x4078 = CounterChain(name = "x4078", ctr10).iter(1)
      var stage: List[Stage] = Nil
    }
    val x4088_dsp0 = MemoryPipeline(name="x4088_dsp0",parent="x4154") { implicit CU => 
      val x4123_x4123 =  VectorFIFO(size=1).wtPort(x4053_x4123_x4088_v)
      val x4112_x4112 =  VectorFIFO(size=1).wtPort(x4090_x4104_data_v)
      val x4106 = CounterChain.copy("x4113", "x4106")
      val x4088_x4127 =  SRAM(size=768,banking = NoBanking()).wtPort(x4112_x4112.readPort).rdPort(x4088_x4127_x4144_v).wtAddr(x4106(0))
      var stage: List[Stage] = Nil
      RAStage(operands=List(CU.load(x4123_x4123)), op=Bypass, results=List(x4088_x4127.readAddr))
    }
    val x4114 = StreamController(name="x4114",parent=x4154) { implicit CU => 
    }
    val x4103_0 = Pipeline(name="x4103_0",parent=x4114) { implicit CU => 
      val x4099 = CU.temp
      val x4096_x4096 =  VectorFIFO(size=1).wtPort(x4075_x4096_x4103_v)
      val x4097 =  ScalarBuffer().wtPort(x4097_argin)
      val ctr11 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x4092 = CounterChain(name = "x4092", ctr11).iter(768)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4096_x4096), Const(4)), op=FixMul, results=List(x4099))
      Stage(operands=List(x4099, CU.load(x4097)), op=FixAdd, results=List(CU.scalarOut(x4089_x4102_s)))
    }
    val x4104 = MemoryController(name="x4104",parent=x4114,offchip=x3814_oc, mctpe=Gather) { implicit CU => 
      val x4089_x4104 =  ScalarFIFO(name="addr",size=1).wtPort(x4089_x4102_s)
      CU.newVout("data", x4090_x4104_data_v)
    }
    val x4113 = Pipeline(name="x4113",parent=x4114) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x4106 = CounterChain(name = "x4106", ctr12).iter(768)
      var stage: List[Stage] = Nil
    }
    val x4144 = StreamController(name="x4144",parent=x4154) { implicit CU => 
      val ctr13 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x4118 = CounterChain(name = "x4118", ctr13).iter(1)
    }
    val x4144_0 = Pipeline(name="x4144_0",parent=x4144) { implicit CU => 
      val x4134 = CU.temp
      val x4133 = CU.temp
      val x4122_x4122 =  VectorFIFO(size=1).wtPort(x3920_x4122_x4144_v)
      val x4125_x4125 =  VectorFIFO(size=1).wtPort(x3838_x4125_x4144_v)
      val x4124_x4124 =  VectorFIFO(size=1).wtPort(x3839_x4124_x4144_v)
      val x3907 = CounterChain.copy("x4154", "x3907")
      val x3837 = CounterChain.copy("x4186", "x3837")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4122_x4122), CU.ctr(x3837(0))), op=FixSub, results=List(x4133))
      Stage(operands=List(x4133, CU.ctr(x3907(0))), op=FixLeq, results=List(x4134))
      Stage(operands=List(x4134, CU.load(x4124_x4124), CU.load(x4125_x4125)), op=Mux, results=List(CU.scalarOut(bus_602_s)))
    }
    val x4144_1 = Pipeline(name="x4144_1",parent=x4144) { implicit CU => 
      val x4136 = CU.temp
      val x4137 = CU.temp
      val x4135 =  ScalarFIFO(size=1).wtPort(bus_602_s)
      val x4123_x4123 =  VectorFIFO(size=1).wtPort(x3839_x4124_x4144_v)
      val x4128_x4128 =  VectorFIFO(size=1).wtPort(x3921_x4128_x4144_v)
      val x4127_x4127 =  VectorFIFO(size=1).wtPort(x4088_x4127_x4144_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4123_x4123), Const(-1)), op=FixEql, results=List(x4136))
      Stage(operands=List(x4136, CU.load(x4135), CU.load(x4127_x4127)), op=Mux, results=List(x4137))
      Stage(operands=List(x4137, CU.load(x4128_x4128)), op=FltDiv, results=List(CU.scalarOut(bus_607_s)))
    }
    val x4144_2 = Pipeline(name="x4144_2",parent=x4144) { implicit CU => 
      val rr607 =  ScalarFIFO(size=1).wtPort(bus_607_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(rr607)), op=Bypass, results=List(CU.reduce))
      val (_, rr609) = Stage.reduce(op=FltAdd, init=Const(0))
      Stage(operands=List(rr609), op=Bypass, results=List(CU.scalarOut(x4115_x4142_s)))
    }
    val x4153_0 = Pipeline(name="x4153_0",parent=x4154) { implicit CU => 
      val x4148 = CU.temp
      val x4147 = CU.temp
      val x3809_x4145 =  ScalarBuffer().wtPort(x3809_argin)
      val x4115_x4146 =  ScalarBuffer().wtPort(x4115_x4142_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x4115_x4146), CU.load(x3809_x4145)), op=FltMul, results=List(x4147))
      Stage(operands=List(Const(1), CU.load(x3809_x4145)), op=FltSub, results=List(x4148))
      Stage(operands=List(x4147, x4148), op=FltAdd, results=List(CU.vecOut(x3838_x4152_v)))
    }
    val x4185 = StreamController(name="x4185",parent=x4186) { implicit CU => 
    }
    val x4176 = Sequential(name="x4176",parent=x4185) { implicit CU => 
    }
    val x4166_0 = Pipeline(name="x4166_0",parent=x4176) { implicit CU => 
      val x4159 = CU.temp
      val x4158 =  ScalarBuffer().wtPort(x4158_argin)
      val x3837 = CounterChain.copy("x4186", "x3837")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x3837(0)), Const(4)), op=FixMul, results=List(x4159))
      Stage(operands=List(x4159, CU.load(x4158)), op=FixAdd, results=List(CU.scalarOut(x4155_b4282_x4165_b4284_s)))
      Stage(operands=List(Const(3072)), op=Bypass, results=List(CU.scalarOut(x4155_b4283_x4165_b4285_s)))
    }
    val x4175 = Pipeline(name="x4175",parent=x4176) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(768), step=Const(1), par=1) // Counter
      val x4168 = CounterChain(name = "x4168", ctr14).iter(768)
      var stage: List[Stage] = Nil
    }
    val x4177 = MemoryController(name="x4177",parent=x4185,offchip=x3814_oc, mctpe=TileStore) { implicit CU => 
      val x4155_b4282_x4177 =  ScalarFIFO(name="offset",size=1).wtPort(x4155_b4282_x4165_b4284_s)
      val x4156_x4177 =  VectorFIFO(name="data",size=1).wtPort(x3838_x4171_x4175_v)
      val x4155_b4283_x4177 =  ScalarFIFO(name="size",size=1).wtPort(x4155_b4283_x4165_b4285_s)
    }
    
  }
}
