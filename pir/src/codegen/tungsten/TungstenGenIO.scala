package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenIOGen extends TungstenCodegen with TungstenCtxGen {

  override def emitNode(n:N) = n match {
    case n:GlobalOutput =>
      val (tp, name) = varOf(n)
      val args = if (noPlaceAndRoute) Seq(name.qstr) else Seq(name.qstr, "net".&, "statnet".&)
      genTopMember(n, args)
      if (noPlaceAndRoute) {
        val bcArgs = n.out.T.map { out => varOf(out)._2.& }
        genTopMember("Broadcast<Token>", s"bc_$n", Seq(s"bc_$n".qstr, name.&, s"{${bcArgs.mkString(",")}}"), extern=false, end=true, escape=false)
      }
      
    case n:GlobalInput =>
      val (tp, name) = varOf(n)
      val args = if (noPlaceAndRoute) Seq(name.qstr) else Seq(name.qstr, "net".&, "statnet".&)
      genTopMember(n, args)
      val bcArgs = n.out.T.map { out => varOf(out)._2.& }
      genTopMember("Broadcast<Token>", s"bc_$n", Seq(s"bc_$n".qstr, name.&, s"{${bcArgs.mkString(",")}}"), extern=n.isExtern.get, end=true, escape=false)

    case n => super.emitNode(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:GlobalOutput if noPlaceAndRoute =>
      (s"FIFO<Token,2>", s"$n")
    case n:GlobalInput if noPlaceAndRoute =>
      (s"FIFO<Token,2>", s"$n")
    case n:GlobalOutput =>
      (s"NetworkInput", s"$n")
    case n:GlobalInput =>
      (s"NetworkOutput", s"$n")
    case n => super.varOf(n)
  }

}

