package prism
package codegen

import prism.graph._

class BasicIRPrinter[TN<:Node[TN]](override val dirName:String, val fileName:String, val top:TN) extends Pass()(null) with IRPrinter {
  type N = TN
  override def debug = true
}

trait IRPrinter extends Pass with DFSTopDownTopologicalTraversal with Codegen {

  val forward = true

  def qdef(n:N) = s"${quote(n)}${n.to[Product].fold("") { n => s"(${n.productIterator.map(quote).mkString(",")})" }}"

  //val metadata:Option[Metadata]

  def emitBlock(ms:String)(block: =>Unit):T ={ 
    super.emitBlock(ms) {
      try {
        block
      } catch {
        case e:Exception =>
          err[Unit](s"Exception $e during IRPrinter on. Stack trace printed in log", exception=false)
          emitln(s"Exception $e during IRPrinter on. Stack trace printed in log")
          flush
          dbg(s"Exception:$e")
          dbg(e.getStackTrace.mkString("\n"))
        case e:Throwable => throw e
      }
    }
  }

  override def emitNode(n:N) = {
    emitBlock(qdef(n)) {
      emitln(s"parent=${n.parent.map(quote)}")
      //metadata.foreach { _.summary(n).foreach(emitln) }
      if (n.children.nonEmpty) {
        if (n.children.size > 10)
          emitln(s"children=${n.children.slice(0,10).map(quote)} ...")
        else
          emitln(s"children=${n.children.map(quote)}")
      }
      n.localEdges.foreach { edge =>
        emitBlock(s"$edge=[${quote(edge.connected)}]") {
          edge.metadata.values.foreach { metadata =>
            metadata.v.foreach { v =>
              dbg(s"${metadata.name} = $v")
            }
          }
        }
      }
      emitln(s"deps=${n.deps().toList.map(quote)}")
      emitln(s"depeds=${n.depeds().toList.map(quote)}")
      n.metadata.values.foreach { metadata =>
        metadata.v.foreach { v =>
          emitln(s"${metadata.name} = $v")
        }
      }
      if (n.children.nonEmpty) visitNode(n)
    }
    if (n.children.isEmpty) visitNode(n)
  }

  override def quote(n:Any) = n match {
    case n:Iterable[_] => 
      s"[${n.map(quote).mkString(",")}]"
    case Some(n) => 
      s"Some(${quote(n)})"
    case n => super.quote(n)
  }

}

