package pir
package util

import prism.util._
import spade.node._
import pir.codegen._

trait PIRDebugger extends Debugger {

  implicit def compiler:PIR

  override def action:BreakAction = openPIRDot orElse openPIRCtxDot orElse openPIRGlobalDot orElse super.action

  def openPIRDot:BreakAction = { case ("top", state, callBack) =>
    info(s"Open PIRIRDotGen")
    new PIRTopDotGen(s"top.dot").run
    callBack()
  }

  def openPIRCtxDot:BreakAction = { case ("ctx", state, callBack) =>
    info(s"Open PIRCtxDotGen")
    new PIRCtxDotGen(s"ctx.dot").run
    callBack()
  }

  def openPIRGlobalDot:BreakAction = { case ("global", state, callBack) =>
    info(s"Open PIRCtxGlobalGen")
    new PIRGlobalDotGen(s"global.dot").run
    callBack()
  }

}

