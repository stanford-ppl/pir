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
#include "example.h"
#include "module.h"
#include "state.h"
#include "token.h"
#include <cassert>
#include <iomanip>

#include "counter.h"
#include "controller.h"
#include "op.h"
#include "context.h"
#include "broadcast.h"
#include "bankedsram.h"
#include "nbuffer.h"
#include "logger.h"

#include "io.h"

using namespace std;

""")

  }

  override def emitNode(n:N) = n match {
    case n:Top => visitNode(n)
    case n:GlobalContainer => visitNode(n)
    case n => super.emitNode(n)
  }

  override def finPass = {
    getBuffer("top-end").foreach { _.flushTo(sw) }
    emitln(
"""
class Tester : public Logger {
 public:
  explicit Tester(): Logger() { }
  void Log() {
    cout << "#" << setw(2) << cycle << " ";
    // Log modules here
    cout << endl;
  }
};

Tester logger;
""")
    dutArgs += "logger";
    emitln(s"""Module DUT({${dutArgs.map(_.&).mkString(",")}}, "DUT");""")
    super.finPass
  }

  val dutArgs = mutable.ListBuffer[String]()

  final protected def genTop(block: => Unit) = enterFile(outputPath, false)(block)

  final protected def genTopEnd(block: => Unit) = enterBuffer("top-end")(block)

}
