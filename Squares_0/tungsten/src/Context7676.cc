#include "Context7676.h"
Context7676::Context7676(FIFO<Token, 16>& _fifo_BufferRead7723,FIFO<Token, 64>& _fifo_BufferRead7725,FIFO<Token, 16>& _fifo_BufferRead7727,FIFO<Token, 16>& _fifo_BufferRead7729,NetworkInput& _go8017) :
Context("Context7676","ComputeContainer7892","Context7676 Squares.scala:42:46\nContextInsertion.scala:26\nComputePartitioner.scala:96")
, fifo_BufferRead7723(_fifo_BufferRead7723)
, fifo_BufferRead7725(_fifo_BufferRead7725)
, fifo_BufferRead7727(_fifo_BufferRead7727)
, fifo_BufferRead7729(_fifo_BufferRead7729)
, go8017(_go8017)
  , TopController7876("TopController7876")
  , UnitController7877("UnitController7877")
  , LoopController7878("LoopController7878")
  , StridedCounter7879("StridedCounter7879")
  , LoopController7881("LoopController7881")
  , StridedCounter7882("StridedCounter7882")
  , pipe_BufferWrite7468("pipe_BufferWrite7468",6)
 {
  AddChild(&TopController7876, false);
  AddCtrler(&TopController7876);
  AddChild(&UnitController7877, false);
  AddCtrler(&UnitController7877);
  AddChild(&LoopController7878, false);
  AddCtrler(&LoopController7878);
  LoopController7878.AddCounter(&StridedCounter7879);
  AddChild(&StridedCounter7879, false);
  LoopController7881.AddInput(&fifo_BufferRead7723, false);
  // Initialize BufferRead7723
  for (int i = 0; i < 16; i++) {
    BufferRead7723[i] = 0;
  }
  LoopController7881.AddInput(&fifo_BufferRead7725, false);
  // Initialize BufferRead7725
  for (int i = 0; i < 16; i++) {
    BufferRead7725[i] = false;
  }
  LoopController7881.AddInput(&fifo_BufferRead7727, false);
  // Initialize BufferRead7727
  for (int i = 0; i < 16; i++) {
    BufferRead7727[i] = 0;
  }
  LoopController7881.AddInput(&fifo_BufferRead7729, false);
  // Initialize BufferRead7729
  for (int i = 0; i < 16; i++) {
    BufferRead7729[i] = 0;
  }
  AddChild(&LoopController7881, false);
  AddCtrler(&LoopController7881);
  LoopController7881.AddCounter(&StridedCounter7882);
  AddChild(&StridedCounter7882, false);
  AddChild(&pipe_BufferWrite7468, false);
  LoopController7881.AddOutput(&go8017, &pipe_BufferWrite7468);
}
void Context7676::Clock() {
  LoopController7878.Clock();
  LoopController7881.Clock();
  StridedCounter7879.Clock();
  StridedCounter7882.Clock();
  TopController7876.Clock();
  UnitController7877.Clock();
  pipe_BufferWrite7468.Clock();
}
void Context7676::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7723_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7725_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7727_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7729_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7468_en[i] = false;
  }
  if (TopController7876.Enabled()) {
    if (UnitController7877.Enabled()) {
      StridedCounter7879.setMin(Const7870);
      StridedCounter7879.setStep(Const7874);
      StridedCounter7879.setMax(Const7873);
      StridedCounter7879.Eval(); // ControlTree12
      CounterIter7880 = StridedCounter7879.Iters()[0];
      if (LoopController7878.Enabled()) {
        for (int i = 0; i < 16; i++) {
          BufferRead7723_en[i] = true;
        }
        fifo_BufferRead7723.SetReadEn(BufferRead7723_en);
        if (!fifo_BufferRead7723_loaded && Any<16>(BufferRead7723_en) && fifo_BufferRead7723.Valid()) {
          fifo_BufferRead7723_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7723[i] = toT<uint32_t>(fifo_BufferRead7723.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7725_en[i] = true;
        }
        fifo_BufferRead7725.SetReadEn(BufferRead7725_en);
        if (!fifo_BufferRead7725_loaded && Any<16>(BufferRead7725_en) && fifo_BufferRead7725.Valid()) {
          fifo_BufferRead7725_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7725[i] = toT<bool>(fifo_BufferRead7725.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7727_en[i] = true;
        }
        fifo_BufferRead7727.SetReadEn(BufferRead7727_en);
        if (!fifo_BufferRead7727_loaded && Any<16>(BufferRead7727_en) && fifo_BufferRead7727.Valid()) {
          fifo_BufferRead7727_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7727[i] = toT<uint32_t>(fifo_BufferRead7727.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7729_en[i] = true;
        }
        fifo_BufferRead7729.SetReadEn(BufferRead7729_en);
        if (!fifo_BufferRead7729_loaded && Any<16>(BufferRead7729_en) && fifo_BufferRead7729.Valid()) {
          fifo_BufferRead7729_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7729[i] = toT<uint32_t>(fifo_BufferRead7729.Read(), i);
          }
        }
        StridedCounter7882.setMin(Const7871);
        StridedCounter7882.setStep(Const7872);
        StridedCounter7882.setMax(Const7875);
        StridedCounter7882.Eval(); // ControlTree21
        CounterIter7883[0] = StridedCounter7882.Iters()[0];
        CounterIter7883[1] = StridedCounter7882.Iters()[1];
        CounterIter7883[2] = StridedCounter7882.Iters()[2];
        CounterIter7883[3] = StridedCounter7882.Iters()[3];
        CounterIter7883[4] = StridedCounter7882.Iters()[4];
        CounterIter7883[5] = StridedCounter7882.Iters()[5];
        CounterIter7883[6] = StridedCounter7882.Iters()[6];
        CounterIter7883[7] = StridedCounter7882.Iters()[7];
        CounterIter7883[8] = StridedCounter7882.Iters()[8];
        CounterIter7883[9] = StridedCounter7882.Iters()[9];
        CounterIter7883[10] = StridedCounter7882.Iters()[10];
        CounterIter7883[11] = StridedCounter7882.Iters()[11];
        CounterIter7883[12] = StridedCounter7882.Iters()[12];
        CounterIter7883[13] = StridedCounter7882.Iters()[13];
        CounterIter7883[14] = StridedCounter7882.Iters()[14];
        CounterIter7883[15] = StridedCounter7882.Iters()[15];
        LoopController7881.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7881.LaneValids()[i];
        }
        if (LoopController7881.Enabled()) {
          bool uen = true;
          Active();
          for (int i = 0; i < 16; i++) {
            OpDef83[i] = fixmulh(BufferRead7727[i],BufferRead7727[i]);
          }
          for (int i = 0; i < 16; i++) {
            OpDef89[i] = BufferRead7725[i] ? Const7868 : Const7867;
          }
          for (int i = 0; i < 16; i++) {
            OpDef85[i] = BufferRead7727[i] * BufferRead7729[i] + OpDef83[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef86[i] = BufferRead7729[i] * BufferRead7727[i] + OpDef85[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef92[i] = OpDef86[i] + BufferRead7723[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef93[i] = OpDef92[i] + OpDef89[i];
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7881.ChildDone() && Any<16>(BufferRead7723_en)) {
    fifo_BufferRead7723_loaded = false;
    fifo_BufferRead7723.Pop();
  }
  if (LoopController7881.ChildDone() && Any<16>(BufferRead7725_en)) {
    fifo_BufferRead7725_loaded = false;
    fifo_BufferRead7725.Pop();
  }
  if (LoopController7881.ChildDone() && Any<16>(BufferRead7727_en)) {
    fifo_BufferRead7727_loaded = false;
    fifo_BufferRead7727.Pop();
  }
  if (LoopController7881.ChildDone() && Any<16>(BufferRead7729_en)) {
    fifo_BufferRead7729_loaded = false;
    fifo_BufferRead7729.Pop();
  }
  if (LoopController7881.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7468_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7468[i] = BufferWrite7468_en[i] ? OpDef93[i] : BufferWrite7468[i];
    }
  }
  if (LoopController7881.ChildDone() && Any<16>(BufferWrite7468_en)) {
    Token data = make_token(BufferWrite7468);
    set_token_en<16>(data, BufferWrite7468_en);
    data.done_vec = LoopController7881.LevelsDone()+1;
    pipe_BufferWrite7468.Push(data);
  }
  pipe_BufferWrite7468.Eval();
}
