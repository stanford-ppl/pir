package prism
package util

trait Debugger extends Logging {

  type BreakAction = PartialFunction[(String, Any, () => Unit), Unit]

  val quit:BreakAction = {
    case ("q", state, callBack) => 
      info(s"Stop debugging and exiting...")
      System.exit(0)
  }

  val continue:BreakAction = {
    case ("n", state, callBack) =>
  }

  val invalidInput:BreakAction = {
    case (input, state, callBack) => 
      info(s"Invalid input or state input=$input state=$state ...")
      callBack()
  }

  def ask(question:String) = {
    info(Console.RED, "break", question)
    scala.io.StdIn.readLine()
  }

  def action:BreakAction = continue orElse quit orElse invalidInput

  def breakPoint(msg:String, state:Any=None):Unit = {
    logger.closeAllBuffersAndWrite
    info(Console.RED, "break", msg)
    val answer = ask(s"Waiting for input ...")

    def callBack = breakPoint(msg, state)
    action((answer, state, callBack _))
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

}

