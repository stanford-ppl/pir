package pir
package mapper

import pir.codegen._
import spade.node._
import prism.mapper.SearchFailure

trait Debugger extends PIRPass {

  override def initPass = {
    super.initPass
    snapshotCount = 0
  }

  var snapshotCount = 0
  lazy val snapshotInterval = PIRConfig.option[Int]("snapint")
  def snapshot[M](m:M):M = {
    if (PIRConfig.enableSnapshot) {
      if (snapshotCount % snapshotInterval == 0 && snapshotCount != 0) {
        val idx = snapshotCount / snapshotInterval
        new PIRNetworkDotCodegen[Bit](s"control$idx.dot", m, false).run
        new PIRNetworkDotCodegen[Word](s"scalar$idx.dot", m, false).run
        new PIRNetworkDotCodegen[Vector](s"vector$idx.dot", m, false).run
      }
      snapshotCount += 1
    }
    m
  }

  type BreakAction = PartialFunction[(String, Unit => Unit), Unit]

  val quit:BreakAction = {
    case ("q", bp) => 
      info(s"Stop debugging and exiting...")
      System.exit(-1)
  }

  val continue:BreakAction = {
    case ("n", bp) =>
  }

  val invalidInput:BreakAction = {
    case (_, bp) => 
      info(s"Invalid input, o-open, q-quit, n-next ...")
      bp(())
  }

  def openDot(m:Any, f:Any):BreakAction = {
    case ("o", bp) =>
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
      bp(())
  }

  def ask(question:String) = {
    info(Console.RED, "break", question)
    scala.io.StdIn.readLine()
  }

  def breakPoint(msg:String, act:BreakAction):Unit = {
    logger.closeAllBuffersAndWrite
    info(Console.RED, "break", msg)
    val answer = ask(s"Waiting for input ...")

    val actWithDefault:BreakAction = act orElse continue orElse quit orElse invalidInput
    actWithDefault((answer, { case unit:Unit => breakPoint(msg, actWithDefault) }))
  }

  def breakPoint[M](m:PIRMap)(block: => EOption[M]):EOption[M] = if (PIRConfig.enablePlaceAndRouteBreakPoint) {
    Try(block) match {
      case Failure(InvalidMapping(m, e)) => 
        breakPoint(s"$e", openDot(m, e))
        throw e
      case Failure(e) => 
        breakPoint(s"$e", openDot(m, e))
        throw e
      case Success(Left(f)) => 
        breakPoint(s"$f", openDot(m, f))
        Left(f)
      case Success(res) => res
    }
  } else block 


}

