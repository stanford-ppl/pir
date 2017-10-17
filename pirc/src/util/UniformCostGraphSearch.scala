package pirc.util

import pirc.codegen.Logger

import scala.util.{Try, Success, Failure}
import scala.collection.mutable

trait UniformCostGraphSearch {
  /* Find the minimum path from start to end
   * Call finPass when a route is found. If finPass is succeeded, return mapping from finPass. 
   * If finPass fails, continue find routes. Throw exception when no route is found 
   * @return mapping 
   * */
  def uniformCostSearch[S, A, C:Ordering,M](
    start:S, 
    isEnd:S => Boolean,
    zeroCost:C,
    sumCost:(C,C) => C,
    advance:(S,C) => Iterable[(S, A, C)], 
    quote:S => String,
    finPass:(List[(S,A)], C) => M,
    logger:Option[Logger]
  ):M = {

    case class State(n:S, var cost:C) extends Ordered[State] {
      override def toString = s"State(${quote(n)}, $cost)" 
      def compare(that:State):Int = -implicitly[Ordering[C]].compare(cost, that.cost)
    }

    val explored = mutable.ListBuffer[S]()

    val backPointers = mutable.Map[S, (S,A,C)]()

    var frontier = mutable.PriorityQueue[State]()

    def extractResult(start:S, end:S):List[(S,A)] = {
      val history = mutable.ListBuffer[(S, A)]()
      var current = end
      while (current != start) {
        val (prevNode, action, cost) = backPointers(current)
        history += ((current, action))
        current = prevNode
      }
      return history.toList.reverse
    }

    frontier += State(start, zeroCost)

    while (!frontier.isEmpty) {
      logger.foreach { l =>
        l.dprintln(s"frontier:")
        l.dprintln(s"- ${frontier}")
        l.dprintln("")
      }

      val State(minNode, pastCost) = frontier.dequeue()

      logger.foreach { l =>
        l.emitBSln(s"${quote(minNode)}, pastCost:$pastCost")
      }

      if (isEnd(minNode)) {
        assert(explored.toSet.size == explored.size)
        val route = extractResult(start, minNode)
        Try(finPass(route, pastCost))  match {
          case Success(m) => 
            logger.foreach { l =>
              l.emitBEln
              l.dprintln("")
            }
            return m
          case Failure(e) => // Continue
            //explored.clear
            //backPointers.clear
            //frontier.clear
            //frontier += State(start, zeroCost)
            logger.foreach { l => 
              l.dprintln(s"$e")
            }
        }
      } else {
        explored += minNode 
      }

      var neighbors = advance(minNode, pastCost)

      //logger.foreach { l =>
        //l.dprintln(s"neighbors:")
        //l.dprintln(s" - ${neighbors.map { case (n, a, c) => s"(${quote(n)}, $c)" }.mkString(",")}")
      //}
      
      neighbors = neighbors.filterNot { case (n, a, c) => explored.contains(n) }

      //logger.foreach { l =>
        //l.dprintln(s"neighbors not explored:")
        //l.dprintln(s" - ${neighbors.map { case (n, a, c) => s"(${quote(n)}, $c)" }.mkString(",")}")
      //}
      
      neighbors = neighbors.groupBy { case (n, a, c) => n }.map { case (n, groups) =>
        groups.minBy { case (n, a, c) => c }
      }

      logger.foreach { l =>
        l.dprintln(s"neighbors minBy:")
        l.dprintln(s" - ${neighbors.map { case (n, a, c) => s"(${quote(n)}, $c)" }.mkString(",")}")
      }

      neighbors.foreach { case (neighbor, action, cost) =>
        val newCost = sumCost(pastCost, cost)
        frontier.filter { case State(n,c) => n == neighbor }.headOption.fold [Unit]{
          frontier += State(neighbor, newCost)
          backPointers += neighbor -> ((minNode, action, cost))
        } { node =>
          if (implicitly[Ordering[C]].lt(newCost, node.cost)) {
            node.cost = newCost
            backPointers += neighbor -> ((minNode, action, cost))
            frontier = frontier.clone() // Create a new copy to force reordering
          }
        }
      }
      logger.foreach { l =>
        l.emitBEln
        l.dprintln("")
      }

    }
    return throw new Exception(s"No route from ${quote(start)}") 
  }

  /*
   * Find list of nodes reachable from start
   * */
  def uniformCostSpan[S, C:Ordering](
    start:S, 
    zeroCost:C,
    sumCost:(C,C) => C,
    advance:(S,C) => Iterable[(S, C)], 
    quote:S => String,
    logger:Option[Logger]
  ):Iterable[(S,C)] = {

    case class State(n:S, var cost:C) extends Ordered[State] {
      override def toString = s"State(${quote(n)}, $cost)" 
      def compare(that:State):Int = -implicitly[Ordering[C]].compare(cost, that.cost)
    }

    val explored = mutable.ListBuffer[S]()

    val backPointers = mutable.Map[S, (S,C)]()

    var frontier = mutable.PriorityQueue[State]()

    def extractCost(start:S, end:S):C = {
      var totalCost = zeroCost
      var current = end
      while (current != start) {
        val (prevNode, cost) = backPointers(current)
        totalCost = sumCost(totalCost, cost)
        current = prevNode
      }
      return totalCost 
    }

    frontier += State(start, zeroCost)

    while (!frontier.isEmpty) {
      logger.foreach { l =>
        l.dprintln(s"frontier:")
        l.dprintln(s"- ${frontier}")
        l.dprintln("")
      }

      val State(minNode, pastCost) = frontier.dequeue()

      logger.foreach { l =>
        l.emitBSln(s"${quote(minNode)}, pastCost:$pastCost")
      }

      explored += minNode 

      var neighbors = advance(minNode, pastCost)

      //logger.foreach { l =>
        //l.dprintln(s"neighbors:")
        //l.dprintln(s" - ${neighbors.map { case (n, a, c) => s"(${quote(n)}, $c)" }.mkString(",")}")
      //}
      
      neighbors = neighbors.filterNot { case (n, c) => explored.contains(n) }

      //logger.foreach { l =>
        //l.dprintln(s"neighbors not explored:")
        //l.dprintln(s" - ${neighbors.map { case (n, a, c) => s"(${quote(n)}, $c)" }.mkString(",")}")
      //}
      
      neighbors = neighbors.groupBy { case (n, c) => n }.map { case (n, groups) =>
        groups.minBy { case (n, c) => c }
      }

      logger.foreach { l =>
        l.dprintln(s"neighbors minBy:")
        l.dprintln(s" - ${neighbors.map { case (n, c) => s"(${quote(n)}, $c)" }.mkString(",")}")
      }

      neighbors.foreach { case (neighbor, cost) =>
        val newCost = sumCost(pastCost, cost)
        frontier.filter { case State(n,c) => n == neighbor }.headOption.fold [Unit]{
          frontier += State(neighbor, newCost)
          backPointers += neighbor -> ((minNode, cost))
        } { node =>
          if (implicitly[Ordering[C]].lt(newCost, node.cost)) {
            node.cost = newCost
            backPointers += neighbor -> ((minNode, cost))
            frontier = frontier.clone() // Create a new copy to force reordering
          }
        }
      }

      logger.foreach { l =>
        l.emitBEln
        l.dprintln("")
      }
    }

    assert(explored.toSet.size == explored.size)
    explored.map { n => (n, extractCost(start, n)) }.toList
  }

}
