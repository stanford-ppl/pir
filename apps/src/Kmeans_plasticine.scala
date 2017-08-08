import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object Kmeans_plasticine extends PIRApp {
  def main(top:Top) = {
    val points_da = DRAMAddress("points", "points")
    val centroids_da = DRAMAddress("centroids", "centroids")
    val bus_435_v = Vector("bus_435")
    val centroids_oc = OffChip("centroids")
    val x3384_x3384_dsp2_bank0_s = Scalar("x3384_x3384_dsp2_bank0")
    val x3455_x3477_s = Scalar("x3455_x3477")
    val x3384_x3384_dsp0_bank0_s = Scalar("x3384_x3384_dsp0_bank0")
    val x3421_b3695_x3436_b3697_s = Scalar("x3421_b3695_x3436_b3697")
    val x3493_x3506_s = Scalar("x3493_x3506")
    val x3387_b3690_x3399_b3692_s = Scalar("x3387_b3690_x3399_b3692")
    val x3416_x3416_dsp1_bank0_s = Scalar("x3416_x3416_dsp1_bank0")
    val iters_argin = ArgIn("iters")
    val x3561_b3726_x3571_b3728_s = Scalar("x3561_b3726_x3571_b3728")
    val x3451_x3490_s = Scalar("x3451_x3490")
    val x3382_x3382_dsp0_bank0_s = Scalar("x3382_x3382_dsp0_bank0")
    val points_oc = OffChip("points")
    val x3527_x3534_s = Scalar("x3527_x3534")
    val x3416_x3416_dsp0_bank0_s = Scalar("x3416_x3416_dsp0_bank0")
    val x3383_x3547_s = Scalar("x3383_x3547")
    val x3383_x3383_dsp0_bank0_s = Scalar("x3383_x3383_dsp0_bank0")
    val x3493_x3493_dsp0_bank0_s = Scalar("x3493_x3493_dsp0_bank0")
    val N_argin = ArgIn("N").bound(960000)
    val x3383_x3383_dsp1_bank0_s = Scalar("x3383_x3383_dsp1_bank0")
    val x3384_x3523_s = Scalar("x3384_x3523")
    val x3550_x3550_dsp0_bank0_s = Scalar("x3550_x3550_dsp0_bank0")
    val x3527_x3527_dsp0_bank0_s = Scalar("x3527_x3527_dsp0_bank0")
    val x3561_b3725_x3571_b3727_s = Scalar("x3561_b3725_x3571_b3727")
    val x3388_x3401_data_s = Scalar("x3388_x3401_data")
    val x3422_x3438_data_s = Scalar("x3422_x3438_data")
    val x3384_x3384_dsp1_bank0_s = Scalar("x3384_x3384_dsp1_bank0")
    val x3387_b3689_x3399_b3691_s = Scalar("x3387_b3689_x3399_b3691")
    val x3421_b3696_x3436_b3698_s = Scalar("x3421_b3696_x3436_b3698")
    val x3585 = Sequential(name="x3585",parent=top) { implicit CU => 
      val x3585_unit = CounterChain(name = "x3585_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3584 = Sequential(name="x3584",parent=x3585) { implicit CU => 
      val x3584_unit = CounterChain(name = "x3584_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3382_dsp0_bank0 = MemoryPipeline(name="x3382_dsp0_bank0",parent="x3584") { implicit CU => 
      val b3693 = CU.temp(None)
      val b3701 = CU.temp(None)
      val x3407 = ScalarFIFO(size=1,name="x3407").wtPort(x3388_x3401_data_s)
      val x3457 = CounterChain.copy("x3478", "x3457")
      val x3386 = CounterChain.copy("x3409", "x3386")
      val x3454 = CounterChain.copy("x3492", "x3454")
      val x3403 = CounterChain.copy("x3408", "x3403")
      val x3382 = SRAM(size=1920,name="x3382",banking = Strided(1)).wtPort(x3407.readPort).rdPort(x3382_x3382_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x3386(0)), Const(96)), op=FixMul, results=List(b3693))
      WAStage(operands=List(b3693, CU.ctr(x3403(0))), op=FixAdd, results=List(x3382.writeAddr))
      RAStage(operands=List(CU.ctr(x3454(0)), Const(96)), op=FixMul, results=List(b3701))
      RAStage(operands=List(b3701, CU.ctr(x3457(0))), op=FixAdd, results=List(x3382.readAddr))
    }
    val x3383_dsp0_bank0 = MemoryPipeline(name="x3383_dsp0_bank0",parent="x3584") { implicit CU => 
      val b3721 = CU.temp(None)
      val b3723 = CU.temp(None)
      val x3547 = ScalarFIFO(size=1,name="x3547").wtPort(x3383_x3547_s)
      val x3538 = CounterChain.copy("x3548_0", "x3538")
      val x3553 = CounterChain.copy("x3560_0", "x3553")
      val x3383 = SRAM(size=1920,name="x3383",banking = Strided(1)).wtPort(x3547.readPort).rdPort(x3383_x3383_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x3538(0)), Const(96)), op=FixMul, results=List(b3721))
      WAStage(operands=List(b3721, CU.ctr(x3538(1))), op=FixAdd, results=List(x3383.writeAddr))
      RAStage(operands=List(CU.ctr(x3553(0)), Const(96)), op=FixMul, results=List(b3723))
      RAStage(operands=List(b3723, CU.ctr(x3553(1))), op=FixAdd, results=List(x3383.readAddr))
    }
    val x3383_dsp1_bank0 = MemoryPipeline(name="x3383_dsp1_bank0",parent="x3584") { implicit CU => 
      val b3721 = CU.temp(None)
      val b3703 = CU.temp(None)
      val x3547 = ScalarFIFO(size=1,name="x3547").wtPort(x3383_x3547_s)
      val x3457 = CounterChain.copy("x3478", "x3457")
      val x3454 = CounterChain.copy("x3492", "x3454")
      val x3538 = CounterChain.copy("x3548_0", "x3538")
      val x3383 = SRAM(size=1920,name="x3383",banking = Strided(1)).wtPort(x3547.readPort).rdPort(x3383_x3383_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x3538(0)), Const(96)), op=FixMul, results=List(b3721))
      WAStage(operands=List(b3721, CU.ctr(x3538(1))), op=FixAdd, results=List(x3383.writeAddr))
      RAStage(operands=List(CU.ctr(x3454(0)), Const(96)), op=FixMul, results=List(b3703))
      RAStage(operands=List(b3703, CU.ctr(x3457(0))), op=FixAdd, results=List(x3383.readAddr))
    }
    val x3384_dsp0_bank0 = MemoryPipeline(name="x3384_dsp0_bank0",parent="x3525") { implicit CU => 
      val b3715 = CU.temp(None)
      val b3719 = CU.temp(None)
      val x3523 = ScalarFIFO(size=1,name="x3523").wtPort(x3384_x3523_s)
      val x3510 = CounterChain.copy("x3524_0", "x3510")
      val x3538 = CounterChain.copy("x3548_0", "x3538")
      val x3384 = SRAM(size=1920,name="x3384",banking = Strided(1)).wtPort(x3523.readPort).rdPort(x3384_x3384_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x3510(0)), Const(96)), op=FixMul, results=List(b3715))
      WAStage(operands=List(b3715, CU.ctr(x3510(1))), op=FixAdd, results=List(x3384.writeAddr))
      RAStage(operands=List(CU.ctr(x3538(0)), Const(96)), op=FixMul, results=List(b3719))
      RAStage(operands=List(b3719, CU.ctr(x3538(1))), op=FixAdd, results=List(x3384.readAddr))
    }
    val x3384_dsp1_bank0 = MemoryPipeline(name="x3384_dsp1_bank0",parent="x3525") { implicit CU => 
      val b3715 = CU.temp(None)
      val b3717 = CU.temp(None)
      val x3523 = ScalarFIFO(size=1,name="x3523").wtPort(x3384_x3523_s)
      val x3510 = CounterChain.copy("x3524_0", "x3510")
      val x3529 = CounterChain.copy("x3535_0", "x3529")
      val x3384 = SRAM(size=1920,name="x3384",banking = Strided(1)).wtPort(x3523.readPort).rdPort(x3384_x3384_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x3510(0)), Const(96)), op=FixMul, results=List(b3715))
      WAStage(operands=List(b3715, CU.ctr(x3510(1))), op=FixAdd, results=List(x3384.writeAddr))
      RAStage(operands=List(CU.ctr(x3529(0)), Const(96)), op=FixMul, results=List(b3717))
      RAStage(operands=List(b3717, Const(95)), op=FixAdd, results=List(x3384.readAddr))
    }
    val x3384_dsp2_bank0 = MemoryPipeline(name="x3384_dsp2_bank0",parent="x3525") { implicit CU => 
      val b3713 = CU.temp(None)
      val b3715 = CU.temp(None)
      val x3523 = ScalarFIFO(size=1,name="x3523").wtPort(x3384_x3523_s)
      val x3510 = CounterChain.copy("x3524_0", "x3510")
      val x3384 = SRAM(size=1920,name="x3384",banking = Strided(1)).wtPort(x3523.readPort).rdPort(x3384_x3384_dsp2_bank0_s)
      WAStage(operands=List(CU.ctr(x3510(0)), Const(96)), op=FixMul, results=List(b3715))
      WAStage(operands=List(b3715, CU.ctr(x3510(1))), op=FixAdd, results=List(x3384.writeAddr))
      RAStage(operands=List(CU.ctr(x3510(0)), Const(96)), op=FixMul, results=List(b3713))
      RAStage(operands=List(b3713, CU.ctr(x3510(1))), op=FixAdd, results=List(x3384.readAddr))
    }
    val x3409 = StreamController(name="x3409",parent=x3584) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3386 = CounterChain(name = "x3386", ctr1).iter(20)
    }
    val x3400_0 = Pipeline(name="x3400_0",parent=x3409) { implicit CU => 
      val x3390 = CU.temp(None)
      val x3392 = CU.temp(None)
      val x3393 = CU.temp(None)
      val x3395 = ScalarBuffer(name="x3395").wtPort(points_da)
      val x3386 = CounterChain.copy("x3409", "x3386")
      val x3400_unit = CounterChain(name = "x3400_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x3386(0)), Const(96)), op=FixMul, results=List(x3390))
      Stage(operands=List(x3390, Const(0)), op=FixAdd, results=List(x3392))
      Stage(operands=List(x3392, Const(2)), op=FixSla, results=List(x3393))
      Stage(operands=List(x3393, CU.load(x3395)), op=FixAdd, results=List(CU.scalarOut(x3387_b3689_x3399_b3691_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x3387_b3690_x3399_b3692_s)))
    }
    val x3401 = MemoryController(name="x3401",parent=x3409,offchip=points_oc, mctpe=TileLoad) { implicit CU => 
      val x3387_b3690 = ScalarFIFO(size=1,name="size").wtPort(x3387_b3690_x3399_b3692_s)
      val x3387_b3689 = ScalarFIFO(size=1,name="offset").wtPort(x3387_b3689_x3399_b3691_s)
      CU.newSout("data", x3388_x3401_data_s)
    }
    val x3408 = Pipeline(name="x3408",parent=x3409) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3403 = CounterChain(name = "x3403", ctr2).iter(6)
    }
    val x3549 = Sequential(name="x3549",parent=x3584) { implicit CU => 
      val x3372 = ScalarBuffer(name="x3372").wtPort(iters_argin)
      val ctr3 = Counter(min=Const(0), max=x3372.readPort, step=Const(1), par=1) // Counter
      val x3412 = CounterChain(name = "x3412", ctr3).iter(1)
    }
    val x3526 = MetaPipeline(name="x3526",parent=x3549) { implicit CU => 
      val x3373 = ScalarBuffer(name="x3373").wtPort(N_argin)
      val ctr4 = Counter(min=Const(0), max=x3373.readPort, step=Const(1), par=1) // Counter
      val x3415 = CounterChain(name = "x3415", ctr4).iter(1)
    }
    val x3416_dsp0_bank0 = MemoryPipeline(name="x3416_dsp0_bank0",parent="x3526") { implicit CU => 
      val b3705 = CU.temp(None)
      val b3699 = CU.temp(None)
      val x3446 = ScalarFIFO(size=1,name="x3446").wtPort(x3422_x3438_data_s)
      val x3457 = CounterChain.copy("x3478", "x3457")
      val x3440 = CounterChain.copy("x3447", "x3440")
      val x3450 = CounterChain.copy("x3525", "x3450")
      val x3420 = CounterChain.copy("x3448", "x3420")
      val x3416 = SRAM(size=96,name="x3416",banking = Strided(1)).wtPort(x3446.readPort).rdPort(x3416_x3416_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x3420(0)), Const(96)), op=FixMul, results=List(b3699))
      WAStage(operands=List(b3699, CU.ctr(x3440(0))), op=FixAdd, results=List(x3416.writeAddr))
      RAStage(operands=List(CU.ctr(x3450(0)), Const(96)), op=FixMul, results=List(b3705))
      RAStage(operands=List(b3705, CU.ctr(x3457(0))), op=FixAdd, results=List(x3416.readAddr))
    }
    val x3416_dsp1_bank0 = MemoryPipeline(name="x3416_dsp1_bank0",parent="x3526") { implicit CU => 
      val b3707 = CU.temp(None)
      val b3699 = CU.temp(None)
      val x3446 = ScalarFIFO(size=1,name="x3446").wtPort(x3422_x3438_data_s)
      val x3440 = CounterChain.copy("x3447", "x3440")
      val x3450 = CounterChain.copy("x3525", "x3450")
      val x3496 = CounterChain.copy("x3507_0", "x3496")
      val x3420 = CounterChain.copy("x3448", "x3420")
      val x3416 = SRAM(size=96,name="x3416",banking = Strided(1)).wtPort(x3446.readPort).rdPort(x3416_x3416_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x3420(0)), Const(96)), op=FixMul, results=List(b3699))
      WAStage(operands=List(b3699, CU.ctr(x3440(0))), op=FixAdd, results=List(x3416.writeAddr))
      RAStage(operands=List(CU.ctr(x3450(0)), Const(96)), op=FixMul, results=List(b3707))
      RAStage(operands=List(b3707, CU.ctr(x3496(1))), op=FixAdd, results=List(x3416.readAddr))
    }
    val x3448 = StreamController(name="x3448",parent=x3526) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3420 = CounterChain(name = "x3420", ctr5).iter(1)
    }
    val x3437_0 = Pipeline(name="x3437_0",parent=x3448) { implicit CU => 
      val x3428 = CU.temp(None)
      val x3423 = CU.temp(None)
      val x3425 = CU.temp(None)
      val x3427 = CU.temp(None)
      val x3430 = ScalarBuffer(name="x3430").wtPort(points_da)
      val x3415 = CounterChain.copy("x3526", "x3415")
      val x3420 = CounterChain.copy("x3448", "x3420")
      val x3437_unit = CounterChain(name = "x3437_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x3415(0)), CU.ctr(x3420(0))), op=FixAdd, results=List(x3423))
      Stage(operands=List(x3423, Const(96)), op=FixMul, results=List(x3425))
      Stage(operands=List(x3425, Const(0)), op=FixAdd, results=List(x3427))
      Stage(operands=List(x3427, Const(2)), op=FixSla, results=List(x3428))
      Stage(operands=List(x3428, CU.load(x3430)), op=FixAdd, results=List(CU.scalarOut(x3421_b3695_x3436_b3697_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x3421_b3696_x3436_b3698_s)))
    }
    val x3438 = MemoryController(name="x3438",parent=x3448,offchip=points_oc, mctpe=TileLoad) { implicit CU => 
      val x3421_b3695 = ScalarFIFO(size=1,name="offset").wtPort(x3421_b3695_x3436_b3697_s)
      val x3421_b3696 = ScalarFIFO(size=1,name="size").wtPort(x3421_b3696_x3436_b3698_s)
      CU.newSout("data", x3422_x3438_data_s)
    }
    val x3447 = Pipeline(name="x3447",parent=x3448) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3440 = CounterChain(name = "x3440", ctr6).iter(6)
    }
    val x3525 = MetaPipeline(name="x3525",parent=x3526) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x3450 = CounterChain(name = "x3450", ctr7).iter(1)
    }
    val x3492 = MetaPipeline(name="x3492",parent=x3525) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3454 = CounterChain(name = "x3454", ctr8).iter(20)
    }
    val x3478 = StreamController(name="x3478",parent=x3492) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3457 = CounterChain(name = "x3457", ctr9).iter(6)
    }
    val x3478_0 = Pipeline(name="x3478_0",parent=x3478) { implicit CU => 
      val x3469 = CU.temp(None)
      val x3466 = CU.temp(None)
      val x3471 = CU.temp(None)
      val x3462 = ScalarFIFO(size=1,name="x3462").wtPort(x3382_x3382_dsp0_bank0_s)
      val x3464 = ScalarFIFO(size=1,name="x3464").wtPort(x3416_x3416_dsp0_bank0_s)
      val x3463 = ScalarFIFO(size=1,name="x3463").wtPort(x3383_x3383_dsp1_bank0_s)
      val x3412 = CounterChain.copy("x3549", "x3412")
      Stage(operands=List(CU.ctr(x3412(0)), Const(0)), op=FixEql, results=List(x3466))
      Stage(operands=List(x3466, CU.load(x3462), CU.load(x3463)), op=Mux, results=List(x3469))
      Stage(operands=List(CU.load(x3464), x3469), op=FixSub, results=List(x3471))
      Stage(operands=List(x3471, x3471), op=FixMul, results=List(CU.vecOut(bus_435_v)))
    }
    val x3478_1 = Pipeline(name="x3478_1",parent=x3478) { implicit CU => 
      val rr435 = VectorFIFO(size=1,name="rr435").wtPort(bus_435_v)
      Stage(operands=List(CU.load(rr435)), op=Bypass, results=List(CU.reduce))
      val (_, rr437) = Stage.reduce(op=FixAdd, init=Const(0), accumParent="x3478")
      Stage(operands=List(rr437), op=Bypass, results=List(CU.scalarOut(x3455_x3477_s)))
    }
    val x3491_0 = Pipeline(name="x3491_0",parent=x3492) { implicit CU => 
      val x3452 = CU.temp(Some(100000))
      val x3487 = CU.temp(None)
      val x3455 = ScalarBuffer(name="x3455").wtPort(x3455_x3477_s)
      val x3451 = CU.temp(Some(0))
      val x3454 = CounterChain.copy("x3492", "x3454")
      val x3491_unit = CounterChain(name = "x3491_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(x3452, CU.load(x3455)), op=FixEql, results=List(x3487))
      Stage(operands=List(x3487, CU.ctr(x3454(0)), x3451), op=Mux, results=List(x3451, CU.scalarOut(x3451_x3490_s)))
    }
    val x3493_dsp0_bank0 = MemoryPipeline(name="x3493_dsp0_bank0",parent="x3525") { implicit CU => 
      val b3709 = CU.temp(None)
      val b3711 = CU.temp(None)
      val x3506 = ScalarFIFO(size=1,name="x3506").wtPort(x3493_x3506_s)
      val x3510 = CounterChain.copy("x3524_0", "x3510")
      val x3496 = CounterChain.copy("x3507_0", "x3496")
      val x3493 = SRAM(size=1920,name="x3493",banking = Strided(1)).wtPort(x3506.readPort).rdPort(x3493_x3493_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x3496(0)), Const(96)), op=FixMul, results=List(b3709))
      WAStage(operands=List(b3709, CU.ctr(x3496(1))), op=FixAdd, results=List(x3493.writeAddr))
      RAStage(operands=List(CU.ctr(x3510(0)), Const(96)), op=FixMul, results=List(b3711))
      RAStage(operands=List(b3711, CU.ctr(x3510(1))), op=FixAdd, results=List(x3493.readAddr))
    }
    val x3507_0 = Pipeline(name="x3507_0",parent=x3525) { implicit CU => 
      val x3504 = CU.temp(None)
      val x3501 = ScalarFIFO(size=1,name="x3501").wtPort(x3416_x3416_dsp1_bank0_s)
      val x3451 = ScalarBuffer(name="x3451").wtPort(x3451_x3490_s)
      val ctr10 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr11 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3496 = CounterChain(name = "x3496", ctr10, ctr11).iter(120)
      Stage(operands=List(CU.ctr(x3496(0)), CU.load(x3451)), op=FixEql, results=List(x3504))
      Stage(operands=List(x3504, CU.load(x3501), Const(0)), op=Mux, results=List(CU.scalarOut(x3493_x3506_s)))
    }
    val x3524_0 = Pipeline(name="x3524_0",parent=x3525) { implicit CU => 
      val x3516 = ScalarFIFO(size=1,name="x3516").wtPort(x3384_x3384_dsp2_bank0_s)
      val x3514 = ScalarFIFO(size=1,name="x3514").wtPort(x3493_x3493_dsp0_bank0_s)
      val ctr12 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr13 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x3510 = CounterChain(name = "x3510", ctr12, ctr13).iter(1920)
      Stage(operands=List(CU.load(x3514), CU.load(x3516)), op=FixAdd, results=List(CU.scalarOut(x3384_x3523_s)))
    }
    val x3527_dsp0_bank0 = MemoryPipeline(name="x3527_dsp0_bank0",parent="x3549") { implicit CU => 
      val x3534 = ScalarFIFO(size=1,name="x3534").wtPort(x3527_x3534_s)
      val x3529 = CounterChain.copy("x3535_0", "x3529")
      val x3538 = CounterChain.copy("x3548_0", "x3538")
      val x3527 = SRAM(size=20,name="x3527",banking = Strided(1)).wtPort(x3534.readPort).rdPort(x3527_x3527_dsp0_bank0_s).rdAddr(x3538(0)).wtAddr(x3529(0))
    }
    val x3535_0 = Pipeline(name="x3535_0",parent=x3549) { implicit CU => 
      val x3531 = ScalarFIFO(size=1,name="x3531").wtPort(x3384_x3384_dsp1_bank0_s)
      val ctr14 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x3529 = CounterChain(name = "x3529", ctr14).iter(20)
      Stage(operands=List(CU.load(x3531), Const(1)), op=FixMax, results=List(CU.scalarOut(x3527_x3534_s)))
    }
    val x3548_0 = Pipeline(name="x3548_0",parent=x3549) { implicit CU => 
      val x3545 = CU.temp(None)
      val x3542 = CU.temp(None)
      val x3543 = ScalarFIFO(size=1,name="x3543").wtPort(x3384_x3384_dsp0_bank0_s)
      val x3541 = ScalarFIFO(size=1,name="x3541").wtPort(x3527_x3527_dsp0_bank0_s)
      val ctr15 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr16 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x3538 = CounterChain(name = "x3538", ctr15, ctr16).iter(120)
      Stage(operands=List(CU.load(x3541), Const(0)), op=FixEql, results=List(x3542))
      Stage(operands=List(CU.load(x3543), CU.load(x3541)), op=FixDiv, results=List(x3545))
      Stage(operands=List(x3542, Const(0), x3545), op=Mux, results=List(CU.scalarOut(x3383_x3547_s)))
    }
    val x3550_dsp0_bank0 = MemoryPipeline(name="x3550_dsp0_bank0",parent="x3584") { implicit CU => 
      val x3554 = CU.temp(None)
      val x3559 = ScalarFIFO(size=1,name="x3559").wtPort(x3383_x3383_dsp0_bank0_s)
      val x3553 = CounterChain.copy("x3560_0", "x3553")
      val x3574 = CounterChain.copy("x3579", "x3574")
      val x3550 = SRAM(size=1920,name="x3550",banking = Strided(1)).wtPort(x3559.readPort).rdPort(x3550_x3550_dsp0_bank0_s).rdAddr(x3574(0))
      WAStage(operands=List(CU.ctr(x3553(0)), Const(96)), op=FixMul, results=List(x3554))
      WAStage(operands=List(x3554, CU.ctr(x3553(1))), op=FixAdd, results=List(x3550.writeAddr))
    }
    val x3560_0 = Pipeline(name="x3560_0",parent=x3584) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val ctr18 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x3553 = CounterChain(name = "x3553", ctr17, ctr18).iter(1920)
      Stage(operands=List(CU.ctr(x3553(0)), Const(96)), op=FixMul, results=List())
    }
    val x3583 = StreamController(name="x3583",parent=x3584) { implicit CU => 
      val x3583_unit = CounterChain(name = "x3583_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x3572_0 = Pipeline(name="x3572_0",parent=x3583) { implicit CU => 
      val x3565 = CU.temp(None)
      val x3567 = ScalarBuffer(name="x3567").wtPort(centroids_da)
      val x3572_unit = CounterChain(name = "x3572_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x3565))
      Stage(operands=List(x3565, CU.load(x3567)), op=FixAdd, results=List(CU.scalarOut(x3561_b3725_x3571_b3727_s)))
      Stage(operands=List(Const(7680)), op=Bypass, results=List(CU.scalarOut(x3561_b3726_x3571_b3728_s)))
    }
    val x3579 = Pipeline(name="x3579",parent=x3583) { implicit CU => 
      val ctr19 = Counter(min=Const(0), max=Const(1920), step=Const(1), par=16) // Counter
      val x3574 = CounterChain(name = "x3574", ctr19).iter(120)
    }
    val x3580 = MemoryController(name="x3580",parent=x3583,offchip=centroids_oc, mctpe=TileStore) { implicit CU => 
      val x3561_b3726 = ScalarFIFO(size=1,name="size").wtPort(x3561_b3726_x3571_b3728_s)
      val x3561_b3725 = ScalarFIFO(size=1,name="offset").wtPort(x3561_b3725_x3571_b3727_s)
      val x3562 = ScalarFIFO(size=1,name="data").wtPort(x3550_x3550_dsp0_bank0_s)
    }
    
  }
}
