#include "Context165.h"
Context165::Context165(FIFO<Token, 1>& _fifo_TokenRead7510) :
Context("Context165","ArgFringe3","Context165 ContextInsertion.scala:26")
, fifo_TokenRead7510(_fifo_TokenRead7510)
  , TopController7513("TopController7513")
  , HostOutController147("HostOutController147")
 {
  AddChild(&TopController7513, false);
  AddCtrler(&TopController7513);
  HostOutController147.AddInput(&fifo_TokenRead7510, false);
  // Initialize TokenRead7510
  TokenRead7510 = false;
  AddChild(&HostOutController147, false);
  AddCtrler(&HostOutController147);
  Expect(1);
}
void Context165::Clock() {
  HostOutController147.Clock();
  TopController7513.Clock();
}
void Context165::Eval() {
  TokenRead7510_en = false;
  if (TopController7513.Enabled()) {
    TokenRead7510_en = true;
    if (!fifo_TokenRead7510_loaded && fifo_TokenRead7510.Valid()) {
      fifo_TokenRead7510_loaded = true;
      TokenRead7510 = toT<bool>(fifo_TokenRead7510.Read(), 0);
    }
    laneValid = true;
    if (HostOutController147.Enabled()) {
      bool uen = true;
      Active();
      Complete(1);
    }
  }
  EvalControllers();
  if (HostOutController147.Done()) {
    fifo_TokenRead7510_loaded = false;
    fifo_TokenRead7510.Pop();
  }
}
