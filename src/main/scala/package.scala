package pir

import pir.graph._
import graph.mapper._
import codegen.Printer
import scala.language.implicitConversions

package object misc extends Logger {
  implicit def pr_to_inport(pr:PipeReg):InPort = pr.in
  implicit def pr_to_outport(pr:PipeReg):OutPort = pr.out
  implicit def sram_to_outport(sram:SRAM):OutPort = sram.readPort
  implicit def ctr_to_port(ctr:Counter):OutPort = ctr.out
  implicit def const_to_port(const:Const):OutPort = const.out
  implicit def mExcep_to_string(e:MappingException):String = e.toString
  implicit def range_to_bound(r:Range)(implicit design:Design) = r by Const("1d") 
  implicit def sRange_to_bound(r:scala.collection.immutable.Range)(implicit design:Design): (OutPort, OutPort, OutPort) =
    (Const(s"${r.min}i").out, Const(s"${r.max+1}i").out, Const(s"${r.step}i").out)
}

trait Logger extends Printer {
  def dprintln(pred:Boolean, header:Option[String], s:Any):Unit = 
    if (pred) emitln(s"[debug${header.fold("") { h => s"-$h"}}] $s")
  def dprint(pred:Boolean, header:Option[String], s:Any):Unit = 
    if (pred) emit(s"[debug${header.fold("") { h => s"-$h"}}] $s")
  def dprintln(pred:Boolean, header:String, s:Any):Unit = dprintln(pred, Some(header), s) 
  def dprint(pred:Boolean, header:String, s:Any):Unit = dprint(pred, Some(header), s) 
  def dprintln(header:String, s:Any):Unit = dprintln(Config.debug, header, s) 
  def dprint(header:String, s:Any):Unit = dprint(Config.debug, header, s) 
  def dprintln(s:Any):Unit = dprintln(Config.debug, None, s) 
  def dprint(s:Any):Unit = dprintln(Config.debug, None, s)

  def info(s:String) = emitln(s"[pir] ${s}")
}

package object typealias {
  // PIR Nodes 
  type Node = pir.graph.Node
  type CL   = pir.graph.Controller
  type ICL   = pir.graph.InnerController
  type OCL   = pir.graph.OuterController
  type CU   = pir.graph.ComputeUnit
  type TT   = pir.graph.TileTransfer
  type MC = pir.graph.MemoryController
  type PRIM = pir.graph.Primitive
  type Reg = pir.graph.Reg
  type PR = pir.graph.PipeReg
  type SRAM = pir.graph.SRAM
  type CC = pir.graph.CounterChain
  type Ctr   = pir.graph.Counter
  type FU= pir.graph.FuncUnit
  type ST = pir.graph.Stage
  type EST = pir.graph.EmptyStage
  type WAST = pir.graph.WAStage
  type RDST = pir.graph.ReduceStage
  type ACST = pir.graph.AccumStage
  type SI = pir.graph.ScalarIn
  type SO = pir.graph.ScalarOut
  type VI = pir.graph.VecIn
  type VO = pir.graph.VecOut
  type I = pir.graph.Input
  type PT = pir.graph.Port
  type IP   = pir.graph.InPort
  type OP = pir.graph.OutPort
  type CB   = pir.graph.CtrlBox
  type LUT = pir.graph.LUT
  type TOLUT   = pir.graph.TokenOutLUT
  type EnLUT   = pir.graph.EnLUT
  type UC = pir.graph.UDCounter
  type Const   = pir.graph.Const
  type Top = pir.graph.Top
  // Spade Nodes
  type PNode = pir.plasticine.graph.Node
  type PCL = pir.plasticine.graph.Controller
  type PCU = pir.plasticine.graph.ComputeUnit
  type PTT = pir.plasticine.graph.TileTransfer
  type PReg = pir.plasticine.graph.Reg
  type PPR = pir.plasticine.graph.PipeReg
  type PCtr = pir.plasticine.graph.Counter
  type PSRAM = pir.plasticine.graph.SRAM
  type PFU = pir.plasticine.graph.FuncUnit
  type PST = pir.plasticine.graph.Stage
  type PEST = pir.plasticine.graph.EmptyStage
  type PFUST = pir.plasticine.graph.FUStage
  type PWAST = pir.plasticine.graph.WAStage
  type PRDST = pir.plasticine.graph.ReduceStage
  type PSI = pir.plasticine.graph.ScalarIn
  type PSO = pir.plasticine.graph.ScalarOut
  type PPT = pir.plasticine.graph.Port
  type PIP = pir.plasticine.graph.InPort
  type POP = pir.plasticine.graph.OutPort
  type PRMPT = pir.plasticine.graph.RMPort
  type PIB = pir.plasticine.graph.InBus
  type POB = pir.plasticine.graph.OutBus
  type PFIP = pir.plasticine.graph.FUInPort
  type PBIP = pir.plasticine.graph.BusInPort
  type PCB = pir.plasticine.graph.CtrlBox
  type PLUT = pir.plasticine.graph.LUT
  type PEnLUT = pir.plasticine.graph.EnLUT
  type PUC = pir.plasticine.graph.UDCounter
  type PSB = pir.plasticine.graph.SwitchBox
  type Stagable = pir.plasticine.graph.Stagable
  type PConst = pir.plasticine.graph.Const
  type PConstVal = pir.plasticine.graph.ConstVal
  type PTop = pir.plasticine.graph.Top
}
