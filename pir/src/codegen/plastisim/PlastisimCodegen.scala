package pir
package codegen

import pir.node._
import pir.pass._
import prism.util._

trait PlastisimCodegen extends PIRCodegen with FileManager with PlastisimUtil {
  import pirmeta._
  import spademeta._

  lazy val PLASTISIM_HOME = sys.env.get("PLASTISIM_HOME")

  lazy val relativePath = s"configs/gen/${compiler}/${fileName}" 

  def copyGeneratedFile = {
    PLASTISIM_HOME.fold {
      warn(s"Specify PLASTISIM_HOME environmental variable to generate to PLASTISIM_HOME/${relativePath}")
    } { PLASTISIM_HOME =>
      val genPath = s"$PLASTISIM_HOME/${relativePath}"
      mkdir(dirName(genPath))
      copyFile(outputPath, genPath)
    }
  }

  override def finPass = {
    super.finPass
    copyGeneratedFile
  }

}
