package pir.mapper

import pir.codegen._
import spade.node._

trait Debugger { self:PIRPass =>
  def input = {
    val answer = ask(s"Waiting for input ...")
  }

  def act[M](m:M, answer:String):Unit = {
    answer match {
      case "o" =>
        new PIRNetworkDotCodegen[Bit](s"control.dot", m).run.open
        new PIRNetworkDotCodegen[Word](s"scalar.dot", m).run.open
        new PIRNetworkDotCodegen[Vector](s"vector.dot", m).run.open
        val answer = ask(s"Waiting for input ...")
        act(m, answer)
      case "q" =>
        info(s"Stop debugging routing and exiting...")
        System.exit(-1)
      case "n" =>
      case _ =>
        val answer = ask(s"Invalid input, o-open, q-quit, n-next ...")
        act(m, answer)
    }
  }

  def breakPoint[M](m:M)(e: => EOption[M]):EOption[M] = if (PIRConfig.breakPoint) {
    e.left.map { f =>
      bp(s"$f")
      val answer = ask(s"Waiting for input ...")
      act(m, answer)
      f
    }
  } else e 
}

