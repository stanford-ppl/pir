package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Printer
import pir.pass.Pass
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.traversal._
import pir.plasticine.util.{quote => _, _}
import pir.exceptions.PIRException

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class VcdPrinter(implicit sim:Simulator, design: Design) extends Printer {
  lazy val spademeta:SpadeMetadata = design.arch
  override lazy val stream = newStream("sim.vcd") 
  implicit lazy val spade:Spade = design.arch
  import sim.quote

  lazy val fimap = sim.mapping.fimap

  val tracking = ListBuffer[Simulatable]()

  def qv(v:SingleValue):String = qv(v.value)

  def qv(x:Option[_]):String = x match {
    case Some(true) => "1"
    case Some(false) => "0"
    case Some(f:Float) => s"${f.toInt}" //TODO
    case Some(x) => s"$x"
    case None => "x"
  }

  def quote(n:Any):String = {
    val str = sim.quote(n)
    str.replace(".","_").replace("[","(").replace("]",")")
  }

  def end = {
    emitln(s"$$end")
  }

  def emitkv(key:String, value:String) = {
    emitln(s"$$$key  $value  $$end")
  }

  def emitHeader = {
    val now = java.util.Calendar.getInstance().getTime() 
    val format = new java.text.SimpleDateFormat("MMM dd yyyy, hh:mm:ss")
    emitkv("date", format.format(now))
    emitkv("version", "Spade Simulator")
    emitkv("timescale", s"${sim.period} ns")
    declarator.traverse
    emitln("enddefinitions $end")
    emitln("$dumpvars")
    emitSignals
  }

  val declarator = new Traversal {
    override def visitNode (node:Node): Unit = node match {
      case node:Primitive if (!tracking.contains(node)) => super.visitNode(node)
      case node:Module => declare(node) { super.visitNode(node) }
      case _ => super.visitNode(node)
    }
    override def traverse(implicit spade: Spade):Unit = {
      visitNode(spade.top)
      emitkv(s"scope module", "pcus")
      spade.pcus.foreach(visitNode)
      emitln(s"$$upscope $$end")
      emitkv(s"scope module", "mcus")
      spade.mcus.foreach(visitNode)
      emitln(s"$$upscope $$end")
      emitkv(s"scope module", "scus")
      spade.scus.foreach(visitNode)
      emitln(s"$$upscope $$end")
      emitkv(s"scope module", "ocus")
      spade.ocus.foreach(visitNode)
      emitln(s"$$upscope $$end")
      emitkv(s"scope module", "mcs")
      spade.mcs.foreach(visitNode)
      emitln(s"$$upscope $$end")
      spade match {
        case spade:SwitchNetwork => 
          emitkv(s"scope module", "sbs")
          spade.sbs.foreach(visitNode)
          emitln(s"$$upscope $$end")
        case _ =>
      }
    } 
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

  def declare(m:Module)(finPass: => Unit):Unit = {
    emitkv(s"scope module", s"${quote(m)}")
    m match {
      case m:NetworkElement if tracking.contains(m) =>
        emitkv(s"scope module", "sio")
        m.scalarIO.ios.foreach { io => declare(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "vio")
        m.vectorIO.ios.foreach { io => declare(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "cio")
        m.ctrlIO.ios.foreach { io => declare(io) }
        emitln(s"$$upscope $$end")
      case m:Simulatable if tracking.contains(m) => m.ios.foreach { io => declare(io) }
      case _ =>
    }
    finPass
    emitln(s"$$upscope $$end")
  }

  def declare(io:IO[_<:PortType,_<:Module]):Unit = {
    declare(io.v)
  }

  def declare(value:Value):Unit = {
    value match {
      case value:BusValue =>
        value.foreach { case (v,i) => declare(v) }
      case Word(wordWidth) if wordWidth == 1 =>
        emitVar("wire", wordWidth, id(value), name(value))
      case Word(wordWidth) if wordWidth <= 32 =>
        emitVar("integer", wordWidth, id(value), name(value))
      case Bit() =>
        emitVar("wire", 1, id(value), name(value))
    }
  }

  def emitVar(tpe:String, bitWidth:Int, id:String, name:String) = {
    emitkv("var", s"$tpe $bitWidth $id $name")
  }

  def emitTime = {
    emitln(s"#${sim.cycle}")
  }

  def emitValue(io:IO[_<:PortType, _]):Unit = {
    if (!io.changed) return
    emitValue(io.v)
  }

  def emitValue(value:Value):Unit = {
    value match {
      case p@Bus(busWidth, _) =>
        p.value.zipWithIndex.foreach { case (vv, i) => emitValue(vv) }
      case p@Word(wordWidth) =>
        emitln(s"${qv(p)}${id(p)}")
      case p@Bit() =>
        emitln(s"b${qv(p)} ${id(p)}")
    }
  }

  def emitSignals = {
    emitTime
    tracking.foreach { m =>
      m.ios.foreach { io => emitValue(io) }
    }
  }

  val adder = new Traversal {
    override def visitNode (node:Node): Unit = {
      node match {
        case node:GlobalIO[_,_] =>
        case node:Simulatable if !tracking.contains(node) && isMapped(node) => 
          sim.dprintln(s"tracking ${sim.quote(node)}")
          tracking += node
        case _ =>
      }
      super.visitNode(node)
    }
  } 

  def addModule(m:Simulatable) = {
    adder.visitNode(m)
  }

  def addAll = {
    sim.dprintln(s"${sim.mapping.ctmap.keys}")
    adder.traverse
    val sb = spade.asInstanceOf[SwitchNetwork].sbs.head
    val out = sb.ctrlIO.outs.head
  }

}
