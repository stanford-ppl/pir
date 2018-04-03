package pir.mapper

import pir.node._
import spade.node._

class Router[B<:PinType:ClassTag:TypeTag](implicit pass:PIRPass) extends spade.util.NetworkAStarSearch[B](pass.compiler.arch.design) {

  //def advance(forward:Boolean, end:Option[Routable])
              //(state:Bundle[B], backPointers:BackPointer, pastCost:C):Seq[(Bundle[B], Edge, C)] = {
    //routableOf(state).get match {
      //case rt:SwitchBox =>
        /*
         *   +----------+      +----------+       +----------+
         *   |        t1+----->|h1      t2+------>|h2        |
         *   |          |      |  curr    |       |          |
         *   +----------+      +----------+       +----------+
         * */
        //val (_, (tail1, head1), _) = backPointers(state)
        //head1.internal.connected.flatMap { tail2ic =>
          //val tail2 = tail2ic.src.asInstanceOf[Port[B]]
          //tail2.connected.map { head2 =>
            //(head2.src.asInstanceOf[Bundle[B]], (tail2, head2), 1 + heuristic(head2, end))
          //}
        //}
      //case rt:Routable if !backPointers.contains(state) => // Start
        /*
         *   +----------+      +----------+
         *   |    tails +----->|heads     +
         *   |  curr    +----->|          |
         *   +----------+      +----------+
         * */
        //val tails = if (forward) state.outputs else state.inputs
        //tails.flatMap { tail =>
          //tail.connected.map { head =>
            //(head.src.asInstanceOf[Bundle[B]], (tail, head), 1 + heuristic(head, end))
          //}
        //}
      //case _ => Nil
    //}
  //}

  //def span(
    //start:GlobalIO, 
    //m:PIRMap,
    //logger:Option[Logging]
  //):Seq[(Routable,C)] = {
    //val cuP = globalOf(start)
    //val cuS = m.cumap(cuP)
    //uniformCostSpan(
      //start=bundleOf[B](cuS).get, 
      //advance=advance(forward, None)_,
      //logger=logger
    //).map { case (bundle, cost) => (routableOf(bundle).get, cost) }
  //}

}
