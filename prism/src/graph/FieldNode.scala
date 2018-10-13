package prism
package graph

import scala.collection.mutable

/*
 * With field node the edges are declared with FieldEdge
 * The number of edge must be declared in the IR statically. 
 * This allow the fields to match up after mirroring and deserialization.
 * FieldNode is usually used for graph with mutation and each edge
 * has semantic meaning.
 * The constructor argument should not containing connection to other
 * nodes
 * */
abstract class FieldNode[N:ClassTag](implicit design:Design) extends Node[N] { self:N =>
  val Nct = classTag[N]
  implicit val n:Node[_] = this

  def asInput:Option[Input] = None
  def asOutput:Option[Output] = None

  def Ts = edges.map { e => e.asInstanceOf[FieldEdge[_]].T }

  def Nss = edges.map { e => e.Ns }
}

/*
 * Field edge provide helper function to connection of the edge with type of T
 * and read connection of the edge with type of T
 * */
trait FieldEdge[T] extends Edge {
  implicit val Fct:ClassTag[T]
  def apply(x:Any) = {
    unpack(x) { 
      case x:FieldNode[_] => 
        this match {
          case edge:Input if x.asOutput.nonEmpty => connect(x.asOutput.get)
          case edge:Output if x.asInput.nonEmpty => connect(x.asInput.get)
          case x => throw new Exception(s"$x cannot be converted to edges")
        }
      case x => throw new Exception(s"$x cannot be converted to edges")
    }
  }
  def T:T = {
    val nodes = Ns
    val t = Fct match {
      case ct if ct == classTag[List[Node[_]]] => 
        nodes.toList
      case ct if ct == classTag[Set[Node[_]]] =>
        nodes.toSet
      case ct if ct == classTag[Option[Node[_]]] => 
        assertOneOrLess(nodes, s"$this.T=Option[Node[_]]")
      case ct if ct == classTag[Node[_]] =>
        assertOne(nodes, s"$this.T=Node[_]")
    }
    t.asInstanceOf[T]
  }
}

class FieldInput[T:ClassTag](implicit src:Node[_], design:Design) extends Input with FieldEdge[T] {
  val Fct = classTag[T]
}

class FieldOutput[T:ClassTag](implicit src:Node[_], design:Design) extends Output with FieldEdge[T] {
  val Fct = classTag[T]
}
