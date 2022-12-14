#include "Context7674.h"
Context7674::Context7674(FIFO<Token, 16>& _fifo_BufferRead7710,FIFO<Token, 16>& _fifo_BufferRead7711,FIFO<Token, 16>& _fifo_BufferRead7713,NetworkInput& _go7995,NetworkInput& _go7996,NetworkInput& _go7994) :
Context("Context7674","ComputeContainer7890","Context7674 Squares.scala:42:46\nContextInsertion.scala:26\nComputePartitioner.scala:96")
, fifo_BufferRead7710(_fifo_BufferRead7710)
, fifo_BufferRead7711(_fifo_BufferRead7711)
, fifo_BufferRead7713(_fifo_BufferRead7713)
, go7995(_go7995)
, go7996(_go7996)
, go7994(_go7994)
  , TopController7844("TopController7844")
  , UnitController7845("UnitController7845")
  , LoopController7846("LoopController7846")
  , StridedCounter7847("StridedCounter7847")
  , LoopController7849("LoopController7849")
  , StridedCounter7850("StridedCounter7850")
  , pipe_BufferWrite7728("pipe_BufferWrite7728",6)
  , pipe_BufferWrite7719("pipe_BufferWrite7719",6)
  , pipe_BufferWrite7716("pipe_BufferWrite7716",6)
 {
  AddChild(&TopController7844, false);
  AddCtrler(&TopController7844);
  AddChild(&UnitController7845, false);
  AddCtrler(&UnitController7845);
  AddChild(&LoopController7846, false);
  AddCtrler(&LoopController7846);
  LoopController7846.AddCounter(&StridedCounter7847);
  AddChild(&StridedCounter7847, false);
  LoopController7849.AddInput(&fifo_BufferRead7710, false);
  // Initialize BufferRead7710
  for (int i = 0; i < 16; i++) {
    BufferRead7710[i] = 0;
  }
  LoopController7849.AddInput(&fifo_BufferRead7711, false);
  // Initialize BufferRead7711
  for (int i = 0; i < 16; i++) {
    BufferRead7711[i] = 0;
  }
  LoopController7849.AddInput(&fifo_BufferRead7713, false);
  // Initialize BufferRead7713
  for (int i = 0; i < 16; i++) {
    BufferRead7713[i] = 0;
  }
  AddChild(&LoopController7849, false);
  AddCtrler(&LoopController7849);
  LoopController7849.AddCounter(&StridedCounter7850);
  AddChild(&StridedCounter7850, false);
  AddChild(&pipe_BufferWrite7728, false);
  LoopController7849.AddOutput(&go7995, &pipe_BufferWrite7728);
  AddChild(&pipe_BufferWrite7719, false);
  LoopController7849.AddOutput(&go7996, &pipe_BufferWrite7719);
  AddChild(&pipe_BufferWrite7716, false);
  LoopController7849.AddOutput(&go7994, &pipe_BufferWrite7716);
}
void Context7674::Clock() {
  LoopController7846.Clock();
  LoopController7849.Clock();
  StridedCounter7847.Clock();
  StridedCounter7850.Clock();
  TopController7844.Clock();
  UnitController7845.Clock();
  pipe_BufferWrite7716.Clock();
  pipe_BufferWrite7719.Clock();
  pipe_BufferWrite7728.Clock();
}
void Context7674::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7710_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7711_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7713_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7728_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7719_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7716_en[i] = false;
  }
  if (TopController7844.Enabled()) {
    if (UnitController7845.Enabled()) {
      StridedCounter7847.setMin(Const7838);
      StridedCounter7847.setStep(Const7842);
      StridedCounter7847.setMax(Const7841);
      StridedCounter7847.Eval(); // ControlTree12
      CounterIter7848 = StridedCounter7847.Iters()[0];
      if (LoopController7846.Enabled()) {
        for (int i = 0; i < 16; i++) {
          BufferRead7710_en[i] = true;
        }
        fifo_BufferRead7710.SetReadEn(BufferRead7710_en);
        if (!fifo_BufferRead7710_loaded && Any<16>(BufferRead7710_en) && fifo_BufferRead7710.Valid()) {
          fifo_BufferRead7710_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7710[i] = toT<uint32_t>(fifo_BufferRead7710.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7711_en[i] = true;
        }
        fifo_BufferRead7711.SetReadEn(BufferRead7711_en);
        if (!fifo_BufferRead7711_loaded && Any<16>(BufferRead7711_en) && fifo_BufferRead7711.Valid()) {
          fifo_BufferRead7711_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7711[i] = toT<uint32_t>(fifo_BufferRead7711.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7713_en[i] = true;
        }
        fifo_BufferRead7713.SetReadEn(BufferRead7713_en);
        if (!fifo_BufferRead7713_loaded && Any<16>(BufferRead7713_en) && fifo_BufferRead7713.Valid()) {
          fifo_BufferRead7713_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7713[i] = toT<uint32_t>(fifo_BufferRead7713.Read(), i);
          }
        }
        StridedCounter7850.setMin(Const7839);
        StridedCounter7850.setStep(Const7840);
        StridedCounter7850.setMax(Const7843);
        StridedCounter7850.Eval(); // ControlTree21
        CounterIter7851[0] = StridedCounter7850.Iters()[0];
        CounterIter7851[1] = StridedCounter7850.Iters()[1];
        CounterIter7851[2] = StridedCounter7850.Iters()[2];
        CounterIter7851[3] = StridedCounter7850.Iters()[3];
        CounterIter7851[4] = StridedCounter7850.Iters()[4];
        CounterIter7851[5] = StridedCounter7850.Iters()[5];
        CounterIter7851[6] = StridedCounter7850.Iters()[6];
        CounterIter7851[7] = StridedCounter7850.Iters()[7];
        CounterIter7851[8] = StridedCounter7850.Iters()[8];
        CounterIter7851[9] = StridedCounter7850.Iters()[9];
        CounterIter7851[10] = StridedCounter7850.Iters()[10];
        CounterIter7851[11] = StridedCounter7850.Iters()[11];
        CounterIter7851[12] = StridedCounter7850.Iters()[12];
        CounterIter7851[13] = StridedCounter7850.Iters()[13];
        CounterIter7851[14] = StridedCounter7850.Iters()[14];
        CounterIter7851[15] = StridedCounter7850.Iters()[15];
        LoopController7849.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7849.LaneValids()[i];
        }
        if (LoopController7849.Enabled()) {
          bool uen = true;
          Active();
          for (int i = 0; i < 16; i++) {
            OpDef73[i] = BufferRead7713[i] * BufferRead7713[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef72[i] = fixmulh(BufferRead7713[i],BufferRead7713[i]);
          }
          for (int i = 0; i < 16; i++) {
            OpDef76[i] = OpDef73[i] + BufferRead7711[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef74[i] = BufferRead7713[i] * BufferRead7710[i] + OpDef72[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef77[i] = OpDef76[i] < OpDef73[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef78[i] = OpDef77[i] ? Const7836 : Const7835;
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7849.ChildDone() && Any<16>(BufferRead7710_en)) {
    fifo_BufferRead7710_loaded = false;
    fifo_BufferRead7710.Pop();
  }
  if (LoopController7849.ChildDone() && Any<16>(BufferRead7711_en)) {
    fifo_BufferRead7711_loaded = false;
    fifo_BufferRead7711.Pop();
  }
  if (LoopController7849.ChildDone() && Any<16>(BufferRead7713_en)) {
    fifo_BufferRead7713_loaded = false;
    fifo_BufferRead7713.Pop();
  }
  if (LoopController7849.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7728_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7728[i] = BufferWrite7728_en[i] ? OpDef76[i] : BufferWrite7728[i];
    }
  }
  if (LoopController7849.ChildDone() && Any<16>(BufferWrite7728_en)) {
    Token data = make_token(BufferWrite7728);
    set_token_en<16>(data, BufferWrite7728_en);
    data.done_vec = LoopController7849.LevelsDone()+1;
    pipe_BufferWrite7728.Push(data);
  }
  if (LoopController7849.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7719_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7719[i] = BufferWrite7719_en[i] ? OpDef74[i] : BufferWrite7719[i];
    }
  }
  if (LoopController7849.ChildDone() && Any<16>(BufferWrite7719_en)) {
    Token data = make_token(BufferWrite7719);
    set_token_en<16>(data, BufferWrite7719_en);
    data.done_vec = LoopController7849.LevelsDone()+1;
    pipe_BufferWrite7719.Push(data);
  }
  if (LoopController7849.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7716_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7716[i] = BufferWrite7716_en[i] ? OpDef78[i] : BufferWrite7716[i];
    }
  }
  if (LoopController7849.ChildDone() && Any<16>(BufferWrite7716_en)) {
    Token data = make_token(BufferWrite7716);
    set_token_en<16>(data, BufferWrite7716_en);
    data.done_vec = LoopController7849.LevelsDone()+1;
    pipe_BufferWrite7716.Push(data);
  }
  pipe_BufferWrite7716.Eval();
  pipe_BufferWrite7719.Eval();
  pipe_BufferWrite7728.Eval();
}
