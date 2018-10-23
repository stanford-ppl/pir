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
trait FieldNode[N] extends Node[N] { self:N =>
  implicit val n:Node[_] = this

  def asInput:Option[Input] = None
  def asOutput:Option[Output] = None

  def Ts = edges.map { e => e.asInstanceOf[FieldEdge[_]].T }

  def Nss = edges.map { e => e.Ns }

  /*
   * Field edge provide helper function to connection of the edge with type of T
   * and read connection of the edge with type of T
   * */
  trait FieldEdge[T] extends Edge {
    implicit val Ftt:TypeTag[T]
    def apply(x:Any):self.type = {
      unpack(x) { 
        case x:FieldEdge[T] => connect(x)
        case x:FieldNode[_] => 
          this match {
            case edge:Input if x.asOutput.nonEmpty => connect(x.asOutput.get)
            case edge:Output if x.asInput.nonEmpty => connect(x.asInput.get)
            case edge => throw new Exception(s"$x cannot be converted to edges")
          }
        case x => throw new Exception(s"$x cannot be converted to edges")
      }
      self
    }
    def T:T = {
      val nodes = Ns
      val t = typeOf[T] match {
        case tp if tp <:< typeOf[List[Node[_]]] => 
          nodes.toList
        case tp if tp <:< typeOf[Set[Node[_]]] =>
          nodes.toSet
        case tp if tp <:< typeOf[Option[Node[_]]] => 
          assertOneOrLess(nodes, s"$this.T=Option[Node[_]]")
        case tp if tp <:< typeOf[Node[_]] =>
          assertOne(nodes, s"$this.T=Node[_]")
      }
      t.asInstanceOf[T]
    }
  }
  
  class FieldInput[T:TypeTag] extends Input()(self) with FieldEdge[T] {
    val Ftt = typeTag[T]
  }
  
  class FieldOutput[T:TypeTag] extends Output()(self) with FieldEdge[T] {
    val Ftt = typeTag[T]
  }
  
  //class FieldChild[T:TypeTag](implicit src:Node[_]) extends 

}
