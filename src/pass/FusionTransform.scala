package pir.pass
import pir.graph._
import pir._
import pir.util._
import pir.util.misc._

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

class FusionTransform(implicit design: Design) extends Pass{
  def shouldRun = true 

  // Handling Remote Copies of counters
  def swapCopy(parent:OuterController, childCU:ComputeUnit) = {
    implicit val child:ComputeUnit = childCU 
    val ccchain = child.localCChain
    val pcchain = parent.localCChain
    pcchain.copied.foreach { pcp =>
      val copiercu = pcp.ctrler.asInstanceOf[InnerController]
      copiercu match {
        // Copy outer controller of the writer for write addr calculation
        case copiercu:MemoryPipeline => 
          val icc = copiercu.getCopy(ccchain)
          pcp.counters.reverseIterator.foreach { ctr =>
            ctr.cchain(icc)
            icc.addOuterCounter(ctr)
          }
          copiercu.removeCChain(pcp)

        // Copy ancesstor outer controller because used in datapath 
        case copiercu if copiercu.ancestors.contains(parent) =>
          val ancestors = copiercu.ancestors
          val pcuPre = ancestors(ancestors.indexOf(parent)-1).asInstanceOf[ComputeUnit]
          val icc = copiercu.getCopy(pcuPre.localCChain)
          pcp.counters.reverseIterator.foreach { ctr =>
            ctr.cchain(icc)
            icc.addOuterCounter(ctr)
          }
          copiercu.removeCChain(pcp)
      }
    }
  }
  def fuseCUs(parent:OuterController, childCU:ComputeUnit) = {
    implicit val child:ComputeUnit = childCU 
    val ccchain = child.localCChain
    val pcchain = parent.localCChain
    // Copying from inner most to outer most
    child match {
      case child:InnerController => // ccu might already have a copy of pcchain 
        child.getCopy(pcchain).counters.reverseIterator.foreach { ctr =>
          ctr.cchain(ccchain)
          ccchain.addOuterCounter(ctr)
        }
        child.removeCChainCopy(pcchain)
      case chain:OuterController =>
        pcchain.counters.reverseIterator.foreach { ctr =>
          val cp = Counter(ccchain)(child, design)
          cp.copy(ctr)
          ccchain.addOuterCounter(cp)
        }
        child.removeCChainCopy(pcchain)
        //val iccchain = ccu.inner.getCopy(ccchain)
        //val ipcchain = ccu.inner.getCopy(pcchain)
        //ipcchain.counters.reverseIterator.foreach { ctr =>
          //val cp = Counter(iccchain)(ccu.inner, design)
          //cp.copy(ctr)
          //iccchain.addOuterCounter(cp)
        //}
        //ccu.inner.removeCChainCopy(pcchain)
    }
    child.parent(parent.parent)
    parent.parent.addChildren(child) 
    parent.parent.removeChildren(parent)
    parent.removeChildren(child)
    parent.removeParent
    design.removeNode(parent)
  }

  override def traverse:Unit = {
    design.top.outerCUs.foreach { pcu =>
      if (pcu.children.size==1) {
        pcu.children.head match {
          case sp:StreamPipeline =>
          case cu => 
            swapCopy(pcu, cu)
            fuseCUs(pcu, cu)
        }
      }
    }
    //ForwardRef.collectOuters
  } 

  override def finPass = {
    endInfo("Finishing fusion transformation")
  }

}
