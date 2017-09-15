package pir.pass
import pir.graph._
import pir._
import pir.util._
import pir.util.misc._
import pir.exceptions._
import pir.codegen.Logger

import scala.collection.mutable.Set
import scala.collection.immutable.{Set => ISet}
import scala.collection.mutable.Map

class DelayAnalyzer(implicit design: Design) extends Pass with Logger {
  def shouldRun = (Config.mapping && Config.ctrl)
  import spademeta._
  import pirmeta._

  override lazy val stream = newStream(s"DelayAnalyzer.log")

  addPass (canRun=design.pirMapping.succeeded) {
    val mp = design.mapping.get
    design.top.ctrlers.foreach {
      case cu:MemoryPipeline =>
        val pmcu = mp.pmmap(cu)
        val rstages = pmcu.stages.filter { pst => mp.pmmap.get(pst).fold(false) { st => forRead(st) } }
        val wstages = pmcu.stages.filter { pst => mp.pmmap.get(pst).fold(false) { st => forWrite(st) } }
        val ridxs = rstages.map(_.index)
        val widxs = wstages.map(_.index)
        val rdelay = (if (ridxs.nonEmpty) ridxs.max - ridxs.min else 0)
        val wdelay = if (widxs.nonEmpty) widxs.max - widxs.min else 0
        dprintln(s"$cu(${quote(pmcu)}) rdelay=$rdelay wdelay=$wdelay")
        delayOf(pmcu.ctrlBox.readEnDelay) = rdelay
        delayOf(pmcu.ctrlBox.readDoneDelay) = rdelay
        delayOf(pmcu.ctrlBox.writeEnDelay) = wdelay
        delayOf(pmcu.ctrlBox.writeDoneDelay) = wdelay
        (cu.souts ++ cu.vouts).foreach { out =>  
          mp.vomap(out).foreach { pout => validOf(pout) = mp.pmmap(cu.ctrlBox.readEnDelay).out }
        }
      case cu:Pipeline =>
        val pcu = mp.pmmap(cu)
        val delay = pcu.stages.size
        dprintln(s"$cu(${quote(pcu)}) delay=$delay")
        delayOf(mp.pmmap(cu.ctrlBox.enDelay)) = delay
        delayOf(mp.pmmap(cu.ctrlBox.doneDelay)) = delay
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
  } 

}
