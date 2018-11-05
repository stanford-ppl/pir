package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenMemGen extends TungstenCodegen with TungstenCtxGen {

  override def emitNode(n:N) = n match {
    case n:BufferRead =>
      genFields {
        val depth = n.depth.get
        emitln(s"""FIFO<Token, $depth> fifo_${n} = new FIFO<Token, $depth>("$n")""")
      }
      genInits {
        emitln(s"""addInput(fifo_$n);""")
        emitln(s"""addChild(fifo_$n);""")
      }
      emitVec(n)(s"fifo_${n}->Pop(${n.done.T})${if (n.getVec==0) "[0]" else ""};")

    case n:BufferWrite =>
      val data = n.data.T
      genExtern {
        n.out.T.foreach { read =>
          emitln(s"extern CheckedSend<Token>* $read;")
        }
      }
      genFields {
        emitln(s"""ValPipeline<Token, $numStages> *pipe_${n} = new ValPipeline<Token, $numStages>("pipe_${n}");""")
      }
      genInits {
        emitln(s"""addPipe(pipe_${n});""")
        emitln(s"""addChild(pipe_${n});""")
        n.out.T.foreach { read =>
          emitln(s"""addSend(${read});""")
        }
      }

      emitBlock(s"if (${n.done.T})") {
        emitln(s"Token ${n} = Token();")
        emitBlock(s"for (int i = 0; i < ${n.getVec}; i++)") {
          emitln(s"$n.floatVec_[i] = ${quoteRef(data).cast("float")}")
        }
        val (accumReads, otherReads) = n.out.T.partition { _.isPipeReg.get }
        accumReads.foreach { read =>
          emitln(s"fifo_$read.Push($n)")
        }
        if (otherReads.nonEmpty) {
          emitln(s"pipe_${n}.Push($n)")
        }
      }

    case n:BankedRead =>
    case n:BankedWrite =>
    case n:MemRead =>
    case n:MemWrite =>

    case n => super.emitNode(n)
  }

}
