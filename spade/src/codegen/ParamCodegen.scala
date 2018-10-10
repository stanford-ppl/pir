package spade
package codegen

import spade.param._
import prism.traversal._

abstract class ParamCodegen(implicit compiler:Spade) extends Pass with BFSTopologicalTraversal with Codegen {
  type N = Parameter

  override def runPass = {
    val allNodes = new BFSTopologicalTraversal with GraphSchedular {
      type N = Parameter
      val forward = false
    }.scheduleNode(compiler.designParam).toSet.toList
    traverseNodes(scheduleDepFree(allNodes), ())
  }
  
}
