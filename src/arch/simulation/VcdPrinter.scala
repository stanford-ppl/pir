package pir.plasticine.simulation

import pir._
import pir.mapper.PIRMap
import pir.codegen.Printer
import pir.pass.Pass
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.plasticine.traversal._
import pir.exceptions.PIRException
import pir.plasticine.util._

import scala.collection.mutable.Map
import scala.collection.mutable.ListBuffer

class VcdPrinter(sim:Simulator)(implicit design: Design) extends Printer {
  override lazy val stream = newStream("sim.vcd") 
  implicit lazy val spade:Spade = design.arch

  lazy val fimap = sim.mapping.fimap

  val tracking = ListBuffer[Simulatable]()

  def end = {
    emitln(s"$$end")
  }

  def emitkv(key:String, value:String) = {
    emitln(s"$$$key  $value  $$end")
  }

  def emitVar(tpe:String, bitWidth:Int, id:String, name:String) = {
    emitkv("var", s"$tpe $bitWidth $id $name")
  }

  def emitHeader = {
    val now = java.util.Calendar.getInstance().getTime() 
    val format = new java.text.SimpleDateFormat("MMM dd yyyy, hh:mm:ss")
    emitkv("date", format.format(now))
    emitkv("version", "Spade Simulator")
    emitkv("timescale", s"${sim.period} ns")
    emitModules
    emitln("enddefinitions $end")
    emitln("$dumpvars")
    emitSignals
  }

  def emitModules = {
    declarator.traverse
  }

  def emitModule(m:Module)(finPass: => Unit) = {
    emitkv(s"scope module", s"${quote(m)}")
    m match {
      case m:NetworkElement if tracking.contains(m) =>
        emitkv(s"scope module", "sio")
        m.scalarIO.ios.foreach { io => emitIO(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "vio")
        m.vectorIO.ios.foreach { io => emitIO(io) }
        emitln(s"$$upscope $$end")
        emitkv(s"scope module", "cio")
        m.ctrlIO.ios.foreach { io => emitIO(io) }
        emitln(s"$$upscope $$end")
      case m:Simulatable if tracking.contains(m) => m.ios.foreach { io => emitIO(io) }
      case _ =>
    }
    finPass
    emitln(s"$$upscope $$end")
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

  def emitIO(io:IO[_<:PortType,_<:Module]) = {
    io.tp match {
      case Bus(busWidth, Word(wordWidth)) =>
        (0 until busWidth).foreach { i => emitVar("integer", wordWidth, s"${io}_$i", s"${name(io, Some(i))}") }
      case Bus(busWidth, Bit()) =>
        emitVar("wire", busWidth, s"$io", s"${name(io)}")
      case Word(wordWidth) if wordWidth == 1 =>
        emitVar("wire", wordWidth, s"$io", s"${name(io)}")
      case Word(wordWidth) if wordWidth <= 32 =>
        emitVar("integer", wordWidth, s"$io", s"${name(io)}")
      case Bit() =>
        emitVar("wire", 1, s"$io", s"${name(io)}")
    }
  }

  def emitTime = {
    emitln(s"#${sim.cycle}")
  }

  def qV(x:Option[_]):String = x match {
    case Some(true) => "1"
    case Some(false) => "0"
    case Some(f:Float) => s"${f.toInt}"
    case Some(x) => s"$x"
    case None => "x"
  }

  def emitValue(io:IO[_, _]):Unit = {
    val v = io.v
    if (!v.changed) return
    v.value match {
      case p@Bus(busWidth, Word(wordWidth)) =>
        p.value.zipWithIndex.foreach { case (vv, i) => emitln(s"${vv.s}${io}_$i") }
      case p@Bus(busWidth, Bit()) =>
        emitln(s"b${p.s} $io")
      case p@Word(wordWidth) =>
        emitln(s"${p.s}${io}")
      case p@Bit() =>
        emitln(s"b${p.s} ${io}")
    }
  }

  def emitSignals = {
    emitTime
    tracking.foreach { m =>
      m.ios.foreach { io => emitValue(io) }
    }
  }

  val declarator = new Traversal {
    override def visitNode (node:Node): Unit = node match {
      case node:Primitive if (!tracking.contains(node)) => super.visitNode(node)
      case node:Module => emitModule(node) { super.visitNode(node) }
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

  val adder = new Traversal {
    override def visitNode (node:Node): Unit = {
      node match {
        case node:GlobalIO[_,_] =>
        case node:Simulatable if !tracking.contains(node) && isMapped(node) => 
          sim.dprintln(s"tracking $node")
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
