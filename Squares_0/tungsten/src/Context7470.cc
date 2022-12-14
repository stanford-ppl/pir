#include "Context7470.h"
Context7470::Context7470(FIFO<Token, 16>& _fifo_BufferRead7474,FIFO<Token, 16>& _fifo_BufferRead7476,FIFO<Token, 64>& _fifo_BufferRead7478,NBufferSRAM<2, uint32_t, 512, 16>& _SRAM16,FIFO<Token, 2>& _fifo_TokenRead7498) :
Context("Context7470","MemoryContainer7458","Context7470 Squares.scala:69:31\nGlobalMemoryLowering.scala:216")
, fifo_BufferRead7474(_fifo_BufferRead7474)
, fifo_BufferRead7476(_fifo_BufferRead7476)
, fifo_BufferRead7478(_fifo_BufferRead7478)
, SRAM16(_SRAM16)
, fifo_TokenRead7498(_fifo_TokenRead7498)
  , UnitController7472("UnitController7472")
 {
  UnitController7472.AddInput(&fifo_BufferRead7474, false);
  // Initialize BufferRead7474
  for (int i = 0; i < 16; i++) {
    BufferRead7474[i] = 0;
  }
  UnitController7472.AddInput(&fifo_BufferRead7476, false);
  // Initialize BufferRead7476
  for (int i = 0; i < 16; i++) {
    BufferRead7476[i] = 0;
  }
  UnitController7472.AddInput(&fifo_BufferRead7478, false);
  // Initialize BufferRead7478
  BufferRead7478 = false;
  AddChild(&UnitController7472, false);
  AddCtrler(&UnitController7472);
  UnitController7472.AddOutput(&fifo_TokenRead7498);
}
void Context7470::Clock() {
  UnitController7472.Clock();
}
void Context7470::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7474_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7476_en[i] = false;
  }
  BufferRead7478_en = false;
  TokenWrite7497_en = false;
  for (int i = 0; i < 16; i++) {
    BufferRead7474_en[i] = true;
  }
  fifo_BufferRead7474.SetReadEn(BufferRead7474_en);
  if (!fifo_BufferRead7474_loaded && Any<16>(BufferRead7474_en) && fifo_BufferRead7474.Valid()) {
    fifo_BufferRead7474_loaded = true;
    for (int i = 0; i < 16; i++) {
      BufferRead7474[i] = toT<int>(fifo_BufferRead7474.Read(), i);
    }
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7476_en[i] = true;
  }
  fifo_BufferRead7476.SetReadEn(BufferRead7476_en);
  if (!fifo_BufferRead7476_loaded && Any<16>(BufferRead7476_en) && fifo_BufferRead7476.Valid()) {
    fifo_BufferRead7476_loaded = true;
    for (int i = 0; i < 16; i++) {
      BufferRead7476[i] = toT<uint32_t>(fifo_BufferRead7476.Read(), i);
    }
  }
  BufferRead7478_en = true;
  fifo_BufferRead7478.SetReadEn(BufferRead7478_en);
  if (!fifo_BufferRead7478_loaded && BufferRead7478_en && fifo_BufferRead7478.Valid()) {
    fifo_BufferRead7478_loaded = true;
    BufferRead7478 = toT<bool>(fifo_BufferRead7478.Read(), 0);
  }
  laneValid = true;
  if (UnitController7472.Enabled()) {
    bool uen = true;
    Active();
    // FlatBankedWrite7471
    SRAM16.Write(7471, make_token(BufferRead7476), make_token(BufferRead7474));
  }
  EvalControllers();
  if (UnitController7472.ChildDone() && Any<16>(BufferRead7474_en)) {
    fifo_BufferRead7474_loaded = false;
    fifo_BufferRead7474.Pop();
  }
  if (UnitController7472.ChildDone() && Any<16>(BufferRead7476_en)) {
    fifo_BufferRead7476_loaded = false;
    fifo_BufferRead7476.Pop();
  }
  if (UnitController7472.ChildDone() && BufferRead7478_en) {
    fifo_BufferRead7478_loaded = false;
    fifo_BufferRead7478.Pop();
  }
  if (UnitController7472.Enabled()) {
    SRAM16.SetDone(7471, UnitController7472.ChildDone() && BufferRead7478);
  }
  if (UnitController7472.Enabled()) {
    TokenWrite7497_en = true;
    TokenWrite7497 = true;
  }
  if (UnitController7472.ChildDone() && BufferRead7478) {
    Token data = make_token(TokenWrite7497);
    set_token_en(data, TokenWrite7497_en);
    data.done_vec = UnitController7472.LevelsDone()+1;
    fifo_TokenRead7498.Push(data);
  }
}
