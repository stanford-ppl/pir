package prism
package util

import sys.process._
import prism.exceptions._

trait Misc {
  def tic(msg:String):Unit = {
    infor(msg)
    tic
  }
  def tic:Unit = {
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

  def cstr(color:String, msg:String) = { s"$color$msg${Console.RESET}" }
  def info(color:String, header:String, msg:Any) = {
    emitln(s"[${cstr(color, header)}] ${msg}")
  }
  def info(msg:String) = emitln(s"[pir] ${msg}")
  def infor(msg:String) = emit(s"[pir] ${msg}\r")
  def warn(msg:Any) = info(Console.YELLOW, "warn", msg)
  def warn(predicate:Boolean, s:Any):Unit = if (predicate) warn(s) 
  def err[T](msg:Any, exception:Boolean=true):T = { info(Console.RED, "error", msg); if (exception) throw CompileError(s"$msg") else ().as[T] }
  def bug[T](msg:Any, exception:Boolean=true):T = { info(Console.RED, "bug", msg); if (exception) throw PIRException(s"$msg") else ().as[T] }
  def todo[T](msg:Any, exception:Boolean=true):T = { info(Console.RED, "error", "TODO: " + msg); if (exception) throw TODOException(s"$msg") else ().as[T] }
 
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

  def shell(command:String):Int = shell(command=command, header=None)

  def shell(
    header:String, 
    command:String, 
    cwd:String,
    logPath:String
  ):Int = shell(
    command=command, 
    header=Some(header), 
    cwd=Some(cwd),
    logPath=Some(logPath),
  )
  def shell(
    header:String, 
    command:String, 
    logPath:String
  ):Int = shell(
    command=command, 
    header=Some(header), 
    logPath=Some(logPath)
  )

  def shellProcess(
    header:String, 
    command:String, 
    logPath:String
  )(processLambda:String => Unit):Int = shell(
    command=command, 
    header=Some(header), 
    logPath=Some(logPath), 
    processLambda=Some(processLambda)
  )

  def shellProcess(
    header:String, 
    command:String, 
    cwd:String,
    logPath:String
  )(processLambda:String => Unit):Int = shell(
    command=command, 
    header=Some(header), 
    cwd=Some(cwd),
    logPath=Some(logPath), 
    processLambda=Some(processLambda)
  )

  def shell(
    command:String, 
    header:Option[String]=None, 
    cwd:Option[String]=None, 
    logPath:Option[String]=None, 
    processLambda:Option[String => Unit]=None
  ):Int = {
    info(Console.YELLOW, header.getOrElse("command"), command)

    logPath.foreach { path => 
      info(Console.YELLOW, header.getOrElse("command"), s"log in ${getAbsolutePath(path)}")
    }
    val printer = new Printer {}
    val logFile = logPath.map { path => printer.openFile(path, false) }
    val cwdFile = cwd.map { path => new java.io.File(path) }
    val exitCode = sys.process.Process(command, cwdFile) ! ProcessLogger (
      { line => 
        logFile.foreach { l => l.println(line); l.flush }
        processLambda.foreach { _(line) }
      },
      { line => 
        logFile.foreach { l => l.println(line); l.flush } 
        processLambda.foreach { _(line) }
      }
    )
    logFile.foreach { _.close }

    if (exitCode != 0) err[Unit](s"failed $command", false)
    exitCode
  } 

}
