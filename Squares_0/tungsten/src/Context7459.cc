#include "Context7459.h"
Context7459::Context7459(FIFO<Token, 16>& _fifo_BufferRead7466,FIFO<Token, 16>& _fifo_BufferRead7469,FIFO<Token, 16>& _fifo_BufferRead7474,FIFO<Token, 16>& _fifo_BufferRead7476,FIFO<Token, 64>& _fifo_BufferRead7478) :
Context("Context7459","MemoryContainer7458","Context7459 Squares.scala:69:31\nGlobalMemoryLowering.scala:163")
, fifo_BufferRead7466(_fifo_BufferRead7466)
, fifo_BufferRead7469(_fifo_BufferRead7469)
, fifo_BufferRead7474(_fifo_BufferRead7474)
, fifo_BufferRead7476(_fifo_BufferRead7476)
, fifo_BufferRead7478(_fifo_BufferRead7478)
  , TopController7584("TopController7584")
  , UnitController7583("UnitController7583")
  , LoopController7582("LoopController7582")
  , StridedCounter7576("StridedCounter7576")
  , LoopController7574("LoopController7574")
  , StridedCounter7587("StridedCounter7587")
 {
  AddChild(&TopController7584, false);
  AddCtrler(&TopController7584);
  AddChild(&UnitController7583, false);
  AddCtrler(&UnitController7583);
  AddChild(&LoopController7582, false);
  AddCtrler(&LoopController7582);
  LoopController7582.AddCounter(&StridedCounter7576);
  AddChild(&StridedCounter7576, false);
  LoopController7574.AddInput(&fifo_BufferRead7466, false);
  // Initialize BufferRead7466
  for (int i = 0; i < 16; i++) {
    BufferRead7466[i] = 0;
  }
  LoopController7574.AddInput(&fifo_BufferRead7469, false);
  // Initialize BufferRead7469
  for (int i = 0; i < 16; i++) {
    BufferRead7469[i] = 0;
  }
  AddChild(&LoopController7574, false);
  AddCtrler(&LoopController7574);
  LoopController7574.AddCounter(&StridedCounter7587);
  AddChild(&StridedCounter7587, false);
  LoopController7574.AddOutput(&fifo_BufferRead7474);
  LoopController7574.AddOutput(&fifo_BufferRead7476);
  LoopController7574.AddOutput(&fifo_BufferRead7478);
}
void Context7459::Clock() {
  LoopController7574.Clock();
  LoopController7582.Clock();
  StridedCounter7576.Clock();
  StridedCounter7587.Clock();
  TopController7584.Clock();
  UnitController7583.Clock();
}
void Context7459::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7466_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7469_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7473_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7475_en[i] = false;
  }
  BufferWrite7477_en = false;
  if (TopController7584.Enabled()) {
    if (UnitController7583.Enabled()) {
      StridedCounter7576.setMin(Const7578);
      StridedCounter7576.setStep(Const7577);
      StridedCounter7576.setMax(Const7579);
      StridedCounter7576.Eval(); // ControlTree12
      if (LoopController7582.Enabled()) {
        for (int i = 0; i < 16; i++) {
          BufferRead7466_en[i] = true;
        }
        fifo_BufferRead7466.SetReadEn(BufferRead7466_en);
        if (!fifo_BufferRead7466_loaded && Any<16>(BufferRead7466_en) && fifo_BufferRead7466.Valid()) {
          fifo_BufferRead7466_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7466[i] = toT<int>(fifo_BufferRead7466.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7469_en[i] = true;
        }
        fifo_BufferRead7469.SetReadEn(BufferRead7469_en);
        if (!fifo_BufferRead7469_loaded && Any<16>(BufferRead7469_en) && fifo_BufferRead7469.Valid()) {
          fifo_BufferRead7469_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7469[i] = toT<uint32_t>(fifo_BufferRead7469.Read(), i);
          }
        }
        StridedCounter7587.setMin(Const7575);
        StridedCounter7587.setStep(Const7580);
        StridedCounter7587.setMax(Const7581);
        StridedCounter7587.Eval(); // ControlTree21
        LoopController7574.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7574.LaneValids()[i];
        }
        if (LoopController7574.Enabled()) {
          bool uen = true;
          Active();
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7574.ChildDone() && Any<16>(BufferRead7466_en)) {
    fifo_BufferRead7466_loaded = false;
    fifo_BufferRead7466.Pop();
  }
  if (LoopController7574.ChildDone() && Any<16>(BufferRead7469_en)) {
    fifo_BufferRead7469_loaded = false;
    fifo_BufferRead7469.Pop();
  }
  if (LoopController7574.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7473_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7473[i] = BufferWrite7473_en[i] ? BufferRead7466[i] : BufferWrite7473[i];
    }
  }
  if (LoopController7574.ChildDone() && Any<16>(BufferWrite7473_en)) {
    Token data = make_token(BufferWrite7473);
    set_token_en<16>(data, BufferWrite7473_en);
    data.done_vec = LoopController7574.LevelsDone()+1;
    fifo_BufferRead7474.Push(data);
  }
  if (LoopController7574.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7475_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7475[i] = BufferWrite7475_en[i] ? BufferRead7469[i] : BufferWrite7475[i];
    }
  }
  if (LoopController7574.ChildDone() && Any<16>(BufferWrite7475_en)) {
    Token data = make_token(BufferWrite7475);
    set_token_en<16>(data, BufferWrite7475_en);
    data.done_vec = LoopController7574.LevelsDone()+1;
    fifo_BufferRead7476.Push(data);
  }
  if (LoopController7574.Enabled()) {
    BufferWrite7477_en = true;
    BufferWrite7477 = BufferWrite7477_en ? LoopController7574.Done() : BufferWrite7477;
  }
  if (LoopController7574.ChildDone() && BufferWrite7477_en) {
    Token data = make_token(BufferWrite7477);
    set_token_en(data, BufferWrite7477_en);
    data.done_vec = LoopController7574.LevelsDone()+1;
    fifo_BufferRead7478.Push(data);
  }
}
