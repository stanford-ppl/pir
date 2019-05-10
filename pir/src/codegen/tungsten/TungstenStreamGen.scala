package pir
package codegen

import pir.node._
import prism.graph._
import scala.collection.mutable

trait TungstenStreamGen extends TungstenCodegen with TungstenCtxGen {

  override def emitNode(n:N) = n match {
    case StreamReadContext(cmd) => super.visitNode(n)
    case StreamWriteContext(cmd) => super.visitNode(n)

    case n@FringeStreamRead(FileBus(filePath)) =>
      val (tp, name) = varOf(n)
      genCtxInits {
        emitln(s"""ofstream ${n}_file("${filePath}", std::ios::out);""")
      }

    case n => super.emitNode(n)
  }

  override def varOf(n:PIRNode):(String,String) = n match {
    case n:FringeDenseLoad => (s"DenseLoadAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n:FringeDenseStore => (s"DenseStoreAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n:FringeSparseLoad => (s"SparseLoadAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n:FringeSparseStore => (s"SparseStoreAG<${n.data.getVec}, ${spadeParam.burstSizeByte}, ${n.data.qtp}>", s"${n}")
    case n => super.varOf(n)
  }

  override def quoteRef(n:Any):String = n match {
    case n@OutputField(x:DRAMCommand, field) => s"true"
    case n => super.quoteRef(n)
  }

}

