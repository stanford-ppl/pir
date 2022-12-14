#include "Context164.h"
Context164::Context164(NetworkInput& _go7899) :
Context("Context164","ArgFringe3","Context164 ContextInsertion.scala:26")
, go7899(_go7899)
  , TopController7512("TopController7512")
  , HostInController5("HostInController5")
 {
  AddChild(&TopController7512, false);
  AddCtrler(&TopController7512);
  AddChild(&HostInController5, false);
  AddCtrler(&HostInController5);
  HostInController5.AddOutput(&go7899);
}
void Context164::Clock() {
  HostInController5.Clock();
  TopController7512.Clock();
}
void Context164::Eval() {
  BufferWrite7507_en = false;
  if (TopController7512.Enabled()) {
    laneValid = true;
    if (HostInController5.Enabled()) {
      bool uen = true;
      Active();
      extern void* x145;
      DRAMAddr112 = (long) x145;
    }
  }
  EvalControllers();
  if (HostInController5.Enabled()) {
    BufferWrite7507_en = true;
    BufferWrite7507 = BufferWrite7507_en ? DRAMAddr112 : BufferWrite7507;
  }
  if (HostInController5.Done()) {
    Token data = make_token(BufferWrite7507);
    set_token_en(data, BufferWrite7507_en);
    data.done_vec = HostInController5.LevelsDone()+1;
    go7899.Push(data);
  }
}
