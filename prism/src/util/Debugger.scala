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

}

