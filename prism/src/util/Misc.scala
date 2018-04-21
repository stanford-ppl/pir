package prism.util

import prism.exceptions._

trait Misc {
  def tic = {
    times.push(System.nanoTime())
  }
  def toc(unit:String):Double = {
    val startTime = times.pop()
    val endTime = System.nanoTime()
    val timeUnit = unit match {
      case "ns" => 1
      case "us" => 1000
      case "ms" => 1000000
      case "s" => 1000000000
      case _ => throw PIRException(s"Unknown time unit!")
    }
    (endTime - startTime) * 1.0 / timeUnit
  }

  def toc(info:String, unit:String):Unit = {
    val time = toc(unit)
    println(s"$info elapsed time: ${f"$time%1.3f"}$unit")
  }

  def timer[T](info:String, unit:String)(block: => T) = {
    tic
    val res = block
    toc(info, unit)
    res
  }

  def getStackTrace:String = {
    getStackTrace(0, 20)
  }
  def getStackTrace(start:Int, end:Int):String = {
    val curThread = Thread.currentThread()
    val trace = curThread.getStackTrace()
    trace.slice(start,end).map("" + _).mkString("\n")
  }

  def info(color:String, header:String, msg:Any) = {
    emitln(s"[$color$header${Console.RESET}] ${msg}")
  }
  def info(msg:String) = emitln(s"[pir] ${msg}")
  def infor(msg:String) = emit(s"[pir] ${msg}\r")
  def succeed(msg:Any) = info(Console.GREEN, "success", msg)
  def fail(msg:Any) = info(Console.RED, "fail", msg)
  def warn(msg:Any) = info(Console.YELLOW, "warn", msg)
  def warn(predicate:Boolean, s:Any):Unit = if (predicate) warn(s) 
  def err(msg:Any, exception:Boolean=true):Unit = { info(Console.RED, "error", msg); if (exception) throw PIRException(s"$msg") }

  def ask(question:String) = {
    info(question)
    scala.io.StdIn.readLine()
  }

  def toValue(s:String):AnyVal = {
    if (s.contains("true") || s.contains("false")) 
      s.toBoolean
    else if (s.contains("."))
      s.toFloat
    else s.toInt
  }

  def dbgblk[T](logger:Option[Logging], msg:Any)(f: => T):T = {
    logger.fold(f) { _.dbgblk(s"$msg") { f } }
  }
  
  def dbg(logger:Option[Logging], msg:Any) = {
    logger.foreach { _.dbg(msg) }
  }
}
