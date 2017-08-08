import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.spade.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object BlackScholes extends PIRApp {
  def main(top:Top) = {
    val bus_415_v = Vector("bus_415")
    val bus_302_v = Vector("bus_302")
    val bus_425_v = Vector("bus_425")
    val bus_263_v = Vector("bus_263")
    val bus_255_v = Vector("bus_255")
    val bus_266_v = Vector("bus_266")
    val bus_344_v = Vector("bus_344")
    val x6672_b7196_x6681_b7198_s = Scalar("x6672_b7196_x6681_b7198")
    val bus_309_v = Vector("bus_309")
    val bus_313_v = Vector("bus_313")
    val bus_445_v = Vector("bus_445")
    val x6732_b7208_x6741_b7210_s = Scalar("x6732_b7208_x6741_b7210")
    val bus_397_v = Vector("bus_397")
    val bus_439_v = Vector("bus_439")
    val bus_403_v = Vector("bus_403")
    val vol_da = DRAMAddress("vol", "vol")
    val x6653_x6663_data_s = Scalar("x6653_x6663_data")
    val x6629_x6629_dsp0_bank0_s = Scalar("x6629_x6629_dsp0_bank0")
    val bus_315_v = Vector("bus_315")
    val bus_300_v = Vector("bus_300")
    val bus_402_v = Vector("bus_402")
    val bus_431_v = Vector("bus_431")
    val prices_da = DRAMAddress("prices", "prices")
    val bus_299_v = Vector("bus_299")
    val bus_373_v = Vector("bus_373")
    val strike_da = DRAMAddress("strike", "strike")
    val x6672_b7195_x6681_b7197_s = Scalar("x6672_b7195_x6681_b7197")
    val bus_376_v = Vector("bus_376")
    val vol_oc = OffChip("vol")
    val x6633_x6643_data_s = Scalar("x6633_x6643_data")
    val bus_389_v = Vector("bus_389")
    val bus_433_v = Vector("bus_433")
    val x6632_b7188_x6641_b7190_s = Scalar("x6632_b7188_x6641_b7190")
    val optprice_oc = OffChip("optprice")
    val bus_444_v = Vector("bus_444")
    val bus_416_v = Vector("bus_416")
    val x6732_b7207_x6741_b7209_s = Scalar("x6732_b7207_x6741_b7209")
    val x6713_x6723_data_s = Scalar("x6713_x6723_data")
    val bus_240_v = Vector("bus_240")
    val bus_288_v = Vector("bus_288")
    val bus_396_v = Vector("bus_396")
    val bus_354_v = Vector("bus_354")
    val bus_294_v = Vector("bus_294")
    val x6629_x6943_s = Scalar("x6629_x6943")
    val x6712_b7203_x6721_b7205_s = Scalar("x6712_b7203_x6721_b7205")
    val bus_384_v = Vector("bus_384")
    val x6626_x6626_dsp0_bank0_s = Scalar("x6626_x6626_dsp0_bank0")
    val bus_347_v = Vector("bus_347")
    val bus_241_v = Vector("bus_241")
    val bus_401_v = Vector("bus_401")
    val bus_419_v = Vector("bus_419")
    val bus_346_v = Vector("bus_346")
    val bus_356_v = Vector("bus_356")
    val bus_423_v = Vector("bus_423")
    val x6652_b7192_x6661_b7194_s = Scalar("x6652_b7192_x6661_b7194")
    val bus_268_v = Vector("bus_268")
    val bus_301_v = Vector("bus_301")
    val bus_296_v = Vector("bus_296")
    val bus_324_v = Vector("bus_324")
    val bus_407_v = Vector("bus_407")
    val x6692_b7199_x6701_b7201_s = Scalar("x6692_b7199_x6701_b7201")
    val bus_398_v = Vector("bus_398")
    val bus_290_v = Vector("bus_290")
    val bus_429_v = Vector("bus_429")
    val x6947_b7211_x6957_b7213_s = Scalar("x6947_b7211_x6957_b7213")
    val x6692_b7200_x6701_b7202_s = Scalar("x6692_b7200_x6701_b7202")
    val x6947_b7212_x6957_b7214_s = Scalar("x6947_b7212_x6957_b7214")
    val bus_320_v = Vector("bus_320")
    val bus_374_v = Vector("bus_374")
    val bus_256_v = Vector("bus_256")
    val bus_308_v = Vector("bus_308")
    val x6733_x6743_data_s = Scalar("x6733_x6743_data")
    val bus_319_v = Vector("bus_319")
    val bus_369_v = Vector("bus_369")
    val x6673_x6683_data_s = Scalar("x6673_x6683_data")
    val bus_392_v = Vector("bus_392")
    val x6625_x6625_dsp0_bank0_s = Scalar("x6625_x6625_dsp0_bank0")
    val bus_352_v = Vector("bus_352")
    val bus_236_v = Vector("bus_236")
    val bus_307_v = Vector("bus_307")
    val strike_oc = OffChip("strike")
    val times_oc = OffChip("times")
    val times_da = DRAMAddress("times", "times")
    val bus_424_v = Vector("bus_424")
    val bus_248_v = Vector("bus_248")
    val optprice_da = DRAMAddress("optprice", "optprice")
    val bus_254_v = Vector("bus_254")
    val bus_249_v = Vector("bus_249")
    val bus_273_v = Vector("bus_273")
    val bus_327_v = Vector("bus_327")
    val rate_da = DRAMAddress("rate", "rate")
    val x6627_x6627_dsp0_bank0_s = Scalar("x6627_x6627_dsp0_bank0")
    val x6693_x6703_data_s = Scalar("x6693_x6703_data")
    val types_da = DRAMAddress("types", "types")
    val bus_335_v = Vector("bus_335")
    val bus_271_v = Vector("bus_271")
    val x6623_x6623_dsp0_bank0_s = Scalar("x6623_x6623_dsp0_bank0")
    val prices_oc = OffChip("prices")
    val x6712_b7204_x6721_b7206_s = Scalar("x6712_b7204_x6721_b7206")
    val bus_284_v = Vector("bus_284")
    val bus_303_v = Vector("bus_303")
    val bus_311_v = Vector("bus_311")
    val bus_417_v = Vector("bus_417")
    val bus_340_v = Vector("bus_340")
    val bus_435_v = Vector("bus_435")
    val bus_370_v = Vector("bus_370")
    val bus_378_v = Vector("bus_378")
    val x6632_b7187_x6641_b7189_s = Scalar("x6632_b7187_x6641_b7189")
    val bus_385_v = Vector("bus_385")
    val bus_383_v = Vector("bus_383")
    val bus_377_v = Vector("bus_377")
    val N_argin = ArgIn("N").bound(9995328)
    val bus_358_v = Vector("bus_358")
    val bus_367_v = Vector("bus_367")
    val x6628_x6628_dsp0_bank0_s = Scalar("x6628_x6628_dsp0_bank0")
    val bus_437_v = Vector("bus_437")
    val bus_277_v = Vector("bus_277")
    val bus_372_v = Vector("bus_372")
    val types_oc = OffChip("types")
    val bus_282_v = Vector("bus_282")
    val bus_348_v = Vector("bus_348")
    val bus_404_v = Vector("bus_404")
    val x6624_x6624_dsp0_bank0_s = Scalar("x6624_x6624_dsp0_bank0")
    val bus_368_v = Vector("bus_368")
    val bus_279_v = Vector("bus_279")
    val rate_oc = OffChip("rate")
    val bus_413_v = Vector("bus_413")
    val bus_361_v = Vector("bus_361")
    val bus_395_v = Vector("bus_395")
    val bus_379_v = Vector("bus_379")
    val bus_262_v = Vector("bus_262")
    val bus_305_v = Vector("bus_305")
    val x6652_b7191_x6661_b7193_s = Scalar("x6652_b7191_x6661_b7193")
    val x6972 = Sequential(name="x6972",parent=top) { implicit CU => 
      val x6972_unit = CounterChain(name = "x6972_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6971 = MetaPipeline(name="x6971",parent=x6972) { implicit CU => 
      val x6597 = ScalarBuffer(name="x6597").wtPort(N_argin)
      val ctr1 = Counter(min=Const(0), max=x6597.readPort, step=Const(16), par=1) // Counter
      val x6622 = CounterChain(name = "x6622", ctr1).iter(1)
    }
    val x6623_dsp0_bank0 = MemoryPipeline(name="x6623_dsp0_bank0",parent="x6971") { implicit CU => 
      val x6649 = ScalarFIFO(size=1,name="x6649").wtPort(x6633_x6643_data_s)
      val x6645 = CounterChain.copy("x6650", "x6645")
      val x6754 = CounterChain.copy("x6944", "x6754")
      val x6623 = SRAM(size=16,name="x6623",banking = Strided(1)).wtPort(x6649.readPort).rdPort(x6623_x6623_dsp0_bank0_s).rdAddr(x6754(0)).wtAddr(x6645(0))
    }
    val x6624_dsp0_bank0 = MemoryPipeline(name="x6624_dsp0_bank0",parent="x6971") { implicit CU => 
      val x6669 = ScalarFIFO(size=1,name="x6669").wtPort(x6653_x6663_data_s)
      val x6665 = CounterChain.copy("x6670", "x6665")
      val x6754 = CounterChain.copy("x6944", "x6754")
      val x6624 = SRAM(size=16,name="x6624",banking = Strided(1)).wtPort(x6669.readPort).rdPort(x6624_x6624_dsp0_bank0_s).rdAddr(x6754(0)).wtAddr(x6665(0))
    }
    val x6625_dsp0_bank0 = MemoryPipeline(name="x6625_dsp0_bank0",parent="x6971") { implicit CU => 
      val x6689 = ScalarFIFO(size=1,name="x6689").wtPort(x6673_x6683_data_s)
      val x6685 = CounterChain.copy("x6690", "x6685")
      val x6754 = CounterChain.copy("x6944", "x6754")
      val x6625 = SRAM(size=16,name="x6625",banking = Strided(1)).wtPort(x6689.readPort).rdPort(x6625_x6625_dsp0_bank0_s).rdAddr(x6754(0)).wtAddr(x6685(0))
    }
    val x6626_dsp0_bank0 = MemoryPipeline(name="x6626_dsp0_bank0",parent="x6971") { implicit CU => 
      val x6709 = ScalarFIFO(size=1,name="x6709").wtPort(x6693_x6703_data_s)
      val x6705 = CounterChain.copy("x6710", "x6705")
      val x6754 = CounterChain.copy("x6944", "x6754")
      val x6626 = SRAM(size=16,name="x6626",banking = Strided(1)).wtPort(x6709.readPort).rdPort(x6626_x6626_dsp0_bank0_s).rdAddr(x6754(0)).wtAddr(x6705(0))
    }
    val x6627_dsp0_bank0 = MemoryPipeline(name="x6627_dsp0_bank0",parent="x6971") { implicit CU => 
      val x6729 = ScalarFIFO(size=1,name="x6729").wtPort(x6713_x6723_data_s)
      val x6725 = CounterChain.copy("x6730", "x6725")
      val x6754 = CounterChain.copy("x6944", "x6754")
      val x6627 = SRAM(size=16,name="x6627",banking = Strided(1)).wtPort(x6729.readPort).rdPort(x6627_x6627_dsp0_bank0_s).rdAddr(x6754(0)).wtAddr(x6725(0))
    }
    val x6628_dsp0_bank0 = MemoryPipeline(name="x6628_dsp0_bank0",parent="x6971") { implicit CU => 
      val x6749 = ScalarFIFO(size=1,name="x6749").wtPort(x6733_x6743_data_s)
      val x6745 = CounterChain.copy("x6750", "x6745")
      val x6754 = CounterChain.copy("x6944", "x6754")
      val x6628 = SRAM(size=16,name="x6628",banking = Strided(1)).wtPort(x6749.readPort).rdPort(x6628_x6628_dsp0_bank0_s).rdAddr(x6754(0)).wtAddr(x6745(0))
    }
    val x6629_dsp0_bank0 = MemoryPipeline(name="x6629_dsp0_bank0",parent="x6971") { implicit CU => 
      val x6943 = ScalarFIFO(size=1,name="x6943").wtPort(x6629_x6943_s)
      val x6754 = CounterChain.copy("x6944", "x6754")
      val x6960 = CounterChain.copy("x6966", "x6960")
      val x6629 = SRAM(size=16,name="x6629",banking = Strided(1)).wtPort(x6943.readPort).rdPort(x6629_x6629_dsp0_bank0_s).rdAddr(x6960(0)).wtAddr(x6754(0))
    }
    val x6651 = StreamController(name="x6651",parent=x6971) { implicit CU => 
      val x6651_unit = CounterChain(name = "x6651_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6642_0 = Pipeline(name="x6642_0",parent=x6651) { implicit CU => 
      val x6635 = CU.temp(None)
      val x6637 = ScalarBuffer(name="x6637").wtPort(types_da)
      val x6622 = CounterChain.copy("x6971", "x6622")
      val x6642_unit = CounterChain(name = "x6642_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6622(0)), Const(2)), op=FixSla, results=List(x6635))
      Stage(operands=List(x6635, CU.load(x6637)), op=FixAdd, results=List(CU.scalarOut(x6632_b7187_x6641_b7189_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x6632_b7188_x6641_b7190_s)))
    }
    val x6643 = MemoryController(name="x6643",parent=x6651,offchip=types_oc, mctpe=TileLoad) { implicit CU => 
      val x6632_b7188 = ScalarFIFO(size=1,name="size").wtPort(x6632_b7188_x6641_b7190_s)
      val x6632_b7187 = ScalarFIFO(size=1,name="offset").wtPort(x6632_b7187_x6641_b7189_s)
      CU.newSout("data", x6633_x6643_data_s)
    }
    val x6650 = Pipeline(name="x6650",parent=x6651) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x6645 = CounterChain(name = "x6645", ctr2).iter(1)
    }
    val x6671 = StreamController(name="x6671",parent=x6971) { implicit CU => 
      val x6671_unit = CounterChain(name = "x6671_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6662_0 = Pipeline(name="x6662_0",parent=x6671) { implicit CU => 
      val x6655 = CU.temp(None)
      val x6657 = ScalarBuffer(name="x6657").wtPort(prices_da)
      val x6622 = CounterChain.copy("x6971", "x6622")
      val x6662_unit = CounterChain(name = "x6662_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6622(0)), Const(2)), op=FixSla, results=List(x6655))
      Stage(operands=List(x6655, CU.load(x6657)), op=FixAdd, results=List(CU.scalarOut(x6652_b7191_x6661_b7193_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x6652_b7192_x6661_b7194_s)))
    }
    val x6663 = MemoryController(name="x6663",parent=x6671,offchip=prices_oc, mctpe=TileLoad) { implicit CU => 
      val x6652_b7192 = ScalarFIFO(size=1,name="size").wtPort(x6652_b7192_x6661_b7194_s)
      val x6652_b7191 = ScalarFIFO(size=1,name="offset").wtPort(x6652_b7191_x6661_b7193_s)
      CU.newSout("data", x6653_x6663_data_s)
    }
    val x6670 = Pipeline(name="x6670",parent=x6671) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x6665 = CounterChain(name = "x6665", ctr3).iter(1)
    }
    val x6691 = StreamController(name="x6691",parent=x6971) { implicit CU => 
      val x6691_unit = CounterChain(name = "x6691_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6682_0 = Pipeline(name="x6682_0",parent=x6691) { implicit CU => 
      val x6675 = CU.temp(None)
      val x6677 = ScalarBuffer(name="x6677").wtPort(strike_da)
      val x6622 = CounterChain.copy("x6971", "x6622")
      val x6682_unit = CounterChain(name = "x6682_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6622(0)), Const(2)), op=FixSla, results=List(x6675))
      Stage(operands=List(x6675, CU.load(x6677)), op=FixAdd, results=List(CU.scalarOut(x6672_b7195_x6681_b7197_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x6672_b7196_x6681_b7198_s)))
    }
    val x6683 = MemoryController(name="x6683",parent=x6691,offchip=strike_oc, mctpe=TileLoad) { implicit CU => 
      val x6672_b7196 = ScalarFIFO(size=1,name="size").wtPort(x6672_b7196_x6681_b7198_s)
      val x6672_b7195 = ScalarFIFO(size=1,name="offset").wtPort(x6672_b7195_x6681_b7197_s)
      CU.newSout("data", x6673_x6683_data_s)
    }
    val x6690 = Pipeline(name="x6690",parent=x6691) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x6685 = CounterChain(name = "x6685", ctr4).iter(1)
    }
    val x6711 = StreamController(name="x6711",parent=x6971) { implicit CU => 
      val x6711_unit = CounterChain(name = "x6711_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6702_0 = Pipeline(name="x6702_0",parent=x6711) { implicit CU => 
      val x6695 = CU.temp(None)
      val x6697 = ScalarBuffer(name="x6697").wtPort(rate_da)
      val x6622 = CounterChain.copy("x6971", "x6622")
      val x6702_unit = CounterChain(name = "x6702_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6622(0)), Const(2)), op=FixSla, results=List(x6695))
      Stage(operands=List(x6695, CU.load(x6697)), op=FixAdd, results=List(CU.scalarOut(x6692_b7199_x6701_b7201_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x6692_b7200_x6701_b7202_s)))
    }
    val x6703 = MemoryController(name="x6703",parent=x6711,offchip=rate_oc, mctpe=TileLoad) { implicit CU => 
      val x6692_b7200 = ScalarFIFO(size=1,name="size").wtPort(x6692_b7200_x6701_b7202_s)
      val x6692_b7199 = ScalarFIFO(size=1,name="offset").wtPort(x6692_b7199_x6701_b7201_s)
      CU.newSout("data", x6693_x6703_data_s)
    }
    val x6710 = Pipeline(name="x6710",parent=x6711) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x6705 = CounterChain(name = "x6705", ctr5).iter(1)
    }
    val x6731 = StreamController(name="x6731",parent=x6971) { implicit CU => 
      val x6731_unit = CounterChain(name = "x6731_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6722_0 = Pipeline(name="x6722_0",parent=x6731) { implicit CU => 
      val x6715 = CU.temp(None)
      val x6717 = ScalarBuffer(name="x6717").wtPort(vol_da)
      val x6622 = CounterChain.copy("x6971", "x6622")
      val x6722_unit = CounterChain(name = "x6722_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6622(0)), Const(2)), op=FixSla, results=List(x6715))
      Stage(operands=List(x6715, CU.load(x6717)), op=FixAdd, results=List(CU.scalarOut(x6712_b7203_x6721_b7205_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x6712_b7204_x6721_b7206_s)))
    }
    val x6723 = MemoryController(name="x6723",parent=x6731,offchip=vol_oc, mctpe=TileLoad) { implicit CU => 
      val x6712_b7203 = ScalarFIFO(size=1,name="offset").wtPort(x6712_b7203_x6721_b7205_s)
      val x6712_b7204 = ScalarFIFO(size=1,name="size").wtPort(x6712_b7204_x6721_b7206_s)
      CU.newSout("data", x6713_x6723_data_s)
    }
    val x6730 = Pipeline(name="x6730",parent=x6731) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x6725 = CounterChain(name = "x6725", ctr6).iter(1)
    }
    val x6751 = StreamController(name="x6751",parent=x6971) { implicit CU => 
      val x6751_unit = CounterChain(name = "x6751_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6742_0 = Pipeline(name="x6742_0",parent=x6751) { implicit CU => 
      val x6735 = CU.temp(None)
      val x6737 = ScalarBuffer(name="x6737").wtPort(times_da)
      val x6622 = CounterChain.copy("x6971", "x6622")
      val x6742_unit = CounterChain(name = "x6742_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6622(0)), Const(2)), op=FixSla, results=List(x6735))
      Stage(operands=List(x6735, CU.load(x6737)), op=FixAdd, results=List(CU.scalarOut(x6732_b7207_x6741_b7209_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x6732_b7208_x6741_b7210_s)))
    }
    val x6743 = MemoryController(name="x6743",parent=x6751,offchip=times_oc, mctpe=TileLoad) { implicit CU => 
      val x6732_b7207 = ScalarFIFO(size=1,name="offset").wtPort(x6732_b7207_x6741_b7209_s)
      val x6732_b7208 = ScalarFIFO(size=1,name="size").wtPort(x6732_b7208_x6741_b7210_s)
      CU.newSout("data", x6733_x6743_data_s)
    }
    val x6750 = Pipeline(name="x6750",parent=x6751) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x6745 = CounterChain(name = "x6745", ctr7).iter(1)
    }
    val x6944 = StreamController(name="x6944",parent=x6971) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x6754 = CounterChain(name = "x6754", ctr8).iter(1)
    }
    val x6944_0 = Pipeline(name="x6944_0",parent=x6944) { implicit CU => 
      val x6768 = CU.temp(None)
      val x6770 = CU.temp(None)
      val x6769 = CU.temp(None)
      val x6771 = CU.temp(None)
      val x6758 = ScalarFIFO(size=1,name="x6758").wtPort(x6625_x6625_dsp0_bank0_s)
      val x6756 = ScalarFIFO(size=1,name="x6756").wtPort(x6624_x6624_dsp0_bank0_s)
      Stage(operands=List(CU.load(x6756), CU.load(x6758)), op=FltDiv, results=List(x6768))
      Stage(operands=List(x6768, Const(1)), op=FltSub, results=List(x6769, CU.vecOut(bus_236_v)))
      Stage(operands=List(x6769, x6769), op=FltMul, results=List(x6770))
      Stage(operands=List(x6770, Const(2)), op=FltDiv, results=List(x6771))
      Stage(operands=List(x6769, x6771), op=FltSub, results=List(CU.vecOut(bus_240_v)))
      Stage(operands=List(x6770, x6769), op=FltMul, results=List(CU.vecOut(bus_241_v)))
    }
    val x6944_1 = Pipeline(name="x6944_1",parent=x6944) { implicit CU => 
      val x6775 = CU.temp(None)
      val x6774 = CU.temp(None)
      val x6777 = CU.temp(None)
      val x6776 = CU.temp(None)
      val x6769 = VectorFIFO(size=1,name="x6769").wtPort(bus_236_v)
      val x6773 = VectorFIFO(size=1,name="x6773").wtPort(bus_241_v)
      val x6762 = ScalarFIFO(size=1,name="x6762").wtPort(x6627_x6627_dsp0_bank0_s)
      val x6772 = VectorFIFO(size=1,name="x6772").wtPort(bus_240_v)
      Stage(operands=List(CU.load(x6773), Const(3)), op=FltDiv, results=List(x6774))
      Stage(operands=List(CU.load(x6772), x6774), op=FltAdd, results=List(x6775))
      Stage(operands=List(CU.load(x6773), CU.load(x6769)), op=FltMul, results=List(x6776))
      Stage(operands=List(x6776, Const(4)), op=FltDiv, results=List(x6777))
      Stage(operands=List(x6775, x6777), op=FltSub, results=List(CU.vecOut(bus_248_v)))
      Stage(operands=List(CU.load(x6762), CU.load(x6762)), op=FltMul, results=List(CU.vecOut(bus_249_v)))
    }
    val x6944_2 = Pipeline(name="x6944_2",parent=x6944) { implicit CU => 
      val x6781 = CU.temp(None)
      val x6782 = CU.temp(None)
      val x6780 = CU.temp(None)
      val x6764 = ScalarFIFO(size=1,name="x6764").wtPort(x6628_x6628_dsp0_bank0_s)
      val x6778 = VectorFIFO(size=1,name="x6778").wtPort(bus_248_v)
      val x6779 = VectorFIFO(size=1,name="x6779").wtPort(bus_249_v)
      val x6760 = ScalarFIFO(size=1,name="x6760").wtPort(x6626_x6626_dsp0_bank0_s)
      Stage(operands=List(CU.load(x6779), Const(0.5)), op=FltMul, results=List(x6780))
      Stage(operands=List(CU.load(x6760), x6780), op=FltAdd, results=List(x6781))
      Stage(operands=List(x6781, CU.load(x6764)), op=FltMul, results=List(x6782))
      Stage(operands=List(x6782, CU.load(x6778)), op=FltAdd, results=List(CU.vecOut(bus_254_v)))
      Stage(operands=List(CU.load(x6764), Const(2)), op=FltLt, results=List(CU.vecOut(bus_255_v)))
      Stage(operands=List(CU.load(x6764), Const(1)), op=FltSub, results=List(CU.vecOut(bus_256_v)))
    }
    val x6944_3 = Pipeline(name="x6944_3",parent=x6944) { implicit CU => 
      val x6786 = CU.temp(None)
      val x6789 = CU.temp(None)
      val x6788 = CU.temp(None)
      val x6787 = CU.temp(None)
      val x6785 = VectorFIFO(size=1,name="x6785").wtPort(bus_256_v)
      Stage(operands=List(CU.load(x6785), Const(2)), op=FltDiv, results=List(x6786))
      Stage(operands=List(Const(1), x6786), op=FltAdd, results=List(x6787))
      Stage(operands=List(CU.load(x6785), CU.load(x6785)), op=FltMul, results=List(x6788))
      Stage(operands=List(x6788, Const(8)), op=FltDiv, results=List(x6789))
      Stage(operands=List(x6787, x6789), op=FltSub, results=List(CU.vecOut(bus_262_v)))
      Stage(operands=List(x6788, CU.load(x6785)), op=FltMul, results=List(CU.vecOut(bus_263_v)))
    }
    val x6944_4 = Pipeline(name="x6944_4",parent=x6944) { implicit CU => 
      val x6795 = CU.temp(None)
      val x6792 = CU.temp(None)
      val x6790 = VectorFIFO(size=1,name="x6790").wtPort(bus_262_v)
      val x6764 = ScalarFIFO(size=1,name="x6764").wtPort(x6628_x6628_dsp0_bank0_s)
      val x6791 = VectorFIFO(size=1,name="x6791").wtPort(bus_263_v)
      Stage(operands=List(CU.load(x6791), Const(16)), op=FltDiv, results=List(x6792))
      Stage(operands=List(CU.load(x6790), x6792), op=FltAdd, results=List(CU.vecOut(bus_266_v)))
      Stage(operands=List(CU.load(x6764), Const(10)), op=FltLt, results=List(CU.vecOut(bus_268_v)))
      Stage(operands=List(CU.load(x6764), Const(0.22)), op=FltMul, results=List(x6795))
      Stage(operands=List(x6795, Const(1)), op=FltAdd, results=List(CU.vecOut(bus_271_v)))
      Stage(operands=List(CU.load(x6764), Const(100)), op=FltLt, results=List(CU.vecOut(bus_273_v)))
    }
    val x6944_5 = Pipeline(name="x6944_5",parent=x6944) { implicit CU => 
      val x6801 = CU.temp(None)
      val x6798 = CU.temp(None)
      val x6764 = ScalarFIFO(size=1,name="x6764").wtPort(x6628_x6628_dsp0_bank0_s)
      Stage(operands=List(CU.load(x6764), Const(0.08)), op=FltMul, results=List(x6798))
      Stage(operands=List(x6798, Const(2.5)), op=FltAdd, results=List(CU.vecOut(bus_277_v)))
      Stage(operands=List(CU.load(x6764), Const(1000)), op=FltLt, results=List(CU.vecOut(bus_279_v)))
      Stage(operands=List(CU.load(x6764), Const(0.028)), op=FltMul, results=List(x6801))
      Stage(operands=List(x6801, Const(8)), op=FltAdd, results=List(CU.vecOut(bus_282_v)))
      Stage(operands=List(CU.load(x6764), Const(10000)), op=FltLt, results=List(CU.vecOut(bus_284_v)))
    }
    val x6944_6 = Pipeline(name="x6944_6",parent=x6944) { implicit CU => 
      val x6804 = CU.temp(None)
      val x6807 = CU.temp(None)
      val x6764 = ScalarFIFO(size=1,name="x6764").wtPort(x6628_x6628_dsp0_bank0_s)
      Stage(operands=List(CU.load(x6764), Const(0.008)), op=FltMul, results=List(x6804))
      Stage(operands=List(x6804, Const(20)), op=FltAdd, results=List(CU.vecOut(bus_288_v)))
      Stage(operands=List(CU.load(x6764), Const(100000)), op=FltLt, results=List(CU.vecOut(bus_290_v)))
      Stage(operands=List(CU.load(x6764), Const(0.003)), op=FltMul, results=List(x6807))
      Stage(operands=List(x6807, Const(60)), op=FltAdd, results=List(CU.vecOut(bus_294_v)))
      Stage(operands=List(CU.load(x6764), Const(2.0E-4)), op=FltMul, results=List(CU.vecOut(bus_296_v)))
    }
    val x6944_7 = Pipeline(name="x6944_7",parent=x6944) { implicit CU => 
      val x6810 = CU.temp(None)
      val x6809 = VectorFIFO(size=1,name="x6809").wtPort(bus_296_v)
      val x6806 = VectorFIFO(size=1,name="x6806").wtPort(bus_290_v)
      val x6808 = VectorFIFO(size=1,name="x6808").wtPort(bus_294_v)
      Stage(operands=List(CU.load(x6809), Const(300)), op=FltAdd, results=List(x6810))
      Stage(operands=List(CU.load(x6806), CU.load(x6808), x6810), op=Mux, results=List(CU.vecOut(bus_299_v)))
    }
    val x6944_8 = Pipeline(name="x6944_8",parent=x6944) { implicit CU => 
      val x6805 = VectorFIFO(size=1,name="x6805").wtPort(bus_288_v)
      val x6811 = VectorFIFO(size=1,name="x6811").wtPort(bus_299_v)
      val x6803 = VectorFIFO(size=1,name="x6803").wtPort(bus_284_v)
      Stage(operands=List(CU.load(x6803), CU.load(x6805), CU.load(x6811)), op=Mux, results=List(CU.vecOut(bus_300_v)))
    }
    val x6944_9 = Pipeline(name="x6944_9",parent=x6944) { implicit CU => 
      val x6812 = VectorFIFO(size=1,name="x6812").wtPort(bus_300_v)
      val x6802 = VectorFIFO(size=1,name="x6802").wtPort(bus_282_v)
      val x6800 = VectorFIFO(size=1,name="x6800").wtPort(bus_279_v)
      Stage(operands=List(CU.load(x6800), CU.load(x6802), CU.load(x6812)), op=Mux, results=List(CU.vecOut(bus_301_v)))
    }
    val x6944_10 = Pipeline(name="x6944_10",parent=x6944) { implicit CU => 
      val x6813 = VectorFIFO(size=1,name="x6813").wtPort(bus_301_v)
      val x6799 = VectorFIFO(size=1,name="x6799").wtPort(bus_277_v)
      val x6797 = VectorFIFO(size=1,name="x6797").wtPort(bus_273_v)
      Stage(operands=List(CU.load(x6797), CU.load(x6799), CU.load(x6813)), op=Mux, results=List(CU.vecOut(bus_302_v)))
    }
    val x6944_11 = Pipeline(name="x6944_11",parent=x6944) { implicit CU => 
      val x6796 = VectorFIFO(size=1,name="x6796").wtPort(bus_271_v)
      val x6814 = VectorFIFO(size=1,name="x6814").wtPort(bus_302_v)
      val x6794 = VectorFIFO(size=1,name="x6794").wtPort(bus_268_v)
      Stage(operands=List(CU.load(x6794), CU.load(x6796), CU.load(x6814)), op=Mux, results=List(CU.vecOut(bus_303_v)))
    }
    val x6944_12 = Pipeline(name="x6944_12",parent=x6944) { implicit CU => 
      val x6817 = CU.temp(None)
      val x6819 = CU.temp(None)
      val x6818 = CU.temp(None)
      val x6820 = CU.temp(None)
      val x6816 = CU.temp(None)
      val x6815 = VectorFIFO(size=1,name="x6815").wtPort(bus_303_v)
      val x6793 = VectorFIFO(size=1,name="x6793").wtPort(bus_266_v)
      val x6784 = VectorFIFO(size=1,name="x6784").wtPort(bus_255_v)
      val x6762 = ScalarFIFO(size=1,name="x6762").wtPort(x6627_x6627_dsp0_bank0_s)
      val x6783 = VectorFIFO(size=1,name="x6783").wtPort(bus_254_v)
      Stage(operands=List(CU.load(x6784), CU.load(x6793), CU.load(x6815)), op=Mux, results=List(x6816))
      Stage(operands=List(CU.load(x6762), x6816), op=FltMul, results=List(x6817, CU.vecOut(bus_305_v)))
      Stage(operands=List(x6817, x6817), op=FltMul, results=List(x6818))
      Stage(operands=List(CU.load(x6783), x6818), op=FltDiv, results=List(x6819, CU.vecOut(bus_307_v)))
      Stage(operands=List(x6819), op=FltAbs, results=List(x6820, CU.vecOut(bus_308_v)))
      Stage(operands=List(x6820, x6820), op=FltMul, results=List(CU.vecOut(bus_309_v)))
    }
    val x6944_13 = Pipeline(name="x6944_13",parent=x6944) { implicit CU => 
      val x6825 = CU.temp(None)
      val x6822 = CU.temp(None)
      val x6821 = VectorFIFO(size=1,name="x6821").wtPort(bus_309_v)
      Stage(operands=List(CU.load(x6821), Const(-0.05)), op=FltMul, results=List(x6822, CU.vecOut(bus_311_v)))
      Stage(operands=List(x6822, Const(-3.5)), op=FltLt, results=List(CU.vecOut(bus_313_v)))
      Stage(operands=List(x6822, Const(-1.2)), op=FltLt, results=List(CU.vecOut(bus_315_v)))
      Stage(operands=List(x6822, Const(0.1)), op=FltMul, results=List(x6825))
      Stage(operands=List(x6825, Const(0.35)), op=FltAdd, results=List(CU.vecOut(bus_319_v)))
      Stage(operands=List(Const(1), x6822), op=FltAdd, results=List(CU.vecOut(bus_320_v)))
    }
    val x6944_14 = Pipeline(name="x6944_14",parent=x6944) { implicit CU => 
      val x6830 = CU.temp(None)
      val x6828 = CU.temp(None)
      val x6831 = CU.temp(None)
      val x6829 = CU.temp(None)
      val x6832 = CU.temp(None)
      val x6827 = VectorFIFO(size=1,name="x6827").wtPort(bus_320_v)
      val x6822 = VectorFIFO(size=1,name="x6822").wtPort(bus_311_v)
      Stage(operands=List(CU.load(x6822), CU.load(x6822)), op=FltMul, results=List(x6828))
      Stage(operands=List(x6828, Const(2)), op=FltDiv, results=List(x6829))
      Stage(operands=List(CU.load(x6827), x6829), op=FltAdd, results=List(x6830))
      Stage(operands=List(x6828, CU.load(x6822)), op=FltMul, results=List(x6831, CU.vecOut(bus_324_v)))
      Stage(operands=List(x6831, Const(6)), op=FltDiv, results=List(x6832))
      Stage(operands=List(x6830, x6832), op=FltAdd, results=List(CU.vecOut(bus_327_v)))
    }
    val x6944_15 = Pipeline(name="x6944_15",parent=x6944) { implicit CU => 
      val x6834 = CU.temp(None)
      val x6835 = CU.temp(None)
      val x6838 = CU.temp(None)
      val x6836 = CU.temp(None)
      val x6837 = CU.temp(None)
      val x6831 = VectorFIFO(size=1,name="x6831").wtPort(bus_324_v)
      val x6833 = VectorFIFO(size=1,name="x6833").wtPort(bus_327_v)
      val x6822 = VectorFIFO(size=1,name="x6822").wtPort(bus_311_v)
      Stage(operands=List(CU.load(x6831), CU.load(x6822)), op=FltMul, results=List(x6834))
      Stage(operands=List(x6834, Const(24)), op=FltDiv, results=List(x6835))
      Stage(operands=List(CU.load(x6833), x6835), op=FltAdd, results=List(x6836))
      Stage(operands=List(x6834, CU.load(x6822)), op=FltMul, results=List(x6837))
      Stage(operands=List(x6837, Const(120)), op=FltDiv, results=List(x6838))
      Stage(operands=List(x6836, x6838), op=FltAdd, results=List(CU.vecOut(bus_335_v)))
    }
    val x6944_16 = Pipeline(name="x6944_16",parent=x6944) { implicit CU => 
      val x6840 = CU.temp(None)
      val x6841 = CU.temp(None)
      val x6839 = VectorFIFO(size=1,name="x6839").wtPort(bus_335_v)
      val x6826 = VectorFIFO(size=1,name="x6826").wtPort(bus_319_v)
      val x6823 = VectorFIFO(size=1,name="x6823").wtPort(bus_313_v)
      val x6824 = VectorFIFO(size=1,name="x6824").wtPort(bus_315_v)
      Stage(operands=List(CU.load(x6824), CU.load(x6826), CU.load(x6839)), op=Mux, results=List(x6840))
      Stage(operands=List(CU.load(x6823), Const(0), x6840), op=Mux, results=List(x6841))
      Stage(operands=List(x6841, Const(0.3989423)), op=FltMul, results=List(CU.vecOut(bus_340_v)))
    }
    val x6944_17 = Pipeline(name="x6944_17",parent=x6944) { implicit CU => 
      val x6845 = CU.temp(None)
      val x6846 = CU.temp(None)
      val x6844 = CU.temp(None)
      val x6843 = CU.temp(None)
      val x6820 = VectorFIFO(size=1,name="x6820").wtPort(bus_308_v)
      Stage(operands=List(CU.load(x6820), Const(0.2316419)), op=FltMul, results=List(x6843))
      Stage(operands=List(x6843, Const(1)), op=FltAdd, results=List(x6844))
      Stage(operands=List(Const(1), x6844), op=FltDiv, results=List(x6845, CU.vecOut(bus_344_v)))
      Stage(operands=List(x6845, Const(0.31938154)), op=FltMul, results=List(CU.vecOut(bus_346_v)))
      Stage(operands=List(x6845, x6845), op=FltMul, results=List(x6846, CU.vecOut(bus_347_v)))
      Stage(operands=List(x6846, x6845), op=FltMul, results=List(CU.vecOut(bus_348_v)))
    }
    val x6944_18 = Pipeline(name="x6944_18",parent=x6944) { implicit CU => 
      val x6849 = CU.temp(None)
      val x6848 = CU.temp(None)
      val x6847 = VectorFIFO(size=1,name="x6847").wtPort(bus_348_v)
      val x6845 = VectorFIFO(size=1,name="x6845").wtPort(bus_344_v)
      val x6846 = VectorFIFO(size=1,name="x6846").wtPort(bus_347_v)
      Stage(operands=List(CU.load(x6847), CU.load(x6845)), op=FltMul, results=List(x6848))
      Stage(operands=List(x6848, CU.load(x6845)), op=FltMul, results=List(x6849))
      Stage(operands=List(x6849, Const(1.3302745)), op=FltMul, results=List(CU.vecOut(bus_352_v)))
      Stage(operands=List(x6848, Const(-1.8212559)), op=FltMul, results=List(CU.vecOut(bus_354_v)))
      Stage(operands=List(CU.load(x6846), Const(-0.35656378)), op=FltMul, results=List(CU.vecOut(bus_356_v)))
      Stage(operands=List(CU.load(x6847), Const(1.7814779)), op=FltMul, results=List(CU.vecOut(bus_358_v)))
    }
    val x6944_19 = Pipeline(name="x6944_19",parent=x6944) { implicit CU => 
      val x6855 = CU.temp(None)
      val x6856 = CU.temp(None)
      val x6852 = VectorFIFO(size=1,name="x6852").wtPort(bus_358_v)
      val x6851 = VectorFIFO(size=1,name="x6851").wtPort(bus_356_v)
      val x6854 = VectorFIFO(size=1,name="x6854").wtPort(bus_352_v)
      val x6853 = VectorFIFO(size=1,name="x6853").wtPort(bus_354_v)
      Stage(operands=List(CU.load(x6851), CU.load(x6852)), op=FltAdd, results=List(x6855))
      Stage(operands=List(x6855, CU.load(x6853)), op=FltAdd, results=List(x6856))
      Stage(operands=List(x6856, CU.load(x6854)), op=FltAdd, results=List(CU.vecOut(bus_361_v)))
    }
    val x6944_20 = Pipeline(name="x6944_20",parent=x6944) { implicit CU => 
      val x6860 = CU.temp(None)
      val x6859 = CU.temp(None)
      val x6862 = CU.temp(None)
      val x6858 = CU.temp(None)
      val x6861 = CU.temp(None)
      val x6850 = VectorFIFO(size=1,name="x6850").wtPort(bus_346_v)
      val x6857 = VectorFIFO(size=1,name="x6857").wtPort(bus_361_v)
      val x6819 = VectorFIFO(size=1,name="x6819").wtPort(bus_307_v)
      val x6842 = VectorFIFO(size=1,name="x6842").wtPort(bus_340_v)
      Stage(operands=List(CU.load(x6857), CU.load(x6850)), op=FltAdd, results=List(x6858))
      Stage(operands=List(x6858, CU.load(x6842)), op=FltMul, results=List(x6859))
      Stage(operands=List(x6859), op=FltNeg, results=List(x6860))
      Stage(operands=List(x6860, Const(1)), op=FltAdd, results=List(x6861))
      Stage(operands=List(CU.load(x6819), Const(0)), op=FltLt, results=List(x6862))
      Stage(operands=List(x6862, x6859, x6861), op=Mux, results=List(CU.vecOut(bus_367_v)))
    }
    val x6944_21 = Pipeline(name="x6944_21",parent=x6944) { implicit CU => 
      val x6867 = CU.temp(None)
      val x6866 = CU.temp(None)
      val x6865 = CU.temp(None)
      val x6864 = CU.temp(None)
      val x6817 = VectorFIFO(size=1,name="x6817").wtPort(bus_305_v)
      val x6863 = VectorFIFO(size=1,name="x6863").wtPort(bus_367_v)
      val x6756 = ScalarFIFO(size=1,name="x6756").wtPort(x6624_x6624_dsp0_bank0_s)
      val x6819 = VectorFIFO(size=1,name="x6819").wtPort(bus_307_v)
      Stage(operands=List(CU.load(x6756), CU.load(x6863)), op=FltMul, results=List(CU.vecOut(bus_368_v)))
      Stage(operands=List(CU.load(x6819), CU.load(x6817)), op=FltSub, results=List(x6864, CU.vecOut(bus_369_v)))
      Stage(operands=List(x6864), op=FltAbs, results=List(x6865, CU.vecOut(bus_370_v)))
      Stage(operands=List(x6865, x6865), op=FltMul, results=List(x6866))
      Stage(operands=List(x6866, Const(-0.05)), op=FltMul, results=List(x6867, CU.vecOut(bus_372_v)))
      Stage(operands=List(x6867, Const(-3.5)), op=FltLt, results=List(CU.vecOut(bus_373_v)))
    }
    val x6944_22 = Pipeline(name="x6944_22",parent=x6944) { implicit CU => 
      val x6873 = CU.temp(None)
      val x6870 = CU.temp(None)
      val x6867 = VectorFIFO(size=1,name="x6867").wtPort(bus_372_v)
      Stage(operands=List(CU.load(x6867), Const(-1.2)), op=FltLt, results=List(CU.vecOut(bus_374_v)))
      Stage(operands=List(CU.load(x6867), Const(0.1)), op=FltMul, results=List(x6870))
      Stage(operands=List(x6870, Const(0.35)), op=FltAdd, results=List(CU.vecOut(bus_376_v)))
      Stage(operands=List(Const(1), CU.load(x6867)), op=FltAdd, results=List(CU.vecOut(bus_377_v)))
      Stage(operands=List(CU.load(x6867), CU.load(x6867)), op=FltMul, results=List(x6873, CU.vecOut(bus_378_v)))
      Stage(operands=List(x6873, Const(2)), op=FltDiv, results=List(CU.vecOut(bus_379_v)))
    }
    val x6944_23 = Pipeline(name="x6944_23",parent=x6944) { implicit CU => 
      val x6876 = CU.temp(None)
      val x6877 = CU.temp(None)
      val x6875 = CU.temp(None)
      val x6879 = CU.temp(None)
      val x6867 = VectorFIFO(size=1,name="x6867").wtPort(bus_372_v)
      val x6874 = VectorFIFO(size=1,name="x6874").wtPort(bus_379_v)
      val x6872 = VectorFIFO(size=1,name="x6872").wtPort(bus_377_v)
      val x6873 = VectorFIFO(size=1,name="x6873").wtPort(bus_378_v)
      Stage(operands=List(CU.load(x6872), CU.load(x6874)), op=FltAdd, results=List(x6875))
      Stage(operands=List(CU.load(x6873), CU.load(x6867)), op=FltMul, results=List(x6876))
      Stage(operands=List(x6876, Const(6)), op=FltDiv, results=List(x6877))
      Stage(operands=List(x6875, x6877), op=FltAdd, results=List(CU.vecOut(bus_383_v)))
      Stage(operands=List(x6876, CU.load(x6867)), op=FltMul, results=List(x6879, CU.vecOut(bus_384_v)))
      Stage(operands=List(x6879, Const(24)), op=FltDiv, results=List(CU.vecOut(bus_385_v)))
    }
    val x6944_24 = Pipeline(name="x6944_24",parent=x6944) { implicit CU => 
      val x6883 = CU.temp(None)
      val x6881 = CU.temp(None)
      val x6882 = CU.temp(None)
      val x6878 = VectorFIFO(size=1,name="x6878").wtPort(bus_383_v)
      val x6867 = VectorFIFO(size=1,name="x6867").wtPort(bus_372_v)
      val x6880 = VectorFIFO(size=1,name="x6880").wtPort(bus_385_v)
      val x6879 = VectorFIFO(size=1,name="x6879").wtPort(bus_384_v)
      Stage(operands=List(CU.load(x6878), CU.load(x6880)), op=FltAdd, results=List(x6881))
      Stage(operands=List(CU.load(x6879), CU.load(x6867)), op=FltMul, results=List(x6882))
      Stage(operands=List(x6882, Const(120)), op=FltDiv, results=List(x6883))
      Stage(operands=List(x6881, x6883), op=FltAdd, results=List(CU.vecOut(bus_389_v)))
    }
    val x6944_25 = Pipeline(name="x6944_25",parent=x6944) { implicit CU => 
      val x6886 = CU.temp(None)
      val x6885 = CU.temp(None)
      val x6869 = VectorFIFO(size=1,name="x6869").wtPort(bus_374_v)
      val x6884 = VectorFIFO(size=1,name="x6884").wtPort(bus_389_v)
      val x6871 = VectorFIFO(size=1,name="x6871").wtPort(bus_376_v)
      val x6868 = VectorFIFO(size=1,name="x6868").wtPort(bus_373_v)
      Stage(operands=List(CU.load(x6869), CU.load(x6871), CU.load(x6884)), op=Mux, results=List(x6885))
      Stage(operands=List(CU.load(x6868), Const(0), x6885), op=Mux, results=List(x6886))
      Stage(operands=List(x6886, Const(0.3989423)), op=FltMul, results=List(CU.vecOut(bus_392_v)))
    }
    val x6944_26 = Pipeline(name="x6944_26",parent=x6944) { implicit CU => 
      val x6891 = CU.temp(None)
      val x6889 = CU.temp(None)
      val x6888 = CU.temp(None)
      val x6890 = CU.temp(None)
      val x6865 = VectorFIFO(size=1,name="x6865").wtPort(bus_370_v)
      Stage(operands=List(CU.load(x6865), Const(0.2316419)), op=FltMul, results=List(x6888))
      Stage(operands=List(x6888, Const(1)), op=FltAdd, results=List(x6889))
      Stage(operands=List(Const(1), x6889), op=FltDiv, results=List(x6890, CU.vecOut(bus_395_v)))
      Stage(operands=List(x6890, Const(0.31938154)), op=FltMul, results=List(CU.vecOut(bus_396_v)))
      Stage(operands=List(x6890, x6890), op=FltMul, results=List(x6891, CU.vecOut(bus_397_v)))
      Stage(operands=List(x6891, x6890), op=FltMul, results=List(CU.vecOut(bus_398_v)))
    }
    val x6944_27 = Pipeline(name="x6944_27",parent=x6944) { implicit CU => 
      val x6893 = CU.temp(None)
      val x6894 = CU.temp(None)
      val x6892 = VectorFIFO(size=1,name="x6892").wtPort(bus_398_v)
      val x6891 = VectorFIFO(size=1,name="x6891").wtPort(bus_397_v)
      val x6890 = VectorFIFO(size=1,name="x6890").wtPort(bus_395_v)
      Stage(operands=List(CU.load(x6892), CU.load(x6890)), op=FltMul, results=List(x6893))
      Stage(operands=List(x6893, CU.load(x6890)), op=FltMul, results=List(x6894))
      Stage(operands=List(x6894, Const(1.3302745)), op=FltMul, results=List(CU.vecOut(bus_401_v)))
      Stage(operands=List(x6893, Const(-1.8212559)), op=FltMul, results=List(CU.vecOut(bus_402_v)))
      Stage(operands=List(CU.load(x6891), Const(-0.35656378)), op=FltMul, results=List(CU.vecOut(bus_403_v)))
      Stage(operands=List(CU.load(x6892), Const(1.7814779)), op=FltMul, results=List(CU.vecOut(bus_404_v)))
    }
    val x6944_28 = Pipeline(name="x6944_28",parent=x6944) { implicit CU => 
      val x6901 = CU.temp(None)
      val x6900 = CU.temp(None)
      val x6899 = VectorFIFO(size=1,name="x6899").wtPort(bus_401_v)
      val x6896 = VectorFIFO(size=1,name="x6896").wtPort(bus_403_v)
      val x6897 = VectorFIFO(size=1,name="x6897").wtPort(bus_404_v)
      val x6898 = VectorFIFO(size=1,name="x6898").wtPort(bus_402_v)
      Stage(operands=List(CU.load(x6896), CU.load(x6897)), op=FltAdd, results=List(x6900))
      Stage(operands=List(x6900, CU.load(x6898)), op=FltAdd, results=List(x6901))
      Stage(operands=List(x6901, CU.load(x6899)), op=FltAdd, results=List(CU.vecOut(bus_407_v)))
    }
    val x6944_29 = Pipeline(name="x6944_29",parent=x6944) { implicit CU => 
      val x6903 = CU.temp(None)
      val x6907 = CU.temp(None)
      val x6906 = CU.temp(None)
      val x6904 = CU.temp(None)
      val x6905 = CU.temp(None)
      val x6895 = VectorFIFO(size=1,name="x6895").wtPort(bus_396_v)
      val x6887 = VectorFIFO(size=1,name="x6887").wtPort(bus_392_v)
      val x6902 = VectorFIFO(size=1,name="x6902").wtPort(bus_407_v)
      val x6864 = VectorFIFO(size=1,name="x6864").wtPort(bus_369_v)
      Stage(operands=List(CU.load(x6902), CU.load(x6895)), op=FltAdd, results=List(x6903))
      Stage(operands=List(x6903, CU.load(x6887)), op=FltMul, results=List(x6904))
      Stage(operands=List(x6904), op=FltNeg, results=List(x6905))
      Stage(operands=List(x6905, Const(1)), op=FltAdd, results=List(x6906))
      Stage(operands=List(CU.load(x6864), Const(0)), op=FltLt, results=List(x6907))
      Stage(operands=List(x6907, x6904, x6906), op=Mux, results=List(CU.vecOut(bus_413_v)))
    }
    val x6944_30 = Pipeline(name="x6944_30",parent=x6944) { implicit CU => 
      val x6910 = CU.temp(None)
      val x6909 = CU.temp(None)
      val x6913 = CU.temp(None)
      val x6764 = ScalarFIFO(size=1,name="x6764").wtPort(x6628_x6628_dsp0_bank0_s)
      val x6760 = ScalarFIFO(size=1,name="x6760").wtPort(x6626_x6626_dsp0_bank0_s)
      Stage(operands=List(CU.load(x6760)), op=FltNeg, results=List(x6909))
      Stage(operands=List(x6909, CU.load(x6764)), op=FltMul, results=List(x6910, CU.vecOut(bus_415_v)))
      Stage(operands=List(x6910, Const(-3.5)), op=FltLt, results=List(CU.vecOut(bus_416_v)))
      Stage(operands=List(x6910, Const(-1.2)), op=FltLt, results=List(CU.vecOut(bus_417_v)))
      Stage(operands=List(x6910, Const(0.1)), op=FltMul, results=List(x6913))
      Stage(operands=List(x6913, Const(0.35)), op=FltAdd, results=List(CU.vecOut(bus_419_v)))
    }
    val x6944_31 = Pipeline(name="x6944_31",parent=x6944) { implicit CU => 
      val x6917 = CU.temp(None)
      val x6915 = CU.temp(None)
      val x6916 = CU.temp(None)
      val x6919 = CU.temp(None)
      val x6910 = VectorFIFO(size=1,name="x6910").wtPort(bus_415_v)
      Stage(operands=List(Const(1), CU.load(x6910)), op=FltAdd, results=List(x6915))
      Stage(operands=List(CU.load(x6910), CU.load(x6910)), op=FltMul, results=List(x6916))
      Stage(operands=List(x6916, Const(2)), op=FltDiv, results=List(x6917))
      Stage(operands=List(x6915, x6917), op=FltAdd, results=List(CU.vecOut(bus_423_v)))
      Stage(operands=List(x6916, CU.load(x6910)), op=FltMul, results=List(x6919, CU.vecOut(bus_424_v)))
      Stage(operands=List(x6919, Const(6)), op=FltDiv, results=List(CU.vecOut(bus_425_v)))
    }
    val x6944_32 = Pipeline(name="x6944_32",parent=x6944) { implicit CU => 
      val x6925 = CU.temp(None)
      val x6921 = CU.temp(None)
      val x6922 = CU.temp(None)
      val x6923 = CU.temp(None)
      val x6918 = VectorFIFO(size=1,name="x6918").wtPort(bus_423_v)
      val x6919 = VectorFIFO(size=1,name="x6919").wtPort(bus_424_v)
      val x6910 = VectorFIFO(size=1,name="x6910").wtPort(bus_415_v)
      val x6920 = VectorFIFO(size=1,name="x6920").wtPort(bus_425_v)
      Stage(operands=List(CU.load(x6918), CU.load(x6920)), op=FltAdd, results=List(x6921))
      Stage(operands=List(CU.load(x6919), CU.load(x6910)), op=FltMul, results=List(x6922))
      Stage(operands=List(x6922, Const(24)), op=FltDiv, results=List(x6923))
      Stage(operands=List(x6921, x6923), op=FltAdd, results=List(CU.vecOut(bus_429_v)))
      Stage(operands=List(x6922, CU.load(x6910)), op=FltMul, results=List(x6925))
      Stage(operands=List(x6925, Const(120)), op=FltDiv, results=List(CU.vecOut(bus_431_v)))
    }
    val x6944_33 = Pipeline(name="x6944_33",parent=x6944) { implicit CU => 
      val x6927 = CU.temp(None)
      val x6926 = VectorFIFO(size=1,name="x6926").wtPort(bus_431_v)
      val x6914 = VectorFIFO(size=1,name="x6914").wtPort(bus_419_v)
      val x6924 = VectorFIFO(size=1,name="x6924").wtPort(bus_429_v)
      val x6912 = VectorFIFO(size=1,name="x6912").wtPort(bus_417_v)
      Stage(operands=List(CU.load(x6924), CU.load(x6926)), op=FltAdd, results=List(x6927))
      Stage(operands=List(CU.load(x6912), CU.load(x6914), x6927), op=Mux, results=List(CU.vecOut(bus_433_v)))
    }
    val x6944_34 = Pipeline(name="x6944_34",parent=x6944) { implicit CU => 
      val x6936 = CU.temp(None)
      val x6930 = CU.temp(None)
      val x6929 = CU.temp(None)
      val x6933 = CU.temp(None)
      val x6758 = ScalarFIFO(size=1,name="x6758").wtPort(x6625_x6625_dsp0_bank0_s)
      val x6928 = VectorFIFO(size=1,name="x6928").wtPort(bus_433_v)
      val x6935 = VectorFIFO(size=1,name="x6935").wtPort(bus_368_v)
      val x6908 = VectorFIFO(size=1,name="x6908").wtPort(bus_413_v)
      val x6911 = VectorFIFO(size=1,name="x6911").wtPort(bus_416_v)
      Stage(operands=List(CU.load(x6911), Const(0), CU.load(x6928)), op=Mux, results=List(x6929))
      Stage(operands=List(CU.load(x6758), x6929), op=FltMul, results=List(x6930, CU.vecOut(bus_435_v)))
      Stage(operands=List(x6930, CU.load(x6908)), op=FltMul, results=List(x6936))
      Stage(operands=List(CU.load(x6935), x6936), op=FltSub, results=List(CU.vecOut(bus_437_v)))
      Stage(operands=List(CU.load(x6908)), op=FltNeg, results=List(x6933))
      Stage(operands=List(x6933, Const(1)), op=FltAdd, results=List(CU.vecOut(bus_439_v)))
    }
    val x6944_35 = Pipeline(name="x6944_35",parent=x6944) { implicit CU => 
      val x6931 = CU.temp(None)
      val x6939 = CU.temp(None)
      val x6932 = CU.temp(None)
      val x6938 = CU.temp(None)
      val x6756 = ScalarFIFO(size=1,name="x6756").wtPort(x6624_x6624_dsp0_bank0_s)
      val x6930 = VectorFIFO(size=1,name="x6930").wtPort(bus_435_v)
      val x6766 = ScalarFIFO(size=1,name="x6766").wtPort(x6623_x6623_dsp0_bank0_s)
      val x6863 = VectorFIFO(size=1,name="x6863").wtPort(bus_367_v)
      val x6934 = VectorFIFO(size=1,name="x6934").wtPort(bus_439_v)
      Stage(operands=List(CU.load(x6930), CU.load(x6934)), op=FltMul, results=List(x6938))
      Stage(operands=List(CU.load(x6863)), op=FltNeg, results=List(x6931))
      Stage(operands=List(x6931, Const(1)), op=FltAdd, results=List(x6932))
      Stage(operands=List(CU.load(x6756), x6932), op=FltMul, results=List(x6939))
      Stage(operands=List(x6938, x6939), op=FltSub, results=List(CU.vecOut(bus_444_v)))
      Stage(operands=List(CU.load(x6766), Const(0)), op=FixEql, results=List(CU.vecOut(bus_445_v)))
    }
    val x6944_36 = Pipeline(name="x6944_36",parent=x6944) { implicit CU => 
      val x6941 = VectorFIFO(size=1,name="x6941").wtPort(bus_445_v)
      val x6937 = VectorFIFO(size=1,name="x6937").wtPort(bus_437_v)
      val x6940 = VectorFIFO(size=1,name="x6940").wtPort(bus_444_v)
      Stage(operands=List(CU.load(x6941), CU.load(x6940), CU.load(x6937)), op=Mux, results=List(CU.scalarOut(x6629_x6943_s)))
    }
    val x6970 = StreamController(name="x6970",parent=x6971) { implicit CU => 
      val x6970_unit = CounterChain(name = "x6970_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x6958_0 = Pipeline(name="x6958_0",parent=x6970) { implicit CU => 
      val x6951 = CU.temp(None)
      val x6953 = ScalarBuffer(name="x6953").wtPort(optprice_da)
      val x6622 = CounterChain.copy("x6971", "x6622")
      val x6958_unit = CounterChain(name = "x6958_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x6622(0)), Const(2)), op=FixSla, results=List(x6951))
      Stage(operands=List(x6951, CU.load(x6953)), op=FixAdd, results=List(CU.scalarOut(x6947_b7211_x6957_b7213_s)))
      Stage(operands=List(Const(64)), op=Bypass, results=List(CU.scalarOut(x6947_b7212_x6957_b7214_s)))
    }
    val x6966 = Pipeline(name="x6966",parent=x6970) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x6960 = CounterChain(name = "x6960", ctr9).iter(1)
    }
    val x6967 = MemoryController(name="x6967",parent=x6970,offchip=optprice_oc, mctpe=TileStore) { implicit CU => 
      val x6947_b7212 = ScalarFIFO(size=1,name="size").wtPort(x6947_b7212_x6957_b7214_s)
      val x6948 = ScalarFIFO(size=1,name="data").wtPort(x6629_x6629_dsp0_bank0_s)
      val x6947_b7211 = ScalarFIFO(size=1,name="offset").wtPort(x6947_b7211_x6957_b7213_s)
    }
    
  }
}
