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
  def uniformCostSearch[N, A, C:Ordering,M](
    start:N, 
    isEnd:N => Boolean,
    zeroCost:C,
    sumCost:(C,C) => C,
    advance:N => Iterable[(N, A, C)], 
    quote:N => String,
    finPass:(List[(N,A)], C) => M,
    logger:Option[Logger]
  ):M = {

    case class Node(n:N, var cost:C) extends Ordered[Node] {
      override def toString = s"Node(${quote(n)}, $cost)" 
      def compare(that:Node):Int = -implicitly[Ordering[C]].compare(cost, that.cost)
    }

    val explored = mutable.ListBuffer[N]()

    val backPointers = mutable.Map[N, (N,A,C)]()

    var frontier = mutable.PriorityQueue[Node]()

    def extractResult(start:N, end:N):List[(N,A)] = {
      val history = mutable.ListBuffer[(N, A)]()
      var current = end
      while (current != start) {
        val (prevNode, action, cost) = backPointers(current)
        history += ((current, action))
        current = prevNode
      }
      return history.toList
    }

    frontier += Node(start, zeroCost)

    while (!frontier.isEmpty) {
      logger.foreach { l =>
        l.dprintln(s"frontier:")
        l.dprintln(s"- ${frontier}")
        l.dprintln("")
      }

      val Node(minNode, pastCost) = frontier.dequeue()

      logger.foreach { l =>
        l.emitBSln(s"${quote(minNode)}, pastCost:$pastCost")
      }

      if (isEnd(minNode)) {
        val route = extractResult(start, minNode).reverse
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
            //frontier += Node(start, zeroCost)
            logger.foreach { l => 
              l.dprintln(s"$e")
            }
        }
      } else {
        explored += minNode 
      }

      var neighbors = advance(minNode)

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
        frontier.filter { case Node(n,c) => n == neighbor }.headOption.fold [Unit]{
          frontier += Node(neighbor, newCost)
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
  def uniformCostSpan[N, C:Ordering](
    start:N, 
    zeroCost:C,
    sumCost:(C,C) => C,
    advance:(N,C) => Iterable[(N, C)], 
    quote:N => String,
    logger:Option[Logger]
  ):Iterable[N] = {

    case class Node(n:N, var cost:C) extends Ordered[Node] {
      override def toString = s"Node(${quote(n)}, $cost)" 
      def compare(that:Node):Int = -implicitly[Ordering[C]].compare(cost, that.cost)
    }

    val explored = mutable.ListBuffer[N]()

    val backPointers = mutable.Map[N, (N,C)]()

    var frontier = mutable.PriorityQueue[Node]()

    frontier += Node(start, zeroCost)

    while (!frontier.isEmpty) {
      logger.foreach { l =>
        l.dprintln(s"frontier:")
        l.dprintln(s"- ${frontier}")
        l.dprintln("")
      }

      val Node(minNode, pastCost) = frontier.dequeue()

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
        frontier.filter { case Node(n,c) => n == neighbor }.headOption.fold [Unit]{
          frontier += Node(neighbor, newCost)
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
    explored.toList
  }

}
