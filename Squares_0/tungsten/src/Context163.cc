#include "Context163.h"
Context163::Context163(FIFO<Token, 64>& _fifo_BufferRead7506,NetworkInput& _go8036) :
Context("Context163","ComputeContainer7893","Context163 Squares.scala:73:52\nContextInsertion.scala:26")
, fifo_BufferRead7506(_fifo_BufferRead7506)
, go8036(_go8036)
  , TopController7565("TopController7565")
  , UnitController7564("UnitController7564")
  , LoopController7567("LoopController7567")
  , StridedCounter7571("StridedCounter7571")
  , UnitController7566("UnitController7566")
  , UnitController144("UnitController144")
 {
  AddChild(&TopController7565, false);
  AddCtrler(&TopController7565);
  AddChild(&UnitController7564, false);
  AddCtrler(&UnitController7564);
  AddChild(&LoopController7567, false);
  AddCtrler(&LoopController7567);
  LoopController7567.AddCounter(&StridedCounter7571);
  AddChild(&StridedCounter7571, false);
  AddChild(&UnitController7566, false);
  AddCtrler(&UnitController7566);
  UnitController144.AddInput(&fifo_BufferRead7506, false);
  // Initialize BufferRead7506
  BufferRead7506 = false;
  AddChild(&UnitController144, false);
  AddCtrler(&UnitController144);
  UnitController7564.AddOutput(&go8036);
}
void Context163::Clock() {
  LoopController7567.Clock();
  StridedCounter7571.Clock();
  TopController7565.Clock();
  UnitController144.Clock();
  UnitController7564.Clock();
  UnitController7566.Clock();
}
void Context163::Eval() {
  BufferRead7506_en = false;
  TokenWrite7509_en = false;
  if (TopController7565.Enabled()) {
    if (UnitController7564.Enabled()) {
      StridedCounter7571.setMin(Const7569);
      StridedCounter7571.setStep(Const7568);
      StridedCounter7571.setMax(Const7570);
      StridedCounter7571.Eval(); // ControlTree12
      if (LoopController7567.Enabled()) {
        if (UnitController7566.Enabled()) {
          BufferRead7506_en = true;
          fifo_BufferRead7506.SetReadEn(BufferRead7506_en);
          if (!fifo_BufferRead7506_loaded && BufferRead7506_en && fifo_BufferRead7506.Valid()) {
            fifo_BufferRead7506_loaded = true;
            BufferRead7506 = toT<bool>(fifo_BufferRead7506.Read(), 0);
          }
          laneValid = true;
          if (UnitController144.Enabled()) {
            bool uen = true;
            Active();
            AccumAck148 = true;
          }
        }
      }
    }
  }
  EvalControllers();
  if (UnitController144.ChildDone() && BufferRead7506_en) {
    fifo_BufferRead7506_loaded = false;
    fifo_BufferRead7506.Pop();
  }
  if (UnitController144.Enabled()) {
    TokenWrite7509_en = true;
    TokenWrite7509 = true;
  }
  if (UnitController7564.Done()) {
    Token data = make_token(TokenWrite7509);
    set_token_en(data, TokenWrite7509_en);
    data.done_vec = UnitController144.LevelsDone()+1;
    go8036.Push(data);
  }
}
