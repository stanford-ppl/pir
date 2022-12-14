#include "Context7487.h"
Context7487::Context7487(FIFO<Token, 16>& _fifo_BufferRead7491,FIFO<Token, 64>& _fifo_BufferRead7493,FIFO<Token, 2>& _fifo_TokenRead7498,NBufferSRAM<2, uint32_t, 512, 16>& _SRAM16,NetworkInput& _go7921) :
Context("Context7487","MemoryContainer7458","Context7487 Squares.scala:73:52\nGlobalMemoryLowering.scala:216")
, fifo_BufferRead7491(_fifo_BufferRead7491)
, fifo_BufferRead7493(_fifo_BufferRead7493)
, fifo_TokenRead7498(_fifo_TokenRead7498)
, SRAM16(_SRAM16)
, go7921(_go7921)
  , UnitController7489("UnitController7489")
 {
  UnitController7489.AddInput(&fifo_BufferRead7491, false);
  // Initialize BufferRead7491
  for (int i = 0; i < 16; i++) {
    BufferRead7491[i] = 0;
  }
  UnitController7489.AddInput(&fifo_BufferRead7493, false);
  // Initialize BufferRead7493
  BufferRead7493 = false;
  UnitController7489.AddInput(&fifo_TokenRead7498, false);
  // Initialize TokenRead7498
  TokenRead7498 = false;
  AddChild(&UnitController7489, false);
  AddCtrler(&UnitController7489);
  UnitController7489.AddOutput(&go7921);
  SRAM16.SetSends(7488, {&go7921});
}
void Context7487::Clock() {
  UnitController7489.Clock();
}
void Context7487::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7491_en[i] = false;
  }
  BufferRead7493_en = false;
  TokenRead7498_en = false;
  for (int i = 0; i < 16; i++) {
    BufferRead7491_en[i] = true;
  }
  fifo_BufferRead7491.SetReadEn(BufferRead7491_en);
  if (!fifo_BufferRead7491_loaded && Any<16>(BufferRead7491_en) && fifo_BufferRead7491.Valid()) {
    fifo_BufferRead7491_loaded = true;
    for (int i = 0; i < 16; i++) {
      BufferRead7491[i] = toT<int>(fifo_BufferRead7491.Read(), i);
    }
  }
  BufferRead7493_en = true;
  fifo_BufferRead7493.SetReadEn(BufferRead7493_en);
  if (!fifo_BufferRead7493_loaded && BufferRead7493_en && fifo_BufferRead7493.Valid()) {
    fifo_BufferRead7493_loaded = true;
    BufferRead7493 = toT<bool>(fifo_BufferRead7493.Read(), 0);
  }
  TokenRead7498_en = true;
  if (!fifo_TokenRead7498_loaded && fifo_TokenRead7498.Valid()) {
    fifo_TokenRead7498_loaded = true;
    TokenRead7498 = toT<bool>(fifo_TokenRead7498.Read(), 0);
  }
  laneValid = true;
  if (UnitController7489.Enabled()) {
    bool uen = true;
    Active();
    // FlatBankedRead7488
    SRAM16.SetupRead(7488,make_token(BufferRead7491));
  }
  EvalControllers();
  if (UnitController7489.ChildDone() && Any<16>(BufferRead7491_en)) {
    fifo_BufferRead7491_loaded = false;
    fifo_BufferRead7491.Pop();
  }
  if (UnitController7489.ChildDone() && BufferRead7493_en) {
    fifo_BufferRead7493_loaded = false;
    fifo_BufferRead7493.Pop();
  }
  if (UnitController7489.ChildDone() && BufferRead7493) {
    fifo_TokenRead7498_loaded = false;
    fifo_TokenRead7498.Pop();
  }
  if (UnitController7489.Enabled()) {
    SRAM16.SetDone(7488, UnitController7489.ChildDone() && BufferRead7493);
  }
}
