package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import spade.param._
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

using namespace std;

""")

    if (!config.asModule) {
      emitln(s"""#include "hostio.h"""")
    }

  }

  // (type, name, constructor args, extern)
  // extern: if generate as module, these members are passed from outside of top module
  val topMembers = mutable.ListBuffer[(String, String, Seq[String], Boolean)]()
  def genTopMember(tp:Any, name:Any, args:Seq[String], extern:Boolean=false) = {
    genTop {
      if (!config.asModule | !extern)
        emitln(s"$tp $name;")
    }
    topMembers += ((tp.toString, name.toString, args.map{_.toString}, extern))
  }
  def genTopFinalMember(tp:Any, name:Any, args:Seq[String], extern:Boolean=false) = {
    genTopEnd {
      if (!config.asModule | !extern)
        emitln(s"$tp $name;")
    }
    topMembers += ((tp.toString, name.toString, args.map{_.toString}, extern))
  }

  override def emitNode(n:N) = n match {
    case n:Top => visitNode(n)
    case n:GlobalContainer => visitNode(n)
    case n => super.emitNode(n)
  }

  override def finPass = {
    if (!noPlaceAndRoute) {
      val pattern = spadeParam.pattern.as[GridPattern]
      val row = pattern.row
      val col = pattern.col
      genTopMember("DynamicNetwork<4, 4>", "net", Seq(s"{${row}, ${col}}", "net".qstr), extern=true)
      genTopMember("DynamicNetwork<4, 4>", "statnet", Seq(s"{${row}, ${col}}", "statnet".qstr), extern=true)
    }
    val topName = if (config.asModule) pirTop.name.get else "Top"
    declareClass(s"""$topName: public Module""") {
      emitln("public:")
      getBuffer("top").foreach { _.flushTo(sw) }
      getBuffer("top-end").foreach { _.flushTo(sw) }
      val (topArgs, members) = topMembers.partition { _._4 & config.asModule } 
      val argList = topArgs.map { case (tp, name, args, extern) => s"${tp}& ${name}" }.mkString(",\n    ")
      val memberInits = members.filter { _._3.nonEmpty }.map { case (tp, name, args, extern) =>
        s"$name(${args.mkString(",")})"
      }.mkString(",\n      ")
      emitBlock(s"""explicit $topName(\n    $argList\n  ): Module("$topName"),\n      $memberInits""") {
        emitln(s"AddChildren({${members.map { _._2.&}.mkString(",")}});")
      }
    }
    if (!config.asModule) {
      emitBlock(s"void RunAccel()") {
        emitRunAccel
      }
    }
    super.finPass
  }

  protected def emitRunAccel = {
    emitln("Top top;")
    emitln(s"""REPL repl(&top, std::cout);""")
    emitln(s"""repl.Command("source script");""")
  }

  final protected def genTop(block: => Unit) = enterBuffer("top", level=1)(block)

  final protected def genTopEnd(block: => Unit) = enterBuffer("top-end", level=1)(block)

}
