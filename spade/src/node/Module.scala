package spade
package node
import param._

trait Module extends SpadeNode with prism.node.SubGraph[SpadeNode] {
  implicit val module:this.type = this
}
object Module {
  def apply[M<:Module](module:M, name:String)(implicit parent:Module, design:SpadeDesign):M = {
    naming(module.setParent(parent), name)
  }
  def apply[M<:Module](module:M)(implicit parent:Module, design:SpadeDesign):M = {
    module.setParent(parent)
  }
}

object Modules {
  def apply[M<:Module](name:String, modules:List[M])(implicit parent:Module, design:SpadeDesign):List[M] = {
    modules.map(m => Module(m, name))
  }
  def apply[M<:Module](name:String,num:Int,lambda: => M)(implicit parent:Module, design:SpadeDesign):List[M] = {
    indexing(List.fill(num){ Module(lambda, name) })
  }
}
