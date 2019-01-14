package prism
package graph

import scala.collection.mutable

trait Field[T] extends Serializable {
  def name:String
  implicit val Ttt:TypeTag[T]
  def update(x:Any):Unit
  def fieldToNodes:Seq[Node[_]]
  def T:T
  def node:Node[_]
}
object Field {
  def unapply(x:Any):Option[(Node[_], String)] = x match {
    case x:Field[_] => Some((x.node, x.name))
    case _ => None
  }
}
object InputField {
  def unapply(x:Any):Option[(Node[_], String)] = x match {
    case x:Field[_] with Input => Some((x.node, x.name))
    case _ => None
  }
}
object OutputField {
  def unapply(x:Any):Option[(Node[_], String)] = x match {
    case x:Field[_] with Output => Some((x.node, x.name))
    case _ => None
  }
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
  val idx = Metadata[Int]("idx")

  def asInput:Option[Input] = None
  def asOutput:Option[Output] = None

  def Ts = edges.map { e => e.asInstanceOf[FieldEdge[_]].T }

  trait NodeField[T] extends Field[T] {
    val name:String
    implicit val Ttt:TypeTag[T]
    implicit val Tct:ClassTag[T]
    def apply(xs:Any*):self.type = { xs.foreach(update); self }
    def update(x:Any):Unit
    def fieldToNodes:Seq[Node[_]]
    def T:T = {
      val nodes = fieldToNodes
      val t = typeOf[T] match {
        case tp if tp <:< typeOf[List[Node[_]]] => 
          nodes.toList
        case tp if tp <:< typeOf[Set[_]] =>
          nodes.toSet
        case tp if tp <:< typeOf[Option[Node[_]]] => 
          assertOneOrLess(nodes, s"$self.$this.T=Option[Node[_]]")
        case tp if tp <:< typeOf[Node[_]] =>
          assertOne(nodes, s"$self.$this.T=Node[_]")
      }
      t.to[T].getOrElse {
        throw PIRException(s"$self.$this=$t cannot be evaluated to ${Tct}")
      }
    }
    def evalTo(n:Any) = fieldToNodes.contains(n)
    def evalTo(n:List[Any]) = n.forall { n => fieldToNodes.contains(n) }
    def node:Node[_] = n
  }

  /*
   * Field edge provide helper function to connection of the edge with type of T
   * and read connection of the edge with type of T
   * */
  trait FieldEdge[T] extends Edge with NodeField[T] {
    def fieldToNodes:Seq[Node[_]] = neighbors
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
  
  class InputField[T:TypeTag:ClassTag](val name:String) extends Input()(self) with FieldEdge[T] {
    val Ttt = typeTag[T]
    val Tct = classTag[T]
    override def toString = s"${super.toString}_$name"
  }
  
  class OutputField[T:TypeTag:ClassTag](val name:String) extends Output()(self) with FieldEdge[T] {
    val Ttt = typeTag[T]
    val Tct = classTag[T]
    override def toString = s"${super.toString}_$name"
  }

  class ChildField[M<:FieldNode[_]:ClassTag, T:TypeTag:ClassTag](val name:String) extends NodeField[T] {
    val Ttt = typeTag[T]
    val Tct = classTag[T]
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
  
}
