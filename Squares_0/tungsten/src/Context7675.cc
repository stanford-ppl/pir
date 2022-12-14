#include "Context7675.h"
Context7675::Context7675(FIFO<Token, 16>& _fifo_BufferRead7714,FIFO<Token, 16>& _fifo_BufferRead7715,FIFO<Token, 16>& _fifo_BufferRead7717,FIFO<Token, 16>& _fifo_BufferRead7718,FIFO<Token, 16>& _fifo_BufferRead7720,FIFO<Token, 16>& _fifo_BufferRead7721,NetworkInput& _go8007,NetworkInput& _go8008) :
Context("Context7675","ComputeContainer7891","Context7675 Squares.scala:42:46\nContextInsertion.scala:26\nComputePartitioner.scala:96")
, fifo_BufferRead7714(_fifo_BufferRead7714)
, fifo_BufferRead7715(_fifo_BufferRead7715)
, fifo_BufferRead7717(_fifo_BufferRead7717)
, fifo_BufferRead7718(_fifo_BufferRead7718)
, fifo_BufferRead7720(_fifo_BufferRead7720)
, fifo_BufferRead7721(_fifo_BufferRead7721)
, go8007(_go8007)
, go8008(_go8008)
  , TopController7859("TopController7859")
  , UnitController7860("UnitController7860")
  , LoopController7861("LoopController7861")
  , StridedCounter7862("StridedCounter7862")
  , LoopController7864("LoopController7864")
  , StridedCounter7865("StridedCounter7865")
  , pipe_BufferWrite7726("pipe_BufferWrite7726",6)
  , pipe_BufferWrite7724("pipe_BufferWrite7724",6)
 {
  AddChild(&TopController7859, false);
  AddCtrler(&TopController7859);
  AddChild(&UnitController7860, false);
  AddCtrler(&UnitController7860);
  AddChild(&LoopController7861, false);
  AddCtrler(&LoopController7861);
  LoopController7861.AddCounter(&StridedCounter7862);
  AddChild(&StridedCounter7862, false);
  LoopController7864.AddInput(&fifo_BufferRead7714, false);
  // Initialize BufferRead7714
  for (int i = 0; i < 16; i++) {
    BufferRead7714[i] = 0;
  }
  LoopController7864.AddInput(&fifo_BufferRead7715, false);
  // Initialize BufferRead7715
  for (int i = 0; i < 16; i++) {
    BufferRead7715[i] = 0;
  }
  LoopController7864.AddInput(&fifo_BufferRead7717, false);
  // Initialize BufferRead7717
  for (int i = 0; i < 16; i++) {
    BufferRead7717[i] = 0;
  }
  LoopController7864.AddInput(&fifo_BufferRead7718, false);
  // Initialize BufferRead7718
  for (int i = 0; i < 16; i++) {
    BufferRead7718[i] = 0;
  }
  LoopController7864.AddInput(&fifo_BufferRead7720, false);
  // Initialize BufferRead7720
  for (int i = 0; i < 16; i++) {
    BufferRead7720[i] = 0;
  }
  LoopController7864.AddInput(&fifo_BufferRead7721, false);
  // Initialize BufferRead7721
  for (int i = 0; i < 16; i++) {
    BufferRead7721[i] = 0;
  }
  AddChild(&LoopController7864, false);
  AddCtrler(&LoopController7864);
  LoopController7864.AddCounter(&StridedCounter7865);
  AddChild(&StridedCounter7865, false);
  AddChild(&pipe_BufferWrite7726, false);
  LoopController7864.AddOutput(&go8007, &pipe_BufferWrite7726);
  AddChild(&pipe_BufferWrite7724, false);
  LoopController7864.AddOutput(&go8008, &pipe_BufferWrite7724);
}
void Context7675::Clock() {
  LoopController7861.Clock();
  LoopController7864.Clock();
  StridedCounter7862.Clock();
  StridedCounter7865.Clock();
  TopController7859.Clock();
  UnitController7860.Clock();
  pipe_BufferWrite7724.Clock();
  pipe_BufferWrite7726.Clock();
}
void Context7675::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7714_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7715_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7717_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7718_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7720_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7721_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7726_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7724_en[i] = false;
  }
  if (TopController7859.Enabled()) {
    if (UnitController7860.Enabled()) {
      StridedCounter7862.setMin(Const7853);
      StridedCounter7862.setStep(Const7857);
      StridedCounter7862.setMax(Const7856);
      StridedCounter7862.Eval(); // ControlTree12
      CounterIter7863 = StridedCounter7862.Iters()[0];
      if (LoopController7861.Enabled()) {
        for (int i = 0; i < 16; i++) {
          BufferRead7714_en[i] = true;
        }
        fifo_BufferRead7714.SetReadEn(BufferRead7714_en);
        if (!fifo_BufferRead7714_loaded && Any<16>(BufferRead7714_en) && fifo_BufferRead7714.Valid()) {
          fifo_BufferRead7714_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7714[i] = toT<uint32_t>(fifo_BufferRead7714.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7715_en[i] = true;
        }
        fifo_BufferRead7715.SetReadEn(BufferRead7715_en);
        if (!fifo_BufferRead7715_loaded && Any<16>(BufferRead7715_en) && fifo_BufferRead7715.Valid()) {
          fifo_BufferRead7715_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7715[i] = toT<uint32_t>(fifo_BufferRead7715.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7717_en[i] = true;
        }
        fifo_BufferRead7717.SetReadEn(BufferRead7717_en);
        if (!fifo_BufferRead7717_loaded && Any<16>(BufferRead7717_en) && fifo_BufferRead7717.Valid()) {
          fifo_BufferRead7717_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7717[i] = toT<uint32_t>(fifo_BufferRead7717.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7718_en[i] = true;
        }
        fifo_BufferRead7718.SetReadEn(BufferRead7718_en);
        if (!fifo_BufferRead7718_loaded && Any<16>(BufferRead7718_en) && fifo_BufferRead7718.Valid()) {
          fifo_BufferRead7718_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7718[i] = toT<uint32_t>(fifo_BufferRead7718.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7720_en[i] = true;
        }
        fifo_BufferRead7720.SetReadEn(BufferRead7720_en);
        if (!fifo_BufferRead7720_loaded && Any<16>(BufferRead7720_en) && fifo_BufferRead7720.Valid()) {
          fifo_BufferRead7720_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7720[i] = toT<uint32_t>(fifo_BufferRead7720.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7721_en[i] = true;
        }
        fifo_BufferRead7721.SetReadEn(BufferRead7721_en);
        if (!fifo_BufferRead7721_loaded && Any<16>(BufferRead7721_en) && fifo_BufferRead7721.Valid()) {
          fifo_BufferRead7721_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7721[i] = toT<uint32_t>(fifo_BufferRead7721.Read(), i);
          }
        }
        StridedCounter7865.setMin(Const7854);
        StridedCounter7865.setStep(Const7855);
        StridedCounter7865.setMax(Const7858);
        StridedCounter7865.Eval(); // ControlTree21
        CounterIter7866[0] = StridedCounter7865.Iters()[0];
        CounterIter7866[1] = StridedCounter7865.Iters()[1];
        CounterIter7866[2] = StridedCounter7865.Iters()[2];
        CounterIter7866[3] = StridedCounter7865.Iters()[3];
        CounterIter7866[4] = StridedCounter7865.Iters()[4];
        CounterIter7866[5] = StridedCounter7865.Iters()[5];
        CounterIter7866[6] = StridedCounter7865.Iters()[6];
        CounterIter7866[7] = StridedCounter7865.Iters()[7];
        CounterIter7866[8] = StridedCounter7865.Iters()[8];
        CounterIter7866[9] = StridedCounter7865.Iters()[9];
        CounterIter7866[10] = StridedCounter7865.Iters()[10];
        CounterIter7866[11] = StridedCounter7865.Iters()[11];
        CounterIter7866[12] = StridedCounter7865.Iters()[12];
        CounterIter7866[13] = StridedCounter7865.Iters()[13];
        CounterIter7866[14] = StridedCounter7865.Iters()[14];
        CounterIter7866[15] = StridedCounter7865.Iters()[15];
        LoopController7864.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7864.LaneValids()[i];
        }
        if (LoopController7864.Enabled()) {
          bool uen = true;
          Active();
          for (int i = 0; i < 16; i++) {
            OpDef75[i] = BufferRead7714[i] * BufferRead7721[i] + BufferRead7720[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef81[i] = OpDef75[i] + BufferRead7715[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef82[i] = OpDef81[i] + BufferRead7717[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef84[i] = OpDef82[i] * OpDef82[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef87[i] = OpDef84[i] + BufferRead7718[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef88[i] = OpDef87[i] < OpDef84[i];
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7864.ChildDone() && Any<16>(BufferRead7714_en)) {
    fifo_BufferRead7714_loaded = false;
    fifo_BufferRead7714.Pop();
  }
  if (LoopController7864.ChildDone() && Any<16>(BufferRead7715_en)) {
    fifo_BufferRead7715_loaded = false;
    fifo_BufferRead7715.Pop();
  }
  if (LoopController7864.ChildDone() && Any<16>(BufferRead7717_en)) {
    fifo_BufferRead7717_loaded = false;
    fifo_BufferRead7717.Pop();
  }
  if (LoopController7864.ChildDone() && Any<16>(BufferRead7718_en)) {
    fifo_BufferRead7718_loaded = false;
    fifo_BufferRead7718.Pop();
  }
  if (LoopController7864.ChildDone() && Any<16>(BufferRead7720_en)) {
    fifo_BufferRead7720_loaded = false;
    fifo_BufferRead7720.Pop();
  }
  if (LoopController7864.ChildDone() && Any<16>(BufferRead7721_en)) {
    fifo_BufferRead7721_loaded = false;
    fifo_BufferRead7721.Pop();
  }
  if (LoopController7864.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7726_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7726[i] = BufferWrite7726_en[i] ? OpDef82[i] : BufferWrite7726[i];
    }
  }
  if (LoopController7864.ChildDone() && Any<16>(BufferWrite7726_en)) {
    Token data = make_token(BufferWrite7726);
    set_token_en<16>(data, BufferWrite7726_en);
    data.done_vec = LoopController7864.LevelsDone()+1;
    pipe_BufferWrite7726.Push(data);
  }
  if (LoopController7864.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7724_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7724[i] = BufferWrite7724_en[i] ? OpDef88[i] : BufferWrite7724[i];
    }
  }
  if (LoopController7864.ChildDone() && Any<16>(BufferWrite7724_en)) {
    Token data = make_token(BufferWrite7724);
    set_token_en<16>(data, BufferWrite7724_en);
    data.done_vec = LoopController7864.LevelsDone()+1;
    pipe_BufferWrite7724.Push(data);
  }
  pipe_BufferWrite7724.Eval();
  pipe_BufferWrite7726.Eval();
}
