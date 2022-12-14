#include "Context7669.h"
Context7669::Context7669(FIFO<Token, 16>& _fifo_BufferRead7678,NetworkInput& _go7941,NetworkInput& _go7940,NetworkInput& _go7938,NetworkInput& _go7939) :
Context("Context7669","ComputeContainer7885","Context7669 Squares.scala:42:46\nContextInsertion.scala:26\nComputePartitioner.scala:96")
, fifo_BufferRead7678(_fifo_BufferRead7678)
, go7941(_go7941)
, go7940(_go7940)
, go7938(_go7938)
, go7939(_go7939)
  , TopController7755("TopController7755")
  , UnitController7756("UnitController7756")
  , LoopController7757("LoopController7757")
  , StridedCounter7758("StridedCounter7758")
  , LoopController7760("LoopController7760")
  , StridedCounter7761("StridedCounter7761")
  , pipe_BufferWrite7679("pipe_BufferWrite7679",6)
  , pipe_BufferWrite7686("pipe_BufferWrite7686",6)
  , pipe_BufferWrite7684("pipe_BufferWrite7684",6)
  , pipe_BufferWrite7682("pipe_BufferWrite7682",6)
 {
  AddChild(&TopController7755, false);
  AddCtrler(&TopController7755);
  AddChild(&UnitController7756, false);
  AddCtrler(&UnitController7756);
  AddChild(&LoopController7757, false);
  AddCtrler(&LoopController7757);
  LoopController7757.AddCounter(&StridedCounter7758);
  AddChild(&StridedCounter7758, false);
  LoopController7760.AddInput(&fifo_BufferRead7678, false);
  // Initialize BufferRead7678
  for (int i = 0; i < 16; i++) {
    BufferRead7678[i] = 0;
  }
  AddChild(&LoopController7760, false);
  AddCtrler(&LoopController7760);
  LoopController7760.AddCounter(&StridedCounter7761);
  AddChild(&StridedCounter7761, false);
  AddChild(&pipe_BufferWrite7679, false);
  LoopController7760.AddOutput(&go7941, &pipe_BufferWrite7679);
  AddChild(&pipe_BufferWrite7686, false);
  LoopController7760.AddOutput(&go7940, &pipe_BufferWrite7686);
  AddChild(&pipe_BufferWrite7684, false);
  LoopController7760.AddOutput(&go7938, &pipe_BufferWrite7684);
  AddChild(&pipe_BufferWrite7682, false);
  LoopController7760.AddOutput(&go7939, &pipe_BufferWrite7682);
}
void Context7669::Clock() {
  LoopController7757.Clock();
  LoopController7760.Clock();
  StridedCounter7758.Clock();
  StridedCounter7761.Clock();
  TopController7755.Clock();
  UnitController7756.Clock();
  pipe_BufferWrite7679.Clock();
  pipe_BufferWrite7682.Clock();
  pipe_BufferWrite7684.Clock();
  pipe_BufferWrite7686.Clock();
}
void Context7669::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7678_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7679_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7686_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7684_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7682_en[i] = false;
  }
  if (TopController7755.Enabled()) {
    if (UnitController7756.Enabled()) {
      StridedCounter7758.setMin(Const7749);
      StridedCounter7758.setStep(Const7753);
      StridedCounter7758.setMax(Const7752);
      StridedCounter7758.Eval(); // ControlTree12
      CounterIter7759 = StridedCounter7758.Iters()[0];
      if (LoopController7757.Enabled()) {
        for (int i = 0; i < 16; i++) {
          BufferRead7678_en[i] = true;
        }
        fifo_BufferRead7678.SetReadEn(BufferRead7678_en);
        if (!fifo_BufferRead7678_loaded && Any<16>(BufferRead7678_en) && fifo_BufferRead7678.Valid()) {
          fifo_BufferRead7678_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7678[i] = toT<uint32_t>(fifo_BufferRead7678.Read(), i);
          }
        }
        StridedCounter7761.setMin(Const7750);
        StridedCounter7761.setStep(Const7751);
        StridedCounter7761.setMax(Const7754);
        StridedCounter7761.Eval(); // ControlTree21
        CounterIter7762[0] = StridedCounter7761.Iters()[0];
        CounterIter7762[1] = StridedCounter7761.Iters()[1];
        CounterIter7762[2] = StridedCounter7761.Iters()[2];
        CounterIter7762[3] = StridedCounter7761.Iters()[3];
        CounterIter7762[4] = StridedCounter7761.Iters()[4];
        CounterIter7762[5] = StridedCounter7761.Iters()[5];
        CounterIter7762[6] = StridedCounter7761.Iters()[6];
        CounterIter7762[7] = StridedCounter7761.Iters()[7];
        CounterIter7762[8] = StridedCounter7761.Iters()[8];
        CounterIter7762[9] = StridedCounter7761.Iters()[9];
        CounterIter7762[10] = StridedCounter7761.Iters()[10];
        CounterIter7762[11] = StridedCounter7761.Iters()[11];
        CounterIter7762[12] = StridedCounter7761.Iters()[12];
        CounterIter7762[13] = StridedCounter7761.Iters()[13];
        CounterIter7762[14] = StridedCounter7761.Iters()[14];
        CounterIter7762[15] = StridedCounter7761.Iters()[15];
        LoopController7760.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7760.LaneValids()[i];
        }
        if (LoopController7760.Enabled()) {
          bool uen = true;
          Active();
          for (int i = 0; i < 16; i++) {
            OpDef35[i] = BufferRead7678[i] * Const7764;
          }
          for (int i = 0; i < 16; i++) {
            OpDef28[i] = BufferRead7678[i] < Const7745;
          }
          for (int i = 0; i < 16; i++) {
            OpDef33[i] = fixmulh(BufferRead7678[i],Const7763);
          }
          for (int i = 0; i < 16; i++) {
            OpDef50[i] = fixmulh(OpDef35[i],OpDef35[i]);
          }
          for (int i = 0; i < 16; i++) {
            OpDef30[i] = OpDef28[i] ? Const7747 : Const7746;
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7760.ChildDone() && Any<16>(BufferRead7678_en)) {
    fifo_BufferRead7678_loaded = false;
    fifo_BufferRead7678.Pop();
  }
  if (LoopController7760.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7679_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7679[i] = BufferWrite7679_en[i] ? OpDef35[i] : BufferWrite7679[i];
    }
  }
  if (LoopController7760.ChildDone() && Any<16>(BufferWrite7679_en)) {
    Token data = make_token(BufferWrite7679);
    set_token_en<16>(data, BufferWrite7679_en);
    data.done_vec = LoopController7760.LevelsDone()+1;
    pipe_BufferWrite7679.Push(data);
  }
  if (LoopController7760.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7686_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7686[i] = BufferWrite7686_en[i] ? OpDef33[i] : BufferWrite7686[i];
    }
  }
  if (LoopController7760.ChildDone() && Any<16>(BufferWrite7686_en)) {
    Token data = make_token(BufferWrite7686);
    set_token_en<16>(data, BufferWrite7686_en);
    data.done_vec = LoopController7760.LevelsDone()+1;
    pipe_BufferWrite7686.Push(data);
  }
  if (LoopController7760.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7684_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7684[i] = BufferWrite7684_en[i] ? OpDef50[i] : BufferWrite7684[i];
    }
  }
  if (LoopController7760.ChildDone() && Any<16>(BufferWrite7684_en)) {
    Token data = make_token(BufferWrite7684);
    set_token_en<16>(data, BufferWrite7684_en);
    data.done_vec = LoopController7760.LevelsDone()+1;
    pipe_BufferWrite7684.Push(data);
  }
  if (LoopController7760.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7682_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7682[i] = BufferWrite7682_en[i] ? OpDef30[i] : BufferWrite7682[i];
    }
  }
  if (LoopController7760.ChildDone() && Any<16>(BufferWrite7682_en)) {
    Token data = make_token(BufferWrite7682);
    set_token_en<16>(data, BufferWrite7682_en);
    data.done_vec = LoopController7760.LevelsDone()+1;
    pipe_BufferWrite7682.Push(data);
  }
  pipe_BufferWrite7679.Eval();
  pipe_BufferWrite7682.Eval();
  pipe_BufferWrite7684.Eval();
  pipe_BufferWrite7686.Eval();
}
