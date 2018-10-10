package spade
package node
import param._

trait SpadeCollector extends prism.traversal.GraphCollector[SpadeNode] { self:SpadeNode =>

  override def collectDown[M<:SpadeNode:ClassTag:TypeTag](depth:Int= -1, logger:Option[Logging]=None):List[M] = {
    // Performance optimization
    typeOf[M] match {
      case tag if tag <:< typeOf[Routable] => // Single layer of hiearchy
        def visitDown(n:N):List[N] = n match { case n:Routable => Nil; case n => n.children }
        super.collect[M](visitDown _, depth, logger)
      case tag if tag <:< typeOf[Pin[_]] =>  // Shouldn't get submodule's Pin
        def visitDown(n:N):List[N] = n.children.filter { case n:M => true; case n:Bundle[_] => true; case _ => false }
        super.collect[M](visitDown _, depth, logger)
      case tag if tag <:< typeOf[Bundle[_]] =>  // Shouldn't get submodule's Bundle
        def visitDown(n:N):List[N] = n.children.filter { case n:M => true; case _ => false }
        super.collect[M](visitDown _, depth, logger)
      case tag if tag <:< typeOf[Module] => // No need to go inside the bundles
        def visitDown(n:N):List[N] = n.children.filter { case _:Pin[_] => false; case _:Bundle[_] => false; case _ => true }
        super.collect[M](visitDown _, depth, logger)
      case _ => super.collectDown[M](depth, logger)
    }
  }

}

