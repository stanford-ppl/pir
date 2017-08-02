import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object Kmeans_plasticine extends PIRApp {
  def main(top:Top) = {
    val x3428_b3703_x3443_b3705_s = Scalar("x3428_b3703_x3443_b3705")
    val x3423_1_s = Scalar("x3423_1")
    val x3394_b3697_x3406_b3699_s = Scalar("x3394_b3697_x3406_b3699")
    val x3423_x3453_s = Scalar("x3423_x3453")
    val x3391_2_s = Scalar("x3391_2")
    val x3391_1_s = Scalar("x3391_1")
    val centroids_oc = OffChip("centroids")
    val x3458_x3497_s = Scalar("x3458_x3497")
    val x3389_0_s = Scalar("x3389_0")
    val x3568_b3734_x3578_b3736_s = Scalar("x3568_b3734_x3578_b3736")
    val x3534_x3541_s = Scalar("x3534_x3541")
    val x3423_0_s = Scalar("x3423_0")
    val x3390_0_s = Scalar("x3390_0")
    val x3391_0_s = Scalar("x3391_0")
    val x3462_x3484_s = Scalar("x3462_x3484")
    val x3394_b3698_x3406_b3700_s = Scalar("x3394_b3698_x3406_b3700")
    val x3557_x3566_s = Scalar("x3557_x3566")
    val x3429_x3445_data_v = Vector("x3429_x3445_data")
    val x3390_x3554_s = Scalar("x3390_x3554")
    val x3557_0_s = Scalar("x3557_0")
    val x3428_b3704_x3443_b3706_s = Scalar("x3428_b3704_x3443_b3706")
    val points_oc = OffChip("points")
    val points_da = DRAMAddress("points", "points")
    val iters_argin = ArgIn("iters")
    val x3391_x3530_s = Scalar("x3391_x3530")
    val x3568_b3733_x3578_b3735_s = Scalar("x3568_b3733_x3578_b3735")
    val centroids_da = DRAMAddress("centroids", "centroids")
    val x3500_x3513_s = Scalar("x3500_x3513")
    val x3395_x3408_data_v = Vector("x3395_x3408_data")
    val bus_434_v = Vector("bus_434")
    val x3569_x3585_v = Vector("x3569_x3585")
    val x3534_0_s = Scalar("x3534_0")
    val x3389_x3414_s = Scalar("x3389_x3414")
    val N_argin = ArgIn("N").bound(960000)
    val x3500_0_s = Scalar("x3500_0")
    val x3390_1_s = Scalar("x3390_1")
    val x3593 = Sequential(name="x3593",parent=top) { implicit CU => 
      val x3593_unit = CounterChain(name = "x3593_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3592 = Sequential(name="x3592",parent=x3593) { implicit CU => 
      val x3592_unit = CounterChain(name = "x3592_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3389_dsp0 = MemoryPipeline(name="x3389_dsp0",parent="x3592") { implicit CU => 
      val b3709 = CU.temp(None)
      val b3701 = CU.temp(None)
      val x3414 = ScalarFIFO(size=1,name="x3414").wtPort(x3389_x3414_s)
      val x3461 = CounterChain.copy("x3499", "x3461")
      val x3464 = CounterChain.copy("x3485", "x3464")
      val x3393 = CounterChain.copy("x3416", "x3393")
      val x3410 = CounterChain.copy("x3415_0", "x3410")
      val x3389 = SRAM(size=1920,name="x3389",banking = Strided(1)).wtPort(x3414.readPort).rdPort(x3389_0_s)
      WAStage(operands=List(CU.ctr(x3393(0)), Const(96)), op=FixMul, results=List(b3701))
      WAStage(operands=List(b3701, CU.ctr(x3410(0))), op=FixAdd, results=List(x3389.writeAddr))
      RAStage(operands=List(CU.ctr(x3461(0)), Const(96)), op=FixMul, results=List(b3709))
      RAStage(operands=List(b3709, CU.ctr(x3464(0))), op=FixAdd, results=List(x3389.readAddr))
    }
    val x3390_dsp0 = MemoryPipeline(name="x3390_dsp0",parent="x3592") { implicit CU => 
      val b3729 = CU.temp(None)
      val b3731 = CU.temp(None)
      val x3554 = ScalarFIFO(size=1,name="x3554").wtPort(x3390_x3554_s)
      val x3545 = CounterChain.copy("x3555_0", "x3545")
      val x3560 = CounterChain.copy("x3567_0", "x3560")
      val x3390 = SRAM(size=1920,name="x3390",banking = Strided(1)).wtPort(x3554.readPort).rdPort(x3390_0_s)
      WAStage(operands=List(CU.ctr(x3545(0)), Const(96)), op=FixMul, results=List(b3729))
      WAStage(operands=List(b3729, CU.ctr(x3545(1))), op=FixAdd, results=List(x3390.writeAddr))
      RAStage(operands=List(CU.ctr(x3560(0)), Const(96)), op=FixMul, results=List(b3731))
      RAStage(operands=List(b3731, CU.ctr(x3560(1))), op=FixAdd, results=List(x3390.readAddr))
    }
    val x3390_dsp1 = MemoryPipeline(name="x3390_dsp1",parent="x3592") { implicit CU => 
      val b3711 = CU.temp(None)
      val b3729 = CU.temp(None)
      val x3554 = ScalarFIFO(size=1,name="x3554").wtPort(x3390_x3554_s)
      val x3461 = CounterChain.copy("x3499", "x3461")
      val x3464 = CounterChain.copy("x3485", "x3464")
      val x3545 = CounterChain.copy("x3555_0", "x3545")
      val x3390 = SRAM(size=1920,name="x3390",banking = Strided(1)).wtPort(x3554.readPort).rdPort(x3390_1_s)
      WAStage(operands=List(CU.ctr(x3545(0)), Const(96)), op=FixMul, results=List(b3729))
      WAStage(operands=List(b3729, CU.ctr(x3545(1))), op=FixAdd, results=List(x3390.writeAddr))
      RAStage(operands=List(CU.ctr(x3461(0)), Const(96)), op=FixMul, results=List(b3711))
      RAStage(operands=List(b3711, CU.ctr(x3464(0))), op=FixAdd, results=List(x3390.readAddr))
    }
    val x3391_dsp0 = MemoryPipeline(name="x3391_dsp0",parent="x3532") { implicit CU => 
      val b3727 = CU.temp(None)
      val b3723 = CU.temp(None)
      val x3530 = ScalarFIFO(size=1,name="x3530").wtPort(x3391_x3530_s)
      val x3545 = CounterChain.copy("x3555_0", "x3545")
      val x3517 = CounterChain.copy("x3531_0", "x3517")
      val x3391 = SRAM(size=1920,name="x3391",banking = Strided(1)).wtPort(x3530.readPort).rdPort(x3391_0_s)
      WAStage(operands=List(CU.ctr(x3517(0)), Const(96)), op=FixMul, results=List(b3723))
      WAStage(operands=List(b3723, CU.ctr(x3517(1))), op=FixAdd, results=List(x3391.writeAddr))
      RAStage(operands=List(CU.ctr(x3545(0)), Const(96)), op=FixMul, results=List(b3727))
      RAStage(operands=List(b3727, CU.ctr(x3545(1))), op=FixAdd, results=List(x3391.readAddr))
    }
    val x3391_dsp1 = MemoryPipeline(name="x3391_dsp1",parent="x3532") { implicit CU => 
      val b3725 = CU.temp(None)
      val b3723 = CU.temp(None)
      val x3530 = ScalarFIFO(size=1,name="x3530").wtPort(x3391_x3530_s)
      val x3536 = CounterChain.copy("x3542_0", "x3536")
      val x3517 = CounterChain.copy("x3531_0", "x3517")
      val x3391 = SRAM(size=1920,name="x3391",banking = Strided(1)).wtPort(x3530.readPort).rdPort(x3391_1_s)
      WAStage(operands=List(CU.ctr(x3517(0)), Const(96)), op=FixMul, results=List(b3723))
      WAStage(operands=List(b3723, CU.ctr(x3517(1))), op=FixAdd, results=List(x3391.writeAddr))
      RAStage(operands=List(CU.ctr(x3536(0)), Const(96)), op=FixMul, results=List(b3725))
      RAStage(operands=List(b3725, Const(95)), op=FixAdd, results=List(x3391.readAddr))
    }
    val x3391_dsp2 = MemoryPipeline(name="x3391_dsp2",parent="x3532") { implicit CU => 
      val b3721 = CU.temp(None)
      val b3723 = CU.temp(None)
      val x3530 = ScalarFIFO(size=1,name="x3530").wtPort(x3391_x3530_s)
      val x3517 = CounterChain.copy("x3531_0", "x3517")
      val x3391 = SRAM(size=1920,name="x3391",banking = Strided(1)).wtPort(x3530.readPort).rdPort(x3391_2_s)
      WAStage(operands=List(CU.ctr(x3517(0)), Const(96)), op=FixMul, results=List(b3723))
      WAStage(operands=List(b3723, CU.ctr(x3517(1))), op=FixAdd, results=List(x3391.writeAddr))
      RAStage(operands=List(CU.ctr(x3517(0)), Const(96)), op=FixMul, results=List(b3721))
      RAStage(operands=List(b3721, CU.ctr(x3517(1))), op=FixAdd, results=List(x3391.readAddr))
    }
    val x3416 = StreamController(name="x3416",parent=x3592) { implicit CU => 
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
      Stage(operands=List(x3400, CU.load(x3402)), op=FixAdd, results=List(CU.scalarOut(x3394_b3697_x3406_b3699_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x3394_b3698_x3406_b3700_s)))
    }
    val x3408 = MemoryController(name="x3408",parent=x3416,offchip=points_oc, mctpe=TileLoad) { implicit CU => 
      val x3394_b3698 = ScalarFIFO(size=1,name="size").wtPort(x3394_b3698_x3406_b3700_s)
      val x3394_b3697 = ScalarFIFO(size=1,name="offset").wtPort(x3394_b3697_x3406_b3699_s)
      CU.newVout("data", x3395_x3408_data_v)
    }
    val x3415_0 = Pipeline(name="x3415_0",parent=x3416) { implicit CU => 
      val x3395 = VectorFIFO(size=1,name="x3395").wtPort(x3395_x3408_data_v)
      val ctr2 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3410 = CounterChain(name = "x3410", ctr2).iter(6)
      Stage(operands=List(CU.load(x3395)), op=Bypass, results=List(CU.scalarOut(x3389_x3414_s)))
    }
    val x3556 = Sequential(name="x3556",parent=x3592) { implicit CU => 
      val x3379 = ScalarBuffer(name="x3379").wtPort(iters_argin)
      val ctr3 = Counter(min=Const(0), max=x3379.readPort, step=Const(1), par=1) // Counter
      val x3419 = CounterChain(name = "x3419", ctr3).iter(1)
    }
    val x3533 = MetaPipeline(name="x3533",parent=x3556) { implicit CU => 
      val x3380 = ScalarBuffer(name="x3380").wtPort(N_argin)
      val ctr4 = Counter(min=Const(0), max=x3380.readPort, step=Const(1), par=1) // Counter
      val x3422 = CounterChain(name = "x3422", ctr4).iter(1)
    }
    val x3423_dsp0 = MemoryPipeline(name="x3423_dsp0",parent="x3533") { implicit CU => 
      val b3713 = CU.temp(None)
      val b3707 = CU.temp(None)
      val x3453 = ScalarFIFO(size=1,name="x3453").wtPort(x3423_x3453_s)
      val x3457 = CounterChain.copy("x3532", "x3457")
      val x3464 = CounterChain.copy("x3485", "x3464")
      val x3447 = CounterChain.copy("x3454_0", "x3447")
      val x3427 = CounterChain.copy("x3455", "x3427")
      val x3423 = SRAM(size=96,name="x3423",banking = Strided(1)).wtPort(x3453.readPort).rdPort(x3423_0_s)
      WAStage(operands=List(CU.ctr(x3427(0)), Const(96)), op=FixMul, results=List(b3707))
      WAStage(operands=List(b3707, CU.ctr(x3447(0))), op=FixAdd, results=List(x3423.writeAddr))
      RAStage(operands=List(CU.ctr(x3457(0)), Const(96)), op=FixMul, results=List(b3713))
      RAStage(operands=List(b3713, CU.ctr(x3464(0))), op=FixAdd, results=List(x3423.readAddr))
    }
    val x3423_dsp1 = MemoryPipeline(name="x3423_dsp1",parent="x3533") { implicit CU => 
      val b3715 = CU.temp(None)
      val b3707 = CU.temp(None)
      val x3453 = ScalarFIFO(size=1,name="x3453").wtPort(x3423_x3453_s)
      val x3457 = CounterChain.copy("x3532", "x3457")
      val x3503 = CounterChain.copy("x3514_0", "x3503")
      val x3447 = CounterChain.copy("x3454_0", "x3447")
      val x3427 = CounterChain.copy("x3455", "x3427")
      val x3423 = SRAM(size=96,name="x3423",banking = Strided(1)).wtPort(x3453.readPort).rdPort(x3423_1_s)
      WAStage(operands=List(CU.ctr(x3427(0)), Const(96)), op=FixMul, results=List(b3707))
      WAStage(operands=List(b3707, CU.ctr(x3447(0))), op=FixAdd, results=List(x3423.writeAddr))
      RAStage(operands=List(CU.ctr(x3457(0)), Const(96)), op=FixMul, results=List(b3715))
      RAStage(operands=List(b3715, CU.ctr(x3503(1))), op=FixAdd, results=List(x3423.readAddr))
    }
    val x3455 = StreamController(name="x3455",parent=x3533) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3427 = CounterChain(name = "x3427", ctr5).iter(1)
    }
    val x3444_0 = Pipeline(name="x3444_0",parent=x3455) { implicit CU => 
      val x3434 = CU.temp(None)
      val x3435 = CU.temp(None)
      val x3432 = CU.temp(None)
      val x3430 = CU.temp(None)
      val x3437 = ScalarBuffer(name="x3437").wtPort(points_da)
      val x3422 = CounterChain.copy("x3533", "x3422")
      val x3427 = CounterChain.copy("x3455", "x3427")
      val x3444_unit = CounterChain(name = "x3444_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x3422(0)), CU.ctr(x3427(0))), op=FixAdd, results=List(x3430))
      Stage(operands=List(x3430, Const(96)), op=FixMul, results=List(x3432))
      Stage(operands=List(x3432, Const(0)), op=FixAdd, results=List(x3434))
      Stage(operands=List(x3434, Const(2)), op=FixSla, results=List(x3435))
      Stage(operands=List(x3435, CU.load(x3437)), op=FixAdd, results=List(CU.scalarOut(x3428_b3703_x3443_b3705_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x3428_b3704_x3443_b3706_s)))
    }
    val x3445 = MemoryController(name="x3445",parent=x3455,offchip=points_oc, mctpe=TileLoad) { implicit CU => 
      val x3428_b3704 = ScalarFIFO(size=1,name="size").wtPort(x3428_b3704_x3443_b3706_s)
      val x3428_b3703 = ScalarFIFO(size=1,name="offset").wtPort(x3428_b3703_x3443_b3705_s)
      CU.newVout("data", x3429_x3445_data_v)
    }
    val x3454_0 = Pipeline(name="x3454_0",parent=x3455) { implicit CU => 
      val x3429 = VectorFIFO(size=1,name="x3429").wtPort(x3429_x3445_data_v)
      val ctr6 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3447 = CounterChain(name = "x3447", ctr6).iter(6)
      Stage(operands=List(CU.load(x3429)), op=Bypass, results=List(CU.scalarOut(x3423_x3453_s)))
    }
    val x3532 = MetaPipeline(name="x3532",parent=x3533) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3457 = CounterChain(name = "x3457", ctr7).iter(1)
    }
    val x3499 = MetaPipeline(name="x3499",parent=x3532) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3461 = CounterChain(name = "x3461", ctr8).iter(20)
    }
    val x3485 = StreamController(name="x3485",parent=x3499) { implicit CU => 
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
      val x3419 = CounterChain.copy("x3556", "x3419")
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
    val x3498_0 = Pipeline(name="x3498_0",parent=x3499) { implicit CU => 
      val x3459 = CU.temp(Some(100000))
      val x3458 = CU.temp(Some(0))
      val x3494 = CU.temp(None)
      val x3462 = ScalarBuffer(name="x3462").wtPort(x3462_x3484_s)
      val x3461 = CounterChain.copy("x3499", "x3461")
      val x3498_unit = CounterChain(name = "x3498_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x3459, CU.load(x3462)), op=FixEql, results=List(x3494))
      Stage(operands=List(x3494, CU.ctr(x3461(0)), x3458), op=Mux, results=List(CU.scalarOut(x3458_x3497_s)))
    }
    val x3500_dsp0 = MemoryPipeline(name="x3500_dsp0",parent="x3532") { implicit CU => 
      val b3719 = CU.temp(None)
      val b3717 = CU.temp(None)
      val x3513 = ScalarFIFO(size=1,name="x3513").wtPort(x3500_x3513_s)
      val x3503 = CounterChain.copy("x3514_0", "x3503")
      val x3517 = CounterChain.copy("x3531_0", "x3517")
      val x3500 = SRAM(size=1920,name="x3500",banking = Strided(1)).wtPort(x3513.readPort).rdPort(x3500_0_s)
      WAStage(operands=List(CU.ctr(x3503(0)), Const(96)), op=FixMul, results=List(b3717))
      WAStage(operands=List(b3717, CU.ctr(x3503(1))), op=FixAdd, results=List(x3500.writeAddr))
      RAStage(operands=List(CU.ctr(x3517(0)), Const(96)), op=FixMul, results=List(b3719))
      RAStage(operands=List(b3719, CU.ctr(x3517(1))), op=FixAdd, results=List(x3500.readAddr))
    }
    val x3514_0 = Pipeline(name="x3514_0",parent=x3532) { implicit CU => 
      val x3511 = CU.temp(None)
      val x3458 = ScalarBuffer(name="x3458").wtPort(x3458_x3497_s)
      val x3508 = ScalarFIFO(size=1,name="x3508").wtPort(x3423_1_s)
      val ctr10 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr11 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3503 = CounterChain(name = "x3503", ctr10, ctr11).iter(120)
      Stage(operands=List(CU.ctr(x3503(0)), CU.load(x3458)), op=FixEql, results=List(x3511))
      Stage(operands=List(x3511, CU.load(x3508), Const(0)), op=Mux, results=List(CU.scalarOut(x3500_x3513_s)))
    }
    val x3531_0 = Pipeline(name="x3531_0",parent=x3532) { implicit CU => 
      val x3521 = ScalarFIFO(size=1,name="x3521").wtPort(x3500_0_s)
      val x3523 = ScalarFIFO(size=1,name="x3523").wtPort(x3391_2_s)
      val ctr12 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr13 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x3517 = CounterChain(name = "x3517", ctr12, ctr13).iter(1920)
      Stage(operands=List(CU.load(x3521), CU.load(x3523)), op=FixAdd, results=List(CU.scalarOut(x3391_x3530_s)))
    }
    val x3534_dsp0 = MemoryPipeline(name="x3534_dsp0",parent="x3556") { implicit CU => 
      val x3541 = ScalarFIFO(size=1,name="x3541").wtPort(x3534_x3541_s)
      val x3536 = CounterChain.copy("x3542_0", "x3536")
      val x3545 = CounterChain.copy("x3555_0", "x3545")
      val x3534 = SRAM(size=20,name="x3534",banking = Strided(1)).wtPort(x3541.readPort).rdPort(x3534_0_s).rdAddr(x3545(0)).wtAddr(x3536(0))
    }
    val x3542_0 = Pipeline(name="x3542_0",parent=x3556) { implicit CU => 
      val x3538 = ScalarFIFO(size=1,name="x3538").wtPort(x3391_1_s)
      val ctr14 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3536 = CounterChain(name = "x3536", ctr14).iter(20)
      Stage(operands=List(CU.load(x3538), Const(1)), op=FixMax, results=List(CU.scalarOut(x3534_x3541_s)))
    }
    val x3555_0 = Pipeline(name="x3555_0",parent=x3556) { implicit CU => 
      val x3552 = CU.temp(None)
      val x3549 = CU.temp(None)
      val x3548 = ScalarFIFO(size=1,name="x3548").wtPort(x3534_0_s)
      val x3550 = ScalarFIFO(size=1,name="x3550").wtPort(x3391_0_s)
      val ctr15 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr16 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3545 = CounterChain(name = "x3545", ctr15, ctr16).iter(120)
      Stage(operands=List(CU.load(x3548), Const(0)), op=FixEql, results=List(x3549))
      Stage(operands=List(CU.load(x3550), CU.load(x3548)), op=FixDiv, results=List(x3552))
      Stage(operands=List(x3549, Const(0), x3552), op=Mux, results=List(CU.scalarOut(x3390_x3554_s)))
    }
    val x3557_dsp0 = MemoryPipeline(name="x3557_dsp0",parent="x3592") { implicit CU => 
      val x3561 = CU.temp(None)
      val x3566 = ScalarFIFO(size=1,name="x3566").wtPort(x3557_x3566_s)
      val x3560 = CounterChain.copy("x3567_0", "x3560")
      val x3581 = CounterChain.copy("x3586_0", "x3581")
      val x3557 = SRAM(size=1920,name="x3557",banking = Strided(1)).wtPort(x3566.readPort).rdPort(x3557_0_s).rdAddr(x3581(0))
      WAStage(operands=List(CU.ctr(x3560(0)), Const(96)), op=FixMul, results=List(x3561))
      WAStage(operands=List(x3561, CU.ctr(x3560(1))), op=FixAdd, results=List(x3557.writeAddr))
    }
    val x3567_0 = Pipeline(name="x3567_0",parent=x3592) { implicit CU => 
      val x3564 = ScalarFIFO(size=1,name="x3564").wtPort(x3390_0_s)
      val ctr17 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr18 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x3560 = CounterChain(name = "x3560", ctr17, ctr18).iter(1920)
      Stage(operands=List(CU.ctr(x3560(0)), Const(96)), op=FixMul, results=List())
      Stage(operands=List(CU.load(x3564)), op=Bypass, results=List(CU.scalarOut(x3557_x3566_s)))
    }
    val x3591 = StreamController(name="x3591",parent=x3592) { implicit CU => 
      val x3591_unit = CounterChain(name = "x3591_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3587 = Sequential(name="x3587",parent=x3591) { implicit CU => 
      val x3587_unit = CounterChain(name = "x3587_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3579_0 = Pipeline(name="x3579_0",parent=x3587) { implicit CU => 
      val x3572 = CU.temp(None)
      val x3574 = ScalarBuffer(name="x3574").wtPort(centroids_da)
      val x3579_unit = CounterChain(name = "x3579_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x3572))
      Stage(operands=List(x3572, CU.load(x3574)), op=FixAdd, results=List(CU.scalarOut(x3568_b3733_x3578_b3735_s)))
      Stage(operands=List(Const(7680)), op=Bypass, results=List(CU.scalarOut(x3568_b3734_x3578_b3736_s)))
    }
    val x3586_0 = Pipeline(name="x3586_0",parent=x3587) { implicit CU => 
      val x3582 = ScalarFIFO(size=1,name="x3582").wtPort(x3557_0_s)
      val ctr19 = Counter(min=Const(0), max=Const(1920), step=Const(1), par=16) // Counter
      val x3581 = CounterChain(name = "x3581", ctr19).iter(120)
      Stage(operands=List(CU.load(x3582)), op=Bypass, results=List(CU.vecOut(x3569_x3585_v)))
    }
    val x3588 = MemoryController(name="x3588",parent=x3591,offchip=centroids_oc, mctpe=TileStore) { implicit CU => 
      val x3568_b3734 = ScalarFIFO(size=1,name="size").wtPort(x3568_b3734_x3578_b3736_s)
      val x3569 = VectorFIFO(size=1,name="data").wtPort(x3569_x3585_v)
      val x3568_b3733 = ScalarFIFO(size=1,name="offset").wtPort(x3568_b3733_x3578_b3735_s)
    }
    
  }
}
