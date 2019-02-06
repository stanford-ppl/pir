package prism
package graph

import scala.collection.mutable

trait Node[N<:Node[N]] extends IR { self:N =>

  type TN = N

  /*  ------- State -------- */
  implicit def Nct:ClassTag[N]
  private var _parent:Option[N] = None
  private lazy val _children = ListBuffer[N]()
  val localEdges = mutable.ListBuffer[EN[N]]()

  /*  ------- Metadata -------- */
  val pos = Metadata[(Double,Double)]("pos")

  /*  ------- functions -------- */
  // Parent
  def parent:Option[N] = _parent
  def setParent(p:Node[N]):this.type =  {
    assert(p != this, s"setting parent of $this to be the same as it self $p!")
    _parent match {
      case Some(`p`) => this
      case Some(parent) => err(s"Resetting parent of $this from $parent to $p"); this
      case None =>
        p match {
          case p:N =>
            assert(!this.isAncestorOf(p), s"Setting descendent $p as parent of $this") 
            _parent = Some(p)
            p.addChild(this)
          case _ => 
        }
        this
    }
  }
  def unsetParent:self.type = {
    parent.foreach { p =>
      _parent = None
      p.removeChild(this)
    }
    this
  }
  def resetParent(p:Node[N]):this.type = { unsetParent; setParent(p) }
  def isParentOf(m:Node[N]) = m.parent == Some(this)

  // Children
  def children:List[N] = _children.toList
  def addChild(cs:Node[N]*):this.type = { 
    cs.foreach { c =>
      assert(c != this, s"Cannot add self as a children node=$this")
      c match {
        case c:N => 
          if (_children.contains(c)) return this
          _children += c
          c.setParent(this)
        case c => 
          throw new Exception(s"Cannot add $c as child of node $this with type $Nct")
      }
    }
    this
  }

  def removeChild(c:Node[N]):Unit = {
    if (!_children.contains(c)) return
    _children -= c.as[N]
    c.unsetParent
  }
  def isChildOf(p:Node[N]) = p.children.contains(this)

  def siblings:List[N] = parent.map { _.children.filterNot { _ == this} }.getOrElse(Nil)
  def ancestors:Stream[N] = {
    parent.toStream.flatMap { parent => parent +: parent.ancestors }
  }
  def ancestorTree:Stream[N] = this.as[N] +: ancestors
  def isAncestorOf(n:Node[N]):Boolean = n.isDescendentOf(this)
  // Inclusive
  def ancestorSlice(top:Node[N]) = { // from this to top inclusive
    val chain = ancestorTree
    val idx = chain.indexOf(top)
    assert(idx != -1, s"$top is not ancestor of the $this")
    chain.slice(0, idx+1)
  }
  def descendents:Stream[N] = children.toStream.flatMap { child => child +: child.descendents }
  def descendentTree:Stream[N] = this.as[N] +: descendents
  def isDescendentOf(n:Node[N]):Boolean = parent.fold(false) { p => p == n || p.isDescendentOf(n) }

  def addEdge(io:EN[N]) = {
    localEdges += io
  }
  def removeEdge(io:EN[N]) = localEdges -= io
  def localIns:Vector[Input[N]] = localEdges.toVector.collect { case i:Input[N] => i }
  def localOuts:Vector[Output[N]] = localEdges.toVector.collect { case i:Output[N] => i }

  def edges = localEdges ++ descendents.flatMap { _.localEdges }
  def ins = edges.collect { case i:Input[N] => i }
  def outs = edges.collect { case i:Output[N] => i }
  def localDeps:Vector[N] = { 
    localIns.flatMap { _.connected.map { _.src} }.distinct
  }

  def localDepeds:Vector[N] = {
    localOuts.flatMap { _.connected.map { _.src} }.distinct
  }

  def matchLevel(n:Node[N]):Option[N] = n.ancestorTree.filter { _.parent == this.parent }.headOption

  /*
   * A map of external dependent outputs and internal inputs that depends on the external 
   * dependencies
   * */
  def depsFrom:Map[Output[N], Vector[Input[N]]] = {
    val descendents = this.descendents
    val descendentSet = descendents.toSet
    val ins = localIns.toIterator ++ descendents.toIterator.flatMap { _.localIns }
    ins.flatMap { in =>
      in.connected.filterNot { out => descendentSet.contains(out.src) } 
      .map { out => (out, in) } 
    }.toVector.groupBy { _._1 }.mapValues { _.map { _._2 } }
  }

  def deps:Vector[N] = depsFrom.keys.flatMap(_.src.descendentTree).toVector.distinct

  /*
   * A map of internal outs to seq of external inputs
   * */
  def depedsTo:Map[Output[N], Vector[Input[N]]] = {
    val descendents = this.descendents
    val descendentSet = descendents.toSet
    val outs = localOuts.toIterator ++ descendents.toIterator.flatMap { _.localOuts }
    outs.flatMap { out =>
      out.connected.filterNot { in => descendentSet.contains(in.src) } 
      .map { in => (in, out) } 
    }.toVector.groupBy { _._2 }.mapValues { _.map { _._1 } }
  }

  def depeds:Vector[N] = depedsTo.values.flatten.flatMap { _.src.descendentTree}.toVector.distinct

  def siblingDeps:Vector[N] = deps.flatMap(matchLevel)
  def globalDeps:Vector[N] = deps.filter { d => matchLevel(d).isEmpty }
  def siblingDepeds:Vector[N] = depeds.flatMap(matchLevel)
  def globalDepeds:Vector[N] = depeds.filter { d => matchLevel(d).isEmpty }
  def neighbors:Vector[N] = deps ++ depeds

}
object Node {
  implicit def node2n[N<:Node[N]](node:Node[N]):N = node.as[N]
}
