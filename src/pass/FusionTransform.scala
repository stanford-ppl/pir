package pir.pass
import pir.node._
import pir._
import pir.util._
import pirc.util._
import pir.codegen.Logger

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

class FusionTransform(implicit design: PIR) extends Pass with Logger {
  def shouldRun = true 
  import design.pirmeta._

  override lazy val stream = newStream(s"FusionTransform.log")

  def fuseCUs(parent:OuterController, childCU:ComputeUnit) = {
    implicit val child:ComputeUnit = childCU 
    val pcc = localCChainOf(parent)
    val ccc = localCChainOf(child)
    dprintln(s"moving counters=${pcc.counters.mkString(",")} in $pcc from $parent to $child")
    dprintln(s"before=${child.cchains.map{ cc => s"$cc(${cc.counters.mkString(",")})"}}")
    pcc.counters.reverseIterator.foreach { ctr =>
      val nctr = Counter(ctr.name, pcc)(child, design)
      nctr.clone(ctr)
      ccc.addOuterCounter(nctr)
    }
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
