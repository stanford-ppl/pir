package pir.mapper
import pir.graph._
import pir._
import pir.util._
import pir.util.misc._
import pir.exceptions._
import pir.codegen.Logger
import pir.spade.graph.{SRAM => _, Top => _, Const => _, _}

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.Map

class DelayMapper(implicit val design: Design) extends Mapper {
  def shouldRun = Config.ctrl && Config.mapping

  val typeStr = "DelayMapper"
  import pirmeta._
  import spademeta._

  def map(m:M):M = {
    var mp = m
    design.top.ctrlers.foreach {
      case cu:MemoryPipeline =>
        // Set delay
        val pmcu = mp.pmmap(cu)
        val rstages = pmcu.stages.filter { pst => mp.pmmap.get(pst).fold(false) { st => forRead(st) } }
        val wstages = pmcu.stages.filter { pst => mp.pmmap.get(pst).fold(false) { st => forWrite(st) } }
        val ridxs = rstages.map(_.index)
        val widxs = wstages.map(_.index)
        val rdelay = (if (ridxs.nonEmpty) ridxs.max - ridxs.min else 0)
        val wdelay = if (widxs.nonEmpty) widxs.max - widxs.min else 0
        dprintln(s"$cu(${quote(pmcu)}) rdelay=$rdelay wdelay=$wdelay")
        mp = mp.setCF(pmcu.ctrlBox.readEnDelay, DelayConfig(rdelay))
        mp = mp.setCF(pmcu.ctrlBox.readDoneDelay, DelayConfig(rdelay))
        mp = mp.setCF(pmcu.ctrlBox.writeEnDelay, DelayConfig(wdelay))
        mp = mp.setCF(pmcu.ctrlBox.writeDoneDelay, DelayConfig(wdelay))

        // Set valid
        //mp = mp.setCF(pmcu, ComputeUnitConfig(outputValid = mp.pmmap(cu.ctrlBox.readEnDelay).out))
        (cu.souts ++ cu.vouts).foreach { out =>  
          mp.vomap(out).foreach { pout => 
            validOf(pout) = mp.pmmap(cu.ctrlBox.readEnDelay).out
          }
        }
      case cu:Pipeline =>
        // Set delay
        val pcu = mp.pmmap(cu)
        val delay = pcu.stages.size
        dprintln(s"$cu(${quote(pcu)}) delay=$delay")
        mp = mp.setCF(mp.pmmap(cu.ctrlBox.enDelay), DelayConfig(delay))
        mp = mp.setCF(mp.pmmap(cu.ctrlBox.doneDelay), DelayConfig(delay))

        // Set valid
        (cu.souts ++ cu.vouts).foreach { out =>
          val in = out.readers.head
          in.ctrler match {
            case top:Top => 
              mp.vomap(out).foreach { pout => validOf(pout) = mp.pmmap(cu.ctrlBox.doneDelay).out }
            case _ =>
              if (collectOut[FIFO](in).nonEmpty)
                mp.vomap(out).foreach { pout => validOf(pout) = mp.pmmap(cu.ctrlBox.enDelay).out }
              else if (collectOut[MultiBuffer](in).nonEmpty)
                mp.vomap(out).foreach { pout => validOf(pout) = mp.pmmap(cu.ctrlBox.doneDelay).out }
          }
        }
      case cu:Top =>
        val pcu = mp.pmmap(cu)
        (cu.souts ++ cu.vouts).foreach { out =>  
          mp.vomap(out).foreach { pout => validOf(pout) = pcu.ctrlBox.command }
        }
      case _ =>
    }
    mp
  }

}
