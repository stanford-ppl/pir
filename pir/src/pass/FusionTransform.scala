package pir.pass

import pir._
import pir.node._

import pirc._
import pirc.util._

class FusionTransform(implicit design: PIR) extends Pass with Logger {
  def shouldRun = true 
  import design.pirmeta._

  override lazy val stream = newStream(s"FusionTransform.log")

  def fuseCUs(parent:OuterController, childCU:Controller) = {
    implicit val child:ComputeUnit = childCU.asInstanceOf[ComputeUnit]
    val pcc = localCChainOf(parent)
    val ccc = localCChainOf(child)
    dprintln(s"moving counters=${pcc.counters.mkString(",")} in $pcc from $parent to $child")
    dprintln(s"before=${child.cchains.map{ cc => s"$cc(${cc.counters.mkString(",")})"}}")
    pcc.counters.reverseIterator.foreach { ctr =>
      val nctr = Counter(ctr.name, pcc)(child, design)
      nctr.clone(ctr, logger=Some(this))
      ccc.addOuterCounter(nctr)
    }
    design.memoryAnalyzer.analyzeNewCC(ccc)
    dprintln(s"after=${child.cchains.map{ cc => s"$cc(${cc.counters.mkString(",")})"}}")
    design.removeNode(parent)
  }

  addPass(canRun=design.optimizer.hasRun) {
    design.top.outerCUs.foreach {
      case pcu if pcu.children.size == 1=>
        pcu.children.head match {
          case cu => 
            dprintln(s"fusing parent=$pcu and child=$cu")
            //swapCopy(pcu, cu) // Don't touch counter after memory ananlyzer
            fuseCUs(pcu, cu)
        }
      case pcu =>
    }
  } 

  override def finPass = {
    endInfo("Finishing fusion transformation")
  }

}
