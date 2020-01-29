package pir
package codegen

import pir.node._
import prism.codegen._

class ControlTreeDotGen(val fileName:String)(implicit compiler:PIR) extends ControlTreeTraversal with IRDotCodegen {

  override def quote(n:Any) = super.quote(n)
    .foldAt(n.as[ControlTree].sname.v) { (q, v) => s"$q[$v]" }
    .foldAt(n.to[ControlTree]) { (q,n) => 
      s"$q\n${n.schedule}" + 
      n.par.v.fold("") { s => s"\npar=$s" } +
      n.isLoop.v.fold("") { s => s"\nisLoop=$s" } +
      n.srcCtx.v.fold(""){ s => s"\n$s" } +
      n.uid.v.fold("") { uid => s"\nuid=[${uid.mkString(",")}]"}
    }

}
