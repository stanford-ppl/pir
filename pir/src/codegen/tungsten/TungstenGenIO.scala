package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenIOGen extends TungstenCodegen with TungstenCtxGen with TungstenTopGen {

  override def emitNode(n:N) = n match {
    case n:GlobalOutput =>
      val (tp, name) = varOf(n)
      genTopMember(n, Seq(name.qstr, "net".&, "statnet".&, "idealnet".&))
      
    case n:GlobalInput =>
      val (tp, name) = varOf(n)
      genTopMember(n, Seq(name.qstr, "net".&, "statnet".&, "idealnet".&))
      val bcArgs = n.out.connected.map { in => in.qref.& }
      genTopMember("Broadcast<Token>", s"bc_$n", Seq(s"bc_$n".qstr, name.&, s"{${bcArgs.mkString(",")}}"), extern=n.isExtern.get, end=true, escape=false)

    case n => super.emitNode(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:GlobalOutput =>
      (s"NetworkInput", quote(n))
    case n:GlobalInput =>
      (s"NetworkOutput", quote(n))
    case n => super.varOf(n)
  }

}

