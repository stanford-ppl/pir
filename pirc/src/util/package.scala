package pirc

import pirc.exceptions._

import scala.language.implicitConversions
import scala.collection.mutable.Stack
import java.lang.Thread

package object util extends Printer {
  val times = Stack[Long]()
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

  def getStackTrace:String = {
    getStackTrace(1, 5)
  }
  def getStackTrace(start:Int, end:Int):String = {
    val curThread = Thread.currentThread()
    val trace = curThread.getStackTrace()
    trace.slice(start,end).map("" + _).mkString("\n")
  }

  def info(s:String) = emitln(s"[pir] ${s}")
  def startInfo(s:String) = emit(s"[pir] ${s}")
  def endInfo(s:String) = { emitln(s" ${s}") }
  def success(s:Any) = emitln(s"\n[${Console.GREEN}success${Console.RESET}] ${s}")
  def warn(s:Any) = emitln(s"\n[${Console.YELLOW}warning${Console.RESET}] ${s}")
  def errmsg(s:Any) = { emitln(s"\n[${Console.RED}error${Console.RESET}] ${s}") }
  def bp(s:Any) = emitln(s"[${Console.RED}break${Console.RESET}]${s}")
  def err(s:Any) = { errmsg(s); throw PIRException(s"$s") }
  def ask(question:String) = {
    info(question)
    scala.io.StdIn.readLine()
  }

}

