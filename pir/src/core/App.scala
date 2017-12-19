package pir

import pir.node._
import pir.util._

import spade._
import arch._

import pirc._
import pirc.util._

import scala.language.implicitConversions
import scala.reflect.runtime.universe

trait PIRApp extends PIR {
  override def name:String = this.getClass().getSimpleName().replace("$","")
  
  def dramDefault = arch.top.dram.dramDefault

  def setDram(start:Int, array:Iterable[AnyVal]) = {
    array.zipWithIndex.foreach { case (a, i) => dramDefault(start + i) = a }
  }

  var args:Array[String] = _ 

  override def setArgs(args: Array[String]):Unit = {
    super.setArgs(args)
    this.args = args
  }

  def parseArgIns() = {
    args.foreach { 
      case arg if arg.contains("=") =>
        val k::v::_ = arg.split("=").toList
        top.argIns.filter {_.name==Some(k)}.foreach { argIn =>
          argIn.bound(toValue(v))
        }
      case arg =>
    }
  }

  def loadApp() = {
  }

  def newApp() = {
    top = Top()
    top.updateBlock(main) 
  }

  def newArch() = {
    val name = PIRConfig.arch
    val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
    val module = runtimeMirror.staticModule("arch." + name)
    val obj = runtimeMirror.reflectModule(module)
    arch = obj.instance.asInstanceOf[Spade]
    arch.top.connectAll
  }

  def main(top:Top): Any 
  def main(args: Array[String]): Unit = {
    info(s"args=[${args.mkString(", ")}]")
    reset
    setArgs(args)
    try {
      newApp()
      newArch()
      endInfo(s"Finishing graph construction for ${this}")
      run
    } catch { 
      case e:Exception =>
        errmsg(e)
        handle(e)
    }
  }

}

