package pir.mapper

import pir.codegen._
import spade.node._

trait Debugger { self:PIRPass =>
  def breakPoint[M](m:M)(e: => EOption[M]):EOption[M] = if (debug) {
    e.left.map { f =>
      bp(s"$f")
      val answer = ask(s"Waiting for input ...")
      val open = answer match {
        case "o" => true
        case "q" =>
          info(s"Stop debugging routing and exiting...")
          System.exit(-1)
          false
        case _ => false
      }
      if (open) {
        new PIRNetworkDotCodegen[Bit](s"control.dot", m).run.open
        new PIRNetworkDotCodegen[Word](s"scalar.dot", m).run.open
        new PIRNetworkDotCodegen[Vector](s"vector.dot", m).run.open
      }
      f
    }
  } else e 

}

