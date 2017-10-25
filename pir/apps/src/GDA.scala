import pir._
import pir.node._
import arch._
import pirc.enums._

object GDA extends PIRApp {
  def main(top:Top) = {
    val x5517_x5517_dsp1_bank0_data_s = Scalar("x5517_x5517_dsp1_bank0_data")
    val x5522_x5538_data_v = Vector("x5522_x5538_data")
    val x5319_b5843_x5333_b5845_s = Scalar("x5319_b5843_x5333_b5845")
    val x5518_x5706_ra_s = Scalar("x5518_x5706_ra")
    val x5352_b5849_x5361_b5851_s = Scalar("x5352_b5849_x5361_b5851")
    val x5572_x5599_wa_v = Vector("x5572_x5599_wa")
    val x5620_x5620_dsp1_bank0_data_s = Scalar("x5620_x5620_dsp1_bank0_data")
    val x5518_x5518_dsp0_bank0_data_s = Scalar("x5518_x5518_dsp0_bank0_data")
    val x5287_x5287_dsp0_bank0_data_v = Vector("x5287_x5287_dsp0_bank0_data")
    val x5517_x5663_wa_s = Scalar("x5517_x5663_wa")
    val x5516_x5721_ra_s = Scalar("x5516_x5721_ra")
    val x5516_x5614_data_s = Scalar("x5516_x5614_data")
    val x5670_x5697_data_v = Vector("x5670_x5697_data")
    val x5515_x5515_dsp0_bank0_data_s = Scalar("x5515_x5515_dsp0_bank0_data")
    val x5429_b5866_x5443_b5868_s = Scalar("x5429_b5866_x5443_b5868")
    val x5293_x5458_s = Scalar("x5293_x5458")
    val x5240_x5630_ra_v = Vector("x5240_x5630_ra")
    val x5621_x5621_dsp0_bank0_data_s = Scalar("x5621_x5621_dsp0_bank0_data")
    val x5239_x5239_dsp3_bank0_data_v = Vector("x5239_x5239_dsp3_bank0_data")
    val x5242_x5252_data_v = Vector("x5242_x5252_data")
    val x5517_x5517_dsp0_bank0_data_s = Scalar("x5517_x5517_dsp0_bank0_data")
    val x5670_x5704_ra_s = Scalar("x5670_x5704_ra")
    val x5239_x5534_ra_v = Vector("x5239_x5534_ra")
    val x5260_b5833_x5269_b5835_s = Scalar("x5260_b5833_x5269_b5835")
    val x5289_x5289_dsp0_bank0_data_v = Vector("x5289_x5289_dsp0_bank0_data")
    val x5285_x5285_dsp0_bank0_data_s = Scalar("x5285_x5285_dsp0_bank0_data")
    val x5518_x5518_dsp1_bank0_data_s = Scalar("x5518_x5518_dsp1_bank0_data")
    val x5463_x5473_data_v = Vector("x5463_x5473_data")
    val x5239_x5239_dsp0_bank0_data_v = Vector("x5239_x5239_dsp0_bank0_data")
    val mu1_oc = OffChip("mu1")
    val y_da = DRAMAddress("y", "y")
    val x5621_x5648_data_v = Vector("x5621_x5648_data")
    val mu1_da = DRAMAddress("mu1", "mu1")
    val x5294_x5513_s = Scalar("x5294_x5513")
    val x5515_x5565_data_s = Scalar("x5515_x5565_data")
    val x5297_b5838_x5306_b5840_s = Scalar("x5297_b5838_x5306_b5840")
    val x5484_b5877_x5498_b5879_s = Scalar("x5484_b5877_x5498_b5879")
    val R_argin = ArgIn("R").bound(360000)
    val x5620_x5644_ra_s = Scalar("x5620_x5644_ra")
    val x5287_x5528_ra_v = Vector("x5287_x5528_ra")
    val x5239_x5583_ra_v = Vector("x5239_x5583_ra")
    val x5286_x5286_dsp0_bank0_data_s = Scalar("x5286_x5286_dsp0_bank0_data")
    val x5669_x5694_ra_v = Vector("x5669_x5694_ra")
    val sigma_oc = OffChip("sigma")
    val x5523_x5523_dsp0_bank0_data_s = Scalar("x5523_x5523_dsp0_bank0_data")
    val x5319_b5844_x5333_b5846_s = Scalar("x5319_b5844_x5333_b5846")
    val x5518_x5712_data_s = Scalar("x5518_x5712_data")
    val x5241_b5829_x5250_b5831_s = Scalar("x5241_b5829_x5250_b5831")
    val x5517_x5723_ra_s = Scalar("x5517_x5723_ra")
    val x5240_x5240_dsp1_bank0_data_v = Vector("x5240_x5240_dsp1_bank0_data")
    val x5261_x5271_data_v = Vector("x5261_x5271_data")
    val x5407_b5861_x5416_b5863_s = Scalar("x5407_b5861_x5416_b5863")
    val x5289_x5452_wa_v = Vector("x5289_x5452_wa")
    val x5285_x5628_ra_s = Scalar("x5285_x5628_ra")
    val x5283_x5530_ra_s = Scalar("x5283_x5530_ra")
    val x5620_x5645_ra_v = Vector("x5620_x5645_ra")
    val x5279_x5744_wa_s = Scalar("x5279_x5744_wa")
    val x5279_x5767_ra_v = Vector("x5279_x5767_ra")
    val x5288_x5397_wa_v = Vector("x5288_x5397_wa")
    val x5283_x5283_dsp0_bank0_data_s = Scalar("x5283_x5283_dsp0_bank0_data")
    val x5462_b5871_x5471_b5873_s = Scalar("x5462_b5871_x5471_b5873")
    val x5515_x5559_ra_s = Scalar("x5515_x5559_ra")
    val x5620_x5636_data_v = Vector("x5620_x5636_data")
    val x5239_x5681_ra_v = Vector("x5239_x5681_ra")
    val x5284_x5579_ra_s = Scalar("x5284_x5579_ra")
    val x5517_x5663_data_s = Scalar("x5517_x5663_data")
    val x5290_x5675_ra_v = Vector("x5290_x5675_ra")
    val x5572_x5606_ra_s = Scalar("x5572_x5606_ra")
    val x5669_x5685_data_v = Vector("x5669_x5685_data")
    val x5749_b5959_x5762_b5961_s = Scalar("x5749_b5959_x5762_b5961")
    val x5240_x5240_dsp3_bank0_data_v = Vector("x5240_x5240_dsp3_bank0_data")
    val x5240_x5276_wa_v = Vector("x5240_x5276_wa")
    val x5516_x5516_dsp1_bank0_data_s = Scalar("x5516_x5516_dsp1_bank0_data")
    val x5523_x5550_data_v = Vector("x5523_x5550_data")
    val x5284_x5284_dsp0_bank0_data_s = Scalar("x5284_x5284_dsp0_bank0_data")
    val x5517_x5657_ra_s = Scalar("x5517_x5657_ra")
    val mu0_da = DRAMAddress("mu0", "mu0")
    val x5288_x5577_ra_v = Vector("x5288_x5577_ra")
    val x5353_x5363_data_v = Vector("x5353_x5363_data")
    val x5515_x5719_ra_s = Scalar("x5515_x5719_ra")
    val x5320_x5335_data_v = Vector("x5320_x5335_data")
    val x5571_x5571_dsp1_bank0_data_s = Scalar("x5571_x5571_dsp1_bank0_data")
    val x5239_x5239_dsp2_bank0_data_v = Vector("x5239_x5239_dsp2_bank0_data")
    val x5288_x5288_dsp0_bank0_data_v = Vector("x5288_x5288_dsp0_bank0_data")
    val x5408_x5418_data_v = Vector("x5408_x5418_data")
    val x5571_x5587_wa_v = Vector("x5571_x5587_wa")
    val x5240_x5581_ra_v = Vector("x5240_x5581_ra")
    val x5290_x5290_dsp0_bank0_data_v = Vector("x5290_x5290_dsp0_bank0_data")
    val x5279_x5744_data_s = Scalar("x5279_x5744_data")
    val x5239_x5239_dsp1_bank0_data_v = Vector("x5239_x5239_dsp1_bank0_data")
    val x5571_x5587_data_v = Vector("x5571_x5587_data")
    val x5485_x5500_data_v = Vector("x5485_x5500_data")
    val x5239_x5257_wa_v = Vector("x5239_x5257_wa")
    val x5518_x5725_ra_s = Scalar("x5518_x5725_ra")
    val x5285_x5424_wa_v = Vector("x5285_x5424_wa")
    val x5620_x5620_dsp0_bank0_data_v = Vector("x5620_x5620_dsp0_bank0_data")
    val x5279_x5727_ra_s = Scalar("x5279_x5727_ra")
    val x5522_x5522_dsp0_bank0_data_v = Vector("x5522_x5522_dsp0_bank0_data")
    val x5430_x5445_data_v = Vector("x5430_x5445_data")
    val x5240_x5240_dsp2_bank0_data_v = Vector("x5240_x5240_dsp2_bank0_data")
    val x5572_x5599_data_v = Vector("x5572_x5599_data")
    val x5670_x5670_dsp0_bank0_data_s = Scalar("x5670_x5670_dsp0_bank0_data")
    val x5286_x5677_ra_s = Scalar("x5286_x5677_ra")
    val x5279_x5279_dsp1_bank0_data_s = Scalar("x5279_x5279_dsp1_bank0_data")
    val x5429_b5865_x5443_b5867_s = Scalar("x5429_b5865_x5443_b5867")
    val x5286_x5479_wa_v = Vector("x5286_x5479_wa")
    val x5523_x5557_ra_s = Scalar("x5523_x5557_ra")
    val x5515_x5565_wa_s = Scalar("x5515_x5565_wa")
    val x5516_x5608_ra_s = Scalar("x5516_x5608_ra")
    val x5516_x5614_wa_s = Scalar("x5516_x5614_wa")
    val x5522_x5546_ra_s = Scalar("x5522_x5546_ra")
    val x5292_x5403_s = Scalar("x5292_x5403")
    val x5279_x5279_dsp0_bank0_data_v = Vector("x5279_x5279_dsp0_bank0_data")
    val x5571_x5595_ra_s = Scalar("x5571_x5595_ra")
    val x5669_x5669_dsp0_bank0_data_v = Vector("x5669_x5669_dsp0_bank0_data")
    val x5287_x5342_wa_v = Vector("x5287_x5342_wa")
    val x5240_x5240_dsp0_bank0_data_v = Vector("x5240_x5240_dsp0_bank0_data")
    val x5571_x5596_ra_v = Vector("x5571_x5596_ra")
    val mu0_oc = OffChip("mu0")
    val x5298_x5308_data_v = Vector("x5298_x5308_data")
    val x5407_b5860_x5416_b5862_s = Scalar("x5407_b5860_x5416_b5862")
    val x5241_b5828_x5250_b5830_s = Scalar("x5241_b5828_x5250_b5830")
    val x5522_x5538_wa_v = Vector("x5522_x5538_wa")
    val x5669_x5685_wa_v = Vector("x5669_x5685_wa")
    val x5670_x5697_wa_v = Vector("x5670_x5697_wa")
    val x5462_b5872_x5471_b5874_s = Scalar("x5462_b5872_x5471_b5874")
    val x5260_b5834_x5269_b5836_s = Scalar("x5260_b5834_x5269_b5836")
    val x5290_x5507_wa_v = Vector("x5290_x5507_wa")
    val x_da = DRAMAddress("x", "x")
    val x5297_b5839_x5306_b5841_s = Scalar("x5297_b5839_x5306_b5841")
    val x5374_b5854_x5388_b5856_s = Scalar("x5374_b5854_x5388_b5856")
    val x5749_b5958_x5762_b5960_s = Scalar("x5749_b5958_x5762_b5960")
    val x5375_x5390_data_v = Vector("x5375_x5390_data")
    val x5289_x5626_ra_v = Vector("x5289_x5626_ra")
    val x5620_x5636_wa_v = Vector("x5620_x5636_wa")
    val x5621_x5655_ra_s = Scalar("x5621_x5655_ra")
    val x5484_b5876_x5498_b5878_s = Scalar("x5484_b5876_x5498_b5878")
    val x5669_x5669_dsp1_bank0_data_s = Scalar("x5669_x5669_dsp1_bank0_data")
    val x_oc = OffChip("x")
    val x5374_b5855_x5388_b5857_s = Scalar("x5374_b5855_x5388_b5857")
    val x5571_x5571_dsp0_bank0_data_v = Vector("x5571_x5571_dsp0_bank0_data")
    val x5283_x5314_wa_v = Vector("x5283_x5314_wa")
    val x5284_x5369_wa_v = Vector("x5284_x5369_wa")
    val x5523_x5550_wa_v = Vector("x5523_x5550_wa")
    val x5240_x5679_ra_v = Vector("x5240_x5679_ra")
    val x5515_x5515_dsp1_bank0_data_s = Scalar("x5515_x5515_dsp1_bank0_data")
    val x5352_b5850_x5361_b5852_s = Scalar("x5352_b5850_x5361_b5852")
    val x5522_x5522_dsp1_bank0_data_s = Scalar("x5522_x5522_dsp1_bank0_data")
    val x5572_x5572_dsp0_bank0_data_s = Scalar("x5572_x5572_dsp0_bank0_data")
    val x5240_x5532_ra_v = Vector("x5240_x5532_ra")
    val x5621_x5648_wa_v = Vector("x5621_x5648_wa")
    val x5516_x5516_dsp0_bank0_data_s = Scalar("x5516_x5516_dsp0_bank0_data")
    val x5669_x5693_ra_s = Scalar("x5669_x5693_ra")
    val x5291_x5348_s = Scalar("x5291_x5348")
    val y_oc = OffChip("y")
    val x5522_x5547_ra_v = Vector("x5522_x5547_ra")
    val sigma_da = DRAMAddress("sigma", "sigma")
    val x5239_x5632_ra_v = Vector("x5239_x5632_ra")
    val x5518_x5712_wa_s = Scalar("x5518_x5712_wa")
    val x5239_dsp0_bank0 = MemoryPipeline(name="x5239_dsp0_bank0",parent="top") { implicit CU => 
      val b5886 = VectorFIFO(size=1,name="b5886").wtPort(x5239_x5534_ra_v)
      val b5832 = VectorFIFO(size=1,name="b5832").wtPort(x5239_x5257_wa_v)
      val x5257 = VectorFIFO(size=1,name="x5257").wtPort(x5242_x5252_data_v)
      val x5239 = SRAM(size=6,name="x5239",banking = Strided(1,16)).buffering(1).wtPort(x5257.readPort).rdPort(x5239_x5239_dsp0_bank0_data_v).rdAddr(b5886.readPort).wtAddr(b5832.readPort)
    }
    val x5239_dsp2_bank0 = MemoryPipeline(name="x5239_dsp2_bank0",parent="top") { implicit CU => 
      val b5918 = VectorFIFO(size=1,name="b5918").wtPort(x5239_x5632_ra_v)
      val b5832 = VectorFIFO(size=1,name="b5832").wtPort(x5239_x5257_wa_v)
      val x5257 = VectorFIFO(size=1,name="x5257").wtPort(x5242_x5252_data_v)
      val x5239 = SRAM(size=6,name="x5239",banking = Strided(1,16)).buffering(1).wtPort(x5257.readPort).rdPort(x5239_x5239_dsp2_bank0_data_v).rdAddr(b5918.readPort).wtAddr(b5832.readPort)
    }
    val x5239_dsp1_bank0 = MemoryPipeline(name="x5239_dsp1_bank0",parent="top") { implicit CU => 
      val b5902 = VectorFIFO(size=1,name="b5902").wtPort(x5239_x5583_ra_v)
      val b5832 = VectorFIFO(size=1,name="b5832").wtPort(x5239_x5257_wa_v)
      val x5257 = VectorFIFO(size=1,name="x5257").wtPort(x5242_x5252_data_v)
      val x5239 = SRAM(size=6,name="x5239",banking = Strided(1,16)).buffering(1).wtPort(x5257.readPort).rdPort(x5239_x5239_dsp1_bank0_data_v).rdAddr(b5902.readPort).wtAddr(b5832.readPort)
    }
    val x5239_dsp3_bank0 = MemoryPipeline(name="x5239_dsp3_bank0",parent="top") { implicit CU => 
      val b5934 = VectorFIFO(size=1,name="b5934").wtPort(x5239_x5681_ra_v)
      val b5832 = VectorFIFO(size=1,name="b5832").wtPort(x5239_x5257_wa_v)
      val x5257 = VectorFIFO(size=1,name="x5257").wtPort(x5242_x5252_data_v)
      val x5239 = SRAM(size=6,name="x5239",banking = Strided(1,16)).buffering(1).wtPort(x5257.readPort).rdPort(x5239_x5239_dsp3_bank0_data_v).rdAddr(b5934.readPort).wtAddr(b5832.readPort)
    }
    val x5240_dsp0_bank0 = MemoryPipeline(name="x5240_dsp0_bank0",parent="top") { implicit CU => 
      val b5837 = VectorFIFO(size=1,name="b5837").wtPort(x5240_x5276_wa_v)
      val x5276 = VectorFIFO(size=1,name="x5276").wtPort(x5261_x5271_data_v)
      val b5885 = VectorFIFO(size=1,name="b5885").wtPort(x5240_x5532_ra_v)
      val x5240 = SRAM(size=6,name="x5240",banking = Strided(1,16)).buffering(1).wtPort(x5276.readPort).rdPort(x5240_x5240_dsp0_bank0_data_v).rdAddr(b5885.readPort).wtAddr(b5837.readPort)
    }
    val x5240_dsp2_bank0 = MemoryPipeline(name="x5240_dsp2_bank0",parent="top") { implicit CU => 
      val b5837 = VectorFIFO(size=1,name="b5837").wtPort(x5240_x5276_wa_v)
      val b5917 = VectorFIFO(size=1,name="b5917").wtPort(x5240_x5630_ra_v)
      val x5276 = VectorFIFO(size=1,name="x5276").wtPort(x5261_x5271_data_v)
      val x5240 = SRAM(size=6,name="x5240",banking = Strided(1,16)).buffering(1).wtPort(x5276.readPort).rdPort(x5240_x5240_dsp2_bank0_data_v).rdAddr(b5917.readPort).wtAddr(b5837.readPort)
    }
    val x5240_dsp1_bank0 = MemoryPipeline(name="x5240_dsp1_bank0",parent="top") { implicit CU => 
      val b5837 = VectorFIFO(size=1,name="b5837").wtPort(x5240_x5276_wa_v)
      val x5276 = VectorFIFO(size=1,name="x5276").wtPort(x5261_x5271_data_v)
      val b5901 = VectorFIFO(size=1,name="b5901").wtPort(x5240_x5581_ra_v)
      val x5240 = SRAM(size=6,name="x5240",banking = Strided(1,16)).buffering(1).wtPort(x5276.readPort).rdPort(x5240_x5240_dsp1_bank0_data_v).rdAddr(b5901.readPort).wtAddr(b5837.readPort)
    }
    val x5240_dsp3_bank0 = MemoryPipeline(name="x5240_dsp3_bank0",parent="top") { implicit CU => 
      val b5933 = VectorFIFO(size=1,name="b5933").wtPort(x5240_x5679_ra_v)
      val b5837 = VectorFIFO(size=1,name="b5837").wtPort(x5240_x5276_wa_v)
      val x5276 = VectorFIFO(size=1,name="x5276").wtPort(x5261_x5271_data_v)
      val x5240 = SRAM(size=6,name="x5240",banking = Strided(1,16)).buffering(1).wtPort(x5276.readPort).rdPort(x5240_x5240_dsp3_bank0_data_v).rdAddr(b5933.readPort).wtAddr(b5837.readPort)
    }
    val x5259 = StreamController(name="x5259",parent="top") { implicit CU => 
      val x5259_unit = CounterChain(name = "x5259_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5251_0 = Pipeline(name="x5251_0",parent="x5259") { implicit CU => 
      val x5244 = CU.temp(None)
      val x5246 = ScalarBuffer(name="x5246").buffering(1).wtPort(mu0_da)
      val x5251_unit = CounterChain(name = "x5251_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x5244))
      Stage(operands=List(x5244, CU.load(x5246)), op=FixAdd, results=List(CU.scalarOut(x5241_b5828_x5250_b5830_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5241_b5829_x5250_b5831_s)))
    }
    val x5252 = MemoryController(name="x5252",parent="x5259",offchip=mu0_oc, mctpe=TileLoad) { implicit CU => 
      val x5241_b5828 = ScalarFIFO(size=1,name="offset").wtPort(x5241_b5828_x5250_b5830_s)
      val x5241_b5829 = ScalarFIFO(size=1,name="size").wtPort(x5241_b5829_x5250_b5831_s)
      CU.newOut("data", x5242_x5252_data_v)
    }
    val x5258_0 = Pipeline(name="x5258_0",parent="x5259") { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5254 = CounterChain(name = "x5254", ctr1).iter(6)
      Stage(operands=List(CU.ctr(x5254(0))), op=Bypass, results=List(CU.vecOut(x5239_x5257_wa_v)))
    }
    val x5278 = StreamController(name="x5278",parent="top") { implicit CU => 
      val x5278_unit = CounterChain(name = "x5278_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5270_0 = Pipeline(name="x5270_0",parent="x5278") { implicit CU => 
      val x5263 = CU.temp(None)
      val x5265 = ScalarBuffer(name="x5265").buffering(1).wtPort(mu1_da)
      val x5270_unit = CounterChain(name = "x5270_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(Const(0), Const(2)), op=FixSla, results=List(x5263))
      Stage(operands=List(x5263, CU.load(x5265)), op=FixAdd, results=List(CU.scalarOut(x5260_b5833_x5269_b5835_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5260_b5834_x5269_b5836_s)))
    }
    val x5271 = MemoryController(name="x5271",parent="x5278",offchip=mu1_oc, mctpe=TileLoad) { implicit CU => 
      val x5260_b5834 = ScalarFIFO(size=1,name="size").wtPort(x5260_b5834_x5269_b5836_s)
      val x5260_b5833 = ScalarFIFO(size=1,name="offset").wtPort(x5260_b5833_x5269_b5835_s)
      CU.newOut("data", x5261_x5271_data_v)
    }
    val x5277_0 = Pipeline(name="x5277_0",parent="x5278") { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5273 = CounterChain(name = "x5273", ctr2).iter(6)
      Stage(operands=List(CU.ctr(x5273(0))), op=Bypass, results=List(CU.vecOut(x5240_x5276_wa_v)))
    }
    val x5279_dsp0_bank0 = MemoryPipeline(name="x5279_dsp0_bank0",parent="x5746") { implicit CU => 
      val x5744 = ScalarFIFO(size=1,name="x5744").wtPort(x5279_x5744_data_s)
      val b5963 = VectorFIFO(size=1,name="b5963").wtPort(x5279_x5767_ra_v)
      val b5957 = ScalarFIFO(size=1,name="b5957").wtPort(x5279_x5744_wa_s)
      val x5279 = SRAM(size=576,name="x5279",banking = Strided(1,16)).buffering(1).wtPort(x5744.readPort).rdPort(x5279_x5279_dsp0_bank0_data_v).rdAddr(b5963.readPort).wtAddr(b5957.readPort)
    }
    val x5279_dsp1_bank0 = MemoryPipeline(name="x5279_dsp1_bank0",parent="x5746") { implicit CU => 
      val x5744 = ScalarFIFO(size=1,name="x5744").wtPort(x5279_x5744_data_s)
      val b5955 = ScalarFIFO(size=1,name="b5955").wtPort(x5279_x5727_ra_s)
      val b5957 = ScalarFIFO(size=1,name="b5957").wtPort(x5279_x5744_wa_s)
      val x5279 = SRAM(size=9216,name="x5279",banking = NoBanking()).buffering(1).wtPort(x5744.readPort).rdPort(x5279_x5279_dsp1_bank0_data_s).rdAddr(b5955.readPort).wtAddr(b5957.readPort)
    }
    val x5746 = MetaPipeline(name="x5746",parent="top") { implicit CU => 
      val x5226 = ScalarBuffer(name="x5226").buffering(1).wtPort(R_argin)
      val ctr3 = Counter(min=Const(0), max=x5226.readPort, step=Const(20), par=4) // Counter
      val x5282 = CounterChain(name = "x5282", ctr3).iter(4500)
    }
    val x5283_dsp0_bank0 = MemoryPipeline(name="x5283_dsp0_bank0",parent="x5746") { implicit CU => 
      val x5314 = VectorFIFO(size=1,name="x5314").wtPort(x5298_x5308_data_v)
      val b5884 = ScalarFIFO(size=1,name="b5884").wtPort(x5283_x5530_ra_s)
      val b5842 = VectorFIFO(size=1,name="b5842").wtPort(x5283_x5314_wa_v)
      val x5283 = SRAM(size=1,name="x5283",banking = Strided(1,16)).buffering(2).wtPort(x5314.readPort).rdPort(x5283_x5283_dsp0_bank0_data_s).rdAddr(b5884.readPort).wtAddr(b5842.readPort).consumer("x5530_0", "x5567").producer("x5315_0", "x5316")
    }
    val x5284_dsp0_bank0 = MemoryPipeline(name="x5284_dsp0_bank0",parent="x5746") { implicit CU => 
      val b5900 = ScalarFIFO(size=1,name="b5900").wtPort(x5284_x5579_ra_s)
      val x5369 = VectorFIFO(size=1,name="x5369").wtPort(x5353_x5363_data_v)
      val b5853 = VectorFIFO(size=1,name="b5853").wtPort(x5284_x5369_wa_v)
      val x5284 = SRAM(size=1,name="x5284",banking = Strided(1,16)).buffering(2).wtPort(x5369.readPort).rdPort(x5284_x5284_dsp0_bank0_data_s).rdAddr(b5900.readPort).wtAddr(b5853.readPort).consumer("x5579_0", "x5616").producer("x5370_0", "x5371")
    }
    val x5285_dsp0_bank0 = MemoryPipeline(name="x5285_dsp0_bank0",parent="x5746") { implicit CU => 
      val b5864 = VectorFIFO(size=1,name="b5864").wtPort(x5285_x5424_wa_v)
      val x5424 = VectorFIFO(size=1,name="x5424").wtPort(x5408_x5418_data_v)
      val b5916 = ScalarFIFO(size=1,name="b5916").wtPort(x5285_x5628_ra_s)
      val x5285 = SRAM(size=1,name="x5285",banking = Strided(1,16)).buffering(2).wtPort(x5424.readPort).rdPort(x5285_x5285_dsp0_bank0_data_s).rdAddr(b5916.readPort).wtAddr(b5864.readPort).consumer("x5628_0", "x5665").producer("x5425_0", "x5426")
    }
    val x5286_dsp0_bank0 = MemoryPipeline(name="x5286_dsp0_bank0",parent="x5746") { implicit CU => 
      val b5875 = VectorFIFO(size=1,name="b5875").wtPort(x5286_x5479_wa_v)
      val b5932 = ScalarFIFO(size=1,name="b5932").wtPort(x5286_x5677_ra_s)
      val x5479 = VectorFIFO(size=1,name="x5479").wtPort(x5463_x5473_data_v)
      val x5286 = SRAM(size=1,name="x5286",banking = Strided(1,16)).buffering(2).wtPort(x5479.readPort).rdPort(x5286_x5286_dsp0_bank0_data_s).rdAddr(b5932.readPort).wtAddr(b5875.readPort).consumer("x5677_0", "x5714").producer("x5480_0", "x5481")
    }
    val x5287_dsp0_bank0 = MemoryPipeline(name="x5287_dsp0_bank0",parent="x5746") { implicit CU => 
      val b5848 = VectorFIFO(size=1,name="b5848").wtPort(x5287_x5342_wa_v)
      val x5342 = VectorFIFO(size=1,name="x5342").wtPort(x5320_x5335_data_v)
      val b5883 = VectorFIFO(size=1,name="b5883").wtPort(x5287_x5528_ra_v)
      val x5287 = SRAM(size=120,name="x5287",banking = Strided(1,16)).buffering(2).wtPort(x5342.readPort).rdPort(x5287_x5287_dsp0_bank0_data_v).rdAddr(b5883.readPort).wtAddr(b5848.readPort).consumer("x5528_0", "x5567").producer("x5343_0", "x5344")
    }
    val x5288_dsp0_bank0 = MemoryPipeline(name="x5288_dsp0_bank0",parent="x5746") { implicit CU => 
      val b5899 = VectorFIFO(size=1,name="b5899").wtPort(x5288_x5577_ra_v)
      val x5397 = VectorFIFO(size=1,name="x5397").wtPort(x5375_x5390_data_v)
      val b5859 = VectorFIFO(size=1,name="b5859").wtPort(x5288_x5397_wa_v)
      val x5288 = SRAM(size=120,name="x5288",banking = Strided(1,16)).buffering(2).wtPort(x5397.readPort).rdPort(x5288_x5288_dsp0_bank0_data_v).rdAddr(b5899.readPort).wtAddr(b5859.readPort).consumer("x5577_0", "x5616").producer("x5398_0", "x5399")
    }
    val x5289_dsp0_bank0 = MemoryPipeline(name="x5289_dsp0_bank0",parent="x5746") { implicit CU => 
      val b5915 = VectorFIFO(size=1,name="b5915").wtPort(x5289_x5626_ra_v)
      val b5870 = VectorFIFO(size=1,name="b5870").wtPort(x5289_x5452_wa_v)
      val x5452 = VectorFIFO(size=1,name="x5452").wtPort(x5430_x5445_data_v)
      val x5289 = SRAM(size=120,name="x5289",banking = Strided(1,16)).buffering(2).wtPort(x5452.readPort).rdPort(x5289_x5289_dsp0_bank0_data_v).rdAddr(b5915.readPort).wtAddr(b5870.readPort).consumer("x5626_0", "x5665").producer("x5453_0", "x5454")
    }
    val x5290_dsp0_bank0 = MemoryPipeline(name="x5290_dsp0_bank0",parent="x5746") { implicit CU => 
      val b5881 = VectorFIFO(size=1,name="b5881").wtPort(x5290_x5507_wa_v)
      val x5507 = VectorFIFO(size=1,name="x5507").wtPort(x5485_x5500_data_v)
      val b5931 = VectorFIFO(size=1,name="b5931").wtPort(x5290_x5675_ra_v)
      val x5290 = SRAM(size=120,name="x5290",banking = Strided(1,16)).buffering(2).wtPort(x5507.readPort).rdPort(x5290_x5290_dsp0_bank0_data_v).rdAddr(b5931.readPort).wtAddr(b5881.readPort).consumer("x5675_0", "x5714").producer("x5508_0", "x5509")
    }
    val x5316 = StreamController(name="x5316",parent="x5746") { implicit CU => 
      val x5316_unit = CounterChain(name = "x5316_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5307_0 = Pipeline(name="x5307_0",parent="x5316") { implicit CU => 
      val x5300 = CU.temp(None)
      val x5302 = ScalarBuffer(name="x5302").buffering(1).wtPort(y_da)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 0)
      val x5307_unit = CounterChain(name = "x5307_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5282(0)), Const(2)), op=FixSla, results=List(x5300))
      Stage(operands=List(x5300, CU.load(x5302)), op=FixAdd, results=List(CU.scalarOut(x5297_b5838_x5306_b5840_s)))
      Stage(operands=List(Const(80)), op=Bypass, results=List(CU.scalarOut(x5297_b5839_x5306_b5841_s)))
    }
    val x5308 = MemoryController(name="x5308",parent="x5316",offchip=y_oc, mctpe=TileLoad) { implicit CU => 
      val x5297_b5839 = ScalarFIFO(size=1,name="size").wtPort(x5297_b5839_x5306_b5841_s)
      val x5297_b5838 = ScalarFIFO(size=1,name="offset").wtPort(x5297_b5838_x5306_b5840_s)
      CU.newOut("data", x5298_x5308_data_v)
    }
    val x5315_0 = Pipeline(name="x5315_0",parent="x5316") { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(20), step=Const(1), par=16) // Counter
      val x5310 = CounterChain(name = "x5310", ctr4).iter(2)
      Stage(operands=List(CU.ctr(x5310(0))), op=Bypass, results=List(CU.vecOut(x5283_x5314_wa_v)))
    }
    val x5344 = StreamController(name="x5344",parent="x5746") { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x5318 = CounterChain(name = "x5318", ctr5).iter(20)
    }
    val x5334_0 = Pipeline(name="x5334_0",parent="x5344") { implicit CU => 
      val x5321 = CU.temp(None)
      val x5326 = CU.temp(None)
      val x5325 = CU.temp(None)
      val x5323 = CU.temp(None)
      val x5328 = ScalarBuffer(name="x5328").buffering(1).wtPort(x_da)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 0)
      val x5318 = CounterChain.copy("x5344", "x5318").iterIdx(0, 0)
      val x5334_unit = CounterChain(name = "x5334_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5282(0)), CU.ctr(x5318(0))), op=FixAdd, results=List(x5321))
      Stage(operands=List(x5321, Const(96)), op=FixMul, results=List(x5323))
      Stage(operands=List(x5323, Const(0)), op=FixAdd, results=List(x5325))
      Stage(operands=List(x5325, Const(2)), op=FixSla, results=List(x5326))
      Stage(operands=List(x5326, CU.load(x5328)), op=FixAdd, results=List(CU.scalarOut(x5319_b5843_x5333_b5845_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5319_b5844_x5333_b5846_s)))
    }
    val x5335 = MemoryController(name="x5335",parent="x5344",offchip=x_oc, mctpe=TileLoad) { implicit CU => 
      val x5319_b5843 = ScalarFIFO(size=1,name="offset").wtPort(x5319_b5843_x5333_b5845_s)
      val x5319_b5844 = ScalarFIFO(size=1,name="size").wtPort(x5319_b5844_x5333_b5846_s)
      CU.newOut("data", x5320_x5335_data_v)
    }
    val x5343_0 = Pipeline(name="x5343_0",parent="x5344") { implicit CU => 
      val b5847 = CU.temp(None)
      val x5318 = CounterChain.copy("x5344", "x5318").iterIdx(0, 0)
      val ctr6 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5337 = CounterChain(name = "x5337", ctr6).iter(6)
      Stage(operands=List(CU.ctr(x5318(0)), Const(96)), op=FixMul, results=List(b5847))
      Stage(operands=List(b5847, CU.ctr(x5337(0))), op=FixAdd, results=List(CU.vecOut(x5287_x5342_wa_v)))
    }
    val x5349_0 = Pipeline(name="x5349_0",parent="x5746") { implicit CU => 
      val x5346 = CU.temp(None)
      val x5226 = ScalarBuffer(name="x5226").buffering(1).wtPort(R_argin)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 0)
      val x5349_unit = CounterChain(name = "x5349_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x5226), CU.ctr(x5282(0))), op=FixSub, results=List(x5346))
      Stage(operands=List(x5346, Const(20)), op=FixMin, results=List(CU.scalarOut(x5291_x5348_s)))
    }
    val x5371 = StreamController(name="x5371",parent="x5746") { implicit CU => 
      val x5371_unit = CounterChain(name = "x5371_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5362_0 = Pipeline(name="x5362_0",parent="x5371") { implicit CU => 
      val x5355 = CU.temp(None)
      val x5357 = ScalarBuffer(name="x5357").buffering(1).wtPort(y_da)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 1)
      val x5362_unit = CounterChain(name = "x5362_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5282(0)), Const(2)), op=FixSla, results=List(x5355))
      Stage(operands=List(x5355, CU.load(x5357)), op=FixAdd, results=List(CU.scalarOut(x5352_b5849_x5361_b5851_s)))
      Stage(operands=List(Const(80)), op=Bypass, results=List(CU.scalarOut(x5352_b5850_x5361_b5852_s)))
    }
    val x5363 = MemoryController(name="x5363",parent="x5371",offchip=y_oc, mctpe=TileLoad) { implicit CU => 
      val x5352_b5849 = ScalarFIFO(size=1,name="offset").wtPort(x5352_b5849_x5361_b5851_s)
      val x5352_b5850 = ScalarFIFO(size=1,name="size").wtPort(x5352_b5850_x5361_b5852_s)
      CU.newOut("data", x5353_x5363_data_v)
    }
    val x5370_0 = Pipeline(name="x5370_0",parent="x5371") { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(20), step=Const(1), par=16) // Counter
      val x5365 = CounterChain(name = "x5365", ctr7).iter(2)
      Stage(operands=List(CU.ctr(x5365(0))), op=Bypass, results=List(CU.vecOut(x5284_x5369_wa_v)))
    }
    val x5399 = StreamController(name="x5399",parent="x5746") { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x5373 = CounterChain(name = "x5373", ctr8).iter(20)
    }
    val x5389_0 = Pipeline(name="x5389_0",parent="x5399") { implicit CU => 
      val x5376 = CU.temp(None)
      val x5380 = CU.temp(None)
      val x5381 = CU.temp(None)
      val x5378 = CU.temp(None)
      val x5383 = ScalarBuffer(name="x5383").buffering(1).wtPort(x_da)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 1)
      val x5373 = CounterChain.copy("x5399", "x5373").iterIdx(0, 0)
      val x5389_unit = CounterChain(name = "x5389_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5282(0)), CU.ctr(x5373(0))), op=FixAdd, results=List(x5376))
      Stage(operands=List(x5376, Const(96)), op=FixMul, results=List(x5378))
      Stage(operands=List(x5378, Const(0)), op=FixAdd, results=List(x5380))
      Stage(operands=List(x5380, Const(2)), op=FixSla, results=List(x5381))
      Stage(operands=List(x5381, CU.load(x5383)), op=FixAdd, results=List(CU.scalarOut(x5374_b5854_x5388_b5856_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5374_b5855_x5388_b5857_s)))
    }
    val x5390 = MemoryController(name="x5390",parent="x5399",offchip=x_oc, mctpe=TileLoad) { implicit CU => 
      val x5374_b5855 = ScalarFIFO(size=1,name="size").wtPort(x5374_b5855_x5388_b5857_s)
      val x5374_b5854 = ScalarFIFO(size=1,name="offset").wtPort(x5374_b5854_x5388_b5856_s)
      CU.newOut("data", x5375_x5390_data_v)
    }
    val x5398_0 = Pipeline(name="x5398_0",parent="x5399") { implicit CU => 
      val b5858 = CU.temp(None)
      val x5373 = CounterChain.copy("x5399", "x5373").iterIdx(0, 0)
      val ctr9 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5392 = CounterChain(name = "x5392", ctr9).iter(6)
      Stage(operands=List(CU.ctr(x5373(0)), Const(96)), op=FixMul, results=List(b5858))
      Stage(operands=List(b5858, CU.ctr(x5392(0))), op=FixAdd, results=List(CU.vecOut(x5288_x5397_wa_v)))
    }
    val x5404_0 = Pipeline(name="x5404_0",parent="x5746") { implicit CU => 
      val x5401 = CU.temp(None)
      val x5226 = ScalarBuffer(name="x5226").buffering(1).wtPort(R_argin)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 1)
      val x5404_unit = CounterChain(name = "x5404_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x5226), CU.ctr(x5282(0))), op=FixSub, results=List(x5401))
      Stage(operands=List(x5401, Const(20)), op=FixMin, results=List(CU.scalarOut(x5292_x5403_s)))
    }
    val x5426 = StreamController(name="x5426",parent="x5746") { implicit CU => 
      val x5426_unit = CounterChain(name = "x5426_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5417_0 = Pipeline(name="x5417_0",parent="x5426") { implicit CU => 
      val x5410 = CU.temp(None)
      val x5412 = ScalarBuffer(name="x5412").buffering(1).wtPort(y_da)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 2)
      val x5417_unit = CounterChain(name = "x5417_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5282(0)), Const(2)), op=FixSla, results=List(x5410))
      Stage(operands=List(x5410, CU.load(x5412)), op=FixAdd, results=List(CU.scalarOut(x5407_b5860_x5416_b5862_s)))
      Stage(operands=List(Const(80)), op=Bypass, results=List(CU.scalarOut(x5407_b5861_x5416_b5863_s)))
    }
    val x5418 = MemoryController(name="x5418",parent="x5426",offchip=y_oc, mctpe=TileLoad) { implicit CU => 
      val x5407_b5861 = ScalarFIFO(size=1,name="size").wtPort(x5407_b5861_x5416_b5863_s)
      val x5407_b5860 = ScalarFIFO(size=1,name="offset").wtPort(x5407_b5860_x5416_b5862_s)
      CU.newOut("data", x5408_x5418_data_v)
    }
    val x5425_0 = Pipeline(name="x5425_0",parent="x5426") { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(20), step=Const(1), par=16) // Counter
      val x5420 = CounterChain(name = "x5420", ctr10).iter(2)
      Stage(operands=List(CU.ctr(x5420(0))), op=Bypass, results=List(CU.vecOut(x5285_x5424_wa_v)))
    }
    val x5454 = StreamController(name="x5454",parent="x5746") { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x5428 = CounterChain(name = "x5428", ctr11).iter(20)
    }
    val x5444_0 = Pipeline(name="x5444_0",parent="x5454") { implicit CU => 
      val x5433 = CU.temp(None)
      val x5431 = CU.temp(None)
      val x5435 = CU.temp(None)
      val x5436 = CU.temp(None)
      val x5438 = ScalarBuffer(name="x5438").buffering(1).wtPort(x_da)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 2)
      val x5428 = CounterChain.copy("x5454", "x5428").iterIdx(0, 0)
      val x5444_unit = CounterChain(name = "x5444_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5282(0)), CU.ctr(x5428(0))), op=FixAdd, results=List(x5431))
      Stage(operands=List(x5431, Const(96)), op=FixMul, results=List(x5433))
      Stage(operands=List(x5433, Const(0)), op=FixAdd, results=List(x5435))
      Stage(operands=List(x5435, Const(2)), op=FixSla, results=List(x5436))
      Stage(operands=List(x5436, CU.load(x5438)), op=FixAdd, results=List(CU.scalarOut(x5429_b5865_x5443_b5867_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5429_b5866_x5443_b5868_s)))
    }
    val x5445 = MemoryController(name="x5445",parent="x5454",offchip=x_oc, mctpe=TileLoad) { implicit CU => 
      val x5429_b5866 = ScalarFIFO(size=1,name="size").wtPort(x5429_b5866_x5443_b5868_s)
      val x5429_b5865 = ScalarFIFO(size=1,name="offset").wtPort(x5429_b5865_x5443_b5867_s)
      CU.newOut("data", x5430_x5445_data_v)
    }
    val x5453_0 = Pipeline(name="x5453_0",parent="x5454") { implicit CU => 
      val b5869 = CU.temp(None)
      val x5428 = CounterChain.copy("x5454", "x5428").iterIdx(0, 0)
      val ctr12 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5447 = CounterChain(name = "x5447", ctr12).iter(6)
      Stage(operands=List(CU.ctr(x5428(0)), Const(96)), op=FixMul, results=List(b5869))
      Stage(operands=List(b5869, CU.ctr(x5447(0))), op=FixAdd, results=List(CU.vecOut(x5289_x5452_wa_v)))
    }
    val x5459_0 = Pipeline(name="x5459_0",parent="x5746") { implicit CU => 
      val x5456 = CU.temp(None)
      val x5226 = ScalarBuffer(name="x5226").buffering(1).wtPort(R_argin)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 2)
      val x5459_unit = CounterChain(name = "x5459_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x5226), CU.ctr(x5282(0))), op=FixSub, results=List(x5456))
      Stage(operands=List(x5456, Const(20)), op=FixMin, results=List(CU.scalarOut(x5293_x5458_s)))
    }
    val x5481 = StreamController(name="x5481",parent="x5746") { implicit CU => 
      val x5481_unit = CounterChain(name = "x5481_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x5472_0 = Pipeline(name="x5472_0",parent="x5481") { implicit CU => 
      val x5465 = CU.temp(None)
      val x5467 = ScalarBuffer(name="x5467").buffering(1).wtPort(y_da)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 3)
      val x5472_unit = CounterChain(name = "x5472_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5282(0)), Const(2)), op=FixSla, results=List(x5465))
      Stage(operands=List(x5465, CU.load(x5467)), op=FixAdd, results=List(CU.scalarOut(x5462_b5871_x5471_b5873_s)))
      Stage(operands=List(Const(80)), op=Bypass, results=List(CU.scalarOut(x5462_b5872_x5471_b5874_s)))
    }
    val x5473 = MemoryController(name="x5473",parent="x5481",offchip=y_oc, mctpe=TileLoad) { implicit CU => 
      val x5462_b5872 = ScalarFIFO(size=1,name="size").wtPort(x5462_b5872_x5471_b5874_s)
      val x5462_b5871 = ScalarFIFO(size=1,name="offset").wtPort(x5462_b5871_x5471_b5873_s)
      CU.newOut("data", x5463_x5473_data_v)
    }
    val x5480_0 = Pipeline(name="x5480_0",parent="x5481") { implicit CU => 
      val ctr13 = Counter(min=Const(0), max=Const(20), step=Const(1), par=16) // Counter
      val x5475 = CounterChain(name = "x5475", ctr13).iter(2)
      Stage(operands=List(CU.ctr(x5475(0))), op=Bypass, results=List(CU.vecOut(x5286_x5479_wa_v)))
    }
    val x5509 = StreamController(name="x5509",parent="x5746") { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x5483 = CounterChain(name = "x5483", ctr14).iter(20)
    }
    val x5499_0 = Pipeline(name="x5499_0",parent="x5509") { implicit CU => 
      val x5490 = CU.temp(None)
      val x5486 = CU.temp(None)
      val x5491 = CU.temp(None)
      val x5488 = CU.temp(None)
      val x5493 = ScalarBuffer(name="x5493").buffering(1).wtPort(x_da)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 3)
      val x5483 = CounterChain.copy("x5509", "x5483").iterIdx(0, 0)
      val x5499_unit = CounterChain(name = "x5499_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5282(0)), CU.ctr(x5483(0))), op=FixAdd, results=List(x5486))
      Stage(operands=List(x5486, Const(96)), op=FixMul, results=List(x5488))
      Stage(operands=List(x5488, Const(0)), op=FixAdd, results=List(x5490))
      Stage(operands=List(x5490, Const(2)), op=FixSla, results=List(x5491))
      Stage(operands=List(x5491, CU.load(x5493)), op=FixAdd, results=List(CU.scalarOut(x5484_b5876_x5498_b5878_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5484_b5877_x5498_b5879_s)))
    }
    val x5500 = MemoryController(name="x5500",parent="x5509",offchip=x_oc, mctpe=TileLoad) { implicit CU => 
      val x5484_b5877 = ScalarFIFO(size=1,name="size").wtPort(x5484_b5877_x5498_b5879_s)
      val x5484_b5876 = ScalarFIFO(size=1,name="offset").wtPort(x5484_b5876_x5498_b5878_s)
      CU.newOut("data", x5485_x5500_data_v)
    }
    val x5508_0 = Pipeline(name="x5508_0",parent="x5509") { implicit CU => 
      val b5880 = CU.temp(None)
      val x5483 = CounterChain.copy("x5509", "x5483").iterIdx(0, 0)
      val ctr15 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5502 = CounterChain(name = "x5502", ctr15).iter(6)
      Stage(operands=List(CU.ctr(x5483(0)), Const(96)), op=FixMul, results=List(b5880))
      Stage(operands=List(b5880, CU.ctr(x5502(0))), op=FixAdd, results=List(CU.vecOut(x5290_x5507_wa_v)))
    }
    val x5514_0 = Pipeline(name="x5514_0",parent="x5746") { implicit CU => 
      val x5511 = CU.temp(None)
      val x5226 = ScalarBuffer(name="x5226").buffering(1).wtPort(R_argin)
      val x5282 = CounterChain.copy("x5746", "x5282").iterIdx(0, 3)
      val x5514_unit = CounterChain(name = "x5514_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.load(x5226), CU.ctr(x5282(0))), op=FixSub, results=List(x5511))
      Stage(operands=List(x5511, Const(20)), op=FixMin, results=List(CU.scalarOut(x5294_x5513_s)))
    }
    val x5515_dsp1_bank0 = MemoryPipeline(name="x5515_dsp1_bank0",parent="x5567") { implicit CU => 
      val x5565 = ScalarFIFO(size=1,name="x5565").wtPort(x5515_x5565_data_s)
      val b5897 = ScalarFIFO(size=1,name="b5897").wtPort(x5515_x5565_wa_s)
      val b5895 = ScalarFIFO(size=1,name="b5895").wtPort(x5515_x5559_ra_s)
      val x5515 = SRAM(size=9216,name="x5515",banking = NoBanking()).buffering(1).wtPort(x5565.readPort).rdPort(x5515_x5515_dsp1_bank0_data_s).rdAddr(b5895.readPort).wtAddr(b5897.readPort)
    }
    val x5515_dsp0_bank0 = MemoryPipeline(name="x5515_dsp0_bank0",parent="x5567") { implicit CU => 
      val x5565 = ScalarFIFO(size=1,name="x5565").wtPort(x5515_x5565_data_s)
      val b5897 = ScalarFIFO(size=1,name="b5897").wtPort(x5515_x5565_wa_s)
      val b5947 = ScalarFIFO(size=1,name="b5947").wtPort(x5515_x5719_ra_s)
      val x5515 = SRAM(size=9216,name="x5515",banking = NoBanking()).buffering(2).wtPort(x5565.readPort).rdPort(x5515_x5515_dsp0_bank0_data_s).rdAddr(b5947.readPort).wtAddr(b5897.readPort).consumer("x5719_0", "x5745_0").producer("x5566_0", "x5567")
    }
    val x5516_dsp1_bank0 = MemoryPipeline(name="x5516_dsp1_bank0",parent="x5616") { implicit CU => 
      val b5911 = ScalarFIFO(size=1,name="b5911").wtPort(x5516_x5608_ra_s)
      val b5913 = ScalarFIFO(size=1,name="b5913").wtPort(x5516_x5614_wa_s)
      val x5614 = ScalarFIFO(size=1,name="x5614").wtPort(x5516_x5614_data_s)
      val x5516 = SRAM(size=9216,name="x5516",banking = NoBanking()).buffering(1).wtPort(x5614.readPort).rdPort(x5516_x5516_dsp1_bank0_data_s).rdAddr(b5911.readPort).wtAddr(b5913.readPort)
    }
    val x5516_dsp0_bank0 = MemoryPipeline(name="x5516_dsp0_bank0",parent="x5616") { implicit CU => 
      val b5949 = ScalarFIFO(size=1,name="b5949").wtPort(x5516_x5721_ra_s)
      val b5913 = ScalarFIFO(size=1,name="b5913").wtPort(x5516_x5614_wa_s)
      val x5614 = ScalarFIFO(size=1,name="x5614").wtPort(x5516_x5614_data_s)
      val x5516 = SRAM(size=9216,name="x5516",banking = NoBanking()).buffering(2).wtPort(x5614.readPort).rdPort(x5516_x5516_dsp0_bank0_data_s).rdAddr(b5949.readPort).wtAddr(b5913.readPort).consumer("x5721_0", "x5745_0").producer("x5615_0", "x5616")
    }
    val x5517_dsp0_bank0 = MemoryPipeline(name="x5517_dsp0_bank0",parent="x5665") { implicit CU => 
      val b5951 = ScalarFIFO(size=1,name="b5951").wtPort(x5517_x5723_ra_s)
      val x5663 = ScalarFIFO(size=1,name="x5663").wtPort(x5517_x5663_data_s)
      val b5929 = ScalarFIFO(size=1,name="b5929").wtPort(x5517_x5663_wa_s)
      val x5517 = SRAM(size=9216,name="x5517",banking = NoBanking()).buffering(2).wtPort(x5663.readPort).rdPort(x5517_x5517_dsp0_bank0_data_s).rdAddr(b5951.readPort).wtAddr(b5929.readPort).consumer("x5723_0", "x5745_0").producer("x5664_0", "x5665")
    }
    val x5517_dsp1_bank0 = MemoryPipeline(name="x5517_dsp1_bank0",parent="x5665") { implicit CU => 
      val b5927 = ScalarFIFO(size=1,name="b5927").wtPort(x5517_x5657_ra_s)
      val x5663 = ScalarFIFO(size=1,name="x5663").wtPort(x5517_x5663_data_s)
      val b5929 = ScalarFIFO(size=1,name="b5929").wtPort(x5517_x5663_wa_s)
      val x5517 = SRAM(size=9216,name="x5517",banking = NoBanking()).buffering(1).wtPort(x5663.readPort).rdPort(x5517_x5517_dsp1_bank0_data_s).rdAddr(b5927.readPort).wtAddr(b5929.readPort)
    }
    val x5518_dsp1_bank0 = MemoryPipeline(name="x5518_dsp1_bank0",parent="x5714") { implicit CU => 
      val b5943 = ScalarFIFO(size=1,name="b5943").wtPort(x5518_x5706_ra_s)
      val x5712 = ScalarFIFO(size=1,name="x5712").wtPort(x5518_x5712_data_s)
      val b5945 = ScalarFIFO(size=1,name="b5945").wtPort(x5518_x5712_wa_s)
      val x5518 = SRAM(size=9216,name="x5518",banking = NoBanking()).buffering(1).wtPort(x5712.readPort).rdPort(x5518_x5518_dsp1_bank0_data_s).rdAddr(b5943.readPort).wtAddr(b5945.readPort)
    }
    val x5518_dsp0_bank0 = MemoryPipeline(name="x5518_dsp0_bank0",parent="x5714") { implicit CU => 
      val b5953 = ScalarFIFO(size=1,name="b5953").wtPort(x5518_x5725_ra_s)
      val x5712 = ScalarFIFO(size=1,name="x5712").wtPort(x5518_x5712_data_s)
      val b5945 = ScalarFIFO(size=1,name="b5945").wtPort(x5518_x5712_wa_s)
      val x5518 = SRAM(size=9216,name="x5518",banking = NoBanking()).buffering(2).wtPort(x5712.readPort).rdPort(x5518_x5518_dsp0_bank0_data_s).rdAddr(b5953.readPort).wtAddr(b5945.readPort).consumer("x5725_0", "x5745_0").producer("x5713_0", "x5714")
    }
    val x5567 = MetaPipeline(name="x5567",parent="x5746") { implicit CU => 
      val x5291 = ScalarBuffer(name="x5291").buffering(2).wtPort(x5291_x5348_s).consumer("x5567", "x5567")
      val ctr16 = Counter(min=Const(0), max=x5291.readPort, step=Const(1), par=1) // Counter
      val x5521 = CounterChain(name = "x5521", ctr16).iter(1)
    }
    val x5522_dsp1_bank0 = MemoryPipeline(name="x5522_dsp1_bank0",parent="x5567") { implicit CU => 
      val b5888 = ScalarFIFO(size=1,name="b5888").wtPort(x5522_x5546_ra_s)
      val x5538 = VectorFIFO(size=1,name="x5538").wtPort(x5522_x5538_data_v)
      val b5887 = VectorFIFO(size=1,name="b5887").wtPort(x5522_x5538_wa_v)
      val x5522 = SRAM(size=6,name="x5522",banking = Strided(1,16)).buffering(2).wtPort(x5538.readPort).rdPort(x5522_x5522_dsp1_bank0_data_s).rdAddr(b5888.readPort).wtAddr(b5887.readPort).consumer("x5546_0", "x5551_0").producer("x5539_0", "x5539_0")
    }
    val x5522_dsp0_bank0 = MemoryPipeline(name="x5522_dsp0_bank0",parent="x5567") { implicit CU => 
      val x5538 = VectorFIFO(size=1,name="x5538").wtPort(x5522_x5538_data_v)
      val b5887 = VectorFIFO(size=1,name="b5887").wtPort(x5522_x5538_wa_v)
      val b5889 = VectorFIFO(size=1,name="b5889").wtPort(x5522_x5547_ra_v)
      val x5522 = SRAM(size=6,name="x5522",banking = Strided(1,16)).buffering(2).wtPort(x5538.readPort).rdPort(x5522_x5522_dsp0_bank0_data_v).rdAddr(b5889.readPort).wtAddr(b5887.readPort).consumer("x5547_0", "x5551_0").producer("x5539_0", "x5539_0")
    }
    val x5523_dsp0_bank0 = MemoryPipeline(name="x5523_dsp0_bank0",parent="x5567") { implicit CU => 
      val x5550 = VectorFIFO(size=1,name="x5550").wtPort(x5523_x5550_data_v)
      val b5891 = VectorFIFO(size=1,name="b5891").wtPort(x5523_x5550_wa_v)
      val b5893 = ScalarFIFO(size=1,name="b5893").wtPort(x5523_x5557_ra_s)
      val x5523 = SRAM(size=576,name="x5523",banking = Strided(1,16)).buffering(2).wtPort(x5550.readPort).rdPort(x5523_x5523_dsp0_bank0_data_s).rdAddr(b5893.readPort).wtAddr(b5891.readPort).consumer("x5557_0", "x5566_0").producer("x5551_0", "x5551_0")
    }
    val x5539_0 = Pipeline(name="x5539_0",parent="x5567") { implicit CU => 
      val x5531 = CU.temp(None)
      val x5536 = CU.temp(None)
      val x5532 = VectorFIFO(size=1,name="x5532").wtPort(x5240_x5240_dsp0_bank0_data_v)
      val x5534 = VectorFIFO(size=1,name="x5534").wtPort(x5239_x5239_dsp0_bank0_data_v)
      val x5528 = VectorFIFO(size=1,name="x5528").wtPort(x5287_x5287_dsp0_bank0_data_v)
      val x5530 = ScalarFIFO(size=1,name="x5530").wtPort(x5283_x5283_dsp0_bank0_data_s)
      val ctr17 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5525 = CounterChain(name = "x5525", ctr17).iter(6)
      Stage(operands=List(CU.load(x5530), Const(1)), op=FixEql, results=List(x5531))
      Stage(operands=List(x5531, CU.load(x5532), CU.load(x5534)), op=MuxOp, results=List(x5536))
      Stage(operands=List(CU.load(x5528), x5536), op=FixSub, results=List(CU.vecOut(x5522_x5538_data_v)))
      Stage(operands=List(CU.ctr(x5525(0))), op=Bypass, results=List(CU.vecOut(x5522_x5538_wa_v)))
    }
    val x5528_0 = Pipeline(name="x5528_0",parent="x5567") { implicit CU => 
      val b5882 = CU.temp(None)
      val x5521 = CounterChain.copy("x5567", "x5521").iterIdx(0, 0)
      val x5525 = CounterChain.copy("x5539_0", "x5525").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5521(0)), Const(96)), op=FixMul, results=List(b5882))
      Stage(operands=List(b5882, CU.ctr(x5525(0))), op=FixAdd, results=List(CU.vecOut(x5287_x5528_ra_v)))
    }
    val x5530_0 = Pipeline(name="x5530_0",parent="x5567") { implicit CU => 
      val x5521 = CounterChain.copy("x5567", "x5521").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5521(0))), op=Bypass, results=List(CU.scalarOut(x5283_x5530_ra_s)))
    }
    val x5532_0 = Pipeline(name="x5532_0",parent="x5567") { implicit CU => 
      val x5525 = CounterChain.copy("x5539_0", "x5525").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5525(0))), op=Bypass, results=List(CU.vecOut(x5240_x5532_ra_v)))
    }
    val x5534_0 = Pipeline(name="x5534_0",parent="x5567") { implicit CU => 
      val x5525 = CounterChain.copy("x5539_0", "x5525").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5525(0))), op=Bypass, results=List(CU.vecOut(x5239_x5534_ra_v)))
    }
    val x5551_0 = Pipeline(name="x5551_0",parent="x5567") { implicit CU => 
      val b5890 = CU.temp(None)
      val x5547 = VectorFIFO(size=1,name="x5547").wtPort(x5522_x5522_dsp0_bank0_data_v)
      val x5546 = ScalarFIFO(size=1,name="x5546").wtPort(x5522_x5522_dsp1_bank0_data_s)
      val ctr18 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr19 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5542 = CounterChain(name = "x5542", ctr18, ctr19).iter(576)
      Stage(operands=List(CU.load(x5546), CU.load(x5547)), op=FixMul, results=List(CU.vecOut(x5523_x5550_data_v)))
      Stage(operands=List(CU.ctr(x5542(0)), Const(96)), op=FixMul, results=List(b5890))
      Stage(operands=List(b5890, CU.ctr(x5542(1))), op=FixAdd, results=List(CU.vecOut(x5523_x5550_wa_v)))
    }
    val x5546_0 = Pipeline(name="x5546_0",parent="x5567") { implicit CU => 
      val x5542 = CounterChain.copy("x5551_0", "x5542").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5542(0))), op=Bypass, results=List(CU.scalarOut(x5522_x5546_ra_s)))
    }
    val x5547_0 = Pipeline(name="x5547_0",parent="x5567") { implicit CU => 
      val x5542 = CounterChain.copy("x5551_0", "x5542").iterIdx(1, 0)
      Stage(operands=List(CU.ctr(x5542(1))), op=Bypass, results=List(CU.vecOut(x5522_x5547_ra_v)))
    }
    val x5566_0 = Pipeline(name="x5566_0",parent="x5567") { implicit CU => 
      val b5896 = CU.temp(None)
      val x5559 = ScalarFIFO(size=1,name="x5559").wtPort(x5515_x5515_dsp1_bank0_data_s)
      val x5557 = ScalarFIFO(size=1,name="x5557").wtPort(x5523_x5523_dsp0_bank0_data_s)
      val ctr20 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr21 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5554 = CounterChain(name = "x5554", ctr20, ctr21).iter(9216)
      Stage(operands=List(CU.load(x5557), CU.load(x5559)), op=FixAdd, results=List(CU.scalarOut(x5515_x5565_data_s)))
      Stage(operands=List(CU.ctr(x5554(0)), Const(96)), op=FixMul, results=List(b5896))
      Stage(operands=List(b5896, CU.ctr(x5554(1))), op=FixAdd, results=List(CU.scalarOut(x5515_x5565_wa_s)))
    }
    val x5557_0 = Pipeline(name="x5557_0",parent="x5567") { implicit CU => 
      val b5892 = CU.temp(None)
      val x5554 = CounterChain.copy("x5566_0", "x5554").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5554(0)), Const(96)), op=FixMul, results=List(b5892))
      Stage(operands=List(b5892, CU.ctr(x5554(1))), op=FixAdd, results=List(CU.scalarOut(x5523_x5557_ra_s)))
    }
    val x5559_0 = Pipeline(name="x5559_0",parent="x5567") { implicit CU => 
      val b5894 = CU.temp(None)
      val x5554 = CounterChain.copy("x5566_0", "x5554").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5554(0)), Const(96)), op=FixMul, results=List(b5894))
      Stage(operands=List(b5894, CU.ctr(x5554(1))), op=FixAdd, results=List(CU.scalarOut(x5515_x5559_ra_s)))
    }
    val x5616 = MetaPipeline(name="x5616",parent="x5746") { implicit CU => 
      val x5292 = ScalarBuffer(name="x5292").buffering(2).wtPort(x5292_x5403_s).consumer("x5616", "x5616")
      val ctr22 = Counter(min=Const(0), max=x5292.readPort, step=Const(1), par=1) // Counter
      val x5570 = CounterChain(name = "x5570", ctr22).iter(1)
    }
    val x5571_dsp0_bank0 = MemoryPipeline(name="x5571_dsp0_bank0",parent="x5616") { implicit CU => 
      val b5905 = VectorFIFO(size=1,name="b5905").wtPort(x5571_x5596_ra_v)
      val x5587 = VectorFIFO(size=1,name="x5587").wtPort(x5571_x5587_data_v)
      val b5903 = VectorFIFO(size=1,name="b5903").wtPort(x5571_x5587_wa_v)
      val x5571 = SRAM(size=6,name="x5571",banking = Strided(1,16)).buffering(2).wtPort(x5587.readPort).rdPort(x5571_x5571_dsp0_bank0_data_v).rdAddr(b5905.readPort).wtAddr(b5903.readPort).consumer("x5596_0", "x5600_0").producer("x5588_0", "x5588_0")
    }
    val x5571_dsp1_bank0 = MemoryPipeline(name="x5571_dsp1_bank0",parent="x5616") { implicit CU => 
      val x5587 = VectorFIFO(size=1,name="x5587").wtPort(x5571_x5587_data_v)
      val b5904 = ScalarFIFO(size=1,name="b5904").wtPort(x5571_x5595_ra_s)
      val b5903 = VectorFIFO(size=1,name="b5903").wtPort(x5571_x5587_wa_v)
      val x5571 = SRAM(size=6,name="x5571",banking = Strided(1,16)).buffering(2).wtPort(x5587.readPort).rdPort(x5571_x5571_dsp1_bank0_data_s).rdAddr(b5904.readPort).wtAddr(b5903.readPort).consumer("x5595_0", "x5600_0").producer("x5588_0", "x5588_0")
    }
    val x5572_dsp0_bank0 = MemoryPipeline(name="x5572_dsp0_bank0",parent="x5616") { implicit CU => 
      val b5909 = ScalarFIFO(size=1,name="b5909").wtPort(x5572_x5606_ra_s)
      val x5599 = VectorFIFO(size=1,name="x5599").wtPort(x5572_x5599_data_v)
      val b5907 = VectorFIFO(size=1,name="b5907").wtPort(x5572_x5599_wa_v)
      val x5572 = SRAM(size=576,name="x5572",banking = Strided(1,16)).buffering(2).wtPort(x5599.readPort).rdPort(x5572_x5572_dsp0_bank0_data_s).rdAddr(b5909.readPort).wtAddr(b5907.readPort).consumer("x5606_0", "x5615_0").producer("x5600_0", "x5600_0")
    }
    val x5588_0 = Pipeline(name="x5588_0",parent="x5616") { implicit CU => 
      val x5580 = CU.temp(None)
      val x5585 = CU.temp(None)
      val x5583 = VectorFIFO(size=1,name="x5583").wtPort(x5239_x5239_dsp1_bank0_data_v)
      val x5577 = VectorFIFO(size=1,name="x5577").wtPort(x5288_x5288_dsp0_bank0_data_v)
      val x5579 = ScalarFIFO(size=1,name="x5579").wtPort(x5284_x5284_dsp0_bank0_data_s)
      val x5581 = VectorFIFO(size=1,name="x5581").wtPort(x5240_x5240_dsp1_bank0_data_v)
      val ctr23 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5574 = CounterChain(name = "x5574", ctr23).iter(6)
      Stage(operands=List(CU.load(x5579), Const(1)), op=FixEql, results=List(x5580))
      Stage(operands=List(x5580, CU.load(x5581), CU.load(x5583)), op=MuxOp, results=List(x5585))
      Stage(operands=List(CU.load(x5577), x5585), op=FixSub, results=List(CU.vecOut(x5571_x5587_data_v)))
      Stage(operands=List(CU.ctr(x5574(0))), op=Bypass, results=List(CU.vecOut(x5571_x5587_wa_v)))
    }
    val x5577_0 = Pipeline(name="x5577_0",parent="x5616") { implicit CU => 
      val b5898 = CU.temp(None)
      val x5570 = CounterChain.copy("x5616", "x5570").iterIdx(0, 0)
      val x5574 = CounterChain.copy("x5588_0", "x5574").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5570(0)), Const(96)), op=FixMul, results=List(b5898))
      Stage(operands=List(b5898, CU.ctr(x5574(0))), op=FixAdd, results=List(CU.vecOut(x5288_x5577_ra_v)))
    }
    val x5579_0 = Pipeline(name="x5579_0",parent="x5616") { implicit CU => 
      val x5570 = CounterChain.copy("x5616", "x5570").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5570(0))), op=Bypass, results=List(CU.scalarOut(x5284_x5579_ra_s)))
    }
    val x5581_0 = Pipeline(name="x5581_0",parent="x5616") { implicit CU => 
      val x5574 = CounterChain.copy("x5588_0", "x5574").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5574(0))), op=Bypass, results=List(CU.vecOut(x5240_x5581_ra_v)))
    }
    val x5583_0 = Pipeline(name="x5583_0",parent="x5616") { implicit CU => 
      val x5574 = CounterChain.copy("x5588_0", "x5574").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5574(0))), op=Bypass, results=List(CU.vecOut(x5239_x5583_ra_v)))
    }
    val x5600_0 = Pipeline(name="x5600_0",parent="x5616") { implicit CU => 
      val b5906 = CU.temp(None)
      val x5595 = ScalarFIFO(size=1,name="x5595").wtPort(x5571_x5571_dsp1_bank0_data_s)
      val x5596 = VectorFIFO(size=1,name="x5596").wtPort(x5571_x5571_dsp0_bank0_data_v)
      val ctr24 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr25 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5591 = CounterChain(name = "x5591", ctr24, ctr25).iter(576)
      Stage(operands=List(CU.load(x5595), CU.load(x5596)), op=FixMul, results=List(CU.vecOut(x5572_x5599_data_v)))
      Stage(operands=List(CU.ctr(x5591(0)), Const(96)), op=FixMul, results=List(b5906))
      Stage(operands=List(b5906, CU.ctr(x5591(1))), op=FixAdd, results=List(CU.vecOut(x5572_x5599_wa_v)))
    }
    val x5595_0 = Pipeline(name="x5595_0",parent="x5616") { implicit CU => 
      val x5591 = CounterChain.copy("x5600_0", "x5591").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5591(0))), op=Bypass, results=List(CU.scalarOut(x5571_x5595_ra_s)))
    }
    val x5596_0 = Pipeline(name="x5596_0",parent="x5616") { implicit CU => 
      val x5591 = CounterChain.copy("x5600_0", "x5591").iterIdx(1, 0)
      Stage(operands=List(CU.ctr(x5591(1))), op=Bypass, results=List(CU.vecOut(x5571_x5596_ra_v)))
    }
    val x5615_0 = Pipeline(name="x5615_0",parent="x5616") { implicit CU => 
      val b5912 = CU.temp(None)
      val x5606 = ScalarFIFO(size=1,name="x5606").wtPort(x5572_x5572_dsp0_bank0_data_s)
      val x5608 = ScalarFIFO(size=1,name="x5608").wtPort(x5516_x5516_dsp1_bank0_data_s)
      val ctr26 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr27 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5603 = CounterChain(name = "x5603", ctr26, ctr27).iter(9216)
      Stage(operands=List(CU.load(x5606), CU.load(x5608)), op=FixAdd, results=List(CU.scalarOut(x5516_x5614_data_s)))
      Stage(operands=List(CU.ctr(x5603(0)), Const(96)), op=FixMul, results=List(b5912))
      Stage(operands=List(b5912, CU.ctr(x5603(1))), op=FixAdd, results=List(CU.scalarOut(x5516_x5614_wa_s)))
    }
    val x5606_0 = Pipeline(name="x5606_0",parent="x5616") { implicit CU => 
      val b5908 = CU.temp(None)
      val x5603 = CounterChain.copy("x5615_0", "x5603").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5603(0)), Const(96)), op=FixMul, results=List(b5908))
      Stage(operands=List(b5908, CU.ctr(x5603(1))), op=FixAdd, results=List(CU.scalarOut(x5572_x5606_ra_s)))
    }
    val x5608_0 = Pipeline(name="x5608_0",parent="x5616") { implicit CU => 
      val b5910 = CU.temp(None)
      val x5603 = CounterChain.copy("x5615_0", "x5603").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5603(0)), Const(96)), op=FixMul, results=List(b5910))
      Stage(operands=List(b5910, CU.ctr(x5603(1))), op=FixAdd, results=List(CU.scalarOut(x5516_x5608_ra_s)))
    }
    val x5665 = MetaPipeline(name="x5665",parent="x5746") { implicit CU => 
      val x5293 = ScalarBuffer(name="x5293").buffering(2).wtPort(x5293_x5458_s).consumer("x5665", "x5665")
      val ctr28 = Counter(min=Const(0), max=x5293.readPort, step=Const(1), par=1) // Counter
      val x5619 = CounterChain(name = "x5619", ctr28).iter(1)
    }
    val x5620_dsp1_bank0 = MemoryPipeline(name="x5620_dsp1_bank0",parent="x5665") { implicit CU => 
      val x5636 = VectorFIFO(size=1,name="x5636").wtPort(x5620_x5636_data_v)
      val b5920 = ScalarFIFO(size=1,name="b5920").wtPort(x5620_x5644_ra_s)
      val b5919 = VectorFIFO(size=1,name="b5919").wtPort(x5620_x5636_wa_v)
      val x5620 = SRAM(size=6,name="x5620",banking = Strided(1,16)).buffering(2).wtPort(x5636.readPort).rdPort(x5620_x5620_dsp1_bank0_data_s).rdAddr(b5920.readPort).wtAddr(b5919.readPort).consumer("x5644_0", "x5649_0").producer("x5637_0", "x5637_0")
    }
    val x5620_dsp0_bank0 = MemoryPipeline(name="x5620_dsp0_bank0",parent="x5665") { implicit CU => 
      val x5636 = VectorFIFO(size=1,name="x5636").wtPort(x5620_x5636_data_v)
      val b5919 = VectorFIFO(size=1,name="b5919").wtPort(x5620_x5636_wa_v)
      val b5921 = VectorFIFO(size=1,name="b5921").wtPort(x5620_x5645_ra_v)
      val x5620 = SRAM(size=6,name="x5620",banking = Strided(1,16)).buffering(2).wtPort(x5636.readPort).rdPort(x5620_x5620_dsp0_bank0_data_v).rdAddr(b5921.readPort).wtAddr(b5919.readPort).consumer("x5645_0", "x5649_0").producer("x5637_0", "x5637_0")
    }
    val x5621_dsp0_bank0 = MemoryPipeline(name="x5621_dsp0_bank0",parent="x5665") { implicit CU => 
      val x5648 = VectorFIFO(size=1,name="x5648").wtPort(x5621_x5648_data_v)
      val b5923 = VectorFIFO(size=1,name="b5923").wtPort(x5621_x5648_wa_v)
      val b5925 = ScalarFIFO(size=1,name="b5925").wtPort(x5621_x5655_ra_s)
      val x5621 = SRAM(size=576,name="x5621",banking = Strided(1,16)).buffering(2).wtPort(x5648.readPort).rdPort(x5621_x5621_dsp0_bank0_data_s).rdAddr(b5925.readPort).wtAddr(b5923.readPort).consumer("x5655_0", "x5664_0").producer("x5649_0", "x5649_0")
    }
    val x5637_0 = Pipeline(name="x5637_0",parent="x5665") { implicit CU => 
      val x5629 = CU.temp(None)
      val x5634 = CU.temp(None)
      val x5628 = ScalarFIFO(size=1,name="x5628").wtPort(x5285_x5285_dsp0_bank0_data_s)
      val x5630 = VectorFIFO(size=1,name="x5630").wtPort(x5240_x5240_dsp2_bank0_data_v)
      val x5626 = VectorFIFO(size=1,name="x5626").wtPort(x5289_x5289_dsp0_bank0_data_v)
      val x5632 = VectorFIFO(size=1,name="x5632").wtPort(x5239_x5239_dsp2_bank0_data_v)
      val ctr29 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5623 = CounterChain(name = "x5623", ctr29).iter(6)
      Stage(operands=List(CU.load(x5628), Const(1)), op=FixEql, results=List(x5629))
      Stage(operands=List(x5629, CU.load(x5630), CU.load(x5632)), op=MuxOp, results=List(x5634))
      Stage(operands=List(CU.load(x5626), x5634), op=FixSub, results=List(CU.vecOut(x5620_x5636_data_v)))
      Stage(operands=List(CU.ctr(x5623(0))), op=Bypass, results=List(CU.vecOut(x5620_x5636_wa_v)))
    }
    val x5626_0 = Pipeline(name="x5626_0",parent="x5665") { implicit CU => 
      val b5914 = CU.temp(None)
      val x5619 = CounterChain.copy("x5665", "x5619").iterIdx(0, 0)
      val x5623 = CounterChain.copy("x5637_0", "x5623").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5619(0)), Const(96)), op=FixMul, results=List(b5914))
      Stage(operands=List(b5914, CU.ctr(x5623(0))), op=FixAdd, results=List(CU.vecOut(x5289_x5626_ra_v)))
    }
    val x5628_0 = Pipeline(name="x5628_0",parent="x5665") { implicit CU => 
      val x5619 = CounterChain.copy("x5665", "x5619").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5619(0))), op=Bypass, results=List(CU.scalarOut(x5285_x5628_ra_s)))
    }
    val x5630_0 = Pipeline(name="x5630_0",parent="x5665") { implicit CU => 
      val x5623 = CounterChain.copy("x5637_0", "x5623").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5623(0))), op=Bypass, results=List(CU.vecOut(x5240_x5630_ra_v)))
    }
    val x5632_0 = Pipeline(name="x5632_0",parent="x5665") { implicit CU => 
      val x5623 = CounterChain.copy("x5637_0", "x5623").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5623(0))), op=Bypass, results=List(CU.vecOut(x5239_x5632_ra_v)))
    }
    val x5649_0 = Pipeline(name="x5649_0",parent="x5665") { implicit CU => 
      val b5922 = CU.temp(None)
      val x5645 = VectorFIFO(size=1,name="x5645").wtPort(x5620_x5620_dsp0_bank0_data_v)
      val x5644 = ScalarFIFO(size=1,name="x5644").wtPort(x5620_x5620_dsp1_bank0_data_s)
      val ctr30 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr31 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5640 = CounterChain(name = "x5640", ctr30, ctr31).iter(576)
      Stage(operands=List(CU.load(x5644), CU.load(x5645)), op=FixMul, results=List(CU.vecOut(x5621_x5648_data_v)))
      Stage(operands=List(CU.ctr(x5640(0)), Const(96)), op=FixMul, results=List(b5922))
      Stage(operands=List(b5922, CU.ctr(x5640(1))), op=FixAdd, results=List(CU.vecOut(x5621_x5648_wa_v)))
    }
    val x5644_0 = Pipeline(name="x5644_0",parent="x5665") { implicit CU => 
      val x5640 = CounterChain.copy("x5649_0", "x5640").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5640(0))), op=Bypass, results=List(CU.scalarOut(x5620_x5644_ra_s)))
    }
    val x5645_0 = Pipeline(name="x5645_0",parent="x5665") { implicit CU => 
      val x5640 = CounterChain.copy("x5649_0", "x5640").iterIdx(1, 0)
      Stage(operands=List(CU.ctr(x5640(1))), op=Bypass, results=List(CU.vecOut(x5620_x5645_ra_v)))
    }
    val x5664_0 = Pipeline(name="x5664_0",parent="x5665") { implicit CU => 
      val b5928 = CU.temp(None)
      val x5655 = ScalarFIFO(size=1,name="x5655").wtPort(x5621_x5621_dsp0_bank0_data_s)
      val x5657 = ScalarFIFO(size=1,name="x5657").wtPort(x5517_x5517_dsp1_bank0_data_s)
      val ctr32 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr33 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5652 = CounterChain(name = "x5652", ctr32, ctr33).iter(9216)
      Stage(operands=List(CU.load(x5655), CU.load(x5657)), op=FixAdd, results=List(CU.scalarOut(x5517_x5663_data_s)))
      Stage(operands=List(CU.ctr(x5652(0)), Const(96)), op=FixMul, results=List(b5928))
      Stage(operands=List(b5928, CU.ctr(x5652(1))), op=FixAdd, results=List(CU.scalarOut(x5517_x5663_wa_s)))
    }
    val x5655_0 = Pipeline(name="x5655_0",parent="x5665") { implicit CU => 
      val b5924 = CU.temp(None)
      val x5652 = CounterChain.copy("x5664_0", "x5652").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5652(0)), Const(96)), op=FixMul, results=List(b5924))
      Stage(operands=List(b5924, CU.ctr(x5652(1))), op=FixAdd, results=List(CU.scalarOut(x5621_x5655_ra_s)))
    }
    val x5657_0 = Pipeline(name="x5657_0",parent="x5665") { implicit CU => 
      val b5926 = CU.temp(None)
      val x5652 = CounterChain.copy("x5664_0", "x5652").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5652(0)), Const(96)), op=FixMul, results=List(b5926))
      Stage(operands=List(b5926, CU.ctr(x5652(1))), op=FixAdd, results=List(CU.scalarOut(x5517_x5657_ra_s)))
    }
    val x5714 = MetaPipeline(name="x5714",parent="x5746") { implicit CU => 
      val x5294 = ScalarBuffer(name="x5294").buffering(2).wtPort(x5294_x5513_s).consumer("x5714", "x5714")
      val ctr34 = Counter(min=Const(0), max=x5294.readPort, step=Const(1), par=1) // Counter
      val x5668 = CounterChain(name = "x5668", ctr34).iter(1)
    }
    val x5669_dsp1_bank0 = MemoryPipeline(name="x5669_dsp1_bank0",parent="x5714") { implicit CU => 
      val b5935 = VectorFIFO(size=1,name="b5935").wtPort(x5669_x5685_wa_v)
      val b5936 = ScalarFIFO(size=1,name="b5936").wtPort(x5669_x5693_ra_s)
      val x5685 = VectorFIFO(size=1,name="x5685").wtPort(x5669_x5685_data_v)
      val x5669 = SRAM(size=6,name="x5669",banking = Strided(1,16)).buffering(2).wtPort(x5685.readPort).rdPort(x5669_x5669_dsp1_bank0_data_s).rdAddr(b5936.readPort).wtAddr(b5935.readPort).consumer("x5693_0", "x5698_0").producer("x5686_0", "x5686_0")
    }
    val x5669_dsp0_bank0 = MemoryPipeline(name="x5669_dsp0_bank0",parent="x5714") { implicit CU => 
      val b5935 = VectorFIFO(size=1,name="b5935").wtPort(x5669_x5685_wa_v)
      val b5937 = VectorFIFO(size=1,name="b5937").wtPort(x5669_x5694_ra_v)
      val x5685 = VectorFIFO(size=1,name="x5685").wtPort(x5669_x5685_data_v)
      val x5669 = SRAM(size=6,name="x5669",banking = Strided(1,16)).buffering(2).wtPort(x5685.readPort).rdPort(x5669_x5669_dsp0_bank0_data_v).rdAddr(b5937.readPort).wtAddr(b5935.readPort).consumer("x5694_0", "x5698_0").producer("x5686_0", "x5686_0")
    }
    val x5670_dsp0_bank0 = MemoryPipeline(name="x5670_dsp0_bank0",parent="x5714") { implicit CU => 
      val b5941 = ScalarFIFO(size=1,name="b5941").wtPort(x5670_x5704_ra_s)
      val x5697 = VectorFIFO(size=1,name="x5697").wtPort(x5670_x5697_data_v)
      val b5939 = VectorFIFO(size=1,name="b5939").wtPort(x5670_x5697_wa_v)
      val x5670 = SRAM(size=576,name="x5670",banking = Strided(1,16)).buffering(2).wtPort(x5697.readPort).rdPort(x5670_x5670_dsp0_bank0_data_s).rdAddr(b5941.readPort).wtAddr(b5939.readPort).consumer("x5704_0", "x5713_0").producer("x5698_0", "x5698_0")
    }
    val x5686_0 = Pipeline(name="x5686_0",parent="x5714") { implicit CU => 
      val x5678 = CU.temp(None)
      val x5683 = CU.temp(None)
      val x5681 = VectorFIFO(size=1,name="x5681").wtPort(x5239_x5239_dsp3_bank0_data_v)
      val x5675 = VectorFIFO(size=1,name="x5675").wtPort(x5290_x5290_dsp0_bank0_data_v)
      val x5677 = ScalarFIFO(size=1,name="x5677").wtPort(x5286_x5286_dsp0_bank0_data_s)
      val x5679 = VectorFIFO(size=1,name="x5679").wtPort(x5240_x5240_dsp3_bank0_data_v)
      val ctr35 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5672 = CounterChain(name = "x5672", ctr35).iter(6)
      Stage(operands=List(CU.load(x5677), Const(1)), op=FixEql, results=List(x5678))
      Stage(operands=List(x5678, CU.load(x5679), CU.load(x5681)), op=MuxOp, results=List(x5683))
      Stage(operands=List(CU.load(x5675), x5683), op=FixSub, results=List(CU.vecOut(x5669_x5685_data_v)))
      Stage(operands=List(CU.ctr(x5672(0))), op=Bypass, results=List(CU.vecOut(x5669_x5685_wa_v)))
    }
    val x5675_0 = Pipeline(name="x5675_0",parent="x5714") { implicit CU => 
      val b5930 = CU.temp(None)
      val x5668 = CounterChain.copy("x5714", "x5668").iterIdx(0, 0)
      val x5672 = CounterChain.copy("x5686_0", "x5672").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5668(0)), Const(96)), op=FixMul, results=List(b5930))
      Stage(operands=List(b5930, CU.ctr(x5672(0))), op=FixAdd, results=List(CU.vecOut(x5290_x5675_ra_v)))
    }
    val x5677_0 = Pipeline(name="x5677_0",parent="x5714") { implicit CU => 
      val x5668 = CounterChain.copy("x5714", "x5668").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5668(0))), op=Bypass, results=List(CU.scalarOut(x5286_x5677_ra_s)))
    }
    val x5679_0 = Pipeline(name="x5679_0",parent="x5714") { implicit CU => 
      val x5672 = CounterChain.copy("x5686_0", "x5672").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5672(0))), op=Bypass, results=List(CU.vecOut(x5240_x5679_ra_v)))
    }
    val x5681_0 = Pipeline(name="x5681_0",parent="x5714") { implicit CU => 
      val x5672 = CounterChain.copy("x5686_0", "x5672").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5672(0))), op=Bypass, results=List(CU.vecOut(x5239_x5681_ra_v)))
    }
    val x5698_0 = Pipeline(name="x5698_0",parent="x5714") { implicit CU => 
      val b5938 = CU.temp(None)
      val x5693 = ScalarFIFO(size=1,name="x5693").wtPort(x5669_x5669_dsp1_bank0_data_s)
      val x5694 = VectorFIFO(size=1,name="x5694").wtPort(x5669_x5669_dsp0_bank0_data_v)
      val ctr36 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr37 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5689 = CounterChain(name = "x5689", ctr36, ctr37).iter(576)
      Stage(operands=List(CU.load(x5693), CU.load(x5694)), op=FixMul, results=List(CU.vecOut(x5670_x5697_data_v)))
      Stage(operands=List(CU.ctr(x5689(0)), Const(96)), op=FixMul, results=List(b5938))
      Stage(operands=List(b5938, CU.ctr(x5689(1))), op=FixAdd, results=List(CU.vecOut(x5670_x5697_wa_v)))
    }
    val x5693_0 = Pipeline(name="x5693_0",parent="x5714") { implicit CU => 
      val x5689 = CounterChain.copy("x5698_0", "x5689").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5689(0))), op=Bypass, results=List(CU.scalarOut(x5669_x5693_ra_s)))
    }
    val x5694_0 = Pipeline(name="x5694_0",parent="x5714") { implicit CU => 
      val x5689 = CounterChain.copy("x5698_0", "x5689").iterIdx(1, 0)
      Stage(operands=List(CU.ctr(x5689(1))), op=Bypass, results=List(CU.vecOut(x5669_x5694_ra_v)))
    }
    val x5713_0 = Pipeline(name="x5713_0",parent="x5714") { implicit CU => 
      val b5944 = CU.temp(None)
      val x5704 = ScalarFIFO(size=1,name="x5704").wtPort(x5670_x5670_dsp0_bank0_data_s)
      val x5706 = ScalarFIFO(size=1,name="x5706").wtPort(x5518_x5518_dsp1_bank0_data_s)
      val ctr38 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr39 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5701 = CounterChain(name = "x5701", ctr38, ctr39).iter(9216)
      Stage(operands=List(CU.load(x5704), CU.load(x5706)), op=FixAdd, results=List(CU.scalarOut(x5518_x5712_data_s)))
      Stage(operands=List(CU.ctr(x5701(0)), Const(96)), op=FixMul, results=List(b5944))
      Stage(operands=List(b5944, CU.ctr(x5701(1))), op=FixAdd, results=List(CU.scalarOut(x5518_x5712_wa_s)))
    }
    val x5704_0 = Pipeline(name="x5704_0",parent="x5714") { implicit CU => 
      val b5940 = CU.temp(None)
      val x5701 = CounterChain.copy("x5713_0", "x5701").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5701(0)), Const(96)), op=FixMul, results=List(b5940))
      Stage(operands=List(b5940, CU.ctr(x5701(1))), op=FixAdd, results=List(CU.scalarOut(x5670_x5704_ra_s)))
    }
    val x5706_0 = Pipeline(name="x5706_0",parent="x5714") { implicit CU => 
      val b5942 = CU.temp(None)
      val x5701 = CounterChain.copy("x5713_0", "x5701").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5701(0)), Const(96)), op=FixMul, results=List(b5942))
      Stage(operands=List(b5942, CU.ctr(x5701(1))), op=FixAdd, results=List(CU.scalarOut(x5518_x5706_ra_s)))
    }
    val x5745_0 = Pipeline(name="x5745_0",parent="x5746") { implicit CU => 
      val x5733 = CU.temp(None)
      val b5956 = CU.temp(None)
      val x5736 = CU.temp(None)
      val x5739 = CU.temp(None)
      val x5723 = ScalarFIFO(size=1,name="x5723").wtPort(x5517_x5517_dsp0_bank0_data_s)
      val x5721 = ScalarFIFO(size=1,name="x5721").wtPort(x5516_x5516_dsp0_bank0_data_s)
      val x5725 = ScalarFIFO(size=1,name="x5725").wtPort(x5518_x5518_dsp0_bank0_data_s)
      val x5719 = ScalarFIFO(size=1,name="x5719").wtPort(x5515_x5515_dsp0_bank0_data_s)
      val x5727 = ScalarFIFO(size=1,name="x5727").wtPort(x5279_x5279_dsp1_bank0_data_s)
      val ctr40 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr41 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5717 = CounterChain(name = "x5717", ctr40, ctr41).iter(9216)
      Stage(operands=List(CU.load(x5719), CU.load(x5721)), op=FixAdd, results=List(x5733))
      Stage(operands=List(CU.load(x5723), CU.load(x5725)), op=FixAdd, results=List(x5736))
      Stage(operands=List(x5733, x5736), op=FixAdd, results=List(x5739))
      Stage(operands=List(x5739, CU.load(x5727)), op=FixAdd, results=List(CU.scalarOut(x5279_x5744_data_s)))
      Stage(operands=List(CU.ctr(x5717(0)), Const(96)), op=FixMul, results=List(b5956))
      Stage(operands=List(b5956, CU.ctr(x5717(1))), op=FixAdd, results=List(CU.scalarOut(x5279_x5744_wa_s)))
    }
    val x5719_0 = Pipeline(name="x5719_0",parent="x5746") { implicit CU => 
      val b5946 = CU.temp(None)
      val x5717 = CounterChain.copy("x5745_0", "x5717").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5717(0)), Const(96)), op=FixMul, results=List(b5946))
      Stage(operands=List(b5946, CU.ctr(x5717(1))), op=FixAdd, results=List(CU.scalarOut(x5515_x5719_ra_s)))
    }
    val x5721_0 = Pipeline(name="x5721_0",parent="x5746") { implicit CU => 
      val b5948 = CU.temp(None)
      val x5717 = CounterChain.copy("x5745_0", "x5717").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5717(0)), Const(96)), op=FixMul, results=List(b5948))
      Stage(operands=List(b5948, CU.ctr(x5717(1))), op=FixAdd, results=List(CU.scalarOut(x5516_x5721_ra_s)))
    }
    val x5723_0 = Pipeline(name="x5723_0",parent="x5746") { implicit CU => 
      val b5950 = CU.temp(None)
      val x5717 = CounterChain.copy("x5745_0", "x5717").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5717(0)), Const(96)), op=FixMul, results=List(b5950))
      Stage(operands=List(b5950, CU.ctr(x5717(1))), op=FixAdd, results=List(CU.scalarOut(x5517_x5723_ra_s)))
    }
    val x5725_0 = Pipeline(name="x5725_0",parent="x5746") { implicit CU => 
      val b5952 = CU.temp(None)
      val x5717 = CounterChain.copy("x5745_0", "x5717").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5717(0)), Const(96)), op=FixMul, results=List(b5952))
      Stage(operands=List(b5952, CU.ctr(x5717(1))), op=FixAdd, results=List(CU.scalarOut(x5518_x5725_ra_s)))
    }
    val x5727_0 = Pipeline(name="x5727_0",parent="x5746") { implicit CU => 
      val b5954 = CU.temp(None)
      val x5717 = CounterChain.copy("x5745_0", "x5717").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5717(0)), Const(96)), op=FixMul, results=List(b5954))
      Stage(operands=List(b5954, CU.ctr(x5717(1))), op=FixAdd, results=List(CU.scalarOut(x5279_x5727_ra_s)))
    }
    val x5775 = StreamController(name="x5775",parent="top") { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x5748 = CounterChain(name = "x5748", ctr42).iter(96)
    }
    val x5763_0 = Pipeline(name="x5763_0",parent="x5775") { implicit CU => 
      val x5756 = CU.temp(None)
      val x5755 = CU.temp(None)
      val x5753 = CU.temp(None)
      val x5758 = ScalarBuffer(name="x5758").buffering(1).wtPort(sigma_da)
      val x5748 = CounterChain.copy("x5775", "x5748").iterIdx(0, 0)
      val x5763_unit = CounterChain(name = "x5763_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x5748(0)), Const(96)), op=FixMul, results=List(x5753))
      Stage(operands=List(x5753, Const(0)), op=FixAdd, results=List(x5755))
      Stage(operands=List(x5755, Const(2)), op=FixSla, results=List(x5756))
      Stage(operands=List(x5756, CU.load(x5758)), op=FixAdd, results=List(CU.scalarOut(x5749_b5958_x5762_b5960_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5749_b5959_x5762_b5961_s)))
    }
    val x5771 = Pipeline(name="x5771",parent="x5775") { implicit CU => 
      val ctr43 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5765 = CounterChain(name = "x5765", ctr43).iter(6)
    }
    val x5767_0 = Pipeline(name="x5767_0",parent="x5775") { implicit CU => 
      val b5962 = CU.temp(None)
      val x5748 = CounterChain.copy("x5775", "x5748").iterIdx(0, 0)
      val x5765 = CounterChain.copy("x5771", "x5765").iterIdx(0, 0)
      Stage(operands=List(CU.ctr(x5748(0)), Const(96)), op=FixMul, results=List(b5962))
      Stage(operands=List(b5962, CU.ctr(x5765(0))), op=FixAdd, results=List(CU.vecOut(x5279_x5767_ra_v)))
    }
    val x5772 = MemoryController(name="x5772",parent="x5775",offchip=sigma_oc, mctpe=TileStore) { implicit CU => 
      val x5750 = VectorFIFO(size=1,name="data").wtPort(x5279_x5279_dsp0_bank0_data_v)
      val x5749_b5959 = ScalarFIFO(size=1,name="size").wtPort(x5749_b5959_x5762_b5961_s)
      val x5749_b5958 = ScalarFIFO(size=1,name="offset").wtPort(x5749_b5958_x5762_b5960_s)
    }
    
  }
}
