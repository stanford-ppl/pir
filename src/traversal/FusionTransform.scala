package pir.graph.traversal
import pir.graph._
import pir._
import pir.misc._
import pir.graph.mapper.PIRException

import scala.collection.mutable.Set
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

class FusionTransform(implicit val design: Design) extends Traversal{

  override def traverse:Unit = {
    design.top.outerCUs.foreach { pcu =>
      // TODO At the moment no need to consider two parallel CUs since multiple last stages are not supported
      if (pcu.children.size==1) {
        pcu.children.head match {
          case sp:StreamPipeline =>
          case _ =>
            implicit val ccu:ComputeUnit = pcu.children.head 
            assert(ccu.isHead && ccu.isLast)
            val ccchain = ccu.localCChain
            val pcchain = pcu.localCChain
            // Handling Remote Copies of counters
            pcchain.copied.foreach { pcp =>
              val cpccu = pcp.ctrler.asInstanceOf[InnerController]
              pcu match {
                // Copy ancesstor outer controller because used in datapath 
                case cu:OuterController if cpccu.ancestors.contains(cu) && (cu.inner!=cpccu)=> 
                  val ancestors = cpccu.ancestors
                  val pcuPre = ancestors(ancestors.indexOf(pcu)-1)
                  val icc = cpccu.getCopy(pcuPre.localCChain)
                  pcp.counters.reverseIterator.foreach { ctr =>
                    ctr.cchain(icc)
                    icc.addOuterCounter(ctr)
                  }
                  cpccu.removeCChain(pcp)
                // Copy outer controller of the writer for write addr calculation
                case cu:OuterController if !cpccu.ancestors.contains(cu) =>
                  val icc = cpccu.getCopy(ccchain)
                  pcp.counters.reverseIterator.foreach { ctr =>
                    ctr.cchain(icc)
                    icc.addOuterCounter(ctr)
                  }
                  cpccu.removeCChain(pcp)
                case _ =>
              }
            }
            // Copying from inner most to outer most
            ccu match {
              case ccu:InnerController => // ccu might already have a copy of pcchain 
                ccu.getCopy(pcchain).counters.reverseIterator.foreach { ctr =>
                  ctr.cchain(ccchain)
                  ccchain.addOuterCounter(ctr)
                }
                ccu.removeCChainCopy(pcchain)
              case ccu:OuterController =>
                pcchain.counters.reverseIterator.foreach { ctr =>
                  val cp = Counter(ccchain)(ccu, design)
                  cp.copy(ctr)
                  ccchain.addOuterCounter(cp)
                }
                ccu.removeCChainCopy(pcchain)
                val iccchain = ccu.inner.getCopy(ccchain)
                val ipcchain = ccu.inner.getCopy(pcchain)
                ipcchain.counters.reverseIterator.foreach { ctr =>
                  val cp = Counter(iccchain)(ccu.inner, design)
                  cp.copy(ctr)
                  iccchain.addOuterCounter(cp)
                }
                ccu.inner.removeCChainCopy(pcchain)
            }
            ccu.parent(pcu.parent)
            pcu.parent.addChildren(ccu) 
            pcu.parent.removeChildren(pcu)
            pcu.removeChildren(ccu)
            pcu.parent(null)
            design.removeNode(pcu)
        }
      }
    }
    ForwardRef.collectOuters
  } 

  def finPass = {
    info("Finishing fusion transformation")
  }

}
