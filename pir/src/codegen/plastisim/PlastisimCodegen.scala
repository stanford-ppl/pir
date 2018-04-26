package pir.codegen

import pir.node._
import pir.pass._
import spade.node._
import prism.util._

import prism.collection.mutable._

trait PlastisimCodegen extends PIRCodegen with FileManager with PlastisimUtil {
  import pirmeta._
  import spademeta._

  lazy val PLASTISIM_HOME = sys.env.get("PLASTISIM_HOME")

  override def runPass = {
    super.runPass // traverse dataflow graph and call emitNode on each node
    linkGroupOf.values.toSet.foreach { link => emitLink(link) }
  }

  override def finPass = {
    super.finPass
    val relativePath = s"configs/gen/${compiler}/${fileName}" 
    PLASTISIM_HOME.fold {
      warn(s"Specify PLASTISIM_HOME environmental variable to generate to PLASTISIM_HOME/${relativePath}")
    } { PLASTISIM_HOME =>
      val genPath = s"$PLASTISIM_HOME/${relativePath}"
      mkdir(dirName(genPath))
      copyFile(outputPath, genPath)
    }
  }

  override def emitNode(n:N) = n match {
    case n:Node => emitNetworkNode(n)
    case n => super.visitNode(n)
  }

  def emitNetworkNode(n:ContextEnable):Unit
  def emitLink(n:Link):Unit

}
