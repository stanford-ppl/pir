package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenCtxGen extends TungstenCodegen {

  val numStages = 6

  private def emitHeader(ctx:Context) = {
    emitln(s"""
#ifndef PCU_ACC_H_
#define PCU_ACC_H_

#include "context.h"
""")
    val reads = ctx.collectDown[BufferRead]()
    val writes = ctx.collectDown[BufferWrite]().filter { write =>
      val writeCtx = write.ctx.get
      write.out.T.exists { _.ctx.get != writeCtx }
    }
    val ctrs = ctx.collectDown[Counter]()
    // Declare externs
    writes.foreach { write =>
      write.out.T.foreach { read =>
        emitln(s"extern CheckedSend<Token>* $read;")
      }
    }
    // Class Definition
    emitln(s"""class $ctx: public Context<Token, $numStages> {""")
    incLevel
    // Emit fields
    emitln(s"protected:")
    // Declare buffers
    reads.foreach { read =>
      val depth = read.depth.get
      emitln(s"""FIFO<T, $depth> fifo_${read} = new FIFO<T, $depth>("$read")""")
    }
    writes.foreach { write =>
      emitln(s"""ValPipeline<Token, $numStages> *pipe_${write} = new ValPipeline<Token, $numStages>("pipe_${write}");""")
    }
    ctrs.foreach { ctr =>
      emitln(s"Counter<${ctr.par}> $ctr;")
    }
    emitln(s"public:")
    emitBlock(s"""explicit $ctx():Context<Token, $numStages>("$ctx")""") {
      reads.foreach { read =>
        emitln(s"""addInput(fifo_${read});""")
        emitln(s"""addChild(fifo_${read});""")
      }
      writes.foreach { write =>
        emitln(s"""addPipe(pipe_${write});""")
        emitln(s"""addChild(pipe_${write});""")
        write.out.T.foreach { read =>
          emitln(s"""addSend(${read});""")
        }
      }
    }
  }

  private def emitFooter = {
    decLevel
    emit(s"""
};
""")
  }

  override def emitNode(n:N) = n match {
    case n:Context =>
      emitln(s"""#include "$n.h"""")
      emitln(s"""$n ctx_${n}("$n");""")
      withOpen(dirName, s"$n.h", false) {
        emitHeader(n)
        emitBlock(s"void Compute()") {
          visitNode(n)
        }
        emitBlock(s"void PushPipe()") {
          n.collectDown[BufferWrite]().foreach { write =>
            emitBlock(s"if (pipe_$write->Valid())") {
              write.out.T.foreach { read =>
                emitln(s"""${read}->Push(pipe_$write->Read());""")
              }
            }
          }
        }
        emitFooter
      }

    case n => super.emitNode(n)
  }
}
