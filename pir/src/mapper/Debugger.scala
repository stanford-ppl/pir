package pir
package mapper

import pir.codegen._
import spade.node._
import prism.mapper.SearchFailure

trait Debugger { self:PIRPass =>
  def input = {
    val answer = ask(s"Waiting for input ...")
  }

  def act[M](m:M, f:Any, answer:String):Unit = {
    answer match {
      case "o" =>
        f match {
          case SearchFailure(from:Bundle[_], to, msg) if isBit(from) =>
            new PIRNetworkDotCodegen[Bit](s"control.dot", m).run.open
          case SearchFailure(from:Bundle[_], to, msg) if isWord(from) =>
            new PIRNetworkDotCodegen[Word](s"scalar.dot", m).run.open
          case SearchFailure(from:Bundle[_], to, msg) if isVector(from) =>
            new PIRNetworkDotCodegen[Vector](s"vector.dot", m).run.open
          case _ =>
            new PIRNetworkDotCodegen[Bit](s"control.dot", m).run.open
            new PIRNetworkDotCodegen[Word](s"scalar.dot", m).run.open
            new PIRNetworkDotCodegen[Vector](s"vector.dot", m).run.open
        }
        val answer = ask(s"Waiting for input ...")
        act(m, f, answer)
      case "q" =>
        info(s"Stop debugging and exiting...")
        System.exit(-1)
      case "n" =>
      case _ =>
        val answer = ask(s"Invalid input, o-open, q-quit, n-next ...")
        act(m, f, answer)
    }
  }

  def handle(m:PIRMap, error:Any) = {
    logger.closeAllBufferAndWrite
    info(Console.RED, "break", error)
    val answer = ask(s"Waiting for input ...")
    act(m, error, answer)
  }

  def breakPoint[M](m:PIRMap)(block: => EOption[M]):EOption[M] = if (PIRConfig.enableBreakPoint) {
    Try(block) match {
      case Failure(e) => handle(m, e); throw e
      case Success(Left(f)) => handle(m, f); Left(f)
      case Success(res) => res
    }
  } else block 
}

