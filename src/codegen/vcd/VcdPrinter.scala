package pir.codegen

import pir._
import spade._
import spade.node._
import spade.traversal._
import spade.util.{quote => _, _}
import pirc.util._
import spade.simulation._
import pirc._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

abstract class VcdPrinter(implicit val sim:Simulator) extends Printer with SpadeVcdDeclarator {
  import sim.util._ 

  def qv(x:Any):String = x match {
    case x:SingleValue => qv(x.value)
    case Some(x:Float) => qv(x.toInt) //TODO
    case Some(x:Int) => qv(x)
    case Some(false) => "b0"
    case Some(true) => "b1"
    case None => "bx"
    case x:Int => s"b${x.toBinaryString}"
    case x => 
      warn(s"Don't know how to qv($x)")
      s"$x"
  }

  def quote(n:Any):String = {
    val str = sim.quote(n)
    str.replace(".","_").replace("[","(").replace("]",")").replace(" ","")
  }

  def end = {
    emitln(s"$$end")
  }

  def emitkv(key:String, value:String) = {
    emitln(s"$$$key  $value  $$end")
  }

  def declareAll:Unit

  def emitSignals:Unit

  def emitHeader = {
    val now = java.util.Calendar.getInstance().getTime() 
    val format = new java.text.SimpleDateFormat("MMM dd yyyy, hh:mm:ss")
    emitkv("date", format.format(now))
    emitkv("version", "Spade Simulator")
    emitkv("timescale", s"${sim.period} ns")
    declareAll
    emitln("enddefinitions $end")
    emitln("$dumpvars")
    emitSignals
  }

  def name(io:IO[_<:PortType,_<:Module], i:Option[Int] = None):String = {
    val cm = ListBuffer[String]()
    io match {
      case io:Input[_,_] =>
        if (fimap.contains(io)) cm += s"f-${quote(fimap(io).src)}"
      case io:Output[_,_] =>
        val is = io.fanOuts.filter(i => fimap.get(i).fold(false){ _ == io }).map(_.src)
        if (is.nonEmpty) cm += s"t-${is.map(quote).mkString(",")}"
    }
    io match {
      case io:GlobalInput[_, _] =>
        val os = io.fanIns.filter(o => fimap.get(o.asGlobal.ic).fold(false) { _ == io.ic} )
        if (os.nonEmpty) cm += s"t-${os.map(quote).mkString(",")}"
      case io:GlobalOutput[_, _] =>
        if (fimap.contains(io.ic)) cm += s"f-${quote(fimap(io.ic).src)}"
      case _ =>
    }
    val nm = s"${io}${i.map(i => s"_$i").getOrElse("")}"
    if (cm.nonEmpty) s"${nm}_${cm.mkString("_")}"
    else s"$nm"
  }
  
  def name(value:Value):String = {
    quote(value)
  }

  def id(node:Node) = {
    s"n${node.id}"
  }

  def declare(m:String)(finPass: => Unit):Unit = {
    emitkv(s"scope module", m)
    finPass
    emitln(s"$$upscope $$end")
  }

  def declare(io:IO[_<:PortType,_<:Module], name:Option[String]=None):Unit = {
    declare(io.v, name)
  }

  def declare(value:Value, nm:Option[String]):Unit = {
    val sname = nm.getOrElse(name(value))
    value match {
      case value:Bus =>
        if (value.busWidth>1) emitkv(s"scope module", sname)
        value.foreachv { 
          case (v,i) => declare(v, nm.map { n => s"$n($i)"}) 
        } { valid => declare(valid, nm.map { n => s"${n}_valid" }) }
        if (value.busWidth>1) emitln(s"$$upscope $$end")
      case Word(wordWidth) if wordWidth == 1 =>
        emitVar("wire", wordWidth, id(value), sname)
      case Word(wordWidth) if wordWidth <= 32 =>
        emitVar("integer", wordWidth, id(value), sname)
      case Bit() =>
        emitVar("wire", 1, id(value), sname)
    }
  }

  def emitVar(tpe:String, bitWidth:Int, id:String, name:String) = {
    emitkv("var", s"$tpe $bitWidth $id $name")
  }

  def emitTime = {
    emitln(s"#${sim.cycle}")
  }

  def emitValue(io:IO[_<:PortType, _]):Unit = {
    //sim.dprintln(s"emitting ${sim.quote(io.v)} #${sim.cycle} changed=${io.changed}")
    //if (!io.changed) return
    emitValue(io.v)
  }

  def emitValue(value:Value):Unit = {
    value match {
      case p@Bus(busWidth, _) =>
        p.value.zipWithIndex.foreach { case (vv, i) => emitValue(vv) }
        emitValue(p.valid)
      case p@Word(wordWidth) =>
        emitln(s"${qv(p)} ${id(p)}")
      case p@Bit() =>
        emitln(s"${qv(p)} ${id(p)}")
    }
  }

}
