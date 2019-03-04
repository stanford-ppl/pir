package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import scala.collection.mutable

trait TungstenTopGen extends TungstenCodegen {

  val dutArgs = mutable.ListBuffer[String]()

  override def initPass = {
    super.initPass
    emitln(
"""
#include "example.h"
#include "module.h"
#include "state.h"
#include "token.h"
#include <cassert>
#include <iomanip>
#include <fstream>

#include "counter.h"
#include "controller.h"
#include "op.h"
#include "context.h"
#include "broadcast.h"
#include "bankedsram.h"
#include "nbuffer.h"
#include "dramag.h"
#include "network.h"
#include "staticnetwork.h"

#include "hostio.h"

using namespace std;

""")

    if (!noPlaceAndRoute) { //TODO: use spade parameter
      emitln("""DynamicNetwork<4, 4> net({16, 12}, "net");""")
      dutArgs += "net"
      emitln("""StaticNetwork<2, 1> statnet("statnet");""")
      dutArgs += "statnet"
    }

  }

  override def emitNode(n:N) = n match {
    case n:Top => visitNode(n)
    case n:GlobalContainer => visitNode(n)
    case n => super.emitNode(n)
  }

  override def finPass = {
    getBuffer("top-end").foreach { _.flushTo(sw) }

    emitln(s"""Module DUT({${dutArgs.map(_.&).mkString(",")}}, "DUT");""")
    emitBlock(s"void RunAccel()") {
      emitln(s"""REPL Top(&DUT, std::cout);""")
      emitln(s"""Top.Command("source script");""")
      emitln(s"""Top.RunAll();""")
    }
    super.finPass
  }

  final protected def genTop(block: => Unit) = enterFile(outputPath, false)(block)

  final protected def genTopEnd(block: => Unit) = enterBuffer("top-end")(block)

}
