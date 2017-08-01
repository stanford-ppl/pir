import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object Kmeans_plasticine extends PIRApp {
  def main(top:Top) = {
    val x3423_1_s = Scalar("x3423_1")
    val x3423_x3453_s = Scalar("x3423_x3453")
    val x3391_2_s = Scalar("x3391_2")
    val x3391_1_s = Scalar("x3391_1")
    val centroids_oc = OffChip("centroids")
    val x3389_0_s = Scalar("x3389_0")
    val x3558_x3567_s = Scalar("x3558_x3567")
    val x3394_b3699_x3406_b3701_s = Scalar("x3394_b3699_x3406_b3701")
    val x3423_0_s = Scalar("x3423_0")
    val x3390_0_s = Scalar("x3390_0")
    val x3391_0_s = Scalar("x3391_0")
    val x3501_0_s = Scalar("x3501_0")
    val x3458_x3498_s = Scalar("x3458_x3498")
    val x3462_x3484_s = Scalar("x3462_x3484")
    val x3394_b3698_x3406_b3700_s = Scalar("x3394_b3698_x3406_b3700")
    val x3429_x3445_data_v = Vector("x3429_x3445_data")
    val x3391_x3531_s = Scalar("x3391_x3531")
    val x3428_b3704_x3443_b3706_s = Scalar("x3428_b3704_x3443_b3706")
    val x3558_0_s = Scalar("x3558_0")
    val points_oc = OffChip("points")
    val points_da = DRAMAddress("points", "points")
    val iters_argin = ArgIn("iters")
    val x3569_b3735_x3579_b3737_s = Scalar("x3569_b3735_x3579_b3737")
    val centroids_da = DRAMAddress("centroids", "centroids")
    val x3569_b3734_x3579_b3736_s = Scalar("x3569_b3734_x3579_b3736")
    val x3535_0_s = Scalar("x3535_0")
    val x3570_x3586_v = Vector("x3570_x3586")
    val x3395_x3408_data_v = Vector("x3395_x3408_data")
    val bus_434_v = Vector("bus_434")
    val x3535_x3542_s = Scalar("x3535_x3542")
    val x3501_x3514_s = Scalar("x3501_x3514")
    val x3389_x3414_s = Scalar("x3389_x3414")
    val x3428_b3705_x3443_b3707_s = Scalar("x3428_b3705_x3443_b3707")
    val N_argin = ArgIn("N").bound(960000)
    val x3390_x3555_s = Scalar("x3390_x3555")
    val x3390_1_s = Scalar("x3390_1")
    val x3594 = Sequential(name="x3594",parent=top) { implicit CU => 
      val x3594_unit = CounterChain(name = "x3594_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3593 = Sequential(name="x3593",parent=x3594) { implicit CU => 
      val x3593_unit = CounterChain(name = "x3593_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3389_dsp0 = MemoryPipeline(name="x3389_dsp0",parent="x3593") { implicit CU => 
      val b3702 = CU.temp(None)
      val b3710 = CU.temp(None)
      val x3414 = ScalarFIFO(size=1,name="x3414").wtPort(x3389_x3414_s)
      val x3461 = CounterChain.copy("x3500", "x3461")
      val x3464 = CounterChain.copy("x3485", "x3464")
      val x3393 = CounterChain.copy("x3416", "x3393")
      val x3410 = CounterChain.copy("x3415_0", "x3410")
      val x3389 = SRAM(size=1920,name="x3389",banking = Strided(1)).wtPort(x3414.readPort).rdPort(x3389_0_s)
      WAStage(operands=List(CU.ctr(x3393(0)), Const(96)), op=FixMul, results=List(b3702))
      WAStage(operands=List(b3702, CU.ctr(x3410(0))), op=FixAdd, results=List(x3389.writeAddr))
      RAStage(operands=List(CU.ctr(x3461(0)), Const(96)), op=FixMul, results=List(b3710))
      RAStage(operands=List(b3710, CU.ctr(x3464(0))), op=FixAdd, results=List(x3389.readAddr))
    }
    val x3390_dsp0 = MemoryPipeline(name="x3390_dsp0",parent="x3593") { implicit CU => 
      val b3732 = CU.temp(None)
      val b3730 = CU.temp(None)
      val x3555 = ScalarFIFO(size=1,name="x3555").wtPort(x3390_x3555_s)
      val x3546 = CounterChain.copy("x3556_0", "x3546")
      val x3561 = CounterChain.copy("x3568_0", "x3561")
      val x3390 = SRAM(size=1920,name="x3390",banking = Strided(1)).wtPort(x3555.readPort).rdPort(x3390_0_s)
      WAStage(operands=List(CU.ctr(x3546(0)), Const(96)), op=FixMul, results=List(b3730))
      WAStage(operands=List(b3730, CU.ctr(x3546(1))), op=FixAdd, results=List(x3390.writeAddr))
      RAStage(operands=List(CU.ctr(x3561(0)), Const(96)), op=FixMul, results=List(b3732))
      RAStage(operands=List(b3732, CU.ctr(x3561(1))), op=FixAdd, results=List(x3390.readAddr))
    }
    val x3390_dsp1 = MemoryPipeline(name="x3390_dsp1",parent="x3593") { implicit CU => 
      val b3712 = CU.temp(None)
      val b3730 = CU.temp(None)
      val x3555 = ScalarFIFO(size=1,name="x3555").wtPort(x3390_x3555_s)
      val x3546 = CounterChain.copy("x3556_0", "x3546")
      val x3461 = CounterChain.copy("x3500", "x3461")
      val x3464 = CounterChain.copy("x3485", "x3464")
      val x3390 = SRAM(size=1920,name="x3390",banking = Strided(1)).wtPort(x3555.readPort).rdPort(x3390_1_s)
      WAStage(operands=List(CU.ctr(x3546(0)), Const(96)), op=FixMul, results=List(b3730))
      WAStage(operands=List(b3730, CU.ctr(x3546(1))), op=FixAdd, results=List(x3390.writeAddr))
      RAStage(operands=List(CU.ctr(x3461(0)), Const(96)), op=FixMul, results=List(b3712))
      RAStage(operands=List(b3712, CU.ctr(x3464(0))), op=FixAdd, results=List(x3390.readAddr))
    }
    val x3391_dsp0 = MemoryPipeline(name="x3391_dsp0",parent="x3533") { implicit CU => 
      val b3728 = CU.temp(None)
      val b3724 = CU.temp(None)
      val x3531 = ScalarFIFO(size=1,name="x3531").wtPort(x3391_x3531_s)
      val x3546 = CounterChain.copy("x3556_0", "x3546")
      val x3518 = CounterChain.copy("x3532_0", "x3518")
      val x3391 = SRAM(size=1920,name="x3391",banking = Strided(1)).wtPort(x3531.readPort).rdPort(x3391_0_s)
      WAStage(operands=List(CU.ctr(x3518(0)), Const(96)), op=FixMul, results=List(b3724))
      WAStage(operands=List(b3724, CU.ctr(x3518(1))), op=FixAdd, results=List(x3391.writeAddr))
      RAStage(operands=List(CU.ctr(x3546(0)), Const(96)), op=FixMul, results=List(b3728))
      RAStage(operands=List(b3728, CU.ctr(x3546(1))), op=FixAdd, results=List(x3391.readAddr))
    }
    val x3391_dsp1 = MemoryPipeline(name="x3391_dsp1",parent="x3533") { implicit CU => 
      val b3726 = CU.temp(None)
      val b3724 = CU.temp(None)
      val x3531 = ScalarFIFO(size=1,name="x3531").wtPort(x3391_x3531_s)
      val x3537 = CounterChain.copy("x3543_0", "x3537")
      val x3518 = CounterChain.copy("x3532_0", "x3518")
      val x3391 = SRAM(size=1920,name="x3391",banking = Strided(1)).wtPort(x3531.readPort).rdPort(x3391_1_s)
      WAStage(operands=List(CU.ctr(x3518(0)), Const(96)), op=FixMul, results=List(b3724))
      WAStage(operands=List(b3724, CU.ctr(x3518(1))), op=FixAdd, results=List(x3391.writeAddr))
      RAStage(operands=List(CU.ctr(x3537(0)), Const(96)), op=FixMul, results=List(b3726))
      RAStage(operands=List(b3726, Const(95)), op=FixAdd, results=List(x3391.readAddr))
    }
    val x3391_dsp2 = MemoryPipeline(name="x3391_dsp2",parent="x3533") { implicit CU => 
      val b3722 = CU.temp(None)
      val b3724 = CU.temp(None)
      val x3531 = ScalarFIFO(size=1,name="x3531").wtPort(x3391_x3531_s)
      val x3518 = CounterChain.copy("x3532_0", "x3518")
      val x3391 = SRAM(size=1920,name="x3391",banking = Strided(1)).wtPort(x3531.readPort).rdPort(x3391_2_s)
      WAStage(operands=List(CU.ctr(x3518(0)), Const(96)), op=FixMul, results=List(b3724))
      WAStage(operands=List(b3724, CU.ctr(x3518(1))), op=FixAdd, results=List(x3391.writeAddr))
      RAStage(operands=List(CU.ctr(x3518(0)), Const(96)), op=FixMul, results=List(b3722))
      RAStage(operands=List(b3722, CU.ctr(x3518(1))), op=FixAdd, results=List(x3391.readAddr))
    }
    val x3416 = StreamController(name="x3416",parent=x3593) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3393 = CounterChain(name = "x3393", ctr1).iter(20)
    }
    val x3407_0 = Pipeline(name="x3407_0",parent=x3416) { implicit CU => 
      val x3400 = CU.temp(None)
      val x3397 = CU.temp(None)
      val x3399 = CU.temp(None)
      val x3402 = ScalarBuffer(name="x3402").wtPort(points_da)
      val x3393 = CounterChain.copy("x3416", "x3393")
      val x3407_unit = CounterChain(name = "x3407_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x3393(0)), Const(96)), op=FixMul, results=List(x3397))
      Stage(operands=List(x3397, Const(0)), op=FixAdd, results=List(x3399))
      Stage(operands=List(x3399, Const(2)), op=FixSla, results=List(x3400))
      Stage(operands=List(x3400, CU.load(x3402)), op=FixAdd, results=List(CU.scalarOut(x3394_b3698_x3406_b3700_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x3394_b3699_x3406_b3701_s)))
    }
    val x3408 = MemoryController(name="x3408",parent=x3416,offchip=points_oc, mctpe=TileLoad) { implicit CU => 
      val x3394_b3699 = ScalarFIFO(size=1,name="size").wtPort(x3394_b3699_x3406_b3701_s)
      val x3394_b3698 = ScalarFIFO(size=1,name="offset").wtPort(x3394_b3698_x3406_b3700_s)
      CU.newVout("data", x3395_x3408_data_v)
    }
    val x3415_0 = Pipeline(name="x3415_0",parent=x3416) { implicit CU => 
      val x3395 = VectorFIFO(size=1,name="x3395").wtPort(x3395_x3408_data_v)
      val ctr2 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3410 = CounterChain(name = "x3410", ctr2).iter(6)
      Stage(operands=List(CU.load(x3395)), op=Bypass, results=List(CU.scalarOut(x3389_x3414_s)))
    }
    val x3557 = Sequential(name="x3557",parent=x3593) { implicit CU => 
      val x3379 = ScalarBuffer(name="x3379").wtPort(iters_argin)
      val ctr3 = Counter(min=Const(0), max=x3379.readPort, step=Const(1), par=1) // Counter
      val x3419 = CounterChain(name = "x3419", ctr3).iter(1)
    }
    val x3534 = MetaPipeline(name="x3534",parent=x3557) { implicit CU => 
      val x3380 = ScalarBuffer(name="x3380").wtPort(N_argin)
      val ctr4 = Counter(min=Const(0), max=x3380.readPort, step=Const(1), par=1) // Counter
      val x3422 = CounterChain(name = "x3422", ctr4).iter(1)
    }
    val x3423_dsp0 = MemoryPipeline(name="x3423_dsp0",parent="x3534") { implicit CU => 
      val b3714 = CU.temp(None)
      val b3708 = CU.temp(None)
      val x3453 = ScalarFIFO(size=1,name="x3453").wtPort(x3423_x3453_s)
      val x3464 = CounterChain.copy("x3485", "x3464")
      val x3457 = CounterChain.copy("x3533", "x3457")
      val x3447 = CounterChain.copy("x3454_0", "x3447")
      val x3427 = CounterChain.copy("x3455", "x3427")
      val x3423 = SRAM(size=96,name="x3423",banking = Strided(1)).wtPort(x3453.readPort).rdPort(x3423_0_s)
      WAStage(operands=List(CU.ctr(x3427(0)), Const(96)), op=FixMul, results=List(b3708))
      WAStage(operands=List(b3708, CU.ctr(x3447(0))), op=FixAdd, results=List(x3423.writeAddr))
      RAStage(operands=List(CU.ctr(x3457(0)), Const(96)), op=FixMul, results=List(b3714))
      RAStage(operands=List(b3714, CU.ctr(x3464(0))), op=FixAdd, results=List(x3423.readAddr))
    }
    val x3423_dsp1 = MemoryPipeline(name="x3423_dsp1",parent="x3534") { implicit CU => 
      val b3708 = CU.temp(None)
      val b3716 = CU.temp(None)
      val x3453 = ScalarFIFO(size=1,name="x3453").wtPort(x3423_x3453_s)
      val x3504 = CounterChain.copy("x3515_0", "x3504")
      val x3457 = CounterChain.copy("x3533", "x3457")
      val x3447 = CounterChain.copy("x3454_0", "x3447")
      val x3427 = CounterChain.copy("x3455", "x3427")
      val x3423 = SRAM(size=96,name="x3423",banking = Strided(1)).wtPort(x3453.readPort).rdPort(x3423_1_s)
      WAStage(operands=List(CU.ctr(x3427(0)), Const(96)), op=FixMul, results=List(b3708))
      WAStage(operands=List(b3708, CU.ctr(x3447(0))), op=FixAdd, results=List(x3423.writeAddr))
      RAStage(operands=List(CU.ctr(x3457(0)), Const(96)), op=FixMul, results=List(b3716))
      RAStage(operands=List(b3716, CU.ctr(x3504(1))), op=FixAdd, results=List(x3423.readAddr))
    }
    val x3455 = StreamController(name="x3455",parent=x3534) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3427 = CounterChain(name = "x3427", ctr5).iter(1)
    }
    val x3444_0 = Pipeline(name="x3444_0",parent=x3455) { implicit CU => 
      val x3434 = CU.temp(None)
      val x3435 = CU.temp(None)
      val x3432 = CU.temp(None)
      val x3430 = CU.temp(None)
      val x3437 = ScalarBuffer(name="x3437").wtPort(points_da)
      val x3422 = CounterChain.copy("x3534", "x3422")
      val x3427 = CounterChain.copy("x3455", "x3427")
      val x3444_unit = CounterChain(name = "x3444_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x3422(0)), CU.ctr(x3427(0))), op=FixAdd, results=List(x3430))
      Stage(operands=List(x3430, Const(96)), op=FixMul, results=List(x3432))
      Stage(operands=List(x3432, Const(0)), op=FixAdd, results=List(x3434))
      Stage(operands=List(x3434, Const(2)), op=FixSla, results=List(x3435))
      Stage(operands=List(x3435, CU.load(x3437)), op=FixAdd, results=List(CU.scalarOut(x3428_b3704_x3443_b3706_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x3428_b3705_x3443_b3707_s)))
    }
    val x3445 = MemoryController(name="x3445",parent=x3455,offchip=points_oc, mctpe=TileLoad) { implicit CU => 
      val x3428_b3704 = ScalarFIFO(size=1,name="offset").wtPort(x3428_b3704_x3443_b3706_s)
      val x3428_b3705 = ScalarFIFO(size=1,name="size").wtPort(x3428_b3705_x3443_b3707_s)
      CU.newVout("data", x3429_x3445_data_v)
    }
    val x3454_0 = Pipeline(name="x3454_0",parent=x3455) { implicit CU => 
      val x3429 = VectorFIFO(size=1,name="x3429").wtPort(x3429_x3445_data_v)
      val ctr6 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3447 = CounterChain(name = "x3447", ctr6).iter(6)
      Stage(operands=List(CU.load(x3429)), op=Bypass, results=List(CU.scalarOut(x3423_x3453_s)))
    }
    val x3533 = MetaPipeline(name="x3533",parent=x3534) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3457 = CounterChain(name = "x3457", ctr7).iter(1)
    }
    val x3500 = MetaPipeline(name="x3500",parent=x3533) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3461 = CounterChain(name = "x3461", ctr8).iter(20)
    }
    val x3485 = StreamController(name="x3485",parent=x3500) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3464 = CounterChain(name = "x3464", ctr9).iter(6)
    }
    val x3485_0 = Pipeline(name="x3485_0",parent=x3485) { implicit CU => 
      val x3478 = CU.temp(None)
      val x3473 = CU.temp(None)
      val x3476 = CU.temp(None)
      val x3471 = ScalarFIFO(size=1,name="x3471").wtPort(x3423_0_s)
      val x3470 = ScalarFIFO(size=1,name="x3470").wtPort(x3390_1_s)
      val x3469 = ScalarFIFO(size=1,name="x3469").wtPort(x3389_0_s)
      val x3419 = CounterChain.copy("x3557", "x3419")
      Stage(operands=List(CU.ctr(x3419(0)), Const(0)), op=FixEql, results=List(x3473))
      Stage(operands=List(x3473, CU.load(x3469), CU.load(x3470)), op=Mux, results=List(x3476))
      Stage(operands=List(CU.load(x3471), x3476), op=FixSub, results=List(x3478))
      Stage(operands=List(x3478, x3478), op=FixMul, results=List(CU.vecOut(bus_434_v)))
    }
    val x3485_1 = Pipeline(name="x3485_1",parent=x3485) { implicit CU => 
      val rr434 = VectorFIFO(size=1,name="rr434").wtPort(bus_434_v)
      Stage(operands=List(CU.load(rr434)), op=Bypass, results=List(CU.reduce))
      val (_, rr436) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3485")
      Stage(operands=List(rr436), op=Bypass, results=List(CU.scalarOut(x3462_x3484_s)))
    }
    val x3499_0 = Pipeline(name="x3499_0",parent=x3500) { implicit CU => 
      val x3459 = CU.temp(Some(100000))
      val x3462 = ScalarBuffer(name="x3462").wtPort(x3462_x3484_s)
      val x3461 = CounterChain.copy("x3500", "x3461")
      val x3499_unit = CounterChain(name = "x3499_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x3459, CU.load(x3462)), op=FixEql, results=List())
      Stage(operands=List(CU.ctr(x3461(0))), op=Bypass, results=List(CU.scalarOut(x3458_x3498_s)))
    }
    val x3501_dsp0 = MemoryPipeline(name="x3501_dsp0",parent="x3533") { implicit CU => 
      val b3718 = CU.temp(None)
      val b3720 = CU.temp(None)
      val x3514 = ScalarFIFO(size=1,name="x3514").wtPort(x3501_x3514_s)
      val x3504 = CounterChain.copy("x3515_0", "x3504")
      val x3518 = CounterChain.copy("x3532_0", "x3518")
      val x3501 = SRAM(size=1920,name="x3501",banking = Strided(1)).wtPort(x3514.readPort).rdPort(x3501_0_s)
      WAStage(operands=List(CU.ctr(x3504(0)), Const(96)), op=FixMul, results=List(b3718))
      WAStage(operands=List(b3718, CU.ctr(x3504(1))), op=FixAdd, results=List(x3501.writeAddr))
      RAStage(operands=List(CU.ctr(x3518(0)), Const(96)), op=FixMul, results=List(b3720))
      RAStage(operands=List(b3720, CU.ctr(x3518(1))), op=FixAdd, results=List(x3501.readAddr))
    }
    val x3515_0 = Pipeline(name="x3515_0",parent=x3533) { implicit CU => 
      val x3512 = CU.temp(None)
      val x3509 = ScalarFIFO(size=1,name="x3509").wtPort(x3423_1_s)
      val x3458 = ScalarBuffer(name="x3458").wtPort(x3458_x3498_s)
      val ctr10 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr11 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3504 = CounterChain(name = "x3504", ctr10, ctr11).iter(120)
      Stage(operands=List(CU.ctr(x3504(0)), CU.load(x3458)), op=FixEql, results=List(x3512))
      Stage(operands=List(x3512, CU.load(x3509), Const(0)), op=Mux, results=List(CU.scalarOut(x3501_x3514_s)))
    }
    val x3532_0 = Pipeline(name="x3532_0",parent=x3533) { implicit CU => 
      val x3522 = ScalarFIFO(size=1,name="x3522").wtPort(x3501_0_s)
      val x3524 = ScalarFIFO(size=1,name="x3524").wtPort(x3391_2_s)
      val ctr12 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr13 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x3518 = CounterChain(name = "x3518", ctr12, ctr13).iter(1920)
      Stage(operands=List(CU.load(x3522), CU.load(x3524)), op=FixAdd, results=List(CU.scalarOut(x3391_x3531_s)))
    }
    val x3535_dsp0 = MemoryPipeline(name="x3535_dsp0",parent="x3557") { implicit CU => 
      val x3542 = ScalarFIFO(size=1,name="x3542").wtPort(x3535_x3542_s)
      val x3537 = CounterChain.copy("x3543_0", "x3537")
      val x3546 = CounterChain.copy("x3556_0", "x3546")
      val x3535 = SRAM(size=20,name="x3535",banking = Strided(1)).wtPort(x3542.readPort).rdPort(x3535_0_s).rdAddr(x3546(0)).wtAddr(x3537(0))
    }
    val x3543_0 = Pipeline(name="x3543_0",parent=x3557) { implicit CU => 
      val x3539 = ScalarFIFO(size=1,name="x3539").wtPort(x3391_1_s)
      val ctr14 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3537 = CounterChain(name = "x3537", ctr14).iter(20)
      Stage(operands=List(CU.load(x3539), Const(1)), op=FixMax, results=List(CU.scalarOut(x3535_x3542_s)))
    }
    val x3556_0 = Pipeline(name="x3556_0",parent=x3557) { implicit CU => 
      val x3550 = CU.temp(None)
      val x3553 = CU.temp(None)
      val x3549 = ScalarFIFO(size=1,name="x3549").wtPort(x3535_0_s)
      val x3551 = ScalarFIFO(size=1,name="x3551").wtPort(x3391_0_s)
      val ctr15 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr16 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3546 = CounterChain(name = "x3546", ctr15, ctr16).iter(120)
      Stage(operands=List(CU.load(x3549), Const(0)), op=FixEql, results=List(x3550))
      Stage(operands=List(CU.load(x3551), CU.load(x3549)), op=FixDiv, results=List(x3553))
      Stage(operands=List(x3550, Const(0), x3553), op=Mux, results=List(CU.scalarOut(x3390_x3555_s)))
    }
    val x3558_dsp0 = MemoryPipeline(name="x3558_dsp0",parent="x3593") { implicit CU => 
      val x3562 = CU.temp(None)
      val x3567 = ScalarFIFO(size=1,name="x3567").wtPort(x3558_x3567_s)
      val x3561 = CounterChain.copy("x3568_0", "x3561")
      val x3582 = CounterChain.copy("x3587_0", "x3582")
      val x3558 = SRAM(size=1920,name="x3558",banking = Strided(1)).wtPort(x3567.readPort).rdPort(x3558_0_s).rdAddr(x3582(0))
      WAStage(operands=List(CU.ctr(x3561(0)), Const(96)), op=FixMul, results=List(x3562))
      WAStage(operands=List(x3562, CU.ctr(x3561(1))), op=FixAdd, results=List(x3558.writeAddr))
    }
    val x3568_0 = Pipeline(name="x3568_0",parent=x3593) { implicit CU => 
      val x3565 = ScalarFIFO(size=1,name="x3565").wtPort(x3390_0_s)
      val ctr17 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr18 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x3561 = CounterChain(name = "x3561", ctr17, ctr18).iter(1920)
      Stage(operands=List(CU.ctr(x3561(0)), Const(96)), op=FixMul, results=List())
      Stage(operands=List(CU.load(x3565)), op=Bypass, results=List(CU.scalarOut(x3558_x3567_s)))
    }
    val x3592 = StreamController(name="x3592",parent=x3593) { implicit CU => 
      val x3592_unit = CounterChain(name = "x3592_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3588 = Sequential(name="x3588",parent=x3592) { implicit CU => 
      val x3588_unit = CounterChain(name = "x3588_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3580_0 = Pipeline(name="x3580_0",parent=x3588) { implicit CU => 
      val x3573 = CU.temp(None)
      val x3575 = ScalarBuffer(name="x3575").wtPort(centroids_da)
      val x3580_unit = CounterChain(name = "x3580_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x3573))
      Stage(operands=List(x3573, CU.load(x3575)), op=FixAdd, results=List(CU.scalarOut(x3569_b3734_x3579_b3736_s)))
      Stage(operands=List(Const(7680)), op=Bypass, results=List(CU.scalarOut(x3569_b3735_x3579_b3737_s)))
    }
    val x3587_0 = Pipeline(name="x3587_0",parent=x3588) { implicit CU => 
      val x3583 = ScalarFIFO(size=1,name="x3583").wtPort(x3558_0_s)
      val ctr19 = Counter(min=Const(0), max=Const(1920), step=Const(1), par=16) // Counter
      val x3582 = CounterChain(name = "x3582", ctr19).iter(120)
      Stage(operands=List(CU.load(x3583)), op=Bypass, results=List(CU.vecOut(x3570_x3586_v)))
    }
    val x3589 = MemoryController(name="x3589",parent=x3592,offchip=centroids_oc, mctpe=TileStore) { implicit CU => 
      val x3569_b3735 = ScalarFIFO(size=1,name="size").wtPort(x3569_b3735_x3579_b3737_s)
      val x3570 = VectorFIFO(size=1,name="data").wtPort(x3570_x3586_v)
      val x3569_b3734 = ScalarFIFO(size=1,name="offset").wtPort(x3569_b3734_x3579_b3736_s)
    }
    
  }
}
