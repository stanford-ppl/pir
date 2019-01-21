package prism
package codegen

import prism.graph._

trait HtmlIRPrinter extends IRPrinter with HtmlCodegen {

  override def emitBlock(ms:String)(block: =>Unit):T ={ 
    text(ms)
    emitElem("table", "border"->3, "cellpadding"->5, "cellspacing"->10) {
      emitElem("tbody") {
        emitElem("tr") {
          emitElem("td") {
            try {
              block
            } catch {
              case e:Exception =>
                err(s"Exception $e during IRPrinter on. Stack trace printed in log", exception=false)
                text(s"Exception $e during IRPrinter on. Stack trace printed in log")
                flush
                dbg(s"Exception:$e")
                dbg(e.getStackTrace.mkString("\n"))
              case e:Throwable => throw e
            }
          }
        }
      }
    }
  }

  override def emitNode(n:N) = {
    emitBlock(elem("h3", qdef(n), "id" -> s"$n")) {
      text(s"parent=${n.parent.map(quote)}")
      if (n.children.nonEmpty) {
        if (n.children.size > 10)
          text(s"children=${quote(n.children.slice(0,10))} ...")
        else
          text(s"children=${quote(n.children)}")
        text("deps:")
        n.depsFrom.foreach { case (out, ins) => 
          val dep = out.src
          val depParent = dep.parent.fold(""){ p=> s"(${quote(p)})"}
          text(s"${quote(dep)}.${quote(out)}$depParent: ${ins.map { in => s"${quote(in.src)}.${quote(in)}"}}")
        }
        text("depeds:")
        n.depedsTo.foreach { case (out, ins) => 
          text(s"${quote(out)}: ${ins.map { in => s"${in.src}.${in}${in.src.parent.fold("") { p => s"(${quote(p)})" }}" }}")
        }
      }
      n.localEdges.foreach { edge =>
        emitElem("text", s"${edge}: ${quote(edge.connected.map{ e => s"${quote(e.src)}.${quote(e)}"})}<br>", "id"->s"$edge")
      }
      text(elem("strong", "Metadata"))
      n.metadata.values.foreach { metadata =>
        metadata.v.foreach { v =>
          text(s"${metadata.name} = ${quote(v)}")
        }
      }
      if (n.children.nonEmpty) visitNode(n)
    }
    if (n.children.isEmpty) visitNode(n)
  }

  override def quote(n:Any) = n match {
    case n:Node[n] =>
      val q = super.quote(n)
      elem("a", q, "href"->s"${fileName}#$q")
    case n:Edge =>
      val q = super.quote(n)
      elem("a", q, "href"->s"${fileName}#$q")
    case n => super.quote(n)
  }

}

