#include "Context162.h"
Context162::Context162(FIFO<Token, 16>& _fifo_BufferRead7496,NetworkInput& _go8040,NetworkInput& _go8038) :
Context("Context162","ComputeContainer7893","Context162 Squares.scala:73:52\nContextInsertion.scala:26")
, fifo_BufferRead7496(_fifo_BufferRead7496)
, go8040(_go8040)
, go8038(_go8038)
  , TopController7556("TopController7556")
  , UnitController7555("UnitController7555")
  , LoopController7552("LoopController7552")
  , StridedCounter7558("StridedCounter7558")
  , UnitController7551("UnitController7551")
  , LoopController127("LoopController127")
  , StridedCounter122("StridedCounter122")
 {
  AddChild(&TopController7556, false);
  AddCtrler(&TopController7556);
  AddChild(&UnitController7555, false);
  AddCtrler(&UnitController7555);
  AddChild(&LoopController7552, false);
  AddCtrler(&LoopController7552);
  LoopController7552.AddCounter(&StridedCounter7558);
  AddChild(&StridedCounter7558, false);
  AddChild(&UnitController7551, false);
  AddCtrler(&UnitController7551);
  LoopController127.AddInput(&fifo_BufferRead7496, false);
  // Initialize BufferRead7496
  for (int i = 0; i < 16; i++) {
    BufferRead7496[i] = 0;
  }
  AddChild(&LoopController127, false);
  AddCtrler(&LoopController127);
  LoopController127.AddCounter(&StridedCounter122);
  AddChild(&StridedCounter122, false);
  LoopController127.AddOutput(&go8040);
  LoopController127.AddOutput(&go8038);
}
void Context162::Clock() {
  LoopController127.Clock();
  LoopController7552.Clock();
  StridedCounter122.Clock();
  StridedCounter7558.Clock();
  TopController7556.Clock();
  UnitController7551.Clock();
  UnitController7555.Clock();
}
void Context162::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7496_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7501_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7503_en[i] = false;
  }
  if (TopController7556.Enabled()) {
    if (UnitController7555.Enabled()) {
      StridedCounter7558.setMin(Const7554);
      StridedCounter7558.setStep(Const7553);
      StridedCounter7558.setMax(Const7557);
      StridedCounter7558.Eval(); // ControlTree12
      if (LoopController7552.Enabled()) {
        if (UnitController7551.Enabled()) {
          for (int i = 0; i < 16; i++) {
            BufferRead7496_en[i] = true;
          }
          fifo_BufferRead7496.SetReadEn(BufferRead7496_en);
          if (!fifo_BufferRead7496_loaded && Any<16>(BufferRead7496_en) && fifo_BufferRead7496.Valid()) {
            fifo_BufferRead7496_loaded = true;
            for (int i = 0; i < 16; i++) {
              BufferRead7496[i] = toT<uint32_t>(fifo_BufferRead7496.Read(), i);
            }
          }
          StridedCounter122.setMin(Const7561);
          StridedCounter122.setStep(Const7562);
          StridedCounter122.setMax(Const7563);
          StridedCounter122.Eval(); // ControlTree126
          LoopController127.EvalLaneValids();
          for (int i = 0; i < 16; i++) {
            laneValid[i] = LoopController127.LaneValids()[i];
          }
          if (LoopController127.Enabled()) {
            bool uen = true;
            Active();
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController127.ChildDone() && Any<16>(BufferRead7496_en)) {
    fifo_BufferRead7496_loaded = false;
    fifo_BufferRead7496.Pop();
  }
  if (LoopController127.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7501_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7501[i] = BufferWrite7501_en[i] ? BufferRead7496[i] : BufferWrite7501[i];
    }
  }
  if (LoopController127.ChildDone() && Any<16>(BufferWrite7501_en)) {
    Token data = make_token(BufferWrite7501);
    set_token_en<16>(data, BufferWrite7501_en);
    data.done_vec = LoopController127.LevelsDone()+1;
    go8040.Push(data);
  }
  if (LoopController127.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7503_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7503[i] = BufferWrite7503_en[i] ? Const134 : BufferWrite7503[i];
    }
  }
  if (LoopController127.ChildDone() && Any<16>(BufferWrite7503_en)) {
    Token data = make_token(BufferWrite7503);
    set_token_en<16>(data, BufferWrite7503_en);
    data.done_vec = LoopController127.LevelsDone()+1;
    go8038.Push(data);
  }
}
