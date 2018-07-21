package pir
package pass

import pir.node._

import scala.collection.mutable

trait AccessControlUtil extends PIRNodeUtil with Logging with prism.traversal.GraphUtil {
  implicit val compiler:PIR
  val pirmeta:PIRMetadata
  import pirmeta._

  private var currMem:Option[Memory] = None
  private def mem = currMem.get

  // TODO memorize
  def getTopCtrl(n:LocalAccess):Controller = {
    dbgblk(s"getTopCtrl($n)"){
      val mem::Nil = memsOf(n)
      val memCtrl = ctrlOf(mem)
      val ctrl = ctrlOf(n)
      var ancestors = ctrl::ctrl.ancestors

      val ctx = contextOf(n).get
      var foundOtherAccess = false
      var next:Controller = ancestors.head
      do {
        next = ancestors.head
        ancestors = ancestors.tail
        val nextAccesses = accessesOf(next).filter { a => memsOf(a).contains(mem) }
        dbg(s"$next accesses=$nextAccesses")
        foundOtherAccess = nextAccesses.exists { a => 
          contextOf(a).get != ctx && leastCommonAncesstor(ctrlOf(a), ctrl).get.style != ForkJoin
        }
      } while (next != memCtrl && !foundOtherAccess)
      if (foundOtherAccess) {
        getTopCtrl(ctrl, next)
      } else {
        throw PIRException(s"Didn't found visiable access relative to ${quote(n)} for $mem")
      }
    }
  }

  def getTopCtrl(n:Controller, lca:Controller):Controller = {
    if (n == lca) n else {
      val ancestors = n :: n.ancestors
      val idx = ancestors.indexOf(lca)
      ancestors(idx-1)
    }
  }

}

