package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

class TungstenPIRGen(implicit design:PIR) extends TungstenCodegen with TungstenTopGen with TungstenCtxGen with TungstenOpGen

trait TungstenCodegen extends PIRTraversal with DFSTopDownTopologicalTraversal with CppCodegen {
  override def dirName = buildPath(super.dirName, s"../tungsten") 
  val forward = true
  val fileName = "Top.h"

  override def quote(n:Any) = n match {
    case n:Iterable[_] => 
      s"{${n.map(quote).mkString(",")}}"
    case n => super.quote(n)
  }

}
