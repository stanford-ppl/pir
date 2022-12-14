#include "Context7479.h"
Context7479::Context7479(FIFO<Token, 16>& _fifo_BufferRead7486,FIFO<Token, 16>& _fifo_BufferRead7491,FIFO<Token, 64>& _fifo_BufferRead7493) :
Context("Context7479","MemoryContainer7458","Context7479 Squares.scala:73:52\nGlobalMemoryLowering.scala:163")
, fifo_BufferRead7486(_fifo_BufferRead7486)
, fifo_BufferRead7491(_fifo_BufferRead7491)
, fifo_BufferRead7493(_fifo_BufferRead7493)
  , TopController7614("TopController7614")
  , UnitController7613("UnitController7613")
  , LoopController7610("LoopController7610")
  , StridedCounter7616("StridedCounter7616")
  , UnitController7609("UnitController7609")
  , LoopController7619("LoopController7619")
  , StridedCounter7623("StridedCounter7623")
 {
  AddChild(&TopController7614, false);
  AddCtrler(&TopController7614);
  AddChild(&UnitController7613, false);
  AddCtrler(&UnitController7613);
  AddChild(&LoopController7610, false);
  AddCtrler(&LoopController7610);
  LoopController7610.AddCounter(&StridedCounter7616);
  AddChild(&StridedCounter7616, false);
  AddChild(&UnitController7609, false);
  AddCtrler(&UnitController7609);
  LoopController7619.AddInput(&fifo_BufferRead7486, false);
  // Initialize BufferRead7486
  for (int i = 0; i < 16; i++) {
    BufferRead7486[i] = 0;
  }
  AddChild(&LoopController7619, false);
  AddCtrler(&LoopController7619);
  LoopController7619.AddCounter(&StridedCounter7623);
  AddChild(&StridedCounter7623, false);
  LoopController7619.AddOutput(&fifo_BufferRead7491);
  LoopController7619.AddOutput(&fifo_BufferRead7493);
}
void Context7479::Clock() {
  LoopController7610.Clock();
  LoopController7619.Clock();
  StridedCounter7616.Clock();
  StridedCounter7623.Clock();
  TopController7614.Clock();
  UnitController7609.Clock();
  UnitController7613.Clock();
}
void Context7479::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7486_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7490_en[i] = false;
  }
  BufferWrite7492_en = false;
  if (TopController7614.Enabled()) {
    if (UnitController7613.Enabled()) {
      StridedCounter7616.setMin(Const7612);
      StridedCounter7616.setStep(Const7611);
      StridedCounter7616.setMax(Const7615);
      StridedCounter7616.Eval(); // ControlTree12
      if (LoopController7610.Enabled()) {
        if (UnitController7609.Enabled()) {
          for (int i = 0; i < 16; i++) {
            BufferRead7486_en[i] = true;
          }
          fifo_BufferRead7486.SetReadEn(BufferRead7486_en);
          if (!fifo_BufferRead7486_loaded && Any<16>(BufferRead7486_en) && fifo_BufferRead7486.Valid()) {
            fifo_BufferRead7486_loaded = true;
            for (int i = 0; i < 16; i++) {
              BufferRead7486[i] = toT<int>(fifo_BufferRead7486.Read(), i);
            }
          }
          StridedCounter7623.setMin(Const7622);
          StridedCounter7623.setStep(Const7621);
          StridedCounter7623.setMax(Const7620);
          StridedCounter7623.Eval(); // ControlTree126
          LoopController7619.EvalLaneValids();
          for (int i = 0; i < 16; i++) {
            laneValid[i] = LoopController7619.LaneValids()[i];
          }
          if (LoopController7619.Enabled()) {
            bool uen = true;
            Active();
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7619.ChildDone() && Any<16>(BufferRead7486_en)) {
    fifo_BufferRead7486_loaded = false;
    fifo_BufferRead7486.Pop();
  }
  if (LoopController7619.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7490_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7490[i] = BufferWrite7490_en[i] ? BufferRead7486[i] : BufferWrite7490[i];
    }
  }
  if (LoopController7619.ChildDone() && Any<16>(BufferWrite7490_en)) {
    Token data = make_token(BufferWrite7490);
    set_token_en<16>(data, BufferWrite7490_en);
    data.done_vec = LoopController7619.LevelsDone()+1;
    fifo_BufferRead7491.Push(data);
  }
  if (LoopController7619.Enabled()) {
    BufferWrite7492_en = true;
    BufferWrite7492 = BufferWrite7492_en ? UnitController7609.Done() : BufferWrite7492;
  }
  if (LoopController7619.ChildDone() && BufferWrite7492_en) {
    Token data = make_token(BufferWrite7492);
    set_token_en(data, BufferWrite7492_en);
    data.done_vec = LoopController7619.LevelsDone()+1;
    fifo_BufferRead7493.Push(data);
  }
}
