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
      if (pcu.children.size==1 && !pcu.children.head.isInstanceOf[StreamPipeline]) {
        implicit val ccu:ComputeUnit = pcu.children.head 
        assert(ccu.isHead && ccu.isTail)
        val ccchain = ccu.localCChain
        val pcchain = pcu.localCChain
        // Copying from inner most to outer most
        ccu match {
          case ccu:InnerController => // ccu might already have a copy of pcchain 
            ccu.getCopy(pcchain).counters.reverseIterator.foreach { ctr =>
              ctr.cchain(ccchain)
              ccchain.addOuterCounter(ctr)
            }
            ccu.removeCChainCopy(pcchain)
          case _ =>
            pcchain.counters.reverseIterator.foreach { ctr =>
              val cp = Counter(ccchain)
              cp.copy(ctr)
              ccchain.addOuterCounter(cp)
            }
        }
        ccu.parent(pcu.parent)
        pcu.parent.addChildren(ccu) 
        pcu.parent.removeChildren(pcu)
        pcu.removeChildren(ccu)
        pcu.parent(null)
        ccu.updateDeps(pcu.dependencies)
        pcu.dependeds.foreach { _.updateDep(ccu) }
        pcu.removeDeps
        pcu.removeDepeds
        design.removeNode(pcu)
      }
    }
    ForwardRef.collectOuters
  } 

  def finPass = {
    info("Finishing fusion transformation")
  }

}
