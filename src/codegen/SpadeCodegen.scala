package pir.codegen

import pir.Design
import pir.plasticine.main._
import pir.plasticine.graph._
import pir.util._
import pir.plasticine.util._

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import java.io.OutputStream
import java.io.File

class SpadeCodegen(implicit design: Design) extends Codegen  {
  def shouldRun = true

  lazy val dir = sys.env("PLASTICINE_HOME") + "/arch"

  override val stream:OutputStream = newStream(dir, s"Plasticine_${design.arch}.scala") 
  
  implicit def spade = design.arch.asInstanceOf[SwitchNetwork]
  def numRows = spade.numRows
  def numCols = spade.numCols

  override def initPass = {
    super.initPass
    emitHeader
  }

  def traverse = {
  }
  
  override def finPass = {
    super.finPass
    close
  }

  def emitLambda(s:String, ins:Any*)(block: =>Any) = { 
    emitBS(s"$s ")
    val input = if (ins.size==1) { s"${ins.head}" } else { s"case (${ins.mkString(",")})" }
    emitln(s"${input} =>")
    block
    emitBEln
  }

  def emitComment(s:Any) = {
    emitln(s"// $s")
  }

  def emitHeader = {
    emitln(s"package plasticine.arch")
    //emitln(s"import Chisel._")
    emitln(s"import plasticine.templete.dummy._")
    emitln(3)

    emitBlock(s"object ${design.arch} extends Plasticine") {
  
      emitBlock(s"val archParam = new ArchParam") {
        emitln(s"val wordWidth = ${spade.wordWidth}")
        emitln(s"val numLanes = ${spade.numLanes}")
        emitln(s"val numRows = ${spade.numRows}")
        emitln(s"val numCols = ${spade.numCols}")
        emitComment(s"val numMCs = TODO")
      }

      val pcu = spade.pcus.head
      emitBlock(s"val pcuParam = new CUParam") {
        emitln(s"val numCtrs = ${pcu.ctrs.size}")
        emitComment(s"val numScalarReg = ${}")
      }
    
      emitln(s"val ${spade.top} = this.top")

      val cuArray = spade.cuArray
      cuArray.zipWithIndex.foreach { case (row, i) =>
        row.zipWithIndex.foreach { case (cu, j) =>
          val temp = cu match {
            case mcu:MemoryComputeUnit => s"new ComputeUnit(pcuParam)"
            case cu:ComputeUnit => s"new MemoryComputeUnit(mcuParam)"
          }
          emitln(s"val $cu = Module($temp))")
        }
      }

      val scus = spade.scus
      scus.foreach { scu =>
        emitln(s"val $scu = Module(new ScalarComputeUnit(scuParam)))")
      }

      val mcs = spade.mcs
      //emitLambda(s"val mcs = List.tabulate(${2*(numRows+1)})", "i",) {
        //mcs.zipWithIndex.foreach { case (row, i) =>
          //emitln(s"Module())")
        //}
      //}
      
      val sbs = spade.sbs
      sbs.foreach { sb =>
        emitln(s"val $sb = Module(new Switch(switchParam)))")
      }
      
      emitComment("VectorNetwork Connection")
      spade.pnes.foreach { pne =>
        pne.vectorIO.outs.foreach { out =>
          out.fanOuts.foreach { in =>
            emitln(s"connectVector($pne, ${in.src}, ${out.index}, ${in.index})")
          }
        }
      }
      emitComment("ScalarNetwork Connection")
      spade.pnes.foreach { pne =>
        pne.scalarIO.outs.foreach { out =>
          out.fanOuts.foreach { in =>
            emitln(s"connectScalar($pne, ${in.src}, ${out.index}, ${in.index})")
          }
        }
      }
      emitComment("ControlNetwork Connection")
      spade.pnes.foreach { pne =>
        pne.ctrlIO.outs.foreach { out =>
          out.fanOuts.foreach { in =>
            emitln(s"connectControl($pne, ${in.src}, ${out.index}, ${in.index})")
          }
        }
      }

    }

  }

}
