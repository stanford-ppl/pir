package spade
package util

import spade.node._

trait NetworkAStarSearch extends prism.mapper.UniformCostGraphSearch[Bundle[_], (Port[_<:PinType], Port[_<:PinType]), Int] {

  val spademeta:SpadeMetadata
  import spademeta._

  type PT = Port[_<:PinType]
  type Action = (PT, PT)
  type C = Int

  val cnu:Numeric[Int] = implicitly[Numeric[Int]]

  def advance(
    startTails:List[PT],
    tailToHead:(Edge, BackPointer) => List[Edge],
    linkCost:(PT, PT) => C,
    maxCost:Int
  )(
    state:Bundle[_], 
    backPointers:BackPointer, 
    pastCost:C
  ):Seq[(Bundle[_], Action, C)] = {

    if (maxCost>0 && pastCost>maxCost) return Nil
    dbgblk(s"advance(state=${quote(state)} pastCost=$pastCost)",buffer=false) {
      routableOf(state).get match {
        case _:Terminal =>
          /*
           *   +----------+      +----------+
           *   |    tails +----->|heads     +
           *   |  curr    +----->|          |
           *   +----------+      +----------+
           * */
          startTails.flatMap { tail =>
            tailToHead(tail.external, backPointers).map { headedge =>
              val head = headedge.src.asInstanceOf[PT]
              val newState = head.src.asInstanceOf[Bundle[_<:PinType]]
              (newState, (tail, head), linkCost(tail, head))
            }
          }
        case _:SwitchBox | _:Router =>
          /*
           *   +----------+      +----------+       +----------+
           *   |        t1+----->|h1      t2+------>|h2        |
           *   |          |      |  curr    |       |          |
           *   +----------+      +----------+       +----------+
           * */
          val (_, (tail1, head1), _) = backPointers(state)
          tailToHead(head1.internal, backPointers).flatMap { tail2ic =>
            val tail2 = tail2ic.src.asInstanceOf[PT]
            tailToHead(tail2.external, backPointers).map { head2edge =>
              val head2 = head2edge.src.asInstanceOf[PT]
              val newState = head2.src.asInstanceOf[Bundle[_<:PinType]]
              (newState, (tail2, head2), linkCost(tail2, head2))
            }
          }
      }
    }
  }

  override def quote(n:Any) = n match {
    case (state, action, cost) => (quote(state), quote(action), cost).toString
    case (tail, head) => (quote(tail), quote(head)).toString
    case n:Bundle[_] => s"${quote(routableOf(n).get)}.${n}[${n.bct.runtimeClass.getSimpleName}]"
    case n => super.quote(n)
  }

}
