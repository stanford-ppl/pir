package prism
package macros

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.blackbox

final class node extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro node.impl
}

object node {
  def impl(c: blackbox.Context)(annottees: c.Tree*): c.Tree = {
    import c.universe._

    val (cls,obj) = annottees.toList match {
      case List(cd: ClassDef, md: ModuleDef) => (cd,md)
      case List(cd: ClassDef) => 
        (cd, q"object ${cd.name.toTermName}")
      case _ => invalidAnnotationUse(c, "node"); (null,null)
    }

    def recPrint(tree:Tree):Unit = {
      ConsoleLogger.dbgblk(s"======= ${tree.getClass.getSimpleName} =======") {
        println(tree)
        tree.children.foreach { c =>
          recPrint(c)
        }
      }
    }

    q"..${List(cls,obj)}"
  }

  def invalidAnnotationUse(c:blackbox.Context, name:String) {
    c.abort(c.enclosingPosition, s"@$name annotation can only be used on ops")
  }
}
