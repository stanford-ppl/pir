package pir
package codegen

import pir.node._
import prism.graph._
import prism.codegen._
import spade.param._
import scala.collection.mutable

trait TungstenTopGen extends TungstenCodegen {

  lazy val topHeader = s"$topName.h"

  def emitTopHeader = {
    genTop {
      emitln(
"""
#ifndef __TOP_H__
#define __TOP_H__

#include "top_import.h"

using namespace std;

""")
      if (config.asModule) {
        val hostio = s"${topName}_hostio.h"
        withOpen(buildPath(dirName,hostio), false) {
          getLines(buildPath(dirName, s"hostio.h")).foreach { line =>
            emitln(line.replace("AllocAllMems", s"AllocAllMems_${topName}"))
          }
        }
      } 
    }
  }
  
  // (type, name, constructor args, extern, escape)
  // extern: if generated as module, these members are instantiated from outside of top module
  // escape: if generated as module, these members are passed in to the top module
  case class TopMember(tp:String, name:String, args:Seq[String], end:Boolean, extern:Boolean, escape:Boolean, alias:Option[String])
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
    topMembers += name -> TopMember(interfaceTp, name, args, end, ext, escape & ext, alias)
  }

  private var enterTop = false
  override def isPointer(x:Any) = {
    x.to[String].flatMap { x =>
      if (enterTop)
        topMembers.get(x).flatMap { mem =>
          if (mem.escape) Some(true) else None
        }
      else if (x == "top") Some(true)
      else None
    }.getOrElse(super.isPointer(x))
  }

  override def initPass = {
    super.initPass
    emitln(s"""#ifndef __DUT_H__""")
    emitln(s"""#define __DUT_H__""")

    emitln(s"""#include "$topHeader"""")
    emitln(s"""using namespace std;""")
    emitBSln("void RunAccel()")
    if (!noPlaceAndRoute) {
      val pattern = spadeParam.pattern.as[GridPattern]
      val row = pattern.row
      val col = pattern.col
      genTopMember("DynamicNetwork<4, 8, 1>", "net", Seq(s"{${col+2}, ${row+config.option[Int]("add-row")}}", "net".qstr), end=false, extern=true, escape=true)
    } else {
      genTopMember("DynamicNetwork<4, 8, 1>", "net", Seq(s"{4, 4}", "net".qstr), end=false, extern=true, escape=true)
    }
    genTopMember("StaticNetwork<4, 1>", "statnet", Seq("statnet".qstr), end=false, extern=true, escape=true)
    genTopMember("IdealNetwork<2>", "idealnet", Seq("idealnet".qstr), end=false, extern=true, escape=true)
    genTopMember("NetworkLinkManager", "netman", Seq("netman".qstr), end=false, extern=true, escape=true)
    genTopCpp {
      emitln(s"""#include "$topHeader"""")
    }
  }

  override def emitNode(n:N) = n match {
    case n:Top => 
      enterTop = true
      emitTopHeader
      visitNode(n)
      enterTop = false
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
        val memberInits = members.filter { _.args.nonEmpty }.sortBy { _.end }.map { mem =>
          s"${mem.name}(${mem.args.mkString(",")})"
        }
        val inits = (s"""Module("$topName")""" +: memberInits).mkString(",\n      ")
        emitln(s"""explicit $topName(\n    $topArgsSig\n  );""")
        genTopCpp {
          emitBlock(s"""$topName::$topName(\n    $topArgsSig\n  ): $inits""") {
            emitln(s"AddChildren({${members.map { _.name.&}.mkString(",")}});")
            getBuffer("top-init").foreach { _.flushTo(sw) }
            //emitInit
          }
        }
        val aliasArgs = escapes.map { mem => 
          val alias = mem.alias.getOrElse(mem.name) 
          s""" ((${mem.tp}*)alias["$alias"])"""
        }.mkString(",")
        emitln(s"""$topName(map<string, Module*> alias): $topName($aliasArgs) {}""")
      }
      getBuffer("top-after-module").foreach { _.flushTo(sw) }
      emitln(s"#endif /* __TOP_H__ */")
    }

    emitln(s"$topName* top = new $topName$topArgs;")
    getBuffer("extern-end").foreach { _.flushTo(sw) }
    emitln(s"""Module DUT({$dutArgs}, "DUT");""")
    emitln(s"""REPL repl(&DUT, std::cout);""")
    emitln(s"""repl.Command("source script");""")
    emitln(s"""delete top;""")
    emitBEln
    emitln(s"""#endif /* __DUT_H__ */""")
    super.finPass
  }

  //protected def emitInit = {}

  protected def genTop(block: => Unit) = enterFile(dirName, topHeader)(block)

  protected def genTopCpp(block: => Unit) = enterFile(dirName, "Top.cc")(block)

  protected def genTopFields(block: => Unit) = enterBuffer("top", level=1)(block)

  private def genTopEndFields(block: => Unit) = enterBuffer("top-end", level=1)(block)

  protected def genExternEnd(block: => Unit) = enterBuffer("extern-end", level=1)(block)

  protected def genTopInit(block: => Unit) = enterBuffer("top-init", level=1)(block)

  protected def genTopAfterModule(block: => Unit) = enterBuffer("top-after-module")(block)

}
