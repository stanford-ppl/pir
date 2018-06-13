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

  override def runPass =  {
    traverseNode(compiler.top)
  }

  // Break control dependency
  override def depFunc(n:N) = n match {
    case n:ControlNode => super.depFunc(n)
    case n => super.depFunc(n).filterNot { _.isInstanceOf[ControlNode] }
  }

  override def visitNode(n:N) = {
    dbgblk(s"visitNode($n)")  {
      n match {
        case n:LocalAccess => getCountsOf(n)
        case n:Memory if !linkGroupOf.contains(n) => computeLinkGroup(n); getCountsOf(n)
        case n:NetworkNode => computeInterferenceMemory(n); getCountsOf(n)
        case n:GlobalIO => getCountsOf(n)
        case n => 
      }
    }
    super.visitNode(n)
  }

  def computeInterferenceMemory(n:NetworkNode) = dbgblk(s"computeInterferenceMemory($n)"){
    val mems = dbgblk(s"inMemsOf") { inMemsOf(n) }
    mems.foreach { mem =>
      val linkTp = pinTypeOf(mem)
      val interfered = mems.filter {
        case `mem` => false
        case other if pinTypeOf(other) == linkTp => true
        case other => false
      }
      infMemsOf(mem) = interfered.toSet
    }
  }

  def computeLinkGroup(n:Memory) = dbgblk(s"computeLinkGroup($n)"){
    val group = (writersOf(n) ++ resetersOf(n)).flatMap { access => 
      def visitFunc(n:PIRNode) = visitGlobalOut(n).filterNot {
        case n:DataReady => true // Remove back pressure
        case _ => false
      }
      val src = access match {
        case Def(n, LocalStore(mems, addr, data:GlobalInput)) => goutOf(data).get
        case Def(n, LocalStore(mems, addr, data)) => data
        case Def(n, LocalReset(mems, reset:GlobalInput)) => goutOf(reset).get
      }
      dbg(s"src=$src")
      src.collect[Memory](visitFunc=visitFunc _, logger=Some(this))
    }.toSet
    dbg(s"group=${group}")
    group.foreach { mem => linkGroupOf += mem -> group }
  }

}
