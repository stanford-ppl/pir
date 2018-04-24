package pir.pass

import pir.node._
import prism.collection.mutable

class CUInsertion2(implicit compiler:PIR) extends PIRTransformer with BFSBottomUpTopologicalTraversal with UnitTraversal {
  import pirmeta._

  val forward = false

  override def runPass =  {
    traverseNode(compiler.top)
  }

  override def visitNode(n:N):Unit = {
    insertCU(n)
    super.visitNode(n)
  }

  def insertCU(n:N) = dbgblk(s"insertCU ${qdef(n)}") {
    n match {
      case n:Top => 
      case n:GlobalContainer => 
      case n if globalOf(n).nonEmpty =>
      case n if isRemoteMem(n) =>
        val cu = CUContainer().setParent(compiler.top).name(s"${qtype(n)}")
        dbg(s"create cu $cu")
        swapParent(n, cu)
      case n:Primitive =>
        val depeds = n.depeds.filterNot { _.isInstanceOf[Memory] }
        var depedCUs = depeds.flatMap { deped =>
          globalOf(deped)
        }
        dbg(s"depedCUs=${depedCUs}")
        val topParent = (n :: n.ancestors.filterNot { p =>
          p.isInstanceOf[GlobalContainer] || p.isInstanceOf[Top]
        }).last
        if (depedCUs.isEmpty) {
          val cu = CUContainer().setParent(compiler.top)
          dbg(s"create cu $cu")
          depedCUs += cu
        }
        val cu::rest = depedCUs.toList
        swapParent(topParent, cu)
        if (rest.nonEmpty) {
          rest.foreach { cu =>
            mirror(n, Some(cu))
          }
        }
      case n =>
    }
  }

}
