package pir.pass

import pir.node._
import pir.mapper._
import spade.node._
import prism.collection.mutable
import prism.collection.immutable

class PlastisimVCAllocation(implicit compiler: PIR) extends PIRTraversal with ChildFirstTraversal with UnitTraversal with PlastisimUtil with MappingLogger with BackTracking {
  import pirmeta._

  case class LinkGroup(link:Link)
  val infGraph = new mutable.OneToManyMap[LinkGroup, LinkGroup]()
  type VCMap = immutable.ManyToOneFactorGraph[LinkGroup, Int]

  override def runPass =  {
    traverseNode(compiler.top)
    log(infGraph)
    log(assignVCColor)
  }

  def assignVCColor = {
    var vcmap = immutable.ManyToOneFactorGraph.empty[LinkGroup, Int]
    infGraph.keys.foreach { linkGroup =>
      val linktp = pinTypeOf(linkGroup.link)
      val networkParam = topParam.networkParams.filter { net => bctOf(net) == linktp }.head
      val numVC = networkParam.numVirtualChannel
      vcmap ++= linkGroup -> (0 until numVC).toSet
    }
    log(vcmap)
    // Color the link with most interference first
    val sortedLinks = infGraph.keys.toSeq.sortBy { key => -infGraph(key).size }
    bind[LinkGroup,Int,VCMap](
      pnodes=sortedLinks,
      snodes=(p:LinkGroup, m:VCMap) => m(p).toList.sortBy { case v => -m.freeKeys(v).size }, // use the most flexable color
      init=vcmap,
      bindLambda=assignColor _
    )
  }

  def assignColor(p:LinkGroup, s:Int, m:VCMap):EOption[VCMap] = {
    m.set(p,s).flatMap { vcmap =>
      flatFold[LinkGroup, VCMap](infGraph(p), vcmap) { case (vcmap, interfered) =>
        vcmap.filterNot(interfered) { _ == s }
      }
    }
  }

  override def visitNode(n:N) = n match {
    case n:ContextEnable => 
      val inlinks = inlinksOf(n).map { case (link, reads) => link }
      inlinks.foreach { link =>
        val linkTp = pinTypeOf(link)
        inlinks.foreach {
          case `link` =>
          case other if pinTypeOf(other) == linkTp=> infGraph(LinkGroup(link)) = LinkGroup(other)
          case other =>
        }
      }
    case n => super.visitNode(n)
  }
}
