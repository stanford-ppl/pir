package prism
package macros

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context

object TermExtractor {
  def impl(c: Context)(x: c.Tree): c.Tree = {
    import c.universe._
    x match {
      case q"$mods class $tpname[..$tparams] $ctorMods(...$paramss) extends { ..$earlydefns } with ..$parents { $self => ..$stats }" =>
        val newStats = stats.flatMap { 
          case s@q"$mods val $tname = $expr" => List(s, q"$tname.setName(${c.parse(s""""$tname"""")})")
          case s => List(s)
        }
        q"$mods class $tpname[..$tparams] $ctorMods(...$paramss) extends { ..$earlydefns } with ..$parents { $self => ..$newStats }"
      case _ => x
    }
  }
}

@compileTimeOnly("This should not be here")
final class metadata extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro metadata.impl
}
object metadata {
  def impl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._
    c.Expr[Any](TermExtractor.impl(c)(annottees(0).tree))
  } 
}
