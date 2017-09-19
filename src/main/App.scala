package pir

import node._
import pass._
import mapper._
import pir.codegen._
import spade.util._
import pir.util._
import pirc.exceptions._
import pir.util.misc._
import pir.spade.main._
import pir.spade.simulation._
import pir.spade.arch._

import scala.util.{Try, Success, Failure}

import scala.language.implicitConversions

trait PIRApp extends PIR {
  var arch:Spade = SN2x2
  override def name:String = this.getClass().getSimpleName().replace("$","")
  
  def setArgs(args: Array[String]):Unit = {
    args.foreach { 
      case arg if arg.contains("--arch") => arch = ConfigFactory.getArch(arg.split("=")(1))
      case arg if arg.contains("--") => Config.setOption(arg.replace("--","").trim)
      case arg if arg.contains("=") =>
        val k::v::_ = arg.split("=").toList
        top.argIns.filter {_.name==Some(k)}.foreach { argIn =>
          argIn.bound(toValue(v))
        }
      case arg =>
    }
  }

  def dramDefault = arch.dram.dramDefault

  def setDram(start:Int, array:Iterable[AnyVal]) = {
    array.zipWithIndex.foreach { case (a, i) => dramDefault(start + i) = a }
  }

  def main(top:Top): Any 
  def main(args: String): Unit = main(args.split(" "))
  def main(args: Array[String]): Unit = {
    info(s"args=[${args.mkString(", ")}]")
    reset
    top = Top().updateBlock(main) 
    setArgs(args)
    endInfo(s"Finishing graph construction for ${this}")
    run
  }
}

