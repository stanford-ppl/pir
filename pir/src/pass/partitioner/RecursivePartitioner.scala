package pir
package pass

import pir.node._
import pir.mapper._
import pir.codegen._

trait RecursivePartitioner extends GlobalPartioner { self =>
  import pirmeta._
  import PIRConfig._

  override def split(cu:GlobalContainer, fit:CUMap.K => Boolean) = 
  if (option[String]("splitting-algo") == "recursive"){
    schedular.resetTraversal
    schedular.setSplitTarget(cu)
    schedular.setFit(fit)
    schedular.traverseScope(cu.children, Set.empty)
  } else super.split(cu, fit)

  private val schedular = new PIRTraversal with prism.traversal.BFSTopologicalTraversal {
    type T = Set[GlobalContainer]
    def zero = Set.empty
    override lazy val logger = self.logger
    val forward = false
    private var _splitTarget:Option[GlobalContainer] = None
    def setSplitTarget(cu:GlobalContainer) = _splitTarget = Some(cu)
    def splitTarget = _splitTarget.get
    private var _fit:Option[CUMap.K => Boolean] = None
    def fit = _fit.get
    def setFit(fit:CUMap.K => Boolean) = _fit = Some(fit)
    override def resetTraversal = {
      super.resetTraversal
      _splitTarget = None
      _fit = None
    }
    override def visitIn(n:N):List[N] = visitLocalIn(n)
    override def visitOut(n:N):List[N] = visitLocalOut(n)
    override def isDepFree(n:N) = depFunc(n).exists(isVisited)
    override def visitNode(n:N, prev:T):T = breakPoint(prev, s"Splitting $splitTarget - fit $n") {
      val newParent = dbgblk(s"visitNode($n)") {
        val depeds = n.depeds

        val depedCU = prev.filter { cu =>
          n.depeds.exists { _.isDescendentOf(cu) }
        }
        dbg(s"depedCU = ${depedCU}")

        val candidates = if (depedCU.isEmpty) prev else depedCU

        val newParent = candidates.foldLeft[Option[GlobalContainer]](None) {
          case (None, cu) =>
            swapParent(n, cu)
            val isFit = fit(cu) 
            dbg(s"fit($cu) = $isFit")
            if (isFit) { Some(cu) } else {
              swapParent(n, splitTarget)
              None
            }
          case (Some(cu), _) => Some(cu)
        }
        
        newParent.getOrElse {
          withParent(compiler.top) { 
            val newParent = CUContainer()
            pirmeta.mirror(splitTarget, newParent)
            swapParent(n, newParent) // should alwasy fit
            newParent
          }
        }
      }
      super.visitNode(n, prev + newParent)
    }
  }

}
