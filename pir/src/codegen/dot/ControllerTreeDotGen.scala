package pir
package codegen

import pir.node._
import prism.codegen._

class ControlTreeDotGen(val fileName:String)(implicit compiler:PIR) extends ControlTreeTraversal with IRDotCodegen {

  override def quote(n:Any) = super.quote(n)
    .foldAt(n.to[ControlTree]) { (q,n) => 
      s"$q\n${n.schedule}\npar=${n.par.v.fold("") { s => s.toString }}\n${n.srcCtx.v.fold(""){ s => s }}"
    }

}
