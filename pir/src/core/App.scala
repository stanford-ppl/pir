package pir

import pir.node._
import pir.util._

import spade._
import arch._

import pirc._
import pirc.util._

import scala.language.implicitConversions

trait PIRApp extends PIR {
  var arch:Spade = SN2x2
  override def name:String = this.getClass().getSimpleName().replace("$","")
  
  def dramDefault = arch.top.dram.dramDefault

  def setDram(start:Int, array:Iterable[AnyVal]) = {
    array.zipWithIndex.foreach { case (a, i) => dramDefault(start + i) = a }
  }

  override def setArgs(args: Array[String]):Unit = {
    super.setArgs(args)
    args.foreach { 
      case arg if arg.contains("--arch") => arch = getArch(arg.split("=")(1))
      case arg if arg.contains("=") =>
        val k::v::_ = arg.split("=").toList
        top.argIns.filter {_.name==Some(k)}.foreach { argIn =>
          argIn.bound(toValue(v))
        }
      case arg =>
    }
  }

  def main(top:Top): Any 
  def main(args: Array[String]): Unit = {
    info(s"args=[${args.mkString(", ")}]")
    reset
    try {
      top = Top()
      top.updateBlock(main) 
      setArgs(args)
      arch.top.config
      endInfo(s"Finishing graph construction for ${this}")
      run
    } catch { 
      case e:Exception =>
        errmsg(e)
        handle(e)
    }
  }

  def getArch(name:String) = {
    name match {
      case "SN16x13_LD" => SN16x13_LD
      case "SN16x8_LD" => SN16x8_LD
      case "SN8x8_LD" => SN8x8_LD
      case "SN4x4" => SN4x4
    }
  }
}

