package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import spade.param._
import scala.collection.mutable

trait TungstenTopGen extends TungstenCodegen {

  def emitTopHeader = {
    genTop {
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
      emitln(s"""#include "hostio.h"""")
    }
  }
  
  // (type, name, constructor args, extern, escape)
  // extern: if generated as module, these members are instantiated from outside of top module
  // escape: if generated as module, these members are passed in to the top module
  case class TopMember(tp:String, name:String, args:Seq[String], extern:Boolean, escape:Boolean, alias:Option[String])
  val topMembers = mutable.ListBuffer[TopMember]()

  def genTopMember(n:PIRNode, args:Seq[String], end:Boolean=false):Unit = {
    val interfaceTp = n match {
      case n:GlobalInput if config.asModule & n.isExtern.get => "CheckedSend<Token>"
      case n:GlobalOutput if config.asModule & n.isExtern.get => "CheckedReceive<Token>"
      case n => varOf(n)._1
    }
    val (tp, name) = varOf(n)
    genTopMember(tp, name,args,end,n.isExtern.get,n.isEscaped, n.externAlias.v, interfaceTp)
  }
  def genTopMember(tp:Any, name:Any, args:Seq[String], end:Boolean, extern:Boolean, escape:Boolean):Unit = {
    genTopMember(tp.toString, name.toString, args, end, extern, escape, None, tp.toString)
  }
  private def genTopMember(tp:String, name:String, args:Seq[String], end:Boolean, extern:Boolean, escape:Boolean, alias:Option[String], interfaceTp:String):Unit = {
    val ext = extern & config.asModule
    if (!ext) {
      if (end)
        genTopEndFields {
          emitln(s"$tp $name;")
        }
      else
        genTopFields {
          emitln(s"$tp $name;")
        }
    } else {
      if (end) genExternEnd {
        emitln(s"$tp $name(${args.mkString(",")});")
      } else enterFile(outputPath, false) {
        emitln(s"$tp $name(${args.mkString(",")});")
      }
    }
    topMembers += TopMember(interfaceTp, name, args, ext, escape, alias)
  }

  override def initPass = {
    super.initPass
    emitln(s"""#include "Top.h"""")
  }

  override def emitNode(n:N) = n match {
    case n:Top => 
      emitTopHeader
      visitNode(n)
    case n:GlobalContainer => visitNode(n)
    case n => super.emitNode(n)
  }

  override def finPass = {
    if (!noPlaceAndRoute) {
      val pattern = spadeParam.pattern.as[GridPattern]
      val row = pattern.row
      val col = pattern.col
      genTopMember("DynamicNetwork<4, 4>", "net", Seq(s"{${row}, ${col}}", "net".qstr), end=false, extern=true, escape=true)
      genTopMember("DynamicNetwork<4, 4>", "statnet", Seq(s"{${row}, ${col}}", "statnet".qstr), end=false, extern=true, escape=true)
    }
    getBuffer("extern-end").foreach { _.flushTo(sw) }
    val topName = if (config.asModule) pirTop.name.get else "Top"
    val (externs, members) = topMembers.partition { _.extern } 
    val (escapes, _) = externs.partition { _.escape }
    val dutArgs = ("top" +: externs.map { _.name }).map{_.&}.mkString(",")
    val topArgsSig = escapes.map { mem => s"${mem.tp}& ${mem.name}" }.mkString(",\n    ")
    val topArgs = if (escapes.isEmpty) "" else s"(${escapes.map { _.name }.mkString(",")})" 

    genTop {
      declareClass(s"""$topName: public Module""") {
        emitln("public:")
        getBuffer("top").foreach { _.flushTo(sw) }
        getBuffer("top-end").foreach { _.flushTo(sw) }
        val memberInits = members.filter { _.args.nonEmpty }.map { mem =>
          s"${mem.name}(${mem.args.mkString(",")})"
        }.mkString(",\n      ")
        emitBlock(s"""explicit $topName(\n    $topArgsSig\n  ): Module("$topName"),\n      $memberInits""") {
          emitln(s"AddChildren({${members.map { _.name.&}.mkString(",")}});")
          emitInit
        }
        val aliasArgs = escapes.map { mem => 
          val alias = mem.alias.getOrElse(mem.name) 
          s""" *((${mem.tp}*)alias["$alias"])"""
        }.mkString(",")
        emitln(s"""$topName(map<string, Module*> alias): $topName($aliasArgs) {}""")
      }
    }
    emitln(s"""using namespace std;""")
    emitBlock(s"void RunAccel()") {
      emitln(s"$topName top$topArgs;")
      emitln(s"""Module DUT({$dutArgs}, "DUT");""")
      emitln(s"""REPL repl(&DUT, std::cout);""")
      emitln(s"""repl.Command("source script");""")
    }
    super.finPass
  }

  protected def emitInit = {}

  protected def genTop(block: => Unit) = enterFile(dirName, "Top.h")(block)

  private def genTopFields(block: => Unit) = enterBuffer("top", level=1)(block)

  private def genTopEndFields(block: => Unit) = enterBuffer("top-end", level=1)(block)

  private def genExternEnd(block: => Unit) = enterBuffer("extern-end")(block)

}
