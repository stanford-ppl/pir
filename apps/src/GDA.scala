import pir.graph
import pir.graph._
import pir.codegen._
import pir.plasticine.config._
import pir.Design
import pir.util.enums._
import pir.util._
import pir.PIRApp

object GDA extends PIRApp {
  def main(args: String*)(top:Top) = {
    val x6095_b7208_x6106_b7210_s = Scalar("x6095_b7208_x6106_b7210")
    val x6530_b7303_x6541_b7305_s = Scalar("x6530_b7303_x6541_b7305")
    val x6651_x7040_x7081_v = Vector("x6651_x7040_x7081")
    val x5887_b7167_x5894_b7169_s = Scalar("x5887_b7167_x5894_b7169")
    val x5944_x6208_s = Scalar("x5944_x6208")
    val x6705_x6721_v = Vector("x6705_x6721")
    val x6800_x6827_v = Vector("x6800_x6827")
    val x5885_x6996_x7004_v = Vector("x5885_x6996_x7004")
    val x6065_x6070_s = Scalar("x6065_x6070")
    val x5980_x5987_s = Scalar("x5980_x5987")
    val x6648_x6741_x6746_v = Vector("x6648_x6741_x6746")
    val bus_7129_v = Vector("bus_7129")
    val bus_6660_s = Scalar("bus_6660")
    val x5929_x6806_x6816_v = Vector("x5929_x6806_x6816")
    val bus_7083_v = Vector("bus_7083")
    val x6846_x6870_x6875_v = Vector("x6846_x6870_x6875")
    val x5875_oc = OffChip("x5875")
    val x5886_x6995_x7004_v = Vector("x5886_x6995_x7004")
    val x5887_b7168_x5894_b7170_s = Scalar("x5887_b7168_x5894_b7170")
    val x5952_x5977_data_v = Vector("x5952_x5977_data")
    val x5979_x5985_s = Scalar("x5979_x5985")
    val bus_7037_v = Vector("bus_7037")
    val bus_6826_s = Scalar("bus_6826")
    val x6443_b7283_x6454_b7285_s = Scalar("x6443_b7283_x6454_b7285")
    val x6532_argin = ArgIn("x6532")
    val x5948_x6556_s = Scalar("x5948_x6556")
    val bus_6769_s = Scalar("bus_6769")
    val x5905_x5913_data_v = Vector("x5905_x5913_data")
    val x5931_x6900_x6910_v = Vector("x5931_x6900_x6910")
    val bus_6661_s = Scalar("bus_6661")
    val x6588_x6594_s = Scalar("x6588_x6594")
    val x5886_x6666_x6675_v = Vector("x5886_x6666_x6675")
    val x6473_b7293_x6497_b7301_s = Scalar("x6473_b7293_x6497_b7301")
    val x6617_b7322_x6628_b7324_s = Scalar("x6617_b7322_x6628_b7324")
    val bus_6759_s = Scalar("bus_6759")
    val x5951_b7177_x5975_b7185_s = Scalar("x5951_b7177_x5975_b7185")
    val bus_6867_s = Scalar("bus_6867")
    val bus_6727_s = Scalar("bus_6727")
    val x6270_x6282_data_v = Vector("x6270_x6282_data")
    val x6124_b7214_x6142_b7222_s = Scalar("x6124_b7214_x6142_b7222")
    val x5880_oc = OffChip("x5880")
    val x6127_argin = ArgIn("x6127")
    val x5949_x6643_s = Scalar("x5949_x6643")
    val x5886_x6760_x6769_v = Vector("x5886_x6760_x6769")
    val bus_7014_v = Vector("bus_7014")
    val x5932_x6947_x6957_v = Vector("x5932_x6947_x6957")
    val x5904_b7171_x5911_b7173_s = Scalar("x5904_b7171_x5911_b7173")
    val x6647_x6698_v = Vector("x6647_x6698")
    val x5904_b7172_x5911_b7174_s = Scalar("x5904_b7172_x5911_b7174")
    val x6659_x6693_x6699_v = Vector("x6659_x6693_x6699")
    val x5936_x6758_x6769_v = Vector("x5936_x6758_x6769")
    val x5885_x6808_x6816_v = Vector("x5885_x6808_x6816")
    val x6653_x6980_v = Vector("x6653_x6980")
    val x6415_x6422_s = Scalar("x6415_x6422")
    val x6301_argin = ArgIn("x6301")
    val x6846_x6871_x6875_v = Vector("x6846_x6871_x6875")
    val x6066_x6072_s = Scalar("x6066_x6072")
    val x6038_b7198_x6062_b7206_s = Scalar("x6038_b7198_x6062_b7206")
    val x6654_x7023_x7028_v = Vector("x6654_x7023_x7028")
    val x6212_b7236_x6236_b7244_s = Scalar("x6212_b7236_x6236_b7244")
    val bus_6724_s = Scalar("bus_6724")
    val x5886_x6901_x6910_v = Vector("x5886_x6901_x6910")
    val x6560_b7310_x6584_b7318_s = Scalar("x6560_b7310_x6584_b7318")
    val x5886_x6854_x6863_v = Vector("x5886_x6854_x6863")
    val x6298_b7252_x6316_b7260_s = Scalar("x6298_b7252_x6316_b7260")
    val x6474_x6499_data_v = Vector("x6474_x6499_data")
    val x6067_x6074_s = Scalar("x6067_x6074")
    val x6799_x6815_v = Vector("x6799_x6815")
    val bus_6861_s = Scalar("bus_6861")
    val x6125_b7216_x6149_b7224_s = Scalar("x6125_b7216_x6149_b7224")
    val x6154_x6161_s = Scalar("x6154_x6161")
    val x6271_argin = ArgIn("x6271")
    val x6619_argin = ArgIn("x6619")
    val x6893_x6918_x6922_v = Vector("x6893_x6918_x6922")
    val x6940_x6956_v = Vector("x6940_x6956")
    val bus_6691_s = Scalar("bus_6691")
    val x5934_x6664_x6675_v = Vector("x5934_x6664_x6675")
    val bus_6729_s = Scalar("bus_6729")
    val bus_6835_s = Scalar("bus_6835")
    val x6125_b7215_x6149_b7223_s = Scalar("x6125_b7215_x6149_b7223")
    val x6239_x6244_s = Scalar("x6239_x6244")
    val x5879_oc = OffChip("x5879")
    val x6299_b7253_x6323_b7261_s = Scalar("x6299_b7253_x6323_b7261")
    val x6651_x6882_x6887_v = Vector("x6651_x6882_x6887")
    val x6650_x7039_x7081_v = Vector("x6650_x7039_x7081")
    val x6654_x7043_x7081_v = Vector("x6654_x7043_x7081")
    val x6008_b7189_x6019_b7191_s = Scalar("x6008_b7189_x6019_b7191")
    val x6799_x6823_x6828_v = Vector("x6799_x6823_x6828")
    val x6327_x6333_s = Scalar("x6327_x6333")
    val x6211_b7232_x6229_b7240_s = Scalar("x6211_b7232_x6229_b7240")
    val x6475_argin = ArgIn("x6475")
    val x6618_x6630_data_v = Vector("x6618_x6630_data")
    val x6650_x6835_x6840_v = Vector("x6650_x6835_x6840")
    val x6847_x6881_x6887_v = Vector("x6847_x6881_x6887")
    val bus_7106_v = Vector("bus_7106")
    val x6531_x6543_data_v = Vector("x6531_x6543_data")
    val x6649_x7038_x7081_v = Vector("x6649_x7038_x7081")
    val x6658_x6682_x6687_v = Vector("x6658_x6682_x6687")
    val bus_6696_s = Scalar("bus_6696")
    val x6652_x6929_x6934_v = Vector("x6652_x6929_x6934")
    val bus_6768_s = Scalar("bus_6768")
    val x5922_x7044_x7081_v = Vector("x5922_x7044_x7081")
    val x6184_argin = ArgIn("x6184")
    val x5930_x6853_x6863_v = Vector("x5930_x6853_x6863")
    val x6097_argin = ArgIn("x6097")
    val x5885_x6714_x6722_v = Vector("x5885_x6714_x6722")
    val x6651_x6886_v = Vector("x6651_x6886")
    val x6413_x6418_s = Scalar("x6413_x6418")
    val bus_6637_s = Scalar("bus_6637")
    val x5950_b7175_x5968_b7183_s = Scalar("x5950_b7175_x5968_b7183")
    val x6414_x6420_s = Scalar("x6414_x6420")
    val bus_6859_s = Scalar("bus_6859")
    val bus_6628_s = Scalar("bus_6628")
    val x5947_x6469_s = Scalar("x5947_x6469")
    val x6560_b7311_x6584_b7319_s = Scalar("x6560_b7311_x6584_b7319")
    val x5941_x6993_x7004_v = Vector("x5941_x6993_x7004")
    val x5927_x6712_x6722_v = Vector("x5927_x6712_x6722")
    val x6182_b7226_x6193_b7228_s = Scalar("x6182_b7226_x6193_b7228")
    val x5885_x6667_x6675_v = Vector("x5885_x6667_x6675")
    val x5872_argin = ArgIn("x5872")
    val x6652_x7041_x7081_v = Vector("x6652_x7041_x7081")
    val x6213_x6238_data_v = Vector("x6213_x6238_data")
    val x6587_x6592_s = Scalar("x6587_x6592")
    val x6847_x6874_v = Vector("x6847_x6874")
    val x5943_x6121_s = Scalar("x5943_x6121")
    val x6038_b7197_x6062_b7205_s = Scalar("x6038_b7197_x6062_b7205")
    val x6753_x6787_x6793_v = Vector("x6753_x6787_x6793")
    val bus_6625_s = Scalar("bus_6625")
    val x6500_x6505_s = Scalar("x6500_x6505")
    val bus_6702_s = Scalar("bus_6702")
    val x5926_x6665_x6675_v = Vector("x5926_x6665_x6675")
    val bus_6735_s = Scalar("bus_6735")
    val x6705_x6729_x6734_v = Vector("x6705_x6729_x6734")
    val x5946_x6382_s = Scalar("x5946_x6382")
    val x6752_x6777_x6781_v = Vector("x6752_x6777_x6781")
    val x6654_x7027_v = Vector("x6654_x7027")
    val x6649_x6792_v = Vector("x6649_x6792")
    val x6559_b7309_x6577_b7317_s = Scalar("x6559_b7309_x6577_b7317")
    val x6096_x6108_data_v = Vector("x6096_x6108_data")
    val x5939_x6899_x6910_v = Vector("x5939_x6899_x6910")
    val x6009_x6021_data_v = Vector("x6009_x6021_data")
    val x6358_argin = ArgIn("x6358")
    val bus_6630_s = Scalar("bus_6630")
    val bus_6762_s = Scalar("bus_6762")
    val x6212_b7235_x6236_b7243_s = Scalar("x6212_b7235_x6236_b7243")
    val x6473_b7291_x6497_b7299_s = Scalar("x6473_b7291_x6497_b7299")
    val x6387_x6412_data_v = Vector("x6387_x6412_data")
    val x6559_b7308_x6577_b7316_s = Scalar("x6559_b7308_x6577_b7316")
    val x6039_x6064_data_v = Vector("x6039_x6064_data")
    val x6653_x7042_x7081_v = Vector("x6653_x7042_x7081")
    val x5885_x6949_x6957_v = Vector("x5885_x6949_x6957")
    val x5885_x6855_x6863_v = Vector("x5885_x6855_x6863")
    val x6752_x6776_x6781_v = Vector("x6752_x6776_x6781")
    val x6385_b7270_x6403_b7278_s = Scalar("x6385_b7270_x6403_b7278")
    val x7086_b7428_x7096_b7430_s = Scalar("x7086_b7428_x7096_b7430")
    val bus_6694_s = Scalar("bus_6694")
    val bus_6627_s = Scalar("bus_6627")
    val bus_6790_s = Scalar("bus_6790")
    val x6894_x6928_x6934_v = Vector("x6894_x6928_x6934")
    val bus_6834_s = Scalar("bus_6834")
    val x6658_x6683_x6687_v = Vector("x6658_x6683_x6687")
    val x5885_x6761_x6769_v = Vector("x5885_x6761_x6769")
    val x6653_x6976_x6981_v = Vector("x6653_x6976_x6981")
    val x6649_x6788_x6793_v = Vector("x6649_x6788_x6793")
    val x6445_argin = ArgIn("x6445")
    val x6562_argin = ArgIn("x6562")
    val x6326_x6331_s = Scalar("x6326_x6331")
    val x6647_x7036_x7081_v = Vector("x6647_x7036_x7081")
    val bus_7060_v = Vector("bus_7060")
    val x6647_x6694_x6699_v = Vector("x6647_x6694_x6699")
    val x6037_b7195_x6055_b7203_s = Scalar("x6037_b7195_x6055_b7203")
    val x5953_argin = ArgIn("x5953")
    val x6659_x6686_v = Vector("x6659_x6686")
    val x6300_x6325_data_v = Vector("x6300_x6325_data")
    val x6473_b7292_x6497_b7300_s = Scalar("x6473_b7292_x6497_b7300")
    val bus_7175_v = Vector("bus_7175")
    val bus_6795_s = Scalar("bus_6795")
    val x5878_oc = OffChip("x5878")
    val x6941_x6968_v = Vector("x6941_x6968")
    val x6124_b7213_x6142_b7221_s = Scalar("x6124_b7213_x6142_b7221")
    val bus_7199_s = Scalar("bus_7199")
    val x6799_x6824_x6828_v = Vector("x6799_x6824_x6828")
    val x6010_argin = ArgIn("x6010")
    val x6472_b7290_x6490_b7298_s = Scalar("x6472_b7290_x6490_b7298")
    val x6940_x6965_x6969_v = Vector("x6940_x6965_x6969")
    val x6183_x6195_data_v = Vector("x6183_x6195_data")
    val x6560_b7312_x6584_b7320_s = Scalar("x6560_b7312_x6584_b7320")
    val x6357_x6369_data_v = Vector("x6357_x6369_data")
    val x5945_x6295_s = Scalar("x5945_x6295")
    val x6617_b7321_x6628_b7323_s = Scalar("x6617_b7321_x6628_b7323")
    val x6752_x6768_v = Vector("x6752_x6768")
    val x5938_x6852_x6863_v = Vector("x5938_x6852_x6863")
    val x6269_b7245_x6280_b7247_s = Scalar("x6269_b7245_x6280_b7247")
    val x5886_x6948_x6957_v = Vector("x5886_x6948_x6957")
    val x6356_b7265_x6367_b7267_s = Scalar("x6356_b7265_x6367_b7267")
    val x6385_b7271_x6403_b7279_s = Scalar("x6385_b7271_x6403_b7279")
    val x6386_b7273_x6410_b7281_s = Scalar("x6386_b7273_x6410_b7281")
    val bus_6669_s = Scalar("bus_6669")
    val x5906_argin = ArgIn("x5906")
    val bus_6823_s = Scalar("bus_6823")
    val x6212_b7234_x6236_b7242_s = Scalar("x6212_b7234_x6236_b7242")
    val bus_6663_s = Scalar("bus_6663")
    val x6095_b7207_x6106_b7209_s = Scalar("x6095_b7207_x6106_b7209")
    val x6502_x6509_s = Scalar("x6502_x6509")
    val x5928_x6759_x6769_v = Vector("x5928_x6759_x6769")
    val x5922_x7080_v = Vector("x5922_x7080")
    val x6444_x6456_data_v = Vector("x6444_x6456_data")
    val x5922_x7101_x7105_v = Vector("x5922_x7101_x7105")
    val bus_6825_s = Scalar("bus_6825")
    val x6706_x6733_v = Vector("x6706_x6733")
    val x6589_x6596_s = Scalar("x6589_x6596")
    val x6800_x6834_x6840_v = Vector("x6800_x6834_x6840")
    val x6443_b7284_x6454_b7286_s = Scalar("x6443_b7284_x6454_b7286")
    val x6472_b7289_x6490_b7297_s = Scalar("x6472_b7289_x6490_b7297")
    val x7086_b7427_x7096_b7429_s = Scalar("x7086_b7427_x7096_b7429")
    val x5888_x5896_data_v = Vector("x5888_x5896_data")
    val bus_6658_s = Scalar("bus_6658")
    val bus_6828_s = Scalar("bus_6828")
    val bus_6793_s = Scalar("bus_6793")
    val x6388_argin = ArgIn("x6388")
    val x6299_b7255_x6323_b7263_s = Scalar("x6299_b7255_x6323_b7263")
    val x6650_x6839_v = Vector("x6650_x6839")
    val x6008_b7188_x6019_b7190_s = Scalar("x6008_b7188_x6019_b7190")
    val x6846_x6862_v = Vector("x6846_x6862")
    val bus_6792_s = Scalar("bus_6792")
    val bus_6801_s = Scalar("bus_6801")
    val x6038_b7196_x6062_b7204_s = Scalar("x6038_b7196_x6062_b7204")
    val bus_6726_s = Scalar("bus_6726")
    val x7089_argin = ArgIn("x7089")
    val x5951_b7179_x5975_b7187_s = Scalar("x5951_b7179_x5975_b7187")
    val bus_7186_s = Scalar("bus_7186")
    val x6893_x6909_v = Vector("x6893_x6909")
    val bus_6736_s = Scalar("bus_6736")
    val x6648_x7037_x7081_v = Vector("x6648_x7037_x7081")
    val x6153_x6159_s = Scalar("x6153_x6159")
    val x6299_b7254_x6323_b7262_s = Scalar("x6299_b7254_x6323_b7262")
    val bus_6636_s = Scalar("bus_6636")
    val x6705_x6730_x6734_v = Vector("x6705_x6730_x6734")
    val x5950_b7176_x5968_b7184_s = Scalar("x5950_b7176_x5968_b7184")
    val x5937_x6805_x6816_v = Vector("x5937_x6805_x6816")
    val x6356_b7264_x6367_b7266_s = Scalar("x6356_b7264_x6367_b7266")
    val x6040_argin = ArgIn("x6040")
    val bus_6802_s = Scalar("bus_6802")
    val bus_6757_s = Scalar("bus_6757")
    val x5978_x5983_s = Scalar("x5978_x5983")
    val x6182_b7227_x6193_b7229_s = Scalar("x6182_b7227_x6193_b7229")
    val x6987_x7012_x7016_v = Vector("x6987_x7012_x7016")
    val x6037_b7194_x6055_b7202_s = Scalar("x6037_b7194_x6055_b7202")
    val x6241_x6248_s = Scalar("x6241_x6248")
    val bus_6858_s = Scalar("bus_6858")
    val bus_6703_s = Scalar("bus_6703")
    val x5877_oc = OffChip("x5877")
    val x6941_x6975_x6981_v = Vector("x6941_x6975_x6981")
    val x6652_x6933_v = Vector("x6652_x6933")
    val x6648_x6745_v = Vector("x6648_x6745")
    val x6753_x6780_v = Vector("x6753_x6780")
    val x5933_x6994_x7004_v = Vector("x5933_x6994_x7004")
    val x6987_x7003_v = Vector("x6987_x7003")
    val x5886_x6807_x6816_v = Vector("x5886_x6807_x6816")
    val x6658_x6674_v = Vector("x6658_x6674")
    val x6501_x6507_s = Scalar("x6501_x6507")
    val x5951_b7178_x5975_b7186_s = Scalar("x5951_b7178_x5975_b7186")
    val x5889_argin = ArgIn("x5889")
    val bus_6760_s = Scalar("bus_6760")
    val x6386_b7272_x6410_b7280_s = Scalar("x6386_b7272_x6410_b7280")
    val x6988_x7015_v = Vector("x6988_x7015")
    val bus_6856_s = Scalar("bus_6856")
    val x6211_b7233_x6229_b7241_s = Scalar("x6211_b7233_x6229_b7241")
    val x5935_x6711_x6722_v = Vector("x5935_x6711_x6722")
    val x5942_x6034_s = Scalar("x5942_x6034")
    val bus_6868_s = Scalar("bus_6868")
    val x6386_b7274_x6410_b7282_s = Scalar("x6386_b7274_x6410_b7282")
    val x5886_x6713_x6722_v = Vector("x5886_x6713_x6722")
    val x6987_x7011_x7016_v = Vector("x6987_x7011_x7016")
    val x6126_x6151_data_v = Vector("x6126_x6151_data")
    val x6706_x6740_x6746_v = Vector("x6706_x6740_x6746")
    val x6125_b7217_x6149_b7225_s = Scalar("x6125_b7217_x6149_b7225")
    val x6988_x7022_x7028_v = Vector("x6988_x7022_x7028")
    val x6530_b7302_x6541_b7304_s = Scalar("x6530_b7302_x6541_b7304")
    val bus_6670_s = Scalar("bus_6670")
    val x6240_x6246_s = Scalar("x6240_x6246")
    val x5885_x6902_x6910_v = Vector("x5885_x6902_x6910")
    val x6328_x6335_s = Scalar("x6328_x6335")
    val x6152_x6157_s = Scalar("x6152_x6157")
    val bus_6693_s = Scalar("bus_6693")
    val bus_7152_v = Vector("bus_7152")
    val x6298_b7251_x6316_b7259_s = Scalar("x6298_b7251_x6316_b7259")
    val x6940_x6964_x6969_v = Vector("x6940_x6964_x6969")
    val x5940_x6946_x6957_v = Vector("x5940_x6946_x6957")
    val bus_7191_s = Scalar("bus_7191")
    val x6894_x6921_v = Vector("x6894_x6921")
    val x6893_x6917_x6922_v = Vector("x6893_x6917_x6922")
    val x6214_argin = ArgIn("x6214")
    val x6269_b7246_x6280_b7248_s = Scalar("x6269_b7246_x6280_b7248")
    val x6561_x6586_data_v = Vector("x6561_x6586_data")
    val x7115 = Sequential(name="x7115",parent=top) { implicit CU => 
    }
    val x5885_dsp0 = MemoryPipeline(name="x5885_dsp0",parent="x7115") { implicit CU => 
      val x5901_x5901 =  VectorFIFO(size=1).wtPort(x5888_x5896_data_v)
      val x6990 = CounterChain.copy("x7004", "x6990")
      val x5898 = CounterChain.copy("x5902", "x5898")
      val x5885_x6996 =  SRAM(size=96,banking = Strided(1)).wtPort(x5901_x5901.readPort).rdPort(x5885_x6996_x7004_v).rdAddr(x6990(0)).wtAddr(x5898(0))
      var stage: List[Stage] = Nil
    }
    val x5885_dsp1 = MemoryPipeline(name="x5885_dsp1",parent="x7115") { implicit CU => 
      val x5901_x5901 =  VectorFIFO(size=1).wtPort(x5888_x5896_data_v)
      val x5898 = CounterChain.copy("x5902", "x5898")
      val x6943 = CounterChain.copy("x6957", "x6943")
      val x5885_x6949 =  SRAM(size=96,banking = Strided(1)).wtPort(x5901_x5901.readPort).rdPort(x5885_x6949_x6957_v).rdAddr(x6943(0)).wtAddr(x5898(0))
      var stage: List[Stage] = Nil
    }
    val x5885_dsp2 = MemoryPipeline(name="x5885_dsp2",parent="x7115") { implicit CU => 
      val x5901_x5901 =  VectorFIFO(size=1).wtPort(x5888_x5896_data_v)
      val x6896 = CounterChain.copy("x6910", "x6896")
      val x5898 = CounterChain.copy("x5902", "x5898")
      val x5885_x6902 =  SRAM(size=96,banking = Strided(1)).wtPort(x5901_x5901.readPort).rdPort(x5885_x6902_x6910_v).rdAddr(x6896(0)).wtAddr(x5898(0))
      var stage: List[Stage] = Nil
    }
    val x5885_dsp3 = MemoryPipeline(name="x5885_dsp3",parent="x7115") { implicit CU => 
      val x5901_x5901 =  VectorFIFO(size=1).wtPort(x5888_x5896_data_v)
      val x6849 = CounterChain.copy("x6863", "x6849")
      val x5898 = CounterChain.copy("x5902", "x5898")
      val x5885_x6855 =  SRAM(size=96,banking = Strided(1)).wtPort(x5901_x5901.readPort).rdPort(x5885_x6855_x6863_v).rdAddr(x6849(0)).wtAddr(x5898(0))
      var stage: List[Stage] = Nil
    }
    val x5885_dsp4 = MemoryPipeline(name="x5885_dsp4",parent="x7115") { implicit CU => 
      val x5901_x5901 =  VectorFIFO(size=1).wtPort(x5888_x5896_data_v)
      val x6802 = CounterChain.copy("x6816", "x6802")
      val x5898 = CounterChain.copy("x5902", "x5898")
      val x5885_x6808 =  SRAM(size=96,banking = Strided(1)).wtPort(x5901_x5901.readPort).rdPort(x5885_x6808_x6816_v).rdAddr(x6802(0)).wtAddr(x5898(0))
      var stage: List[Stage] = Nil
    }
    val x5885_dsp5 = MemoryPipeline(name="x5885_dsp5",parent="x7115") { implicit CU => 
      val x5901_x5901 =  VectorFIFO(size=1).wtPort(x5888_x5896_data_v)
      val x5898 = CounterChain.copy("x5902", "x5898")
      val x6755 = CounterChain.copy("x6769", "x6755")
      val x5885_x6761 =  SRAM(size=96,banking = Strided(1)).wtPort(x5901_x5901.readPort).rdPort(x5885_x6761_x6769_v).rdAddr(x6755(0)).wtAddr(x5898(0))
      var stage: List[Stage] = Nil
    }
    val x5885_dsp6 = MemoryPipeline(name="x5885_dsp6",parent="x7115") { implicit CU => 
      val x5901_x5901 =  VectorFIFO(size=1).wtPort(x5888_x5896_data_v)
      val x6708 = CounterChain.copy("x6722", "x6708")
      val x5898 = CounterChain.copy("x5902", "x5898")
      val x5885_x6714 =  SRAM(size=96,banking = Strided(1)).wtPort(x5901_x5901.readPort).rdPort(x5885_x6714_x6722_v).rdAddr(x6708(0)).wtAddr(x5898(0))
      var stage: List[Stage] = Nil
    }
    val x5885_dsp7 = MemoryPipeline(name="x5885_dsp7",parent="x7115") { implicit CU => 
      val x5901_x5901 =  VectorFIFO(size=1).wtPort(x5888_x5896_data_v)
      val x6661 = CounterChain.copy("x6675", "x6661")
      val x5898 = CounterChain.copy("x5902", "x5898")
      val x5885_x6667 =  SRAM(size=96,banking = Strided(1)).wtPort(x5901_x5901.readPort).rdPort(x5885_x6667_x6675_v).rdAddr(x6661(0)).wtAddr(x5898(0))
      var stage: List[Stage] = Nil
    }
    val x5886_dsp0 = MemoryPipeline(name="x5886_dsp0",parent="x7115") { implicit CU => 
      val x5918_x5918 =  VectorFIFO(size=1).wtPort(x5905_x5913_data_v)
      val x6990 = CounterChain.copy("x7004", "x6990")
      val x5915 = CounterChain.copy("x5919", "x5915")
      val x5886_x6995 =  SRAM(size=96,banking = Strided(1)).wtPort(x5918_x5918.readPort).rdPort(x5886_x6995_x7004_v).rdAddr(x6990(0)).wtAddr(x5915(0))
      var stage: List[Stage] = Nil
    }
    val x5886_dsp1 = MemoryPipeline(name="x5886_dsp1",parent="x7115") { implicit CU => 
      val x5918_x5918 =  VectorFIFO(size=1).wtPort(x5905_x5913_data_v)
      val x5915 = CounterChain.copy("x5919", "x5915")
      val x6943 = CounterChain.copy("x6957", "x6943")
      val x5886_x6948 =  SRAM(size=96,banking = Strided(1)).wtPort(x5918_x5918.readPort).rdPort(x5886_x6948_x6957_v).rdAddr(x6943(0)).wtAddr(x5915(0))
      var stage: List[Stage] = Nil
    }
    val x5886_dsp2 = MemoryPipeline(name="x5886_dsp2",parent="x7115") { implicit CU => 
      val x5918_x5918 =  VectorFIFO(size=1).wtPort(x5905_x5913_data_v)
      val x6896 = CounterChain.copy("x6910", "x6896")
      val x5915 = CounterChain.copy("x5919", "x5915")
      val x5886_x6901 =  SRAM(size=96,banking = Strided(1)).wtPort(x5918_x5918.readPort).rdPort(x5886_x6901_x6910_v).rdAddr(x6896(0)).wtAddr(x5915(0))
      var stage: List[Stage] = Nil
    }
    val x5886_dsp3 = MemoryPipeline(name="x5886_dsp3",parent="x7115") { implicit CU => 
      val x5918_x5918 =  VectorFIFO(size=1).wtPort(x5905_x5913_data_v)
      val x5915 = CounterChain.copy("x5919", "x5915")
      val x6849 = CounterChain.copy("x6863", "x6849")
      val x5886_x6854 =  SRAM(size=96,banking = Strided(1)).wtPort(x5918_x5918.readPort).rdPort(x5886_x6854_x6863_v).rdAddr(x6849(0)).wtAddr(x5915(0))
      var stage: List[Stage] = Nil
    }
    val x5886_dsp4 = MemoryPipeline(name="x5886_dsp4",parent="x7115") { implicit CU => 
      val x5918_x5918 =  VectorFIFO(size=1).wtPort(x5905_x5913_data_v)
      val x5915 = CounterChain.copy("x5919", "x5915")
      val x6802 = CounterChain.copy("x6816", "x6802")
      val x5886_x6807 =  SRAM(size=96,banking = Strided(1)).wtPort(x5918_x5918.readPort).rdPort(x5886_x6807_x6816_v).rdAddr(x6802(0)).wtAddr(x5915(0))
      var stage: List[Stage] = Nil
    }
    val x5886_dsp5 = MemoryPipeline(name="x5886_dsp5",parent="x7115") { implicit CU => 
      val x5918_x5918 =  VectorFIFO(size=1).wtPort(x5905_x5913_data_v)
      val x5915 = CounterChain.copy("x5919", "x5915")
      val x6755 = CounterChain.copy("x6769", "x6755")
      val x5886_x6760 =  SRAM(size=96,banking = Strided(1)).wtPort(x5918_x5918.readPort).rdPort(x5886_x6760_x6769_v).rdAddr(x6755(0)).wtAddr(x5915(0))
      var stage: List[Stage] = Nil
    }
    val x5886_dsp6 = MemoryPipeline(name="x5886_dsp6",parent="x7115") { implicit CU => 
      val x5918_x5918 =  VectorFIFO(size=1).wtPort(x5905_x5913_data_v)
      val x6708 = CounterChain.copy("x6722", "x6708")
      val x5915 = CounterChain.copy("x5919", "x5915")
      val x5886_x6713 =  SRAM(size=96,banking = Strided(1)).wtPort(x5918_x5918.readPort).rdPort(x5886_x6713_x6722_v).rdAddr(x6708(0)).wtAddr(x5915(0))
      var stage: List[Stage] = Nil
    }
    val x5886_dsp7 = MemoryPipeline(name="x5886_dsp7",parent="x7115") { implicit CU => 
      val x5918_x5918 =  VectorFIFO(size=1).wtPort(x5905_x5913_data_v)
      val x6661 = CounterChain.copy("x6675", "x6661")
      val x5915 = CounterChain.copy("x5919", "x5915")
      val x5886_x6666 =  SRAM(size=96,banking = Strided(1)).wtPort(x5918_x5918.readPort).rdPort(x5886_x6666_x6675_v).rdAddr(x6661(0)).wtAddr(x5915(0))
      var stage: List[Stage] = Nil
    }
    val x5903 = StreamController(name="x5903",parent=x7115) { implicit CU => 
    }
    val x5895_0 = Pipeline(name="x5895_0",parent=x5903) { implicit CU => 
      val x5889 =  ScalarBuffer().wtPort(x5889_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x5889)), op=FixAdd, results=List(CU.scalarOut(x5887_b7167_x5894_b7169_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5887_b7168_x5894_b7170_s)))
    }
    val x5896 = MemoryController(name="x5896",parent=x5903,offchip=x5878_oc, mctpe=TileLoad) { implicit CU => 
      val x5887_b7167_x5896 =  ScalarFIFO(name="offset",size=1).wtPort(x5887_b7167_x5894_b7169_s)
      val x5887_b7168_x5896 =  ScalarFIFO(name="size",size=1).wtPort(x5887_b7168_x5894_b7170_s)
      CU.newVout("data", x5888_x5896_data_v)
    }
    val x5902 = Pipeline(name="x5902",parent=x5903) { implicit CU => 
      val ctr1 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5898 = CounterChain(name = "x5898", ctr1).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5920 = StreamController(name="x5920",parent=x7115) { implicit CU => 
    }
    val x5912_0 = Pipeline(name="x5912_0",parent=x5920) { implicit CU => 
      val x5906 =  ScalarBuffer().wtPort(x5906_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(0), CU.load(x5906)), op=FixAdd, results=List(CU.scalarOut(x5904_b7171_x5911_b7173_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x5904_b7172_x5911_b7174_s)))
    }
    val x5913 = MemoryController(name="x5913",parent=x5920,offchip=x5879_oc, mctpe=TileLoad) { implicit CU => 
      val x5904_b7171_x5913 =  ScalarFIFO(name="offset",size=1).wtPort(x5904_b7171_x5911_b7173_s)
      val x5904_b7172_x5913 =  ScalarFIFO(name="size",size=1).wtPort(x5904_b7172_x5911_b7174_s)
      CU.newVout("data", x5905_x5913_data_v)
    }
    val x5919 = Pipeline(name="x5919",parent=x5920) { implicit CU => 
      val ctr2 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x5915 = CounterChain(name = "x5915", ctr2).iter(6)
      var stage: List[Stage] = Nil
    }
    val x5922_dsp0 = MemoryPipeline(name="x5922_dsp0",parent="x7083") { implicit CU => 
      val b7431 = CU.temp
      val b7425 = CU.temp
      val x7080_x7080 =  VectorFIFO(size=1).wtPort(x5922_x7080_v)
      val x7034 = CounterChain.copy("x7081", "x7034")
      val x7085 = CounterChain.copy("x7114", "x7085")
      val x7099 = CounterChain.copy("x7105", "x7099")
      val x5922_x7101 =  SRAM(size=9216,banking = Strided(1)).wtPort(x7080_x7080.readPort).rdPort(x5922_x7101_x7105_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7425))
      WAStage(operands=List(b7425, CU.ctr(x7034(1))), op=FixAdd, results=List(x5922_x7101.writeAddr))
      RAStage(operands=List(CU.ctr(x7085(0)), Const(96)), op=FixMul, results=List(b7431))
      RAStage(operands=List(b7431, CU.ctr(x7099(0))), op=FixAdd, results=List(x5922_x7101.readAddr))
    }
    val x5922_dsp1 = MemoryPipeline(name="x5922_dsp1",parent="x7083") { implicit CU => 
      val b7423 = CU.temp
      val b7425 = CU.temp
      val x7080_x7080 =  VectorFIFO(size=1).wtPort(x5922_x7080_v)
      val x7034 = CounterChain.copy("x7081", "x7034")
      val x5922_x7044 =  SRAM(size=9216,banking = NoBanking()).wtPort(x7080_x7080.readPort).rdPort(x5922_x7044_x7081_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7425))
      WAStage(operands=List(b7425, CU.ctr(x7034(1))), op=FixAdd, results=List(x5922_x7044.writeAddr))
      RAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7423))
      RAStage(operands=List(b7423, CU.ctr(x7034(1))), op=FixAdd, results=List(x5922_x7044.readAddr))
    }
    val x7083 = MetaPipeline(name="x7083",parent=x7115) { implicit CU => 
      val x5872_x5923 =  ScalarBuffer().wtPort(x5872_argin)
      val ctr3 = Counter(min=Const(0), max=x5872_x5923.load, step=Const(20), par=8) // Counter
      val x5925 = CounterChain(name = "x5925", ctr3).iter(2250)
    }
    val x5926_dsp0 = MemoryPipeline(name="x5926_dsp0",parent="x7083") { implicit CU => 
      val x5978_x5992 =  ScalarBuffer().wtPort(x5978_x5983_s)
      val x6002_x6002 =  VectorFIFO(size=1).wtPort(x5952_x5977_data_v)
      val x5991 = CounterChain.copy("x6003", "x5991")
      val x6657 = CounterChain.copy("x6701", "x6657")
      val x5926_x6665 =  SRAM(size=20,banking = Strided(1)).wtPort(x6002_x6002.readPort).rdPort(x5926_x6665_x6675_v).rdAddr(x6657(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x5991(0)), CU.load(x5978_x5992)), op=FixSub, results=List(x5926_x6665.writeAddr))
    }
    val x5927_dsp0 = MemoryPipeline(name="x5927_dsp0",parent="x7083") { implicit CU => 
      val x6065_x6079 =  ScalarBuffer().wtPort(x6065_x6070_s)
      val x6089_x6089 =  VectorFIFO(size=1).wtPort(x6039_x6064_data_v)
      val x6078 = CounterChain.copy("x6090", "x6078")
      val x6704 = CounterChain.copy("x6748", "x6704")
      val x5927_x6712 =  SRAM(size=20,banking = Strided(1)).wtPort(x6089_x6089.readPort).rdPort(x5927_x6712_x6722_v).rdAddr(x6704(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6078(0)), CU.load(x6065_x6079)), op=FixSub, results=List(x5927_x6712.writeAddr))
    }
    val x5928_dsp0 = MemoryPipeline(name="x5928_dsp0",parent="x7083") { implicit CU => 
      val x6176_x6176 =  VectorFIFO(size=1).wtPort(x6126_x6151_data_v)
      val x6152_x6166 =  ScalarBuffer().wtPort(x6152_x6157_s)
      val x6165 = CounterChain.copy("x6177", "x6165")
      val x6751 = CounterChain.copy("x6795", "x6751")
      val x5928_x6759 =  SRAM(size=20,banking = Strided(1)).wtPort(x6176_x6176.readPort).rdPort(x5928_x6759_x6769_v).rdAddr(x6751(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6165(0)), CU.load(x6152_x6166)), op=FixSub, results=List(x5928_x6759.writeAddr))
    }
    val x5929_dsp0 = MemoryPipeline(name="x5929_dsp0",parent="x7083") { implicit CU => 
      val x6263_x6263 =  VectorFIFO(size=1).wtPort(x6213_x6238_data_v)
      val x6239_x6253 =  ScalarBuffer().wtPort(x6239_x6244_s)
      val x6252 = CounterChain.copy("x6264", "x6252")
      val x6798 = CounterChain.copy("x6842", "x6798")
      val x5929_x6806 =  SRAM(size=20,banking = Strided(1)).wtPort(x6263_x6263.readPort).rdPort(x5929_x6806_x6816_v).rdAddr(x6798(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6252(0)), CU.load(x6239_x6253)), op=FixSub, results=List(x5929_x6806.writeAddr))
    }
    val x5930_dsp0 = MemoryPipeline(name="x5930_dsp0",parent="x7083") { implicit CU => 
      val x6350_x6350 =  VectorFIFO(size=1).wtPort(x6300_x6325_data_v)
      val x6326_x6340 =  ScalarBuffer().wtPort(x6326_x6331_s)
      val x6339 = CounterChain.copy("x6351", "x6339")
      val x6845 = CounterChain.copy("x6889", "x6845")
      val x5930_x6853 =  SRAM(size=20,banking = Strided(1)).wtPort(x6350_x6350.readPort).rdPort(x5930_x6853_x6863_v).rdAddr(x6845(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6339(0)), CU.load(x6326_x6340)), op=FixSub, results=List(x5930_x6853.writeAddr))
    }
    val x5931_dsp0 = MemoryPipeline(name="x5931_dsp0",parent="x7083") { implicit CU => 
      val x6437_x6437 =  VectorFIFO(size=1).wtPort(x6387_x6412_data_v)
      val x6413_x6427 =  ScalarBuffer().wtPort(x6413_x6418_s)
      val x6426 = CounterChain.copy("x6438", "x6426")
      val x6892 = CounterChain.copy("x6936", "x6892")
      val x5931_x6900 =  SRAM(size=20,banking = Strided(1)).wtPort(x6437_x6437.readPort).rdPort(x5931_x6900_x6910_v).rdAddr(x6892(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6426(0)), CU.load(x6413_x6427)), op=FixSub, results=List(x5931_x6900.writeAddr))
    }
    val x5932_dsp0 = MemoryPipeline(name="x5932_dsp0",parent="x7083") { implicit CU => 
      val x6524_x6524 =  VectorFIFO(size=1).wtPort(x6474_x6499_data_v)
      val x6500_x6514 =  ScalarBuffer().wtPort(x6500_x6505_s)
      val x6513 = CounterChain.copy("x6525", "x6513")
      val x6939 = CounterChain.copy("x6983", "x6939")
      val x5932_x6947 =  SRAM(size=20,banking = Strided(1)).wtPort(x6524_x6524.readPort).rdPort(x5932_x6947_x6957_v).rdAddr(x6939(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6513(0)), CU.load(x6500_x6514)), op=FixSub, results=List(x5932_x6947.writeAddr))
    }
    val x5933_dsp0 = MemoryPipeline(name="x5933_dsp0",parent="x7083") { implicit CU => 
      val x6611_x6611 =  VectorFIFO(size=1).wtPort(x6561_x6586_data_v)
      val x6587_x6601 =  ScalarBuffer().wtPort(x6587_x6592_s)
      val x6600 = CounterChain.copy("x6612", "x6600")
      val x6986 = CounterChain.copy("x7030", "x6986")
      val x5933_x6994 =  SRAM(size=20,banking = Strided(1)).wtPort(x6611_x6611.readPort).rdPort(x5933_x6994_x7004_v).rdAddr(x6986(0))
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6600(0)), CU.load(x6587_x6601)), op=FixSub, results=List(x5933_x6994.writeAddr))
    }
    val x5934_dsp0 = MemoryPipeline(name="x5934_dsp0",parent="x7083") { implicit CU => 
      val b7192 = CU.temp
      val b7327 = CU.temp
      val x6028_x6028 =  VectorFIFO(size=1).wtPort(x6009_x6021_data_v)
      val x6023 = CounterChain.copy("x6029", "x6023")
      val x6661 = CounterChain.copy("x6675", "x6661")
      val x6657 = CounterChain.copy("x6701", "x6657")
      val x6007 = CounterChain.copy("x6030", "x6007")
      val x5934_x6664 =  SRAM(size=1920,banking = Strided(1)).wtPort(x6028_x6028.readPort).rdPort(x5934_x6664_x6675_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6007(0)), Const(96)), op=FixMul, results=List(b7192))
      WAStage(operands=List(b7192, CU.ctr(x6023(0))), op=FixAdd, results=List(x5934_x6664.writeAddr))
      RAStage(operands=List(CU.ctr(x6657(0)), Const(96)), op=FixMul, results=List(b7327))
      RAStage(operands=List(b7327, CU.ctr(x6661(0))), op=FixAdd, results=List(x5934_x6664.readAddr))
    }
    val x5935_dsp0 = MemoryPipeline(name="x5935_dsp0",parent="x7083") { implicit CU => 
      val b7337 = CU.temp
      val b7211 = CU.temp
      val x6115_x6115 =  VectorFIFO(size=1).wtPort(x6096_x6108_data_v)
      val x6110 = CounterChain.copy("x6116", "x6110")
      val x6708 = CounterChain.copy("x6722", "x6708")
      val x6094 = CounterChain.copy("x6117", "x6094")
      val x6704 = CounterChain.copy("x6748", "x6704")
      val x5935_x6711 =  SRAM(size=1920,banking = Strided(1)).wtPort(x6115_x6115.readPort).rdPort(x5935_x6711_x6722_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6094(0)), Const(96)), op=FixMul, results=List(b7211))
      WAStage(operands=List(b7211, CU.ctr(x6110(0))), op=FixAdd, results=List(x5935_x6711.writeAddr))
      RAStage(operands=List(CU.ctr(x6704(0)), Const(96)), op=FixMul, results=List(b7337))
      RAStage(operands=List(b7337, CU.ctr(x6708(0))), op=FixAdd, results=List(x5935_x6711.readAddr))
    }
    val x5936_dsp0 = MemoryPipeline(name="x5936_dsp0",parent="x7083") { implicit CU => 
      val b7347 = CU.temp
      val b7230 = CU.temp
      val x6202_x6202 =  VectorFIFO(size=1).wtPort(x6183_x6195_data_v)
      val x6181 = CounterChain.copy("x6204", "x6181")
      val x6197 = CounterChain.copy("x6203", "x6197")
      val x6755 = CounterChain.copy("x6769", "x6755")
      val x6751 = CounterChain.copy("x6795", "x6751")
      val x5936_x6758 =  SRAM(size=1920,banking = Strided(1)).wtPort(x6202_x6202.readPort).rdPort(x5936_x6758_x6769_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6181(0)), Const(96)), op=FixMul, results=List(b7230))
      WAStage(operands=List(b7230, CU.ctr(x6197(0))), op=FixAdd, results=List(x5936_x6758.writeAddr))
      RAStage(operands=List(CU.ctr(x6751(0)), Const(96)), op=FixMul, results=List(b7347))
      RAStage(operands=List(b7347, CU.ctr(x6755(0))), op=FixAdd, results=List(x5936_x6758.readAddr))
    }
    val x5937_dsp0 = MemoryPipeline(name="x5937_dsp0",parent="x7083") { implicit CU => 
      val b7249 = CU.temp
      val b7357 = CU.temp
      val x6289_x6289 =  VectorFIFO(size=1).wtPort(x6270_x6282_data_v)
      val x6798 = CounterChain.copy("x6842", "x6798")
      val x6802 = CounterChain.copy("x6816", "x6802")
      val x6268 = CounterChain.copy("x6291", "x6268")
      val x6284 = CounterChain.copy("x6290", "x6284")
      val x5937_x6805 =  SRAM(size=1920,banking = Strided(1)).wtPort(x6289_x6289.readPort).rdPort(x5937_x6805_x6816_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6268(0)), Const(96)), op=FixMul, results=List(b7249))
      WAStage(operands=List(b7249, CU.ctr(x6284(0))), op=FixAdd, results=List(x5937_x6805.writeAddr))
      RAStage(operands=List(CU.ctr(x6798(0)), Const(96)), op=FixMul, results=List(b7357))
      RAStage(operands=List(b7357, CU.ctr(x6802(0))), op=FixAdd, results=List(x5937_x6805.readAddr))
    }
    val x5938_dsp0 = MemoryPipeline(name="x5938_dsp0",parent="x7083") { implicit CU => 
      val b7367 = CU.temp
      val b7268 = CU.temp
      val x6376_x6376 =  VectorFIFO(size=1).wtPort(x6357_x6369_data_v)
      val x6355 = CounterChain.copy("x6378", "x6355")
      val x6849 = CounterChain.copy("x6863", "x6849")
      val x6845 = CounterChain.copy("x6889", "x6845")
      val x6371 = CounterChain.copy("x6377", "x6371")
      val x5938_x6852 =  SRAM(size=1920,banking = Strided(1)).wtPort(x6376_x6376.readPort).rdPort(x5938_x6852_x6863_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6355(0)), Const(96)), op=FixMul, results=List(b7268))
      WAStage(operands=List(b7268, CU.ctr(x6371(0))), op=FixAdd, results=List(x5938_x6852.writeAddr))
      RAStage(operands=List(CU.ctr(x6845(0)), Const(96)), op=FixMul, results=List(b7367))
      RAStage(operands=List(b7367, CU.ctr(x6849(0))), op=FixAdd, results=List(x5938_x6852.readAddr))
    }
    val x5939_dsp0 = MemoryPipeline(name="x5939_dsp0",parent="x7083") { implicit CU => 
      val b7377 = CU.temp
      val b7287 = CU.temp
      val x6463_x6463 =  VectorFIFO(size=1).wtPort(x6444_x6456_data_v)
      val x6896 = CounterChain.copy("x6910", "x6896")
      val x6892 = CounterChain.copy("x6936", "x6892")
      val x6458 = CounterChain.copy("x6464", "x6458")
      val x6442 = CounterChain.copy("x6465", "x6442")
      val x5939_x6899 =  SRAM(size=1920,banking = Strided(1)).wtPort(x6463_x6463.readPort).rdPort(x5939_x6899_x6910_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6442(0)), Const(96)), op=FixMul, results=List(b7287))
      WAStage(operands=List(b7287, CU.ctr(x6458(0))), op=FixAdd, results=List(x5939_x6899.writeAddr))
      RAStage(operands=List(CU.ctr(x6892(0)), Const(96)), op=FixMul, results=List(b7377))
      RAStage(operands=List(b7377, CU.ctr(x6896(0))), op=FixAdd, results=List(x5939_x6899.readAddr))
    }
    val x5940_dsp0 = MemoryPipeline(name="x5940_dsp0",parent="x7083") { implicit CU => 
      val b7387 = CU.temp
      val b7306 = CU.temp
      val x6550_x6550 =  VectorFIFO(size=1).wtPort(x6531_x6543_data_v)
      val x6529 = CounterChain.copy("x6552", "x6529")
      val x6545 = CounterChain.copy("x6551", "x6545")
      val x6939 = CounterChain.copy("x6983", "x6939")
      val x6943 = CounterChain.copy("x6957", "x6943")
      val x5940_x6946 =  SRAM(size=1920,banking = Strided(1)).wtPort(x6550_x6550.readPort).rdPort(x5940_x6946_x6957_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6529(0)), Const(96)), op=FixMul, results=List(b7306))
      WAStage(operands=List(b7306, CU.ctr(x6545(0))), op=FixAdd, results=List(x5940_x6946.writeAddr))
      RAStage(operands=List(CU.ctr(x6939(0)), Const(96)), op=FixMul, results=List(b7387))
      RAStage(operands=List(b7387, CU.ctr(x6943(0))), op=FixAdd, results=List(x5940_x6946.readAddr))
    }
    val x5941_dsp0 = MemoryPipeline(name="x5941_dsp0",parent="x7083") { implicit CU => 
      val b7325 = CU.temp
      val b7397 = CU.temp
      val x6637_x6637 =  VectorFIFO(size=1).wtPort(x6618_x6630_data_v)
      val x6990 = CounterChain.copy("x7004", "x6990")
      val x6632 = CounterChain.copy("x6638", "x6632")
      val x6616 = CounterChain.copy("x6639", "x6616")
      val x6986 = CounterChain.copy("x7030", "x6986")
      val x5941_x6993 =  SRAM(size=1920,banking = Strided(1)).wtPort(x6637_x6637.readPort).rdPort(x5941_x6993_x7004_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6616(0)), Const(96)), op=FixMul, results=List(b7325))
      WAStage(operands=List(b7325, CU.ctr(x6632(0))), op=FixAdd, results=List(x5941_x6993.writeAddr))
      RAStage(operands=List(CU.ctr(x6986(0)), Const(96)), op=FixMul, results=List(b7397))
      RAStage(operands=List(b7397, CU.ctr(x6990(0))), op=FixAdd, results=List(x5941_x6993.readAddr))
    }
    val x6005 = StreamController(name="x6005",parent=x7083) { implicit CU => 
    }
    val x5976 = StreamController(name="x5976",parent=x6005) { implicit CU => 
    }
    val x5976_0 = Pipeline(name="x5976_0",parent=x5976) { implicit CU => 
      val x5954 = CU.temp
      val x5960 = CU.temp
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), Const(4)), op=FixMul, results=List(x5954, CU.scalarOut(bus_6625_s)))
      Stage(operands=List(x5954, Const(64)), op=FixMod, results=List(x5960, CU.scalarOut(bus_6627_s)))
      Stage(operands=List(x5960, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6628_s), CU.scalarOut(x5951_b7178_x5975_b7186_s)))
      Stage(operands=List(x5954, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_6630_s)))
    }
    val x5976_1 = Pipeline(name="x5976_1",parent=x5976) { implicit CU => 
      val x5957 = CU.temp
      val x5958 = CU.temp
      val x5956 = CU.temp
      val x5959 = CU.temp
      val x5955 =  ScalarFIFO(size=1).wtPort(bus_6630_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5955), Const(4)), op=FixDiv, results=List(CU.scalarOut(x5951_b7179_x5975_b7187_s)))
      Stage(operands=List(CU.load(x5955), Const(64)), op=FixMod, results=List(x5956))
      Stage(operands=List(x5956, Const(0)), op=FixEql, results=List(x5957))
      Stage(operands=List(Const(64), x5956), op=FixSub, results=List(x5958))
      Stage(operands=List(x5957, Const(0), x5958), op=Mux, results=List(x5959, CU.scalarOut(bus_6636_s)))
      Stage(operands=List(x5959, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6637_s)))
    }
    val x5976_2 = Pipeline(name="x5976_2",parent=x5976) { implicit CU => 
      val x5961 = CU.temp
      val x5972 = CU.temp
      val x5963 = CU.temp
      val x5954 =  ScalarFIFO(size=1).wtPort(bus_6625_s)
      val x5969 =  ScalarFIFO(size=1).wtPort(bus_6628_s)
      val x5959 =  ScalarFIFO(size=1).wtPort(bus_6636_s)
      val x5960 =  ScalarFIFO(size=1).wtPort(bus_6627_s)
      val x5971 =  ScalarFIFO(size=1).wtPort(bus_6637_s)
      val x5953 =  ScalarBuffer().wtPort(x5953_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x5969)), op=FixAdd, results=List(x5972))
      Stage(operands=List(x5972, CU.load(x5971)), op=FixAdd, results=List(CU.scalarOut(x5951_b7177_x5975_b7185_s)))
      Stage(operands=List(Const(80), CU.load(x5960)), op=FixAdd, results=List(x5961))
      Stage(operands=List(x5961, CU.load(x5959)), op=FixAdd, results=List(CU.scalarOut(x5950_b7176_x5968_b7184_s)))
      Stage(operands=List(CU.load(x5954), CU.load(x5960)), op=FixSub, results=List(x5963))
      Stage(operands=List(x5963, CU.load(x5953)), op=FixAdd, results=List(CU.scalarOut(x5950_b7175_x5968_b7183_s)))
    }
    val x5977 = MemoryController(name="x5977",parent=x6005,offchip=x5877_oc, mctpe=TileLoad) { implicit CU => 
      val x5950_b7176_x5977 =  ScalarFIFO(name="size",size=1).wtPort(x5950_b7176_x5968_b7184_s)
      val x5950_b7175_x5977 =  ScalarFIFO(name="offset",size=1).wtPort(x5950_b7175_x5968_b7183_s)
      CU.newVout("data", x5952_x5977_data_v)
    }
    val x6004 = Sequential(name="x6004",parent=x6005) { implicit CU => 
    }
    val x5988_0 = Pipeline(name="x5988_0",parent=x6004) { implicit CU => 
      val x5951_b7179_x5981_b7182 =  ScalarFIFO(size=16).wtPort(x5951_b7179_x5975_b7187_s)
      val x5951_b7178_x5981_b7181 =  ScalarFIFO(size=16).wtPort(x5951_b7178_x5975_b7186_s)
      val x5951_b7177_x5981_b7180 =  ScalarFIFO(size=16).wtPort(x5951_b7177_x5975_b7185_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5951_b7178_x5981_b7181)), op=Bypass, results=List(CU.scalarOut(x5978_x5983_s)))
      Stage(operands=List(CU.load(x5951_b7179_x5981_b7182)), op=Bypass, results=List(CU.scalarOut(x5979_x5985_s)))
      Stage(operands=List(CU.load(x5951_b7177_x5981_b7180)), op=Bypass, results=List(CU.scalarOut(x5980_x5987_s)))
    }
    val x6003 = Pipeline(name="x6003",parent=x6004) { implicit CU => 
      val x5978_x5992 =  ScalarBuffer().wtPort(x5978_x5983_s)
      val x5980_x5989 =  ScalarBuffer().wtPort(x5980_x5987_s)
      val x5979_x5993 =  ScalarBuffer().wtPort(x5979_x5985_s)
      val ctr4 = Counter(min=Const(0), max=x5980_x5989.load, step=Const(1), par=16) // Counter
      val x5991 = CounterChain(name = "x5991", ctr4).iter(1)
      var stage: List[Stage] = Nil
    }
    val x6030 = StreamController(name="x6030",parent=x7083) { implicit CU => 
      val ctr5 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x6007 = CounterChain(name = "x6007", ctr5).iter(20)
    }
    val x6020_0 = Pipeline(name="x6020_0",parent=x6030) { implicit CU => 
      val x6011 = CU.temp
      val x6013 = CU.temp
      val x6012 = CU.temp
      val x6010 =  ScalarBuffer().wtPort(x6010_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      val x6007 = CounterChain.copy("x6030", "x6007")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), CU.ctr(x6007(0))), op=FixAdd, results=List(x6011))
      Stage(operands=List(x6011, Const(96)), op=FixMul, results=List(x6012))
      Stage(operands=List(x6012, Const(4)), op=FixMul, results=List(x6013))
      Stage(operands=List(x6013, CU.load(x6010)), op=FixAdd, results=List(CU.scalarOut(x6008_b7188_x6019_b7190_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x6008_b7189_x6019_b7191_s)))
    }
    val x6021 = MemoryController(name="x6021",parent=x6030,offchip=x5875_oc, mctpe=TileLoad) { implicit CU => 
      val x6008_b7189_x6021 =  ScalarFIFO(name="size",size=1).wtPort(x6008_b7189_x6019_b7191_s)
      val x6008_b7188_x6021 =  ScalarFIFO(name="offset",size=1).wtPort(x6008_b7188_x6019_b7190_s)
      CU.newVout("data", x6009_x6021_data_v)
    }
    val x6029 = Pipeline(name="x6029",parent=x6030) { implicit CU => 
      val ctr6 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6023 = CounterChain(name = "x6023", ctr6).iter(6)
      var stage: List[Stage] = Nil
    }
    val x6035_0 = Pipeline(name="x6035_0",parent=x7083) { implicit CU => 
      val x6032 = CU.temp
      val x5872_x6031 =  ScalarBuffer().wtPort(x5872_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5872_x6031), CU.ctr(x5925(0))), op=FixSub, results=List(x6032))
      Stage(operands=List(x6032, Const(20)), op=FixMin, results=List(CU.scalarOut(x5942_x6034_s)))
    }
    val x6092 = StreamController(name="x6092",parent=x7083) { implicit CU => 
    }
    val x6063 = StreamController(name="x6063",parent=x6092) { implicit CU => 
    }
    val x6063_0 = Pipeline(name="x6063_0",parent=x6063) { implicit CU => 
      val x6047 = CU.temp
      val x6041 = CU.temp
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), Const(4)), op=FixMul, results=List(x6041, CU.scalarOut(bus_6658_s)))
      Stage(operands=List(x6041, Const(64)), op=FixMod, results=List(x6047, CU.scalarOut(bus_6660_s)))
      Stage(operands=List(x6047, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6661_s), CU.scalarOut(x6038_b7197_x6062_b7205_s)))
      Stage(operands=List(x6041, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_6663_s)))
    }
    val x6063_1 = Pipeline(name="x6063_1",parent=x6063) { implicit CU => 
      val x6046 = CU.temp
      val x6043 = CU.temp
      val x6045 = CU.temp
      val x6044 = CU.temp
      val x6042 =  ScalarFIFO(size=1).wtPort(bus_6663_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6042), Const(4)), op=FixDiv, results=List(CU.scalarOut(x6038_b7198_x6062_b7206_s)))
      Stage(operands=List(CU.load(x6042), Const(64)), op=FixMod, results=List(x6043))
      Stage(operands=List(x6043, Const(0)), op=FixEql, results=List(x6044))
      Stage(operands=List(Const(64), x6043), op=FixSub, results=List(x6045))
      Stage(operands=List(x6044, Const(0), x6045), op=Mux, results=List(x6046, CU.scalarOut(bus_6669_s)))
      Stage(operands=List(x6046, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6670_s)))
    }
    val x6063_2 = Pipeline(name="x6063_2",parent=x6063) { implicit CU => 
      val x6048 = CU.temp
      val x6050 = CU.temp
      val x6059 = CU.temp
      val x6047 =  ScalarFIFO(size=1).wtPort(bus_6660_s)
      val x6058 =  ScalarFIFO(size=1).wtPort(bus_6670_s)
      val x6056 =  ScalarFIFO(size=1).wtPort(bus_6661_s)
      val x6041 =  ScalarFIFO(size=1).wtPort(bus_6658_s)
      val x6046 =  ScalarFIFO(size=1).wtPort(bus_6669_s)
      val x6040 =  ScalarBuffer().wtPort(x6040_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x6056)), op=FixAdd, results=List(x6059))
      Stage(operands=List(x6059, CU.load(x6058)), op=FixAdd, results=List(CU.scalarOut(x6038_b7196_x6062_b7204_s)))
      Stage(operands=List(Const(80), CU.load(x6047)), op=FixAdd, results=List(x6048))
      Stage(operands=List(x6048, CU.load(x6046)), op=FixAdd, results=List(CU.scalarOut(x6037_b7195_x6055_b7203_s)))
      Stage(operands=List(CU.load(x6041), CU.load(x6047)), op=FixSub, results=List(x6050))
      Stage(operands=List(x6050, CU.load(x6040)), op=FixAdd, results=List(CU.scalarOut(x6037_b7194_x6055_b7202_s)))
    }
    val x6064 = MemoryController(name="x6064",parent=x6092,offchip=x5877_oc, mctpe=TileLoad) { implicit CU => 
      val x6037_b7194_x6064 =  ScalarFIFO(name="offset",size=1).wtPort(x6037_b7194_x6055_b7202_s)
      val x6037_b7195_x6064 =  ScalarFIFO(name="size",size=1).wtPort(x6037_b7195_x6055_b7203_s)
      CU.newVout("data", x6039_x6064_data_v)
    }
    val x6091 = Sequential(name="x6091",parent=x6092) { implicit CU => 
    }
    val x6075_0 = Pipeline(name="x6075_0",parent=x6091) { implicit CU => 
      val x6038_b7198_x6068_b7201 =  ScalarFIFO(size=16).wtPort(x6038_b7198_x6062_b7206_s)
      val x6038_b7197_x6068_b7200 =  ScalarFIFO(size=16).wtPort(x6038_b7197_x6062_b7205_s)
      val x6038_b7196_x6068_b7199 =  ScalarFIFO(size=16).wtPort(x6038_b7196_x6062_b7204_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6038_b7197_x6068_b7200)), op=Bypass, results=List(CU.scalarOut(x6065_x6070_s)))
      Stage(operands=List(CU.load(x6038_b7198_x6068_b7201)), op=Bypass, results=List(CU.scalarOut(x6066_x6072_s)))
      Stage(operands=List(CU.load(x6038_b7196_x6068_b7199)), op=Bypass, results=List(CU.scalarOut(x6067_x6074_s)))
    }
    val x6090 = Pipeline(name="x6090",parent=x6091) { implicit CU => 
      val x6067_x6076 =  ScalarBuffer().wtPort(x6067_x6074_s)
      val x6066_x6080 =  ScalarBuffer().wtPort(x6066_x6072_s)
      val x6065_x6079 =  ScalarBuffer().wtPort(x6065_x6070_s)
      val ctr7 = Counter(min=Const(0), max=x6067_x6076.load, step=Const(1), par=16) // Counter
      val x6078 = CounterChain(name = "x6078", ctr7).iter(1)
      var stage: List[Stage] = Nil
    }
    val x6117 = StreamController(name="x6117",parent=x7083) { implicit CU => 
      val ctr8 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x6094 = CounterChain(name = "x6094", ctr8).iter(20)
    }
    val x6107_0 = Pipeline(name="x6107_0",parent=x6117) { implicit CU => 
      val x6100 = CU.temp
      val x6098 = CU.temp
      val x6099 = CU.temp
      val x6097 =  ScalarBuffer().wtPort(x6097_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      val x6094 = CounterChain.copy("x6117", "x6094")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), CU.ctr(x6094(0))), op=FixAdd, results=List(x6098))
      Stage(operands=List(x6098, Const(96)), op=FixMul, results=List(x6099))
      Stage(operands=List(x6099, Const(4)), op=FixMul, results=List(x6100))
      Stage(operands=List(x6100, CU.load(x6097)), op=FixAdd, results=List(CU.scalarOut(x6095_b7207_x6106_b7209_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x6095_b7208_x6106_b7210_s)))
    }
    val x6108 = MemoryController(name="x6108",parent=x6117,offchip=x5875_oc, mctpe=TileLoad) { implicit CU => 
      val x6095_b7207_x6108 =  ScalarFIFO(name="offset",size=1).wtPort(x6095_b7207_x6106_b7209_s)
      val x6095_b7208_x6108 =  ScalarFIFO(name="size",size=1).wtPort(x6095_b7208_x6106_b7210_s)
      CU.newVout("data", x6096_x6108_data_v)
    }
    val x6116 = Pipeline(name="x6116",parent=x6117) { implicit CU => 
      val ctr9 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6110 = CounterChain(name = "x6110", ctr9).iter(6)
      var stage: List[Stage] = Nil
    }
    val x6122_0 = Pipeline(name="x6122_0",parent=x7083) { implicit CU => 
      val x6119 = CU.temp
      val x5872_x6118 =  ScalarBuffer().wtPort(x5872_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5872_x6118), CU.ctr(x5925(0))), op=FixSub, results=List(x6119))
      Stage(operands=List(x6119, Const(20)), op=FixMin, results=List(CU.scalarOut(x5943_x6121_s)))
    }
    val x6179 = StreamController(name="x6179",parent=x7083) { implicit CU => 
    }
    val x6150 = StreamController(name="x6150",parent=x6179) { implicit CU => 
    }
    val x6150_0 = Pipeline(name="x6150_0",parent=x6150) { implicit CU => 
      val x6134 = CU.temp
      val x6128 = CU.temp
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), Const(4)), op=FixMul, results=List(x6128, CU.scalarOut(bus_6691_s)))
      Stage(operands=List(x6128, Const(64)), op=FixMod, results=List(x6134, CU.scalarOut(bus_6693_s)))
      Stage(operands=List(x6134, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6694_s), CU.scalarOut(x6125_b7216_x6149_b7224_s)))
      Stage(operands=List(x6128, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_6696_s)))
    }
    val x6150_1 = Pipeline(name="x6150_1",parent=x6150) { implicit CU => 
      val x6130 = CU.temp
      val x6132 = CU.temp
      val x6133 = CU.temp
      val x6131 = CU.temp
      val x6129 =  ScalarFIFO(size=1).wtPort(bus_6696_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6129), Const(4)), op=FixDiv, results=List(CU.scalarOut(x6125_b7217_x6149_b7225_s)))
      Stage(operands=List(CU.load(x6129), Const(64)), op=FixMod, results=List(x6130))
      Stage(operands=List(x6130, Const(0)), op=FixEql, results=List(x6131))
      Stage(operands=List(Const(64), x6130), op=FixSub, results=List(x6132))
      Stage(operands=List(x6131, Const(0), x6132), op=Mux, results=List(x6133, CU.scalarOut(bus_6702_s)))
      Stage(operands=List(x6133, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6703_s)))
    }
    val x6150_2 = Pipeline(name="x6150_2",parent=x6150) { implicit CU => 
      val x6135 = CU.temp
      val x6137 = CU.temp
      val x6146 = CU.temp
      val x6145 =  ScalarFIFO(size=1).wtPort(bus_6703_s)
      val x6127 =  ScalarBuffer().wtPort(x6127_argin)
      val x6134 =  ScalarFIFO(size=1).wtPort(bus_6693_s)
      val x6133 =  ScalarFIFO(size=1).wtPort(bus_6702_s)
      val x6143 =  ScalarFIFO(size=1).wtPort(bus_6694_s)
      val x6128 =  ScalarFIFO(size=1).wtPort(bus_6691_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x6143)), op=FixAdd, results=List(x6146))
      Stage(operands=List(x6146, CU.load(x6145)), op=FixAdd, results=List(CU.scalarOut(x6125_b7215_x6149_b7223_s)))
      Stage(operands=List(Const(80), CU.load(x6134)), op=FixAdd, results=List(x6135))
      Stage(operands=List(x6135, CU.load(x6133)), op=FixAdd, results=List(CU.scalarOut(x6124_b7214_x6142_b7222_s)))
      Stage(operands=List(CU.load(x6128), CU.load(x6134)), op=FixSub, results=List(x6137))
      Stage(operands=List(x6137, CU.load(x6127)), op=FixAdd, results=List(CU.scalarOut(x6124_b7213_x6142_b7221_s)))
    }
    val x6151 = MemoryController(name="x6151",parent=x6179,offchip=x5877_oc, mctpe=TileLoad) { implicit CU => 
      val x6124_b7214_x6151 =  ScalarFIFO(name="size",size=1).wtPort(x6124_b7214_x6142_b7222_s)
      val x6124_b7213_x6151 =  ScalarFIFO(name="offset",size=1).wtPort(x6124_b7213_x6142_b7221_s)
      CU.newVout("data", x6126_x6151_data_v)
    }
    val x6178 = Sequential(name="x6178",parent=x6179) { implicit CU => 
    }
    val x6162_0 = Pipeline(name="x6162_0",parent=x6178) { implicit CU => 
      val x6125_b7216_x6155_b7219 =  ScalarFIFO(size=16).wtPort(x6125_b7216_x6149_b7224_s)
      val x6125_b7215_x6155_b7218 =  ScalarFIFO(size=16).wtPort(x6125_b7215_x6149_b7223_s)
      val x6125_b7217_x6155_b7220 =  ScalarFIFO(size=16).wtPort(x6125_b7217_x6149_b7225_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6125_b7216_x6155_b7219)), op=Bypass, results=List(CU.scalarOut(x6152_x6157_s)))
      Stage(operands=List(CU.load(x6125_b7217_x6155_b7220)), op=Bypass, results=List(CU.scalarOut(x6153_x6159_s)))
      Stage(operands=List(CU.load(x6125_b7215_x6155_b7218)), op=Bypass, results=List(CU.scalarOut(x6154_x6161_s)))
    }
    val x6177 = Pipeline(name="x6177",parent=x6178) { implicit CU => 
      val x6154_x6163 =  ScalarBuffer().wtPort(x6154_x6161_s)
      val x6153_x6167 =  ScalarBuffer().wtPort(x6153_x6159_s)
      val x6152_x6166 =  ScalarBuffer().wtPort(x6152_x6157_s)
      val ctr10 = Counter(min=Const(0), max=x6154_x6163.load, step=Const(1), par=16) // Counter
      val x6165 = CounterChain(name = "x6165", ctr10).iter(1)
      var stage: List[Stage] = Nil
    }
    val x6204 = StreamController(name="x6204",parent=x7083) { implicit CU => 
      val ctr11 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x6181 = CounterChain(name = "x6181", ctr11).iter(20)
    }
    val x6194_0 = Pipeline(name="x6194_0",parent=x6204) { implicit CU => 
      val x6186 = CU.temp
      val x6187 = CU.temp
      val x6185 = CU.temp
      val x6184 =  ScalarBuffer().wtPort(x6184_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      val x6181 = CounterChain.copy("x6204", "x6181")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), CU.ctr(x6181(0))), op=FixAdd, results=List(x6185))
      Stage(operands=List(x6185, Const(96)), op=FixMul, results=List(x6186))
      Stage(operands=List(x6186, Const(4)), op=FixMul, results=List(x6187))
      Stage(operands=List(x6187, CU.load(x6184)), op=FixAdd, results=List(CU.scalarOut(x6182_b7226_x6193_b7228_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x6182_b7227_x6193_b7229_s)))
    }
    val x6195 = MemoryController(name="x6195",parent=x6204,offchip=x5875_oc, mctpe=TileLoad) { implicit CU => 
      val x6182_b7227_x6195 =  ScalarFIFO(name="size",size=1).wtPort(x6182_b7227_x6193_b7229_s)
      val x6182_b7226_x6195 =  ScalarFIFO(name="offset",size=1).wtPort(x6182_b7226_x6193_b7228_s)
      CU.newVout("data", x6183_x6195_data_v)
    }
    val x6203 = Pipeline(name="x6203",parent=x6204) { implicit CU => 
      val ctr12 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6197 = CounterChain(name = "x6197", ctr12).iter(6)
      var stage: List[Stage] = Nil
    }
    val x6209_0 = Pipeline(name="x6209_0",parent=x7083) { implicit CU => 
      val x6206 = CU.temp
      val x5872_x6205 =  ScalarBuffer().wtPort(x5872_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5872_x6205), CU.ctr(x5925(0))), op=FixSub, results=List(x6206))
      Stage(operands=List(x6206, Const(20)), op=FixMin, results=List(CU.scalarOut(x5944_x6208_s)))
    }
    val x6266 = StreamController(name="x6266",parent=x7083) { implicit CU => 
    }
    val x6237 = StreamController(name="x6237",parent=x6266) { implicit CU => 
    }
    val x6237_0 = Pipeline(name="x6237_0",parent=x6237) { implicit CU => 
      val x6221 = CU.temp
      val x6215 = CU.temp
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), Const(4)), op=FixMul, results=List(x6215, CU.scalarOut(bus_6724_s)))
      Stage(operands=List(x6215, Const(64)), op=FixMod, results=List(x6221, CU.scalarOut(bus_6726_s)))
      Stage(operands=List(x6221, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6727_s), CU.scalarOut(x6212_b7235_x6236_b7243_s)))
      Stage(operands=List(x6215, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_6729_s)))
    }
    val x6237_1 = Pipeline(name="x6237_1",parent=x6237) { implicit CU => 
      val x6217 = CU.temp
      val x6218 = CU.temp
      val x6220 = CU.temp
      val x6219 = CU.temp
      val x6216 =  ScalarFIFO(size=1).wtPort(bus_6729_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6216), Const(4)), op=FixDiv, results=List(CU.scalarOut(x6212_b7236_x6236_b7244_s)))
      Stage(operands=List(CU.load(x6216), Const(64)), op=FixMod, results=List(x6217))
      Stage(operands=List(x6217, Const(0)), op=FixEql, results=List(x6218))
      Stage(operands=List(Const(64), x6217), op=FixSub, results=List(x6219))
      Stage(operands=List(x6218, Const(0), x6219), op=Mux, results=List(x6220, CU.scalarOut(bus_6735_s)))
      Stage(operands=List(x6220, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6736_s)))
    }
    val x6237_2 = Pipeline(name="x6237_2",parent=x6237) { implicit CU => 
      val x6224 = CU.temp
      val x6222 = CU.temp
      val x6233 = CU.temp
      val x6220 =  ScalarFIFO(size=1).wtPort(bus_6735_s)
      val x6230 =  ScalarFIFO(size=1).wtPort(bus_6727_s)
      val x6221 =  ScalarFIFO(size=1).wtPort(bus_6726_s)
      val x6232 =  ScalarFIFO(size=1).wtPort(bus_6736_s)
      val x6214 =  ScalarBuffer().wtPort(x6214_argin)
      val x6215 =  ScalarFIFO(size=1).wtPort(bus_6724_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x6230)), op=FixAdd, results=List(x6233))
      Stage(operands=List(x6233, CU.load(x6232)), op=FixAdd, results=List(CU.scalarOut(x6212_b7234_x6236_b7242_s)))
      Stage(operands=List(Const(80), CU.load(x6221)), op=FixAdd, results=List(x6222))
      Stage(operands=List(x6222, CU.load(x6220)), op=FixAdd, results=List(CU.scalarOut(x6211_b7233_x6229_b7241_s)))
      Stage(operands=List(CU.load(x6215), CU.load(x6221)), op=FixSub, results=List(x6224))
      Stage(operands=List(x6224, CU.load(x6214)), op=FixAdd, results=List(CU.scalarOut(x6211_b7232_x6229_b7240_s)))
    }
    val x6238 = MemoryController(name="x6238",parent=x6266,offchip=x5877_oc, mctpe=TileLoad) { implicit CU => 
      val x6211_b7233_x6238 =  ScalarFIFO(name="size",size=1).wtPort(x6211_b7233_x6229_b7241_s)
      val x6211_b7232_x6238 =  ScalarFIFO(name="offset",size=1).wtPort(x6211_b7232_x6229_b7240_s)
      CU.newVout("data", x6213_x6238_data_v)
    }
    val x6265 = Sequential(name="x6265",parent=x6266) { implicit CU => 
    }
    val x6249_0 = Pipeline(name="x6249_0",parent=x6265) { implicit CU => 
      val x6212_b7234_x6242_b7237 =  ScalarFIFO(size=16).wtPort(x6212_b7234_x6236_b7242_s)
      val x6212_b7236_x6242_b7239 =  ScalarFIFO(size=16).wtPort(x6212_b7236_x6236_b7244_s)
      val x6212_b7235_x6242_b7238 =  ScalarFIFO(size=16).wtPort(x6212_b7235_x6236_b7243_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6212_b7235_x6242_b7238)), op=Bypass, results=List(CU.scalarOut(x6239_x6244_s)))
      Stage(operands=List(CU.load(x6212_b7236_x6242_b7239)), op=Bypass, results=List(CU.scalarOut(x6240_x6246_s)))
      Stage(operands=List(CU.load(x6212_b7234_x6242_b7237)), op=Bypass, results=List(CU.scalarOut(x6241_x6248_s)))
    }
    val x6264 = Pipeline(name="x6264",parent=x6265) { implicit CU => 
      val x6241_x6250 =  ScalarBuffer().wtPort(x6241_x6248_s)
      val x6240_x6254 =  ScalarBuffer().wtPort(x6240_x6246_s)
      val x6239_x6253 =  ScalarBuffer().wtPort(x6239_x6244_s)
      val ctr13 = Counter(min=Const(0), max=x6241_x6250.load, step=Const(1), par=16) // Counter
      val x6252 = CounterChain(name = "x6252", ctr13).iter(1)
      var stage: List[Stage] = Nil
    }
    val x6291 = StreamController(name="x6291",parent=x7083) { implicit CU => 
      val ctr14 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x6268 = CounterChain(name = "x6268", ctr14).iter(20)
    }
    val x6281_0 = Pipeline(name="x6281_0",parent=x6291) { implicit CU => 
      val x6273 = CU.temp
      val x6272 = CU.temp
      val x6274 = CU.temp
      val x6271 =  ScalarBuffer().wtPort(x6271_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      val x6268 = CounterChain.copy("x6291", "x6268")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), CU.ctr(x6268(0))), op=FixAdd, results=List(x6272))
      Stage(operands=List(x6272, Const(96)), op=FixMul, results=List(x6273))
      Stage(operands=List(x6273, Const(4)), op=FixMul, results=List(x6274))
      Stage(operands=List(x6274, CU.load(x6271)), op=FixAdd, results=List(CU.scalarOut(x6269_b7245_x6280_b7247_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x6269_b7246_x6280_b7248_s)))
    }
    val x6282 = MemoryController(name="x6282",parent=x6291,offchip=x5875_oc, mctpe=TileLoad) { implicit CU => 
      val x6269_b7245_x6282 =  ScalarFIFO(name="offset",size=1).wtPort(x6269_b7245_x6280_b7247_s)
      val x6269_b7246_x6282 =  ScalarFIFO(name="size",size=1).wtPort(x6269_b7246_x6280_b7248_s)
      CU.newVout("data", x6270_x6282_data_v)
    }
    val x6290 = Pipeline(name="x6290",parent=x6291) { implicit CU => 
      val ctr15 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6284 = CounterChain(name = "x6284", ctr15).iter(6)
      var stage: List[Stage] = Nil
    }
    val x6296_0 = Pipeline(name="x6296_0",parent=x7083) { implicit CU => 
      val x6293 = CU.temp
      val x5872_x6292 =  ScalarBuffer().wtPort(x5872_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5872_x6292), CU.ctr(x5925(0))), op=FixSub, results=List(x6293))
      Stage(operands=List(x6293, Const(20)), op=FixMin, results=List(CU.scalarOut(x5945_x6295_s)))
    }
    val x6353 = StreamController(name="x6353",parent=x7083) { implicit CU => 
    }
    val x6324 = StreamController(name="x6324",parent=x6353) { implicit CU => 
    }
    val x6324_0 = Pipeline(name="x6324_0",parent=x6324) { implicit CU => 
      val x6302 = CU.temp
      val x6308 = CU.temp
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), Const(4)), op=FixMul, results=List(x6302, CU.scalarOut(bus_6757_s)))
      Stage(operands=List(x6302, Const(64)), op=FixMod, results=List(x6308, CU.scalarOut(bus_6759_s)))
      Stage(operands=List(x6308, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6760_s), CU.scalarOut(x6299_b7254_x6323_b7262_s)))
      Stage(operands=List(x6302, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_6762_s)))
    }
    val x6324_1 = Pipeline(name="x6324_1",parent=x6324) { implicit CU => 
      val x6305 = CU.temp
      val x6306 = CU.temp
      val x6307 = CU.temp
      val x6304 = CU.temp
      val x6303 =  ScalarFIFO(size=1).wtPort(bus_6762_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6303), Const(4)), op=FixDiv, results=List(CU.scalarOut(x6299_b7255_x6323_b7263_s)))
      Stage(operands=List(CU.load(x6303), Const(64)), op=FixMod, results=List(x6304))
      Stage(operands=List(x6304, Const(0)), op=FixEql, results=List(x6305))
      Stage(operands=List(Const(64), x6304), op=FixSub, results=List(x6306))
      Stage(operands=List(x6305, Const(0), x6306), op=Mux, results=List(x6307, CU.scalarOut(bus_6768_s)))
      Stage(operands=List(x6307, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6769_s)))
    }
    val x6324_2 = Pipeline(name="x6324_2",parent=x6324) { implicit CU => 
      val x6320 = CU.temp
      val x6311 = CU.temp
      val x6309 = CU.temp
      val x6301 =  ScalarBuffer().wtPort(x6301_argin)
      val x6302 =  ScalarFIFO(size=1).wtPort(bus_6757_s)
      val x6308 =  ScalarFIFO(size=1).wtPort(bus_6759_s)
      val x6317 =  ScalarFIFO(size=1).wtPort(bus_6760_s)
      val x6319 =  ScalarFIFO(size=1).wtPort(bus_6769_s)
      val x6307 =  ScalarFIFO(size=1).wtPort(bus_6768_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x6317)), op=FixAdd, results=List(x6320))
      Stage(operands=List(x6320, CU.load(x6319)), op=FixAdd, results=List(CU.scalarOut(x6299_b7253_x6323_b7261_s)))
      Stage(operands=List(Const(80), CU.load(x6308)), op=FixAdd, results=List(x6309))
      Stage(operands=List(x6309, CU.load(x6307)), op=FixAdd, results=List(CU.scalarOut(x6298_b7252_x6316_b7260_s)))
      Stage(operands=List(CU.load(x6302), CU.load(x6308)), op=FixSub, results=List(x6311))
      Stage(operands=List(x6311, CU.load(x6301)), op=FixAdd, results=List(CU.scalarOut(x6298_b7251_x6316_b7259_s)))
    }
    val x6325 = MemoryController(name="x6325",parent=x6353,offchip=x5877_oc, mctpe=TileLoad) { implicit CU => 
      val x6298_b7252_x6325 =  ScalarFIFO(name="size",size=1).wtPort(x6298_b7252_x6316_b7260_s)
      val x6298_b7251_x6325 =  ScalarFIFO(name="offset",size=1).wtPort(x6298_b7251_x6316_b7259_s)
      CU.newVout("data", x6300_x6325_data_v)
    }
    val x6352 = Sequential(name="x6352",parent=x6353) { implicit CU => 
    }
    val x6336_0 = Pipeline(name="x6336_0",parent=x6352) { implicit CU => 
      val x6299_b7254_x6329_b7257 =  ScalarFIFO(size=16).wtPort(x6299_b7254_x6323_b7262_s)
      val x6299_b7253_x6329_b7256 =  ScalarFIFO(size=16).wtPort(x6299_b7253_x6323_b7261_s)
      val x6299_b7255_x6329_b7258 =  ScalarFIFO(size=16).wtPort(x6299_b7255_x6323_b7263_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6299_b7254_x6329_b7257)), op=Bypass, results=List(CU.scalarOut(x6326_x6331_s)))
      Stage(operands=List(CU.load(x6299_b7255_x6329_b7258)), op=Bypass, results=List(CU.scalarOut(x6327_x6333_s)))
      Stage(operands=List(CU.load(x6299_b7253_x6329_b7256)), op=Bypass, results=List(CU.scalarOut(x6328_x6335_s)))
    }
    val x6351 = Pipeline(name="x6351",parent=x6352) { implicit CU => 
      val x6327_x6341 =  ScalarBuffer().wtPort(x6327_x6333_s)
      val x6326_x6340 =  ScalarBuffer().wtPort(x6326_x6331_s)
      val x6328_x6337 =  ScalarBuffer().wtPort(x6328_x6335_s)
      val ctr16 = Counter(min=Const(0), max=x6328_x6337.load, step=Const(1), par=16) // Counter
      val x6339 = CounterChain(name = "x6339", ctr16).iter(1)
      var stage: List[Stage] = Nil
    }
    val x6378 = StreamController(name="x6378",parent=x7083) { implicit CU => 
      val ctr17 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x6355 = CounterChain(name = "x6355", ctr17).iter(20)
    }
    val x6368_0 = Pipeline(name="x6368_0",parent=x6378) { implicit CU => 
      val x6361 = CU.temp
      val x6359 = CU.temp
      val x6360 = CU.temp
      val x6358 =  ScalarBuffer().wtPort(x6358_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      val x6355 = CounterChain.copy("x6378", "x6355")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), CU.ctr(x6355(0))), op=FixAdd, results=List(x6359))
      Stage(operands=List(x6359, Const(96)), op=FixMul, results=List(x6360))
      Stage(operands=List(x6360, Const(4)), op=FixMul, results=List(x6361))
      Stage(operands=List(x6361, CU.load(x6358)), op=FixAdd, results=List(CU.scalarOut(x6356_b7264_x6367_b7266_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x6356_b7265_x6367_b7267_s)))
    }
    val x6369 = MemoryController(name="x6369",parent=x6378,offchip=x5875_oc, mctpe=TileLoad) { implicit CU => 
      val x6356_b7265_x6369 =  ScalarFIFO(name="size",size=1).wtPort(x6356_b7265_x6367_b7267_s)
      val x6356_b7264_x6369 =  ScalarFIFO(name="offset",size=1).wtPort(x6356_b7264_x6367_b7266_s)
      CU.newVout("data", x6357_x6369_data_v)
    }
    val x6377 = Pipeline(name="x6377",parent=x6378) { implicit CU => 
      val ctr18 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6371 = CounterChain(name = "x6371", ctr18).iter(6)
      var stage: List[Stage] = Nil
    }
    val x6383_0 = Pipeline(name="x6383_0",parent=x7083) { implicit CU => 
      val x6380 = CU.temp
      val x5872_x6379 =  ScalarBuffer().wtPort(x5872_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5872_x6379), CU.ctr(x5925(0))), op=FixSub, results=List(x6380))
      Stage(operands=List(x6380, Const(20)), op=FixMin, results=List(CU.scalarOut(x5946_x6382_s)))
    }
    val x6440 = StreamController(name="x6440",parent=x7083) { implicit CU => 
    }
    val x6411 = StreamController(name="x6411",parent=x6440) { implicit CU => 
    }
    val x6411_0 = Pipeline(name="x6411_0",parent=x6411) { implicit CU => 
      val x6395 = CU.temp
      val x6389 = CU.temp
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), Const(4)), op=FixMul, results=List(x6389, CU.scalarOut(bus_6790_s)))
      Stage(operands=List(x6389, Const(64)), op=FixMod, results=List(x6395, CU.scalarOut(bus_6792_s)))
      Stage(operands=List(x6395, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6793_s), CU.scalarOut(x6386_b7273_x6410_b7281_s)))
      Stage(operands=List(x6389, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_6795_s)))
    }
    val x6411_1 = Pipeline(name="x6411_1",parent=x6411) { implicit CU => 
      val x6393 = CU.temp
      val x6394 = CU.temp
      val x6392 = CU.temp
      val x6391 = CU.temp
      val x6390 =  ScalarFIFO(size=1).wtPort(bus_6795_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6390), Const(4)), op=FixDiv, results=List(CU.scalarOut(x6386_b7274_x6410_b7282_s)))
      Stage(operands=List(CU.load(x6390), Const(64)), op=FixMod, results=List(x6391))
      Stage(operands=List(x6391, Const(0)), op=FixEql, results=List(x6392))
      Stage(operands=List(Const(64), x6391), op=FixSub, results=List(x6393))
      Stage(operands=List(x6392, Const(0), x6393), op=Mux, results=List(x6394, CU.scalarOut(bus_6801_s)))
      Stage(operands=List(x6394, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6802_s)))
    }
    val x6411_2 = Pipeline(name="x6411_2",parent=x6411) { implicit CU => 
      val x6407 = CU.temp
      val x6398 = CU.temp
      val x6396 = CU.temp
      val x6404 =  ScalarFIFO(size=1).wtPort(bus_6793_s)
      val x6406 =  ScalarFIFO(size=1).wtPort(bus_6802_s)
      val x6394 =  ScalarFIFO(size=1).wtPort(bus_6801_s)
      val x6395 =  ScalarFIFO(size=1).wtPort(bus_6792_s)
      val x6389 =  ScalarFIFO(size=1).wtPort(bus_6790_s)
      val x6388 =  ScalarBuffer().wtPort(x6388_argin)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x6404)), op=FixAdd, results=List(x6407))
      Stage(operands=List(x6407, CU.load(x6406)), op=FixAdd, results=List(CU.scalarOut(x6386_b7272_x6410_b7280_s)))
      Stage(operands=List(Const(80), CU.load(x6395)), op=FixAdd, results=List(x6396))
      Stage(operands=List(x6396, CU.load(x6394)), op=FixAdd, results=List(CU.scalarOut(x6385_b7271_x6403_b7279_s)))
      Stage(operands=List(CU.load(x6389), CU.load(x6395)), op=FixSub, results=List(x6398))
      Stage(operands=List(x6398, CU.load(x6388)), op=FixAdd, results=List(CU.scalarOut(x6385_b7270_x6403_b7278_s)))
    }
    val x6412 = MemoryController(name="x6412",parent=x6440,offchip=x5877_oc, mctpe=TileLoad) { implicit CU => 
      val x6385_b7271_x6412 =  ScalarFIFO(name="size",size=1).wtPort(x6385_b7271_x6403_b7279_s)
      val x6385_b7270_x6412 =  ScalarFIFO(name="offset",size=1).wtPort(x6385_b7270_x6403_b7278_s)
      CU.newVout("data", x6387_x6412_data_v)
    }
    val x6439 = Sequential(name="x6439",parent=x6440) { implicit CU => 
    }
    val x6423_0 = Pipeline(name="x6423_0",parent=x6439) { implicit CU => 
      val x6386_b7272_x6416_b7275 =  ScalarFIFO(size=16).wtPort(x6386_b7272_x6410_b7280_s)
      val x6386_b7274_x6416_b7277 =  ScalarFIFO(size=16).wtPort(x6386_b7274_x6410_b7282_s)
      val x6386_b7273_x6416_b7276 =  ScalarFIFO(size=16).wtPort(x6386_b7273_x6410_b7281_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6386_b7273_x6416_b7276)), op=Bypass, results=List(CU.scalarOut(x6413_x6418_s)))
      Stage(operands=List(CU.load(x6386_b7274_x6416_b7277)), op=Bypass, results=List(CU.scalarOut(x6414_x6420_s)))
      Stage(operands=List(CU.load(x6386_b7272_x6416_b7275)), op=Bypass, results=List(CU.scalarOut(x6415_x6422_s)))
    }
    val x6438 = Pipeline(name="x6438",parent=x6439) { implicit CU => 
      val x6414_x6428 =  ScalarBuffer().wtPort(x6414_x6420_s)
      val x6413_x6427 =  ScalarBuffer().wtPort(x6413_x6418_s)
      val x6415_x6424 =  ScalarBuffer().wtPort(x6415_x6422_s)
      val ctr19 = Counter(min=Const(0), max=x6415_x6424.load, step=Const(1), par=16) // Counter
      val x6426 = CounterChain(name = "x6426", ctr19).iter(1)
      var stage: List[Stage] = Nil
    }
    val x6465 = StreamController(name="x6465",parent=x7083) { implicit CU => 
      val ctr20 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x6442 = CounterChain(name = "x6442", ctr20).iter(20)
    }
    val x6455_0 = Pipeline(name="x6455_0",parent=x6465) { implicit CU => 
      val x6446 = CU.temp
      val x6447 = CU.temp
      val x6448 = CU.temp
      val x6445 =  ScalarBuffer().wtPort(x6445_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      val x6442 = CounterChain.copy("x6465", "x6442")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), CU.ctr(x6442(0))), op=FixAdd, results=List(x6446))
      Stage(operands=List(x6446, Const(96)), op=FixMul, results=List(x6447))
      Stage(operands=List(x6447, Const(4)), op=FixMul, results=List(x6448))
      Stage(operands=List(x6448, CU.load(x6445)), op=FixAdd, results=List(CU.scalarOut(x6443_b7283_x6454_b7285_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x6443_b7284_x6454_b7286_s)))
    }
    val x6456 = MemoryController(name="x6456",parent=x6465,offchip=x5875_oc, mctpe=TileLoad) { implicit CU => 
      val x6443_b7284_x6456 =  ScalarFIFO(name="size",size=1).wtPort(x6443_b7284_x6454_b7286_s)
      val x6443_b7283_x6456 =  ScalarFIFO(name="offset",size=1).wtPort(x6443_b7283_x6454_b7285_s)
      CU.newVout("data", x6444_x6456_data_v)
    }
    val x6464 = Pipeline(name="x6464",parent=x6465) { implicit CU => 
      val ctr21 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6458 = CounterChain(name = "x6458", ctr21).iter(6)
      var stage: List[Stage] = Nil
    }
    val x6470_0 = Pipeline(name="x6470_0",parent=x7083) { implicit CU => 
      val x6467 = CU.temp
      val x5872_x6466 =  ScalarBuffer().wtPort(x5872_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5872_x6466), CU.ctr(x5925(0))), op=FixSub, results=List(x6467))
      Stage(operands=List(x6467, Const(20)), op=FixMin, results=List(CU.scalarOut(x5947_x6469_s)))
    }
    val x6527 = StreamController(name="x6527",parent=x7083) { implicit CU => 
    }
    val x6498 = StreamController(name="x6498",parent=x6527) { implicit CU => 
    }
    val x6498_0 = Pipeline(name="x6498_0",parent=x6498) { implicit CU => 
      val x6476 = CU.temp
      val x6482 = CU.temp
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), Const(4)), op=FixMul, results=List(x6476, CU.scalarOut(bus_6823_s)))
      Stage(operands=List(x6476, Const(64)), op=FixMod, results=List(x6482, CU.scalarOut(bus_6825_s)))
      Stage(operands=List(x6482, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6826_s), CU.scalarOut(x6473_b7292_x6497_b7300_s)))
      Stage(operands=List(x6476, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_6828_s)))
    }
    val x6498_1 = Pipeline(name="x6498_1",parent=x6498) { implicit CU => 
      val x6479 = CU.temp
      val x6480 = CU.temp
      val x6481 = CU.temp
      val x6478 = CU.temp
      val x6477 =  ScalarFIFO(size=1).wtPort(bus_6828_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6477), Const(4)), op=FixDiv, results=List(CU.scalarOut(x6473_b7293_x6497_b7301_s)))
      Stage(operands=List(CU.load(x6477), Const(64)), op=FixMod, results=List(x6478))
      Stage(operands=List(x6478, Const(0)), op=FixEql, results=List(x6479))
      Stage(operands=List(Const(64), x6478), op=FixSub, results=List(x6480))
      Stage(operands=List(x6479, Const(0), x6480), op=Mux, results=List(x6481, CU.scalarOut(bus_6834_s)))
      Stage(operands=List(x6481, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6835_s)))
    }
    val x6498_2 = Pipeline(name="x6498_2",parent=x6498) { implicit CU => 
      val x6485 = CU.temp
      val x6483 = CU.temp
      val x6494 = CU.temp
      val x6481 =  ScalarFIFO(size=1).wtPort(bus_6834_s)
      val x6475 =  ScalarBuffer().wtPort(x6475_argin)
      val x6491 =  ScalarFIFO(size=1).wtPort(bus_6826_s)
      val x6493 =  ScalarFIFO(size=1).wtPort(bus_6835_s)
      val x6482 =  ScalarFIFO(size=1).wtPort(bus_6825_s)
      val x6476 =  ScalarFIFO(size=1).wtPort(bus_6823_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x6491)), op=FixAdd, results=List(x6494))
      Stage(operands=List(x6494, CU.load(x6493)), op=FixAdd, results=List(CU.scalarOut(x6473_b7291_x6497_b7299_s)))
      Stage(operands=List(Const(80), CU.load(x6482)), op=FixAdd, results=List(x6483))
      Stage(operands=List(x6483, CU.load(x6481)), op=FixAdd, results=List(CU.scalarOut(x6472_b7290_x6490_b7298_s)))
      Stage(operands=List(CU.load(x6476), CU.load(x6482)), op=FixSub, results=List(x6485))
      Stage(operands=List(x6485, CU.load(x6475)), op=FixAdd, results=List(CU.scalarOut(x6472_b7289_x6490_b7297_s)))
    }
    val x6499 = MemoryController(name="x6499",parent=x6527,offchip=x5877_oc, mctpe=TileLoad) { implicit CU => 
      val x6472_b7290_x6499 =  ScalarFIFO(name="size",size=1).wtPort(x6472_b7290_x6490_b7298_s)
      val x6472_b7289_x6499 =  ScalarFIFO(name="offset",size=1).wtPort(x6472_b7289_x6490_b7297_s)
      CU.newVout("data", x6474_x6499_data_v)
    }
    val x6526 = Sequential(name="x6526",parent=x6527) { implicit CU => 
    }
    val x6510_0 = Pipeline(name="x6510_0",parent=x6526) { implicit CU => 
      val x6473_b7293_x6503_b7296 =  ScalarFIFO(size=16).wtPort(x6473_b7293_x6497_b7301_s)
      val x6473_b7292_x6503_b7295 =  ScalarFIFO(size=16).wtPort(x6473_b7292_x6497_b7300_s)
      val x6473_b7291_x6503_b7294 =  ScalarFIFO(size=16).wtPort(x6473_b7291_x6497_b7299_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6473_b7292_x6503_b7295)), op=Bypass, results=List(CU.scalarOut(x6500_x6505_s)))
      Stage(operands=List(CU.load(x6473_b7293_x6503_b7296)), op=Bypass, results=List(CU.scalarOut(x6501_x6507_s)))
      Stage(operands=List(CU.load(x6473_b7291_x6503_b7294)), op=Bypass, results=List(CU.scalarOut(x6502_x6509_s)))
    }
    val x6525 = Pipeline(name="x6525",parent=x6526) { implicit CU => 
      val x6501_x6515 =  ScalarBuffer().wtPort(x6501_x6507_s)
      val x6500_x6514 =  ScalarBuffer().wtPort(x6500_x6505_s)
      val x6502_x6511 =  ScalarBuffer().wtPort(x6502_x6509_s)
      val ctr22 = Counter(min=Const(0), max=x6502_x6511.load, step=Const(1), par=16) // Counter
      val x6513 = CounterChain(name = "x6513", ctr22).iter(1)
      var stage: List[Stage] = Nil
    }
    val x6552 = StreamController(name="x6552",parent=x7083) { implicit CU => 
      val ctr23 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x6529 = CounterChain(name = "x6529", ctr23).iter(20)
    }
    val x6542_0 = Pipeline(name="x6542_0",parent=x6552) { implicit CU => 
      val x6533 = CU.temp
      val x6535 = CU.temp
      val x6534 = CU.temp
      val x6532 =  ScalarBuffer().wtPort(x6532_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      val x6529 = CounterChain.copy("x6552", "x6529")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), CU.ctr(x6529(0))), op=FixAdd, results=List(x6533))
      Stage(operands=List(x6533, Const(96)), op=FixMul, results=List(x6534))
      Stage(operands=List(x6534, Const(4)), op=FixMul, results=List(x6535))
      Stage(operands=List(x6535, CU.load(x6532)), op=FixAdd, results=List(CU.scalarOut(x6530_b7302_x6541_b7304_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x6530_b7303_x6541_b7305_s)))
    }
    val x6543 = MemoryController(name="x6543",parent=x6552,offchip=x5875_oc, mctpe=TileLoad) { implicit CU => 
      val x6530_b7302_x6543 =  ScalarFIFO(name="offset",size=1).wtPort(x6530_b7302_x6541_b7304_s)
      val x6530_b7303_x6543 =  ScalarFIFO(name="size",size=1).wtPort(x6530_b7303_x6541_b7305_s)
      CU.newVout("data", x6531_x6543_data_v)
    }
    val x6551 = Pipeline(name="x6551",parent=x6552) { implicit CU => 
      val ctr24 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6545 = CounterChain(name = "x6545", ctr24).iter(6)
      var stage: List[Stage] = Nil
    }
    val x6557_0 = Pipeline(name="x6557_0",parent=x7083) { implicit CU => 
      val x6554 = CU.temp
      val x5872_x6553 =  ScalarBuffer().wtPort(x5872_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5872_x6553), CU.ctr(x5925(0))), op=FixSub, results=List(x6554))
      Stage(operands=List(x6554, Const(20)), op=FixMin, results=List(CU.scalarOut(x5948_x6556_s)))
    }
    val x6614 = StreamController(name="x6614",parent=x7083) { implicit CU => 
    }
    val x6585 = StreamController(name="x6585",parent=x6614) { implicit CU => 
    }
    val x6585_0 = Pipeline(name="x6585_0",parent=x6585) { implicit CU => 
      val x6563 = CU.temp
      val x6569 = CU.temp
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), Const(4)), op=FixMul, results=List(x6563, CU.scalarOut(bus_6856_s)))
      Stage(operands=List(x6563, Const(64)), op=FixMod, results=List(x6569, CU.scalarOut(bus_6858_s)))
      Stage(operands=List(x6569, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6859_s), CU.scalarOut(x6560_b7311_x6584_b7319_s)))
      Stage(operands=List(x6563, Const(80)), op=FixAdd, results=List(CU.scalarOut(bus_6861_s)))
    }
    val x6585_1 = Pipeline(name="x6585_1",parent=x6585) { implicit CU => 
      val x6565 = CU.temp
      val x6567 = CU.temp
      val x6566 = CU.temp
      val x6568 = CU.temp
      val x6564 =  ScalarFIFO(size=1).wtPort(bus_6861_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6564), Const(4)), op=FixDiv, results=List(CU.scalarOut(x6560_b7312_x6584_b7320_s)))
      Stage(operands=List(CU.load(x6564), Const(64)), op=FixMod, results=List(x6565))
      Stage(operands=List(x6565, Const(0)), op=FixEql, results=List(x6566))
      Stage(operands=List(Const(64), x6565), op=FixSub, results=List(x6567))
      Stage(operands=List(x6566, Const(0), x6567), op=Mux, results=List(x6568, CU.scalarOut(bus_6867_s)))
      Stage(operands=List(x6568, Const(4)), op=FixDiv, results=List(CU.scalarOut(bus_6868_s)))
    }
    val x6585_2 = Pipeline(name="x6585_2",parent=x6585) { implicit CU => 
      val x6570 = CU.temp
      val x6581 = CU.temp
      val x6572 = CU.temp
      val x6562 =  ScalarBuffer().wtPort(x6562_argin)
      val x6580 =  ScalarFIFO(size=1).wtPort(bus_6868_s)
      val x6569 =  ScalarFIFO(size=1).wtPort(bus_6858_s)
      val x6578 =  ScalarFIFO(size=1).wtPort(bus_6859_s)
      val x6568 =  ScalarFIFO(size=1).wtPort(bus_6867_s)
      val x6563 =  ScalarFIFO(size=1).wtPort(bus_6856_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(Const(20), CU.load(x6578)), op=FixAdd, results=List(x6581))
      Stage(operands=List(x6581, CU.load(x6580)), op=FixAdd, results=List(CU.scalarOut(x6560_b7310_x6584_b7318_s)))
      Stage(operands=List(Const(80), CU.load(x6569)), op=FixAdd, results=List(x6570))
      Stage(operands=List(x6570, CU.load(x6568)), op=FixAdd, results=List(CU.scalarOut(x6559_b7309_x6577_b7317_s)))
      Stage(operands=List(CU.load(x6563), CU.load(x6569)), op=FixSub, results=List(x6572))
      Stage(operands=List(x6572, CU.load(x6562)), op=FixAdd, results=List(CU.scalarOut(x6559_b7308_x6577_b7316_s)))
    }
    val x6586 = MemoryController(name="x6586",parent=x6614,offchip=x5877_oc, mctpe=TileLoad) { implicit CU => 
      val x6559_b7308_x6586 =  ScalarFIFO(name="offset",size=1).wtPort(x6559_b7308_x6577_b7316_s)
      val x6559_b7309_x6586 =  ScalarFIFO(name="size",size=1).wtPort(x6559_b7309_x6577_b7317_s)
      CU.newVout("data", x6561_x6586_data_v)
    }
    val x6613 = Sequential(name="x6613",parent=x6614) { implicit CU => 
    }
    val x6597_0 = Pipeline(name="x6597_0",parent=x6613) { implicit CU => 
      val x6560_b7311_x6590_b7314 =  ScalarFIFO(size=16).wtPort(x6560_b7311_x6584_b7319_s)
      val x6560_b7310_x6590_b7313 =  ScalarFIFO(size=16).wtPort(x6560_b7310_x6584_b7318_s)
      val x6560_b7312_x6590_b7315 =  ScalarFIFO(size=16).wtPort(x6560_b7312_x6584_b7320_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6560_b7311_x6590_b7314)), op=Bypass, results=List(CU.scalarOut(x6587_x6592_s)))
      Stage(operands=List(CU.load(x6560_b7312_x6590_b7315)), op=Bypass, results=List(CU.scalarOut(x6588_x6594_s)))
      Stage(operands=List(CU.load(x6560_b7310_x6590_b7313)), op=Bypass, results=List(CU.scalarOut(x6589_x6596_s)))
    }
    val x6612 = Pipeline(name="x6612",parent=x6613) { implicit CU => 
      val x6588_x6602 =  ScalarBuffer().wtPort(x6588_x6594_s)
      val x6587_x6601 =  ScalarBuffer().wtPort(x6587_x6592_s)
      val x6589_x6598 =  ScalarBuffer().wtPort(x6589_x6596_s)
      val ctr25 = Counter(min=Const(0), max=x6589_x6598.load, step=Const(1), par=16) // Counter
      val x6600 = CounterChain(name = "x6600", ctr25).iter(1)
      var stage: List[Stage] = Nil
    }
    val x6639 = StreamController(name="x6639",parent=x7083) { implicit CU => 
      val ctr26 = Counter(min=Const(0), max=Const(20), step=Const(1), par=1) // Counter
      val x6616 = CounterChain(name = "x6616", ctr26).iter(20)
    }
    val x6629_0 = Pipeline(name="x6629_0",parent=x6639) { implicit CU => 
      val x6621 = CU.temp
      val x6622 = CU.temp
      val x6620 = CU.temp
      val x6619 =  ScalarBuffer().wtPort(x6619_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      val x6616 = CounterChain.copy("x6639", "x6616")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x5925(0)), CU.ctr(x6616(0))), op=FixAdd, results=List(x6620))
      Stage(operands=List(x6620, Const(96)), op=FixMul, results=List(x6621))
      Stage(operands=List(x6621, Const(4)), op=FixMul, results=List(x6622))
      Stage(operands=List(x6622, CU.load(x6619)), op=FixAdd, results=List(CU.scalarOut(x6617_b7321_x6628_b7323_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x6617_b7322_x6628_b7324_s)))
    }
    val x6630 = MemoryController(name="x6630",parent=x6639,offchip=x5875_oc, mctpe=TileLoad) { implicit CU => 
      val x6617_b7322_x6630 =  ScalarFIFO(name="size",size=1).wtPort(x6617_b7322_x6628_b7324_s)
      val x6617_b7321_x6630 =  ScalarFIFO(name="offset",size=1).wtPort(x6617_b7321_x6628_b7323_s)
      CU.newVout("data", x6618_x6630_data_v)
    }
    val x6638 = Pipeline(name="x6638",parent=x6639) { implicit CU => 
      val ctr27 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6632 = CounterChain(name = "x6632", ctr27).iter(6)
      var stage: List[Stage] = Nil
    }
    val x6644_0 = Pipeline(name="x6644_0",parent=x7083) { implicit CU => 
      val x6641 = CU.temp
      val x5872_x6640 =  ScalarBuffer().wtPort(x5872_argin)
      val x5925 = CounterChain.copy("x7083", "x5925")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x5872_x6640), CU.ctr(x5925(0))), op=FixSub, results=List(x6641))
      Stage(operands=List(x6641, Const(20)), op=FixMin, results=List(CU.scalarOut(x5949_x6643_s)))
    }
    val x6647_dsp0 = MemoryPipeline(name="x6647_dsp0",parent="x6701") { implicit CU => 
      val b7335 = CU.temp
      val b7407 = CU.temp
      val x6698_x6698 =  VectorFIFO(size=1).wtPort(x6647_x6698_v)
      val x6690 = CounterChain.copy("x6699_0", "x6690")
      val x7034 = CounterChain.copy("x7081", "x7034")
      val x6647_x7036 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6698_x6698.readPort).rdPort(x6647_x7036_x7081_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6690(0)), Const(96)), op=FixMul, results=List(b7335))
      WAStage(operands=List(b7335, CU.ctr(x6690(1))), op=FixAdd, results=List(x6647_x7036.writeAddr))
      RAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7407))
      RAStage(operands=List(b7407, CU.ctr(x7034(1))), op=FixAdd, results=List(x6647_x7036.readAddr))
    }
    val x6647_dsp1 = MemoryPipeline(name="x6647_dsp1",parent="x6701") { implicit CU => 
      val b7333 = CU.temp
      val b7335 = CU.temp
      val x6698_x6698 =  VectorFIFO(size=1).wtPort(x6647_x6698_v)
      val x6690 = CounterChain.copy("x6699_0", "x6690")
      val x6647_x6694 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6698_x6698.readPort).rdPort(x6647_x6694_x6699_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6690(0)), Const(96)), op=FixMul, results=List(b7335))
      WAStage(operands=List(b7335, CU.ctr(x6690(1))), op=FixAdd, results=List(x6647_x6694.writeAddr))
      RAStage(operands=List(CU.ctr(x6690(0)), Const(96)), op=FixMul, results=List(b7333))
      RAStage(operands=List(b7333, CU.ctr(x6690(1))), op=FixAdd, results=List(x6647_x6694.readAddr))
    }
    val x6648_dsp0 = MemoryPipeline(name="x6648_dsp0",parent="x6748") { implicit CU => 
      val b7409 = CU.temp
      val b7345 = CU.temp
      val x6745_x6745 =  VectorFIFO(size=1).wtPort(x6648_x6745_v)
      val x6737 = CounterChain.copy("x6746_0", "x6737")
      val x7034 = CounterChain.copy("x7081", "x7034")
      val x6648_x7037 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6745_x6745.readPort).rdPort(x6648_x7037_x7081_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6737(0)), Const(96)), op=FixMul, results=List(b7345))
      WAStage(operands=List(b7345, CU.ctr(x6737(1))), op=FixAdd, results=List(x6648_x7037.writeAddr))
      RAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7409))
      RAStage(operands=List(b7409, CU.ctr(x7034(1))), op=FixAdd, results=List(x6648_x7037.readAddr))
    }
    val x6648_dsp1 = MemoryPipeline(name="x6648_dsp1",parent="x6748") { implicit CU => 
      val b7343 = CU.temp
      val b7345 = CU.temp
      val x6745_x6745 =  VectorFIFO(size=1).wtPort(x6648_x6745_v)
      val x6737 = CounterChain.copy("x6746_0", "x6737")
      val x6648_x6741 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6745_x6745.readPort).rdPort(x6648_x6741_x6746_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6737(0)), Const(96)), op=FixMul, results=List(b7345))
      WAStage(operands=List(b7345, CU.ctr(x6737(1))), op=FixAdd, results=List(x6648_x6741.writeAddr))
      RAStage(operands=List(CU.ctr(x6737(0)), Const(96)), op=FixMul, results=List(b7343))
      RAStage(operands=List(b7343, CU.ctr(x6737(1))), op=FixAdd, results=List(x6648_x6741.readAddr))
    }
    val x6649_dsp0 = MemoryPipeline(name="x6649_dsp0",parent="x6795") { implicit CU => 
      val b7411 = CU.temp
      val b7355 = CU.temp
      val x6792_x6792 =  VectorFIFO(size=1).wtPort(x6649_x6792_v)
      val x6784 = CounterChain.copy("x6793_0", "x6784")
      val x7034 = CounterChain.copy("x7081", "x7034")
      val x6649_x7038 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6792_x6792.readPort).rdPort(x6649_x7038_x7081_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6784(0)), Const(96)), op=FixMul, results=List(b7355))
      WAStage(operands=List(b7355, CU.ctr(x6784(1))), op=FixAdd, results=List(x6649_x7038.writeAddr))
      RAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7411))
      RAStage(operands=List(b7411, CU.ctr(x7034(1))), op=FixAdd, results=List(x6649_x7038.readAddr))
    }
    val x6649_dsp1 = MemoryPipeline(name="x6649_dsp1",parent="x6795") { implicit CU => 
      val b7353 = CU.temp
      val b7355 = CU.temp
      val x6792_x6792 =  VectorFIFO(size=1).wtPort(x6649_x6792_v)
      val x6784 = CounterChain.copy("x6793_0", "x6784")
      val x6649_x6788 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6792_x6792.readPort).rdPort(x6649_x6788_x6793_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6784(0)), Const(96)), op=FixMul, results=List(b7355))
      WAStage(operands=List(b7355, CU.ctr(x6784(1))), op=FixAdd, results=List(x6649_x6788.writeAddr))
      RAStage(operands=List(CU.ctr(x6784(0)), Const(96)), op=FixMul, results=List(b7353))
      RAStage(operands=List(b7353, CU.ctr(x6784(1))), op=FixAdd, results=List(x6649_x6788.readAddr))
    }
    val x6650_dsp0 = MemoryPipeline(name="x6650_dsp0",parent="x6842") { implicit CU => 
      val b7365 = CU.temp
      val b7413 = CU.temp
      val x6839_x6839 =  VectorFIFO(size=1).wtPort(x6650_x6839_v)
      val x6831 = CounterChain.copy("x6840_0", "x6831")
      val x7034 = CounterChain.copy("x7081", "x7034")
      val x6650_x7039 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6839_x6839.readPort).rdPort(x6650_x7039_x7081_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6831(0)), Const(96)), op=FixMul, results=List(b7365))
      WAStage(operands=List(b7365, CU.ctr(x6831(1))), op=FixAdd, results=List(x6650_x7039.writeAddr))
      RAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7413))
      RAStage(operands=List(b7413, CU.ctr(x7034(1))), op=FixAdd, results=List(x6650_x7039.readAddr))
    }
    val x6650_dsp1 = MemoryPipeline(name="x6650_dsp1",parent="x6842") { implicit CU => 
      val b7365 = CU.temp
      val b7363 = CU.temp
      val x6839_x6839 =  VectorFIFO(size=1).wtPort(x6650_x6839_v)
      val x6831 = CounterChain.copy("x6840_0", "x6831")
      val x6650_x6835 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6839_x6839.readPort).rdPort(x6650_x6835_x6840_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6831(0)), Const(96)), op=FixMul, results=List(b7365))
      WAStage(operands=List(b7365, CU.ctr(x6831(1))), op=FixAdd, results=List(x6650_x6835.writeAddr))
      RAStage(operands=List(CU.ctr(x6831(0)), Const(96)), op=FixMul, results=List(b7363))
      RAStage(operands=List(b7363, CU.ctr(x6831(1))), op=FixAdd, results=List(x6650_x6835.readAddr))
    }
    val x6651_dsp0 = MemoryPipeline(name="x6651_dsp0",parent="x6889") { implicit CU => 
      val b7415 = CU.temp
      val b7375 = CU.temp
      val x6886_x6886 =  VectorFIFO(size=1).wtPort(x6651_x6886_v)
      val x6878 = CounterChain.copy("x6887_0", "x6878")
      val x7034 = CounterChain.copy("x7081", "x7034")
      val x6651_x7040 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6886_x6886.readPort).rdPort(x6651_x7040_x7081_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6878(0)), Const(96)), op=FixMul, results=List(b7375))
      WAStage(operands=List(b7375, CU.ctr(x6878(1))), op=FixAdd, results=List(x6651_x7040.writeAddr))
      RAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7415))
      RAStage(operands=List(b7415, CU.ctr(x7034(1))), op=FixAdd, results=List(x6651_x7040.readAddr))
    }
    val x6651_dsp1 = MemoryPipeline(name="x6651_dsp1",parent="x6889") { implicit CU => 
      val b7373 = CU.temp
      val b7375 = CU.temp
      val x6886_x6886 =  VectorFIFO(size=1).wtPort(x6651_x6886_v)
      val x6878 = CounterChain.copy("x6887_0", "x6878")
      val x6651_x6882 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6886_x6886.readPort).rdPort(x6651_x6882_x6887_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6878(0)), Const(96)), op=FixMul, results=List(b7375))
      WAStage(operands=List(b7375, CU.ctr(x6878(1))), op=FixAdd, results=List(x6651_x6882.writeAddr))
      RAStage(operands=List(CU.ctr(x6878(0)), Const(96)), op=FixMul, results=List(b7373))
      RAStage(operands=List(b7373, CU.ctr(x6878(1))), op=FixAdd, results=List(x6651_x6882.readAddr))
    }
    val x6652_dsp0 = MemoryPipeline(name="x6652_dsp0",parent="x6936") { implicit CU => 
      val b7385 = CU.temp
      val b7417 = CU.temp
      val x6933_x6933 =  VectorFIFO(size=1).wtPort(x6652_x6933_v)
      val x6925 = CounterChain.copy("x6934_0", "x6925")
      val x7034 = CounterChain.copy("x7081", "x7034")
      val x6652_x7041 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6933_x6933.readPort).rdPort(x6652_x7041_x7081_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6925(0)), Const(96)), op=FixMul, results=List(b7385))
      WAStage(operands=List(b7385, CU.ctr(x6925(1))), op=FixAdd, results=List(x6652_x7041.writeAddr))
      RAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7417))
      RAStage(operands=List(b7417, CU.ctr(x7034(1))), op=FixAdd, results=List(x6652_x7041.readAddr))
    }
    val x6652_dsp1 = MemoryPipeline(name="x6652_dsp1",parent="x6936") { implicit CU => 
      val b7385 = CU.temp
      val b7383 = CU.temp
      val x6933_x6933 =  VectorFIFO(size=1).wtPort(x6652_x6933_v)
      val x6925 = CounterChain.copy("x6934_0", "x6925")
      val x6652_x6929 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6933_x6933.readPort).rdPort(x6652_x6929_x6934_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6925(0)), Const(96)), op=FixMul, results=List(b7385))
      WAStage(operands=List(b7385, CU.ctr(x6925(1))), op=FixAdd, results=List(x6652_x6929.writeAddr))
      RAStage(operands=List(CU.ctr(x6925(0)), Const(96)), op=FixMul, results=List(b7383))
      RAStage(operands=List(b7383, CU.ctr(x6925(1))), op=FixAdd, results=List(x6652_x6929.readAddr))
    }
    val x6653_dsp0 = MemoryPipeline(name="x6653_dsp0",parent="x6983") { implicit CU => 
      val b7419 = CU.temp
      val b7395 = CU.temp
      val x6980_x6980 =  VectorFIFO(size=1).wtPort(x6653_x6980_v)
      val x6972 = CounterChain.copy("x6981_0", "x6972")
      val x7034 = CounterChain.copy("x7081", "x7034")
      val x6653_x7042 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6980_x6980.readPort).rdPort(x6653_x7042_x7081_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6972(0)), Const(96)), op=FixMul, results=List(b7395))
      WAStage(operands=List(b7395, CU.ctr(x6972(1))), op=FixAdd, results=List(x6653_x7042.writeAddr))
      RAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7419))
      RAStage(operands=List(b7419, CU.ctr(x7034(1))), op=FixAdd, results=List(x6653_x7042.readAddr))
    }
    val x6653_dsp1 = MemoryPipeline(name="x6653_dsp1",parent="x6983") { implicit CU => 
      val b7393 = CU.temp
      val b7395 = CU.temp
      val x6980_x6980 =  VectorFIFO(size=1).wtPort(x6653_x6980_v)
      val x6972 = CounterChain.copy("x6981_0", "x6972")
      val x6653_x6976 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6980_x6980.readPort).rdPort(x6653_x6976_x6981_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6972(0)), Const(96)), op=FixMul, results=List(b7395))
      WAStage(operands=List(b7395, CU.ctr(x6972(1))), op=FixAdd, results=List(x6653_x6976.writeAddr))
      RAStage(operands=List(CU.ctr(x6972(0)), Const(96)), op=FixMul, results=List(b7393))
      RAStage(operands=List(b7393, CU.ctr(x6972(1))), op=FixAdd, results=List(x6653_x6976.readAddr))
    }
    val x6654_dsp0 = MemoryPipeline(name="x6654_dsp0",parent="x7030") { implicit CU => 
      val b7405 = CU.temp
      val b7421 = CU.temp
      val x7027_x7027 =  VectorFIFO(size=1).wtPort(x6654_x7027_v)
      val x7019 = CounterChain.copy("x7028_0", "x7019")
      val x7034 = CounterChain.copy("x7081", "x7034")
      val x6654_x7043 =  SRAM(size=9216,banking = Strided(1)).wtPort(x7027_x7027.readPort).rdPort(x6654_x7043_x7081_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7019(0)), Const(96)), op=FixMul, results=List(b7405))
      WAStage(operands=List(b7405, CU.ctr(x7019(1))), op=FixAdd, results=List(x6654_x7043.writeAddr))
      RAStage(operands=List(CU.ctr(x7034(0)), Const(96)), op=FixMul, results=List(b7421))
      RAStage(operands=List(b7421, CU.ctr(x7034(1))), op=FixAdd, results=List(x6654_x7043.readAddr))
    }
    val x6654_dsp1 = MemoryPipeline(name="x6654_dsp1",parent="x7030") { implicit CU => 
      val b7405 = CU.temp
      val b7403 = CU.temp
      val x7027_x7027 =  VectorFIFO(size=1).wtPort(x6654_x7027_v)
      val x7019 = CounterChain.copy("x7028_0", "x7019")
      val x6654_x7023 =  SRAM(size=9216,banking = Strided(1)).wtPort(x7027_x7027.readPort).rdPort(x6654_x7023_x7028_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7019(0)), Const(96)), op=FixMul, results=List(b7405))
      WAStage(operands=List(b7405, CU.ctr(x7019(1))), op=FixAdd, results=List(x6654_x7023.writeAddr))
      RAStage(operands=List(CU.ctr(x7019(0)), Const(96)), op=FixMul, results=List(b7403))
      RAStage(operands=List(b7403, CU.ctr(x7019(1))), op=FixAdd, results=List(x6654_x7023.readAddr))
    }
    val x6701 = MetaPipeline(name="x6701",parent=x7083) { implicit CU => 
      val x5942_x6655 =  ScalarBuffer().wtPort(x5942_x6034_s)
      val ctr28 = Counter(min=Const(0), max=x5942_x6655.load, step=Const(1), par=1) // Counter
      val x6657 = CounterChain(name = "x6657", ctr28).iter(1)
    }
    val x6658_dsp0 = MemoryPipeline(name="x6658_dsp0",parent="x6701") { implicit CU => 
      val x6674_x6674 =  VectorFIFO(size=1).wtPort(x6658_x6674_v)
      val x6661 = CounterChain.copy("x6675", "x6661")
      val x6678 = CounterChain.copy("x6687_0", "x6678")
      val x6658_x6683 =  SRAM(size=96,banking = Strided(1)).wtPort(x6674_x6674.readPort).rdPort(x6658_x6683_x6687_v).rdAddr(x6678(1)).wtAddr(x6661(0))
      var stage: List[Stage] = Nil
    }
    val x6658_dsp1 = MemoryPipeline(name="x6658_dsp1",parent="x6701") { implicit CU => 
      val x6674_x6674 =  VectorFIFO(size=1).wtPort(x6658_x6674_v)
      val x6661 = CounterChain.copy("x6675", "x6661")
      val x6678 = CounterChain.copy("x6687_0", "x6678")
      val x6658_x6682 =  SRAM(size=96,banking = Strided(1)).wtPort(x6674_x6674.readPort).rdPort(x6658_x6682_x6687_v).rdAddr(x6678(0)).wtAddr(x6661(0))
      var stage: List[Stage] = Nil
    }
    val x6659_dsp0 = MemoryPipeline(name="x6659_dsp0",parent="x6701") { implicit CU => 
      val b7329 = CU.temp
      val b7331 = CU.temp
      val x6686_x6686 =  VectorFIFO(size=1).wtPort(x6659_x6686_v)
      val x6678 = CounterChain.copy("x6687_0", "x6678")
      val x6690 = CounterChain.copy("x6699_0", "x6690")
      val x6659_x6693 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6686_x6686.readPort).rdPort(x6659_x6693_x6699_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6678(0)), Const(96)), op=FixMul, results=List(b7329))
      WAStage(operands=List(b7329, CU.ctr(x6678(1))), op=FixAdd, results=List(x6659_x6693.writeAddr))
      RAStage(operands=List(CU.ctr(x6690(0)), Const(96)), op=FixMul, results=List(b7331))
      RAStage(operands=List(b7331, CU.ctr(x6690(1))), op=FixAdd, results=List(x6659_x6693.readAddr))
    }
    val x6675 = StreamController(name="x6675",parent=x6701) { implicit CU => 
      val ctr29 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6661 = CounterChain(name = "x6661", ctr29).iter(6)
    }
    val x6675_0 = Pipeline(name="x6675_0",parent=x6675) { implicit CU => 
      val x6671 = CU.temp
      val x6665_x6665 =  VectorFIFO(size=1).wtPort(x5926_x6665_x6675_v)
      val x6667_x6667 =  VectorFIFO(size=1).wtPort(x5885_x6667_x6675_v)
      val x6666_x6666 =  VectorFIFO(size=1).wtPort(x5886_x6666_x6675_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6665_x6665), Const(1)), op=FixEql, results=List(x6671))
      Stage(operands=List(x6671, CU.load(x6666_x6666), CU.load(x6667_x6667)), op=Mux, results=List(CU.vecOut(bus_7014_v)))
    }
    val x6675_1 = Pipeline(name="x6675_1",parent=x6675) { implicit CU => 
      val x6672 =  VectorFIFO(size=1).wtPort(bus_7014_v)
      val x6664_x6664 =  VectorFIFO(size=1).wtPort(x5934_x6664_x6675_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6664_x6664), CU.load(x6672)), op=FixSub, results=List(CU.vecOut(x6658_x6674_v)))
    }
    val x6687_0 = Pipeline(name="x6687_0",parent=x6701) { implicit CU => 
      val x6683_x6683 =  VectorFIFO(size=1).wtPort(x6658_x6683_x6687_v)
      val x6682_x6682 =  VectorFIFO(size=1).wtPort(x6658_x6682_x6687_v)
      val ctr30 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr31 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6678 = CounterChain(name = "x6678", ctr30, ctr31).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6682_x6682), CU.load(x6683_x6683)), op=FixMul, results=List(CU.vecOut(x6659_x6686_v)))
    }
    val x6699_0 = Pipeline(name="x6699_0",parent=x6701) { implicit CU => 
      val x6694_x6694 =  VectorFIFO(size=1).wtPort(x6647_x6694_x6699_v)
      val x6693_x6693 =  VectorFIFO(size=1).wtPort(x6659_x6693_x6699_v)
      val ctr32 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr33 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6690 = CounterChain(name = "x6690", ctr32, ctr33).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6693_x6693), CU.load(x6694_x6694)), op=FixAdd, results=List(CU.vecOut(x6647_x6698_v)))
    }
    val x6748 = MetaPipeline(name="x6748",parent=x7083) { implicit CU => 
      val x5943_x6702 =  ScalarBuffer().wtPort(x5943_x6121_s)
      val ctr34 = Counter(min=Const(0), max=x5943_x6702.load, step=Const(1), par=1) // Counter
      val x6704 = CounterChain(name = "x6704", ctr34).iter(1)
    }
    val x6705_dsp0 = MemoryPipeline(name="x6705_dsp0",parent="x6748") { implicit CU => 
      val x6721_x6721 =  VectorFIFO(size=1).wtPort(x6705_x6721_v)
      val x6708 = CounterChain.copy("x6722", "x6708")
      val x6725 = CounterChain.copy("x6734_0", "x6725")
      val x6705_x6730 =  SRAM(size=96,banking = Strided(1)).wtPort(x6721_x6721.readPort).rdPort(x6705_x6730_x6734_v).rdAddr(x6725(1)).wtAddr(x6708(0))
      var stage: List[Stage] = Nil
    }
    val x6705_dsp1 = MemoryPipeline(name="x6705_dsp1",parent="x6748") { implicit CU => 
      val x6721_x6721 =  VectorFIFO(size=1).wtPort(x6705_x6721_v)
      val x6708 = CounterChain.copy("x6722", "x6708")
      val x6725 = CounterChain.copy("x6734_0", "x6725")
      val x6705_x6729 =  SRAM(size=96,banking = Strided(1)).wtPort(x6721_x6721.readPort).rdPort(x6705_x6729_x6734_v).rdAddr(x6725(0)).wtAddr(x6708(0))
      var stage: List[Stage] = Nil
    }
    val x6706_dsp0 = MemoryPipeline(name="x6706_dsp0",parent="x6748") { implicit CU => 
      val b7339 = CU.temp
      val b7341 = CU.temp
      val x6733_x6733 =  VectorFIFO(size=1).wtPort(x6706_x6733_v)
      val x6725 = CounterChain.copy("x6734_0", "x6725")
      val x6737 = CounterChain.copy("x6746_0", "x6737")
      val x6706_x6740 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6733_x6733.readPort).rdPort(x6706_x6740_x6746_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6725(0)), Const(96)), op=FixMul, results=List(b7339))
      WAStage(operands=List(b7339, CU.ctr(x6725(1))), op=FixAdd, results=List(x6706_x6740.writeAddr))
      RAStage(operands=List(CU.ctr(x6737(0)), Const(96)), op=FixMul, results=List(b7341))
      RAStage(operands=List(b7341, CU.ctr(x6737(1))), op=FixAdd, results=List(x6706_x6740.readAddr))
    }
    val x6722 = StreamController(name="x6722",parent=x6748) { implicit CU => 
      val ctr35 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6708 = CounterChain(name = "x6708", ctr35).iter(6)
    }
    val x6722_0 = Pipeline(name="x6722_0",parent=x6722) { implicit CU => 
      val x6718 = CU.temp
      val x6713_x6713 =  VectorFIFO(size=1).wtPort(x5886_x6713_x6722_v)
      val x6712_x6712 =  VectorFIFO(size=1).wtPort(x5927_x6712_x6722_v)
      val x6714_x6714 =  VectorFIFO(size=1).wtPort(x5885_x6714_x6722_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6712_x6712), Const(1)), op=FixEql, results=List(x6718))
      Stage(operands=List(x6718, CU.load(x6713_x6713), CU.load(x6714_x6714)), op=Mux, results=List(CU.vecOut(bus_7037_v)))
    }
    val x6722_1 = Pipeline(name="x6722_1",parent=x6722) { implicit CU => 
      val x6719 =  VectorFIFO(size=1).wtPort(bus_7037_v)
      val x6711_x6711 =  VectorFIFO(size=1).wtPort(x5935_x6711_x6722_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6711_x6711), CU.load(x6719)), op=FixSub, results=List(CU.vecOut(x6705_x6721_v)))
    }
    val x6734_0 = Pipeline(name="x6734_0",parent=x6748) { implicit CU => 
      val x6730_x6730 =  VectorFIFO(size=1).wtPort(x6705_x6730_x6734_v)
      val x6729_x6729 =  VectorFIFO(size=1).wtPort(x6705_x6729_x6734_v)
      val ctr36 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr37 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6725 = CounterChain(name = "x6725", ctr36, ctr37).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6729_x6729), CU.load(x6730_x6730)), op=FixMul, results=List(CU.vecOut(x6706_x6733_v)))
    }
    val x6746_0 = Pipeline(name="x6746_0",parent=x6748) { implicit CU => 
      val x6740_x6740 =  VectorFIFO(size=1).wtPort(x6706_x6740_x6746_v)
      val x6741_x6741 =  VectorFIFO(size=1).wtPort(x6648_x6741_x6746_v)
      val ctr38 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr39 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6737 = CounterChain(name = "x6737", ctr38, ctr39).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6740_x6740), CU.load(x6741_x6741)), op=FixAdd, results=List(CU.vecOut(x6648_x6745_v)))
    }
    val x6795 = MetaPipeline(name="x6795",parent=x7083) { implicit CU => 
      val x5944_x6749 =  ScalarBuffer().wtPort(x5944_x6208_s)
      val ctr40 = Counter(min=Const(0), max=x5944_x6749.load, step=Const(1), par=1) // Counter
      val x6751 = CounterChain(name = "x6751", ctr40).iter(1)
    }
    val x6752_dsp0 = MemoryPipeline(name="x6752_dsp0",parent="x6795") { implicit CU => 
      val x6768_x6768 =  VectorFIFO(size=1).wtPort(x6752_x6768_v)
      val x6755 = CounterChain.copy("x6769", "x6755")
      val x6772 = CounterChain.copy("x6781_0", "x6772")
      val x6752_x6777 =  SRAM(size=96,banking = Strided(1)).wtPort(x6768_x6768.readPort).rdPort(x6752_x6777_x6781_v).rdAddr(x6772(1)).wtAddr(x6755(0))
      var stage: List[Stage] = Nil
    }
    val x6752_dsp1 = MemoryPipeline(name="x6752_dsp1",parent="x6795") { implicit CU => 
      val x6768_x6768 =  VectorFIFO(size=1).wtPort(x6752_x6768_v)
      val x6755 = CounterChain.copy("x6769", "x6755")
      val x6772 = CounterChain.copy("x6781_0", "x6772")
      val x6752_x6776 =  SRAM(size=96,banking = Strided(1)).wtPort(x6768_x6768.readPort).rdPort(x6752_x6776_x6781_v).rdAddr(x6772(0)).wtAddr(x6755(0))
      var stage: List[Stage] = Nil
    }
    val x6753_dsp0 = MemoryPipeline(name="x6753_dsp0",parent="x6795") { implicit CU => 
      val b7351 = CU.temp
      val b7349 = CU.temp
      val x6780_x6780 =  VectorFIFO(size=1).wtPort(x6753_x6780_v)
      val x6772 = CounterChain.copy("x6781_0", "x6772")
      val x6784 = CounterChain.copy("x6793_0", "x6784")
      val x6753_x6787 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6780_x6780.readPort).rdPort(x6753_x6787_x6793_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6772(0)), Const(96)), op=FixMul, results=List(b7349))
      WAStage(operands=List(b7349, CU.ctr(x6772(1))), op=FixAdd, results=List(x6753_x6787.writeAddr))
      RAStage(operands=List(CU.ctr(x6784(0)), Const(96)), op=FixMul, results=List(b7351))
      RAStage(operands=List(b7351, CU.ctr(x6784(1))), op=FixAdd, results=List(x6753_x6787.readAddr))
    }
    val x6769 = StreamController(name="x6769",parent=x6795) { implicit CU => 
      val ctr41 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6755 = CounterChain(name = "x6755", ctr41).iter(6)
    }
    val x6769_0 = Pipeline(name="x6769_0",parent=x6769) { implicit CU => 
      val x6765 = CU.temp
      val x6761_x6761 =  VectorFIFO(size=1).wtPort(x5885_x6761_x6769_v)
      val x6760_x6760 =  VectorFIFO(size=1).wtPort(x5886_x6760_x6769_v)
      val x6759_x6759 =  VectorFIFO(size=1).wtPort(x5928_x6759_x6769_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6759_x6759), Const(1)), op=FixEql, results=List(x6765))
      Stage(operands=List(x6765, CU.load(x6760_x6760), CU.load(x6761_x6761)), op=Mux, results=List(CU.vecOut(bus_7060_v)))
    }
    val x6769_1 = Pipeline(name="x6769_1",parent=x6769) { implicit CU => 
      val x6758_x6758 =  VectorFIFO(size=1).wtPort(x5936_x6758_x6769_v)
      val x6766 =  VectorFIFO(size=1).wtPort(bus_7060_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6758_x6758), CU.load(x6766)), op=FixSub, results=List(CU.vecOut(x6752_x6768_v)))
    }
    val x6781_0 = Pipeline(name="x6781_0",parent=x6795) { implicit CU => 
      val x6776_x6776 =  VectorFIFO(size=1).wtPort(x6752_x6776_x6781_v)
      val x6777_x6777 =  VectorFIFO(size=1).wtPort(x6752_x6777_x6781_v)
      val ctr42 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr43 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6772 = CounterChain(name = "x6772", ctr42, ctr43).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6776_x6776), CU.load(x6777_x6777)), op=FixMul, results=List(CU.vecOut(x6753_x6780_v)))
    }
    val x6793_0 = Pipeline(name="x6793_0",parent=x6795) { implicit CU => 
      val x6788_x6788 =  VectorFIFO(size=1).wtPort(x6649_x6788_x6793_v)
      val x6787_x6787 =  VectorFIFO(size=1).wtPort(x6753_x6787_x6793_v)
      val ctr44 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr45 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6784 = CounterChain(name = "x6784", ctr44, ctr45).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6787_x6787), CU.load(x6788_x6788)), op=FixAdd, results=List(CU.vecOut(x6649_x6792_v)))
    }
    val x6842 = MetaPipeline(name="x6842",parent=x7083) { implicit CU => 
      val x5945_x6796 =  ScalarBuffer().wtPort(x5945_x6295_s)
      val ctr46 = Counter(min=Const(0), max=x5945_x6796.load, step=Const(1), par=1) // Counter
      val x6798 = CounterChain(name = "x6798", ctr46).iter(1)
    }
    val x6799_dsp0 = MemoryPipeline(name="x6799_dsp0",parent="x6842") { implicit CU => 
      val x6815_x6815 =  VectorFIFO(size=1).wtPort(x6799_x6815_v)
      val x6802 = CounterChain.copy("x6816", "x6802")
      val x6819 = CounterChain.copy("x6828_0", "x6819")
      val x6799_x6824 =  SRAM(size=96,banking = Strided(1)).wtPort(x6815_x6815.readPort).rdPort(x6799_x6824_x6828_v).rdAddr(x6819(1)).wtAddr(x6802(0))
      var stage: List[Stage] = Nil
    }
    val x6799_dsp1 = MemoryPipeline(name="x6799_dsp1",parent="x6842") { implicit CU => 
      val x6815_x6815 =  VectorFIFO(size=1).wtPort(x6799_x6815_v)
      val x6802 = CounterChain.copy("x6816", "x6802")
      val x6819 = CounterChain.copy("x6828_0", "x6819")
      val x6799_x6823 =  SRAM(size=96,banking = Strided(1)).wtPort(x6815_x6815.readPort).rdPort(x6799_x6823_x6828_v).rdAddr(x6819(0)).wtAddr(x6802(0))
      var stage: List[Stage] = Nil
    }
    val x6800_dsp0 = MemoryPipeline(name="x6800_dsp0",parent="x6842") { implicit CU => 
      val b7361 = CU.temp
      val b7359 = CU.temp
      val x6827_x6827 =  VectorFIFO(size=1).wtPort(x6800_x6827_v)
      val x6819 = CounterChain.copy("x6828_0", "x6819")
      val x6831 = CounterChain.copy("x6840_0", "x6831")
      val x6800_x6834 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6827_x6827.readPort).rdPort(x6800_x6834_x6840_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6819(0)), Const(96)), op=FixMul, results=List(b7359))
      WAStage(operands=List(b7359, CU.ctr(x6819(1))), op=FixAdd, results=List(x6800_x6834.writeAddr))
      RAStage(operands=List(CU.ctr(x6831(0)), Const(96)), op=FixMul, results=List(b7361))
      RAStage(operands=List(b7361, CU.ctr(x6831(1))), op=FixAdd, results=List(x6800_x6834.readAddr))
    }
    val x6816 = StreamController(name="x6816",parent=x6842) { implicit CU => 
      val ctr47 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6802 = CounterChain(name = "x6802", ctr47).iter(6)
    }
    val x6816_0 = Pipeline(name="x6816_0",parent=x6816) { implicit CU => 
      val x6812 = CU.temp
      val x6806_x6806 =  VectorFIFO(size=1).wtPort(x5929_x6806_x6816_v)
      val x6808_x6808 =  VectorFIFO(size=1).wtPort(x5885_x6808_x6816_v)
      val x6807_x6807 =  VectorFIFO(size=1).wtPort(x5886_x6807_x6816_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6806_x6806), Const(1)), op=FixEql, results=List(x6812))
      Stage(operands=List(x6812, CU.load(x6807_x6807), CU.load(x6808_x6808)), op=Mux, results=List(CU.vecOut(bus_7083_v)))
    }
    val x6816_1 = Pipeline(name="x6816_1",parent=x6816) { implicit CU => 
      val x6813 =  VectorFIFO(size=1).wtPort(bus_7083_v)
      val x6805_x6805 =  VectorFIFO(size=1).wtPort(x5937_x6805_x6816_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6805_x6805), CU.load(x6813)), op=FixSub, results=List(CU.vecOut(x6799_x6815_v)))
    }
    val x6828_0 = Pipeline(name="x6828_0",parent=x6842) { implicit CU => 
      val x6824_x6824 =  VectorFIFO(size=1).wtPort(x6799_x6824_x6828_v)
      val x6823_x6823 =  VectorFIFO(size=1).wtPort(x6799_x6823_x6828_v)
      val ctr48 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr49 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6819 = CounterChain(name = "x6819", ctr48, ctr49).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6823_x6823), CU.load(x6824_x6824)), op=FixMul, results=List(CU.vecOut(x6800_x6827_v)))
    }
    val x6840_0 = Pipeline(name="x6840_0",parent=x6842) { implicit CU => 
      val x6835_x6835 =  VectorFIFO(size=1).wtPort(x6650_x6835_x6840_v)
      val x6834_x6834 =  VectorFIFO(size=1).wtPort(x6800_x6834_x6840_v)
      val ctr50 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr51 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6831 = CounterChain(name = "x6831", ctr50, ctr51).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6834_x6834), CU.load(x6835_x6835)), op=FixAdd, results=List(CU.vecOut(x6650_x6839_v)))
    }
    val x6889 = MetaPipeline(name="x6889",parent=x7083) { implicit CU => 
      val x5946_x6843 =  ScalarBuffer().wtPort(x5946_x6382_s)
      val ctr52 = Counter(min=Const(0), max=x5946_x6843.load, step=Const(1), par=1) // Counter
      val x6845 = CounterChain(name = "x6845", ctr52).iter(1)
    }
    val x6846_dsp0 = MemoryPipeline(name="x6846_dsp0",parent="x6889") { implicit CU => 
      val x6862_x6862 =  VectorFIFO(size=1).wtPort(x6846_x6862_v)
      val x6849 = CounterChain.copy("x6863", "x6849")
      val x6866 = CounterChain.copy("x6875_0", "x6866")
      val x6846_x6871 =  SRAM(size=96,banking = Strided(1)).wtPort(x6862_x6862.readPort).rdPort(x6846_x6871_x6875_v).rdAddr(x6866(1)).wtAddr(x6849(0))
      var stage: List[Stage] = Nil
    }
    val x6846_dsp1 = MemoryPipeline(name="x6846_dsp1",parent="x6889") { implicit CU => 
      val x6862_x6862 =  VectorFIFO(size=1).wtPort(x6846_x6862_v)
      val x6849 = CounterChain.copy("x6863", "x6849")
      val x6866 = CounterChain.copy("x6875_0", "x6866")
      val x6846_x6870 =  SRAM(size=96,banking = Strided(1)).wtPort(x6862_x6862.readPort).rdPort(x6846_x6870_x6875_v).rdAddr(x6866(0)).wtAddr(x6849(0))
      var stage: List[Stage] = Nil
    }
    val x6847_dsp0 = MemoryPipeline(name="x6847_dsp0",parent="x6889") { implicit CU => 
      val b7369 = CU.temp
      val b7371 = CU.temp
      val x6874_x6874 =  VectorFIFO(size=1).wtPort(x6847_x6874_v)
      val x6866 = CounterChain.copy("x6875_0", "x6866")
      val x6878 = CounterChain.copy("x6887_0", "x6878")
      val x6847_x6881 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6874_x6874.readPort).rdPort(x6847_x6881_x6887_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6866(0)), Const(96)), op=FixMul, results=List(b7369))
      WAStage(operands=List(b7369, CU.ctr(x6866(1))), op=FixAdd, results=List(x6847_x6881.writeAddr))
      RAStage(operands=List(CU.ctr(x6878(0)), Const(96)), op=FixMul, results=List(b7371))
      RAStage(operands=List(b7371, CU.ctr(x6878(1))), op=FixAdd, results=List(x6847_x6881.readAddr))
    }
    val x6863 = StreamController(name="x6863",parent=x6889) { implicit CU => 
      val ctr53 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6849 = CounterChain(name = "x6849", ctr53).iter(6)
    }
    val x6863_0 = Pipeline(name="x6863_0",parent=x6863) { implicit CU => 
      val x6859 = CU.temp
      val x6853_x6853 =  VectorFIFO(size=1).wtPort(x5930_x6853_x6863_v)
      val x6855_x6855 =  VectorFIFO(size=1).wtPort(x5885_x6855_x6863_v)
      val x6854_x6854 =  VectorFIFO(size=1).wtPort(x5886_x6854_x6863_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6853_x6853), Const(1)), op=FixEql, results=List(x6859))
      Stage(operands=List(x6859, CU.load(x6854_x6854), CU.load(x6855_x6855)), op=Mux, results=List(CU.vecOut(bus_7106_v)))
    }
    val x6863_1 = Pipeline(name="x6863_1",parent=x6863) { implicit CU => 
      val x6860 =  VectorFIFO(size=1).wtPort(bus_7106_v)
      val x6852_x6852 =  VectorFIFO(size=1).wtPort(x5938_x6852_x6863_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6852_x6852), CU.load(x6860)), op=FixSub, results=List(CU.vecOut(x6846_x6862_v)))
    }
    val x6875_0 = Pipeline(name="x6875_0",parent=x6889) { implicit CU => 
      val x6871_x6871 =  VectorFIFO(size=1).wtPort(x6846_x6871_x6875_v)
      val x6870_x6870 =  VectorFIFO(size=1).wtPort(x6846_x6870_x6875_v)
      val ctr54 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr55 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6866 = CounterChain(name = "x6866", ctr54, ctr55).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6870_x6870), CU.load(x6871_x6871)), op=FixMul, results=List(CU.vecOut(x6847_x6874_v)))
    }
    val x6887_0 = Pipeline(name="x6887_0",parent=x6889) { implicit CU => 
      val x6882_x6882 =  VectorFIFO(size=1).wtPort(x6651_x6882_x6887_v)
      val x6881_x6881 =  VectorFIFO(size=1).wtPort(x6847_x6881_x6887_v)
      val ctr56 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr57 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6878 = CounterChain(name = "x6878", ctr56, ctr57).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6881_x6881), CU.load(x6882_x6882)), op=FixAdd, results=List(CU.vecOut(x6651_x6886_v)))
    }
    val x6936 = MetaPipeline(name="x6936",parent=x7083) { implicit CU => 
      val x5947_x6890 =  ScalarBuffer().wtPort(x5947_x6469_s)
      val ctr58 = Counter(min=Const(0), max=x5947_x6890.load, step=Const(1), par=1) // Counter
      val x6892 = CounterChain(name = "x6892", ctr58).iter(1)
    }
    val x6893_dsp0 = MemoryPipeline(name="x6893_dsp0",parent="x6936") { implicit CU => 
      val x6909_x6909 =  VectorFIFO(size=1).wtPort(x6893_x6909_v)
      val x6896 = CounterChain.copy("x6910", "x6896")
      val x6913 = CounterChain.copy("x6922_0", "x6913")
      val x6893_x6918 =  SRAM(size=96,banking = Strided(1)).wtPort(x6909_x6909.readPort).rdPort(x6893_x6918_x6922_v).rdAddr(x6913(1)).wtAddr(x6896(0))
      var stage: List[Stage] = Nil
    }
    val x6893_dsp1 = MemoryPipeline(name="x6893_dsp1",parent="x6936") { implicit CU => 
      val x6909_x6909 =  VectorFIFO(size=1).wtPort(x6893_x6909_v)
      val x6896 = CounterChain.copy("x6910", "x6896")
      val x6913 = CounterChain.copy("x6922_0", "x6913")
      val x6893_x6917 =  SRAM(size=96,banking = Strided(1)).wtPort(x6909_x6909.readPort).rdPort(x6893_x6917_x6922_v).rdAddr(x6913(0)).wtAddr(x6896(0))
      var stage: List[Stage] = Nil
    }
    val x6894_dsp0 = MemoryPipeline(name="x6894_dsp0",parent="x6936") { implicit CU => 
      val b7379 = CU.temp
      val b7381 = CU.temp
      val x6921_x6921 =  VectorFIFO(size=1).wtPort(x6894_x6921_v)
      val x6913 = CounterChain.copy("x6922_0", "x6913")
      val x6925 = CounterChain.copy("x6934_0", "x6925")
      val x6894_x6928 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6921_x6921.readPort).rdPort(x6894_x6928_x6934_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6913(0)), Const(96)), op=FixMul, results=List(b7379))
      WAStage(operands=List(b7379, CU.ctr(x6913(1))), op=FixAdd, results=List(x6894_x6928.writeAddr))
      RAStage(operands=List(CU.ctr(x6925(0)), Const(96)), op=FixMul, results=List(b7381))
      RAStage(operands=List(b7381, CU.ctr(x6925(1))), op=FixAdd, results=List(x6894_x6928.readAddr))
    }
    val x6910 = StreamController(name="x6910",parent=x6936) { implicit CU => 
      val ctr59 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6896 = CounterChain(name = "x6896", ctr59).iter(6)
    }
    val x6910_0 = Pipeline(name="x6910_0",parent=x6910) { implicit CU => 
      val x6906 = CU.temp
      val x6902_x6902 =  VectorFIFO(size=1).wtPort(x5885_x6902_x6910_v)
      val x6901_x6901 =  VectorFIFO(size=1).wtPort(x5886_x6901_x6910_v)
      val x6900_x6900 =  VectorFIFO(size=1).wtPort(x5931_x6900_x6910_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6900_x6900), Const(1)), op=FixEql, results=List(x6906))
      Stage(operands=List(x6906, CU.load(x6901_x6901), CU.load(x6902_x6902)), op=Mux, results=List(CU.vecOut(bus_7129_v)))
    }
    val x6910_1 = Pipeline(name="x6910_1",parent=x6910) { implicit CU => 
      val x6907 =  VectorFIFO(size=1).wtPort(bus_7129_v)
      val x6899_x6899 =  VectorFIFO(size=1).wtPort(x5939_x6899_x6910_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6899_x6899), CU.load(x6907)), op=FixSub, results=List(CU.vecOut(x6893_x6909_v)))
    }
    val x6922_0 = Pipeline(name="x6922_0",parent=x6936) { implicit CU => 
      val x6918_x6918 =  VectorFIFO(size=1).wtPort(x6893_x6918_x6922_v)
      val x6917_x6917 =  VectorFIFO(size=1).wtPort(x6893_x6917_x6922_v)
      val ctr60 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr61 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6913 = CounterChain(name = "x6913", ctr60, ctr61).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6917_x6917), CU.load(x6918_x6918)), op=FixMul, results=List(CU.vecOut(x6894_x6921_v)))
    }
    val x6934_0 = Pipeline(name="x6934_0",parent=x6936) { implicit CU => 
      val x6929_x6929 =  VectorFIFO(size=1).wtPort(x6652_x6929_x6934_v)
      val x6928_x6928 =  VectorFIFO(size=1).wtPort(x6894_x6928_x6934_v)
      val ctr62 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr63 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6925 = CounterChain(name = "x6925", ctr62, ctr63).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6928_x6928), CU.load(x6929_x6929)), op=FixAdd, results=List(CU.vecOut(x6652_x6933_v)))
    }
    val x6983 = MetaPipeline(name="x6983",parent=x7083) { implicit CU => 
      val x5948_x6937 =  ScalarBuffer().wtPort(x5948_x6556_s)
      val ctr64 = Counter(min=Const(0), max=x5948_x6937.load, step=Const(1), par=1) // Counter
      val x6939 = CounterChain(name = "x6939", ctr64).iter(1)
    }
    val x6940_dsp0 = MemoryPipeline(name="x6940_dsp0",parent="x6983") { implicit CU => 
      val x6956_x6956 =  VectorFIFO(size=1).wtPort(x6940_x6956_v)
      val x6943 = CounterChain.copy("x6957", "x6943")
      val x6960 = CounterChain.copy("x6969_0", "x6960")
      val x6940_x6965 =  SRAM(size=96,banking = Strided(1)).wtPort(x6956_x6956.readPort).rdPort(x6940_x6965_x6969_v).rdAddr(x6960(1)).wtAddr(x6943(0))
      var stage: List[Stage] = Nil
    }
    val x6940_dsp1 = MemoryPipeline(name="x6940_dsp1",parent="x6983") { implicit CU => 
      val x6956_x6956 =  VectorFIFO(size=1).wtPort(x6940_x6956_v)
      val x6943 = CounterChain.copy("x6957", "x6943")
      val x6960 = CounterChain.copy("x6969_0", "x6960")
      val x6940_x6964 =  SRAM(size=96,banking = Strided(1)).wtPort(x6956_x6956.readPort).rdPort(x6940_x6964_x6969_v).rdAddr(x6960(0)).wtAddr(x6943(0))
      var stage: List[Stage] = Nil
    }
    val x6941_dsp0 = MemoryPipeline(name="x6941_dsp0",parent="x6983") { implicit CU => 
      val b7391 = CU.temp
      val b7389 = CU.temp
      val x6968_x6968 =  VectorFIFO(size=1).wtPort(x6941_x6968_v)
      val x6960 = CounterChain.copy("x6969_0", "x6960")
      val x6972 = CounterChain.copy("x6981_0", "x6972")
      val x6941_x6975 =  SRAM(size=9216,banking = Strided(1)).wtPort(x6968_x6968.readPort).rdPort(x6941_x6975_x6981_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x6960(0)), Const(96)), op=FixMul, results=List(b7389))
      WAStage(operands=List(b7389, CU.ctr(x6960(1))), op=FixAdd, results=List(x6941_x6975.writeAddr))
      RAStage(operands=List(CU.ctr(x6972(0)), Const(96)), op=FixMul, results=List(b7391))
      RAStage(operands=List(b7391, CU.ctr(x6972(1))), op=FixAdd, results=List(x6941_x6975.readAddr))
    }
    val x6957 = StreamController(name="x6957",parent=x6983) { implicit CU => 
      val ctr65 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6943 = CounterChain(name = "x6943", ctr65).iter(6)
    }
    val x6957_0 = Pipeline(name="x6957_0",parent=x6957) { implicit CU => 
      val x6953 = CU.temp
      val x6947_x6947 =  VectorFIFO(size=1).wtPort(x5932_x6947_x6957_v)
      val x6949_x6949 =  VectorFIFO(size=1).wtPort(x5885_x6949_x6957_v)
      val x6948_x6948 =  VectorFIFO(size=1).wtPort(x5886_x6948_x6957_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6947_x6947), Const(1)), op=FixEql, results=List(x6953))
      Stage(operands=List(x6953, CU.load(x6948_x6948), CU.load(x6949_x6949)), op=Mux, results=List(CU.vecOut(bus_7152_v)))
    }
    val x6957_1 = Pipeline(name="x6957_1",parent=x6957) { implicit CU => 
      val x6954 =  VectorFIFO(size=1).wtPort(bus_7152_v)
      val x6946_x6946 =  VectorFIFO(size=1).wtPort(x5940_x6946_x6957_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6946_x6946), CU.load(x6954)), op=FixSub, results=List(CU.vecOut(x6940_x6956_v)))
    }
    val x6969_0 = Pipeline(name="x6969_0",parent=x6983) { implicit CU => 
      val x6964_x6964 =  VectorFIFO(size=1).wtPort(x6940_x6964_x6969_v)
      val x6965_x6965 =  VectorFIFO(size=1).wtPort(x6940_x6965_x6969_v)
      val ctr66 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr67 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6960 = CounterChain(name = "x6960", ctr66, ctr67).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6964_x6964), CU.load(x6965_x6965)), op=FixMul, results=List(CU.vecOut(x6941_x6968_v)))
    }
    val x6981_0 = Pipeline(name="x6981_0",parent=x6983) { implicit CU => 
      val x6976_x6976 =  VectorFIFO(size=1).wtPort(x6653_x6976_x6981_v)
      val x6975_x6975 =  VectorFIFO(size=1).wtPort(x6941_x6975_x6981_v)
      val ctr68 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr69 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6972 = CounterChain(name = "x6972", ctr68, ctr69).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6975_x6975), CU.load(x6976_x6976)), op=FixAdd, results=List(CU.vecOut(x6653_x6980_v)))
    }
    val x7030 = MetaPipeline(name="x7030",parent=x7083) { implicit CU => 
      val x5949_x6984 =  ScalarBuffer().wtPort(x5949_x6643_s)
      val ctr70 = Counter(min=Const(0), max=x5949_x6984.load, step=Const(1), par=1) // Counter
      val x6986 = CounterChain(name = "x6986", ctr70).iter(1)
    }
    val x6987_dsp0 = MemoryPipeline(name="x6987_dsp0",parent="x7030") { implicit CU => 
      val x7003_x7003 =  VectorFIFO(size=1).wtPort(x6987_x7003_v)
      val x6990 = CounterChain.copy("x7004", "x6990")
      val x7007 = CounterChain.copy("x7016_0", "x7007")
      val x6987_x7012 =  SRAM(size=96,banking = Strided(1)).wtPort(x7003_x7003.readPort).rdPort(x6987_x7012_x7016_v).rdAddr(x7007(1)).wtAddr(x6990(0))
      var stage: List[Stage] = Nil
    }
    val x6987_dsp1 = MemoryPipeline(name="x6987_dsp1",parent="x7030") { implicit CU => 
      val x7003_x7003 =  VectorFIFO(size=1).wtPort(x6987_x7003_v)
      val x6990 = CounterChain.copy("x7004", "x6990")
      val x7007 = CounterChain.copy("x7016_0", "x7007")
      val x6987_x7011 =  SRAM(size=96,banking = Strided(1)).wtPort(x7003_x7003.readPort).rdPort(x6987_x7011_x7016_v).rdAddr(x7007(0)).wtAddr(x6990(0))
      var stage: List[Stage] = Nil
    }
    val x6988_dsp0 = MemoryPipeline(name="x6988_dsp0",parent="x7030") { implicit CU => 
      val b7401 = CU.temp
      val b7399 = CU.temp
      val x7015_x7015 =  VectorFIFO(size=1).wtPort(x6988_x7015_v)
      val x7007 = CounterChain.copy("x7016_0", "x7007")
      val x7019 = CounterChain.copy("x7028_0", "x7019")
      val x6988_x7022 =  SRAM(size=9216,banking = Strided(1)).wtPort(x7015_x7015.readPort).rdPort(x6988_x7022_x7028_v)
      var stage: List[Stage] = Nil
      WAStage(operands=List(CU.ctr(x7007(0)), Const(96)), op=FixMul, results=List(b7399))
      WAStage(operands=List(b7399, CU.ctr(x7007(1))), op=FixAdd, results=List(x6988_x7022.writeAddr))
      RAStage(operands=List(CU.ctr(x7019(0)), Const(96)), op=FixMul, results=List(b7401))
      RAStage(operands=List(b7401, CU.ctr(x7019(1))), op=FixAdd, results=List(x6988_x7022.readAddr))
    }
    val x7004 = StreamController(name="x7004",parent=x7030) { implicit CU => 
      val ctr71 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x6990 = CounterChain(name = "x6990", ctr71).iter(6)
    }
    val x7004_0 = Pipeline(name="x7004_0",parent=x7004) { implicit CU => 
      val x7000 = CU.temp
      val x6994_x6994 =  VectorFIFO(size=1).wtPort(x5933_x6994_x7004_v)
      val x6996_x6996 =  VectorFIFO(size=1).wtPort(x5885_x6996_x7004_v)
      val x6995_x6995 =  VectorFIFO(size=1).wtPort(x5886_x6995_x7004_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6994_x6994), Const(1)), op=FixEql, results=List(x7000))
      Stage(operands=List(x7000, CU.load(x6995_x6995), CU.load(x6996_x6996)), op=Mux, results=List(CU.vecOut(bus_7175_v)))
    }
    val x7004_1 = Pipeline(name="x7004_1",parent=x7004) { implicit CU => 
      val x6993_x6993 =  VectorFIFO(size=1).wtPort(x5941_x6993_x7004_v)
      val x7001 =  VectorFIFO(size=1).wtPort(bus_7175_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x6993_x6993), CU.load(x7001)), op=FixSub, results=List(CU.vecOut(x6987_x7003_v)))
    }
    val x7016_0 = Pipeline(name="x7016_0",parent=x7030) { implicit CU => 
      val x7012_x7012 =  VectorFIFO(size=1).wtPort(x6987_x7012_x7016_v)
      val x7011_x7011 =  VectorFIFO(size=1).wtPort(x6987_x7011_x7016_v)
      val ctr72 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr73 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x7007 = CounterChain(name = "x7007", ctr72, ctr73).iter(576)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7011_x7011), CU.load(x7012_x7012)), op=FixMul, results=List(CU.vecOut(x6988_x7015_v)))
    }
    val x7028_0 = Pipeline(name="x7028_0",parent=x7030) { implicit CU => 
      val x7023_x7023 =  VectorFIFO(size=1).wtPort(x6654_x7023_x7028_v)
      val x7022_x7022 =  VectorFIFO(size=1).wtPort(x6988_x7022_x7028_v)
      val ctr74 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val ctr75 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x7019 = CounterChain(name = "x7019", ctr74, ctr75).iter(36)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7022_x7022), CU.load(x7023_x7023)), op=FixAdd, results=List(CU.vecOut(x6654_x7027_v)))
    }
    val x7081 = StreamController(name="x7081",parent=x7083) { implicit CU => 
      val ctr76 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val ctr77 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x7034 = CounterChain(name = "x7034", ctr76, ctr77).iter(9216)
    }
    val x7081_0 = Pipeline(name="x7081_0",parent=x7081) { implicit CU => 
      val x7036_x7036 =  VectorFIFO(size=1).wtPort(x6647_x7036_x7081_v)
      val x7037_x7037 =  VectorFIFO(size=1).wtPort(x6648_x7037_x7081_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7036_x7036), CU.load(x7037_x7037)), op=FixAdd, results=List(CU.scalarOut(bus_7186_s)))
    }
    val x7081_1 = Pipeline(name="x7081_1",parent=x7081) { implicit CU => 
      val x7056 = CU.temp
      val x7039_x7039 =  VectorFIFO(size=1).wtPort(x6650_x7039_x7081_v)
      val x7038_x7038 =  VectorFIFO(size=1).wtPort(x6649_x7038_x7081_v)
      val x7049 =  ScalarFIFO(size=1).wtPort(bus_7186_s)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7038_x7038), CU.load(x7039_x7039)), op=FixAdd, results=List(x7056))
      Stage(operands=List(CU.load(x7049), x7056), op=FixAdd, results=List(CU.scalarOut(bus_7191_s)))
    }
    val x7081_2 = Pipeline(name="x7081_2",parent=x7081) { implicit CU => 
      val x7041_x7041 =  VectorFIFO(size=1).wtPort(x6652_x7041_x7081_v)
      val x7040_x7040 =  VectorFIFO(size=1).wtPort(x6651_x7040_x7081_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7040_x7040), CU.load(x7041_x7041)), op=FixAdd, results=List(CU.scalarOut(bus_7199_s)))
    }
    val x7081_3 = Pipeline(name="x7081_3",parent=x7081) { implicit CU => 
      val x7073 = CU.temp
      val x7077 = CU.temp
      val x7075 = CU.temp
      val x7042_x7042 =  VectorFIFO(size=1).wtPort(x6653_x7042_x7081_v)
      val x7069 =  ScalarFIFO(size=1).wtPort(bus_7199_s)
      val x7044_x7044 =  VectorFIFO(size=1).wtPort(x5922_x7044_x7081_v)
      val x7058 =  ScalarFIFO(size=1).wtPort(bus_7191_s)
      val x7043_x7043 =  VectorFIFO(size=1).wtPort(x6654_x7043_x7081_v)
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.load(x7042_x7042), CU.load(x7043_x7043)), op=FixAdd, results=List(x7073))
      Stage(operands=List(CU.load(x7069), x7073), op=FixAdd, results=List(x7075))
      Stage(operands=List(CU.load(x7058), x7075), op=FixAdd, results=List(x7077))
      Stage(operands=List(x7077, CU.load(x7044_x7044)), op=FixAdd, results=List(CU.vecOut(x5922_x7080_v)))
    }
    val x7114 = StreamController(name="x7114",parent=x7115) { implicit CU => 
      val ctr78 = Counter(min=Const(0), max=Const(96), step=Const(1), par=1) // Counter
      val x7085 = CounterChain(name = "x7085", ctr78).iter(96)
    }
    val x7106 = Sequential(name="x7106",parent=x7114) { implicit CU => 
    }
    val x7097_0 = Pipeline(name="x7097_0",parent=x7106) { implicit CU => 
      val x7090 = CU.temp
      val x7091 = CU.temp
      val x7089 =  ScalarBuffer().wtPort(x7089_argin)
      val x7085 = CounterChain.copy("x7114", "x7085")
      var stage: List[Stage] = Nil
      Stage(operands=List(CU.ctr(x7085(0)), Const(96)), op=FixMul, results=List(x7090))
      Stage(operands=List(x7090, Const(4)), op=FixMul, results=List(x7091))
      Stage(operands=List(x7091, CU.load(x7089)), op=FixAdd, results=List(CU.scalarOut(x7086_b7427_x7096_b7429_s)))
      Stage(operands=List(Const(384)), op=Bypass, results=List(CU.scalarOut(x7086_b7428_x7096_b7430_s)))
    }
    val x7105 = Pipeline(name="x7105",parent=x7106) { implicit CU => 
      val ctr79 = Counter(min=Const(0), max=Const(96), step=Const(1), par=16) // Counter
      val x7099 = CounterChain(name = "x7099", ctr79).iter(6)
      var stage: List[Stage] = Nil
    }
    val x7107 = MemoryController(name="x7107",parent=x7114,offchip=x5880_oc, mctpe=TileStore) { implicit CU => 
      val x7086_b7428_x7107 =  ScalarFIFO(name="size",size=1).wtPort(x7086_b7428_x7096_b7430_s)
      val x7087_x7107 =  VectorFIFO(name="data",size=1).wtPort(x5922_x7101_x7105_v)
      val x7086_b7427_x7107 =  ScalarFIFO(name="offset",size=1).wtPort(x7086_b7427_x7096_b7429_s)
    }
    
  }
}
