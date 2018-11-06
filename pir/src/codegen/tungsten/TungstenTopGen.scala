package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenTopGen extends TungstenCodegen {

  override def initPass = {
    super.initPass
    emitln(
"""
#ifndef TEST_PCU_ACC_H_
#define TEST_PCU_ACC_H_
#include "example.h"
#include "module.h"
#include "state.h"
#include "token.h"
""")

  }

  override def emitNode(n:N) = n match {
    case n:Top => visitNode(n)
    case n:GlobalContainer => visitNode(n)
    case n => super.emitNode(n)
  }

  override def finPass = {
    getBuffer("top_end").foreach { _.flushTo(sw) }
    emitln(s"Module DUT({${dutArgs.map(_.&).mkString(",")}})")
    emitln(
"""
#endif // TEST_PCU_ACC_H_
""")
    super.finPass
  }

}
