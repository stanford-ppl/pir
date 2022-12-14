#include "Context7668.h"
Context7668::Context7668(NetworkInput& _go7932) :
Context("Context7668","ComputeContainer7884","Context7668 Squares.scala:42:46\nContextInsertion.scala:26\nComputePartitioner.scala:96")
, go7932(_go7932)
  , TopController7737("TopController7737")
  , UnitController7738("UnitController7738")
  , LoopController7739("LoopController7739")
  , StridedCounter7740("StridedCounter7740")
  , LoopController7742("LoopController7742")
  , StridedCounter7743("StridedCounter7743")
  , pipe_BufferWrite7677("pipe_BufferWrite7677",6)
 {
  AddChild(&TopController7737, false);
  AddCtrler(&TopController7737);
  AddChild(&UnitController7738, false);
  AddCtrler(&UnitController7738);
  AddChild(&LoopController7739, false);
  AddCtrler(&LoopController7739);
  LoopController7739.AddCounter(&StridedCounter7740);
  AddChild(&StridedCounter7740, false);
  AddChild(&LoopController7742, false);
  AddCtrler(&LoopController7742);
  LoopController7742.AddCounter(&StridedCounter7743);
  AddChild(&StridedCounter7743, false);
  AddChild(&pipe_BufferWrite7677, false);
  LoopController7742.AddOutput(&go7932, &pipe_BufferWrite7677);
}
void Context7668::Clock() {
  LoopController7739.Clock();
  LoopController7742.Clock();
  StridedCounter7740.Clock();
  StridedCounter7743.Clock();
  TopController7737.Clock();
  UnitController7738.Clock();
  pipe_BufferWrite7677.Clock();
}
void Context7668::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferWrite7677_en[i] = false;
  }
  if (TopController7737.Enabled()) {
    if (UnitController7738.Enabled()) {
      StridedCounter7740.setMin(Const7731);
      StridedCounter7740.setStep(Const7735);
      StridedCounter7740.setMax(Const7734);
      StridedCounter7740.Eval(); // ControlTree12
      CounterIter7741 = StridedCounter7740.Iters()[0];
      if (LoopController7739.Enabled()) {
        StridedCounter7743.setMin(Const7732);
        StridedCounter7743.setStep(Const7733);
        StridedCounter7743.setMax(Const7736);
        StridedCounter7743.Eval(); // ControlTree21
        CounterIter7744[0] = StridedCounter7743.Iters()[0];
        CounterIter7744[1] = StridedCounter7743.Iters()[1];
        CounterIter7744[2] = StridedCounter7743.Iters()[2];
        CounterIter7744[3] = StridedCounter7743.Iters()[3];
        CounterIter7744[4] = StridedCounter7743.Iters()[4];
        CounterIter7744[5] = StridedCounter7743.Iters()[5];
        CounterIter7744[6] = StridedCounter7743.Iters()[6];
        CounterIter7744[7] = StridedCounter7743.Iters()[7];
        CounterIter7744[8] = StridedCounter7743.Iters()[8];
        CounterIter7744[9] = StridedCounter7743.Iters()[9];
        CounterIter7744[10] = StridedCounter7743.Iters()[10];
        CounterIter7744[11] = StridedCounter7743.Iters()[11];
        CounterIter7744[12] = StridedCounter7743.Iters()[12];
        CounterIter7744[13] = StridedCounter7743.Iters()[13];
        CounterIter7744[14] = StridedCounter7743.Iters()[14];
        CounterIter7744[15] = StridedCounter7743.Iters()[15];
        LoopController7742.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7742.LaneValids()[i];
        }
        if (LoopController7742.Enabled()) {
          bool uen = true;
          Active();
          OpDef25 = (uint32_t) CounterIter7741;
          for (int i = 0; i < 16; i++) {
            OpDef26[i] = (uint32_t) CounterIter7744[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef27[i] = OpDef25 + OpDef26[i];
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7742.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7677_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7677[i] = BufferWrite7677_en[i] ? OpDef27[i] : BufferWrite7677[i];
    }
  }
  if (LoopController7742.ChildDone() && Any<16>(BufferWrite7677_en)) {
    Token data = make_token(BufferWrite7677);
    set_token_en<16>(data, BufferWrite7677_en);
    data.done_vec = LoopController7742.LevelsDone()+1;
    pipe_BufferWrite7677.Push(data);
  }
  pipe_BufferWrite7677.Eval();
}
