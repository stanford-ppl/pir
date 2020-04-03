package pir
package node

import prism.graph._
import prism.util._

import scala.collection.mutable

abstract class PIRNode(implicit env:BuildEnvironment) 
  extends EnvNode[PIRNode] 
  with FieldNode[PIRNode] { self =>
  lazy val Nct = classTag[PIRNode]

  val name = new Metadata[String]("name") {
    override def mirror(frommeta:MetadataLike[_]) = {
      if (v.isEmpty) super.mirror(frommeta)
      else self
    }
  }
  val sname = new Metadata[String]("sname") {
    override def check(v:String) = {}
  }
  val srcCtx = new Metadata[String]("srcCtx") {
    override def :=(v:String) = { 
      value = Some(value.map { _ + "\n" + v }.getOrElse(v))
    }
  }
  def setSrcCtx(implicit file:sourcecode.File, line: sourcecode.Line):self.type = srcCtx(getSrcCtx)
  def setSrcCtx(name:String)(implicit file:sourcecode.File, line: sourcecode.Line):self.type = {
      srcCtx(getSrcCtx).name(name)
  }

  val ctrl = new Metadata[ControlTree]("ctrl") {
    override def apply(value:ControlTree, reset:Boolean=false) = self match {
      case self:GlobalContainer => getSelf
      case self => super.apply(value, reset)
    }
    override def reset = {
      self match {
        case _:Controller => value.foreach { v => v.ctrler.reset }
        case _ =>
      }
      super.reset
    }
  }
  val progorder = new Metadata[Int]("progorder") {
    override def mirror(frommeta:MetadataLike[_]):self.type = { 
      if (value.isEmpty) {
        frommeta.value.foreach { v => update(v) }
      }
      self
    }
  }
  val tp = new Metadata[BitType]("tp")

  // Scale is relative rate of a node active to ctx enable
  val scale = new Metadata[Value[Long]]("scale") {
    override def mirror(frommeta:MetadataLike[_]) = self
  }
  // Count is total number of time a node is active
  val count = new Metadata[Value[Long]]("count") {
    override def check(v:Value[Long]) = {
      (value, v) match {
        case (Some(Infinite), Unknown) => // Unknown is more specific (data-dependent) than Infinite
        case (Some(Infinite), Finite(s)) => // Finite is more specific (data-dependent) than Infinite
        case _ => super.check(v)
      }
    }
  }
  // Iter is how many iteration a node run between enabled and done. Independent of what it stacks on
  val iter = new Metadata[Value[Long]]("iter")
  val vec = new Metadata[Int]("vec") {
    override def mirror(frommeta:MetadataLike[_]) = self
  }
  // TODO: this should be removed after adding mirrorRule
  val presetVec = new Metadata[Int]("presetVec")

  // Marker for whether the operation is reduction operation across lane
  val isInnerReduceOp = new Metadata[Boolean]("isInnerReduceOp", default=Some(false))

  // Is external node when modularize app
  val isExtern = new Metadata[Boolean]("isExtern", default=Some(false))
  val externAlias = new Metadata[String]("externAlias")

  val waitFors = new Metadata[List[Int]]("waitFors")
  val barrier = new Metadata[Int]("barrier")

  def compType(n:IR):Option[BitType] = n match {
    case n:Input[_] if n.isConnected && n.connected.size == 1 =>
      n.singleConnected.get.inferTp
    case n => None
  }
  def compVec(n:IR):Option[Int] = n match {
    case n:Input[_] if n.isConnected && n.connected.size == 1 =>
      n.singleConnected.get.inferVec
    case n => None
  }

  val delay = new Metadata[Int]("delay")

  env.initNode(this)
}
object PIRNode extends MemoryUtil with AccessUtil {
  implicit class PIRNodeOp(n:PIRNode) {
    def getCtrl:ControlTree = n.ctrl.get
    def ctx = n.collectUp[Context]().headOption
    def global = n.collectUp[GlobalContainer]().headOption
    def isUnder[T:ClassTag] = n.ancestors.exists { _.to[T].nonEmpty }
  }

}

sealed abstract class CtrlSchedule
case object Sequenced extends CtrlSchedule
case object Pipelined extends CtrlSchedule
case object Streaming extends CtrlSchedule
case object ForkJoin extends CtrlSchedule
case object Fork extends CtrlSchedule
case class ControlTree(schedule:CtrlSchedule)(implicit env:Env) extends EnvNode[ControlTree] with FieldNode[ControlTree] with Ordered[ControlTree] { self =>
  lazy val Nct = classTag[ControlTree]

  val sname = new Metadata[String]("sname")
  val ctrler = new Metadata[Controller]("ctrler")
  val par = new Metadata[Int]("par", default=Some(1))
  val iter = new Metadata[Value[Long]]("iter")
  val isLoop = new Metadata[Boolean]("isLoop", default=Some(false))
  val isForever = new Metadata[Boolean]("isForever", default=Some(false))
  val srcCtx = new Metadata[String]("srcCtx") {
    override def :=(v:String) = { 
      value = Some(value.map { _ + "\n" + v }.getOrElse(v))
    }
  }
  val uid = new Metadata[List[Int]]("uid")
  val progorder = new Metadata[Int]("progorder")

  def compare(that:ControlTree) = {
    if (this == that) 0
    else if (this.isAncestorOf(that)) 1
    else if (that.isAncestorOf(this)) -1
    else bug(s"Cannot compare $this with $that")
  }

  def isAsync = schedule == Streaming && isForever.get

  env.initNode(this)
}

