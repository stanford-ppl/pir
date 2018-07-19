package pir
package pass

import pir.node._

class PlastisimLinkAnalyzer(implicit compiler: PIR) extends PIRTraversal with DFSBottomUpTopologicalTraversal with UnitTraversal with PlastisimUtil {
  import pirmeta._

  val forward = true

  override def initPass = {
    super.initPass
    linkGroupOf.clear
  }

  // Break control dependency
  override def depFunc(n:N) = n match {
    case n:ControlNode => super.depFunc(n)
    case n => super.depFunc(n).filterNot { _.isInstanceOf[ControlNode] }
  }

  override def visitNode(n:N) = {
    n match {
      case n:LocalAccess => getScaleOf(n); getCountsOf(n)
      case n:Memory => 
        if (!linkGroupOf.contains(n)) computeLinkGroup(n)
        if (inAccessesOf(n).size <= 1) getCountsOf(n)
      case n:NetworkNode => getCountsOf(n)
      case n:GlobalIO => getCountsOf(n)
      case n => 
    }
    super.visitNode(n)
  }

  def computeLinkGroup(n:Memory) = dbgblk(s"computeLinkGroup($n)"){
    val group = inAccessesOf(n).flatMap { case Def(n, LocalInAccess(mems, addr, src::Nil)) => 
      def visitFunc(n:PIRNode) = visitGlobalOut(n).filterNot {
        case n:DataReady => true // Remove back pressure
        case _ => false
      }
      val origSrc = src match {
        case Def(gin, GlobalInput(gout)) => gout
        case src => src
      }
      dbg(s"origSrc=$origSrc")
      origSrc.collect[Memory](visitFunc=visitFunc _)
    }.toSet
    dbg(s"group=${group}")
    group.foreach { mem => linkGroupOf += mem -> group }
  }

}
