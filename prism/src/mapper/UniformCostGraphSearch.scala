package prism.mapper

import scala.collection.mutable

trait UniformCostGraphSearch[N,A,C] extends Logging {

  def routingVerbosity:Int

  def dbg(verbosity:Int, msg:Any):Unit = dbg(debug && routingVerbosity >= verbosity, msg)
  def dbgblk[T](verbosity:Int, msg:Any)(block: => T):T = dbgblk(debug && routingVerbosity >= verbosity, msg.toString)(block)

  // (N, A, C): (Node, Action, Cost)
  type BackPointer = mutable.Map[N, (N,A,C)]
  type Explored = mutable.ListBuffer[N]
  type Route = List[A]

  implicit val cnu:Numeric[C]

  case class State(n:N, var cost:C) extends Ordered[State] {
    override def toString = s"State(${quote(n)}, $cost)" 
    def compare(that:State):Int = -cnu.compare(cost, that.cost)
  }

  /* Find the minimum path from start to end
   * @return mapping 
   * */
  def uniformCostSearch(
    start:N, 
    end:N,
    advance:(N, BackPointer, C) => Seq[(N,A,C)]
  ):EOption[Route] = {
    val backPointers = uniformCostTraverse(
      start=start,
      end=Some(end),
      advance=advance
    )
    if (backPointers.contains(end)) {
      val (route, cost) = extractHistory(start, end, backPointers)
      Right(route)
    } else {
      Left(SearchFailure(s"No route from ${quote(start)} to ${quote(end)}"))
    }
  }

  /*
   * Find list of nodes reachable from start
   * */
  def uniformCostSpan(
    start:N, 
    advance:(N, BackPointer, C) => Seq[(N,A,C)]
  ):Seq[(N,C)] = {
    val backPointers = uniformCostTraverse(
      start=start,
      end=None,
      advance=advance
    )
    backPointers.keys.toList.map { n => (n, extractHistory(start, n, backPointers)._2) }
  }

  def uniformCostTraverse(
    start:N, 
    end:Option[N],
    advance:(N, BackPointer, C) => Seq[(N,A,C)]
  ):BackPointer = {

    val explored:Explored = ListBuffer[N]()
    val backPointers:BackPointer = mutable.Map[N, (N,A,C)]()

    var frontier = mutable.PriorityQueue[State]()

    frontier += State(start, cnu.zero)

    while (!frontier.isEmpty) {
      dbg(3, s"frontier: ${frontier}")

      val State(minNode, pastCost) = frontier.dequeue()
      explored += minNode

      var neighbors:Seq[(N, A, C)] = advance(minNode, backPointers, pastCost)

      neighbors = neighbors.groupBy { case (n, a, c) => n }.map { case (n, groups) =>
        groups.minBy { case (n, a, c) => c }
      }.toSeq

      neighbors = neighbors.filterNot { case (n, a, c) => explored.contains(n) }

      dbg(3, s"neighbors minBy:")
      dbg(3, s" - ${neighbors.map { case (n, a, c) => s"(${quote(n)}, $c)" }.mkString(",")}")

      neighbors.foreach { case (neighbor, action, cost) =>
        val newCost = cnu.plus(pastCost, cost)
        frontier.filter { case State(n,c) => n == neighbor }.headOption.fold [Unit] {
          frontier += State(neighbor, newCost)
          backPointers += neighbor -> ((minNode, action, cost))
          if (end == Some(neighbor)) return backPointers
        } { node =>
          if (cnu.lt(newCost, node.cost)) {
            node.cost = newCost
            backPointers += neighbor -> ((minNode, action, cost))
            frontier = frontier.clone() // Create a new copy to force reordering
          }
        }
      }
    }

    backPointers
  }

  def extractHistory(
    start:N, 
    end:N, 
    backPointers:BackPointer, 
  ):(Route, C) = {
    var totalCost = cnu.zero
    val history = mutable.ListBuffer[A]()
    var current = end
    while (current != start) {
      val (prevNode, action, cost) = backPointers(current)
      totalCost = cnu.plus(totalCost, cost)
      history += action
      current = prevNode
    }
    return (history.toList.reverse, totalCost)
  }

}
