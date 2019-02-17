package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._
import prism.util._

trait PlastisimUtil extends PIRPass {
  lazy val psimOut = config.psimOut
  lazy val configName = "psim.conf"
  lazy val configPath = buildPath(psimOut, configName)
  lazy val prouteName = "final.place"
  lazy val proutePath = buildPath(psimOut, prouteName)
  lazy val prouteLinkName = "link.csv"
  lazy val prouteLinkPath = buildPath(psimOut, prouteLinkName)
  lazy val prouteNodeName = "node.csv"
  lazy val prouteNodePath = buildPath(psimOut, prouteNodeName)
  lazy val traceName = "gen_trace.scala"
  lazy val traceRelativePath = s"trace"
  lazy val tracePath = buildPath(psimOut, traceRelativePath)
  lazy val summaryName = "summary.csv"
  lazy val summaryPath = buildPath(psimOut, summaryName)
  lazy val psimLog = buildPath(psimOut, "psim.log")
  lazy val prouteLog = buildPath(psimOut, "proute.log")

  val infCount = 1000000

  implicit class PIRNodePsimOp(n:PIRNode) {
    def constScale:Long = n.scale.get match {
      case Unknown => throw PIRException(s"${n}.scale not statically known")
      case Finite(c) => c
      case Infinite => infCount
    }
    def constCount:Long = n.count.get match {
      case Unknown => throw PIRException(s"${n}.count not statically known")
      case Finite(c) => c
      case Infinite => infCount
    }
  }
}

trait PlastisimCodegen extends PlastisimUtil with Codegen with PIRTraversal with ChildFirstTraversal {
  override def dirName = psimOut
}
