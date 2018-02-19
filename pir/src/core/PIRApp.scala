package pir

import pir.node._
import pir.util._

import spade._
import arch._

import pirc._
import pirc.util._

import scala.language.implicitConversions
import scala.reflect.runtime.universe
import scala.collection.mutable.ListBuffer
import java.io._

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
        //top.argIns.filter {_.name==Some(k)}.foreach { argIn =>
          //argIn.bound(toValue(v))
        //}
      case arg =>
    }
  }

  val designPath = s"${outDir}${File.separator}${name}.pir" //TODO: make this configurable

  def loadDesign = top = loadFromFile[Top](designPath)

  def saveDesign:Unit = saveToFile(top, designPath)

  def newDesign = {
    top = new Top()
    main(top)
    endInfo(s"Finishing graph construction for ${this}")
  }

  def getArch(name:String) = {
    val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
    val module = runtimeMirror.staticModule("arch." + name)
    val obj = runtimeMirror.reflectModule(module)
    obj.instance.asInstanceOf[Spade]
  }

  def main(top:Top): Any 
  def main(args: Array[String]): Unit = {
    info(s"args=[${args.mkString(", ")}]")
    reset
    setArgs(args)
    try {
      if (PIRConfig.loadDesign) loadDesign else newDesign
      arch = getArch(PIRConfig.arch)
      arch.initDesign
      run
      saveDesign //TODO: if (PIRConfig.saveDesign) saveDesign
      //if (SpadeConfig.saveDesign) arch.saveDesign
    } catch { 
      case e:Exception =>
        errmsg(e)
        handle(e)
    }
  }

}

