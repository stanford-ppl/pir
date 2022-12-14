#include "Context7460.h"
Context7460::Context7460(FIFO<Token, 16>& _fifo_BufferRead7466) :
Context("Context7460","MemoryContainer7458","Context7460: BankedWrite97_addrCtx Squares.scala:69:31\nGlobalMemoryLowering.scala:174")
, fifo_BufferRead7466(_fifo_BufferRead7466)
  , TopController7605("TopController7605")
  , UnitController7604("UnitController7604")
  , LoopController7603("LoopController7603")
  , StridedCounter7599("StridedCounter7599")
  , LoopController7598("LoopController7598")
  , StridedCounter7593("StridedCounter7593")
  , pipe_BufferWrite7465("pipe_BufferWrite7465",6)
 {
  AddChild(&TopController7605, false);
  AddCtrler(&TopController7605);
  AddChild(&UnitController7604, false);
  AddCtrler(&UnitController7604);
  AddChild(&LoopController7603, false);
  AddCtrler(&LoopController7603);
  LoopController7603.AddCounter(&StridedCounter7599);
  AddChild(&StridedCounter7599, false);
  AddChild(&LoopController7598, false);
  AddCtrler(&LoopController7598);
  LoopController7598.AddCounter(&StridedCounter7593);
  AddChild(&StridedCounter7593, false);
  AddChild(&pipe_BufferWrite7465, false);
  LoopController7598.AddOutput(&fifo_BufferRead7466, &pipe_BufferWrite7465);
}
void Context7460::Clock() {
  LoopController7598.Clock();
  LoopController7603.Clock();
  StridedCounter7593.Clock();
  StridedCounter7599.Clock();
  TopController7605.Clock();
  UnitController7604.Clock();
  pipe_BufferWrite7465.Clock();
}
void Context7460::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferWrite7465_en[i] = false;
  }
  if (TopController7605.Enabled()) {
    if (UnitController7604.Enabled()) {
      StridedCounter7599.setMin(Const7601);
      StridedCounter7599.setStep(Const7600);
      StridedCounter7599.setMax(Const7602);
      StridedCounter7599.Eval(); // ControlTree12
      if (LoopController7603.Enabled()) {
        StridedCounter7593.setMin(Const7596);
        StridedCounter7593.setStep(Const7595);
        StridedCounter7593.setMax(Const7594);
        StridedCounter7593.Eval(); // ControlTree21
        CounterIter7592[0] = StridedCounter7593.Iters()[0];
        CounterIter7592[1] = StridedCounter7593.Iters()[1];
        CounterIter7592[2] = StridedCounter7593.Iters()[2];
        CounterIter7592[3] = StridedCounter7593.Iters()[3];
        CounterIter7592[4] = StridedCounter7593.Iters()[4];
        CounterIter7592[5] = StridedCounter7593.Iters()[5];
        CounterIter7592[6] = StridedCounter7593.Iters()[6];
        CounterIter7592[7] = StridedCounter7593.Iters()[7];
        CounterIter7592[8] = StridedCounter7593.Iters()[8];
        CounterIter7592[9] = StridedCounter7593.Iters()[9];
        CounterIter7592[10] = StridedCounter7593.Iters()[10];
        CounterIter7592[11] = StridedCounter7593.Iters()[11];
        CounterIter7592[12] = StridedCounter7593.Iters()[12];
        CounterIter7592[13] = StridedCounter7593.Iters()[13];
        CounterIter7592[14] = StridedCounter7593.Iters()[14];
        CounterIter7592[15] = StridedCounter7593.Iters()[15];
        LoopController7598.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7598.LaneValids()[i];
        }
        if (LoopController7598.Enabled()) {
          bool uen = true;
          Active();
          for (int i = 0; i < 16; i++) {
            OpDef7591[i] = CounterIter7592[i] >> Const7597;
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7598.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7465_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7465[i] = BufferWrite7465_en[i] ? OpDef7591[i] : BufferWrite7465[i];
    }
  }
  if (LoopController7598.ChildDone() && Any<16>(BufferWrite7465_en)) {
    Token data = make_token(BufferWrite7465);
    set_token_en<16>(data, BufferWrite7465_en);
    data.done_vec = LoopController7598.LevelsDone()+1;
    pipe_BufferWrite7465.Push(data);
  }
  pipe_BufferWrite7465.Eval();
}
