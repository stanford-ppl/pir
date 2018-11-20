package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._

trait PlastisimUtil extends PIRPass {
  lazy val psimOut = buildPath(compiler.outDir, s"../plastisim") 
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

  lazy val PLASTISIM_HOME = sys.env.get("PLASTISIM_HOME").getOrElse(throw PIRException(s"PLASTISIM_HOME is not set"))
  lazy val PLASTIROUTE_HOME = sys.env.get("PLASTIROUTE_HOME").getOrElse(throw PIRException(s"PLASTIROUTE_HOME is not set"))

  def noPlaceAndRoute = spadeParam.isAsic || spadeParam.isP2P

  implicit class PIRNodePsimOp(n:PIRNode) {
    def constScale:Long = n.scale.get.getOrElse(throw PIRException(s"${n}.scale not statically known"))
    def constCount:Long = n.count.get.getOrElse(throw PIRException(s"${n}.count not statically known"))
  }
}

trait PlastisimCodegen extends PlastisimUtil with Codegen with PIRTraversal with ChildFirstTraversal {
  override def dirName = psimOut
}
