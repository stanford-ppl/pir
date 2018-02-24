
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

  override def toString = s"${className}$id"
}

abstract class Node[N<:Node[N]:ClassTag](implicit design:Design) extends IR with Product { self:N =>

  type P <: SubGraph[N] with N
  type A <: Atom[N] with N
  val nct = implicitly[ClassTag[N]]

  override def productName = s"$productPrefix$id(${values.mkString(",")})" 

  lazy val arity = self.productArity
  lazy val fieldNames = self.getClass.getDeclaredFields.filterNot(_.isSynthetic).map(_.getName).toList //TODO

  // Parent
  var _parent:Option[P] = None
  def parent:Option[P] = _parent
  def setParent(p:P):this.type =  {
    assert(p != this)
    _parent match {
      case Some(`p`) => this
      case Some(parent) => err(s"Resetting parent of $this from $parent to $p"); this
      case None =>
        _parent = Some(p)
        p.addChild(this)
        this
    }
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

  def siblings:List[N] = parent.map { _.children.filterNot { _ == this} }.getOrElse(Nil)
  def ancestors:List[P] = parent.toList.flatMap { parent => parent :: parent.ancestors.asInstanceOf[List[P]] }
  def isAncestorOf(n:N) = n.ancestors.contains(this) 
  def descendents:List[N]
  def isDescendentOf(p:P) = p.descendents.contains(this)

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
  def neighbors = deps ++ depeds

  def connectFields(x:Any, i:Int)(implicit design:Design):Any = {
    x match {
      case Some(x) => Some(connectFields(x, i))
      case x:Iterable[_] => x.map(xx => connectFields(xx, i)) 
      case x:Iterator[_] => x.map(xx => connectFields(xx, i)) 
      case x => x
    }
  }

  def evaluateFields(f:Any, x:Any):Any = {
    (f, x) match {
      case (Some(f), Some(x:Edge[_])) if !x.isConnected => None
      case (Some(f), Some(x)) => Some(evaluateFields(f, x))
      case (f:Iterable[_], x:Iterable[_]) => 
        f.zip(x).flatMap { 
          case (f, x:Edge[_]) if !x.isConnected => None
          case (f,x) => Some(evaluateFields(f,x))
        }
      case (f:Iterable[_], x:Iterator[_]) => 
        f.zip(x.toList).flatMap { 
          case (f, x:Edge[_]) if !x.isConnected => None
          case (f,x) => Some(evaluateFields(f,x))
        }
      case (f, x) => x
    }
  }

  def staging(implicit design:Design):List[Any] = {
    if (design.staging) 
      productIterator.toList.zipWithIndex.map{ case (field, i) => connectFields(field, i)}
    else Nil
  }

  val stagedFields = staging
  def values = productIterator.toList.zip(stagedFields).map { case (field, staged) => evaluateFields(field, staged) }

  def newInstance[T](args:List[Any], staging:Boolean=true)(implicit design:Design):T = {
    val constructor = this.getClass.getConstructors()(0) 
    val arguments = args :+ design
    val prevStaging = design.staging
    design.staging = staging
    val newNode = try {
      constructor.newInstance(arguments.map(_.asInstanceOf[Object]):_*).asInstanceOf[T]
    } catch {
      case e:java.lang.IllegalArgumentException =>
        errmsg(s"Error during newInstance of node $this")
        errmsg(s"Expected type: ${constructor.getParameterTypes().mkString(",")}")
        errmsg(s"Got type: ${arguments.map(_.getClass).mkString(",")}")
        throw e
      case e:Throwable => throw e
    }
    design.staging = prevStaging
    newNode
  }
}

trait Atom[N<:Node[N]] extends Node[N] { self:N =>
  implicit lazy val atom:this.type = this

  def children:List[N] = Nil
  def descendents:List[N] = Nil

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

trait SubGraph[N<:Node[N]] extends Node[N] with Memorization { self:N with SubGraph[N] =>
  // Children
  private lazy val _children = mutable.ListBuffer[N]()
  def children = _children.toList

  def descendents:List[N] = children.flatMap { child => child :: child.descendents }

  def atoms = descendents.collect { case atom:Atom[N] => atom }

  def addChild(c:N):Unit = { 
    assert(c != this)
    if (_children.contains(c)) return
    _children += c
    c.setParent(this.asInstanceOf[c.P])
  }

  def removeChild(c:N):Unit = {
    if (!_children.contains(c)) return
    _children -= c
    c.unsetParent
  }

  def ins = atoms.flatMap { _.ins.filter { _.connected.exists{ !_.src.ancestors.contains(this) } } }
  def outs = atoms.flatMap { _.outs.filter { _.connected.exists{ !_.src.ancestors.contains(this) } } }
  override def deps:Set[A] = descendents.flatMap{ _.deps.filterNot(descendents.contains).asInstanceOf[Set[A]] }.toSet
  override def depeds:Set[A] = descendents.flatMap{ _.depeds.filterNot(descendents.contains).asInstanceOf[Set[A]] }.toSet
}

abstract class Edge[N<:Node[N]:ClassTag]()(implicit design:Design) extends IR {
  type A <: Atom[N] with N

  def src:A

  type E<:Edge[N]
  protected val _connected = mutable.ListBuffer[E]()
  def connected:List[E] = _connected.toList
  def singleConnected:Option[E] = {
    assert(connected.size <= 1, s"${this.src}.$this has more than 1 connection. connected to ${connected.map( c => s"${c.src}.$c")}")
    connected.headOption
  }
  def isConnected:Boolean = connected.nonEmpty
  def isConnectedTo(p:Edge[N]) = connected.contains(p.asInstanceOf[E])
  def connect(e:Edge[N]):this.type = {
    val p = e.asInstanceOf[E] // This cast actually does nothing at runtime
    if (isConnectedTo(p)) return this
    _connected += p 
    p.connect(this.asInstanceOf[p.E])
    this
  }

  def disconnectFrom(io:Edge[N]):Unit = {
    assert(this.isConnectedTo(io), s"$this is not connected to $io. this.connected=$connected")
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

trait Memorization {
  //val memorizing = true
  val memorizing = false
  private val caches = mutable.ListBuffer[Cache[_,_]]()
  case class Cache[I:ClassTag,O](lambda:I => O) {
    caches += this
    val memory = mutable.Map[I,O]()
    def memorize(input:I) = if (memorizing) memory.getOrElseUpdate(input, lambda(input)) else lambda(input)
    def resetCache(input:Any) = input match { case input:I => memory -= input; case _ => }
    def resetAll = memory.clear
  }
  def resetCache(input:Any) = caches.foreach(_.resetCache(input)) 
  def resetAllCaches = caches.foreach(_.resetAll)
}

import prism.collection.mutable._
import scala.util.{Try, Success, Failure}
trait Metadata extends Serializable {

  lazy val maps = getDeclaredObjects(this).collect { case o:MetadataMap => o }

  def reset = maps.foreach(_.clear)

  def summerize(n:Any, maps:MetadataMap*):List[String] = { maps.flatMap { map => map.info(n) }.toList }

  def summary(n:Any):List[String] = summerize(n, maps.toSeq:_*)

  def mirrorOnly(orig:Any, clone:Any, includes:MetadataMap*) = {
    if (orig != clone) includes.foreach { map => 
      (map.asK(orig), map.asK(clone)) match {
        case (Some(orig), Some(clone)) => map.mirror(orig, clone)
        case _ =>
      }
    }
  }

  def mirror(orig:Any, clone:Any) = mirrorOnly(orig, clone, maps.toSeq:_*)

  def mirrorExcept(orig:Any, clone:Any, excludes:MetadataMap*) = {
    val includes = (maps.toList diff excludes.toList)
    mirrorOnly(orig, clone, includes:_*)
  }

  def removeAll(node:Any) = maps.foreach { map => map.removeAll(node) }

  trait MetadataMap { 
    type K
    type V
    type VV
    def asK(k:Any):Option[K]
    def asV(v:Any):Option[V]
    def asVV(vv:Any):Option[VV]
  
    def name:String
    def clear:Unit
    def get(k:K):Option[VV]
    def contains(k:K):Boolean
    def removeAll(a:Any):Unit
    def update(k:K,vv:VV):Unit
  
    def isDefinedAt(k:K) = contains(k)
    // Default just copy over
    def mirror(orig:K, clone:K):Unit = {
      get(orig).foreach { vv => update(clone, vv) }
    }
    def info(a:Any):Option[String] = { 
      asK(a) match {
        case Some(k) => get(k).map { vv => s"${name}($k)=$vv" }
        case None =>
          this match {
            case map:BiMap[_,_,_,_] => 
              map.asV(a).flatMap { v => 
                map.bmap.get(v).map( kk => s"${name}.bmap($v) = $kk" )
              }
            case _ => None
          }
      }
    }
  }
}


