
package prism.node

import pirc._
import pirc.util._

import scala.reflect._
import scala.reflect.runtime.universe._
import scala.collection.mutable

@SerialVersionUID(123L)
abstract class IR(implicit design:Design) extends Serializable { 
  val id = design.nextId

  override def equals(that: Any) = that match {
    case n: IR => super.equals(that) && id == n.id
    case _ => super.equals(that)
  }

  def className = this.getClass.getSimpleName
  def productName = s"${this.getClass.getSimpleName}$id"

  override def toString = s"${this.getClass.getSimpleName}$id"
}

abstract class Node[N<:Node[N]:ClassTag](implicit design:Design) extends IR with Product { self:N =>

  type P <: SubGraph[N] with N
  type A <: Atom[N] with N
  val nct = implicitly[ClassTag[N]]

  override def productName = s"$productPrefix$id(${productIterator.mkString(",")})" 

  lazy val arity = self.productArity
  lazy val fieldNames = self.getClass.getDeclaredFields.filterNot(_.isSynthetic).map(_.getName).toList //TODO

  // Parent
  var _parent:Option[P] = None
  def parent:Option[P] = _parent
  def setParent(p:P):this.type =  {
    assert(p != this)
    _parent.foreach { parent =>
      assert(p == parent, s"Resetting parent of $this from $parent to $p")
    }
    if (p.isParentOf(this)) return this
    _parent = Some(p)
    p.addChild(this)
    this
  }
  def unsetParent = {
    parent.foreach { p =>
      _parent = None
      p.removeChild(this)
    }
  }
  def isParentOf(m:N) = m.parent == Some(this)

  // Children
  def children:List[N]
  def isChildOf(p:N) = p.children.contains(this)

  def ancestors:List[P] = parent.toList.flatMap { parent => parent :: parent.ancestors.asInstanceOf[List[P]] }
  def descendents:List[N] = children.flatMap { child => 
    child :: child.descendents
  }

  def ins:List[Input[N]]
  def outs:List[Output[N]]
  def ios:List[Edge[N]] = ins ++ outs

  def matchLevel(n:N) = (n :: n.ancestors).filter { _.parent == this.parent }.headOption.asInstanceOf[Option[N]] // why is this necessary
  def deps:Set[A] = ins.flatMap { _.connected.map { _.src.asInstanceOf[A] } }.toSet
  def localDeps = deps.flatMap(matchLevel)
  def globalDeps = deps.filter { d => matchLevel(d).isEmpty }
  def depeds:Set[A] = outs.flatMap { _.connected.map { _.src.asInstanceOf[A] } }.toSet
  def localDepeds = depeds.flatMap(matchLevel)
  def globalDepeds = depeds.filter { d => matchLevel(d).isEmpty }

  def connectFields[X](x:X)(implicit design:Design):Lambda[X] = {
    x match {
      case x:Some[_] => 
        val lambdas = x.map(connectFields)
        Lambda(lambdas.map(_.n()).asInstanceOf[X])
      case x:Iterable[_] => 
        val lambdas = x.map(connectFields)
        Lambda(lambdas.map(_.n()).asInstanceOf[X])
      case x:Iterator[_] => 
        val lambdas = x.map(connectFields)
        Lambda(lambdas.map(_.n()).asInstanceOf[X])
      case x => Lambda(x)
    }
  }

  def staging(implicit design:Design):List[Lambda[Any]] = {
    if (design.staging) productIterator.map(connectFields).toList else Nil
  }

  val lambdas = staging
  def values = lambdas.map { _.n() }

  def newInstance[T](args:List[Any], staging:Boolean=true)(implicit design:Design):T = {
    //TODO: n.getClass.getConstructor(values.map{_.getClass}:_*).newInstance(values.map{
    // Some how this compiles but gives runtime error for not able to find the constructor when values contain Int type since
    // field.getClass returns java.lang.Integer type but getConstructor expects typeOf[Int]
    val constructor = this.getClass.getConstructors()(0) 
    val arguments = args :+ design
    val prevStaging = design.staging
    design.staging = staging
    val newNode = constructor.newInstance(arguments.map(_.asInstanceOf[Object]):_*).asInstanceOf[T]
    design.staging = prevStaging
    newNode
  }
}

object Def {
  def unapply(n:Node[_])(implicit design:Design):Option[Node[_]] = {
    n match {
      case n:Node[_] => Some(n.newInstance(n.values, staging=false))
      case _ => None
    }
  }
}

class Lambda[N](val n:() => N)(implicit design:Design) extends IR {
  def map[T](f:N => T)(implicit design:Design):Lambda[T] = Lambda(f(n()))
}
object Lambda {
  def apply[N](n: => N)(implicit design:Design) = new Lambda(() => n)
}

trait Atom[N<:Node[N]] extends Node[N] { self:N =>
  implicit lazy val atom:this.type = this

  def children:List[N] = Nil

  private lazy val _ins = mutable.ListBuffer[Input[N]]()
  private lazy val _outs = mutable.ListBuffer[Output[N]]()

  def addEdge(io:Edge[N]) = {
    io match {
      case io:Input[N] => _ins += io
      case io:Output[N] => _outs += io
    }
  }
  def removeEdge(io:Edge[N]) = {
    io match {
      case io:Input[N] => _ins -= io
      case io:Output[N] => _outs -= io
    }
  }
  def ins = _ins.toList
  def outs = _outs.toList
}

trait SubGraph[N<:Node[N]] extends Node[N] { self:N with SubGraph[N] =>
  // Children
  private lazy val _children = mutable.ListBuffer[N]()
  def children = _children.toList
  def addChild(c:N):Unit = { 
    assert(c != this)
    if (c.isChildOf(this)) return
    _children += c
    c.setParent(this.asInstanceOf[c.P])
  }
  def removeChild(c:N):Unit = {
    if (!isParentOf(c)) return
    _children -= c
    c.unsetParent
  }

  def ins = descendents.flatMap { _.ins.filter { _.connected.exists{ !_.src.ancestors.contains(this) } } }
  def outs = descendents.flatMap { _.outs.filter { _.connected.exists{ !_.src.ancestors.contains(this) } } }

  override def connectFields[X](x:X)(implicit design:Design):Lambda[X] = {
    implicit val ev = nct
    x match {
      case x:N =>
        this.addChild(x)
        Lambda(x.asInstanceOf[X])
      case x => super.connectFields(x)
    }
  }

}
abstract class Edge[N<:Node[N]:ClassTag]()(implicit design:Design) extends IR {
  type A <: Atom[N] with N

  def src:A

  type E<:Edge[N]
  protected val _connected = mutable.ListBuffer[E]()
  def connected:List[E] = _connected.toList
  def singleConnected:E = {
    assert(connected.size==1, s"$this doesn't have exactly 1 connection connected to ${connected}")
    connected.head
  }
  def isConnected:Boolean = connected.nonEmpty
  def isConnectedTo(p:Edge[N]) = connected.contains(p.asInstanceOf[E])
  def connect(p:E):this.type = {
    if (isConnectedTo(p)) return this
    _connected += p 
    p.connect(this.asInstanceOf[p.E])
    this
  }

  def disconnectFrom(io:Edge[N]):Unit = {
    _connected -= io.asInstanceOf[E]
    if (io.isConnectedTo(this)) io.disconnectFrom(this)
  }
  def disconnect = connected.foreach(disconnectFrom)

  src.addEdge(this)
}

trait Input[N<:Node[N]] extends Edge[N] {
  type E <: Output[N]
}

trait Output[N<:Node[N]] extends Edge[N] {
  type E <: Input[N]
}


