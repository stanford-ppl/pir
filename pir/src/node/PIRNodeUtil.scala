package pir
package node

trait PIRNodeUtil extends ContainerUtil with MemoryUtil with DramFringeUtil with StreamFringeUtil
  with GlobalIOUtil with DefUtil with AccessUtil with ComputeNodeUtil with ArgFringeUtil 
  with ControllerUtil with Logging {
  val pirmeta:PIRMetadata

  def within[T<:PIRNode:ClassTag](n:PIRNode) = {
    n.ancestors.collect { case cu:T => cu }.nonEmpty
  }

  def globalOf(n:PIRNode) = {
    n match {
      case n:GlobalContainer => Some(n)
      case n => n.collectUp[GlobalContainer]().headOption
    }
  }

  def enableOf(n:PIRNode):Option[Primitive] = {
    n match {
      case n:LocalAccess => Some(accessNextOf(n))
      case n:Counter => n.getEnable
      case Def(n, DataValid(gin)) => enableOf(gin)
      case n:ControlNode => 
        assert(n.deps.size==1, s"$n has more than 1 dep")
        Some(n.deps.head)
      case n:GlobalInput => goutOf(n).flatMap(enableOf) 
      case n:GlobalOutput => Some(validOf(n))
    }
  }

  override def quote(n:Any) = n match {
    case n:IR => n.qtype(pirmeta)
    case n:Iterable[_] => n.map(quote).toString
    case n:Option[_] => n.map(quote).toString
    case n => super.quote(n)
  }

}

