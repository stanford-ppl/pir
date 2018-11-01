package prism
package graph

import scala.collection.mutable

trait Field[T] extends Serializable {
  val name:String
  implicit val Ftt:TypeTag[T]
  def update(x:Any):Unit
  def fieldToNodes:Seq[Node[_]]
  def T:T
}
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
  val idx = new Metadata[Int]("idx")

  def asInput:Option[Input] = None
  def asOutput:Option[Output] = None

  def Ts = edges.map { e => e.asInstanceOf[FieldEdge[_]].T }

  def Nss = edges.map { e => e.Ns }

  trait NodeField[T] extends Field[T] {
    val name:String
    implicit val Ftt:TypeTag[T]
    def apply(xs:Any*):self.type = { xs.foreach(update); self }
    def update(x:Any):Unit
    def fieldToNodes:Seq[Node[_]]
    def T:T = {
      val nodes = fieldToNodes
      val t = typeOf[T] match {
        case tp if tp <:< typeOf[List[Node[_]]] => 
          nodes.toList
        case tp if tp <:< typeOf[Set[Node[_]]] =>
          nodes.toSet
        case tp if tp <:< typeOf[Option[Node[_]]] => 
          assertOneOrLess(nodes, s"$self.$this.T=Option[Node[_]]")
        case tp if tp <:< typeOf[Node[_]] =>
          assertOne(nodes, s"$self.$this.T=Node[_]")
      }
      t.asInstanceOf[T]
    }
  }

  /*
   * Field edge provide helper function to connection of the edge with type of T
   * and read connection of the edge with type of T
   * */
  trait FieldEdge[T] extends Edge with NodeField[T] {
    def fieldToNodes:Seq[Node[_]] = Ns
    def update(x:Any):Unit = {
      unpack(x) { 
        case x:Edge => connect(x)
        case x:FieldNode[_] => 
          this match {
            case edge:Input if x.asOutput.nonEmpty => connect(x.asOutput.get)
            case edge:Output if x.asInput.nonEmpty => connect(x.asInput.get)
            case edge => throw new Exception(s"$x cannot be converted to edges")
          }
        case x => throw new Exception(s"$x cannot be converted to edges")
      }
    }
  }
  
  class InputField[T:TypeTag](val name:String) extends Input()(self) with FieldEdge[T] {
    val Ftt = typeTag[T]
    override def toString = s"${super.toString}_$name"
  }
  
  class OutputField[T:TypeTag](val name:String) extends Output()(self) with FieldEdge[T] {
    val Ftt = typeTag[T]
    override def toString = s"${super.toString}_$name"
  }

  class ChildField[M<:FieldNode[_]:ClassTag, T:TypeTag](val name:String) extends NodeField[T] {
    val Ftt = typeTag[T]
    def update(x:Any):Unit = {
      unpack(x) {
        case x:M => 
          x.idx := children.size
          x.resetParent(self)
        case _ =>
      }
    }
    def fieldToNodes:Seq[Node[_]] = {
      children.collect { case c:M => c }.toSeq.sortBy { _.idx.get }
    }
  }
  
  //class FieldChild[T:TypeTag](implicit src:Node[_]) extends 

}
