package prism.codegen

import prism._
import prism.util._
import prism.node._
import prism.codegen._

trait IRPrinter extends Codegen {

  type N <: Node[N]

  def shouldRun = Config.debug

  def qdef(n:Any):String

  val metadata:Metadata

  def emitSubGraph(n:SubGraph[N] with N) = {
    emitBlock(qdef(n)) {
      handle {
        emitln(s"parent=${n.parent.map(quote)}")
        metadata.summary(n).foreach(emitln)
      }
      super.visitNode(n)
    }
  }

  def emitAtom(n:Atom[N] with N) = {
    emitBlock(qdef(n.asInstanceOf[N])) {
      handle {
        emitln(s"parent=${n.parent.map(quote)}")
        n.ios.foreach { io =>
          emitln(s"$io.connected=[${io.connected.mkString(",")}]")
        }
        emitln(s"deps=${n.deps.map(quote)}")
        emitln(s"depeds=${n.depeds.map(quote)}")
        metadata.summary(n).foreach(emitln)
      }
    }
    super.visitNode(n)
  }

  override def emitNode(n:N) = {
    n match {
      case n:SubGraph[N] => emitSubGraph(n.asInstanceOf[SubGraph[N] with N])
      case n:Atom[N] => emitAtom(n.asInstanceOf[Atom[N] with N])
    }
  }

  def handle(block: => Unit) = {
    try {
      block
    } catch {
      case e:Exception =>
        errmsg(s"Exception $e during IRPrinter on. Stack trace printed in log")
        emitln(s"Exception $e during IRPrinter on. Stack trace printed in log")
        dbg(e)
        dbg(e.getStackTrace)
      case _:Throwable =>
    }
  }

}

