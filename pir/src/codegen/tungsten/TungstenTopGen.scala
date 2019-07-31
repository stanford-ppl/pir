package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import spade.param._
import scala.collection.mutable

trait TungstenTopGen extends TungstenCodegen {

  lazy val topFile = s"$topName.h"

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
#include "staticnetwork.h"
#include "idealnetwork.h"

using namespace std;

""")
      if (config.asModule) {
        val hostio = s"${topName}_hostio.h"
        moveFile(buildPath(dirName, s"hostio.h"), buildPath(dirName,hostio))
        emitln(s"""#include "$hostio"""")
      } else {
        emitln(s"""#include "hostio.h"""")
      }
    }
  }
  
  // (type, name, constructor args, extern, escape)
  // extern: if generated as module, these members are instantiated from outside of top module
  // escape: if generated as module, these members are passed in to the top module
  case class TopMember(tp:String, name:String, args:Seq[String], extern:Boolean, escape:Boolean, alias:Option[String])
  val topMembers = mutable.Map[String, TopMember]()

  def genTopMember(n:PIRNode, args: => Seq[String], end:Boolean=false):Unit = {
    val interfaceTp = n match {
      case n:GlobalInput if config.asModule & n.isExtern.get => "CheckedSend<Token>"
      case n:GlobalOutput if config.asModule & n.isExtern.get => "CheckedReceive<Token>"
      case n => varOf(n)._1
    }
    val (tp, name) = varOf(n)
    genTopMember(tp, name,args,end,n.isExtern.get,false, n.externAlias.v, interfaceTp)
  }
  def genTopMember(tp:Any, name:Any, args: => Seq[String], end:Boolean, extern:Boolean, escape:Boolean):Unit = {
    genTopMember(tp.toString, name.toString, args, end, extern, escape, None, tp.toString)
  }
  private def genTopMember(tp:String, name:String, args: => Seq[String], end:Boolean, extern:Boolean, escape:Boolean, alias:Option[String], interfaceTp:String):Unit = {
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
      val saved = enterTop
      enterTop = false
      if (end) genExternEnd {
        emitln(s"$tp $name(${args.mkString(",")});")
      } else enterFile(outputPath, false) {
        emitln(s"$tp $name(${args.mkString(",")});")
      }
      enterTop = saved
    }
    topMembers += name -> TopMember(interfaceTp, name, args, ext, escape & ext, alias)
  }

  private var enterTop = false
  override def isPointer(x:Any) = {
    x.to[String].flatMap { x =>
      if (enterTop)
        topMembers.get(x).flatMap { mem =>
          if (mem.escape) Some(true) else None
        }
      else None
    }.getOrElse(super.isPointer(x))
  }

  override def initPass = {
    super.initPass
    emitln(s"""#include "$topFile"""")
    emitln(s"""using namespace std;""")
    emitBSln("void RunAccel()")
    if (!noPlaceAndRoute) {
      val pattern = spadeParam.pattern.as[GridPattern]
      val row = pattern.row
      val col = pattern.col
      genTopMember("DynamicNetwork<4, 4, 1>", "net", Seq(s"{${col+2}, ${row}}", "net".qstr), end=false, extern=true, escape=true)
      genTopMember("StaticNetwork<4, 1>", "statnet", Seq("statnet".qstr), end=false, extern=true, escape=true)
      genTopMember("IdealNetwork<2>", "idealnet", Seq("idealnet".qstr), end=false, extern=true, escape=true)
    } else {
      genTopMember("DynamicNetwork<4, 4, 1>", "net", Seq(s"{4, 4}", "net".qstr), end=false, extern=true, escape=true)
      genTopMember("StaticNetwork<4, 4>", "statnet", Seq("statnet".qstr), end=false, extern=true, escape=true)
      genTopMember("IdealNetwork<2>", "idealnet", Seq("idealnet".qstr), end=false, extern=true, escape=true)
    }
  }

  override def emitNode(n:N) = n match {
    case n:Top => 
      enterTop = true
      emitTopHeader
      visitNode(n)
      enterTop = false
    case n:GlobalContainer => visitNode(n)
    case n => super.emitNode(n)
  }

  override def finPass = {
    // Emit Top Module
    val (externs, members) = topMembers.values.toList.partition { _.extern } 
    val (escapes, _) = externs.partition { _.escape }
    val dutArgs = ("top" +: externs.map { _.name }).map{_.&}.mkString(",")
    val topArgsSig = escapes.map { mem => s"${mem.tp}* ${mem.name}" }.mkString(",\n    ")
    val topArgs = if (escapes.isEmpty) "" else s"(${escapes.map { _.name.& }.mkString(",")})" 

    genTop {
      declareClass(s"""$topName: public Module""") {
        emitln("public:")
        getBuffer("top").foreach { _.flushTo(sw) }
        getBuffer("top-end").foreach { _.flushTo(sw) }
        val memberInits = members.filter { _.args.nonEmpty }.map { mem =>
          s"${mem.name}(${mem.args.mkString(",")})"
        }
        val inits = (s"""Module("$topName")""" +: memberInits).mkString(",\n      ")
        emitBlock(s"""explicit $topName(\n    $topArgsSig\n  ): $inits""") {
          emitln(s"AddChildren({${members.map { _.name.&}.mkString(",")}});")
          emitInit
        }
        val aliasArgs = escapes.map { mem => 
          val alias = mem.alias.getOrElse(mem.name) 
          s""" ((${mem.tp}*)alias["$alias"])"""
        }.mkString(",")
        emitln(s"""$topName(map<string, Module*> alias): $topName($aliasArgs) {}""")
      }
    }

    emitln(s"$topName top$topArgs;")
    getBuffer("extern-end").foreach { _.flushTo(sw) }
    emitln(s"""Module DUT({$dutArgs}, "DUT");""")
    emitln(s"""REPL repl(&DUT, std::cout);""")
    emitln(s"""repl.Command("source script");""")
    emitBEln
    super.finPass
  }

  protected def emitInit = {}

  protected def genTop(block: => Unit) = enterFile(dirName, topFile)(block)

  private def genTopFields(block: => Unit) = enterBuffer("top", level=1)(block)

  private def genTopEndFields(block: => Unit) = enterBuffer("top-end", level=1)(block)

  protected def genExternEnd(block: => Unit) = enterBuffer("extern-end", level=1)(block)

}
