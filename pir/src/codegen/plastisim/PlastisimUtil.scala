package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.util._

trait PlastisimUtil extends PIRPass {
  val infCount = 1000000

  implicit class PIRNodePsimOp(n:PIRNode) {
    def constScale:Long = n.scale.get match {
      case Unknown => throw PIRException(s"${n}.scale not statically known")
      case Finite(c) => c
      case Infinite => infCount
    }
    def constCount:Long = n.count.get match {
      case Unknown => throw PIRException(s"${n}.count not statically known")
      case Finite(c) => c
      case Infinite => infCount
    }
  }
}

trait PlastisimCodegen extends PlastisimUtil with Codegen with PIRTraversal with ChildFirstTraversal {
  override def dirName = config.psimOut
}
