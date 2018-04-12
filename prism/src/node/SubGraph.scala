package prism.node

trait SubGraph[N<:Node[N]] extends Node[N] { self:SubGraph[N] with N =>
  // Children
  private lazy val _children = ListBuffer[N]()
  def children = _children.toList

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

