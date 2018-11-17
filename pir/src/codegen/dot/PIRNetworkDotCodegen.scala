package pir
package codegen

import pir.node._
import pir.mapper._
import prism.codegen._
import spade.codegen._

class PIRNetworkDotGen(val fileName:String)(implicit design:PIR) extends PIRPass with NetworkDotGen {

  lazy val top = spadeTop

  override def quote(n:Any) = {
    super.quote(n).foldAt(n.to[CUMap.V]) { (q, n) =>
      topMap.right.toOption
        .flatMap { _.cumap.usedMap.bmap.get(n) }
        .fold(q){ cuP => s"$q\n${cuP}" }
    }
  }

  override def color(attr:DotAttr, n:N) = n match {
    case n:CUMap.V =>
      topMap.right.toOption
        .flatMap { _.cumap.usedMap.bmap.get(n) }
        .fold(attr){ cuP => super.color(attr, n) }
    case n => attr
  }
  

}
