package pir.mapper

import pir._
import pir.util.typealias._

import spade._

import pirc._

import scala.language.existentials
import scala.reflect.runtime.universe._
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait Router extends Mapper {
  import spademeta._

  implicit def design:PIR

  lazy val minHop = 1
  lazy val maxHop = design.arch.top.diameter + 2

  override def debug:Boolean = PIRConfig.debugRouting

  override val exceptLimit = 200

  type I = pir.util.typealias.I
  type O = pir.util.typealias.O
  /* ---- API ------- */
  def filterPCL(cl:CL, prts:List[PCL], m:PIRMap):List[PCL]

  def route(cl:CL, m:M):M

  def ctrler(io:IO):CL
  def from(in:I):O
  def to(out:O):List[I]
  def ins(cl:CL):List[I]
  def outs(cl:CL):List[O]
  def io(prt:PRT):PGrid[PRT]
  def pins(prt:PRT):List[PI[PModule]]
  def pouts(prt:PRT):List[PO[PModule]]
  /* ---- API (END) ------- */

  override def mappingCheck(mapping:M) = {
    design.top.ctrlers.foreach { ctrler =>
      ins(ctrler).foreach { in =>
        if(!mapping.vimap.contains(in))
          throw PIRException(s"${in} in ${ctrler} from ${from(in)} wasn't mapped")
      }
      outs(ctrler).foreach { out =>
        if(out.isConnected && !mapping.vomap.contains(out))
            throw PIRException(s"${out} in ${ctrler} to [${to(out).mkString(",")}] wasn't mapped")
      }
    }
  }

  def failPass(e:Throwable):Unit = if (debug) {
    e match {
      case e:MappingException[_] =>
        logger.closeAndWriteAllBuffers
        breakPoint(e.mapping.asInstanceOf[PIRMap], s"$e", true)
      case e:PassThroughException[_] =>
      case e:Throwable => println(e)
    }
  } else {
    (e:Throwable) => ()
  }
}

trait VectorRouter extends Router {
  override val typeStr = "VecRouter"

  def io(prt:PRT):PGrid[PRT] = prt.vectorIO

  def ctrler(io:IO):CL = io.ctrler
  def from(in:I):O = in.from
  def to(out:O) = out.to.toList
  def ins(cl:CL) = cl.vins.toList
  def outs(cl:CL) = cl.vouts.toList
  def pins(n:PRT) = n.vins
  def pouts(n:PRT) = n.vouts
}

trait ScalarRouter extends Router {
  override val typeStr = "ScalRouter"

  def io(prt:PRT):PGrid[PRT] = prt.scalarIO

  def ctrler(io:IO):CL = io.ctrler
  def from(in:I):O = in.from
  def to(out:O) = out.to.toList
  def ins(cl:CL) = cl.sins.toList
  def outs(cl:CL) = cl.souts.toList
  def pins(n:PRT) = n.sins
  def pouts(n:PRT) = n.souts
}

trait ControlRouter extends Router {
  override val typeStr = "CtrlRouter"

  def io(prt:PRT):PGrid[PRT] = prt.ctrlIO

  def ctrler(io:IO):CL = io.ctrler
  def from(in:I):O = in.from
  def to(out:O) = out.to.toList
  def ins(cl:CL) = cl.cins.toList
  def outs(cl:CL) = cl.couts.toList
  def pins(n:PRT) = n.cins
  def pouts(n:PRT) = n.couts
}
