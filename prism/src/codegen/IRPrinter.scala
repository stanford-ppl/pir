package prism.codegen

import prism.node._

trait IRPrinter extends Codegen {

  type N <: Node[N]

  def shouldRun = Config.debug

  def qdef(n:Any):String

  val metadata:Option[Metadata]

  def emitSubGraph(n:SubGraph[N] with N) = {
    emitBlock(qdef(n)) {
      emitln(s"parent=${n.parent.map(quote)}")
      metadata.foreach { _.summary(n).foreach(emitln) }
      super.visitNode(n)
    }
  }

  def emitAtom(n:Atom[N] with N) = {
    emitBlock(qdef(n.asInstanceOf[N])) {
      emitln(s"parent=${n.parent.map(quote)}")
      n.ios.foreach { io =>
        emitln(s"$io.connected=[${io.connected.mkString(",")}]")
      }
      emitln(s"deps=${n.deps.map(quote)}")
      emitln(s"depeds=${n.depeds.map(quote)}")
      metadata.foreach { _.summary(n).foreach(emitln) }
    }
    super.visitNode(n)
  }

  def emitBlock(ms:String)(block: =>Unit):T ={ 
    super.emitBlock(ms) {
      try {
        block
      } catch {
        case e:Exception =>
          errmsg(s"Exception $e during IRPrinter on. Stack trace printed in log")
          emitln(s"Exception $e during IRPrinter on. Stack trace printed in log")
          flush
          dbg(s"Exception:$e")
          dbg(e.getStackTrace.mkString("\n"))
        case e:Throwable => throw e
      }
    }
  }

  override def emitNode(n:N) = {
    n match {
      case n:SubGraph[N] => emitSubGraph(n.asInstanceOf[SubGraph[N] with N])
      case n:Atom[N] => emitAtom(n.asInstanceOf[Atom[N] with N])
    }
  }

}

