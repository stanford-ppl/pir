#include "Context7672.h"
Context7672::Context7672(FIFO<Token, 16>& _fifo_BufferRead7694,FIFO<Token, 64>& _fifo_BufferRead7696,FIFO<Token, 16>& _fifo_BufferRead7698,NetworkInput& _go7973,NetworkInput& _go7975,NetworkInput& _go7974) :
Context("Context7672","ComputeContainer7888","Context7672 Squares.scala:42:46\nContextInsertion.scala:26\nComputePartitioner.scala:96")
, fifo_BufferRead7694(_fifo_BufferRead7694)
, fifo_BufferRead7696(_fifo_BufferRead7696)
, fifo_BufferRead7698(_fifo_BufferRead7698)
, go7973(_go7973)
, go7975(_go7975)
, go7974(_go7974)
  , TopController7812("TopController7812")
  , UnitController7813("UnitController7813")
  , LoopController7814("LoopController7814")
  , StridedCounter7815("StridedCounter7815")
  , LoopController7817("LoopController7817")
  , StridedCounter7818("StridedCounter7818")
  , pipe_BufferWrite7703("pipe_BufferWrite7703",6)
  , pipe_BufferWrite7709("pipe_BufferWrite7709",6)
  , pipe_BufferWrite7701("pipe_BufferWrite7701",6)
 {
  AddChild(&TopController7812, false);
  AddCtrler(&TopController7812);
  AddChild(&UnitController7813, false);
  AddCtrler(&UnitController7813);
  AddChild(&LoopController7814, false);
  AddCtrler(&LoopController7814);
  LoopController7814.AddCounter(&StridedCounter7815);
  AddChild(&StridedCounter7815, false);
  LoopController7817.AddInput(&fifo_BufferRead7694, false);
  // Initialize BufferRead7694
  for (int i = 0; i < 16; i++) {
    BufferRead7694[i] = 0;
  }
  LoopController7817.AddInput(&fifo_BufferRead7696, false);
  // Initialize BufferRead7696
  for (int i = 0; i < 16; i++) {
    BufferRead7696[i] = false;
  }
  LoopController7817.AddInput(&fifo_BufferRead7698, false);
  // Initialize BufferRead7698
  for (int i = 0; i < 16; i++) {
    BufferRead7698[i] = 0;
  }
  AddChild(&LoopController7817, false);
  AddCtrler(&LoopController7817);
  LoopController7817.AddCounter(&StridedCounter7818);
  AddChild(&StridedCounter7818, false);
  AddChild(&pipe_BufferWrite7703, false);
  LoopController7817.AddOutput(&go7973, &pipe_BufferWrite7703);
  AddChild(&pipe_BufferWrite7709, false);
  LoopController7817.AddOutput(&go7975, &pipe_BufferWrite7709);
  AddChild(&pipe_BufferWrite7701, false);
  LoopController7817.AddOutput(&go7974, &pipe_BufferWrite7701);
}
void Context7672::Clock() {
  LoopController7814.Clock();
  LoopController7817.Clock();
  StridedCounter7815.Clock();
  StridedCounter7818.Clock();
  TopController7812.Clock();
  UnitController7813.Clock();
  pipe_BufferWrite7701.Clock();
  pipe_BufferWrite7703.Clock();
  pipe_BufferWrite7709.Clock();
}
void Context7672::Eval() {
  for (int i = 0; i < 16; i++) {
    BufferRead7694_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7696_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferRead7698_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7703_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7709_en[i] = false;
  }
  for (int i = 0; i < 16; i++) {
    BufferWrite7701_en[i] = false;
  }
  if (TopController7812.Enabled()) {
    if (UnitController7813.Enabled()) {
      StridedCounter7815.setMin(Const7806);
      StridedCounter7815.setStep(Const7810);
      StridedCounter7815.setMax(Const7809);
      StridedCounter7815.Eval(); // ControlTree12
      CounterIter7816 = StridedCounter7815.Iters()[0];
      if (LoopController7814.Enabled()) {
        for (int i = 0; i < 16; i++) {
          BufferRead7694_en[i] = true;
        }
        fifo_BufferRead7694.SetReadEn(BufferRead7694_en);
        if (!fifo_BufferRead7694_loaded && Any<16>(BufferRead7694_en) && fifo_BufferRead7694.Valid()) {
          fifo_BufferRead7694_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7694[i] = toT<uint32_t>(fifo_BufferRead7694.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7696_en[i] = true;
        }
        fifo_BufferRead7696.SetReadEn(BufferRead7696_en);
        if (!fifo_BufferRead7696_loaded && Any<16>(BufferRead7696_en) && fifo_BufferRead7696.Valid()) {
          fifo_BufferRead7696_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7696[i] = toT<bool>(fifo_BufferRead7696.Read(), i);
          }
        }
        for (int i = 0; i < 16; i++) {
          BufferRead7698_en[i] = true;
        }
        fifo_BufferRead7698.SetReadEn(BufferRead7698_en);
        if (!fifo_BufferRead7698_loaded && Any<16>(BufferRead7698_en) && fifo_BufferRead7698.Valid()) {
          fifo_BufferRead7698_loaded = true;
          for (int i = 0; i < 16; i++) {
            BufferRead7698[i] = toT<uint32_t>(fifo_BufferRead7698.Read(), i);
          }
        }
        StridedCounter7818.setMin(Const7807);
        StridedCounter7818.setStep(Const7808);
        StridedCounter7818.setMax(Const7811);
        StridedCounter7818.Eval(); // ControlTree21
        CounterIter7819[0] = StridedCounter7818.Iters()[0];
        CounterIter7819[1] = StridedCounter7818.Iters()[1];
        CounterIter7819[2] = StridedCounter7818.Iters()[2];
        CounterIter7819[3] = StridedCounter7818.Iters()[3];
        CounterIter7819[4] = StridedCounter7818.Iters()[4];
        CounterIter7819[5] = StridedCounter7818.Iters()[5];
        CounterIter7819[6] = StridedCounter7818.Iters()[6];
        CounterIter7819[7] = StridedCounter7818.Iters()[7];
        CounterIter7819[8] = StridedCounter7818.Iters()[8];
        CounterIter7819[9] = StridedCounter7818.Iters()[9];
        CounterIter7819[10] = StridedCounter7818.Iters()[10];
        CounterIter7819[11] = StridedCounter7818.Iters()[11];
        CounterIter7819[12] = StridedCounter7818.Iters()[12];
        CounterIter7819[13] = StridedCounter7818.Iters()[13];
        CounterIter7819[14] = StridedCounter7818.Iters()[14];
        CounterIter7819[15] = StridedCounter7818.Iters()[15];
        LoopController7817.EvalLaneValids();
        for (int i = 0; i < 16; i++) {
          laneValid[i] = LoopController7817.LaneValids()[i];
        }
        if (LoopController7817.Enabled()) {
          bool uen = true;
          Active();
          for (int i = 0; i < 16; i++) {
            OpDef56[i] = BufferRead7696[i] ? Const7804 : Const7801;
          }
          for (int i = 0; i < 16; i++) {
            OpDef60[i] = BufferRead7694[i] + OpDef56[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef62[i] = OpDef60[i] * OpDef60[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef65[i] = OpDef62[i] + BufferRead7698[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef66[i] = OpDef65[i] < OpDef62[i];
          }
          for (int i = 0; i < 16; i++) {
            OpDef67[i] = OpDef66[i] ? Const7803 : Const7802;
          }
        }
      }
    }
  }
  EvalControllers();
  if (LoopController7817.ChildDone() && Any<16>(BufferRead7694_en)) {
    fifo_BufferRead7694_loaded = false;
    fifo_BufferRead7694.Pop();
  }
  if (LoopController7817.ChildDone() && Any<16>(BufferRead7696_en)) {
    fifo_BufferRead7696_loaded = false;
    fifo_BufferRead7696.Pop();
  }
  if (LoopController7817.ChildDone() && Any<16>(BufferRead7698_en)) {
    fifo_BufferRead7698_loaded = false;
    fifo_BufferRead7698.Pop();
  }
  if (LoopController7817.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7703_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7703[i] = BufferWrite7703_en[i] ? OpDef60[i] : BufferWrite7703[i];
    }
  }
  if (LoopController7817.ChildDone() && Any<16>(BufferWrite7703_en)) {
    Token data = make_token(BufferWrite7703);
    set_token_en<16>(data, BufferWrite7703_en);
    data.done_vec = LoopController7817.LevelsDone()+1;
    pipe_BufferWrite7703.Push(data);
  }
  if (LoopController7817.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7709_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7709[i] = BufferWrite7709_en[i] ? OpDef65[i] : BufferWrite7709[i];
    }
  }
  if (LoopController7817.ChildDone() && Any<16>(BufferWrite7709_en)) {
    Token data = make_token(BufferWrite7709);
    set_token_en<16>(data, BufferWrite7709_en);
    data.done_vec = LoopController7817.LevelsDone()+1;
    pipe_BufferWrite7709.Push(data);
  }
  if (LoopController7817.Enabled()) {
    for (int i = 0; i < 16; i++) {
      BufferWrite7701_en[i] = true;
    }
    for (int i = 0; i < 16; i++) {
      BufferWrite7701[i] = BufferWrite7701_en[i] ? OpDef67[i] : BufferWrite7701[i];
    }
  }
  if (LoopController7817.ChildDone() && Any<16>(BufferWrite7701_en)) {
    Token data = make_token(BufferWrite7701);
    set_token_en<16>(data, BufferWrite7701_en);
    data.done_vec = LoopController7817.LevelsDone()+1;
    pipe_BufferWrite7701.Push(data);
  }
  pipe_BufferWrite7701.Eval();
  pipe_BufferWrite7703.Eval();
  pipe_BufferWrite7709.Eval();
}
