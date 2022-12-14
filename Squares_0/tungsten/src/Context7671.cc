#include "Context7671.h"
Context7671::Context7671(FIFO<Token, 16>& _fifo_BufferRead7689,FIFO<Token, 16>& _fifo_BufferRead7690,FIFO<Token, 16>& _fifo_BufferRead7692,NetworkInput& _go7964,NetworkInput& _go7963,NetworkInput& _go7965,NetworkInput& _go7962) :
Context("Context7671","ComputeContainer7887","Context7671 Squares.scala:42:46\nContextInsertion.scala:26\nComputePartitioner.scala:96")
, fifo_BufferRead7689(_fifo_BufferRead7689)
, fifo_BufferRead7690(_fifo_BufferRead7690)
, fifo_BufferRead7692(_fifo_BufferRead7692)
, go7964(_go7964)
, go7963(_go7963)
, go7965(_go7965)
, go7962(_go7962)
  , TopController7792("TopController7792")
  , UnitController7793("UnitController7793")
  , LoopController7794("LoopController7794")
  , StridedCounter7795("StridedCounter7795")
  , LoopController7797("LoopController7797")
  , StridedCounter7798("StridedCounter7798")
  , pipe_BufferWrite7697("pipe_BufferWrite7697",6)
  , pipe_BufferWrite7705("pipe_BufferWrite7705",6)
  , pipe_BufferWrite7693("pipe_BufferWrite7693",6)
  , pipe_BufferWrite7707("pipe_BufferWrite7707",6)
 {
  AddChild(&TopController7792, false);
  AddCtrler(&TopController7792);
  AddChild(&UnitController7793, false);
  AddCtrler(&UnitController7793);
  AddChild(&LoopController7794, false);
  AddCtrler(&LoopController7794);
  LoopController7794.AddCounter(&StridedCounter7795);
  AddChild(&StridedCounter7795, false);
  LoopController7797.AddInput(&fifo_BufferRead7689, false);
  // Initialize BufferRead7689
  for (int i = 0; i < 16; i++) {
    BufferRead7689[i] = 0;
  }
  LoopController7797.AddInput(&fifo_BufferRead7690, false);
  // Initialize BufferRead7690
  for (int i = 0; i < 16; i++) {
    BufferRead7690[i] = 0;
  }
  LoopController7797.AddInput(&fifo_BufferRead7692, false);
  // Initialize BufferRead7692
  for (int i = 0; i < 16; i++) {
    BufferRead7692[i] = 0;
  }
  AddChild(&LoopController7797, false);
  AddCtrler(&LoopController7797);
  LoopController7797.AddCounter(&StridedCounter7798);
  AddChild(&StridedCounter7798, false);
  AddChild(&pipe_BufferWrite7697, false);
  LoopController7797.AddOutput(&go7964, &pipe_BufferWrite7697);
  AddChild(&pipe_BufferWrite7705, false);
  LoopController7797.AddOutput(&go7963, &pipe_BufferWrite7705);
  AddChild(&pipe_BufferWrite7693, false);
  LoopController7797.AddOutput(&go7965, &pipe_BufferWrite7693);
  AddChild(&pipe_BufferWrite7707, false);
  LoopController7797.AddOutput(&go7962, &pipe_BufferWrite7707);
}
void Context7671::Clock() {
  LoopController7794.Clock();
  LoopController7797.Clock();
  StridedCounter7795.Clock();
  StridedCounter7798.Clock();
  TopController7792.Clock();
  UnitController7793.Clock();
  pipe_BufferWrite7693.Clock();
  pipe_BufferWrite7697.Clock();
  pipe_BufferWrite7705.Clock();
  pipe_BufferWrite7707.Clock();
}
void Context7671::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7689_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7690_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7692_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7697_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7705_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7693_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7707_en[i] = false;
  }
  if (TopController7792.Enabled()) {
    if (UnitController7793.Enabled()) {
      StridedCounter7795.setMin(Const7786);
      StridedCounter7795.setStep(Const7790);
      StridedCounter7795.setMax(Const7789);
      StridedCounter7795.Eval(); // ControlTree12
      CounterIter7796 = StridedCounter7795.Iters()[0];
      if (LoopController7794.Enabled()) {
        for (int i = 0; i < 16; i++) {
          BufferRead7689_en[i] = true;
        }
        fifo_BufferRead7689.SetReadEn(BufferRead7689_en);
        if (!fifo_BufferRead7689_loaded && Any<16>(BufferRead7689_en) && fifo_BufferRead7689.Valid()) {
          fifo_BufferRead7689_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7689[i] = toT<uint32_t>(fifo_BufferRead7689.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7690_en[i] = true;
        }
        fifo_BufferRead7690.SetReadEn(BufferRead7690_en);
        if (!fifo_BufferRead7690_loaded && Any<16>(BufferRead7690_en) && fifo_BufferRead7690.Valid()) {
          fifo_BufferRead7690_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7690[i] = toT<uint32_t>(fifo_BufferRead7690.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7692_en[i] = true;
        }
        fifo_BufferRead7692.SetReadEn(BufferRead7692_en);
        if (!fifo_BufferRead7692_loaded && Any<16>(BufferRead7692_en) && fifo_BufferRead7692.Valid()) {
          fifo_BufferRead7692_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7692[i] = toT<uint32_t>(fifo_BufferRead7692.Read(), i);
          }
        }
        StridedCounter7798.setMin(Const7787);
        StridedCounter7798.setStep(Const7788);
        StridedCounter7798.setMax(Const7791);
        StridedCounter7798.Eval(); // ControlTree21
        CounterIter7799[0] = StridedCounter7798.Iters()[0];
        CounterIter7799[1] = StridedCounter7798.Iters()[1];
        CounterIter7799[2] = StridedCounter7798.Iters()[2];
        CounterIter7799[3] = StridedCounter7798.Iters()[3];
        CounterIter7799[4] = StridedCounter7798.Iters()[4];
        CounterIter7799[5] = StridedCounter7798.Iters()[5];
        CounterIter7799[6] = StridedCounter7798.Iters()[6];
        CounterIter7799[7] = StridedCounter7798.Iters()[7];
        CounterIter7799[8] = StridedCounter7798.Iters()[8];
        CounterIter7799[9] = StridedCounter7798.Iters()[9];
        CounterIter7799[10] = StridedCounter7798.Iters()[10];
        CounterIter7799[11] = StridedCounter7798.Iters()[11];
        CounterIter7799[12] = StridedCounter7798.Iters()[12];
        CounterIter7799[13] = StridedCounter7798.Iters()[13];
        CounterIter7799[14] = StridedCounter7798.Iters()[14];
        CounterIter7799[15] = StridedCounter7798.Iters()[15];
        LoopController7797.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7797.LaneValids()[i];
        }
        if (LoopController7797.Enabled()) {
          bool uen = true;
          Active();
          for (int i = 0; i < 16; i++) {
            OpDef41[i] = BufferRead7690[i] + Const7800;
          }
          for (int i = 0; i < 16; i++) {
            OpDef47[i] = BufferRead7689[i] + Const7784;
          }
          for (int i = 0; i < 16; i++) {
            OpDef53[i] = BufferRead7689[i] * BufferRead7690[i] + BufferRead7692[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef43[i] = OpDef41[i] < BufferRead7690[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef59[i] = OpDef53[i] + BufferRead7689[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef44[i] = OpDef43[i] ? Const7783 : Const7782;
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7797.ChildDone() && Any<16>(BufferRead7689_en)) {
    fifo_BufferRead7689_loaded = false;
    fifo_BufferRead7689.Pop();
  }
  if (LoopController7797.ChildDone() && Any<16>(BufferRead7690_en)) {
    fifo_BufferRead7690_loaded = false;
    fifo_BufferRead7690.Pop();
  }
  if (LoopController7797.ChildDone() && Any<16>(BufferRead7692_en)) {
    fifo_BufferRead7692_loaded = false;
    fifo_BufferRead7692.Pop();
  }
  if (LoopController7797.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7697_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7697[i] = BufferWrite7697_en[i] ? OpDef41[i] : BufferWrite7697[i];
    }
  }
  if (LoopController7797.ChildDone() && Any<16>(BufferWrite7697_en)) {
    Token data = make_token(BufferWrite7697);
    set_token_en<16>(data, BufferWrite7697_en);
    data.done_vec = LoopController7797.LevelsDone()+1;
    pipe_BufferWrite7697.Push(data);
  }
  if (LoopController7797.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7705_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7705[i] = BufferWrite7705_en[i] ? OpDef47[i] : BufferWrite7705[i];
    }
  }
  if (LoopController7797.ChildDone() && Any<16>(BufferWrite7705_en)) {
    Token data = make_token(BufferWrite7705);
    set_token_en<16>(data, BufferWrite7705_en);
    data.done_vec = LoopController7797.LevelsDone()+1;
    pipe_BufferWrite7705.Push(data);
  }
  if (LoopController7797.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7693_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7693[i] = BufferWrite7693_en[i] ? OpDef59[i] : BufferWrite7693[i];
    }
  }
  if (LoopController7797.ChildDone() && Any<16>(BufferWrite7693_en)) {
    Token data = make_token(BufferWrite7693);
    set_token_en<16>(data, BufferWrite7693_en);
    data.done_vec = LoopController7797.LevelsDone()+1;
    pipe_BufferWrite7693.Push(data);
  }
  if (LoopController7797.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7707_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7707[i] = BufferWrite7707_en[i] ? OpDef44[i] : BufferWrite7707[i];
    }
  }
  if (LoopController7797.ChildDone() && Any<16>(BufferWrite7707_en)) {
    Token data = make_token(BufferWrite7707);
    set_token_en<16>(data, BufferWrite7707_en);
    data.done_vec = LoopController7797.LevelsDone()+1;
    pipe_BufferWrite7707.Push(data);
  }
  pipe_BufferWrite7693.Eval();
  pipe_BufferWrite7697.Eval();
  pipe_BufferWrite7705.Eval();
  pipe_BufferWrite7707.Eval();
}
