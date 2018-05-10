package prism
package util

import java.io._
trait Serialization {

  def saveToFile(node:Serializable, path:String) = {
    mkdir(dirName(path))
    val oos = new ObjectOutputStream(new FileOutputStream(path))
    info(s"Saving node $node to $path")
    oos.writeObject(node)
    oos.close
  }

  def loadFromFile[T](path:String) = {
    infor(s"Loading from $path")
    val ois = new ObjectInputStream(new FileInputStream(path)) {
      override def resolveClass(desc: java.io.ObjectStreamClass): Class[_] = {
        try { Class.forName(desc.getName, false, getClass.getClassLoader) }
        catch { case ex: ClassNotFoundException => super.resolveClass(desc) } // Magic. Don't know why this fix ClassNotFound exception
      }
    }
    val obj = ois.readObject.asInstanceOf[T]
    info(s"Loading $obj from $path")
    obj
  }
}

trait Serializable extends scala.Serializable
