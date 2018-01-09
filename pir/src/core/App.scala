package pir

import pir.newnode._
import pir.util._

import spade._
import arch._

import pirc._
import pirc.util._

import scala.language.implicitConversions
import scala.reflect.runtime.universe
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
        top.argIns.filter {_.name==Some(k)}.foreach { argIn =>
          argIn.bound(toValue(v))
        }
      case arg =>
    }
  }

  def loadDesign = {
    val path = s"${outDir}${File.separator}${name}.pir"
    val ois = new ObjectInputStream(new FileInputStream(path))
    ois.readObject.asInstanceOf[Top]
  }

  def newDesign = {
    val top = new Top()
    main(top)
    endInfo(s"Finishing graph construction for ${this}")
    top
  }

  def getArch(name:String) = {
    val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
    val module = runtimeMirror.staticModule("arch." + name)
    val obj = runtimeMirror.reflectModule(module)
    obj.instance.asInstanceOf[Spade]
  }

  def saveDesign:Unit = {
    info(s"Saving Design")
    val path = s"${outDir}${File.separator}${name}.pir"
    val oos = new ObjectOutputStream(new FileOutputStream(path))
    val obj = newTop
    println(obj)
    oos.writeObject(obj)
    oos.close
  }

  def save(node:Any) = {
    val path = s"${outDir}${File.separator}${name}.pir"
    val oos = new ObjectOutputStream(new FileOutputStream(path))
    info(s"Saving Design")
    oos.writeObject(node)
    oos.close
  }

  def load = {
    import pir.newnode._
    val path = s"${outDir}${File.separator}${name}.pir"
    val ois = new ObjectInputStream(new FileInputStream(path)) {
      override def resolveClass(desc: java.io.ObjectStreamClass): Class[_] = {
        try { Class.forName(desc.getName, false, getClass.getClassLoader) }
        catch { case ex: ClassNotFoundException => super.resolveClass(desc) } // Magic. Don't know why this fix ClassNotFound exception
      }
    }
    info(s"Loading Design")
    ois.readObject
  }


  def main(top:Top): Any 
  def main(args: Array[String]): Unit = {

    val node = newDesign
    println("node", node)
    save(node)
    val loaded = load.asInstanceOf[pir.newnode.Top]
    println("loaded", loaded)
    System.exit(0)

    info(s"args=[${args.mkString(", ")}]")
    reset
    setArgs(args)
    try {
      newTop = if (PIRConfig.loadDesign) loadDesign else newDesign
      arch = getArch(PIRConfig.arch)
      arch.initDesign
      run
      if (PIRConfig.saveDesign) saveDesign
      if (SpadeConfig.saveDesign) arch.saveDesign
    } catch { 
      case e:Exception =>
        errmsg(e)
        handle(e)
    }
  }

}

