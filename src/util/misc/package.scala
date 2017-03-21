package pir.util

import pir.graph._
import pir.codegen.{Printer, Logger}
import scala.language.implicitConversions
import java.lang.Thread
import pir.exceptions._

package object misc extends Logger {
  var startTime:Long = _
  var endTime:Long = _
  def tic = {
    startTime = System.nanoTime()
  }
  def toc(info:String, unit:String) = {
    endTime = System.nanoTime()
    val timeUnit = unit match {
      case "ns" => 1
      case "us" => 1000
      case "ms" => 1000000
      case "s" => 1000000000
      case _ => throw PIRException(s"Unknown time unit!")
    }
    val time = (endTime - startTime) * 1.0 / timeUnit
    println(s"$info elapsed time: ${f"$time%1.3f"}$unit")
  }

  def getStackTrace:String = {
    getStackTrace(1, 5)
  }
  def getStackTrace(start:Int, end:Int):String = {
    Thread.currentThread().getStackTrace().slice(start,end).map("" + _).mkString("\n")
  }
}

