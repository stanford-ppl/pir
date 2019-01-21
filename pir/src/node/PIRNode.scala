package pir
package node

import prism.graph._
import prism.util._

import scala.collection.mutable

abstract class PIRNode(implicit env:BuildEnvironment) 
  extends EnvNode[PIRNode] 
  with FieldNode[PIRNode] { self =>
  lazy val Nct = classTag[PIRNode]

  val name = new Metadata[String]("name")
  val sname = new Metadata[String]("sname") {
    override def check(v:String) = {}
  }
  val srcCtx = new Metadata[String]("srcCtx") {
    override def check(v:String) = {}
  }

  val ctrl = new Metadata[ControlTree]("ctrl") {
    override def apply(value:ControlTree) = self match {
      case self:GlobalContainer => getSelf
      case self => super.apply(value)
    }
    override def reset = {
      self match {
        case _:Controller => value.foreach { v => v.ctrler.reset }
        case _ =>
      }
      super.reset
    }
  }

  // Scale is relative rate of a node active to ctx enable
  val scale = new Metadata[Value[Long]]("scale") {
    override def mirror(frommeta:MetadataLike[_]) = {}
  }
  // Count is total number of time a node is active
  val count = new Metadata[Value[Long]]("count")
  // Iter is how many iteration a node run between enabled and done. Independent of what it stacks on
  val iter = new Metadata[Value[Long]]("iter")
  val vec = new Metadata[Int]("vec")

  env.initNode(this)
}
object PIRNode extends MemoryUtil with AccessUtil {
  implicit class PIRNodeOp(n:PIRNode) extends NodeCollector[PIRNode](n){
    def ctx = n.collectUp[Context]().headOption
    def global = n.collectUp[GlobalContainer]().headOption
    def isUnder[T:ClassTag] = n.ancestors.exists { _.to[T].nonEmpty }
  }
}

case class ControlTree(schedule:String)(implicit env:Env) extends EnvNode[ControlTree] with FieldNode[ControlTree] with Ordered[ControlTree] { self =>
  lazy val Nct = classTag[ControlTree]

  val ctrler = new Metadata[Controller]("ctrler")
  val par = new Metadata[Int]("par")
  val srcCtx = new Metadata[String]("srcCtx")

  def compare(that:ControlTree) = {
    if (this == that) 0
    else if (this.isAncestorOf(that)) 1
    else if (that.isAncestorOf(this)) -1
    else throw PIRException(s"Cannot compare $this with $that")
  }

  def isLeaf = children.isEmpty

  env.initNode(this)
}
object ControlTree {
  implicit class ControlTreeOp(n:ControlTree) extends NodeCollector[ControlTree](n)
}

