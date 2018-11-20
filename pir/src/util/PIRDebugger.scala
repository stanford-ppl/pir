package pir
package util

import prism.util._
import spade.node._
import pir.codegen._

trait PIRDebugger extends Debugger {

  implicit def compiler:PIR

  override def action:BreakAction = openPIRDot orElse openPIRCtxDot orElse super.action

  def openPIRDot:BreakAction = { case ("top", state, callBack) =>
    info(s"Open PIRIRDotGen")
    new PIRIRDotGen(s"top_bp.dot").run.open
    callBack()
  }

  def openPIRCtxDot:BreakAction = { case ("ctx", state, callBack) =>
    info(s"Open PIRCtxDotGen")
    new PIRCtxDotGen(s"top_ctx.dot").run.open
    callBack()
  }

  ///* Snapshot */
  //import pir.codegen._

  //override def initPass = {
    //super.initPass
    //snapshotCount = 0
  //}

  //var snapshotCount = 0
  //lazy val snapshotInterval = PIRConfig.option[Int]("snapint")
  //def snapshot[M](m:M):M = {
    //if (PIRConfig.enableSnapshot) {
      //if (snapshotCount % snapshotInterval == 0 && snapshotCount != 0) {
        //val idx = snapshotCount / snapshotInterval
        //new PIRNetworkDotCodegen[Bit](s"control$idx.dot", m, false).run
        //new PIRNetworkDotCodegen[Word](s"scalar$idx.dot", m, false).run
        //new PIRNetworkDotCodegen[Vector](s"vector$idx.dot", m, false).run
      //}
      //snapshotCount += 1
    //}
    //m
  //}

  ///* Place And Route Break Point */
  //import pir.codegen._
  //import prism.mapper.SearchFailure

  //def breakPoint[M](m:PIRMap)(block: => EOption[M]):EOption[M] = if (PIRConfig.enablePlaceAndRouteBreakPoint) {
    //Try(block) match {
      //case Failure(InvalidMapping(m, e)) => 
        //breakPoint(s"$e", (m, e))
        //throw e
      //case Failure(e) => 
        //breakPoint(s"$e", (m, e))
        //throw e
      //case Success(Left(f)) => 
        //breakPoint(s"$f", (m, f))
        //Left(f)
      //case Success(res) => res
    //}
  //} else block 

  //def openNetworkDot(m:Any, f:Any):BreakAction = {
    //case ("on", (m, f), callBack) =>
      //f match {
        //case SearchFailure(from:Bundle[_], to, msg) if isBit(from) =>
          //new PIRNetworkDotCodegen[Bit](s"control.dot", m).run.open
        //case SearchFailure(from:Bundle[_], to, msg) if isWord(from) =>
          //new PIRNetworkDotCodegen[Word](s"scalar.dot", m).run.open
        //case SearchFailure(from:Bundle[_], to, msg) if isVector(from) =>
          //new PIRNetworkDotCodegen[Vector](s"vector.dot", m).run.open
        //case _ =>
          //new PIRNetworkDotCodegen[Bit](s"control.dot", m).run.open
          //new PIRNetworkDotCodegen[Word](s"scalar.dot", m).run.open
          //new PIRNetworkDotCodegen[Vector](s"vector.dot", m).run.open
      //}
      //callBack(())
  //}

}

