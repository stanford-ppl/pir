#include "Context7673.h"
Context7673::Context7673(FIFO<Token, 16>& _fifo_BufferRead7700,FIFO<Token, 16>& _fifo_BufferRead7702,FIFO<Token, 16>& _fifo_BufferRead7704,FIFO<Token, 16>& _fifo_BufferRead7706,FIFO<Token, 16>& _fifo_BufferRead7708,NetworkInput& _go7986,NetworkInput& _go7985) :
Context("Context7673","ComputeContainer7889","Context7673 Squares.scala:42:46\nContextInsertion.scala:26\nComputePartitioner.scala:96")
, fifo_BufferRead7700(_fifo_BufferRead7700)
, fifo_BufferRead7702(_fifo_BufferRead7702)
, fifo_BufferRead7704(_fifo_BufferRead7704)
, fifo_BufferRead7706(_fifo_BufferRead7706)
, fifo_BufferRead7708(_fifo_BufferRead7708)
, go7986(_go7986)
, go7985(_go7985)
  , TopController7827("TopController7827")
  , UnitController7828("UnitController7828")
  , LoopController7829("LoopController7829")
  , StridedCounter7830("StridedCounter7830")
  , LoopController7832("LoopController7832")
  , StridedCounter7833("StridedCounter7833")
  , pipe_BufferWrite7722("pipe_BufferWrite7722",6)
  , pipe_BufferWrite7712("pipe_BufferWrite7712",6)
 {
  AddChild(&TopController7827, false);
  AddCtrler(&TopController7827);
  AddChild(&UnitController7828, false);
  AddCtrler(&UnitController7828);
  AddChild(&LoopController7829, false);
  AddCtrler(&LoopController7829);
  LoopController7829.AddCounter(&StridedCounter7830);
  AddChild(&StridedCounter7830, false);
  LoopController7832.AddInput(&fifo_BufferRead7700, false);
  // Initialize BufferRead7700
  for (int i = 0; i < 16; i++) {
    BufferRead7700[i] = 0;
  }
  LoopController7832.AddInput(&fifo_BufferRead7702, false);
  // Initialize BufferRead7702
  for (int i = 0; i < 16; i++) {
    BufferRead7702[i] = 0;
  }
  LoopController7832.AddInput(&fifo_BufferRead7704, false);
  // Initialize BufferRead7704
  for (int i = 0; i < 16; i++) {
    BufferRead7704[i] = 0;
  }
  LoopController7832.AddInput(&fifo_BufferRead7706, false);
  // Initialize BufferRead7706
  for (int i = 0; i < 16; i++) {
    BufferRead7706[i] = 0;
  }
  LoopController7832.AddInput(&fifo_BufferRead7708, false);
  // Initialize BufferRead7708
  for (int i = 0; i < 16; i++) {
    BufferRead7708[i] = 0;
  }
  AddChild(&LoopController7832, false);
  AddCtrler(&LoopController7832);
  LoopController7832.AddCounter(&StridedCounter7833);
  AddChild(&StridedCounter7833, false);
  AddChild(&pipe_BufferWrite7722, false);
  LoopController7832.AddOutput(&go7986, &pipe_BufferWrite7722);
  AddChild(&pipe_BufferWrite7712, false);
  LoopController7832.AddOutput(&go7985, &pipe_BufferWrite7712);
}
void Context7673::Clock() {
  LoopController7829.Clock();
  LoopController7832.Clock();
  StridedCounter7830.Clock();
  StridedCounter7833.Clock();
  TopController7827.Clock();
  UnitController7828.Clock();
  pipe_BufferWrite7712.Clock();
  pipe_BufferWrite7722.Clock();
}
void Context7673::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7700_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7702_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7704_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7706_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7708_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7722_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7712_en[i] = false;
  }
  if (TopController7827.Enabled()) {
    if (UnitController7828.Enabled()) {
      StridedCounter7830.setMin(Const7821);
      StridedCounter7830.setStep(Const7825);
      StridedCounter7830.setMax(Const7824);
      StridedCounter7830.Eval(); // ControlTree12
      CounterIter7831 = StridedCounter7830.Iters()[0];
      if (LoopController7829.Enabled()) {
        for (int i = 0; i < 16; i++) {
          BufferRead7700_en[i] = true;
        }
        fifo_BufferRead7700.SetReadEn(BufferRead7700_en);
        if (!fifo_BufferRead7700_loaded && Any<16>(BufferRead7700_en) && fifo_BufferRead7700.Valid()) {
          fifo_BufferRead7700_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7700[i] = toT<uint32_t>(fifo_BufferRead7700.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7702_en[i] = true;
        }
        fifo_BufferRead7702.SetReadEn(BufferRead7702_en);
        if (!fifo_BufferRead7702_loaded && Any<16>(BufferRead7702_en) && fifo_BufferRead7702.Valid()) {
          fifo_BufferRead7702_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7702[i] = toT<uint32_t>(fifo_BufferRead7702.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7704_en[i] = true;
        }
        fifo_BufferRead7704.SetReadEn(BufferRead7704_en);
        if (!fifo_BufferRead7704_loaded && Any<16>(BufferRead7704_en) && fifo_BufferRead7704.Valid()) {
          fifo_BufferRead7704_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7704[i] = toT<uint32_t>(fifo_BufferRead7704.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7706_en[i] = true;
        }
        fifo_BufferRead7706.SetReadEn(BufferRead7706_en);
        if (!fifo_BufferRead7706_loaded && Any<16>(BufferRead7706_en) && fifo_BufferRead7706.Valid()) {
          fifo_BufferRead7706_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7706[i] = toT<uint32_t>(fifo_BufferRead7706.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7708_en[i] = true;
        }
        fifo_BufferRead7708.SetReadEn(BufferRead7708_en);
        if (!fifo_BufferRead7708_loaded && Any<16>(BufferRead7708_en) && fifo_BufferRead7708.Valid()) {
          fifo_BufferRead7708_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7708[i] = toT<uint32_t>(fifo_BufferRead7708.Read(), i);
          }
        }
        StridedCounter7833.setMin(Const7822);
        StridedCounter7833.setStep(Const7823);
        StridedCounter7833.setMax(Const7826);
        StridedCounter7833.Eval(); // ControlTree21
        CounterIter7834[0] = StridedCounter7833.Iters()[0];
        CounterIter7834[1] = StridedCounter7833.Iters()[1];
        CounterIter7834[2] = StridedCounter7833.Iters()[2];
        CounterIter7834[3] = StridedCounter7833.Iters()[3];
        CounterIter7834[4] = StridedCounter7833.Iters()[4];
        CounterIter7834[5] = StridedCounter7833.Iters()[5];
        CounterIter7834[6] = StridedCounter7833.Iters()[6];
        CounterIter7834[7] = StridedCounter7833.Iters()[7];
        CounterIter7834[8] = StridedCounter7833.Iters()[8];
        CounterIter7834[9] = StridedCounter7833.Iters()[9];
        CounterIter7834[10] = StridedCounter7833.Iters()[10];
        CounterIter7834[11] = StridedCounter7833.Iters()[11];
        CounterIter7834[12] = StridedCounter7833.Iters()[12];
        CounterIter7834[13] = StridedCounter7833.Iters()[13];
        CounterIter7834[14] = StridedCounter7833.Iters()[14];
        CounterIter7834[15] = StridedCounter7833.Iters()[15];
        LoopController7832.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7832.LaneValids()[i];
        }
        if (LoopController7832.Enabled()) {
          bool uen = true;
          Active();
          for (int i = 0; i < 16; i++) {
            OpDef61[i] = fixmulh(BufferRead7704[i],BufferRead7704[i]);
          }
          for (int i = 0; i < 16; i++) {
            OpDef49[i] = BufferRead7706[i] + BufferRead7708[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef63[i] = BufferRead7704[i] * BufferRead7700[i] + OpDef61[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef64[i] = BufferRead7700[i] * BufferRead7704[i] + OpDef63[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef70[i] = OpDef64[i] + OpDef49[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef71[i] = OpDef70[i] + BufferRead7702[i];
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7832.ChildDone() && Any<16>(BufferRead7700_en)) {
    fifo_BufferRead7700_loaded = false;
    fifo_BufferRead7700.Pop();
  }
  if (LoopController7832.ChildDone() && Any<16>(BufferRead7702_en)) {
    fifo_BufferRead7702_loaded = false;
    fifo_BufferRead7702.Pop();
  }
  if (LoopController7832.ChildDone() && Any<16>(BufferRead7704_en)) {
    fifo_BufferRead7704_loaded = false;
    fifo_BufferRead7704.Pop();
  }
  if (LoopController7832.ChildDone() && Any<16>(BufferRead7706_en)) {
    fifo_BufferRead7706_loaded = false;
    fifo_BufferRead7706.Pop();
  }
  if (LoopController7832.ChildDone() && Any<16>(BufferRead7708_en)) {
    fifo_BufferRead7708_loaded = false;
    fifo_BufferRead7708.Pop();
  }
  if (LoopController7832.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7722_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7722[i] = BufferWrite7722_en[i] ? OpDef49[i] : BufferWrite7722[i];
    }
  }
  if (LoopController7832.ChildDone() && Any<16>(BufferWrite7722_en)) {
    Token data = make_token(BufferWrite7722);
    set_token_en<16>(data, BufferWrite7722_en);
    data.done_vec = LoopController7832.LevelsDone()+1;
    pipe_BufferWrite7722.Push(data);
  }
  if (LoopController7832.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7712_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7712[i] = BufferWrite7712_en[i] ? OpDef71[i] : BufferWrite7712[i];
    }
  }
  if (LoopController7832.ChildDone() && Any<16>(BufferWrite7712_en)) {
    Token data = make_token(BufferWrite7712);
    set_token_en<16>(data, BufferWrite7712_en);
    data.done_vec = LoopController7832.LevelsDone()+1;
    pipe_BufferWrite7712.Push(data);
  }
  pipe_BufferWrite7712.Eval();
  pipe_BufferWrite7722.Eval();
}
