import pir.graph.{Mux =>_, _}
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object GEMM_Blocked extends PIRApp {
  def main(top:Top) = {
    val x11092_x11109_data_s = Scalar("x11092_x11109_data")
    val x10246_x10246_dsp0_bank0_s = Scalar("x10246_x10246_dsp0_bank0")
    val x10320_x10388_s = Scalar("x10320_x10388")
    val x11760_x11760_dsp0_bank0_s = Scalar("x11760_x11760_dsp0_bank0")
    val x11309_x11309_dsp0_bank1_s = Scalar("x11309_x11309_dsp0_bank1")
    val x10958_x10958_dsp0_bank0_s = Scalar("x10958_x10958_dsp0_bank0")
    val x10289_b12135_x10305_b12137_s = Scalar("x10289_b12135_x10305_b12137")
    val x11455_x11493_s = Scalar("x11455_x11493")
    val x11450_x11450_dsp1_bank0_s = Scalar("x11450_x11450_dsp1_bank0")
    val x9693_x9710_data_s = Scalar("x9693_x9710_data")
    val x9828_x9855_s = Scalar("x9828_x9855")
    val x11761_x11761_dsp0_bank0_s = Scalar("x11761_x11761_dsp0_bank0")
    val x9608_x10174_s = Scalar("x9608_x10174")
    val x9617_x9617_dsp1_bank0_s = Scalar("x9617_x9617_dsp1_bank0")
    val x9756_x9756_dsp0_bank0_s = Scalar("x9756_x9756_dsp0_bank0")
    val x10747_x10747_dsp1_bank0_s = Scalar("x10747_x10747_dsp1_bank0")
    val x11049_x11049_dsp0_bank0_s = Scalar("x11049_x11049_dsp0_bank0")
    val x9759_x9786_s = Scalar("x9759_x9786")
    val x10557_x10625_s = Scalar("x10557_x10625")
    val x10750_b12179_x10765_b12181_s = Scalar("x10750_b12179_x10765_b12181")
    val b_dram_da = DRAMAddress("b_dram", "b_dram")
    val x11310_x11310_dsp0_bank1_s = Scalar("x11310_x11310_dsp0_bank1")
    val x10179_x10179_dsp0_bank0_s = Scalar("x10179_x10179_dsp0_bank0")
    val x9997_x9997_dsp0_bank0_s = Scalar("x9997_x9997_dsp0_bank0")
    val x10066_x10066_dsp0_bank0_s = Scalar("x10066_x10066_dsp0_bank0")
    val x9993_x9993_dsp1_bank0_s = Scalar("x9993_x9993_dsp1_bank0")
    val x11316_x11332_data_s = Scalar("x11316_x11332_data")
    val b_dram_oc = OffChip("b_dram")
    val x11916_b12306_x11930_b12308_s = Scalar("x11916_b12306_x11930_b12308")
    val x10561_x10561_dsp0_bank0_s = Scalar("x10561_x10561_dsp0_bank0")
    val x11884_b12300_x11898_b12302_s = Scalar("x11884_b12300_x11898_b12302")
    val x10557_x10557_dsp1_bank0_s = Scalar("x10557_x10557_dsp1_bank0")
    val x9608_x9608_dsp0_bank0_s = Scalar("x9608_x9608_dsp0_bank0")
    val x10747_x10747_dsp0_bank1_s = Scalar("x10747_x10747_dsp0_bank1")
    val x9919_x9919_dsp0_bank0_s = Scalar("x9919_x9919_dsp0_bank0")
    val x10066_x10104_s = Scalar("x10066_x10104")
    val x10182_x10182_dsp1_bank0_s = Scalar("x10182_x10182_dsp1_bank0")
    val x11419_b12255_x11435_b12257_s = Scalar("x11419_b12255_x11435_b12257")
    val x11524_x11562_s = Scalar("x11524_x11562")
    val x11091_b12216_x11107_b12218_s = Scalar("x11091_b12216_x11107_b12218")
    val x9930_x9947_data_s = Scalar("x9930_x9947_data")
    val x10885_x10885_dsp1_bank0_s = Scalar("x10885_x10885_dsp1_bank0")
    val x10290_x10307_data_s = Scalar("x10290_x10307_data")
    val x10744_x10744_dsp0_bank1_s = Scalar("x10744_x10744_dsp0_bank1")
    val x11980_b12317_x11994_b12319_s = Scalar("x11980_b12317_x11994_b12319")
    val x9759_x9759_dsp0_bank0_s = Scalar("x9759_x9759_dsp0_bank0")
    val x11123_x11259_s = Scalar("x11123_x11259")
    val x10181_x10181_dsp0_bank0_s = Scalar("x10181_x10181_dsp0_bank0")
    val x10886_x10886_dsp0_bank0_s = Scalar("x10886_x10886_dsp0_bank0")
    val x11315_b12239_x11330_b12241_s = Scalar("x11315_b12239_x11330_b12241")
    val x9650_b12065_x9665_b12067_s = Scalar("x9650_b12065_x9665_b12067")
    val x11614_x11614_dsp0_bank0_s = Scalar("x11614_x11614_dsp0_bank0")
    val x10562_x10600_s = Scalar("x10562_x10600")
    val x10393_x10393_dsp0_bank0_s = Scalar("x10393_x10393_dsp0_bank0")
    val x10526_b12156_x10542_b12158_s = Scalar("x10526_b12156_x10542_b12158")
    val a_dram_da = DRAMAddress("a_dram", "a_dram")
    val x10182_x10182_dsp1_bank1_s = Scalar("x10182_x10182_dsp1_bank1")
    val x10854_b12195_x10870_b12197_s = Scalar("x10854_b12195_x10870_b12197")
    val x11346_x11362_data_s = Scalar("x11346_x11362_data")
    val x11127_x11127_dsp0_bank0_s = Scalar("x11127_x11127_dsp0_bank0")
    val x11691_x11718_s = Scalar("x11691_x11718")
    val x9620_b12060_x9635_b12062_s = Scalar("x9620_b12060_x9635_b12062")
    val x10631_x10669_s = Scalar("x10631_x10669")
    val x10822_b12191_x10838_b12193_s = Scalar("x10822_b12191_x10838_b12193")
    val x10557_x10557_dsp0_bank0_s = Scalar("x10557_x10557_dsp0_bank0")
    val x9617_x9617_dsp0_bank1_s = Scalar("x9617_x9617_dsp0_bank1")
    val x9609_x10739_s = Scalar("x9609_x10739")
    val x11454_x11481_s = Scalar("x11454_x11481")
    val x10394_x10394_dsp0_bank0_s = Scalar("x10394_x10394_dsp0_bank0")
    val x11948_b12312_x11962_b12314_s = Scalar("x11948_b12312_x11962_b12314")
    val x10394_x10432_s = Scalar("x10394_x10432")
    val x10746_x10746_dsp1_bank0_s = Scalar("x10746_x10746_dsp1_bank0")
    val x10886_x11022_s = Scalar("x10886_x11022")
    val x9961_b12096_x9977_b12098_s = Scalar("x9961_b12096_x9977_b12098")
    val x11311_x11311_dsp1_bank0_s = Scalar("x11311_x11311_dsp1_bank0")
    val x9616_x9616_dsp1_bank0_s = Scalar("x9616_x9616_dsp1_bank0")
    val x10890_x10890_dsp0_bank0_s = Scalar("x10890_x10890_dsp0_bank0")
    val x11419_b12256_x11435_b12258_s = Scalar("x11419_b12256_x11435_b12258")
    val x11761_x11799_s = Scalar("x11761_x11799")
    val x10324_x10351_s = Scalar("x10324_x10351")
    val x10854_b12196_x10870_b12198_s = Scalar("x10854_b12196_x10870_b12198")
    val x11916_b12305_x11930_b12307_s = Scalar("x11916_b12305_x11930_b12307")
    val x9692_b12071_x9708_b12073_s = Scalar("x9692_b12071_x9708_b12073")
    val x10065_x10065_dsp0_bank0_s = Scalar("x10065_x10065_dsp0_bank0")
    val x9756_x9756_dsp1_bank0_s = Scalar("x9756_x9756_dsp1_bank0")
    val x10215_b12126_x10230_b12128_s = Scalar("x10215_b12126_x10230_b12128")
    val x11451_x11587_s = Scalar("x11451_x11587")
    val x11687_x11687_dsp1_bank0_s = Scalar("x11687_x11687_dsp1_bank0")
    val x9682_x9682_dsp0_bank0_s = Scalar("x9682_x9682_dsp0_bank0")
    val x10886_x10886_dsp1_bank0_s = Scalar("x10886_x10886_dsp1_bank0")
    val x10750_b12180_x10765_b12182_s = Scalar("x10750_b12180_x10765_b12182")
    val x10558_x10694_s = Scalar("x10558_x10694")
    val x10959_x10997_s = Scalar("x10959_x10997")
    val x10751_x10767_data_s = Scalar("x10751_x10767_data")
    val x10822_b12192_x10838_b12194_s = Scalar("x10822_b12192_x10838_b12194")
    val x10558_x10558_dsp0_bank0_s = Scalar("x10558_x10558_dsp0_bank0")
    val x11420_x11437_data_s = Scalar("x11420_x11437_data")
    val x11688_x11688_dsp0_bank0_s = Scalar("x11688_x11688_dsp0_bank0")
    val x10258_x10275_data_s = Scalar("x10258_x10275_data")
    val x11688_x11824_s = Scalar("x11688_x11824")
    val x10179_x10179_dsp0_bank1_s = Scalar("x10179_x10179_dsp0_bank1")
    val x9993_x10129_s = Scalar("x9993_x10129")
    val x11377_x11377_dsp0_bank0_s = Scalar("x11377_x11377_dsp0_bank0")
    val x11311_x11311_dsp1_bank1_s = Scalar("x11311_x11311_dsp1_bank1")
    val x10257_b12131_x10273_b12133_s = Scalar("x10257_b12131_x10273_b12133")
    val x10321_x10321_dsp1_bank0_s = Scalar("x10321_x10321_dsp1_bank0")
    val x10186_x10202_data_s = Scalar("x10186_x10202_data")
    val x9993_x9993_dsp0_bank0_s = Scalar("x9993_x9993_dsp0_bank0")
    val x9997_x10035_s = Scalar("x9997_x10035")
    val x10289_b12136_x10305_b12138_s = Scalar("x10289_b12136_x10305_b12138")
    val x10320_x10320_dsp0_bank0_s = Scalar("x10320_x10320_dsp0_bank0")
    val x11048_x11048_dsp0_bank0_s = Scalar("x11048_x11048_dsp0_bank0")
    val x11948_b12311_x11962_b12313_s = Scalar("x11948_b12311_x11962_b12313")
    val x10325_x10325_dsp0_bank0_s = Scalar("x10325_x10325_dsp0_bank0")
    val x11345_b12245_x11360_b12247_s = Scalar("x11345_b12245_x11360_b12247")
    val x11451_x11451_dsp0_bank0_s = Scalar("x11451_x11451_dsp0_bank0")
    val c_dram_da = DRAMAddress("c_dram", "c_dram")
    val x11657_x11674_data_s = Scalar("x11657_x11674_data")
    val x10324_x10324_dsp0_bank0_s = Scalar("x10324_x10324_dsp0_bank0")
    val x10494_b12151_x10510_b12153_s = Scalar("x10494_b12151_x10510_b12153")
    val x9617_x9617_dsp0_bank0_s = Scalar("x9617_x9617_dsp0_bank0")
    val x10558_x10558_dsp1_bank0_s = Scalar("x10558_x10558_dsp1_bank0")
    val x11624_b12271_x11640_b12273_s = Scalar("x11624_b12271_x11640_b12273")
    val x9828_x9828_dsp0_bank0_s = Scalar("x9828_x9828_dsp0_bank0")
    val x10811_x10811_dsp0_bank0_s = Scalar("x10811_x10811_dsp0_bank0")
    val x9724_b12076_x9740_b12078_s = Scalar("x9724_b12076_x9740_b12078")
    val x9611_x9611_dsp1_bank0_s = Scalar("x9611_x9611_dsp1_bank0")
    val x11450_x11450_dsp0_bank0_s = Scalar("x11450_x11450_dsp0_bank0")
    val x11059_b12212_x11075_b12214_s = Scalar("x11059_b12212_x11075_b12214")
    val x10526_b12155_x10542_b12157_s = Scalar("x10526_b12155_x10542_b12157")
    val x10321_x10457_s = Scalar("x10321_x10457")
    val x11122_x11122_dsp0_bank0_s = Scalar("x11122_x11122_dsp0_bank0")
    val x9756_x9892_s = Scalar("x9756_x9892")
    val x11688_x11688_dsp1_bank0_s = Scalar("x11688_x11688_dsp1_bank0")
    val x9610_x9610_dsp0_bank0_s = Scalar("x9610_x9610_dsp0_bank0")
    val x11312_x11312_dsp1_bank0_s = Scalar("x11312_x11312_dsp1_bank0")
    val x9611_x11869_s = Scalar("x9611_x11869")
    val x11450_x11518_s = Scalar("x11450_x11518")
    val x11126_x11126_dsp0_bank0_s = Scalar("x11126_x11126_dsp0_bank0")
    val x10746_x10746_dsp0_bank0_s = Scalar("x10746_x10746_dsp0_bank0")
    val x10744_x10744_dsp0_bank0_s = Scalar("x10744_x10744_dsp0_bank0")
    val x11524_x11524_dsp0_bank0_s = Scalar("x11524_x11524_dsp0_bank0")
    val x10065_x10092_s = Scalar("x10065_x10092")
    val x11656_b12275_x11672_b12277_s = Scalar("x11656_b12275_x11672_b12277")
    val x10247_x10247_dsp0_bank0_s = Scalar("x10247_x10247_dsp0_bank0")
    val x11123_x11123_dsp0_bank0_s = Scalar("x11123_x11123_dsp0_bank0")
    val x9620_b12059_x9635_b12061_s = Scalar("x9620_b12059_x9635_b12061")
    val x11687_x11687_dsp0_bank0_s = Scalar("x11687_x11687_dsp0_bank0")
    val x11760_x11787_s = Scalar("x11760_x11787")
    val x10959_x10959_dsp0_bank0_s = Scalar("x10959_x10959_dsp0_bank0")
    val x9992_x10060_s = Scalar("x9992_x10060")
    val x11059_b12211_x11075_b12213_s = Scalar("x11059_b12211_x11075_b12213")
    val x10855_x10872_data_s = Scalar("x10855_x10872_data")
    val x10182_x10182_dsp0_bank1_s = Scalar("x10182_x10182_dsp0_bank1")
    val x10780_b12186_x10795_b12188_s = Scalar("x10780_b12186_x10795_b12188")
    val x11312_x11312_dsp1_bank1_s = Scalar("x11312_x11312_dsp1_bank1")
    val x10780_b12185_x10795_b12187_s = Scalar("x10780_b12185_x10795_b12187")
    val x10631_x10631_dsp0_bank0_s = Scalar("x10631_x10631_dsp0_bank0")
    val x9614_x9614_dsp0_bank1_s = Scalar("x9614_x9614_dsp0_bank1")
    val x11060_x11077_data_s = Scalar("x11060_x11077_data")
    val x10181_x10181_dsp1_bank0_s = Scalar("x10181_x10181_dsp1_bank0")
    val x11196_x11234_s = Scalar("x11196_x11234")
    val x10630_x10630_dsp0_bank0_s = Scalar("x10630_x10630_dsp0_bank0")
    val x11692_x11730_s = Scalar("x11692_x11730")
    val x10494_b12152_x10510_b12154_s = Scalar("x10494_b12152_x10510_b12154")
    val x11311_x11311_dsp0_bank0_s = Scalar("x11311_x11311_dsp0_bank0")
    val x10180_x10180_dsp0_bank0_s = Scalar("x10180_x10180_dsp0_bank0")
    val x11312_x11312_dsp0_bank1_s = Scalar("x11312_x11312_dsp0_bank1")
    val x10215_b12125_x10230_b12127_s = Scalar("x10215_b12125_x10230_b12127")
    val x10746_x10746_dsp1_bank1_s = Scalar("x10746_x10746_dsp1_bank1")
    val x9615_x9615_dsp0_bank0_s = Scalar("x9615_x9615_dsp0_bank0")
    val x9918_x9918_dsp0_bank0_s = Scalar("x9918_x9918_dsp0_bank0")
    val c_dram_oc = OffChip("c_dram")
    val x11126_x11153_s = Scalar("x11126_x11153")
    val x9615_x9615_dsp0_bank1_s = Scalar("x9615_x9615_dsp0_bank1")
    val x11310_x11310_dsp0_bank0_s = Scalar("x11310_x11310_dsp0_bank0")
    val x9724_b12075_x9740_b12077_s = Scalar("x9724_b12075_x9740_b12077")
    val a_dram_oc = OffChip("a_dram")
    val x9681_x9681_dsp0_bank0_s = Scalar("x9681_x9681_dsp0_bank0")
    val x9725_x9742_data_s = Scalar("x9725_x9742_data")
    val x11123_x11123_dsp1_bank0_s = Scalar("x11123_x11123_dsp1_bank0")
    val x9692_b12072_x9708_b12074_s = Scalar("x9692_b12072_x9708_b12074")
    val x11523_x11550_s = Scalar("x11523_x11550")
    val x9616_x9616_dsp0_bank1_s = Scalar("x9616_x9616_dsp0_bank1")
    val x10812_x10812_dsp0_bank0_s = Scalar("x10812_x10812_dsp0_bank0")
    val x9609_x9609_dsp0_bank0_s = Scalar("x9609_x9609_dsp0_bank0")
    val x11311_x11311_dsp0_bank1_s = Scalar("x11311_x11311_dsp0_bank1")
    val x11196_x11196_dsp0_bank0_s = Scalar("x11196_x11196_dsp0_bank0")
    val x10181_x10181_dsp1_bank1_s = Scalar("x10181_x10181_dsp1_bank1")
    val x10181_x10181_dsp0_bank1_s = Scalar("x10181_x10181_dsp0_bank1")
    val x11195_x11222_s = Scalar("x11195_x11222")
    val x10889_x10916_s = Scalar("x10889_x10916")
    val x9610_x9610_dsp1_bank0_s = Scalar("x9610_x9610_dsp1_bank0")
    val x10890_x10928_s = Scalar("x10890_x10928")
    val x10216_x10232_data_s = Scalar("x10216_x10232_data")
    val x11624_b12272_x11640_b12274_s = Scalar("x11624_b12272_x11640_b12274")
    val x10823_x10840_data_s = Scalar("x10823_x10840_data")
    val x11122_x11190_s = Scalar("x11122_x11190")
    val x11884_b12299_x11898_b12301_s = Scalar("x11884_b12299_x11898_b12301")
    val x11127_x11165_s = Scalar("x11127_x11165")
    val x10483_x10483_dsp0_bank0_s = Scalar("x10483_x10483_dsp0_bank0")
    val x9617_x9617_dsp1_bank1_s = Scalar("x9617_x9617_dsp1_bank1")
    val x10781_x10797_data_s = Scalar("x10781_x10797_data")
    val x10958_x10985_s = Scalar("x10958_x10985")
    val x11312_x11312_dsp0_bank0_s = Scalar("x11312_x11312_dsp0_bank0")
    val x9996_x9996_dsp0_bank0_s = Scalar("x9996_x9996_dsp0_bank0")
    val x9621_x9637_data_s = Scalar("x9621_x9637_data")
    val x9651_x9667_data_s = Scalar("x9651_x9667_data")
    val x10484_x10484_dsp0_bank0_s = Scalar("x10484_x10484_dsp0_bank0")
    val x10889_x10889_dsp0_bank0_s = Scalar("x10889_x10889_dsp0_bank0")
    val x9616_x9616_dsp0_bank0_s = Scalar("x9616_x9616_dsp0_bank0")
    val x9929_b12091_x9945_b12093_s = Scalar("x9929_b12091_x9945_b12093")
    val x11523_x11523_dsp0_bank0_s = Scalar("x11523_x11523_dsp0_bank0")
    val x10257_b12132_x10273_b12134_s = Scalar("x10257_b12132_x10273_b12134")
    val x9996_x10023_s = Scalar("x9996_x10023")
    val x9961_b12095_x9977_b12097_s = Scalar("x9961_b12095_x9977_b12097")
    val x10320_x10320_dsp1_bank0_s = Scalar("x10320_x10320_dsp1_bank0")
    val x9609_x9609_dsp1_bank0_s = Scalar("x9609_x9609_dsp1_bank0")
    val x11455_x11455_dsp0_bank0_s = Scalar("x11455_x11455_dsp0_bank0")
    val x11376_x11376_dsp0_bank0_s = Scalar("x11376_x11376_dsp0_bank0")
    val x11656_b12276_x11672_b12278_s = Scalar("x11656_b12276_x11672_b12278")
    val x9611_x9611_dsp0_bank0_s = Scalar("x9611_x9611_dsp0_bank0")
    val x11451_x11451_dsp1_bank0_s = Scalar("x11451_x11451_dsp1_bank0")
    val x9829_x9829_dsp0_bank0_s = Scalar("x9829_x9829_dsp0_bank0")
    val x10325_x10363_s = Scalar("x10325_x10363")
    val x10885_x10885_dsp0_bank0_s = Scalar("x10885_x10885_dsp0_bank0")
    val x10182_x10182_dsp0_bank0_s = Scalar("x10182_x10182_dsp0_bank0")
    val x9755_x9755_dsp1_bank0_s = Scalar("x9755_x9755_dsp1_bank0")
    val x9962_x9979_data_s = Scalar("x9962_x9979_data")
    val x10185_b12119_x10200_b12121_s = Scalar("x10185_b12119_x10200_b12121")
    val x9992_x9992_dsp1_bank0_s = Scalar("x9992_x9992_dsp1_bank0")
    val x10746_x10746_dsp0_bank1_s = Scalar("x10746_x10746_dsp0_bank1")
    val x9755_x9755_dsp0_bank0_s = Scalar("x9755_x9755_dsp0_bank0")
    val x11091_b12215_x11107_b12217_s = Scalar("x11091_b12215_x11107_b12217")
    val x9929_b12092_x9945_b12094_s = Scalar("x9929_b12092_x9945_b12094")
    val x10495_x10512_data_s = Scalar("x10495_x10512_data")
    val x10885_x10953_s = Scalar("x10885_x10953")
    val x11122_x11122_dsp1_bank0_s = Scalar("x11122_x11122_dsp1_bank0")
    val x9829_x9867_s = Scalar("x9829_x9867")
    val x11315_b12240_x11330_b12242_s = Scalar("x11315_b12240_x11330_b12242")
    val x9760_x9760_dsp0_bank0_s = Scalar("x9760_x9760_dsp0_bank0")
    val x10185_b12120_x10200_b12122_s = Scalar("x10185_b12120_x10200_b12122")
    val x9616_x9616_dsp1_bank1_s = Scalar("x9616_x9616_dsp1_bank1")
    val x10561_x10588_s = Scalar("x10561_x10588")
    val x9614_x9614_dsp0_bank0_s = Scalar("x9614_x9614_dsp0_bank0")
    val x11387_b12251_x11403_b12253_s = Scalar("x11387_b12251_x11403_b12253")
    val x9755_x9823_s = Scalar("x9755_x9823")
    val x10747_x10747_dsp1_bank1_s = Scalar("x10747_x10747_dsp1_bank1")
    val x9650_b12066_x9665_b12068_s = Scalar("x9650_b12066_x9665_b12068")
    val x11613_x11613_dsp0_bank0_s = Scalar("x11613_x11613_dsp0_bank0")
    val x10393_x10420_s = Scalar("x10393_x10420")
    val x11692_x11692_dsp0_bank0_s = Scalar("x11692_x11692_dsp0_bank0")
    val x10747_x10747_dsp0_bank0_s = Scalar("x10747_x10747_dsp0_bank0")
    val x11980_b12318_x11994_b12320_s = Scalar("x11980_b12318_x11994_b12320")
    val x10321_x10321_dsp0_bank0_s = Scalar("x10321_x10321_dsp0_bank0")
    val x11345_b12246_x11360_b12248_s = Scalar("x11345_b12246_x11360_b12248")
    val x11454_x11454_dsp0_bank0_s = Scalar("x11454_x11454_dsp0_bank0")
    val x10527_x10544_data_s = Scalar("x10527_x10544_data")
    val x10562_x10562_dsp0_bank0_s = Scalar("x10562_x10562_dsp0_bank0")
    val x9608_x9608_dsp1_bank0_s = Scalar("x9608_x9608_dsp1_bank0")
    val x11625_x11642_data_s = Scalar("x11625_x11642_data")
    val x11687_x11755_s = Scalar("x11687_x11755")
    val x10630_x10657_s = Scalar("x10630_x10657")
    val x10745_x10745_dsp0_bank1_s = Scalar("x10745_x10745_dsp0_bank1")
    val x11388_x11405_data_s = Scalar("x11388_x11405_data")
    val x9760_x9798_s = Scalar("x9760_x9798")
    val x11309_x11309_dsp0_bank0_s = Scalar("x11309_x11309_dsp0_bank0")
    val x10745_x10745_dsp0_bank0_s = Scalar("x10745_x10745_dsp0_bank0")
    val x11691_x11691_dsp0_bank0_s = Scalar("x11691_x11691_dsp0_bank0")
    val x10180_x10180_dsp0_bank1_s = Scalar("x10180_x10180_dsp0_bank1")
    val x9992_x9992_dsp0_bank0_s = Scalar("x9992_x9992_dsp0_bank0")
    val x11387_b12252_x11403_b12254_s = Scalar("x11387_b12252_x11403_b12254")
    val x9610_x11304_s = Scalar("x9610_x11304")
    val x11195_x11195_dsp0_bank0_s = Scalar("x11195_x11195_dsp0_bank0")
    val x12012 = Sequential(name="x12012",parent=top) { implicit CU => 
      val x12012_unit = CounterChain(name = "x12012_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
    }
    val x12011 = MetaPipeline(name="x12011",parent=x12012) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(64), step=Const(16), par=4) // Counter
      val x9607 = CounterChain(name = "x9607", ctr1).iter(1)
    }
    val x9608_dsp0_bank0 = MemoryPipeline(name="x9608_dsp0_bank0",parent="x10176") { implicit CU => 
      val b12117 = CU.temp(None)
      val b12303 = CU.temp(None)
      val x10174 = ScalarFIFO(size=1,name="x10174").wtPort(x9608_x10174_s)
      val x10156 = CounterChain.copy("x10175_0", "x10156")
      val x11883 = CounterChain.copy("x11913", "x11883")
      val x11901 = CounterChain.copy("x11908", "x11901")
      val x9608 = SRAM(size=1024,name="x9608",banking = Strided(1)).wtPort(x10174.readPort).rdPort(x9608_x9608_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x10156(0)), Const(16)), op=FixMul, results=List(b12117))
      WAStage(operands=List(b12117, CU.ctr(x10156(1))), op=FixAdd, results=List(x9608.writeAddr))
      RAStage(operands=List(CU.ctr(x11883(0)), Const(16)), op=FixMul, results=List(b12303))
      RAStage(operands=List(b12303, CU.ctr(x11901(0))), op=FixAdd, results=List(x9608.readAddr))
    }
    val x9608_dsp1_bank0 = MemoryPipeline(name="x9608_dsp1_bank0",parent="x10176") { implicit CU => 
      val b12117 = CU.temp(None)
      val b12115 = CU.temp(None)
      val x10174 = ScalarFIFO(size=1,name="x10174").wtPort(x9608_x10174_s)
      val x10156 = CounterChain.copy("x10175_0", "x10156")
      val x9608 = SRAM(size=1024,name="x9608",banking = Strided(1)).wtPort(x10174.readPort).rdPort(x9608_x9608_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x10156(0)), Const(16)), op=FixMul, results=List(b12117))
      WAStage(operands=List(b12117, CU.ctr(x10156(1))), op=FixAdd, results=List(x9608.writeAddr))
      RAStage(operands=List(CU.ctr(x10156(0)), Const(16)), op=FixMul, results=List(b12115))
      RAStage(operands=List(b12115, CU.ctr(x10156(1))), op=FixAdd, results=List(x9608.readAddr))
    }
    val x9609_dsp0_bank0 = MemoryPipeline(name="x9609_dsp0_bank0",parent="x10741") { implicit CU => 
      val b12309 = CU.temp(None)
      val b12177 = CU.temp(None)
      val x10739 = ScalarFIFO(size=1,name="x10739").wtPort(x9609_x10739_s)
      val x11933 = CounterChain.copy("x11940", "x11933")
      val x11915 = CounterChain.copy("x11945", "x11915")
      val x10721 = CounterChain.copy("x10740_0", "x10721")
      val x9609 = SRAM(size=1024,name="x9609",banking = Strided(1)).wtPort(x10739.readPort).rdPort(x9609_x9609_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x10721(0)), Const(16)), op=FixMul, results=List(b12177))
      WAStage(operands=List(b12177, CU.ctr(x10721(1))), op=FixAdd, results=List(x9609.writeAddr))
      RAStage(operands=List(CU.ctr(x11915(0)), Const(16)), op=FixMul, results=List(b12309))
      RAStage(operands=List(b12309, CU.ctr(x11933(0))), op=FixAdd, results=List(x9609.readAddr))
    }
    val x9609_dsp1_bank0 = MemoryPipeline(name="x9609_dsp1_bank0",parent="x10741") { implicit CU => 
      val b12175 = CU.temp(None)
      val b12177 = CU.temp(None)
      val x10739 = ScalarFIFO(size=1,name="x10739").wtPort(x9609_x10739_s)
      val x10721 = CounterChain.copy("x10740_0", "x10721")
      val x9609 = SRAM(size=1024,name="x9609",banking = Strided(1)).wtPort(x10739.readPort).rdPort(x9609_x9609_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x10721(0)), Const(16)), op=FixMul, results=List(b12177))
      WAStage(operands=List(b12177, CU.ctr(x10721(1))), op=FixAdd, results=List(x9609.writeAddr))
      RAStage(operands=List(CU.ctr(x10721(0)), Const(16)), op=FixMul, results=List(b12175))
      RAStage(operands=List(b12175, CU.ctr(x10721(1))), op=FixAdd, results=List(x9609.readAddr))
    }
    val x9610_dsp0_bank0 = MemoryPipeline(name="x9610_dsp0_bank0",parent="x11306") { implicit CU => 
      val b12237 = CU.temp(None)
      val b12315 = CU.temp(None)
      val x11304 = ScalarFIFO(size=1,name="x11304").wtPort(x9610_x11304_s)
      val x11965 = CounterChain.copy("x11972", "x11965")
      val x11947 = CounterChain.copy("x11977", "x11947")
      val x11286 = CounterChain.copy("x11305_0", "x11286")
      val x9610 = SRAM(size=1024,name="x9610",banking = Strided(1)).wtPort(x11304.readPort).rdPort(x9610_x9610_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x11286(0)), Const(16)), op=FixMul, results=List(b12237))
      WAStage(operands=List(b12237, CU.ctr(x11286(1))), op=FixAdd, results=List(x9610.writeAddr))
      RAStage(operands=List(CU.ctr(x11947(0)), Const(16)), op=FixMul, results=List(b12315))
      RAStage(operands=List(b12315, CU.ctr(x11965(0))), op=FixAdd, results=List(x9610.readAddr))
    }
    val x9610_dsp1_bank0 = MemoryPipeline(name="x9610_dsp1_bank0",parent="x11306") { implicit CU => 
      val b12237 = CU.temp(None)
      val b12235 = CU.temp(None)
      val x11304 = ScalarFIFO(size=1,name="x11304").wtPort(x9610_x11304_s)
      val x11286 = CounterChain.copy("x11305_0", "x11286")
      val x9610 = SRAM(size=1024,name="x9610",banking = Strided(1)).wtPort(x11304.readPort).rdPort(x9610_x9610_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x11286(0)), Const(16)), op=FixMul, results=List(b12237))
      WAStage(operands=List(b12237, CU.ctr(x11286(1))), op=FixAdd, results=List(x9610.writeAddr))
      RAStage(operands=List(CU.ctr(x11286(0)), Const(16)), op=FixMul, results=List(b12235))
      RAStage(operands=List(b12235, CU.ctr(x11286(1))), op=FixAdd, results=List(x9610.readAddr))
    }
    val x9611_dsp0_bank0 = MemoryPipeline(name="x9611_dsp0_bank0",parent="x11871") { implicit CU => 
      val b12321 = CU.temp(None)
      val b12297 = CU.temp(None)
      val x11869 = ScalarFIFO(size=1,name="x11869").wtPort(x9611_x11869_s)
      val x11979 = CounterChain.copy("x12009", "x11979")
      val x11851 = CounterChain.copy("x11870_0", "x11851")
      val x11997 = CounterChain.copy("x12004", "x11997")
      val x9611 = SRAM(size=1024,name="x9611",banking = Strided(1)).wtPort(x11869.readPort).rdPort(x9611_x9611_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x11851(0)), Const(16)), op=FixMul, results=List(b12297))
      WAStage(operands=List(b12297, CU.ctr(x11851(1))), op=FixAdd, results=List(x9611.writeAddr))
      RAStage(operands=List(CU.ctr(x11979(0)), Const(16)), op=FixMul, results=List(b12321))
      RAStage(operands=List(b12321, CU.ctr(x11997(0))), op=FixAdd, results=List(x9611.readAddr))
    }
    val x9611_dsp1_bank0 = MemoryPipeline(name="x9611_dsp1_bank0",parent="x11871") { implicit CU => 
      val b12297 = CU.temp(None)
      val b12295 = CU.temp(None)
      val x11869 = ScalarFIFO(size=1,name="x11869").wtPort(x9611_x11869_s)
      val x11851 = CounterChain.copy("x11870_0", "x11851")
      val x9611 = SRAM(size=1024,name="x9611",banking = Strided(1)).wtPort(x11869.readPort).rdPort(x9611_x9611_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x11851(0)), Const(16)), op=FixMul, results=List(b12297))
      WAStage(operands=List(b12297, CU.ctr(x11851(1))), op=FixAdd, results=List(x9611.writeAddr))
      RAStage(operands=List(CU.ctr(x11851(0)), Const(16)), op=FixMul, results=List(b12295))
      RAStage(operands=List(b12295, CU.ctr(x11851(1))), op=FixAdd, results=List(x9611.readAddr))
    }
    val x10176 = MetaPipeline(name="x10176",parent=x12011) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(64), step=Const(16), par=2) // Counter
      val x9613 = CounterChain(name = "x9613", ctr2).iter(2)
    }
    val x9614_dsp0_bank0 = MemoryPipeline(name="x9614_dsp0_bank0",parent="x10176") { implicit CU => 
      val b12111 = CU.temp(None)
      val b12087 = CU.temp(None)
      val x9903 = ScalarFIFO(size=1,name="x9903").wtPort(x9755_x9755_dsp1_bank0_s)
      val x10156 = CounterChain.copy("x10175_0", "x10156")
      val x9680 = CounterChain.copy("x9915", "x9680")
      val x9897 = CounterChain.copy("x9904", "x9897")
      val x9614 = SRAM(size=1024,name="x9614",banking = Strided(1)).wtPort(x9903.readPort).rdPort(x9614_x9614_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x9680(0)), Const(16)), op=FixMul, results=List(b12087))
      WAStage(operands=List(b12087, CU.ctr(x9897(0))), op=FixAdd, results=List(x9614.writeAddr))
      RAStage(operands=List(CU.ctr(x10156(0)), Const(16)), op=FixMul, results=List(b12111))
      RAStage(operands=List(b12111, CU.ctr(x10156(1))), op=FixAdd, results=List(x9614.readAddr))
    }
    val x9614_dsp0_bank1 = MemoryPipeline(name="x9614_dsp0_bank1",parent="x10176") { implicit CU => 
      val b12111 = CU.temp(None)
      val b12089 = CU.temp(None)
      val x9912 = ScalarFIFO(size=1,name="x9912").wtPort(x9756_x9756_dsp1_bank0_s)
      val x9906 = CounterChain.copy("x9913", "x9906")
      val x10156 = CounterChain.copy("x10175_0", "x10156")
      val x9680 = CounterChain.copy("x9915", "x9680")
      val x9614 = SRAM(size=1024,name="x9614",banking = Strided(1)).wtPort(x9912.readPort).rdPort(x9614_x9614_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x9680(0)), Const(16)), op=FixMul, results=List(b12089))
      WAStage(operands=List(b12089, CU.ctr(x9906(0))), op=FixAdd, results=List(x9614.writeAddr))
      RAStage(operands=List(CU.ctr(x10156(0)), Const(16)), op=FixMul, results=List(b12111))
      RAStage(operands=List(b12111, CU.ctr(x10156(1))), op=FixAdd, results=List(x9614.readAddr))
    }
    val x9615_dsp0_bank0 = MemoryPipeline(name="x9615_dsp0_bank0",parent="x10176") { implicit CU => 
      val b12113 = CU.temp(None)
      val b12107 = CU.temp(None)
      val x10140 = ScalarFIFO(size=1,name="x10140").wtPort(x9992_x9992_dsp1_bank0_s)
      val x10156 = CounterChain.copy("x10175_0", "x10156")
      val x10134 = CounterChain.copy("x10141", "x10134")
      val x9917 = CounterChain.copy("x10152", "x9917")
      val x9615 = SRAM(size=1024,name="x9615",banking = Strided(1)).wtPort(x10140.readPort).rdPort(x9615_x9615_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x9917(0)), Const(16)), op=FixMul, results=List(b12107))
      WAStage(operands=List(b12107, CU.ctr(x10134(0))), op=FixAdd, results=List(x9615.writeAddr))
      RAStage(operands=List(CU.ctr(x10156(0)), Const(16)), op=FixMul, results=List(b12113))
      RAStage(operands=List(b12113, CU.ctr(x10156(1))), op=FixAdd, results=List(x9615.readAddr))
    }
    val x9615_dsp0_bank1 = MemoryPipeline(name="x9615_dsp0_bank1",parent="x10176") { implicit CU => 
      val b12113 = CU.temp(None)
      val b12109 = CU.temp(None)
      val x10149 = ScalarFIFO(size=1,name="x10149").wtPort(x9993_x9993_dsp1_bank0_s)
      val x10156 = CounterChain.copy("x10175_0", "x10156")
      val x10143 = CounterChain.copy("x10150", "x10143")
      val x9917 = CounterChain.copy("x10152", "x9917")
      val x9615 = SRAM(size=1024,name="x9615",banking = Strided(1)).wtPort(x10149.readPort).rdPort(x9615_x9615_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x9917(0)), Const(16)), op=FixMul, results=List(b12109))
      WAStage(operands=List(b12109, CU.ctr(x10143(0))), op=FixAdd, results=List(x9615.writeAddr))
      RAStage(operands=List(CU.ctr(x10156(0)), Const(16)), op=FixMul, results=List(b12113))
      RAStage(operands=List(b12113, CU.ctr(x10156(1))), op=FixAdd, results=List(x9615.readAddr))
    }
    val x9616_dsp0_bank0 = MemoryPipeline(name="x9616_dsp0_bank0",parent="x10176") { implicit CU => 
      val b12079 = CU.temp(None)
      val b12063 = CU.temp(None)
      val x9645 = ScalarFIFO(size=1,name="x9645").wtPort(x9621_x9637_data_s)
      val x9639 = CounterChain.copy("x9646", "x9639")
      val x9619 = CounterChain.copy("x9647", "x9619")
      val x9758 = CounterChain.copy("x9825", "x9758")
      val x9777 = CounterChain.copy("x9787_0", "x9777")
      val x9616 = SRAM(size=256,name="x9616",banking = Strided(1)).wtPort(x9645.readPort).rdPort(x9616_x9616_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x9619(0)), Const(16)), op=FixMul, results=List(b12063))
      WAStage(operands=List(b12063, CU.ctr(x9639(0))), op=FixAdd, results=List(x9616.writeAddr))
      RAStage(operands=List(CU.ctr(x9758(0)), Const(16)), op=FixMul, results=List(b12079))
      RAStage(operands=List(b12079, CU.ctr(x9777(0))), op=FixAdd, results=List(x9616.readAddr))
    }
    val x9616_dsp0_bank1 = MemoryPipeline(name="x9616_dsp0_bank1",parent="x10176") { implicit CU => 
      val b12063 = CU.temp(None)
      val b12081 = CU.temp(None)
      val x9645 = ScalarFIFO(size=1,name="x9645").wtPort(x9621_x9637_data_s)
      val x9639 = CounterChain.copy("x9646", "x9639")
      val x9619 = CounterChain.copy("x9647", "x9619")
      val x9758 = CounterChain.copy("x9825", "x9758")
      val x9789 = CounterChain.copy("x9799_0", "x9789")
      val x9616 = SRAM(size=256,name="x9616",banking = Strided(1)).wtPort(x9645.readPort).rdPort(x9616_x9616_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x9619(0)), Const(16)), op=FixMul, results=List(b12063))
      WAStage(operands=List(b12063, CU.ctr(x9639(0))), op=FixAdd, results=List(x9616.writeAddr))
      RAStage(operands=List(CU.ctr(x9758(0)), Const(16)), op=FixMul, results=List(b12081))
      RAStage(operands=List(b12081, CU.ctr(x9789(0))), op=FixAdd, results=List(x9616.readAddr))
    }
    val x9616_dsp1_bank0 = MemoryPipeline(name="x9616_dsp1_bank0",parent="x10176") { implicit CU => 
      val b12063 = CU.temp(None)
      val b12083 = CU.temp(None)
      val x9645 = ScalarFIFO(size=1,name="x9645").wtPort(x9621_x9637_data_s)
      val x9639 = CounterChain.copy("x9646", "x9639")
      val x9619 = CounterChain.copy("x9647", "x9619")
      val x9846 = CounterChain.copy("x9856_0", "x9846")
      val x9827 = CounterChain.copy("x9894", "x9827")
      val x9616 = SRAM(size=256,name="x9616",banking = Strided(1)).wtPort(x9645.readPort).rdPort(x9616_x9616_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x9619(0)), Const(16)), op=FixMul, results=List(b12063))
      WAStage(operands=List(b12063, CU.ctr(x9639(0))), op=FixAdd, results=List(x9616.writeAddr))
      RAStage(operands=List(CU.ctr(x9827(0)), Const(16)), op=FixMul, results=List(b12083))
      RAStage(operands=List(b12083, CU.ctr(x9846(0))), op=FixAdd, results=List(x9616.readAddr))
    }
    val x9616_dsp1_bank1 = MemoryPipeline(name="x9616_dsp1_bank1",parent="x10176") { implicit CU => 
      val b12063 = CU.temp(None)
      val b12085 = CU.temp(None)
      val x9645 = ScalarFIFO(size=1,name="x9645").wtPort(x9621_x9637_data_s)
      val x9639 = CounterChain.copy("x9646", "x9639")
      val x9619 = CounterChain.copy("x9647", "x9619")
      val x9858 = CounterChain.copy("x9868_0", "x9858")
      val x9827 = CounterChain.copy("x9894", "x9827")
      val x9616 = SRAM(size=256,name="x9616",banking = Strided(1)).wtPort(x9645.readPort).rdPort(x9616_x9616_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x9619(0)), Const(16)), op=FixMul, results=List(b12063))
      WAStage(operands=List(b12063, CU.ctr(x9639(0))), op=FixAdd, results=List(x9616.writeAddr))
      RAStage(operands=List(CU.ctr(x9827(0)), Const(16)), op=FixMul, results=List(b12085))
      RAStage(operands=List(b12085, CU.ctr(x9858(0))), op=FixAdd, results=List(x9616.readAddr))
    }
    val x9617_dsp0_bank0 = MemoryPipeline(name="x9617_dsp0_bank0",parent="x10176") { implicit CU => 
      val b12069 = CU.temp(None)
      val b12099 = CU.temp(None)
      val x9675 = ScalarFIFO(size=1,name="x9675").wtPort(x9651_x9667_data_s)
      val x9649 = CounterChain.copy("x9677", "x9649")
      val x10014 = CounterChain.copy("x10024_0", "x10014")
      val x9995 = CounterChain.copy("x10062", "x9995")
      val x9669 = CounterChain.copy("x9676", "x9669")
      val x9617 = SRAM(size=256,name="x9617",banking = Strided(1)).wtPort(x9675.readPort).rdPort(x9617_x9617_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x9649(0)), Const(16)), op=FixMul, results=List(b12069))
      WAStage(operands=List(b12069, CU.ctr(x9669(0))), op=FixAdd, results=List(x9617.writeAddr))
      RAStage(operands=List(CU.ctr(x9995(0)), Const(16)), op=FixMul, results=List(b12099))
      RAStage(operands=List(b12099, CU.ctr(x10014(0))), op=FixAdd, results=List(x9617.readAddr))
    }
    val x9617_dsp0_bank1 = MemoryPipeline(name="x9617_dsp0_bank1",parent="x10176") { implicit CU => 
      val b12069 = CU.temp(None)
      val b12101 = CU.temp(None)
      val x9675 = ScalarFIFO(size=1,name="x9675").wtPort(x9651_x9667_data_s)
      val x9649 = CounterChain.copy("x9677", "x9649")
      val x10026 = CounterChain.copy("x10036_0", "x10026")
      val x9995 = CounterChain.copy("x10062", "x9995")
      val x9669 = CounterChain.copy("x9676", "x9669")
      val x9617 = SRAM(size=256,name="x9617",banking = Strided(1)).wtPort(x9675.readPort).rdPort(x9617_x9617_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x9649(0)), Const(16)), op=FixMul, results=List(b12069))
      WAStage(operands=List(b12069, CU.ctr(x9669(0))), op=FixAdd, results=List(x9617.writeAddr))
      RAStage(operands=List(CU.ctr(x9995(0)), Const(16)), op=FixMul, results=List(b12101))
      RAStage(operands=List(b12101, CU.ctr(x10026(0))), op=FixAdd, results=List(x9617.readAddr))
    }
    val x9617_dsp1_bank0 = MemoryPipeline(name="x9617_dsp1_bank0",parent="x10176") { implicit CU => 
      val b12069 = CU.temp(None)
      val b12103 = CU.temp(None)
      val x9675 = ScalarFIFO(size=1,name="x9675").wtPort(x9651_x9667_data_s)
      val x9649 = CounterChain.copy("x9677", "x9649")
      val x10083 = CounterChain.copy("x10093_0", "x10083")
      val x10064 = CounterChain.copy("x10131", "x10064")
      val x9669 = CounterChain.copy("x9676", "x9669")
      val x9617 = SRAM(size=256,name="x9617",banking = Strided(1)).wtPort(x9675.readPort).rdPort(x9617_x9617_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x9649(0)), Const(16)), op=FixMul, results=List(b12069))
      WAStage(operands=List(b12069, CU.ctr(x9669(0))), op=FixAdd, results=List(x9617.writeAddr))
      RAStage(operands=List(CU.ctr(x10064(0)), Const(16)), op=FixMul, results=List(b12103))
      RAStage(operands=List(b12103, CU.ctr(x10083(0))), op=FixAdd, results=List(x9617.readAddr))
    }
    val x9617_dsp1_bank1 = MemoryPipeline(name="x9617_dsp1_bank1",parent="x10176") { implicit CU => 
      val b12069 = CU.temp(None)
      val b12105 = CU.temp(None)
      val x9675 = ScalarFIFO(size=1,name="x9675").wtPort(x9651_x9667_data_s)
      val x9649 = CounterChain.copy("x9677", "x9649")
      val x10095 = CounterChain.copy("x10105_0", "x10095")
      val x10064 = CounterChain.copy("x10131", "x10064")
      val x9669 = CounterChain.copy("x9676", "x9669")
      val x9617 = SRAM(size=256,name="x9617",banking = Strided(1)).wtPort(x9675.readPort).rdPort(x9617_x9617_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x9649(0)), Const(16)), op=FixMul, results=List(b12069))
      WAStage(operands=List(b12069, CU.ctr(x9669(0))), op=FixAdd, results=List(x9617.writeAddr))
      RAStage(operands=List(CU.ctr(x10064(0)), Const(16)), op=FixMul, results=List(b12105))
      RAStage(operands=List(b12105, CU.ctr(x10095(0))), op=FixAdd, results=List(x9617.readAddr))
    }
    val x9647 = StreamController(name="x9647",parent=x10176) { implicit CU => 
      val ctr3 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9619 = CounterChain(name = "x9619", ctr3).iter(16)
    }
    val x9636_0 = Pipeline(name="x9636_0",parent=x9647) { implicit CU => 
      val x9624 = CU.temp(None)
      val x9627 = CU.temp(None)
      val x9626 = CU.temp(None)
      val x9622 = CU.temp(None)
      val x9629 = ScalarBuffer(name="x9629").wtPort(b_dram_da)
      val x9613 = CounterChain.copy("x10176", "x9613")
      val x9619 = CounterChain.copy("x9647", "x9619")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x9636_unit = CounterChain(name = "x9636_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x9613(0)), CU.ctr(x9619(0))), op=FixAdd, results=List(x9622))
      Stage(operands=List(x9622, Const(6)), op=FixSla, results=List(x9624))
      Stage(operands=List(x9624, CU.ctr(x9607(0))), op=FixAdd, results=List(x9626))
      Stage(operands=List(x9626, Const(3)), op=FixSla, results=List(x9627))
      Stage(operands=List(x9627, CU.load(x9629)), op=FixAdd, results=List(CU.scalarOut(x9620_b12059_x9635_b12061_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9620_b12060_x9635_b12062_s)))
    }
    val x9637 = MemoryController(name="x9637",parent=x9647,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x9620_b12060 = ScalarFIFO(size=1,name="size").wtPort(x9620_b12060_x9635_b12062_s)
      val x9620_b12059 = ScalarFIFO(size=1,name="offset").wtPort(x9620_b12059_x9635_b12061_s)
      CU.newSout("data", x9621_x9637_data_s)
    }
    val x9646 = Pipeline(name="x9646",parent=x9647) { implicit CU => 
      val ctr4 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9639 = CounterChain(name = "x9639", ctr4).iter(1)
    }
    val x9677 = StreamController(name="x9677",parent=x10176) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9649 = CounterChain(name = "x9649", ctr5).iter(16)
    }
    val x9666_0 = Pipeline(name="x9666_0",parent=x9677) { implicit CU => 
      val x9656 = CU.temp(None)
      val x9652 = CU.temp(None)
      val x9657 = CU.temp(None)
      val x9654 = CU.temp(None)
      val x9659 = ScalarBuffer(name="x9659").wtPort(b_dram_da)
      val x9613 = CounterChain.copy("x10176", "x9613")
      val x9649 = CounterChain.copy("x9677", "x9649")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x9666_unit = CounterChain(name = "x9666_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x9613(0)), CU.ctr(x9649(0))), op=FixAdd, results=List(x9652))
      Stage(operands=List(x9652, Const(6)), op=FixSla, results=List(x9654))
      Stage(operands=List(x9654, CU.ctr(x9607(0))), op=FixAdd, results=List(x9656))
      Stage(operands=List(x9656, Const(3)), op=FixSla, results=List(x9657))
      Stage(operands=List(x9657, CU.load(x9659)), op=FixAdd, results=List(CU.scalarOut(x9650_b12065_x9665_b12067_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9650_b12066_x9665_b12068_s)))
    }
    val x9667 = MemoryController(name="x9667",parent=x9677,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x9650_b12066 = ScalarFIFO(size=1,name="size").wtPort(x9650_b12066_x9665_b12068_s)
      val x9650_b12065 = ScalarFIFO(size=1,name="offset").wtPort(x9650_b12065_x9665_b12067_s)
      CU.newSout("data", x9651_x9667_data_s)
    }
    val x9676 = Pipeline(name="x9676",parent=x9677) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9669 = CounterChain(name = "x9669", ctr6).iter(1)
    }
    val x9915 = MetaPipeline(name="x9915",parent=x10176) { implicit CU => 
      val ctr7 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x9680 = CounterChain(name = "x9680", ctr7).iter(32)
    }
    val x9681_dsp0_bank0 = MemoryPipeline(name="x9681_dsp0_bank0",parent="x9915") { implicit CU => 
      val x9719 = ScalarFIFO(size=1,name="x9719").wtPort(x9693_x9710_data_s)
      val x9758 = CounterChain.copy("x9825", "x9758")
      val x9712 = CounterChain.copy("x9720", "x9712")
      val x9681 = SRAM(size=16,name="x9681",banking = Strided(1)).wtPort(x9719.readPort).rdPort(x9681_x9681_dsp0_bank0_s).rdAddr(x9758(0)).rdAddr(x9758(0)).wtAddr(x9712(0))
    }
    val x9682_dsp0_bank0 = MemoryPipeline(name="x9682_dsp0_bank0",parent="x9915") { implicit CU => 
      val x9751 = ScalarFIFO(size=1,name="x9751").wtPort(x9725_x9742_data_s)
      val x9744 = CounterChain.copy("x9752", "x9744")
      val x9827 = CounterChain.copy("x9894", "x9827")
      val x9682 = SRAM(size=16,name="x9682",banking = Strided(1)).wtPort(x9751.readPort).rdPort(x9682_x9682_dsp0_bank0_s).rdAddr(x9827(0)).rdAddr(x9827(0)).wtAddr(x9744(0))
    }
    val x9721 = StreamController(name="x9721",parent=x9915) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x9691 = CounterChain(name = "x9691", ctr8).iter(1)
    }
    val x9709_0 = Pipeline(name="x9709_0",parent=x9721) { implicit CU => 
      val x9694 = CU.temp(None)
      val x9699 = CU.temp(None)
      val x9698 = CU.temp(None)
      val x9696 = CU.temp(None)
      val x9701 = ScalarBuffer(name="x9701").wtPort(a_dram_da)
      val x9680 = CounterChain.copy("x9915", "x9680")
      val x9613 = CounterChain.copy("x10176", "x9613")
      val x9709_unit = CounterChain(name = "x9709_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x9691 = CounterChain.copy("x9721", "x9691")
      Stage(operands=List(CU.ctr(x9680(0)), CU.ctr(x9691(0))), op=FixAdd, results=List(x9694))
      Stage(operands=List(x9694, Const(6)), op=FixSla, results=List(x9696))
      Stage(operands=List(x9696, CU.ctr(x9613(0))), op=FixAdd, results=List(x9698))
      Stage(operands=List(x9698, Const(3)), op=FixSla, results=List(x9699))
      Stage(operands=List(x9699, CU.load(x9701)), op=FixAdd, results=List(CU.scalarOut(x9692_b12071_x9708_b12073_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9692_b12072_x9708_b12074_s)))
    }
    val x9710 = MemoryController(name="x9710",parent=x9721,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x9692_b12072 = ScalarFIFO(size=1,name="size").wtPort(x9692_b12072_x9708_b12074_s)
      val x9692_b12071 = ScalarFIFO(size=1,name="offset").wtPort(x9692_b12071_x9708_b12073_s)
      CU.newSout("data", x9693_x9710_data_s)
    }
    val x9720 = Pipeline(name="x9720",parent=x9721) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9712 = CounterChain(name = "x9712", ctr9).iter(16)
    }
    val x9753 = StreamController(name="x9753",parent=x9915) { implicit CU => 
      val ctr10 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x9723 = CounterChain(name = "x9723", ctr10).iter(1)
    }
    val x9741_0 = Pipeline(name="x9741_0",parent=x9753) { implicit CU => 
      val x9728 = CU.temp(None)
      val x9726 = CU.temp(None)
      val x9731 = CU.temp(None)
      val x9730 = CU.temp(None)
      val x9733 = ScalarBuffer(name="x9733").wtPort(a_dram_da)
      val x9741_unit = CounterChain(name = "x9741_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x9680 = CounterChain.copy("x9915", "x9680")
      val x9723 = CounterChain.copy("x9753", "x9723")
      val x9613 = CounterChain.copy("x10176", "x9613")
      Stage(operands=List(CU.ctr(x9680(0)), CU.ctr(x9723(0))), op=FixAdd, results=List(x9726))
      Stage(operands=List(x9726, Const(6)), op=FixSla, results=List(x9728))
      Stage(operands=List(x9728, CU.ctr(x9613(0))), op=FixAdd, results=List(x9730))
      Stage(operands=List(x9730, Const(3)), op=FixSla, results=List(x9731))
      Stage(operands=List(x9731, CU.load(x9733)), op=FixAdd, results=List(CU.scalarOut(x9724_b12075_x9740_b12077_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9724_b12076_x9740_b12078_s)))
    }
    val x9742 = MemoryController(name="x9742",parent=x9753,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x9724_b12075 = ScalarFIFO(size=1,name="offset").wtPort(x9724_b12075_x9740_b12077_s)
      val x9724_b12076 = ScalarFIFO(size=1,name="size").wtPort(x9724_b12076_x9740_b12078_s)
      CU.newSout("data", x9725_x9742_data_s)
    }
    val x9752 = Pipeline(name="x9752",parent=x9753) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9744 = CounterChain(name = "x9744", ctr11).iter(16)
    }
    val x9755_dsp0_bank0 = MemoryPipeline(name="x9755_dsp0_bank0",parent="x9825") { implicit CU => 
      val x9823 = ScalarFIFO(size=1,name="x9823").wtPort(x9755_x9823_s)
      val x9802 = CounterChain.copy("x9824_0", "x9802")
      val x9755 = SRAM(size=16,name="x9755",banking = Strided(1)).wtPort(x9823.readPort).rdPort(x9755_x9755_dsp0_bank0_s).rdAddr(x9802(0)).wtAddr(x9802(0))
    }
    val x9755_dsp1_bank0 = MemoryPipeline(name="x9755_dsp1_bank0",parent="x9825") { implicit CU => 
      val x9823 = ScalarFIFO(size=1,name="x9823").wtPort(x9755_x9823_s)
      val x9897 = CounterChain.copy("x9904", "x9897")
      val x9802 = CounterChain.copy("x9824_0", "x9802")
      val x9755 = SRAM(size=16,name="x9755",banking = Strided(1)).wtPort(x9823.readPort).rdPort(x9755_x9755_dsp1_bank0_s).rdAddr(x9897(0)).wtAddr(x9802(0))
    }
    val x9756_dsp0_bank0 = MemoryPipeline(name="x9756_dsp0_bank0",parent="x9894") { implicit CU => 
      val x9892 = ScalarFIFO(size=1,name="x9892").wtPort(x9756_x9892_s)
      val x9871 = CounterChain.copy("x9893_0", "x9871")
      val x9756 = SRAM(size=16,name="x9756",banking = Strided(1)).wtPort(x9892.readPort).rdPort(x9756_x9756_dsp0_bank0_s).rdAddr(x9871(0)).wtAddr(x9871(0))
    }
    val x9756_dsp1_bank0 = MemoryPipeline(name="x9756_dsp1_bank0",parent="x9894") { implicit CU => 
      val x9892 = ScalarFIFO(size=1,name="x9892").wtPort(x9756_x9892_s)
      val x9906 = CounterChain.copy("x9913", "x9906")
      val x9871 = CounterChain.copy("x9893_0", "x9871")
      val x9756 = SRAM(size=16,name="x9756",banking = Strided(1)).wtPort(x9892.readPort).rdPort(x9756_x9756_dsp1_bank0_s).rdAddr(x9906(0)).wtAddr(x9871(0))
    }
    val x9825 = MetaPipeline(name="x9825",parent=x9915) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x9758 = CounterChain(name = "x9758", ctr12).iter(8)
    }
    val x9759_dsp0_bank0 = MemoryPipeline(name="x9759_dsp0_bank0",parent="x9825") { implicit CU => 
      val x9786 = ScalarFIFO(size=1,name="x9786").wtPort(x9759_x9786_s)
      val x9777 = CounterChain.copy("x9787_0", "x9777")
      val x9802 = CounterChain.copy("x9824_0", "x9802")
      val x9759 = SRAM(size=16,name="x9759",banking = Strided(1)).wtPort(x9786.readPort).rdPort(x9759_x9759_dsp0_bank0_s).rdAddr(x9802(0)).wtAddr(x9777(0))
    }
    val x9760_dsp0_bank0 = MemoryPipeline(name="x9760_dsp0_bank0",parent="x9825") { implicit CU => 
      val x9798 = ScalarFIFO(size=1,name="x9798").wtPort(x9760_x9798_s)
      val x9789 = CounterChain.copy("x9799_0", "x9789")
      val x9802 = CounterChain.copy("x9824_0", "x9802")
      val x9760 = SRAM(size=16,name="x9760",banking = Strided(1)).wtPort(x9798.readPort).rdPort(x9760_x9760_dsp0_bank0_s).rdAddr(x9802(0)).wtAddr(x9789(0))
    }
    val x9787_0 = Pipeline(name="x9787_0",parent=x9825) { implicit CU => 
      val x9782 = ScalarFIFO(size=1,name="x9782").wtPort(x9616_x9616_dsp0_bank0_s)
      val x9761 = ScalarBuffer(name="x9761").wtPort(x9681_x9681_dsp0_bank0_s)
      val ctr13 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9777 = CounterChain(name = "x9777", ctr13).iter(1)
      Stage(operands=List(CU.load(x9782), CU.load(x9761)), op=FixMul, results=List(CU.scalarOut(x9759_x9786_s)))
    }
    val x9799_0 = Pipeline(name="x9799_0",parent=x9825) { implicit CU => 
      val x9762 = ScalarBuffer(name="x9762").wtPort(x9681_x9681_dsp0_bank0_s)
      val x9794 = ScalarFIFO(size=1,name="x9794").wtPort(x9616_x9616_dsp0_bank1_s)
      val ctr14 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9789 = CounterChain(name = "x9789", ctr14).iter(1)
      Stage(operands=List(CU.load(x9794), CU.load(x9762)), op=FixMul, results=List(CU.scalarOut(x9760_x9798_s)))
    }
    val x9824_0 = Pipeline(name="x9824_0",parent=x9825) { implicit CU => 
      val x9818 = CU.temp(None)
      val x9808 = ScalarFIFO(size=1,name="x9808").wtPort(x9760_x9760_dsp0_bank0_s)
      val x9810 = ScalarFIFO(size=1,name="x9810").wtPort(x9755_x9755_dsp0_bank0_s)
      val x9806 = ScalarFIFO(size=1,name="x9806").wtPort(x9759_x9759_dsp0_bank0_s)
      val ctr15 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9802 = CounterChain(name = "x9802", ctr15).iter(1)
      Stage(operands=List(CU.load(x9806), CU.load(x9808)), op=FixAdd, results=List(x9818))
      Stage(operands=List(x9818, CU.load(x9810)), op=FixAdd, results=List(CU.scalarOut(x9755_x9823_s)))
    }
    val x9894 = MetaPipeline(name="x9894",parent=x9915) { implicit CU => 
      val ctr16 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x9827 = CounterChain(name = "x9827", ctr16).iter(8)
    }
    val x9828_dsp0_bank0 = MemoryPipeline(name="x9828_dsp0_bank0",parent="x9894") { implicit CU => 
      val x9855 = ScalarFIFO(size=1,name="x9855").wtPort(x9828_x9855_s)
      val x9846 = CounterChain.copy("x9856_0", "x9846")
      val x9871 = CounterChain.copy("x9893_0", "x9871")
      val x9828 = SRAM(size=16,name="x9828",banking = Strided(1)).wtPort(x9855.readPort).rdPort(x9828_x9828_dsp0_bank0_s).rdAddr(x9871(0)).wtAddr(x9846(0))
    }
    val x9829_dsp0_bank0 = MemoryPipeline(name="x9829_dsp0_bank0",parent="x9894") { implicit CU => 
      val x9867 = ScalarFIFO(size=1,name="x9867").wtPort(x9829_x9867_s)
      val x9858 = CounterChain.copy("x9868_0", "x9858")
      val x9871 = CounterChain.copy("x9893_0", "x9871")
      val x9829 = SRAM(size=16,name="x9829",banking = Strided(1)).wtPort(x9867.readPort).rdPort(x9829_x9829_dsp0_bank0_s).rdAddr(x9871(0)).wtAddr(x9858(0))
    }
    val x9856_0 = Pipeline(name="x9856_0",parent=x9894) { implicit CU => 
      val x9830 = ScalarBuffer(name="x9830").wtPort(x9682_x9682_dsp0_bank0_s)
      val x9851 = ScalarFIFO(size=1,name="x9851").wtPort(x9616_x9616_dsp1_bank0_s)
      val ctr17 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9846 = CounterChain(name = "x9846", ctr17).iter(1)
      Stage(operands=List(CU.load(x9851), CU.load(x9830)), op=FixMul, results=List(CU.scalarOut(x9828_x9855_s)))
    }
    val x9868_0 = Pipeline(name="x9868_0",parent=x9894) { implicit CU => 
      val x9831 = ScalarBuffer(name="x9831").wtPort(x9682_x9682_dsp0_bank0_s)
      val x9863 = ScalarFIFO(size=1,name="x9863").wtPort(x9616_x9616_dsp1_bank1_s)
      val ctr18 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9858 = CounterChain(name = "x9858", ctr18).iter(1)
      Stage(operands=List(CU.load(x9863), CU.load(x9831)), op=FixMul, results=List(CU.scalarOut(x9829_x9867_s)))
    }
    val x9893_0 = Pipeline(name="x9893_0",parent=x9894) { implicit CU => 
      val x9887 = CU.temp(None)
      val x9877 = ScalarFIFO(size=1,name="x9877").wtPort(x9829_x9829_dsp0_bank0_s)
      val x9879 = ScalarFIFO(size=1,name="x9879").wtPort(x9756_x9756_dsp0_bank0_s)
      val x9875 = ScalarFIFO(size=1,name="x9875").wtPort(x9828_x9828_dsp0_bank0_s)
      val ctr19 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x9871 = CounterChain(name = "x9871", ctr19).iter(1)
      Stage(operands=List(CU.load(x9875), CU.load(x9877)), op=FixAdd, results=List(x9887))
      Stage(operands=List(x9887, CU.load(x9879)), op=FixAdd, results=List(CU.scalarOut(x9756_x9892_s)))
    }
    val x9904 = Pipeline(name="x9904",parent=x9915) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9897 = CounterChain(name = "x9897", ctr20).iter(16)
    }
    val x9913 = Pipeline(name="x9913",parent=x9915) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9906 = CounterChain(name = "x9906", ctr21).iter(16)
    }
    val x10152 = MetaPipeline(name="x10152",parent=x10176) { implicit CU => 
      val ctr22 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x9917 = CounterChain(name = "x9917", ctr22).iter(32)
    }
    val x9918_dsp0_bank0 = MemoryPipeline(name="x9918_dsp0_bank0",parent="x10152") { implicit CU => 
      val x9956 = ScalarFIFO(size=1,name="x9956").wtPort(x9930_x9947_data_s)
      val x9949 = CounterChain.copy("x9957", "x9949")
      val x9995 = CounterChain.copy("x10062", "x9995")
      val x9918 = SRAM(size=16,name="x9918",banking = Strided(1)).wtPort(x9956.readPort).rdPort(x9918_x9918_dsp0_bank0_s).rdAddr(x9995(0)).rdAddr(x9995(0)).wtAddr(x9949(0))
    }
    val x9919_dsp0_bank0 = MemoryPipeline(name="x9919_dsp0_bank0",parent="x10152") { implicit CU => 
      val x9988 = ScalarFIFO(size=1,name="x9988").wtPort(x9962_x9979_data_s)
      val x9981 = CounterChain.copy("x9989", "x9981")
      val x10064 = CounterChain.copy("x10131", "x10064")
      val x9919 = SRAM(size=16,name="x9919",banking = Strided(1)).wtPort(x9988.readPort).rdPort(x9919_x9919_dsp0_bank0_s).rdAddr(x10064(0)).rdAddr(x10064(0)).wtAddr(x9981(0))
    }
    val x9958 = StreamController(name="x9958",parent=x10152) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x9928 = CounterChain(name = "x9928", ctr23).iter(1)
    }
    val x9946_0 = Pipeline(name="x9946_0",parent=x9958) { implicit CU => 
      val x9935 = CU.temp(None)
      val x9933 = CU.temp(None)
      val x9936 = CU.temp(None)
      val x9931 = CU.temp(None)
      val x9938 = ScalarBuffer(name="x9938").wtPort(a_dram_da)
      val x9946_unit = CounterChain(name = "x9946_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x9928 = CounterChain.copy("x9958", "x9928")
      val x9613 = CounterChain.copy("x10176", "x9613")
      val x9917 = CounterChain.copy("x10152", "x9917")
      Stage(operands=List(CU.ctr(x9917(0)), CU.ctr(x9928(0))), op=FixAdd, results=List(x9931))
      Stage(operands=List(x9931, Const(6)), op=FixSla, results=List(x9933))
      Stage(operands=List(x9933, CU.ctr(x9613(0))), op=FixAdd, results=List(x9935))
      Stage(operands=List(x9935, Const(3)), op=FixSla, results=List(x9936))
      Stage(operands=List(x9936, CU.load(x9938)), op=FixAdd, results=List(CU.scalarOut(x9929_b12091_x9945_b12093_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9929_b12092_x9945_b12094_s)))
    }
    val x9947 = MemoryController(name="x9947",parent=x9958,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x9929_b12092 = ScalarFIFO(size=1,name="size").wtPort(x9929_b12092_x9945_b12094_s)
      val x9929_b12091 = ScalarFIFO(size=1,name="offset").wtPort(x9929_b12091_x9945_b12093_s)
      CU.newSout("data", x9930_x9947_data_s)
    }
    val x9957 = Pipeline(name="x9957",parent=x9958) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9949 = CounterChain(name = "x9949", ctr24).iter(16)
    }
    val x9990 = StreamController(name="x9990",parent=x10152) { implicit CU => 
      val ctr25 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x9960 = CounterChain(name = "x9960", ctr25).iter(1)
    }
    val x9978_0 = Pipeline(name="x9978_0",parent=x9990) { implicit CU => 
      val x9968 = CU.temp(None)
      val x9967 = CU.temp(None)
      val x9965 = CU.temp(None)
      val x9963 = CU.temp(None)
      val x9970 = ScalarBuffer(name="x9970").wtPort(a_dram_da)
      val x9978_unit = CounterChain(name = "x9978_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x9960 = CounterChain.copy("x9990", "x9960")
      val x9613 = CounterChain.copy("x10176", "x9613")
      val x9917 = CounterChain.copy("x10152", "x9917")
      Stage(operands=List(CU.ctr(x9917(0)), CU.ctr(x9960(0))), op=FixAdd, results=List(x9963))
      Stage(operands=List(x9963, Const(6)), op=FixSla, results=List(x9965))
      Stage(operands=List(x9965, CU.ctr(x9613(0))), op=FixAdd, results=List(x9967))
      Stage(operands=List(x9967, Const(3)), op=FixSla, results=List(x9968))
      Stage(operands=List(x9968, CU.load(x9970)), op=FixAdd, results=List(CU.scalarOut(x9961_b12095_x9977_b12097_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x9961_b12096_x9977_b12098_s)))
    }
    val x9979 = MemoryController(name="x9979",parent=x9990,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x9961_b12096 = ScalarFIFO(size=1,name="size").wtPort(x9961_b12096_x9977_b12098_s)
      val x9961_b12095 = ScalarFIFO(size=1,name="offset").wtPort(x9961_b12095_x9977_b12097_s)
      CU.newSout("data", x9962_x9979_data_s)
    }
    val x9989 = Pipeline(name="x9989",parent=x9990) { implicit CU => 
      val ctr26 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x9981 = CounterChain(name = "x9981", ctr26).iter(16)
    }
    val x9992_dsp0_bank0 = MemoryPipeline(name="x9992_dsp0_bank0",parent="x10062") { implicit CU => 
      val x10060 = ScalarFIFO(size=1,name="x10060").wtPort(x9992_x10060_s)
      val x10039 = CounterChain.copy("x10061_0", "x10039")
      val x9992 = SRAM(size=16,name="x9992",banking = Strided(1)).wtPort(x10060.readPort).rdPort(x9992_x9992_dsp0_bank0_s).rdAddr(x10039(0)).wtAddr(x10039(0))
    }
    val x9992_dsp1_bank0 = MemoryPipeline(name="x9992_dsp1_bank0",parent="x10062") { implicit CU => 
      val x10060 = ScalarFIFO(size=1,name="x10060").wtPort(x9992_x10060_s)
      val x10134 = CounterChain.copy("x10141", "x10134")
      val x10039 = CounterChain.copy("x10061_0", "x10039")
      val x9992 = SRAM(size=16,name="x9992",banking = Strided(1)).wtPort(x10060.readPort).rdPort(x9992_x9992_dsp1_bank0_s).rdAddr(x10134(0)).wtAddr(x10039(0))
    }
    val x9993_dsp0_bank0 = MemoryPipeline(name="x9993_dsp0_bank0",parent="x10131") { implicit CU => 
      val x10129 = ScalarFIFO(size=1,name="x10129").wtPort(x9993_x10129_s)
      val x10108 = CounterChain.copy("x10130_0", "x10108")
      val x9993 = SRAM(size=16,name="x9993",banking = Strided(1)).wtPort(x10129.readPort).rdPort(x9993_x9993_dsp0_bank0_s).rdAddr(x10108(0)).wtAddr(x10108(0))
    }
    val x9993_dsp1_bank0 = MemoryPipeline(name="x9993_dsp1_bank0",parent="x10131") { implicit CU => 
      val x10129 = ScalarFIFO(size=1,name="x10129").wtPort(x9993_x10129_s)
      val x10143 = CounterChain.copy("x10150", "x10143")
      val x10108 = CounterChain.copy("x10130_0", "x10108")
      val x9993 = SRAM(size=16,name="x9993",banking = Strided(1)).wtPort(x10129.readPort).rdPort(x9993_x9993_dsp1_bank0_s).rdAddr(x10143(0)).wtAddr(x10108(0))
    }
    val x10062 = MetaPipeline(name="x10062",parent=x10152) { implicit CU => 
      val ctr27 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x9995 = CounterChain(name = "x9995", ctr27).iter(8)
    }
    val x9996_dsp0_bank0 = MemoryPipeline(name="x9996_dsp0_bank0",parent="x10062") { implicit CU => 
      val x10023 = ScalarFIFO(size=1,name="x10023").wtPort(x9996_x10023_s)
      val x10039 = CounterChain.copy("x10061_0", "x10039")
      val x10014 = CounterChain.copy("x10024_0", "x10014")
      val x9996 = SRAM(size=16,name="x9996",banking = Strided(1)).wtPort(x10023.readPort).rdPort(x9996_x9996_dsp0_bank0_s).rdAddr(x10039(0)).wtAddr(x10014(0))
    }
    val x9997_dsp0_bank0 = MemoryPipeline(name="x9997_dsp0_bank0",parent="x10062") { implicit CU => 
      val x10035 = ScalarFIFO(size=1,name="x10035").wtPort(x9997_x10035_s)
      val x10026 = CounterChain.copy("x10036_0", "x10026")
      val x10039 = CounterChain.copy("x10061_0", "x10039")
      val x9997 = SRAM(size=16,name="x9997",banking = Strided(1)).wtPort(x10035.readPort).rdPort(x9997_x9997_dsp0_bank0_s).rdAddr(x10039(0)).wtAddr(x10026(0))
    }
    val x10024_0 = Pipeline(name="x10024_0",parent=x10062) { implicit CU => 
      val x9998 = ScalarBuffer(name="x9998").wtPort(x9918_x9918_dsp0_bank0_s)
      val x10019 = ScalarFIFO(size=1,name="x10019").wtPort(x9617_x9617_dsp0_bank0_s)
      val ctr28 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10014 = CounterChain(name = "x10014", ctr28).iter(1)
      Stage(operands=List(CU.load(x10019), CU.load(x9998)), op=FixMul, results=List(CU.scalarOut(x9996_x10023_s)))
    }
    val x10036_0 = Pipeline(name="x10036_0",parent=x10062) { implicit CU => 
      val x9999 = ScalarBuffer(name="x9999").wtPort(x9918_x9918_dsp0_bank0_s)
      val x10031 = ScalarFIFO(size=1,name="x10031").wtPort(x9617_x9617_dsp0_bank1_s)
      val ctr29 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10026 = CounterChain(name = "x10026", ctr29).iter(1)
      Stage(operands=List(CU.load(x10031), CU.load(x9999)), op=FixMul, results=List(CU.scalarOut(x9997_x10035_s)))
    }
    val x10061_0 = Pipeline(name="x10061_0",parent=x10062) { implicit CU => 
      val x10055 = CU.temp(None)
      val x10045 = ScalarFIFO(size=1,name="x10045").wtPort(x9997_x9997_dsp0_bank0_s)
      val x10047 = ScalarFIFO(size=1,name="x10047").wtPort(x9992_x9992_dsp0_bank0_s)
      val x10043 = ScalarFIFO(size=1,name="x10043").wtPort(x9996_x9996_dsp0_bank0_s)
      val ctr30 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10039 = CounterChain(name = "x10039", ctr30).iter(1)
      Stage(operands=List(CU.load(x10043), CU.load(x10045)), op=FixAdd, results=List(x10055))
      Stage(operands=List(x10055, CU.load(x10047)), op=FixAdd, results=List(CU.scalarOut(x9992_x10060_s)))
    }
    val x10131 = MetaPipeline(name="x10131",parent=x10152) { implicit CU => 
      val ctr31 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x10064 = CounterChain(name = "x10064", ctr31).iter(8)
    }
    val x10065_dsp0_bank0 = MemoryPipeline(name="x10065_dsp0_bank0",parent="x10131") { implicit CU => 
      val x10092 = ScalarFIFO(size=1,name="x10092").wtPort(x10065_x10092_s)
      val x10083 = CounterChain.copy("x10093_0", "x10083")
      val x10108 = CounterChain.copy("x10130_0", "x10108")
      val x10065 = SRAM(size=16,name="x10065",banking = Strided(1)).wtPort(x10092.readPort).rdPort(x10065_x10065_dsp0_bank0_s).rdAddr(x10108(0)).wtAddr(x10083(0))
    }
    val x10066_dsp0_bank0 = MemoryPipeline(name="x10066_dsp0_bank0",parent="x10131") { implicit CU => 
      val x10104 = ScalarFIFO(size=1,name="x10104").wtPort(x10066_x10104_s)
      val x10095 = CounterChain.copy("x10105_0", "x10095")
      val x10108 = CounterChain.copy("x10130_0", "x10108")
      val x10066 = SRAM(size=16,name="x10066",banking = Strided(1)).wtPort(x10104.readPort).rdPort(x10066_x10066_dsp0_bank0_s).rdAddr(x10108(0)).wtAddr(x10095(0))
    }
    val x10093_0 = Pipeline(name="x10093_0",parent=x10131) { implicit CU => 
      val x10067 = ScalarBuffer(name="x10067").wtPort(x9919_x9919_dsp0_bank0_s)
      val x10088 = ScalarFIFO(size=1,name="x10088").wtPort(x9617_x9617_dsp1_bank0_s)
      val ctr32 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10083 = CounterChain(name = "x10083", ctr32).iter(1)
      Stage(operands=List(CU.load(x10088), CU.load(x10067)), op=FixMul, results=List(CU.scalarOut(x10065_x10092_s)))
    }
    val x10105_0 = Pipeline(name="x10105_0",parent=x10131) { implicit CU => 
      val x10068 = ScalarBuffer(name="x10068").wtPort(x9919_x9919_dsp0_bank0_s)
      val x10100 = ScalarFIFO(size=1,name="x10100").wtPort(x9617_x9617_dsp1_bank1_s)
      val ctr33 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10095 = CounterChain(name = "x10095", ctr33).iter(1)
      Stage(operands=List(CU.load(x10100), CU.load(x10068)), op=FixMul, results=List(CU.scalarOut(x10066_x10104_s)))
    }
    val x10130_0 = Pipeline(name="x10130_0",parent=x10131) { implicit CU => 
      val x10124 = CU.temp(None)
      val x10114 = ScalarFIFO(size=1,name="x10114").wtPort(x10066_x10066_dsp0_bank0_s)
      val x10116 = ScalarFIFO(size=1,name="x10116").wtPort(x9993_x9993_dsp0_bank0_s)
      val x10112 = ScalarFIFO(size=1,name="x10112").wtPort(x10065_x10065_dsp0_bank0_s)
      val ctr34 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10108 = CounterChain(name = "x10108", ctr34).iter(1)
      Stage(operands=List(CU.load(x10112), CU.load(x10114)), op=FixAdd, results=List(x10124))
      Stage(operands=List(x10124, CU.load(x10116)), op=FixAdd, results=List(CU.scalarOut(x9993_x10129_s)))
    }
    val x10141 = Pipeline(name="x10141",parent=x10152) { implicit CU => 
      val ctr35 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10134 = CounterChain(name = "x10134", ctr35).iter(16)
    }
    val x10150 = Pipeline(name="x10150",parent=x10152) { implicit CU => 
      val ctr36 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10143 = CounterChain(name = "x10143", ctr36).iter(16)
    }
    val x10175_0 = Pipeline(name="x10175_0",parent=x10176) { implicit CU => 
      val x10169 = CU.temp(None)
      val x10161 = ScalarFIFO(size=1,name="x10161").wtPort(x9615_x9615_dsp0_bank0_s).wtPort(x9615_x9615_dsp0_bank1_s)
      val x10163 = ScalarFIFO(size=1,name="x10163").wtPort(x9608_x9608_dsp1_bank0_s)
      val x10159 = ScalarFIFO(size=1,name="x10159").wtPort(x9614_x9614_dsp0_bank0_s).wtPort(x9614_x9614_dsp0_bank1_s)
      val ctr37 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr38 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10156 = CounterChain(name = "x10156", ctr37, ctr38).iter(64)
      Stage(operands=List(CU.load(x10159), CU.load(x10161)), op=FixAdd, results=List(x10169))
      Stage(operands=List(x10169, CU.load(x10163)), op=FixAdd, results=List(CU.scalarOut(x9608_x10174_s)))
    }
    val x10741 = MetaPipeline(name="x10741",parent=x12011) { implicit CU => 
      val ctr39 = Counter(min=Const(0), max=Const(64), step=Const(16), par=2) // Counter
      val x10178 = CounterChain(name = "x10178", ctr39).iter(2)
    }
    val x10179_dsp0_bank0 = MemoryPipeline(name="x10179_dsp0_bank0",parent="x10741") { implicit CU => 
      val b12171 = CU.temp(None)
      val b12147 = CU.temp(None)
      val x10468 = ScalarFIFO(size=1,name="x10468").wtPort(x10320_x10320_dsp1_bank0_s)
      val x10721 = CounterChain.copy("x10740_0", "x10721")
      val x10462 = CounterChain.copy("x10469", "x10462")
      val x10245 = CounterChain.copy("x10480", "x10245")
      val x10179 = SRAM(size=1024,name="x10179",banking = Strided(1)).wtPort(x10468.readPort).rdPort(x10179_x10179_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x10245(0)), Const(16)), op=FixMul, results=List(b12147))
      WAStage(operands=List(b12147, CU.ctr(x10462(0))), op=FixAdd, results=List(x10179.writeAddr))
      RAStage(operands=List(CU.ctr(x10721(0)), Const(16)), op=FixMul, results=List(b12171))
      RAStage(operands=List(b12171, CU.ctr(x10721(1))), op=FixAdd, results=List(x10179.readAddr))
    }
    val x10179_dsp0_bank1 = MemoryPipeline(name="x10179_dsp0_bank1",parent="x10741") { implicit CU => 
      val b12149 = CU.temp(None)
      val b12171 = CU.temp(None)
      val x10477 = ScalarFIFO(size=1,name="x10477").wtPort(x10321_x10321_dsp1_bank0_s)
      val x10471 = CounterChain.copy("x10478", "x10471")
      val x10721 = CounterChain.copy("x10740_0", "x10721")
      val x10245 = CounterChain.copy("x10480", "x10245")
      val x10179 = SRAM(size=1024,name="x10179",banking = Strided(1)).wtPort(x10477.readPort).rdPort(x10179_x10179_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x10245(0)), Const(16)), op=FixMul, results=List(b12149))
      WAStage(operands=List(b12149, CU.ctr(x10471(0))), op=FixAdd, results=List(x10179.writeAddr))
      RAStage(operands=List(CU.ctr(x10721(0)), Const(16)), op=FixMul, results=List(b12171))
      RAStage(operands=List(b12171, CU.ctr(x10721(1))), op=FixAdd, results=List(x10179.readAddr))
    }
    val x10180_dsp0_bank0 = MemoryPipeline(name="x10180_dsp0_bank0",parent="x10741") { implicit CU => 
      val b12167 = CU.temp(None)
      val b12173 = CU.temp(None)
      val x10705 = ScalarFIFO(size=1,name="x10705").wtPort(x10557_x10557_dsp1_bank0_s)
      val x10482 = CounterChain.copy("x10717", "x10482")
      val x10699 = CounterChain.copy("x10706", "x10699")
      val x10721 = CounterChain.copy("x10740_0", "x10721")
      val x10180 = SRAM(size=1024,name="x10180",banking = Strided(1)).wtPort(x10705.readPort).rdPort(x10180_x10180_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x10482(0)), Const(16)), op=FixMul, results=List(b12167))
      WAStage(operands=List(b12167, CU.ctr(x10699(0))), op=FixAdd, results=List(x10180.writeAddr))
      RAStage(operands=List(CU.ctr(x10721(0)), Const(16)), op=FixMul, results=List(b12173))
      RAStage(operands=List(b12173, CU.ctr(x10721(1))), op=FixAdd, results=List(x10180.readAddr))
    }
    val x10180_dsp0_bank1 = MemoryPipeline(name="x10180_dsp0_bank1",parent="x10741") { implicit CU => 
      val b12169 = CU.temp(None)
      val b12173 = CU.temp(None)
      val x10714 = ScalarFIFO(size=1,name="x10714").wtPort(x10558_x10558_dsp1_bank0_s)
      val x10482 = CounterChain.copy("x10717", "x10482")
      val x10721 = CounterChain.copy("x10740_0", "x10721")
      val x10708 = CounterChain.copy("x10715", "x10708")
      val x10180 = SRAM(size=1024,name="x10180",banking = Strided(1)).wtPort(x10714.readPort).rdPort(x10180_x10180_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x10482(0)), Const(16)), op=FixMul, results=List(b12169))
      WAStage(operands=List(b12169, CU.ctr(x10708(0))), op=FixAdd, results=List(x10180.writeAddr))
      RAStage(operands=List(CU.ctr(x10721(0)), Const(16)), op=FixMul, results=List(b12173))
      RAStage(operands=List(b12173, CU.ctr(x10721(1))), op=FixAdd, results=List(x10180.readAddr))
    }
    val x10181_dsp0_bank0 = MemoryPipeline(name="x10181_dsp0_bank0",parent="x10741") { implicit CU => 
      val b12139 = CU.temp(None)
      val b12123 = CU.temp(None)
      val x10210 = ScalarFIFO(size=1,name="x10210").wtPort(x10186_x10202_data_s)
      val x10184 = CounterChain.copy("x10212", "x10184")
      val x10204 = CounterChain.copy("x10211", "x10204")
      val x10323 = CounterChain.copy("x10390", "x10323")
      val x10342 = CounterChain.copy("x10352_0", "x10342")
      val x10181 = SRAM(size=256,name="x10181",banking = Strided(1)).wtPort(x10210.readPort).rdPort(x10181_x10181_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x10184(0)), Const(16)), op=FixMul, results=List(b12123))
      WAStage(operands=List(b12123, CU.ctr(x10204(0))), op=FixAdd, results=List(x10181.writeAddr))
      RAStage(operands=List(CU.ctr(x10323(0)), Const(16)), op=FixMul, results=List(b12139))
      RAStage(operands=List(b12139, CU.ctr(x10342(0))), op=FixAdd, results=List(x10181.readAddr))
    }
    val x10181_dsp0_bank1 = MemoryPipeline(name="x10181_dsp0_bank1",parent="x10741") { implicit CU => 
      val b12123 = CU.temp(None)
      val b12141 = CU.temp(None)
      val x10210 = ScalarFIFO(size=1,name="x10210").wtPort(x10186_x10202_data_s)
      val x10184 = CounterChain.copy("x10212", "x10184")
      val x10204 = CounterChain.copy("x10211", "x10204")
      val x10323 = CounterChain.copy("x10390", "x10323")
      val x10354 = CounterChain.copy("x10364_0", "x10354")
      val x10181 = SRAM(size=256,name="x10181",banking = Strided(1)).wtPort(x10210.readPort).rdPort(x10181_x10181_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x10184(0)), Const(16)), op=FixMul, results=List(b12123))
      WAStage(operands=List(b12123, CU.ctr(x10204(0))), op=FixAdd, results=List(x10181.writeAddr))
      RAStage(operands=List(CU.ctr(x10323(0)), Const(16)), op=FixMul, results=List(b12141))
      RAStage(operands=List(b12141, CU.ctr(x10354(0))), op=FixAdd, results=List(x10181.readAddr))
    }
    val x10181_dsp1_bank0 = MemoryPipeline(name="x10181_dsp1_bank0",parent="x10741") { implicit CU => 
      val b12123 = CU.temp(None)
      val b12143 = CU.temp(None)
      val x10210 = ScalarFIFO(size=1,name="x10210").wtPort(x10186_x10202_data_s)
      val x10184 = CounterChain.copy("x10212", "x10184")
      val x10411 = CounterChain.copy("x10421_0", "x10411")
      val x10204 = CounterChain.copy("x10211", "x10204")
      val x10392 = CounterChain.copy("x10459", "x10392")
      val x10181 = SRAM(size=256,name="x10181",banking = Strided(1)).wtPort(x10210.readPort).rdPort(x10181_x10181_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x10184(0)), Const(16)), op=FixMul, results=List(b12123))
      WAStage(operands=List(b12123, CU.ctr(x10204(0))), op=FixAdd, results=List(x10181.writeAddr))
      RAStage(operands=List(CU.ctr(x10392(0)), Const(16)), op=FixMul, results=List(b12143))
      RAStage(operands=List(b12143, CU.ctr(x10411(0))), op=FixAdd, results=List(x10181.readAddr))
    }
    val x10181_dsp1_bank1 = MemoryPipeline(name="x10181_dsp1_bank1",parent="x10741") { implicit CU => 
      val b12123 = CU.temp(None)
      val b12145 = CU.temp(None)
      val x10210 = ScalarFIFO(size=1,name="x10210").wtPort(x10186_x10202_data_s)
      val x10184 = CounterChain.copy("x10212", "x10184")
      val x10204 = CounterChain.copy("x10211", "x10204")
      val x10423 = CounterChain.copy("x10433_0", "x10423")
      val x10392 = CounterChain.copy("x10459", "x10392")
      val x10181 = SRAM(size=256,name="x10181",banking = Strided(1)).wtPort(x10210.readPort).rdPort(x10181_x10181_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x10184(0)), Const(16)), op=FixMul, results=List(b12123))
      WAStage(operands=List(b12123, CU.ctr(x10204(0))), op=FixAdd, results=List(x10181.writeAddr))
      RAStage(operands=List(CU.ctr(x10392(0)), Const(16)), op=FixMul, results=List(b12145))
      RAStage(operands=List(b12145, CU.ctr(x10423(0))), op=FixAdd, results=List(x10181.readAddr))
    }
    val x10182_dsp0_bank0 = MemoryPipeline(name="x10182_dsp0_bank0",parent="x10741") { implicit CU => 
      val b12159 = CU.temp(None)
      val b12129 = CU.temp(None)
      val x10240 = ScalarFIFO(size=1,name="x10240").wtPort(x10216_x10232_data_s)
      val x10560 = CounterChain.copy("x10627", "x10560")
      val x10234 = CounterChain.copy("x10241", "x10234")
      val x10579 = CounterChain.copy("x10589_0", "x10579")
      val x10214 = CounterChain.copy("x10242", "x10214")
      val x10182 = SRAM(size=256,name="x10182",banking = Strided(1)).wtPort(x10240.readPort).rdPort(x10182_x10182_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x10214(0)), Const(16)), op=FixMul, results=List(b12129))
      WAStage(operands=List(b12129, CU.ctr(x10234(0))), op=FixAdd, results=List(x10182.writeAddr))
      RAStage(operands=List(CU.ctr(x10560(0)), Const(16)), op=FixMul, results=List(b12159))
      RAStage(operands=List(b12159, CU.ctr(x10579(0))), op=FixAdd, results=List(x10182.readAddr))
    }
    val x10182_dsp0_bank1 = MemoryPipeline(name="x10182_dsp0_bank1",parent="x10741") { implicit CU => 
      val b12161 = CU.temp(None)
      val b12129 = CU.temp(None)
      val x10240 = ScalarFIFO(size=1,name="x10240").wtPort(x10216_x10232_data_s)
      val x10560 = CounterChain.copy("x10627", "x10560")
      val x10234 = CounterChain.copy("x10241", "x10234")
      val x10214 = CounterChain.copy("x10242", "x10214")
      val x10591 = CounterChain.copy("x10601_0", "x10591")
      val x10182 = SRAM(size=256,name="x10182",banking = Strided(1)).wtPort(x10240.readPort).rdPort(x10182_x10182_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x10214(0)), Const(16)), op=FixMul, results=List(b12129))
      WAStage(operands=List(b12129, CU.ctr(x10234(0))), op=FixAdd, results=List(x10182.writeAddr))
      RAStage(operands=List(CU.ctr(x10560(0)), Const(16)), op=FixMul, results=List(b12161))
      RAStage(operands=List(b12161, CU.ctr(x10591(0))), op=FixAdd, results=List(x10182.readAddr))
    }
    val x10182_dsp1_bank0 = MemoryPipeline(name="x10182_dsp1_bank0",parent="x10741") { implicit CU => 
      val b12163 = CU.temp(None)
      val b12129 = CU.temp(None)
      val x10240 = ScalarFIFO(size=1,name="x10240").wtPort(x10216_x10232_data_s)
      val x10234 = CounterChain.copy("x10241", "x10234")
      val x10648 = CounterChain.copy("x10658_0", "x10648")
      val x10214 = CounterChain.copy("x10242", "x10214")
      val x10629 = CounterChain.copy("x10696", "x10629")
      val x10182 = SRAM(size=256,name="x10182",banking = Strided(1)).wtPort(x10240.readPort).rdPort(x10182_x10182_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x10214(0)), Const(16)), op=FixMul, results=List(b12129))
      WAStage(operands=List(b12129, CU.ctr(x10234(0))), op=FixAdd, results=List(x10182.writeAddr))
      RAStage(operands=List(CU.ctr(x10629(0)), Const(16)), op=FixMul, results=List(b12163))
      RAStage(operands=List(b12163, CU.ctr(x10648(0))), op=FixAdd, results=List(x10182.readAddr))
    }
    val x10182_dsp1_bank1 = MemoryPipeline(name="x10182_dsp1_bank1",parent="x10741") { implicit CU => 
      val b12165 = CU.temp(None)
      val b12129 = CU.temp(None)
      val x10240 = ScalarFIFO(size=1,name="x10240").wtPort(x10216_x10232_data_s)
      val x10660 = CounterChain.copy("x10670_0", "x10660")
      val x10234 = CounterChain.copy("x10241", "x10234")
      val x10214 = CounterChain.copy("x10242", "x10214")
      val x10629 = CounterChain.copy("x10696", "x10629")
      val x10182 = SRAM(size=256,name="x10182",banking = Strided(1)).wtPort(x10240.readPort).rdPort(x10182_x10182_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x10214(0)), Const(16)), op=FixMul, results=List(b12129))
      WAStage(operands=List(b12129, CU.ctr(x10234(0))), op=FixAdd, results=List(x10182.writeAddr))
      RAStage(operands=List(CU.ctr(x10629(0)), Const(16)), op=FixMul, results=List(b12165))
      RAStage(operands=List(b12165, CU.ctr(x10660(0))), op=FixAdd, results=List(x10182.readAddr))
    }
    val x10212 = StreamController(name="x10212",parent=x10741) { implicit CU => 
      val ctr40 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10184 = CounterChain(name = "x10184", ctr40).iter(16)
    }
    val x10201_0 = Pipeline(name="x10201_0",parent=x10212) { implicit CU => 
      val x10189 = CU.temp(None)
      val x10191 = CU.temp(None)
      val x10192 = CU.temp(None)
      val x10187 = CU.temp(None)
      val x10194 = ScalarBuffer(name="x10194").wtPort(b_dram_da)
      val x10178 = CounterChain.copy("x10741", "x10178")
      val x10184 = CounterChain.copy("x10212", "x10184")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x10201_unit = CounterChain(name = "x10201_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x10178(0)), CU.ctr(x10184(0))), op=FixAdd, results=List(x10187))
      Stage(operands=List(x10187, Const(6)), op=FixSla, results=List(x10189))
      Stage(operands=List(x10189, CU.ctr(x9607(0))), op=FixAdd, results=List(x10191))
      Stage(operands=List(x10191, Const(3)), op=FixSla, results=List(x10192))
      Stage(operands=List(x10192, CU.load(x10194)), op=FixAdd, results=List(CU.scalarOut(x10185_b12119_x10200_b12121_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x10185_b12120_x10200_b12122_s)))
    }
    val x10202 = MemoryController(name="x10202",parent=x10212,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x10185_b12120 = ScalarFIFO(size=1,name="size").wtPort(x10185_b12120_x10200_b12122_s)
      val x10185_b12119 = ScalarFIFO(size=1,name="offset").wtPort(x10185_b12119_x10200_b12121_s)
      CU.newSout("data", x10186_x10202_data_s)
    }
    val x10211 = Pipeline(name="x10211",parent=x10212) { implicit CU => 
      val ctr41 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10204 = CounterChain(name = "x10204", ctr41).iter(1)
    }
    val x10242 = StreamController(name="x10242",parent=x10741) { implicit CU => 
      val ctr42 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10214 = CounterChain(name = "x10214", ctr42).iter(16)
    }
    val x10231_0 = Pipeline(name="x10231_0",parent=x10242) { implicit CU => 
      val x10217 = CU.temp(None)
      val x10221 = CU.temp(None)
      val x10222 = CU.temp(None)
      val x10219 = CU.temp(None)
      val x10224 = ScalarBuffer(name="x10224").wtPort(b_dram_da)
      val x10178 = CounterChain.copy("x10741", "x10178")
      val x10214 = CounterChain.copy("x10242", "x10214")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x10231_unit = CounterChain(name = "x10231_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x10178(0)), CU.ctr(x10214(0))), op=FixAdd, results=List(x10217))
      Stage(operands=List(x10217, Const(6)), op=FixSla, results=List(x10219))
      Stage(operands=List(x10219, CU.ctr(x9607(0))), op=FixAdd, results=List(x10221))
      Stage(operands=List(x10221, Const(3)), op=FixSla, results=List(x10222))
      Stage(operands=List(x10222, CU.load(x10224)), op=FixAdd, results=List(CU.scalarOut(x10215_b12125_x10230_b12127_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x10215_b12126_x10230_b12128_s)))
    }
    val x10232 = MemoryController(name="x10232",parent=x10242,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x10215_b12126 = ScalarFIFO(size=1,name="size").wtPort(x10215_b12126_x10230_b12128_s)
      val x10215_b12125 = ScalarFIFO(size=1,name="offset").wtPort(x10215_b12125_x10230_b12127_s)
      CU.newSout("data", x10216_x10232_data_s)
    }
    val x10241 = Pipeline(name="x10241",parent=x10242) { implicit CU => 
      val ctr43 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10234 = CounterChain(name = "x10234", ctr43).iter(1)
    }
    val x10480 = MetaPipeline(name="x10480",parent=x10741) { implicit CU => 
      val ctr44 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x10245 = CounterChain(name = "x10245", ctr44).iter(32)
    }
    val x10246_dsp0_bank0 = MemoryPipeline(name="x10246_dsp0_bank0",parent="x10480") { implicit CU => 
      val x10284 = ScalarFIFO(size=1,name="x10284").wtPort(x10258_x10275_data_s)
      val x10277 = CounterChain.copy("x10285", "x10277")
      val x10323 = CounterChain.copy("x10390", "x10323")
      val x10246 = SRAM(size=16,name="x10246",banking = Strided(1)).wtPort(x10284.readPort).rdPort(x10246_x10246_dsp0_bank0_s).rdAddr(x10323(0)).rdAddr(x10323(0)).wtAddr(x10277(0))
    }
    val x10247_dsp0_bank0 = MemoryPipeline(name="x10247_dsp0_bank0",parent="x10480") { implicit CU => 
      val x10316 = ScalarFIFO(size=1,name="x10316").wtPort(x10290_x10307_data_s)
      val x10309 = CounterChain.copy("x10317", "x10309")
      val x10392 = CounterChain.copy("x10459", "x10392")
      val x10247 = SRAM(size=16,name="x10247",banking = Strided(1)).wtPort(x10316.readPort).rdPort(x10247_x10247_dsp0_bank0_s).rdAddr(x10392(0)).rdAddr(x10392(0)).wtAddr(x10309(0))
    }
    val x10286 = StreamController(name="x10286",parent=x10480) { implicit CU => 
      val ctr45 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x10256 = CounterChain(name = "x10256", ctr45).iter(1)
    }
    val x10274_0 = Pipeline(name="x10274_0",parent=x10286) { implicit CU => 
      val x10263 = CU.temp(None)
      val x10259 = CU.temp(None)
      val x10264 = CU.temp(None)
      val x10261 = CU.temp(None)
      val x10266 = ScalarBuffer(name="x10266").wtPort(a_dram_da)
      val x10256 = CounterChain.copy("x10286", "x10256")
      val x10178 = CounterChain.copy("x10741", "x10178")
      val x10274_unit = CounterChain(name = "x10274_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x10245 = CounterChain.copy("x10480", "x10245")
      Stage(operands=List(CU.ctr(x10245(0)), CU.ctr(x10256(0))), op=FixAdd, results=List(x10259))
      Stage(operands=List(x10259, Const(6)), op=FixSla, results=List(x10261))
      Stage(operands=List(x10261, CU.ctr(x10178(0))), op=FixAdd, results=List(x10263))
      Stage(operands=List(x10263, Const(3)), op=FixSla, results=List(x10264))
      Stage(operands=List(x10264, CU.load(x10266)), op=FixAdd, results=List(CU.scalarOut(x10257_b12131_x10273_b12133_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x10257_b12132_x10273_b12134_s)))
    }
    val x10275 = MemoryController(name="x10275",parent=x10286,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x10257_b12132 = ScalarFIFO(size=1,name="size").wtPort(x10257_b12132_x10273_b12134_s)
      val x10257_b12131 = ScalarFIFO(size=1,name="offset").wtPort(x10257_b12131_x10273_b12133_s)
      CU.newSout("data", x10258_x10275_data_s)
    }
    val x10285 = Pipeline(name="x10285",parent=x10286) { implicit CU => 
      val ctr46 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10277 = CounterChain(name = "x10277", ctr46).iter(16)
    }
    val x10318 = StreamController(name="x10318",parent=x10480) { implicit CU => 
      val ctr47 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x10288 = CounterChain(name = "x10288", ctr47).iter(1)
    }
    val x10306_0 = Pipeline(name="x10306_0",parent=x10318) { implicit CU => 
      val x10291 = CU.temp(None)
      val x10296 = CU.temp(None)
      val x10293 = CU.temp(None)
      val x10295 = CU.temp(None)
      val x10298 = ScalarBuffer(name="x10298").wtPort(a_dram_da)
      val x10288 = CounterChain.copy("x10318", "x10288")
      val x10306_unit = CounterChain(name = "x10306_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x10178 = CounterChain.copy("x10741", "x10178")
      val x10245 = CounterChain.copy("x10480", "x10245")
      Stage(operands=List(CU.ctr(x10245(0)), CU.ctr(x10288(0))), op=FixAdd, results=List(x10291))
      Stage(operands=List(x10291, Const(6)), op=FixSla, results=List(x10293))
      Stage(operands=List(x10293, CU.ctr(x10178(0))), op=FixAdd, results=List(x10295))
      Stage(operands=List(x10295, Const(3)), op=FixSla, results=List(x10296))
      Stage(operands=List(x10296, CU.load(x10298)), op=FixAdd, results=List(CU.scalarOut(x10289_b12135_x10305_b12137_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x10289_b12136_x10305_b12138_s)))
    }
    val x10307 = MemoryController(name="x10307",parent=x10318,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x10289_b12135 = ScalarFIFO(size=1,name="offset").wtPort(x10289_b12135_x10305_b12137_s)
      val x10289_b12136 = ScalarFIFO(size=1,name="size").wtPort(x10289_b12136_x10305_b12138_s)
      CU.newSout("data", x10290_x10307_data_s)
    }
    val x10317 = Pipeline(name="x10317",parent=x10318) { implicit CU => 
      val ctr48 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10309 = CounterChain(name = "x10309", ctr48).iter(16)
    }
    val x10320_dsp0_bank0 = MemoryPipeline(name="x10320_dsp0_bank0",parent="x10390") { implicit CU => 
      val x10388 = ScalarFIFO(size=1,name="x10388").wtPort(x10320_x10388_s)
      val x10367 = CounterChain.copy("x10389_0", "x10367")
      val x10320 = SRAM(size=16,name="x10320",banking = Strided(1)).wtPort(x10388.readPort).rdPort(x10320_x10320_dsp0_bank0_s).rdAddr(x10367(0)).wtAddr(x10367(0))
    }
    val x10320_dsp1_bank0 = MemoryPipeline(name="x10320_dsp1_bank0",parent="x10390") { implicit CU => 
      val x10388 = ScalarFIFO(size=1,name="x10388").wtPort(x10320_x10388_s)
      val x10367 = CounterChain.copy("x10389_0", "x10367")
      val x10462 = CounterChain.copy("x10469", "x10462")
      val x10320 = SRAM(size=16,name="x10320",banking = Strided(1)).wtPort(x10388.readPort).rdPort(x10320_x10320_dsp1_bank0_s).rdAddr(x10462(0)).wtAddr(x10367(0))
    }
    val x10321_dsp0_bank0 = MemoryPipeline(name="x10321_dsp0_bank0",parent="x10459") { implicit CU => 
      val x10457 = ScalarFIFO(size=1,name="x10457").wtPort(x10321_x10457_s)
      val x10436 = CounterChain.copy("x10458_0", "x10436")
      val x10321 = SRAM(size=16,name="x10321",banking = Strided(1)).wtPort(x10457.readPort).rdPort(x10321_x10321_dsp0_bank0_s).rdAddr(x10436(0)).wtAddr(x10436(0))
    }
    val x10321_dsp1_bank0 = MemoryPipeline(name="x10321_dsp1_bank0",parent="x10459") { implicit CU => 
      val x10457 = ScalarFIFO(size=1,name="x10457").wtPort(x10321_x10457_s)
      val x10436 = CounterChain.copy("x10458_0", "x10436")
      val x10471 = CounterChain.copy("x10478", "x10471")
      val x10321 = SRAM(size=16,name="x10321",banking = Strided(1)).wtPort(x10457.readPort).rdPort(x10321_x10321_dsp1_bank0_s).rdAddr(x10471(0)).wtAddr(x10436(0))
    }
    val x10390 = MetaPipeline(name="x10390",parent=x10480) { implicit CU => 
      val ctr49 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x10323 = CounterChain(name = "x10323", ctr49).iter(8)
    }
    val x10324_dsp0_bank0 = MemoryPipeline(name="x10324_dsp0_bank0",parent="x10390") { implicit CU => 
      val x10351 = ScalarFIFO(size=1,name="x10351").wtPort(x10324_x10351_s)
      val x10367 = CounterChain.copy("x10389_0", "x10367")
      val x10342 = CounterChain.copy("x10352_0", "x10342")
      val x10324 = SRAM(size=16,name="x10324",banking = Strided(1)).wtPort(x10351.readPort).rdPort(x10324_x10324_dsp0_bank0_s).rdAddr(x10367(0)).wtAddr(x10342(0))
    }
    val x10325_dsp0_bank0 = MemoryPipeline(name="x10325_dsp0_bank0",parent="x10390") { implicit CU => 
      val x10363 = ScalarFIFO(size=1,name="x10363").wtPort(x10325_x10363_s)
      val x10367 = CounterChain.copy("x10389_0", "x10367")
      val x10354 = CounterChain.copy("x10364_0", "x10354")
      val x10325 = SRAM(size=16,name="x10325",banking = Strided(1)).wtPort(x10363.readPort).rdPort(x10325_x10325_dsp0_bank0_s).rdAddr(x10367(0)).wtAddr(x10354(0))
    }
    val x10352_0 = Pipeline(name="x10352_0",parent=x10390) { implicit CU => 
      val x10347 = ScalarFIFO(size=1,name="x10347").wtPort(x10181_x10181_dsp0_bank0_s)
      val x10326 = ScalarBuffer(name="x10326").wtPort(x10246_x10246_dsp0_bank0_s)
      val ctr50 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10342 = CounterChain(name = "x10342", ctr50).iter(1)
      Stage(operands=List(CU.load(x10347), CU.load(x10326)), op=FixMul, results=List(CU.scalarOut(x10324_x10351_s)))
    }
    val x10364_0 = Pipeline(name="x10364_0",parent=x10390) { implicit CU => 
      val x10359 = ScalarFIFO(size=1,name="x10359").wtPort(x10181_x10181_dsp0_bank1_s)
      val x10327 = ScalarBuffer(name="x10327").wtPort(x10246_x10246_dsp0_bank0_s)
      val ctr51 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10354 = CounterChain(name = "x10354", ctr51).iter(1)
      Stage(operands=List(CU.load(x10359), CU.load(x10327)), op=FixMul, results=List(CU.scalarOut(x10325_x10363_s)))
    }
    val x10389_0 = Pipeline(name="x10389_0",parent=x10390) { implicit CU => 
      val x10383 = CU.temp(None)
      val x10373 = ScalarFIFO(size=1,name="x10373").wtPort(x10325_x10325_dsp0_bank0_s)
      val x10375 = ScalarFIFO(size=1,name="x10375").wtPort(x10320_x10320_dsp0_bank0_s)
      val x10371 = ScalarFIFO(size=1,name="x10371").wtPort(x10324_x10324_dsp0_bank0_s)
      val ctr52 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10367 = CounterChain(name = "x10367", ctr52).iter(1)
      Stage(operands=List(CU.load(x10371), CU.load(x10373)), op=FixAdd, results=List(x10383))
      Stage(operands=List(x10383, CU.load(x10375)), op=FixAdd, results=List(CU.scalarOut(x10320_x10388_s)))
    }
    val x10459 = MetaPipeline(name="x10459",parent=x10480) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x10392 = CounterChain(name = "x10392", ctr53).iter(8)
    }
    val x10393_dsp0_bank0 = MemoryPipeline(name="x10393_dsp0_bank0",parent="x10459") { implicit CU => 
      val x10420 = ScalarFIFO(size=1,name="x10420").wtPort(x10393_x10420_s)
      val x10436 = CounterChain.copy("x10458_0", "x10436")
      val x10411 = CounterChain.copy("x10421_0", "x10411")
      val x10393 = SRAM(size=16,name="x10393",banking = Strided(1)).wtPort(x10420.readPort).rdPort(x10393_x10393_dsp0_bank0_s).rdAddr(x10436(0)).wtAddr(x10411(0))
    }
    val x10394_dsp0_bank0 = MemoryPipeline(name="x10394_dsp0_bank0",parent="x10459") { implicit CU => 
      val x10432 = ScalarFIFO(size=1,name="x10432").wtPort(x10394_x10432_s)
      val x10436 = CounterChain.copy("x10458_0", "x10436")
      val x10423 = CounterChain.copy("x10433_0", "x10423")
      val x10394 = SRAM(size=16,name="x10394",banking = Strided(1)).wtPort(x10432.readPort).rdPort(x10394_x10394_dsp0_bank0_s).rdAddr(x10436(0)).wtAddr(x10423(0))
    }
    val x10421_0 = Pipeline(name="x10421_0",parent=x10459) { implicit CU => 
      val x10395 = ScalarBuffer(name="x10395").wtPort(x10247_x10247_dsp0_bank0_s)
      val x10416 = ScalarFIFO(size=1,name="x10416").wtPort(x10181_x10181_dsp1_bank0_s)
      val ctr54 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10411 = CounterChain(name = "x10411", ctr54).iter(1)
      Stage(operands=List(CU.load(x10416), CU.load(x10395)), op=FixMul, results=List(CU.scalarOut(x10393_x10420_s)))
    }
    val x10433_0 = Pipeline(name="x10433_0",parent=x10459) { implicit CU => 
      val x10428 = ScalarFIFO(size=1,name="x10428").wtPort(x10181_x10181_dsp1_bank1_s)
      val x10396 = ScalarBuffer(name="x10396").wtPort(x10247_x10247_dsp0_bank0_s)
      val ctr55 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10423 = CounterChain(name = "x10423", ctr55).iter(1)
      Stage(operands=List(CU.load(x10428), CU.load(x10396)), op=FixMul, results=List(CU.scalarOut(x10394_x10432_s)))
    }
    val x10458_0 = Pipeline(name="x10458_0",parent=x10459) { implicit CU => 
      val x10452 = CU.temp(None)
      val x10442 = ScalarFIFO(size=1,name="x10442").wtPort(x10394_x10394_dsp0_bank0_s)
      val x10444 = ScalarFIFO(size=1,name="x10444").wtPort(x10321_x10321_dsp0_bank0_s)
      val x10440 = ScalarFIFO(size=1,name="x10440").wtPort(x10393_x10393_dsp0_bank0_s)
      val ctr56 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10436 = CounterChain(name = "x10436", ctr56).iter(1)
      Stage(operands=List(CU.load(x10440), CU.load(x10442)), op=FixAdd, results=List(x10452))
      Stage(operands=List(x10452, CU.load(x10444)), op=FixAdd, results=List(CU.scalarOut(x10321_x10457_s)))
    }
    val x10469 = Pipeline(name="x10469",parent=x10480) { implicit CU => 
      val ctr57 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10462 = CounterChain(name = "x10462", ctr57).iter(16)
    }
    val x10478 = Pipeline(name="x10478",parent=x10480) { implicit CU => 
      val ctr58 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10471 = CounterChain(name = "x10471", ctr58).iter(16)
    }
    val x10717 = MetaPipeline(name="x10717",parent=x10741) { implicit CU => 
      val ctr59 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x10482 = CounterChain(name = "x10482", ctr59).iter(32)
    }
    val x10483_dsp0_bank0 = MemoryPipeline(name="x10483_dsp0_bank0",parent="x10717") { implicit CU => 
      val x10521 = ScalarFIFO(size=1,name="x10521").wtPort(x10495_x10512_data_s)
      val x10560 = CounterChain.copy("x10627", "x10560")
      val x10514 = CounterChain.copy("x10522", "x10514")
      val x10483 = SRAM(size=16,name="x10483",banking = Strided(1)).wtPort(x10521.readPort).rdPort(x10483_x10483_dsp0_bank0_s).rdAddr(x10560(0)).rdAddr(x10560(0)).wtAddr(x10514(0))
    }
    val x10484_dsp0_bank0 = MemoryPipeline(name="x10484_dsp0_bank0",parent="x10717") { implicit CU => 
      val x10553 = ScalarFIFO(size=1,name="x10553").wtPort(x10527_x10544_data_s)
      val x10546 = CounterChain.copy("x10554", "x10546")
      val x10629 = CounterChain.copy("x10696", "x10629")
      val x10484 = SRAM(size=16,name="x10484",banking = Strided(1)).wtPort(x10553.readPort).rdPort(x10484_x10484_dsp0_bank0_s).rdAddr(x10629(0)).rdAddr(x10629(0)).wtAddr(x10546(0))
    }
    val x10523 = StreamController(name="x10523",parent=x10717) { implicit CU => 
      val ctr60 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x10493 = CounterChain(name = "x10493", ctr60).iter(1)
    }
    val x10511_0 = Pipeline(name="x10511_0",parent=x10523) { implicit CU => 
      val x10498 = CU.temp(None)
      val x10500 = CU.temp(None)
      val x10496 = CU.temp(None)
      val x10501 = CU.temp(None)
      val x10503 = ScalarBuffer(name="x10503").wtPort(a_dram_da)
      val x10511_unit = CounterChain(name = "x10511_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x10482 = CounterChain.copy("x10717", "x10482")
      val x10178 = CounterChain.copy("x10741", "x10178")
      val x10493 = CounterChain.copy("x10523", "x10493")
      Stage(operands=List(CU.ctr(x10482(0)), CU.ctr(x10493(0))), op=FixAdd, results=List(x10496))
      Stage(operands=List(x10496, Const(6)), op=FixSla, results=List(x10498))
      Stage(operands=List(x10498, CU.ctr(x10178(0))), op=FixAdd, results=List(x10500))
      Stage(operands=List(x10500, Const(3)), op=FixSla, results=List(x10501))
      Stage(operands=List(x10501, CU.load(x10503)), op=FixAdd, results=List(CU.scalarOut(x10494_b12151_x10510_b12153_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x10494_b12152_x10510_b12154_s)))
    }
    val x10512 = MemoryController(name="x10512",parent=x10523,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x10494_b12152 = ScalarFIFO(size=1,name="size").wtPort(x10494_b12152_x10510_b12154_s)
      val x10494_b12151 = ScalarFIFO(size=1,name="offset").wtPort(x10494_b12151_x10510_b12153_s)
      CU.newSout("data", x10495_x10512_data_s)
    }
    val x10522 = Pipeline(name="x10522",parent=x10523) { implicit CU => 
      val ctr61 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10514 = CounterChain(name = "x10514", ctr61).iter(16)
    }
    val x10555 = StreamController(name="x10555",parent=x10717) { implicit CU => 
      val ctr62 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x10525 = CounterChain(name = "x10525", ctr62).iter(1)
    }
    val x10543_0 = Pipeline(name="x10543_0",parent=x10555) { implicit CU => 
      val x10528 = CU.temp(None)
      val x10532 = CU.temp(None)
      val x10533 = CU.temp(None)
      val x10530 = CU.temp(None)
      val x10535 = ScalarBuffer(name="x10535").wtPort(a_dram_da)
      val x10525 = CounterChain.copy("x10555", "x10525")
      val x10482 = CounterChain.copy("x10717", "x10482")
      val x10543_unit = CounterChain(name = "x10543_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x10178 = CounterChain.copy("x10741", "x10178")
      Stage(operands=List(CU.ctr(x10482(0)), CU.ctr(x10525(0))), op=FixAdd, results=List(x10528))
      Stage(operands=List(x10528, Const(6)), op=FixSla, results=List(x10530))
      Stage(operands=List(x10530, CU.ctr(x10178(0))), op=FixAdd, results=List(x10532))
      Stage(operands=List(x10532, Const(3)), op=FixSla, results=List(x10533))
      Stage(operands=List(x10533, CU.load(x10535)), op=FixAdd, results=List(CU.scalarOut(x10526_b12155_x10542_b12157_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x10526_b12156_x10542_b12158_s)))
    }
    val x10544 = MemoryController(name="x10544",parent=x10555,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x10526_b12156 = ScalarFIFO(size=1,name="size").wtPort(x10526_b12156_x10542_b12158_s)
      val x10526_b12155 = ScalarFIFO(size=1,name="offset").wtPort(x10526_b12155_x10542_b12157_s)
      CU.newSout("data", x10527_x10544_data_s)
    }
    val x10554 = Pipeline(name="x10554",parent=x10555) { implicit CU => 
      val ctr63 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10546 = CounterChain(name = "x10546", ctr63).iter(16)
    }
    val x10557_dsp0_bank0 = MemoryPipeline(name="x10557_dsp0_bank0",parent="x10627") { implicit CU => 
      val x10625 = ScalarFIFO(size=1,name="x10625").wtPort(x10557_x10625_s)
      val x10604 = CounterChain.copy("x10626_0", "x10604")
      val x10557 = SRAM(size=16,name="x10557",banking = Strided(1)).wtPort(x10625.readPort).rdPort(x10557_x10557_dsp0_bank0_s).rdAddr(x10604(0)).wtAddr(x10604(0))
    }
    val x10557_dsp1_bank0 = MemoryPipeline(name="x10557_dsp1_bank0",parent="x10627") { implicit CU => 
      val x10625 = ScalarFIFO(size=1,name="x10625").wtPort(x10557_x10625_s)
      val x10604 = CounterChain.copy("x10626_0", "x10604")
      val x10699 = CounterChain.copy("x10706", "x10699")
      val x10557 = SRAM(size=16,name="x10557",banking = Strided(1)).wtPort(x10625.readPort).rdPort(x10557_x10557_dsp1_bank0_s).rdAddr(x10699(0)).wtAddr(x10604(0))
    }
    val x10558_dsp0_bank0 = MemoryPipeline(name="x10558_dsp0_bank0",parent="x10696") { implicit CU => 
      val x10694 = ScalarFIFO(size=1,name="x10694").wtPort(x10558_x10694_s)
      val x10673 = CounterChain.copy("x10695_0", "x10673")
      val x10558 = SRAM(size=16,name="x10558",banking = Strided(1)).wtPort(x10694.readPort).rdPort(x10558_x10558_dsp0_bank0_s).rdAddr(x10673(0)).wtAddr(x10673(0))
    }
    val x10558_dsp1_bank0 = MemoryPipeline(name="x10558_dsp1_bank0",parent="x10696") { implicit CU => 
      val x10694 = ScalarFIFO(size=1,name="x10694").wtPort(x10558_x10694_s)
      val x10673 = CounterChain.copy("x10695_0", "x10673")
      val x10708 = CounterChain.copy("x10715", "x10708")
      val x10558 = SRAM(size=16,name="x10558",banking = Strided(1)).wtPort(x10694.readPort).rdPort(x10558_x10558_dsp1_bank0_s).rdAddr(x10708(0)).wtAddr(x10673(0))
    }
    val x10627 = MetaPipeline(name="x10627",parent=x10717) { implicit CU => 
      val ctr64 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x10560 = CounterChain(name = "x10560", ctr64).iter(8)
    }
    val x10561_dsp0_bank0 = MemoryPipeline(name="x10561_dsp0_bank0",parent="x10627") { implicit CU => 
      val x10588 = ScalarFIFO(size=1,name="x10588").wtPort(x10561_x10588_s)
      val x10579 = CounterChain.copy("x10589_0", "x10579")
      val x10604 = CounterChain.copy("x10626_0", "x10604")
      val x10561 = SRAM(size=16,name="x10561",banking = Strided(1)).wtPort(x10588.readPort).rdPort(x10561_x10561_dsp0_bank0_s).rdAddr(x10604(0)).wtAddr(x10579(0))
    }
    val x10562_dsp0_bank0 = MemoryPipeline(name="x10562_dsp0_bank0",parent="x10627") { implicit CU => 
      val x10600 = ScalarFIFO(size=1,name="x10600").wtPort(x10562_x10600_s)
      val x10604 = CounterChain.copy("x10626_0", "x10604")
      val x10591 = CounterChain.copy("x10601_0", "x10591")
      val x10562 = SRAM(size=16,name="x10562",banking = Strided(1)).wtPort(x10600.readPort).rdPort(x10562_x10562_dsp0_bank0_s).rdAddr(x10604(0)).wtAddr(x10591(0))
    }
    val x10589_0 = Pipeline(name="x10589_0",parent=x10627) { implicit CU => 
      val x10584 = ScalarFIFO(size=1,name="x10584").wtPort(x10182_x10182_dsp0_bank0_s)
      val x10563 = ScalarBuffer(name="x10563").wtPort(x10483_x10483_dsp0_bank0_s)
      val ctr65 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10579 = CounterChain(name = "x10579", ctr65).iter(1)
      Stage(operands=List(CU.load(x10584), CU.load(x10563)), op=FixMul, results=List(CU.scalarOut(x10561_x10588_s)))
    }
    val x10601_0 = Pipeline(name="x10601_0",parent=x10627) { implicit CU => 
      val x10564 = ScalarBuffer(name="x10564").wtPort(x10483_x10483_dsp0_bank0_s)
      val x10596 = ScalarFIFO(size=1,name="x10596").wtPort(x10182_x10182_dsp0_bank1_s)
      val ctr66 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10591 = CounterChain(name = "x10591", ctr66).iter(1)
      Stage(operands=List(CU.load(x10596), CU.load(x10564)), op=FixMul, results=List(CU.scalarOut(x10562_x10600_s)))
    }
    val x10626_0 = Pipeline(name="x10626_0",parent=x10627) { implicit CU => 
      val x10620 = CU.temp(None)
      val x10610 = ScalarFIFO(size=1,name="x10610").wtPort(x10562_x10562_dsp0_bank0_s)
      val x10612 = ScalarFIFO(size=1,name="x10612").wtPort(x10557_x10557_dsp0_bank0_s)
      val x10608 = ScalarFIFO(size=1,name="x10608").wtPort(x10561_x10561_dsp0_bank0_s)
      val ctr67 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10604 = CounterChain(name = "x10604", ctr67).iter(1)
      Stage(operands=List(CU.load(x10608), CU.load(x10610)), op=FixAdd, results=List(x10620))
      Stage(operands=List(x10620, CU.load(x10612)), op=FixAdd, results=List(CU.scalarOut(x10557_x10625_s)))
    }
    val x10696 = MetaPipeline(name="x10696",parent=x10717) { implicit CU => 
      val ctr68 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x10629 = CounterChain(name = "x10629", ctr68).iter(8)
    }
    val x10630_dsp0_bank0 = MemoryPipeline(name="x10630_dsp0_bank0",parent="x10696") { implicit CU => 
      val x10657 = ScalarFIFO(size=1,name="x10657").wtPort(x10630_x10657_s)
      val x10648 = CounterChain.copy("x10658_0", "x10648")
      val x10673 = CounterChain.copy("x10695_0", "x10673")
      val x10630 = SRAM(size=16,name="x10630",banking = Strided(1)).wtPort(x10657.readPort).rdPort(x10630_x10630_dsp0_bank0_s).rdAddr(x10673(0)).wtAddr(x10648(0))
    }
    val x10631_dsp0_bank0 = MemoryPipeline(name="x10631_dsp0_bank0",parent="x10696") { implicit CU => 
      val x10669 = ScalarFIFO(size=1,name="x10669").wtPort(x10631_x10669_s)
      val x10660 = CounterChain.copy("x10670_0", "x10660")
      val x10673 = CounterChain.copy("x10695_0", "x10673")
      val x10631 = SRAM(size=16,name="x10631",banking = Strided(1)).wtPort(x10669.readPort).rdPort(x10631_x10631_dsp0_bank0_s).rdAddr(x10673(0)).wtAddr(x10660(0))
    }
    val x10658_0 = Pipeline(name="x10658_0",parent=x10696) { implicit CU => 
      val x10653 = ScalarFIFO(size=1,name="x10653").wtPort(x10182_x10182_dsp1_bank0_s)
      val x10632 = ScalarBuffer(name="x10632").wtPort(x10484_x10484_dsp0_bank0_s)
      val ctr69 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10648 = CounterChain(name = "x10648", ctr69).iter(1)
      Stage(operands=List(CU.load(x10653), CU.load(x10632)), op=FixMul, results=List(CU.scalarOut(x10630_x10657_s)))
    }
    val x10670_0 = Pipeline(name="x10670_0",parent=x10696) { implicit CU => 
      val x10633 = ScalarBuffer(name="x10633").wtPort(x10484_x10484_dsp0_bank0_s)
      val x10665 = ScalarFIFO(size=1,name="x10665").wtPort(x10182_x10182_dsp1_bank1_s)
      val ctr70 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10660 = CounterChain(name = "x10660", ctr70).iter(1)
      Stage(operands=List(CU.load(x10665), CU.load(x10633)), op=FixMul, results=List(CU.scalarOut(x10631_x10669_s)))
    }
    val x10695_0 = Pipeline(name="x10695_0",parent=x10696) { implicit CU => 
      val x10689 = CU.temp(None)
      val x10679 = ScalarFIFO(size=1,name="x10679").wtPort(x10631_x10631_dsp0_bank0_s)
      val x10681 = ScalarFIFO(size=1,name="x10681").wtPort(x10558_x10558_dsp0_bank0_s)
      val x10677 = ScalarFIFO(size=1,name="x10677").wtPort(x10630_x10630_dsp0_bank0_s)
      val ctr71 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10673 = CounterChain(name = "x10673", ctr71).iter(1)
      Stage(operands=List(CU.load(x10677), CU.load(x10679)), op=FixAdd, results=List(x10689))
      Stage(operands=List(x10689, CU.load(x10681)), op=FixAdd, results=List(CU.scalarOut(x10558_x10694_s)))
    }
    val x10706 = Pipeline(name="x10706",parent=x10717) { implicit CU => 
      val ctr72 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10699 = CounterChain(name = "x10699", ctr72).iter(16)
    }
    val x10715 = Pipeline(name="x10715",parent=x10717) { implicit CU => 
      val ctr73 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10708 = CounterChain(name = "x10708", ctr73).iter(16)
    }
    val x10740_0 = Pipeline(name="x10740_0",parent=x10741) { implicit CU => 
      val x10734 = CU.temp(None)
      val x10724 = ScalarFIFO(size=1,name="x10724").wtPort(x10179_x10179_dsp0_bank0_s).wtPort(x10179_x10179_dsp0_bank1_s)
      val x10726 = ScalarFIFO(size=1,name="x10726").wtPort(x10180_x10180_dsp0_bank0_s).wtPort(x10180_x10180_dsp0_bank1_s)
      val x10728 = ScalarFIFO(size=1,name="x10728").wtPort(x9609_x9609_dsp1_bank0_s)
      val ctr74 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr75 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10721 = CounterChain(name = "x10721", ctr74, ctr75).iter(64)
      Stage(operands=List(CU.load(x10724), CU.load(x10726)), op=FixAdd, results=List(x10734))
      Stage(operands=List(x10734, CU.load(x10728)), op=FixAdd, results=List(CU.scalarOut(x9609_x10739_s)))
    }
    val x11306 = MetaPipeline(name="x11306",parent=x12011) { implicit CU => 
      val ctr76 = Counter(min=Const(0), max=Const(64), step=Const(16), par=2) // Counter
      val x10743 = CounterChain(name = "x10743", ctr76).iter(2)
    }
    val x10744_dsp0_bank0 = MemoryPipeline(name="x10744_dsp0_bank0",parent="x11306") { implicit CU => 
      val b12231 = CU.temp(None)
      val b12207 = CU.temp(None)
      val x11033 = ScalarFIFO(size=1,name="x11033").wtPort(x10885_x10885_dsp1_bank0_s)
      val x11027 = CounterChain.copy("x11034", "x11027")
      val x10810 = CounterChain.copy("x11045", "x10810")
      val x11286 = CounterChain.copy("x11305_0", "x11286")
      val x10744 = SRAM(size=1024,name="x10744",banking = Strided(1)).wtPort(x11033.readPort).rdPort(x10744_x10744_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x10810(0)), Const(16)), op=FixMul, results=List(b12207))
      WAStage(operands=List(b12207, CU.ctr(x11027(0))), op=FixAdd, results=List(x10744.writeAddr))
      RAStage(operands=List(CU.ctr(x11286(0)), Const(16)), op=FixMul, results=List(b12231))
      RAStage(operands=List(b12231, CU.ctr(x11286(1))), op=FixAdd, results=List(x10744.readAddr))
    }
    val x10744_dsp0_bank1 = MemoryPipeline(name="x10744_dsp0_bank1",parent="x11306") { implicit CU => 
      val b12231 = CU.temp(None)
      val b12209 = CU.temp(None)
      val x11042 = ScalarFIFO(size=1,name="x11042").wtPort(x10886_x10886_dsp1_bank0_s)
      val x10810 = CounterChain.copy("x11045", "x10810")
      val x11036 = CounterChain.copy("x11043", "x11036")
      val x11286 = CounterChain.copy("x11305_0", "x11286")
      val x10744 = SRAM(size=1024,name="x10744",banking = Strided(1)).wtPort(x11042.readPort).rdPort(x10744_x10744_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x10810(0)), Const(16)), op=FixMul, results=List(b12209))
      WAStage(operands=List(b12209, CU.ctr(x11036(0))), op=FixAdd, results=List(x10744.writeAddr))
      RAStage(operands=List(CU.ctr(x11286(0)), Const(16)), op=FixMul, results=List(b12231))
      RAStage(operands=List(b12231, CU.ctr(x11286(1))), op=FixAdd, results=List(x10744.readAddr))
    }
    val x10745_dsp0_bank0 = MemoryPipeline(name="x10745_dsp0_bank0",parent="x11306") { implicit CU => 
      val b12227 = CU.temp(None)
      val b12233 = CU.temp(None)
      val x11270 = ScalarFIFO(size=1,name="x11270").wtPort(x11122_x11122_dsp1_bank0_s)
      val x11264 = CounterChain.copy("x11271", "x11264")
      val x11047 = CounterChain.copy("x11282", "x11047")
      val x11286 = CounterChain.copy("x11305_0", "x11286")
      val x10745 = SRAM(size=1024,name="x10745",banking = Strided(1)).wtPort(x11270.readPort).rdPort(x10745_x10745_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x11047(0)), Const(16)), op=FixMul, results=List(b12227))
      WAStage(operands=List(b12227, CU.ctr(x11264(0))), op=FixAdd, results=List(x10745.writeAddr))
      RAStage(operands=List(CU.ctr(x11286(0)), Const(16)), op=FixMul, results=List(b12233))
      RAStage(operands=List(b12233, CU.ctr(x11286(1))), op=FixAdd, results=List(x10745.readAddr))
    }
    val x10745_dsp0_bank1 = MemoryPipeline(name="x10745_dsp0_bank1",parent="x11306") { implicit CU => 
      val b12229 = CU.temp(None)
      val b12233 = CU.temp(None)
      val x11279 = ScalarFIFO(size=1,name="x11279").wtPort(x11123_x11123_dsp1_bank0_s)
      val x11047 = CounterChain.copy("x11282", "x11047")
      val x11286 = CounterChain.copy("x11305_0", "x11286")
      val x11273 = CounterChain.copy("x11280", "x11273")
      val x10745 = SRAM(size=1024,name="x10745",banking = Strided(1)).wtPort(x11279.readPort).rdPort(x10745_x10745_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x11047(0)), Const(16)), op=FixMul, results=List(b12229))
      WAStage(operands=List(b12229, CU.ctr(x11273(0))), op=FixAdd, results=List(x10745.writeAddr))
      RAStage(operands=List(CU.ctr(x11286(0)), Const(16)), op=FixMul, results=List(b12233))
      RAStage(operands=List(b12233, CU.ctr(x11286(1))), op=FixAdd, results=List(x10745.readAddr))
    }
    val x10746_dsp0_bank0 = MemoryPipeline(name="x10746_dsp0_bank0",parent="x11306") { implicit CU => 
      val b12183 = CU.temp(None)
      val b12199 = CU.temp(None)
      val x10775 = ScalarFIFO(size=1,name="x10775").wtPort(x10751_x10767_data_s)
      val x10769 = CounterChain.copy("x10776", "x10769")
      val x10749 = CounterChain.copy("x10777", "x10749")
      val x10907 = CounterChain.copy("x10917_0", "x10907")
      val x10888 = CounterChain.copy("x10955", "x10888")
      val x10746 = SRAM(size=256,name="x10746",banking = Strided(1)).wtPort(x10775.readPort).rdPort(x10746_x10746_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x10749(0)), Const(16)), op=FixMul, results=List(b12183))
      WAStage(operands=List(b12183, CU.ctr(x10769(0))), op=FixAdd, results=List(x10746.writeAddr))
      RAStage(operands=List(CU.ctr(x10888(0)), Const(16)), op=FixMul, results=List(b12199))
      RAStage(operands=List(b12199, CU.ctr(x10907(0))), op=FixAdd, results=List(x10746.readAddr))
    }
    val x10746_dsp0_bank1 = MemoryPipeline(name="x10746_dsp0_bank1",parent="x11306") { implicit CU => 
      val b12183 = CU.temp(None)
      val b12201 = CU.temp(None)
      val x10775 = ScalarFIFO(size=1,name="x10775").wtPort(x10751_x10767_data_s)
      val x10769 = CounterChain.copy("x10776", "x10769")
      val x10919 = CounterChain.copy("x10929_0", "x10919")
      val x10749 = CounterChain.copy("x10777", "x10749")
      val x10888 = CounterChain.copy("x10955", "x10888")
      val x10746 = SRAM(size=256,name="x10746",banking = Strided(1)).wtPort(x10775.readPort).rdPort(x10746_x10746_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x10749(0)), Const(16)), op=FixMul, results=List(b12183))
      WAStage(operands=List(b12183, CU.ctr(x10769(0))), op=FixAdd, results=List(x10746.writeAddr))
      RAStage(operands=List(CU.ctr(x10888(0)), Const(16)), op=FixMul, results=List(b12201))
      RAStage(operands=List(b12201, CU.ctr(x10919(0))), op=FixAdd, results=List(x10746.readAddr))
    }
    val x10746_dsp1_bank0 = MemoryPipeline(name="x10746_dsp1_bank0",parent="x11306") { implicit CU => 
      val b12183 = CU.temp(None)
      val b12203 = CU.temp(None)
      val x10775 = ScalarFIFO(size=1,name="x10775").wtPort(x10751_x10767_data_s)
      val x10769 = CounterChain.copy("x10776", "x10769")
      val x10749 = CounterChain.copy("x10777", "x10749")
      val x10976 = CounterChain.copy("x10986_0", "x10976")
      val x10957 = CounterChain.copy("x11024", "x10957")
      val x10746 = SRAM(size=256,name="x10746",banking = Strided(1)).wtPort(x10775.readPort).rdPort(x10746_x10746_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x10749(0)), Const(16)), op=FixMul, results=List(b12183))
      WAStage(operands=List(b12183, CU.ctr(x10769(0))), op=FixAdd, results=List(x10746.writeAddr))
      RAStage(operands=List(CU.ctr(x10957(0)), Const(16)), op=FixMul, results=List(b12203))
      RAStage(operands=List(b12203, CU.ctr(x10976(0))), op=FixAdd, results=List(x10746.readAddr))
    }
    val x10746_dsp1_bank1 = MemoryPipeline(name="x10746_dsp1_bank1",parent="x11306") { implicit CU => 
      val b12183 = CU.temp(None)
      val b12205 = CU.temp(None)
      val x10775 = ScalarFIFO(size=1,name="x10775").wtPort(x10751_x10767_data_s)
      val x10769 = CounterChain.copy("x10776", "x10769")
      val x10749 = CounterChain.copy("x10777", "x10749")
      val x10988 = CounterChain.copy("x10998_0", "x10988")
      val x10957 = CounterChain.copy("x11024", "x10957")
      val x10746 = SRAM(size=256,name="x10746",banking = Strided(1)).wtPort(x10775.readPort).rdPort(x10746_x10746_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x10749(0)), Const(16)), op=FixMul, results=List(b12183))
      WAStage(operands=List(b12183, CU.ctr(x10769(0))), op=FixAdd, results=List(x10746.writeAddr))
      RAStage(operands=List(CU.ctr(x10957(0)), Const(16)), op=FixMul, results=List(b12205))
      RAStage(operands=List(b12205, CU.ctr(x10988(0))), op=FixAdd, results=List(x10746.readAddr))
    }
    val x10747_dsp0_bank0 = MemoryPipeline(name="x10747_dsp0_bank0",parent="x11306") { implicit CU => 
      val b12189 = CU.temp(None)
      val b12219 = CU.temp(None)
      val x10805 = ScalarFIFO(size=1,name="x10805").wtPort(x10781_x10797_data_s)
      val x11125 = CounterChain.copy("x11192", "x11125")
      val x10799 = CounterChain.copy("x10806", "x10799")
      val x10779 = CounterChain.copy("x10807", "x10779")
      val x11144 = CounterChain.copy("x11154_0", "x11144")
      val x10747 = SRAM(size=256,name="x10747",banking = Strided(1)).wtPort(x10805.readPort).rdPort(x10747_x10747_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x10779(0)), Const(16)), op=FixMul, results=List(b12189))
      WAStage(operands=List(b12189, CU.ctr(x10799(0))), op=FixAdd, results=List(x10747.writeAddr))
      RAStage(operands=List(CU.ctr(x11125(0)), Const(16)), op=FixMul, results=List(b12219))
      RAStage(operands=List(b12219, CU.ctr(x11144(0))), op=FixAdd, results=List(x10747.readAddr))
    }
    val x10747_dsp0_bank1 = MemoryPipeline(name="x10747_dsp0_bank1",parent="x11306") { implicit CU => 
      val b12189 = CU.temp(None)
      val b12221 = CU.temp(None)
      val x10805 = ScalarFIFO(size=1,name="x10805").wtPort(x10781_x10797_data_s)
      val x11125 = CounterChain.copy("x11192", "x11125")
      val x10799 = CounterChain.copy("x10806", "x10799")
      val x11156 = CounterChain.copy("x11166_0", "x11156")
      val x10779 = CounterChain.copy("x10807", "x10779")
      val x10747 = SRAM(size=256,name="x10747",banking = Strided(1)).wtPort(x10805.readPort).rdPort(x10747_x10747_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x10779(0)), Const(16)), op=FixMul, results=List(b12189))
      WAStage(operands=List(b12189, CU.ctr(x10799(0))), op=FixAdd, results=List(x10747.writeAddr))
      RAStage(operands=List(CU.ctr(x11125(0)), Const(16)), op=FixMul, results=List(b12221))
      RAStage(operands=List(b12221, CU.ctr(x11156(0))), op=FixAdd, results=List(x10747.readAddr))
    }
    val x10747_dsp1_bank0 = MemoryPipeline(name="x10747_dsp1_bank0",parent="x11306") { implicit CU => 
      val b12189 = CU.temp(None)
      val b12223 = CU.temp(None)
      val x10805 = ScalarFIFO(size=1,name="x10805").wtPort(x10781_x10797_data_s)
      val x10799 = CounterChain.copy("x10806", "x10799")
      val x10779 = CounterChain.copy("x10807", "x10779")
      val x11194 = CounterChain.copy("x11261", "x11194")
      val x11213 = CounterChain.copy("x11223_0", "x11213")
      val x10747 = SRAM(size=256,name="x10747",banking = Strided(1)).wtPort(x10805.readPort).rdPort(x10747_x10747_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x10779(0)), Const(16)), op=FixMul, results=List(b12189))
      WAStage(operands=List(b12189, CU.ctr(x10799(0))), op=FixAdd, results=List(x10747.writeAddr))
      RAStage(operands=List(CU.ctr(x11194(0)), Const(16)), op=FixMul, results=List(b12223))
      RAStage(operands=List(b12223, CU.ctr(x11213(0))), op=FixAdd, results=List(x10747.readAddr))
    }
    val x10747_dsp1_bank1 = MemoryPipeline(name="x10747_dsp1_bank1",parent="x11306") { implicit CU => 
      val b12189 = CU.temp(None)
      val b12225 = CU.temp(None)
      val x10805 = ScalarFIFO(size=1,name="x10805").wtPort(x10781_x10797_data_s)
      val x10799 = CounterChain.copy("x10806", "x10799")
      val x10779 = CounterChain.copy("x10807", "x10779")
      val x11225 = CounterChain.copy("x11235_0", "x11225")
      val x11194 = CounterChain.copy("x11261", "x11194")
      val x10747 = SRAM(size=256,name="x10747",banking = Strided(1)).wtPort(x10805.readPort).rdPort(x10747_x10747_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x10779(0)), Const(16)), op=FixMul, results=List(b12189))
      WAStage(operands=List(b12189, CU.ctr(x10799(0))), op=FixAdd, results=List(x10747.writeAddr))
      RAStage(operands=List(CU.ctr(x11194(0)), Const(16)), op=FixMul, results=List(b12225))
      RAStage(operands=List(b12225, CU.ctr(x11225(0))), op=FixAdd, results=List(x10747.readAddr))
    }
    val x10777 = StreamController(name="x10777",parent=x11306) { implicit CU => 
      val ctr77 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10749 = CounterChain(name = "x10749", ctr77).iter(16)
    }
    val x10766_0 = Pipeline(name="x10766_0",parent=x10777) { implicit CU => 
      val x10756 = CU.temp(None)
      val x10757 = CU.temp(None)
      val x10752 = CU.temp(None)
      val x10754 = CU.temp(None)
      val x10759 = ScalarBuffer(name="x10759").wtPort(b_dram_da)
      val x10743 = CounterChain.copy("x11306", "x10743")
      val x10749 = CounterChain.copy("x10777", "x10749")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x10766_unit = CounterChain(name = "x10766_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x10743(0)), CU.ctr(x10749(0))), op=FixAdd, results=List(x10752))
      Stage(operands=List(x10752, Const(6)), op=FixSla, results=List(x10754))
      Stage(operands=List(x10754, CU.ctr(x9607(0))), op=FixAdd, results=List(x10756))
      Stage(operands=List(x10756, Const(3)), op=FixSla, results=List(x10757))
      Stage(operands=List(x10757, CU.load(x10759)), op=FixAdd, results=List(CU.scalarOut(x10750_b12179_x10765_b12181_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x10750_b12180_x10765_b12182_s)))
    }
    val x10767 = MemoryController(name="x10767",parent=x10777,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x10750_b12180 = ScalarFIFO(size=1,name="size").wtPort(x10750_b12180_x10765_b12182_s)
      val x10750_b12179 = ScalarFIFO(size=1,name="offset").wtPort(x10750_b12179_x10765_b12181_s)
      CU.newSout("data", x10751_x10767_data_s)
    }
    val x10776 = Pipeline(name="x10776",parent=x10777) { implicit CU => 
      val ctr78 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10769 = CounterChain(name = "x10769", ctr78).iter(1)
    }
    val x10807 = StreamController(name="x10807",parent=x11306) { implicit CU => 
      val ctr79 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10779 = CounterChain(name = "x10779", ctr79).iter(16)
    }
    val x10796_0 = Pipeline(name="x10796_0",parent=x10807) { implicit CU => 
      val x10782 = CU.temp(None)
      val x10787 = CU.temp(None)
      val x10784 = CU.temp(None)
      val x10786 = CU.temp(None)
      val x10789 = ScalarBuffer(name="x10789").wtPort(b_dram_da)
      val x10743 = CounterChain.copy("x11306", "x10743")
      val x10779 = CounterChain.copy("x10807", "x10779")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x10796_unit = CounterChain(name = "x10796_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x10743(0)), CU.ctr(x10779(0))), op=FixAdd, results=List(x10782))
      Stage(operands=List(x10782, Const(6)), op=FixSla, results=List(x10784))
      Stage(operands=List(x10784, CU.ctr(x9607(0))), op=FixAdd, results=List(x10786))
      Stage(operands=List(x10786, Const(3)), op=FixSla, results=List(x10787))
      Stage(operands=List(x10787, CU.load(x10789)), op=FixAdd, results=List(CU.scalarOut(x10780_b12185_x10795_b12187_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x10780_b12186_x10795_b12188_s)))
    }
    val x10797 = MemoryController(name="x10797",parent=x10807,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x10780_b12186 = ScalarFIFO(size=1,name="size").wtPort(x10780_b12186_x10795_b12188_s)
      val x10780_b12185 = ScalarFIFO(size=1,name="offset").wtPort(x10780_b12185_x10795_b12187_s)
      CU.newSout("data", x10781_x10797_data_s)
    }
    val x10806 = Pipeline(name="x10806",parent=x10807) { implicit CU => 
      val ctr80 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10799 = CounterChain(name = "x10799", ctr80).iter(1)
    }
    val x11045 = MetaPipeline(name="x11045",parent=x11306) { implicit CU => 
      val ctr81 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x10810 = CounterChain(name = "x10810", ctr81).iter(32)
    }
    val x10811_dsp0_bank0 = MemoryPipeline(name="x10811_dsp0_bank0",parent="x11045") { implicit CU => 
      val x10849 = ScalarFIFO(size=1,name="x10849").wtPort(x10823_x10840_data_s)
      val x10842 = CounterChain.copy("x10850", "x10842")
      val x10888 = CounterChain.copy("x10955", "x10888")
      val x10811 = SRAM(size=16,name="x10811",banking = Strided(1)).wtPort(x10849.readPort).rdPort(x10811_x10811_dsp0_bank0_s).rdAddr(x10888(0)).rdAddr(x10888(0)).wtAddr(x10842(0))
    }
    val x10812_dsp0_bank0 = MemoryPipeline(name="x10812_dsp0_bank0",parent="x11045") { implicit CU => 
      val x10881 = ScalarFIFO(size=1,name="x10881").wtPort(x10855_x10872_data_s)
      val x10957 = CounterChain.copy("x11024", "x10957")
      val x10874 = CounterChain.copy("x10882", "x10874")
      val x10812 = SRAM(size=16,name="x10812",banking = Strided(1)).wtPort(x10881.readPort).rdPort(x10812_x10812_dsp0_bank0_s).rdAddr(x10957(0)).rdAddr(x10957(0)).wtAddr(x10874(0))
    }
    val x10851 = StreamController(name="x10851",parent=x11045) { implicit CU => 
      val ctr82 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x10821 = CounterChain(name = "x10821", ctr82).iter(1)
    }
    val x10839_0 = Pipeline(name="x10839_0",parent=x10851) { implicit CU => 
      val x10829 = CU.temp(None)
      val x10826 = CU.temp(None)
      val x10828 = CU.temp(None)
      val x10824 = CU.temp(None)
      val x10831 = ScalarBuffer(name="x10831").wtPort(a_dram_da)
      val x10810 = CounterChain.copy("x11045", "x10810")
      val x10821 = CounterChain.copy("x10851", "x10821")
      val x10839_unit = CounterChain(name = "x10839_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x10743 = CounterChain.copy("x11306", "x10743")
      Stage(operands=List(CU.ctr(x10810(0)), CU.ctr(x10821(0))), op=FixAdd, results=List(x10824))
      Stage(operands=List(x10824, Const(6)), op=FixSla, results=List(x10826))
      Stage(operands=List(x10826, CU.ctr(x10743(0))), op=FixAdd, results=List(x10828))
      Stage(operands=List(x10828, Const(3)), op=FixSla, results=List(x10829))
      Stage(operands=List(x10829, CU.load(x10831)), op=FixAdd, results=List(CU.scalarOut(x10822_b12191_x10838_b12193_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x10822_b12192_x10838_b12194_s)))
    }
    val x10840 = MemoryController(name="x10840",parent=x10851,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x10822_b12192 = ScalarFIFO(size=1,name="size").wtPort(x10822_b12192_x10838_b12194_s)
      val x10822_b12191 = ScalarFIFO(size=1,name="offset").wtPort(x10822_b12191_x10838_b12193_s)
      CU.newSout("data", x10823_x10840_data_s)
    }
    val x10850 = Pipeline(name="x10850",parent=x10851) { implicit CU => 
      val ctr83 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10842 = CounterChain(name = "x10842", ctr83).iter(16)
    }
    val x10883 = StreamController(name="x10883",parent=x11045) { implicit CU => 
      val ctr84 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x10853 = CounterChain(name = "x10853", ctr84).iter(1)
    }
    val x10871_0 = Pipeline(name="x10871_0",parent=x10883) { implicit CU => 
      val x10861 = CU.temp(None)
      val x10856 = CU.temp(None)
      val x10860 = CU.temp(None)
      val x10858 = CU.temp(None)
      val x10863 = ScalarBuffer(name="x10863").wtPort(a_dram_da)
      val x10810 = CounterChain.copy("x11045", "x10810")
      val x10853 = CounterChain.copy("x10883", "x10853")
      val x10743 = CounterChain.copy("x11306", "x10743")
      val x10871_unit = CounterChain(name = "x10871_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x10810(0)), CU.ctr(x10853(0))), op=FixAdd, results=List(x10856))
      Stage(operands=List(x10856, Const(6)), op=FixSla, results=List(x10858))
      Stage(operands=List(x10858, CU.ctr(x10743(0))), op=FixAdd, results=List(x10860))
      Stage(operands=List(x10860, Const(3)), op=FixSla, results=List(x10861))
      Stage(operands=List(x10861, CU.load(x10863)), op=FixAdd, results=List(CU.scalarOut(x10854_b12195_x10870_b12197_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x10854_b12196_x10870_b12198_s)))
    }
    val x10872 = MemoryController(name="x10872",parent=x10883,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x10854_b12195 = ScalarFIFO(size=1,name="offset").wtPort(x10854_b12195_x10870_b12197_s)
      val x10854_b12196 = ScalarFIFO(size=1,name="size").wtPort(x10854_b12196_x10870_b12198_s)
      CU.newSout("data", x10855_x10872_data_s)
    }
    val x10882 = Pipeline(name="x10882",parent=x10883) { implicit CU => 
      val ctr85 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x10874 = CounterChain(name = "x10874", ctr85).iter(16)
    }
    val x10885_dsp0_bank0 = MemoryPipeline(name="x10885_dsp0_bank0",parent="x10955") { implicit CU => 
      val x10953 = ScalarFIFO(size=1,name="x10953").wtPort(x10885_x10953_s)
      val x10932 = CounterChain.copy("x10954_0", "x10932")
      val x10885 = SRAM(size=16,name="x10885",banking = Strided(1)).wtPort(x10953.readPort).rdPort(x10885_x10885_dsp0_bank0_s).rdAddr(x10932(0)).wtAddr(x10932(0))
    }
    val x10885_dsp1_bank0 = MemoryPipeline(name="x10885_dsp1_bank0",parent="x10955") { implicit CU => 
      val x10953 = ScalarFIFO(size=1,name="x10953").wtPort(x10885_x10953_s)
      val x11027 = CounterChain.copy("x11034", "x11027")
      val x10932 = CounterChain.copy("x10954_0", "x10932")
      val x10885 = SRAM(size=16,name="x10885",banking = Strided(1)).wtPort(x10953.readPort).rdPort(x10885_x10885_dsp1_bank0_s).rdAddr(x11027(0)).wtAddr(x10932(0))
    }
    val x10886_dsp0_bank0 = MemoryPipeline(name="x10886_dsp0_bank0",parent="x11024") { implicit CU => 
      val x11022 = ScalarFIFO(size=1,name="x11022").wtPort(x10886_x11022_s)
      val x11001 = CounterChain.copy("x11023_0", "x11001")
      val x10886 = SRAM(size=16,name="x10886",banking = Strided(1)).wtPort(x11022.readPort).rdPort(x10886_x10886_dsp0_bank0_s).rdAddr(x11001(0)).wtAddr(x11001(0))
    }
    val x10886_dsp1_bank0 = MemoryPipeline(name="x10886_dsp1_bank0",parent="x11024") { implicit CU => 
      val x11022 = ScalarFIFO(size=1,name="x11022").wtPort(x10886_x11022_s)
      val x11036 = CounterChain.copy("x11043", "x11036")
      val x11001 = CounterChain.copy("x11023_0", "x11001")
      val x10886 = SRAM(size=16,name="x10886",banking = Strided(1)).wtPort(x11022.readPort).rdPort(x10886_x10886_dsp1_bank0_s).rdAddr(x11036(0)).wtAddr(x11001(0))
    }
    val x10955 = MetaPipeline(name="x10955",parent=x11045) { implicit CU => 
      val ctr86 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x10888 = CounterChain(name = "x10888", ctr86).iter(8)
    }
    val x10889_dsp0_bank0 = MemoryPipeline(name="x10889_dsp0_bank0",parent="x10955") { implicit CU => 
      val x10916 = ScalarFIFO(size=1,name="x10916").wtPort(x10889_x10916_s)
      val x10907 = CounterChain.copy("x10917_0", "x10907")
      val x10932 = CounterChain.copy("x10954_0", "x10932")
      val x10889 = SRAM(size=16,name="x10889",banking = Strided(1)).wtPort(x10916.readPort).rdPort(x10889_x10889_dsp0_bank0_s).rdAddr(x10932(0)).wtAddr(x10907(0))
    }
    val x10890_dsp0_bank0 = MemoryPipeline(name="x10890_dsp0_bank0",parent="x10955") { implicit CU => 
      val x10928 = ScalarFIFO(size=1,name="x10928").wtPort(x10890_x10928_s)
      val x10919 = CounterChain.copy("x10929_0", "x10919")
      val x10932 = CounterChain.copy("x10954_0", "x10932")
      val x10890 = SRAM(size=16,name="x10890",banking = Strided(1)).wtPort(x10928.readPort).rdPort(x10890_x10890_dsp0_bank0_s).rdAddr(x10932(0)).wtAddr(x10919(0))
    }
    val x10917_0 = Pipeline(name="x10917_0",parent=x10955) { implicit CU => 
      val x10912 = ScalarFIFO(size=1,name="x10912").wtPort(x10746_x10746_dsp0_bank0_s)
      val x10891 = ScalarBuffer(name="x10891").wtPort(x10811_x10811_dsp0_bank0_s)
      val ctr87 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10907 = CounterChain(name = "x10907", ctr87).iter(1)
      Stage(operands=List(CU.load(x10912), CU.load(x10891)), op=FixMul, results=List(CU.scalarOut(x10889_x10916_s)))
    }
    val x10929_0 = Pipeline(name="x10929_0",parent=x10955) { implicit CU => 
      val x10924 = ScalarFIFO(size=1,name="x10924").wtPort(x10746_x10746_dsp0_bank1_s)
      val x10892 = ScalarBuffer(name="x10892").wtPort(x10811_x10811_dsp0_bank0_s)
      val ctr88 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10919 = CounterChain(name = "x10919", ctr88).iter(1)
      Stage(operands=List(CU.load(x10924), CU.load(x10892)), op=FixMul, results=List(CU.scalarOut(x10890_x10928_s)))
    }
    val x10954_0 = Pipeline(name="x10954_0",parent=x10955) { implicit CU => 
      val x10948 = CU.temp(None)
      val x10936 = ScalarFIFO(size=1,name="x10936").wtPort(x10889_x10889_dsp0_bank0_s)
      val x10938 = ScalarFIFO(size=1,name="x10938").wtPort(x10890_x10890_dsp0_bank0_s)
      val x10940 = ScalarFIFO(size=1,name="x10940").wtPort(x10885_x10885_dsp0_bank0_s)
      val ctr89 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10932 = CounterChain(name = "x10932", ctr89).iter(1)
      Stage(operands=List(CU.load(x10936), CU.load(x10938)), op=FixAdd, results=List(x10948))
      Stage(operands=List(x10948, CU.load(x10940)), op=FixAdd, results=List(CU.scalarOut(x10885_x10953_s)))
    }
    val x11024 = MetaPipeline(name="x11024",parent=x11045) { implicit CU => 
      val ctr90 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x10957 = CounterChain(name = "x10957", ctr90).iter(8)
    }
    val x10958_dsp0_bank0 = MemoryPipeline(name="x10958_dsp0_bank0",parent="x11024") { implicit CU => 
      val x10985 = ScalarFIFO(size=1,name="x10985").wtPort(x10958_x10985_s)
      val x10976 = CounterChain.copy("x10986_0", "x10976")
      val x11001 = CounterChain.copy("x11023_0", "x11001")
      val x10958 = SRAM(size=16,name="x10958",banking = Strided(1)).wtPort(x10985.readPort).rdPort(x10958_x10958_dsp0_bank0_s).rdAddr(x11001(0)).wtAddr(x10976(0))
    }
    val x10959_dsp0_bank0 = MemoryPipeline(name="x10959_dsp0_bank0",parent="x11024") { implicit CU => 
      val x10997 = ScalarFIFO(size=1,name="x10997").wtPort(x10959_x10997_s)
      val x10988 = CounterChain.copy("x10998_0", "x10988")
      val x11001 = CounterChain.copy("x11023_0", "x11001")
      val x10959 = SRAM(size=16,name="x10959",banking = Strided(1)).wtPort(x10997.readPort).rdPort(x10959_x10959_dsp0_bank0_s).rdAddr(x11001(0)).wtAddr(x10988(0))
    }
    val x10986_0 = Pipeline(name="x10986_0",parent=x11024) { implicit CU => 
      val x10960 = ScalarBuffer(name="x10960").wtPort(x10812_x10812_dsp0_bank0_s)
      val x10981 = ScalarFIFO(size=1,name="x10981").wtPort(x10746_x10746_dsp1_bank0_s)
      val ctr91 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10976 = CounterChain(name = "x10976", ctr91).iter(1)
      Stage(operands=List(CU.load(x10981), CU.load(x10960)), op=FixMul, results=List(CU.scalarOut(x10958_x10985_s)))
    }
    val x10998_0 = Pipeline(name="x10998_0",parent=x11024) { implicit CU => 
      val x10993 = ScalarFIFO(size=1,name="x10993").wtPort(x10746_x10746_dsp1_bank1_s)
      val x10961 = ScalarBuffer(name="x10961").wtPort(x10812_x10812_dsp0_bank0_s)
      val ctr92 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x10988 = CounterChain(name = "x10988", ctr92).iter(1)
      Stage(operands=List(CU.load(x10993), CU.load(x10961)), op=FixMul, results=List(CU.scalarOut(x10959_x10997_s)))
    }
    val x11023_0 = Pipeline(name="x11023_0",parent=x11024) { implicit CU => 
      val x11017 = CU.temp(None)
      val x11005 = ScalarFIFO(size=1,name="x11005").wtPort(x10958_x10958_dsp0_bank0_s)
      val x11007 = ScalarFIFO(size=1,name="x11007").wtPort(x10959_x10959_dsp0_bank0_s)
      val x11009 = ScalarFIFO(size=1,name="x11009").wtPort(x10886_x10886_dsp0_bank0_s)
      val ctr93 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11001 = CounterChain(name = "x11001", ctr93).iter(1)
      Stage(operands=List(CU.load(x11005), CU.load(x11007)), op=FixAdd, results=List(x11017))
      Stage(operands=List(x11017, CU.load(x11009)), op=FixAdd, results=List(CU.scalarOut(x10886_x11022_s)))
    }
    val x11034 = Pipeline(name="x11034",parent=x11045) { implicit CU => 
      val ctr94 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11027 = CounterChain(name = "x11027", ctr94).iter(16)
    }
    val x11043 = Pipeline(name="x11043",parent=x11045) { implicit CU => 
      val ctr95 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11036 = CounterChain(name = "x11036", ctr95).iter(16)
    }
    val x11282 = MetaPipeline(name="x11282",parent=x11306) { implicit CU => 
      val ctr96 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x11047 = CounterChain(name = "x11047", ctr96).iter(32)
    }
    val x11048_dsp0_bank0 = MemoryPipeline(name="x11048_dsp0_bank0",parent="x11282") { implicit CU => 
      val x11086 = ScalarFIFO(size=1,name="x11086").wtPort(x11060_x11077_data_s)
      val x11125 = CounterChain.copy("x11192", "x11125")
      val x11079 = CounterChain.copy("x11087", "x11079")
      val x11048 = SRAM(size=16,name="x11048",banking = Strided(1)).wtPort(x11086.readPort).rdPort(x11048_x11048_dsp0_bank0_s).rdAddr(x11125(0)).rdAddr(x11125(0)).wtAddr(x11079(0))
    }
    val x11049_dsp0_bank0 = MemoryPipeline(name="x11049_dsp0_bank0",parent="x11282") { implicit CU => 
      val x11118 = ScalarFIFO(size=1,name="x11118").wtPort(x11092_x11109_data_s)
      val x11111 = CounterChain.copy("x11119", "x11111")
      val x11194 = CounterChain.copy("x11261", "x11194")
      val x11049 = SRAM(size=16,name="x11049",banking = Strided(1)).wtPort(x11118.readPort).rdPort(x11049_x11049_dsp0_bank0_s).rdAddr(x11194(0)).rdAddr(x11194(0)).wtAddr(x11111(0))
    }
    val x11088 = StreamController(name="x11088",parent=x11282) { implicit CU => 
      val ctr97 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x11058 = CounterChain(name = "x11058", ctr97).iter(1)
    }
    val x11076_0 = Pipeline(name="x11076_0",parent=x11088) { implicit CU => 
      val x11066 = CU.temp(None)
      val x11061 = CU.temp(None)
      val x11065 = CU.temp(None)
      val x11063 = CU.temp(None)
      val x11068 = ScalarBuffer(name="x11068").wtPort(a_dram_da)
      val x11076_unit = CounterChain(name = "x11076_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x11047 = CounterChain.copy("x11282", "x11047")
      val x11058 = CounterChain.copy("x11088", "x11058")
      val x10743 = CounterChain.copy("x11306", "x10743")
      Stage(operands=List(CU.ctr(x11047(0)), CU.ctr(x11058(0))), op=FixAdd, results=List(x11061))
      Stage(operands=List(x11061, Const(6)), op=FixSla, results=List(x11063))
      Stage(operands=List(x11063, CU.ctr(x10743(0))), op=FixAdd, results=List(x11065))
      Stage(operands=List(x11065, Const(3)), op=FixSla, results=List(x11066))
      Stage(operands=List(x11066, CU.load(x11068)), op=FixAdd, results=List(CU.scalarOut(x11059_b12211_x11075_b12213_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11059_b12212_x11075_b12214_s)))
    }
    val x11077 = MemoryController(name="x11077",parent=x11088,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x11059_b12212 = ScalarFIFO(size=1,name="size").wtPort(x11059_b12212_x11075_b12214_s)
      val x11059_b12211 = ScalarFIFO(size=1,name="offset").wtPort(x11059_b12211_x11075_b12213_s)
      CU.newSout("data", x11060_x11077_data_s)
    }
    val x11087 = Pipeline(name="x11087",parent=x11088) { implicit CU => 
      val ctr98 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11079 = CounterChain(name = "x11079", ctr98).iter(16)
    }
    val x11120 = StreamController(name="x11120",parent=x11282) { implicit CU => 
      val ctr99 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x11090 = CounterChain(name = "x11090", ctr99).iter(1)
    }
    val x11108_0 = Pipeline(name="x11108_0",parent=x11120) { implicit CU => 
      val x11095 = CU.temp(None)
      val x11093 = CU.temp(None)
      val x11098 = CU.temp(None)
      val x11097 = CU.temp(None)
      val x11100 = ScalarBuffer(name="x11100").wtPort(a_dram_da)
      val x11108_unit = CounterChain(name = "x11108_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x11047 = CounterChain.copy("x11282", "x11047")
      val x11090 = CounterChain.copy("x11120", "x11090")
      val x10743 = CounterChain.copy("x11306", "x10743")
      Stage(operands=List(CU.ctr(x11047(0)), CU.ctr(x11090(0))), op=FixAdd, results=List(x11093))
      Stage(operands=List(x11093, Const(6)), op=FixSla, results=List(x11095))
      Stage(operands=List(x11095, CU.ctr(x10743(0))), op=FixAdd, results=List(x11097))
      Stage(operands=List(x11097, Const(3)), op=FixSla, results=List(x11098))
      Stage(operands=List(x11098, CU.load(x11100)), op=FixAdd, results=List(CU.scalarOut(x11091_b12215_x11107_b12217_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11091_b12216_x11107_b12218_s)))
    }
    val x11109 = MemoryController(name="x11109",parent=x11120,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x11091_b12216 = ScalarFIFO(size=1,name="size").wtPort(x11091_b12216_x11107_b12218_s)
      val x11091_b12215 = ScalarFIFO(size=1,name="offset").wtPort(x11091_b12215_x11107_b12217_s)
      CU.newSout("data", x11092_x11109_data_s)
    }
    val x11119 = Pipeline(name="x11119",parent=x11120) { implicit CU => 
      val ctr100 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11111 = CounterChain(name = "x11111", ctr100).iter(16)
    }
    val x11122_dsp0_bank0 = MemoryPipeline(name="x11122_dsp0_bank0",parent="x11192") { implicit CU => 
      val x11190 = ScalarFIFO(size=1,name="x11190").wtPort(x11122_x11190_s)
      val x11169 = CounterChain.copy("x11191_0", "x11169")
      val x11122 = SRAM(size=16,name="x11122",banking = Strided(1)).wtPort(x11190.readPort).rdPort(x11122_x11122_dsp0_bank0_s).rdAddr(x11169(0)).wtAddr(x11169(0))
    }
    val x11122_dsp1_bank0 = MemoryPipeline(name="x11122_dsp1_bank0",parent="x11192") { implicit CU => 
      val x11190 = ScalarFIFO(size=1,name="x11190").wtPort(x11122_x11190_s)
      val x11264 = CounterChain.copy("x11271", "x11264")
      val x11169 = CounterChain.copy("x11191_0", "x11169")
      val x11122 = SRAM(size=16,name="x11122",banking = Strided(1)).wtPort(x11190.readPort).rdPort(x11122_x11122_dsp1_bank0_s).rdAddr(x11264(0)).wtAddr(x11169(0))
    }
    val x11123_dsp0_bank0 = MemoryPipeline(name="x11123_dsp0_bank0",parent="x11261") { implicit CU => 
      val x11259 = ScalarFIFO(size=1,name="x11259").wtPort(x11123_x11259_s)
      val x11238 = CounterChain.copy("x11260_0", "x11238")
      val x11123 = SRAM(size=16,name="x11123",banking = Strided(1)).wtPort(x11259.readPort).rdPort(x11123_x11123_dsp0_bank0_s).rdAddr(x11238(0)).wtAddr(x11238(0))
    }
    val x11123_dsp1_bank0 = MemoryPipeline(name="x11123_dsp1_bank0",parent="x11261") { implicit CU => 
      val x11259 = ScalarFIFO(size=1,name="x11259").wtPort(x11123_x11259_s)
      val x11238 = CounterChain.copy("x11260_0", "x11238")
      val x11273 = CounterChain.copy("x11280", "x11273")
      val x11123 = SRAM(size=16,name="x11123",banking = Strided(1)).wtPort(x11259.readPort).rdPort(x11123_x11123_dsp1_bank0_s).rdAddr(x11273(0)).wtAddr(x11238(0))
    }
    val x11192 = MetaPipeline(name="x11192",parent=x11282) { implicit CU => 
      val ctr101 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x11125 = CounterChain(name = "x11125", ctr101).iter(8)
    }
    val x11126_dsp0_bank0 = MemoryPipeline(name="x11126_dsp0_bank0",parent="x11192") { implicit CU => 
      val x11153 = ScalarFIFO(size=1,name="x11153").wtPort(x11126_x11153_s)
      val x11169 = CounterChain.copy("x11191_0", "x11169")
      val x11144 = CounterChain.copy("x11154_0", "x11144")
      val x11126 = SRAM(size=16,name="x11126",banking = Strided(1)).wtPort(x11153.readPort).rdPort(x11126_x11126_dsp0_bank0_s).rdAddr(x11169(0)).wtAddr(x11144(0))
    }
    val x11127_dsp0_bank0 = MemoryPipeline(name="x11127_dsp0_bank0",parent="x11192") { implicit CU => 
      val x11165 = ScalarFIFO(size=1,name="x11165").wtPort(x11127_x11165_s)
      val x11156 = CounterChain.copy("x11166_0", "x11156")
      val x11169 = CounterChain.copy("x11191_0", "x11169")
      val x11127 = SRAM(size=16,name="x11127",banking = Strided(1)).wtPort(x11165.readPort).rdPort(x11127_x11127_dsp0_bank0_s).rdAddr(x11169(0)).wtAddr(x11156(0))
    }
    val x11154_0 = Pipeline(name="x11154_0",parent=x11192) { implicit CU => 
      val x11128 = ScalarBuffer(name="x11128").wtPort(x11048_x11048_dsp0_bank0_s)
      val x11149 = ScalarFIFO(size=1,name="x11149").wtPort(x10747_x10747_dsp0_bank0_s)
      val ctr102 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11144 = CounterChain(name = "x11144", ctr102).iter(1)
      Stage(operands=List(CU.load(x11149), CU.load(x11128)), op=FixMul, results=List(CU.scalarOut(x11126_x11153_s)))
    }
    val x11166_0 = Pipeline(name="x11166_0",parent=x11192) { implicit CU => 
      val x11129 = ScalarBuffer(name="x11129").wtPort(x11048_x11048_dsp0_bank0_s)
      val x11161 = ScalarFIFO(size=1,name="x11161").wtPort(x10747_x10747_dsp0_bank1_s)
      val ctr103 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11156 = CounterChain(name = "x11156", ctr103).iter(1)
      Stage(operands=List(CU.load(x11161), CU.load(x11129)), op=FixMul, results=List(CU.scalarOut(x11127_x11165_s)))
    }
    val x11191_0 = Pipeline(name="x11191_0",parent=x11192) { implicit CU => 
      val x11185 = CU.temp(None)
      val x11175 = ScalarFIFO(size=1,name="x11175").wtPort(x11127_x11127_dsp0_bank0_s)
      val x11177 = ScalarFIFO(size=1,name="x11177").wtPort(x11122_x11122_dsp0_bank0_s)
      val x11173 = ScalarFIFO(size=1,name="x11173").wtPort(x11126_x11126_dsp0_bank0_s)
      val ctr104 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11169 = CounterChain(name = "x11169", ctr104).iter(1)
      Stage(operands=List(CU.load(x11173), CU.load(x11175)), op=FixAdd, results=List(x11185))
      Stage(operands=List(x11185, CU.load(x11177)), op=FixAdd, results=List(CU.scalarOut(x11122_x11190_s)))
    }
    val x11261 = MetaPipeline(name="x11261",parent=x11282) { implicit CU => 
      val ctr105 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x11194 = CounterChain(name = "x11194", ctr105).iter(8)
    }
    val x11195_dsp0_bank0 = MemoryPipeline(name="x11195_dsp0_bank0",parent="x11261") { implicit CU => 
      val x11222 = ScalarFIFO(size=1,name="x11222").wtPort(x11195_x11222_s)
      val x11238 = CounterChain.copy("x11260_0", "x11238")
      val x11213 = CounterChain.copy("x11223_0", "x11213")
      val x11195 = SRAM(size=16,name="x11195",banking = Strided(1)).wtPort(x11222.readPort).rdPort(x11195_x11195_dsp0_bank0_s).rdAddr(x11238(0)).wtAddr(x11213(0))
    }
    val x11196_dsp0_bank0 = MemoryPipeline(name="x11196_dsp0_bank0",parent="x11261") { implicit CU => 
      val x11234 = ScalarFIFO(size=1,name="x11234").wtPort(x11196_x11234_s)
      val x11238 = CounterChain.copy("x11260_0", "x11238")
      val x11225 = CounterChain.copy("x11235_0", "x11225")
      val x11196 = SRAM(size=16,name="x11196",banking = Strided(1)).wtPort(x11234.readPort).rdPort(x11196_x11196_dsp0_bank0_s).rdAddr(x11238(0)).wtAddr(x11225(0))
    }
    val x11223_0 = Pipeline(name="x11223_0",parent=x11261) { implicit CU => 
      val x11197 = ScalarBuffer(name="x11197").wtPort(x11049_x11049_dsp0_bank0_s)
      val x11218 = ScalarFIFO(size=1,name="x11218").wtPort(x10747_x10747_dsp1_bank0_s)
      val ctr106 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11213 = CounterChain(name = "x11213", ctr106).iter(1)
      Stage(operands=List(CU.load(x11218), CU.load(x11197)), op=FixMul, results=List(CU.scalarOut(x11195_x11222_s)))
    }
    val x11235_0 = Pipeline(name="x11235_0",parent=x11261) { implicit CU => 
      val x11198 = ScalarBuffer(name="x11198").wtPort(x11049_x11049_dsp0_bank0_s)
      val x11230 = ScalarFIFO(size=1,name="x11230").wtPort(x10747_x10747_dsp1_bank1_s)
      val ctr107 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11225 = CounterChain(name = "x11225", ctr107).iter(1)
      Stage(operands=List(CU.load(x11230), CU.load(x11198)), op=FixMul, results=List(CU.scalarOut(x11196_x11234_s)))
    }
    val x11260_0 = Pipeline(name="x11260_0",parent=x11261) { implicit CU => 
      val x11254 = CU.temp(None)
      val x11244 = ScalarFIFO(size=1,name="x11244").wtPort(x11196_x11196_dsp0_bank0_s)
      val x11246 = ScalarFIFO(size=1,name="x11246").wtPort(x11123_x11123_dsp0_bank0_s)
      val x11242 = ScalarFIFO(size=1,name="x11242").wtPort(x11195_x11195_dsp0_bank0_s)
      val ctr108 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11238 = CounterChain(name = "x11238", ctr108).iter(1)
      Stage(operands=List(CU.load(x11242), CU.load(x11244)), op=FixAdd, results=List(x11254))
      Stage(operands=List(x11254, CU.load(x11246)), op=FixAdd, results=List(CU.scalarOut(x11123_x11259_s)))
    }
    val x11271 = Pipeline(name="x11271",parent=x11282) { implicit CU => 
      val ctr109 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11264 = CounterChain(name = "x11264", ctr109).iter(16)
    }
    val x11280 = Pipeline(name="x11280",parent=x11282) { implicit CU => 
      val ctr110 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11273 = CounterChain(name = "x11273", ctr110).iter(16)
    }
    val x11305_0 = Pipeline(name="x11305_0",parent=x11306) { implicit CU => 
      val x11299 = CU.temp(None)
      val x11289 = ScalarFIFO(size=1,name="x11289").wtPort(x10744_x10744_dsp0_bank0_s).wtPort(x10744_x10744_dsp0_bank1_s)
      val x11291 = ScalarFIFO(size=1,name="x11291").wtPort(x10745_x10745_dsp0_bank0_s).wtPort(x10745_x10745_dsp0_bank1_s)
      val x11293 = ScalarFIFO(size=1,name="x11293").wtPort(x9610_x9610_dsp1_bank0_s)
      val ctr111 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr112 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11286 = CounterChain(name = "x11286", ctr111, ctr112).iter(64)
      Stage(operands=List(CU.load(x11289), CU.load(x11291)), op=FixAdd, results=List(x11299))
      Stage(operands=List(x11299, CU.load(x11293)), op=FixAdd, results=List(CU.scalarOut(x9610_x11304_s)))
    }
    val x11871 = MetaPipeline(name="x11871",parent=x12011) { implicit CU => 
      val ctr113 = Counter(min=Const(0), max=Const(64), step=Const(16), par=2) // Counter
      val x11308 = CounterChain(name = "x11308", ctr113).iter(2)
    }
    val x11309_dsp0_bank0 = MemoryPipeline(name="x11309_dsp0_bank0",parent="x11871") { implicit CU => 
      val b12267 = CU.temp(None)
      val b12291 = CU.temp(None)
      val x11598 = ScalarFIFO(size=1,name="x11598").wtPort(x11450_x11450_dsp1_bank0_s)
      val x11375 = CounterChain.copy("x11610", "x11375")
      val x11592 = CounterChain.copy("x11599", "x11592")
      val x11851 = CounterChain.copy("x11870_0", "x11851")
      val x11309 = SRAM(size=1024,name="x11309",banking = Strided(1)).wtPort(x11598.readPort).rdPort(x11309_x11309_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x11375(0)), Const(16)), op=FixMul, results=List(b12267))
      WAStage(operands=List(b12267, CU.ctr(x11592(0))), op=FixAdd, results=List(x11309.writeAddr))
      RAStage(operands=List(CU.ctr(x11851(0)), Const(16)), op=FixMul, results=List(b12291))
      RAStage(operands=List(b12291, CU.ctr(x11851(1))), op=FixAdd, results=List(x11309.readAddr))
    }
    val x11309_dsp0_bank1 = MemoryPipeline(name="x11309_dsp0_bank1",parent="x11871") { implicit CU => 
      val b12269 = CU.temp(None)
      val b12291 = CU.temp(None)
      val x11607 = ScalarFIFO(size=1,name="x11607").wtPort(x11451_x11451_dsp1_bank0_s)
      val x11375 = CounterChain.copy("x11610", "x11375")
      val x11851 = CounterChain.copy("x11870_0", "x11851")
      val x11601 = CounterChain.copy("x11608", "x11601")
      val x11309 = SRAM(size=1024,name="x11309",banking = Strided(1)).wtPort(x11607.readPort).rdPort(x11309_x11309_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x11375(0)), Const(16)), op=FixMul, results=List(b12269))
      WAStage(operands=List(b12269, CU.ctr(x11601(0))), op=FixAdd, results=List(x11309.writeAddr))
      RAStage(operands=List(CU.ctr(x11851(0)), Const(16)), op=FixMul, results=List(b12291))
      RAStage(operands=List(b12291, CU.ctr(x11851(1))), op=FixAdd, results=List(x11309.readAddr))
    }
    val x11310_dsp0_bank0 = MemoryPipeline(name="x11310_dsp0_bank0",parent="x11871") { implicit CU => 
      val b12287 = CU.temp(None)
      val b12293 = CU.temp(None)
      val x11835 = ScalarFIFO(size=1,name="x11835").wtPort(x11687_x11687_dsp1_bank0_s)
      val x11851 = CounterChain.copy("x11870_0", "x11851")
      val x11829 = CounterChain.copy("x11836", "x11829")
      val x11612 = CounterChain.copy("x11847", "x11612")
      val x11310 = SRAM(size=1024,name="x11310",banking = Strided(1)).wtPort(x11835.readPort).rdPort(x11310_x11310_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x11612(0)), Const(16)), op=FixMul, results=List(b12287))
      WAStage(operands=List(b12287, CU.ctr(x11829(0))), op=FixAdd, results=List(x11310.writeAddr))
      RAStage(operands=List(CU.ctr(x11851(0)), Const(16)), op=FixMul, results=List(b12293))
      RAStage(operands=List(b12293, CU.ctr(x11851(1))), op=FixAdd, results=List(x11310.readAddr))
    }
    val x11310_dsp0_bank1 = MemoryPipeline(name="x11310_dsp0_bank1",parent="x11871") { implicit CU => 
      val b12293 = CU.temp(None)
      val b12289 = CU.temp(None)
      val x11844 = ScalarFIFO(size=1,name="x11844").wtPort(x11688_x11688_dsp1_bank0_s)
      val x11851 = CounterChain.copy("x11870_0", "x11851")
      val x11838 = CounterChain.copy("x11845", "x11838")
      val x11612 = CounterChain.copy("x11847", "x11612")
      val x11310 = SRAM(size=1024,name="x11310",banking = Strided(1)).wtPort(x11844.readPort).rdPort(x11310_x11310_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x11612(0)), Const(16)), op=FixMul, results=List(b12289))
      WAStage(operands=List(b12289, CU.ctr(x11838(0))), op=FixAdd, results=List(x11310.writeAddr))
      RAStage(operands=List(CU.ctr(x11851(0)), Const(16)), op=FixMul, results=List(b12293))
      RAStage(operands=List(b12293, CU.ctr(x11851(1))), op=FixAdd, results=List(x11310.readAddr))
    }
    val x11311_dsp0_bank0 = MemoryPipeline(name="x11311_dsp0_bank0",parent="x11871") { implicit CU => 
      val b12259 = CU.temp(None)
      val b12243 = CU.temp(None)
      val x11340 = ScalarFIFO(size=1,name="x11340").wtPort(x11316_x11332_data_s)
      val x11314 = CounterChain.copy("x11342", "x11314")
      val x11453 = CounterChain.copy("x11520", "x11453")
      val x11472 = CounterChain.copy("x11482_0", "x11472")
      val x11334 = CounterChain.copy("x11341", "x11334")
      val x11311 = SRAM(size=256,name="x11311",banking = Strided(1)).wtPort(x11340.readPort).rdPort(x11311_x11311_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x11314(0)), Const(16)), op=FixMul, results=List(b12243))
      WAStage(operands=List(b12243, CU.ctr(x11334(0))), op=FixAdd, results=List(x11311.writeAddr))
      RAStage(operands=List(CU.ctr(x11453(0)), Const(16)), op=FixMul, results=List(b12259))
      RAStage(operands=List(b12259, CU.ctr(x11472(0))), op=FixAdd, results=List(x11311.readAddr))
    }
    val x11311_dsp0_bank1 = MemoryPipeline(name="x11311_dsp0_bank1",parent="x11871") { implicit CU => 
      val b12243 = CU.temp(None)
      val b12261 = CU.temp(None)
      val x11340 = ScalarFIFO(size=1,name="x11340").wtPort(x11316_x11332_data_s)
      val x11314 = CounterChain.copy("x11342", "x11314")
      val x11453 = CounterChain.copy("x11520", "x11453")
      val x11484 = CounterChain.copy("x11494_0", "x11484")
      val x11334 = CounterChain.copy("x11341", "x11334")
      val x11311 = SRAM(size=256,name="x11311",banking = Strided(1)).wtPort(x11340.readPort).rdPort(x11311_x11311_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x11314(0)), Const(16)), op=FixMul, results=List(b12243))
      WAStage(operands=List(b12243, CU.ctr(x11334(0))), op=FixAdd, results=List(x11311.writeAddr))
      RAStage(operands=List(CU.ctr(x11453(0)), Const(16)), op=FixMul, results=List(b12261))
      RAStage(operands=List(b12261, CU.ctr(x11484(0))), op=FixAdd, results=List(x11311.readAddr))
    }
    val x11311_dsp1_bank0 = MemoryPipeline(name="x11311_dsp1_bank0",parent="x11871") { implicit CU => 
      val b12263 = CU.temp(None)
      val b12243 = CU.temp(None)
      val x11340 = ScalarFIFO(size=1,name="x11340").wtPort(x11316_x11332_data_s)
      val x11314 = CounterChain.copy("x11342", "x11314")
      val x11541 = CounterChain.copy("x11551_0", "x11541")
      val x11522 = CounterChain.copy("x11589", "x11522")
      val x11334 = CounterChain.copy("x11341", "x11334")
      val x11311 = SRAM(size=256,name="x11311",banking = Strided(1)).wtPort(x11340.readPort).rdPort(x11311_x11311_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x11314(0)), Const(16)), op=FixMul, results=List(b12243))
      WAStage(operands=List(b12243, CU.ctr(x11334(0))), op=FixAdd, results=List(x11311.writeAddr))
      RAStage(operands=List(CU.ctr(x11522(0)), Const(16)), op=FixMul, results=List(b12263))
      RAStage(operands=List(b12263, CU.ctr(x11541(0))), op=FixAdd, results=List(x11311.readAddr))
    }
    val x11311_dsp1_bank1 = MemoryPipeline(name="x11311_dsp1_bank1",parent="x11871") { implicit CU => 
      val b12243 = CU.temp(None)
      val b12265 = CU.temp(None)
      val x11340 = ScalarFIFO(size=1,name="x11340").wtPort(x11316_x11332_data_s)
      val x11314 = CounterChain.copy("x11342", "x11314")
      val x11553 = CounterChain.copy("x11563_0", "x11553")
      val x11522 = CounterChain.copy("x11589", "x11522")
      val x11334 = CounterChain.copy("x11341", "x11334")
      val x11311 = SRAM(size=256,name="x11311",banking = Strided(1)).wtPort(x11340.readPort).rdPort(x11311_x11311_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x11314(0)), Const(16)), op=FixMul, results=List(b12243))
      WAStage(operands=List(b12243, CU.ctr(x11334(0))), op=FixAdd, results=List(x11311.writeAddr))
      RAStage(operands=List(CU.ctr(x11522(0)), Const(16)), op=FixMul, results=List(b12265))
      RAStage(operands=List(b12265, CU.ctr(x11553(0))), op=FixAdd, results=List(x11311.readAddr))
    }
    val x11312_dsp0_bank0 = MemoryPipeline(name="x11312_dsp0_bank0",parent="x11871") { implicit CU => 
      val b12249 = CU.temp(None)
      val b12279 = CU.temp(None)
      val x11370 = ScalarFIFO(size=1,name="x11370").wtPort(x11346_x11362_data_s)
      val x11364 = CounterChain.copy("x11371", "x11364")
      val x11709 = CounterChain.copy("x11719_0", "x11709")
      val x11690 = CounterChain.copy("x11757", "x11690")
      val x11344 = CounterChain.copy("x11372", "x11344")
      val x11312 = SRAM(size=256,name="x11312",banking = Strided(1)).wtPort(x11370.readPort).rdPort(x11312_x11312_dsp0_bank0_s)
      WAStage(operands=List(CU.ctr(x11344(0)), Const(16)), op=FixMul, results=List(b12249))
      WAStage(operands=List(b12249, CU.ctr(x11364(0))), op=FixAdd, results=List(x11312.writeAddr))
      RAStage(operands=List(CU.ctr(x11690(0)), Const(16)), op=FixMul, results=List(b12279))
      RAStage(operands=List(b12279, CU.ctr(x11709(0))), op=FixAdd, results=List(x11312.readAddr))
    }
    val x11312_dsp0_bank1 = MemoryPipeline(name="x11312_dsp0_bank1",parent="x11871") { implicit CU => 
      val b12249 = CU.temp(None)
      val b12281 = CU.temp(None)
      val x11370 = ScalarFIFO(size=1,name="x11370").wtPort(x11346_x11362_data_s)
      val x11364 = CounterChain.copy("x11371", "x11364")
      val x11721 = CounterChain.copy("x11731_0", "x11721")
      val x11690 = CounterChain.copy("x11757", "x11690")
      val x11344 = CounterChain.copy("x11372", "x11344")
      val x11312 = SRAM(size=256,name="x11312",banking = Strided(1)).wtPort(x11370.readPort).rdPort(x11312_x11312_dsp0_bank1_s)
      WAStage(operands=List(CU.ctr(x11344(0)), Const(16)), op=FixMul, results=List(b12249))
      WAStage(operands=List(b12249, CU.ctr(x11364(0))), op=FixAdd, results=List(x11312.writeAddr))
      RAStage(operands=List(CU.ctr(x11690(0)), Const(16)), op=FixMul, results=List(b12281))
      RAStage(operands=List(b12281, CU.ctr(x11721(0))), op=FixAdd, results=List(x11312.readAddr))
    }
    val x11312_dsp1_bank0 = MemoryPipeline(name="x11312_dsp1_bank0",parent="x11871") { implicit CU => 
      val b12249 = CU.temp(None)
      val b12283 = CU.temp(None)
      val x11370 = ScalarFIFO(size=1,name="x11370").wtPort(x11346_x11362_data_s)
      val x11364 = CounterChain.copy("x11371", "x11364")
      val x11759 = CounterChain.copy("x11826", "x11759")
      val x11344 = CounterChain.copy("x11372", "x11344")
      val x11778 = CounterChain.copy("x11788_0", "x11778")
      val x11312 = SRAM(size=256,name="x11312",banking = Strided(1)).wtPort(x11370.readPort).rdPort(x11312_x11312_dsp1_bank0_s)
      WAStage(operands=List(CU.ctr(x11344(0)), Const(16)), op=FixMul, results=List(b12249))
      WAStage(operands=List(b12249, CU.ctr(x11364(0))), op=FixAdd, results=List(x11312.writeAddr))
      RAStage(operands=List(CU.ctr(x11759(0)), Const(16)), op=FixMul, results=List(b12283))
      RAStage(operands=List(b12283, CU.ctr(x11778(0))), op=FixAdd, results=List(x11312.readAddr))
    }
    val x11312_dsp1_bank1 = MemoryPipeline(name="x11312_dsp1_bank1",parent="x11871") { implicit CU => 
      val b12249 = CU.temp(None)
      val b12285 = CU.temp(None)
      val x11370 = ScalarFIFO(size=1,name="x11370").wtPort(x11346_x11362_data_s)
      val x11364 = CounterChain.copy("x11371", "x11364")
      val x11790 = CounterChain.copy("x11800_0", "x11790")
      val x11759 = CounterChain.copy("x11826", "x11759")
      val x11344 = CounterChain.copy("x11372", "x11344")
      val x11312 = SRAM(size=256,name="x11312",banking = Strided(1)).wtPort(x11370.readPort).rdPort(x11312_x11312_dsp1_bank1_s)
      WAStage(operands=List(CU.ctr(x11344(0)), Const(16)), op=FixMul, results=List(b12249))
      WAStage(operands=List(b12249, CU.ctr(x11364(0))), op=FixAdd, results=List(x11312.writeAddr))
      RAStage(operands=List(CU.ctr(x11759(0)), Const(16)), op=FixMul, results=List(b12285))
      RAStage(operands=List(b12285, CU.ctr(x11790(0))), op=FixAdd, results=List(x11312.readAddr))
    }
    val x11342 = StreamController(name="x11342",parent=x11871) { implicit CU => 
      val ctr114 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11314 = CounterChain(name = "x11314", ctr114).iter(16)
    }
    val x11331_0 = Pipeline(name="x11331_0",parent=x11342) { implicit CU => 
      val x11322 = CU.temp(None)
      val x11317 = CU.temp(None)
      val x11321 = CU.temp(None)
      val x11319 = CU.temp(None)
      val x11324 = ScalarBuffer(name="x11324").wtPort(b_dram_da)
      val x11308 = CounterChain.copy("x11871", "x11308")
      val x11314 = CounterChain.copy("x11342", "x11314")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x11331_unit = CounterChain(name = "x11331_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x11308(0)), CU.ctr(x11314(0))), op=FixAdd, results=List(x11317))
      Stage(operands=List(x11317, Const(6)), op=FixSla, results=List(x11319))
      Stage(operands=List(x11319, CU.ctr(x9607(0))), op=FixAdd, results=List(x11321))
      Stage(operands=List(x11321, Const(3)), op=FixSla, results=List(x11322))
      Stage(operands=List(x11322, CU.load(x11324)), op=FixAdd, results=List(CU.scalarOut(x11315_b12239_x11330_b12241_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11315_b12240_x11330_b12242_s)))
    }
    val x11332 = MemoryController(name="x11332",parent=x11342,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x11315_b12240 = ScalarFIFO(size=1,name="size").wtPort(x11315_b12240_x11330_b12242_s)
      val x11315_b12239 = ScalarFIFO(size=1,name="offset").wtPort(x11315_b12239_x11330_b12241_s)
      CU.newSout("data", x11316_x11332_data_s)
    }
    val x11341 = Pipeline(name="x11341",parent=x11342) { implicit CU => 
      val ctr115 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11334 = CounterChain(name = "x11334", ctr115).iter(1)
    }
    val x11372 = StreamController(name="x11372",parent=x11871) { implicit CU => 
      val ctr116 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11344 = CounterChain(name = "x11344", ctr116).iter(16)
    }
    val x11361_0 = Pipeline(name="x11361_0",parent=x11372) { implicit CU => 
      val x11349 = CU.temp(None)
      val x11347 = CU.temp(None)
      val x11351 = CU.temp(None)
      val x11352 = CU.temp(None)
      val x11354 = ScalarBuffer(name="x11354").wtPort(b_dram_da)
      val x11308 = CounterChain.copy("x11871", "x11308")
      val x11344 = CounterChain.copy("x11372", "x11344")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x11361_unit = CounterChain(name = "x11361_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x11308(0)), CU.ctr(x11344(0))), op=FixAdd, results=List(x11347))
      Stage(operands=List(x11347, Const(6)), op=FixSla, results=List(x11349))
      Stage(operands=List(x11349, CU.ctr(x9607(0))), op=FixAdd, results=List(x11351))
      Stage(operands=List(x11351, Const(3)), op=FixSla, results=List(x11352))
      Stage(operands=List(x11352, CU.load(x11354)), op=FixAdd, results=List(CU.scalarOut(x11345_b12245_x11360_b12247_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11345_b12246_x11360_b12248_s)))
    }
    val x11362 = MemoryController(name="x11362",parent=x11372,offchip=b_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x11345_b12245 = ScalarFIFO(size=1,name="offset").wtPort(x11345_b12245_x11360_b12247_s)
      val x11345_b12246 = ScalarFIFO(size=1,name="size").wtPort(x11345_b12246_x11360_b12248_s)
      CU.newSout("data", x11346_x11362_data_s)
    }
    val x11371 = Pipeline(name="x11371",parent=x11372) { implicit CU => 
      val ctr117 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11364 = CounterChain(name = "x11364", ctr117).iter(1)
    }
    val x11610 = MetaPipeline(name="x11610",parent=x11871) { implicit CU => 
      val ctr118 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x11375 = CounterChain(name = "x11375", ctr118).iter(32)
    }
    val x11376_dsp0_bank0 = MemoryPipeline(name="x11376_dsp0_bank0",parent="x11610") { implicit CU => 
      val x11414 = ScalarFIFO(size=1,name="x11414").wtPort(x11388_x11405_data_s)
      val x11453 = CounterChain.copy("x11520", "x11453")
      val x11407 = CounterChain.copy("x11415", "x11407")
      val x11376 = SRAM(size=16,name="x11376",banking = Strided(1)).wtPort(x11414.readPort).rdPort(x11376_x11376_dsp0_bank0_s).rdAddr(x11453(0)).rdAddr(x11453(0)).wtAddr(x11407(0))
    }
    val x11377_dsp0_bank0 = MemoryPipeline(name="x11377_dsp0_bank0",parent="x11610") { implicit CU => 
      val x11446 = ScalarFIFO(size=1,name="x11446").wtPort(x11420_x11437_data_s)
      val x11439 = CounterChain.copy("x11447", "x11439")
      val x11522 = CounterChain.copy("x11589", "x11522")
      val x11377 = SRAM(size=16,name="x11377",banking = Strided(1)).wtPort(x11446.readPort).rdPort(x11377_x11377_dsp0_bank0_s).rdAddr(x11522(0)).rdAddr(x11522(0)).wtAddr(x11439(0))
    }
    val x11416 = StreamController(name="x11416",parent=x11610) { implicit CU => 
      val ctr119 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x11386 = CounterChain(name = "x11386", ctr119).iter(1)
    }
    val x11404_0 = Pipeline(name="x11404_0",parent=x11416) { implicit CU => 
      val x11391 = CU.temp(None)
      val x11389 = CU.temp(None)
      val x11393 = CU.temp(None)
      val x11394 = CU.temp(None)
      val x11396 = ScalarBuffer(name="x11396").wtPort(a_dram_da)
      val x11404_unit = CounterChain(name = "x11404_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x11375 = CounterChain.copy("x11610", "x11375")
      val x11386 = CounterChain.copy("x11416", "x11386")
      val x11308 = CounterChain.copy("x11871", "x11308")
      Stage(operands=List(CU.ctr(x11375(0)), CU.ctr(x11386(0))), op=FixAdd, results=List(x11389))
      Stage(operands=List(x11389, Const(6)), op=FixSla, results=List(x11391))
      Stage(operands=List(x11391, CU.ctr(x11308(0))), op=FixAdd, results=List(x11393))
      Stage(operands=List(x11393, Const(3)), op=FixSla, results=List(x11394))
      Stage(operands=List(x11394, CU.load(x11396)), op=FixAdd, results=List(CU.scalarOut(x11387_b12251_x11403_b12253_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11387_b12252_x11403_b12254_s)))
    }
    val x11405 = MemoryController(name="x11405",parent=x11416,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x11387_b12252 = ScalarFIFO(size=1,name="size").wtPort(x11387_b12252_x11403_b12254_s)
      val x11387_b12251 = ScalarFIFO(size=1,name="offset").wtPort(x11387_b12251_x11403_b12253_s)
      CU.newSout("data", x11388_x11405_data_s)
    }
    val x11415 = Pipeline(name="x11415",parent=x11416) { implicit CU => 
      val ctr120 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11407 = CounterChain(name = "x11407", ctr120).iter(16)
    }
    val x11448 = StreamController(name="x11448",parent=x11610) { implicit CU => 
      val ctr121 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x11418 = CounterChain(name = "x11418", ctr121).iter(1)
    }
    val x11436_0 = Pipeline(name="x11436_0",parent=x11448) { implicit CU => 
      val x11425 = CU.temp(None)
      val x11426 = CU.temp(None)
      val x11421 = CU.temp(None)
      val x11423 = CU.temp(None)
      val x11428 = ScalarBuffer(name="x11428").wtPort(a_dram_da)
      val x11375 = CounterChain.copy("x11610", "x11375")
      val x11308 = CounterChain.copy("x11871", "x11308")
      val x11436_unit = CounterChain(name = "x11436_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x11418 = CounterChain.copy("x11448", "x11418")
      Stage(operands=List(CU.ctr(x11375(0)), CU.ctr(x11418(0))), op=FixAdd, results=List(x11421))
      Stage(operands=List(x11421, Const(6)), op=FixSla, results=List(x11423))
      Stage(operands=List(x11423, CU.ctr(x11308(0))), op=FixAdd, results=List(x11425))
      Stage(operands=List(x11425, Const(3)), op=FixSla, results=List(x11426))
      Stage(operands=List(x11426, CU.load(x11428)), op=FixAdd, results=List(CU.scalarOut(x11419_b12255_x11435_b12257_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11419_b12256_x11435_b12258_s)))
    }
    val x11437 = MemoryController(name="x11437",parent=x11448,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x11419_b12256 = ScalarFIFO(size=1,name="size").wtPort(x11419_b12256_x11435_b12258_s)
      val x11419_b12255 = ScalarFIFO(size=1,name="offset").wtPort(x11419_b12255_x11435_b12257_s)
      CU.newSout("data", x11420_x11437_data_s)
    }
    val x11447 = Pipeline(name="x11447",parent=x11448) { implicit CU => 
      val ctr122 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11439 = CounterChain(name = "x11439", ctr122).iter(16)
    }
    val x11450_dsp0_bank0 = MemoryPipeline(name="x11450_dsp0_bank0",parent="x11520") { implicit CU => 
      val x11518 = ScalarFIFO(size=1,name="x11518").wtPort(x11450_x11518_s)
      val x11497 = CounterChain.copy("x11519_0", "x11497")
      val x11450 = SRAM(size=16,name="x11450",banking = Strided(1)).wtPort(x11518.readPort).rdPort(x11450_x11450_dsp0_bank0_s).rdAddr(x11497(0)).wtAddr(x11497(0))
    }
    val x11450_dsp1_bank0 = MemoryPipeline(name="x11450_dsp1_bank0",parent="x11520") { implicit CU => 
      val x11518 = ScalarFIFO(size=1,name="x11518").wtPort(x11450_x11518_s)
      val x11592 = CounterChain.copy("x11599", "x11592")
      val x11497 = CounterChain.copy("x11519_0", "x11497")
      val x11450 = SRAM(size=16,name="x11450",banking = Strided(1)).wtPort(x11518.readPort).rdPort(x11450_x11450_dsp1_bank0_s).rdAddr(x11592(0)).wtAddr(x11497(0))
    }
    val x11451_dsp0_bank0 = MemoryPipeline(name="x11451_dsp0_bank0",parent="x11589") { implicit CU => 
      val x11587 = ScalarFIFO(size=1,name="x11587").wtPort(x11451_x11587_s)
      val x11566 = CounterChain.copy("x11588_0", "x11566")
      val x11451 = SRAM(size=16,name="x11451",banking = Strided(1)).wtPort(x11587.readPort).rdPort(x11451_x11451_dsp0_bank0_s).rdAddr(x11566(0)).wtAddr(x11566(0))
    }
    val x11451_dsp1_bank0 = MemoryPipeline(name="x11451_dsp1_bank0",parent="x11589") { implicit CU => 
      val x11587 = ScalarFIFO(size=1,name="x11587").wtPort(x11451_x11587_s)
      val x11566 = CounterChain.copy("x11588_0", "x11566")
      val x11601 = CounterChain.copy("x11608", "x11601")
      val x11451 = SRAM(size=16,name="x11451",banking = Strided(1)).wtPort(x11587.readPort).rdPort(x11451_x11451_dsp1_bank0_s).rdAddr(x11601(0)).wtAddr(x11566(0))
    }
    val x11520 = MetaPipeline(name="x11520",parent=x11610) { implicit CU => 
      val ctr123 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x11453 = CounterChain(name = "x11453", ctr123).iter(8)
    }
    val x11454_dsp0_bank0 = MemoryPipeline(name="x11454_dsp0_bank0",parent="x11520") { implicit CU => 
      val x11481 = ScalarFIFO(size=1,name="x11481").wtPort(x11454_x11481_s)
      val x11497 = CounterChain.copy("x11519_0", "x11497")
      val x11472 = CounterChain.copy("x11482_0", "x11472")
      val x11454 = SRAM(size=16,name="x11454",banking = Strided(1)).wtPort(x11481.readPort).rdPort(x11454_x11454_dsp0_bank0_s).rdAddr(x11497(0)).wtAddr(x11472(0))
    }
    val x11455_dsp0_bank0 = MemoryPipeline(name="x11455_dsp0_bank0",parent="x11520") { implicit CU => 
      val x11493 = ScalarFIFO(size=1,name="x11493").wtPort(x11455_x11493_s)
      val x11484 = CounterChain.copy("x11494_0", "x11484")
      val x11497 = CounterChain.copy("x11519_0", "x11497")
      val x11455 = SRAM(size=16,name="x11455",banking = Strided(1)).wtPort(x11493.readPort).rdPort(x11455_x11455_dsp0_bank0_s).rdAddr(x11497(0)).wtAddr(x11484(0))
    }
    val x11482_0 = Pipeline(name="x11482_0",parent=x11520) { implicit CU => 
      val x11456 = ScalarBuffer(name="x11456").wtPort(x11376_x11376_dsp0_bank0_s)
      val x11477 = ScalarFIFO(size=1,name="x11477").wtPort(x11311_x11311_dsp0_bank0_s)
      val ctr124 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11472 = CounterChain(name = "x11472", ctr124).iter(1)
      Stage(operands=List(CU.load(x11477), CU.load(x11456)), op=FixMul, results=List(CU.scalarOut(x11454_x11481_s)))
    }
    val x11494_0 = Pipeline(name="x11494_0",parent=x11520) { implicit CU => 
      val x11489 = ScalarFIFO(size=1,name="x11489").wtPort(x11311_x11311_dsp0_bank1_s)
      val x11457 = ScalarBuffer(name="x11457").wtPort(x11376_x11376_dsp0_bank0_s)
      val ctr125 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11484 = CounterChain(name = "x11484", ctr125).iter(1)
      Stage(operands=List(CU.load(x11489), CU.load(x11457)), op=FixMul, results=List(CU.scalarOut(x11455_x11493_s)))
    }
    val x11519_0 = Pipeline(name="x11519_0",parent=x11520) { implicit CU => 
      val x11513 = CU.temp(None)
      val x11501 = ScalarFIFO(size=1,name="x11501").wtPort(x11454_x11454_dsp0_bank0_s)
      val x11503 = ScalarFIFO(size=1,name="x11503").wtPort(x11455_x11455_dsp0_bank0_s)
      val x11505 = ScalarFIFO(size=1,name="x11505").wtPort(x11450_x11450_dsp0_bank0_s)
      val ctr126 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11497 = CounterChain(name = "x11497", ctr126).iter(1)
      Stage(operands=List(CU.load(x11501), CU.load(x11503)), op=FixAdd, results=List(x11513))
      Stage(operands=List(x11513, CU.load(x11505)), op=FixAdd, results=List(CU.scalarOut(x11450_x11518_s)))
    }
    val x11589 = MetaPipeline(name="x11589",parent=x11610) { implicit CU => 
      val ctr127 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x11522 = CounterChain(name = "x11522", ctr127).iter(8)
    }
    val x11523_dsp0_bank0 = MemoryPipeline(name="x11523_dsp0_bank0",parent="x11589") { implicit CU => 
      val x11550 = ScalarFIFO(size=1,name="x11550").wtPort(x11523_x11550_s)
      val x11541 = CounterChain.copy("x11551_0", "x11541")
      val x11566 = CounterChain.copy("x11588_0", "x11566")
      val x11523 = SRAM(size=16,name="x11523",banking = Strided(1)).wtPort(x11550.readPort).rdPort(x11523_x11523_dsp0_bank0_s).rdAddr(x11566(0)).wtAddr(x11541(0))
    }
    val x11524_dsp0_bank0 = MemoryPipeline(name="x11524_dsp0_bank0",parent="x11589") { implicit CU => 
      val x11562 = ScalarFIFO(size=1,name="x11562").wtPort(x11524_x11562_s)
      val x11553 = CounterChain.copy("x11563_0", "x11553")
      val x11566 = CounterChain.copy("x11588_0", "x11566")
      val x11524 = SRAM(size=16,name="x11524",banking = Strided(1)).wtPort(x11562.readPort).rdPort(x11524_x11524_dsp0_bank0_s).rdAddr(x11566(0)).wtAddr(x11553(0))
    }
    val x11551_0 = Pipeline(name="x11551_0",parent=x11589) { implicit CU => 
      val x11525 = ScalarBuffer(name="x11525").wtPort(x11377_x11377_dsp0_bank0_s)
      val x11546 = ScalarFIFO(size=1,name="x11546").wtPort(x11311_x11311_dsp1_bank0_s)
      val ctr128 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11541 = CounterChain(name = "x11541", ctr128).iter(1)
      Stage(operands=List(CU.load(x11546), CU.load(x11525)), op=FixMul, results=List(CU.scalarOut(x11523_x11550_s)))
    }
    val x11563_0 = Pipeline(name="x11563_0",parent=x11589) { implicit CU => 
      val x11558 = ScalarFIFO(size=1,name="x11558").wtPort(x11311_x11311_dsp1_bank1_s)
      val x11526 = ScalarBuffer(name="x11526").wtPort(x11377_x11377_dsp0_bank0_s)
      val ctr129 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11553 = CounterChain(name = "x11553", ctr129).iter(1)
      Stage(operands=List(CU.load(x11558), CU.load(x11526)), op=FixMul, results=List(CU.scalarOut(x11524_x11562_s)))
    }
    val x11588_0 = Pipeline(name="x11588_0",parent=x11589) { implicit CU => 
      val x11582 = CU.temp(None)
      val x11570 = ScalarFIFO(size=1,name="x11570").wtPort(x11523_x11523_dsp0_bank0_s)
      val x11572 = ScalarFIFO(size=1,name="x11572").wtPort(x11524_x11524_dsp0_bank0_s)
      val x11574 = ScalarFIFO(size=1,name="x11574").wtPort(x11451_x11451_dsp0_bank0_s)
      val ctr130 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11566 = CounterChain(name = "x11566", ctr130).iter(1)
      Stage(operands=List(CU.load(x11570), CU.load(x11572)), op=FixAdd, results=List(x11582))
      Stage(operands=List(x11582, CU.load(x11574)), op=FixAdd, results=List(CU.scalarOut(x11451_x11587_s)))
    }
    val x11599 = Pipeline(name="x11599",parent=x11610) { implicit CU => 
      val ctr131 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11592 = CounterChain(name = "x11592", ctr131).iter(16)
    }
    val x11608 = Pipeline(name="x11608",parent=x11610) { implicit CU => 
      val ctr132 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11601 = CounterChain(name = "x11601", ctr132).iter(16)
    }
    val x11847 = MetaPipeline(name="x11847",parent=x11871) { implicit CU => 
      val ctr133 = Counter(min=Const(0), max=Const(64), step=Const(1), par=2) // Counter
      val x11612 = CounterChain(name = "x11612", ctr133).iter(32)
    }
    val x11613_dsp0_bank0 = MemoryPipeline(name="x11613_dsp0_bank0",parent="x11847") { implicit CU => 
      val x11651 = ScalarFIFO(size=1,name="x11651").wtPort(x11625_x11642_data_s)
      val x11690 = CounterChain.copy("x11757", "x11690")
      val x11644 = CounterChain.copy("x11652", "x11644")
      val x11613 = SRAM(size=16,name="x11613",banking = Strided(1)).wtPort(x11651.readPort).rdPort(x11613_x11613_dsp0_bank0_s).rdAddr(x11690(0)).rdAddr(x11690(0)).wtAddr(x11644(0))
    }
    val x11614_dsp0_bank0 = MemoryPipeline(name="x11614_dsp0_bank0",parent="x11847") { implicit CU => 
      val x11683 = ScalarFIFO(size=1,name="x11683").wtPort(x11657_x11674_data_s)
      val x11759 = CounterChain.copy("x11826", "x11759")
      val x11676 = CounterChain.copy("x11684", "x11676")
      val x11614 = SRAM(size=16,name="x11614",banking = Strided(1)).wtPort(x11683.readPort).rdPort(x11614_x11614_dsp0_bank0_s).rdAddr(x11759(0)).rdAddr(x11759(0)).wtAddr(x11676(0))
    }
    val x11653 = StreamController(name="x11653",parent=x11847) { implicit CU => 
      val ctr134 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x11623 = CounterChain(name = "x11623", ctr134).iter(1)
    }
    val x11641_0 = Pipeline(name="x11641_0",parent=x11653) { implicit CU => 
      val x11626 = CU.temp(None)
      val x11631 = CU.temp(None)
      val x11630 = CU.temp(None)
      val x11628 = CU.temp(None)
      val x11633 = ScalarBuffer(name="x11633").wtPort(a_dram_da)
      val x11641_unit = CounterChain(name = "x11641_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x11308 = CounterChain.copy("x11871", "x11308")
      val x11623 = CounterChain.copy("x11653", "x11623")
      val x11612 = CounterChain.copy("x11847", "x11612")
      Stage(operands=List(CU.ctr(x11612(0)), CU.ctr(x11623(0))), op=FixAdd, results=List(x11626))
      Stage(operands=List(x11626, Const(6)), op=FixSla, results=List(x11628))
      Stage(operands=List(x11628, CU.ctr(x11308(0))), op=FixAdd, results=List(x11630))
      Stage(operands=List(x11630, Const(3)), op=FixSla, results=List(x11631))
      Stage(operands=List(x11631, CU.load(x11633)), op=FixAdd, results=List(CU.scalarOut(x11624_b12271_x11640_b12273_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11624_b12272_x11640_b12274_s)))
    }
    val x11642 = MemoryController(name="x11642",parent=x11653,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x11624_b12272 = ScalarFIFO(size=1,name="size").wtPort(x11624_b12272_x11640_b12274_s)
      val x11624_b12271 = ScalarFIFO(size=1,name="offset").wtPort(x11624_b12271_x11640_b12273_s)
      CU.newSout("data", x11625_x11642_data_s)
    }
    val x11652 = Pipeline(name="x11652",parent=x11653) { implicit CU => 
      val ctr135 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11644 = CounterChain(name = "x11644", ctr135).iter(16)
    }
    val x11685 = StreamController(name="x11685",parent=x11847) { implicit CU => 
      val ctr136 = Counter(min=Const(0), max=Const(1), step=Const(1), par=1) // Counter
      val x11655 = CounterChain(name = "x11655", ctr136).iter(1)
    }
    val x11673_0 = Pipeline(name="x11673_0",parent=x11685) { implicit CU => 
      val x11658 = CU.temp(None)
      val x11663 = CU.temp(None)
      val x11662 = CU.temp(None)
      val x11660 = CU.temp(None)
      val x11665 = ScalarBuffer(name="x11665").wtPort(a_dram_da)
      val x11673_unit = CounterChain(name = "x11673_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      val x11655 = CounterChain.copy("x11685", "x11655")
      val x11308 = CounterChain.copy("x11871", "x11308")
      val x11612 = CounterChain.copy("x11847", "x11612")
      Stage(operands=List(CU.ctr(x11612(0)), CU.ctr(x11655(0))), op=FixAdd, results=List(x11658))
      Stage(operands=List(x11658, Const(6)), op=FixSla, results=List(x11660))
      Stage(operands=List(x11660, CU.ctr(x11308(0))), op=FixAdd, results=List(x11662))
      Stage(operands=List(x11662, Const(3)), op=FixSla, results=List(x11663))
      Stage(operands=List(x11663, CU.load(x11665)), op=FixAdd, results=List(CU.scalarOut(x11656_b12275_x11672_b12277_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11656_b12276_x11672_b12278_s)))
    }
    val x11674 = MemoryController(name="x11674",parent=x11685,offchip=a_dram_oc, mctpe=TileLoad) { implicit CU => 
      val x11656_b12276 = ScalarFIFO(size=1,name="size").wtPort(x11656_b12276_x11672_b12278_s)
      val x11656_b12275 = ScalarFIFO(size=1,name="offset").wtPort(x11656_b12275_x11672_b12277_s)
      CU.newSout("data", x11657_x11674_data_s)
    }
    val x11684 = Pipeline(name="x11684",parent=x11685) { implicit CU => 
      val ctr137 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11676 = CounterChain(name = "x11676", ctr137).iter(16)
    }
    val x11687_dsp0_bank0 = MemoryPipeline(name="x11687_dsp0_bank0",parent="x11757") { implicit CU => 
      val x11755 = ScalarFIFO(size=1,name="x11755").wtPort(x11687_x11755_s)
      val x11734 = CounterChain.copy("x11756_0", "x11734")
      val x11687 = SRAM(size=16,name="x11687",banking = Strided(1)).wtPort(x11755.readPort).rdPort(x11687_x11687_dsp0_bank0_s).rdAddr(x11734(0)).wtAddr(x11734(0))
    }
    val x11687_dsp1_bank0 = MemoryPipeline(name="x11687_dsp1_bank0",parent="x11757") { implicit CU => 
      val x11755 = ScalarFIFO(size=1,name="x11755").wtPort(x11687_x11755_s)
      val x11829 = CounterChain.copy("x11836", "x11829")
      val x11734 = CounterChain.copy("x11756_0", "x11734")
      val x11687 = SRAM(size=16,name="x11687",banking = Strided(1)).wtPort(x11755.readPort).rdPort(x11687_x11687_dsp1_bank0_s).rdAddr(x11829(0)).wtAddr(x11734(0))
    }
    val x11688_dsp0_bank0 = MemoryPipeline(name="x11688_dsp0_bank0",parent="x11826") { implicit CU => 
      val x11824 = ScalarFIFO(size=1,name="x11824").wtPort(x11688_x11824_s)
      val x11803 = CounterChain.copy("x11825_0", "x11803")
      val x11688 = SRAM(size=16,name="x11688",banking = Strided(1)).wtPort(x11824.readPort).rdPort(x11688_x11688_dsp0_bank0_s).rdAddr(x11803(0)).wtAddr(x11803(0))
    }
    val x11688_dsp1_bank0 = MemoryPipeline(name="x11688_dsp1_bank0",parent="x11826") { implicit CU => 
      val x11824 = ScalarFIFO(size=1,name="x11824").wtPort(x11688_x11824_s)
      val x11803 = CounterChain.copy("x11825_0", "x11803")
      val x11838 = CounterChain.copy("x11845", "x11838")
      val x11688 = SRAM(size=16,name="x11688",banking = Strided(1)).wtPort(x11824.readPort).rdPort(x11688_x11688_dsp1_bank0_s).rdAddr(x11838(0)).wtAddr(x11803(0))
    }
    val x11757 = MetaPipeline(name="x11757",parent=x11847) { implicit CU => 
      val ctr138 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x11690 = CounterChain(name = "x11690", ctr138).iter(8)
    }
    val x11691_dsp0_bank0 = MemoryPipeline(name="x11691_dsp0_bank0",parent="x11757") { implicit CU => 
      val x11718 = ScalarFIFO(size=1,name="x11718").wtPort(x11691_x11718_s)
      val x11709 = CounterChain.copy("x11719_0", "x11709")
      val x11734 = CounterChain.copy("x11756_0", "x11734")
      val x11691 = SRAM(size=16,name="x11691",banking = Strided(1)).wtPort(x11718.readPort).rdPort(x11691_x11691_dsp0_bank0_s).rdAddr(x11734(0)).wtAddr(x11709(0))
    }
    val x11692_dsp0_bank0 = MemoryPipeline(name="x11692_dsp0_bank0",parent="x11757") { implicit CU => 
      val x11730 = ScalarFIFO(size=1,name="x11730").wtPort(x11692_x11730_s)
      val x11721 = CounterChain.copy("x11731_0", "x11721")
      val x11734 = CounterChain.copy("x11756_0", "x11734")
      val x11692 = SRAM(size=16,name="x11692",banking = Strided(1)).wtPort(x11730.readPort).rdPort(x11692_x11692_dsp0_bank0_s).rdAddr(x11734(0)).wtAddr(x11721(0))
    }
    val x11719_0 = Pipeline(name="x11719_0",parent=x11757) { implicit CU => 
      val x11693 = ScalarBuffer(name="x11693").wtPort(x11613_x11613_dsp0_bank0_s)
      val x11714 = ScalarFIFO(size=1,name="x11714").wtPort(x11312_x11312_dsp0_bank0_s)
      val ctr139 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11709 = CounterChain(name = "x11709", ctr139).iter(1)
      Stage(operands=List(CU.load(x11714), CU.load(x11693)), op=FixMul, results=List(CU.scalarOut(x11691_x11718_s)))
    }
    val x11731_0 = Pipeline(name="x11731_0",parent=x11757) { implicit CU => 
      val x11694 = ScalarBuffer(name="x11694").wtPort(x11613_x11613_dsp0_bank0_s)
      val x11726 = ScalarFIFO(size=1,name="x11726").wtPort(x11312_x11312_dsp0_bank1_s)
      val ctr140 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11721 = CounterChain(name = "x11721", ctr140).iter(1)
      Stage(operands=List(CU.load(x11726), CU.load(x11694)), op=FixMul, results=List(CU.scalarOut(x11692_x11730_s)))
    }
    val x11756_0 = Pipeline(name="x11756_0",parent=x11757) { implicit CU => 
      val x11750 = CU.temp(None)
      val x11740 = ScalarFIFO(size=1,name="x11740").wtPort(x11692_x11692_dsp0_bank0_s)
      val x11742 = ScalarFIFO(size=1,name="x11742").wtPort(x11687_x11687_dsp0_bank0_s)
      val x11738 = ScalarFIFO(size=1,name="x11738").wtPort(x11691_x11691_dsp0_bank0_s)
      val ctr141 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11734 = CounterChain(name = "x11734", ctr141).iter(1)
      Stage(operands=List(CU.load(x11738), CU.load(x11740)), op=FixAdd, results=List(x11750))
      Stage(operands=List(x11750, CU.load(x11742)), op=FixAdd, results=List(CU.scalarOut(x11687_x11755_s)))
    }
    val x11826 = MetaPipeline(name="x11826",parent=x11847) { implicit CU => 
      val ctr142 = Counter(min=Const(0), max=Const(16), step=Const(1), par=2) // Counter
      val x11759 = CounterChain(name = "x11759", ctr142).iter(8)
    }
    val x11760_dsp0_bank0 = MemoryPipeline(name="x11760_dsp0_bank0",parent="x11826") { implicit CU => 
      val x11787 = ScalarFIFO(size=1,name="x11787").wtPort(x11760_x11787_s)
      val x11803 = CounterChain.copy("x11825_0", "x11803")
      val x11778 = CounterChain.copy("x11788_0", "x11778")
      val x11760 = SRAM(size=16,name="x11760",banking = Strided(1)).wtPort(x11787.readPort).rdPort(x11760_x11760_dsp0_bank0_s).rdAddr(x11803(0)).wtAddr(x11778(0))
    }
    val x11761_dsp0_bank0 = MemoryPipeline(name="x11761_dsp0_bank0",parent="x11826") { implicit CU => 
      val x11799 = ScalarFIFO(size=1,name="x11799").wtPort(x11761_x11799_s)
      val x11790 = CounterChain.copy("x11800_0", "x11790")
      val x11803 = CounterChain.copy("x11825_0", "x11803")
      val x11761 = SRAM(size=16,name="x11761",banking = Strided(1)).wtPort(x11799.readPort).rdPort(x11761_x11761_dsp0_bank0_s).rdAddr(x11803(0)).wtAddr(x11790(0))
    }
    val x11788_0 = Pipeline(name="x11788_0",parent=x11826) { implicit CU => 
      val x11783 = ScalarFIFO(size=1,name="x11783").wtPort(x11312_x11312_dsp1_bank0_s)
      val x11762 = ScalarBuffer(name="x11762").wtPort(x11614_x11614_dsp0_bank0_s)
      val ctr143 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11778 = CounterChain(name = "x11778", ctr143).iter(1)
      Stage(operands=List(CU.load(x11783), CU.load(x11762)), op=FixMul, results=List(CU.scalarOut(x11760_x11787_s)))
    }
    val x11800_0 = Pipeline(name="x11800_0",parent=x11826) { implicit CU => 
      val x11763 = ScalarBuffer(name="x11763").wtPort(x11614_x11614_dsp0_bank0_s)
      val x11795 = ScalarFIFO(size=1,name="x11795").wtPort(x11312_x11312_dsp1_bank1_s)
      val ctr144 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11790 = CounterChain(name = "x11790", ctr144).iter(1)
      Stage(operands=List(CU.load(x11795), CU.load(x11763)), op=FixMul, results=List(CU.scalarOut(x11761_x11799_s)))
    }
    val x11825_0 = Pipeline(name="x11825_0",parent=x11826) { implicit CU => 
      val x11819 = CU.temp(None)
      val x11809 = ScalarFIFO(size=1,name="x11809").wtPort(x11761_x11761_dsp0_bank0_s)
      val x11811 = ScalarFIFO(size=1,name="x11811").wtPort(x11688_x11688_dsp0_bank0_s)
      val x11807 = ScalarFIFO(size=1,name="x11807").wtPort(x11760_x11760_dsp0_bank0_s)
      val ctr145 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11803 = CounterChain(name = "x11803", ctr145).iter(1)
      Stage(operands=List(CU.load(x11807), CU.load(x11809)), op=FixAdd, results=List(x11819))
      Stage(operands=List(x11819, CU.load(x11811)), op=FixAdd, results=List(CU.scalarOut(x11688_x11824_s)))
    }
    val x11836 = Pipeline(name="x11836",parent=x11847) { implicit CU => 
      val ctr146 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11829 = CounterChain(name = "x11829", ctr146).iter(16)
    }
    val x11845 = Pipeline(name="x11845",parent=x11847) { implicit CU => 
      val ctr147 = Counter(min=Const(0), max=Const(16), step=Const(1), par=1) // Counter
      val x11838 = CounterChain(name = "x11838", ctr147).iter(16)
    }
    val x11870_0 = Pipeline(name="x11870_0",parent=x11871) { implicit CU => 
      val x11864 = CU.temp(None)
      val x11854 = ScalarFIFO(size=1,name="x11854").wtPort(x11309_x11309_dsp0_bank0_s).wtPort(x11309_x11309_dsp0_bank1_s)
      val x11856 = ScalarFIFO(size=1,name="x11856").wtPort(x11310_x11310_dsp0_bank0_s).wtPort(x11310_x11310_dsp0_bank1_s)
      val x11858 = ScalarFIFO(size=1,name="x11858").wtPort(x9611_x9611_dsp1_bank0_s)
      val ctr148 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val ctr149 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11851 = CounterChain(name = "x11851", ctr148, ctr149).iter(64)
      Stage(operands=List(CU.load(x11854), CU.load(x11856)), op=FixAdd, results=List(x11864))
      Stage(operands=List(x11864, CU.load(x11858)), op=FixAdd, results=List(CU.scalarOut(x9611_x11869_s)))
    }
    val x11913 = StreamController(name="x11913",parent=x12011) { implicit CU => 
      val ctr150 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x11883 = CounterChain(name = "x11883", ctr150).iter(64)
    }
    val x11899_0 = Pipeline(name="x11899_0",parent=x11913) { implicit CU => 
      val x11888 = CU.temp(None)
      val x11891 = CU.temp(None)
      val x11890 = CU.temp(None)
      val x11893 = ScalarBuffer(name="x11893").wtPort(c_dram_da)
      val x11883 = CounterChain.copy("x11913", "x11883")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x11899_unit = CounterChain(name = "x11899_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x11883(0)), Const(6)), op=FixSla, results=List(x11888))
      Stage(operands=List(x11888, CU.ctr(x9607(0))), op=FixAdd, results=List(x11890))
      Stage(operands=List(x11890, Const(3)), op=FixSla, results=List(x11891))
      Stage(operands=List(x11891, CU.load(x11893)), op=FixAdd, results=List(CU.scalarOut(x11884_b12299_x11898_b12301_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11884_b12300_x11898_b12302_s)))
    }
    val x11908 = Pipeline(name="x11908",parent=x11913) { implicit CU => 
      val ctr151 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11901 = CounterChain(name = "x11901", ctr151).iter(1)
    }
    val x11909 = MemoryController(name="x11909",parent=x11913,offchip=c_dram_oc, mctpe=TileStore) { implicit CU => 
      val x11884_b12299 = ScalarFIFO(size=1,name="offset").wtPort(x11884_b12299_x11898_b12301_s)
      val x11885 = ScalarFIFO(size=1,name="data").wtPort(x9608_x9608_dsp0_bank0_s)
      val x11884_b12300 = ScalarFIFO(size=1,name="size").wtPort(x11884_b12300_x11898_b12302_s)
    }
    val x11945 = StreamController(name="x11945",parent=x12011) { implicit CU => 
      val ctr152 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x11915 = CounterChain(name = "x11915", ctr152).iter(64)
    }
    val x11931_0 = Pipeline(name="x11931_0",parent=x11945) { implicit CU => 
      val x11920 = CU.temp(None)
      val x11922 = CU.temp(None)
      val x11923 = CU.temp(None)
      val x11925 = ScalarBuffer(name="x11925").wtPort(c_dram_da)
      val x11915 = CounterChain.copy("x11945", "x11915")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x11931_unit = CounterChain(name = "x11931_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x11915(0)), Const(6)), op=FixSla, results=List(x11920))
      Stage(operands=List(x11920, CU.ctr(x9607(0))), op=FixAdd, results=List(x11922))
      Stage(operands=List(x11922, Const(3)), op=FixSla, results=List(x11923))
      Stage(operands=List(x11923, CU.load(x11925)), op=FixAdd, results=List(CU.scalarOut(x11916_b12305_x11930_b12307_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11916_b12306_x11930_b12308_s)))
    }
    val x11940 = Pipeline(name="x11940",parent=x11945) { implicit CU => 
      val ctr153 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11933 = CounterChain(name = "x11933", ctr153).iter(1)
    }
    val x11941 = MemoryController(name="x11941",parent=x11945,offchip=c_dram_oc, mctpe=TileStore) { implicit CU => 
      val x11917 = ScalarFIFO(size=1,name="data").wtPort(x9609_x9609_dsp0_bank0_s)
      val x11916_b12305 = ScalarFIFO(size=1,name="offset").wtPort(x11916_b12305_x11930_b12307_s)
      val x11916_b12306 = ScalarFIFO(size=1,name="size").wtPort(x11916_b12306_x11930_b12308_s)
    }
    val x11977 = StreamController(name="x11977",parent=x12011) { implicit CU => 
      val ctr154 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x11947 = CounterChain(name = "x11947", ctr154).iter(64)
    }
    val x11963_0 = Pipeline(name="x11963_0",parent=x11977) { implicit CU => 
      val x11954 = CU.temp(None)
      val x11952 = CU.temp(None)
      val x11955 = CU.temp(None)
      val x11957 = ScalarBuffer(name="x11957").wtPort(c_dram_da)
      val x11947 = CounterChain.copy("x11977", "x11947")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x11963_unit = CounterChain(name = "x11963_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x11947(0)), Const(6)), op=FixSla, results=List(x11952))
      Stage(operands=List(x11952, CU.ctr(x9607(0))), op=FixAdd, results=List(x11954))
      Stage(operands=List(x11954, Const(3)), op=FixSla, results=List(x11955))
      Stage(operands=List(x11955, CU.load(x11957)), op=FixAdd, results=List(CU.scalarOut(x11948_b12311_x11962_b12313_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11948_b12312_x11962_b12314_s)))
    }
    val x11972 = Pipeline(name="x11972",parent=x11977) { implicit CU => 
      val ctr155 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11965 = CounterChain(name = "x11965", ctr155).iter(1)
    }
    val x11973 = MemoryController(name="x11973",parent=x11977,offchip=c_dram_oc, mctpe=TileStore) { implicit CU => 
      val x11948_b12312 = ScalarFIFO(size=1,name="size").wtPort(x11948_b12312_x11962_b12314_s)
      val x11949 = ScalarFIFO(size=1,name="data").wtPort(x9610_x9610_dsp0_bank0_s)
      val x11948_b12311 = ScalarFIFO(size=1,name="offset").wtPort(x11948_b12311_x11962_b12313_s)
    }
    val x12009 = StreamController(name="x12009",parent=x12011) { implicit CU => 
      val ctr156 = Counter(min=Const(0), max=Const(64), step=Const(1), par=1) // Counter
      val x11979 = CounterChain(name = "x11979", ctr156).iter(64)
    }
    val x11995_0 = Pipeline(name="x11995_0",parent=x12009) { implicit CU => 
      val x11987 = CU.temp(None)
      val x11984 = CU.temp(None)
      val x11986 = CU.temp(None)
      val x11989 = ScalarBuffer(name="x11989").wtPort(c_dram_da)
      val x11979 = CounterChain.copy("x12009", "x11979")
      val x9607 = CounterChain.copy("x12011", "x9607")
      val x11995_unit = CounterChain(name = "x11995_unit", Counter(Const(0), Const(1), Const(1), par=1)).iter(1l)
      Stage(operands=List(CU.ctr(x11979(0)), Const(6)), op=FixSla, results=List(x11984))
      Stage(operands=List(x11984, CU.ctr(x9607(0))), op=FixAdd, results=List(x11986))
      Stage(operands=List(x11986, Const(3)), op=FixSla, results=List(x11987))
      Stage(operands=List(x11987, CU.load(x11989)), op=FixAdd, results=List(CU.scalarOut(x11980_b12317_x11994_b12319_s)))
      Stage(operands=List(Const(128)), op=Bypass, results=List(CU.scalarOut(x11980_b12318_x11994_b12320_s)))
    }
    val x12004 = Pipeline(name="x12004",parent=x12009) { implicit CU => 
      val ctr157 = Counter(min=Const(0), max=Const(16), step=Const(1), par=16) // Counter
      val x11997 = CounterChain(name = "x11997", ctr157).iter(1)
    }
    val x12005 = MemoryController(name="x12005",parent=x12009,offchip=c_dram_oc, mctpe=TileStore) { implicit CU => 
      val x11980_b12317 = ScalarFIFO(size=1,name="offset").wtPort(x11980_b12317_x11994_b12319_s)
      val x11981 = ScalarFIFO(size=1,name="data").wtPort(x9611_x9611_dsp0_bank0_s)
      val x11980_b12318 = ScalarFIFO(size=1,name="size").wtPort(x11980_b12318_x11994_b12320_s)
    }
    
  }
}
