package prism
package graph

trait ClassUtil {
  implicit class ClassHelper(cl:Class[_]) {
    def newInstanceAs[T](args:Seq[Any]):T = {
      val constructor = cl.getConstructors()(0) 
      try {
        constructor.newInstance(args.map(_.asInstanceOf[Object]):_*).asInstanceOf[T]
      } catch {
        case e:java.lang.IllegalArgumentException =>
          err(s"Error during newInstance of node $this", exception=false)
          err(s"Expected type: ${constructor.getParameterTypes().mkString(",")}", exception=false)
          err(s"Got type: ${args.map(_.getClass).mkString(",")}", exception=false)
          throw e
        case e:java.lang.reflect.InvocationTargetException =>
          err(s"InvocationTargetException during newInstance of node $this", exception=false)
          err(s"args=$args", exception=false)
          err(s"Cause:", exception=false)
          err(s"${e.getCause}", exception=false)
          throw e
        case e:Throwable => throw e
      }
    }
  }
}
