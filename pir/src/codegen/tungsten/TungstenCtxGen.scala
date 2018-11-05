package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenCtxGen extends TungstenCodegen {

  val numStages = 6

  def emitCtx(ctx:Context) = {
    withOpen(dirName, s"$ctx.h", false) {
      genCompute {
        visitNode(ctx)
      }
      emitln(s"""#ifndef PCU_ACC_H_""")
      emitln(s"""#define PCU_ACC_H_""")
      emitln(s"""#include "context.h"""")
      getBuffer("extern").foreach { _.flushTo(sw) }

      emitBlock(s"""class $ctx: public Context<Token, $numStages>""") {
        emitln(s"protected:")
        getBuffer("fields").foreach { _.flushTo(sw) }

        emitln(s"public:")
        emitBlock(s"""explicit $ctx():Context<Token, $numStages>("$ctx")""") {
          getBuffer("inits").foreach { _.flushTo(sw) }
        }
        emitBlock(s"void Compute()") {
          getBuffer("computes").foreach { _.flushTo(sw) }
        }
        //emitBlock(s"void PushPipe()") {
          //n.collectDown[BufferWrite]().foreach { write =>
            //emitBlock(s"if (pipe_$write->Valid())") {
              //write.out.T.foreach { read =>
                //emitln(s"""${read}->Push(pipe_$write->Read());""")
              //}
            //}
          //}
        //}
      }
      emit(s""";""")
    }
  }

  override def emitNode(n:N) = n match {
    case n:Context =>
      emitln(s"""#include "$n.h"""")
      emitln(s"""$n ctx_${n}("$n");""")
      emitCtx(n)

    case n => super.emitNode(n)
  }

  final protected def genExtern(block: => Unit) = enterBuffer("extern"){ block }

  final protected def genFields(block: => Unit) = enterBuffer("fields"){ incLevel(1); block; decLevel(1) }

  final protected def genInits(block: => Unit) = enterBuffer("inits") { incLevel(2); block; decLevel(2) }

  final protected def genCompute(block: => Unit) = enterBuffer("computes") { incLevel(2); block; decLevel(2) }

}
