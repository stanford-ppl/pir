#include "Context161.h"
Context161::Context161(FIFO<Token, 64>& _fifo_BufferRead7508,NetworkInput& _go8039,NetworkInput& _go8037) :
Context("Context161","ComputeContainer7893","Context161 Squares.scala:73:52\nContextInsertion.scala:26")
, fifo_BufferRead7508(_fifo_BufferRead7508)
, go8039(_go8039)
, go8037(_go8037)
  , TopController7547("TopController7547")
  , UnitController7546("UnitController7546")
  , LoopController7549("LoopController7549")
  , StridedCounter7542("StridedCounter7542")
  , UnitController7548("UnitController7548")
  , UnitController107("UnitController107")
  , pipe_BufferWrite168("pipe_BufferWrite168",6)
  , pipe_BufferWrite7499("pipe_BufferWrite7499",6)
 {
  AddChild(&TopController7547, false);
  AddCtrler(&TopController7547);
  UnitController7546.AddInput(&fifo_BufferRead7508, false);
  // Initialize BufferRead7508
  BufferRead7508 = 0;
  AddChild(&UnitController7546, false);
  AddCtrler(&UnitController7546);
  AddChild(&LoopController7549, false);
  AddCtrler(&LoopController7549);
  LoopController7549.AddCounter(&StridedCounter7542);
  AddChild(&StridedCounter7542, false);
  AddChild(&UnitController7548, false);
  AddCtrler(&UnitController7548);
  AddChild(&UnitController107, false);
  AddCtrler(&UnitController107);
  AddChild(&pipe_BufferWrite168, false);
  UnitController107.AddOutput(&go8039, &pipe_BufferWrite168);
  AddChild(&pipe_BufferWrite7499, false);
  UnitController107.AddOutput(&go8037, &pipe_BufferWrite7499);
}
void Context161::Clock() {
  LoopController7549.Clock();
  StridedCounter7542.Clock();
  TopController7547.Clock();
  UnitController107.Clock();
  UnitController7546.Clock();
  UnitController7548.Clock();
  pipe_BufferWrite168.Clock();
  pipe_BufferWrite7499.Clock();
}
void Context161::Eval() {
  BufferRead7508_en = false;
  BufferWrite168_en = false;
  BufferWrite7499_en = false;
  if (TopController7547.Enabled()) {
    BufferRead7508_en = true;
    if (!fifo_BufferRead7508_loaded && fifo_BufferRead7508.Valid()) {
      fifo_BufferRead7508_loaded = true;
      BufferRead7508 = toT<long>(fifo_BufferRead7508.Read(), 0);
    }
    if (UnitController7546.Enabled()) {
      StridedCounter7542.setMin(Const7544);
      StridedCounter7542.setStep(Const7543);
      StridedCounter7542.setMax(Const7545);
      StridedCounter7542.Eval(); // ControlTree12
      CounterIter7541 = StridedCounter7542.Iters()[0];
      if (LoopController7549.Enabled()) {
        if (UnitController7548.Enabled()) {
          laneValid = true;
          if (UnitController107.Enabled()) {
            bool uen = true;
            Active();
            OpDef108 = CounterIter7541 << Const109;
            OpDef110 = (long) OpDef108;
            OpDef115 = OpDef110 + BufferRead7508;
          }
        }
      }
    }
  }
  EvalControllers();
  if (UnitController7546.Done()) {
    fifo_BufferRead7508_loaded = false;
    fifo_BufferRead7508.Pop();
  }
  if (UnitController107.Enabled()) {
    BufferWrite168_en = true;
    BufferWrite168 = BufferWrite168_en ? Const116 : BufferWrite168;
  }
  if (UnitController107.ChildDone() && BufferWrite168_en) {
    Token data = make_token(BufferWrite168);
    set_token_en(data, BufferWrite168_en);
    data.done_vec = UnitController107.LevelsDone()+1;
    pipe_BufferWrite168.Push(data);
  }
  if (UnitController107.Enabled()) {
    BufferWrite7499_en = true;
    BufferWrite7499 = BufferWrite7499_en ? OpDef115 : BufferWrite7499;
  }
  if (UnitController107.ChildDone() && BufferWrite7499_en) {
    Token data = make_token(BufferWrite7499);
    set_token_en(data, BufferWrite7499_en);
    data.done_vec = UnitController107.LevelsDone()+1;
    pipe_BufferWrite7499.Push(data);
  }
  pipe_BufferWrite168.Eval();
  pipe_BufferWrite7499.Eval();
}
