package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenCtxGen extends TungstenCodegen {

  def emitCtx(ctx:Context) = {
    enterFile(dirName, s"$ctx.h", false) {
      genCompute {
        visitNode(ctx)
      }
      emitln(s"""#ifndef PCU_ACC_H_""")
      emitln(s"""#define PCU_ACC_H_""")
      emitln(s"""#include "context.h"""")

      emitBlock(s"""class $ctx: public Context<Token, $numStages>""") {
        emitln(s"public:")
        getBuffer("fields").foreach { _.flushTo(sw) }
        ctxArgs.foreach { case (tp, field) =>
          emitln(s"$tp *$field;")
        }

        emitln(s"public:")
        emitBlock(s"""explicit $ctx(std::initializer_list<Module*> modules):Context<Token, $numStages>("$ctx")""") {
          ctxArgs.zipWithIndex.foreach { case ((tp, field), i) =>
            emitln(s"$field = ($tp*) modules[$i];")
          }
          getBuffer("inits").foreach { _.flushTo(sw) }
        }
        emitBlock(s"void Compute()") {
          getBuffer("computes").foreach { _.flushTo(sw) }
        }
        emitBlock(s"void PushPipe()") {
          emitln(s"Context::PushPipe();")
          getBuffer("push").foreach { _.flushTo(sw) }
        }
      }
      emit(s""";""")
    }
  }

  override def emitNode(n:N) = n match {
    case n:Context =>
      emitCtx(n)
      emitln(s"""#include "$n.h"""")
      emitln(s"""$n ctx_$n({${ctxArgs.map { _._2 }.map { _.& }.mkString(",")}});""")
      dutArgs += s"ctx_$n"
      ctxArgs.clear

    case n => super.emitNode(n)
  }

}
