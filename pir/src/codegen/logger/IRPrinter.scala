package pir.codegen
import pir._
import pir.util._
import pir.pass._
import pir.node._

import pirc._
import pirc.util._
import prism.node._
import prism.traversal._
import prism.codegen._

import sys.process._
import scala.language.postfixOps
import scala.collection.mutable

class IRPrinter(val fileName:String)(implicit design:PIR) extends PIRCodegen {

  def shouldRun = Config.debug

  override def quote(n:Any) = qtype(n)

  override def emitNode(n:N) = {
    n match {
      case n:SubGraph[_] =>
        emitBlock(qdef(n)) {
          emitln(s"parent=${quote(n.parent)}")
          traverse(n)
        }
      case n:Atom[_] =>
        emitBlock(qdef(n)) {
          emitln(s"parent=${quote(n.parent)}")
          emitln(s"deps=${n.deps.map(quote)}")
          emitln(s"depeds=${n.depeds.map(quote)}")
          n.ios.foreach { io =>
            emitln(s"$io.connected=[${io.connected.mkString(",")}]")
          }
        }
        traverse(n)
    }
  }

}

