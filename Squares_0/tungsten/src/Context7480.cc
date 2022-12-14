#include "Context7480.h"
Context7480::Context7480(FIFO<Token, 16>& _fifo_BufferRead7486) :
Context("Context7480","MemoryContainer7458","Context7480: BankedRead133_addrCtx Squares.scala:73:52\nGlobalMemoryLowering.scala:174")
, fifo_BufferRead7486(_fifo_BufferRead7486)
  , TopController7640("TopController7640")
  , UnitController7639("UnitController7639")
  , LoopController7636("LoopController7636")
  , StridedCounter7642("StridedCounter7642")
  , UnitController7635("UnitController7635")
  , LoopController7634("LoopController7634")
  , StridedCounter7630("StridedCounter7630")
  , pipe_BufferWrite7485("pipe_BufferWrite7485",6)
 {
  AddChild(&TopController7640, false);
  AddCtrler(&TopController7640);
  AddChild(&UnitController7639, false);
  AddCtrler(&UnitController7639);
  AddChild(&LoopController7636, false);
  AddCtrler(&LoopController7636);
  LoopController7636.AddCounter(&StridedCounter7642);
  AddChild(&StridedCounter7642, false);
  AddChild(&UnitController7635, false);
  AddCtrler(&UnitController7635);
  AddChild(&LoopController7634, false);
  AddCtrler(&LoopController7634);
  LoopController7634.AddCounter(&StridedCounter7630);
  AddChild(&StridedCounter7630, false);
  AddChild(&pipe_BufferWrite7485, false);
  LoopController7634.AddOutput(&fifo_BufferRead7486, &pipe_BufferWrite7485);
}
void Context7480::Clock() {
  LoopController7634.Clock();
  LoopController7636.Clock();
  StridedCounter7630.Clock();
  StridedCounter7642.Clock();
  TopController7640.Clock();
  UnitController7635.Clock();
  UnitController7639.Clock();
  pipe_BufferWrite7485.Clock();
}
void Context7480::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferWrite7485_en[i] = false;
  }
  if (TopController7640.Enabled()) {
    if (UnitController7639.Enabled()) {
      StridedCounter7642.setMin(Const7638);
      StridedCounter7642.setStep(Const7637);
      StridedCounter7642.setMax(Const7641);
      StridedCounter7642.Eval(); // ControlTree12
      if (LoopController7636.Enabled()) {
        if (UnitController7635.Enabled()) {
          StridedCounter7630.setMin(Const7633);
          StridedCounter7630.setStep(Const7632);
          StridedCounter7630.setMax(Const7631);
          StridedCounter7630.Eval(); // ControlTree126
          CounterIter7629[0] = StridedCounter7630.Iters()[0];
          CounterIter7629[1] = StridedCounter7630.Iters()[1];
          CounterIter7629[2] = StridedCounter7630.Iters()[2];
          CounterIter7629[3] = StridedCounter7630.Iters()[3];
          CounterIter7629[4] = StridedCounter7630.Iters()[4];
          CounterIter7629[5] = StridedCounter7630.Iters()[5];
          CounterIter7629[6] = StridedCounter7630.Iters()[6];
          CounterIter7629[7] = StridedCounter7630.Iters()[7];
          CounterIter7629[8] = StridedCounter7630.Iters()[8];
          CounterIter7629[9] = StridedCounter7630.Iters()[9];
          CounterIter7629[10] = StridedCounter7630.Iters()[10];
          CounterIter7629[11] = StridedCounter7630.Iters()[11];
          CounterIter7629[12] = StridedCounter7630.Iters()[12];
          CounterIter7629[13] = StridedCounter7630.Iters()[13];
          CounterIter7629[14] = StridedCounter7630.Iters()[14];
          CounterIter7629[15] = StridedCounter7630.Iters()[15];
          LoopController7634.EvalLaneValids();
          for (int i = 0; i < 16; i++) {
            laneValid[i] = LoopController7634.LaneValids()[i];
          }
          if (LoopController7634.Enabled()) {
            bool uen = true;
            Active();
            for (int i = 0; i < 16; i++) {
              OpDef7627[i] = CounterIter7629[i] >> Const7628;
            }
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7634.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7485_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7485[i] = BufferWrite7485_en[i] ? OpDef7627[i] : BufferWrite7485[i];
    }
  }
  if (LoopController7634.ChildDone() && Any<16>(BufferWrite7485_en)) {
    Token data = make_token(BufferWrite7485);
    set_token_en<16>(data, BufferWrite7485_en);
    data.done_vec = LoopController7634.LevelsDone()+1;
    pipe_BufferWrite7485.Push(data);
  }
  pipe_BufferWrite7485.Eval();
}
