package pir
package codegen

import pir.node._
import pir.pass._
import prism.graph._

trait PlastisimUtil extends PIRPass {
  lazy val psimOut = buildPath(compiler.outDir, s"../plastisim") 
  lazy val configName = "psim.conf"
  lazy val configPath = buildPath(psimOut, configName)

  lazy val PLASTISIM_HOME = sys.env.get("PLASTISIM_HOME").getOrElse(throw PIRException(s"PLASTISIM_HOME is not set"))

  def noPlaceAndRoute = spadeParam.isAsic || spadeParam.isP2P

  implicit class PIRNodePsimOp(n:PIRNode) {
    def constScale:Long = n.scale.get.getOrElse(throw PIRException(s"${n}.scale not statically known"))
  }
}
