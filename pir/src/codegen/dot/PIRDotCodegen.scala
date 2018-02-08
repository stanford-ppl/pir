package pir.codegen

import pir._
import pir.util.typealias._
import prism.codegen._
import spade._
import pirc._

trait PIRDotCodegen extends PIRCodegen with DotCodegen {
  override def quote(n:Any) = {
    implicit val arch:Spade = design.arch
    n match {
      case pin:PI[_] =>
        pin.src match {
          case cu:PCU => s"""${pin.src}:${pin}:n"""
          case sb:PSB => s"""${pin.src}"""
          case _ => pin.src.toString
        }
      case pout:PO[_] =>
        pout.src match {
          case cu:PCU => s"""${pout.src}:${pout}:s"""
          case sb:PSB => s"""${pout.src}"""
          case _ => pout.src.toString
        }
      case n:PNode => spade.util.quote(n)
      case _ => s"$n"
    } 
  }
}

