#include "Context7670.h"
Context7670::Context7670(FIFO<Token, 16>& _fifo_BufferRead7680,FIFO<Token, 16>& _fifo_BufferRead7681,FIFO<Token, 16>& _fifo_BufferRead7683,FIFO<Token, 16>& _fifo_BufferRead7685,FIFO<Token, 16>& _fifo_BufferRead7687,NetworkInput& _go7953,NetworkInput& _go7951,NetworkInput& _go7954,NetworkInput& _go7952) :
Context("Context7670","ComputeContainer7886","Context7670 Squares.scala:42:46\nContextInsertion.scala:26\nComputePartitioner.scala:96")
, fifo_BufferRead7680(_fifo_BufferRead7680)
, fifo_BufferRead7681(_fifo_BufferRead7681)
, fifo_BufferRead7683(_fifo_BufferRead7683)
, fifo_BufferRead7685(_fifo_BufferRead7685)
, fifo_BufferRead7687(_fifo_BufferRead7687)
, go7953(_go7953)
, go7951(_go7951)
, go7954(_go7954)
, go7952(_go7952)
  , TopController7773("TopController7773")
  , UnitController7774("UnitController7774")
  , LoopController7775("LoopController7775")
  , StridedCounter7776("StridedCounter7776")
  , LoopController7778("LoopController7778")
  , StridedCounter7779("StridedCounter7779")
  , pipe_BufferWrite7699("pipe_BufferWrite7699",6)
  , pipe_BufferWrite7688("pipe_BufferWrite7688",6)
  , pipe_BufferWrite7695("pipe_BufferWrite7695",6)
  , pipe_BufferWrite7691("pipe_BufferWrite7691",6)
 {
  AddChild(&TopController7773, false);
  AddCtrler(&TopController7773);
  AddChild(&UnitController7774, false);
  AddCtrler(&UnitController7774);
  AddChild(&LoopController7775, false);
  AddCtrler(&LoopController7775);
  LoopController7775.AddCounter(&StridedCounter7776);
  AddChild(&StridedCounter7776, false);
  LoopController7778.AddInput(&fifo_BufferRead7680, false);
  // Initialize BufferRead7680
  for (int i = 0; i < 16; i++) {
    BufferRead7680[i] = 0;
  }
  LoopController7778.AddInput(&fifo_BufferRead7681, false);
  // Initialize BufferRead7681
  for (int i = 0; i < 16; i++) {
    BufferRead7681[i] = 0;
  }
  LoopController7778.AddInput(&fifo_BufferRead7683, false);
  // Initialize BufferRead7683
  for (int i = 0; i < 16; i++) {
    BufferRead7683[i] = 0;
  }
  LoopController7778.AddInput(&fifo_BufferRead7685, false);
  // Initialize BufferRead7685
  for (int i = 0; i < 16; i++) {
    BufferRead7685[i] = 0;
  }
  LoopController7778.AddInput(&fifo_BufferRead7687, false);
  // Initialize BufferRead7687
  for (int i = 0; i < 16; i++) {
    BufferRead7687[i] = 0;
  }
  AddChild(&LoopController7778, false);
  AddCtrler(&LoopController7778);
  LoopController7778.AddCounter(&StridedCounter7779);
  AddChild(&StridedCounter7779, false);
  AddChild(&pipe_BufferWrite7699, false);
  LoopController7778.AddOutput(&go7953, &pipe_BufferWrite7699);
  AddChild(&pipe_BufferWrite7688, false);
  LoopController7778.AddOutput(&go7951, &pipe_BufferWrite7688);
  AddChild(&pipe_BufferWrite7695, false);
  LoopController7778.AddOutput(&go7954, &pipe_BufferWrite7695);
  AddChild(&pipe_BufferWrite7691, false);
  LoopController7778.AddOutput(&go7952, &pipe_BufferWrite7691);
}
void Context7670::Clock() {
  LoopController7775.Clock();
  LoopController7778.Clock();
  StridedCounter7776.Clock();
  StridedCounter7779.Clock();
  TopController7773.Clock();
  UnitController7774.Clock();
  pipe_BufferWrite7688.Clock();
  pipe_BufferWrite7691.Clock();
  pipe_BufferWrite7695.Clock();
  pipe_BufferWrite7699.Clock();
}
void Context7670::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7680_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7681_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7683_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7685_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7687_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7699_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7688_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7695_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7691_en[i] = false;
  }
  if (TopController7773.Enabled()) {
    if (UnitController7774.Enabled()) {
      StridedCounter7776.setMin(Const7767);
      StridedCounter7776.setStep(Const7771);
      StridedCounter7776.setMax(Const7770);
      StridedCounter7776.Eval(); // ControlTree12
      CounterIter7777 = StridedCounter7776.Iters()[0];
      if (LoopController7775.Enabled()) {
        for (int i = 0; i < 16; i++) {
          BufferRead7680_en[i] = true;
        }
        fifo_BufferRead7680.SetReadEn(BufferRead7680_en);
        if (!fifo_BufferRead7680_loaded && Any<16>(BufferRead7680_en) && fifo_BufferRead7680.Valid()) {
          fifo_BufferRead7680_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7680[i] = toT<uint32_t>(fifo_BufferRead7680.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7681_en[i] = true;
        }
        fifo_BufferRead7681.SetReadEn(BufferRead7681_en);
        if (!fifo_BufferRead7681_loaded && Any<16>(BufferRead7681_en) && fifo_BufferRead7681.Valid()) {
          fifo_BufferRead7681_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7681[i] = toT<uint32_t>(fifo_BufferRead7681.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7683_en[i] = true;
        }
        fifo_BufferRead7683.SetReadEn(BufferRead7683_en);
        if (!fifo_BufferRead7683_loaded && Any<16>(BufferRead7683_en) && fifo_BufferRead7683.Valid()) {
          fifo_BufferRead7683_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7683[i] = toT<uint32_t>(fifo_BufferRead7683.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7685_en[i] = true;
        }
        fifo_BufferRead7685.SetReadEn(BufferRead7685_en);
        if (!fifo_BufferRead7685_loaded && Any<16>(BufferRead7685_en) && fifo_BufferRead7685.Valid()) {
          fifo_BufferRead7685_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7685[i] = toT<uint32_t>(fifo_BufferRead7685.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7687_en[i] = true;
        }
        fifo_BufferRead7687.SetReadEn(BufferRead7687_en);
        if (!fifo_BufferRead7687_loaded && Any<16>(BufferRead7687_en) && fifo_BufferRead7687.Valid()) {
          fifo_BufferRead7687_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7687[i] = toT<uint32_t>(fifo_BufferRead7687.Read(), i);
          }
        }
        StridedCounter7779.setMin(Const7768);
        StridedCounter7779.setStep(Const7769);
        StridedCounter7779.setMax(Const7772);
        StridedCounter7779.Eval(); // ControlTree21
        CounterIter7780[0] = StridedCounter7779.Iters()[0];
        CounterIter7780[1] = StridedCounter7779.Iters()[1];
        CounterIter7780[2] = StridedCounter7779.Iters()[2];
        CounterIter7780[3] = StridedCounter7779.Iters()[3];
        CounterIter7780[4] = StridedCounter7779.Iters()[4];
        CounterIter7780[5] = StridedCounter7779.Iters()[5];
        CounterIter7780[6] = StridedCounter7779.Iters()[6];
        CounterIter7780[7] = StridedCounter7779.Iters()[7];
        CounterIter7780[8] = StridedCounter7779.Iters()[8];
        CounterIter7780[9] = StridedCounter7779.Iters()[9];
        CounterIter7780[10] = StridedCounter7779.Iters()[10];
        CounterIter7780[11] = StridedCounter7779.Iters()[11];
        CounterIter7780[12] = StridedCounter7779.Iters()[12];
        CounterIter7780[13] = StridedCounter7779.Iters()[13];
        CounterIter7780[14] = StridedCounter7779.Iters()[14];
        CounterIter7780[15] = StridedCounter7779.Iters()[15];
        LoopController7778.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7778.LaneValids()[i];
        }
        if (LoopController7778.Enabled()) {
          bool uen = true;
          Active();
          for (int i = 0; i < 16; i++) {
            OpDef51[i] = BufferRead7680[i] * BufferRead7680[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef37[i] = BufferRead7681[i] * Const7765 + BufferRead7687[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef54[i] = OpDef51[i] + BufferRead7680[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef39[i] = BufferRead7683[i] * Const7781 + OpDef37[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef55[i] = OpDef54[i] < OpDef51[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef52[i] = BufferRead7680[i] * OpDef39[i] + BufferRead7685[i];
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7778.ChildDone() && Any<16>(BufferRead7680_en)) {
    fifo_BufferRead7680_loaded = false;
    fifo_BufferRead7680.Pop();
  }
  if (LoopController7778.ChildDone() && Any<16>(BufferRead7681_en)) {
    fifo_BufferRead7681_loaded = false;
    fifo_BufferRead7681.Pop();
  }
  if (LoopController7778.ChildDone() && Any<16>(BufferRead7683_en)) {
    fifo_BufferRead7683_loaded = false;
    fifo_BufferRead7683.Pop();
  }
  if (LoopController7778.ChildDone() && Any<16>(BufferRead7685_en)) {
    fifo_BufferRead7685_loaded = false;
    fifo_BufferRead7685.Pop();
  }
  if (LoopController7778.ChildDone() && Any<16>(BufferRead7687_en)) {
    fifo_BufferRead7687_loaded = false;
    fifo_BufferRead7687.Pop();
  }
  if (LoopController7778.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7699_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7699[i] = BufferWrite7699_en[i] ? OpDef54[i] : BufferWrite7699[i];
    }
  }
  if (LoopController7778.ChildDone() && Any<16>(BufferWrite7699_en)) {
    Token data = make_token(BufferWrite7699);
    set_token_en<16>(data, BufferWrite7699_en);
    data.done_vec = LoopController7778.LevelsDone()+1;
    pipe_BufferWrite7699.Push(data);
  }
  if (LoopController7778.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7688_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7688[i] = BufferWrite7688_en[i] ? OpDef39[i] : BufferWrite7688[i];
    }
  }
  if (LoopController7778.ChildDone() && Any<16>(BufferWrite7688_en)) {
    Token data = make_token(BufferWrite7688);
    set_token_en<16>(data, BufferWrite7688_en);
    data.done_vec = LoopController7778.LevelsDone()+1;
    pipe_BufferWrite7688.Push(data);
  }
  if (LoopController7778.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7695_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7695[i] = BufferWrite7695_en[i] ? OpDef55[i] : BufferWrite7695[i];
    }
  }
  if (LoopController7778.ChildDone() && Any<16>(BufferWrite7695_en)) {
    Token data = make_token(BufferWrite7695);
    set_token_en<16>(data, BufferWrite7695_en);
    data.done_vec = LoopController7778.LevelsDone()+1;
    pipe_BufferWrite7695.Push(data);
  }
  if (LoopController7778.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7691_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7691[i] = BufferWrite7691_en[i] ? OpDef52[i] : BufferWrite7691[i];
    }
  }
  if (LoopController7778.ChildDone() && Any<16>(BufferWrite7691_en)) {
    Token data = make_token(BufferWrite7691);
    set_token_en<16>(data, BufferWrite7691_en);
    data.done_vec = LoopController7778.LevelsDone()+1;
    pipe_BufferWrite7691.Push(data);
  }
  pipe_BufferWrite7688.Eval();
  pipe_BufferWrite7691.Eval();
  pipe_BufferWrite7695.Eval();
  pipe_BufferWrite7699.Eval();
}
